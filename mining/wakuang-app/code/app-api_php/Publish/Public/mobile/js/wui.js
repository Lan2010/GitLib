/**
 * Created by elin on 2015/11/21.
 */

$(function () {
    // page stack
    var stack = [];
    var $container = $('.js_container');
    $container.on('click', '.js_cell[data-id]', function () {
        var id = $(this).data('id');
        go(id);
    });

    // location.hash = '#hash1' 和点击后退都会触发`hashchange`，这个demo页面只关心后退
    $(window).on('hashchange', function (e) {
        if (/#.+/gi.test(e.newURL)) {
            return;
        }
        var $top = stack.pop();
        if (!$top) {
            return;
        }
        $top.addClass('slideOut').on('animationend', function () {
            $top.remove();
        }).on('webkitAnimationEnd', function () {
            $top.remove();
        });
    });

    function go(id){
        var $tpl = $($('#tpl_' + id).html()).addClass('slideIn').addClass(id);
        $container.append($tpl);
        stack.push($tpl);
        // why not use `history.pushState`, https://github.com/weui/weui/issues/26
        //history.pushState({id: id}, '', '#' + id);
        location.hash = '#' + id;

        $($tpl).on('webkitAnimationEnd', function (){
            $(this).removeClass('slideIn');
        }).on('animationend', function (){
            $(this).removeClass('slideIn');
        });
        // tooltips
        if (id == 'cell') {
            $('.js_tooltips').show();
            setTimeout(function (){
                $('.js_tooltips').hide();
            }, 3000);
        }
    }

    if (/#.*/gi.test(location.href)) {
        go(location.hash.slice(1));
    }

    // toast
    $container.on('click', '#showToast', function () {
        $('#toast').show();
        setTimeout(function () {
            $('#toast').hide();
        }, 5000);
    });
    $container.on('click', '#showLoadingToast', function () {
        $('#loadingToast').show();
        setTimeout(function () {
            $('#loadingToast').hide();
        }, 5000);
    });

    $container.on('click', '#showDialog1', function () {
        $('#dialog1').show();
        $('#dialog1').find('.weui_btn_dialog').on('click', function () {
            $('#dialog1').hide();
        });
    });
    $container.on('click', '#showDialog2', function () {
        $('#dialog2').show();
        $('#dialog2').find('.weui_btn_dialog').on('click', function () {
            $('#dialog2').hide();
        });
    });

    function hideActionSheet(weuiActionsheet, mask) {
        weuiActionsheet.removeClass('weui_actionsheet_toggle');
        mask.removeClass('weui_fade_toggle');
        weuiActionsheet.on('transitionend', function () {
            mask.hide();
        }).on('webkitTransitionEnd', function () {
            mask.hide();
        })
    }
    $container.on('click','#showActionSheet', function () {
        var mask = $('#mask');
        var weuiActionsheet = $('#weui_actionsheet');
        weuiActionsheet.addClass('weui_actionsheet_toggle');
        mask.show().addClass('weui_fade_toggle').click(function () {
            hideActionSheet(weuiActionsheet, mask);
        });
        $('#actionsheet_cancel').click(function () {
            hideActionSheet(weuiActionsheet, mask);
        });
        weuiActionsheet.unbind('transitionend').unbind('webkitTransitionEnd');
    });
	
 $('.dropdown-toggle').dropdown('');  	
	
});

(function ($) {
    "use strict";

    $.fn.transitionEnd = function (callback) {
        var events = ['webkitTransitionEnd', 'transitionend', 'oTransitionEnd', 'MSTransitionEnd', 'msTransitionEnd'],
                i, dom = this;

        function fireCallBack(e) {
            /*jshint validthis:true */
            if (e.target !== this)
                return;
            callback.call(this, e);
            for (i = 0; i < events.length; i++) {
                dom.off(events[i], fireCallBack);
            }
        }
        if (callback) {
            for (i = 0; i < events.length; i++) {
                dom.on(events[i], fireCallBack);
            }
        }
        return this;
    };

    $.support = (function () {
        var support = {
            touch: !!(('ontouchstart' in window) || window.DocumentTouch && document instanceof window.DocumentTouch)
        };
        return support;
    })();

    $.touchEvents = {
        start: $.support.touch ? 'touchstart' : 'mousedown',
        move: $.support.touch ? 'touchmove' : 'mousemove',
        end: $.support.touch ? 'touchend' : 'mouseup'
    };

    $.getTouchPosition = function (e) {
        e = e.originalEvent || e; //jquery wrap the originevent
        if (e.type === 'touchstart' || e.type === 'touchmove' || e.type === 'touchend') {
            return {
                x: e.targetTouches[0].pageX,
                y: e.targetTouches[0].pageY
            };
        } else {
            return {
                x: e.pageX,
                y: e.pageY
            };
        }
    };

    $.fn.scrollHeight = function () {
        return this[0].scrollHeight;
    };

    $.fn.transform = function (transform) {
        for (var i = 0; i < this.length; i++) {
            var elStyle = this[i].style;
            elStyle.webkitTransform = elStyle.MsTransform = elStyle.msTransform = elStyle.MozTransform = elStyle.OTransform = elStyle.transform = transform;
        }
        return this;
    };
    $.fn.transition = function (duration) {
        if (typeof duration !== 'string') {
            duration = duration + 'ms';
        }
        for (var i = 0; i < this.length; i++) {
            var elStyle = this[i].style;
            elStyle.webkitTransitionDuration = elStyle.MsTransitionDuration = elStyle.msTransitionDuration = elStyle.MozTransitionDuration = elStyle.OTransitionDuration = elStyle.transitionDuration = duration;
        }
        return this;
    };

    $.getTranslate = function (el, axis) {
        var matrix, curTransform, curStyle, transformMatrix;

        // automatic axis detection
        if (typeof axis === 'undefined') {
            axis = 'x';
        }

        curStyle = window.getComputedStyle(el, null);
        if (window.WebKitCSSMatrix) {
            // Some old versions of Webkit choke when 'none' is passed; pass
            // empty string instead in this case
            transformMatrix = new WebKitCSSMatrix(curStyle.webkitTransform === 'none' ? '' : curStyle.webkitTransform);
        } else {
            transformMatrix = curStyle.MozTransform || curStyle.OTransform || curStyle.MsTransform || curStyle.msTransform || curStyle.transform || curStyle.getPropertyValue('transform').replace('translate(', 'matrix(1, 0, 0, 1,');
            matrix = transformMatrix.toString().split(',');
        }

        if (axis === 'x') {
            //Latest Chrome and webkits Fix
            if (window.WebKitCSSMatrix)
                curTransform = transformMatrix.m41;
            //Crazy IE10 Matrix
            else if (matrix.length === 16)
                curTransform = parseFloat(matrix[12]);
            //Normal Browsers
            else
                curTransform = parseFloat(matrix[4]);
        }
        if (axis === 'y') {
            //Latest Chrome and webkits Fix
            if (window.WebKitCSSMatrix)
                curTransform = transformMatrix.m42;
            //Crazy IE10 Matrix
            else if (matrix.length === 16)
                curTransform = parseFloat(matrix[13]);
            //Normal Browsers
            else
                curTransform = parseFloat(matrix[5]);
        }

        return curTransform || 0;
    };
    $.requestAnimationFrame = function (callback) {
        if (window.requestAnimationFrame)
            return window.requestAnimationFrame(callback);
        else if (window.webkitRequestAnimationFrame)
            return window.webkitRequestAnimationFrame(callback);
        else if (window.mozRequestAnimationFrame)
            return window.mozRequestAnimationFrame(callback);
        else {
            return window.setTimeout(callback, 1000 / 60);
        }
    };

    $.cancelAnimationFrame = function (id) {
        if (window.cancelAnimationFrame)
            return window.cancelAnimationFrame(id);
        else if (window.webkitCancelAnimationFrame)
            return window.webkitCancelAnimationFrame(id);
        else if (window.mozCancelAnimationFrame)
            return window.mozCancelAnimationFrame(id);
        else {
            return window.clearTimeout(id);
        }
    };

    $.fn.join = function (arg) {
        return this.toArray().join(arg);
    }

    $.fn.data = function (key, value) {
        if (typeof value === 'undefined') {
            // Get value
            if (this[0] && this[0].getAttribute) {
                var dataKey = this[0].getAttribute('data-' + key);

                if (dataKey) {
                    return dataKey;
                } else if (this[0].elementDataStorage && (key in this[0].elementDataStorage)) {


                    return this[0].elementDataStorage[key];

                } else {
                    return undefined;
                }
            } else
                return undefined;

        } else {
            // Set value
            for (var i = 0; i < this.length; i++) {
                var el = this[i];
                if (!el.elementDataStorage)
                    el.elementDataStorage = {};
                el.elementDataStorage[key] = value;
            }
            return this;
        }
    };

})($);

+function ($) {
    "use strict";

    var defaults;

    $.modal = function (params) {
        params = $.extend({}, defaults, params);


        var buttons = params.buttons;

        var buttonsHtml = buttons.map(function (d, i) {
            return '<a href="javascript:;" class="weui_btn_dialog ' + (d.className || "") + '">' + d.text + '</a>';
        }).join("");

        var tpl = '<div class="weui_dialog">' +
                '<div class="weui_dialog_hd"><strong class="weui_dialog_title">' + params.title + '</strong></div>' +
                (params.text ? '<div class="weui_dialog_bd">' + params.text + '</div>' : '') +
                '<div class="weui_dialog_ft">' + buttonsHtml + '</div>' +
                '</div>';

        var dialog = $.openModal(tpl);

        dialog.find(".weui_btn_dialog").each(function (i, e) {
            var el = $(e);
            el.click(function () {
                //先关闭对话框，再调用回调函数
                if (params.autoClose)
                    $.closeModal();

                if (buttons[i].onClick) {
                    buttons[i].onClick();
                }
            });
        });
    };

    $.openModal = function (tpl) {
        var mask = $("<div class='weui_mask'></div>").appendTo(document.body);
        mask.show();

        var dialog = $(tpl).appendTo(document.body);

        dialog.show();
        mask.addClass("weui_mask_visible");
        dialog.addClass("weui_dialog_visible");

        return dialog;
    }

    $.closeModal = function () {
        $(".weui_mask_visible").removeClass("weui_mask_visible").transitionEnd(function () {
            $(this).remove();
        });
        $(".weui_dialog_visible").removeClass("weui_dialog_visible").transitionEnd(function () {
            $(this).remove();
        });
    };

    $.alert = function (text, title, callback) {
        if (typeof title === 'function') {
            callback = arguments[1];
            title = undefined;
        }
        return $.modal({
            text: text,
            title: title,
            buttons: [{
                    text: defaults.buttonOK,
                    className: "primary",
                    onClick: callback
                }]
        });
    }

    $.confirm = function (text, title, callbackOK, callbackCancel) {
        if (typeof title === 'function') {
            callbackCancel = arguments[2];
            callbackOK = arguments[1];
            title = undefined;
        }
        return $.modal({
            text: text,
            title: title,
            buttons: [
                {
                    text: defaults.buttonCancel,
                    className: "default",
                    onClick: callbackCancel
                },
                {
                    text: defaults.buttonOK,
                    className: "primary",
                    onClick: callbackOK
                }]
        });
    };

    $.prompt = function (text, title, callbackOK, callbackCancel) {
        if (typeof title === 'function') {
            callbackCancel = arguments[2];
            callbackOK = arguments[1];
            title = undefined;
        }

        return $.modal({
            text: "<p class='weui-prompt-text'>" + (text || "") + "</p><input type='text' class='weui_input weui-prompt-input' id='weui-prompt-input'/>",
            title: title,
            buttons: [
                {
                    text: defaults.buttonCancel,
                    className: "default",
                    onClick: callbackCancel
                },
                {
                    text: defaults.buttonOK,
                    className: "primary",
                    onClick: function () {
                        callbackOK && callbackOK($("#weui-prompt-input").val());
                    }
                }]
        });
    };

    defaults = $.modal.prototype.defaults = {
        title: "提示",
        text: undefined,
        buttonOK: "确定",
        buttonCancel: "取消",
        buttons: [{
                text: "确定",
                className: "primary"
            }],
        autoClose: true //点击按钮自动关闭对话框，如果你不希望点击按钮就关闭对话框，可以把这个设置为false
    };

}($);