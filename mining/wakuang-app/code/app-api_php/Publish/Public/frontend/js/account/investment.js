/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(function() {
    tabClick("#litag1", "tagContent1", "tagContent");
    //回款中
    $("#onclickReping").click(function() {
        searchRepingValidate();
        var isValid = $(".tenderReping").valid();
        if (isValid) {
            getTenderRepingRecord(1, null);
        }
        //  $(".form-inline").children("a").removeClass('active');

    });
    //全部回款
    $("#onclickRepSucc").click(function() {
        searchRepSuccValidate();
        var isValid = $(".tenderRepSucc").valid();
        if (isValid) {
            getTenderRepSuccRecord(1, null);
        }
        //  $(".form-inline").children("a").removeClass('active');

    });

    //失败
    $("#searchFailure").click(function() {
        searchFailureValidate();
        // $(".form-inline").children("a").removeClass('active');
        var isOK = $(".tenderFaile").valid();
        if (isOK) {
            getTenderFailureRecord(1, null);
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
            getTenderingRecord(1, obj);
            break;
        case"tagContent2":
            getTenderRepingRecord(1, obj);
            break;
        case"tagContent3":
            getTenderRepSuccRecord(1, obj);
            break;
        case"tagContent4":
            getTenderFailureRecord(1, obj);
            break;
        default:
            getTenderingRecord(1, obj);
            break;
    }
}
/********************************************************* 投资中 tab1 *********************************/
function getTenderingRecord(pageIndex, obj) {
    $.ajax({
        type: "post",
        url: "/Investment/getTenderingList",
        data: {'p': pageIndex},
        dataType: "json",
        beforeSend: function(XMLHttpRequest) {
            layer.load();
        },
        success: function(data) {
            layer.hideload();
            if (data.status == 1) {
                $('#tenderingHtml').html(data.data.html);
                initPage(pageIndex, data.data.page, data.data.total, "tab1Page");
                return false;

                if (obj !== null) {
                    $(obj).attr("data", "yes");
                }

            } else {
                layer.msg(data.msg);
            }
        }
    });
}

/********************************************************* 投资成功回款中.... tab2 *********************************/
function getTenderRepingRecord(pageIndex, obj) {
    var where = getRepingWhere();
    $.ajax({
        type: "post",
        url: "/Investment/getTenderRepingList",
        data: {'p': pageIndex, 'condition': where},
        dataType: "json",
        beforeSend: function(XMLHttpRequest) {
            layer.load();
        },
        success: function(data) {
            layer.hideload();
            if (data.status == 1) {
                $('#repingHtml').html(data.data.html);
                initPage(pageIndex, data.data.page, data.data.total, "tab2Page");
                if (obj !== null) {
                    $(obj).attr("data", "yes");
                }
            } else {
                layer.msg(data.msg);
            }
        }
    });
}

function onclickReping(obj, type) {
    $(obj).addClass('btn-blue').siblings().removeClass("btn-blue");
    $("#hidReping").val(type);
    $("#startReping").val("");
    $("#endReping").val("");
    getTenderRepingRecord(1, null);
}
function getRepingWhere() {
    var emp = new Object();
    emp.datatype = $("#hidReping").val();
    emp.start = $("#startReping").val();
    emp.end = $("#endReping").val();

    return emp;
}
function searchRepingValidate() {
    $.validator.addMethod("datevalidate", function(value, element) {
        var startdate = new Date(($("#startReping").val()).replace(/-/g, "/"));
        var enddate = new Date(($("#endReping").val()).replace(/-/g, "/"));
        if (enddate > 0) {
            if (enddate < startdate) {
                return false;
            }
        }
        return true;
    }, "结束日期不能小于开始日期");
    $.validator.addMethod("datelimit", function(value, element) {
        var startdate = $("#startReping").val();
        var enddate = $("#endReping").val();
        var m1 = parseInt(startdate.split("-")[1].replace(/^0+/, "")) + parseInt(startdate.split("-")[0]) * 12;
        var m2 = parseInt(enddate.split("-")[1].replace(/^0+/, "")) + parseInt(enddate.split("-")[0]) * 12;
        if ((m2 - m1) > 6) {
            return false;
        }
        return true;
    }, "不得大于半年");
    $('.tenderReping').validate({
        rules: {
            startReping: {
                required: true,
                dateISO: true
            },
            endReping: {
                required: true,
                datevalidate: true,
                dateISO: true,
                datelimit: true
            }
        },
        messages: {
            startReping: {
                required: "请输入开始日期",
                dateISO: "日期格式不正确"
            },
            endReping: {
                required: "请输入结束日期",
                datevalidate: "结束不小于开始",
                dateISO: "日期格式不正确",
                datelimit: "范围不大于半年"
            }
        }
    });
}


/********************************************************* 投资成功全部回款 tab3 *********************************/

function getTenderRepSuccRecord(pageIndex, obj) {
    var where = getRepSuccWhere();
    $.ajax({
        type: "post",
        url: "/Investment/getTenderRepSuccList",
        data: {'p': pageIndex, 'condition': where},
        dataType: "json",
        beforeSend: function(XMLHttpRequest) {
            layer.load();
        },
        success: function(data) {
            layer.hideload();
            if (data.status == 1) {
                $('#repSuccHtml').html(data.data.html);
                initPage(pageIndex, data.data.page, data.data.total, "tab3Page");
                if (obj !== null) {
                    $(obj).attr("data", "yes");
                }
            } else {
                layer.msg(data.msg);
            }
        }
    });
}

function onclickRepSucc(obj, type) {
    $(obj).addClass('btn-blue').siblings().removeClass("btn-blue");
    $("#hidRepSucc").val(type);
    $("#startRepSucc").val("");
    $("#endRepSucc").val("");
    getTenderRepSuccRecord(1, null);
}
function getRepSuccWhere() {
    var emp = new Object();
    emp.datatype = $("#hidRepSucc").val();
    emp.start = $("#startRepSucc").val();
    emp.end = $("#endRepSucc").val();

    return emp;
}
function searchRepSuccValidate() {
    $.validator.addMethod("datevalidate", function(value, element) {
        var startdate = new Date(($("#startRepSucc").val()).replace(/-/g, "/"));
        var enddate = new Date(($("#endRepSucc").val()).replace(/-/g, "/"));
        if (enddate > 0) {
            if (enddate < startdate) {
                return false;
            }
        }
        return true;
    }, "结束日期不能小于开始日期");
    $.validator.addMethod("datelimit", function(value, element) {
        var startdate = $("#startRepSucc").val();
        var enddate = $("#endRepSucc").val();
        var m1 = parseInt(startdate.split("-")[1].replace(/^0+/, "")) + parseInt(startdate.split("-")[0]) * 12;
        var m2 = parseInt(enddate.split("-")[1].replace(/^0+/, "")) + parseInt(enddate.split("-")[0]) * 12;
        if ((m2 - m1) > 6) {
            return false;
        }
        return true;
    }, "不得大于半年");
    $('.tenderRepSucc').validate({
        rules: {
            startRepSucc: {
                required: true,
                dateISO: true
            },
            endRepSucc: {
                required: true,
                datevalidate: true,
                dateISO: true,
                datelimit: true
            }
        },
        messages: {
            startRepSucc: {
                required: "请输入开始日期",
                dateISO: "日期格式不正确"
            },
            endRepSucc: {
                required: "请输入结束日期",
                datevalidate: "结束不小于开始",
                dateISO: "日期格式不正确",
                datelimit: "范围不大于半年"
            }
        }
    });
}
/*********************************************** tab4 投资失败 ********************************************************************/
/*
 * 
 * @param {type} pageIndex
 * @param {type} obj
 * @returns {undefined}
 */
function getFailureWhere() {
    var emp = new Object();
    emp.datatype = $("#hidFailure").val();
    emp.start = $("#startFailure").val();
    emp.end = $("#endFailure").val();
    return emp;
}

function getTenderFailureRecord(pageIndex, obj) {
    var where = getFailureWhere();
    $.ajax({
        type: "post",
        url: "/Investment/getTenderFailureList",
        data: {'p': pageIndex, 'condition': where},
        dataType: "json",
        beforeSend: function(XMLHttpRequest) {
            layer.load();
        },
        success: function(data) {
            layer.hideload();
            if (data.status == 1) {
                $('#tenderFaileHtml').html(data.data.html);
                initPage(pageIndex, data.data.page, data.data.total, "tab4Page");
                if (obj !== null) {
                    $(obj).attr("data", "yes");
                }

            } else {
                layer.msg(data.msg);
            }
        }
    });
}

function onclickAFailure(obj, type) {
    $(obj).addClass('btn-blue').siblings().removeClass("btn-blue");
    $("#hidFailure").val(type);
    $("#startFailure").val("");
    $("#endFailure").val("");
    getTenderFailureRecord(1, null);
}

function searchFailureValidate() {
    $.validator.addMethod("datevalidate", function(value, element) {
        var startdate = new Date(($("#startFailure").val()).replace(/-/g, "/"));
        var enddate = new Date(($("#endFailure").val()).replace(/-/g, "/"));
        if (enddate > 0) {
            if (enddate < startdate) {
                return false;
            }
        }
        return true;
    }, "结束日期不能小于开始日期");
    $.validator.addMethod("datelimit", function(value, element) {
        var startdate = $("#startFailure").val();
        var enddate = $("#endFailure").val();
        var m1 = parseInt(startdate.split("-")[1].replace(/^0+/, "")) + parseInt(startdate.split("-")[0]) * 12;
        var m2 = parseInt(enddate.split("-")[1].replace(/^0+/, "")) + parseInt(enddate.split("-")[0]) * 12;
        if ((m2 - m1) > 6) {
            return false;
        }
        return true;
    }, "不得大于半年");
    $('.tenderFaile').validate({
        rules: {
            startFailure: {
                required: true,
                dateISO: true,
            },
            endFailure: {
                required: true,
                datevalidate: true,
                dateISO: true,
                datelimit: true
            }
        },
        messages: {
            startFailure: {
                required: "请输入开始日期",
                dateISO: "日期格式不正确"
            },
            endFailure: {
                required: "请输入结束日期",
                datevalidate: "结束不小于开始",
                dateISO: "日期格式不正确",
                datelimit: "范围不大于半年"
            }
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
                getTenderingRecord(n, null);
            } else if (pageName == "tab2Page") {
                getTenderRepingRecord(n, null);
            } else if (pageName == "tab3Page") {
                getTenderRepSuccRecord(n, null);
            } else if (pageName == "tab4Page") {
                getTenderFailureRecord(n, null);
            }

            this.selectPage(n);
            return false;
        }
    });
}