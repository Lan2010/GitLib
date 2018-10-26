$(function() {
	initUserGreeterCardStatistics(0);
	initUserPostCardStatistics(0);
});

function initUserGreeterCardStatistics(type) {
	ajaxUtil.ajax('statistics/userGreeterCard/chart', {
		type : type
	}, "GET", function(data) {
		if (data.code === 200) {
			initUserGreeterCardStatisticsScatterLine(data.data.list[0], chartUtil.userCardTitle, '时', '值', data.data.list[2], data.data.object,data.data.list[1]);
		}
	}, null, null, null, null, false);
}

function initUserPostCardStatistics(type) {
	ajaxUtil.ajax('statistics/userPostCard/postCardChart', {
		type : type
	}, "GET", function(data) {
		if (data.code === 200) {
			initUserPostCardStatisticsScatterLine(data.data.list[0], chartUtil.userCardTitle, '时', '值', data.data.list[2], data.data.object,data.data.list[1]);
		}
	}, null, null, null, null, false);
}

function initUserGreeterCardStatisticsScatterLine(data, title, xAxesLabelStr, yAxesLabelStr, total, xLine,data2) {
	$('#canvas-holder-userUserGreeterCard-channel-scatter-line').empty();
	$('#canvas-holder-userUserGreeterCard-channel-scatter-line').append('<canvas id="canvas-holder-userUserGreeterCard-channel-scatter-line-chart" height="130px"/>');
	var initUserGreeterCardStatisticsScatterLineConfig = lineConfig(data, total, xLine, title, xAxesLabelStr, yAxesLabelStr,data2);
	var line_ctx = document.getElementById("canvas-holder-userUserGreeterCard-channel-scatter-line-chart").getContext("2d");
	window.userUserGreeterCardScatterLine = new Chart(line_ctx, initUserGreeterCardStatisticsScatterLineConfig);
	initUserGreeterCardStatisticsScatterLineConfig.options.scales.yAxes[0].ticks.suggestedMax = total;
	window.userUserGreeterCardScatterLine.update();
}

function initUserPostCardStatisticsScatterLine(data, title, xAxesLabelStr, yAxesLabelStr, total, xLine,data2) {
	$('#canvas-holder-userUserPostCard-channel-scatter-line').empty();
	$('#canvas-holder-userUserPostCard-channel-scatter-line').append('<canvas id="canvas-holder-userUserPostCard-channel-scatter-line-chart" height="130px"/>');
	var initUserGreeterCardStatisticsScatterLineConfig = lineConfig(data, total, xLine, title, xAxesLabelStr, yAxesLabelStr,data2);
	var line_ctx = document.getElementById("canvas-holder-userUserPostCard-channel-scatter-line-chart").getContext("2d");
	window.userUserGreeterCardScatterLine = new Chart(line_ctx, initUserGreeterCardStatisticsScatterLineConfig);
	initUserGreeterCardStatisticsScatterLineConfig.options.scales.yAxes[0].ticks.suggestedMax = total;
	window.userUserGreeterCardScatterLine.update();
}

function lineConfig(data, maxCount, xLine, title, xAxesLabelStr, yAxesLabelStr,data2,randomC) {
	var lineConfig = {
		type : 'line',
		data : {
			labels : xLine,
			datasets : [ {
				label : "创建量", // 当前数据的说明
				fill : true, // 是否要显示数据部分阴影面积块 false:不显示
				data : data, // 填充的数据
			}, {
				label : "分享量", // 当前数据的说明
				fill : true, // 是否要显示数据部分阴影面积块 false:不显示
				data : data2, // 填充的数据
			} ]
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
						suggestedMin : 0
						//suggestedMax : 10000
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
	initUserGreeterCardStatistics($(this).attr('value'));
	initUserPostCardStatistics($(this).attr('value'));
});

function randomColor(opacity) {
	return 'rgba(' + Math.round(Math.random() * 255) + ',' + Math.round(Math.random() * 255) + ',' + Math.round(Math.random() * 255) + ',' + (opacity || '.3') + ')';
}
