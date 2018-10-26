var qhz = qhz || {};
qhz.forgetPwdStep1 = {},
        qhz.forgetPwdStep2 = {},
        qhz.forgetPwdStep1.init = function() {
            this.submit();
            $("#btnPhone").qhzCutDownBtn();
        }, qhz.forgetPwdStep1.submit = function() {
    $("#formStep1").validate({
        errorClass: "pageerror",
        rules: {
            txtPhone: {
                required: true,
                phone: true
            },
            txtCode: {
                required: true
            },
            smsCode: {
                required: true
            }
        },
        messages: {
            txtPhone: {
                required: "请输入手机号码",
                phone: "手机号码不正确"
            },
            txtCode: {
                required: "请输入图片验证码"
            },
            smsCode: {
                required: "请输入短信验证码"
            }
        }
    });
    $("#submitBtn").click(function() {
        if ($("#formStep1").valid()) {
            $("#formStep1").submit();
        }
    });
}, qhz.forgetPwdStep2.init = function() {
    this.submit();
}, qhz.forgetPwdStep2.submit = function() {

    $("#form-rest").validate({
        errorClass: "pageerror",
        rules: {
            txtRealName: {
                required: true,
            },
            txtPassword: {
                required: true,
                rangelength: [6, 16]
            },
            verifypwd: {
                required: true,
                equalTo: '#txtPassword'
            },
        },
        messages: {
            txtRealName: {
                 required: "请输入您的姓名",
            },
            txtPassword: {
                required: "密码不能为空",
                rangelength: "6-16位字符，包括英文字母、数字或符号"
            },
            verifypwd: {
                required: "确认密码不能为空",
                equalTo: "确认密码不一致"
            },
        }
    });
    $("#submitBtn").click(function() {
        if ($("#form-rest").valid()) {
            $.ajax({
                type: "post",
                url: "/User/changePassWord",
                data: {'phone': $("#hidPhone").val(),'realName': $("#txtRealName").val(), 'code': $("#hidCode").val(), 'password': $('#txtPassword').encrypt($("#hidPhone").val())},
                dataType: "json",
                beforeSend: function() {
                    $("#submitBtn").attr('disabled', "true");
                    layer.load();
                },
                success: function(context) {
                    if (context.status == 1) {
                        window.location.href = "/User/resetsucceed.html";
                    } else if (context.status == -2) {
                        layer.msg(context.msg, function() {
                            history.back(-1);
                        });
                    } else {
                        layer.hideload();
                        $("#submitBtn").removeAttr("disabled");
                        layer.msg(context.msg);
                    }
                },
                error: function() {
                    $("#submitBtn").removeAttr("disabled");
                    layer.hideload();
                }
            });
        }
    });
}, $.fn.qhzCutDownBtn = function() {
    var $that = $(this);
    sendSms = function() {
        var phone = $("#txtPhone").val();
        var imgcode = $("#txtCode").val();
        $.ajax({
            type: "post",
            url: "/User/findCode",
            data: {'phone': phone, 'imgCode': imgcode},
            dataType: "json",
            beforeSend: function() {
                layer.load();
            },
            success: function(data) {
                layer.hideload();
                if (data.status == 1) {
                    var timeCount = 120;
                    var but = $that;
                    var times = setInterval(function() {
                        if (timeCount > 0) {
                            timeCount--;
                            but.val(timeCount + " S")
                        } else {
                            clearInterval(times);
                            but.val("免费获取");
                            but.removeAttr("disabled");
                        }
                    }, 1000);
                } else {
                    $that.removeAttr("disabled");
                    $('#txtImage').attr('src', '/Common/captchaImage/' + Math.random());
                    layer.msg(data.msg);
                }
            },
            error: function() {
                layer.hideload();
                layer.msg("网络错误~");
                $that.removeAttr("disabled");
            }
        });
    };
    this.click(function() {
        $("#btnPhone").attr("disabled", true);
        if ($("#txtPhone").valid() && $("#txtCode").valid()) {
            sendSms();
        } else {
            $("#btnPhone").removeAttr("disabled");
        }
    });
}, qhz.getImgCodeUrl = function() {
    return "//Common/captchaImage/" + Math.random()
};