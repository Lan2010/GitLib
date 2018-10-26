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

    url = "/Mobile/Account/recordincome/?type=" + typeWhere + "&month=" + mohthWhere;
    gotoPage(url, 3, '收款记录');
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
        announcementContent = announcementContent + '<li time="' + jsondata[i].collectiontime + '"><a class="app-pop-page" href="/Mobile/Account/recordincome_details?num=' + jsondata[i].collectionID + '"><div class="fl">' +
                '<div class="hd">' + jsondata[i].borrowTitle + '</div>';
        if (jsondata[i].collectionStatus == 1) {
            announcementContent = announcementContent + '<div class="green">回款中</div></div>';
        } else if (jsondata[i].collectionStatus == 2) {
            announcementContent = announcementContent + '<div class="red">已回款</div></div>';
        }
        if (jsondata[i].collectionStatus == 1) {
            txt = jsondata[i].collectionMoney;
            txttime = jsondata[i].collectionDatetimeFormat;
        } else if (jsondata[i].collectionStatus == 2) {
            txt = jsondata[i].realCollectMoney;
            txttime = jsondata[i].realCollectDatetime;
        }



        announcementContent = announcementContent + '<div class="fr">' +
                '<div class="time">' + txttime + '></div>' +
                '<div class="orange tx_right">+' + jsondata[i].collectionMoney + '</div></div></a> </li>';
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
        url: "/Mobile/Account/getMorefinishCollect",
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
