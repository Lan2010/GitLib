/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(function() {

    $('#login_form').validate({
        errorElement: 'label',
        focusCleanup: true,
        errorClass: 'login-error',
        focusInvalid: false,
        ignore: "",
        rules: {
            txtName: {
                required: true,
                phoneCode: true
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
            txtName: {
                required: "请输入手机号码",
                phoneCode: "手机号码不正确"
            },
            txtPassword: {
                required: "请输入密码"
            },
            imgCode: {
                required: "请输入验证码",
                maxlength: "验证码为四位字符",
                minlength: "验证码为四位字符"
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
    $("#butLogin").click(function() {
        var isOK = $("#login_form").valid();
        if (isOK) {
            startlogin();
        }
    });
    document.onkeydown = function(e) {
        var ev = document.all ? window.event : e;
        if (ev.keyCode == 13) {
            var isOK = $("#login_form").valid();
            if (isOK) {
                startlogin();
            }
        }
    };
})
function startlogin() {
    var emp = new Object();
    emp.password = encrypt("txtPassword", $("#txtName").val());
    emp.userName = $("#txtName").val();
    emp.openID = $("#openID").val();
    emp.returnurl = $("#returnurl").val();
    emp.verifyCode = $("#imgCode").val();
    $.ajax({
        type: "post",
        url: "/Mobile/User/loginUser",
        data: {'Parameter': emp},
        dataType: "json",
        beforeSend: function(XMLHttpRequest) {

            $("#butLogin").attr('disabled', "true");
        },
        success: function(context, textStatus) {
            $("body").hideLoading();
            if (context.status == 1) {
                var returnurl = context.data;
                if (!returnurl) {
                    returnurl = '/Mobile/Account/index';
                }
                gotoPage(returnurl);
            } else {
                $("#errorTxt").html(context.msg);
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



