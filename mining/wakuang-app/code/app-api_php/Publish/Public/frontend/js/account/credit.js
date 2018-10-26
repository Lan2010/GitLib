/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(function () {
    tabClick("#litag1", "tagContent1", "tagContent");
    /*申请债权*/
    $("#applyCredit").click(function () {
        var isok = validMoney();
        if (isok) {
            $(".modalbox").show();
            $("#credit").submit();
            $("#transItem").hide();
        }
    });
    $(".modalbox a[class*='operateOK']").click(function () {
        window.location.reload();
    });
    $(".modalbox a[class*='operateErr']").click(function () {
        $(".modalbox").hide();
    });
    $('#money').blur(function () {
        var isok = validMoney();
        if (isok) {
            var money = $("#money").val();
            $.ajax({
                type: "post",
                url: "/Debenture/setFee",
                data: {'money': money},
                dataType: "json",
                beforeSend: function (XMLHttpRequest) {
                    layer.load();
                },
                success: function (data) {
                    layer.hideload();
                    if (data.status == 1) {
                        $('#debeFees').text(data.data.debeFees);
                        $('#income').text(data.data.income);
                    }
                }
            });
        }

    });
});
function validMoney() {
    var money = $("#money").val();
    var validMoney = /^(([1-9][0-9]*)|(([0]\.\d{1,2}|[1-9][0-9]*\.\d{1,2})))$/;
    var debeRangArr = $("#debeRange").text().split("~");
    var isok = false;
    if (money == '') {
        layer.msg("请输入转让金额");
    } else if (!validMoney.test(money)) {
        layer.msg("输入的金额格式有误");
    } else if (money*1 < debeRangArr[0]*1 || money*1 > debeRangArr[1]*1) {
        layer.msg("超出债转价格范围");
    } else {
        isok = true;
    }
    return isok;
}
function tabClick(obj, tab, tabs) {
    $(obj).addClass('selectTag').siblings().removeClass("selectTag");
    $("#" + tabs + "").children().css('display', 'none');
    $("#" + tab + "").show();
    if ($(obj).attr("data") == "yes") {
        return;
    }
    switch (tab) {
        case"tagContent1":
            getCreditSq(1, obj);
            break;
        case"tagContent2":
            getCreditZr(1, obj);
            break;
        case"tagContent3":
            getCreditSucc(1, obj);
            break;
        default:
            getCreditSq(1, obj);
            break;
    }
}
/********************************************************* 债权申请 tab1 *********************************/
function getCreditSq(pageIndex, obj) {
    $.ajax({
        type: "post",
        url: "/Debenture/getCreditSq",
        data: {'p': pageIndex},
        dataType: "json",
        beforeSend: function (XMLHttpRequest) {
            layer.load();
        },
        success: function (data) {
            layer.hideload();
            if (data.status == 1) {
                $('#html1').html(data.data.html);

                initPage(pageIndex, data.data.page, data.data.total, "tab1Page");
                if (obj !== null) {
                    $(obj).attr("data", "yes");
                }

            } else {
                layer.msg(data.msg);
            }
        }
    });
}
/********************************************************* 债权转让中 tab1 *********************************/
function getCreditZr(pageIndex, obj) {
    $.ajax({
        type: "post",
        url: "/Debenture/getCreditZr",
        data: {'p': pageIndex},
        dataType: "json",
        beforeSend: function (XMLHttpRequest) {
            layer.load();
        },
        success: function (data) {
            layer.hideload();
            if (data.status == 1) {
                $('#html2').html(data.data.html);

                initPage(pageIndex, data.data.page, data.data.total, "tab2Page");
                if (obj !== null) {
                    $(obj).attr("data", "yes");
                }

            } else {
                layer.msg(data.msg);
            }
        }
    });
}
/********************************************************* 债权转让成功 tab1 *********************************/

function getCreditSucc(pageIndex, obj) {
    $.ajax({
        type: "post",
        url: "/Debenture/getCreditSucc",
        data: {'p': pageIndex},
        dataType: "json",
        beforeSend: function (XMLHttpRequest) {
            layer.load();
        },
        success: function (data) {
            layer.hideload();
            if (data.status == 1) {
                $('#html3').html(data.data.html);

                initPage(pageIndex, data.data.page, data.data.total, "tab3Page");
                if (obj !== null) {
                    $(obj).attr("data", "yes");
                }

            } else {
                layer.msg(data.msg);
            }
        }
    });
}

function initPage(pageNo, totalPage, totalRecords, pageName) {
    kkpager.generPageHtml({
        pno: pageNo,
        pagerid: pageName,
        gopageWrapId: 'tab1Page_gopage_wrap',
        gopageButtonId: 'tab1Page_btn_go',
        gopageTextboxId: 'tab1Page_btn_go_input',
        //总页码
        total: totalPage,
        //总数据条数
        totalRecords: totalRecords,
        getLink: function (n) {
            return this.hrefFormer + this.hrefLatter + "?pno=" + n;
        },
        mode: 'click', //默认值是link，可选link或者click
        click: function (n) {

            if (pageName == "tab1Page") {
                getCreditSq(n, null);
            } else if (pageName == "tab2Page") {
                getCreditZr(n, null);
            } else if (pageName == "tab3Page") {
                getCreditSucc(n, null);
            }  
            this.selectPage(n);
            return false;
        }
    });
}


