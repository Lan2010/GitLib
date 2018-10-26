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
									<label >&nbsp;&nbsp;昵称：</label> <input type="text" class="form-control" placeholder="请输入昵称" id="nickName">
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
						<table id="table"></table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="${ctx}/statics/javascripts/modules/user/userWechatController.js?v=1.0.43"></script>
</body>
</html>