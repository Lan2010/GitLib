/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(function() {
    $("#wxMore").show();
    $("#wxLoad").hide();
    $("#wxNo").hide();
    $("#blistRefresh").click(function() {
        var value = $("#txtWhere").val();
        var url = "/Mobile/Project/plist?type=" + value + "";
        gotoPage(url, 3, '投资');
    });
    window.scrollTo(0, 0); //每次F5刷新把滚动条置顶
    //marginBottom表示滚动条离底部的距离，0表示滚动到最底部才加载，可以根据需要修改
    new NeuF.ScrollPage(window, {delay: 1000, marginBottom: 0}, function(offset) {
        if (offset > 0) {
            $("#wxLoad").show(); //加载提示
            $("#wxNo").hide();
            $("#wxMore").hide();
            setTimeout(function() {
                getBorrowMoreList();
            }, 1000);
        }
    });
});

function selectBorrowClick(obj) {
    var value = $(obj).val();
    $("#txtWhere").val(value);
    var url = "/Mobile/Project/plist?type=" + value + "";
    gotoPage(url, 3, '投资');
}

function viewBorrowList(jsondata, pageIndex) {
    if (!jsondata || jsondata == null) {
        $("#wxNo").show();
        $("#wxMore").hide();
        $("#wxLoad").hide();
        return;
    }
    var borrowListHtml = "";
    if (jsondata.length < 15) {
        $("#wxMore").hide();
        $("#wxLoad").hide();
        $("#wxNo").show();
    } else {
        $("#wxMore").show();
        $("#wxNo").hide();
        $("#wxLoad").hide();
    }

    var getSign = function(borrowType) {
        switch (borrowType) {
            case "90" :
                return '<span class="label label_xsb">新手标</span>';
            case "100" :
                return '<span class="p_icon p_y mr_5">优</span>';
            case "300" :
                return '<span class="p_icon p_c mr_5">车</span>';
            case "400" :
                return '<span class="p_icon p_z mr_5">主</span>';
            case "500" :
                return '<span class="label label_wxzx">微信专享</span>';
            case "700" :
                return '<span class="p_icon p_t mr_5">天</span>';
            default  :
                return "";
        }
    };
    for (var i = 0; i < jsondata.length; i++) {
        var limit = "" + jsondata[i].borrowLimit + "个月";
        if (jsondata[i].repaymentStyle * 1 == 3) {
            limit = "" + jsondata[i].borrowDays + "天";
        }
        var status = "soldout";
        var colorClass = "class='orange'";
        if (jsondata[i].borrowStatus * 1 == 1) {
            status = "presell";
        } else if (jsondata[i].borrowStatus * 1 > 1 && jsondata[i].borrowStatus * 1 < 4) {
            status = "selling";
        } else {
            colorClass = "";
        }
        borrowListHtml += "<div class='container list mt_10 " + status + "'>" +
                "  <a class='app-pop-page' href='/Mobile/Project/detail/borrowNO/" + jsondata[i].borrowNO + "'> <div class='hd'>" + getSign(jsondata[i].borrowType) + jsondata[i].borrowTitle + "</div>" +
                " <div class='fl pl_10'>年利率<br /><h2 " + colorClass + ">" + jsondata[i].borrowApr + "%</h2></div>" +
                " <div class='fl pl_10'>期限<br /><h2 " + colorClass + ">" + limit + "</h2></div>" +
                " <div class='fl'>剩余可投<br /><h2 " + colorClass + ">￥" + jsondata[i].balance + "</h2></div></div>";
    }
    $("pageIndex").val(pageIndex * 1 + 1);
    $("#borrowList").append(borrowListHtml);
}

function getBorrowMoreList() {
    var pageIndex = $("#pageIndex").val();
    var type = $("#txtWhere").val();
    var date = {'p': pageIndex, 'type': type};
    $.ajax({
        async: true,
        url: "/Mobile/Project/getBorrowMores",
        type: 'post',
        dataType: 'json',
        data: date,
        success: function(json) {
            if (json.status) {
                viewBorrowList(json.data.Rows, pageIndex);
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

















