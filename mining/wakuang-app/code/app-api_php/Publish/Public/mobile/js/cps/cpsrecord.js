/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(function () {
    getMoreList();
    $("#wxMore").show();
    $("#wxLoad").hide();
    $("#wxNo").hide();
    window.scrollTo(0, 0); //每次F5刷新把滚动条置顶
    //marginBottom表示滚动条离底部的距离，0表示滚动到最底部才加载，可以根据需要修改
    new NeuF.ScrollPage(window, {delay: 1000, marginBottom: 0}, function (offset) {
        if (offset > 0) {
            $("#wxLoad").show(); //加载提示
            $("#wxNo").hide();
            $("#wxMore").hide();
            setTimeout(function () {
                getMoreList();
            }, 1000);
        }
    });
});

function getMoreList() {
    var pageIndex = $("#pageIndex").val();
    var date = {'p': pageIndex};
    $.ajax({
        async: true,
        url: "/Mobile/Cps/getCpsRecord",
        type: 'post',
        dataType: 'json',
        data: date,
        success: function (json) {             
            if (json.status) {
                viewList(json.data.Rows,pageIndex);
            }else{
               popMsg(json.data.msg);
            }
        },
        error: function (xhr) {
           
        }
    });
}

function viewList(jsondata,pageIndex) {
    if (jsondata[0] == null && jsondata == null) {
        $("#wxNo").show();
        $("#wxMore").hide();
        $("#wxLoad").hide();
        return;
    }
    var inviteListHtml = "";
    if (jsondata.length < 15) {
        $("#wxMore").hide();
        $("#wxLoad").hide();
        $("#wxNo").show();
    }else{
        $("#wxMore").show();
        $("#wxNo").hide();
        $("#wxLoad").hide();
    }
    for (var i = 0; i < jsondata.length; i++) {
        inviteListHtml += "<p class='col-xs-4'>" + jsondata[i].date + "</p>" +
                "<p class='col-xs-4'>" + jsondata[i].regCount + "</p>" +
                "<p class='col-xs-4'>" + jsondata[i].tenderCount + "</p>";
    }
    if(inviteListHtml=="" && pageIndex==1){
        $("#wxMore").hide();
        $("#wxNo").hide();
        $("#wxLoad").hide();
        inviteListHtml = "<div class='nodata'>" +
                    "<div><span class='glyphicon glyphicon-piggy-bank'></span></div>" +
                    "<div class='tx_center h3'>暂无数据</div></div>";
    }
    $("#pageIndex").val(pageIndex*1+1);
    $("#recordlist").append(inviteListHtml);  

}
