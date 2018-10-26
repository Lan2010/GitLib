$(function () {

    initStatistics(0);
    
    initStatisticsByType(0);
    
    initStatisticsBar(1,0);
    
});

function initStatistics(type){
    ajaxUtil.ajax('statistics/userStarPointIncrement/chart', {type:type}, "GET", function (data) {
        if(data.code === 200){
            initUserStarPointIncrementStatisticsPie(data);
            initUserStarPointIncrementStatisticsLine(data.data.list[2], chartUtil.userStarPointIncrement, '时', '值', data.data.list[3], data.data.object);
        }
    }, null, null, null, null, false);
}

function initStatisticsByType(type){
    ajaxUtil.ajax('statistics/userStarPointIncrement/lineChart', {type:type}, "GET", function (data) {
        if(data.code === 200){
            initUserStarPointIncrementStatisticsPieByType(data);
            initUserStarPointIncrementStatisticsLineByType(data.data.list[2], chartUtil.userStarPointIncrement, '时', '值', data.data.list[3], data.data.object);
        }
    }, null, null, null, null, false);
}

function initUserStarPointIncrementStatisticsPie(data){
    $('#canvas-holder-application-pie').empty();
    $('#canvas-holder-application-pie').append('<canvas id="canvas-holder-application-pie-chart" width="300" height="300" />');
    chartUtil.applicationPieChartLabels = data.data.list[0];
    var applicationStatisticsPieConfig = pieConfig(data.data.list[1], chartUtil.userStarPointIncrement);
    var pie_ctx = document.getElementById("canvas-holder-application-pie-chart").getContext("2d");
    window.applicationPie = new Chart(pie_ctx, applicationStatisticsPieConfig);
    window.applicationPie.update();
}

function initUserStarPointIncrementStatisticsPieByType(data){
    $('#canvas-holder-userStarPoint-pie').empty();
    $('#canvas-holder-userStarPoint-pie').append('<canvas id="canvas-holder-userStarPoint-pie-chart" width="300" height="300" />');
    chartUtil.applicationPieChartLabels = data.data.list[0];
    var applicationStatisticsPieConfig = pieConfig(data.data.list[1], chartUtil.applicationPieChartTitle);
    var pie_ctx = document.getElementById("canvas-holder-userStarPoint-pie-chart").getContext("2d");
    window.applicationPie = new Chart(pie_ctx, applicationStatisticsPieConfig);
    window.applicationPie.update();
}

function initUserStarPointIncrementStatisticsLine(data, title, xAxesLabelStr, yAxesLabelStr, total, xLine ){
    $('#canvas-holder-application-line').empty();
    $('#canvas-holder-application-line').append('<canvas id="canvas-holder-application-line-chart"/>');
    var initUserStarPointIncrementStatisticsLineConfig = lineConfig(data, total, xLine, title, xAxesLabelStr, yAxesLabelStr);
    var line_ctx = document.getElementById("canvas-holder-application-line-chart").getContext("2d");
    window.applicationLine = new Chart(line_ctx, initUserStarPointIncrementStatisticsLineConfig);
    initUserStarPointIncrementStatisticsLineConfig.options.scales.yAxes[0].ticks.suggestedMax = total;
    window.applicationLine.update();
}

function initUserStarPointIncrementStatisticsLineByType(data, title, xAxesLabelStr, yAxesLabelStr, total, xLine ){
    $('#canvas-holder-userStarPoint-line').empty();
    $('#canvas-holder-userStarPoint-line').append('<canvas id="canvas-holder-userStarPoint-line-chart"/>');
    var initUserStarPointIncrementStatisticsLineConfig = lineConfig(data, total, xLine, title, xAxesLabelStr, yAxesLabelStr);
    var line_ctx = document.getElementById("canvas-holder-userStarPoint-line-chart").getContext("2d");
    window.applicationLine = new Chart(line_ctx, initUserStarPointIncrementStatisticsLineConfig);
    initUserStarPointIncrementStatisticsLineConfig.options.scales.yAxes[0].ticks.suggestedMax = total;
    window.applicationLine.update();
}

function pieConfig(data, title){
    var pie_config = {
        type: 'pie',
        data: {
            datasets: [{
                data: data,
                backgroundColor: chartUtil.applicationPieChartBackground
            }],
            labels: chartUtil.applicationPieChartLabels
        },
        options: {
            responsive: true,
            title: {
                display: true,
                text: title
            }
        }
    };
    return pie_config;
}

function lineConfig(data, maxCount, xLine, title, xAxesLabelStr, yAxesLabelStr) {
    var lineConfig = {
        type: 'line',
        data: {
            labels: xLine,
            datasets: data
        },
        options: {
            responsive: true,
            title: {
                display: true,
                text: title
            },
            tooltips: {
                mode: 'label',
                callbacks: {}
            },
            hover: {
                mode: 'dataset'
            },
            scales: {
                xAxes: [{
                    display: true,
                    scaleLabel: {
                        display: false,
                        labelString: xAxesLabelStr
                    }
                }],
                yAxes: [{
                    display: true,
                    scaleLabel: {
                        display: false,
                        labelString: yAxesLabelStr
                    },
                    ticks: {
                        suggestedMin: 0,
                        suggestedMax: maxCount
                    }
                }]
            }
        }
    };
    $.each(lineConfig.data.datasets, function(i, dataset) {
        dataset.borderColor = chartUtil.applicationLineChartStyle[i].borderColor;
        dataset.backgroundColor = chartUtil.applicationLineChartStyle[i].backgroundColor;
        dataset.pointBorderColor = chartUtil.applicationLineChartStyle[i].pointBorderColor;
        dataset.pointBackgroundColor = chartUtil.applicationLineChartStyle[i].pointBackgroundColor;
        dataset.pointBorderWidth = chartUtil.applicationLineChartStyle[i].pointBorderWidth;
    });
    return lineConfig;

}

$('#label_chart label').hover(function(){$(this).addClass("hover-blue");},function() {$(this).removeClass("hover-blue");});

$('#label_chart label').on("click", function(){
    initStatistics($(this).attr('value'));
    initStatisticsByType($(this).attr('value'));
});

function initStatisticsBar (pageNo,type){
	var pageSize = 5;
    ajaxUtil.ajax('statistics/userStarPointIncrement/detailChart', {pageNo:pageNo,pageSize:pageSize,type:type}, "GET", function (data) {
        if(data.code === 200){
        	var totalCount =data.data.total;
        	if(totalCount>0){
        		$("#pageDiv").attr("style","display:block;");
        	}
            initUserAdvertisementStatisticsBar(data);
            document.getElementById("totalsSpan").innerHTML='总共 '+totalCount+'条记录';
            $("#totalCounts").val(data.data.total);
            $("#pageNo").val(pageNo);
            goPages(pageNo,pageSize,type);
        }
    }, null, null, null, null, false);
}

function initUserAdvertisementStatisticsBar(data){
    $('#canvas-holder-user-advertisement-scatter-bar').empty();
    $('#canvas-holder-user-advertisement-scatter-bar').append('<canvas id="canvas-holder-user-advertisement-scatter-bar-chart" height="130px"  />');
    var userLoginStatisticsPieConfig = barChartConfig(data.data.list[0],data.data.list[1],data.data.list[2],data.data.list[3]);
    var pie_ctx = document.getElementById("canvas-holder-user-advertisement-scatter-bar-chart").getContext("2d");
    window.userLoginPie =  new Chart(pie_ctx,userLoginStatisticsPieConfig);
    window.userLoginPie.update();
}

function barChartConfig(labels,incrementCountDown,incrementCountTask,incrementCountAdv){
	var barChartConfig = {
			type: 'bar',
		    data: {
		        labels: labels,
		        datasets: [{
		            label: '增长类型(掉落)',
		            data: incrementCountDown,
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
		            label: '增长类型(任务)',
		            data: incrementCountTask,
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
		            label: '增长类型(广告)',
		            data: incrementCountAdv,
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
                    text: '用户星点增长统计'
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
    return barChartConfig;
}

$('#label_chart_y label').hover(function() {
	$(this).addClass("hover-blue");
}, function() {
	$(this).removeClass("hover-blue");
});

$('#label_chart_y label').on("click", function() {
	var pageNo = document.getElementById("pageNo").value;
	initStatisticsBar(pageNo,$(this).attr('value'));
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
		tempStr += "<li class='page-first'><a href=\"javascript:void(0)\" onclick=\"initStatisticsBar(" + (1) +","+(type)+ ")\">«</a></li>";
		tempStr += "<li class='page-pre'><a href=\"javascript:void(0)\" onclick=\"initStatisticsBar(" + (currentPage - 1) +","+(type)+ ")\">‹</a></li>";
	} else {
		tempStr += "<li class='page-first'><a href=\"javascript:void(0)\">«</a></li>";
		tempStr += "<li class='page-pre'><a href=\"javascript:void(0)\">‹</a></li>";
	}

	for (var pageIndex = 1; pageIndex < totalPage + 1; pageIndex++) {
		tempStr += "<li class='page-number'><a href=\"javascript:void(0)\" onclick=\"initStatisticsBar(" + pageIndex +","+(type)+ ")\">" + pageIndex + "</a></li>";
	}

	if (currentPage < totalPage) {
		tempStr += "</li><li class='page-next'><a href=\"javascript:void(0)\" onclick=\"initStatistics(" + (currentPage + 1) +","+(type)+ ")\" >›</a></li>";
		tempStr += "<li class='page-last'><a href=\"javascript:void(0)\" onclick=\"initStatisticsBar(" + (totalPage) +","+(type)+ ")\">»</a></li>";
	} else {
		tempStr += "</li><li class='page-next'><a href=\"javascript:void(0)\">›</a></li>";
		tempStr += "<li class='page-last'><a href=\"javascript:void(0)\">»</a></li>";
	}

	document.getElementById("pageLine").innerHTML = tempStr;
}