/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(function () {
    regValidate();

    $("#butPhone").click(function () {
        $("#butPhone").attr("disabled", true);
        var b = $("#txtPhone").valid();
        var c = $("#imgCode").valid();
        if (b && c) {
            sendCode();
        } else {
            $("#butPhone").removeAttr("disabled");
        }
    });
    $("#butReg").click(function () {
        var isOK = $("#reg_from").valid();
        if (isOK) {
            SaveRegister();
        }
    });

});
function regValidate() {
    $('#reg_from').validate({
        errorClass: "pageerror",
        rules: {
            txtPhone: {
                required: true,
                phone: true,
            },
            txtPassword: {
                required: true,
                maxlength: 16,
                minlength: 6
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
                phone: "手机号码输入不正确",
            },
            txtPassword: {
                required: "密码不能为空",
                maxlength: "密码不能大于16个字符",
                minlength: "密码不能小于6个字符"
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
        }
    });
}
function SaveRegister() {
    var par = GetData();
    if (!$("#cbAgreement").prop("checked")) {
        layer.msg("您需要同意平台协议！");
        return;
    }
    $.ajax({
        type: "post",
        url: "/User/addReg",
        data: {'Par': par},
        dataType: "json",
        beforeSend: function (XMLHttpRequest) {
            $("#butReg").attr('disabled', "true");
            layer.load();
        },
        success: function (context, textStatus) {
            if (context.status == 1) {
                window.location.href = "/";
            } else {
                layer.hideload();
                $("#butReg").removeAttr("disabled");
                layer.msg(context.msg);
            }
        },
        complete: function (XMLHttpRequest, textStatus) {
            if (typeof ($("#butRegister").attr('disabled')) != "undefined") {
                $("#butReg").removeAttr("disabled");
                layer.hideload();
            }
        },
        error: function () {
            $("#butReg").removeAttr("disabled");
            layer.hideload();
        }
    });
}
function GetData() {
    var emp = new Object();
    emp.phone = $("#txtPhone").val();
    emp.password = $('#txtPassword').encrypt($("#txtphoneCode").val());
    emp.phoneCode = $("#txtphoneCode").val();
    emp.source = $("#txtsource").val();
    return emp;
}

function sendCode() {
    var data = {};
    data.phone = $("#txtPhone").val();
    data.imgCode = $("#imgCode").val();
    $.ajax({
        type: "post",
        url: "/User/regCode",
        data: {'Par': data},
        dataType: "json",
        beforeSend: function (XMLHttpRequest) {
        },
        success: function (data, textStatus) {
            if (data.status == 1) {
                var timeCount = 120;
                var but = $("#butPhone");
                var times = setInterval(function () {
                    if (timeCount > 0) {
                        timeCount--;
                        but.val(timeCount + " S")
                    } else {
                        clearInterval(times);
                        but.val("免费获取");
                        but.removeAttr("disabled");
                    }
                }, 1000);
            } else {
                $("#butPhone").removeAttr("disabled");
                $('#txtImage').attr('src', '/Common/captchaImage/' + Math.random());
                layer.msg(data.msg);
            }

        },
        complete: function (XMLHttpRequest, textStatus) {
        },
        error: function () {
        }
    });
}
