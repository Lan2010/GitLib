/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(function () {
    getInviteMoreList();
    
    window.scrollTo(0, 0); //每次F5刷新把滚动条置顶
    //marginBottom表示滚动条离底部的距离，0表示滚动到最底部才加载，可以根据需要修改
    new NeuF.ScrollPage(window, {delay: 1000, marginBottom: 0}, function (offset) {
        if (offset > 0) {
            $("#wxLoad").show(); //加载提示
            $("#wxNo").hide();
            $("#wxMore").hide();
            setTimeout(function () {
                //邀请记录列
                if($("[aria-controls]").eq(1).attr("aria-expanded")){
                    getInviteMoreList();
                }
            }, 1000);
        }
    });
});

function getInviteMoreList() {
    var pageIndex = $("#pageIndex").val();
    var date = {'p': pageIndex};
    $.ajax({
        async: true,
        url: "/Mobile/Account/getInviteMores",
        type: 'post',
        dataType: 'json',
        data: date,
        success: function (json) {
            if (json.status) {
                $(".total").text(json.data.total+"人");
                viewInviteList(json.data.Rows,pageIndex);
            } else {
                $("#wxMore").hide();
                $("#wxLoad").hide();
                if (pageIndex == 1) {
                    var inviteListHtml = "<div class='nodata'>" +
                            "<div><span class='glyphicon glyphicon-piggy-bank'></span></div>" +
                            "<div class='tx_center h3'>暂无数据</div></div>";
                    $("#inviteList").html(inviteListHtml);
                }else{
                     $("#wxNo").show();
                }
            }
        } 
    });
}

function viewInviteList(jsondata,pageIndex) {
    if (!jsondata || jsondata == null) {
        $("#wxNo").show();
        $("#wxMore").hide();
        $("#wxLoad").hide();
        return;
    }
    var inviteListHtml = "";
    if (jsondata.length < 20) {
        $("#wxMore").hide();
        $("#wxLoad").hide();
        $("#wxNo").show();
    } else {
        $("#wxMore").show();
        $("#wxNo").hide();
        $("#wxLoad").hide();
    }
    for (var i = 0; i < jsondata.length; i++) {
        inviteListHtml += "<div class='col-xs-4'>"+jsondata[i].userNameFormat+"</div>" +
                           "<div class='col-xs-4'>"+jsondata[i].rewardMoneyFormat+"</div>" +
                           "<div class='col-xs-4'>"+jsondata[i].regDatetimeFormat+"</div>" ;
    }
    $("#pageIndex").val(pageIndex*1+1);
    $("#inviteList").append(inviteListHtml);
}
