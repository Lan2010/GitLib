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
        url: "/Mobile/Account/shouTender",
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
                popMsg(data.msg, 1, function() {
                    popPage({type: 4, url: window.location.href, title: ""});
                });
            } else {
                popMsg(data.msg);
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
        url: "/Mobile/Account/shouOpenTender",
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
        url: "/Mobile/Account/shouCloseTender",
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
        errorClass: 'wx-error',
        focusInvalid: false,
        ignore: "",
        rules: {
            ratioValue: {required: true},
            moneyValue: {
                required: true,
                minMoney: true,
                number: true

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
            accountMoney: {required: true,
                number: true
            }
        },
        messages: {
            ratioValue: {required: "请选择"},
            moneyValue: {
                required: "请输入金额",
                minMoney: "不能小于100",
                number: "必须输入数字"
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
            accountMoney: {required: "请输入金额",
                number: "必须输入数字"
            }
        },
        invalidHandler: function() {
        },
        highlight: function(element) {
            $(element).closest('.inputOut').addClass('wx-error');
        },
        success: function(label) {
            label.closest('.inputOut').removeClass('wx-error');
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
    val = $('input:radio[name="enableMoney"]:checked').val();

    if (val == 'enableRatio') {

        var ratio = $("#ratioValue").valid();
        emp.Validate = ratio && emp.Validate;
        emp.enableRatio = 1;
        emp.ratioValue = $("#ratioValue").val();
        emp.enableMoney = 0;
        emp.moneyValue = '';
    }
    else if (val == 'enableMoney') {
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
    if ($("#enableDay:checked").val()) {
        emp.enableDay = 1;
    }
    else {
        emp.enableDay = 0;
    }

    if ($("#enableMonth:checked").val()) {
        emp.enableMonth = 1;
    }
    else {
        emp.enableMonth = 0;
    }
    if ($("#enableTwoMonth:checked").val()) {
        emp.enableTwoMonth = 1;
    }
    else {
        emp.enableTwoMonth = 0;
    }

    if ($("#enableThreeMonth:checked").val()) {
        emp.enableThreeMonth = 1;
    }
    else {
        emp.enableThreeMonth = 0;
    }
    if ($("#enableMoreMonth:checked").val()) {
        emp.enableMoreMonth = 1;
    }
    else {
        emp.enableMoreMonth = 0;
    }

    //有效期
    if ($("#enableDate:checked").val()) {
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
    //收益范围\

    if ($("#enableIncome:checked").val()) {
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
    if ($("#enableAcount:checked").val()) {
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
            $Dialog.warn("投资期限里至少选一项！");
            return false;
        }
    }
    return emp;
}