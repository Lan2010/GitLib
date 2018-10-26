/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function() {
    tabClick("ticker1", "0");

});

function tabClick(tab, pageIndex) {
    switch (tab) {
        case"ticker1":
            //未使用
            getTicketLiset(pageIndex, 1);
            break;
        case"ticker2":
            //已使用
            getTicketLiset(pageIndex, 2);
            break;
        default:
            //默认--未使用
            getTicketLiset(pageIndex, 1);
            break;
    }
}

function getTicketLiset(pageIndex, status) {
    $("#hidStatus").val(status);
    //   var dpage = {"pageIndex": pageIndex};
    $.ajax({
        type: "post",
        url: "/Mobile/Ticket/getTicket",
        data: {'p': pageIndex, 'status': status},
        dataType: "json",
        beforeSend: function(XMLHttpRequest) {

        },
        success: function(data) {
            $("#gray_gradients").hideLoading();
            if (data.status == 1) {
                viewTicketOneHtml(data.data, status)
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

function viewTicketOneHtml(jsondataRow, status) {
    if (jsondataRow.Rows[0] == null && jsondataRow.Rows == null) {
        $("#wxNo").show();
        $("#wxMore").hide();
        $("#wxLoad").hide();
        return;
    }
    if (jsondataRow.Rows.length < 5) {
        $("#wxMore").hide();
        $("#wxLoad").hide();
        $("#wxNo").show();
    } else {
        $("#wxMore").show();
        $("#wxNo").hide();
        $("#wxLoad").hide();
    }
    var announcementContent = '';
    var jsondata = jsondataRow.Rows;
    var page = jsondataRow.pageIndex;

    for (var i = 0; i < jsondata.length; i++) {
        //区分现金券  还是加息券
        if (jsondata[i].typeCode == 20) //现金
        {
            announcementContent += '<li class="container jxc">';
        } else if (jsondata[i].typeCode == 30) {
            announcementContent += '<li class="container dyc">';
        } else {
            announcementContent += '<li class="container moneyc">';
        }
        if (status == 1)
            announcementContent += '<a class="app-gotoPlist"  href="/Mobile/Project/plist" class="btn">使用</a>';
        if (jsondata[i].typeCode == 20) {
            announcementContent += ' <div class="pad_10"><span class="h1  color_3">' + jsondata[i].deductCash + '</span>元（' + jsondata[i].sourceName + '）</div><div class="pl_10 fs_12">' + jsondata[i].ruleInfo;
        } else if (jsondata[i].typeCode == 30) {
            announcementContent += ' <div class="pad_10"><span class="h1  color_3">' + jsondata[i].deductCash + '</span>元（' + jsondata[i].sourceName + '）</div><div class="pl_10 fs_12">' + jsondata[i].ruleInfo;
        } else {
            announcementContent += ' <div class="pad_10"><span class="h1  color_3">' + jsondata[i].addInterest + '</span>%（' + jsondata[i].sourceName + '）</div><div class="pl_10 fs_12">' + jsondata[i].ruleInfo;
        }

        if (jsondata[i].effectiveDate == 0 || jsondata[i].effectiveDate == null) {
            announcementContent += '<p class="pt_5">有效时间：<b class="orange">永久有效</b></p></div></li>';
        } else {
            announcementContent += '<p class="pt_5">有效时间：<b class="orange">' + jsondata[i].effectiveDateFormate + '-' + jsondata[i].expireDateFormate + '</b></p></div></li>';
        }


    }
    var ul = $("#ticker" + status + ">ul>li").length;
    var notdata = $("#ticker" + status + ">ul>div").length;
    if (announcementContent == '' && ul == 0 && notdata == 0)
    {
        announcementContent = ' <div class="nodata"> <div><span class="glyphicon glyphicon-piggy-bank"></span></div> <div class="tx_center h3">暂无数据</div> </div>';
    }
    if (page > 1)
    {
        $("#ticker" + status + ">ul").append(announcementContent);
    } else {
        $("#ticker" + status + ">ul").html(announcementContent);
    }
    $("#pageIndex").val(page);

}
 