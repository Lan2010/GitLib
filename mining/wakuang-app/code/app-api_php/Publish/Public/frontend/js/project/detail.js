var qhz = qhz || {};
qhz.detail = {};
qhz.detail.init = function (detailData) {
    this.expectIncome(detailData), this.debtSubmit(detailData), this.investLog(1), this.fullinvest(detailData),
            this.selectTag(), this.photo(), this.showlogin(), this.ok(), this.error(), this.bindCouponEvt();
}, qhz.detail.expectIncome = function (detailData) {
    var that = this;
    $("#txtMoney").on("input propertychange", function () {
        if (!$("#txtMoney").valid()) {
            $("#expectIncomeAmount div").hide();
            return;
        }
        var investAmount = this.value;
        var preInvestAmount = $("#preInvestAmount").val();
        if (preInvestAmount != investAmount) {
            $("#preInvestAmount").val(investAmount);
            if (investAmount >= detailData.min) {
                $.ajax({
                    url: "/Project/getExpectIncome",
                    type: "POST",
                    data: {
                        "borrowNO": detailData.borrowId,
                        "investAmount": investAmount
                    },
                    dataType: "json",
                    success: function (data) {
                        if (data.status == 1) {
                            $("#expectIncomeAmount div span[class='text-danger']").html(data.data);
                            $("#expectIncomeAmount div").show();
                        } else {
                            $("#expectIncomeAmount div").hide();
                        }
                    }
                });
                that.resetCouponEvt();
            } else {
                $("#expectIncomeAmount div").hide();
            }
        }
    });
}, qhz.detail.investLog = function (pageIndex) {
    var _this = this;
    var borrowNo = ($("#hidDebeNum").val() == undefined) ? $("#hidNum").val() : $("#hidDebeNum").val();
    var url = ($("#hidDebeNum").val() == undefined) ? "/Project/getInvestLog" : "/Project/getDebeInvestLog";
    $.ajax({
        type: "post",
        url: url,
        data: {"borrowNO": borrowNo, "p": pageIndex},
        dataType: "json",
        beforeSend: function () {
            layer.load();
        },
        success: function (data) {
            layer.hideload();
            if (data.status == 1) {
                $("#investlog").html(data.data.html);
                _this.logpager(pageIndex, data.data.totalPage, data.data.Records);
            } else {
                layer.msg(data.msg);
            }
        },
        error: function () {
            layer.hideload();
        }
    });
}, qhz.detail.logpager = function (pageNo, totalPage, totalRecords) {
    var _this = this;
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
            _this.investLog(n);
            this.selectPage(n);
            return false;
        }
    });
}, qhz.detail.debtSubmit = function (detailData) {
    var _this = this;
    var rules = {
        txtMoney: {
            required: true,
            unPointMoney: true,
            range: [detailData.min, detailData.max],
            step: 100,
            //max: detailData.avBalance
        }
    };
    var messages = {
        txtMoney: {
            required: "请输入一个介于 " + detailData.min + " 和 " + detailData.max + " 之间的值",
            unPointMoney: "请输入一个大于0的正整数",
            step: "金额为100的整数倍"
                    //max: "可用余额不足"
        }
    };
    if (detailData.isPassWord == 1) {
        rules = $.extend({}, rules, {txtPassword: {required: true}});
        messages = $.extend({}, messages, {txtPassword: {required: "请输入出借密码"}});
    } else {
        rules = $.extend({}, rules, {txtCode: {required: true, minlength: 4}});
        messages = $.extend({}, messages, {txtCode: {required: "请输入验证码", minlength: "请输入正确的验证码"}});
    }
    $('#form_tender').validate({
        errorClass: "toperror",
        rules: rules,
        messages: messages
    });
    $("#subBtn").click(function () {
        if (!$("#subBtn").attr("disabled")) {
            var isOK = $("#form_tender").valid();
            if (isOK) {
                if (!$('#checkfx').is(':checked')) {
                    layer.msg("请阅读并同意《风险提示函》");
                    return '';
                }
                var riskStatus = $("#riskEvalStatus").val();
                if (riskStatus * 1 == 1) {
                    layer.confirm("出借前需风险能力评估，前往评估？", function () {
                        window.location.href = "/User/riskAssess.html";
                    });
                    return false;
                }
                _this.tip.show();
                $("#form_tender").submit();
            }
        }
    });
}, qhz.detail.tip = {
    show: function () {
        $(".modalbox").show();
    },
    hide: function () {
        $(".modalbox").hide();
    }
}, qhz.detail.ok = function () {
    $(".modalbox a[class*='operateOK']").click(function () {
        window.location.reload();
    });
}, qhz.detail.error = function () {
    $(".modalbox a[class*='operateErr']").click(function () {
        qhz.detail.tip.hide();
    });
}, qhz.detail.invest = function () {
    var emp = new Object();
    emp.money = $("#txtMoney").val();
    emp.borrowNO = $("#hidNum").val();
    emp.password = $("#txtPassword").val();
    emp.imgCode = $("#txtCode").val();
    emp.ticketID = $("#selticket").val();
    $.ajax({
        type: "post",
        url: "/Project/investment",
        data: {'Par': emp},
        dataType: "json",
        beforeSend: function () {
            layer.load();
            $("#subBtn").attr('disabled', "true");
        },
        success: function (data) {
            layer.hideload();
            if (data.status == -1) {
                qhz.showlogin();
            } else if (data.status == 1) {

            } else {
                layer.msg(data.msg);
            }
            $("#subBtn").removeAttr("disabled");
        },
        complete: function () {
            layer.hideload();
            $("#subBtn").removeAttr("disabled");
        },
        error: function () {
            layer.hideload();
            $("#subBtn").removeAttr("disabled");
        }
    });
}, qhz.detail.fullinvest = function (detailData) {
    var that = this;
    $(".fullinvest").click(function () {
        if (!$(".fullinvest").attr("disabled")) {
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
            that.expInterest(detailData);
            that.resetCouponEvt();
        }
    });
}, qhz.detail.selectTag = function () {
    $("#tags_detail li").click(function () {
        if (!$(this).hasClass("selectTag")) {
            $(this).closest('ul').children("li").removeClass('selectTag')
            $(this).addClass('selectTag');
            $("#tagContent").children().css('display', 'none');
            var showtab = $(this).attr('data');
            $("#tagContent div[id='" + showtab + "']").show();
        }
    });
}, qhz.detail.photo = function () {
    $(".zjphoto a[rel=example_group]").fancybox({
        'titlePosition': 'over',
        'titleFormat': function (title, currentArray, currentIndex, currentOpts) {
            return '<span id="fancybox-title-over"> ' + (currentIndex + 1) + ' / ' + currentArray.length + (title.length ? ' &nbsp; ' + title : '') + '</span>';
        }
    });
}, qhz.detail.showlogin = function () {
    $(".showlogin[href='javascript:void(0);']").click(function () {
        qhz.showlogin();
    });
}, qhz.detail.bindCouponEvt = function () {
    var that = this;
    that.trigger = $("[data-toggle=dropdown]"), that.wrapper = $(".couponSelectWrap"), that.options = that.wrapper.find(".options");
    that.lists = that.options.find("li");
    if (that.lists.length == 0) {
        that.updateTriggerText();
        return;
    }
    that.trigger.on("click", function () {
        if ($(this).hasClass('disabled'))
            return;
        that.toggle($(this));
    });
    that.options.on('mouseenter', 'li', function (e) {
        $(this).addClass('current');
    });
    that.options.on('mouseleave', 'li', function (e) {
        $(this).removeClass('current');
    });
    that.options.on('click', 'li', function (e) {
        var index = $(this).index();
        if (!$(this).hasClass("disabled")) {
            that.updateTriggerText(index);
            $(this).siblings(".couponList").removeClass('selected');
            $(this).addClass('selected');
            that.options.removeClass('open');
            that.options.find("li").removeClass("current");
            that.bindSelectCallback($(this), index);
        } else if ($(this).hasClass("discardList")) {   //不适用优惠券
            //更新trigger文案
            that.updateTriggerText(index);
            $("#hidTicketID").val('').attr({min: ''});
            that.close();
        }
    });
}, qhz.detail.updateTriggerText = function (index) {
    var that = this;
    if (typeof index == 'undefined') {
        that.trigger = $("[data-toggle=dropdown]"), that.wrapper = $(".couponSelectWrap"), that.options = that.wrapper.find(".options");
        that.lists = that.options.find("li");
        if (that.lists.length > 0) {
            that.trigger.find(".triggerTxt").text("请选择卡券");
        } else {
//            that.trigger.find(".triggerTxt").text("暂无可用卡券");
            that.trigger.addClass('disabled');
        }
    } else {
        that.trigger.find(".triggerTxt").text(that.lists.eq(index).find(".couponName").text() || "不使用卡券");
    }
    that.selectedIndex = index;
}, qhz.detail.bindSelectCallback = function (listItem, index) {
    var ticketid = listItem.attr("val");
    $("#hidTicketID").val(ticketid).attr({min: listItem.attr("min"), max: listItem.attr("max")});
}, qhz.detail.resetCouponEvt = function () {
    var val = $.trim($("#txtMoney").val());
    var that = this;
    var listItems = $(".couponList");
    $.each(listItems, function (idx, n) {
        var $this = $(n);
        $(".trigger").removeClass('warn');
        var max = Number($this.attr("max"));
        if (val < Number($this.attr("min")) || (max > 0 && val > max)) {
            $this.addClass('disabled');
        } else if ($this.attr("val") != "") {
            $this.removeClass('disabled');
        }
    });
    $('#hidTicketID').val('');
    that.updateTriggerText();
    that.close();
}, qhz.detail.toggle = function () {
    var that = this;
    that.trigger.toggleClass('open');
    that.options.toggleClass('open');
}, qhz.detail.close = function () {
    var that = this;
    that.trigger.removeClass('open');
    that.options.removeClass('open');
}, qhz.detail.expInterest = function (detailData) {
    if (!$("#txtMoney").valid()) {
        $("#expectIncomeAmount div").hide();
        return;
    }
    var investAmount = $("#txtMoney").val();
    var preInvestAmount = $("#preInvestAmount").val();
    if (preInvestAmount != investAmount) {
        $("#preInvestAmount").val(investAmount);
        if (investAmount >= detailData.min) {
            $.ajax({
                url: "/Project/getExpectIncome",
                type: "POST",
                data: {
                    "borrowNO": detailData.borrowId,
                    "investAmount": investAmount
                },
                dataType: "json",
                success: function (data) {
                    if (data.status == 1) {
                        $("#expectIncomeAmount div span[class='text-danger']").html(data.data);
                        $("#expectIncomeAmount div").show();
                    } else {
                        $("#expectIncomeAmount div").hide();
                    }
                }
            });
        } else {
            $("#expectIncomeAmount div").hide();
        }
    }
}, $(function () {
    var detailData = {
        borrowId: $("#hidNum").val(),
        min: $("#hidMin").val() || 100,
        max: $("#hidMax").val() || 100,
        isPassWord: $("#hidIsPassWord").val() || 0,
        avBalance: $("#hidAvMoney").val() || 0
    };
    qhz.detail.init(detailData);
    $("#show_" + detailData.borrowId).showTime();
    var debeBorrowNO = $("#hidDebeNum").val();//债转倒计时
    $("#show_" + debeBorrowNO).showTime();
});