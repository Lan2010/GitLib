$(function(){

    var	$create_form = $("#create_form"),
        $update_form = $("#update_form"),
        $btnAdd = $("#btnAdd"),
        $btnModify = $("#btnModify"),
        $btnSave = $("#btnSave"),
        $btnUpdate = $("#btnUpdate"),
        $table = $("#table"),
        url = "user/courseDetail/",
        urlList = url + "list",
        urlDetail = url + "detail",
        urlCreate = url + "add",
        urlUpdateInfo = url + "update";

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
            field : 'videoName',
            title : '视频名称'
        }, {
            field : 'videoLink',
            title : '视频地址'
        }, {
            field : 'productName',
            title : '所属产品'
        }, {
            field : 'courseType',
            title : '视频类型',
            formatter: function (courseType) {
                var str;
               	if(courseType==0){
               		str = "单一课程";
               	}else if(courseType==1){
               		str = "课程包";
               	}
               	 return str;
               }
        }, {
            field : 'videoNum',
            title : '视频编号'
        }, {
            field : 'coursePic',
            title : '视频图片',
            formatter : function (value, row, index) {
            	if(value==""){
            		return "";
            	}
                return "<img style='width: 50px;height: 50px;' src='"+value+"'>"
            }
        }, {
            field : 'courseComment',
            title : '课程简介'
        }, {
            field : 'enable',
            title : '是否可用',
            formatter: function (courseType) {
                var str;
               	if(courseType==0){
               		str = "否";
               	}else if(courseType==1){
               		str = "是";
               	}
               	 return str;
               }
        }
    ]
    };
    $table = tableService.init(tableObj);

    $btnAdd.click(function() {
        modalUtil.open($('#create_modal'), "创建", $create_form, true, null, null);
    });

    var validatorForm = $create_form.validate({
        rules : {
        	"videoName" : {
                required: true,
            },
            "videoLink" : {
                required: true,
            },
            "productId" : {
                required: true,
            },
            "courseType" : {
                required: true,
            },
            "videoNum" : {
                required: true,
            },
            "coursePic" : {
                required: false,
            },
            "courseComment" : {
                required: false,
            }
        },
        messages : {
        	"videoName" : {
                required: '请输入视频名称'
            },
            "videoLink" : {
                required: '请上传视频'
            },
            "productId" : {
                required: "请输入所属产品"
            },
            "courseType" : {
                required: "请输入视频类型"
            },
            "videoNum" : {
                required: "请输入视频编号"
            },
            "coursePic" : {
                required: "请上传视频图片"
            },
            "courseComment" : {
                required: "请输入视频简介"
            }
        }
    });

    var validatorUpdateForm = $update_form.validate({
        rules : {
        	"videoName" : {
                required: true,
            },
            "videoLink" : {
                required: true,
            },
            "videoNum" : {
                required: true,
            },
            "coursePic" : {
                required: false,
            },
            "courseComment" : {
                required: false,
            },
            "enable" : {
                required: true,
            }
        },
        messages : {
        	"videoName" : {
                required: '请输入视频名称'
            },
            "videoLink" : {
                required: '请上传视频'
            },
            "videoNum" : {
                required: "请输入视频编号"
            },
            "coursePic" : {
                required: "请上传视频图片"
            },
            "courseComment" : {
                required: "请输入视频简介"
            },
            "enable" : {
                required: "请输入是否可用"
            }
        }
    });

    $btnSave.on("click", function(){

        if(!validateFile("videoLink_create_hidden")){
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
        if(!validateFile("videoLink_update_hidden")){
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

    function validateFile(v1){
        return $('#'+v1).val() != null && $('#'+v1).val() != '';
    }

    $btnModify.on("click", function(){
        var row = tableService.getSelections("更新", $table, false);
        row && modalUtil.open($('#update_modal'), "更新", $update_form, true, utils.getInfo, {id:row.id, url: urlDetail, form: "update_form", btn:"btnModify"});
    });

    uploadUtil.init({ctrlName:"videoLink_create", uploadUrl: config.api.videoUpload, fileType:['avi','mp4', 'rmvb', 'mov','flv','wmv'], maxFileCount: 1, drop:false, autoUpload: true}, {},uploadedFile, {field:"videoLink_create"});
    uploadUtil.init({ctrlName:"videoLink_update", uploadUrl: config.api.videoUpload, fileType:['avi','mp4', 'rmvb', 'mov','flv','wmv'], maxFileCount: 1, drop:false, autoUpload: true}, {},uploadedFile, {field:"videoLink_update"});
    uploadUtil.init({ctrlName:"coursePic_create", uploadUrl: config.api.imageUpload, fileType:['jpg','jpeg', 'gif', 'png'], maxFileCount: 1, drop:false, autoUpload: true}, {},uploadedFile, {field:"coursePic_create"});
    uploadUtil.init({ctrlName:"coursePic_update", uploadUrl: config.api.imageUpload, fileType:['jpg','jpeg', 'gif', 'png'], maxFileCount: 1, drop:false, autoUpload: true}, {},uploadedFile, {field:"coursePic_update"});
   
    function uploadedFile(arg, resp){
        if(resp.data.code == 200){
            $('#' + arg.field + "_hidden").val(resp.data.data.object);
        }
    }
    
    $("#courseType_create").on("change",function(){
    	$("#productId_create").empty();
        if($("option:selected",this).val()==0){
        	  $.ajax({ 
        	    	type:"GET", 
        	    	cache:false, 
        	    	url : "/user/product/list", 
        	    	async:true, 
        	    	data:{
        	    		"pageNo":1,
        	    		"pageSize":1000000,
        	    		"productType":3
        	    	},
        	    	success : function(data){
        	    	var obj = data.data;
        	    	var list = obj.list;
        	    	$.each(list, function (i) {
        	    	$('#productId_create.form-control').append("<option value=" + list[i].id + ">" + list[i].productName + "</option>");
        	    	});
        	    	}
        	    	});
        }else if($("option:selected",this).val()==1){
        	$("#productId_create").empty();
        	$.ajax({ 
    	    	type:"GET", 
    	    	cache:false, 
    	    	url : "/user/product/list", 
    	    	async:true, 
    	    	data:{
    	    		"pageNo":1,
    	    		"pageSize":1000000,
    	    		"productType":4
    	    	},
    	    	success : function(data){
    	    	var obj = data.data;
    	    	var list = obj.list;
    	    	$.each(list, function (i) {
    	    	$('#productId_create.form-control').append("<option value=" + list[i].id + ">" + list[i].productName + "</option>");
    	    	});
    	    	}
    	    	});
        }else if($("option:selected",this).val()==9){
        	$("#productId_create").empty();
        }
    });
    
    $("#courseType_update").on("change",function(){
    	$('#productId_update').removeAttr("disabled");
        if($("option:selected",this).val()==0){
        	$("#productId_update").empty();
        	  $.ajax({ 
        	    	type:"GET", 
        	    	cache:false, 
        	    	url : "/user/product/list", 
        	    	async:true, 
        	    	data:{
        	    		"pageNo":1,
        	    		"pageSize":1000000,
        	    		"productType":3
        	    	},
        	    	success : function(data){
        	    	var obj = data.data;
        	    	var list = obj.list;
        	    	$.each(list, function (i) {
        	    	$('#productId_update.form-control').append("<option value=" + list[i].id + ">" + list[i].productName + "</option>");
        	    	});
        	    	}
        	    	});
        }else if($("option:selected",this).val()==1){
        	$("#productId_update").empty();
        	$.ajax({ 
    	    	type:"GET", 
    	    	cache:false, 
    	    	url : "/user/product/list", 
    	    	async:true, 
    	    	data:{
    	    		"pageNo":1,
    	    		"pageSize":1000000,
    	    		"productType":4
    	    	},
    	    	success : function(data){
    	    	var obj = data.data;
    	    	var list = obj.list;
    	    	$.each(list, function (i) {
    	    	$('#productId_update.form-control').append("<option value=" + list[i].id + ">" + list[i].productName + "</option>");
    	    	});
    	    	}
    	    	});
        }
    });
//    var productType;
//    alert($("#courseType_update").val());
//    if($("#courseType_update").val()==1){
//    	productType = 4;
//    }else if($("#courseType_update").val()==0){
//    	productType = 3;
//    }
    $.ajax({ 
    	type:"GET", 
    	cache:false, 
    	url : "/user/product/list", 
    	async:true, 
    	data:{
    		"pageNo":1,
    		"pageSize":1000000
    	},
    	success : function(data){
    	var obj = data.data;
    	var list = obj.list;
    	$.each(list, function (i) {
    	$('#productId_update.form-control').append("<option value=" + list[i].id + ">" + list[i].productName + "</option>");
    	});
    	}
    	});
});