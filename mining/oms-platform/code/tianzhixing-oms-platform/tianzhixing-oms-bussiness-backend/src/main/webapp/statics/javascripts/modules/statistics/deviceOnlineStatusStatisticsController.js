$(function () {

    initStatistics(1,0);
    
});

function initStatistics (pageNo,type){
	var pageSize = 5;
    ajaxUtil.ajax('statistics/deviceOnline/chart', {pageNo:pageNo,pageSize:pageSize,type:type}, "GET", function (data) {
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
    var userLoginStatisticsPieConfig = barChartConfig(data.data.list[0],data.data.list[1],data.data.list[2],data.data.list[3],data.data.list[4],data.data.list[5],data.data.list[6],data.data.list[7],data.data.list[8],data.data.list[9],data.data.list[10]);
    var pie_ctx = document.getElementById("canvas-holder-user-advertisement-scatter-bar-chart").getContext("2d");
    window.userLoginPie =  new Chart(pie_ctx,userLoginStatisticsPieConfig);
    window.userLoginPie.update();
}

function barChartConfig(labels,bindDeviceOnlineCount,bindDeviceDiffIDOnlineCount,unBindDeviceOnlineCount,unBindDeviceDiffIDOnlineCount,bindDeviceOfflineCount,bindDeviceDiffIDOfflineCount,unBindDeviceOfflineCount,unBindDeviceDiffIDOfflineCount,unBindDeviceCurrentOnlineCount,bindDeviceCurrentOnlineCount){
	var barChartConfig = {
			type: 'bar',
		    data: {
		        labels: labels,
		        datasets: [{
		            label: '已绑定设备上线数',
		            data: bindDeviceOnlineCount,
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
		            label: '已绑定设备上线数（独立device）',
		            data: bindDeviceDiffIDOnlineCount,
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
		            label: '未绑定设备上线数',
		            data: unBindDeviceOnlineCount,
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
		            label: '未绑定设备上线数（独立device）',
		            data: unBindDeviceDiffIDOnlineCount,
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
		        },
		        {
		            label: '已绑定设备下线数',
		            data: bindDeviceOfflineCount,
		            backgroundColor: [
		                'rgba(30,144,255, 0.5)',
		                'rgba(30,144,255, 0.5)',
		                'rgba(30,144,255, 0.5)',
		                'rgba(30,144,255, 0.5)',
		                'rgba(30,144,255, 0.5)',
		                'rgba(30,144,255, 0.5)'
		            ],
		            borderColor: [
		                'rgba(30,144,255, 1)',
		                'rgba(30,144,255, 1)',
		                'rgba(30,144,255, 1)',
		                'rgba(30,144,255, 1)',
		                'rgba(30,144,255, 1)',
		                'rgba(30,144,255, 1)'
		            ],
		            borderWidth: 1
		        },
		        {
		            label: '已绑定设备下线数（独立device）',
		            data: bindDeviceDiffIDOfflineCount,
		            backgroundColor: [
		                'rgba(0,128,0, 0.5)',
		                'rgba(0,128,0, 0.5)',
		                'rgba(0,128,0, 0.5)',
		                'rgba(0,128,0, 0.5)',
		                'rgba(0,128,0, 0.5)',
		                'rgba(0,128,0, 0.5)'
		            ],
		            borderColor: [
		                'rgba(0,128,0, 1)',
		                'rgba(0,128,0, 1)',
		                'rgba(0,128,0, 1)',
		                'rgba(0,128,0, 1)',
		                'rgba(0,128,0, 1)',
		                'rgba(0,128,0, 1)'
		            ],
		            borderWidth: 1
		        },
		        {
		            label: '未绑定设备下线数',
		            data: unBindDeviceOfflineCount,
		            backgroundColor: [
		                'rgba(0,206,209, 0.5)',
		                'rgba(0,206,209, 0.5)',
		                'rgba(0,206,209, 0.5)',
		                'rgba(0,206,209, 0.5)',
		                'rgba(0,206,209, 0.5)',
		                'rgba(0,206,209, 0.5)'
		            ],
		            borderColor: [
		                'rgba(0,206,209, 1)',
		                'rgba(0,206,209, 1)',
		                'rgba(0,206,209, 1)',
		                'rgba(0,206,209, 1)',
		                'rgba(0,206,209, 1)',
		                'rgba(0,206,209, 1)'
		            ],
		            borderWidth: 1
		        },
		        {
		            label: '未绑定设备下线数（独立device）',
		            data: unBindDeviceDiffIDOfflineCount,
		            backgroundColor: [
		                'rgba(127,255,0, 0.5)',
		                'rgba(127,255,0, 0.5)',
		                'rgba(127,255,0, 0.5)',
		                'rgba(127,255,0, 0.5)',
		                'rgba(127,255,0, 0.5)',
		                'rgba(127,255,0, 0.5)'
		            ],
		            borderColor: [
		                'rgba(127,255,0, 1)',
		                'rgba(127,255,0, 1)',
		                'rgba(127,255,0, 1)',
		                'rgba(127,255,0, 1)',
		                'rgba(127,255,0, 1)',
		                'rgba(127,255,0, 1)'
		            ],
		            borderWidth: 1
		        },
		        {
		            label: '当前未绑定设备在线数（独立device）',
		            data: unBindDeviceCurrentOnlineCount,
		            backgroundColor: [
		                'rgba(218,112,214, 0.5)',
		                'rgba(218,112,214, 0.5)',
		                'rgba(218,112,214, 0.5)',
		                'rgba(218,112,214, 0.5)',
		                'rgba(218,112,214, 0.5)',
		                'rgba(218,112,214, 0.5)'
		            ],
		            borderColor: [
		                'rgba(218,112,214, 1)',
		                'rgba(218,112,214, 1)',
		                'rgba(218,112,214, 1)',
		                'rgba(218,112,214, 1)',
		                'rgba(218,112,214, 1)',
		                'rgba(218,112,214, 1)'
		            ],
		            borderWidth: 1
		        },
		        {
		            label: '当前已绑定设备在线数（独立device）',
		            data: bindDeviceCurrentOnlineCount,
		            backgroundColor: [
		                'rgba(216,191,216, 0.5)',
		                'rgba(216,191,216, 0.5)',
		                'rgba(216,191,216, 0.5)',
		                'rgba(216,191,216, 0.5)',
		                'rgba(216,191,216, 0.5)',
		                'rgba(216,191,216, 0.5)'
		            ],
		            borderColor: [
		                'rgba(216,191,216, 1)',
		                'rgba(216,191,216, 1)',
		                'rgba(216,191,216, 1)',
		                'rgba(216,191,216, 1)',
		                'rgba(216,191,216, 1)',
		                'rgba(216,191,216, 1)'
		            ],
		            borderWidth: 1
		        }
		        ]
		    },
		    options: {
		    	title: {
                    display: true,
                    text: '设备上线/下线统计'
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

$('#label_chart label').hover(function() {
	$(this).addClass("hover-blue");
}, function() {
	$(this).removeClass("hover-blue");
});

$('#label_chart label').on("click", function() {
	var pageNo = document.getElementById("pageNo").value;
	initStatistics(pageNo,$(this).attr('value'));
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
