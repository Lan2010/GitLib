/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(function() {
    //控制切换
    var tabCtrl = $("#tabCtrl").val();
    tabClick("#litag" + tabCtrl, "tagContent" + tabCtrl, "tagContent");
    //保存地址
    $("#saveBtn").live("click", function() {
        addressvalidate();
        var isOK = $("#formbtn").valid();
        if (isOK) {
            saveAddress();
        }
    });
    //刷新让省份重置第一个
    $("#province option:first").prop("selected", 'selected');
});

function tabClick(obj, tab, tabs) {
    $(obj).addClass('selectTag').siblings().removeClass("selectTag");
    $("#" + tabs + "").children().css('display', 'none');
    $("#" + tab + "").show();
    $("#" + tab + "").next().show();

    if ($(obj).attr("data") == "yes") {
        return;
    }

    switch (tab) {
        case"tagContent1":
            getExchangeList(1);
            break;
        case"tagContent2":
            getIntegralList(1, obj);
            break;
        case"tagContent3":

            break;
        default:
            getExchangeList(1, obj);
            break;
    }
}
/********************************************************* 积分兑换记录 tab1 *********************************/
function getExchangeList(pageIndex) {
    $.ajax({
        type: "get",
        url: "/Integral/getExchangeList/",
        data: {'p': pageIndex},
        dataType: "json",
        beforeSend: function(XMLHttpRequest) {
            layer.load();
        },
        success: function(data) {
            layer.hideload();

            if (data.status == 1) {

                $("#exchangeHtml").html(data.data.html);
                initPage(pageIndex, data.data.page, data.data.total);
                return false;
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


/********************************************************* 积分获取记录 tab2 *********************************/
function getIntegralList(pageIndex, obj) {
    $.ajax({
        type: "get",
        url: "/Integral/getIntegralList/",
        data: {'p': pageIndex},
        dataType: "json",
        beforeSend: function(XMLHttpRequest) {
            layer.load();
        },
        success: function(data) {
            layer.hideload();
            if (data.status == 1) {
                $("#integralHtml").html(data.data.html);
                initIntegeralPage(pageIndex, data.data.page, data.data.total);
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
    $("#formbtn").validate({
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
function initPage(pageNo, totalPage, totalRecords) {

    kkpager.generPageHtml({
        pno: pageNo,
        pagerid: "tab1Page",
        gopageWrapId: 'tab1Page_gopage_wrap',
        gopageButtonId: 'tab1Page_btn_go',
        gopageTextboxId: 'tab1Page_btn_go_input',
        //总页码
        total: totalPage,
        //总数据条数
        totalRecords: totalRecords,
        getLink: function(n) {
            return this.hrefFormer + this.hrefLatter + "?pno=" + n;
        },
        mode: 'click', //默认值是link，可选link或者click
        click: function(n) {
            getExchangeList(n);
            this.selectPage(n);
            return false;
        }
    });
}

function initIntegeralPage(pageNo, totalPage, totalRecords) {

    kkpager.generPageHtml({
        pno: pageNo,
        pagerid: "tab2Page",
        gopageWrapId: 'tab1Page_gopage_wrap',
        gopageButtonId: 'tab1Page_btn_go',
        gopageTextboxId: 'tab1Page_btn_go_input',
        //总页码
        total: totalPage,
        //总数据条数
        totalRecords: totalRecords,
        getLink: function(n) {
            return this.hrefFormer + this.hrefLatter + "?pno=" + n;
        },
        mode: 'click', //默认值是link，可选link或者click
        click: function(n) {
            getIntegralList(n);
            this.selectPage(n);
            return false;
        }
    });
}
//获取城市列表
function getRegion(parentid, name, sel) {
    $("#district option:first").prop("selected", 'selected');
    $.ajax({
        type: "post",
        url: "/Integral/getRegion",
        data: {'parentID': parentid},
        dataType: "json",
        beforeSend: function(XMLHttpRequest) {

        },
        success: function(data) {

            if (data.status == 1) {
                selectHtml(data.data, name, sel);
            } else {
                $Dialog.warn(data.msg);
            }
        },
        complete: function(XMLHttpRequest, textStatus) {

        },
        error: function() {

        }
    });
}
function selectHtml(data, name, sel) {
    var html = '';
    for (var i = 0; i < data.length; i++) {
        if (data[i]['regionID'] == sel) {
            html += " <option value=" + data[i]['regionID'] + " selected='selected' >" + data[i]['name'] + "</option>";
        } else {
            html += " <option value=" + data[i]['regionID'] + " >" + data[i]['name'] + "</option>";
        }
    }

    if (name == 'province') {
        html = '<option value="-1" >请选择城市</option>' + html;
        $("#city").html(html);
    } else if (name == 'city') {
        html = '<option value="-1" >请选择地区</option>' + html;
        $("#district").html(html);
    }
}

//获取城市列表
function saveAddress() {
    var data = $("#formbtn").serialize();
    provinceName = $("#province").find("option:selected").text();
    cityName = $("#city").find("option:selected").text();
    districtName = $("#district").find("option:selected").text();
    data = data + "&provinceName=" + provinceName + "&cityName=" + cityName + "&districtName=" + districtName;

    $.ajax({
        type: "post",
        url: "/Integral/saveAddress",
        data: data,
        dataType: "json",
        beforeSend: function(XMLHttpRequest) {

        },
        success: function(data) {
            layer.msg(data.msg);
            if (data.status == 1) {
                window.location.href = '/integral/integral/tab/3';
            } else {
                layer.msg(data.msg);
            }
        },
        complete: function(XMLHttpRequest, textStatus) {

        },
        error: function() {

        }
    });
}
//修改
function addressUpate(txtID, contacts, phone, province, city, district, address) {
    $("#gridSystemModalLabel").text("修改收货地址");
    $("#txtID").val(txtID);
    $("#contacts").val(contacts);
    $("#phone").val(phone);
    $("#address").val(address);
    $("#province").find("option[value='" + province + "']").attr("selected", true);
    getRegion(province, 'province', city)
    getRegion(city, 'city', district)
}
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

//删除
function addressDel(id) {
            layer.confirm("确定删除该收货地址吗？", {title: "提示"}, function() {
            $.ajax({
                            type: "post",
                            url: "/Integral/delAddresss",
                            data: {'txtID': id},
                            dataType: "json",
                            beforeSend: function(XMLHttpRequest) {

                            },
                            success: function(data) {
                                layer.msg(data.msg);
                                if (data.status == 1) {
                                    window.location.href = '/integral/integral/tab/3';
                                } else {

                                }

                            },
                            complete: function(XMLHttpRequest, textStatus) {

                            },
                            error: function() {

                            }
                    });
        });
}

// 选为默认

//删除
function addressDefault(id) {
    $.ajax({
        type: "post",
        url: "/Integral/addressDefault",
        data: {'txtID': id},
        dataType: "json",
        beforeSend: function(XMLHttpRequest) {

        },
        success: function(data) {
            layer.msg(data.msg);
            if (data.status == 1) {
                window.location.href = '/integral/integral/tab/3';
            } else {

            }

        },
        complete: function(XMLHttpRequest, textStatus) {

        },
        error: function() {

        }
    });
}

