$(function(){

    var	$update_form = $("#update_form"),
        $app_create_form = $("#app_create_form"),
        $btnModify = $("#btnModify"),
        $btnUpdate = $("#btnUpdate"),
        $btnAppSuspend = $("#btnAppSuspend"),
        $btnAppSave = $("#btnAppSave"),
        $table = $("#table"),
        url = "system/",
        urlList = url + "list",
        urlDetail = url + "detail",
        urlUpdateInfo = url + "update",
        urlAppSuspendInfo = url + "app/suspend/push"

    var queryParams = function(params) {
        return {
            pageSize: params.limit,
            pageNo: params.pageNumber
        };
    };

    jQuery.validator.addMethod("valueValidator",function(value, element){
        var regu = /^[0-9]+\.?[0-9]*$/;
        if (value != "") {
            if (!regu.test(value)) {
                return false;
            } else {
                if (value.indexOf('.') > -1) {
                    if (value.split('.')[1].length > 4) {
                        return false;
                    }
                }
            }
        }
        return true;
    },"小数点后最多为四位");

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
            field : 'systemParamType',
            title : '参数类型'
        }, {
            field : 'systemValue',
            title : '参数值'
        }
    ]
    };
    $table = tableService.init(tableObj);

    var validatorForm = $update_form.validate({
        rules : {
            "systemValue" : {
                required: true,
                valueValidator: $('#value_update').val()
            }
        },
        messages : {
            "systemValue" : {
                required: '请输入任务名称',
                valueValidator:'请输入数字或者小数值'
            }
        }
    });
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
    uploadUtil.init({ctrlName:"appPic_create", uploadUrl: config.api.upload, fileType:['jpg','jpeg', 'gif', 'png'], maxFileCount: 1, drop:false, autoUpload: true}, {},uploadedFile, {field:"appPic_create"});
    function uploadedFile(arg, resp){
        if(resp.data.code == 200){
            $('#' + arg.field + "_hidden").val(resp.data.data.object);
        }
    }
    $btnModify.on("click", function(){
        var row = tableService.getSelections("更新", $table, false);
        row && modalUtil.open($('#update_modal'), "更新参数信息", $update_form, true, utils.getInfo, {id:row.id, url: urlDetail, form: "update_form", btn:"btnModify"});
    });

    $btnUpdate.on("click", function(){
        var _data = utils.getFormJsonString($update_form), _url = urlUpdateInfo;
        validatorForm.form() && ajaxUtil.ajax(_url, _data, "POST", function(resp) {
            if (resp.code === 200) {
                $('#update_modal').modal('hide');
                layer.alert(resp.msg || "操作成功！", {icon: 6}, function(){document.location.reload();});
            } else {
                layer.msg(resp.msg || "操作失败！", {icon: 5});
            }
        }, null, null, {btn : $btnUpdate});
    });

});