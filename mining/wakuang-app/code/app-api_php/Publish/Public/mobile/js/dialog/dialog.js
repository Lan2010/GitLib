/*对话框操作类*/
$Dialog = function () {
    var closeDialog = function ($this) {
        $this.closest(".qm_dialog").remove();
        $(".dialog_mask").remove();
    };
    var open = function (content, title, buttons, options) {
        var defaultOptions = {dclass: "mg_dialog"};
        $.extend(defaultOptions, options);

        buttons = buttons || [];
        var html = '<div class="qm_dialog ">'
            + '<div class="dialog_head" ><span class="title" ></span>' +
            '<a class="dialog_head_closeIcon" href="javascript::"></a></div>'
            + '<div class="dialog_content">'
            + '    <div class="dialog_content_icon"><span></span></div>'
            + '    <div class="dialog_content_msg"></div>'
            + '    </div>'
            + '<div class="dialog_foot" ></div>'
            + '</div>'
            + '<div class="dialog_mask " style="z-index: 98;" onkeypress="return false;" onkeydown="return false;" tabindex="0"></div>';

        var $d = $(html);

        var bodyWidth = $("body").width();
        if ( bodyWidth > 400){
            $d.first().css("left", (bodyWidth - 400) / 2);
        }
        $d.closeDialog = function () {
            closeDialog($(this));
        };
        $d.find(".dialog_head_closeIcon").click(function () {
            closeDialog($(this));
        });
        /*如果没标题，不显示*/
        if (title) {
            $d.find(".dialog_head > .title").text(title);
        } else {
            $d.find(".dialog_head").hide();
        }
        /*如果是自定义内容，全填充*/
        if (defaultOptions.content) {
            $d.find(".dialog_content").html(defaultOptions.content);
        } else {
            $d.find(".dialog_content_msg").html(content);
        }

        var $btns = $d.find(".dialog_foot");
        if (buttons.length > 0) {
            $.each(buttons, function () {
                var callback = this["callback"];
                var $tempBtn = $('<a class="dialog_button " href="javascript:;"></a>');
                $tempBtn.text(this["title"]);
                $tempBtn.click(function (e) {
                    e.preventDefault();
                    closeDialog($btns);
                    if (callback) {
                        callback();
                    }
                    return false;
                });
                $tempBtn.appendTo($btns);
            });
        } else {
            $btns.hide();
        }


        $d.find(".dialog_button, .dialog_head").css("color", defaultOptions.color)
            .css("background-color", defaultOptions.bgcolor);
        $d.css("border-color", defaultOptions.bgcolor);
        $d.first().addClass(defaultOptions.dclass);
        $d.appendTo($("body"));
        return $d;
    };
    var ajaxLoad = function($dialog, options){
        var $content = $dialog.find(".dialog_content");
       
        var ajaxOptions = $.extend({}, options);
        ajaxOptions.complete = function(){
            $content.hideLoading();
        };
        ajaxOptions.success = function(context) {
                $content.html(context);
                if (options.success) {
                    options.success(context);
                }
            };
        $content.showLoading();
        $.ajax(ajaxOptions);
    };
    return {
        open: open,    
        ask: function (msg, title, callback, buttons, options) {
            title = title || "询问";
            buttons = buttons || [
                {title: "确认", callback: callback},
                {title: "取消"}
            ];
            options = options || {dclass: "dialog-ask"};
            return open(msg, title, buttons, options);
        },
        message: function (msg, title, callback, buttons, options) {
            title = title || "提示";
            buttons = buttons || [
                {title: "确认", callback: callback}
            ];
            options = options || { dclass: "dialog-message"};
            return open(msg, title, buttons, options);
        },
        warn: function (msg, title, callback, buttons, options) {
            title = title || "警告";
            buttons = buttons || [
                {title: "确认", callback: callback}
            ];
            options = options || { dclass: "dialog-warn"};
            return open(msg, title, buttons, options);
        },
        error: function (msg, title, callback, buttons, options) {
            title = title || "错误";
            buttons = buttons || [
                {title: "确认", callback: callback}
            ];
            options = options || { dclass: "dialog-error"};
            return open(msg, title, buttons, options);
        },
        window: function (content,title,buttons, options) {
            title = title || "窗口";
            options = $.extend({dclass : "dialog-window"}, options ||{});
            var ajaxOptions = content;
            var onLoad = function(){};
            content = content || '';
            if (typeof ajaxOptions !== 'string'){
                content = '<div></div> ';
                onLoad = function($dialog){
                    ajaxLoad($dialog, ajaxOptions);
                };
            }
            options.content = content;
            buttons = buttons || [];
            var $d = open('', title, buttons, options);
            onLoad($d);
            return $d;
        }
    };
}();