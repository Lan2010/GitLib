$(document).ready(function() {
    $("#wxAnnouncementList a").each(function() {
        this.href = "";
        this.target = "";
    });
});


function selectBorrowClick() {

    var mohthWhere = $("#mohthWhere").val();
    var typeWhere = $("#typeWhere").val();
    $("#txtType").val(typeWhere);
    $("#txtMonth").val(mohthWhere);

    url = "/Mobile/Account/recordinvest/?type=" + typeWhere + "&month=" + mohthWhere;
    gotoPage(url, 3, '投资记录');
}


function getAnnouncementList(jsondataFromat) {
    jsondata = jsondataFromat.rows;
    page = jsondataFromat.pageIndex;
    if (!jsondata || jsondata.length == 0) {
        $("#wxNo").show();
        $("#wxMore").hide();
        $("#wxLoad").hide();
        return;
    }
    var announcementContent = "";
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
        announcementContent = announcementContent + '<li  time="' + jsondata[i].addtime + '" >' +
                ' <a class="app-pop-page" href="/Mobile/Account/recordinvest_details?tenderID=' + jsondata[i].tenderID + '"> <div class="fl">' +
                ' <div class="hd">' + jsondata[i].borrowTitle + '</div>';
        if (jsondata[i].tenderStatus == 0) {
            announcementContent = announcementContent + '<div class="green">投资中</div>';
        } else if (jsondata[i].tenderStatus == 1) {
            announcementContent = announcementContent + '<div class="red">投资成功</div>';
        } else
        {
            announcementContent = announcementContent + '<div class="color_9">投资失败</div>';
        }
        announcementContent = announcementContent + '</div>' +
                '<div class="fr">' +
                ' <div class="time">' + jsondata[i].addDatetime + '></div>' +
                '<div class="blue tx_right">' + jsondata[i].realTenderMoney + '</div>' +
                '</div></a></li>';
    }

    $(".list").append(announcementContent);
    $("#pageIndex").val(page);
}


function getMoreAnnouncementList(pageIndex) {
    var type = $("#txtType").val();
    var month = $("#txtMonth").val();
    var data = {"pageIndex": pageIndex, 'type': type, 'month': month};
    $.ajax({
        async: true,
        url: "/Mobile/Account/getMoreTender",
        type: 'post',
        dataType: 'json',
        data: data,
        success: function(json) {
            if (json.status.status) {
                getAnnouncementList(json.status.data);
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















