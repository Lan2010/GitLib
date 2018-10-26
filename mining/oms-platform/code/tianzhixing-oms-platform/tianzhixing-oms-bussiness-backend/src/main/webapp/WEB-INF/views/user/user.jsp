<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>天智星|运营管理|广告列表</title>
<jsp:include page="../../../vendor.jsp"></jsp:include>
</head>

<body class="gray-bg">
	<div class="">
		<div class="wrapper wrapper-content animated fadeInRight">
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<h5>列表</h5>
					<div class="ibox-tools">
						<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
						</a>
					</div>
				</div>
				<div class="ibox-content">
					<div class="row">

						<div class="form-inline">
							<div class="form-group">
								<label>&nbsp;&nbsp;注册时间：</label>
								<div class="input-group input-daterange datepicker_day_input">
									<input type="text" class="form-control datetime " id="time_begin" placeholder="请选择" data-time-icon="icon-time" data-date-icon="icon-calendar"> <span class="input-group-addon">至</span> <input type="text" class="form-control" id="time_end" placeholder="请选择">
								</div>
								<div class="form-group">
									<label>&nbsp;&nbsp;昵称：</label> <input type="text" class="form-control" placeholder="请输入昵称" id="nickName">
								</div>
								<div class="form-group">
									&nbsp;&nbsp;&nbsp;&nbsp;
									<button class="btn btn-primary m-b-none" id="btnSearch">
										<span class="fa fa-search"></span>&nbsp;<strong>搜索</strong>
									</button>
								</div>
							</div>
						</div>
						<div class="form-inline">
							<div class="form-group">
								<label>&nbsp;&nbsp;注册渠道：</label> <input type="text" class="form-control" placeholder="请输入注册渠道" id="userChannel">
							</div>
							<div class="form-group">
								<label>&nbsp;&nbsp;注册平台：</label> <input type="text" class="form-control" placeholder="请输入注册平台" id="regPlatform">
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="ibox-content">
			<div class="row">
				<div class="example-wrap">
					<div class="example">
						<div class="bs-bars pull-left">
							<div id="toolbar">
								<button id="btnModify" class="btn btn-warning" type="button">
									<i class="fa  fa-wrench"></i>修改
								</button>
								<button id="btnUserStarpointAccount" class="btn btn-warning" type="button">
									<i class="fa  fa-wrench"></i>用户星星数据信息
								</button>
								<button id="btnUserWechat" class="btn btn-warning" type="button">
									<i class="fa  fa-wrench"></i>用户微信数据信息
								</button>
							</div>
						</div>
						<table id="table"></table>
					</div>
				</div>
			</div>
		</div>

		<div class="modal inmodal" id="update_modal" tabindex="-1" role="dialog" aria-hidden="true" style="max-height: 570px; overflow-y: scroll; overflow-x: none;">
			<div class="modal-dialog modal-lg">
				<div class="modal-content animated fadeInRight">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title"></h4>
					</div>
					<div class="modal-body">
						<div class="row">
							<form role="form" class="form-horizontal" id="update_form">
								<input type="hidden" name="id">
								<div class="form-group">
									<label class="col-sm-3 control-label">联系电话：</label>
									<div class="col-sm-6">
										<input type="text" class="form-control" placeholder="请输入联系电话" id="phoneNum_update" name="phoneNum">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">密码：</label>
									<div class="col-sm-6">
										<input type="text" class="form-control" placeholder="请输入密码" id="passwd_update" name="passwd">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">邮箱：</label>
									<div class="col-sm-6">
										<input type="text" class="form-control" placeholder="请输入邮箱" id="email_update" name="email">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">性别：</label>
									<div class="col-sm-6">
										<select class="form-control" id="gender_update" name="gender">
											<option value=""></option>
											<option value="0">未知</option>
											<option value="1">男</option>
											<option value="2">女</option>
										</select>
									</div>
								</div>
								<div class="form-group datepicker_time_input">
									<label class="col-sm-3 control-label">生日：</label>
									<div class="col-sm-6">
										<input type="text" class="form-control datetime" placeholder="请输入生日" id="birthday_update" name="birthday" data-time-icon="icon-time" data-date-icon="icon-calendar">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">微信公众号openId：</label>
									<div class="col-sm-6">
										<input type="text" class="form-control" placeholder="请输入微信公众号openId" id="mpOpenId_update" name="mpOpenId">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">微信小程序openId：</label>
									<div class="col-sm-6">
										<input type="text" class="form-control" placeholder="请输入微信小程序openId" id="miniappOpenId_update" name="miniappOpenId">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">有赞对应userid：</label>
									<div class="col-sm-6">
										<input type="text" class="form-control" placeholder="请输入youzanUserId" id="youzanUserId_update" name="youzanUserId">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">有赞对应的粉丝id：</label>
									<div class="col-sm-6">
										<input type="text" class="form-control" placeholder="请输入youzanFansId" id="youzanFansId_update" name="youzanFansId">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">昵称：</label>
									<div class="col-sm-6">
										<input type="text" class="form-control" placeholder="请输入昵称" id="nickname_update" name="nickname">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">微信union_id：</label>
									<div class="col-sm-6">
										<input type="text" class="form-control" placeholder="请输入微信union_id" id="unionId_update" name="unionId">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">推荐奖：</label>
									<div class="col-sm-6">
										<input type="text" class="form-control" placeholder="请输入推荐奖" id="bonus_update" name="bonus">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">注册平台：</label>
									<div class="col-sm-6">
										<input type="text" class="form-control" placeholder="请输入注册平台" id="regPlatform_update" name="regPlatform">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">注册渠道：</label>
									<div class="col-sm-6">
										<input type="text" class="form-control" placeholder="请输入注册渠道" id="userChannel_update" name="userChannel">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">头像url：</label>
									<div class="col-sm-6">
										<input type="file" class="form-control file-loading" id="headimgUrl_update" name="file"> <input type="hidden" id="headimgUrl_update_hidden" name="headimgUrl">
									</div>
								</div>
								<input type="hidden" class="form-control" id="createdt_update" name="createdt">
							</form>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" id="btnCancle" class="btn btn-white" data-dismiss="modal">关闭</button>
						<button type="button" id="btnUpdate" class="btn btn-primary">更新</button>
					</div>
				</div>
			</div>
		</div>

		<div class="modal inmodal" id="userStarpointAccount" tabindex="-1" role="dialog" aria-hidden="true" style="max-height: 570px; overflow-y: scroll; overflow-x: none;">
			<div class="modal-dialog modal-lg">
				<div class="modal-content animated fadeInRight">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title"></h4>
					</div>
					<div class="modal-body">
						<div class="row">
							<form role="form" class="form-horizontal" id="userStarpointAccount_form">
								<input type="hidden" name="id">
								<div class="form-group">
									<label class="col-sm-3 control-label">捡星星账户手机号：</label>
									<div class="col-sm-6">
										<input type="text" class="form-control" placeholder="" id="wkappMobile" name="wkappMobile" disabled="disabled">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">userId：</label>
									<div class="col-sm-6">
										<input type="text" class="form-control" placeholder="" id="userId" name="userId" disabled="disabled">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">是否有效：</label>
									<div class="col-sm-6">
										<select class="form-control" id="enable_update" name="enable" disabled="disabled">
											<option value="0">失效</option>
											<option value="1">有效</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">捡星星账户机构标示：</label>
									<div class="col-sm-6">
										<input type="text" class="form-control" placeholder="" id="wkappOrg" name="wkappOrg" disabled="disabled">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">对应中心账户平台机构标示：</label>
									<div class="col-sm-6">
										<input type="text" class="form-control" placeholder="" id="selfAccountOrg" name="selfAccountOrg" disabled="disabled">
									</div>
								</div>
							</form>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" id="btnCancle" class="btn btn-white" data-dismiss="modal">关闭</button>
					</div>
				</div>
			</div>
		</div>
		
		<div class="modal inmodal" id="userWechat" tabindex="-1" role="dialog" aria-hidden="true" style="max-height: 570px; overflow-y: scroll; overflow-x: none;">
			<div class="modal-dialog modal-lg">
				<div class="modal-content animated fadeInRight">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title"></h4>
					</div>
					<div class="modal-body">
						<div class="row">
							<form role="form" class="form-horizontal" id="userWechat_form">
								<input type="hidden" name="id">
								<div class="form-group">
									<label class="col-sm-3 control-label">UserID：</label>
									<div class="col-sm-6">
										<input type="text" class="form-control" placeholder="" id="userId" name="userId" disabled="disabled">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">微信公众号openID：</label>
									<div class="col-sm-6">
										<input type="text" class="form-control" placeholder="" id="mpOpenId" name="mpOpenId" disabled="disabled">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">昵称：</label>
									<div class="col-sm-6">
										<input type="text" class="form-control" placeholder="" id="nickname" name="nickname" disabled="disabled">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">性别：</label>
									<div class="col-sm-6">
										<select class="form-control" id="gender" name="gender" disabled="disabled">
											<option value="0">未知</option>
											<option value="1">男</option>
											<option value="2">女</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">电话号码：</label>
									<div class="col-sm-6">
										<input type="text" class="form-control" placeholder="" id="phoneNum" name="phoneNum" disabled="disabled">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">unionId：</label>
									<div class="col-sm-6">
										<input type="text" class="form-control" placeholder="" id="unionId" name="unionId" disabled="disabled">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">小程序openID：</label>
									<div class="col-sm-6">
										<input type="text" class="form-control" placeholder="" id="miniappOpenId" name="miniappOpenId" disabled="disabled">
									</div>
								</div>
							</form>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" id="btnCancle" class="btn btn-white" data-dismiss="modal">关闭</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="${ctx}/statics/javascripts/modules/user/userController.js?v=1.0.43"></script>
</body>
</html>