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
									<label class="col-sm-3 control-label">视频名称：</label>
									<div class="col-sm-6">
										<input type="text" class="form-control" placeholder="请输入视频名称" id="videoName_create" name="videoName">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">视频编号：</label>
									<div class="col-sm-6">
										<input type="text" class="form-control" placeholder="请输入视频编号" id="videoNum_create" name="videoNum">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">视频简介：</label>
									<div class="col-sm-6">
										<textarea id="courseComment_create" name="courseComment" style="width: 100%"></textarea>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">视频类型：</label>
									<div class="col-sm-6">
										<select class="form-control" id="courseType_create" name="courseType">
											<option value="9"></option>
											<option value="0">单一课程</option>
											<option value="1">课程包</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">所属产品：</label>
									<div class="col-sm-6">
										<select id="productId_create" name="productId" class="form-control" />
										<option></option>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">所属产品：</label>
									<div class="col-sm-6">
										<input type="hidden" id="ptyeId" value="${data.data.object.productId}" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">视频 ：</label>
									<div class="col-sm-6">
										<input type="file" class="form-control file-loading" id="videoLink_create" name="file"> <input type="hidden" id="videoLink_create_hidden" name="videoLink">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">视频图片 ：</label>
									<div class="col-sm-6">
										<input type="file" class="form-control file-loading" id="coursePic_create" name="file"> <input type="hidden" id="coursePic_create_hidden" name="coursePic">
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
									<label class="col-sm-3 control-label">视频名称：</label>
									<div class="col-sm-6">
										<input type="text" class="form-control" placeholder="请输入视频名称" id="videoName_update" name="videoName">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">视频编号：</label>
									<div class="col-sm-6">
										<input type="text" class="form-control" placeholder="请输入视频编号" id="videoNum_update" name="videoNum">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">视频简介：</label>
									<div class="col-sm-6">
										<textarea id="courseComment_update" name="courseComment" style="width: 100%"></textarea>
									</div>
								</div>
<!-- 								<div class="form-group"> -->
<!-- 									<label class="col-sm-3 control-label">视频类型：</label> -->
<!-- 									<div class="col-sm-6"> -->
<!-- 										<select class="form-control" id="courseType_update" name="courseType"> -->
<!-- 											<option value="0">单一课程</option> -->
<!-- 											<option value="1">课程包</option> -->
<!-- 										</select> -->
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 								<div class="form-group"> -->
<!-- 									<label class="col-sm-3 control-label">所属产品：</label> -->
<!-- 									<div class="col-sm-6"> -->
<!-- 										<select id="productId_update" name="productId" class="form-control" disabled="disabled" /> -->
<!-- 										<option></option> -->
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 								<div class="form-group"> -->
<!-- 									<label class="col-sm-3 control-label">所属产品：</label> -->
<!-- 									<div class="col-sm-6"> -->
<!-- 										<input type="hidden" id="proid" /> -->
<!-- 									</div> -->
<!-- 								</div> -->
								<div class="form-group">
									<label class="col-sm-3 control-label">视频：</label>
									<div class="col-sm-6">
										<input type="file" class="form-control file-loading" id="videoLink_update" name="file"> <input type="hidden" id="videoLink_update_hidden" name="videoLink">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">视频图片 ：</label>
									<div class="col-sm-6">
										<input type="file" class="form-control file-loading" id="coursePic_update" name="file"> <input type="hidden" id="coursePic_update_hidden" name="coursePic">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">是否可用：</label>
									<div class="col-sm-6">
										<select class="form-control" id="enable_update" name="enable">
											<option value="0">否</option>
											<option value="1">是</option>
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
	</div>
	<script src="${ctx}/statics/javascripts/modules/user/courseDetailController.js?v=1.0.43"></script>
</body>
</html>