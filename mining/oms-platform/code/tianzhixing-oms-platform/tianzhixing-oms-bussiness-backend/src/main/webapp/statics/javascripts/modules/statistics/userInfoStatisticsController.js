$(function () {

    initStatistics(0);

    initUserChannelStatistics(0);

    function initStatistics(type){
        ajaxUtil.ajax('statistics/userinfo/chart', {type:type}, "GET", function (data) {
            if(data.code === 200){
                initUserInfoStatisticsPie(data);
                initUserInfoChannelStatisticsPie(data);
                initUserInfoStatisticsLine(data.data.list[3], chartUtil.userInfoPieChartTitle, '时', '值', data.data.list[2], data.data.object);
                initUserInfoChannelStatisticsLine(data.data.list[7], chartUtil.userInfoPieChartChannelTitle, '时', '值', data.data.list[6], data.data.object);
            }
        }, null, null, null, null, false);
    }

    function initUserChannelStatistics(type){
        ajaxUtil.ajax('statistics/userinfo/scatter/chart', {type:type}, "GET", function (data) {
            if(data.code === 200){
                initUserInfoChannelStatisticsScatterLine(data.data.list[1], chartUtil.userInfoPieChartChannelTitle, '时', '值', data.data.list[0], data.data.object);
            }
        }, null, null, null, null, false);
    }

    function initUserInfoStatisticsPie(data){
        $('#canvas-holder-userinfo-pie').empty();
        $('#canvas-holder-userinfo-pie').append('<canvas id="canvas-holder-userinfo-pie-chart" width="300" height="300" />');
        chartUtil.userInfoPieChartLabels = data.data.list[0];
        var userInfoStatisticsPieConfig = pieConfig(data.data.list[1], chartUtil.userInfoPieChartTitle, chartUtil.userInfoPieChartLabels);
        var pie_ctx = document.getElementById("canvas-holder-userinfo-pie-chart").getContext("2d");
        window.userInfoPie = new Chart(pie_ctx, userInfoStatisticsPieConfig);
        window.userInfoPie.update();
    }

    function initUserInfoStatisticsLine(data, title, xAxesLabelStr, yAxesLabelStr, total, xLine ){
        $('#canvas-holder-userinfo-line').empty();
        $('#canvas-holder-userinfo-line').append('<canvas id="canvas-holder-userinfo-line-chart"/>');
        var initUserInfoStatisticsLineConfig = lineConfig(data, total, xLine, title, xAxesLabelStr, yAxesLabelStr, false);
        var line_ctx = document.getElementById("canvas-holder-userinfo-line-chart").getContext("2d");
        window.userInfoLine = new Chart(line_ctx, initUserInfoStatisticsLineConfig);
        initUserInfoStatisticsLineConfig.options.scales.yAxes[0].ticks.suggestedMax = total;
        window.userInfoLine.update();
    }

    function initUserInfoChannelStatisticsPie(data){
        $('#canvas-holder-userinfo-channel-pie').empty();
        $('#canvas-holder-userinfo-channel-pie').append('<canvas id="canvas-holder-userinfo-channel-pie-chart" width="300" height="300" />');
        chartUtil.userInfoChannelPieChartLabels = data.data.list[4];
        var userInfoChannelStatisticsPieConfig = pieConfig(data.data.list[5], chartUtil.userInfoPieChartChannelTitle, chartUtil.userInfoChannelPieChartLabels);
        var pie_ctx = document.getElementById("canvas-holder-userinfo-channel-pie-chart").getContext("2d");
        window.userInfoChannelPie = new Chart(pie_ctx, userInfoChannelStatisticsPieConfig);
        window.userInfoChannelPie.update();
    }

    function initUserInfoChannelStatisticsLine(data, title, xAxesLabelStr, yAxesLabelStr, total, xLine ){
        $('#canvas-holder-userinfo-channel-line').empty();
        $('#canvas-holder-userinfo-channel-line').append('<canvas id="canvas-holder-userinfo-channel-line-chart"/>');
        var initUserInfoChannelStatisticsLineConfig = lineConfig(data, total, xLine, title, xAxesLabelStr, yAxesLabelStr, false);
        var line_ctx = document.getElementById("canvas-holder-userinfo-channel-line-chart").getContext("2d");
        window.userInfoChannelLine = new Chart(line_ctx, initUserInfoChannelStatisticsLineConfig);
        initUserInfoChannelStatisticsLineConfig.options.scales.yAxes[0].ticks.suggestedMax = total;
        window.userInfoChannelLine.update();
    }

    function initUserInfoChannelStatisticsScatterLine(data, title, xAxesLabelStr, yAxesLabelStr, total, xLine ){
        $('#canvas-holder-userinfo-channel-scatter-line').empty();
        $('#canvas-holder-userinfo-channel-scatter-line').append('<canvas id="canvas-holder-userinfo-channel-scatter-line-chart"/>');
        var initUserInfoChannelStatisticsScatterLineConfig = lineConfig(data, total, xLine, title, xAxesLabelStr, yAxesLabelStr, true);
        var line_ctx = document.getElementById("canvas-holder-userinfo-channel-scatter-line-chart").getContext("2d");
        window.userInfoChannelScatterLine = new Chart(line_ctx, initUserInfoChannelStatisticsScatterLineConfig);
        initUserInfoChannelStatisticsScatterLineConfig.options.scales.yAxes[0].ticks.suggestedMax = total;
        window.userInfoChannelScatterLine.update();
    }

    function pieConfig(data, title, labels){
        var pie_config = {
            type: 'pie',
            data: {
                datasets: [{
                    data: data,
                    backgroundColor: chartUtil.applicationPieChartBackground
                }],
                labels: labels
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

    function lineConfig(data, maxCount, xLine, title, xAxesLabelStr, yAxesLabelStr, randomC) {
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
            if(randomC){
                var color = randomColor(0.3);
                dataset.borderColor = color;
                dataset.backgroundColor = color;
                dataset.pointBorderColor = color;
                dataset.pointBackgroundColor = color;
                dataset.pointBorderWidth = 1;
            }else{
                dataset.borderColor = chartUtil.applicationLineChartStyle[i].borderColor;
                dataset.backgroundColor = chartUtil.applicationLineChartStyle[i].backgroundColor;
                dataset.pointBorderColor = chartUtil.applicationLineChartStyle[i].pointBorderColor;
                dataset.pointBackgroundColor = chartUtil.applicationLineChartStyle[i].pointBackgroundColor;
                dataset.pointBorderWidth = chartUtil.applicationLineChartStyle[i].pointBorderWidth;
            }

        });
        return lineConfig;

    }

    function scatterLineConfig(data, maxCount, xLine, title, xAxesLabelStr, yAxesLabelStr) {
        var lineConfig = {
            type: 'scatter',
            data: {
               // labels: xLine,
                datasets: data
            },
            options: {
                title: {
                    display: true,
                    text: 'Chart.js Scatter Chart'
                },
                scales: {
                    xAxes: [{
                        position: 'bottom',
                        gridLines: {
                            zeroLineColor: "rgba(0,255,0,1)"
                        },
                        ticks: {
                            userCallback: function (label, index, labels) {
                                return label+"/";
                            }


                        },
                        scaleLabel: {
                            display: true,
                            labelString: 'x axis'
                        }
                    }],
                    yAxes: [{
                        position: 'right',
                        gridLines: {
                            zeroLineColor: "rgba(0,255,0,1)"
                        },
                        scaleLabel: {
                            display: true,
                            labelString: 'y axis'
                        }
                    }]
                }
            }
        };
        $.each(lineConfig.data.datasets, function(i, dataset) {
            //dataset.borderColor = chartUtil.applicationLineChartStyle[i].borderColor;
            //dataset.backgroundColor = chartUtil.applicationLineChartStyle[i].backgroundColor;
            //dataset.pointBorderColor = chartUtil.applicationLineChartStyle[i].pointBorderColor;
            //dataset.pointBackgroundColor = chartUtil.applicationLineChartStyle[i].pointBackgroundColor;
            //dataset.pointBorderWidth = chartUtil.applicationLineChartStyle[i].pointBorderWidth;
        });
        return lineConfig;

    }

    $('#label_chart label').hover(function(){$(this).addClass("hover-blue");},function() {$(this).removeClass("hover-blue");});

    $('#label_chart label').on("click", function(){
        initStatistics($(this).attr('value'));
        initUserChannelStatistics($(this).attr('value'));
    });

    function randomColor(opacity) {
        return 'rgba(' + Math.round(Math.random() * 255) + ',' + Math.round(Math.random() * 255) + ',' + Math.round(Math.random() * 255) + ',' + (opacity || '.3') + ')';
    }


});
