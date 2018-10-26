<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!--   <title>天智星|运营管理|任务列表</title> -->
<jsp:include page="../../../vendor.jsp"></jsp:include>
</head>

<body class="gray-bg">
	<div class="">
		<div class="ibox-content">
			<div class="row">
				<div class="example-wrap">
					<div class="example">
						<div class="bs-bars pull-left">
							<div id="toolbar">
								<button id="btnAdd" class="btn btn-info" type="button">
									<i class="fa fa-plus-square"></i> 添加
								</button>
								<!-- 								<button id="btnModify" class="btn btn-warning" type="button"> -->
								<!-- 									<i class="fa  fa-wrench"></i>修改 -->
								<!-- 								</button> -->
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
									<label class="col-sm-3 control-label">产品名称：</label>
									<div class="col-sm-6">
										<input type="text" class="form-control" placeholder="请输入名称" id="productName_create" name="productName">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">产品套餐价格：</label>
									<div class="col-sm-6">
										<input type="text" class="form-control" placeholder="请输入产品套餐价格" id="price_create" name="price">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">产品套餐促销价：</label>
									<div class="col-sm-6">
										<input type="text" class="form-control" placeholder="请输入产品套餐促销价" id="promotionPrice_create" name="promotionPrice">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">产品状态：</label>
									<div class="col-sm-6">
										<select class="form-control" id="productStatus_create" name="productStatus">
											<option value="0">未发布</option>
											<option value="1">上架</option>
											<option value="3">挂起</option>
										</select>
									</div>
								</div>
								<div class="form-group datepicker_time_input">
									<label class="col-sm-3 control-label">产品上架时间：</label>
									<div class="col-sm-6">
										<input type="text" class="form-control datetime" placeholder="请输入产品上架时间" id="sellDt_create" name="sellDt" data-time-icon="icon-time" data-date-icon="icon-calendar">
									</div>
								</div>
								<div class="form-group datepicker_time_input">
									<label class="col-sm-3 control-label">产品下架时间：</label>
									<div class="col-sm-6">
										<input type="text" class="form-control datetime datepicker_time_input" placeholder="请输入产品下架时间" id="noSellDt_create" name="noSellDt" data-time-icon="icon-time" data-date-icon="icon-calendar">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">该产品套餐所需要的唾液盒数量（仅试用于基因产品）：</label>
									<div class="col-sm-6">
										<input type="text" class="form-control" placeholder="请输入该产品套餐所需要的唾液盒数量（仅试用于基因产品）" id="sampleTubeCnt_create" name="sampleTubeCnt">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">产品类型：</label>
									<div class="col-sm-6">
										<select class="form-control" id="productType_create" name="productType">
											<option value="0">基因检测</option>
											<option value="1">充电宝</option>
											<option value="2">手机</option>
											<option value="3">单一课程</option>
											<option value="4">课程包</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">产品创建类型：</label>
									<div class="col-sm-6">
										<select class="form-control" id="productCreatePlatform_create" name="productCreatePlatform">
											<option value="1">有赞商城</option>
										</select>
									</div>
								</div>
								<!-- 								<div class="form-group"> -->
								<!-- 									<label class="col-sm-3 control-label">有赞商品：</label> -->
								<!-- 									<div class="col-sm-6"> -->
								<!-- 										<select id="youzanItemId_create" name="youzanItemId" class="form-control" /> -->
								<!-- 										<option>请选择</option> -->
								<!-- 									</div> -->
								<!-- 								</div> -->
								<!-- 								<div class="form-group"> -->
								<!-- 									<label class="col-sm-3 control-label">所属产品：</label> -->
								<!-- 									<div class="col-sm-6"> -->
								<!-- 										<input type="hidden" id="ptyeId" value="" /> -->
								<!-- 									</div> -->
								<!-- 								</div> -->
								<div class="form-group">
									<label class="col-sm-3 control-label">有赞商品id：</label>
									<div class="col-sm-6">
										<input type="text" class="form-control" placeholder="" id="youzanItemId_create" name="youzanItemId">
									</div>
									<button id="btnAddYouzan" class="btn btn-info" type="button">
										<i class="fa fa-plus-square"></i> 添加
									</button>
								</div>


								<div class="form-group">
									<label class="col-sm-3 control-label">产品图片 ：</label>
									<div class="col-sm-6">
										<input type="file" class="form-control file-loading" id="productPic_create" name="file"> <input type="hidden" id="productPic_create_hidden" name="productPic">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">产品简介：</label>
									<div class="col-sm-6">
										<textarea id="productComment_create" name="productComment" style="width: 100%"></textarea>
									</div>
								</div>
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
									<label class="col-sm-3 control-label">产品名称：</label>
									<div class="col-sm-6">
										<input type="text" class="form-control" placeholder="请输入名称" id="productName_update" name="productName">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">产品套餐价格：</label>
									<div class="col-sm-6">
										<input type="text" class="form-control" placeholder="请输入产品套餐价格" id="price_update" name="price">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">产品套餐促销价：</label>
									<div class="col-sm-6">
										<input type="text" class="form-control" placeholder="请输入产品套餐促销价" id="promotionPrice_update" name="promotionPrice">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">产品状态：</label>
									<div class="col-sm-6">
										<select class="form-control" id="productStatus_update" name="productStatus">
											<option value="0">未发布</option>
											<option value="1">上架</option>
											<option value="2">下架</option>
											<option value="3">挂起</option>
										</select>
									</div>
								</div>
								<div class="form-group datepicker_time_input">
									<label class="col-sm-3 control-label">产品上架时间：</label>
									<div class="col-sm-6">
										<input type="text" class="form-control datetime" placeholder="请输入产品上架时间" id="sellDt_update" name="sellDt" data-time-icon="icon-time" data-date-icon="icon-calendar">
									</div>
								</div>
								<div class="form-group datepicker_time_input">
									<label class="col-sm-3 control-label">产品下架时间：</label>
									<div class="col-sm-6">
										<input type="text" class="form-control datetime datepicker_time_input" placeholder="请输入产品下架时间" id="noSellDt_update" name="noSellDt" data-time-icon="icon-time" data-date-icon="icon-calendar">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">该产品套餐所需要的唾液盒数量（仅试用于基因产品）：</label>
									<div class="col-sm-6">
										<input type="text" class="form-control" placeholder="请输入该产品套餐所需要的唾液盒数量（仅试用于基因产品）" id="sampleTubeCnt_update" name="sampleTubeCnt">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">产品类型：</label>
									<div class="col-sm-6">
										<select class="form-control" id="productType_update" name="productType">
											<option value="0">基因检测</option>
											<option value="1">充电宝</option>
											<option value="2">手机</option>
											<option value="3">单一课程</option>
											<option value="4">课程包</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">产品创建类型：</label>
									<div class="col-sm-6">
										<select class="form-control" id="productCreatePlatform_update" name="productCreatePlatform">
											<option value="2">微信小程序</option>
										</select>
									</div>
								</div>
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

		<div class="modal inmodal" id="youzanList" tabindex="-1" role="dialog" aria-hidden="true" style="max-height: 570px; overflow-y: scroll; overflow-x: none;">
			<div class="modal-dialog modal-lg">
				<div class="modal-content animated fadeInRight">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" id="closeB">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title"></h4>
					</div>
					<div class="modal-body">
						<div class="row" >
							<table class="table table-hover table-striped">
								<tr>
									<td></td>
									<td>商品id</td>
									<td>商品名称</td>
								</tr>
								<tbody id="youzanListTable"></tbody>
							</table>
							<div class="fixed-table-pagination" style="display: none;" id="pageDiv">
								<div class="pull-left pagination-detail">
									<span class="pagination-info" id="totalsSpan"></span>
								</div>
								<div class="pull-right pagination">
									<ul class="pagination" id="pageLine">
									</ul>
								</div>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" id="btnClose" class="btn btn-white" data-dismiss="modal">关闭</button>
						<button type="button" id="btnC" class="btn btn-primary">选择</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<input type="hidden" id="totalCounts">
	<input type="hidden" id="pageNo">
	<script src="${ctx}/statics/javascripts/modules/user/productController.js?v=1.0.47"></script>
</body>
</html>