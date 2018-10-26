/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function() {
    tabClick("intergral1", "1");

});

function tabClick(tab, pageIndex) {
    $("#wxNo").hide();
    $("#wxMore").hide();
    $("#wxLoad").hide();
    switch (tab) {
        case"intergral1":
            //未使用
            getExchangeList(pageIndex);
            break;
        case"intergral2":  //获取记录
            getIntergralLiset(pageIndex);
            break;
        default:
            //默认--未使用
            //  getExchangeList(pageIndex);
            break;
    }
}
//获取积分列表
function getIntergralLiset(pageIndex) {

    $.ajax({
        type: "post",
        url: "/Mobile/Integral/getIntegralList",
        data: {'p': pageIndex},
        dataType: "json",
        beforeSend: function(XMLHttpRequest) {
             
        },
        success: function(data) {

            
            if (data.status.status == 1) {
                viewIntegralOneHtml(data.status.data)
            } else {
                popMsg(data.status.msg);
            }
        },
        complete: function(XMLHttpRequest, textStatus) {
          
        },
        error: function() {
            
        }
    });
}
//积分获取
function viewIntegralOneHtml(jsondataRow) {
    var announcementContent = '';
    var jsondata = jsondataRow.rows;
    var page = jsondataRow.pageIndex;

    for (var i = 0; i < jsondata.length; i++) {

        announcementContent += '<div class="row">';
        if (jsondata[i].changeValue > 0) //现金
        {
            announcementContent += ' <div class="fl tx_center "><p><b>+' + jsondata[i].changeValue + '</b></br></p></div>';
        } else
        {
            announcementContent += ' <div class="fl tx_center red"><p><b>' + jsondata[i].changeValue + '</b></br></p></div>';
        }
        announcementContent += '<div class="fr"><p>' + jsondata[i].addDatetime + '</br>' + jsondata[i].remark + '</p>';
        announcementContent += "</div> </div>";
    }

   var notdata = $("#gain>div").length;
    if (announcementContent == '' && notdata == 0)
    {
        announcementContent = ' <div class="nodata"> <div><span class="glyphicon glyphicon-piggy-bank"></span></div> <div class="tx_center h3">暂无数据</div> </div>';
    } else {
        if (!jsondata || jsondata.length == 0) {

            $("#wxNo").show();
            $("#wxMore").hide();
            $("#wxLoad").hide();
            return;
        }

        if (jsondata.length < 10) {
            $("#wxMore").hide();
            $("#wxLoad").hide();
            $("#wxNo").show();
        } else {
            $("#wxMore").show();
            $("#wxNo").hide();
            $("#wxLoad").hide();
        }

    }

    if (page > 2)
    {
        $("#gain").append(announcementContent);
    } else {
        $("#gain").html(announcementContent);
    }
    $("#pageIndex").val(page);

}


//=============================================================================>
//兑换列表
function getExchangeList(pageIndex) {

    $.ajax({
        type: "post",
        url: "/Mobile/Integral/getExchangeList",
        data: {'p': pageIndex},
        dataType: "json",
        beforeSend: function(XMLHttpRequest) {
             
        },
        success: function(data) {
            
            if (data.status.status == 1) {
                viewIntegralExchangeHtml(data.status.data)
            } else {
                popMsg(data.status.msg);
            }
        },
        complete: function(XMLHttpRequest, textStatus) {
           
        },
        error: function() {
            
        }
    });
}
//积分兑换
function viewIntegralExchangeHtml(jsondataRow) {
    var announcementContent = '';
    var jsondata = jsondataRow.rows;
    var page = jsondataRow.pageIndex;


    for (var i = 0; i < jsondata.length; i++) {
        announcementContent += ' <div class="row">';
        announcementContent += '<div class="fl tx_center"><p><b>' + jsondata[i].goodsName + '</b></br></p></div>';
        announcementContent += '<div class="fr">';
        announcementContent += '<p><em >换购价：' + jsondata[i].goodsIntegral + ' 积分</em> </br>' +
                ' <em>时间: ' + jsondata[i].addDatetime + '</em> </br>' +
                '<em>状态: ' + jsondata[i].orderStatusTxt + '</em> </br>';
        announcementContent += "</div> </div>";
    }

 var  notdata = $("#exchange>div").length;
    if (announcementContent == '' && notdata == 0)
    {
        announcementContent = ' <div class="nodata"> <div><span class="glyphicon glyphicon-piggy-bank"></span></div> <div class="tx_center h3">暂无数据</div> </div>';
    } else {
        if (!jsondata || jsondata.length == 0) {

            $("#wxNo").show();
            $("#wxMore").hide();
            $("#wxLoad").hide();
            return;
        }

        if (jsondata.length < 10) {
            $("#wxMore").hide();
            $("#wxLoad").hide();
            $("#wxNo").show();
        } else {
            $("#wxMore").show();
            $("#wxNo").hide();
            $("#wxLoad").hide();
        }
    }

    if (page > 2)
    {
        $("#exchange").append(announcementContent);
    } else {
        $("#exchange").html(announcementContent);
    }
    $("#pageIndex").val(page);

}
 