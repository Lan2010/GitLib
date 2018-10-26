$(function(){

    taskList(true, 0);
    cityList();

    function taskList(empty, taskId){
        if(empty){
            $('#task_info_list_select option').remove();
            $('#task_info_list_select').append("<option value=''>全部</option>");
            $('#task_info_list').append("<option value=''>请选择</option>");
        }

        $.ajax({
            url : '/task/all',
            type : 'get',
            data : {},
            success : function(rep) {
                if(rep.code === 200 && rep.data.total != 0){
                    $.each(rep.data.list, function(index, val){
                        $('#task_info_list_select').append("<option value='"+val.id+"'>"+val.taskName+"</option>");
                        $('#task_info_list').append("<option value='"+val.id+"'>"+val.taskName+"</option>");
                    });
                }
            },
            error : function() {
            }
        });
    };

    function cityList(){
        $.ajax({
            url : '/city/list/baidu',
            type : 'get',
            data : {},
            success : function(rep) {
                if(rep.code === 200 && rep.data.total != 0){
                    $.each(rep.data.list, function(index, val){
                        $('#city_create').append("<option value='"+val.code+"'>"+val.name+"</option>");
                    });
                }
            },
            error : function() {
            }
        });
    }

    var	$taskId = $("#task_info_list_select"),
        $btnSearch = $("#btnSearch"),
        $create_form = $("#create_form"),
        $create_location_form = $("#create_location_form"),
        $btnAdd = $("#btnAdd"),
        $btnAddLocation = $("#btnAddLocation"),
        $btnSave = $("#btnSave"),
        $btnSaveLocation = $("#btnSaveLocation"),
        $table = $("#table"),
        url = "task/",
        urlList = url + "location/list",
        urlCreateTask = url + "add",
        urlCreateLocation = url + "location/add";

    var queryParams = function(params) {
        return {
            pageSize: params.limit,
            pageNo: params.pageNumber,
            taskId : $taskId.val()
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
            title : '所属任务'
        }, {
            field : 'status',
            title : '状态'
        }, {
            field : 'name',
            title : '名字'
        }, {
            field : 'address',
            title : '地址'
        }
    ]
    };
    $table = tableService.init(tableObj);

    $btnSearch.on("click", function(){
        tableService.refresh($table);
    });

    /**
     * 创建按钮点击事件
     */
    $btnAdd.click(function() {
        modalUtil.open($('#create_modal'), "创建任务", $create_form, true, null, null);
    });

    $btnAddLocation.click(function() {
        modalUtil.open($('#create_location_modal'), "创建地址", $create_location_form, true, null, null);
    });


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

    var validatorForm = $create_form.validate({
        rules : {
            "taskName" : {
                required: true,
                maxlength:32
            },
            "taskAward" : {
                required: true,
                min: 0,
                rateValidator: $('#taskAward_create').val()
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
            "keyword":{
                required: true,
                maxlength:32
            },
            "rate":{
                required: true,
                min: 0,
                rateValidator: $('#area_create').val()
            },
            "taskRadius":{
                required: true,
                min: 0,
                numberValidator: $('#taskRadius_create').val()
            },
            "city":{
                required: true,
                maxlength:32
            }
        },
        messages : {
            "taskName" : {
                required: '请输入任务名称',
                maxlength:'请不要超过32个字符'
            },
            "taskAward" : {
                required: "请输入任务奖励",
                min: 0,
                numberValidator: "任务奖励格式不正确"
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
            "keyword":{
                required: '请输入关键字',
                maxlength:'请不要超过32个字符'
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
            },
            "city":{
                required: '请输入城市',
                maxlength:'请不要超过32个字符'
            }
        }
    });

    var validatorFormLocation = $create_location_form.validate({
        rules : {
            "taskId" : {
                required: true,
            },
            "name" : {
                required: true,
            },
            "lng" : {
                required: true,
            },
            "lat" : {
                required: true,
            },
            "address" : {
                required: true,
            }
        },
        messages : {
            "taskId" : {
                required: "请选择任务"
            },
            "name" : {
                required: "输入地址名称"
            },
            "lng" : {
                required: "请输入经度"
            },
            "lat" : {
                required: "请输入纬度"
            },
            "address" : {
                required: "请输入详细地址"
            }
        }
    });

    $btnSave.on("click", function(){
        if($('#icon_create_hidden').val() == null || $('#icon_create_hidden').val() == '' ){
            layer.msg("请选择上传文件后提交", {icon: 5});
            return;
        }
        var _data = utils.getFormJsonString($create_form), _url = urlCreateTask;
        validatorForm.form() && ajaxUtil.ajax(_url, _data, "POST", function(resp) {
            if (resp.code === 200) {
                taskList(true, resp.data.object.id);
                $('#create_modal').modal('hide');
                layer.alert(resp.msg || "操作成功！", {icon: 6}, function(){document.location.reload();});
            } else {
                layer.msg(resp.msg || "操作失败！", {icon: 5});
            }
        }, null, null, {btn : $btnSave});
    });

    $btnSaveLocation.on("click", function(){
        var _data = utils.getFormJsonString($create_location_form), _url = urlCreateLocation;
        validatorFormLocation.form() && ajaxUtil.ajax(_url, _data, "POST", function(resp) {
            if (resp.code === 200) {
                $('#create_location_modal').modal('hide');
                layer.alert(resp.msg || "操作成功！", {icon: 6}, function(){document.location.reload();});
            } else {
                layer.msg(resp.msg || "操作失败！", {icon: 5});
            }
        }, null, null, {btn : $btnSaveLocation});
    });

    uploadUtil.init({ctrlName:"icon_create", uploadUrl: config.api.upload, fileType:['jpg','jpeg', 'gif', 'png'], maxFileCount: 1, drop:false, autoUpload: true}, {},uploadedFile, {field:"icon_create"});
    function uploadedFile(arg, resp){
        if(resp.data.code == 200){
            $('#' + arg.field + "_hidden").val(resp.data.data.object);
        }
    }

});

