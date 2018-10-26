$(function () {

    initStatistics(1,0);
    initStatisticsByType(1,0);
    initStatisticsByAppAndType(1,0);
    
});

function initStatistics (pageNo,type){
	var pageSize = 5;
    ajaxUtil.ajax('statistics/mallUserOrderPay/barByApplication', {pageNo:pageNo,pageSize:pageSize,type:type}, "GET", function (data) {
        if(data.code === 200){
        	var totalCount =data.data.total;
        	if(totalCount>0){
        		$("#pageDiv").attr("style","display:block;");
        	}
            initMallUserOrderPayCountByApplicationBar(data);
            initMallUserOrderPayAmountByApplicationBar(data);
            document.getElementById("totalsSpan").innerHTML='总共 '+totalCount+'条记录';
            $("#totalCounts").val(data.data.total);
            $("#pageNo").val(pageNo);
            goPages(pageNo,pageSize,type);
        }
    }, null, null, null, null, false);
}

function initStatisticsByType (pageNo,type){
	var pageSize = 5;
    ajaxUtil.ajax('statistics/mallUserOrderPay/barByType', {pageNo:pageNo,pageSize:pageSize,type:type}, "GET", function (data) {
        if(data.code === 200){
        	var totalCount =data.data.total;
        	if(totalCount>0){
        		$("#pageDivByType").attr("style","display:block;");
        	}
            initMallUserOrderPayCountByTypeBar(data);
            initMallUserOrderPayAmountByTypeBar(data);
            document.getElementById("totalsSpanByType").innerHTML='总共 '+totalCount+'条记录';
            $("#totalCountsByType").val(data.data.total);
            $("#pageNoByType").val(pageNo);
            goPagesByType(pageNo,pageSize,type);
        }
    }, null, null, null, null, false);
}

function initStatisticsByAppAndType (pageNo,type){
	var pageSize = 5;
    ajaxUtil.ajax('statistics/mallUserOrderPay/barByAppAndType', {pageNo:pageNo,pageSize:pageSize,type:type}, "GET", function (data) {
        if(data.code === 200){
        	var totalCount =data.data.total;
        	if(totalCount>0){
        		$("#pageDivByAppAndType").attr("style","display:block;");
        	}
        	initMallUserOrderPayCountByAppAndTypeBar(data);
        	initMallUserOrderPayAmountByAppAndTypeBar(data);
            document.getElementById("totalsSpanByAppAndType").innerHTML='总共 '+totalCount+'条记录';
            $("#totalCountsByAppAndType").val(data.data.total);
            $("#pageNoByAppAndType").val(pageNo);
            goPagesByAppAndType(pageNo,pageSize,type);
        }
    }, null, null, null, null, false);
}

function initMallUserOrderPayCountByApplicationBar(data){
    $('#canvas-holder-user-orderPayCountByApp-scatter-bar').empty();
    $('#canvas-holder-user-orderPayCountByApp-scatter-bar').append('<canvas id="canvas-holder-user-orderPayCountByApp-scatter-bar-chart" height="60px"  />');
    var mallUserOrderPayStatisticsPieConfig = barChartCountConfigByApplication(data.data.list[0],data.data.list[1],data.data.list[2],data.data.list[3]);
    var pie_ctx = document.getElementById("canvas-holder-user-orderPayCountByApp-scatter-bar-chart").getContext("2d");
    window.mallUserOrderPayPie =  new Chart(pie_ctx,mallUserOrderPayStatisticsPieConfig);
    window.mallUserOrderPayPie.update();
}

function initMallUserOrderPayAmountByApplicationBar(data){
    $('#canvas-holder-user-orderPayAmountByApp-scatter-bar').empty();
    $('#canvas-holder-user-orderPayAmountByApp-scatter-bar').append('<canvas id="canvas-holder-user-orderPayAmountByApp-scatter-bar-chart" height="60px"  />');
    var mallUserOrderPayStatisticsPieConfig = barChartAmountConfigByApplication(data.data.list[0],data.data.list[4],data.data.list[5],data.data.list[6]);
    var pie_ctx = document.getElementById("canvas-holder-user-orderPayAmountByApp-scatter-bar-chart").getContext("2d");
    window.mallUserOrderPayPie =  new Chart(pie_ctx,mallUserOrderPayStatisticsPieConfig);
    window.mallUserOrderPayPie.update();
}

function initMallUserOrderPayCountByTypeBar(data){
    $('#canvas-holder-user-orderPayCountByType-scatter-bar').empty();
    $('#canvas-holder-user-orderPayCountByType-scatter-bar').append('<canvas id="canvas-holder-user-orderPayCountByType-scatter-bar-chart" height="60px"  />');
    var mallUserOrderPayStatisticsPieConfig = barChartCountConfigByApplication(data.data.list[0],data.data.list[1],data.data.list[2],data.data.list[3]);
    var pie_ctx = document.getElementById("canvas-holder-user-orderPayCountByType-scatter-bar-chart").getContext("2d");
    window.mallUserOrderPayPie =  new Chart(pie_ctx,mallUserOrderPayStatisticsPieConfig);
    window.mallUserOrderPayPie.update();
}

function initMallUserOrderPayAmountByTypeBar(data){
    $('#canvas-holder-user-orderPayAmountByType-scatter-bar').empty();
    $('#canvas-holder-user-orderPayAmountByType-scatter-bar').append('<canvas id="canvas-holder-user-orderPayAmountByType-scatter-bar-chart" height="60px"  />');
    var mallUserOrderPayStatisticsPieConfig = barChartAmountConfigByApplication(data.data.list[0],data.data.list[4],data.data.list[5],data.data.list[6]);
    var pie_ctx = document.getElementById("canvas-holder-user-orderPayAmountByType-scatter-bar-chart").getContext("2d");
    window.mallUserOrderPayPie =  new Chart(pie_ctx,mallUserOrderPayStatisticsPieConfig);
    window.mallUserOrderPayPie.update();
}

function initMallUserOrderPayCountByAppAndTypeBar(data){
    $('#canvas-holder-user-orderPayCountByAppAndType-scatter-bar').empty();
    $('#canvas-holder-user-orderPayCountByAppAndType-scatter-bar').append('<canvas id="canvas-holder-user-orderPayCountByAppAndType-scatter-bar-chart" height="60px"  />');
    var mallUserOrderPayStatisticsPieConfig = barChartCountConfigByApplication(data.data.list[0],data.data.list[1],data.data.list[2],data.data.list[3]);
    var pie_ctx = document.getElementById("canvas-holder-user-orderPayCountByAppAndType-scatter-bar-chart").getContext("2d");
    window.mallUserOrderPayPie =  new Chart(pie_ctx,mallUserOrderPayStatisticsPieConfig);
    window.mallUserOrderPayPie.update();
}

function initMallUserOrderPayAmountByAppAndTypeBar(data){
    $('#canvas-holder-user-orderPayAmountByAppAndType-scatter-bar').empty();
    $('#canvas-holder-user-orderPayAmountByAppAndType-scatter-bar').append('<canvas id="canvas-holder-user-orderPayAmountByAppAndType-scatter-bar-chart" height="60px"  />');
    var mallUserOrderPayStatisticsPieConfig = barChartAmountConfigByApplication(data.data.list[0],data.data.list[4],data.data.list[5],data.data.list[6]);
    var pie_ctx = document.getElementById("canvas-holder-user-orderPayAmountByAppAndType-scatter-bar-chart").getContext("2d");
    window.mallUserOrderPayPie =  new Chart(pie_ctx,mallUserOrderPayStatisticsPieConfig);
    window.mallUserOrderPayPie.update();
}

function barChartCountConfigByApplication(labels,alreadyPayCount,awaitPayCount,failedPayCount){
	var barChartCountConfigByApplication = {
			type: 'bar',
		    data: {
		        labels: labels,
		        datasets: [{
		            label: '已支付数量',
		            data: alreadyPayCount,
		            backgroundColor: [
		                'rgba(153, 204, 255, 0.5)',
		                'rgba(153, 204, 255, 0.5)',
		                'rgba(153, 204, 255, 0.5)',
		                'rgba(153, 204, 255, 0.5)',
		                'rgba(153, 204, 255, 0.5)',
		                'rgba(153, 204, 255, 0.5)'
		            ],
		            borderColor: [
		                'rgba(153, 204, 255, 1)',
		                'rgba(153, 204, 255, 1)',
		                'rgba(153, 204, 255, 1)',
		                'rgba(153, 204, 255, 1)',
		                'rgba(153, 204, 255, 1)',
		                'rgba(153, 204, 255, 1)'
		            ],
		            borderWidth: 1
		        },
		        {
		            label: '待支付数量',
		            data: awaitPayCount,
		            backgroundColor: [
		                'rgba(255, 153, 204, 0.5)',
		                'rgba(255, 153, 204, 0.5)',
		                'rgba(255, 153, 204, 0.5)',
		                'rgba(255, 153, 204, 0.5)',
		                'rgba(255, 153, 204, 0.5)',
		                'rgba(255, 153, 204, 0.5)'
		            ],
		            borderColor: [
		                'rgba(255, 153, 204, 1)',
		                'rgba(255, 153, 204, 1)',
		                'rgba(255, 153, 204, 1)',
		                'rgba(255, 153, 204, 1)',
		                'rgba(255, 153, 204, 1)',
		                'rgba(255, 153, 204, 1)'
		            ],
		            borderWidth: 1
		        },
		        {
		            label: '支付失败数量',
		            data: failedPayCount,
		            backgroundColor: [
		                'rgba(51, 204, 102, 0.5)',
		                'rgba(51, 204, 102, 0.5)',
		                'rgba(51, 204, 102, 0.5)',
		                'rgba(51, 204, 102, 0.5)',
		                'rgba(51, 204, 102, 0.5)',
		                'rgba(51, 204, 102, 0.5)'
		            ],
		            borderColor: [
		                'rgba(51, 204, 102, 1)',
		                'rgba(51, 204, 102, 1)',
		                'rgba(51, 204, 102, 1)',
		                'rgba(51, 204, 102, 1)',
		                'rgba(51, 204, 102, 1)',
		                'rgba(51, 204, 102, 1)'
		            ],
		            borderWidth: 1
		        }
		        ]
		    },
		    options: {
		    	title: {
                    display: true,
                    text: '商城用户订单支付统计'
                },
		        scales: {
		            yAxes: [{
		                ticks: {
		                    beginAtZero:true
		                }
		            }]
		        }
		    }
            };
    return barChartCountConfigByApplication;
}

function barChartAmountConfigByApplication(labels,alreadyPayAmountList,awaitPayAmountList,failedPayAmountList){
	var barChartAmountConfigByApplication = {
			type: 'bar',
		    data: {
		        labels: labels,
		        datasets: [{
		            label: '已支付额度',
		            data: alreadyPayAmountList,
		            backgroundColor: [
		                'rgba(153, 204, 255, 0.5)',
		                'rgba(153, 204, 255, 0.5)',
		                'rgba(153, 204, 255, 0.5)',
		                'rgba(153, 204, 255, 0.5)',
		                'rgba(153, 204, 255, 0.5)',
		                'rgba(153, 204, 255, 0.5)'
		            ],
		            borderColor: [
		                'rgba(153, 204, 255, 1)',
		                'rgba(153, 204, 255, 1)',
		                'rgba(153, 204, 255, 1)',
		                'rgba(153, 204, 255, 1)',
		                'rgba(153, 204, 255, 1)',
		                'rgba(153, 204, 255, 1)'
		            ],
		            borderWidth: 1
		        },
		        {
		            label: '待支付额度',
		            data: awaitPayAmountList,
		            backgroundColor: [
		                'rgba(255, 153, 204, 0.5)',
		                'rgba(255, 153, 204, 0.5)',
		                'rgba(255, 153, 204, 0.5)',
		                'rgba(255, 153, 204, 0.5)',
		                'rgba(255, 153, 204, 0.5)',
		                'rgba(255, 153, 204, 0.5)'
		            ],
		            borderColor: [
		                'rgba(255, 153, 204, 1)',
		                'rgba(255, 153, 204, 1)',
		                'rgba(255, 153, 204, 1)',
		                'rgba(255, 153, 204, 1)',
		                'rgba(255, 153, 204, 1)',
		                'rgba(255, 153, 204, 1)'
		            ],
		            borderWidth: 1
		        },
		        {
		            label: '支付失败额度',
		            data: failedPayAmountList,
		            backgroundColor: [
		                'rgba(51, 204, 102, 0.5)',
		                'rgba(51, 204, 102, 0.5)',
		                'rgba(51, 204, 102, 0.5)',
		                'rgba(51, 204, 102, 0.5)',
		                'rgba(51, 204, 102, 0.5)',
		                'rgba(51, 204, 102, 0.5)'
		            ],
		            borderColor: [
		                'rgba(51, 204, 102, 1)',
		                'rgba(51, 204, 102, 1)',
		                'rgba(51, 204, 102, 1)',
		                'rgba(51, 204, 102, 1)',
		                'rgba(51, 204, 102, 1)',
		                'rgba(51, 204, 102, 1)'
		            ],
		            borderWidth: 1
		        }
		        ]
		    },
		    options: {
		    	title: {
                    display: true,
                    text: '商城用户订单支付统计'
                },
		        scales: {
		            yAxes: [{
		                ticks: {
		                    beginAtZero:true
		                }
		            }]
		        }
		    }
            };
    return barChartAmountConfigByApplication;
}

$('#label_chart label').hover(function() {
	$(this).addClass("hover-blue");
}, function() {
	$(this).removeClass("hover-blue");
});

$('#label_chart label').on("click", function() {
	var pageNo = document.getElementById("pageNo").value;
	initStatistics(pageNo,$(this).attr('value'));
	var pageNoByType = document.getElementById("pageNoByType").value;
	initStatisticsByType(pageNoByType,$(this).attr('value'));
	var pageNoByAppAndType = document.getElementById("pageNoByAppAndType").value;
	initStatisticsByAppAndType(pageNoByAppAndType,$(this).attr('value'));
});

function goPages(pno, pageSize,type) {
	var num = document.getElementById("totalCounts").value;
	var totalPage = 0;// 总页数
	// 总共分几页
	if (num / pageSize > parseInt(num / pageSize)) {
		totalPage = parseInt(num / pageSize) + 1;
	} else {
		totalPage = parseInt(num / pageSize);
	}
	var currentPage = pno;// 当前页数
	var tempStr = "";

	if (currentPage > 1) {
		tempStr += "<li class='page-first'><a href=\"javascript:void(0)\" onclick=\"initStatistics(" + (1) +","+(type)+ ")\">«</a></li>";
		tempStr += "<li class='page-pre'><a href=\"javascript:void(0)\" onclick=\"initStatistics(" + (currentPage - 1) +","+(type)+ ")\">‹</a></li>";
	} else {
		tempStr += "<li class='page-first'><a href=\"javascript:void(0)\">«</a></li>";
		tempStr += "<li class='page-pre'><a href=\"javascript:void(0)\">‹</a></li>";
	}

	for (var pageIndex = 1; pageIndex < totalPage + 1; pageIndex++) {
		tempStr += "<li class='page-number'><a href=\"javascript:void(0)\" onclick=\"initStatistics(" + pageIndex +","+(type)+ ")\">" + pageIndex + "</a></li>";
	}

	if (currentPage < totalPage) {
		tempStr += "</li><li class='page-next'><a href=\"javascript:void(0)\" onclick=\"initStatistics(" + (currentPage + 1) +","+(type)+ ")\" >›</a></li>";
		tempStr += "<li class='page-last'><a href=\"javascript:void(0)\" onclick=\"initStatistics(" + (totalPage) +","+(type)+ ")\">»</a></li>";
	} else {
		tempStr += "</li><li class='page-next'><a href=\"javascript:void(0)\">›</a></li>";
		tempStr += "<li class='page-last'><a href=\"javascript:void(0)\">»</a></li>";
	}

	document.getElementById("pageLine").innerHTML = tempStr;
}

function goPagesByType(pno, pageSize,type) {
	var num = document.getElementById("totalCountsByType").value;
	var totalPage = 0;// 总页数
	// 总共分几页
	if (num / pageSize > parseInt(num / pageSize)) {
		totalPage = parseInt(num / pageSize) + 1;
	} else {
		totalPage = parseInt(num / pageSize);
	}
	var currentPage = pno;// 当前页数
	var tempStr = "";

	if (currentPage > 1) {
		tempStr += "<li class='page-first'><a href=\"javascript:void(0)\" onclick=\"initStatisticsByType(" + (1) +","+(type)+ ")\">«</a></li>";
		tempStr += "<li class='page-pre'><a href=\"javascript:void(0)\" onclick=\"initStatisticsByType(" + (currentPage - 1) +","+(type)+ ")\">‹</a></li>";
	} else {
		tempStr += "<li class='page-first'><a href=\"javascript:void(0)\">«</a></li>";
		tempStr += "<li class='page-pre'><a href=\"javascript:void(0)\">‹</a></li>";
	}

	for (var pageIndex = 1; pageIndex < totalPage + 1; pageIndex++) {
		tempStr += "<li class='page-number'><a href=\"javascript:void(0)\" onclick=\"initStatisticsByType(" + pageIndex +","+(type)+ ")\">" + pageIndex + "</a></li>";
	}

	if (currentPage < totalPage) {
		tempStr += "</li><li class='page-next'><a href=\"javascript:void(0)\" onclick=\"initStatisticsByType(" + (currentPage + 1) +","+(type)+ ")\" >›</a></li>";
		tempStr += "<li class='page-last'><a href=\"javascript:void(0)\" onclick=\"initStatisticsByType(" + (totalPage) +","+(type)+ ")\">»</a></li>";
	} else {
		tempStr += "</li><li class='page-next'><a href=\"javascript:void(0)\">›</a></li>";
		tempStr += "<li class='page-last'><a href=\"javascript:void(0)\">»</a></li>";
	}

	document.getElementById("pageLineByType").innerHTML = tempStr;
}

function goPagesByAppAndType(pno, pageSize,type) {
	var num = document.getElementById("totalCountsByAppAndType").value;
	var totalPage = 0;// 总页数
	// 总共分几页
	if (num / pageSize > parseInt(num / pageSize)) {
		totalPage = parseInt(num / pageSize) + 1;
	} else {
		totalPage = parseInt(num / pageSize);
	}
	var currentPage = pno;// 当前页数
	var tempStr = "";

	if (currentPage > 1) {
		tempStr += "<li class='page-first'><a href=\"javascript:void(0)\" onclick=\"initStatisticsByAppAndType(" + (1) +","+(type)+ ")\">«</a></li>";
		tempStr += "<li class='page-pre'><a href=\"javascript:void(0)\" onclick=\"initStatisticsByAppAndType(" + (currentPage - 1) +","+(type)+ ")\">‹</a></li>";
	} else {
		tempStr += "<li class='page-first'><a href=\"javascript:void(0)\">«</a></li>";
		tempStr += "<li class='page-pre'><a href=\"javascript:void(0)\">‹</a></li>";
	}

	for (var pageIndex = 1; pageIndex < totalPage + 1; pageIndex++) {
		tempStr += "<li class='page-number'><a href=\"javascript:void(0)\" onclick=\"initStatisticsByAppAndType(" + pageIndex +","+(type)+ ")\">" + pageIndex + "</a></li>";
	}

	if (currentPage < totalPage) {
		tempStr += "</li><li class='page-next'><a href=\"javascript:void(0)\" onclick=\"initStatisticsByAppAndType(" + (currentPage + 1) +","+(type)+ ")\" >›</a></li>";
		tempStr += "<li class='page-last'><a href=\"javascript:void(0)\" onclick=\"initStatisticsByAppAndType(" + (totalPage) +","+(type)+ ")\">»</a></li>";
	} else {
		tempStr += "</li><li class='page-next'><a href=\"javascript:void(0)\">›</a></li>";
		tempStr += "<li class='page-last'><a href=\"javascript:void(0)\">»</a></li>";
	}

	document.getElementById("pageLineByAppAndType").innerHTML = tempStr;
}
