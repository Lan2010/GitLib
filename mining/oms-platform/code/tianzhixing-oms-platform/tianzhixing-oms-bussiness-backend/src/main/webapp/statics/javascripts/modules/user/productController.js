$(function(){

	var $create_form = $("#create_form"),
        $update_form = $("#update_form"),
        $btnAdd = $("#btnAdd"),
        $btnModify = $("#btnModify"),
        $btnSave = $("#btnSave"),
        $btnUpdate = $("#btnUpdate"),
        $table = $("#table"),
        url = "user/product/",
        urlList = url + "list",
        urlCreate = url + "add",
        urlDetail = url + "detail",
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
            field : 'productName',
            title : '产品名称'
        }, {
            field : 'price',
            title : '产品套餐价格'
        }, {
            field : 'promotionPrice',
            title : '产品套餐促销价'
        },{
            field : 'productStatus',
            title : '产品状态',
            formatter: function (productStatus) {
            var str;
           	if(productStatus==0){
           		str = "未发布";
           	}else if(productStatus==1){
           		str = "上架";
           	}else if(productStatus==2){
           		str = "下架";
           	}else if(productStatus==3){
           		str = "挂起";
           	}
           	 return str;
           }
        },{
            field : 'sellDt',
            title : '产品上架时间',
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
            field : 'lastModifyDt',
            title : '上次修改时间',
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
            field : 'noSellDt',
            title : '产品下架时间',
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
            field : 'sampleTubeCnt',
            title : '该产品套餐所需要的唾液盒数量'
        }, {
            field : 'youzanItemId',
            title : '有赞商品ID'
        }, {
            field : 'productType',
            title : '产品类型',
            formatter: function (productType) {
                var str;
               	if(productType==0){
               		str = "基因检测";
               	}else if(productType==1){
               		str = "充电宝";
               	}else if(productType==2){
               		str = "收集";
               	}else if(productType==3){
               		str = "单一课程";
               	}else if(productType==4){
               		str = "课程包";
               	}
               	 return str;
               }
        }, {
            field : 'productCreatePlatform',
            title : '产品创建类型',
            formatter: function (productType) {
                var str;
               	if(productType==1){
               		str = "有赞商城";
               	}
               	 return str;
               }
        }, {
            field : 'productComment',
            title : '产品简介'
        }
    ]
    };
    $table = tableService.init(tableObj);

    $btnAdd.click(function() {
        modalUtil.open($('#create_modal'), "创建", $create_form, true, null, null);
    });

    var validatorForm = $create_form.validate({
        rules : {
            "productName" : {
                required: true,
                maxlength:32
            },"price" : {
                required: true
            },"promotionPrice" : {
            	required: true
            },"productStatus" : {
                required: true
            },"sampleTubeCnt" : {
            	required: false
            },"productType" : {
                required: true
            },"productCreatePlatform" : {
                required: true
            },"youzanItemId" : {
                required: true
            },"productPic" : {
                required: true
            },"productComment" : {
                required: true
            }
        },
        messages : {
            "productName" : {
                required: '请输入名称',
                maxlength:'请不要超过32个字符'
            }
            ,"price" : {
                required: "请输入产品套餐价格"
            }
            ,"promotionPrice" : {
                required: "请输入产品套餐促销价"
            }
            ,"productStatus" : {
                required: "请输入产品状态"
            }
            ,"sampleTubeCnt" : {
                required: "请输入该产品套餐所需要的唾液盒数量（仅试用于基因产品）"
            }
            ,"productType" : {
                required: "请输入产品类型"
            }
            ,"productCreatePlatform" : {
                required: "请输入产品创建类型"
            }
            ,"youzanItemId" : {
                required: "请输入有赞商品ID"
            }
            ,"productPic" : {
                required: "请输入产品图片"
            } 
            ,"productComment" : {
                required: "请输入产品简介"
            }
        }
    });

    var validatorUpdateForm = $update_form.validate({
        rules : {
        	 "productName" : {
                 required: true,
                 maxlength:32
             },"price" : {
                 required: true
             },"promotionPrice" : {
            	 required: true 
             },"productStatus" : {
                 required: true
             },"sellDt" : {
                 required: true
             },"noSellDt" : {
                 required: true
             },"sampleTubeCnt" : {
            	 required: false
             },"productType" : {
                 required: true
             },"productCreatePlatform" : {
                 required: true
             }
        },
        messages : {
        	 "productName" : {
                 required: '请输入名称',
                 maxlength:'请不要超过32个字符'
             },"price" : {
                 required: "请输入产品套餐价格"
             }
             ,"promotionPrice" : {
                 required: "请输入产品套餐促销价"
             }
             ,"productStatus" : {
                 required: "请输入产品状态"
             }
             ,"sellDt" : {
                 required: "请输入产品上架时间"
             }
             ,"noSellDt" : {
                 required: "请输入产品下架时间"
             }
             ,"sampleTubeCnt" : {
                 required: "请输入该产品套餐所需要的唾液盒数量（仅试用于基因产品）"
             }
             ,"productType" : {
                 required: "请输入产品类型"
             }
             ,"productCreatePlatform" : {
                 required: "请输入产品创建类型"
             }
        }
    });

    $btnSave.on("click", function(){
    	if(!validateFile("productPic_create_hidden")){
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
    
    function validateFile(v1){
        return $('#'+v1).val() != null && $('#'+v1).val() != '';
    }
    
    uploadUtil.init({ctrlName:"productPic_create", uploadUrl: config.api.imageUpload, fileType:['jpg','jpeg', 'gif', 'png'], maxFileCount: 1, drop:false, autoUpload: true}, {},uploadedFile, {field:"productPic_create"});
    uploadUtil.init({ctrlName:"productPic_update", uploadUrl: config.api.imageUpload, fileType:['jpg','jpeg', 'gif', 'png'], maxFileCount: 1, drop:false, autoUpload: true}, {},uploadedFile, {field:"productPic_update"});

    function uploadedFile(arg, resp){
        if(resp.data.code == 200){
            $('#' + arg.field + "_hidden").val(resp.data.data.object);
        }
    }
    
    $("#productType_update").on("change",function(){
        if($("option:selected",this).val()==0){
            $("#sampleTubeCnt_update").show();
        }else{
            $("#sampleTubeCnt_update").hide();
            $("#sampleTubeCnt_update").val("0");
        }
    });
    $("#productType_create").on("change",function(){
        if($("option:selected",this).val()==0){
            $("#sampleTubeCnt_create").show();
        }else{
            $("#sampleTubeCnt_create").hide();
            $("#sampleTubeCnt_create").val("0");
        }
    });
   
    if($("#promotionCodeType_update").val()==0){
        $("#sampleTubeCnt_update").show();
    }else{
        $("#sampleTubeCnt_update").hide();
        $("#sampleTubeCnt_update").val("0");
    }
    
//    $.ajax({ 
//    	type:"GET", 
//    	cache:false, 
//    	url : "/user/product/youzanList", 
//    	async:true, 
//    	data:{
//    		"pageNo":1,
//    		"pageSize":100
//    	},
//    	success : function(data){
//    		var obj = $.parseJSON(data);
//    		var list = obj.data;
//    		$.each(list, function (i) {
//    			$('#youzanItemId_create.form-control').append("<option value=" + list[i].itemId + ">" + list[i].itemName + "</option>");
//    			});
//    		}
//    	});
    
//	$.ajax({ 
//    	type:"GET", 
//    	cache:false, 
//    	url : "/user/product/youzanList", 
//    	async:true, 
//    	data:{
//    		"pageNo":1,
//    		"pageSize":100
//    	},
//    	success : function(data){
//    		var obj = $.parseJSON(data);
//    		var list = obj.data;
//    		$('#youzanListTable').empty();
//    		$.each(list, function (i) {
//    			$('#youzanListTable').append("<TR><TD><input name='btSelect' type='checkbox'></TD><TD>"+(i+1)+"</TD><TD>"+list[i].itemId+"</TD><TD>"+list[i].itemName+"</TD></TR>");
//    			});
//    		}
//    	});
    
    $('#btnAddYouzan').on("click", function(){
    	$('#youzanList').show();
    });
    
    $('#closeB').on("click", function(){
    	$('#youzanList').hide();
    });
    
    $('#btnClose').on("click", function(){
    	$('#youzanList').hide();
    });
    
    $('#btnC').on("click", function(){
    	$('#youzanItemId_create').val($('input:checkbox:checked').val());
    	$('#youzanList').hide();
    });
    
    initStatistics(1);
});

function copyText(i){
	$('#youzanListTable').find('input[type=checkbox]').attr("checked", false);
	$('#'+i).prop('checked',true)
}


function initStatistics (pageNo){
	var pageSize = 10;
	$.ajax({ 
    	type:"GET", 
    	cache:false, 
    	url : "/user/product/youzanList", 
    	async:true, 
    	data:{
    		"pageNo":pageNo,
    		"pageSize":pageSize
    	},
    	success : function(data){
    		var obj = $.parseJSON(data);
    		var list = obj.data;
    		var totalCount = obj.total;
    		if(totalCount>0){
        		$("#pageDiv").attr("style","display:block;");
        	}
    		$('#youzanListTable').empty();
    		$.each(list, function (i) {
    			$('#youzanListTable').append("<TR><TD><input name='btS' id = '"+i+"' type='checkbox' onclick='copyText("+i+")' value='"+list[i].itemId+"'/></TD><TD>"+list[i].itemId+"</TD><TD>"+list[i].itemName+"</TD></TR>");
    			});
    		 document.getElementById("totalsSpan").innerHTML='总共 '+totalCount+'条记录';
             $("#totalCounts").val(totalCount);
             $("#pageNo").val(pageNo);
             goPage(pageNo,pageSize);
    		}
    	
    	});
}

function goPage(pno, pageSize) {
	var num = document.getElementById("totalCounts").value;
	var totalPage = 0;// 总页数
	// 总共分几页
	if (num / pageSize > parseInt(num / pageSize)) {
		totalPage = parseInt(num / pageSize) + 1;
	} else {
		totalPage = parseInt(num / pageSize);
	}
	var currentPage = pno;// 当前页数
	var tempStr = "";

	if (currentPage > 1) {
		tempStr += "<li class='page-first'><a href=\"javascript:void(0)\" onclick=\"initStatistics(" + (1) + ")\">«</a></li>";
		tempStr += "<li class='page-pre'><a href=\"javascript:void(0)\" onclick=\"initStatistics(" + (currentPage - 1) + ")\">‹</a></li>";
	} else {
		tempStr += "<li class='page-first'><a href=\"javascript:void(0)\">«</a></li>";
		tempStr += "<li class='page-pre'><a href=\"javascript:void(0)\">‹</a></li>";
	}

	for (var pageIndex = 1; pageIndex < totalPage + 1; pageIndex++) {
		tempStr += "<li class='page-number'><a href=\"javascript:void(0)\" onclick=\"initStatistics(" + pageIndex + ")\">" + pageIndex + "</a></li>";
	}

	if (currentPage < totalPage) {
		tempStr += "</li><li class='page-next'><a href=\"javascript:void(0)\" onclick=\"initStatistics(" + (currentPage + 1) + ")\" >›</a></li>";
		tempStr += "<li class='page-last'><a href=\"javascript:void(0)\" onclick=\"initStatistics(" + (totalPage) + ")\">»</a></li>";
	} else {
		tempStr += "</li><li class='page-next'><a href=\"javascript:void(0)\">›</a></li>";
		tempStr += "<li class='page-last'><a href=\"javascript:void(0)\">»</a></li>";
	}

	document.getElementById("pageLine").innerHTML = tempStr;
}

