$(function(){

    var	$app_create_form = $("#app_create_form"),
        $btnAppSuspendSave = $("#btnAppSuspendSave"),
        $btnAppSuspendPush = $("#btnAppSuspendPush"),
        $btnAppSave = $("#btnAppSave"),
        $table = $("#table"),
        url = "app/",
        urlList = url + "wksuspend/list",
        urlAdd = url + "wksuspend/add",
        urlAppSuspendPush = url + "wksuspend/push"

    var queryParams = function(params) {
        return {
            pageSize: params.limit,
            pageNo: params.pageNumber
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
            title : '创建时间'
        }, {
            field : 'beginTime',
            title : '开始时间'
        }, {
            field : 'endTime',
            title : '结束时间'
        }, {
            field : 'isSend',
            title : '是否已推送'
        }
        ]
    };
    $table = tableService.init(tableObj);
    var validatorAppForm = $app_create_form.validate({
        rules : {
            "beginTime" : {
                required: true,
            },"endTime" : {
                required: true,
            }
        },
        messages : {
            "beginTime" : {
                required: '请输入开始时间名称',
            },
            "endTime" : {
                required: '请输入结束时间名称',
            }
        }
    });
    uploadUtil.init({ctrlName:"picUrl_create", uploadUrl: config.api.upload, fileType:['jpg','jpeg', 'gif', 'png'], maxFileCount: 1, drop:false, autoUpload: true}, {},uploadedFile, {field:"picUrl_create"});
    function uploadedFile(arg, resp){
        if(resp.data.code == 200){
            $('#' + arg.field + "_hidden").val(resp.data.data.object);
        }
    }
    $btnAppSuspendSave.on("click", function(){
        modalUtil.open($('#app_modal'), "挖矿APP悬浮窗设置", $app_create_form, true, null, null);
    });

    $btnAppSuspendPush.on("click", function(){
        var row = tableService.getSelections("推送", $table, false);
        row && layer.confirm("图片将被推送至APP并参与显示?", function (index) {
            layer.close(index);
            ajaxUtil.ajax(urlAppSuspendPush, {id: row.id}, "POST", function (resp) {
                if (resp.code === 200) {
                    tableService.refresh($table);
                    layer.msg(resp.msg || "操作成功！", {icon: 6});
                } else {
                    layer.msg(resp.msg || "操作失败！", {icon: 5});
                }
            }, null, null, {btn: $btnAppSuspendPush});
        });
    });

    $btnAppSave.on("click", function(){
        if($('#picUrl_create_hidden').val() == null || $('#picUrl_create_hidden').val() == '' ){
            layer.msg("请选择上传文件后提交", {icon: 5});
            return;
        }
        var _data = utils.getFormJsonString($app_create_form), _url = urlAdd;
        validatorAppForm.form() && ajaxUtil.ajax(_url, _data, "POST", function(resp) {
            if (resp.code === 200) {
                $('#app_modal').modal('hide');
                layer.alert(resp.msg || "操作成功！", {icon: 6}, function(){document.location.reload();});
            } else {
                layer.msg(resp.msg || "操作失败！", {icon: 5});
            }
        }, null, null, {btn : $btnAppSave});
    });
});