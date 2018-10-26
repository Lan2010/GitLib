function goPage(pno, pageSize) {
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
		tempStr += "<li class='page-first'><a href=\"javascript:void(0)\" onclick=\"initStatistics(" + (1) + ")\">«</a></li>";
		tempStr += "<li class='page-pre'><a href=\"javascript:void(0)\" onclick=\"initStatistics(" + (currentPage - 1) + ")\">‹</a></li>";
	} else {
		tempStr += "<li class='page-first'><a href=\"javascript:void(0)\">«</a></li>";
		tempStr += "<li class='page-pre'><a href=\"javascript:void(0)\">‹</a></li>";
	}

	for (var pageIndex = 1; pageIndex < totalPage + 1; pageIndex++) {
		tempStr += "<li class='page-number'><a href=\"javascript:void(0)\" onclick=\"initStatistics(" + pageIndex + ")\">" + pageIndex + "</a></li>";
	}

	if (currentPage < totalPage) {
		tempStr += "</li><li class='page-next'><a href=\"javascript:void(0)\" onclick=\"initStatistics(" + (currentPage + 1) + ")\" >›</a></li>";
		tempStr += "<li class='page-last'><a href=\"javascript:void(0)\" onclick=\"initStatistics(" + (totalPage) + ")\">»</a></li>";
	} else {
		tempStr += "</li><li class='page-next'><a href=\"javascript:void(0)\">›</a></li>";
		tempStr += "<li class='page-last'><a href=\"javascript:void(0)\">»</a></li>";
	}

	document.getElementById("pageLine").innerHTML = tempStr;
}