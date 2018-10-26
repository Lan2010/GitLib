$(function(){

    var	$beginTime = $("#time_begin"),
        $endTime = $("#time_end"),
        $btnSearch = $("#btnSearch"),
        $update_form = $("#update_form"),
        $userStarpointAccount_form = $("#userStarpointAccount_form"),
        $userWechat_form = $("#userWechat_form"),
        $btnModify = $("#btnModify"),
        $btnUserStarpointAccount = $("#btnUserStarpointAccount"),
        $btnUserWechat = $("#btnUserWechat"),
        $btnUpdate = $("#btnUpdate"),
        $table = $("#table"),
        $nickName = $("#nickName"),
        $userChannel = $("#userChannel"),
        $regPlatform = $("#regPlatform"),
        url = "user/user/",
        urlList = url + "list",
        urlDetail = url + "detail",
        urlUpdateInfo = url + "update";

    var queryParams = function(params) {
        return {
            pageSize: params.limit,
            pageNo: params.pageNumber,
            beginTime : $beginTime.val(),
            endTime  : $endTime.val(),
            nickname:$nickName.val(),
            userChannel:$userChannel.val(),
            regPlatform:$regPlatform.val()
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
            field : 'phoneNum',
            title : '联系电话'
        }, {
            field : 'email',
            title : '邮箱'
        }, {
            field : 'gender',
            title : '性别',
            formatter: function (gender) {
                var str;
               	if(gender==0){
               		str = "未知";
               	}else if(gender==1){
               		str = "男";
               	}else if(gender==2){
               		str = "女";
               	}
               	 return str;
               }
        }, {
            field : 'birthday',
            title : '生日'
        }, {
            field : 'createdt',
            title : '创建日期',
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
            field : 'mpOpenId',
            title : '微信公众号openId'
        }, {
            field : 'nickname',
            title : '昵称'
        }, {
            field : 'unionId',
            title : '微信union_id'
        }, {
            field : 'bonus',
            title : '推荐奖'
        }, {
            field : 'regPlatform',
            title : '注册平台'
        }, {
            field : 'userChannel',
            title : '注册渠道'
        }, {
            field : 'miniappOpenId',
            title : '微信小程序openId'
        }, {
            field : 'youzanUserId',
            title : '有赞对应userid'
        }, {
            field : 'youzanFansId',
            title : '有赞对应的粉丝id'
        }
    ]
    };
    $table = tableService.init(tableObj);

    $btnSearch.on("click", function(){
        tableService.refresh($table);
    });

    var validatorUpdateForm = $update_form.validate({
        rules : {
            "phoneNum" : {
                required: false,
            },
            "passwd" : {
                required: true,
            },
            "email" : {
                required: false,
            },
            "gender" : {
                required: false,
            },
            "birthday" : {
                required: false,
            },
            "mpOpenId" : {
                required: false,
            },
            "nickname":{
                required: false,
            },
            "headimgUrl":{
                required: false,
            },
            "unionId":{
                required: true,
            },
            "bonus":{
                required: false,
            },
            "regPlatform":{
                required: true,
            },
            "userChannel":{
                required: true,
            },
            "createdt" : {
                required: false,
            },
            "miniappOpenId" : {
                required: false,
            },
            "youzanUserId" : {
                required: false,
            },
            "youzanFansId" : {
                required: false,
            }
        },
        messages : {
            "phoneNum" : {
                required: '请输入联系电话',
            },
            "passwd" : {
                required: "请输入密码",
            },
            "email" : {
                required: "请输入邮箱",
            },
            "gender" : {
                required: "请输入性别",
            },
            "birthday" : {
                required: '请输入生日'
            },
            "mpOpenId" : {
                required: '请输入mpOpenId'
            },
            "nickname":{
                required: '请输入昵称',
            },
            "headimgUrl":{
                required: "请输入头像url",
            },
            "unionId":{
                required: "请输入微信union_id",
            },
            "bonus":{
                required: "请输入推荐奖",
            },
            "regPlatform":{
                required: '请输入注册平台',
            },
            "userChannel":{
                required: '请输入注册渠道',
            },
            "createdt" : {
                required: "请输入创建时间 "
            },
            "miniappOpenId" : {
                required: "请输入miniappOpenId"
            },
            "youzanUserId" : {
                required: "请输入youzanUserId"
            },
            "youzanFansId" : {
                required: "请输入youzanFansId"
            }
        }
    });

    $btnUpdate.on("click", function(){
    	
    	 if(!validateFile("headimgUrl_update_hidden")){
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


    $btnModify.on("click", function(){
        var row = tableService.getSelections("更新", $table, false);
        row && modalUtil.open($('#update_modal'), "更新", $update_form, true, utils.getInfo, {id:row.id, url: urlDetail, form: "update_form", btn:"btnModify"});
    });
    
    $btnUserStarpointAccount.on("click", function(){
        var row = tableService.getSelections("用户星星数据信息", $table, false);
        row && modalUtil.open($('#userStarpointAccount'), "用户星星数据信息", $userStarpointAccount_form, true, utils.getInfo, {id:row.id, url: "user/userStarpointAccount/detail", form: "userStarpointAccount_form", btn:"btnUserStarpointAccount"});
    });
    
    $btnUserWechat.on("click", function(){
        var row = tableService.getSelections("用户微信数据信息", $table, false);
        row && modalUtil.open($('#userWechat'), "用户微信数据信息", $userWechat_form, true, utils.getInfo, {id:row.id, url: "user/userWechat/getDetailByUserId", form: "userWechat_form", btn:"btnUserWechat"});
    });
    
    function validateFile(v1){
        return $('#'+v1).val() != null && $('#'+v1).val() != '';
    }
    
    uploadUtil.init({ctrlName:"headimgUrl_update", uploadUrl: config.api.imageUpload, fileType:['jpg','jpeg', 'gif', 'png'], maxFileCount: 1, drop:false, autoUpload: true}, {},uploadedFile, {field:"headimgUrl_update"});

    function uploadedFile(arg, resp){
        if(resp.data.code == 200){
            $('#' + arg.field + "_hidden").val(resp.data.data.object);
        }
    }

});