/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(function() {

    tabClick("site", 0);

});
function tabClick(type, page) {
    $("#pageIndex").val(0);
    $("#type").val(type);
    switch (type) {
        case"site":
            getSiteRecord(page);
            break;
        case"sms":
            getSMSRecord(page);
            break;
        default:
            getSiteRecord(page);
            break;
    }

}
function getSiteRecord(pageIndex) {

    $.ajax({
        type: "post",
        url: "/Mobile/Information/getSiteList",
        data: {"pageIndex": pageIndex},
        dataType: "json",
        beforeSend: function(XMLHttpRequest) {

        },
        success: function(data) {

            if (data.status == 1) {
                getAnnouncementList(data.data);
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

function getAnnouncementList(jsondataFromat) {
    jsondata = jsondataFromat.Rows;
    page = jsondataFromat.pageIndex;
    var announcementContent = "";
    for (var i = 0; i < jsondata.length; i++) {
        announcementContent = announcementContent + '<a class="app-pop-page" href="/Mobile/Information/viewnews?num=' + jsondata[i].newsID + '"><li class="container"> <div class="fl">';
        if (jsondata[i].newsStatus == 0) {
            announcementContent = announcementContent + '<span class="green pr_10">●</span>';
        } else if (jsondata[i].newsStatus == 1) {
            announcementContent = announcementContent + '<span class="color_d pr_10">●</span>';
        }
        announcementContent = announcementContent + jsondata[i].newTitle + '</div><div class="fr">' + jsondata[i].addDatetime + '</div></li></a>';
    }

    notdata = $(".white_bg>li").length;
    if (announcementContent == '' && notdata == 0)
    {
        announcementContent = '<div class="nodata"> <div><span class="glyphicon glyphicon-piggy-bank"></span></div> <div class="tx_center h3">暂无数据</div> </div>';
    } else {
        if (!jsondata || jsondata.length == 0 && page > 1) {

            $("#wxNo").show();
            $("#wxMore").hide();
            $("#wxLoad").hide();
            return;
        }

        if (jsondata.length < 10 && page > 1) {
            $("#wxMore").hide();
            $("#wxLoad").hide();
            $("#wxNo").show();
        } else {
            $("#wxMore").show();
            $("#wxNo").hide();
            $("#wxLoad").hide();
        }
    }

    if (page > 1) {
        $(".note").append(announcementContent);
    } else {
        $(".note").html(announcementContent);
    }
    $("#pageIndex").val(page);
}


function newsDelete() {
    var ids = "";
    $("input[name='cbknew']:checked").each(function(i) {
        ids += $(this).val() + ',';
    });
    if (ids.length < 1) {
        popMsg("请选择！");
    }
    $.ajax({
        type: "post",
        url: "/Information/deleteNews",
        data: {'ids': ids},
        dataType: "json",
        beforeSend: function(XMLHttpRequest) {
            $("#butDelete").attr('disabled', "true");
            $("#butDelete").val("请等待...");
        },
        success: function(data) {
            if (data.status == 1) {
                getSiteRecord(1, null);
            } else {
                popMsg(data.msg);
            }
            $("#butDelete").removeAttr("disabled");
            $("#butDelete").val("删 除");
        },
        complete: function(XMLHttpRequest, textStatus) {
            $("#butDelete").removeAttr("disabled");
            $("#butDelete").val("删 除");
        },
        error: function() {
            $("#butDelete").removeAttr("disabled");
            $("#butDelete").val("删 除");
        }
    });
}
function newsStutas(type, but) {
    var ids = "";
    $("input[name='cbknew']:checked").each(function(i) {
        ids += $(this).val() + ',';
    });
    if (ids.length < 1) {
        popMsg("请选择！");
        return;
    }
    $.ajax({
        type: "post",
        url: "/Information/operateNews",
        data: {"type": type, "ids": ids},
        dataType: "json",
        beforeSend: function(XMLHttpRequest) {
            $("#" + but + "").attr('disabled', "true");
        },
        success: function(data) {
            if (data.status == 1) {
                $("input[name='cbknew']:checked").each(function(i) {
                    if (type == "yes") {
                        $("#div" + $(this).val() + "").removeClass("aHold");
                        $("#i" + $(this).val() + "").removeClass("msg_1");
                        $("#i" + $(this).val() + "").addClass("msg_2");
                    } else {
                        $("#div" + $(this).val() + "").addClass("aHold");
                        $("#i" + $(this).val() + "").removeClass("msg_2");
                        $("#i" + $(this).val() + "").addClass("msg_1");
                    }
                });
                popMsg(data.msg);
            } else {
                popMsg(data.msg);
            }
            $("#" + but + "").removeAttr("disabled");
        },
        complete: function(XMLHttpRequest, textStatus) {
            $("#" + but + "").removeAttr("disabled");
        },
        error: function() {
            $("#" + but + "").removeAttr("disabled");
        }
    });
}

/*短信信息*/
function getSMSRecord(pageIndex) {

    $.ajax({
        type: "post",
        url: "/Mobile/Information/getSMSList",
        data: {"pageIndex": pageIndex},
        dataType: "json",
        beforeSend: function(XMLHttpRequest) {

        },
        success: function(data) {
            if (data.status == 1) {
                getsmsList(data.data);
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


function getsmsList(jsondataFromat) {
    jsondata = jsondataFromat.Rows;
    page = jsondataFromat.pageIndex;
    var announcementContent = "";

    for (var i = 0; i < jsondata.length; i++) {
        announcementContent = announcementContent + '<a class="app-pop-page" href="/Mobile/Information/sms?num=' + jsondata[i].smsID + '"><li class="container"> <div class="fl">';
        announcementContent = announcementContent + '<span class="color_d pr_10"></span>';
        announcementContent = announcementContent + jsondata[i].smsType + '</div><div class="fr">' + jsondata[i].sendDatetime + '</div></li></a>';
    }

    notdata = $(".white_bg>li").length;
    if (announcementContent == '' && notdata == 0)
    {
        announcementContent = '<div class="nodata"> <div><span class="glyphicon glyphicon-piggy-bank"></span></div> <div class="tx_center h3">暂无数据</div> </div>';
    } else {
        if (!jsondata || jsondata.length == 0 && page > 1) {

            $("#wxNo").show();
            $("#wxMore").hide();
            $("#wxLoad").hide();
            return;
        }

        if (jsondata.length < 10 && page > 1) {
            $("#wxMore").hide();
            $("#wxLoad").hide();
            $("#wxNo").show();
        } else {
            $("#wxMore").show();
            $("#wxNo").hide();
            $("#wxLoad").hide();
        }
    }

    if (page > 1) {
        $(".note").append(announcementContent);
    } else {
        $(".note").html(announcementContent);
    }
    $("#pageIndex").val(page);
}



function onclickSMSDetail(obj, type) {
//    $(obj).prevAll("a").removeClass('active');
//    $(obj).nextAll("a").removeClass('active');
//    $(obj).addClass('active');
    $(obj).addClass('active').siblings().removeClass("active");
    $("#hidSMS").val(type);
    getSMSRecord(1, null);
}