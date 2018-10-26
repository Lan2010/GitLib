/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(function() {
    getLoanRecordList(1);
    $("#immaturity").click(function() {
        searchDetailValidate();
        var isValid = $(".form-inline").valid();
        if (isValid) {
            getLoanRecordList(1, null);
        }
        $(".form-inline").children("a").removeClass('btn-primary');
    });
});


function repay(obj) {
    var orderno = $(obj).attr("data");
    var repno = $(obj).attr("dataRep");
    window.open("/Account/repayAdvance/orderno/" + orderno + "/repno/" + repno);
}
function getLoanRecordList(pageIndex) {
    $.ajax({
        type: "post",
        url: "/Account/advanceAjax",
        data: {'p': pageIndex},
        dataType: "json",
        beforeSend: function(XMLHttpRequest) {
            layer.load();
        },
        success: function(data) {
            layer.hideload();
            if (data.status == 1) {
                $('#loanHtml').html(data.data.html);
                initPage(pageIndex, data.data.page, data.data.total, "tab1Page");
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

function initPage(pageNo, totalPage, totalRecords, pageContent) {
    kkpager.generPageHtml({
        pno: pageNo,
        pagerid: pageContent,
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
            getLoanRecordList(n, null);
            this.selectPage(n);
            return false;
        }
    });
}

