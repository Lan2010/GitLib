/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(function() {
    $("#btnSign").click(function() {
        startSign();
    });
});

function startSign() {
    $.ajax({
        type: "post",
        url: "/Mobile/Activity/UserSign",
        dataType: "json",
        beforeSend: function(XMLHttpRequest) {

        },
        success: function(data) {

            if (data.status == 1) {

                popMsg("您获得了 " + data.msg + " 积分！", 2, function() {
                    var url = "/Mobile/Activity/sign";
                    popPage({type: 4, url: url , title: ""});
                });

            } else {
                popMsg(data.msg);
            }
        },
        complete: function(XMLHttpRequest, textStatus) {
           
        },
        error: function() {
          
        }
    });
}