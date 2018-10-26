$(function () {

    initStatistics(0);

    function initStatistics(type){
        ajaxUtil.ajax('statistics/application/chart', {type:type}, "GET", function (data) {
            if(data.code === 200){
                initApplicationStatisticsPie(data);
                initApplicationStatisticsDiffIPPie(data);
                initApplicationStatisticsLine(data.data.list[3], chartUtil.applicationPieChartTitle, '时', '值', data.data.list[5], data.data.object);
                initApplicationStatisticsDiffIPLine(data.data.list[4], chartUtil.applicationPieChartDiffIPTitle, '时', '值', data.data.list[6], data.data.object);
            }
        }, null, null, null, null, false);
    }

    function initApplicationStatisticsPie(data){
        $('#canvas-holder-application-pie').empty();
        $('#canvas-holder-application-pie').append('<canvas id="canvas-holder-application-pie-chart" width="300" height="300" />');
        chartUtil.applicationPieChartLabels = data.data.list[0];
        var applicationStatisticsPieConfig = pieConfig(data.data.list[1], chartUtil.applicationPieChartTitle);
        var pie_ctx = document.getElementById("canvas-holder-application-pie-chart").getContext("2d");
        window.applicationPie = new Chart(pie_ctx, applicationStatisticsPieConfig);
        window.applicationPie.update();
    }
    function initApplicationStatisticsDiffIPPie(data){
        $('#canvas-holder-application-diffip-pie').empty();
        $('#canvas-holder-application-diffip-pie').append('<canvas id="canvas-holder-application-diffip-pie-chart" width="300" height="300" />');
        chartUtil.applicationPieChartLabels = data.data.list[0];
        var applicationStatisticsPieConfig = pieConfig(data.data.list[2], chartUtil.applicationPieChartDiffIPTitle);
        var pie_ctx = document.getElementById("canvas-holder-application-diffip-pie-chart").getContext("2d");
        window.applicationDiffIPPie = new Chart(pie_ctx, applicationStatisticsPieConfig);
        window.applicationDiffIPPie.update();
    }

    function initApplicationStatisticsLine(data, title, xAxesLabelStr, yAxesLabelStr, total, xLine ){
        $('#canvas-holder-application-line').empty();
        $('#canvas-holder-application-line').append('<canvas id="canvas-holder-application-line-chart"/>');
        var initApplicationStatisticsLineConfig = lineConfig(data, total, xLine, title, xAxesLabelStr, yAxesLabelStr);
        var line_ctx = document.getElementById("canvas-holder-application-line-chart").getContext("2d");
        window.applicationLine = new Chart(line_ctx, initApplicationStatisticsLineConfig);
        initApplicationStatisticsLineConfig.options.scales.yAxes[0].ticks.suggestedMax = total;
        window.applicationLine.update();
    }

    function initApplicationStatisticsDiffIPLine(data, title, xAxesLabelStr, yAxesLabelStr, total, xLine ){
        $('#canvas-holder-application-diffip-line').empty();
        $('#canvas-holder-application-diffip-line').append('<canvas id="canvas-holder-application-diffip-line-chart"/>');
        var initApplicationStatisticsLineConfig = lineConfig(data, total, xLine, title, xAxesLabelStr, yAxesLabelStr);
        var line_ctx = document.getElementById("canvas-holder-application-diffip-line-chart").getContext("2d");
        window.applicationDiffIPLine = new Chart(line_ctx, initApplicationStatisticsLineConfig);
        initApplicationStatisticsLineConfig.options.scales.yAxes[0].ticks.suggestedMax = total;
        window.applicationDiffIPLine.update();
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
    });


});
