$(function () {

    initStatistics(1);
    
});

function initStatistics (pageNo){
	var pageSize = 5;
    ajaxUtil.ajax('statistics/userAdvertisement/chart', {pageNo:pageNo,pageSize:pageSize}, "GET", function (data) {
        if(data.code === 200){
        	var totalCount =data.data.total;
        	if(totalCount>0){
        		$("#pageDiv").attr("style","display:block;");
        	}
            initUserAdvertisementStatisticsPie(data);
            document.getElementById("totalsSpan").innerHTML='总共 '+totalCount+'条记录';
            $("#totalCounts").val(data.data.total);
            goPage(pageNo,pageSize);
        }
    }, null, null, null, null, false);
}

function initUserAdvertisementStatisticsPie(data){
    $('#canvas-holder-user-advertisement-scatter-bar').empty();
    $('#canvas-holder-user-advertisement-scatter-bar').append('<canvas id="canvas-holder-user-advertisement-scatter-bar-chart" height="130px"  />');
    var userLoginStatisticsPieConfig = barChartConfig(data.data.list[0],data.data.list[1],data.data.list[2],data.data.list[3],data.data.list[4]);
    var pie_ctx = document.getElementById("canvas-holder-user-advertisement-scatter-bar-chart").getContext("2d");
    window.userLoginPie =  new Chart(pie_ctx,userLoginStatisticsPieConfig);
    window.userLoginPie.update();
}

function barChartConfig(labels,accessCount,accessDiffIPCount,clickCount,clickDiffIPCount){
	var barChartConfig = {
			type: 'bar',
		    data: {
		        labels: labels,
		        datasets: [{
		            label: '访问量',
		            data: accessCount,
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
		            label: '访问量(独立IP)',
		            data: accessDiffIPCount,
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
		            label: '点击量',
		            data: clickCount,
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
		        },
		        {
		            label: '点击量(独立IP)',
		            data: clickDiffIPCount,
		            backgroundColor: [
		                'rgba(0, 0, 255, 0.5)',
		                'rgba(0, 0, 255, 0.5)',
		                'rgba(0, 0, 255, 0.5)',
		                'rgba(0, 0, 255, 0.5)',
		                'rgba(0, 0, 255, 0.5)',
		                'rgba(0, 0, 255, 0.5)'
		            ],
		            borderColor: [
		                'rgba(0, 0, 255, 1)',
		                'rgba(0, 0, 255, 1)',
		                'rgba(0, 0, 255, 1)',
		                'rgba(0, 0, 255, 1)',
		                'rgba(0, 0, 255, 1)',
		                'rgba(0, 0, 255, 1)'
		            ],
		            borderWidth: 1
		        }
		        ]
		    },
		    options: {
		    	title: {
                    display: true,
                    text: '用户访问/点击广告信息统计'
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
