/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(function() {
    regValidate();
    $("#btnReceive").click(function() {
        var isOK = $("#reg_form").valid();
        if (isOK) {
            SaveRegi();
        }
    });
});
function regValidate() {
    $('#reg_form').validate({
        errorElement: 'label',
        errorClass: 'control-error',
        focusCleanup: true,
        focusInvalid: false,
        ignore: "",
        rules: {
            newPhone: {
                remote: {
                    url: "/Wechat/User/verifyPhone", //后台处理程序
                    type: "get", //数据发送方式
                    dataType: "json", //接受数据格式
                    data: {//要传递的数据
                        vPhone: function() {
                            return $("#newPhone").val();
                        }
                    }},
                required: true,
                phoneCode: true
            }
        },
        messages: {
            newPhone: {
                remote: "手机号码格式不正确或者已注册",
                required: "手机号码不能为空",
                phoneCode: "手机号码输入不正确",
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
            $(error).css("width", element[0].clientWidth - 160);
            $(error).css("margin-top", "20px");
        },
        submitHandler: function(form) {
            form.submit();
        }
    });
}
function SaveRegi() {
    var phone = $("#newPhone").val();
    $.ajax({
        type: "post",
        url: "/Wechat/User/checkUserReg",
        data: {'phone': phone},
        dataType: "json",
        beforeSend: function(XMLHttpRequest) {
            $("#btnReceive").attr('disabled', "true");
            weui.load();
        },
        success: function(context, textStatus) {
            weui.hideload();
            $("#btnReceive").removeAttr("disabled");
            if (context.status == 1) {
                var url = "/Wechat/User/register/Urlphone/" + $("#newPhone").val();
                if ($("#clientSource").val() != "") {
                    url += "/origin/" + $("#clientSource").val();
                }
                if ($("#inviteCode").val() != "") {
                    url += "/inviteCode/" + $("#inviteCode").val();
                }
                window.location.href = url;
            } else {
                $.alert(context.msg);
            }

        },
        complete: function() {
            $("#btnReceive").removeAttr("disabled");
            weui.hideload();
        },
        error: function() {
            $("#btnReceive").removeAttr("disabled");
            weui.hideload();
        }
    });
}
