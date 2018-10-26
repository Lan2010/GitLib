<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>天智星|运营管理|任务列表</title>
  <jsp:include page="../../../vendor.jsp"></jsp:include>
</head>

<body class="gray-bg">
<div class="">
  <div class="wrapper wrapper-content animated fadeInRight">
    <div class="ibox float-e-margins">
      <div class="ibox-title">
        <h5>任务列表</h5>
        <div class="ibox-tools">
          <a class="collapse-link">
            <i class="fa fa-chevron-up"></i>
          </a>
        </div>
      </div>
      <div class="ibox-content">
        <div class="row">

          <div class="form-inline">
            <div class="form-group">
              <label>&nbsp;&nbsp;开始日期：</label>

              <div class="input-group input-daterange datepicker_day_input">
                <input type="text" class="form-control datetime " id="time_begin_b"
                       placeholder="请选择" data-time-icon="icon-time" data-date-icon="icon-calendar">
                <span class="input-group-addon">至</span>
                <input type="text" class="form-control" id="time_end_b"
                       placeholder="请选择">
              </div>

              <span style="height: 10px;">&nbsp;</span>
              <div class="form-group">
                &nbsp;&nbsp;&nbsp;&nbsp;<button class="btn btn-primary m-b-none" id="btnSearch">
                <span class="fa fa-search"></span>&nbsp;<strong>搜索</strong>
              </button>
              </div>
            </div>
            </div>
            <div class="form-inline">
            <div class="form-group">
            <label>&nbsp;&nbsp;结束日期：</label>

            <div class="input-group input-daterange datepicker_day_input">
              <input type="text" class="form-control datetime " id="time_begin_e"
                     placeholder="请选择" data-time-icon="icon-time" data-date-icon="icon-calendar">
              <span class="input-group-addon">至</span>
              <input type="text" class="form-control" id="time_end_e"
                     placeholder="请选择">
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
                  <button id="btnModify" class="btn btn-warning" type="button">
                    <i class="fa fa-wrench"></i> 修改
                  </button>
                  <button id="btnSure" class="btn btn-danger" type="button">
                    <i class="fa fa-external-link-square"></i> 推送任务
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
                <label class="col-sm-3 control-label">任务名称：</label>
                <div class="col-sm-6">
                  <input type="text" class="form-control" placeholder="请输入任务名称" id="task_update" name="taskName">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-3 control-label">任务奖励：</label>
                <div class="col-sm-6">
                  <input type="text" class="form-control" placeholder="请输入任务奖励" id="taskAward_update" name="taskAward">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-3 control-label">任务等级：</label>
                <div class="col-sm-6">
                  <select class="form-control" id="taskLevel_update" name="taskLevel">
                    <option value="0">0</option>
                    <option value="1">1</option>
                  </select>
                </div>
              </div>
              <div class="form-group datepicker_time_input">
                <label class="col-sm-3 control-label">开始时间：</label>
                <div class="col-sm-6">
                  <input type="text" class="form-control datetime datepicker_time_input" placeholder="请输入开始时间" id="begin_time_update" name="beginTime" data-time-icon="icon-time" data-date-icon="icon-calendar">
                </div>
              </div>
              <div class="form-group datepicker_time_input">
                <label class="col-sm-3 control-label">结束时间：</label>
                <div class="col-sm-6">
                  <input type="text" class="form-control datetime datepicker_time_input" placeholder="请输入结束时间" id="end_time_update" name="endTime" data-time-icon="icon-time" data-date-icon="icon-calendar">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-3 control-label">关键字：</label>
                <div class="col-sm-6">
                  <input type="text" disabled="disabled" class="form-control" placeholder="请输入关键字" id="keyword_update" name="keyword">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-3 control-label">任务半径：</label>
                <div class="col-sm-6">
                  <input type="text" class="form-control" placeholder="请输入关键字" id="taskRadius_update" name="taskRadius">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-3 control-label">汇&nbsp;&nbsp;率：</label>
                <div class="col-sm-6">
                  <input type="text" class="form-control" placeholder="请输入汇率（单MAC兑换星点数 min=0）" id="rate_update" name="rate">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-3 control-label">任务图标（72px*72px）：</label>
                <div class="col-sm-6">
                  <input type="file" class="form-control file-loading" id="icon_update" name="file">
                  <input type="hidden" id="icon_update_hidden" name="taskIcon">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-3 control-label">任务描述：</label>
                <div class="col-sm-6">
                  <textarea id="taskRemark_update" name="taskRemark" style="width: 100%"></textarea>
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
<script src="${ctx}/statics/javascripts/modules/task/taskListController.js?v=1.0.18"></script>
</body>
</html>