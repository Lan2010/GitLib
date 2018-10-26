/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(function() {
    getActivityMoreList();
    $("#wxMore").show();
    $("#wxLoad").hide();
    $("#wxNo").hide();
    window.scrollTo(0, 0); //每次F5刷新把滚动条置顶
    //marginBottom表示滚动条离底部的距离，0表示滚动到最底部才加载，可以根据需要修改
    new NeuF.ScrollPage(window, {delay: 1000, marginBottom: 0}, function(offset) {
        if (offset > 0) {
            $("#wxLoad").show(); //加载提示
            $("#wxNo").hide();
            $("#wxMore").hide();
            setTimeout(function() {
                getActivityMoreList();
            }, 1000);
        }
    });
});

function getActivityMoreList() {
    var pageIndex = $("#pageIndex").val();
    var date = {'p': pageIndex};
    $.ajax({
        async: true,
        url: "/Mobile/Activity/getActivityMores",
        type: 'post',
        dataType: 'json',
        data: date,
        success: function(json) {
            if (json.status.status) {
                $("#pageIndex").val(json.status.page);
                viewActivityList(json.status.data);
            } else {
                $("#wxMore").hide();
                $("#wxLoad").hide();
                if (pageIndex == 1) {
                    var activityListHtml = "<div class='nodata'>" +
                            "<div><span class='glyphicon glyphicon-piggy-bank'></span></div>" +
                            "<div class='tx_center h3'>暂无数据</div></div>";
                    $("#activityList").append(activityListHtml);
                } else {
                    $("#wxNo").show();
                }
            }
        } 
    });
}

function viewActivityList(jsondata) {
    var jsonInfo = jsondata["rows"];
    if (jsonInfo == null && jsondata == null) {
        $("#wxNo").show();
        $("#wxMore").hide();
        $("#wxLoad").hide();
        return;
    }
    var activityListHtml = "";
    if (jsondata.length < 5) {
        $("#wxMore").hide();
        $("#wxLoad").hide();
        $("#wxNo").show();
    } else {
        $("#wxMore").show();
        $("#wxNo").hide();
        $("#wxLoad").hide();
    }
    for (var i = 0; i < jsonInfo.length; i++) {
        activityListHtml += "<div class='project-item pt_10'>" +
                "<div class='project-item-content'>" +
                "<div class='banner radiu_4'>" +
                " <a class='app-pop-page' href='" + jsonInfo[i].cenLink + "'><div class='hd h3'><img src='" + jsonInfo[i].cenImg + "' alt=''></a>" +
                " </div>" +
                "<div class='title h3'>" + jsonInfo[i].cenName + "</div>" +
                "<p class='time'>活动时间：" + jsonInfo[i].startDate + " 至 " + jsonInfo[i].endDate + "</p>" +
                "</div>" +
                "<a class='app-pop-page' class='btn btn-info btn-lg btn-block' href='" + jsonInfo[i].cenLink + "'> 参与活动</a>" +
                "</div>";
    }
    $("#activityList").append(activityListHtml);
}

