<%@ page import="com.tianzhixing.oms.bussiness.backend.web.map.BaiduMapConfig" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>天智星|运营管理</title>
  <jsp:include page="../../../vendor.jsp"></jsp:include>

  <style type="text/css">
    body, html,#allmap {width: 100%;height: 100%;overflow: hidden;margin:0;font-family:"微软雅黑";}
    #l-map{height:100%;width:78%;float:left;border-right:2px solid #bcbcbc;}
    #r-result{height:100%;width:20%;float:left;}
  </style>

</head>

<body class="gray-bg">
<div id="allmap">
</div>
</body>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=<%=BaiduMapConfig.BAIDU_MAP_WEB_KEY%>>"></script>
<script src="${ctx }/statics/javascripts/modules/device/map.js?v=1.0.6"></script>
</html>

