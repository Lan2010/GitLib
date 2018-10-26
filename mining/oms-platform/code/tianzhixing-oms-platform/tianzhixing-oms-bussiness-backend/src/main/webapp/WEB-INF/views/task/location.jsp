<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>天智星|运营管理|任务Location</title>
  <jsp:include page="../../../vendor.jsp"></jsp:include>
</head>

<body class="gray-bg">
<div class="">
  <div class="wrapper wrapper-content animated fadeInRight">
    <div class="ibox float-e-margins">
      <div class="ibox-title">
        <h5>任务坐标列表</h5>
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
              <label>&nbsp;&nbsp;任务列表：</label>
              <select class="form-control" id="task_info_list_select" style="width: 172px;">
              </select>
              <span style="height: 10px;">&nbsp;</span>
              <div class="form-group">
                &nbsp;&nbsp;&nbsp;&nbsp;<button class="btn btn-primary m-b-none" id="btnSearch">
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
                    <i class="fa fa-plus-square"></i> 新增任务
                  </button>
                  <button id="btnAddLocation" class="btn btn-info" type="button">
                    <i class="fa fa-plus-square"></i> 新增任务地址
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
                <label class="col-sm-3 control-label">任务名称：</label>
                <div class="col-sm-6">
                  <input type="text" class="form-control" placeholder="请输入任务名称" id="task_create" name="taskName">
                </div>
              </div>
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
                <label class="col-sm-3 control-label">关键字：</label>
                <div class="col-sm-6">
                  <input type="text" class="form-control" placeholder="请输入关键字" id="keyword_create" name="keyword">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-3 control-label">抓取地图数据：</label>
                <div class="col-sm-6">
                  <div>
                    <input type="radio" name="isAutoMap" value="true" checked="checked"/>&nbsp;&nbsp;是 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <input type="radio"  name="isAutoMap" value="false"/>&nbsp;&nbsp;否
                  </div>
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-3 control-label">任务半径：</label>
                <div class="col-sm-6">
                  <input type="text" class="form-control" placeholder="请输入任务半径（米）" id="taskRadius_create" name="taskRadius">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-3 control-label">任务奖励：</label>
                <div class="col-sm-6">
                  <input type="text" class="form-control" placeholder="请输入任务奖励" id="taskAward_create" name="taskAward">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-3 control-label">汇&nbsp;&nbsp;率：</label>
                <div class="col-sm-6">
                  <input type="text" class="form-control" placeholder="请输入汇率（单MAC兑换星点数 min=0）" id="rate_create" name="rate">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-3 control-label">任务等级：</label>
                <div class="col-sm-6">
                  <select class="form-control" id="taskLevel_create" name="taskLevel">
                    <option value="0">0</option>
                    <option value="1">1</option>
                  </select>
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-3 control-label">城&nbsp;&nbsp;市：</label>
                <div class="col-sm-6">
                  <select class="form-control" id="city_create" name="city">
                  </select>
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-3 control-label">地&nbsp;&nbsp;区：</label>
                <div class="col-sm-6">
                  <input type="text" class="form-control" placeholder="请输入地区（选填）" id="area_create" name="area">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-3 control-label">任务图标（72px*72px）：</label>
                <div class="col-sm-6">
                  <input type="file" class="form-control file-loading" id="icon_create" name="file">
                  <input type="hidden" id="icon_create_hidden" name="taskIcon">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-3 control-label">任务描述：</label>
                <div class="col-sm-6">
                  <textarea id="taskRemark_create" name="taskRemark" style="width: 100%"></textarea>
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

  <div class="modal inmodal" id="create_location_modal" tabindex="-1" role="dialog" aria-hidden="true" style="max-height:570px;overflow-y:scroll; overflow-x:none;">
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
            <form role="form" class="form-horizontal" id="create_location_form">
              <div class="form-group">
                <label class="col-sm-3 control-label">选择任务：</label>
                <div class="col-sm-6">
                  <select class="form-control" id="task_info_list" style="width: 172px;" name="taskId">
                  </select>
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-3 control-label">地图类型：</label>
                <div class="col-sm-6">
                  <select class="form-control" id="mapType_create" style="width: 172px;" name="mapType">
                    <option value="BAIDU">百度地图</option>
                  </select>
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-3 control-label">名称：</label>
                <div class="col-sm-6">
                  <input type="text" class="form-control" placeholder="请输入地址名称" id="name_create" name="name">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-3 control-label">经度：</label>
                <div class="col-sm-6">
                  <input type="text" class="form-control" placeholder="请输入地址经度" id="lng_create" name="lng">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-3 control-label">纬度：</label>
                <div class="col-sm-6">
                  <input type="text" class="form-control" placeholder="请输入地址纬度" id="lat_create" name="lat">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-3 control-label">详细地址：</label>
                <div class="col-sm-6">
                  <input type="text" class="form-control" placeholder="请输入详细地址" id="address_create" name="address">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-3 control-label">区&nbsp;&nbsp;域：</label>
                <div class="col-sm-6">
                  <input type="text" class="form-control" placeholder="请输入区域（选填）" id="area_location_create" name="area">
                </div>
              </div>
            </form>
          </div>
        </div>
        <div class="modal-footer">
          <button type="button" id="btnCancelLocation" class="btn btn-white" data-dismiss="modal">关闭</button>
          <button type="button" id="btnSaveLocation" class="btn btn-primary">保存</button>
        </div>
      </div>
    </div>
  </div>
</div>
<script src="${ctx}/statics/javascripts/modules/task/taskLocationController.js?v=1.0.30"></script>
</body>
</html>