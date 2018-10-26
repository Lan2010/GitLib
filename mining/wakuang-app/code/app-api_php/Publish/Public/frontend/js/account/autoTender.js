/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(function() {
    tenderValidate();
    $("#addSave").click(function() {
        var data = GetData();
        if (data.Validate == true) {
            SaveTender(data);
        }
    });
    $("#openSave").click(function() {
        var data = GetData();
        if (data.Validate == true) {
            OpenTender(data);
        }
    });
    $("#closeSave").click(function() {
        var data = GetData('close');
        if (data.Validate == true) {
            CloseTender();
        }
    });
});

function SaveTender(data) {
    var emp = data;
    $.ajax({
        type: "post",
        url: "/Invest/shouTender",
        data: {'Parameter': emp},
        dataType: "json",
        beforeSend: function(XMLHttpRequest) {
            $("body").showLoading();
//            $("#addSave").attr('disabled', "true");
//            $("#addSave").val("保存中...");
        },
        success: function(data, textStatus) {
            $("body").hideLoading();
            if (data.status == 1) {
                $Dialog.message(data.msg, null, function() {
                    window.location.reload();
                });
            } else {
                $Dialog.warn(data.msg);
            }
//            $("#addSave").attr('disabled', "true");
//            $("#addSave").val("保存成功");
        },
        complete: function(XMLHttpRequest, textStatus) {
            $("body").hideLoading();
//            $("#addSave").attr('disabled', "true");
//            $("#addSave").val("保存成功");
        },
        error: function() {
            $("body").hideLoading();
//            $("#addSave").removeAttr("disabled");
//            $("#addSave").val("保存失败");
        }
    });
}

function OpenTender(data) {
    var emp = data;
    $.ajax({
        type: "post",
        url: "/Invest/shouOpenTender",
        data: {'Parameter': emp},
        dataType: "json",
        beforeSend: function(XMLHttpRequest) {

        },
        success: function(data, textStatus) {
            if (data.status == 1) {
                $Dialog.message(data.msg, null, function() {
                    window.location.reload();
                });
            } else {
                $Dialog.warn(data.msg);
            }
        },
        complete: function(XMLHttpRequest, textStatus) {

        },
        error: function() {

        }
    });
}
function CloseTender() {
    var emp = GetData('close');
    $.ajax({
        type: "post",
        url: "/Invest/shouCloseTender",
        data: {'Parameter': emp},
        dataType: "json",
        beforeSend: function(XMLHttpRequest) {

        },
        success: function(data, textStatus) {
            if (data.status == 1) {
                $Dialog.message(data.msg, null, function() {
                    window.location.reload();
                });
            } else {
                $Dialog.warn(data.msg);
            }
        },
        complete: function(XMLHttpRequest, textStatus) {

        },
        error: function() {

        }
    });
}

function tenderValidate() {
    $.validator.addMethod("minMoney", function(value, element) {
        if (1 * value > 0) {
            if (1 * value < 100) {
                return false;
            }
        }
        return true;
    }, "最小投标额");
    $.validator.addMethod("effectivvalidateBegin", function(value, element) {
        var startTime = new Date(($("#beginTime").val()).replace(/-/g, "/"));
        var endTime = new Date(($("#endTime").val()).replace(/-/g, "/"));
        if (1 * endTime > 0) {
            if (1 * endTime < 1 * startTime) {
                return false;
            }
        }
        $("#endTime").closest('.inputOut').removeClass('has-error');
        $("#endTime").next("label").remove();
        return true;
    }, "开始日期不能大于结束日期");
    $.validator.addMethod("effectivvalidateEnd", function(value, element) {
        var startTime = new Date(($("#beginTime").val()).replace(/-/g, "/"));
        var endTime = new Date(($("#endTime").val()).replace(/-/g, "/"));
        if (1 * startTime > 0) {
            if (1 * endTime < 1 * startTime) {
                return false;
            }
        }
        $("#beginTime").closest('.inputOut').removeClass('has-error');
        $("#beginTime").next("label").remove();
        return true;
    }, "结束日期不能小于开始日期");

    $('#autoTender_form').validate({
        errorElement: 'label',
        focusCleanup: true,
        errorClass: 'recharge-error',
        focusInvalid: false,
        ignore: "",
        rules: {
            ratioValue: {required: true},
            moneyValue: {
                required: true,
                minMoney: true
            },
            incomeMin: {required: true},
            beginTime: {
                required: true,
                effectivvalidateBegin: true
            },
            endTime: {
                required: true,
                effectivvalidateEnd: true
            },
            accountMoney: {required: true}
        },
        messages: {
            ratioValue: {required: "请选择"},
            moneyValue: {
                required: "请输入金额",
                minMoney: "不能小于100"
            },
            incomeMin: {required: "请选择"},
            beginTime: {
                required: "请选择日期",
                effectivvalidateBegin: "不能大于后值"
            },
            endTime: {
                required: "请选择日期",
                effectivvalidateEnd: "不能小于前值"
            },
            accountMoney: {required: "请输入金额"}
        },
        invalidHandler: function() {
        },
        highlight: function(element) {
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
function GetData(type) {
    var emp = new Object();
    emp.Validate = true;
    //投资类型
    //if ($('input[name="percentOrCash"]:checked').val() == "ratio") {
    if ($("#enableRatio").attr("checked") == "checked") {
        var ratio = $("#ratioValue").valid();
        emp.Validate = ratio && emp.Validate;
        emp.enableRatio = 1;
        emp.ratioValue = $("#ratioValue").val();
        emp.enableMoney = 0;
        emp.moneyValue = '';
    }
    else if ($("#enableMoney").attr("checked") == "checked") {
        var money = $("#moneyValue").valid();
        emp.Validate = money && emp.Validate;
        emp.enableMoney = 1;
        emp.moneyValue = $("#moneyValue").val();
        emp.enableRatio = 0;
        emp.ratioValue = '';
    }
    else {
        emp.enableMoney = 0;
        emp.moneyValue = '';
        emp.enableRatio = 0;
        emp.ratioValue = '';
    }
    //投资期限
    if ($("#enableDay").attr("checked") == "checked") {
        emp.enableDay = 1;
    }
    else {
        emp.enableDay = 0;
    }
    if ($("#enableMonth").attr("checked") == "checked") {
        emp.enableMonth = 1;
    }
    else {
        emp.enableMonth = 0;
    }
    if ($("#enableTwoMonth").attr("checked") == "checked") {
        emp.enableTwoMonth = 1;
    }
    else {
        emp.enableTwoMonth = 0;
    }
    if ($("#enableThreeMonth").attr("checked") == "checked") {
        emp.enableThreeMonth = 1;
    }
    else {
        emp.enableThreeMonth = 0;
    }
    if ($("#enableMoreMonth").attr("checked") == "checked") {
        emp.enableMoreMonth = 1;
    }
    else {
        emp.enableMoreMonth = 0;
    }
    //有效期
    if ($("#enableDate").attr("checked") == "checked") {
        var begint = $("#beginTime").valid();
        var endt = $("#endTime").valid();
        emp.Validate = begint && emp.Validate;
        emp.Validate = endt && emp.Validate;
        emp.enableDate = 1;
        emp.effectiveStart = $("#beginTime").val();
        emp.effectiveEnd = $("#endTime").val();
    }
    else {
        emp.enableDate = 0;
        emp.effectiveStart = '';
        emp.effectiveEnd = '';
    }
    //收益范围
    if ($("#enableIncome").attr("checked") == "checked") {
        var income = $("#incomeMin").valid();
        emp.Validate = income && emp.Validate;
        emp.enableIncome = 1;
        emp.incomeMin = $("#incomeMin").val();
    }
    else {
        emp.enableIncome = 0;
        emp.incomeMin = '';
    }
    //账户资金
    if ($("#enableAcount").attr("checked") == "checked") {
        var account = $("#accountMoney").valid();
        emp.Validate = account && emp.Validate;
        emp.enableAcount = 1;
        emp.accountMoney = $("#accountMoney").val();
    }
    else {
        emp.enableAcount = 0;
        emp.accountMoney = '';
    }
    var limits = $("input:checkbox[name='enableLimit']:checked").length;
    if (limits < 1) {
        if (type != 'close') {
            $Dialog.warn("出借期限里至少选一项！");
            return false;
        }
    }
    return emp;
}