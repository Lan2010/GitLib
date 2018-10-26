/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(function() {
    $(".tab").click(function() {
        $(".tit1").toggle();
        $(".tit2").toggle();
        $(".tab-content1").toggle();
        $(".tab-content2").toggle();
        $(".pcbtn").slideToggle();
        $(".ewmbtn").slideToggle();
    });
    if ($.cookie('cbmemory_cookie')) {
        $("#txtphone").val($.cookie('cbmemory_cookie'));
        $("#cbmemory").attr("checked", 'true');
    }
    ;
    $('#login_form').validate({
        rules: {
            txtphone: {
                required: true,
                phone: true
            },
            txtPassword: {
                required: true
            },
            imgCode: {
                required: true,
                maxlength: 4,
                minlength: 4
            }
        },
        messages: {
            txtphone: {
                required: "请输入手机号码",
                phone: "手机号码不正确"
            },
            txtPassword: {
                required: "请输入登录密码"
            },
            imgCode: {
                required: "请输入图片验证码",
                maxlength: "验证码为4位数字",
                minlength: "验证码为4位数字",
            }
        }
    });


    $('#login_headrSmsform').validate({
        rules: {
            headrSmsPhone: {
                required: true,
                phone: true
            },
            headrSmSimgCode: {
                required: true,
                maxlength: 4,
                minlength: 4
            },
            txtrphoneCode: {
                required: true,
                maxlength: 6,
                minlength: 6
            }
        },
        messages: {
            headrSmsPhone: {
                required: "请输入手机号码",
                phone: "手机号码不正确"
            },
            headrSmSimgCode: {
                required: "请输入图片验证码",
                maxlength: "验证码为4位数字",
                minlength: "验证码为4位数字",
            },
            txtrphoneCode: {
                required: "请输入短信验证码",
                maxlength: "验证码为六位字符",
                minlength: "验证码为六位字符"
            }
        }
    });

    $("#butLogin").click(function() {
        var isOK = $("#login_form").valid();
        if (isOK) {
            if ($("#cbmemory").prop("checked")) {
                $.cookie('cbmemory_cookie', $("#txtphone").val(), {expires: 7})
            } else {
                $.cookie('cbmemory_cookie', '', {expires: -1});
            }
            startlogin();
        }
    });
    document.onkeydown = function(e) {
        var ev = document.all ? window.event : e;
        if (ev.keyCode == 13) {
            var loginType = $("#loginType").val();
            if (parseInt(loginType) * 1 == 1) {
                var isOK = $("#login_form").valid();
                if (isOK) {
                    startlogin();
                }
            } else {
                var isOK = $("#login_headrSmsform").valid();
                if (isOK) {
                    smsLogin();
                }
            }
        }
    };
    //发送验证码
    $("#butrSmsPhone").click(function() {
        sendCode();
    });
    //短信登录
    $("#butrSmsheadLogin").click(function() {
        var isOK = $("#login_headrSmsform").valid();
        if (isOK) {
            smsLogin();
        }
    });
    $(".loginType").click(function() {
        var type = $(this).attr("data");
        if (parseInt(type) * 1 == 1) {
            $("#CaptchaSmsImageHead").attr("src", '/Common/captchaImage/' + Math.random());
            $("#loginType").val("2");
            $("#accountLogin").hide();
            $("#smsLogin").show();
        } else {
            $("#txtImage").attr("src", '/Common/captchaImage/' + Math.random());
            $("#loginType").val("1");
            $("#smsLogin").hide();
            $("#accountLogin").show();
        }
    });
});
function startlogin() {
    var emp = new Object();
    emp.password = $('#txtPassword').encrypt($("#txtphone").val());
    emp.phone = $("#txtphone").val();
    emp.imgCode = $("#imgCode").val();
    emp.returnurl = $("#returnurl").val();
    $.ajax({
        type: "post",
        url: "/User/loginUser",
        data: {'Par': emp},
        dataType: "json",
        beforeSend: function() {
            layer.load();
            $("#butLogin").attr('disabled', "true");
        },
        success: function(context) {
            layer.hideload();
            if (context.status == 1) {
                qhz.loginsuccess(context.data);
            } else {
                layer.msg(context.msg);
            }
            $("#butLogin").removeAttr("disabled");
        },
        complete: function() {
            layer.hideload();
            $("#butLogin").removeAttr("disabled");
        },
        error: function() {
            layer.hideload();
            $("#butLogin").removeAttr("disabled");
        }
    });
}


//发送手机验证码
function sendCode() {
    var data = {};
    data.phone = $("#headrSmsPhone").val();
    data.code = $("#headrSmSimgCode").val();
    var emp = new Object();
    emp.phone = $("#headrSmsPhone").val();
    emp.smsCode = $("#txtrphoneCode").val();
    $.ajax({
        type: "post",
        url: "/User/loginPhoneCode/",
        data: {'par': data},
        dataType: "json",
        befotsend: function() {
            layer.load();
            $("#butrSmsPhone").attr('disabled', "true");
        },
        success: function(data) {
            layer.hideload();
            if (data.status == 1) {
                jstimer(120);
            } else {
                $("#CaptchaSmsImageHead").attr("src", '/Common/captchaImage/' + Math.random());
                layer.alert(data.msg);
            }
        },
        complete: function() {
            layer.hideload();
            $("#butrSmsPhone").removeAttr("disabled");
        },
        error: function() {
            layer.hideload();
            $("#butrSmsPhone").removeAttr("disabled");
        }
    });
}
//验证码登录
function smsLogin() {
    var emp = new Object();
    emp.phone = $("#headrSmsPhone").val();
    emp.smsCode = $("#txtrphoneCode").val();
    $.ajax({
        type: "post",
        url: "/User/smsLogin",
        data: {'Par': emp},
        dataType: "json",
        beforeSend: function() {
            layer.load();
            $("#butrSmsheadLogin").attr('disabled', "true");
        },
        success: function(context) {
            layer.hideload();
            if (context.status == 1) {
                qhz.loginsuccess(context.data);
            } else {
                layer.msg(context.msg);
            }
            $("#butrSmsheadLogin").removeAttr("disabled");
        },
        complete: function() {
            layer.hideload();
            $("#butrSmsheadLogin").removeAttr("disabled");
        },
        error: function() {
            layer.hideload();
            $("#butrSmsheadLogin").removeAttr("disabled");
        }
    });
}


//倒计时
function jstimer(step) {
    step = step <= 120 && step >= 0 ? step : 120;
    $("#butrSmsPhone").val(step-- + " S").attr("disabled", true);
    if (step < 0) {
        $("#butrSmsPhone").val("免费获取").removeAttr("disabled");
    } else {
        window.setTimeout("jstimer(" + step + ")", 1000);
    }
}

