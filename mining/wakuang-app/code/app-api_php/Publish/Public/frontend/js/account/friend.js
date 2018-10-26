/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(function() {
    getInvite(1);
});

function getInvite(pageIndex, pageUrl) {
    htmlId = $("#inviteHtml").val();
    tabPageId = $("#invitePageID").val();
    inviteForm = $("#inviteForm").val();

    $.ajax({
        type: "post",
        url: inviteForm,
        data: {'p': pageIndex},
        dataType: "json",
        beforeSend: function(XMLHttpRequest) {
            layer.load();
        },
        success: function(data) {
            layer.hideload();

            if (data.status == 1) {
                $('#' + htmlId).html(data.data.html);
                initPage(pageIndex, data.data.page, data.data.total, tabPageId);
            } else {
                layer.msg(data.msg);
            }
        },
        complete: function(XMLHttpRequest, textStatus) {
            layer.hideload();
        },
        error: function() {
            layer.hideload();
        }
    });

}
function initPage(pageNo, totalPage, totalRecords, tabPageId) {
    kkpager.generPageHtml({
        pno: pageNo,
        pagerid: tabPageId,
        gopageWrapId: tabPageId + '_gopage_wrap',
        gopageButtonId: tabPageId + '_btn_go',
        gopageTextboxId: tabPageId + '_btn_go_input',
        //总页码
        total: totalPage,
        //总数据条数
        totalRecords: totalRecords,
        getLink: function(n) {
            return this.hrefFormer + this.hrefLatter + "?pno=" + n;
        },
        mode: 'click', //默认值是link，可选link或者click
        click: function(n) {
            getInvite(n);
            if (tabPageId == "tab1Page") {
                $("#invitePageNo").val(n);
            }
            this.selectPage(n);
            return false;
        }
    });
    if($("#invitePageID").val() == 'tab2Page'){
        $(".normalsize").remove();
        $("#tab2Page_gopage_wrap").remove();
        $('#tab2Page').html($('#tab2Page').html().replace('转到页',''));
    }
}
