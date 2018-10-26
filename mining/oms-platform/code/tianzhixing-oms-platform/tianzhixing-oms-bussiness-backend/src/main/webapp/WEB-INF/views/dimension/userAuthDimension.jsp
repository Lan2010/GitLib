<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

  <div class="modal inmodal" id="create_modal" tabindex="-1" role="dialog" aria-hidden="true" style="max-height:570px;overflow-y:scroll; overflow-x:none;">
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
                <label class="col-sm-3 control-label">名称：</label>
                <div class="col-sm-6">
                  <input type="text" class="form-control" placeholder="请输入名称" id="name" name="name">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-3 control-label">值：</label>
                <div class="col-sm-6">
                  <input type="text" class="form-control" placeholder="请输入值" id="value" name="value">
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
              <input type="hidden" name="id" >
              <div class="form-group">
                <label class="col-sm-3 control-label">名称：</label>
                <div class="col-sm-6">
                  <input type="text" class="form-control" placeholder="请输入名称" id="name_update" name="name">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-3 control-label">值：</label>
                <div class="col-sm-6">
                  <input type="text" class="form-control" placeholder="请输入值" id="value_update" name="value">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-3 control-label">统计维度：</label>
                <div class="col-sm-6">
                  <input id="r1" type="radio" value="true" name="enable">可用</input>
				  <input id="r2" type="radio" value="false" name="enable">不可用</input>
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
<script src="${ctx}/statics/javascripts/modules/dimension/userAuthDimensionController.js?v=1.0.48"></script>
</body>
</html>