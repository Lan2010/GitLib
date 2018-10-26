/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(function() {
    computevalidate();
    $("#butCompute").click(function() {
        var isOK = $("#form_compute").valid();
        if (isOK) {
            startCompute();
        } else {
            $("#butCompute").addClass('bg_gray1 bord0');
        }
    });
    $("input[type='number']").on('blur',function() {
        if (!$("#txtMoney").valid() || !$("#txtlimit").valid() || !$("#txtapr").valid()) {
        } else {
            $("#butCompute").removeClass('bg_gray1 bord0');
        }
    });
});

function repayways(obj) {
    if ($(obj).val() == "3") {
        $("#dayMonth").html("天");
        $("#txtlimit").val("");
    } else {
        $("#dayMonth").html("月");
        $("#txtlimit").val("");
    }
    $("#txtlimit").val('');
    $("#txtMoney").val('');
    $("#txtapr").val('');
    $("#butCompute").addClass('bg_gray1 bord0');
}

function computevalidate() {
    $('#form_compute').validate({
        errorElement: 'label',
        focusCleanup: true,
        errorClass: 'control-error',
        focusInvalid: false,
        ignore: "",
        rules: {
            txtmoney: {
                required: true,
                unPointMoney: true
            },
            txtlimit: {
                required: true,
                //unPointMoney: true,
                digits: true,
                min: 1
            },
            txtapr: {
                required: true,
                aprReward: true
            }
        },
        messages: {
            txtmoney: {
                required: "请输入金额",
                unPointMoney: "请输入大于0的正整数"
            },
            txtlimit: {
                required: "请输入期限",
                //unPointMoney: "期限输入错误",
                digits: "期限只能输入数字",
                min: "期限最小值是大于0的整数"
            },
            txtapr: {
                required: "请输入年利率",
                unPointMoney: "年利率输入错误"
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

function startCompute() {
    var emp = new Object();
    emp.repayways = $('#txtrepayways').val();
    emp.limit = $("#txtlimit").val();
    emp.money = $("#txtMoney").val();
    emp.apr = $("#txtapr").val();
    $.ajax({
        type: "post",
        url: "/Wechat/Project/getCaculate",
        data: {'Par': emp},
        dataType: "json",
        beforeSend: function(XMLHttpRequest) {
            weui.load();
        },
        success: function(context, textStatus) {
            weui.hideload();
            if (context.status == 1) {
                $(".color_orange").html("" + context.data + "元");
                $(".color_orange1").html("• 每出借" + $("#txtMoney").val() + "元，可获得收益" + context.data + "元");
            }
            else {
                $("#butCompute").addClass('bg_gray1 bord0');
                $.alert(context.msg);
            }
        },
        complete: function(XMLHttpRequest, textStatus) {
            weui.hideload();
        },
        error: function() {
            weui.hideload();
        }
    });
}

