$(function () {

    initStatistics(0);

    function initStatistics(type){
        ajaxUtil.ajax('statistics/userLoginStatus/chart', {type:type}, "GET", function (data) {
            if(data.code === 200){
                initUserLoginStatisticsPie(data);
                initUserLoginStatisticsDiffIPPie(data);
                initUserLoginStatisticsDiffIPPie1(data);
                initUserLoginStatisticsLine(data.data.list[4], chartUtil.userLoginPieChartTitle, '时', '值', data.data.list[7], data.data.object);
                initUserLoginStatisticsDiffIPLine(data.data.list[5], chartUtil.userLoginPieChartDiffIPTitle, '时', '值', data.data.list[8], data.data.object);
                initUserLoginOnlineStatisticsDiffIPLine(data.data.list[6], chartUtil.userOnlinePieChartDiffIPTitle, '时', '值', data.data.list[9], data.data.object);
            }
        }, null, null, null, null, false);
    }

    function initUserLoginStatisticsPie(data){
        $('#canvas-holder-userLogin-pie').empty();
        $('#canvas-holder-userLogin-pie').append('<canvas id="canvas-holder-userLogin-pie-chart" width="300" height="300" />');
        chartUtil.applicationPieChartLabels = data.data.list[0];
        var userLoginStatisticsPieConfig = pieConfig(data.data.list[1], chartUtil.userLoginPieChartTitle);
        var pie_ctx = document.getElementById("canvas-holder-userLogin-pie-chart").getContext("2d");
        window.userLoginPie = new Chart(pie_ctx, userLoginStatisticsPieConfig);
        window.userLoginPie.update();
    }
    function initUserLoginStatisticsDiffIPPie(data){
        $('#canvas-holder-userLogin-diffip-pie').empty();
        $('#canvas-holder-userLogin-diffip-pie').append('<canvas id="canvas-holder-userLogin-diffip-pie-chart" width="300" height="300" />');
        chartUtil.applicationPieChartLabels = data.data.list[0];
        var userLoginStatisticsPieConfig = pieConfig(data.data.list[2], chartUtil.userLoginPieChartDiffIPTitle);
        var pie_ctx = document.getElementById("canvas-holder-userLogin-diffip-pie-chart").getContext("2d");
        window.userLoginDiffIPPie = new Chart(pie_ctx, userLoginStatisticsPieConfig);
        window.userLoginDiffIPPie.update();
    }
    
    function initUserLoginStatisticsDiffIPPie1(data){
        $('#canvas-holder-userOnline-pie').empty();
        $('#canvas-holder-userOnline-pie').append('<canvas id="canvas-holder-userLogin-diffip-pie-chart1" width="300" height="300" />');
        chartUtil.applicationPieChartLabels = data.data.list[0];
        var userLoginStatisticsPieConfig = pieConfig(data.data.list[3], chartUtil.userOnlinePieChartDiffIPTitle);
        var pie_ctx = document.getElementById("canvas-holder-userLogin-diffip-pie-chart1").getContext("2d");
        window.userLoginDiffIPPie = new Chart(pie_ctx, userLoginStatisticsPieConfig);
        window.userLoginDiffIPPie.update();
    }

    function initUserLoginStatisticsLine(data, title, xAxesLabelStr, yAxesLabelStr, total, xLine ){
        $('#canvas-holder-userLogin-line').empty();
        $('#canvas-holder-userLogin-line').append('<canvas id="canvas-holder-userLogin-line-chart"/>');
        var initUserLoginStatisticsLineConfig = lineConfig(data, total, xLine, title, xAxesLabelStr, yAxesLabelStr);
        var line_ctx = document.getElementById("canvas-holder-userLogin-line-chart").getContext("2d");
        window.userLoginLine = new Chart(line_ctx, initUserLoginStatisticsLineConfig);
        initUserLoginStatisticsLineConfig.options.scales.yAxes[0].ticks.suggestedMax = total;
        window.userLoginLine.update();
    }

    function initUserLoginStatisticsDiffIPLine(data, title, xAxesLabelStr, yAxesLabelStr, total, xLine ){
        $('#canvas-holder-userLogin-diffip-line').empty();
        $('#canvas-holder-userLogin-diffip-line').append('<canvas id="canvas-holder-userLogin-diffip-line-chart"/>');
        var initUserLoginStatisticsLineConfig = lineConfig(data, total, xLine, title, xAxesLabelStr, yAxesLabelStr);
        var line_ctx = document.getElementById("canvas-holder-userLogin-diffip-line-chart").getContext("2d");
        window.userLoginDiffIPLine = new Chart(line_ctx, initUserLoginStatisticsLineConfig);
        initUserLoginStatisticsLineConfig.options.scales.yAxes[0].ticks.suggestedMax = total;
        window.userLoginDiffIPLine.update();
    }
    
    function initUserLoginOnlineStatisticsDiffIPLine(data, title, xAxesLabelStr, yAxesLabelStr, total, xLine ){
        $('#canvas-holder-userOnline-line').empty();
        $('#canvas-holder-userOnline-line').append('<canvas id="canvas-holder-userOnline-line-chart"/>');
        var initUserLoginStatisticsLineConfig = lineConfig(data, total, xLine, title, xAxesLabelStr, yAxesLabelStr);
        var line_ctx = document.getElementById("canvas-holder-userOnline-line-chart").getContext("2d");
        window.userLoginDiffIPLine = new Chart(line_ctx, initUserLoginStatisticsLineConfig);
        initUserLoginStatisticsLineConfig.options.scales.yAxes[0].ticks.suggestedMax = total;
        window.userLoginDiffIPLine.update();
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
