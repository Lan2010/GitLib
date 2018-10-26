/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(function () {
    computevalidate();
    $("#cal-way").change(function () {
        var way = $("#cal-way").val();
        if (way == "3") {
            $("#trdays").show();
            $("#trmonths").hide();
            $("#txtlimit").val("");
            $("#txtDays").val("");
        } else {
            $("#trmonths").show();
            $("#trdays").hide();
            $("#txtlimit").val("");
            $("#txtDays").val("");
        }
    });
    $("#trdays").hide();
    $("#butEmpty").click(function () {
        $("#txtmoney").val("");
        $("#txtapr").val("");
        $("#txtlimit").val("");
        $("#txtDays").val("");
    });
    $("#butCompute").click(function () {
        var chd = $('input:radio[name="cal-way"]:checked').val();
        var money = $("#txtmoney").valid();
        var apr = $("#txtapr").valid();
        if (chd == "3") {
            var day = $("#txtDays").valid();
            if (money && apr && day) {
                startCompute();
            }
        } else {
            var limit = $("#txtlimit").valid();
            if (money && apr && limit) {
                startCompute();
            }
        }
    });
});


function computevalidate() {
    $.validator.addMethod("dateV", function (value, element) {
        if (value > 0 && value < 366) {
            return true;
        }
        return false;
    }, "融资天数错误");
    $.validator.addMethod("dateL", function (value, element) {
        if (value > 0 && value <= 36) {
            return true;
        }
        return false;
    }, "融资期限错误");
    $('#form_compute').validate({
        rules: {
            txtmoney: {
                required: true,
                unPointMoney: true
            },
            txtapr: {
                required: true,
                aprReward: true
            },
            txtlimit: {
                required: true,
                dateL: true
            },
            txtDays: {
                required: true,
                dateV: true
            }
        },
        messages: {
            txtmoney: {
                required: "请输入出借金额",
                unPointMoney: "请输入大于0的正整数"
            },
            txtapr: {
                required: "请输入往期年化",
                aprReward: "往期年化输入错误"
            },
            txtlimit: {
                required: "请输入出借期限",
                dateL: "出借期限错误"
            },
            txtDays: {
                required: "请输入天数",
                dateV: "天数错误1-365天"
            }
        }

    });
}

function startCompute() {
    var emp = new Object();
    emp.type = $('#cal-way').val();
    emp.days = $("#txtDays").val();
    emp.limit = $("#txtlimit").val();
    emp.money = $("#txtmoney").val();
    emp.apr = $("#txtapr").val();
    $.ajax({
        type: "post",
        url: "/Loan/getCompute",
        data: {'Parameter': emp},
        dataType: "json",
        beforeSend: function (XMLHttpRequest) {
            layer.load();
        },
        success: function (context, textStatus) {
            layer.hideload();
            if (context.status == 1) {
            	$("#computeArea").show();
                $("#emtotal").html(context.data.count);
                $("#computeHtml").html(context.data.html);
            } else {
                layer.msg(context.msg);
            }
        },
        complete: function (XMLHttpRequest, textStatus) {
            layer.hideload();
        },
        error: function () {
            layer.hideload();
        }
    });
}
