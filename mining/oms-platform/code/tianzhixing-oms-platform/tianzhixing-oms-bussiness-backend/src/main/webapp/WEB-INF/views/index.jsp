<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="Expires" CONTENT="0">
    <meta http-equiv="Cache-Control" CONTENT="no-cache">
    <meta http-equiv="Pragma" CONTENT="no-cache">
    <title>运营管理系统|OMS</title>
    <jsp:include page="../../vendor.jsp"></jsp:include>
</head>

<body class="fixed-sidebar full-height-layout gray-bg">
<div id="wrapper">
    <!--左侧导航开始-->
    <nav class="navbar-default navbar-static-side" role="navigation">
        <div class="nav-close"><i class="fa fa-times-circle"></i>
        </div>
        <div class="sidebar-collapse">
            <ul class="nav" id="side-menu">
                <li class="nav-header">
                    <div class="dropdown profile-element text-center">
                        <%--<a href="#" target="_blank"><img alt="image" src="${ctx }/statics/images/logo-FA.png?v1.0" /></a>--%>
                    </div>
                    <div class="logo-element text-danger">天智星</div>
                </li>
            </ul>
        </div>
    </nav>
    <!--左侧导航结束-->
    <!--右侧部分开始-->
    <div id="page-wrapper" class="gray-bg">
        <div class="row border-bottom">
            <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
                <div class="navbar-header"><a class="navbar-minimalize minimalize-styl-2 btn btn-primary " href="#"><i class="fa fa-bars"></i> </a>
                </div>
                <ul class="nav navbar-top-links navbar-right">
                    <li class="dropdown">
                        <a class="dropdown-toggle count-info" data-toggle="dropdown" href="javascript:;">
                            <i class="fa fa-envelope"></i> 通知
                            <span class="label label-warning animated bounceInDown" id="messageNum">0</span>
                        </a>
                        <ul class="dropdown-menu dropdown-messages"></ul>
                    </li>
                    <li>
                        <a class="count-info" data-toggle="modal" data-target="#updatePasswordModal" id="btnloginUser">
                            <i class="fa fa-user"></i> <span id="loginUserName"><%=com.tianzhixing.oms.web.security.util.SecurityUtil.currentLogin().getAccount() %></span>
                        </a>
                    </li>
                </ul>
            </nav>
        </div>
        <div class="row content-tabs">
            <button class="roll-nav roll-left J_tabLeft"><i class="fa fa-backward"></i>
            </button>
            <nav class="page-tabs J_menuTabs">
                <div class="page-tabs-content">
                    <a href="javascript:;" class="active J_menuTab" data-id="/chart">首页</a>
                </div>
            </nav>
            <button class="roll-nav roll-right J_tabRight"><i class="fa fa-forward"></i>
            </button>
            <div class="btn-group roll-nav roll-right">
                <button class="dropdown J_tabClose" data-toggle="dropdown">关闭操作<span class="caret"></span>
                </button>
                <ul role="menu" class="dropdown-menu dropdown-menu-right">
                    <li class="J_tabShowActive"><a>定位当前选项卡</a>
                    </li>
                    <li class="divider"></li>
                    <li class="J_tabCloseAll"><a>关闭全部选项卡</a>
                    </li>
                    <li class="J_tabCloseOther"><a>关闭其他选项卡</a>
                    </li>
                </ul>
            </div>
            <a href="javascript:void(0);" id="logout" class="roll-nav roll-right J_tabExit"><i class="fa fa fa-sign-out"></i> 退出</a>
        </div>
        <div class="row J_mainContent" id="content-main">
            <iframe class="J_iframe" name="iframe0" width="100%" height="100%" src="/statistics/userinfo/index" frameborder="0" data-id="/chart" seamless></iframe>
        </div>
        <div class="footer">
            <div class="pull-right">
                &copy;2016 <a href="http://www.guijizaixian.com" target="_blank">天智星</a>
            </div>
            <div class="pull-left">
                下载谷歌浏览器：<a href="chrome-pc.zip">PC</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="chrome-mac.zip">MAC</a>
            </div>
        </div>
    </div>
    <!--右侧部分结束-->
    <!--右侧边栏开始-->
    <div id="right-sidebar">
        <div class="sidebar-container">
            <div class="tab-content">
                <div id="tab-1" class="tab-pane active">
                    <div class="sidebar-title">
                        <h3> <i class="fa fa-comments-o"></i> 主题设置</h3>
                        <small><i class="fa fa-tim"></i> 你可以从这里选择和预览主题的布局和样式，这些设置会被保存在本地，下次打开的时候会直接应用这些设置。</small>
                    </div>
                    <div class="skin-setttings">
                        <div class="title">主题设置</div>
                        <div class="setings-item">
                            <span>收起左侧菜单</span>
                            <div class="switch">
                                <div class="onoffswitch">
                                    <input type="checkbox" name="collapsemenu" class="onoffswitch-checkbox" id="collapsemenu">
                                    <label class="onoffswitch-label" for="collapsemenu">
                                        <span class="onoffswitch-inner"></span>
                                        <span class="onoffswitch-switch"></span>
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="setings-item">
                            <span>固定顶部</span>
                            <div class="switch">
                                <div class="onoffswitch">
                                    <input type="checkbox" name="fixednavbar" class="onoffswitch-checkbox" id="fixednavbar">
                                    <label class="onoffswitch-label" for="fixednavbar">
                                        <span class="onoffswitch-inner"></span>
                                        <span class="onoffswitch-switch"></span>
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="setings-item">
                                <span>
                                    固定宽度
                                </span>
                            <div class="switch">
                                <div class="onoffswitch">
                                    <input type="checkbox" name="boxedlayout" class="onoffswitch-checkbox" id="boxedlayout">
                                    <label class="onoffswitch-label" for="boxedlayout">
                                        <span class="onoffswitch-inner"></span>
                                        <span class="onoffswitch-switch"></span>
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="title">皮肤选择</div>
                        <div class="setings-item default-skin nb">
                                <span class="skin-name ">
                                     <a href="#" class="s-skin-0">
                                         默认皮肤
                                     </a>
                                </span>
                        </div>
                        <div class="setings-item blue-skin nb">
                                <span class="skin-name ">
                                    <a href="#" class="s-skin-1">
                                        蓝色主题
                                    </a>
                                </span>
                        </div>
                        <div class="setings-item yellow-skin nb">
                                <span class="skin-name ">
                                    <a href="#" class="s-skin-3">
                                        黄色/紫色主题
                                    </a>
                                </span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!--右侧边栏结束-->
    <!-- 个人设置 -->
    <div class="modal fade" id="updatePasswordModal" tabindex="-1" role="dialog" aria-labelledby="updatePasswordModalLabel">
        <div class="modal-dialog">
            <div class="modal-content animated flipInY">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
                    <h4 class="modal-title text-center" id="updatePasswordModalLabel">设置</h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" id="updatePasswordForm">
                        <div class="form-group">
                            <label class="col-sm-3 text-right">用户账户：</label>

                            <div class="col-sm-8"><%=com.tianzhixing.oms.web.security.util.SecurityUtil.currentLogin().getAccount() %></div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 text-right">用户姓名：</label>

                            <div class="col-sm-8"><%=com.tianzhixing.oms.web.security.util.SecurityUtil.currentLogin().getName() %></div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 text-right">邮      箱：</label>

                            <div class="col-sm-8"><%=com.tianzhixing.oms.web.security.util.SecurityUtil.currentLogin().getMail() == null ? "尚未设置邮箱" : com.tianzhixing.oms.web.security.util.SecurityUtil.currentLogin().getMail() %></div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 text-right">联系方式：</label>

                            <div class="col-sm-8"><%=com.tianzhixing.oms.web.security.util.SecurityUtil.currentLogin().getMobile() == null ? "尚未设置联系方式" : com.tianzhixing.oms.web.security.util.SecurityUtil.currentLogin().getMobile() %></div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">旧密码：</label>

                            <div class="col-sm-8">
                                <input type="password" placeholder="请输入旧密码" class="form-control" id="oldPassword" name="oldPassword">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">新密码：</label>

                            <div class="col-sm-8">
                                <input type="password" placeholder="请输入新密码" class="form-control" id="newPassword" name="newPassword">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">确认密码：</label>

                            <div class="col-sm-8">
                                <input type="password" placeholder="请输入确认密码" class="form-control" id="enterPassword" name="enterPassword">
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" id="submitUpdatePassword">提交</button>
                </div>
            </div>
        </div>
    </div>
    <!-- end 个人设置 -->
</div>
</body>
<!-- 该模块对应 js controller 文件 -->
<script src="${ctx }/statics/javascripts/modules/menu/menuController.js?v=1.0.0"></script>
<script src="${ctx }/statics/javascripts/modules/index/indexController.js?v1.0.0"></script>
<script src="${ctx }/statics/javascripts/modules/index/indexMessageController.js?V1.0.6"></script>
</html>

