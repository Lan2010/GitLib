var regDetail = /(detail\/)(.+?)(\.html)/;

var tempAjaxMui = null;
var tempAjax$ = null;
$(function ($) {

    // 弹新窗口打开页面
    var doforA = function (e, $this) {
        if ($this.hasClass("app-gotoAccount")) {
            return jsCallApp("gotoAccount", {"close": true});
        } else if ($this.hasClass("app-gotoAccount2")) {
            return jsCallApp("gotoAccount", {"close": false});
        } else if ($this.hasClass("app-gotoHome")) {
            return jsCallApp("gotoHome", {"close": true});
        }else if ($this.hasClass("app-gotoModifyPwd")) {
            return jsCallApp("gotoModifyPwd", {"close": false});
        }else if ($this.hasClass("app-gotoModifyGesture")) {
            return jsCallApp("gotoModifyGesture", {"close": false});
        } else if ($this.hasClass("app-gotoLogin")) {
            return jsCallApp("gotoLogin", {"close": false});
        } else if ($this.hasClass("app-popAddCard")) {
            return jsCallApp("popAddCard", {});
        } else if ($this.hasClass("app-callMobile")) {
            var mobile = $this.attr("mobile");
            return jsCallApp("callMobile", {"mobile": mobile});
        } else if ($this.hasClass("app-closePage")) {
            return jsCallApp("closePage", {});
        }else if ($this.hasClass("app-gotoPlist")) {
            return jsCallApp("gotoPlist", {"close": true});
        }else if ($this.hasClass("app-gotoRegister")) {
            return jsCallApp("gotoRegister", {"close": true});
        }
        
        var url = $this.attr("href");
        if (url.indexOf("#") == 0 || url.indexOf("javascript:") == 0) {
            return true;
        }
        var pre = url.indexOf("?") > 0 ? "&" : "?";
        if (url.indexOf("/mobile/publicfund/detail/") >= 0) {
            var m = url.match(regDetail);
            if (m.length > 2) {
                url += pre + "fund=g" + m[2];
            }
        } else if (url.indexOf("/mobile/privatefund/detail/") >= 0) {
            var m = url.match(regDetail);

            if (m.length > 2) {
                url += pre + "fund=s" + m[2];
            }
        }
        var options = {"url": url, "type": 1, "title": $this.attr("popTitle")};

        if ($this.hasClass("app-pop-page")) {
            popPage(options);
        } else if ($this.hasClass("app-pop-page2")) {
            options["type"] = 2;
            popPage(options);
        } else if ($this.hasClass("app-pop-page3")) {
            options["type"] = 3;
            popPage(options);
        } else {
            window.location.href = url;
        }

        e.preventDefault();
        return false;
    };


    if (typeof $ != "undefined") {
        $("body").on("click", "a", function (e) {
            if (doforA(e, $(this))) {
                return true;
            }
            e.preventDefault();
            return false;
        });
        tempAjax$ = $.ajax;
        $.ajax = function (options) {
            var defaultOptions = {};
            $.extend(defaultOptions, options);
            var tempBefore = defaultOptions["beforeSend"];
            defaultOptions["beforeSend"] = function () {
                showProgress();
                if (tempBefore) {
                    tempBefore();
                }

            }
            var tempComplete = defaultOptions["complete"];
            var isCompete = false;
            defaultOptions["complete"] = function () {
                if (!isCompete) {
                    hideProgress();
                }
                if (tempComplete) {
                    tempComplete();
                }
            }
            var tempSuccess = defaultOptions["success"];
            if (defaultOptions["success"]) {
                defaultOptions["success"] = function (content) {
                    isCompete = true;
                    hideProgress();
                    if (typeof content == "object") {
                        if (content["status"] == -10) {
                            // top.window.location = content["data"]["loginUrl"];

                            jsCallApp("gotoLogin", {"close": true});
                            isCompete = true;
                            return;
                        }
                    }
                    if (tempSuccess) {
                        tempSuccess(content);
                    }
                }
            }

            return tempAjax$(defaultOptions);
        }

        $.fn.showLoading = function () {
        };
        $.fn.hideLoading = function () {
        };
    }
});

function appAjax(options) {
    var data = options["data"];
    if (typeof data == "object") {
        data = JSON.stringify(data);
    }
    var args = {url: options["url"], data: data};
    var success = options["success"];
    jsCallApp("ajax", args, success);
}

function gotoPage(url, type, title) {
    type = type || 1;
    title = title || "钱盒子";
    var options = {"url": url, "type": type, "title": title};
    popPage(options);

}

function muiAjax(options) {
    return tempAjaxMui(options);
}
function $ajax(options) {
    return tempAjax$(options);
}
function popPage(options) {
    jsCallApp("popPage", options);
}
function gotoLogin(isclose) {
    jsCallApp("gotoLogin", {"close": isclose||true});
}
function popMsg(msg, type, callback) {
    type = type || 1;
    var myCallback = null;
    if (callback) {
        myCallback = function (result) {
            if (result == "ok") {
                callback();
            }
        }
    }
    jsCallApp("popMsg", {"type": type, "msg": msg}, myCallback);
}

function showProgress() {
    jsCallApp("showProgress", {});

}
function hideProgress() {
    jsCallApp("hideProgress", {});
}

function showToast(msg) {
    jsCallApp("showToast", {"msg": msg});
}

function jsCallApp(code, data, callback) {
    var url = "bridge://appLocal?";
    url += "code=" + code;

    var dataId = (new Date()).toString();
    if (typeof Guid != "undefined") {
        dataId = Guid.NewGuid().ToString();
    }
    url += "&dataID=" + dataId;

    url += "&data=" + JSON.stringify(data);

    if (callback) {
        appCallBackList[dataId] = callback;
    }
    window.location.href = encodeURI(url);
    return false;
}
var appCallBackList = {};
function appCallBack(code, dataId, data) {
    //alert("你调用了appCallBack," + "code: " + code + ", dataId:" + dataId + ", data : " + data);
    var fun = appCallBackList[dataId];
    if (fun) {
        fun(data);
        delete appCallBackList[dataId];
    }
}
//表示全局唯一标识符 (GUID)。

