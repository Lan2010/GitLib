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
<style type="text/css">
        .talk_con{
            width:600px;
            height:500px;
            border:1px solid #666;
            margin:50px auto 0;
            background:#f9f9f9;
        }
        .talk_show{
            width:580px;
            height:420px;
            border:1px solid #666;
            background:#fff;
            margin:10px auto 0;
            overflow:auto;
        }
        .talk_input{
            width:580px;
            margin:10px auto 0;
        }
        .whotalk{
            width:80px;
            height:30px;
            float:left;
            outline:none;
        }
        .talk_word{
            width:420px;
            height:26px;
            padding:0px;
            float:left;
            margin-left:10px;
            outline:none;
            text-indent:10px;
        }        
        .talk_sub{
            width:56px;
            height:30px;
            float:left;
            margin-left:10px;
        }
        .atalk{
           margin:10px; 
        }
        .atalk span{
            display:inline-block;
            background:#0181cc;
            border-radius:10px;
            color:#fff;
            padding:5px 10px;
        }
        .btalk{
            margin:10px;
            text-align:right;
        }
        .btalk span{
            display:inline-block;
            background:#ef8201;
            border-radius:10px;
            color:#fff;
            padding:5px 10px;
        }
    </style>
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
								<button id="btnReply" class="btn btn-warning" type="button">
									<i class="fa  fa-wrench"></i>查看消息并回复
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
									<label class="col-sm-3 control-label">选择用户：</label>
									<div class="col-sm-6">
										<select id="userId_create" name="userId" class="form-control" />
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
									<label class="col-sm-3 control-label">消息类型：</label>
									<div class="col-sm-6">
										<select class="form-control" id="type_create" name="type">
											<option value="1">官方推广消息</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">消息主题：</label>
									<div class="col-sm-6">
										<input type="text" class="form-control" placeholder="请输入消息主题" id="title_create" name="title">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">官方推广消息内容：</label>
									<div class="col-sm-6">
										<textarea id="content_create" name="content" style="width: 100%"></textarea>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">图片 ：</label>
									<div class="col-sm-6">
										<input type="file" class="form-control file-loading" id="imgUrl_create" name="file"> <input type="hidden" id="imgUrl_create_hidden" name="imgUrl">
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
									<label class="col-sm-3 control-label">消息类型：</label>
									<div class="col-sm-6">
										<select class="form-control" id="type_update" name="type">
											<option value="1">官方推广消息</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">消息主题：</label>
									<div class="col-sm-6">
										<input type="text" class="form-control" placeholder="请输入消息主题" id="title_update" name="title">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">官方推广消息内容：</label>
									<div class="col-sm-6">
										<textarea id="content_update" name="content" style="width: 100%"></textarea>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">图片 ：</label>
									<div class="col-sm-6">
										<input type="file" class="form-control file-loading" id="imgUrl_update" name="file"> <input type="hidden" id="imgUrl_create_hidden" name="imgUrl">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">删除状态：</label>
									<div class="col-sm-6">
										<select class="form-control" id="delStatus_update" name="delStatus">
											<option value="0">已删除</option>
											<option value="1">正常</option>
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

		<div class="modal inmodal" id="reply" tabindex="-1" role="dialog" aria-hidden="true" style="max-height: 570px; overflow-y: scroll; overflow-x: none;">
			<div class="modal-dialog modal-lg">
				<div class="modal-content animated fadeInRight">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true" id="closeB">&times;</span><span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title">消息记录</h4>
					</div>
					<div class="modal-body">
						<div class="row">
							<form role="form" class="form-horizontal" id="">
								<div class="form-group" id = "replyList">
									
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">回复消息内容：</label>
									<div class="col-sm-6">
										<textarea id="content" name="content" style="width: 100%"></textarea>
									</div>
								</div>
							</form>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" id="btnC" class="btn btn-white" data-dismiss="modal">关闭</button>
						<button type="button" id="btnAnswer" class="btn btn-primary">回复</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<input type="hidden" id="topicId">
	<script src="${ctx}/statics/javascripts/modules/user/userTopicController.js?v=1.0.43"></script>
</body>
</html>