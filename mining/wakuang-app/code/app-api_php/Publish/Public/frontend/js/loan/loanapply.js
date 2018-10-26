/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(function() {
    loanapplyValidate();
    $("#butApply").click(function() {
        var isOK = $("#loanapply_form").valid();
        if (isOK) {
            SaveLoanapply();
        }
    });
})
function SaveLoanapply() {
    var emp = getapply();
    $.ajax({
        type: "post",
        url: "/Loan/saveApply",
        data: {'Parameter': emp},
        dataType: "json",
        beforeSend: function(XMLHttpRequest) {
            $("#butApply").attr('disabled', "true");
            $("#butApply").val("请等待...");
            $(".checking").showLoading();
        },
        success: function(data) {
            if (data.status == 1) {
                $("#txtlinkMan").val("");
                $("#txtlinkphone").val("");
                $("#txtMoney").val("");
                $("#txtCompany").val("");
                $("#selloanLimt").val("");
                $("#txtRemark").val("");
                $("#txtCode").val("");
                $Dialog.message("已提交申请<br/>我们的工作人员会在3个工作日之内和您联系！", null, function() {
                    window.location.href = "/";
                });

            } else {
                $Dialog.warn(data.msg);
            }
            $("#butApply").removeAttr("disabled");
            $("#butApply").val("提交申请");
            $(".checking").hideLoading();
        },
        complete: function(XMLHttpRequest, textStatus) {
            $("#butApply").removeAttr("disabled");
            $("#butApply").val("提交申请");
            $(".checking").hideLoading();
        },
        error: function() {
            $("#butApply").removeAttr("disabled");
            $("#butApply").val("提交申请");
            $(".checking").hideLoading();
        }
    });
}

function getapply() {
    var emp = new Object();
    emp.man = $("#txtlinkMan").val();
    emp.phone = $("#txtlinkphone").val();
    emp.money = $("#txtMoney").val();
    emp.company = $("#txtCompany").val();
    emp.loanLimt = $("#selloanLimt").val();
    emp.remark = $("#txtRemark").val();
    emp.code = $("#imgCode").val();
    return emp;
}

function  loanapplyValidate() {
    $('#loanapply_form').validate({
        errorElement: 'label',
        focusCleanup: true,
        errorClass: 'control-error',
        focusInvalid: false,
        ignore: "",
        rules: {
            txtlinkMan: {
                required: true,
                maxlength: 10
            },
            txtlinkphone: {
                required: true,
                phoneCode: true
            },
            txtMoney: {
                required: true,
                number: true
            },
            imgCode: {
                required: true,
                minlength: 4,
                maxlength: 4
            }
        },
        messages: {
            txtlinkMan: {
                required: "请输入联系人",
                maxlength: "联系人输入错误"
            },
            txtlinkphone: {
                required: "请输入联系电话",
                phoneCode: "电话号码输入错误"
            },
            txtMoney: {
                required: "请输入意向融资金额",
                number: "意向金额输入错误"
            },
            imgCode: {
                required: "请输入验证码",
                minlength: "验证码输入错误",
                maxlength: "验证码输入错误"
            }

        },
        invalidHandler: function(event, validator) {
        },
        highlight: function(element, errorClass, validClass) {
            $(element).closest('.inputOut').addClass('has-error');
        },
        success: function(label) {
            label.closest('.inputOut').removeClass('has-error');
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

