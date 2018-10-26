/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(function() {
    loginValid();
    smsLoginValid();
    $("#butLogin").click(function() {
        var isOK = $("#login_form").valid();
        if (isOK) {
            login();
        }
    });
    document.onkeydown = function(e) {
        var ev = document.all ? window.event : e;
        if (ev.keyCode == 13) {
            var loginType = $("#loginType").val();
            if (parseInt(loginType) * 1 == 1) {
                var isOK = $("#login_form").valid();
                if (isOK) {
                    login();
                }
            } else {
                var isOK = $("#phone-login").valid();
                if (isOK) {
                    smsLogin();
                }
            }
        }
    };
    //验证码登录
    $("#butSmsLogin").click(function() {
        var isOK = $("#phone-login").valid();
        if (isOK) {
            smsLogin();
        }
    });

    $(".butPhone").click(function() {
        var checkPhone = $("#smsUserPhone").valid();
        var checkCode = $("#smsCheckCode").valid();
        if (checkPhone && checkCode) {
            sendCode();
        }
    });

    $(".loginType").click(function() {
        var type = $(this).attr("data");
        if (parseInt(type) * 1 == 1) {
            $("#imgage").attr("src", '/Common/captchaImage/' + Math.random());
            $("#loginType").val("2");
            $('#login_form').hide();
            $('#phone-login').show();
        } else {
            $("#captchaImage").attr("src", '/Common/captchaImage/' + Math.random());
            $("#loginType").val("1");
            $('#phone-login').hide();
            $('#login_form').show();
        }
    });

});

function login() {
    //利用封装的ajax请求
    var obj = {};
    obj.userPhone = $("#userPhone").val();
    obj.userPwd = encrypt("userPwd", $("#userPhone").val());
    obj.checkCode = $("#checkCode").val();
    obj.returnurl = $("#returnurl").val();
    obj.follow = $("#follow").val();
    obj.openID = $("#openID").val();
    obj.userNickname = $("#userNickname").val();
    obj.headimgurl = $("#headimgurl").val();
    $("#butLogin").attr("disabled", true);
    weui.ajax("/Wechat/User/loginUser", obj, function(data) {
        successAjax(data);
    });
}

function smsLogin() {
    //利用封装的ajax请求
    var obj = {};
    obj.userPhone = $("#smsUserPhone").val();
    obj.checkCode = $("#smsCheckCode").val();
    obj.smsCode = $("#smsCode").val();
    obj.returnurl = $("#returnurl").val();
    obj.openID = $("#openID").val();
    obj.userNickname = $("#userNickname").val();
    obj.headimgurl = $("#headimgurl").val();
    $("#butLogin").attr("disabled", true);
    weui.ajax("/Wechat/User/smsLogin", obj, function(data) {
        successAjax(data);
    });
}
//回调函数处理回调回来的数据
function successAjax(data) {
    $("#butLogin").attr("disabled", false);
    if (data.status == 1) {
        var returnurl = data.data;
        if (!returnurl) {
            returnurl = '/Wechat/Account/index';
        }
        window.location.href = returnurl;
    } else {
        $("#imgage").attr("src", '/Common/captchaImage/' + Math.random());
        $.alert(data.msg);
    }
}

function loginValid() {
    $("#login_form").validate({
        rules: {
            userPhone: {required: true, phoneCode: true},
            userPwd: {required: true, minlength: 6},
            checkCode: {required: true, maxlength: 4, minlength: 4},
        },
        messages: {
            userPhone: {required: "请输入手机号码", phoneCode: "手机号码不正确"},
            userPwd: {required: "请输入密码", minlength: "密码不能小于6个字符"},
            checkCode: {required: "请输入图片验证码", maxlength: "验证码为四位字符", minlength: "验证码为四位字符"},
        }
    });
}
//验证码登录验证
function smsLoginValid() {
    $("#phone-login").validate({
        rules: {
            smsUserPhone: {required: true, phoneCode: true},
            smsCheckCode: {required: true, maxlength: 4, minlength: 4},
            smsCode: {required: true}
        },
        messages: {
            smsUserPhone: {required: "请输入手机号码", phoneCode: "手机号码不正确"},
            smsCheckCode: {required: "请输入图片验证码", maxlength: "验证码为四位字符", minlength: "验证码为四位字符"},
            smsCode: {required: "请输入短信验证码"}
        }
    });
}
//发送手机验证码
function sendCode() {
    var url = "/Wechat/User/loginPhoneCode/";
    var data = {};
    data.phone = $("#smsUserPhone").val();
    data.code = $("#smsCheckCode").val();
    weui.ajax(url, data, function(info) {
        sendSuccessAjax(info);
    });

}

//发送短信回调函数
function sendSuccessAjax(data) {
    if (data.status == 1) {
        jstimer(120);
    } else {
        $("#captchaImage").attr("src", '/Common/captchaImage/' + Math.random());
        $.alert(data.msg);
    }
}
//倒计时
function jstimer(step) {
    step = step <= 120 && step >= 0 ? step : 120;
    $(".butPhone").html(step-- + " S").attr("disabled", true);
    if (step < 0) {
        $(".butPhone").html("免费获取").removeAttr("disabled");
    } else {
        window.setTimeout("jstimer(" + step + ")", 1000);
    }
}
