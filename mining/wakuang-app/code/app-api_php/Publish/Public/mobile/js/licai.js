
$(function() {
    // Do our DOM lookups beforehand
    var nav_container = $(".nav-container");
    var nav = $("nav");
    var top_spacing = 0;
    var waypoint_offset = 30;
    nav_container.waypoint({
        handler: function(event, direction) {
            if (direction == 'down') {
                nav_container.css({'height': nav.outerHeight()});
                nav.stop().addClass("sticky").css("top", -nav.outerHeight()).animate({"top": top_spacing});
            } else {
                nav_container.css({'height': 'auto'});
                nav.stop().removeClass("sticky").css("top", nav.outerHeight() + waypoint_offset).animate({"top": ""});
            }
        },
        offset: function() {
            return -nav.outerHeight() - waypoint_offset;
        }
    });
});
