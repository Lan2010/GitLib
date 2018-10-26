$(function(){

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
        $table = $("#table"),
        url = "dimension/pagesDimension/",
        urlList = url + "list",
        urlDetail = url + "detail",
        urlCreate = url + "add",
        urlUpdateInfo = url + "update";

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
        },{
            field : 'name',
            title : '名称'
        }, {
            field : 'url',
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
        }, 
        {
            field : 'beginTime',
            title : '开始时间'
        }, {
            field : 'endTime',
            title : '结束时间'
        },
        {
            field : 'enable',
            title : '是否可用'
        }
    ]
    };
    $table = tableService.init(tableObj);

    $btnSearch.on("click", function(){
        tableService.refresh($table);
    });

    $btnAdd.click(function() {
        modalUtil.open($('#create_modal'), "创建", $create_form, true, null, null);
    });

    var validatorForm = $create_form.validate({
        rules : {
            "name" : {
                required: true,
                maxlength:32
            },
            "url" : {
                required: true,
                maxlength:128
            },
            "beginTime" : {
                required: true,
            },
            "endTime" : {
                required: true,
            }
        },
        messages : {
            "name" : {
                required: '请输入名称',
                maxlength:'请不要超过32个字符'
            },
            "url" : {
                required: "请输入url",
                maxlength:"最大字数为128"
            },
            "beginTime" : {
                required: '请输入开始时间'
            },
            "endTime" : {
                required: '请输入结束时间'
            }
        }
    });

    var validatorUpdateForm = $update_form.validate({
        rules : {
            "name" : {
                required: true,
                maxlength:32
            },"url" : {
                required: true,
            },
            "beginTime" : {
                required: true,
            },
            "endTime" : {
                required: true,
            },
            "enable":{
                required: true,
                maxlength:32
            }
        },
        messages : {
            "name" : {
                required: '请输入名称',
                maxlength:'请不要超过32个字符'
            },
            "url" : {
                required: "请输入广告描述",
            },
            "beginTime" : {
                required: '请输入开始时间'
            },
            "endTime" : {
                required: '请输入结束时间'
            },
            "enable":{
                required: '请选择是否可用',
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