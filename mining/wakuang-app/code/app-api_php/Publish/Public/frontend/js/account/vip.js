/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(function() {
    tabClick("#litag1", "tagContent1", "tagContent");
    $("#immaturity").click(function() {
        searchDetailValidate();
        var isValid = $(".form-inline").valid();
        if (isValid) {
            $("#investType").val("1");
            getVipIncomeList(1, null);
        }
        $(".form-inline").children("a").removeClass('btn-blue');

    });

});
function tabClick(obj, tab, tabs) {
    $(obj).addClass('selectTag').siblings().removeClass("selectTag");
    $("#" + tabs + "").children().css('display', 'none');
    $("#" + tab + "").show();
    getVipIncomeList(1, obj);
}
/********************************************************* vip收益记录 *********************************/
function getVipIncomeList(pageIndex, obj) {
    var where = getSuccessWhere();
    $.ajax({
        type: "post",
        url: "/Investment/vipIncomeList",
        data: {'condition': where, 'p': pageIndex},
        dataType: "json",
        beforeSend: function(XMLHttpRequest) {
            layer.load();
        },
        success: function(data) {
            layer.hideload();
            if (data.status == 1) {
                $('#vipHtml').html(data.data.html);
                initPage(pageIndex, data.data.page, data.data.total, "tab1Page");
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

function initPage(pageNo, totalPage, totalRecords, pageContent) {
    kkpager.generPageHtml({
        pno: pageNo,
        pagerid: pageContent,
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
            getVipIncomeList(n, null);
            this.selectPage(n);
            return false;
        }
    });
}

function onclickAsuccess(obj, type) {
    $(obj).addClass('btn-blue').siblings().removeClass("btn-blue");
    $("#hidSuccess").val(type);
    $("#startSuccess").val("");
    $("#endSuccess").val("");

    $("#startSuccess").removeClass('error');
    $("#endSuccess").removeClass('error');
    $(".form-group").find('label').remove();

    getVipIncomeList(1, null);
}

function onclickAll(obj, type) {
    $(obj).addClass('btn-blue').siblings().removeClass("btn-blue");
    $("#hidType").val(type);
    $(".form-group").find('label').remove();
    getVipIncomeList(1, null);
}

function getSuccessWhere() {
    var emp = new Object();
    emp.datatype = $("#hidSuccess").val();
    emp.start = $("#startSuccess").val();
    emp.end = $("#endSuccess").val();
    emp.investType = $("#investType").val();
    emp.infoType = $("#hidType").val();
    return emp;
}

function searchDetailValidate() {
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