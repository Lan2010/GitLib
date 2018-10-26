var chartUtil = (function () {


    var applicationPieChartBackground = [
        "rgba(255, 0, 0, 0.5)",
        "rgba(102, 102, 102, 0.5)",
        "rgba(51, 204, 102, 0.5)",
        "rgba(0, 0, 255, 0.5)",
        "rgba(30,144,255, 0.5)",
        "rgba(0,128,0, 0.5)",
        "rgba(0,206,209, 0.5)",
        "rgba(127,255,0, 0.5)",
        "rgba(218,112,214, 0.5)",
        "rgba(216,191,216, 0.5)",
        "rgba(221,160,221, 0.5)",
        "rgba(238,130,238, 0.5)"
    ];
    var applicationLineChartStyle = [
        {borderColor : "rgba(255, 153, 204, 0.5)", backgroundColor:"rgba(255, 153, 204, 0.5)", pointBorderColor: "rgba(255, 153, 204, 0.5)", pointBackgroundColor : "rgba(255, 153, 204, 0.5)", pointBorderWidth : 1},
        {borderColor : "rgba(153, 204, 255, 0.5)", backgroundColor:"rgba(153, 204, 255, 0.5)", pointBorderColor: "rgba(153, 204, 255, 0.5)", pointBackgroundColor : "rgba(153, 204, 255, 0.5)", pointBorderWidth : 1},
        {borderColor : "rgba(51, 204, 102, 0.5)", backgroundColor:"rgba(51, 204, 102, 0.5)", pointBorderColor: "rgba(51, 204, 102, 0.5)", pointBackgroundColor : "rgba(51, 204, 102, 0.5)", pointBorderWidth : 1},
        {borderColor : "rgba(0, 0, 255, 0.5)", backgroundColor:"rgba(0, 0, 255, 0.5)", pointBorderColor: "rgba(0, 0, 255, 0.5)", pointBackgroundColor : "rgba(0, 0, 255, 0.5)", pointBorderWidth : 1},
        {borderColor : "rgba(30,144,255, 0.5)", backgroundColor:"rgba(30,144,255, 0.5)", pointBorderColor: "rgba(30,144,255, 0.5)", pointBackgroundColor : "rgba(30,144,255, 0.5)", pointBorderWidth : 1},
        {borderColor : "rgba(0,128,0, 0.5)", backgroundColor:"rgba(0,128,0, 0.5)", pointBorderColor: "rgba(0,128,0, 0.5)", pointBackgroundColor : "rgba(0,128,0, 0.5)", pointBorderWidth : 1},
        {borderColor : "rgba(0,206,209, 0.5)", backgroundColor:"rgba(0,206,209, 0.5)", pointBorderColor: "rgba(0,206,209, 0.5)", pointBackgroundColor : "rgba(0,206,209, 0.5)", pointBorderWidth : 1},
        {borderColor : "rgba(127,255,0, 0.5)", backgroundColor:"rgba(127,255,0, 0.5)", pointBorderColor: "rgba(127,255,0, 0.5)", pointBackgroundColor : "rgba(127,255,0, 0.5)", pointBorderWidth : 1},
        {borderColor : "rgba(218,112,214, 0.5)", backgroundColor:"rgba(218,112,214, 0.5)", pointBorderColor: "rgba(218,112,214, 0.5)", pointBackgroundColor : "rgba(218,112,214, 0.5)", pointBorderWidth : 1},
        {borderColor : "rgba(216,191,216, 0.5)", backgroundColor:"rgba(216,191,216, 0.5)", pointBorderColor: "rgba(216,191,216, 0.5)", pointBackgroundColor : "rgba(216,191,216, 0.5)", pointBorderWidth : 1},
        {borderColor : "rgba(221,160,221, 0.5)", backgroundColor:"rgba(221,160,221, 0.5)", pointBorderColor: "rgba(221,160,221, 0.5)", pointBackgroundColor : "rgba(221,160,221, 0.5)", pointBorderWidth : 1},
        {borderColor : "rgba(238,130,238, 0.5)", backgroundColor:"rgba(238,130,238, 0.5)", pointBorderColor: "rgba(238,130,238, 0.5)", pointBackgroundColor : "rgba(238,130,238, 0.5)", pointBorderWidth : 1}
        
    ];
    var applicationPieChartLabels = [];
    var userInfoPieChartLabels = [];
    var userInfoChannelPieChartLabels = [];
    var applicationPieChartTitle = '应用程序统计';
    var applicationPieChartDiffIPTitle = '应用程序统计（独立IP）';
    var userInfoPieChartTitle = '用户信息统计';
    var userInfoPieChartChannelTitle = '用户信息统计（渠道）';
    var pagesPieChartTitle = '页面访问统计';
    var pagesPieChartDiffIPTitle = '页面访问统计(独立IP)';
    var userLoginPieChartTitle = '用户登录统计';
    var userLoginPieChartDiffIPTitle = '用户登录统计(独立账号)';
    var userOnlinePieChartDiffIPTitle = '用户在线统计';
    var userTaskChartChannelTitle = '用户任务统计(接受数量)';
    var userAuthCountChartTitle = '用户认证统计(认证总数量)';
    var userSucAuthCountChartTitle = '用户认证统计(成功认证的数量)';
    var deviceChartTitle = '设备操作统计(设备绑定数)';
    var userStarPointIncrement = '用户星点增长统计(增长数量)';
    var userCardTitle = '用户贺卡/明信片/绿卡统计';
    return {
        applicationPieChartLabels: applicationPieChartLabels,
        userInfoPieChartLabels: userInfoPieChartLabels,
        userInfoChannelPieChartLabels: userInfoChannelPieChartLabels,
        applicationPieChartBackground: applicationPieChartBackground,
        applicationPieChartTitle: applicationPieChartTitle,
        applicationPieChartDiffIPTitle: applicationPieChartDiffIPTitle,
        applicationLineChartStyle: applicationLineChartStyle,
        userInfoPieChartTitle: userInfoPieChartTitle,
        userInfoPieChartChannelTitle: userInfoPieChartChannelTitle,
        pagesPieChartTitle: pagesPieChartTitle,
        pagesPieChartDiffIPTitle: pagesPieChartDiffIPTitle,
        userLoginPieChartTitle: userLoginPieChartTitle,
        userLoginPieChartDiffIPTitle: userLoginPieChartDiffIPTitle,
        userOnlinePieChartDiffIPTitle: userOnlinePieChartDiffIPTitle,
        userTaskChartChannelTitle: userTaskChartChannelTitle,
        userAuthCountChartTitle: userAuthCountChartTitle,
        userSucAuthCountChartTitle: userSucAuthCountChartTitle,
        deviceChartTitle: deviceChartTitle,
        userStarPointIncrement: userStarPointIncrement,
        userCardTitle: userCardTitle
    };
})();