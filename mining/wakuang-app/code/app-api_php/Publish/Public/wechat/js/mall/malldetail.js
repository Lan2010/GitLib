$(function() {
    $("#once").hide();
    var totalsingleNumber = parseInt($("#totalsingleNumber").val());
    var goodsNumber = parseInt($("#goodsNumber").val())
    var saleNumber = parseInt($("#saleNumber").val());
    var goodsID = parseInt($("#goodsID").val());
    $(".plus").click(function() {
        $("#singleNumber").attr("value", function(index, value) {
            if (parseInt(value) < totalsingleNumber || totalsingleNumber == 0) {
                return (value = (value - 0) + 1);
            } else {
                $("#once").show();
            }
        });
    });
    if (goodsNumber - saleNumber == 0) {
        //$("#exchangeSubmit").attr('disabled', "true");
        $("#exchangeSubmit").addClass('bg_gray1');
        $("#exchangeSubmit").click(function() {
            $.alert({
                title: "提示",
                text: '商品被兑换完了，去看看其他吧！',
                onOK: function() {
                    window.location.href = "/Wechat/Shop/mall";
                }
            });
        });
    } else {
        $("#exchangeSubmit").click(function() {
            var ralstatus = $("#ralstatus").val()
            var tendMoney = $("#tendMoney").val();
            var goodsType = $("#goodsType").val();
            if (goodsType == 135) {
                if (ralstatus > 0) {
                    if (tendMoney > 0) {
                        window.location.href = "/Wechat/Shop/confirmOrder/goodsID/" + goodsID + "/amount/" + ($("#singleNumber").val());
                    } else {
                        $.alert({title: "提示", text: "出借过才能兑换实物商品，是否前往出借？", onOK: function() {
                                window.location.href = "/Wechat/Shop/mall";
                            }});
                    }
                } else {
                    $.alert({title: "提示", text: "兑换实物需要先开通华兴银行存管账户，是否前往开通？", onOK: function() {
                            window.location.href = "/Wechat/Huax/realName";
                        }});
                }
            } else {
                window.location.href = "/Wechat/Shop/confirmOrder/goodsID/" + goodsID + "/amount/" + ($("#singleNumber").val());
            }
        });
    }
    $(".subtract").click(function() {

        $("#singleNumber").attr("value", function(index, value) {
            if (value <= totalsingleNumber) {
                $("#once").hide();
            }
            if ((value - 0) - 1 > 0) {
                return (value = (value - 0) - 1);
            }
        });
    });
    $("#singleNumber").blur(function() {
        $(this).attr("value", function(index, value) {
            if (value > totalsingleNumber && totalsingleNumber != 0) {
                $("#once").show();
                return  totalsingleNumber;
            }
        });
    });
});