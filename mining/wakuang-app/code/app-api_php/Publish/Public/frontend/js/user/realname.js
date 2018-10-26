
var qhz = qhz || {};
qhz.realname = {}, qhz.realname.init = function () {
    this.submit();
    this.ok();
    this.error();
}, qhz.realname.tip = {
    show: function () {
        $(".modalbox").show();
    },
    hide: function () {
        $(".modalbox").hide();
    }
}, qhz.realname.ok = function () {
    $(".modalbox a[class*='operateOK']").click(function () {
        window.location.href = "/Account/index.html";
    });
}, qhz.realname.error = function () {
    $(".modalbox a[class*='operateErr']").click(function () {
        window.location.reload();
    });
}, qhz.realname.submit = function () {
    $("#form_realname").validate({
        errorClass: "pageerror",
        rules: {
            txtName: {
                required: true,
                chinese: true,
                rangelength: [2, 6]
            },
            txtCardID: {
                required: true,
                cardno: true
            }
        },
        messages: {
            txtName: {
                required: "请输入您真实的姓名",
                chinese: "真实姓名必须为中文",
                rangelength: "请输入2-6位中文姓名"
            },
            txtCardID: {
                required: "请输入您的18位的身份证号码",
                cardno: "身份证号码不合法"
            }
        }
    });

    $("#submitBtn").click(function () {
        if ($("#form_realname").valid()) {
            qhz.realname.tip.show();
            $("#form_realname").submit();
        }
    });
}, $(function () {
    qhz.realname.init();
})


