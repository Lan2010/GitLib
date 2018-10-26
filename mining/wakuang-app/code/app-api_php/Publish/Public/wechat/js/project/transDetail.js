/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(function() {
    $("#butTender").click(function() {
        if (!$('#checkfx').is(':checked')) {
            $.alert("请阅读并同意《风险提示函》");
            return '';
        }
        var riskStatus = $("#riskEvalStatus").val();
        riskStatus=2;
        if (riskStatus * 1 == 1) {
            $.alert("出借前需风险能力评估，前往评估？", function() {
                window.location.href = "/Wechat/User/riskAssess";
            });
            return false;
        }
        $("#form_trans_tender").submit();
    });
});
 