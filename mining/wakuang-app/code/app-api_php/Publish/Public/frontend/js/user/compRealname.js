var qhz = qhz || {};
qhz.realname = {}, qhz.realname.init = function() {
    this.submit();
    this.ok();
    this.error();
}, qhz.realname.tip = {
    show: function() {
        $(".modalbox").show();
    },
    hide: function() {
        $(".modalbox").hide();
    }
}, qhz.realname.ok = function() {
    $(".modalbox a[class*='operateOK']").click(function() {
        window.location.href = "/Account/index.html";
    });
}, qhz.realname.error = function() {
    $(".modalbox a[class*='operateErr']").click(function() {
        window.location.reload();
    });
}, qhz.realname.submit = function() {
    $("#form_realname").validate({
        errorClass: "pageerror",
        rules: {
            txtName: {
                required: true
            },
            txtCardID: {
                required: true
            }
        },
        messages: {
            txtName: {
                required: "请输入企业全称"
            },
            txtCardID: {
                required: "请输入组织机构代码"
            }
        }
    });

    $("#submitBtn").click(function() {
        if ($("#form_realname").valid()) {
            qhz.realname.tip.show();
            $("#form_realname").submit();
        }
    });
}, $(function() {
    qhz.realname.init();
})


