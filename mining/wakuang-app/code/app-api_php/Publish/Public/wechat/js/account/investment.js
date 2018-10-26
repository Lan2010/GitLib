/**
 * 投资记录
 * @returns {undefined}
 */
function recordInvest(node) {
    var nodeId = '';
    if (node.attr('data') == "investmentIn") {
        nodeId = "investing";
    } else if (node.attr('data') == "repaymentIn") {
        nodeId = "repayment";
    } else if (node.attr('data') == "settle") {
        nodeId = "over";
    } else {
        return;
    }
    weui.getAjaxData('/Wechat/Account/moreInvest/', {'type': nodeId, 'p': 1}, nodeId);
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
            if ($('.active').attr('data') == "investmentIn") {
                nodeId = "investing";
            } else if ($('.active').attr('data') == "repaymentIn") {
                nodeId = "repayment";
            } else if ($('.active').attr('data') == "settle") {
                nodeId = "over";
            } else {
                return;
            }
            weui.getAjaxData('/Wechat/Account/moreInvest/', {'type': nodeId, 'p': $('#pageIndex').val()}, nodeId);
        }
    });
});