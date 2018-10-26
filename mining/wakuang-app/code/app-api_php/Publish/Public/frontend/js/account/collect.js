/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(function() {
    tabClick("#litag1", "tagContent1", "tagContent");
    $("#searchCollect").click(function() {
        searchCollectValidate();
        var isValid = $(".collectingform").valid();
        if (isValid) {
            getCollectingRecord(1, null);
            $(".collectingform").children("a").removeClass('btn-blue');
        }


    });
    $("#searchFinish").click(function() {
        searchFinishValidate();

        var isOK = $(".collectSuccform").valid();
        if (isOK) {
            getCollectSuccRecord(1, null);
            $(".collectSuccform").children("a").removeClass('btn-blue');
        }
    });

});
function tabClick(obj, tab, tabs) {
    $(obj).addClass('selectTag').siblings().removeClass("selectTag");
    $("#" + tabs + "").children().css('display', 'none');
    $("#" + tab + "").show();
    if ($(obj).attr("data") == "yes") {
        return;
    }
    switch (tab) {
        case"tagContent1":
            getCollectingRecord(1, obj);
            break;
        case"tagContent2":
            getCollectSuccRecord(1, obj);
            break;
        default:
            getCollectingRecord(1, obj);
            break;
    }
}

/********************************************************* 待收中 tab1 *********************************/

function getCollectingRecord(pageIndex, obj) {
    var where = getCollectWhere();
    $.ajax({
        type: "post",
        url: "/Investment/collectingList",
        data: {'p': pageIndex, 'condition': where},
        dataType: "json",
        beforeSend: function(XMLHttpRequest) {
            layer.load();
        },
        success: function(data) {
            layer.hideload();
            if (data.status == 1) {
                $('#collectHtml').html(data.data.html);
                initPage(pageIndex, data.data.page, data.data.total, 'tab1Page');
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

function initPage(pageNo, totalPage, totalRecords, pageName) {
    kkpager.generPageHtml({
        pno: pageNo,
        pagerid: pageName,
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
            if (pageName == "tab1Page") {
                getCollectingRecord(n, null);
            } else if (pageName == "tab2Page") {
                getCollectSuccRecord(n, null);
            }
            this.selectPage(n);
            return false;
        }
    });
}

function onclickACollect(obj, type) {
    $(obj).addClass('btn-blue').siblings().removeClass("btn-blue");
    $("#hidCollect").val(type);

    $("#startCollect").removeClass('error');
    $("#endCollect").removeClass('error');
    $(".form-group").find('label').remove();
    $("#startCollect").val("");
    $("#endCollect").val("");
    getCollectingRecord(1, null);
}
function getCollectWhere() {
    var emp = new Object();
    emp.datatype = $("#hidCollect").val();
    emp.start = $("#startCollect").val();
    emp.end = $("#endCollect").val();
    return emp;
}
function searchCollectValidate() {
    $.validator.addMethod("datevalidate", function(value, element) {
        var startdate = new Date(($("#startCollect").val()).replace(/-/g, "/"));
        var enddate = new Date(($("#endCollect").val()).replace(/-/g, "/"));
        if (enddate > 0) {
            if (enddate < startdate) {
                return false;
            }
        }
        return true;
    }, "结束日期不能小于开始日期");
    $.validator.addMethod("datelimit", function(value, element) {
        var startdate = $("#startCollect").val();
        var enddate = $("#endCollect").val();
        var m1 = parseInt(startdate.split("-")[1].replace(/^0+/, "")) + parseInt(startdate.split("-")[0]) * 12;
        var m2 = parseInt(enddate.split("-")[1].replace(/^0+/, "")) + parseInt(enddate.split("-")[0]) * 12;
        if ((m2 - m1) > 12) {
            return false;
        }
        return true;
    }, "不得大于一年");
    $('.collectingform').validate({
        rules: {
            startCollect: {
                required: true,
                dateISO: true
            },
            endCollect: {
                required: true,
                datevalidate: true,
                dateISO: true,
                datelimit: true
            }
        },
        messages: {
            startCollect: {
                required: "请输入开始日期",
                dateISO: "日期格式不正确"
            },
            endCollect: {
                required: "请输入结束日期",
                datevalidate: "结束不小于开始",
                dateISO: "日期格式不正确",
                datelimit: "范围不大于一年"
            }
        }
    });
}
/*********************************************** tab2  完成收款 ********************************************************************/
function getCollectSuccRecord(pageIndex, obj) {
    var where = getFinishWhere();
    $.ajax({
        type: "post",
        url: "/Investment/getCollectSuccRecord",
        data: {'p': pageIndex, 'condition': where},
        dataType: "json",
        beforeSend: function(XMLHttpRequest) {
            layer.load();
        },
        success: function(data) {
            layer.hideload();
            if (data.status == 1) {
                $('#finishHtml').html(data.data.html);
                initPage(pageIndex, data.data.page, data.data.total, 'tab2Page');
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

function onclickAFinish(obj, type) {
    $(obj).addClass('btn-blue').siblings().removeClass("btn-blue");
    $("#hidFinish").val(type);

    $("#startFinish").removeClass('error');
    $("#endFinish").removeClass('error');
    $(".form-group").find('label').remove();
    $("#startFinish").val("");
    $("#endFinish").val("");
    getCollectSuccRecord(1, null);
}
function getFinishWhere() {
    var emp = new Object();
    emp.datatype = $("#hidFinish").val();
    emp.start = $("#startFinish").val();
    emp.end = $("#endFinish").val();
    return emp;
}
function searchFinishValidate() {
    $.validator.addMethod("datevalidate", function(value, element) {
        var startdate = new Date(($("#startFinish").val()).replace(/-/g, "/"));
        var enddate = new Date(($("#endFinish").val()).replace(/-/g, "/"));
        if (enddate > 0) {
            if (enddate < startdate) {
                return false;
            }
        }
        return true;
    }, "结束日期不能小于开始日期");
    $.validator.addMethod("datelimit", function(value, element) {
        var startdate = $("#startFinish").val();
        var enddate = $("#endFinish").val();
        var m1 = parseInt(startdate.split("-")[1].replace(/^0+/, "")) + parseInt(startdate.split("-")[0]) * 12;
        var m2 = parseInt(enddate.split("-")[1].replace(/^0+/, "")) + parseInt(enddate.split("-")[0]) * 12;
        if ((m2 - m1) > 6) {
            return false;
        }
        return true;
    }, "不得大于半年");
    $('.collectSuccform').validate({
        rules: {
            startFinish: {
                required: true,
                dateISO: true
            },
            endFinish: {
                required: true,
                datevalidate: true,
                dateISO: true,
                datelimit: true
            }
        },
        messages: {
            startFinish: {
                required: "请输入开始日期",
                dateISO: "日期格式不正确"
            },
            endFinish: {
                required: "请输入结束日期",
                datevalidate: "结束不小于开始",
                dateISO: "日期格式不正确",
                datelimit: "范围不大于半年"
            }
        }
    });
}