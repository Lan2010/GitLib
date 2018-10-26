<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        <h5>广告列表</h5>
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
              <button id="btnAdd" class="btn btn-info" type="button">
                <i class="fa fa-plus-square"></i> 添加
              </button>
              <button id="btnModify" class="btn btn-warning" type="button">
                <i class="fa  fa-wrench"></i>修改
              </button>
              <button id="btnPush" class="btn btn-danger" type="button">
                <i class="fa  fa-external-link-square"></i>推送
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
                <label class="col-sm-3 control-label">广告名称：</label>
                <div class="col-sm-6">
                  <input type="text" class="form-control" placeholder="请输入广告名称" id="advertName_create" name="advertName">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-3 control-label">广告类型：</label>
                <div class="col-sm-6">
                  <select class="form-control" id="advertisementAttribute_create" name="advertisementAttribute">
                    <option value="DEFAULT">默认</option>
                    <option value="ADVERTISER">广告商</option>
                  </select>
                </div>
              </div>
              <div class="form-group datepicker_time_input">
                <label class="col-sm-3 control-label">开始时间：</label>
                <div class="col-sm-6">
                  <input type="text" class="form-control datetime" placeholder="请输入开始时间" id="begin_time_create" name="beginTime" data-time-icon="icon-time" data-date-icon="icon-calendar">
                </div>
              </div>
              <div class="form-group datepicker_time_input">
                <label class="col-sm-3 control-label">结束时间：</label>
                <div class="col-sm-6">
                  <input type="text" class="form-control datetime datepicker_time_input" placeholder="请输入结束时间" id="end_time_create" name="endTime" data-time-icon="icon-time" data-date-icon="icon-calendar">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-3 control-label">广告语：</label>
                <div class="col-sm-6">
                  <input type="text" class="form-control" placeholder="请输入广告语" id="advertRemark_create" name="advertRemark">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-3 control-label">链向地址：</label>
                <div class="col-sm-6">
                  <input type="text" class="form-control" placeholder="请输入广告链接跳转地址" id="advertLink_create" name="advertisementLink">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-3 control-label">广告类型：</label>
                <div class="col-sm-6">
                  <select class="form-control" id="advertisementType_create" name="advertisementType">
                    <option value="VIEW">浏览</option>
                    <option value="ALERT">弹出</option>
                  </select>
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-3 control-label">访问总奖励：</label>
                <div class="col-sm-6">
                  <input type="text" class="form-control" placeholder="请输入访问总奖励星点数" id="totalAccessStarPoint_create" name="totalAccessStarPoint">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-3 control-label">点击总奖励：</label>
                <div class="col-sm-6">
                  <input type="text" class="form-control" placeholder="请输入点击总奖励星点数" id="totalClickStarPoint_create" name="totalClickStarPoint">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-3 control-label">总点击/浏览：</label>
                <div class="col-sm-6">
                  <input type="text" class="form-control" placeholder="请输入总点击/浏览量" id="totalCount_create" name="totalCount">
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
                <label class="col-sm-3 control-label">广告图标（72px*72px）：</label>
                <div class="col-sm-6">
                  <input type="file" class="form-control file-loading" id="advertIcon_create" name="file">
                  <input type="hidden" id="advertIcon_create_hidden" name="advertIcon">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-3 control-label">广告宣传图（620px*380px）：</label>
                <div class="col-sm-6">
                  <input type="file" class="form-control file-loading" id="advertPic_create" name="file">
                  <input type="hidden" id="advertPic_create_hidden" name="advertPic">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-3 control-label">广告描述：</label>
                <div class="col-sm-6">
                  <textarea id="advertisementDescribe_create" name="advertisementDescribe" style="width: 100%"></textarea>
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
                <label class="col-sm-3 control-label">广告名称：</label>
                <div class="col-sm-6">
                  <input type="text" class="form-control" placeholder="请输入广告名称" id="advertName_update" name="advertName">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-3 control-label">广告类型：</label>
                <div class="col-sm-6">
                  <select class="form-control" id="advertisementAttribute_update" name="advertisementAttribute">
                    <option value="DEFAULT">默认</option>
                    <option value="ADVERTISER">广告商</option>
                  </select>
                </div>
              </div>
              <div class="form-group datepicker_time_input">
                <label class="col-sm-3 control-label">开始时间：</label>
                <div class="col-sm-6">
                  <input type="text" class="form-control datetime" placeholder="请输入开始时间" id="begin_time_update" name="beginTime" data-time-icon="icon-time" data-date-icon="icon-calendar">
                </div>
              </div>
              <div class="form-group datepicker_time_input">
                <label class="col-sm-3 control-label">结束时间：</label>
                <div class="col-sm-6">
                  <input type="text" class="form-control datetime datepicker_time_input" placeholder="请输入结束时间" id="end_time_update" name="endTime" data-time-icon="icon-time" data-date-icon="icon-calendar">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-3 control-label">广告语：</label>
                <div class="col-sm-6">
                  <input type="text" class="form-control" placeholder="请输入广告语" id="advertRemark_update" name="advertRemark">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-3 control-label">广告链接：</label>
                <div class="col-sm-6">
                  <input type="text" class="form-control" placeholder="请输入广告链接" id="advertLink_update" name="advertisementLink">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-3 control-label">广告类型：</label>
                <div class="col-sm-6">
                  <select class="form-control" id="advertisementType_update" name="advertisementType">
                    <option value="VIEW">浏览</option>
                    <option value="ALERT">弹出</option>
                  </select>
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-3 control-label">访问总奖励：</label>
                <div class="col-sm-6">
                  <input type="text" class="form-control" placeholder="请输入总奖励星点数" id="totalAccessStarPoint_update" name="totalAccessStarPoint">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-3 control-label">点击总奖励：</label>
                <div class="col-sm-6">
                  <input type="text" class="form-control" placeholder="请输入点击总奖励星点数" id="totalClickStarPoint_update" name="totalClickStarPoint">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-3 control-label">总点击/浏览：</label>
                <div class="col-sm-6">
                  <input type="text" class="form-control" placeholder="请输入总点击/浏览量" id="totalCount_update" name="totalCount">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-3 control-label">城&nbsp;&nbsp;市：</label>
                <div class="col-sm-6">
                  <select class="form-control" id="city_update" name="city">
                  </select>
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-3 control-label">地&nbsp;&nbsp;区：</label>
                <div class="col-sm-6">
                  <input type="text" class="form-control" placeholder="请输入地区（选填）" id="area_update" name="area">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-3 control-label">广告图标（72px*72px）：</label>
                <div class="col-sm-6">
                  <input type="file" class="form-control file-loading" id="advertIcon_update" name="file">
                  <input type="hidden" id="advertIcon_update_hidden" name="advertIcon">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-3 control-label">广告宣传图（620px*380px）：</label>
                <div class="col-sm-6">
                  <input type="file" class="form-control file-loading" id="advertPic_update" name="file">
                  <input type="hidden" id="advertPic_update_hidden" name="advertPic">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-3 control-label">广告描述：</label>
                <div class="col-sm-6">
                  <textarea id="advertisementDescribe_update" name="advertisementDescribe" style="width: 100%"></textarea>
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
<script src="${ctx}/statics/javascripts/modules/advertisement/advertisementListController.js?v=1.0.43"></script>
</body>
</html>