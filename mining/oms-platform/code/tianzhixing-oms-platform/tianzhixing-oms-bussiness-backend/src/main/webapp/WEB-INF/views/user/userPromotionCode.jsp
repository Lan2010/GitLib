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
								<div class="form-group">
									<label>&nbsp;&nbsp;用户id：</label> <input type="text" class="form-control" placeholder="请输入userId" id="forUserId">
								</div>
								<div class="form-group">
									&nbsp;&nbsp;&nbsp;&nbsp;
									<button class="btn btn-primary m-b-none" id="btnSearch">
										<span class="fa fa-search"></span>&nbsp;<strong>搜索</strong>
									</button>
								</div>
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
								<button id="btnAdd" class="btn btn-info" type="button">
									<i class="fa fa-plus-square"></i> 添加
								</button>
								<button id="btnModify" class="btn btn-warning" type="button">
									<i class="fa  fa-wrench"></i>修改
								</button>
							</div>
						</div>
						<table id="table"></table>
					</div>
				</div>
			</div>
		</div>

		<div class="modal inmodal" id="create_modal" tabindex="-1" role="dialog" aria-hidden="true" style="max-height: 570px; overflow-y: scroll; overflow-x: none;">
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
							<form role="form" class="form-horizontal" id="create_form">
								<div class="form-group">
									<label class="col-sm-3 control-label">优惠券名称：</label>
									<div class="col-sm-6">
										<input type="text" class="form-control" placeholder="请输入优惠券名称" id="promotionCode_create" name="promotionCode">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">优惠规则：</label>
									<div class="col-sm-6">
										<input type="text" class="form-control" placeholder="请输入优惠规则" id="promotionRules_create" name="promotionRules">
									</div>
								</div>
								<div class="form-group datepicker_time_input">
									<label class="col-sm-3 control-label">最早某日后有效：</label>
									<div class="col-sm-6">
										<input type="text" class="form-control datetime" placeholder="请输入最早某日后有效" id="beginDt_create" name="beginDt" data-time-icon="icon-time" data-date-icon="icon-calendar">
									</div>
								</div>
								<div class="form-group datepicker_time_input">
									<label class="col-sm-3 control-label">最迟某日前有效：</label>
									<div class="col-sm-6">
										<input type="text" class="form-control datetime datepicker_time_input" placeholder="请输入最迟某日前有效" id="expiryDt_create" name="expiryDt" data-time-icon="icon-time" data-date-icon="icon-calendar">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">优惠券指定人userId：</label>
									<div class="col-sm-6">
										<input type="text" class="form-control" placeholder="请输入userId" id="forUserId_create" name="forUserId">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">优惠券指定人openId：</label>
									<div class="col-sm-6">
										<input type="text" class="form-control" placeholder="请输入openId" id="forOpenId_create" name="forOpenId">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">优惠券指定产品：</label>
									<div class="col-sm-6">
										<select id="forProductionId_create" name="forProductionId" class="form-control" />
										<option></option>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">所属产品：</label>
									<div class="col-sm-6">
										<input type="hidden" id="ptyeId" value="" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">优惠券类型：</label>
									<div class="col-sm-6">
										<select class="form-control" id="promotionCodeType_create" name="promotionCodeType">
											<option value="1">抵扣券</option>
											<option value="2">折扣券</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">抵用金额：</label>
									<div class="col-sm-6">
										<input type="text" class="form-control" placeholder="请输入抵用金额" id="voucherAmount_create" name="voucherAmount">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">折扣率：</label>
									<div class="col-sm-6">
										<input type="text" class="form-control" placeholder="请输入折扣率:折让后的价格=原价格*（1-折扣率）" id="discountRate_create" name="discountRate">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">是否允许叠加：</label>
									<div class="col-sm-6">
										<select class="form-control" id="allowComposition_create" name="allowComposition">
											<option value="0">否</option>
											<option value="1">是</option>
										</select>
									</div>
								</div>
								<input type="hidden" class="form-control" id="createDt_create" name="createDt">
							</form>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" id="btnCancle" class="btn btn-white" data-dismiss="modal">关闭</button>
						<button type="button" id="btnSave" class="btn btn-primary">保存</button>
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
									<label class="col-sm-3 control-label">优惠券名称：</label>
									<div class="col-sm-6">
										<input type="text" class="form-control" placeholder="请输入优惠券名称" id="promotionCode_update" name="promotionCode">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">优惠规则：</label>
									<div class="col-sm-6">
										<input type="text" class="form-control" placeholder="请输入优惠规则" id="promotionRules_update" name="promotionRules">
									</div>
								</div>
								<div class="form-group datepicker_time_input">
									<label class="col-sm-3 control-label">最早某日后有效：</label>
									<div class="col-sm-6">
										<input type="text" class="form-control datetime" placeholder="请输入最早某日后有效" id="beginDt_update" name="beginDt" data-time-icon="icon-time" data-date-icon="icon-calendar">
									</div>
								</div>
								<div class="form-group datepicker_time_input">
									<label class="col-sm-3 control-label">最迟某日前有效：</label>
									<div class="col-sm-6">
										<input type="text" class="form-control datetime datepicker_time_input" placeholder="请输入最迟某日前有效" id="expiryDt_update" name="expiryDt" data-time-icon="icon-time" data-date-icon="icon-calendar">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">优惠券指定人userId：</label>
									<div class="col-sm-6">
										<input type="text" class="form-control" placeholder="请输入userId" id="forUserId_update" name="forUserId">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">优惠券指定人openId：</label>
									<div class="col-sm-6">
										<input type="text" class="form-control" placeholder="请输入openId" id="forOpenId_update" name="forOpenId">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">优惠券指定产品：</label>
									<div class="col-sm-6">
										<select id="forProductionId_update" name="forProductionId" class="form-control" />
										<option></option>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">所属产品：</label>
									<div class="col-sm-6">
										<input type="hidden" id="ptyeId" value="" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">优惠券类型：</label>
									<div class="col-sm-6">
										<select class="form-control" id="promotionCodeType_update" name="promotionCodeType">
											<option value="1">抵扣券</option>
											<option value="2">折扣券</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">抵用金额：</label>
									<div class="col-sm-6">
										<input type="text" class="form-control" placeholder="请输入抵用金额" id="voucherAmount_update" name="voucherAmount">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">折扣率：</label>
									<div class="col-sm-6">
										<input type="text" class="form-control" placeholder="请输入折扣率:折让后的价格=原价格*（1-折扣率）" id="discountRate_update" name="discountRate">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">是否允许叠加：</label>
									<div class="col-sm-6">
										<select class="form-control" id="allowComposition_update" name="allowComposition">
											<option value="0">否</option>
											<option value="1">是</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">状态：</label>
									<div class="col-sm-6">
										<select class="form-control" id="status_update" name="status">
											<option value="0">无效</option>
											<option value="1">有效</option>
											<option value="2">已使用</option>
										</select>
									</div>
								</div>
								<input type="hidden" class="form-control" id="createDt_update" name="createDt">
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
	</div>
	<script src="${ctx}/statics/javascripts/modules/user/userPromotionCodeController.js?v=1.0.43"></script>
</body>
</html>