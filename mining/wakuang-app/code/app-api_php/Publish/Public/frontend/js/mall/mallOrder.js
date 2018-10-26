/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(function() {
    $("#province option:first").prop("selected", 'selected');
    if ($("#goodsType").val() == 134) {
        $("#addreshid").hide();
        $(".local").hide();
    }
    $(".default").click(function() {
        $(".default").removeClass("on");
        $(this).addClass("on");
    });

    //保存地址
    $("#saveBtn").live("click", function() {
        addressvalidate();
        var isOK = $("#form_address").valid();
        if (isOK) {
            saveAddress();
        }
    });
    $(".btn-outline").click(function() {
        if ($("#goodsType").val() == 135) {
            if (!$(".default").hasClass("on")) {
                return layer.msg("请选择一个收货地址！");
            }
        }
        var addressID = $(".on").children(".addressID").val();
        var emp = new Object();
        emp.singleNumber = $("#amount").val();
        emp.goodsID = $("#goodsID").val();
        emp.goodsName = $("#goodsName").val();
        emp.addressID = addressID;
        emp.detail=$("#remark").val();
        layer.confirm("您确定要兑换此商品吗？", {title: "提示"}, function() {
            $.ajax({
                type: "post",
                url: "/Shop/mallSave",
                data: {"emp": emp},
                dataType: "json",
                beforeSend: function() {
                    layer.load();
                },
                success: function(data) {
                    layer.hideload();
                    if (data.status == 1) {
                        if ($("#goodsType").val() == 135) {//实物
                            layer.alert("兑换成功，7个工作日内安排商品配送，请保持手机畅通。", function() {
                                window.location.href = "/Shop/mall";
                            });
                        } else {
                            layer.alert("兑换成功，可到“<a href='/Ticket/ticket'>我的卡券</a>”查看。", function() {
                                window.location.href = "/Shop/mall";
                            });
                        }
                    } else if (data.status == 2) {
                        layer.alert(data.msg, {title: "提示"}, function() {
                            window.location.href = "/user/realName.html";
                        });
                    } else if (data.status == 3) {
                        layer.alert(data.msg, {title: "提示"}, function() {
                            window.location.href = "/Project/plist.html";
                        });
                    } else {
                        layer.msg(data.msg);
                    }
                },
                complete: function() {
                    layer.hideload();
                },
                error: function() {
                    layer.hideload();
                }
            });
        });
    });
});
//验证地址表单
function addressvalidate() {
    var phone = $("#phone").val();
    var regex = /^(1(([3|5|7][0-9])|(47)|[8][0123456789]))\d{8}$/;
    $.validator.addMethod("chkphone", function() {
        if (phone.match(regex)) {
            return true;
        } else {
            return false;
        }
    }, "请输入正确的电话号码!");
    $("#form_address").validate({
        rules: {
            contacts: {required: true, maxlength: 15},
            phone: {required: true, chkphone: true},
            province: {required: true},
            city: {required: true},
            district: {required: true},
            address: {required: true, maxlength: 30}
        },
        messages: {
            contacts: {required: "请输入收货人姓名！"},
            phone: {required: "请输出电话号码！"},
            province: {required: "请选择省份！"},
            city: {required: "请选择城市！"},
            district: {required: "请选择地区！"},
            address: {required: "详细地址不能为空！"}
        }
    });
}
//修改收货地址
function editAddress(txtID, contacts, phone, province, city, district, address) {
    $("#gridSystemModalLabel").text("修改收货地址");
    $("#txtID").val(txtID);
    $("#contacts").val(contacts);
    $("#phone").val(phone);
    $("#address").val(address);
    $("#province").find("option[value='" + province + "']").attr("selected", true);
    getRegion(province, 'province', city);
    getRegion(city, 'city', district);
}
//获取省市和地区
function getRegion(parentID, name, sel) {
    $("#district option:first").prop("selected", 'selected');
    $.ajax({
        type: "post",
        url: "/Integral/getRegion",
        data: {"parentID": parentID},
        dataType: "json",
        beforeSend: function(XMLHttpRequest) {

        },
        success: function(data) {
            if (data.status == 1) {
                selectHtml(data.data, name, sel);
            }
        },
        complete: function(XMLHttpRequest, textStatus) {

        },
        error: function() {

        }
    });
}

function selectHtml(data, name, sel) {
    var html = "";
    for (var i = 0; i < data.length; i++) {
        if (data[i]["regionID"] == sel) {
            html += "<option value=" + data[i]["regionID"] + " selected='selected'  >" + data[i]["name"] + "</option>";
        } else {
            html += "<option value=" + data[i]["regionID"] + ">" + data[i]["name"] + "</option>";
        }
    }
    if (name == 'province') {
        html = '<option value="-1" >请选择城市</option>' + html;
        $("#city").html(html);
    } else if (name == "city") {
        html = '<option value="-1" >请选择区县</option>' + html;
        $("#district").html(html);
    }

}

//新增地址
function addAddress() {
    $("#gridSystemModalLabel").text("新增收货地址");
    $("#txtID").val("");
    $("#contacts").val("");
    $("#phone").val("");
    $("#address").val("");
    $("#province option:first").prop("selected", 'selected');
    $("#city option:first").prop("selected", 'selected');
    $("#district option:first").prop("selected", 'selected');
}

//获取城市列表
function saveAddress() {
    var data = $("#form_address").serialize();
    contacts = $("#contacts").val();
    phone = $("#phone").val();
    address = $("#address").val();
    txtID = $("#txtID").val();
    provinceName = $("#province").find("option:selected").text();
    cityName = $("#city").find("option:selected").text();
    districtName = $("#district").find("option:selected").text();
    data = data + "&provinceName=" + provinceName + "&cityName=" + cityName + "&districtName=" + districtName
            + "&contacts=" + contacts + "&phone=" + phone + "&address=" + address + "&txtID=" + txtID;
    $.ajax({
        type: "post",
        url: "/Integral/saveAddress",
        data: data,
        dataType: "json",
        beforeSend: function(XMLHttpRequest) {
            layer.load();
        },
        success: function(data) {
            layer.hideload();
            if (data.status == 1) {
                layer.msg(data.msg, {time: 1000}, function() {
                    window.location.reload();//刷新当前页面
                });
            } else {
                layer.msg(data.msg);
            }
        },
        complete: function(XMLHttpRequest, textStatus) {
            layer.hideload();
        },
        error: function() {
            layer.hideload();
        }
    });
}
//删除地址
function addressDel(id) {
    layer.confirm("您确定要删除么！", function() {
        $.ajax({
            type: "post",
            url: "/Integral/delAddresss",
            data: {'txtID': id},
            dataType: "json",
            beforeSend: function(XMLHttpRequest) {
                layer.load();
            },
            success: function(data) {
                layer.hideload();
                if (data.status == 1) {
                    layer.msg(data.msg, function() {
                        window.location.reload();//刷新当前页面
                    });
                } else {

                }

            },
            complete: function(XMLHttpRequest, textStatus) {
                layer.hideload();
            },
            error: function() {
                layer.hideload();
            }
        });
    });
}
;
