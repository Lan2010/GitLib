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
            if (!$('#checkfx').is(':checked')) {
                $.alert("请阅读并同意《风险提示函》");
                return '';
            }
            var riskStatus = $("#riskEvalStatus").val();
            if (riskStatus * 1 == 1) {
                $.alert("出借前需风险能力评估，前往评估？", function() {
                    window.location.href = "/Wechat/User/riskAssess";
                });
                return false;
            }
            $("#form_tender").submit();
        }
    });
    //加载收益
    $("#txtMoney").keyup(function() {
        var OK = $("#txtMoney").valid();
        var borrowNO = $("#hidNum").val();
        var investAmount = this.value;
        if (OK) {
            $.ajax({
                url: "/Wechat/Project/getExpectIncome",
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
    $('#txtMoney').blur(function() {
        if (Number($('#txtMoney').val()) > Number($('#hidAvMoney').val())) {
            $.alert('你输入的金额大于账户余额！');
            //$('#txtMoney').val(0);
            return;
        }
        resetTicketList(Number($('#txtMoney').val()));
    });

    $('.ticket').click(function() {
        var obj = $(this).find("input");
        var id = obj.val();
        var num = parseInt(obj.attr('use'));
        if (num === 1) {
            obj.removeAttr('checked');
            //$.alert('此卡券不适用于金额' + $('#txtMoney').val());
            return;
        }
        $(".ticket").find('input').removeAttr('checked');
        $("label").css('background:', '');
        $("label").css('background-size', '');
        $("label").attr('style', '');
        $(obj[0]).attr('checked', 'checked')
        var nodeId = "#label" + id;
        $(nodeId).css("background", "url(/Public/wechat/images/shop/xz_icon.png)  no-repeat");
        $(nodeId).css('background-size', '100%');
        var ticketName = '';
        var nodes = obj.parent().prev();
        var length = parseInt(nodes.children().length);
        if (length > 1) {
            ticketName = $(nodes.children('p')[0]).html();
        } else {
            ticketName = nodes.html()
        }
        $('#ticketName').html(ticketName + obj.attr('title'));
        $('#txtTicket').val(id);
        $('.ticketList').hide();
        return;
    });
});

function getMaxMoney() {
    var max = $("#hidMax").val();
    var balance = $("#hidBalance").val();
    var money = $("#hidAvMoney").val();
    var investAmount = 0;
    money = (("" === money) ? 0 : money);
    max = (("" === max) ? 0 : max);
    balance = (("" === balance) ? 0 : balance);
    money = parseInt(money);
    max = parseInt(max);
    balance = parseInt(balance);
    if (balance > max) {
        if (money < max) {
            investAmount = money;
            investAmount = investAmount - investAmount % 100;
            $("#txtMoney").val(investAmount);
        } else {
            investAmount = max;
            investAmount = investAmount - investAmount % 100;
            $("#txtMoney").val(investAmount);
        }
    } else {
        if (money > balance) {
            investAmount = balance;
            investAmount = investAmount - investAmount % 100;
            $("#txtMoney").val(investAmount);
        } else {
            investAmount = money;
            investAmount = investAmount - investAmount % 100;
            $("#txtMoney").val(investAmount);
        }
    }

    //加载收益
    var OK = $("#txtMoney").valid();
    var borrowNO = $("#hidNum").val();
    if (OK) {
        $.ajax({
            url: "/Wechat/Project/getExpectIncome",
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

function tendervalidate() {
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
                required: "请输入出借金额",
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
        $("#txtPassword").rules("add", {required: true, minlength: 6, maxlength: 7,
            messages: {
                required: "请输入出借密码",
                minlength: "请输入6位密码",
                maxlength: "请输入6位密码"
            }
        });
    }
}

function tikcetList() {
    if (!$('#txtMoney').valid()) {
        return;
    }
    resetTicketList(Number($('#txtMoney').val()));
    if (parseInt($('#hidAvMoney').val()) < 100 || parseInt($('#txtMoney').val()) > parseInt($('#hidAvMoney').val())) {
        $.alert('当前账户可用余额小于100元，或者你输入的金额大于账户余额！');
        return;
    }
    if (Number($("#realStatus").val()) != 1 && Number($("#isLogin").val()) == 1) {
        $.alert('请先开通华兴银行存款账户！');
        return;
    }
    if ($('.ticketList').height() < $('body').height()) {
        $('.ticketList').height($('body').height());
    }
    $('.ticketList').show();
    $('body').scrollTop(0);
}

function resetTicketList(money) {
    var max = 0;
    var min = 0;
    var flag = false;
    var length = $(".ticketList input").length;
    var obj = $(".ticketList input");
    var id = 0;
    for (var i = 0; i < length; i++) {
        min = Number($(obj[i]).attr('min'));
        max = Number($(obj[i]).attr('max'));
        if (money < min || (max > 0 && money > max)) {
            $(obj[i]).attr('use', 1);
            $(obj[i]).parents('.ticket').addClass('desaturate');
            if (typeof ($(obj[i]).attr('checked')) != "undefined") {
                $(obj[i]).removeAttr('checked');
                if (i == 0) {
                    flag = 0;
                }
            }
        } else if ($(obj[i]).val() != "") {
            $(obj[i]).parents('.ticket').removeClass('desaturate');
            $(obj[i]).attr('use', 0);
            if (typeof ($(obj[i]).attr('checked')) != "undefined") {
                flag = i;
            }
        }
    }

    if (flag == 0) {
        if (typeof ($(obj[0]).attr('checked')) == "undefined") {
            $("label").css('background:', '');
            $("label").css('background-size', '');
            $("label").attr('style', '');
            $(".ticket").find('input').removeAttr('checked');
            $(obj[0]).attr('checked', 'checked')
            id = "#label" + $(obj[0]).val();
            $(id).css("background", "url(/Public/wechat/images/shop/xz_icon.png)  no-repeat");
            $(id).css('background-size', '100%');
        }
    }
}
