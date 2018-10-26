/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(function() {
    forgotValidate();
    $("#butPhone").click(function() {
        var phone = $("#txtPhone").valid();
        var imgCode = $("#imgCode").valid();
        if (phone && imgCode) {
            sendforgotCode();
        }

    });
    $("#butforgot").click(function() {
        var isOK = $("#forgot_form").valid();
        if (isOK) {
            Saveforgot();
        }
    });
})
function forgotValidate() {
    $('#forgot_form').validate({
        errorElement: 'label',
        errorClass: 'control-error',
        focusCleanup: true,
        focusInvalid: false,
        ignore: "",
        rules: {
            txtPhone: {
                required: true,
                phoneCode: true
            },
            txtphoneCode: {
                required: true,
                maxlength: 6,
                minlength: 6
            },
            txtPassword: {
                required: true,
                minlength: 6
            },
            txtconfirmPassword: {
                required: true,
                minlength: 6,
                equalTo: "#txtPassword"
            },
            imgCode: {
                required: true,
                maxlength: 4,
                minlength: 4
            }
        },
        messages: {
            txtPhone: {
                required: "手机号码不能为空",
                phoneCode: "手机号码输入不正确"
            },
            txtphoneCode: {
                required: "请输入短信验证码",
                maxlength: "验证码为6位数字",
                minlength: "验证码为6位数字"
            },
            txtPassword: {
                required: "密码不能为空",
                minlength: "密码不能小于6个字符",
            },
            txtconfirmPassword: {
                required: "请输入确认密码",
                minlength: "确认密码不能小于6个字符",
                equalTo: "两次输入密码不一致"
            },
            imgCode: {
                required: "请输入图片验证码",
                maxlength: "验证码为4位数字",
                minlength: "验证码为4位数字",
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
            $(error).css("width", element[0].clientWidth - 5);
        },
        submitHandler: function(form) {
            form.submit();
        }
    });
}
function Saveforgot() {
    var par = GetData();
    $.ajax({
        type: "post",
        url: "/Mobile/User/forgotSave",
        data: {'Parameter': par},
        dataType: "json",
        beforeSend: function(XMLHttpRequest) {
            $("#butforgot").attr('disabled', "true");
            $(".form_login").showLoading();
        },
        success: function(data) {
            $(".form_login").hideLoading();
            $("#butforgot").removeAttr("disabled");
            if (data.status == 1) {
                url = "/Mobile/User/forgotSuccess/";
                gotoPage(url);
            } else {
                $("#errorTxt").html(data.msg);
            }
        },
        complete: function(XMLHttpRequest, textStatus) {
            $("#butforgot").removeAttr("disabled");
            
        },
        error: function() {
            $("#butforgot").removeAttr("disabled");
             
        }
    });
}
function GetData()
{
    var emp = new Object();
    emp.password = encrypt("txtPassword", $("#txtphoneCode").val());
    emp.confirmpassword = encrypt("txtconfirmPassword", $("#txtphoneCode").val());
    emp.phone = $("#txtPhone").val();
    emp.phoneCode = $("#txtphoneCode").val();
    return emp;
}

function sendforgotCode() {
    var phone = $("#txtPhone").val();
    var verifyCode = $("#imgCode").val();
    $.ajax({
        type: "post",
        url: "/Mobile/User/forgotPhoneCode",
        data: {'verifyCode': verifyCode, 'phone': phone},
        dataType: "json",
        beforeSend: function(XMLHttpRequest) {
        },
        success: function(data) {
            if (data.status == 1) {
                forgottimer(120);
            } else {
                $('#captchaImage').attr('src', '/Common/captchaImage/' + Math.random());
                popMsg(data.msg);
            }
        },
        complete: function(XMLHttpRequest, textStatus) {
        },
        error: function() {
        }
    });
}
function forgottimer(step) {
    step = step <= 120 && step >= 0 ? step : 120;
    $("#butPhone").html(step-- + " S").attr("disabled", true);
    if (step < 0) {
        $("#butPhone").removeAttr("disabled");
    }
    else {
        window.setTimeout("forgottimer(" + step + ")", 1000);
    }
}


