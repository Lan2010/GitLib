$(function(){
    $('.datepicker_day_input input').datetimepicker({
        language: 'zh-CN',
        autoclose: 1,
        todayBtn: 1,
        pickerPosition: "bottom-left",
        minuteStep: 5,
        format: 'yyyy-mm-dd',
        minView: 'month'
    });

    var	$messageBeginTime = $("#messageTime_begin_s"),
        $messageEndTime = $("#messageTime_end_s"),
        $isRead = $("#isRead_s"),
        $btnSearch = $("#btnSearch"),
        $unReadBtn = $("#unReadBtn"),
        $readBtn = $("#readBtn"),
        $table = $("#table"),
        url = "/message/",
        urlList = url + "list",
        urlUpdateInfo = url + "read";

    var queryParams = function(params) {
        return {
            pageSize: params.limit,
            pageNo: params.pageNumber,
            messageBeginTime : $messageBeginTime.val(),
            messageEndTime  : $messageEndTime.val(),
            isRead : $isRead.val()
        };
    };
    var tableObj = {
        url : urlList,
        queryParams: queryParams,
        clickToSelect : true,
        singleSelect : true,
        pagination: true,
        columns : [ {
            field : '',
            checkbox : true
        }, {
            field : 'id',
            title : '序号'
        }, {
            field : 'createTime',
            title : '消息时间'
        }, {
            field : 'isRead',
            title : '是否已读'
        }, {
            field : 'content',
            title : '消息内容'
        }]
    };
    $table = tableService.init(tableObj);

    $btnSearch.on("click", function(){
        tableService.refresh($table);
    });

    $unReadBtn.on("click", function(){
        var row = tableService.getSelections("更改", $table, false);
        if(row.isRead == '未读'){
            layer.msg("当前状态为未读，不可修改！", {icon: 5});
            return;
        }
        row && layer.confirm("您确定要置为未读吗?", function (index) {
            layer.close(index);
            ajaxUtil.ajax(urlUpdateInfo, {id: row.id, toRead : false}, "get", function (resp) {
                if (resp.code === 200) {
                    tableService.refresh($table);
                    layer.msg(resp.msg || "操作成功！", {icon: 6});
                } else {
                    layer.msg(resp.msg || "操作失败！", {icon: 5});
                }
            }, null, null, {btn: $unReadBtn});
        });
    });
    $readBtn.on("click", function(){
        var row = tableService.getSelections("更改", $table, false);
        if(row.isRead == '已读'){
            layer.msg("当前状态为已读，不可修改！", {icon: 5});
            return;
        }
        row && layer.confirm("您确定要置为已读吗?", function (index) {
            layer.close(index);
            ajaxUtil.ajax(urlUpdateInfo, {id: row.id, toRead : true}, "get", function (resp) {
                if (resp.code === 200) {
                    tableService.refresh($table);
                    layer.msg(resp.msg || "操作成功！", {icon: 6});
                } else {
                    layer.msg(resp.msg || "操作失败！", {icon: 5});
                }
            }, null, null, {btn: $unReadBtn});
        });
    });
});