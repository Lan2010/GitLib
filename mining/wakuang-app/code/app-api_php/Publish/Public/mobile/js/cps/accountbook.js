/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function() {
    tabClick("income",1);
});

function tabClick(tab, pageIndex) {

    switch (tab) {
        case "income":
            //入账
            getIncomeList(pageIndex);
            break;
        case "outcome":
            //出账
            getOutcomeLiset(pageIndex);
            break;
        default:
            //入账
            getIncomeList(pageIndex);
            break;
    }
}
//出账
function getOutcomeLiset(pageIndex) {

    $.ajax({
        type: "post",
        url: "/Mobile/Cps/getWithdrawRecord",
        data: {'p': pageIndex},
        dataType: "json",
        beforeSend: function(XMLHttpRequest) {
           
        },
        success: function(data) {
          
            if (data.status == 1) {
                viewOutcomeOneHtml(data.data.Rows,pageIndex);
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

function viewOutcomeOneHtml(jsondata,pageIndex) {

    if (!jsondata == null || jsondata == null) {
        $("#wxNo").show();
        $("#wxMore").hide();
        $("#wxLoad").hide();
        return false;
    }
    if (jsondata.length < 15) {
        $("#wxMore").hide();
        $("#wxLoad").hide();
        $("#wxNo").show();
    } else {
        $("#wxMore").show();
        $("#wxNo").hide();
        $("#wxLoad").hide();
    }
    var announcementContent = '';

    for (var i = 0; i < jsondata.length; i++) {
        var cls = '';
        if (jsondata[i].verifyStatus == 1) //现金
        {
            cls = '';
        } else if (jsondata[i].verifyStatus == 2) {
            cls = ' text-success  ';
        } else {
            cls = ' text-danger ';
        }

        announcementContent += '<a class="weui_cell" href="javascript:void(0);"><div class="weui_cell_bd weui_cell_primary">';
        announcementContent += '<p class="col-xs-4">' + jsondata[i].createdDate + '</p>';
        announcementContent += '<p class="col-xs-5">' + jsondata[i].amount + '</p>';
        announcementContent += '<p class="col-xs-3 ' + cls + '">' + jsondata[i].verifyStatusText + '</p></div></a>';
//      announcementContent += '<div class="weui_cell_ft"></div>';

    }

 
    if (announcementContent == '' && pageIndex == 1){
        announcementContent = ' <div class="nodata"> <div><span class="glyphicon glyphicon-piggy-bank"></span></div> <div class="tx_center h3">暂无数据</div> </div>';
    }else if(announcementContent != '' && pageIndex == 1){
        $("#outcomeHtml").html(null);
    }
    $("#pageIndex2").val(pageIndex*1+1);
    $("#outcomeHtml").append(announcementContent);
}

//入账
function getIncomeList(pageIndex) {

    $.ajax({
        type: "post",
        url: "/Mobile/Cps/getIncomeRecord",
        data: {'p': pageIndex},
        dataType: "json",
        beforeSend: function(XMLHttpRequest) {
            
        },
        success: function(data) {
           
            if (data.status == 1) {
                viewIncomeOneHtml(data.data.Rows,pageIndex);
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

function viewIncomeOneHtml(jsondata,pageIndex) {

    if (!jsondata || jsondata == null) {
        $("#wxNo").show();
        $("#wxMore").hide();
        $("#wxLoad").hide();
        return;
    }
    if (jsondata.length < 15) {
        $("#wxMore").hide();
        $("#wxLoad").hide();
        $("#wxNo").show();
    } else {
        $("#wxMore").show();
        $("#wxNo").hide();
        $("#wxLoad").hide();
    }
    var announcementContent = '';

    for (var i = 0; i < jsondata.length; i++) {
        var cls = '';
        if (jsondata[i].verifyStatus == 1) //现金
        {
            cls = '';
        } else if (jsondata[i].verifyStatus == 2) {
            cls = ' text-success  ';
        } else {
            cls = ' text-danger ';
        }

        announcementContent += ' <a class="weui_cell" href="javascript:void(0);"> <div class="weui_cell_bd weui_cell_primary">';
        announcementContent += '<p class="col-xs-4">' + jsondata[i].date + '</p>';
        announcementContent += '<p class="col-xs-4">' + jsondata[i].collAmount + '</p>';
        announcementContent += '<p class="col-xs-4 ' + cls + '">' + jsondata[i].amount + '</p></div></a>';
//      announcementContent += '<div class="weui_cell_ft"></div>';

    }
 
  
    if (announcementContent == '' &&  pageIndex == 1){
        announcementContent = ' <div class="nodata"> <div><span class="glyphicon glyphicon-piggy-bank"></span></div> <div class="tx_center h3">暂无数据</div> </div>';
    }else if(announcementContent != '' &&  pageIndex == 1){
        $("#incomeHtml").html(null);
    }

    $("#pageIndex1").val(pageIndex*1+1);
    $("#incomeHtml").append(announcementContent);
}