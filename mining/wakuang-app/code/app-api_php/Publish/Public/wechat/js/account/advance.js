/**
 * 投资记录
 * @returns {undefined}
 */
function recordInvest() {
    weui.getAjaxData('/Wechat/Account/advanceAjax/', {'p': 1}, "advanceReco");
}

function repay(obj) {
    var orderno = $(obj).attr("data");
    var repno = $(obj).attr("dataRep");
    window.location.href = "/Wechat/Account/repayAdvance/orderno/" + orderno + "/repno/" + repno;
}

$(function() {
    recordInvest();
    $('#nodata').hide();
    weui.scrollLoad(document, 50, function(offset) {
        if (offset >= 0) {
            weui.getAjaxData('/Wechat/Account/getLoanInfo/', {'p': $('#pageIndex').val()}, "advanceReco");
        }
    });
});