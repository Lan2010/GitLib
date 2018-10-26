/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(function () {
    $("#btnGetMoreDatas").click(function () {
        getFriendMoreList();
    });
    $("#btnshare").click(function () {
        $(".cover:first").addClass("none");
        $(".cover:last").removeClass("none");
    });
    $("#sharediv").click(function () {
        $(".cover:first").removeClass("none");
        $(".cover:last").addClass("none");
    });
    getFriendMoreList();
});

function getFriendMoreList() {
    var pageIndex = $("#pageIndex").val();
    var inviteCode = $("#hidInviteCode").val();
    var date = {'p': pageIndex, 'inviteCode': inviteCode};
    $.ajax({
        url: "/Mobile/Activity/getLightingRecords",
        type: 'post',
        dataType: 'json',
        data: date,
        beforeSend: function (XMLHttpRequest) {
            $("#btnGetMoreDatas").prop('disabled', true).val("加载中......");
        },
        success: function (json) {
            if (json.status) {
                $("#pageIndex").val(json.page);
                viewFriendList(json.data);
            } else {
                $("#btnGetMoreDatas").val("未找到更多记录");
            }
        },
        error: function (xhr) {
            
        }
    });
}

function viewFriendList(jsondata) {
    var activityListHtml = "";
    for (var i = 0; i < jsondata.length; i++) {
        activityListHtml += "<li>" +
                " <div class='fl'>" + jsondata[i].userName + " 帮你点亮了一盏灯</div>" +
                "<div class='fr'><p>" + jsondata[i].addDateTime + "</p></div>" +
                "</li>";
    }
    $("#activityList").append(activityListHtml);
    if (jsondata.length < 10) {
        $("#btnGetMoreDatas").val("未找到更多记录");
    } else {
        $("#btnGetMoreDatas").prop('disabled', false).val("加载更多");
    }
}

