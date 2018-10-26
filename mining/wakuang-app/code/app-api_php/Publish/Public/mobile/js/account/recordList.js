$(document).ready(function() {

    getMoreAnnouncementList(1);

    window.scrollTo(0, 0); //每次F5刷新把滚动条置顶
    //marginBottom表示滚动条离底部的距离，0表示滚动到最底部才加载，可以根据需要修改
    new NeuF.ScrollPage(window, {delay: 1000, marginBottom: 0}, function(offset) {
        if (offset > 0) {
            $("#wxLoad").show(); //加载提示
            $("#wxNo").hide();
            $("#wxMore").hide();
            setTimeout(function() {
                getMoreAnnouncementList($("#pageIndex").val());
            }, 1000);
        }
    });
});
function getAnnouncementList(jsondata, pageIndex) {
    if (!jsondata || jsondata.length == 0) {
        $("#wxNo").show();
        $("#wxMore").hide();
        $("#wxLoad").hide();
        return;
    }
    var announcementContent = "";
    if (jsondata.length < 15) {
        $("#wxMore").hide();
        $("#wxLoad").hide();
        $("#wxNo").show();
    } else {
        $("#wxMore").show();
        $("#wxNo").hide();
        $("#wxLoad").hide();
    }
    for (var i = 0; i < jsondata.length; i++) {
        var url = jsondata[i].url ? jsondata[i].url : "javascript:void(0);"
        announcementContent = announcementContent + '<li class="mt_10"  id="' + jsondata[i].detailID + '" >  <a class="app-pop-page" href="' + url + '"><div class="fl">';
        announcementContent = announcementContent + '<div class="hd green">' + jsondata[i].busName + '</div>';
        announcementContent = announcementContent + ' <div class="time">' + jsondata[i].addDatetime + '</div></div><div class="fr">';
        if (jsondata[i].Income > 0) {
            announcementContent = announcementContent + '<div class="red tx_right">+' + jsondata[i].IncomeFormat + '</div>';
        } else if (jsondata[i].pay != 0) {
            announcementContent = announcementContent + '<div class="green tx_right">' + jsondata[i].payFormat + '</div>';
        } else {
            var f = "";
            var num = parseInt(jsondata[i].unavailableChange);
            if (num < 0) {
                var changeMoney = ((jsondata[i].unavailableChange).replace(/,/g, ''));
                jsondata[i].unavailableChange = Math.abs(changeMoney);
                f = '<div class="black tx_right">' + jsondata[i].unavailableChange + ".00" + '</div>';
            } else {
                f = '<div class="green tx_right">' + "-" + jsondata[i].unavailableChange + '</div>';
            }
            announcementContent = announcementContent + f;
        }
        announcementContent = announcementContent + '   <div class="time">可用余额：' + jsondata[i].availableMoney + '</div></div></a></li>';
//      announcementContent = announcementContent + '   <div class="time">' + jsondata[i].addDatetime + '</div></div></a></li>';

    }
    $("#pageIndex").val(pageIndex * 1 + 1);
    $(".list").append(announcementContent);
}

function getMoreAnnouncementList(pageIndex) {
    $.ajax({
        async: true,
        url: "/Mobile/Account/getMoreRecord",
        type: 'post',
        dataType: 'json',
        data: {p: pageIndex},
        success: function(json) {
            if (json.status) {
                getAnnouncementList(json.data.Rows, pageIndex);
            } else {
                $("#wxMore").hide();
                $("#wxLoad").hide();
                $("#wxNo").show();
            }
        }
    });
}















