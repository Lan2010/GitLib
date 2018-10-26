function showTime(tenderid, time_distance) {
    this.tenderid = tenderid;
    this.distance = time_distance * 1000;
};
showTime.prototype.setTimeShow = function() {
    var timer = $("#show_" + this.tenderid);
    var str_time;
    var int_day, int_hour, int_minute, int_second;
    distance = this.distance;
    this.distance = this.distance - 1000;
//    debugger;
    if (distance > 0) {
        int_day = Math.floor(distance / 86400000);
        distance -= int_day * 86400000;
        int_hour = Math.floor(distance / 3600000);
        distance -= int_hour * 3600000;
        int_minute = Math.floor(distance / 60000);
        distance -= int_minute * 60000;
        int_second = Math.floor(distance / 1000);
        if (int_hour < 10)
            int_hour = "0" + int_hour;
        if (int_minute < 10)
            int_minute = "0" + int_minute;
        if (int_second < 10)
            int_second = "0" + int_second;
        str_time = int_day + "天" + int_hour + "小时" + int_minute + "分钟" + int_second + "秒";
        timer.text(str_time);
        var self = this;
        setTimeout(function() {
            self.setTimeShow();
        }, 1000);
    }else if (distance == -1000) {
        timer.text("项目未开始");
        return;
    } else {
        timer.text("项目已结束");
        return;
    }
};

$.fn.showLoading = function(options){
    var html = '<div class="qm-loading" style="position: fixed;left: 50%;top: 20%;width: 118px;height: 107px;border-radius: 6px;margin-left:-59px;' +
            'background-color:rgba(0, 0, 0, 0.7);box-shadow: 0 3px 9px rgba(0,0,0,.5);padding: 25px;text-align: center;z-index: 1111;">'
            + '<div><img class="loading_gif" src="/Public/Mobile/images/loading.gif"><p style="color: white;">加载中</p></div>'
            + '</div>'
            + '<div class="dialog_mask qm-mask" style="z-index: 98;" onkeypress="return false;" onkeydown="return false;" tabindex="0"></div>';
//    if (!$(this).is("body")){
//        html = "<div class='qm-loading' style='text-align: center;padding: 12px;background: rgb(244, 244, 244);'>加载中...</div>";
//    } 
    var $d = $(html);
    $d.appendTo($("body"));
};
$.fn.hideLoading = function(options){
    $(".qm-loading, .qm-mask").remove();
};