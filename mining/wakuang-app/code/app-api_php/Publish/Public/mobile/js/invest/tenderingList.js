$(document).ready(function() {
    $("#wxAnnouncementList a").each(function() {
        this.href = "";
        this.target = "";
    });
});
function getAnnouncementList(jsondata) {
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
        announcementContent = announcementContent +
                '<div class="hd pad_10 bord_b"  >' +
                ' <div class="fl">' + jsondata[i].borrowTitle + '</div><div class="fr">' + jsondata[i].addDatetime + '</div>' +
                '</div> <div class="container-fluid ml_10 mr_10">' +
                '<div class="row tx_center " >' +
                '<div class="col-xs-3 pt_10 pb_10"><p>年利率</p><h3>' + jsondata[i].borrowApr + '%</h3></div>' +
                '<div class="col-xs-6 bord_l bord_r pt_10 pb_10"><p>金额</p><h3>￥' + jsondata[i].realTenderMoney + '</h3></div>' +
                '<div class="col-xs-3 pt_10 pb_10"><p>期限</p>';

        if (jsondata[i].repaymentStyle == 3) {
            announcementContent = announcementContent + " <h3>" + jsondata[i].borrowDays + "天</h3>";
        } else {
            announcementContent = announcementContent + " <h3>" + jsondata[i].borrowLimit + "个月</h3>";
        }
        announcementContent = announcementContent +
                '</div></div></div> ' +
                '<div class="gray_bg bord_t bord_b ">' +
                '<div class="row tx_center " time="' + jsondata[i].addtime + '"  >' +
                '<div class="col-xs-6 pt_10 pb_10 "><p>应收利息</p><h3>￥' + jsondata[i].tenderInterest + '</h3></div>' +
                '<div class="col-xs-6 pt_10 pb_10"><p>额外利息</p><h3>￥' + jsondata[i].awardMoneyFormat + '</h3></div>' +
                '</div>  </div>';

    }

    $(".biaolist").append(announcementContent);
}


function getMoreAnnouncementList(minId) {
    var data = {"minId": minId};
    $.ajax({
        async: true,
        url: "/Mobile/Account/getMoreTendering",
        type: 'post',
        dataType: 'json',
        data: data,
        success: function(json) {
            if (json.status) {
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















