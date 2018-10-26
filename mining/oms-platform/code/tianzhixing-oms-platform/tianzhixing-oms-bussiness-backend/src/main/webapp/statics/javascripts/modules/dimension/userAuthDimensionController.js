$(function(){

	var $create_form = $("#create_form"),
        $update_form = $("#update_form"),
        $btnAdd = $("#btnAdd"),
        $btnModify = $("#btnModify"),
        $btnSave = $("#btnSave"),
        $btnUpdate = $("#btnUpdate"),
        $table = $("#table"),
        url = "dimension/",
        urlList = url + "userAuthDimension/list",
        urlCreate = url + "userAuthDimension/add",
        urlDetail = url + "userAuthDimension/detail",
        urlUpdateInfo = url + "userAuthDimension/update";

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
            field : 'name',
            title : '名称'
        }, {
            field : 'value',
            title : '值'
        }, {
            field : 'createTime',
            title : '创建时间',
            format: 'yyyy-mm-dd',
            formatter: function (createTime) {
            	 var date = new Date(createTime);
            	 var str = date.getFullYear() + "-";
            	 str += ((date.getMonth()+1)<10?("0"+(date.getMonth()+1)):(date.getMonth()+1)) +"-" ;
            	 str += (date.getDate()<10?("0"+ date.getDate()): date.getDate()) +" ";
            	 str += (date.getHours()<10?("0"+date.getHours()):date.getHours()) + ":";
            	 str += (date.getMinutes()<10?("0"+date.getMinutes()):date.getMinutes()) + ":";
            	 str += date.getSeconds()<10?("0"+date.getSeconds()):date.getSeconds();
            	 return str;
            }
        }, {
            field : 'enable',
            title : '是否可用'
        }
    ]
    };
    $table = tableService.init(tableObj);

    $btnAdd.click(function() {
        modalUtil.open($('#create_modal'), "创建", $create_form, true, null, null);
    });

    var validatorForm = $create_form.validate({
        rules : {
            "name" : {
                required: true,
                maxlength:32
            },"value" : {
                required: true,
                maxlength:128
            }
        },
        messages : {
            "name" : {
                required: '请输入名称',
                maxlength:'请不要超过32个字符'
            },"value" : {
                required: "请输入值",
                maxlength:"最大字数为128"
            }
        }
    });

    var validatorUpdateForm = $update_form.validate({
        rules : {
            "name" : {
                required: true,
                maxlength:32
            },"value" : {
                required: true,
                maxlength:128
            },"enable" : {
                required: true,
            }
        },
        messages : {
            "name" : {
                required: '请输入名称',
                maxlength:'请不要超过32个字符'
            },"value" : {
                required: "请输入值",
                maxlength:"最大字数为128"
            },
            "enable" : {
                required: '请选择是否可用'
            }
        }
    });

    $btnSave.on("click", function(){

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

    $btnModify.on("click", function(){
        var row = tableService.getSelections("更新", $table, false);
        row && modalUtil.open($('#update_modal'), "更新信息", $update_form, true, utils.getInfo, {id:row.id, url: urlDetail, form: "update_form", btn:"btnModify"});
    });
   
});

