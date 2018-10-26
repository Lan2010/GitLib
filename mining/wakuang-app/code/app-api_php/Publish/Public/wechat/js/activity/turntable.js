
!function ($) {
    "use strict";

    var Lottery = function (el, options) {
        this.init(el, options);
    };
    Lottery.prototype = {
        init: function (el, options) {
            this.container = $(el);
            this.options = $.extend(true, $.fn.lottery.defaults, options);
            this.options.speed = this.options.initSpeed;

            // 这里为每一个奖项设置一个有序的下标，方便lottery._roll的处理
            // 初始化第一行lottery-group的序列
            for (var i = 0; i < this.options.width; ++i) {
                this.container.find('.lottery-group:first .lottery-unit:eq(' + i + ')').attr('lottery-unit-index', i);
            }
            var count = this.count();
            // 初始化最后一行lottery-group的序列
            for (var i = count - this.options.height + 1, j = 0, len = this.options.width + this.options.height - 2; i >= len; --i, ++j) {
                this.container.find('.lottery-group:last .lottery-unit:eq(' + j + ')').attr('lottery-unit-index', i);
            }

            // 初始化两侧lottery-group的序列
            for (var i = 1, len = this.options.height - 2; i <= len; ++i) {
                this.container.find('.lottery-group:eq(' + i + ') .lottery-unit:first').attr('lottery-unit-index', count - i);
                this.container.find('.lottery-group:eq(' + i + ') .lottery-unit:last').attr('lottery-unit-index', this.options.width + i - 1);
            }
            this.enable();
        },
        count: function () {
            return this.options.width * this.options.height - (this.options.width - 2) * (this.options.height - 2);
        },
        // 开启抽奖
        enable: function () {
            this.options.msg = "";
            this.options.target = 11;
            this.options.showMsg = false;
            var that = this;
            this.container.find('a').bind('click', function () {
                that.beforeRoll();
            });
        },
        // 禁用抽奖
        disable: function () {
            this.container.find('a').unbind('click');
        },
        // 转盘滚动
        roll: function () {
            clearTimeout(this.rollerTimer);
            this.container.find('[lottery-unit-index=' + this.nowindex() + ']').removeClass("active");
            ++this.options.index;
            this.container.find('[lottery-unit-index=' + this.nowindex() + '].lottery-unit').addClass("active");
            var that = this;
            this.rollerTimer = setTimeout(function () {
                that.roll();
            }, this.options.speed);
        },
        // 转盘加速
        speedup: function () {
            clearTimeout(this.upTimer);
            if (this.options.speed <= this.options.upMax) {
                this.constant();
            } else {
                var that = this;
                this.options.speed -= this.options.upStep;
                this.upTimer = setTimeout(function () {
                    that.speedup();
                }, this.options.speed);
            }
        },
        // 转盘匀速
        constant: function () {
            var that = this;
            that.aim();
            if (this.upTimer != null) {
                clearTimeout(this.upTimer);
            }
            this.conTimer = setTimeout(function () {
                that.slowdown();
            }, this.options.waiting);
        },
        // 转盘减速
        slowdown: function () {
            clearTimeout(this.conTimer);
            clearTimeout(this.downTimer);
            if (this.options.speed > this.options.downMax && this.options.target == this.nowindex()) {
                this.stop();
            } else {
                var that = this;
                this.options.speed += this.options.downStep;
                this.downTimer = setTimeout(function () {
                    that.slowdown();
                }, this.options.speed);
            }
        },
        start: function () {
            this.options.showMsg = false;
            this.options.target = 11;
            this.roll();
            this.speedup();
        },
        // 转盘停止，还原设置
        stop: function () {
            clearTimeout(this.downTimer);
            clearTimeout(this.rollerTimer);
            clearTimeout(this.upTimer);
            this.options.speed = this.options.initSpeed;
            this.options.isRunning = false;
            if (this.options.target == 5 || this.options.target == 11) {
                if (this.options.showMsg && this.options.msg != "") {
                    $.alert({
                        title: '提示',
                        text: this.options.msg,
                        onOK: function () {
                            window.location.reload();
                        }
                    });
                }
            } else {
                if (this.options.showMsg && this.options.msg != "") {
                    $.alert({
                        title: '提示',
                        text: "恭喜您获得" + this.options.msg,
                        onOK: function () {
                            window.location.reload();
                        }
                    });
                }
            }
            this.enable();
        },
        // 抽奖之前的操作，支持用户追加操作
        beforeRoll: function () {
            var _this = this;
            this.disable();
            $.ajax({
                type: "post",
                url: "/Wechat/Activity/checkLottey",
                cache: false,
                dataType: "json",
                beforeSend: function () {
                    weui.load();
                },
                success: function (data) {
                    weui.hideload();
                    if (data.status == 1) {
                        _this.start();
                    } else if (data.status == -1) {
                        _this.options.showMsg = false;
                        _this.stop();
                        qhzapp.jumpToPage.jumpToLogin();
                    } else {
                        _this.options.showMsg = false;
                        _this.stop();
                        // layer.alert(data.msg);   
                        $.alert(data.msg);
                    }
                },
                error: function () {
                    //   layer.hideload();
                    //  layer.msg("系统繁忙，请稍后再试!");
                    weui.hideload();
                    $.alert("系统繁忙，请稍后再试!");
                    return false;
                }
            });
        },
        // 转盘当前格子下标
        nowindex: function () {
            return this.options.index % this.count();
        },
        // 获取中奖号，用户可重写该事件，默认随机数字
        aim: function () {
            var that = this;
            $.ajax({
                type: "post",
                url: "/Wechat/Activity/recordLottery",
                cache: false,
                dataType: "json",
                success: function (data) {
                    that.options.showMsg = true;
                    that.options.target = data.data;
                    that.options.msg = data.msg;
                    if (data.status == -1) {
                        that.options.showMsg = false;
                        that.stop();
                        qhzapp.jumpToPage.jumpToLogin();
                        $("#btn1").click(function () {
                            qhzapp.jumpToPage.jumpToLogin();
                        });
                    } else if (data.status == 0) {
                        that.options.showMsg = false;
                    }
                },
                error: function () {
                    that.options.target = 11;
                }
            });
        }
    }
    $.fn.lottery = function (options) {
        return this.each(function () {
            new Lottery(this, options);
        });
    };
    $.fn.lottery.defaults = {
        width: 4, // 转盘宽度
        height: 4, // 转盘高度
        initSpeed: 300, // 初始转动速度
        speed: 0, // 当前转动速度
        upStep: 50, // 加速滚动步长
        upMax: 70, // 速度上限
        downStep: 30, // 减速滚动步长
        downMax: 500, // 减速上限
        waiting: 3000, // 匀速转动时长
        index: 0, // 初始位置
        target: 11, // 中奖位置，可通过后台算法来获得，默认值：最便宜的一个奖项或者"谢谢参与"
        isRunning: false, // 当前是否正在抽奖
        showMsg: false,
        msg: ""
    };
}(jQuery);

//webview返回刷新(非与APP共用页面可以用)
qhzapp.webviewOnShow(function () {
    window.location.href = window.location.href;
    window.location.reload();
});

//相当于jq的ready方法。
qhzapp.ready(function () {
    $("#lottery").lottery();
});
