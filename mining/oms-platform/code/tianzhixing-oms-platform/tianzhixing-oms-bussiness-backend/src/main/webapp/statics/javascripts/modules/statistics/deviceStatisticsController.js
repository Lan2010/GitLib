$(function() {

	initStatistics(0);

	initLineStatistics(0)

	function initStatistics(type) {
		ajaxUtil.ajax('statistics/device/chart', {
			type : type
		}, "GET", function(data) {
			if (data.code === 200) {
				initDevicePie(data);
				initDeviceLine(data.data.list[2], chartUtil.deviceChartTitle, '时', '值', data.data.list[3], data.data.object);
			}
		}, null, null, null, null, false);
	}

	function initLineStatistics(type) {
		ajaxUtil.ajax('statistics/device/lineChart', {
			type : type
		}, "GET", function(data) {
			if (data.code === 200) {
				initDeviceStatisticsLineByDeviceType(data.data.list[1], chartUtil.deviceChartTitle, '时', '值', data.data.list[0], data.data.object);
			}
		}, null, null, null, null, false);
	}

	function initDevicePie(data) {
		$('#canvas-holder-device-pie').empty();
		$('#canvas-holder-device-pie').append('<canvas id="canvas-holder-device-pie-chart" width="300" height="300" />');
		chartUtil.applicationPieChartLabels = data.data.list[0];
		var DeviceStatisticsPieConfig = pieConfig(data.data.list[1], chartUtil.deviceChartTitle);
		var pie_ctx = document.getElementById("canvas-holder-device-pie-chart").getContext("2d");
		window.userDevicePie = new Chart(pie_ctx, DeviceStatisticsPieConfig);
		window.userDevicePie.update();
	}

	function initDeviceLine(data, title, xAxesLabelStr, yAxesLabelStr, total, xLine) {
		$('#canvas-holder-device-line').empty();
		$('#canvas-holder-device-line').append('<canvas id="canvas-holder-device-line-chart"/>');
		var initDeviceLineConfig = lineConfig(data, total, xLine, title, xAxesLabelStr, yAxesLabelStr);
		var line_ctx = document.getElementById("canvas-holder-device-line-chart").getContext("2d");
		window.userDeviceLine = new Chart(line_ctx, initDeviceLineConfig);
		initDeviceLineConfig.options.scales.yAxes[0].ticks.suggestedMax = total;
		window.userDeviceLine.update();
	}

	function initDeviceStatisticsLineByDeviceType(data, title, xAxesLabelStr, yAxesLabelStr, total, xLine) {
		$('#canvas-holder-Device-line-ByDeviceType').empty();
		$('#canvas-holder-Device-line-ByDeviceType').append('<canvas id="canvas-holder-Device-line-ByDeviceType-chart" height="130px"/>');
		var initDeviceLineConfig = lineConfig(data, total, xLine, title, xAxesLabelStr, yAxesLabelStr, true);
		var line_ctx = document.getElementById("canvas-holder-Device-line-ByDeviceType-chart").getContext("2d");
		window.userDeviceDiffIPLine = new Chart(line_ctx, initDeviceLineConfig);
		initDeviceLineConfig.options.scales.yAxes[0].ticks.suggestedMax = total;
		window.userDeviceDiffIPLine.update();
	}

	function pieConfig(data, title) {
		var pie_config = {
			type : 'pie',
			data : {
				datasets : [ {
					data : data,
					backgroundColor : chartUtil.applicationPieChartBackground
				} ],
				labels : chartUtil.applicationPieChartLabels
			},
			options : {
				responsive : true,
				title : {
					display : true,
					text : title
				}
			}
		};
		return pie_config;
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
		initStatistics($(this).attr('value'));
		initLineStatistics($(this).attr('value'));
	});

	function randomColor(opacity) {
		return 'rgba(' + Math.round(Math.random() * 255) + ',' + Math.round(Math.random() * 255) + ',' + Math.round(Math.random() * 255) + ',' + (opacity || '.3') + ')';
	}

});
