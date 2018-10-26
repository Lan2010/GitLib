$(function(){
	message();
	window.setInterval(message, 300000);
});

function message(){
	var isRead = false,
		pageNo = 1,
		pageSize = 6;

	$('#messageNum').removeClass('bounceInDown');

	// 查询 用户 未读消息
	$.ajax({
		url : 'message/list',
		type : 'get',
		data : {isRead:isRead, pageNo:pageNo, pageSize:pageSize},
		success : function(rep) {
			if(rep.code === 200 && rep.data.total != 0){
				var count = $('#messageNum').text();
				if(rep.data.total != parseInt(count)){
					getMessage(rep.data.list,rep.data.total);
				}
			}else {
				getMessage([], 0);
			}
		},
		error : function() {
		}
	});
	// 获取消息
	function getMessage(msgList, total){
		//if(!msgList) {
		//	var defaultLi = '<li>'+
		//		'<div class="text-center link-block">'+
		//		'<button class="btn btn-default J_menuItem A_Item" href="#" data-tabTitle="消息" id="btnMessage">'+
		//		'<i class="fa fa-envelope"></i> 暂无消息'+
		//		'</button>'+
		//		'</div>'+
		//		'<li>';
        //
		//	$('.dropdown-messages').append(defaultLi);
        //
		//	return;
		//}

		msgList.reverse(); // 反转循序

		$('.dropdown-messages').empty();
		$('#messageNum').toggleClass('bounceInDown').text(total >= 100 ? '99+' : total);
		$('#btnMessage').text('查看所有消息');

		if(msgList.length > 0){
			for(var i=0; i<msgList.length;i++){
				if(i <= 4){
					context = msgList[i].title,
					id = msgList[i].id,
					sendTime = moment(msgList[i].createTime).format('YYYY-MM-DD HH:mm:ss');
					msgItem = '<li class="m-t-xs" id="li_msg_'+id+'">' +
					'<div class="dropdown-messages-box">'+
					'<div class="media-body">'+
					'<span id="context">'+ context +'</span><br/>'+
					'<small class="text-muted" id="sendTime">'+ sendTime +'</small>'+
					'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <small class="text-muted"><a href="javascript:void(0);" onclick="readMsg('+id+')">标识已读</a></small>'+
					'</div>'+
					'</div>'+
					'</li>';

					$('.dropdown-messages').append(msgItem);
					$('.dropdown-messages').append('<li class="divider"></li>');
				}
			}
			var more = '<li>'+
				'<div class="text-center link-block">'+
				'<button class="btn btn-primary J_menuItem A_Item" href="/message/index" data-tabTitle="通知消息" id="btnMessage">'+
				'<i class="fa fa-envelope"></i> 查看所有消息'+
				'</button>'+
				'</div>'+
				'<li>';

			$('.dropdown-messages').append(more);
		} else {
			var defaultLi = '<li>'+
				'<div class="text-center link-block">'+
				'<button class="btn btn-default J_menuItem A_Item" data-tabTitle="通知消息">'+
				'<i class="fa fa-envelope"></i> 暂无消息'+
				'</button>'+
				'</div>'+
				'<li>';

			$('.dropdown-messages').append(defaultLi);
		}
	}
}
	
// 获取更多消息点击事件
$(document).delegate("#btnMessage", "click", function(){
	aTab(this);
});

function readMsg(id){
	ajaxUtil.ajax('message/read', {id: id, toRead : true}, "get", function (resp) {
		if (resp.code === 200) {
			var count = $('#messageNum').text();
			count = parseInt(count) - 1;
			$('#messageNum').text(count >= 100 ? '99+' : count);
			$('#li_msg_' + id).remove();
		} else {
			layer.msg(resp.msg || "操作失败！", {icon: 5});
		}
	}, function(msg){

	}, null, {btn: $(this)});
}
