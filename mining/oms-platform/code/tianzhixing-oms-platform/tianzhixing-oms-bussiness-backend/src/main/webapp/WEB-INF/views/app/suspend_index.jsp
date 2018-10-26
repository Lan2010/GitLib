<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>天智星|运营管理|APP悬浮窗列表</title>
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
                  <button id="btnAppSuspendSave" class="btn btn-warning" type="button">
                    <i class="fa fa-wrench"></i> 添加
                  </button>
                  <button id="btnAppSuspendPush" class="btn btn-danger" type="button">
                    <i class="fa fa-wrench"></i> 推送
                  </button>
                </div>
              </div>
              <table id="table"></table>
            </div>
          </div>
        </div>
      </div>

  <div class="modal inmodal" id="app_modal" tabindex="-1" role="dialog" aria-hidden="true" style="max-height:570px;overflow-y:scroll; overflow-x:none;">
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
            <form role="form" class="form-horizontal" id="app_create_form">
              <div class="form-group datepicker_time_input">
                <label class="col-sm-3 control-label">开始时间：</label>
                <div class="col-sm-6">
                  <input type="text" class="form-control datetime datepicker_time_input" placeholder="请输入开始时间" id="begin_time_create" name="beginTime" data-time-icon="icon-time" data-date-icon="icon-calendar">
                </div>
              </div>
              <div class="form-group datepicker_time_input">
                <label class="col-sm-3 control-label">结束时间：</label>
                <div class="col-sm-6">
                  <input type="text" class="form-control datetime datepicker_time_input" placeholder="请输入结束时间" id="end_time_create" name="endTime" data-time-icon="icon-time" data-date-icon="icon-calendar">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-3 control-label">链向地址：</label>
                <div class="col-sm-6">
                  <input type="text" class="form-control" placeholder="请输入图片链接跳转地址" id="picLink_create" name="picLink">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-3 control-label">图片（180px*180px）：</label>
                <div class="col-sm-6">
                  <input type="file" class="form-control file-loading" id="picUrl_create" name="file">
                  <input type="hidden" id="picUrl_create_hidden" name="picUrl">
                </div>
              </div>
            </form>
          </div>
        </div>
        <div class="modal-footer">
          <button type="button" id="btnCancle" class="btn btn-white" data-dismiss="modal">关闭</button>
          <button type="button" id="btnAppSave" class="btn btn-primary">保存</button>
        </div>
      </div>
    </div>
  </div>

</div>
<script src="${ctx}/statics/javascripts/modules/app/appSuspendController.js?v=1.0.21"></script>
</body>
</html>