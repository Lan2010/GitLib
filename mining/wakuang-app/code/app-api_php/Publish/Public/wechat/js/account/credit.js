/**
 * 列表信息
 * @returns {undefined}
 */
function recordInvest(node) {
    var nodeId = '';
    if (node.attr('data') == "debetransfer") {
        nodeId = "transfer";
    } else if (node.attr('data') == "debetransfering") {
        nodeId = "transfering";
    } else if (node.attr('data') == "debetransfered") {
        nodeId = "transfered";
    } else {
        return;
    }
    weui.getAjaxData('/Wechat/Account/getCreditInfo/', {'type': nodeId, 'p': 1}, nodeId);
}
$(function () {
    recordInvest($('.active'));
    $('#nodata').hide();
    $(".nav li").click(function () {
        $("#wxMore").hide();
        $("#wxLoad").hide();
        $("#wxNo").hide();
        $('#nodata').hide();
        weui.loading = false;
        recordInvest($(this));
    });
    weui.scrollLoad(document, 50, function (offset) {
        if (offset >= 0) {
            var nodeId = '';
            if ($('.active').attr('data') == "debetransfer") {
                nodeId = "transfer";
            } else if ($('.active').attr('data') == "debetransfering") {
                nodeId = "transfering";
            } else if ($('.active').attr('data') == "debetransfered") {
                nodeId = "transfered";
            } else {
                return;
            }
            weui.getAjaxData('/Wechat/Account/getCreditInfo/', {'type': nodeId, 'p': $('#pageIndex').val()}, nodeId);
        }
    });
});