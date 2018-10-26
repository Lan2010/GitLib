/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(function() {
    tendervalidate();
    $("#allTender").click(function() {
        getMaxMoney();
    });
    $("#butTender").click(function() {
        var isOK = $("#form_tender").valid();
        if (isOK) {
            $("#form_tender").submit();
        }
    });
    getInvestRecordsMore(1);
    // 记载更多投资记录
    $("#btnGetMoreInvestRecords").click(function() {
        getInvestRecordsMore($("#pageIndex1").val());
    });
    //加载收益
    $("#txtMoney").keyup(function() {
        var OK = $("#txtMoney").valid();
        var borrowNO = $("#hidNum").val();
        var investAmount = this.value;
        if (OK) {
            $.ajax({
                url: "/Mobile/Project/getExpectIncome",
                type: "POST",
                data: {borrowNO: borrowNO, investAmount: investAmount},
                dataType: "json",
                success: function(data) {
                    if (data.status == 1) {
                        $("#profit").val(data.data);
                    }
                }
            });
        } else {
            $("#profit").val("");
        }
    });
});

function getMaxMoney() {

    var max = $("#hidMax").val();
    var balance = $("#hidBalance").val();
    var money = $("#hidAvMoney").val();
    money = (("" === money) ? 0 : money);
    max = (("" === max) ? 0 : max);
    balance = (("" === balance) ? 0 : balance);
    balance = balance > max ? max : balance;
    money = parseInt(money) > parseInt(balance) ? parseInt(balance) : parseInt(money);
    money = parseInt(money / 100) * 100;
    $("#txtMoney").val(money);
    //加载收益
    var OK = $("#txtMoney").valid();
    var borrowNO = $("#hidNum").val();
    var investAmount = money;
    if (OK) {
        $.ajax({
            url: "/Mobile/Project/getExpectIncome",
            type: "POST",
            data: {borrowNO: borrowNO, investAmount: investAmount},
            dataType: "json",
            success: function(data) {
                if (data.status == 1) {
                    $("#profit").val(data.data);
                }
            }
        });
    } else {
        $("#profit").val("");
    }
}


// 加载项目投资记录更多
function getInvestRecordsMore(pageIndex) {
    var $tenderHtml = $('#tenderHtml');
    var key = $("#hidNum").val();
    $.ajax({
        type: "post",
        url: "/Mobile/Project/getInvestLog",
        data: {'borrowNO': key, 'p': pageIndex},
        dataType: "json",
        beforeSend: function(XMLHttpRequest) {
            $("#btnGetMoreInvestRecords").prop('disabled', true).val("加载中......");
        },
        success: function(data) {
            if (data.status == 1) {
                if (data.data.Rows.length > 0) {
                    var isbln = data.data.userID;
                    $.each(data.data.Rows, function(index, item) {
                        var ticketMoney = "";
                        var ticketMoneytxt = "";
                        var classtxt = "";
                        if (item.ticketMoney != 0)
                        {
                            ticketMoney = "<span class='ticket_icon icon' title='优惠券收益'>"
                            ticketMoneytxt = "+" + item.ticketMoneyFormat;
                        }
                        if (isbln == item.userID)
                        {
                            classtxt = "style='color:orange'";
                        }

                        var backhtml = "<tr " + classtxt + " id=" + item.tenderID + ">";
                        backhtml += "	<td>" + item.hidPhone + "</td>";
                        backhtml += "	<td>" + item.realTenderMoneyFormat + ticketMoneytxt + "</td>";
                        backhtml += "	<td>" + item.tenderInterestFormat + " </td>";
                        backhtml += "	<td>" + item.awardMoneyFormat + "  </td>";
                        backhtml += "	<td>" + item.addDatetimeFormat + "</td>";
                        backhtml += "</tr>";
                        $tenderHtml.append(backhtml);

                    });
                    $("#pageIndex1").val(pageIndex * 1 + 1);
                    $("#btnGetMoreInvestRecords").prop('disabled', false).val("加载更多");
                } else {
                    $("#btnGetMoreInvestRecords").val("未找到更多记录");
                }
            } else {
                popMsg(data.msg);
            }
        }
    });
}

function   tendervalidate() {
    $.validator.addMethod("MinMoney", function(value, element) {
        var min = $("#hidMin").val();
        min = min.replace(/,/g, "") * 1;
        if (value * 1 < min) {
            return false;
        }
        return true;
    }, " 金额输入超出范围");
    $.validator.addMethod("MaxMoney", function(value, element) {
        var max = $("#hidMax").val();
        max = max.replace(/,/g, "") * 1;
        if (value * 1 > max) {
            return false;
        }
        return true;
    }, "金额输入超出范围");
    $.validator.addMethod("step", function() {
        var txtMoney = $("#txtMoney").val();

        if (txtMoney % 100 != 0) {
            return false;
        }
        return true;
    }, "金额为100的整数倍");
    $('#form_tender').validate({
        errorElement: 'label',
        errorClass: 'control-error',
        focusInvalid: false,
        rules: {
            txtMoney: {
                required: true,
                unPointMoney: true,
                MinMoney: true,
                MaxMoney: true,
                step: true,
            }
        },
        messages: {
            txtMoney: {
                required: "请输入投资金额",
                unPointMoney: "请输入正整数",
                MinMoney: "最小投标金额" + $("#hidMin").val() + "元",
                MaxMoney: "最大投标金额" + $("#hidMax").val() + "元",
                step: "金额为100的整数倍",
            }

        },
        invalidHandler: function() {
        },
        highlight: function(element) {
            $(element).addClass('has-error');
        },
        success: function(label) {
            label.removeClass('has-error');
            label.remove();
        },
        errorPlacement: function(error, element) {
            error.insertAfter(element);
            $(error).css("width", element[0].clientWidth - 3);
        },
        submitHandler: function(form) {
            form.submit();
        }
    });

    if (typeof ($("#txtPassword").val()) != "undefined")
    {
        $("#txtPassword").rules("add", {required: true, minlength: 6, maxlength: 6,
            messages: {
                required: "请输入投资密码",
                minlength: "请输入6位密码",
                maxlength: "请输入6位密码"
            }
        });
    }
}
