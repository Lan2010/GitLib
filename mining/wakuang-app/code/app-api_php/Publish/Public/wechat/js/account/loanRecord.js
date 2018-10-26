/**
 * 投资记录
 * @returns {undefined}
 */
function recordInvest(node) {
    var nodeId = '';
    if (node.attr('data') == "loanIn") {
        nodeId = "loanIn";
    } else if (node.attr('data') == "loanOver") {
        nodeId = "loanOver";
    } else {
        return;
    }
    weui.getAjaxData('/Wechat/Account/getLoanInfo/', {'type': nodeId, 'p': 1}, $("#" + nodeId).attr("data"));
}
function authBorrow(obj) {
    var orderno = $(obj).attr("data");
    window.location.href = "/Wechat/Account/repayAuth/orderno/" + orderno;
}

function repBorrow(obj, isTime) {
    if (isTime * 1 == 1) {
        $.alert("未到还款时间");
        return false;
    }
    var orderno = $(obj).attr("data");
    var repno = $(obj).attr("dataRep");
    window.location.href = "/Wechat/Account/repay/orderno/" + orderno + "/repno/" + repno;
}

$(function() {
    recordInvest($('.active'));
    $('#nodata').hide();
    $(".nav li").click(function() {
        $("#wxMore").hide();
        $("#wxLoad").hide();
        $("#wxNo").hide();
        $('#nodata').hide();
        weui.loading = false;
        recordInvest($(this));
    });
    weui.scrollLoad(document, 50, function(offset) {
        if (offset >= 0) {
            var nodeId = '';
            if ($('.active').attr('data') == "loanIn") {
                nodeId = "loanIn";
            } else if ($('.active').attr('data') == "loanOver") {
                nodeId = "loanOver";
            } else {
                return;
            }
            weui.getAjaxData('/Wechat/Account/getLoanInfo/', {'type': nodeId, 'p': $('#pageIndex').val()}, $("#" + nodeId).attr("data"));
        }
    });
});