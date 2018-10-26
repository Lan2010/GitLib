$(function () {

    initStatistics(0);
    initPagesOperationStatistics(0);
    function initStatistics(type){
        ajaxUtil.ajax('statistics/pages/chart', {type:type}, "GET", function (data) {
            if(data.code === 200){
                initPagesOperationStatisticsPie(data);
                initPagesOperationStatisticsDiffIPPie(data);
                initPagesOperationStatisticsLine(data.data.list[3], chartUtil.pagesPieChartTitle, '时', '值', data.data.list[5], data.data.object);
                initPagesOperationStatisticsDiffIPLine(data.data.list[4], chartUtil.pagesPieChartDiffIPTitle, '时', '值', data.data.list[6], data.data.object);
            }
        }, null, null, null, null, false);
    }

    function initPagesOperationStatistics(type){
        ajaxUtil.ajax('statistics/pages/scatter', {type:type}, "GET", function (data) {
            if(data.code === 200){
                initPagesOperationStatisticsScatterLine(data.data.list[2],chartUtil.pagesPieChartTitle, '时', '值', data.data.list[1], data.data.object);
                initPagesOperationStatisticsScatterDiffIPLine(data.data.list[3],  chartUtil.pagesPieChartDiffIPTitle, '时', '值', data.data.list[0], data.data.object);
            }
        }, null, null, null, null, false);
    }
    function initPagesOperationStatisticsPie(data){
        $('#canvas-holder-pages-pie').empty();
        $('#canvas-holder-pages-pie').append('<canvas id="canvas-holder-pages-pie-chart" width="300" height="300" />');
        chartUtil.applicationPieChartLabels = data.data.list[0];
        var pagesOperationStatisticsPieConfig = pieConfig(data.data.list[1], chartUtil.pagesPieChartTitle);
        var pie_ctx = document.getElementById("canvas-holder-pages-pie-chart").getContext("2d");
        window.pagesPie = new Chart(pie_ctx, pagesOperationStatisticsPieConfig);
        window.pagesPie.update();
    }
    function initPagesOperationStatisticsDiffIPPie(data){
        $('#canvas-holder-pages-diffip-pie').empty();
        $('#canvas-holder-pages-diffip-pie').append('<canvas id="canvas-holder-pages-diffip-pie-chart" width="300" height="300" />');
        chartUtil.applicationPieChartLabels = data.data.list[0];
        var pagesOperationStatisticsPieConfig = pieConfig(data.data.list[2],chartUtil.pagesPieChartDiffIPTitle);
        var pie_ctx = document.getElementById("canvas-holder-pages-diffip-pie-chart").getContext("2d");
        window.pagesDiffIPPie = new Chart(pie_ctx, pagesOperationStatisticsPieConfig);
        window.pagesDiffIPPie.update();
    }

    function initPagesOperationStatisticsLine(data, title, xAxesLabelStr, yAxesLabelStr, total, xLine ){
        $('#canvas-holder-pages-line').empty();
        $('#canvas-holder-pages-line').append('<canvas id="canvas-holder-pages-line-chart"/>');
        var initPagesOperationStatisticsLineConfig = lineConfig(data, total, xLine, title, xAxesLabelStr, yAxesLabelStr);
        var line_ctx = document.getElementById("canvas-holder-pages-line-chart").getContext("2d");
        window.pagesLine = new Chart(line_ctx, initPagesOperationStatisticsLineConfig);
        initPagesOperationStatisticsLineConfig.options.scales.yAxes[0].ticks.suggestedMax = total;
        window.pagesLine.update();
    }

    function initPagesOperationStatisticsDiffIPLine(data, title, xAxesLabelStr, yAxesLabelStr, total, xLine ){
        $('#canvas-holder-device-alarm-diffip-line').empty();
        $('#canvas-holder-device-alarm-diffip-line').append('<canvas id="canvas-holder-device-alarm-diffip-line-chart"/>');
        var initPagesOperationStatisticsLineConfig = lineConfig(data, total, xLine, title, xAxesLabelStr, yAxesLabelStr);
        var line_ctx = document.getElementById("canvas-holder-device-alarm-diffip-line-chart").getContext("2d");
        window.pagesDiffIPLine = new Chart(line_ctx, initPagesOperationStatisticsLineConfig);
        initPagesOperationStatisticsLineConfig.options.scales.yAxes[0].ticks.suggestedMax = total;
        window.pagesDiffIPLine.update();
    }
    
    function initPagesOperationStatisticsScatterLine(data, title, xAxesLabelStr, yAxesLabelStr, total, xLine ){
        $('#canvas-holder-pages-channel-scatter-line').empty();
        $('#canvas-holder-pages-channel-scatter-line').append('<canvas id="canvas-holder-pages-channel-scatter-line-chart"/>');
        var initPagesOperationStatisticsScatterLineConfig = lineConfig(data, total, xLine, title, xAxesLabelStr, yAxesLabelStr);
        var line_ctx = document.getElementById("canvas-holder-pages-channel-scatter-line-chart").getContext("2d");
        window.pagesScatterLine = new Chart(line_ctx, initPagesOperationStatisticsScatterLineConfig);
        initPagesOperationStatisticsScatterLineConfig.options.scales.yAxes[0].ticks.suggestedMax = total;
        window.pagesScatterLine.update();
    }
    
    function initPagesOperationStatisticsScatterDiffIPLine(data, title, xAxesLabelStr, yAxesLabelStr, total, xLine ){
        $('#canvas-holder-pages-channel-scatter-diffip-line').empty();
        $('#canvas-holder-pages-channel-scatter-diffip-line').append('<canvas id="canvas-holder-pages-channel-scatter-diffip-line-chart"/>');
        var initPagesOperationStatisticsScatterLineConfig = lineConfig(data, total, xLine, title, xAxesLabelStr, yAxesLabelStr);
        var line_ctx = document.getElementById("canvas-holder-pages-channel-scatter-diffip-line-chart").getContext("2d");
        window.pagesScatterLine = new Chart(line_ctx, initPagesOperationStatisticsScatterLineConfig);
        initPagesOperationStatisticsScatterLineConfig.options.scales.yAxes[0].ticks.suggestedMax = total;
        window.pagesScatterLine.update();
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
        initPagesOperationStatistics($(this).attr('value'));
    });


});
