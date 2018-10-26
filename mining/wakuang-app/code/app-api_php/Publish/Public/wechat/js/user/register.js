/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



function register() {
    //利用封装的ajax请求
    var obj = {};
    obj.userPhone = $("#userPhone").val();
    obj.userPwd = encrypt("userPwd", $("#userPhone").val());
    obj.phoneCode = $("#phoneCode").val();
    obj.invitation = $("#invitation").val();
    obj.clientSource = $("#clientSource").val();
    obj.openID = $("#openID").val();
    obj.userNickname = $("#userNickname").val();
    obj.headimgurl = $("#headimgurl").val();
    obj.inviteCode = $("#inviteCode").val();
    if ($("#check").prop("checked")) {
        obj.agreement = 1;
    }
    $("#butReg").attr("disabled", true);
    weui.ajax("/Wechat/User/addRegister", obj, function(data) {
        successAjax(data);
    });
}



//回调函数处理回调回来的数据
function successAjax(data) {
    $("#butReg").attr("disabled", false);
    if (data.status == 1) {
        var returnurl = data.data;
        window.location.href = returnurl;
    } else {
        $("#imgage").attr("src", '/Common/captchaImage/' + Math.random());
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

function registerValid() {
    $("#register_form").validate({
        rules: {
            userPhone: {required: true, phoneCode: true},
            userPwd: {required: true, minlength: 6},
            imgCode: {required: true, maxlength: 4, minlength: 4},
            phoneCode: {required: true},
            inviteCode: {maxlength: 11, minlength: 4}
        },
        messages: {
            userPhone: {required: "请输入手机号码", phoneCode: "手机号码不正确"},
            userPwd: {required: "请输入密码", minlength: "密码不能小于6个字符"},
            imgCode: {required: "请输入图片验证码", maxlength: "验证码为四位字符", minlength: "验证码为四位字符"},
            phoneCode: {required: "请输入短信验证码"},
            inviteCode: {maxlength: '邀求码最大长为11位数字和字母', minlength: '邀求码最小长度为4位数字和字母'}
        }
    });
}



//发送手机验证码
function sendCode() {
    var url = "/Wechat/User/getPhoneCode/";
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



$(function() {
    registerValid();
    $("#butReg").click(function() {
        var isOK = $("#register_form").valid();
        if (isOK) {
            register();
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
            var isOK = $("#register_form").valid();
            if (isOK) {
                register();
            }
        }
    };
});


