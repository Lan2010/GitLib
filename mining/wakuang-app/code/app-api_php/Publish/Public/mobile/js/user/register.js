/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(function() {
    registerValidate();
    $("#butPhone").click(function() {
        if ($("#txtPhone").valid() && $("#imgCode").valid()) {
            sendCode();
        }
    });
    $("#butRegister").click(function() {
        var isOK = $("#register_form").valid();
        if (isOK) {
            SaveRegister();
        }
    });
})
function registerValidate() {
    $('#register_form').validate({
        errorElement: 'label',
        errorClass: 'control-error',
        focusCleanup: true,
        focusInvalid: false,
        ignore: "",
        rules: {
            txtPhone: {
                required: true,
                phoneCode: true,
                remote: {
                    url: "/Mobile/User/verifyPhone/", //后台处理程序
                    type: "get", //数据发送方式
                    dataType: "json", //接受数据格式
                    data: {//要传递的数据
                        vPhone: function() {
                            return $("#txtPhone").val();
                        }
                    }},
            },
            txtPassword: {
                required: true,
                minlength: 6,
                maxlength: 16
            },
            txtphoneCode: {
                required: true,
                maxlength: 6,
                minlength: 6
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
                phoneCode: "手机号码输入不正确",
                remote: "手机号码已注册"
            },
            txtPassword: {
                required: "密码不能为空",
                minlength: "密码不能小于6个字符",
                maxlength: "密码不能大于16个字符"
            },
            txtphoneCode: {
                required: "请输入短信验证码",
                maxlength: "验证码为6位数字",
                minlength: "验证码为6位数字"
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
            $(error).css("width", element[0].clientWidth - 3);
        },
        submitHandler: function(form) {
            form.submit();
        }
    });
}
function SaveRegister() {
    var par = GetData();
    $.ajax({
        type: "post",
        url: "/Mobile/User/addRegister",
        data: {'Parameter': par},
        dataType: "json",
        beforeSend: function(XMLHttpRequest) {
            $("#butRegister").attr('disabled', "true");

        },
        success: function(context, textStatus) {

            $("#butRegister").removeAttr("disabled");
            if (context.status == 1) {
                if ($("#hidbm").val() == "1") {
                    url = "/Mobile/User/bmExpPrompt";

                } else {
                    url = "/Mobile/User/regsucceed";
                }
                gotoPage(url);
            } else {
                popMsg(context.msg);
            }
        },
        complete: function(XMLHttpRequest, textStatus) {
            $("#butRegister").removeAttr("disabled");

        },
        error: function() {
            $("#butRegister").removeAttr("disabled");

        }
    });
}
function GetData()
{
    var emp = new Object();
    emp.userName = $("#txtPhone").val();
    emp.password = encrypt("txtPassword", $("#txtphoneCode").val());
    emp.phone = $("#txtPhone").val();
    emp.openID = $("#openID").val();
    emp.phoneCode = $("#txtphoneCode").val();
    emp.inviteCode = $("#txtinviteCode").val();
    emp.clientSource = $("#clientSource").val();
    emp.agreement = 0;
    if ($("#cbAgreement").prop("checked")) {
        emp.agreement = 1;
    }
    return emp;
}

function sendCode() {
    var phone = $("#txtPhone").val();
    var verifyCode = $("#imgCode").val();
    $.ajax({
        type: "post",
        url: "/Mobile/User/getPhoneCode",
        data: {'verifyCode': verifyCode, 'phone': phone},
        dataType: "json",
        beforeSend: function(XMLHttpRequest) {
        },
        success: function(data, textStatus) {
            if (data.status == 1) {
                jstimer(120);
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
function jstimer(step) {
    step = step <= 120 && step >= 0 ? step : 120;
    $("#butPhone").html(step-- + " S").attr("disabled", true);
    if (step < 0) {
        $("#butPhone").html("免费获取").removeAttr("disabled");
    }
    else {
        window.setTimeout("jstimer(" + step + ")", 1000);
    }
}
