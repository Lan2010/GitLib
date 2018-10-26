$(function () {

    $("#searchWithdraw").click(function () {
        searchWithdrawValidate();
        var isOK = $(".formcash").valid();
        if (isOK) {
            $("#tdWithdraw").children("a").removeClass('btn-blue');
            getWithdrawRecord(1);

        }
    });
    getWithdrawRecord(1);
    //提交
    $("#searchLog").click(function () {
        searchLogValidate();
        $("#hidsearchWithdraw").val("searchtime");
        var isOK = $(".formcash").valid();
        if (isOK) {
            getWithdrawRecord(1);
        }
    });

    $("#submitCash").click(function () {
        hxCashValid();
        var isOK = $("#cashForm").valid();
        var isAuth = isAllAuth();   //检查是否全部认证
        if (isOK) {
            var isFirst = isFristCash(); //检查是否第一次提现
            if (isFirst) {
                layer.alert('您是否确认已收到所有合同？',function(){
                    if(!isAuth) {               
                        layer.confirm('提现金额需要全部授权还款，是否前往授权？', function() {
                                        window.location.href='/Account/loanRecord.html';
                                });
                        return;
                    } else {
                        $("#cashForm").submit();
                        setTimeout($(".modalbox").show(),1000);        
                        setTimeout($('.qhz-layer').hide(),1000);
                        setTimeout($('.qhz-layer-shade').hide(),1000);
                    }
                });
            }else{
<<<<<<< .mine
                $("#cashForm").submit();
<<<<<<< .mine
            }
            $(".modalbox").attr("style", "display:block");
=======
                $(".modalbox").show();
=======
                    if(!isAuth) {               
                        layer.confirm('提现金额需要全部授权还款，是否前往授权？', function() {
                                        window.location.href='/Account/loanRecord.html';
                                });
                                return;
                    } else {
                       $("#cashForm").submit();
                       $(".modalbox").show();
                    }
>>>>>>> .r34742
            }            
>>>>>>> .r34469
        }
    });
});


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
    $('.formcash').validate({
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

/**
 * 检测是否是首次提现
 * @returns {undefined}
 */
function isFristCash() {
    var msg = false;
    $.ajax({
        type: "post",
        url: "/Cash/checkIsFirst",
        data: {},
        dataType: "json",
        async: false,
        beforeSend: function () {
            layer.load();
        },
        success: function (data) {
            layer.hideload();
            if (data.status == 1) {
                msg = true;
            }
        },
        error: function () {
            layer.hideload();
        }
    });
    return msg;
}

/**
 * 检测是否全部授权
 * @returns {undefined}
 */
function isAllAuth() {
    var msg = false;
    $.ajax({
        type: "post",
        url: "/Cash/checkIsAllAuth",
        data: {},
        dataType: "json",
        async: false,
        beforeSend: function () {
            layer.load();
        },
        success: function (data) {
            layer.hideload();
            if (data.status == 1) {
                msg = true;
            }
        },
        error: function () {
            layer.hideload();
        }
    });
    return msg;
}

function tabClick(obj, tab, tabs) {

    $(obj).closest('ul').children("li").removeClass('selectTag')
    $(obj).addClass('selectTag');
    $("#" + tabs + "").children().css('display', 'none');
    $("#" + tab + "").show();
    switch (tab) {
        case"tagContent2":
            getWithdrawRecord(1);
            break;
        default:
            break;
    }

}

function getWithdrawRecord(pageIndex) {
    var where = getWithdrawwhere();
    $.ajax({
        type: "post",
        url: "/Cash/cashList",
        data: {'p': pageIndex, 'condition': where},
        dataType: "json",
        beforeSend: function (XMLHttpRequest) {
            layer.load();
        },
        success: function (data) {
            layer.hideload();
            if (data.status == 1) {
                if(where.datatype != 'searchtime') {
                     $("#startlog").val("");
                     $("#endlog").val("");
                }
                $('#cashHtml').html(data.data.html);
                initPage(pageIndex, data.data.page, data.data.total);
                return false;
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

function onclickStateWithdraw(obj, state) {
    $(obj).addClass('btn-blue').siblings("a").removeClass('btn-blue');

    $("#startWithdraw").removeClass('error');
    $("#endWithdraw").removeClass('error');
    $(".form-group").find('label').remove();
    $("#txtsearchStateWithdraw").val(state);
    getWithdrawRecord(1);
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
        getLink: function (n) {
            return this.hrefFormer + this.hrefLatter + "?pno=" + n;
        },
        mode: 'click', //默认值是link，可选link或者click
        click: function (n) {
            getWithdrawRecord(n, null);
            this.selectPage(n);
            return false;
        }
    });
}

function onclickAWithdraw(obj, type) {
    $(obj).addClass('btn-blue').siblings("a").removeClass('btn-blue');
    $("#hidsearchWithdraw").val(type);
    $("#startWithdraw").val("");
    $("#endWithdraw").val("");
    $("#startWithdraw").removeClass('error');
    $("#endWithdraw").removeClass('error');
    $(".form-group").find('label').remove();
    getWithdrawRecord(1);
}
function getWithdrawwhere() {
    var emp = new Object();
    var myDate = new Date();
    var now = myDate.getTime();
    emp.end = timestampToTime(now);
    emp.datatype = $("#hidsearchWithdraw").val();
    var type = $("#hidsearchWithdraw").val();
    if(type == "week") {   //最近一周
        emp.start = timestampToTime(now-(86400000*7)); 
    } else if(type == "month") {   //最近一个月 
        emp.start = timestampToTime(now-(86400000*30));
    } else if( type == "3month") { //最近三个月
        emp.start = timestampToTime(now-(86400000*30*3));
    } else if(type == "searchtime") {
        emp.start = $("#startlog").val();
        emp.end = $("#endlog").val();
    }
    emp.state = $("#txtsearchStateWithdraw").val();
    return emp;
}
/*
 * 时间戳转换成时间
 * 
 */
function timestampToTime(timestamp) {
        var date = new Date(timestamp);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
        Y = date.getFullYear() + '-';
        M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
        D = date.getDate() + ' ';
        h = date.getHours() + ':';
        m = date.getMinutes() + ':';
        s = date.getSeconds();
        return Y+M+D+h+m+s;
}
function searchWithdrawValidate() {
    $.validator.addMethod("datevalidate", function (value, element) {
        var startdate = new Date(($("#startWithdraw").val()).replace(/-/g, "/"));
        var enddate = new Date(($("#endWithdraw").val()).replace(/-/g, "/"));
        if (enddate > 0) {
            if (enddate < startdate) {
                return false;
            }
        }
        return true;
    }, "结束日期不能小于开始日期");
    $.validator.addMethod("datelimit", function (value, element) {
        var startdate = $("#startWithdraw").val();
        var enddate = $("#endWithdraw").val();
        var m1 = parseInt(startdate.split("-")[1].replace(/^0+/, "")) + parseInt(startdate.split("-")[0]) * 12;
        var m2 = parseInt(enddate.split("-")[1].replace(/^0+/, "")) + parseInt(enddate.split("-")[0]) * 12;
        if ((m2 - m1) > 6) {
            return false;
        }
        return true;
    }, "不得大于半年");
    $('.formcash').validate({
        rules: {
            startWithdraw: {
                required: true,
                dateISO: true
            },
            endWithdraw: {
                required: true,
                datevalidate: true,
                dateISO: true,
                datelimit: true
            }
        },
        messages: {
            startWithdraw: {
                required: "请输入开始日期",
                dateISO: "日期格式不正确"
            },
            endWithdraw: {
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
function hxCashValid() {
    $.validator.addMethod("useMoney", function () {
        var usemoney = $("#usemoney").val();
        var amount = $("#amount").val();
        if (parseInt(usemoney) < parseInt(amount)) {
            return false;
        } else {
            return true;
        }
    }, "超过可用金额!");
    $.validator.addMethod("WMinMoney", function (value, element) {
        if (value * 1 < 100) {
            return false;
        }
        return true;
    }, "提现金额至少100元");
    $('#cashForm').validate({
        rules: {
            amount: {
                required: true,
                pointMoney: true,
                useMoney: true,
                WMinMoney: true
            },
        },
        messages: {
            amount: {
                required: "请输入提现金额",
                pointMoney: "输入金额格式不正确"
            }
        }
    });
}