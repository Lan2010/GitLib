if (!NeuF) var NeuF = {};
NeuF.ScrollPage = function (obj, options, callback) {
    var _defaultOptions = { delay: 500, marginBottom: 100 }; //默认配置：延迟时间delay和滚动条距离底部距离marginBottom
    options = $.extend(_defaultOptions, options);
    this.isScrolling = false; //是否在滚动
    this.oriPos = 0; //原始位置
    this.curPos = 0; //当前位置
    var me = this; //顶层
    var $obj = (typeof obj == "string") ? $("#" + obj) : $(obj);
    //绑定滚动事件
    $obj.scroll(function (ev) {
        me.curPos = $obj.scrollTop();
        if ($(window).height() + $(window).scrollTop() >= $(document.body).height() - options.marginBottom) {
            if (me.isScrolling == true) return;
            me.isScrolling = true;
            setTimeout(function () { me.isScrolling = false; }, options.delay);   //重复触发间隔毫秒
            if (typeof callback == "function") callback.call(null, me.curPos - me.oriPos);
        };
        me.oriPos = me.curPos;
    });
};