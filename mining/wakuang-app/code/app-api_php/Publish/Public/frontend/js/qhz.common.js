;
!function (window, undefined) {
    "use strict";

    var $, win, ready = {
        getPath: function () {
            var js = document.scripts, script = js[js.length - 1], jsPath = script.src;
            if (script.getAttribute('merge'))
                return;
            return jsPath.substring(0, jsPath.lastIndexOf("/") + 1);
        }(),
        //屏蔽Enter触发弹层
        enter: function (e) {
            if (e.keyCode === 13)
                e.preventDefault();
        },
        config: {}, end: {},
        btn: ['&#x786E;&#x5B9A;', '&#x53D6;&#x6D88;'],
        //五种原始层模式
        type: ['dialog', 'page', 'iframe', 'loading', 'tips']
    };

//默认内置方法。
    var layer = {
        ie6: !!window.ActiveXObject && !window.XMLHttpRequest,
        index: 0,
        path: ready.getPath,
        config: function (options, fn) {
            options = options || {};
            layer.cache = ready.config = $.extend(ready.config, options);
            layer.path = ready.config.path || layer.path;
            typeof options.extend === 'string' && (options.extend = [options.extend]);
            return this;
        },
        ready: function (path, fn) {
            var type = typeof path === 'function';
            if (type)
                fn = path;
            layer.config($.extend(ready.config, function () {
                return type ? {} : {path: path};
            }()), fn);
            return this;
        },
        //各种快捷引用
        alert: function (content, options, yes) {
            var type = typeof options === 'function';
            if (type)
                yes = options;
            return layer.open($.extend({
                content: content || '',
                yes: yes
            }, type ? {} : options));
        },
        confirm: function (content, options, yes, cancel) {
            var type = typeof options === 'function';
            if (type) {
                cancel = yes;
                yes = options;
            }
            return layer.open($.extend({
                content: content || '',
                btn: ready.btn,
                yes: yes,
                btn2: cancel
            }, type ? {} : options));
        },
        msg: function (content, options, end) { //最常用提示层
            var type = typeof options === 'function', rskin = ready.config.skin;
            var skin = (rskin ? rskin + ' ' + rskin + '-msg' : '') || 'qhz-layer-msg';
            var shift = doms.anim.length - 1;
            if (type)
                end = options;
            return layer.open($.extend({
                content: content || '',
                time: 3000,
                shade: false,
                skin: skin,
                title: false,
                closeBtn: false,
                btn: false,
                end: end
            }, (type && !ready.config.skin) ? {
                skin: skin + ' qhz-layer-hui',
                shift: shift
            } : function () {
                options = options || {};
                if (options.icon === -1 || options.icon === undefined && !ready.config.skin) {
                    options.skin = skin + ' ' + (options.skin || 'qhz-layer-hui');
                }
                return options;
            }()));
        },
        load: function (icon, options) {
            return layer.open($.extend({
                type: 3,
                icon: icon || 0,
                shade: 0.01
            }, options));
        },
        hideload: function () {
            layer.closeAll('loading');
        },
        tips: function (content, follow, options) {
            return layer.open($.extend({
                type: 4,
                content: [content, follow],
                closeBtn: false,
                time: 3000,
                shade: false,
                maxWidth: 210
            }, options));
        }
    };

    var Class = function (setings) {
        var _this = this;
        _this.index = ++layer.index;
        _this.config = $.extend({}, _this.config, ready.config, setings);
        _this.creat();
    };

    Class.pt = Class.prototype;

//缓存常用字符
    var doms = ['qhz-layer', '.qhz-layer-title', '.qhz-layer-main', '.qhz-layer-dialog', 'qhz-layer-iframe', 'qhz-layer-content', 'qhz-layer-btn', 'qhz-layer-close'];
    doms.anim = ['layer-anim', 'layer-anim-01', 'layer-anim-02', 'layer-anim-03', 'layer-anim-04', 'layer-anim-05', 'layer-anim-06'];

//默认配置
    Class.pt.config = {
        type: 0,
        shade: 0.3,
        fix: true,
        title: '&#x4FE1;&#x606F;',
        offset: 'auto',
        area: 'auto',
        closeBtn: 1,
        time: 0, //0表示不自动关闭
        zIndex: 99999999,
        maxWidth: 360,
        shift: 0,
        icon: -1,
        scrollbar: true, //是否允许浏览器滚动条
        tips: 2
    };

//容器
    Class.pt.vessel = function (conType, callback) {
        var _this = this, times = _this.index, config = _this.config;
        var zIndex = config.zIndex + times, titype = typeof config.title === 'object';
        var titleHTML = (config.title ? '<div class="qhz-layer-title" style="' + (titype ? config.title[1] : '') + '">'
                + (titype ? config.title[0] : '提示')
                + '</div>' : '');

        config.zIndex = zIndex;
        callback([
            //遮罩
            config.shade ? ('<div class="qhz-layer-shade" id="qhz-layer-shade' + times + '" times="' + times + '" style="' + ('z-index:' + (zIndex - 1) + '; background-color:' + (config.shade[1] || '#000') + '; opacity:' + (config.shade[0] || config.shade) + '; filter:alpha(opacity=' + (config.shade[0] * 100 || config.shade * 100) + ');') + '"></div>') : '',
            //主体
            '<div class="' + doms[0] + ' ' + (doms.anim[config.shift] || '') + (' qhz-layer-' + ready.type[config.type]) + (((config.type == 0 || config.type == 2) && !config.shade) ? ' qhz-layer-border' : '') + ' ' + (config.skin || '') + '" id="' + doms[0] + times + '" type="' + ready.type[config.type] + '" times="' + times + '" showtime="' + config.time + '" conType="' + (conType ? 'object' : 'string') + '" style="z-index: ' + zIndex + '; width:' + config.area[0] + ';height:' + config.area[1] + (config.fix ? '' : ';position:absolute;') + '">'
                    + (conType && config.type != 2 ? '' : titleHTML)
                    + '<div id="' + (config.id || '') + '" class="qhz-layer-content' + ((config.type == 0 && config.icon !== -1) ? ' qhz-layer-padding' : '') + (config.type == 3 ? ' qhz-layer-loading' + config.icon : '') + '">'
                    + (config.type == 0 && config.icon !== -1 ? '<i class="qhz-layer-ico qhz-layer-ico' + config.icon + '"></i>' : '')
                    + (config.type == 1 && conType ? '' : (config.content || ''))
                    + '</div>'
                    + '<span class="qhz-layer-setwin">' + function () {
                        var closebtn = '';
                        config.closeBtn && (closebtn += '<a class="qhz-layer-ico ' + doms[7] + ' ' + doms[7] + (config.title ? config.closeBtn : (config.type == 4 ? '1' : '2')) + '" href="javascript:;"></a>');
                        return closebtn;
                    }() + '</span>'
                    + (config.btn ? function () {
                        var button = '';
                        typeof config.btn === 'string' && (config.btn = [config.btn]);
                        for (var i = 0, len = config.btn.length; i < len; i++) {
                            button += '<a class="' + doms[6] + '' + i + '">' + config.btn[i] + '</a>'
                        }
                        return '<div class="' + doms[6] + '">' + button + '</div>'
                    }() : '')
                    + '</div>'
        ], titleHTML);
        return _this;
    };

//创建骨架
    Class.pt.creat = function () {
        var _this = this, config = _this.config, times = _this.index, nodeIndex;
        var content = config.content, conType = typeof content === 'object';

        if ($('#' + config.id)[0])
            return;

        if (typeof config.area === 'string') {
            config.area = config.area === 'auto' ? ['', ''] : [config.area, ''];
        }

        switch (config.type) {
            case 0:
                config.btn = ('btn' in config) ? config.btn : ready.btn[0];
                layer.closeAll('dialog');
                break;
            case 2:
                var content = config.content = conType ? config.content : [config.content || 'http://www.qianhezi.cn', 'auto'];
                config.content = '<iframe scrolling="' + (config.content[1] || 'auto') + '" allowtransparency="true" id="' + doms[4] + '' + times + '" name="' + doms[4] + '' + times + '" onload="this.className=\'\';" class="qhz-layer-load" frameborder="0" src="' + config.content[0] + '"></iframe>';
                break;
            case 3:
                config.title = false;
                config.closeBtn = false;
                config.icon === -1 && (config.icon === 0);
                layer.closeAll('loading');
                break;
            case 4:
                conType || (config.content = [config.content, 'body']);
                config.follow = config.content[1];
                config.content = config.content[0] + '<i class="qhz-layer-TipsG"></i>';
                config.title = false;
                config.fix = false;
                config.tips = typeof config.tips === 'object' ? config.tips : [config.tips, true];
                config.tipsMore || layer.closeAll('tips');
                break;
        }

        //建立容器
        _this.vessel(conType, function (html, titleHTML) {
            $('body').append(html[0]);
            conType ? function () {
                (config.type == 2 || config.type == 4) ? function () {
                    $('body').append(html[1]);
                }() : function () {
                    if (!content.parents('.' + doms[0])[0]) {
                        content.show().addClass('qhz-layer-wrap').wrap(html[1]);
                        $('#' + doms[0] + times).find('.' + doms[5]).before(titleHTML);
                    }
                }();
            }() : $('body').append(html[1]);
            _this.layero = $('#' + doms[0] + times);
            config.scrollbar || doms.html.css('overflow', 'hidden').attr('layer-full', times);
        }).callback().auto(times);

        config.type == 2 && layer.ie6 && _this.layero.find('iframe').attr('src', content[0]);
        $(document).off('keydown', ready.enter).on('keydown', ready.enter);
        _this.layero.on('keydown', function (e) {
            $(document).off('keydown', ready.enter);
        });

        //坐标自适应浏览器窗口尺寸
        config.type == 4 ? _this.tips() : _this.offset();
        if (config.fix) {
            win.on('resize', function () {
                _this.offset();
                (/^\d+%$/.test(config.area[0]) || /^\d+%$/.test(config.area[1])) && _this.auto(times);
                config.type == 4 && _this.tips();
            });
        }

        config.time <= 0 || setTimeout(function () {
            layer.close(_this.index)
        }, config.time);
    };

//自适应
    Class.pt.auto = function (index) {
        var _this = this, config = _this.config, layero = $('#' + doms[0] + index);
        if (config.area[0] === '' && config.maxWidth > 0) {
            //为了修复IE7下一个让人难以理解的bug
            if (/MSIE 7/.test(navigator.userAgent) && config.btn) {
                layero.width(layero.innerWidth());
            }
            layero.outerWidth() > config.maxWidth && layero.width(config.maxWidth);
        }
        var area = [layero.innerWidth(), layero.innerHeight()];
        var titHeight = layero.find(doms[1]).outerHeight() || 0;
        var btnHeight = layero.find('.' + doms[6]).outerHeight() || 0;
        function setHeight(elem) {
            elem = layero.find(elem);
            elem.height(area[1] - titHeight - btnHeight - 2 * (parseFloat(elem.css('padding')) | 0));
        }
        switch (config.type) {
            case 2:
                setHeight('iframe');
                break;
            default:
                if (config.area[1] === '') {
                    if (config.fix && area[1] >= win.height()) {
                        area[1] = win.height();
                        setHeight('.' + doms[5]);
                    }
                } else {
                    setHeight('.' + doms[5]);
                }
                break;
        }
        return _this;
    };

//计算坐标
    Class.pt.offset = function () {
        var _this = this, config = _this.config, layero = _this.layero;
        var area = [layero.outerWidth(), layero.outerHeight()];
        var type = typeof config.offset === 'object';
        _this.offsetTop = (win.height() - area[1]) / 2;
        _this.offsetLeft = (win.width() - area[0]) / 2;
        if (type) {
            _this.offsetTop = config.offset[0];
            _this.offsetLeft = config.offset[1] || _this.offsetLeft;
        } else if (config.offset !== 'auto') {
            _this.offsetTop = config.offset;
            if (config.offset === 'rb') { //右下角
                _this.offsetTop = win.height() - area[1];
                _this.offsetLeft = win.width() - area[0];
            }
        }
        if (!config.fix) {
            _this.offsetTop = /%$/.test(_this.offsetTop) ?
                    win.height() * parseFloat(_this.offsetTop) / 100
                    : parseFloat(_this.offsetTop);
            _this.offsetLeft = /%$/.test(_this.offsetLeft) ?
                    win.width() * parseFloat(_this.offsetLeft) / 100
                    : parseFloat(_this.offsetLeft);
            _this.offsetTop += win.scrollTop();
            _this.offsetLeft += win.scrollLeft();
        }
        layero.css({top: _this.offsetTop, left: _this.offsetLeft});
    };

//Tips
    Class.pt.tips = function () {
        var _this = this, config = _this.config, layero = _this.layero;
        var layArea = [layero.outerWidth(), layero.outerHeight()], follow = $(config.follow);
        if (!follow[0])
            follow = $('body');
        var goal = {
            width: follow.outerWidth(),
            height: follow.outerHeight(),
            top: follow.offset().top,
            left: follow.offset().left
        }, tipsG = layero.find('.qhz-layer-TipsG');

        var guide = config.tips[0];
        config.tips[1] || tipsG.remove();

        goal.autoLeft = function () {
            if (goal.left + layArea[0] - win.width() > 0) {
                goal.tipLeft = goal.left + goal.width - layArea[0];
                tipsG.css({right: 12, left: 'auto'});
            } else {
                goal.tipLeft = goal.left;
            }
            ;
        };
        //辨别tips的方位
        goal.where = [function () { //上        
                goal.autoLeft();
                goal.tipTop = goal.top - layArea[1] - 10;
                tipsG.removeClass('qhz-layer-TipsB').addClass('qhz-layer-TipsT').css('border-right-color', config.tips[1]);
            }, function () { //右
                goal.tipLeft = goal.left + goal.width + 10;
                goal.tipTop = goal.top;
                tipsG.removeClass('qhz-layer-TipsL').addClass('qhz-layer-TipsR').css('border-bottom-color', config.tips[1]);
            }, function () { //下
                goal.autoLeft();
                goal.tipTop = goal.top + goal.height + 10;
                tipsG.removeClass('qhz-layer-TipsT').addClass('qhz-layer-TipsB').css('border-right-color', config.tips[1]);
            }, function () { //左
                goal.tipLeft = goal.left - layArea[0] - 10;
                goal.tipTop = goal.top;
                tipsG.removeClass('qhz-layer-TipsR').addClass('qhz-layer-TipsL').css('border-bottom-color', config.tips[1]);
            }];
        goal.where[guide - 1]();

        /* 8*2为小三角形占据的空间 */
        if (guide === 1) {
            goal.top - (win.scrollTop() + layArea[1] + 8 * 2) < 0 && goal.where[2]();
        } else if (guide === 2) {
            win.width() - (goal.left + goal.width + layArea[0] + 8 * 2) > 0 || goal.where[3]()
        } else if (guide === 3) {
            (goal.top - win.scrollTop() + goal.height + layArea[1] + 8 * 2) - win.height() > 0 && goal.where[0]();
        } else if (guide === 4) {
            layArea[0] + 8 * 2 - goal.left > 0 && goal.where[1]()
        }

        layero.find('.' + doms[5]).css({
            'background-color': config.tips[1],
            'padding-right': (config.closeBtn ? '30px' : '')
        });
        layero.css({left: goal.tipLeft, top: goal.tipTop});
    };

    Class.pt.callback = function () {
        var _this = this, layero = _this.layero, config = _this.config;
        _this.openLayer();
        if (config.success) {
            if (config.type == 2) {
                layero.find('iframe').on('load', function () {
                    config.success(layero, _this.index);
                });
            } else {
                config.success(layero, _this.index);
            }
        }
        layer.ie6 && _this.IE6(layero);

        //按钮
        layero.find('.' + doms[6]).children('a').on('click', function () {
            var index = $(this).index();
            if (index === 0) {
                if (config.yes) {
                    config.yes(_this.index, layero)
                } else if (config['btn1']) {
                    config['btn1'](_this.index, layero)
                } else {
                    layer.close(_this.index);
                }
            } else {
                var close = config['btn' + (index + 1)] && config['btn' + (index + 1)](_this.index, layero);
                close === false || layer.close(_this.index);
            }
        });

        //取消
        function cancel() {
            var close = config.cancel && config.cancel(_this.index, layero);
            close === false || layer.close(_this.index);
        }

        //右上角关闭回调
        layero.find('.' + doms[7]).on('click', cancel);

        //点遮罩关闭
        if (config.shadeClose) {
            $('#qhz-layer-shade' + _this.index).on('click', function () {
                layer.close(_this.index);
            });
        }
        config.end && (ready.end[_this.index] = config.end);
        return _this;
    };

//for ie6 恢复select
    ready.reselect = function () {
        $.each($('select'), function (index, value) {
            var sthis = $(this);
            if (!sthis.parents('.' + doms[0])[0]) {
                (sthis.attr('layer') == 1 && $('.' + doms[0]).length < 1) && sthis.removeAttr('layer').show();
            }
            sthis = null;
        });
    };

    Class.pt.IE6 = function (layero) {
        var _this = this, _ieTop = layero.offset().top;

        //ie6的固定与相对定位
        function ie6Fix() {
            layero.css({top: _ieTop + (_this.config.fix ? win.scrollTop() : 0)});
        }
        ;
        ie6Fix();
        win.scroll(ie6Fix);

        //隐藏select
        $('select').each(function (index, value) {
            var sthis = $(this);
            if (!sthis.parents('.' + doms[0])[0]) {
                sthis.css('display') === 'none' || sthis.attr({'layer': '1'}).hide();
            }
            sthis = null;
        });
    };

//需依赖原型的对外方法
    Class.pt.openLayer = function () {
        var _this = this;

        //置顶当前窗口
        layer.zIndex = _this.config.zIndex;
        layer.setTop = function (layero) {
            var setZindex = function () {
                layer.zIndex++;
                layero.css('z-index', layer.zIndex + 1);
            };
            layer.zIndex = parseInt(layero[0].style.zIndex);
            layero.on('mousedown', setZindex);
            return layer.zIndex;
        };
    };

    ready.record = function (layero) {
        var area = [
            layero.outerWidth(),
            layero.outerHeight(),
            layero.position().top,
            layero.position().left + parseFloat(layero.css('margin-left'))
        ];
        layero.find('.qhz-layer-max').addClass('qhz-layer-maxmin');
        layero.attr({area: area});
    };

    ready.rescollbar = function (index) {
        if (doms.html.attr('layer-full') == index) {
            if (doms.html[0].style.removeProperty) {
                doms.html[0].style.removeProperty('overflow');
            } else {
                doms.html[0].style.removeAttribute('overflow');
            }
            doms.html.removeAttr('layer-full');
        }
    };
    /** 内置成员 */
    window.layer = layer;

//获取子iframe的DOM
    layer.getChildFrame = function (selector, index) {
        index = index || $('.' + doms[4]).attr('times');
        return $('#' + doms[0] + index).find('iframe').contents().find(selector);
    };

//得到当前iframe层的索引，子iframe时使用
    layer.getFrameIndex = function (name) {
        return $('#' + name).parents('.' + doms[4]).attr('times');
    };

//iframe层自适应宽高
    layer.iframeAuto = function (index) {
        if (!index)
            return;
        var heg = layer.getChildFrame('html', index).outerHeight();
        var layero = $('#' + doms[0] + index);
        var titHeight = layero.find(doms[1]).outerHeight() || 0;
        var btnHeight = layero.find('.' + doms[6]).outerHeight() || 0;
        layero.css({height: heg + titHeight + btnHeight});
        layero.find('iframe').css({height: heg});
    };

//重置iframe url
    layer.iframeSrc = function (index, url) {
        $('#' + doms[0] + index).find('iframe').attr('src', url);
    };

//设定层的样式
    layer.style = function (index, options) {
        var layero = $('#' + doms[0] + index), type = layero.attr('type');
        var titHeight = layero.find(doms[1]).outerHeight() || 0;
        var btnHeight = layero.find('.' + doms[6]).outerHeight() || 0;
        if (type === ready.type[1] || type === ready.type[2]) {
            layero.css(options);
            if (type === ready.type[2]) {
                layero.find('iframe').css({
                    height: parseFloat(options.height) - titHeight - btnHeight
                });
            }
        }
    };

//改变title
    layer.title = function (name, index) {
        var title = $('#' + doms[0] + (index || layer.index)).find(doms[1]);
        title.html(name);
    };

//关闭layer总方法
    layer.close = function (index) {
        var layero = $('#' + doms[0] + index), type = layero.attr('type');
        if (!layero[0])
            return;
        if (type === ready.type[1] && layero.attr('conType') === 'object') {
            layero.children(':not(.' + doms[5] + ')').remove();
            for (var i = 0; i < 2; i++) {
                layero.find('.qhz-layer-wrap').unwrap().hide();
            }
        } else {
            //低版本IE 回收 iframe
            if (type === ready.type[2]) {
                try {
                    var iframe = $('#' + doms[4] + index)[0];
                    iframe.contentWindow.document.write('');
                    iframe.contentWindow.close();
                    layero.find('.' + doms[5])[0].removeChild(iframe);
                } catch (e) {
                }
            }
            layero[0].innerHTML = '';
            layero.remove();
        }
        $('#qhz-layer-moves, #qhz-layer-shade' + index).remove();
        layer.ie6 && ready.reselect();
        ready.rescollbar(index);
        $(document).off('keydown', ready.enter);
        typeof ready.end[index] === 'function' && ready.end[index]();
        delete ready.end[index];
    };

//关闭所有层
    layer.closeAll = function (type) {
        $.each($('.' + doms[0]), function () {
            var othis = $(this);
            var is = type ? (othis.attr('type') === type) : 1;
            is && layer.close(othis.attr('times'));
            is = null;
        });
    };
//系统prompt
    layer.prompt = function (options, yes) {
        options = options || {};
        if (typeof options === 'function')
            yes = options;
        var prompt, content = options.formType == 2 ? '<textarea class="qhz-layer-input">' + (options.value || '') + '</textarea>' : function () {
            return '<input type="' + (options.formType == 1 ? 'password' : 'text') + '" class="qhz-layer-input" value="' + (options.value || '') + '">';
        }();
        return layer.open($.extend({
            btn: ['&#x786E;&#x5B9A;', '&#x53D6;&#x6D88;'],
            content: content,
            skin: 'qhz-layer-prompt' + skin('prompt'),
            success: function (layero) {
                prompt = layero.find('.qhz-layer-input');
                prompt.focus();
            }, yes: function (index) {
                var value = prompt.val();
                if (value === '') {
                    prompt.focus();
                } else if (value.length > (options.maxlength || 500)) {
                    layer.tips('&#x6700;&#x591A;&#x8F93;&#x5165;' + (options.maxlength || 500) + '&#x4E2A;&#x5B57;&#x6570;', prompt, {tips: 1});
                } else {
                    yes && yes(value, index, prompt);
                }
            }
        }, options));
    };
//主入口
    ready.run = function () {
        $ = jQuery;
        win = $(window);
        doms.html = $('html');
        layer.open = function (deliver) {
            var o = new Class(deliver);
            return o.index;
        };
    };
    'function' === typeof define ? define(function () {
        ready.run();
        return layer;
    }) : function () {
        ready.run();
    }();
}(window);
(function ($) {
    "use strict";

    var keyStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";
    var hexcase = 0;
    var encode64 = function (e) {
        var t = "", n, r, i = "", s, o, u, a = "", f = 0;
        do
            n = e.charCodeAt(f++), r = e.charCodeAt(f++), i = e.charCodeAt(f++), s = n >> 2, o = (n & 3) << 4 | r >> 4, u = (r & 15) << 2 | i >> 6, a = i & 63, isNaN(r) ? u = a = 64 : isNaN(i) && (a = 64), t = t + keyStr.charAt(s) + keyStr.charAt(o) + keyStr.charAt(u) + keyStr.charAt(a), n = r = i = "", s = o = u = a = "";
        while (f < e.length);
        return t
    };
    var decode64 = function (e) {
        var t = "", n, r, i = "", s, o, u, a = "", f = 0;
        if (e.length % 4 != 0)
            return"";
        var l = /[^A-Za-z0-9\+\/\=]/g;
        if (l.exec(e))
            return"";
        do
            s = keyStr.indexOf(e.charAt(f++)), o = keyStr.indexOf(e.charAt(f++)), u = keyStr.indexOf(e.charAt(f++)), a = keyStr.indexOf(e.charAt(f++)), n = s << 2 | o >> 4, r = (o & 15) << 4 | u >> 2, i = (u & 3) << 6 | a, t += String.fromCharCode(n), u != 64 && (t += String.fromCharCode(r)), a != 64 && (t += String.fromCharCode(i)), n = r = i = "", s = o = u = a = "";
        while (f < e.length);
        return t
    };
    var utf16to8 = function (e) {
        var t, n, r, i;
        t = "", r = e.length;
        for (n = 0; n < r; n++)
            i = e.charCodeAt(n), i >= 1 && i <= 127 ? t += e.charAt(n) : i > 2047 ? (t += String.fromCharCode(224 | i >> 12 & 15), t += String.fromCharCode(128 | i >> 6 & 63), t += String.fromCharCode(128 | i >> 0 & 63)) : (t += String.fromCharCode(192 | i >> 6 & 31), t += String.fromCharCode(128 | i >> 0 & 63));
        return t
    };
    var utf8to16 = function (e) {
        var t, n, r, i, s, o;
        t = "", r = e.length, n = 0;
        while (n < r) {
            i = e.charCodeAt(n++);
            switch (i >> 4) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                    t += e.charAt(n - 1);
                    break;
                case 12:
                case 13:
                    s = e.charCodeAt(n++), t += String.fromCharCode((i & 31) << 6 | s & 63);
                    break;
                case 14:
                    s = e.charCodeAt(n++), o = e.charCodeAt(n++), t += String.fromCharCode((i & 15) << 12 | (s & 63) << 6 | (o & 63) << 0)
            }
        }
        return t
    };
    var long2str = function (e, t) {
        var n = e.length, r = n - 1 << 2;
        if (t) {
            var i = e[n - 1];
            if (i < r - 3 || i > r)
                return null;
            r = i
        }
        for (var s = 0; s < n; s++)
            e[s] = String.fromCharCode(e[s] & 255, e[s] >>> 8 & 255, e[s] >>> 16 & 255, e[s] >>> 24 & 255);
        return t ? e.join("").substring(0, r) : e.join("")
    };
    var str2long = function (e, t) {
        var n = e.length, r = [];
        for (var i = 0; i < n; i += 4)
            r[i >> 2] = e.charCodeAt(i) | e.charCodeAt(i + 1) << 8 | e.charCodeAt(i + 2) << 16 | e.charCodeAt(i + 3) << 24;
        return t && (r[r.length] = n), r
    };
    var xxtea_encrypt = function (e, t) {
        if (e == "")
            return"";
        var n = str2long(e, !0), r = str2long(t, !1);
        r.length < 4 && (r.length = 4);
        var i = n.length - 1, s = n[i], o = n[0], u = 2654435769, a, f, l, c = Math.floor(6 + 52 / (i + 1)), h = 0;
        while (0 < c--) {
            h = h + u & 4294967295, f = h >>> 2 & 3;
            for (l = 0; l < i; l++)
                o = n[l + 1], a = (s >>> 5 ^ o << 2) + (o >>> 3 ^ s << 4) ^ (h ^ o) + (r[l & 3 ^ f] ^ s), s = n[l] = n[l] + a & 4294967295;
            o = n[0], a = (s >>> 5 ^ o << 2) + (o >>> 3 ^ s << 4) ^ (h ^ o) + (r[l & 3 ^ f] ^ s), s = n[i] = n[i] + a & 4294967295
        }
        return long2str(n, !1);
    };
    var xxtea_decrypt = function (e, t) {
        if (e == "")
            return"";
        var n = str2long(e, !1), r = str2long(t, !1);
        r.length < 4 && (r.length = 4);
        var i = n.length - 1, s = n[i - 1], o = n[0], u = 2654435769, a, f, l, c = Math.floor(6 + 52 / (i + 1)), h = c * u & 4294967295;
        while (h != 0) {
            f = h >>> 2 & 3;
            for (l = i; l > 0; l--)
                s = n[l - 1], a = (s >>> 5 ^ o << 2) + (o >>> 3 ^ s << 4) ^ (h ^ o) + (r[l & 3 ^ f] ^ s), o = n[l] = n[l] - a & 4294967295;
            s = n[i], a = (s >>> 5 ^ o << 2) + (o >>> 3 ^ s << 4) ^ (h ^ o) + (r[l & 3 ^ f] ^ s), o = n[0] = n[0] - a & 4294967295, h = h - u & 4294967295
        }
        return long2str(n, !0);
    };
    var hex_md5 = function (a) {
        return rstr2hex(rstr_md5(str2rstr_utf8(a)));
    };
    var hex_hmac_md5 = function (a, b) {
        return rstr2hex(rstr_hmac_md5(str2rstr_utf8(a), str2rstr_utf8(b)));
    };
    var md5_vm_test = function () {
        return hex_md5("abc").toLowerCase() == "900150983cd24fb0d6963f7d28e17f72";
    };
    var rstr_md5 = function (a) {
        return binl2rstr(binl_md5(rstr2binl(a), a.length * 8));
    };
    var rstr_hmac_md5 = function (c, f) {
        var e = rstr2binl(c);
        if (e.length > 16) {
            e = binl_md5(e, c.length * 8);
        }
        var a = Array(16), d = Array(16);
        for (var b = 0; b < 16; b++) {
            a[b] = e[b] ^ 909522486;
            d[b] = e[b] ^ 1549556828;
        }
        var g = binl_md5(a.concat(rstr2binl(f)), 512 + f.length * 8);
        return binl2rstr(binl_md5(d.concat(g), 512 + 128));
    };
    var rstr2hex = function (c) {
        try {
            hexcase;
        } catch (g) {
            hexcase = 0
        }
        var f = hexcase ? "0123456789ABCDEF" : "0123456789abcdef";
        var b = "";
        var a;
        for (var d = 0; d < c.length; d++) {
            a = c.charCodeAt(d);
            b += f.charAt((a >>> 4) & 15) + f.charAt(a & 15);
        }
        return b;
    };
    var str2rstr_utf8 = function (c) {
        var b = "";
        var d = -1;
        var a, e;
        while (++d < c.length) {
            a = c.charCodeAt(d);
            e = d + 1 < c.length ? c.charCodeAt(d + 1) : 0;
            if (55296 <= a && a <= 56319 && 56320 <= e && e <= 57343) {
                a = 65536 + ((a & 1023) << 10) + (e & 1023);
                d++;
            }
            if (a <= 127) {
                b += String.fromCharCode(a)
            } else {
                if (a <= 2047) {
                    b += String.fromCharCode(192 | ((a >>> 6) & 31), 128 | (a & 63));
                } else {
                    if (a <= 65535) {
                        b += String.fromCharCode(224 | ((a >>> 12) & 15), 128 | ((a >>> 6) & 63), 128 | (a & 63));
                    } else {
                        if (a <= 2097151) {
                            b += String.fromCharCode(240 | ((a >>> 18) & 7), 128 | ((a >>> 12) & 63), 128 | ((a >>> 6) & 63), 128 | (a & 63));
                        }
                    }
                }
            }
        }
        return b;
    };
    var rstr2binl = function (b) {
        var a = Array(b.length >> 2);
        for (var c = 0; c < a.length; c++) {
            a[c] = 0;
        }
        for (var c = 0; c < b.length * 8; c += 8) {
            a[c >> 5] |= (b.charCodeAt(c / 8) & 255) << (c % 32);
        }
        return a;
    };
    var binl2rstr = function (b) {
        var a = "";
        for (var c = 0; c < b.length * 32; c += 8) {
            a += String.fromCharCode((b[c >> 5] >>> (c % 32)) & 255);
        }
        return a;
    };
    var binl_md5 = function (p, k) {
        p[k >> 5] |= 128 << ((k) % 32);
        p[(((k + 64) >>> 9) << 4) + 14] = k;
        var o = 1732584193;
        var n = -271733879;
        var m = -1732584194;
        var l = 271733878;
        for (var g = 0; g < p.length; g += 16) {
            var j = o;
            var h = n;
            var f = m;
            var e = l;
            o = md5_ff(o, n, m, l, p[g + 0], 7, -680876936);
            l = md5_ff(l, o, n, m, p[g + 1], 12, -389564586);
            m = md5_ff(m, l, o, n, p[g + 2], 17, 606105819);
            n = md5_ff(n, m, l, o, p[g + 3], 22, -1044525330);
            o = md5_ff(o, n, m, l, p[g + 4], 7, -176418897);
            l = md5_ff(l, o, n, m, p[g + 5], 12, 1200080426);
            m = md5_ff(m, l, o, n, p[g + 6], 17, -1473231341);
            n = md5_ff(n, m, l, o, p[g + 7], 22, -45705983);
            o = md5_ff(o, n, m, l, p[g + 8], 7, 1770035416);
            l = md5_ff(l, o, n, m, p[g + 9], 12, -1958414417);
            m = md5_ff(m, l, o, n, p[g + 10], 17, -42063);
            n = md5_ff(n, m, l, o, p[g + 11], 22, -1990404162);
            o = md5_ff(o, n, m, l, p[g + 12], 7, 1804603682);
            l = md5_ff(l, o, n, m, p[g + 13], 12, -40341101);
            m = md5_ff(m, l, o, n, p[g + 14], 17, -1502002290);
            n = md5_ff(n, m, l, o, p[g + 15], 22, 1236535329);
            o = md5_gg(o, n, m, l, p[g + 1], 5, -165796510);
            l = md5_gg(l, o, n, m, p[g + 6], 9, -1069501632);
            m = md5_gg(m, l, o, n, p[g + 11], 14, 643717713);
            n = md5_gg(n, m, l, o, p[g + 0], 20, -373897302);
            o = md5_gg(o, n, m, l, p[g + 5], 5, -701558691);
            l = md5_gg(l, o, n, m, p[g + 10], 9, 38016083);
            m = md5_gg(m, l, o, n, p[g + 15], 14, -660478335);
            n = md5_gg(n, m, l, o, p[g + 4], 20, -405537848);
            o = md5_gg(o, n, m, l, p[g + 9], 5, 568446438);
            l = md5_gg(l, o, n, m, p[g + 14], 9, -1019803690);
            m = md5_gg(m, l, o, n, p[g + 3], 14, -187363961);
            n = md5_gg(n, m, l, o, p[g + 8], 20, 1163531501);
            o = md5_gg(o, n, m, l, p[g + 13], 5, -1444681467);
            l = md5_gg(l, o, n, m, p[g + 2], 9, -51403784);
            m = md5_gg(m, l, o, n, p[g + 7], 14, 1735328473);
            n = md5_gg(n, m, l, o, p[g + 12], 20, -1926607734);
            o = md5_hh(o, n, m, l, p[g + 5], 4, -378558);
            l = md5_hh(l, o, n, m, p[g + 8], 11, -2022574463);
            m = md5_hh(m, l, o, n, p[g + 11], 16, 1839030562);
            n = md5_hh(n, m, l, o, p[g + 14], 23, -35309556);
            o = md5_hh(o, n, m, l, p[g + 1], 4, -1530992060);
            l = md5_hh(l, o, n, m, p[g + 4], 11, 1272893353);
            m = md5_hh(m, l, o, n, p[g + 7], 16, -155497632);
            n = md5_hh(n, m, l, o, p[g + 10], 23, -1094730640);
            o = md5_hh(o, n, m, l, p[g + 13], 4, 681279174);
            l = md5_hh(l, o, n, m, p[g + 0], 11, -358537222);
            m = md5_hh(m, l, o, n, p[g + 3], 16, -722521979);
            n = md5_hh(n, m, l, o, p[g + 6], 23, 76029189);
            o = md5_hh(o, n, m, l, p[g + 9], 4, -640364487);
            l = md5_hh(l, o, n, m, p[g + 12], 11, -421815835);
            m = md5_hh(m, l, o, n, p[g + 15], 16, 530742520);
            n = md5_hh(n, m, l, o, p[g + 2], 23, -995338651);
            o = md5_ii(o, n, m, l, p[g + 0], 6, -198630844);
            l = md5_ii(l, o, n, m, p[g + 7], 10, 1126891415);
            m = md5_ii(m, l, o, n, p[g + 14], 15, -1416354905);
            n = md5_ii(n, m, l, o, p[g + 5], 21, -57434055);
            o = md5_ii(o, n, m, l, p[g + 12], 6, 1700485571);
            l = md5_ii(l, o, n, m, p[g + 3], 10, -1894986606);
            m = md5_ii(m, l, o, n, p[g + 10], 15, -1051523);
            n = md5_ii(n, m, l, o, p[g + 1], 21, -2054922799);
            o = md5_ii(o, n, m, l, p[g + 8], 6, 1873313359);
            l = md5_ii(l, o, n, m, p[g + 15], 10, -30611744);
            m = md5_ii(m, l, o, n, p[g + 6], 15, -1560198380);
            n = md5_ii(n, m, l, o, p[g + 13], 21, 1309151649);
            o = md5_ii(o, n, m, l, p[g + 4], 6, -145523070);
            l = md5_ii(l, o, n, m, p[g + 11], 10, -1120210379);
            m = md5_ii(m, l, o, n, p[g + 2], 15, 718787259);
            n = md5_ii(n, m, l, o, p[g + 9], 21, -343485551);
            o = safe_add(o, j);
            n = safe_add(n, h);
            m = safe_add(m, f);
            l = safe_add(l, e)
        }
        return Array(o, n, m, l);
    };
    var md5_cmn = function (h, e, d, c, g, f) {
        return safe_add(bit_rol(safe_add(safe_add(e, h), safe_add(c, f)), g), d);
    };
    var md5_ff = function (g, f, k, j, e, i, h) {
        return md5_cmn((f & k) | ((~f) & j), g, f, e, i, h);
    };
    var md5_gg = function (g, f, k, j, e, i, h) {
        return md5_cmn((f & j) | (k & (~j)), g, f, e, i, h);
    };
    var md5_hh = function (g, f, k, j, e, i, h) {
        return md5_cmn(f ^ k ^ j, g, f, e, i, h);
    };
    var md5_ii = function (g, f, k, j, e, i, h) {
        return md5_cmn(k ^ (f | (~j)), g, f, e, i, h);
    };
    var safe_add = function (a, d) {
        var c = (a & 65535) + (d & 65535);
        var b = (a >> 16) + (d >> 16) + (c >> 16);
        return(b << 16) | (c & 65535);
    };
    var bit_rol = function (a, b) {
        return(a << b) | (a >>> (32 - b));
    };
    $.extend({
        encrypt: function (obj, key) {
            if (obj.length) {
                var en = encode64(xxtea_encrypt(utf16to8(obj), hex_md5(key)));
                return en;
            } else {
                return "";
            }
        }
    });
    $.fn.encrypt = function (key) {
        return  $.encrypt(this.val(), key);
    }
})(jQuery);
(function ($) {
    $.fn.extend({
        "iePlaceholder": function (options) {
            options = $.extend({
                placeholderColor: '#999',
                isUseSpan: true,
                onInput: true
            }, options);

            $(this).each(function () {
                var _this = this;
                var supportPlaceholder = 'placeholder' in document.createElement('input');
                if (!supportPlaceholder) {
                    var defaultValue = $(_this).attr('placeholder');
                    var defaultColor = $(_this).css('color');
                    if (options.isUseSpan == false) {
                        $(_this).focus(function () {
                            var pattern = new RegExp("^" + defaultValue + "$|^$");
                            pattern.test($(_this).val()) && $(_this).val('').css('color', defaultColor);
                        }).blur(function () {
                            if ($(_this).val() == defaultValue) {
                                $(_this).css('color', defaultColor);
                            } else if ($(_this).val().length == 0) {
                                $(_this).val(defaultValue).css('color', options.placeholderColor)
                            }
                        }).trigger('blur');
                    } else {
                        var $imitate = $('<span class="wrap-placeholder" style="position:absolute; height: 40px!important; display:inline-block; overflow:hidden; color:' + options.placeholderColor + '; width:' + $(_this).width() + 'px; ">' + (defaultValue == undefined ? "" : defaultValue) + '</span>');

                        $imitate.css({
                            'margin-left': $(_this).css('margin-left'),
                            'margin-top': $(_this).css('margin-top'),
                            'text-align': 'left',
                            'font-size': $(_this).css('font-size'),
                            'font-family': $(_this).css('font-family'),
                            'font-weight': $(_this).css('font-weight'),
                            'padding-left': parseInt($(_this).css('padding-left')) + 2 + 'px',
                            'line-height': _this.nodeName.toLowerCase() == 'textarea' ? $(_this).css('line-weight') : $(_this).outerHeight() + 'px',
                            'padding-top': _this.nodeName.toLowerCase() == 'textarea' ? parseInt($(_this).css('padding-top')) + 2 : 0
                        });
                        $(_this).before($imitate.click(function () {
                            $(_this).trigger('focus');
                        }));

                        $(_this).val().length != 0 && $imitate.hide();

                        if (options.onInput) {
                            var inputChangeEvent = typeof (_this.oninput) == 'object' ? 'input' : 'propertychange';
                            $(_this).bind(inputChangeEvent, function () {
                                $imitate[0].style.display = $(_this).val().length != 0 ? 'none' : 'inline-block';
                            });
                        } else {
                            $(_this).focus(function () {
                                $imitate.hide();
                            }).blur(function () {
                                /^$/.test($(_this).val()) && $imitate.show();
                            });
                        }
                    }
                }
            });
            return this;
        }
    });
})(jQuery);
var qhz = qhz || {};
qhz.ua = {
    ie6: !-[1] && !window.XMLHttpRequest,
    ie678: !-[1]
}, $.fn.goToTop = function (obj) {
    var defaultObj = {
        fn: function () {
        },
        "static": !1,
        ele: document.body.scrollTop ? $(document.body) : $(document.documentElement),
        eletop: 0
    },
            options = $.extend({}, obj, defaultObj);
    $(this).click(function () {
        options.ele = document.body.scrollTop ? $(document.body) : $(document.documentElement),
                options.ele.animate({scrollTop: 0},
                        {
                            easing: "swing",
                            duration: 600,
                            complete: function () {
                                options.static = !1;
                            },
                            step: function (num) {
                                options.static = !0,
                                        options.eletop = num
                            }
                        }),
                options.fn()
    }),
            $(window).scroll(function () {
        1 == options.static && options.ele.scrollTop() > options.eletop && options.ele.stop();
    })
}, $.fn.showTime = function (time_distance) {
    var timer = this;
    if (isNaN(time_distance)) {
        time_distance = timer.attr("distance");
    }
    this.distance = time_distance || 0;
    var str_time;
    var int_day, int_hour, int_minute, int_second;
    var distance = this.distance;
    this.distance = this.distance - 1000;
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
        setTimeout(function () {
            timer.showTime(timer.distance);
        }, 1000);
    } else if (distance == -1000) {
        timer.text("项目未开始");
        return;
    } else {
        timer.text("项目已结束");
        return;
    }
}, qhz.riskTip = function () {
    $("#riskTip").hover(function () {
        $(".moquu_risk").show("linear").animate({opacity: "1"},
                {
                    duration: 500,
                    queue: !1,
                    specialEasing: {
                        marginLeft: "easeOutCubic"
                    }
                }),
                $(this).addClass("cur"),
                qhz.ua.ie6 && $(".moquu_risk").show()
    }, function () {
        $(".moquu_risk").hide().animate({opacity: "0"},
                {
                    duration: 500,
                    queue: !1,
                    specialEasing: {
                        marginLeft: "easeOutCubic"
                    }
                }),
                qhz.ua.ie6 && $(".moquu_risk").hide()
    });
}, qhz.kefu = function () {
    $("#pinglun").click(function () {

    });
}, qhz.weiXin = function () {
    $("#xiangguan").hover(function () {
        $(".moquu_wxinh").show("linear").animate({opacity: "1"},
                {
                    duration: 500,
                    queue: !1,
                    specialEasing: {
                        marginLeft: "easeOutCubic"
                    }
                }),
                $(this).addClass("cur"),
                qhz.ua.ie6 && $(".moquu_wxinh").show()
    }, function () {
        $(".moquu_wxinh").hide().animate({opacity: "0"},
                {
                    duration: 500,
                    queue: !1,
                    specialEasing: {
                        marginLeft: "easeOutCubic"
                    }
                }),
                qhz.ua.ie6 && $(".moquu_wxinh").hide()
    });
}, qhz.qianzhu = function () {
    $("#yyqz").hover(function () {
        $(".moquu_qzy").show("linear").animate({opacity: "1"},
                {
                    duration: 500,
                    queue: !1,
                    specialEasing: {
                        marginLeft: "easeOutCubic"
                    }
                }),
                $(this).addClass("cur"),
                qhz.ua.ie6 && $(".moquu_qzy").show()
    }, function () {
        $(".moquu_qzy").hide().animate({opacity: "0"},
                {
                    duration: 500,
                    queue: !1,
                    specialEasing: {
                        marginLeft: "easeOutCubic"
                    }
                }),
                qhz.ua.ie6 && $(".moquu_qzy").hide()
    });
}, qhz.topheader = function () {
    $(window).scroll(function () {
        var header = $('#fh5co-header'),
                scrlTop = $(this).scrollTop();
        if (scrlTop > 41) {
            header.addClass('navbar-fixed-top');
        } else {
            if (header.hasClass('navbar-fixed-top')) {
                header.removeClass('navbar-fixed-top');
            }
        }
    });
}, qhz.feedback = function () {
    var toolbars_goback = $(".back-to-top"),
            toolBarFun = function (arg) {
                var top = document.body.scrollTop || document.documentElement.scrollTop;
                top >= arg ? toolbars_goback.fadeIn(200) : toolbars_goback.fadeOut(200)
            },
            init = function () {
                toolBarFun(480);
            };
    $(window).scroll(function () {
        init()
    }),
            toolbars_goback.goToTop(),
            init()
}, qhz.headerico = function () {
    //头部图标
    $(".hover-weixin").hover(function () {
        $(this).find(".popover").show();
    }, function () {
        $(this).find(".popover").hide();
    });
    $(".hover-qq").hover(function () {
        $(this).find(".popover").show();
    }, function () {
        $(this).find(".popover").hide();
    });
}, qhz.loginsuccess = function (data) {
    if (data && data.ticketInfo != false) {
        $("#showSendTicket").show();
        $("#jxTicket").text(data.ticketInfo || "一张卡券");
        setTimeout(function () {
            $("#showSendTicket").hide();
            if (data.url && data.url != false && data.url != "") {
                window.location.href = data.url;
            } else {
                window.location.href = "/Account/index.html";
            }
        }, 3000);
    } else {
        if (data && data != false && data.url && data.url != false && data.url != "") {
            window.location.href = data.url;
        } else {
            var path = window.location.pathname;
            if (path.length > 3) {
                window.location.reload();
            } else {
                window.location.href = "/Account/index.html";
            }
        }
    }
}, qhz.headloginajax = function () {
    var emp = new Object();
    emp.password = $('#headPassword').encrypt($("#headimgCode").val());
    emp.phone = $("#headPhone").val();
    emp.verifyCode = $("#headimgCode").val();
    $.ajax({
        type: "post",
        url: "/User/loginHeadUser",
        data: {'Par': emp},
        dataType: "json",
        beforeSend: function () {
            $("#butheadLogin").attr('disabled', "true");
        },
        success: function (context, textStatus) {
            if (context.status == 1) {
                qhz.loginsuccess(context.data);
            } else {
                $('#CaptchaImageHead').attr('src', '/Common/captchaImage/' + Math.random());
                $("#butheadLogin").removeAttr("disabled");
	            var $msg = '<div class="login_failure"><i class="icon"></i>' + context.msg + '</div>';
	            $(".errorUnderDialog").html($msg);
            }
        },
        complete: function (XMLHttpRequest, textStatus) {
            $("#butheadLogin").removeAttr("disabled");
        },
        error: function () {
            $("#butheadLogin").removeAttr("disabled");
        }
    });
},
qhz.headSmsloginajax = function () {
    var emp = new Object();
    emp.phone = $("#headSmsPhone").val(); 
    emp.smsCode = $("#txtphoneCode").val();
    $.ajax({
        type: "post",
        url: "/User/smsLogin",
        data: {'Par': emp},
        dataType: "json",
        beforeSend: function () {
            $("#butheadSmsLogin").attr('disabled', "true");
        },
        success: function (context, textStatus) {
            if (context.status == 1) {
                qhz.loginsuccess(context.data);
            } else {
                $('#CaptchaSmsImageHead').attr('src', '/Common/captchaImage/' + Math.random());
                $("#headSmsPhone").removeAttr("disabled");
				layer.msg(context.msg);
            }
        },
        complete: function (XMLHttpRequest, textStatus) {
            $("#butheadSmsLogin").removeAttr("disabled");
        },
        error: function () {
            $("#butheadSmsLogin").removeAttr("disabled");
        }
    });
},qhz.sendCode = function () {
    var data = {};
    data.phone = $("#headSmsPhone").val();
    data.code = $("#headSmsimgCode").val();
    var emp = new Object();
    emp.phone = $("#headSmsPhone").val(); 
    emp.smsCode = $("#txtphoneCode").val();
    $.ajax({
        type: "post",
        url: "/User/loginPhoneCode/",
        data: {'par': data},
        dataType: "json",
        success: function (data) {
           if (data.status == 1) {
             qhz.jstimer(120);
            } else {
                $("#CaptchaSmsImageHead").attr("src", '/Common/captchaImage/' + Math.random());
                layer.alert(data.msg);
            }
        },
        complete: function () {
            $("#butheadSmsLogin").removeAttr("disabled");
        },
        error: function () {
            $("#butheadSmsLogin").removeAttr("disabled");
        }
    });
},qhz.jstimer=function(step){
  step = step <= 120 && step >= 0 ? step : 120;
    $(".butSmsPhone").val(step-- + " S").attr("disabled", true);
    if (step < 0) {
        $(".butSmsPhone").val("免费获取").removeAttr("disabled");
    } else {
        window.setTimeout("qhz.jstimer(" + step + ")", 1000);
    }
}
  qhz.headerlogin = function () {
    $('.theme-popover').fadeOut();
    //弹出登录框
    $('.theme-login').click(function () {
        $("#CaptchaImageHead").click();
        $('.theme-popover-mask').fadeIn(100);
        $('.theme-popover').fadeIn(100);
    });
    $('.theme-poptit .close').click(function () {
        $('.theme-popover').fadeOut(100);
        $('.theme-popover-mask').fadeOut(100);
        $("input[type=reset]").trigger("click");
    });

    $("#headName").focus(function () {
        $(".errorUnderDialog").html("");
    });
    $("#headPassword").focus(function () {
        $(".errorUnderDialog").html("");
    });
    $("#headimgCode").focus(function () {
        $(".errorUnderDialog").html("");
    });
    var validator = $('#login_headform').validate({
        rules: {
            headPhone: {
                required: true,
                phone: true
            },
            headPassword: {
                required: true
            },
            headimgCode: {
                required: true,
                maxlength: 4,
                minlength: 4
            }
        },
        messages: {
            headPhone: {
                required: "请输入手机号码",
                phone: "手机号码不正确"
            },
            headPassword: {
                required: "请输入登录密码"
            },
            headimgCode: {
                required: "请输入图片验证码",
                maxlength: "验证码为四位字符",
                minlength: "验证码为四位字符"
            }

        }
    });
    
    $('#login_smsheadform').validate({
        rules: {
            headSmsPhone: {
                required: true,
                phone: true
            },
            txtphoneCode: {
                required: true,
                maxlength: 6,
                minlength: 6
            },
            headSmsimgCode: {
                required: true,
                maxlength: 4,
                minlength: 4
            }
        },
        messages: {
            headSmsPhone: {
                required: "请输入手机号码",
                phone: "手机号码不正确"
            },
            txtphoneCode: {
                required: "请输入短信验证码",
                maxlength: "验证码为六位字符",
                minlength: "验证码为六位字符"
            },
            headSmsimgCode: {
                required: "请输入图片验证码",
                maxlength: "验证码为四位字符",
                minlength: "验证码为四位字符"
            }

        }
    });
    $("#butSmsPhone").click(function() {
     var checkPhone = $("#headSmsPhone").valid();
     var checkCode = $("#headSmsimgCode").valid();
     if (checkPhone && checkCode) {
         qhz.sendCode();
     }
    });

    $("#butheadLogin").click(function () {
        var isOK = $("#login_headform").valid();
        if (isOK) {
            qhz.headloginajax();
        }
    });
    $("#login_headform").keydown(function (event) {
        var e = event || window.event;
        var k = e.keyCode || e.which;
        if (k == 13) {
            $("#butheadLogin").click();
        }
    });
    $("input[type=reset]").click(function () {
        validator.resetForm();
    });
     $("#butheadSmsLogin").click(function () {
        var isOK = $("#login_smsheadform").valid();
        if (isOK) {
            qhz.headSmsloginajax();
        }
    }); 
    $("#login_smsheadform").keydown(function (event) {
        var e = event || window.event;
        var k = e.keyCode || e.which;
        if (k == 13) {
            $("#butheadSmsLogin").click();
        }
    });
    $(".loginType").click(function() {
        var type = $(this).attr("data");
        if (parseInt(type) * 1 == 1) {
            $("#CaptchaSmsImageHead").attr("src", '/Common/captchaImage/' + Math.random());
            $("#loginType").val("2");
            $("#accountLogin").hide();
            $("#smsLogin").show();
        } else {
            $("#CaptchaImageHead").attr("src", '/Common/captchaImage/' + Math.random());
            $("#loginType").val("1");
            $("#smsLogin").hide();
            $("#accountLogin").show();
        }
    });
    
}, qhz.showlogin = function () {
    $('.theme-popover-mask').fadeIn(100);
    $('.theme-popover').fadeIn(100);
}, qhz.init = function () {
    this.topheader(),
            this.feedback(),
            this.riskTip(),
            this.kefu(),
            this.weiXin(),
            this.qianzhu(),
            this.headerico(),
            this.headerlogin()
}, $(function () {
    qhz.init();
    /*调用方式： textarea需要田间onInput=false属性*/
    $('input[placeholder], textarea[placeholder]').each(function () {
        $(this).is('input') ? $(this).iePlaceholder() : $(this).iePlaceholder({onInput: false});
    });
});
(function (factory) {
    if (typeof define === 'function' && define.amd) {
        // AMD (Register as an anonymous module)
        define(['jquery'], factory);
    } else if (typeof exports === 'object') {
        // Node/CommonJS
        module.exports = factory(require('jquery'));
    } else {
        // Browser globals
        factory(jQuery);
    }
}(function ($) {
    var pluses = /\+/g;

    function encode(s) {
        return config.raw ? s : encodeURIComponent(s);
    }

    function decode(s) {
        return config.raw ? s : decodeURIComponent(s);
    }

    function stringifyCookieValue(value) {
        return encode(config.json ? JSON.stringify(value) : String(value));
    }

    function parseCookieValue(s) {
        if (s.indexOf('"') === 0) {
            // This is a quoted cookie as according to RFC2068, unescape...
            s = s.slice(1, -1).replace(/\\"/g, '"').replace(/\\\\/g, '\\');
        }

        try {
            // Replace server-side written pluses with spaces.
            // If we can't decode the cookie, ignore it, it's unusable.
            // If we can't parse the cookie, ignore it, it's unusable.
            s = decodeURIComponent(s.replace(pluses, ' '));
            return config.json ? JSON.parse(s) : s;
        } catch (e) {
        }
    }

    function read(s, converter) {
        var value = config.raw ? s : parseCookieValue(s);
        return $.isFunction(converter) ? converter(value) : value;
    }

    var config = $.cookie = function (key, value, options) {

        // Write

        if (arguments.length > 1 && !$.isFunction(value)) {
            options = $.extend({}, config.defaults, options);

            if (typeof options.expires === 'number') {
                var days = options.expires, t = options.expires = new Date();
                t.setMilliseconds(t.getMilliseconds() + days * 864e+5);
            }

            return (document.cookie = [
                encode(key), '=', stringifyCookieValue(value),
                options.expires ? '; expires=' + options.expires.toUTCString() : '', // use expires attribute, max-age is not supported by IE
                options.path ? '; path=' + options.path : '',
                options.domain ? '; domain=' + options.domain : '',
                options.secure ? '; secure' : ''
            ].join(''));
        }

        // Read

        var result = key ? undefined : {},
                // To prevent the for loop in the first place assign an empty array
                // in case there are no cookies at all. Also prevents odd result when
                // calling $.cookie().
                cookies = document.cookie ? document.cookie.split('; ') : [],
                i = 0,
                l = cookies.length;

        for (; i < l; i++) {
            var parts = cookies[i].split('='),
                    name = decode(parts.shift()),
                    cookie = parts.join('=');

            if (key === name) {
                // If second argument (value) is a function it's a converter...
                result = read(cookie, value);
                break;
            }

            // Prevent storing a cookie that we couldn't decode.
            if (!key && (cookie = read(cookie)) !== undefined) {
                result[name] = cookie;
            }
        }

        return result;
    };

    config.defaults = {};

    $.removeCookie = function (key, options) {
        // Must not alter options, thus extending a fresh object...
        $.cookie(key, '', $.extend({}, options, {expires: -1}));
        return !$.cookie(key);
    };

}));
