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
    emp.money = $("#txtmoney").val();
    emp.apr = $("#txtapr").val();
    $.ajax({
        type: "post",
        url: "/Mobile/Project/getCaculate",
        data: {'Par': emp},
        dataType: "json",
        beforeSend: function(XMLHttpRequest) {
            
        },
        success: function(context, textStatus) {
            
            if (context.status.status == 1) {
                $("#resh1").html("￥" + context.status.data + "");
                $("#resp").html("• 每投资" + $("#txtmoney").val() + "元，可获得收益" + context.status.data + "元");
            }
            else {
                popMsg(context.status.msg);
            }
        },
        complete: function(XMLHttpRequest, textStatus) {
            
        },
        error: function() {
           
        }
    });
}

