/**
 * Created by Administrator on 2015/6/5.
 */
function $$(selector, $parent) {
    return $("[f-name='" + selector + "']", $parent);
}
function $v(selector, $parent) {
    return $("[f-value='" + selector + "']", $parent);
}

$.fn.value = function (data) {
    var args = arguments;
    var $this = $(this);

    if ($this.is("input,select,textarea")) {

        if (args.length > 0) {
            if ( $this.is("[type='checkbox']")){
                return $this.prop("checked", data);
            }
            return $this.val(data);
        } else {
            if ( $this.is("[type='checkbox']")){
                return $this.prop("checked");
            }
            return $this.val();
        }
    } else {
        if (args.length > 0) {
            if ( $this.is("span, label")){
                $this.text(data);
            }
            if ( $this.data("dataSource")){
                return $this.data("dataSource")(data);
            }
            return $this.data("value_data", data);
        } else {
            if ( $this.data("dataSource")){
                return $this.data("dataSource")();
            }
            return $this.data("value_data");
        }
    }
}
$.fn.getItem = function () {
    var $items = $(this).find("[f-name]");
    var $itemsFind = $(this).find("[f-name] [f-name]");
    if ($itemsFind.length == 0) {
        return $items
    }
    var $result = $.grep($items, function (value, idx) {
        for (var item in $itemsFind) {
            if (value == $itemsFind[item]) {
                return false;
            }
        }
        return true;
    });
    return $result;
}
$.fn.getData = function () {
    var $items = $(this).getItem();
    // var $items = $(this).find("[f-name]");
    var result = {}
    $($items).each(function (idx, value) {
        var key = $(this).attr("f-name");
        var keyValue = $(this).value();
        result[key] = keyValue;
    });
    return result;
}
$.fn.setData = function (data, reset) {
    var $items = $(this).getItem();
    $($items).each(function (idx, value) {
        var key = $(this).attr("f-name");
        if (typeof data[key] == "undefined" && reset){
            $(this).value(null)
        } else {
            $(this).value(data[key])
        }
    });
}
String.prototype.format = function () {
    var args = arguments;
    var result = this.replace(/{(.+?)}/g, function (match, number) {
        var params = args;
        if (typeof number == "string") {
            params = params[0];
        }
        var r = typeof params[number] != 'undefined' ? params[number] : match;
        return r;
    });
    return result;
};
Date.prototype.format = function(fmt)
{ //author: meizz
    var o = {
        "M+" : this.getMonth()+1,                 //月份
        "d+" : this.getDate(),                    //日
        "h+" : this.getHours(),                   //小时
        "m+" : this.getMinutes(),                 //分
        "s+" : this.getSeconds(),                 //秒
        "q+" : Math.floor((this.getMonth()+3)/3), //季度
        "S"  : this.getMilliseconds()             //毫秒
    };
    if(/(y+)/.test(fmt))
        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
    for(var k in o)
        if(new RegExp("("+ k +")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
    return fmt;
}
Number.prototype.date=function(fmt){
    return (new Date(this*1000)).format(fmt);
}

$(function(){
    $(window).resize(function(){
        $(".full-height").each(function(){
            var $this = $(this);
            var $parent = $this.parent();
            var heightOther = 0;
            $this.siblings().each(function(){
                heightOther += $(this).outerHeight(true);
            });
            var $h1 = $this.outerHeight(true);
            var $h2 = $this.height();
            var $temp = Math.ceil($h1 - $h2);
            $this.height($parent.height() - heightOther - $temp);
        });
    });

    $(window).resize();
});
$.submit = function(url, data, success){
    var options = {
        url: url,
        type: "POST",
        'dataType': 'json',
        'contentType': 'application/json',
        success: function (result) {
            if (result["status"]) {
                if ( success){success(result)}
            } else {
                alert(result["msg"]);
            }
        }
    }
    if(data){
        options['data']  = JSON.stringify(data);
    }
    $.ajax(options)
}
$.getNameValue = function(name, data, data2, data3){
    if(name && typeof name==='object' && Array == name.constructor){
        if (data2){
            // name = name[data2];
            return name[data2];
        } else {
            // name = name[0];
            return name[0];
        }
        // return $.getNameValue(name, data);

    }else if (typeof name == "function"){
        return name(data, data2, data3);
    } else if(typeof data == "string"){
        return data;
    }else if ( data == null){
        return name;
    }else if (typeof name == "string"){
        return data[name];
    }else {
        return null;
    }
}
