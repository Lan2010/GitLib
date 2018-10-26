/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {
    tabClick("intergral1");
    
    window.scrollTo(0, 0); //每次F5刷新把滚动条置顶
        //marginBottom表示滚动条离底部的距离，0表示滚动到最底部才加载，可以根据需要修改
        new NeuF.ScrollPage(window, {delay: 1000, marginBottom: 0}, function (offset) {
            if (offset > 0) {
                $("#wxLoad").show(); //加载提示
                $("#wxNo").hide();
                $("#wxMore").hide();
                setTimeout(function () {
                    name = $(".active").attr('data');
                    var pageIndex = $("#pageIndex").val();
                    tabClick(name, pageIndex);
                }, 1000);
            }
    });
});
function tabClick(tab) {
    $("#wxNo").hide();
    $("#wxMore").hide();
    $("#wxLoad").hide();
    switch (tab) {
        case"intergral1":
            //获取记录
            getExperienceGrantList($("#pageIndex1").val());
            break;
        case"intergral2":  //获取记录
            getExperienceUseList($("#pageIndex2").val());
            break;
        default:
            //默认--未使用
            //  getExchangeList(pageIndex);
            break;
    }
}
//体验金使用记录
function getExperienceUseList(pageIndex) {
    $.ajax({
        type: "post",
        url: "/Mobile/Experience/getUseExperienceList",
        data: {'p': pageIndex},
        dataType: "json",
        beforeSend: function (XMLHttpRequest) {
            
        },
        success: function (data) {
            
            if (data.status == 1) {
                viewExperienceUseHtml(data.data.Rows,pageIndex);
            } else {
                popMsg(data.msg);
            }
        },
        complete: function (XMLHttpRequest, textStatus) {
           
        },
        error: function () {
            
        }
    });
}
//体验金使用记录
function viewExperienceUseHtml(jsondata,pageIndex) {
    var announcementContent = '';
    if(!jsondata || jsondata.length == 0){
        $("#wxNo").show();
        $("#wxMore").hide();
        $("#wxLoad").hide();
        return false;
    }
    for (var i = 0; i < jsondata.length; i++) {
        announcementContent += '<div class="row">';
        announcementContent += ' <div class="fl tx_center red"><p><b>-' + jsondata[i].tenderMoney + '</b></br></p></div>';
        announcementContent += '<div class="fr"><p>' + jsondata[i].addDatetime + '</br><a class="app-pop-page" href="/Mobile/Project/detail/borrowNO/'+jsondata[i].borrowNO+'">投标支出体验金</a> 获得收益:'+jsondata[i].useIncome+'</p>';
        announcementContent += "</div> </div>";
    }
    var notdata = $("#useexp>div").length;
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
        if (jsondata.length < 15) {
            $("#wxMore").hide();
            $("#wxLoad").hide();
            $("#wxNo").show();
        } else {
            $("#wxMore").show();
            $("#wxNo").hide();
            $("#wxLoad").hide();
        }
    }
    $("#pageIndex2").val(pageIndex*1+1);
    $("#useexp").append(announcementContent);
    
}


//=============================================================================>
//获取列表
function getExperienceGrantList(pageIndex) {
    $.ajax({
        type: "post",
        url: "/Mobile/Experience/getExperienceList",
        data: {'p': pageIndex},
        dataType: "json",
        beforeSend: function (XMLHttpRequest) {
            
        },
        success: function (data) {
           
            if (data.status == 1) {
                viewExperienceGrantHtml(data.data.Rows,pageIndex)
            } else {
                popMsg(data.msg);
            }
        },
        complete: function (XMLHttpRequest, textStatus) {
           
        },
        error: function () {
           
        }
    });
}
//体验金获取记录
function viewExperienceGrantHtml(jsondata,pageIndex) {
    var announcementContent = '';
    if(!jsondata || jsondata.length == 0){
        $("#wxNo").show();
        $("#wxMore").hide();
        $("#wxLoad").hide();
        return false;
    }
    for (var i = 0; i < jsondata.length; i++) {
        announcementContent += '<div class="row">';
        announcementContent += ' <div class="fl tx_center "><p><b>+' + jsondata[i].grantAmount + '</b></br></p></div>';
        announcementContent += '<div class="fr"><p>' + jsondata[i].addDatetime + '到' + jsondata[i].endDatetime + '有效</br>' + jsondata[i].remark + '</p>';
        announcementContent += "</div> </div>";
    }
    var notdata = $("#grantdiv>div").length;
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
        if (jsondata.length < 15) {
            $("#wxMore").hide();
            $("#wxLoad").hide();
            $("#wxNo").show();
        } else {
            $("#wxMore").show();
            $("#wxNo").hide();
            $("#wxLoad").hide();
        }
    }
    $("#pageIndex1").val(pageIndex*1+1);
    $("#grantdiv").append(announcementContent);
}
 