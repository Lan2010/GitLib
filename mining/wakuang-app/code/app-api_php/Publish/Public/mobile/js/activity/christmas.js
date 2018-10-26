/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(function() {
    inputValidate();
    $("#butPhone").click(function() {
        if ($("#txtPhone").valid()) {
            sendCode();
        }
    });
    $("#butSubmit").click(function() {
        var isOK = $("#christmas_form").valid();
        if (isOK) {
            ChristmasTreeCollect();
        }
    });
});

function inputValidate() {
    $('#christmas_form').validate({
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
            $(error).css("left", 10);
        },
        submitHandler: function(form) {
            form.submit();
        }
    });
}
function ChristmasTreeCollect() {
    var par = GetData();
    $.ajax({
        type: "post",
        url: "/Mobile/Activity/christmasTreeCollect",
        data: {'Parameter': par},
        dataType: "json",
        beforeSend: function(XMLHttpRequest) {
            $("#butSubmit").attr('disabled', "true");
        },
        success: function(context, textStatus) {

            $("#butSubmit").removeAttr("disabled");
            if (context.status == 1) {
                url = "/Mobile/Activity/christmas_tree/inviteCode/" + context.invitecode;
                popMsg(data.msg, 1, function() {
                    gotoPage(url);
                })
               
            } else {
 
                 popMsg(context.msg);
            }
        },
        complete: function(XMLHttpRequest, textStatus) {
            $("#butSubmit").removeAttr("disabled");
        },
        error: function() {
            $("#butSubmit").removeAttr("disabled");
        }
    });
}
function GetData() {
    var emp = new Object();
    emp.phone = $("#txtPhone").val();
    emp.phoneCode = $("#txtPhoneCode").val();
    emp.openID = $("#openID").val();
    emp.userName = $("#hidUserName").val();
    return emp;
}

function sendCode() {
    var phone = $("#txtPhone").val();
    $.ajax({
        type: "post",
        url: "/Mobile/Activity/getPhoneCode",
        data: {'phone': phone},
        dataType: "json",
        beforeSend: function(XMLHttpRequest) {
        },
        success: function(data, textStatus) {
            if (data.status == 1) {
                jstimer(120);
            } else {
                alert(data.msg);
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
    $('#butPhone').unbind("click");
    $("#butPhone").html(step-- + " S").attr("disabled", true);
    if (step < 0) {
        $("#butPhone").html("免费获取").removeAttr("disabled");
        $("#butPhone").click(function() {
            if ($("#txtPhone").valid()) {
                sendCode();
            }
        });
    }
    else {
        window.setTimeout("jstimer(" + step + ")", 1000);
    }
}
