/**
 * 首页用户设置
 */
$(function (){
	var $form = $('#updatePasswordForm'), btnSubmit = $('#submitUpdatePassword');
	
	$("#btnloginUser").click(function() {

		ajaxUtil.ajax('users/info', null, "GET", function (rep){
			if(rep.code === 200 ){
				// 打开修改密码 弹出层
				$('#updatePasswordModal').modal({
					backdrop : 'static',
					keyboard : false
				});

			}else{
				layer.alert(rep.msg, {
					icon : 5
				});
			}
		});
	});


	/**
	 * 提交
	 */
	btnSubmit.click(function (){
		if(validatorSave()){
			ajaxUtil.ajax('users/update/password', {
				oldPassword:$('#oldPassword').val(),
				newPassword:$('#newPassword').val(),
				enterPassword:$('#enterPassword').val()
			}, "POST", function (rep){
				if(rep.code === 200){
					layer.alert('密码修改成功，请重新登录！', {
						icon : 6
					}, function (){
						$('#updatePasswordModal').modal('hide');
						logout();
					});
				}else {
					layer.alert(rep.msg, {
						icon : 5
					});
				}
			});
		}
	}, null, null, btnSubmit);
	
	
	/**
	 * 表单验证方法
	 */
	function validatorSave() {
		return $form.validate({
			rules : {
				"oldPassword" : {
					required : true,
					maxlength: 32
				},
				"newPassword" : {
					required : true,
					maxlength: 32
				},
				"enterPassword" : {
					required : true,
					equalTo : "#newPassword"
				}
			},
			messages : {
				"oldPassword" : {
					required : "请输入旧密码",
					maxlength: "密码长度请保持在32位以内"
				},
				"newPassword" : {
					required : "请输入新密码",
					maxlength: "密码长度请保持在32位以内"
				},
				"enterPassword" : {
					required : "请输入确认密码",
					equalTo : "确认密码与新密码必须一致！"
				}
			}
		}).form();
	}
	
	$("#logout").click(function(){
   	 	layer.confirm("您确定要退出吗?", function(i){
   	 		logout();
   	 		layer.close(i);
    	});
    })
    

   function logout(){
		window.top.location.href = "/logout";
	}
});