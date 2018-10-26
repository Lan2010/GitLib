$(function (){
    var map = new BMap.Map("allmap");
    var point = new BMap.Point(116.404, 39.915);
    map.centerAndZoom(point, 15);
    ajaxUtil.ajax('device/map/point', null, "GET", function (data) {
        if(data.code === 200){
            $(data.data.list).each( function( index, val ){
                var point = new BMap.Point(val.lng,val.lat);
                markPoint(point);
            });
        }
    }, null, null, null, null, false);

    function markPoint(point){
        var marker = new BMap.Marker(point);
        map.addOverlay(marker);
    }
});