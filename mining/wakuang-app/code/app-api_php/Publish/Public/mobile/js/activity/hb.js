/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(function() {
    $(".col-xs-4").click(function() {
        var txtsubmit = $("#txtsubmit").val();
        if (txtsubmit == 1) {
            $("#txtsubmit").val(0);
            SaveHb();
        }
    });
})

function SaveHb() {
    var par = GetData();
    $.ajax({
        type: "post",
        url: "/Mobile/Activity/getRed",
        data: {'Parameter': par},
        dataType: "json",
        beforeSend: function(XMLHttpRequest) {
        },
        success: function(context, textStatus) {
            if (context.status == 1) {
                $.modal({
                    title: "恭喜您",
                    text: context.msg,
                    buttons: [
                        {text: "领取礼包", onClick: function() {
                                gotoPage(context.url);
                            }},
                    ]
                });
            } else {
 
                popMsg(context.msg, 1, function() {
                    gotoPage(context.url);
                });
            }
            $("#txtsubmit").val(1);
        },
        complete: function(XMLHttpRequest, textStatus) {
        },
        error: function() {
        }
    });
}
function GetData()
{
    var emp = new Object();
    emp.source = $("#source").val();
    emp.inviteCode = $("#inviteCode").val();
//    emp.nickName = $("#txtnickName").val();
//    emp.openID = $("#txtopenID").val();
//    emp.headImgUrl = $("#txtheadImgUrl").val();

    return emp;
}

 