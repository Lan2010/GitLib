    var sliderMain = function () {
        $('#fh5co-home .flexslider').flexslider({
            animation: "fade",
            slideshowSpeed: 5000,
            directionNav: true
        });
    };
    var sliderTestimony = function () {
        $('#fh5co-testimony .flexslider').flexslider({
            animation: "slide",
            slideshowSpeed: 5000,
            directionNav: true,
            controlNav: true,
            smoothHeight: true,
            reverse: true
        });
    };
    //动态加载模块
var contentWayPoint = function () {
    $('.animate-box').waypoint(function (direction) {
        if (direction === 'down' && !$(this.element).hasClass('animated')) {
            $(this.element).addClass('item-animate');
            setTimeout(function () {
                $('body .animate-box.item-animate').each(function (k) {
                    var el = $(this);
                    setTimeout(function () {
                        el.removeClass('item-animate');
                        el.addClass('animated fadeInUp');
                    }, k * 100);
                });
            }, 100);
        }
    }, {offset: '85%'});
};
(function ($) {
    "use strict";
    var defaults;

    $.fn.countTo = function (options) {
        options = options || {};
        return $(this).each(function () {
            var settings = $.extend({}, defaults, {
                from: $(this).data('from'),
                to: $(this).data('to'),
                speed: $(this).data('speed'),
                refreshInterval: $(this).data('refresh-interval'),
                decimals: $(this).data('decimals')
            }, options);

            var loops = Math.ceil(settings.speed / settings.refreshInterval),
                    increment = (settings.to - settings.from) / loops;

            var self = this,
                    $self = $(this),
                    loopCount = 0,
                    value = settings.from,
                    data = $self.data('countTo') || {};

            $self.data('countTo', data);

            if (data.interval) {
                clearInterval(data.interval);
            }
            data.interval = setInterval(updateTimer, settings.refreshInterval);
            render(value);

            function updateTimer() {
                value += increment;
                loopCount++;
                render(value);
                if (typeof (settings.onUpdate) == 'function') {
                    settings.onUpdate.call(self, value);
                }

                if (loopCount >= loops) {
                    // remove the interval
                    $self.removeData('countTo');
                    clearInterval(data.interval);
                    value = settings.to;

                    if (typeof (settings.onComplete) == 'function') {
                        settings.onComplete.call(self, value);
                    }
                }
            }

            function render(value) {
                var formattedValue = settings.formatter.call(self, value, settings);
                $self.html(formattedValue);
            }
        });
    };

    defaults = $.fn.countTo.prototype.defaults = {
        from: 0, // the number the element should start at
        to: 0, // the number the element should end at
        speed: 1000, // how long it should take to count between the target numbers
        refreshInterval: 100, // how often the element should be updated
        decimals: 0, // the number of decimal places to show
        formatter: function (value, settings) {
            return parseInt(value.toFixed(settings.decimals));
        }, // handler for formatting the value before rendering
        onUpdate: null, // callback method for every time the element is updated
        onComplete: null       // callback method for when the element finishes updating
    };
})($);

$(function () {
    sliderMain();
    sliderTestimony();
    $("#count-number").data('countToOptions', {
        formatter: function (value, options) {
            return value.toFixed(options.decimals).replace(/\B(?=(?:\d{3})+(?!\d))/g, ',');
        }
    });
    $(".timer").each(function (options) {
        var $this = $(this);
        options = $.extend({}, options || {}, $this.data('countToOptions') || {});
        $this.countTo(options);
    });
    contentWayPoint();
    //公告。
    $(".slideTxtBox").slide();
    //排行榜
    $(".topLoop").slide({
        mainCell: ".bd ul", effect: "topMarquee", vis: 5, interTime: 40, autoPlay: true
    });
    //保障机构
    $(".picScroll-left").slide({
        titCell: ".hd ul", mainCell: ".bd ul", autoPage: true, effect: "left", autoPlay: false, vis: 5, scroll: 5
    });
});

//风险提示
setTimeout(function(){
	$('.moquu_risk').hide();	
},3000);