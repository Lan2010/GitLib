/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(function () {
    registerValidate();
    $("#butPhone").click(function () {
        sendCode();
    });
    $("#butRegister").click(function () {
        var isOK = $("#register_form").valid();
        if (isOK) {
            SaveRegister();
        }
    });
})
function registerValidate() {
    $('#register_form').validate({
        errorElement: 'label',
        errorClass: 'wx-error',
        focusCleanup: true,
        focusInvalid: false,
        ignore: "",
        rules: {
            txtPassword: {
                required: true,
                minlength: 6,
                maxlength: 16
            },
            txtphoneCode: {
                required: true,
                maxlength: 6,
                minlength: 6
            }
        },
        messages: {
            txtPassword: {
                required: "密码不能为空",
                minlength: "密码不能小于6个字符",
                maxlength: "密码不能大于16个字符"
            },
            txtphoneCode: {
                required: "请输入短信验证码",
                maxlength: "验证码为6位数字",
                minlength: "验证码为6位数字"
            }
        },
        invalidHandler: function (event, validator) {
        },
        highlight: function (element, errorClass, validClass) {
            $(element).addClass('has-error');
        },
        success: function (label) {
            label.removeClass('has-error');
            label.remove();
        },
        errorPlacement: function (error, element) {
            error.insertAfter(element);
        },
        submitHandler: function (form) {
            form.submit();
        }
    });
}
function SaveRegister() {
    var par = GetData();
    $.ajax({
        type: "post",
        url: "/Mobile/Activity/addRedRegister",
        data: {'Parameter': par},
        dataType: "json",
        beforeSend: function (XMLHttpRequest) {
            $("#butRegister").attr('disabled', "true");
            
        },
        success: function (context, textStatus) {
            
            $("#butRegister").removeAttr("disabled");
            if (context.status == 1) {
                url = "/Mobile/User/regSuccess";
                 gotoPage(url, 1, '注册成功');
            } else {
                popMsg(context.msg);
            }
        },
        complete: function (XMLHttpRequest, textStatus) {
            $("#butRegister").removeAttr("disabled");

        },
        error: function () {
            $("#butRegister").removeAttr("disabled");
  
        }
    });
}
function GetData()
{
    var emp = new Object();
    emp.userName = $("#hidPhone").val();
    emp.password = encrypt("txtPassword", $("#txtphoneCode").val());
    emp.phone = $("#hidPhone").val();
    emp.openID = $("#hidOpenID").val();
    emp.phoneCode = $("#txtphoneCode").val();
    emp.inviteCode = $("#hidInviteCode").val();
    emp.redNO = $("#hidRedNO").val();
    emp.serialNo = $("#hidSerialNo").val();
    emp.agreement = 1;
    return emp;
}

function sendCode() {
    var phone = $("#hidPhone").val();
    $.ajax({
        type: "post",
        url: "/Mobile/User/getPhoneCode",
        data: {'username': phone, 'phone': phone},
        dataType: "json",
        beforeSend: function (XMLHttpRequest) {
        },
        success: function (data, textStatus) {
            if (data.status == 1) {
                jstimer(120);
            } else {
                popMsg(data.msg);
            }
        },
        complete: function (XMLHttpRequest, textStatus) {
        },
        error: function () {
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
