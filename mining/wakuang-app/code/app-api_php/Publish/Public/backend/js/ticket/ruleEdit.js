/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(function() {
    RuleValid();
    $(".butSaveRule").click(function() {
        var isOK = $("#form-Rule").valid();
        if (isOK) {
            saveRule();
        }
    });

    $(".butReturn").click(function() {
        $Util.returnRefresh(); //返回
    });

});
// $win.warn("融资详情未输入");
function saveRule() {
    var data = {};
    var canVIPlevel = "";
    $('[name=VipStatus]:checkBox').each(function() {
        if (this.checked) {
            var value = $(this).val();
            canVIPlevel += value + ",";
        }
    });
    var canBorrowType = "";
    $('[name=borrowType]:checkBox').each(function() {
        if (this.checked) {
            var value = $(this).val();
            canBorrowType += value + ",";
        }
    });

    var canClient = "";
    $('[name=clientType]:checkBox').each(function() {
        if (this.checked) {
            var value = $(this).val();
            canClient += value + ",";
        }
    });

    var limitType = "";
    $('[name=limitType]:checkBox').each(function() {
        if (this.checked) {
            var value = $(this).val();
            limitType += value + ",";
        }
    });



    data['canVIPlevel'] = canVIPlevel;
    data['canBorrowType'] = canBorrowType;
    data['limitType'] = limitType;
    data['canClient'] = canClient;
    data['minTender'] = $("#minTender").val();
    data['maxTender'] = $("#maxTender").val();
    data['maxBorrowApr'] = $("#maxBorrowApr").val();
    data['ruleInfo'] = $("#ruleInfo").val();
    data['ruleID'] = $("#ruleID").val();
    data['ruleName'] = $("#ruleName").val();
    
    $.ajax({
        type: "post",
        url: "/Backend/Ticket/saveTicketRule",
        data: {'par': data},
        dataType: "json",
        beforeSend: function(XMLHttpRequest) {
            $(".sub-content").showLoading();
        },
        success: function(data, textStatus) {
            $(".sub-content").hideLoading();
            if (data.status == 1) {
                $win.confirm(data.msg + ",是否关闭窗口？").on(function() {
                    $('.butReturn').trigger("click");
                });
            } else {
                $win.warn(data.msg);
            }
        },
        complete: function(XMLHttpRequest, textStatus) {
            $(".sub-content").hideLoading();
        },
        error: function() {
            $(".sub-content").hideLoading();
        }
    });
}
function RuleValid() {

    $("#form-Rule").validate({
        rules: {
            ruleName: {required: true},
            minTender: {Number: true},
            maxTender: {Number: true},
            maxBorrowApr: {Apr: true}
        },
        messages: {
            ruleName: {required: "请填写券规则名称"},
            minTender: {Number: "格式不正确"},
            maxTender: {Number: "格式不正确"},
            maxBorrowApr: {Apr: "利率格式不正确"}
        }
    });
}

