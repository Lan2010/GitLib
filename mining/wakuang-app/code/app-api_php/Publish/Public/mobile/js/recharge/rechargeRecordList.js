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

function getAnnouncementList(jsondata,pageIndex) {
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
        announcementContent += '<li id="' + jsondata[i].rechargeID + '" >' +
                '<a href="#"> <div class="fl">' +
                '<div class="hd">' + jsondata[i].terminal + '</div>' +
                ' <div class="green">' + jsondata[i].Status + '</div></div>' +
                ' <div class="fr">' +
                '<div class="orange tx_right">' + jsondata[i].rechargeMoney + '</div>' +
                ' <div class="time">' + jsondata[i].addDatetime + '</div></div></a> </li>';
    }
    $("#pageIndex").val(pageIndex * 1 + 1);
    $(".list").append(announcementContent);
}

function getMoreAnnouncementList(pageIndex) {
    $.ajax({
        async: true,
        url: "/Mobile/Account/getMoreRechargeRecord",
        type: 'post',
        dataType: 'json',
        data: {p: pageIndex},
        success: function(json) {
            if (json.status) {
                $(".totalMoney").text("+"+json.data.totalMoney);
                getAnnouncementList(json.data.Rows,pageIndex);
            } else {
                $("#wxMore").hide();
                $("#wxLoad").hide();
                $("#wxNo").show();
            }
        },
        error: function(xhr) {
            
        }
    });
}















