/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(function() {
    tabClick("#litag1", 'tagContent1', 'tagContent');
    $("#searchRep").click(function() {
        repValidate();
        var isValid = $(".form-inline").valid();
        if (isValid) {
            getRepLiset(1, null, 0);
        }
        $(".form-inline").children("a").removeClass('btn-blue');
    });
    $("#repaid").click(function() {
        repaidValidate();
        var isValid = $(".form_searchLog").valid();
        if (isValid) {
            getRepLiset(1, null, 2);
        }
        $(".form_searchLog").children("a").removeClass('btn-blue');
    });

});
function repBorrow(obj, isTime) {
    if (isTime * 1 == 1) {
        layer.alert("未到还款时间");
        return false;
    }
    var orderno = $(obj).attr("data");
    var repno = $(obj).attr("dataRep");
    window.open("/Account/repay/orderno/" + orderno + "/repno/" + repno);
}
function tabClick(obj, tab, tabs) {
    $(obj).closest('ul').children("li").removeClass('selectTag')
    $(obj).addClass('selectTag');
    $("#" + tabs + "").children().css('display', 'none');
    $("#" + tab + "").show();
//    if ($(obj).attr("data") == "yes") {
//        return false;
//    }
    switch (tab) {
        case"tagContent1":
            //未使用
            $("#hidInfoID").val("repayInfo1");
            getRepLiset(1, obj, 0);
            break;
        case"tagContent2":
            //已使用
            $("#hidInfoID").val("repayInfo2");
            getRepLiset(1, obj, 2);
            break;
        default:
            //默认--未使用
            $("#hidInfoID").val("repayInfo1");
            getRepLiset(1, obj, 0);
            break;
    }
}

function onclickRepay(obj, type) {
    $(obj).prevAll("a").removeClass('btn-blue');
    $(obj).nextAll("a").removeClass('btn-blue');
    $(obj).addClass('btn-blue');
    $("#startSuccess").val("");
    $("#endSuccess").val("");
    $("#startSuccess").removeClass('error');
    $("#endSuccess").removeClass('error');
    $(".form-group").find('label').remove();
    var status = $("#hidStatus").val();
    $("#hidField").val(type);
    getRepLiset(1, null, status);
}

function onclickRepaid(obj, type) {
    $(obj).prevAll("a").removeClass('btn-blue');
    $(obj).nextAll("a").removeClass('btn-blue');
    $(obj).addClass('btn-blue');
    $("#startRepaid").val("");
    $("#endRepaid").val("");
    $("#startRepaid").removeClass('error');
    $("#endRepaid").removeClass('error');
    $(".form-group").find('label').remove();
    var status = $("#hidStatus").val();
    $("#hidrepType").val(type);
    getRepLiset(1, null, status);
}

function getRepLiset(pageIndex, obj, status) {
    $("#hidStatus").val(status);
    var where = getWhere();
    $.ajax({
        type: "post",
        url: "/Account/repayPlanAjax",
        data: {"par": where, "p": pageIndex},
        dataType: "json",
        beforeSend: function(XMLHttpRequest) {
            layer.load();
        },
        success: function(data) {
            layer.hideload();
            if (data.status == 1) {
                var infoID = $("#hidInfoID").val();
                $("#" + infoID).html(data.data.html);
                if (status == 0) {
                    initRepOne(pageIndex, data.data.totalPage, data.data.Records);
                } else {
                    initRepTwo(pageIndex, data.data.totalPage, data.data.Records);
                }
            } else {
                layer.hideload();
                layer.msg(data.msg);
            }
        },
        error: function() {
            layer.hideload();
        }
    });
}
function getWhere() {
    var emp = {};
    emp.fileType = $("#hidField").val();
    emp.infoID = $("#hidInfoID").val();
    emp.repType = $("#hidrepType").val();
    emp.status = $("#hidStatus").val();
    emp.startSuccess = $("#startSuccess").val();
    emp.endSuccess = $("#endSuccess").val();
    emp.startRepaid = $("#startRepaid").val();
    emp.endRepaid = $("#endRepaid").val();
    return emp;
}

function initRepOne(pageNo, totalPage, totalRecords) {
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
            $("#hidInfoID").val("repayInfo1");
            getRepLiset(n, null, 0);
            this.selectPage(n);
            return false;
        }
    });
}

function initRepTwo(pageNo, totalPage, totalRecords) {
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
            $("#hidInfoID").val("repayInfo2");
            getRepLiset(n, null, 2);
            this.selectPage(n);
            return false;
        }
    });
}

function repValidate() {
    $.validator.addMethod("datevalidate", function(value, element) {
        var startdate = new Date(($("#startSuccess").val()).replace(/-/g, "/"));
        var enddate = new Date(($("#endSuccess").val()).replace(/-/g, "/"));
        if (enddate > 0) {
            if (enddate < startdate) {
                return false;
            }
        }
        return true;
    }, "结束日期不能小于开始日期");
    $.validator.addMethod("datelimit", function(value, element) {
        var startdate = $("#startSuccess").val();
        var enddate = $("#endSuccess").val();
        var m1 = parseInt(startdate.split("-")[1].replace(/^0+/, "")) + parseInt(startdate.split("-")[0]) * 12;
        var m2 = parseInt(enddate.split("-")[1].replace(/^0+/, "")) + parseInt(enddate.split("-")[0]) * 12;
        if ((m2 - m1) > 6) {
            return false;
        }
        return true;
    }, "不得大于半年");
    $('.form-inline').validate({
        rules: {
            startSuccess: {
                required: true,
                dateISO: true
            },
            endSuccess: {
                required: true,
                datevalidate: true,
                dateISO: true,
                datelimit: true
            }
        },
        messages: {
            startSuccess: {
                required: "请输入开始日期",
                dateISO: "日期格式不正确"
            },
            endSuccess: {
                required: "请输入结束日期",
                datevalidate: "结束不小于开始",
                dateISO: "日期格式不正确",
                datelimit: "范围不大于半年"
            }
        }
    });
}


function repaidValidate() {
    $.validator.addMethod("datevalidate", function(value, element) {
        var startdate = new Date(($("#startRepaid").val()).replace(/-/g, "/"));
        var enddate = new Date(($("#endRepaid").val()).replace(/-/g, "/"));
        if (enddate > 0) {
            if (enddate < startdate) {
                return false;
            }
        }
        return true;
    }, "结束日期不能小于开始日期");
    $.validator.addMethod("datelimit", function(value, element) {
        var startdate = $("#startRepaid").val();
        var enddate = $("#endRepaid").val();
        var m1 = parseInt(startdate.split("-")[1].replace(/^0+/, "")) + parseInt(startdate.split("-")[0]) * 12;
        var m2 = parseInt(enddate.split("-")[1].replace(/^0+/, "")) + parseInt(enddate.split("-")[0]) * 12;
        if ((m2 - m1) > 6) {
            return false;
        }
        return true;
    }, "不得大于半年");
    $('.form_searchLog').validate({
        rules: {
            startRepaid: {
                required: true,
                dateISO: true
            },
            endRepaid: {
                required: true,
                datevalidate: true,
                dateISO: true,
                datelimit: true
            }
        },
        messages: {
            startRepaid: {
                required: "请输入开始日期",
                dateISO: "日期格式不正确"
            },
            endRepaid: {
                required: "请输入结束日期",
                datevalidate: "结束不小于开始",
                dateISO: "日期格式不正确",
                datelimit: "范围不大于半年"
            }
        }
    });
} 