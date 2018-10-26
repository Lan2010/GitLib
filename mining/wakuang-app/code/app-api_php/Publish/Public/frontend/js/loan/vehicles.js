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
        url: "/Loan/saveVehicle",
        data: {'Parameter': emp},
        dataType: "json",
        beforeSend: function(XMLHttpRequest) {
            $("#butApply").attr('disabled', "true");

        },
        success: function(data) {
            if (data.status == 1) {
                window.location.href = "/loan/loanthree/";
            } else {

                 layer.msg( data.msg );
            }
            $("#butApply").removeAttr("disabled");

        }
    });
}

function getapply() {
    var emp = new Object();
    emp.brand = $("#txtbrand").val();
    emp.style = $("#txtstyle").val();
    emp.carCode = $("#txtcarCode").val();
    emp.use = $("#txtuse").val();
    emp.licensePlat = $("#txtlicensePlat").val();
    emp.price = $("#txtprice").val();
    emp.carLicense = $("#txtcarLicense").val();
    emp.getOutMoney = $("#txtgetOutMoney").val();
    emp.insurers = $("#txtinsurers").val();
    emp.policy = $("#txtpolicy").val();
    emp.getOut = $("#txtgetOut").val();
    emp.carNum = $("#carNum").val();
    emp.strongPolicy = $("#txtstrongPolicy").val();
    emp.carMiles = $("#txtcarMiles").val();




    return emp;
}

function  loanapplyValidate() {

    $('#loanapply_form').validate({
        rules: {
            txtbrand: {
                required: true,
            },
            txtstyle: {
                required: true,
            },
            txtcarCode: {
                required: true,
            },
            txtdischarge: {
                required: true,
            },
            txtuse: {
                required: true,
            },
            txtlicensePlat: {
                required: true,
            },
            txtcarLicense: {
                required: true,
            },
            txtprice: {
                required: true,
                unPointMoney: true,
            },
            txtgetOutMoney: {
                required: true,
                digits: true,
            },
            txtinsurers: {
                required: true,
            },
            txtpolicy: {
                required: true,
            },
            txtstrongPolicy: {
                required: true,
            },
            txtcarMiles: {
                required: true,
            },
        },
        messages: {
            txtbrand: {
                required: "请输入品牌"
            },
            txtstyle: {
                required: "请输入款式"
            },
            txtcarCode: {
                required: "请输入车辆识别码"
            },
            txtdischarge: {
                required: "请输入排放标准"
            },
            txtuse: {
                required: "请输入用途"
            },
            txtlicensePlat: {
                required: "请输入车牌号"
            },
            txtcarLicense: {
                required: "请输入车的上牌日期",
            },
            txtprice: {
                required: "请输入购车原价",
                unPointMoney: "请输入整数",
            },
            txtgetOutMoney: {
                required: "请输入出险金额",
                digits: "请输入整数",
            },
            txtinsurers: {
                required: "请输入保险公司"
            },
            txtpolicy: {
                required: "请输入保险单号"
            },
            txtstrongPolicy: {
                required: "请输交强险保险单号"
            },
            txtcarMiles: {
                required: "请输行车里程数"
            },
        },
        submitHandler: function(form) {
            form.submit();
        }
    });
}

