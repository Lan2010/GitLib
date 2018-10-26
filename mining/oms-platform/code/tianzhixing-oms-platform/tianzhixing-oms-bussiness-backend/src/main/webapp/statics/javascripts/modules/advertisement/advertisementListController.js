$(function(){

    cityList();
    function cityList(){
        $.ajax({
            url : '/city/list/baidu',
            type : 'get',
            data : {},
            success : function(rep) {
                if(rep.code === 200 && rep.data.total != 0){
                    $.each(rep.data.list, function(index, val){
                        $('#city_create').append("<option value='"+val.code+"'>"+val.name+"</option>");
                        $('#city_update').append("<option value='"+val.code+"'>"+val.name+"</option>");
                    });
                }
            },
            error : function() {
            }
        });
    }

    var	$beginTimeB = $("#time_begin_b"),
        $endTimeB = $("#time_end_b"),
        $beginTimeE = $("#time_begin_e"),
        $endTimeE = $("#time_end_e"),
        $btnSearch = $("#btnSearch"),
        $create_form = $("#create_form"),
        $update_form = $("#update_form"),
        $btnAdd = $("#btnAdd"),
        $btnModify = $("#btnModify"),
        $btnSave = $("#btnSave"),
        $btnUpdate = $("#btnUpdate"),
        $btnPush = $("#btnPush"),
        $table = $("#table"),
        url = "advert/",
        urlList = url + "list",
        urlDetail = url + "detail",
        urlCreate = url + "add",
        urlUpdateInfo = url + "update",
        urlPush = url + "push";

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
            field : 'advertName',
            title : '广告名称'
        }, {
            field : 'totalCount',
            title : '总点击/浏览量'
        }, {
            field : 'totalAccessStarPoint',
            title : '访问总奖励'
        }, {
            field : 'totalClickStarPoint',
            title : '点击总奖励'
        }, {
            field : 'onceAccessStarPoint',
            title : '单次访问奖励'
        }, {
            field : 'onceClickStarPoint',
            title : '单次点击奖励'
        }, {
            field : 'beginTime',
            title : '开始时间'
        }, {
            field : 'endTime',
            title : '结束时间'
        }, {
            field : 'advertStatus',
            title : '广告状态'
        }, {
            field : 'advertisementType',
            title : '广告类型'
        }, {
            field : 'advertisementAttribute',
            title : '广告属性'
        }, {
            field : 'area',
            title : '区'
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

    $btnSearch.on("click", function(){
        tableService.refresh($table);
    });

    $btnAdd.click(function() {
        modalUtil.open($('#create_modal'), "创建广告", $create_form, true, null, null);
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
            "advertName" : {
                required: true,
                maxlength:32
            },
            "advertisementDescribe" : {
                required: true,
                maxlength:128
            },
            "advertIcon" : {
                required: true,
            },
            "advertPic" : {
                required: true,
            },
            "beginTime" : {
                required: true,
            },
            "endTime" : {
                required: true,
            },
            "advertRemark":{
                required: true,
                maxlength:32
            },
            "totalAccessStarPoint":{
                required: true,
                min: 0,
                rateValidator: $('#totalAccessStarPoint_create').val()
            },
            "totalClickStarPoint":{
                required: true,
                min: 0,
                rateValidator: $('#totalClickStarPoint_create').val()
            },
            "totalCount":{
                required: true,
                numberValidator: $('#totalCount_create').val(),
                min: 1,
            },
            "city":{
                required: true,
                maxlength:32
            }
        },
        messages : {
            "advertName" : {
                required: '请输入广告名称',
                maxlength:'请不要超过32个字符'
            },"advertisementDescribe" : {
                required: "请输入广告描述",
                maxlength:"最大字数为128"
            },
            "advertIcon" : {
                required: "请上传广告图标",
            },
            "advertPic" : {
                required: "请上传广告宣传图",
            },
            "beginTime" : {
                required: '请输入开始时间'
            },
            "endTime" : {
                required: '请输入结束时间'
            },
            "advertRemark":{
                required: '请输入广告语',
                maxlength:'请不要超过32个字符'
            },
            "totalAccessStarPoint":{
                required: "请输入访问奖励",
                min: "奖励最小值0.0000",
                rateValidator: "请输入正确的奖励"
            },
            "totalClickStarPoint":{
                required: "请输入点击奖励",
                min: "奖励最小值0.0000",
                rateValidator: "请输入正确的奖励"
            },
            "totalCount":{
                required: "请输入总点击/浏览量",
                numberValidator: "点击/浏览为正整数",
                min: "点击/浏览最小为0",
            },
            "city":{
                required: '请输入城市',
                maxlength:'请不要超过32个字符'
            }
        }
    });

    var validatorUpdateForm = $update_form.validate({
        rules : {
            "advertName" : {
                required: true,
                maxlength:32
            },"advertisementDescribe" : {
                required: true,
                maxlength:128
            },"advetIcon" : {
                required: true,
            },"advertPic" : {
                required: true,
            },
            "beginTime" : {
                required: true,
            },
            "endTime" : {
                required: true,
            },
            "advertRemark":{
                required: true,
                maxlength:32
            },
            "totalAccessStarPoint":{
                required: true,
                min: 0,
                rateValidator: $('#totalAccessStarPoint_update').val()
            },
            "totalClickStarPoint":{
                required: true,
                min: 0,
                rateValidator: $('#totalClickStarPoint_update').val()
            },
            "totalCount":{
                required: true,
                numberValidator: $('#totalCount_update').val(),
                min: 1,
            },
            "city":{
                required: true,
                maxlength:32
            }
        },
        messages : {
            "advertName" : {
                required: '请输入广告名称',
                maxlength:'请不要超过32个字符'
            },"advertisementDescribe" : {
                required: "请输入广告描述",
                maxlength:"最大字数为128"
            },"advetIcon" : {
                required: "请上传广告图标",
            },"advertPic" : {
                required: "请上传广告宣传图",
            },
            "beginTime" : {
                required: '请输入开始时间'
            },
            "endTime" : {
                required: '请输入结束时间'
            },
            "advertRemark":{
                required: '请输入广告语',
                maxlength:'请不要超过32个字符'
            },
            "totalAccessStarPoint":{
                required: "请输入访问奖励",
                min: "奖励最小值0.0000",
                rateValidator: "请输入正确的奖励"
            },
            "totalClickStarPoint":{
                required: "请输入点击奖励",
                min: "奖励最小值0.0000",
                rateValidator: "请输入正确的奖励"
            },
            "totalCount":{
                required: "请输入总点击/浏览量",
                numberValidator: "点击/浏览为正整数",
                min: "点击/浏览最小为0",
            },
            "city":{
                required: '请输入城市',
                maxlength:'请不要超过32个字符'
            }
        }
    });

    $btnSave.on("click", function(){

        if(!validateFile("advertIcon_create_hidden", "advertPic_create_hidden")){
            layer.msg("请选择上传文件后提交", {icon: 5});
            return;
        }

        var _data = utils.getFormJsonString($create_form), _url = urlCreate;
        validatorForm.form() && ajaxUtil.ajax(_url, _data, "POST", function(resp) {
            if (resp.code === 200) {
                $('#create_modal').modal('hide');
                layer.alert(resp.msg || "操作成功！", {icon: 6}, function(){document.location.reload();});
            } else {
                layer.msg(resp.msg || "操作失败！", {icon: 5});
            }
        }, null, null, {btn : $btnSave});
    });

    $btnUpdate.on("click", function(){
        if(!validateFile("advertIcon_update_hidden", "advertPic_update_hidden")){
            layer.msg("请选择上传文件后提交", {icon: 5});
            return;
        }
        var _data = utils.getFormJsonString($update_form), _url = urlUpdateInfo;
        validatorUpdateForm.form() && ajaxUtil.ajax(_url, _data, "POST", function(resp) {
            if (resp.code === 200) {
                $('#update_modal').modal('hide');
                layer.alert(resp.msg || "操作成功！", {icon: 6}, function(){document.location.reload();});
            } else {
                layer.msg(resp.msg || "操作失败！", {icon: 5});
            }
        }, null, null, {btn : $btnUpdate});
    });

    function validateFile(v1, v2){
        return $('#'+v1).val() != null && $('#'+v1).val() != '' && $('#'+v2).val() != null && $('#'+v2).val() != '';
    }

    $btnModify.on("click", function(){
        var row = tableService.getSelections("更新", $table, false);
        row && modalUtil.open($('#update_modal'), "更新广告信息", $update_form, true, utils.getInfo, {id:row.id, url: urlDetail, form: "update_form", btn:"btnModify"});
    });

    $btnPush.on("click", function(){
        var row = tableService.getSelections("推送", $table, false);
        row && layer.confirm("广告将被推送至APP并参与计算?", function (index) {
            layer.close(index);
            ajaxUtil.ajax(urlPush, {id: row.id}, "POST", function (resp) {
                if (resp.code === 200) {
                    tableService.refresh($table);
                    layer.msg(resp.msg || "操作成功！", {icon: 6});
                } else {
                    layer.msg(resp.msg || "操作失败！", {icon: 5});
                }
            }, null, null, {btn: $btnPush});
        });
    });

    uploadUtil.init({ctrlName:"advertIcon_create", uploadUrl: config.api.upload, fileType:['jpg','jpeg', 'gif', 'png'], maxFileCount: 1, drop:false, autoUpload: true}, {},uploadedFile, {field:"advertIcon_create"});
    uploadUtil.init({ctrlName:"advertIcon_update", uploadUrl: config.api.upload, fileType:['jpg','jpeg', 'gif', 'png'], maxFileCount: 1, drop:false, autoUpload: true}, {},uploadedFile, {field:"advertIcon_update"});
    uploadUtil.init({ctrlName:"advertPic_create", uploadUrl: config.api.upload, fileType:['jpg','jpeg', 'gif', 'png'], maxFileCount: 1, drop:false, autoUpload: true}, {},uploadedFile, {field:"advertPic_create"});
    uploadUtil.init({ctrlName:"advertPic_update", uploadUrl: config.api.upload, fileType:['jpg','jpeg', 'gif', 'png'], maxFileCount: 1, drop:false, autoUpload: true}, {},uploadedFile, {field:"advertPic_update"});
    function uploadedFile(arg, resp){
        if(resp.data.code == 200){
            $('#' + arg.field + "_hidden").val(resp.data.data.object);
        }
    }
    
});