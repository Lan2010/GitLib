$(function(){

    var	$btnSearch = $("#btnSearch"),
    	$forUserId = $("#forUserId"),
    	$create_form = $("#create_form"),
        $update_form = $("#update_form"),
        $btnAdd = $("#btnAdd"),
        $btnModify = $("#btnModify"),
        $btnSave = $("#btnSave"),
        $btnUpdate = $("#btnUpdate"),
        $table = $("#table"),
        url = "user/userPromotionCode/",
        urlList = url + "list",
        urlDetail = url + "detail",
        urlCreate = url + "add",
        urlUpdateInfo = url + "update";

    var queryParams = function(params) {
        return {
            pageSize: params.limit,
            pageNo: params.pageNumber,
            forUserId:$forUserId.val()
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
            field : 'promotionCode',
            title : '优惠券'
        }, {
            field : 'promotionRules',
            title : '优惠规则'
        }, {
            field : 'status',
            title : '状态',
            formatter: function (status) {
                var str;
               	if(status==0){
               		str = "无效";
               	}else if(status==1){
               		str = "有效";
               	}else if(status==2){
               		str = "已使用";
               	}
               	 return str;
               }
        }, {
            field : 'beginDt',
            title : '最早某日后有效',
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
            field : 'expiryDt',
            title : '最迟某日前有效',
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
            field : 'createDt',
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
            field : 'forUserId',
            title : '优惠券指定人id'
        }, {
            field : 'forOpenId',
            title : '优惠券指定人openId'
        }, {
            field : 'productName',
            title : '优惠券指定产品'
        }, {
            field : 'voucherAmount',
            title : '抵用金额'
        }, {
            field : 'discountRate',
            title : '折扣率'
        }, {
            field : 'promotionCodeType',
            title : '优惠券类型',
            formatter: function (promotionCodeType) {
                var str;
               	if(promotionCodeType==1){
               		str = "抵扣券";
               	}else if(promotionCodeType==2){
               		str = "折扣券";
               	}
               	 return str;
               }
        }, {
            field : 'allowComposition',
            title : '是否允许叠加',
            formatter: function (allowComposition) {
                var str;
               	if(allowComposition==0){
               		str = "否";
               	}else if(allowComposition==1){
               		str = "是";
               	}
               	 return str;
               }
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
            "promotionCode" : {
                required: true,
            },
            "promotionRules" : {
                required: true,
            },
            "beginDt" : {
                required: true,
            },
            "expiryDt" : {
                required: true,
            },
            "forUserId" : {
                required: true,
            },
            "forOpenId" : {
                required: false,
            },
            "forProductionId" : {
                required: true,
            },
            "voucherAmount" : {
                required: false,
            },
            "discountRate" : {
                required: false,
            },
            "promotionCodeType" : {
                required: true,
            },
            "allowComposition" : {
                required: true,
            }
        },
        messages : {
            "promotionCode" : {
                required: '请输入优惠券名称'
            },
            "promotionRules" : {
                required: "请输入优惠规则"
            },
            "beginDt" : {
                required: "请输入最早某日后有效"
            },
            "expiryDt" : {
                required: "请输入最迟某日前有效"
            },
            "forUserId" : {
                required: "请输入优惠券指定人userId"
            },
            "forOpenId" : {
                required: "请输入优惠券指定人openId"
            },
            "forProductionId" : {
                required: "请输入优惠券指定为产品"
            },
            "voucherAmount" : {
                required: "请输入抵用金额"
            },
            "discountRate" : {
                required: "请输入折扣率：折让后的价格=原价格*（1-折扣率）"
            },
            "promotionCodeType" : {
                required: "优惠券类型"
            },
            "allowComposition" : {
                required: "请输入是否允许叠加"
            }
        }
    });

    var validatorUpdateForm = $update_form.validate({
        rules : {
            "promotionCode" : {
                required: true,
            },
            "promotionRules" : {
                required: true,
            },
            "status" : {
                required: true,
            },
            "beginDt" : {
                required: true,
            },
            "expiryDt" : {
                required: true,
            },
            "forUserId" : {
                required: true,
            },
            "forOpenId" : {
                required: false,
            },
            "forProductionId" : {
                required: true,
            },
            "voucherAmount" : {
                required: false,
            },
            "discountRate" : {
                required: false,
            },
            "promotionCodeType" : {
                required: true,
            },
            "allowComposition" : {
                required: true,
            },
            "createDt" : {
                required: false,
            }
        },
        messages : {
            "promotionCode" : {
                required: '请输入优惠券名称'
            },
            "promotionRules" : {
                required: "请输入优惠规则"
            },
            "status" : {
                required: "请输入状态"
            },
            "beginDt" : {
                required: "请输入最早某日后有效"
            },
            "expiryDt" : {
                required: "请输入最迟某日前有效"
            },
            "forUserId" : {
                required: "请输入优惠券指定人userId"
            },
            "forOpenId" : {
                required: "请输入优惠券指定人openId"
            },
            "forProductionId" : {
                required: "请输入优惠券指定为产品"
            },
            "voucherAmount" : {
                required: "请输入抵用金额"
            },
            "discountRate" : {
                required: "请输入折扣率：折让后的价格=原价格*（1-折扣率）"
            },
            "promotionCodeType" : {
                required: "优惠券类型"
            },
            "allowComposition" : {
                required: "请输入是否允许叠加"
            },
            "createDt" : {
                required: "请输入创建时间 "
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
    	$('#forProductionId_create.form-control').append("<option value=" + list[i].id + ">" + list[i].productName + "</option>");
    	});
    	}
    	});
    
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
    	$('#forProductionId_update.form-control').append("<option value=" + list[i].id + ">" + list[i].productName + "</option>");
    	});
    	}
    	});
    
    $("#promotionCodeType_update").on("change",function(){
        if($("option:selected",this).val()==1){
            $("#voucherAmount_update").show();
            $("#discountRate_update").hide();
            $("#discountRate_update").val("");
        }else if($("option:selected",this).val()==2){
        	$("#voucherAmount_update").hide();
            $("#discountRate_update").show();
            $("#voucherAmount_update").val("");
        }
    });
    
    $("#promotionCodeType_create").on("change",function(){
        if($("option:selected",this).val()==1){
        	$("#voucherAmount_create").show();
            $("#discountRate_create").hide();
            $("#discountRate_create").val("");
        }else if($("option:selected",this).val()==2){
        	$("#voucherAmount_create").hide();
            $("#discountRate_create").show();
            $("#voucherAmount_create").val("");
        }
    });
    
    if($("#promotionCodeType_update").val()==1){
        $("#voucherAmount_update").show();
        $("#discountRate_update").hide();
        $("#discountRate_update").val("");
    }else if($("#promotionCodeType_update").val()==2){
    	$("#voucherAmount_update").hide();
        $("#discountRate_update").show();
        $("#voucherAmount_update").val("");
    }
});