/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(function() {
    loginValidate();
    $("#butLogin").click(function() {
        var isOK = $("#login_form").valid();
        if (isOK) {
            saveLoginpassword();
        }
    });
});
function saveLoginpassword() {
    var par = GetData();
    $.ajax({
        type: "post",
        url: "/Mobile/User/savePassword",
        data: {'Parameter': par},
        dataType: "json",
        beforeSend: function(XMLHttpRequest) {

            $("#butLogin").attr('disabled', "true");
        },
        success: function(data) {

            if (data.status == 1) {
                url = "/Mobile/User/changeSuccess";
                gotoPage(url);
            } else {
                $("#errorTxt").html(data.msg);
                $("#captchaImage").attr("src", "/Common/captchaImage/" + Math.random());
            }
            $("#butLogin").removeAttr("disabled");
        },
        complete: function(XMLHttpRequest, textStatus) {

            $("#butLogin").removeAttr("disabled");
        },
        error: function() {

            $("#butLogin").removeAttr("disabled");
        }
    });
}
function GetData()
{
    var emp = new Object();
    emp.oldPassword = encrypt("txtPassword", $("#imgCode").val());
    emp.newPassword = encrypt("txtnewPassword", $("#imgCode").val());
    emp.confirmPassword = encrypt("txtconfirmPassword", $("#imgCode").val());
    emp.code = $("#imgCode").val();
    return emp;
}
function loginValidate() {
    $('#login_form').validate({
        errorElement: 'label',
        focusCleanup: true,
        errorClass: 'control-error',
        focusInvalid: false,
        ignore: "",
        rules: {
            txtPassword: {
                required: true,
                minlength: 6
            },
            txtnewPassword: {
                required: true,
                minlength: 6,
                maxlength: 16,
            },
            txtconfirmPassword: {
                required: true,
                minlength: 6,
                maxlength: 16,
                equalTo: "#txtnewPassword"
            },
            imgCode: {
                required: true,
                maxlength: 4,
                minlength: 4
            }
        },
        messages: {
            txtPassword: {
                required: "原始密码不能为空",
                minlength: "密码不能小于6个字符"
            },
            txtnewPassword: {
                required: "密码不能为空",
                minlength: "新密码长度为6-16个字符",
                maxlength: "新密码长度为6-16个字符",
            },
            txtconfirmPassword: {
                required: "请输入确认密码",
                minlength: "新密码长度为6-16个字符",
                maxlength: "新密码长度为6-16个字符",
                equalTo: "两次输入密码不一致"
            },
            imgCode: {
                required: "请输入验证码",
                maxlength: "验证码为4位数字",
                minlength: "验证码为4位数字"
            }
        },
        invalidHandler: function(event, validator) {
        },
        highlight: function(element, errorClass, validClass) {
            $(element).addClass('has-error');
        },
        success: function(label) {
            label.removeClass('has-error');
            label.remove();
        },
        errorPlacement: function(error, element) {
            error.insertAfter(element);
            $(error).css("width", element[0].clientWidth - 3);
        },
        submitHandler: function(form) {
            form.submit();
        }
    });
}
