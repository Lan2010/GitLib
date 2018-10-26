/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * 选项卡隐藏显示
 * @param {type} obj
 * @param {type} tab
 * @param {type} tabs
 * @returns {undefined}
 */
$(function() {
    getRecord(1);
    $("#searchLog").click(function() {
        searchLogValidate();
        var isOK = $(".form_searchLog").valid();
        if (isOK) {
            getRecord(1);
        }
    });
});

function condition() {
    var emp = new Object();
    emp.datatype = $("#txtsearchlog").val();
    emp.btype = $("#txtsearchtypelog").val();
    emp.start = $("#startlog").val();
    emp.end = $("#endlog").val();
    return emp;
}

function getRecord(pageIndex) {
    var where = condition();
    $.ajax({
        type: "post",
        url: "/Account/recordfundList",
        data: {'p': pageIndex, 'condition': where},
        dataType: "json",
        beforeSend: function(XMLHttpRequest) {
            layer.load();
        },
        success: function(data) {
            layer.hideload();
            if (data.status == 1) {
                $('#recordHtml').html(data.data.html);
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
            getRecord(n);
            this.selectPage(n);
            return false;
        }
    });
}
function onclickAlog(obj, type) {
    $(obj).addClass('btn-blue').siblings("a").removeClass('btn-blue');
    $("#txtsearchlog").val(type);
    $("#startlog").val("");
    $("#endlog").val("");

    $("#startlog").removeClass('error');
    $("#endlog").removeClass('error');
    $(".form-group").find('label').remove();
    getRecord(1);
}
function onclickAtypelog(obj, type) {
    $(obj).addClass('btn-blue').siblings("a").removeClass('btn-blue');
    $("#startlog").removeClass('error');
    $("#endlog").removeClass('error');
    $(".form-group").find('label').remove();

    $("#txtsearchtypelog").val(type);
    getRecord(1);
}


function searchLogValidate() {
    $.validator.addMethod("datevalidate", function(value, element) {
        var startdate = new Date(($("#startlog").val()).replace(/-/g, "/"));
        var enddate = new Date(($("#endlog").val()).replace(/-/g, "/"));
        if (enddate > 0) {
            if (enddate < startdate) {
                return false;
            }
        }
        return true;
    }, "结束日期不能小于开始日期");
    $.validator.addMethod("datelimit", function(value, element) {
        var startdate = $("#startlog").val();
        var enddate = $("#endlog").val();
        var m1 = parseInt(startdate.split("-")[1].replace(/^0+/, "")) + parseInt(startdate.split("-")[0]) * 12;
        var m2 = parseInt(enddate.split("-")[1].replace(/^0+/, "")) + parseInt(enddate.split("-")[0]) * 12;
        if ((m2 - m1) > 6) {
            return false;
        }
        return true;
    }, "不得大于半年");
    $('.form_searchLog').validate({
        rules: {
            startlog: {
                required: true,
                dateISO: true
            },
            endlog: {
                required: true,
                datevalidate: true,
                dateISO: true,
                datelimit: true
            }
        },
        messages: {
            startlog: {
                required: "请输入开始日期",
                dateISO: "日期格式不正确"
            },
            endlog: {
                required: "请输入结束日期",
                datevalidate: "结束不小于开始",
                dateISO: "日期格式不正确",
                datelimit: "范围不大于半年"
            }
        }
    });
}