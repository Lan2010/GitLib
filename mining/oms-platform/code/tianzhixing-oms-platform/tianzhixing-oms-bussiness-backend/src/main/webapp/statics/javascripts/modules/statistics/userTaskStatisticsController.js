$(function() {

	initStatistics(1);

	initTaskStatistics(0);

});

function initStatistics(pageNo) {
	var pageSize = 5;
	ajaxUtil.ajax('statistics/userTask/chart', {
		pageNo : pageNo,
		pageSize : pageSize
	}, "GET", function(data) {
		if (data.code === 200) {
			var totalCount = data.data.total;
			if (totalCount > 0) {
				$("#pageDiv").attr("style", "display:block;");
			}
			initUserTaskStatisticsBar(data);
			document.getElementById("totalsSpan").innerHTML = '总共 ' + totalCount + '条记录';
			$("#totalCounts").val(data.data.total);
			goPage(pageNo, pageSize);
		}
	}, null, null, null, null, false);
}

function initTaskStatistics(type) {
	ajaxUtil.ajax('statistics/userTask/scatter', {
		type : type
	}, "GET", function(data) {
		if (data.code === 200) {
			initUserTaskStatisticsScatterLine(data.data.list[2], chartUtil.userTaskChartChannelTitle, '时', '值', data.data.list[1], data.data.object);
		}
	}, null, null, null, null, false);
}

function initUserTaskStatisticsBar(data) {
	$('#canvas-holder-user-task-scatter-bar').empty();
	$('#canvas-holder-user-task-scatter-bar').append('<canvas id="canvas-holder-user-task-scatter-bar-chart" height="130px"  />');
	var userLoginStatisticsPieConfig = barChartConfig(data.data.list[0], data.data.list[1], data.data.list[2]);
	var pie_ctx = document.getElementById("canvas-holder-user-task-scatter-bar-chart").getContext("2d");
	window.userLoginPie = new Chart(pie_ctx, userLoginStatisticsPieConfig);
	window.userLoginPie.update();
}

function initUserTaskStatisticsScatterLine(data, title, xAxesLabelStr, yAxesLabelStr, total, xLine) {
	$('#canvas-holder-userTask-channel-scatter-line').empty();
	$('#canvas-holder-userTask-channel-scatter-line').append('<canvas id="canvas-holder-userTask-channel-scatter-line-chart" height="130px"/>');
	var initUserTaskStatisticsScatterLineConfig = lineConfig(data, total, xLine, title, xAxesLabelStr, yAxesLabelStr, true);
	var line_ctx = document.getElementById("canvas-holder-userTask-channel-scatter-line-chart").getContext("2d");
	window.userTaskScatterLine = new Chart(line_ctx, initUserTaskStatisticsScatterLineConfig);
	initUserTaskStatisticsScatterLineConfig.options.scales.yAxes[0].ticks.suggestedMax = total;
	window.userTaskScatterLine.update();
}

function barChartConfig(labels, acceptCount, cancelCount) {
	var barChartConfig = {
		type : 'bar',
		data : {
			labels : labels,
			datasets : [ {
				label : '接受数量',
				data : acceptCount,
				backgroundColor : [ 'rgba(153, 204, 255, 0.5)', 'rgba(153, 204, 255, 0.5)', 'rgba(153, 204, 255, 0.5)', 'rgba(153, 204, 255, 0.5)', 'rgba(153, 204, 255, 0.5)', 'rgba(153, 204, 255, 0.5)' ],
				borderColor : [ 'rgba(153, 204, 255, 1)', 'rgba(153, 204, 255, 1)', 'rgba(153, 204, 255, 1)', 'rgba(153, 204, 255, 1)', 'rgba(153, 204, 255, 1)', 'rgba(153, 204, 255, 1)' ],
				borderWidth : 1
			}, {
				label : '取消数量',
				data : cancelCount,
				backgroundColor : [ 'rgba(255, 153, 204, 0.5)', 'rgba(255, 153, 204, 0.5)', 'rgba(255, 153, 204, 0.5)', 'rgba(255, 153, 204, 0.5)', 'rgba(255, 153, 204, 0.5)', 'rgba(255, 153, 204, 0.5)' ],
				borderColor : [ 'rgba(255, 153, 204, 1)', 'rgba(255, 153, 204, 1)', 'rgba(255, 153, 204, 1)', 'rgba(255, 153, 204, 1)', 'rgba(255, 153, 204, 1)', 'rgba(255, 153, 204, 1)' ],
				borderWidth : 1
			} ]
		},
		options : {
			title : {
				display : true,
				text : '用户任务统计'
			},
			scales : {
				yAxes : [ {
					ticks : {
						beginAtZero : true
					}
				} ]
			}
		}
	};
	return barChartConfig;
}

function lineConfig(data, maxCount, xLine, title, xAxesLabelStr, yAxesLabelStr, randomC) {
	var lineConfig = {
		type : 'line',
		data : {
			labels : xLine,
			datasets : data
		},
		options : {
			responsive : true,
			title : {
				display : true,
				text : title
			},
			tooltips : {
				mode : 'label',
				callbacks : {}
			},
			hover : {
				mode : 'dataset'
			},
			scales : {
				xAxes : [ {
					display : true,
					scaleLabel : {
						display : false,
						labelString : xAxesLabelStr
					}
				} ],
				yAxes : [ {
					display : true,
					scaleLabel : {
						display : false,
						labelString : yAxesLabelStr
					},
					ticks : {
						suggestedMin : 0,
						suggestedMax : maxCount
					}
				} ]
			}
		}
	};
	$.each(lineConfig.data.datasets, function(i, dataset) {
		if (randomC) {
			var color = randomColor(0.3);
			dataset.borderColor = color;
			dataset.backgroundColor = color;
			dataset.pointBorderColor = color;
			dataset.pointBackgroundColor = color;
			dataset.pointBorderWidth = 1;
		} else {
			dataset.borderColor = chartUtil.applicationLineChartStyle[i].borderColor;
			dataset.backgroundColor = chartUtil.applicationLineChartStyle[i].backgroundColor;
			dataset.pointBorderColor = chartUtil.applicationLineChartStyle[i].pointBorderColor;
			dataset.pointBackgroundColor = chartUtil.applicationLineChartStyle[i].pointBackgroundColor;
			dataset.pointBorderWidth = chartUtil.applicationLineChartStyle[i].pointBorderWidth;
		}

	});
	return lineConfig;

}

$('#label_chart label').hover(function() {
	$(this).addClass("hover-blue");
}, function() {
	$(this).removeClass("hover-blue");
});

$('#label_chart label').on("click", function() {
	initTaskStatistics($(this).attr('value'));
});

function randomColor(opacity) {
	return 'rgba(' + Math.round(Math.random() * 255) + ',' + Math.round(Math.random() * 255) + ',' + Math.round(Math.random() * 255) + ',' + (opacity || '.3') + ')';
}
