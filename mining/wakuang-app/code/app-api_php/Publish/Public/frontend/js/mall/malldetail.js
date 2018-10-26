$(function () {
    var isPromote = $("#isPromote").val();
    if (isPromote == 1) {
        var startTime = $("#promoteStartDate").val();
        var endTime = $("#promoteEndDate").val();
        if (endTime <= 3600000 && endTime > 0) {
            remainedTime(endTime, 2);
        } else if (startTime > 0 && startTime < 3600000) {
            remainedTime(startTime, 1);
        } else {
            var activityStr;
            if (startTime > 0 && startTime > 3600000) {
                activityStr = "淘抢购于" + $("#actStart").val() + "开始";
            }
            if (endTime > 360000 && startTime <= 0) {
                activityStr = "淘抢购于" + $("#actEnd").val() + "结束";
            }
            $(".activityTime").text(activityStr);
            $(".activityTime").show();
        }
    }
    //倒计时
    function remainedTime(endTime, status) {
        var surTime = $("#surTime");
        this.endTime = endTime - 1000;
        var int_day, int_hour, int_minute, int_second;
        if (endTime > 0) {
            int_day = Math.floor(endTime / 86400000);
            endTime -= int_day * 86400000;
            int_hour = Math.floor(endTime / 3600000);
            endTime -= int_hour * 3600000;
            int_minute = Math.floor(endTime / 60000);
            endTime -= int_minute * 60000;
            int_second = Math.floor(endTime / 1000);
            if (int_hour < 10)
                int_hour = "0" + int_hour;
            if (int_minute < 10)
                int_minute = "0" + int_minute;
            if (int_second < 10)
                int_second = "0" + int_second;
            var apart;
            if (status == 1) {
                apart = "距离开始";
            } else {
                apart = "距离结束";
            }
            var str_time = "<span class='fs-16 fc-666'>" + apart + "：</span>"
                    + "<span class='hour'>" + int_hour + "</span>" + "<span class='sprit'>:</span>" + "<span class='minute'>" + int_minute + "</span>"
                    + "<span class='sprit'>:</span>" + "<span class='second'>" + int_second + "</span>";
            surTime.html(str_time);
            setTimeout(function () {
                remainedTime(this.endTime, status);
            }, 1000);
        } else {
            window.location.reload();
        }
    }
});


window.setAmount = {
    init: function () {
        var goodsNumber = parseInt($("#goodsNumber").val());
        var userIntegral = parseInt($("#userInteg").val());
        var goodIntegral = parseInt($("#goodsInteg").val());
        var singleNumber = parseInt($("#totalsingleNumber").val());
        var buyIt = parseInt($("#buyIt").val());
        var uses = parseInt($("#uses").val());
        if (this.min = 1, this.max = goodsNumber, this.count = 1, this.userIntegral = userIntegral, this.goodIntegral = goodIntegral, this.singleNumber = singleNumber, this.$buyIt = buyIt, this.$uses = uses, this.disableAdd = !1, this.disableReduce = !0, this.$buyNum = $("#singleNumber"), this.$buyBtn = $("#exchangeSubmit"), this.$add = $("#choose-btns .subtract"), this.$reduce = $("#choose-btns .plus"), this.$singleTip = $("#once"), this.$integralUse = $(".integralUse"), this.$buyNum.length < 1)
            return!1;
        this.$singleTip.hide();
        var t = this.$buyNum.data("min"), i = this.$buyNum.data("max");
        i && (this.max = i), t && (this.min = t, this.count = t), this.checkLimit(), this.handleChange();
        this.bindExchangeBtn();
    },
    bindExchangeBtn: function () {
        if (this.max > 0) {
            this.$buyBtn.click(function () {
                var goodsID = parseInt($("#goodsID").val());
                var ralstatus = $("#ralstatus").val()
                var tendMoney = $("#tendMoney").val();
                var goodsType = $("#goodsType").val();
                if (goodsType == 135) {
                    if (ralstatus > 0) {
                        if (tendMoney > 0) {
                            $(this).attr("href", "/Shop/mall_order/key/" + goodsID + "/amount/" + ($("#singleNumber").val()));
                        } else {
                            layer.alert("出借过才能兑换实物商品，是否前往出借？", {title: "提示"}, function () {
                                window.location.href = "/Project/plist.html";
                            });
                        }
                    } else {
                        var url = $("#urllocation").val();
                        layer.alert("兑换实物需要先开通华兴银行存管账户，是否前往开通？", {title: "提示"}, function () {
                            window.location.href = url;
                        });
                    }
                } else {
                    $(this).attr("href", "/Shop/mall_order/key/" + goodsID + "/amount/" + ($("#singleNumber").val()));
                }
            });
        } else {
            this.$buyBtn.attr('disabled', "true");
            this.$buyBtn.click(function () {
                layer.open({
                    title: "提示",
                    content: '商品被兑换完了，去看看其他吧！'
                });
            });
        }
    },
    disabledReduce: function (t) {
        this.disableReduce = !0, this.disableAdd = !1, this.$reduce.addClass("disabled"), this.$add.removeClass("disabled"), this.$add.attr("data-disabled", "1"), t ? this.$reduce.removeAttr("data-disabled") : this.$reduce.attr("data-disabled", "1")
    },
    disabledAdd: function (t) {
        this.disableAdd = !0, this.disableReduce = !1, this.$add.addClass("disabled"), this.$reduce.removeClass("disabled"), this.$reduce.attr("data-disabled", "1"), t ? this.$add.removeAttr("data-disabled") : this.$add.attr("data-disabled", "1")
    },
    disabledBuyBtn: function (t) {
        this.disableBuyBtn = !0, this.$buyBtn.addClass("disabled"), t ? this.$buyBtn.removeAttr("data-disabled") : this.$buyBtn.attr("data-disabled", "1")
    },
    enabledAll: function () {
        this.disableAdd = !1, this.disableReduce = !1, this.$reduce.removeClass("disabled").attr("data-disabled", "1"), this.$add.removeClass("disabled").attr("data-disabled", "1")
    },
    getVal: function () {
        return this.$buyNum.val()
    },
    setVal: function (t) {
        this.$buyNum.val(t)
    },
    checkLimit: function () {
        var t = this.$buyNum.data("min"), i = Number(this.getVal());
        i <= 1 && this.disabledReduce(), i >= this.max && this.disabledAdd(!0), i > 1 && i < this.max && this.enabledAll(), t && i === this.min && this.disabledReduce(!0);
        this.checkSingleNum();
        this.checkIntegral();
    },
    checkSingleNum: function () {
        var i = Number(this.getVal());
        if (this.singleNumber > 0 && i > this.singleNumber) {
            this.$singleTip.show();
            this.singleNumber > this.max ? i = this.max : i = this.singleNumber;
            this.count = i;
            this.$buyNum.val(i);
        } else {
            this.$singleTip.hide();
        }
    },
    checkIntegral: function () {
        var i = Number(this.getVal());
        if (this.$buyIt != 1 && this.$uses != 1 && (this.goodIntegral * i) > this.userIntegral) {
            this.$integralUse.text("积分不足");
            this.$integralUse.show();
            this.$buyBtn.hide();
        } else {
            this.$integralUse.hide();
            this.$buyBtn.show();
        }
    },
    isEmpty: function (t) {
        return"" == $.trim(t)
    },
    isFloat: function (t) {
        return Number(t) === t && t % 1 != 0
    },
    add: function () {
        var t = Number(this.getVal());
        return!this.disableAdd && !this.isEmpty(t) && (t > this.min && (this.disableReduce = !1), t >= this.max ? (this.setDisabled(this.$add), this.disableAdd = !0, !1) : (this.disableAdd = !1, this.setEnabled(this.$add), this.count++, this.setVal(this.count), this.checkLimit()))
    },
    reduce: function () {
        var t = Number(this.getVal());
        return!this.disableReduce && !this.isEmpty(t) && (t < this.max && (this.disableAdd = !1), t <= this.min ? (this.setDisabled(this.$reduce), this.disableReduce = !0, !1) : (this.setEnabled(this.$reduce), this.disableReduce = !1, this.count--, this.setVal(this.count), this.checkLimit()))
    },
    handleChange: function () {
        var t = this.getVal(), i = null;
        isNaN(Number(t)) || this.isEmpty(t) || this.isFloat(Number(t)) ? i = this.count : (t < this.min && (i = this.min, this.disabledReduce(1 !== i)), t > this.max && (i = this.max, this.disabledAdd(!0))), i ? (this.count = i, this.$buyNum.val(i)) : this.count = Number(t), this.checkLimit()
    },
    modify: function () {
        this.handleChange();
    },
    setDisabled: function (t) {
        t.attr("data-disabled", 1)
    },
    setEnabled: function (t) {
        t.removeAttr("data-disabled")
    }
}, setAmount.init();