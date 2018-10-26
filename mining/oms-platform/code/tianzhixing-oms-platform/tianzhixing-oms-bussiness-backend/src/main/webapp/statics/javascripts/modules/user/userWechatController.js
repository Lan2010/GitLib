$(function(){

    var	$beginTime = $("#time_begin"),
        $endTime = $("#time_end"),
        $btnSearch = $("#btnSearch"),
        $table = $("#table"),
        $nickName = $("#nickName"),
        url = "user/userWechat/",
        urlList = url + "list";

    var queryParams = function(params) {
        return {
            pageSize: params.limit,
            pageNo: params.pageNumber,
            beginTime : $beginTime.val(),
            endTime  : $endTime.val(),
            nickname:$nickName.val()
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
            field : 'userId',
            title : 'userID'
        }, {
            field : 'mpOpenId',
            title : '微信公众号openId'
        }, {
            field : 'nickname',
            title : '昵称'
        }, {
            field : 'phoneNum',
            title : '电话号码'
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
            field : 'province',
            title : '省份'
        }, {
            field : 'city',
            title : '城市'
        }, {
            field : 'country',
            title : '国家'
        }, {
            field : 'headimgUrl',
            title : '头像url'
        }, {
            field : 'language',
            title : '语言'
        }, {
            field : 'followDt',
            title : '关注时间',
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
            field : 'unionId',
            title : 'unionId'
        }, {
            field : 'miniappOpenId',
            title : '微信小程序openId'
        }
    ]
    };
    $table = tableService.init(tableObj);

    $btnSearch.on("click", function(){
        tableService.refresh($table);
    });

});