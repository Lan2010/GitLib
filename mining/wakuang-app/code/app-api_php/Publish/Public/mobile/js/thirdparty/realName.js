/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(function () {
    autoValidate();

    $("#butRealname").click(function () {
        var isOK = $("#form_realname").valid();
        $("#form_realname").submit();
    });
});

function GetRegisterData()
{
    var emp = new Object();
    emp.realName = $("#txtName").val();
    emp.cardCode = $("#txtCardID").val();
    return emp;
}


function autoValidate() {
    $.validator.addMethod("cardID", function (value, element) {
        var regexp = /(^([0-9]{17}[0-9X]{1})$)/;
        return   this.optional(element) || (regexp.test(value));
    }, "身份证错误");

    $.validator.addMethod("chkName", function (value, element) {
        var myReg = /^[\u4e00-\u9fa5]+$/;
        if (!myReg.test($("#txtName").val())) {
            return false;
        } else {
            return true;
        }
    }, "真实名字必须为中文");
    
    $('#form_realname').validate({
        errorElement: 'label',
        focusCleanup: true,
        errorClass: 'control-error',
        focusInvalid: false,
        ignore: "",
        rules: {
            txtName: {
                required: true,
                minlength: 2,
                chkName: true,
                maxlength: 6
            },
            txtCardID: {
                required: true,
                cardID: true
            }
        },
        messages: {
            txtName: {
                maxlength: "最多6个汉字",
                required: "请输入真实姓名",
                minlength: "请输入真实姓名"
            },
            txtCardID: {
                required: "请输入证件号码",
                cardID: "请输入18位的身份证号码"
            }
        },
        invalidHandler: function () {
        },
        highlight: function (element) {
            $(element).addClass('has-error');
        },
        success: function (label) {
            label.removeClass('has-error');
            label.remove();
        },
        errorPlacement: function (error, element) {
            error.insertAfter(element);
            $(error).css("width", element[0].clientWidth - 3);
        },
        submitHandler: function (form) {
            form.submit();
        }
    });
}


