/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(function() {
    laborValidate();
    $("#butPhone").click(function() {
        a = $("#txtPhone").valid();
        b = $("#imgCode").valid();
        if (a && b) {
            sendCode();
        }
    });
    $("#butlabor").click(function() {
        var isOK = $("#labor_form").valid();
        if (isOK) {
            SaveLabor();
        }
    });
})
function laborValidate() {
    $('#labor_form').validate({
        errorElement: 'label',
        errorClass: 'control-error',
        focusCleanup: true,
        focusInvalid: false,
        ignore: "",
        rules: {
            txtPhone: {
                required: true,
                phoneCode: true,
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
function SaveLabor() {
    var par = GetData();
    $.ajax({
        type: "post",
        url: "/Mobile/Activity/qhb",
        data: {'Parameter': par},
        dataType: "json",
        beforeSend: function(XMLHttpRequest) {
            $("#butlabor").attr('disabled', "true");

        },
        success: function(context, textStatus) {

            $("#butlabor").removeAttr("disabled");
            if (context.status == 1) {

                gotoPage(context.url);
            } else {
                popMsg(context.msg);
            }
        },
        complete: function(XMLHttpRequest, textStatus) {
            $("#butlabor").removeAttr("disabled");

        },
        error: function() {
            $("#butlabor").removeAttr("disabled");

        }
    });
}
function GetData()
{
    var emp = new Object();
    emp.phone = $("#txtPhone").val();
    emp.phoneCode = $("#txtphoneCode").val();

    return emp;
}

function sendCode() {
    var phone = $("#txtPhone").val();
    var verifyCode = $("#imgCode").val();
    $.ajax({
        type: "post",
        url: "/Mobile/Activity/sendCode",
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
