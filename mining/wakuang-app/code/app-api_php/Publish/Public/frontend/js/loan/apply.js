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
    //取当前地址参数，给商户类型选中参数
    urlstr = window.location.href;
    index = urlstr.substr(urlstr.indexOf("=", 0) + 1)
    $("#txtmerchantsType").val(index);
})
function SaveLoanapply() {
    var emp = getapply();

    $.ajax({
        type: "post",
        url: "/Loan/saveLoanInfo",
        data: {'Parameter': emp},
        dataType: "json",
        beforeSend: function(XMLHttpRequest) {
            $("#butApply").attr('disabled', "true");
            layer.load();
        },
        success: function(data) {
            layer.hideload();
            if (data.status == 1) {
                window.location.href = "/loan/loanthree";
            } else {
                if (data.status == 0) {
                    if (data.data == 1) {
                        window.location.href = "/loan/loanthree/error/1";
                    }
                    else if (data.data == 2) {
                        window.location.href = "/loan/loanthree/error/2";
                    }
                    else {
                        layer.msg(data.msg);
                    }
                }


            }
            $("#butApply").removeAttr("disabled");
        }
    });
}

function getapply() {
    var emp = new Object();
    emp.realName = $("#txtrealName").val();
    emp.cardID = $("#txtcardID").val();
    emp.registerAddress = $("#txtregisterAddress").val();
    emp.loanLimit = $("#txtloanLimit").val();
    emp.loanMoney = $("#txtloanMoney").val();
    emp.merchants = $("#txtmerchants").val();
    emp.merchantsType = $("#txtmerchantsType").val();

    return emp;
}

function  loanapplyValidate() {
   $.validator.addMethod("cardID", function(value, element) {
        var regexp = /(^([0-9]{17}[0-9X]{1})$)/;
        return   this.optional(element) || (regexp.test(value));
    }, "身份证错误");
    $.validator.addMethod("chkName", function () {
        var myReg = /^[\u4e00-\u9fa5]+$/;
        if (!myReg.test($("#txtrealName").val())) {
            return false;
        } else {
            return true;
        }
    }, "商户名称必须为中文");
    
    $('#loanapply_form').validate({
        rules: {
            txtregisterAddress: {
                required: true,
            },
            txtloanMoney: {
                required: true,
                unPointMoney: true,
                max: 500000,
                min: 5000,
            },
            txtloanLimit: {
                required: true,
                max: 24,
                min: function (){
                    if($("#txtmerchantsType").val()==1){
                        return 6;
                    }
                    else if($("#txtmerchantsType").val()==2){
                        return 1;
                    }
                },
                unPointMoney: true
            },
            txtrealName: {
                required: true,
                chkName: true,
                rangelength: [2, 6]
            },
            txtcardID: {
                required: true,
                cardID:true
            },
            txtmerchants: {
                required: true,
                maxlength: 50
            },
            txtmerchantsType: {
                required: true,
            },
        },
        messages: {
            txtregisterAddress: {
                required: "请填写省份和城市",
            },
            txtloanMoney: {
                required: "请填写借款金额",
                unPointMoney: "请输入范围内的正整数",
                max: "最大金额不大于50万",
                min: "最小金额不要小于5000",
            },
            txtloanLimit: {
                required: '请填写借款期限',
                unPointMoney: "请输入范围内的正整数",
                max: "最大借款期限不大于24个月",
                min: function(){
                    msg = '';
                    if($("#txtmerchantsType").val()==1){
                        msg = '百商宝借款期限为6~24个月';
                    }
                    else if($("#txtmerchantsType").val()==2){
                        msg = '百车宝借款期限为1~24个月';
                    }
                    if(msg){
                        return msg;
                    }
                    return false;
                },
            },
            txtrealName: {
                required: '请填写真实姓名', 
                chkName:'真实姓名必须为中文',
                rangelength: "请输入2-6位中文姓名"
            },
            txtcardID: {
                required: '请填写身份证号码',
                cardID:'请输入18位的身份证号码',
            },
            txtmerchants: {
                required: '请填写商户名称',
                maxlength: '最大长度不大于50个字符'
            },
            txtmerchantsType: {
                required: '请填写借款类型',
            },
        },
        submitHandler: function(form) {
            form.submit();
        }
    });
}

