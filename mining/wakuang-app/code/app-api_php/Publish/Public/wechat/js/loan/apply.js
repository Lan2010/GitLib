/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(function() {

    $.validator.addMethod("cardID", function(value, element) {
        var regexp = /(^([0-9]{17}[0-9X]{1})$)/;
        return   this.optional(element) || (regexp.test(value));
    }, "身份证错误");
    $.validator.addMethod("minMoney", function(value, element) {
        if (value * 1 < 10000) {
            return false;
        }
        return true;
    }, "融资金额至少10000元");
    $('#apply_form').validate({
        errorElement: 'label',
        focusCleanup: true,
        errorClass: 'login-error',
        focusInvalid: false,
        ignore: "",
        rules: {
            txtphone: {
                required: true,
                phoneCode: true
            },
            txtRealName: {
                required: true,
            },
            txtcardID: {
                required: true,
                cardID: true
            },
            txtmoney: {
                required: true,
                min: 5000,
                number: true,
                max: 500000
            },
            txtlimit: {
                required: true,
                number: true,
                min: 6,
                max: 24
            },
            txtliveAddress: {
                required: true,
            }
        },
        messages: {
            txtphone: {
                required: "手机号码不能为空",
                phoneCode: "手机号码输入不正确"
            },
            txtRealName: {
                required: "请输入姓名",
            },
            txtcardID: {
                required: "请输入证件号码",
                cardID: "请输入18位的身份证号码"
            },
            txtmoney: {
                required: "请输入借款金额",
                min: "借款金额不低于5000元",
                max: "借款金额不大于50万",
                number: "必须输入数字"
            },
            txtlimit: {
                required: "请输入借款期限",
                number: "必须输入数字",
                max: "借款期限不大于24个月",
                min: "借款期限不小于6个月"
            },
            txtliveAddress: {
                required: "请输入所在地",
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
            $(error).css("left", "17px");
        },
        submitHandler: function(form) {
            form.submit();
        }
    });
    $("#butApply").click(function() {
        var isOK = $("#apply_form").valid();
        if (isOK) {
            addApply();
        }
    });

});

function addApply() {
    var emp = new Object();
    emp.txtphone = $("#txtphone").val();
    emp.txtRealName = $("#txtRealName").val();
    emp.txtcardID = $("#txtcardID").val();
    emp.txtcompany = $("#txtcompany").val();
    emp.txtmoney = $("#txtmoney").val();
    emp.vip = $("#vip").val();
    emp.txtlimit = $("#txtlimit").val();
    emp.txtliveAddress = $("#txtliveAddress").val();
    $.ajax({
        type: "post",
        url: "/Wechat/Loan/applyAjax",
        data: {'Parameter': emp},
        dataType: "json",
        beforeSend: function(XMLHttpRequest) {
            weui.load();
            $("#butApply").attr('disabled', "true");
        },
        success: function(context, textStatus) {
            weui.hideload();
            $("#butApply").removeAttr("disabled");
            if (context.status == 1) {
                $.alert({title: "提示", text: context.msg, onOK: function() {
                        window.location.reload();
                    }});
            } else if (context.status == 2) {
                $.alert({title: "提示", text: context.msg, onOK: function() {
                        window.location.href = '/Wechat/User/login/returnurl/L1dlY2hhdC9DcHMvcHVibGljaXR5';
                    }});
            } else {
                $.alert(context.msg);
            }
        },
        complete: function(XMLHttpRequest, textStatus) {
            weui.hideload();
            $("#butApply").removeAttr("disabled");
        },
        error: function() {
            $("#butApply").removeAttr("disabled");
            weui.hideload();
        }
    });
}



