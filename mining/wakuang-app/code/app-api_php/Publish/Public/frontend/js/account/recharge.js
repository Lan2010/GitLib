$(function () {
    $("#searchRecharge").click(function () {
        searchRechargeValidate();
        var isOK = $(".formRecharge").valid();
        if (isOK) {
            $("#tdRecharge").children("a").removeClass('btn-blue');
            getRechargeRecord(1);
        }
    });

    $("#submitRecharge").click(function () {
        if ($("#hidRealStatus").val() * 1 > 0) {
            hxRechargeValid();
            var isOK = $("#rechargeForm").valid();
            if (isOK) {
                $(".modalbox").attr("style", "display:block");
                $("#rechargeForm").submit();
                //
            }
        } else {
            $(".modalbox2").attr("style", "display:block");
        }
        return false;
    });
    getRechargeRecord(1);
});


function tabClick(obj, tab, tabs) {

    $(obj).closest('ul').children("li").removeClass('selectTag')
    $(obj).addClass('selectTag');
    $("#" + tabs + "").children().css('display', 'none');
    $("#" + tab + "").show();
    switch (tab) {
        case"tagContent2":
            getRechargeRecord(1);
            break;
        default:
            break;
    }

}

function getRechargeRecord(pageIndex) {
    var where = getRechargewhere();
    var dpage = {"pageIndex": pageIndex};
    $.ajax({
        type: "post",
        url: "/Recharge/rechargeList",
        data: {'p': pageIndex, 'condition': where},
        dataType: "json",
        beforeSend: function (XMLHttpRequest) {
            layer.load();
        },
        success: function (data) {
            layer.hideload();
            if (data.status == 1) {
                $('#rechargeHtml').html(data.data.html);
                initRecharge(pageIndex, data.data.page, data.data.total);
            } else {
                layer.msg(data.msg);
            }
        },
        complete: function (XMLHttpRequest, textStatus) {
            layer.hideload();
        },
        error: function () {
            layer.hideload();
        }
    });
}


function initRecharge(pageNo, totalPage, totalRecords) {
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
        getLink: function (n) {
            return this.hrefFormer + this.hrefLatter + "?pno=" + n;
        },
        mode: 'click', //默认值是link，可选link或者click
        click: function (n) {
            getRechargeRecord(n);
            this.selectPage(n);
            return false;
        }
    });
}
function onclickARecharge(obj, type) {
    $(obj).addClass('btn-blue').siblings("a").removeClass('btn-blue');
    $("#hidsearchRecharge").val(type);

    $("#startRecharge").removeClass('error');
    $("#endRecharge").removeClass('error');
    $(".form-group").find('label').remove();

    $("#startRecharge").val("");
    $("#endRecharge").val("");
    getRechargeRecord(1);
}
function onclickAtypeRecharge(obj, state) {
    $(obj).addClass('btn-blue').siblings("a").removeClass('btn-blue');

    $("#startRecharge").removeClass('error');
    $("#endRecharge").removeClass('error');
    $(".form-group").find('label').remove();

    $("#txtsearchStateRecharge").val(state);
    getRechargeRecord(1);
}
function getRechargewhere() {
    var emp = new Object();
    emp.datatype = $("#hidsearchRecharge").val();
    emp.start = $("#startRecharge").val();
    emp.end = $("#endRecharge").val();
    emp.state = $("#txtsearchStateRecharge").val();
    return emp;
}
function searchRechargeValidate() {
    $.validator.addMethod("datevalidate", function (value, element) {
        var startdate = new Date(($("#startRecharge").val()).replace(/-/g, "/"));
        var enddate = new Date(($("#endRecharge").val()).replace(/-/g, "/"));
        if (enddate > 0) {
            if (enddate < startdate) {
                return false;
            }
        }
        return true;
    }, "结束日期不能小于开始日期");
    $.validator.addMethod("datelimit", function (value, element) {
        var startdate = $("#startRecharge").val();
        var enddate = $("#endRecharge").val();
        var m1 = parseInt(startdate.split("-")[1].replace(/^0+/, "")) + parseInt(startdate.split("-")[0]) * 12;
        var m2 = parseInt(enddate.split("-")[1].replace(/^0+/, "")) + parseInt(enddate.split("-")[0]) * 12;
        if ((m2 - m1) > 6) {
            return false;
        }
        return true;
    }, "不得大于半年");
    $('.formRecharge').validate({
        rules: {
            startRecharge: {
                required: true,
                dateISO: true
            },
            endRecharge: {
                required: true,
                datevalidate: true,
                dateISO: true,
                datelimit: true
            }
        },
        messages: {
            startRecharge: {
                required: "请输入开始日期",
                dateISO: "日期格式不正确"
            },
            endRecharge: {
                required: "请输入结束日期",
                datevalidate: "结束不小于开始",
                dateISO: "日期格式不正确",
                datelimit: "范围不大于半年"
            }
        }
    });
}
/*
 * 充值金额 
 */
function hxRechargeValid() {
    $('#rechargeForm').validate({
        rules: {
            amount: {
                required: true,
                pointMoney: true
            },
        },
        messages: {
            amount: {
                required: "请输入充值金额",
                pointMoney: "输入金额格式不正确"
            }
        }
    });
}