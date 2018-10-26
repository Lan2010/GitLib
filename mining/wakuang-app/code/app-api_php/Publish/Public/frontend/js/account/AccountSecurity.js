$(function() {

    $("#butLogin").live("click", function() {
        checkPwd();
        var isOK = $("#form_editPwd").valid();
        if (isOK) {
            savePwd()
        }
    });
});
//修改用户密码
function savePwd() {
    var emp = new Object();
    emp.originalPwd = $('#originalPwd').encrypt($("#phones").val());
    emp.newPwd = $("#newPwd").encrypt($("#phones").val());
    emp.confirmPwd = $("#confirmPwd").encrypt($("#phones").val());
    $.ajax({
        type: "post",
        url: "/Safety/changePassword",
        data: {"par": emp},
        dataType: "json",
        beforeSend: function(XMLHttpRequest) {
            layer.load();
            $("#butLogin").attr('disabled', "true");
        },
        success: function(data) {
            if (data.status == 1) {
                layer.msg(data.msg);
                window.location.href = "";
            } else {
                layer.msg(data.msg);
            }
        },
        complete: function(XMLHttpRequest, textStatus) {
            layer.hideload();
            $("#butLogin").removeAttr("disabled");
        },
        error: function() {
            layer.hideload();
            $("#butLogin").removeAttr("disabled");
        }


    });
}
//验证用户表单
function checkPwd() {

    var originalPwd = $('#originalPwd').val();
    var newPwd = $("#newPwd").val();
    $.validator.addMethod("equally", function() {
        if (originalPwd == newPwd) {
            return false
        } else {
            return true;
        }
    }, "输入的新旧密码相同!");

    $("#form_editPwd").validate({
        rules: {
            originalPwd: {required: true, minlength: 6},
            newPwd: {required: true, rangelength: [6, 16]},
            confirmPwd: {required: true, equalTo: "#newPwd"}
        },
        messages: {
            originalPwd: {required: "原登录密码不能为空", minlength: "密码不能小于6个字符"},
            newPwd: {required: "新登录密码不能为空", rangelength: "6-16位字符，包括英文字母、数字或符号"},
            confirmPwd: {required: "请输入确认密码", equalTo: "确认密码不一致"}
        }
    });
}