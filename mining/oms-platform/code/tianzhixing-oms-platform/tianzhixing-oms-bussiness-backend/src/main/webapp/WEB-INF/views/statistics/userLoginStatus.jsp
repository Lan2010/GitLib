<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>天智星|运营统计|统计图表.应用程序统计</title>
    <jsp:include page="../../../vendor.jsp"></jsp:include>
    <link href="${ctx }/statics/css/chart.css?v=1.0.1" rel="stylesheet">
</head>

<body class="gray-bg">
<div class="">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="ibox float-e-margins">
            <div class="ibox float-e-margins">
                <div class="ibox-content">
                    <div class="row">
                        <div class="col-sm-4" style="height: 10px;" id="label_chart"><label value="2">月</label><label value="1">天</label><label value="0">时</label></div>
                    </div>
                </div>
                <div class="ibox-content">
                    <div class="row">
                        <div class="col-sm-4">
                            <div id="canvas-holder-userLogin-pie" >

                            </div>
                        </div>
                        <div class="col-sm-8">
                            <div id="canvas-holder-userLogin-line" style="width: 100%;">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="ibox-content">
                    <div class="row">
                        <div class="col-sm-4" style="height: 5px;"></div>
                    </div>
                </div>
                <div class="ibox-content">
                    <div class="row">
                        <div class="col-sm-4">
                            <div id="canvas-holder-userLogin-diffip-pie" >
                            </div>
                        </div>
                        <div class="col-sm-8">
                            <div id="canvas-holder-userLogin-diffip-line" style="width: 100%;">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="ibox-content">
                    <div class="row">
                        <div class="col-sm-4" style="height: 5px;"></div>
                    </div>
                </div>
                 <div class="ibox-content">
                    <div class="row">
                        <div class="col-sm-4">
                            <div id="canvas-holder-userOnline-pie" >
                            </div>
                        </div>
                        <div class="col-sm-8">
                            <div id="canvas-holder-userOnline-line" style="width: 100%;">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="${ctx}/statics/javascripts/modules/statistics/chartConfig.js?v=1.0.4"></script>
<script src="${ctx}/statics/javascripts/lib/chartjs/Chart.bundle.js?v=1.0.0"></script>
<script src="${ctx}/statics/javascripts/modules/statistics/userLoginStatusStatisticsController.js?v=1.0.9"></script>
</body>
</html>