$(function(){

    var	$create_form = $("#create_form"),
        $update_form = $("#update_form"),
        $btnAdd = $("#btnAdd"),
        $btnModify = $("#btnModify"),
        $btnReply = $("#btnReply"),
        $btnSave = $("#btnSave"),
        $btnUpdate = $("#btnUpdate"),
        $table = $("#table"),
        url = "user/userTopic/",
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
            field : 'userName',
            title : '用户名'
        }, {
            field : 'tzxUserName',
            title : '天之星客服'
        }, {
            field : 'type',
            title : '消息类型',
            formatter: function (isExpired) {
                var str;
               	if(isExpired==1){
               		str = "官方推广消息";
               	}
               	 return str;
               }
        }, {
            field : 'title',
            title : '消息主题'
        }, {
            field : 'content',
            title : '官方推广消息内容'
        }, {
            field : 'dt',
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
            field : 'delStatus',
            title : '删除状态',
            formatter: function (isExpired) {
                var str;
               	if(isExpired==0){
               		str = "已删除";
               	}else if(isExpired==1){
               		str = "正常";
               	}
               	 return str;
               }
        }, {
            field : 'repStatus',
            title : '回复状态 ',
            formatter: function (isExpired) {
                var str;
               	if(isExpired==0){
               		str = "未回复";
               	}else if(isExpired==1){
               		str = "已回复";
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
            "userId" : {
                required: true,
            },
            "type" : {
                required: true,
            },
            "title" : {
                required: true,
            },
            "content" : {
                required: true,
            },
            "imgUrl" : {
                required: false,
            }
        },
        messages : {
            "userId" : {
                required: '请输入userId'
            },
            "type" : {
                required: "请输入消息类型"
            },
            "title" : {
                required: "请输入消息主题"
            },
            "content" : {
                required: "请输入官方推广消息内容"
            },
            "imgUrl" : {
                required: "请输入图片url"
            }
        }
    });

    var validatorUpdateForm = $update_form.validate({
        rules : {
            "type" : {
                required: true,
            },
            "title" : {
                required: true,
            },
            "content" : {
                required: true,
            },
            "imgUrl" : {
                required: false,
            },
            "delStatus" : {
                required: true,
            }
        },
        messages : {
            "type" : {
                required: "请输入消息类型"
            },
            "title" : {
                required: "请输入消息主题"
            },
            "content" : {
                required: "请输入官方推广消息内容"
            },
            "imgUrl" : {
                required: "请输入图片url"
            },
            "delStatus" : {
                required: "请输入删除状态"
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
        row && modalUtil.open($('#update_modal'), "更新", $update_form, true, utils.getInfo, {id:row.id, url: urlDetail, form: "update_form", btn:"btnModify"});
    });
    
    $btnReply.on("click", function(){
        	$("#reply").show();
        	getList();
    });
    
    function uploadedFile(arg, resp){
        if(resp.data.code == 200){
            $('#' + arg.field + "_hidden").val(resp.data.data.object);
        }
    }

    $("#closeB").on("click", function(){
    	$("#reply").hide();
    });
    $("#btnC").on("click", function(){
    	$("#reply").hide();
    });
    
    uploadUtil.init({ctrlName:"imgUrl_create", uploadUrl: config.api.imageUpload, fileType:['jpg','jpeg', 'gif', 'png'], maxFileCount: 1, drop:false, autoUpload: true}, {},uploadedFile, {field:"imgUrl_create"});
    uploadUtil.init({ctrlName:"imgUrl_update", uploadUrl: config.api.imageUpload, fileType:['jpg','jpeg', 'gif', 'png'], maxFileCount: 1, drop:false, autoUpload: true}, {},uploadedFile, {field:"imgUrl_update"});
   
    function getList(){
    	 var row = tableService.getSelections("消息记录", $table, false);
    	 $.ajax({ 
         	type:"GET", 
         	cache:false, 
         	url : "/user/userMessage/getList", 
         	async:true, 
         	data:{
         		"id":row.id
         	},
         	success : function(data){
         	var obj = data.data;
         	var list = obj.list;
         	$("#replyList").empty();
         	$.each(list, function (i) {
         		$("#topicId").val(list[i].topicId);
         		if(list[i].type==1){
         			$('#replyList').append("<div class='btalk'><span>" + list[i].tzxUserName + " :" + list[i].content + "&nbsp;&nbsp;" + list[i].dt + "</span></div>");
         		}else if(list[i].type==2){
         			$('#replyList').append("<div class='atalk'><span>" + list[i].userName + " :" + list[i].content + "&nbsp;&nbsp;" + list[i].dt + "</span></div>");
         		}
         	});
         	}
         	});
    }   
    
    $("#btnAnswer").on("click", function(){
   	 $.ajax({ 
      	type:"GET", 
      	cache:false, 
      	url : "/user/userMessage/add", 
      	async:true, 
      	data:{
      		"topicId":$("#topicId").val(),
      		"content":$("#content").val()
      	},
      	success : function(data){
      		if(data.code==200){
      			layer.alert("操作成功！", {icon: 6}, function(){document.location.reload();});
      			//getList();
      			//$("#content").val("")
      		}else{
      			layer.alert("回复异常")
      		}
      	}
      	});
    });
    
    $.ajax({ 
    	type:"GET", 
    	cache:false, 
    	url : "/user/user/list", 
    	async:true, 
    	data:{
    		"pageNo":1,
    		"pageSize":10000000
    	},
    	success : function(data){
    	var obj = data.data;
    	var list = obj.list;
    	$.each(list, function (i) {
    	$('#userId_create.form-control').append("<option value=" + list[i].id + ">" + list[i].nickname + "</option>");
    	});
    	}
    	});
});