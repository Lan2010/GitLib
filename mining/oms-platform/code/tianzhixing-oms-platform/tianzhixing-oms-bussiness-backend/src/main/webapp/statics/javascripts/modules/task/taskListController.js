$(function(){

    var	$beginTimeB = $("#time_begin_b"),
        $endTimeB = $("#time_end_b"),
        $beginTimeE = $("#time_begin_e"),
        $endTimeE = $("#time_end_e"),
        $update_form = $("#update_form"),
        $btnSearch = $("#btnSearch"),
        $btnSure = $("#btnSure"),
        $btnModify = $("#btnModify"),
        $btnUpdate = $("#btnUpdate"),
        $table = $("#table"),
        url = "task/",
        urlList = url + "list",
        urlDetail = url + "detail",
        urlUpdateInfo = url + "update",
        urlPushTask = url + "push";

    var queryParams = function(params) {
        return {
            pageSize: params.limit,
            pageNo: params.pageNumber,
            beginTimeB : $beginTimeB.val(),
            endTimeB  : $endTimeB.val(),
            beginTimeE : $beginTimeE.val(),
            endTimeE : $endTimeE.val()
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
            field : 'taskName',
            title : '任务名称'
        }, {
            field : 'taskLevel',
            title : '等级'
        }, {
            field : 'taskAward',
            title : '任务奖励'
        }, {
            field : 'rate',
            title : '汇率'
        }, {
            field : 'taskRadius',
            title : '任务半径'
        }, {
            field : 'beginTime',
            title : '开始时间'
        }, {
            field : 'endTime',
            title : '结束时间'
        }, {
            field : 'taskStatus',
            title : '任务状态'
        }, {
            field : 'keyword',
            title : '关键字'
        }, {
            field : 'city',
            title : '市'
        }, {
            field : 'isSend',
            title : '推送状态'
        }
    ]
    };
    $table = tableService.init(tableObj);

    jQuery.validator.addMethod("rateValidator",function(value, element){
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

    jQuery.validator.addMethod("numberValidator",function(value, element){
        var regu = /^[0-9]*[1-9][0-9]*$/;
        return regu.test(value)
    },"必需为正整数");

    var validatorForm = $update_form.validate({
        rules : {
            "taskName" : {
                required: true,
                maxlength:32
            },
            "taskAward" : {
                required: true,
                min: 0,
                rateValidator: $('#taskAward_update').val()
            },
            "taskLevel" : {
                required: true,
            },
            "taskRemark" : {
                required: true,
                maxlength:128,
            },
            "taskIcon" : {
                required: true,
            },
            "beginTime" : {
                required: true,
            },
            "endTime" : {
                required: true,
            },
            "rate":{
                required: true,
                min: 0,
                rateValidator: $('#rate_update').val()
            },
            "taskRadius":{
                required: true,
                min: 0,
                numberValidator: $('#taskRadius_update').val()
            }
        },
        messages : {
            "taskName" : {
                required: '请输入任务名称',
                maxlength:'请不要超过32个字符'
            },
            "taskPopupRadius" : {
                required: "请输入任务奖励",
                min: 0,
                rateValidator: "任务奖励格式不正确"
            },
            "taskLevel" : {
                required: "请选择任务等级",
            },
            "taskRemark" : {
                required: "请输入任务描述",
                maxlength:'请不要超过128个字符'
            },
            "taskIcon" : {
                required: '请上传任务图标'
            },
            "beginTime" : {
                required: '请输入开始时间'
            },
            "endTime" : {
                required: '请输入结束时间'
            },
            "rate":{
                min: '最小汇率为0',
                required: '请输入汇率',
                rateValidator: "汇率格式不正确"
            },
            "taskRadius":{
                required: "请输入任务半径",
                min: 0,
                numberValidator: "任务半径格式不正确"
            }
        }
    });

    $btnSearch.on("click", function(){
        tableService.refresh($table);
    });

    $btnSure.on("click", function(){
        var row = tableService.getSelections("推送", $table, false);
        row && layer.confirm("任务将被推送APP并参与计算?", function (index) {
            layer.close(index);
            ajaxUtil.ajax(urlPushTask, {taskId: row.id}, "POST", function (resp) {
                if (resp.code === 200) {
                    tableService.refresh($table);
                    layer.msg(resp.msg || "操作成功！", {icon: 6});
                } else {
                    layer.msg(resp.msg || "操作失败！", {icon: 5});
                }
            }, null, null, {btn: $btnSure});
        });
    });

    $btnModify.on("click", function(){
        var row = tableService.getSelections("更新", $table, false);
        row && modalUtil.open($('#update_modal'), "更新任务信息", $update_form, true, utils.getInfo, {id:row.id, url: urlDetail, form: "update_form", btn:"btnModify"});
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

    uploadUtil.init({ctrlName:"icon_update", uploadUrl: config.api.upload, fileType:['jpg','jpeg', 'gif', 'png'], maxFileCount: 1, drop:false, autoUpload: true}, {},uploadedFile, {field:"icon_update"});
    function uploadedFile(arg, resp){
        if(resp.data.code == 200){
            $('#' + arg.field + "_hidden").val(resp.data.data.object);
        }
    }
});