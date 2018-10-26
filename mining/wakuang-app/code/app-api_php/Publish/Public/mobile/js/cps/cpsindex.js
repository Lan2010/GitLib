/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(function() {
    $("#btnWithdraw").click(function() {
        $.ajax({
            type: "post",
            url: "/Mobile/Cps/withdraw",
            dataType: "json",
            beforeSend: function(XMLHttpRequest) {

                $("#btnWithdraw").attr('disabled', "true");
            },
            success: function(context, textStatus) {

                $("#btnWithdraw").removeAttr("disabled");
                if (context.status == 1) {
                    popMsg(context.msg, 2, function() {
                        url = "/Mobile/Cps/accountbook";
                        gotoPage(url);
                    });
                } else {
                    popMsg(context.msg);
                }
            },
            complete: function(XMLHttpRequest, textStatus) {

                $("#btnWithdraw").removeAttr("disabled");
            },
            error: function() {
                $("#butLogin").removeAttr("disabled");

            }
        });
    });
});



