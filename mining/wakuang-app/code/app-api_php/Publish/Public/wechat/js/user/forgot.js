/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


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



function forgotValid() {
    $("#forgot_form").validate({
        rules: {
            userPhone: {required: true, phoneCode: true},
            userPwd: {required: true, minlength: 6},
            imgCode: {required: true, maxlength: 4, minlength: 4},
            phoneCode: {required: true},
            chkuserPwd: {required: true, minlength: 6, equalTo: "#userPwd"}

        },
        messages: {
            userPhone: {required: "请输入手机号码", phoneCode: "手机号码不正确"},
            userPwd: {required: "请输入密码", minlength: "密码不能小于6个字符"},
            imgCode: {required: "请输入图片验证码", maxlength: "验证码为四位字符", minlength: "验证码为四位字符"},
            phoneCode: {required: "请输入短信验证码"},
            chkuserPwd: {required: "请输入确认密码", minlength: "密码不能小于6个字符", equalTo: "两次输入密码不一致"}
        }
    });
}



//发送手机验证码
function sendCode() {
    var url = "/Wechat/User/forgotPhoneCode/";
    var data = {};
    data.phone = $("#userPhone").val();
    data.code = $("#imgCode").val();
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



function forgot() {
    //利用封装的ajax请求
    var obj = {};
    obj.userPhone = $("#userPhone").val();
    obj.userPwd = encrypt("userPwd", $("#phoneCode").val());
    obj.phoneCode = $("#phoneCode").val();
    obj.chkuserPwd = encrypt("chkuserPwd", $("#phoneCode").val());
    $("#butReg").attr("disabled", true);
    weui.ajax("/Wechat/User/forgotSave", obj, function(data) {
        successAjax(data);
    });
}

//回调函数处理回调回来的数据
function successAjax(data) {
    $("#butReg").attr("disabled", false);
    if (data.status == 1) {
        weui.message("恭喜您修改成功", 2);
        var returnurl = data.data;
        setTimeout(function() {
            window.location.href = returnurl;
        }, 2000);
    } else {
        $("#imgage").attr("src", '/Common/captchaImage/' + Math.random());
        $.alert(data.msg);
    }
}

$(function() {
    forgotValid();
    $("#butForgot").click(function() {
        var isOK = $("#forgot_form").valid();
        if (isOK) {
            forgot();
        }
    });

    $(".butPhone").click(function() {
        var checkPhone = $("#userPhone").valid();
        var checkCode = $("#imgCode").valid();
        if (checkPhone && checkCode) {
            sendCode();
        }
    });

    document.onkeydown = function(e) {
        var ev = document.all ? window.event : e;
        if (ev.keyCode == 13) {
            var isOK = $("#forgot_form").valid();
            if (isOK) {
                forgot();
            }
        }
    };
});


