/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(function() {
    tabClick("#litag1", "tagContent1", "tagContent");
});

function tabClick(obj, tab, tabs) {
    $(obj).closest('ul').children("li").removeClass('selectTag')
    $(obj).addClass('selectTag');
    $("#" + tabs + "").children().css('display', 'none');
    $("#" + tab + "").show();

    if ($(obj).attr("data") == "yes") {
        return;
    }
    switch (tab) {
        case"tagContent1":
            getExperienceRecord(1, obj);
            break;
        case"tagContent2":
            getUseExperienceRecord(1, obj);
            break;
        default:
            getExperienceRecord(1, obj);
            break;
    }
}
/********************************************************* 体验金获取记录 tab1 *********************************/
function getExperienceRecord(pageIndex, obj) {

    $.ajax({
        type: "post",
        url: "/Experience/getExperienceList",
        data: {'p': pageIndex},
        dataType: "json",
        beforeSend: function(XMLHttpRequest) {
            layer.load();
        },
        success: function(data) {
            layer.hideload();
            if (data.status == 1) {
                $('#grantHtml').html(data.data.html);
                initPage(pageIndex, data.data.page, data.data.total);
                if (obj !== null) {
                    $(obj).attr("data", "yes");
                }

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


function initPage(pageNo, totalPage, totalRecords) {
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
            getExperienceRecord(n, null);
            this.selectPage(n);
            return false;
        }
    });
}
/********************************************************* 投资成功 tab2 *********************************/
function getUseExperienceRecord(pageIndex, obj) {
 
    $.ajax({
        type: "post",
        url: "/Experience/getUseExperienceList",
        data: {'p': pageIndex},
        dataType: "json",
        beforeSend: function(XMLHttpRequest) {
            layer.load();
        },
        success: function(data) {
            layer.hideload();
            if (data.status == 1) {
                $('#getHtml').html(data.data.html);
                initPage2(pageIndex, data.data.page, data.data.total);
                if (obj !== null) {
                    $(obj).attr("data", "yes");
                }
            } else {
                layer.warn(data.msg);
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
 

function initPage2(pageNo, totalPage, totalRecords) {
    kkpager.generPageHtml({
        pno: pageNo,
        pagerid: "tab2Page",
        gopageWrapId: 'tab2Page_gopage_wrap',
        gopageButtonId: 'tab2Page_btn_go',
        gopageTextboxId: 'tab2Page_btn_go_input',
        //总页码
        total: totalPage,
        //总数据条数
        totalRecords: totalRecords,
        getLink: function(n) {
            return this.hrefFormer + this.hrefLatter + "?pno=" + n;
        },
        mode: 'click', //默认值是link，可选link或者click
        click: function(n) {
            getTenderSuccessRecord(n, null);
            this.selectPage(n);
            return false;
        }
    });
}