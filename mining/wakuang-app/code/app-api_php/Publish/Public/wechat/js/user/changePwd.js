/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function editPwdValid() {
    $("#editPwd_form").validate({
        rules: {
            oldPassword: {required: true, minlength: 6},
            newPassword: {required: true, minlength: 6},
            confirmPassword: {required: true, minlength: 6, equalTo: "#newPassword"},
            imgCode: {required: true, maxlength: 4, minlength: 4}
        },
        messages: {
            oldPassword: {required: "请输入旧密码", minlength: "密码不能小于6个字符"},
            newPassword: {required: "请输入新密码", minlength: "密码不能小于6个字符"},
            confirmPassword: {required: "请输入确认密码", minlength: "密码不能小于6个字符", equalTo: "两次输入密码不一致"},
            imgCode: {required: "请输入图片验证码", maxlength: "验证码为四位字符", minlength: "验证码为四位字符"}
        }
    });
}
function editPwd() {
    var obj = {};
    obj.imgCode = $("#imgCode").val();
    obj.oldPassword = encrypt("oldPassword", $("#imgCode").val());
    obj.newPassword = encrypt("newPassword", $("#imgCode").val());
    obj.confirmPassword = encrypt("confirmPassword", $("#imgCode").val());
    obj.returnUrl = $("#returnUrl").val();
    $("#butReg").attr("disabled", true);
    weui.ajax("/Wechat/User/savePassword", obj, function(data) {
        successAjax(data);
    });
}
//回调函数处理回调回来的数据
function successAjax(data) {
    $("#butChangePwd").attr("disabled", false);
    if (data.status == 1) {
        weui.message("恭喜您修改成功", 2);
        var returnurl = data.data;
        setTimeout(function() {
            window.location.href = returnurl;
        }, 2000);
    } else {
        $("#captchaImage").attr("src", '/Common/captchaImage/' + Math.random());
        $.alert(data.msg);
    }
}

$(function() {
    editPwdValid();
    $("#butChangePwd").click(function() {
        var isOK = $("#editPwd_form").valid();
        if (isOK) {
            editPwd();
        }
    });

    document.onkeydown = function(e) {
        var ev = document.all ? window.event : e;
        if (ev.keyCode == 13) {
            var isOK = $("#editPwd_form").valid();
            if (isOK) {
                editPwd();
            }
        }
    };
});