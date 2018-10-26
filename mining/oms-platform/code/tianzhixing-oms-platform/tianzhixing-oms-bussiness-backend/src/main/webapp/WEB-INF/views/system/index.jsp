<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>天智星|运营管理|系统参数列表</title>
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
                  <button id="btnModify" class="btn btn-warning" type="button">
                    <i class="fa fa-wrench"></i> 修改
                  </button>
                </div>
              </div>
              <table id="table"></table>
            </div>
          </div>
        </div>
      </div>

  <div class="modal inmodal" id="update_modal" tabindex="-1" role="dialog" aria-hidden="true" style="max-height:570px;overflow-y:scroll; overflow-x:none;">
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
                <label class="col-sm-3 control-label">参数值：</label>
                <div class="col-sm-6">
                  <input type="text" class="form-control" placeholder="请输入参数值" id="systemValue_update" name="systemValue">
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
<script src="${ctx}/statics/javascripts/modules/system/systemListController.js?v=1.0.18"></script>
</body>
</html>