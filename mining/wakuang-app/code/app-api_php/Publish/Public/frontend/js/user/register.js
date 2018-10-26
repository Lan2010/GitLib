/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(function() {
    regValidate();
    $('#txtPassword').focus(function() {
        $(this).keyup();
    });
    $('#txtPassword').keyup(function() {
        var __th = $(this);
        if (!__th.val()) {
            level(0, "");
            return;
        }
        if (__th.val().length < 6) {
            level(0, "");
            return;
        }
        var _r = checkPassword(__th);
        if (_r < 1) {
            level(0, "");
            return;
        }

        if (_r > 0 && _r < 2) {
            level(20, "弱");
        } else if (_r >= 2 && _r < 4) {
            level(50, "中");
        } else if (_r >= 4) {
            level(100, "高");
        }
    });
    function level(plan, name) {
        $("#safeplan").css("width", "" + plan + "%");
    }
    function checkPassword(pwdinput) {
        var maths, smalls, bigs, corps, cat, num;
        var str = $(pwdinput).val();
        var len = str.length;
        var cat = /.{16}/g;
        if (len == 0)
            return 1;
        if (len > 16) {
            $(pwdinput).val(str.match(cat)[0]);
        }
        cat = /.*[\u4e00-\u9fa5]+.*$/;
        if (cat.test(str)) {
            return -1;
        }
        cat = /\d/;
        var maths = cat.test(str);
        cat = /[a-z]/;
        var smalls = cat.test(str);
        cat = /[A-Z]/;
        var bigs = cat.test(str);
        var corps = corpses(pwdinput);
        var num = maths + smalls + bigs + corps;
        if (len < 6) {
            return 1;
        }
        if (len >= 6 && len <= 8) {
            if (num == 1)
                return 1;
            if (num == 2 || num == 3)
                return 2;
            if (num == 4)
                return 3;
        }

        if (len > 8 && len <= 11) {
            if (num == 1)
                return 2;
            if (num == 2)
                return 3;
            if (num == 3)
                return 4;
            if (num == 4)
                return 5;
        }

        if (len > 11) {
            if (num == 1)
                return 3;
            if (num == 2)
                return 4;
            if (num > 2)
                return 5;
        }
    }

    function corpses(pwdinput) {
        var cat = /./g;
        var str = $(pwdinput).val();
        var sz = str.match(cat);
        for (var i = 0; i < sz.length; i++) {
            cat = /\d/;
            maths_01 = cat.test(sz[i]);
            cat = /[a-z]/;
            smalls_01 = cat.test(sz[i]);
            cat = /[A-Z]/;
            bigs_01 = cat.test(sz[i]);
            if (!maths_01 && !smalls_01 && !bigs_01) {
                return true;
            }
        }
        return false;
    }

    $("#butPhone").click(function() {
        $("#butPhone").attr("disabled", true);
        var b = $("#txtPhone").valid();
        var c = $("#imgCode").valid();
        if (b && c) {
            sendCode();
        } else {
            $("#butPhone").removeAttr("disabled");
        }
    });
    $("#butReg").click(function() {
        var isOK = $("#reg_from").valid();
        if (isOK) {
            SaveRegister();
        }
    });
    //点击切换注册类型事件
    $(".switchRegType").click(function() {
        $('#reg_from')[0].reset();
        $("#safeplan").css("width", "" + 0 + "%");
        $this = $(this);
        if ($this.attr("data") == "list") {
            $("#regType").val("2");
        } else {
            $("#regType").val("1");
        }
    });
});
function regValidate() {
    $('#reg_from').validate({
        errorClass: "pageerror",
        rules: {
            txtPhone: {
                required: true,
                phone: true
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
                required: "请输入您的手机号码",
                phone: "手机号码输入不正确"
            },
            txtPassword: {
                required: "请输入登录密码",
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
        beforeSend: function(XMLHttpRequest) {
            $("#butReg").attr('disabled', "true");
            layer.load();
        },
        success: function(context, textStatus) {
            if (context.status == 1) {
                window.location.href = "/User/succeed";
            } else {
                layer.hideload();
                $("#butReg").removeAttr("disabled");
                layer.msg(context.msg);
            }
        },
        complete: function(XMLHttpRequest, textStatus) {
            if (typeof ($("#butRegister").attr('disabled')) != "undefined") {
                $("#butReg").removeAttr("disabled");
                layer.hideload();
            }
        },
        error: function() {
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
    emp.inviteCode = $("#inviteCode").val();
    emp.source = $("#txtsource").val();
    emp.type = $("#regType").val();
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
        beforeSend: function(XMLHttpRequest) {
        },
        success: function(data, textStatus) {
            if (data.status == 1) {
                var timeCount = 120;
                var but = $("#butPhone");
                var times = setInterval(function() {
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
        complete: function(XMLHttpRequest, textStatus) {
        },
        error: function() {
        }
    });
}
