/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(function() {
    $("#hidField").val("all");
    tabClick("#litag1", 'tagContent1', 'tagContent');
});
function tabClick(obj, tab, tabs) {
    $(obj).closest('div').children("a").removeClass('btn-blue')
    $(obj).addClass('btn-blue');
    $("#" + tabs + "").children().css('display', 'none');
    $("#" + tab + "").show();
//    if ($(obj).attr("data") == "yes") {
//        return false;
//    }
    switch (tab) {
        case"tagContent1":
            //未使用
            $("#hidInfoID").val("ticketInfo1");
            getTicketLiset(1, obj, 1);
            break;
        case"tagContent2":
            //已使用
            $("#hidInfoID").val("ticketInfo2");
            getTicketLiset(1, obj, 2);
            break;
        default:
            //默认--未使用
            $("#hidInfoID").val("ticketInfo1");
            getTicketLiset(1, obj, 1);
            break;
    }
}
function onclickAsuccess(obj, type) {
    $(obj).prevAll("a").removeClass('btn-blue');
    $(obj).nextAll("a").removeClass('btn-blue');
    $(obj).addClass('btn-blue');
    var status = $("#hidStatus").val();
    $("#hidField").val(type);
    getTicketLiset(1, null, status);
}

function getTicketLiset(pageIndex, obj, status) {
    $("#hidStatus").val(status);
    var fileType = $("#hidField").val();
    var infoID = $("#hidInfoID").val();
    $.ajax({
        type: "post",
        url: "/Ticket/getTicket",
        data: {"status": status, "p": pageIndex, fileType: fileType},
        dataType: "json",
        beforeSend: function(XMLHttpRequest) {
            layer.load();
        },
        success: function(data) {
            layer.hideload();
            if (data.status == 1) {
                $("#" + infoID).html(data.data.html);
                if (status == 1) {
                    initTicketOne(pageIndex, data.data.totalPage, data.data.Records);
                } else {
                    initTicketTwo(pageIndex, data.data.totalPage, data.data.Records);
                }
               // $(obj).attr("data", "yes");
            } else {
                layer.hideload();
                layer.msg(data.msg);
            }
        }
    });
}
function initTicketOne(pageNo, totalPage, totalRecords) {
    kkpager.generPageHtml({
        pno: pageNo,
        pagerid: "tab1Page",
        gopageWrapId: 'tab1Page_gopage_wrap',
        gopageButtonId: 'tab1Page_btn_go',
        gopageTextboxId: 'tab1Page_btn_go_input',
        //总页码
        total: totalPage,
        //总数据条数
        totalRecords: totalRecords,
        getLink: function(n) {
            return this.hrefFormer + this.hrefLatter + "?pno=" + n;
        },
        mode: 'click', //默认值是link，可选link或者click
        click: function(n) {
            $("#hidInfoID").val("ticketInfo1");
            getTicketLiset(n, null, 1);
            this.selectPage(n);
            return false;
        }
    });
}

function initTicketTwo(pageNo, totalPage, totalRecords) {
    kkpager.generPageHtml({
        pno: pageNo,
        pagerid: "tab2Page",
        gopageWrapId: 'tab1Page_gopage_wrap',
        gopageButtonId: 'tab1Page_btn_go',
        gopageTextboxId: 'tab1Page_btn_go_input',
        //总页码
        total: totalPage,
        //总数据条数
        totalRecords: totalRecords,
        getLink: function(n) {
            return this.hrefFormer + this.hrefLatter + "?pno=" + n;
        },
        mode: 'click', //默认值是link，可选link或者click
        click: function(n) {
            $("#hidInfoID").val("ticketInfo2");
            getTicketLiset(n, null, 2);
            this.selectPage(n);
            return false;
        }
    });
}
/**************加息券--已使用 end**************************/



    