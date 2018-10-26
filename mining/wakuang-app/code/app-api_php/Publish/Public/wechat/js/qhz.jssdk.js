if ($ == undefined && jQuery == undefined) {
    console.log('jQuery is required!');
}
//eventHandler为独立模块
var eventHandler = function (host) {
    this.eventList = {};
    this.eventRely = {};
    this.host = host;
};
eventHandler.prototype.on = function (eventName, handler) {
    if (typeof eventName != 'string') {
        console.log("eventName must be a string!");
        return false;
    }
    if (typeof handler != 'function') {
        console.log("event handler must be a function!");
        return false;
    }
    if (!(eventName in this.eventList)) {
        this.eventList[eventName] = [];
    }
    this.eventList[eventName].push(handler);
    return this;//to support chain method
};
eventHandler.prototype.rely = function (eventName, relyEventList) {
    if (this.eventRely[eventName] == undefined) {
        this.eventRely[eventName] = [];
    }
    for (var i = 0; i < relyEventList.length; i++) {
        if (this.eventRely[eventName].indexOf(relyEventList[i]) == -1) {
            this.eventRely[eventName].push(relyEventList[i]);
        }
    }
};
eventHandler.prototype.off = function (eventName, handler) {
    if (typeof eventName != 'string') {
        console.log("eventName must be a string!");
        return false;
    }
    if (handler == undefined) {
        //remove all handlers
        this.eventList[eventName] = [];
        return this;//to support chain method
    } else {
        if (typeof handler != 'function') {
            console.log("event handler must be a function!");
            return false;
        }
        if (this.eventList[eventName] == undefined) {
            console.log("this event has not been registered!");
        } else {
            for (var i = 0; i < this.eventList[eventName].length; i++) {
                if (this.eventList[eventName][i] === handler) {
                    this.eventList[eventName].splice(i, 1);
                    i--;
                }
            }
        }
    }
};
eventHandler.prototype.emit = function (eventName) {
    if (typeof eventName != 'string') {
        console.log("eventName must be a string!");
        return false;
    }
    if (!this.eventList[eventName]) {
        console.log(eventName + " is not defined");
        this.checkRely(eventName);
        return;
    }
    for (var i = 0; i < this.eventList[eventName].length; i++) {
        var handler = this.eventList[eventName][i];
        if (!!this.host) {
            handler.call(this.host);
        } else {
            handler();
        }
    }
    this.checkRely(eventName);
};
eventHandler.prototype.checkRely = function (eventName) {
    for (var relyEvent in this.eventRely) {
        for (var i = 0; i < this.eventRely[relyEvent].length; i++) {
            if (this.eventRely[relyEvent][i] == eventName) {
                this.eventRely[relyEvent].splice(i, 1);
                break;
            }
        }
    }
    for (var relyEvent in this.eventRely) {
        if (this.eventRely[relyEvent].length == 0) {
            var relyEventName = relyEvent;
            delete this.eventRely[relyEvent];
            this.emit(relyEventName);
        }
    }
};

//获取url参数
function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = location.search.substr(1).match(reg) || location.hash.substr(1).match(reg);
    if (r != null)
        return unescape(r[2]);
    return '';
}

//获取url参数
function getQueryString2(src, name) {
    src = src.split('?')[1];
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = src.match(reg);
    if (r != null)
        return unescape(r[2]);
    return '';
}
/**
 * [QHZAPP JSSDK]
 */
var QHZAPP = function (config) {
    var qhzapp = this;
    qhzapp.eventHandler = new eventHandler();
    qhzapp.api = config.api;
    qhzapp.shareInfo = {};
    qhzapp.parameter="";
    qhzapp.sendShareNum=1;
    qhzapp.isApiShare=false;
    //判断设备
    qhzapp.device = function () {
        var u = navigator.userAgent;
        return {//移动终端浏览器版本信息 
            trident: u.indexOf('Trident') > -1, // IE内核
            presto: u.indexOf('Presto') > -1, // opera内核
            webKit: u.indexOf('AppleWebKit') > -1, // 苹果、谷歌内核
            gecko: u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1, // 火狐内核
            mobile: !!u.match(/AppleWebKit.*Mobile.*/) || !!u.match(/AppleWebKit/), // 是否为移动终端
            ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), // ios终端
            android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1, // android终端或者uc浏览器
            iPhone: u.indexOf('iPhone') > -1 || u.indexOf('Mac') > -1, // 是否为iPhone或者QQHD浏览器
            iPad: u.indexOf('iPad') > -1, // 是否iPad
            weixin: u.indexOf('MicroMessenger') > -1, // 是否微信
            app: u.indexOf('Qianhezi') > -1 || u.indexOf('qhz') > -1, // 是否APP
        };
    }();
    qhzapp.isIOS = (function () {
//        return true;
        return qhzapp.device.ios || qhzapp.device.iPhone || qhzapp.device.iPad;
    })();
    qhzapp.isAndroid = (function () {
        return qhzapp.device.android;
    })();
    qhzapp.isMobile = (function () {
        return qhzapp.device.mobile || qhzapp.isIOS || qhzapp.isAndroid;
    })();
    qhzapp.isWeixin = (function () {
        return qhzapp.device.weixin;
    })();
    qhzapp.isApp = (function () {
//        return true;
        return qhzapp.device.app;
    })();
    qhzapp.isWap = (function () {
        return !qhzapp.isWeixin && !qhzapp.isApp;
    })();

    //当一个api初始化完毕，执行此方法，将该apiName从qhzapp.initApiList中踢出
    qhzapp.apiReady = function (apiName) {
        for (var i = 0; i < qhzapp.initApiList.length; i++) {
            if (qhzapp.initApiList[i] == apiName) {
                qhzapp.initApiList.splice(i, 1);
                break;
            }
        }
        qhzapp.eventHandler.emit('checkready');
    };
    //ready接口，入参为全部api初始化完成之后的回调函数
    qhzapp.ready = function (readyFunction) {
        qhzapp.eventHandler.on('ready', readyFunction);
        if (this.isDefaultApiReady) {
            console.log("define ready function after default api readied");
            readyFunction();
        }
    };
    qhzapp.share = function (params) {
        if (params == undefined) {
            params = {};
        }
        if (qhzapp.shareInfo.code == 0) {
            var result = qhzapp.shareInfo.result;
        } else {
            var result = {};
        }
        if (qhzapp.isApp) {
            qhzapp.onShare({
                title: params.title || result.title,
                msg: params.msg || result.msg,
                imgurl: params.imgurl || result.imgurl,
                url: params.url || result.url
            }, params.callback);
        } else if (qhzapp.isWeixin) {
            var shareInfo = params || qhzapp.shareInfo.result;
            menuShareTimeline(shareInfo);
        }
    };
    //当webview首次打开或重新被show出来时调用
    qhzapp.webviewOnShow = function (webviewOnShowFunction) {
        qhzapp.eventHandler.on("webviewOnShow", webviewOnShowFunction);
    };
    qhzapp.jumpToUrl = function (url) {
        url = ((url == "" || url == undefined || url == null) ? "" : url);
        if (!!url.toLowerCase().match(/project\/plist/)) { //跳转项目详情页
            qhzapp.jumpToPage.jumpToIndex(1);
        } else if (!!url.toLowerCase().match(/account\/index/)) { //跳转项目详情页
            qhzapp.jumpToPage.jumpToIndex(3);
        } else if (!!url.toLowerCase().match(/user\/login/)) {
            qhzapp.jumpToPage.jumpToLogin();
        } else if (!!url.toLowerCase().match(/user\/register/)) {
            qhzapp.jumpToPage.jumpToRegister();
        } else if (!!url.toLowerCase().match(/huax\/realname/)) {
            qhzapp.jumpToPage.jumpToRealName();
        } else if (!!url.toLowerCase().match(/huax\/recharge/)) {
            qhzapp.jumpToPage.jumpToRecharge();
        } else if (!!url.toLowerCase().match(/huax\/withdraw/)) {
            qhzapp.jumpToPage.jumpToWithDraw();
        } else if (!!url.toLowerCase().match(/huax\/bindcard/)) {
            qhzapp.jumpToPage.jumpToBindCard();
        } else if (!!url.toLowerCase().match("closewebview") && qhzapp.isApp) {
            qhzapp.closeWebView();
        } else {
            if (!qhzapp.isApp) {
                window.location = config.wxHost + url;
            } else {
                qhzapp.jumpToPage.jumpToIndex(0);
            }
        }
    };
    //调用分享
    qhzapp.getShareInfo = function (actcode, shareurl) {
        $.ajax({
            type: 'GET',
            url: config.wxHost + "Weixin/getShareInfo",
            contentType: "application/json",
            data: {actcode: actcode, shareurl: shareurl},
            cache: false,
            dataType: "json",
            success: function (result) {
                qhzapp.shareInfo = result;
                qhzapp.isGotShareInfo = true;
                console.log("gotShareInfo");
                qhzapp.eventHandler.emit('gotShareInfo');
            },
            error: function () {
                console.log("gotShareInfo");
                qhzapp.eventHandler.emit('gotShareInfo');
            }
        });
    };

    //对Native提供的api进行封装，提供统一的调用方式
    function initDefaultApi() {
        var jumpToPage = {};
        if (qhzapp.isIOS) {
            jumpToPage.jumpToIndex = function (index) {
                var classNmaeArr = ["QHZHomeViewController", "QHZFinancingViewController", "QHZDiscoverViewController", "QHZMineViewController"];
                var switchPageName = ["home", "financing", "discover", "mine"];
                var data = {
                    className: classNmaeArr[index]
                };
                qhzapp.switchPageId = switchPageName[index];
                qhzapp.jumpToActivity(data);
            };
            jumpToPage.jumpToLogin = function () {
                var data = {
                    className: "QHZLoginController"
                };
                qhzapp.switchPageId = "login";
                qhzapp.jumpToActivity(data);
            };
            jumpToPage.jumpToRegister = function () {
                var data = {
                    className: "QHZRegisteredController"
                };
                qhzapp.switchPageId = "register";
                qhzapp.jumpToActivity(data);
            };
            jumpToPage.jumpToRecharge = function () {
                var data = {
                    className: "QHZTopUpController",
                };
                qhzapp.switchPageId = "recharge";
                qhzapp.jumpToActivity(data);
            };
            jumpToPage.jumpToWithDraw = function () {
                var data = {
                    className: "QHZWithdrawalController",
                };
                qhzapp.switchPageId = "withdraw";
                qhzapp.jumpToActivity(data);
            };
            jumpToPage.jumpToRealName = function () {
                var data = {
                    className: "QHZRealNameController",
                };
                qhzapp.switchPageId = "realname";
                qhzapp.jumpToActivity(data);
            };
            jumpToPage.jumpToBindCard = function () {
                var data = {
                    className: "QHZRealNameController",
                };
                qhzapp.switchPageId = "bindcard";
                qhzapp.jumpToActivity(data);
            };
        } else if (qhzapp.isAndroid) {
            jumpToPage.jumpToIndex = function (index) {
                var classNmaeArr = ["TabHomeFragment", "TabInvestFragment", "TabDiscoverFragment", "TabAccountFragment"];
                var switchPageName = ["home", "financing", "discover", "mine"];
                var data = {
                    activityName: classNmaeArr[index],
                    anim: true
                };
                qhzapp.switchPageId = switchPageName[index];
                qhzapp.jumpToActivity(data);
            };
            jumpToPage.jumpToLogin = function () {
                var data = {
                    activityName: "LoginActivity",
                    anim: false
                };
                qhzapp.switchPageId = "login";
                qhzapp.jumpToActivity(data);
            };
            jumpToPage.jumpToRegister = function () {
                var data = {
                    activityName: "RegisterActivity",
                    anim: true
                };
                qhzapp.switchPageId = "register";
                qhzapp.jumpToActivity(data);
            };
            jumpToPage.jumpToRecharge = function () {
                var data = {
                    activityName: "ReChargeActivity",
                    anim: true
                };
                qhzapp.switchPageId = "recharge";
                qhzapp.jumpToActivity(data);
            };
            jumpToPage.jumpToWithDraw = function () {
                var data = {
                    activityName: "WithDrawActivity",
                    anim: true
                };
                qhzapp.switchPageId = "withdraw";
                qhzapp.jumpToActivity(data);
            };
            jumpToPage.jumpToRealName = function () {
                var data = {
                    activityName: "RealNameIdentificationActivity",
                    anim: true
                };
                qhzapp.switchPageId = "realname";
                qhzapp.jumpToActivity(data);
            };
            jumpToPage.jumpToBindCard = function () {
                var data = {
                    activityName: "RealNameIdentificationActivity",
                    anim: true
                };
                qhzapp.switchPageId = "bindcard";
                qhzapp.jumpToActivity(data);
            };
        }
        qhzapp.jumpToPage = jumpToPage;

        console.log('default api init');
        qhzapp.eventHandler.emit('initDefault');
        console.log('gotBasicInfo');
        qhzapp.eventHandler.emit('gotBasicInfo');
        window.appInitCallback = function (result) {
            if (typeof result == "string") {
                var result = JSON.parse(result);
            }
            qhzapp.uid = result.data.id;
            qhzapp.version = result.data.version;
//            console.log('gotBasicInfo');
//            qhzapp.eventHandler.emit('gotBasicInfo');
        }
        qhzapp.appInit("window.appInitCallback");
    }
    ;

    function initDefault() {
        if (qhzapp.isApp) {
            if (qhzapp.isIOS) {
                setupWebViewJavascriptBridge(function (bridge) {
                    qhzapp.jumpToActivity = function (params, callback) {
                        bridge.callHandler('jumpToNative', params, callback);
                    };
                    qhzapp.closeWebView = function () {
                        bridge.callHandler('closeWebView');
                    };
                    qhzapp.appInit = function (callbackName) {
                        bridge.callHandler('appInit', '', eval(callbackName));
                    };
                    qhzapp.onShare = function (params, callbackName) {
                        bridge.callHandler('onShare', params, eval(callbackName));
                    };
                    qhzapp.requestByNative = function (params, callbackName) {
                        bridge.callHandler('requestByNative', params, eval(callbackName));
                    };
                    bridge.registerHandler('webviewOnShow', function () {
                        qhzapp.eventHandler.emit('webviewOnShow');
                    });
                    bridge.registerHandler('appShareAct', function (msg) {
                        qhzapp.parameter=msg; 
                        qhzapp.eventHandler.emit('appShareAct');
                    });
                    initDefaultApi();
                })
            } else {
                qhzapp.androidCallNative = function (functionName, data, callbackName) {
                    var dataArr = {
                        func: functionName,
                        data: data,
                        callback: callbackName
                    };
                    var requestData = JSON.stringify(dataArr);
                    Qianhezi.callNative(requestData);
                };
                qhzapp.jumpToActivity = function (params, callbackName) {
                    qhzapp.androidCallNative("jumpToNative", params, callbackName);
                };
                qhzapp.closeWebView = function () {
                    qhzapp.androidCallNative("closeWebView", {});
                };
                qhzapp.appInit = function (callbackName) {
                    qhzapp.androidCallNative("appInit", {}, callbackName);
                };
                qhzapp.onShare = function (params, callbackName) {
                    if (typeof params != "string") {
                        params = JSON.stringify(params);
                    }
                    qhzapp.androidCallNative("onShare", params, callbackName);
                };
                qhzapp.requestByNative = function (params, callbackName) {
                    if (typeof params != "string") {
                        params = JSON.stringify(params);
                    }
                    qhzapp.androidCallNative('RequestByNative', params, callbackName);
                };
                initDefaultApi();
            }
        } else {
            var jumpToPage = {};
            console.log(config);
            jumpToPage.jumpToIndex = function (index) {
                var urlArr = ['Index', 'Project/plist', 'Project/plist', 'Account'];
                window.location = config.wxHost + urlArr[index];
            };
            jumpToPage.jumpToLogin = function () {
                window.location = config.wxHost + "User/Login";
            };
            jumpToPage.jumpToRegister = function () {
                window.location = config.wxHost + "User/Register";
            };
            jumpToPage.jumpToRecharge = function () {
                window.location = config.wxHost + "Huax/Recharge";
            };
            jumpToPage.jumpToWithDraw = function () {
                window.location = config.wxHost + "Huax/withDraw";
            };
            jumpToPage.jumpToRealName = function () {
                window.location = config.wxHost + "Huax/realName";
            };
            jumpToPage.jumpToBindCard = function () {
                window.location = config.wxHost + "Huax/bindCard";
            };
            qhzapp.jumpToPage = jumpToPage;
            qhzapp.eventHandler.emit('initDefault');
        }
    }
    function setupWebViewJavascriptBridge(callback) {
        if (window.WebViewJavascriptBridge) {
            return callback(WebViewJavascriptBridge);
        }
        if (window.WVJBCallbacks) {
            return window.WVJBCallbacks.push(callback);
        }
        window.WVJBCallbacks = [callback];

        (function () {
            if (window.WebViewJavascriptBridge) {
                return;
            }
            window.WebViewJavascriptBridge = {
                registerHandler: registerHandler,
                callHandler: callHandler,
                _fetchQueue: _fetchQueue,
                _handleMessageFromObjC: _handleMessageFromObjC
            };

            var messagingIframe;
            var sendMessageQueue = [];
            var messageHandlers = {};

            var CUSTOM_PROTOCOL_SCHEME = 'wvjbscheme';
            var QUEUE_HAS_MESSAGE = '__WVJB_QUEUE_MESSAGE__';

            var responseCallbacks = {};
            var uniqueId = 1;

            function registerHandler(handlerName, handler)
            {
                messageHandlers[handlerName] = handler;
            }

            function callHandler(handlerName, data, responseCallback)
            {
                if (arguments.length == 2 && typeof data == 'function') {
                    responseCallback = data;
                    data = null;
                }
                _doSend({handlerName: handlerName, data: data}, responseCallback);
            }

            function _doSend(message, responseCallback)
            {
                if (responseCallback) {
                    var callbackId = 'cb_' + (uniqueId++) + '_' + new Date().getTime();
                    responseCallbacks[callbackId] = responseCallback;
                    message['callbackId'] = callbackId;
                }
                sendMessageQueue.push(message);
                messagingIframe.src = CUSTOM_PROTOCOL_SCHEME + '://' + QUEUE_HAS_MESSAGE;
            }

            function _fetchQueue()
            {
                var messageQueueString = JSON.stringify(sendMessageQueue);
                sendMessageQueue = [];
                return messageQueueString;
            }

            function _dispatchMessageFromObjC(messageJSON)
            {
                setTimeout(function _timeoutDispatchMessageFromObjC() {
                    var message = JSON.parse(messageJSON);
                    var messageHandler;
                    var responseCallback;

                    if (message.responseId) {
                        responseCallback = responseCallbacks[message.responseId];
                        if (!responseCallback) {
                            return;
                        }
                        responseCallback(message.responseData);
                        delete responseCallbacks[message.responseId];
                    } else {
                        if (message.callbackId) {
                            var callbackResponseId = message.callbackId;
                            responseCallback = function (responseData)
                            {
                                _doSend({responseId: callbackResponseId, responseData: responseData});
                            };
                        }
                        var handler = messageHandlers[message.handlerName];
                        handler(message.data, responseCallback);
                        if (!handler) {
                            console.log("WebViewJavascriptBridge: WARNING: no handler for message from ObjC:", message);
                        }
                    }
                });
            }

            function _handleMessageFromObjC(messageJSON)
            {
                _dispatchMessageFromObjC(messageJSON);
            }

            messagingIframe = document.createElement('iframe');
            messagingIframe.style.display = 'none';
            messagingIframe.src = CUSTOM_PROTOCOL_SCHEME + '://' + QUEUE_HAS_MESSAGE;
            document.documentElement.appendChild(messagingIframe);

            setTimeout(_callWVJBCallbacks, 0);
            function _callWVJBCallbacks()
            {
                var callbacks = window.WVJBCallbacks;
                delete window.WVJBCallbacks;
                for (var i = 0; i < callbacks.length; i++) {
                    callbacks[i](WebViewJavascriptBridge);
                }
            }
        })();

        var WVJBIframe = document.createElement('iframe');
        WVJBIframe.style.display = 'none';
        WVJBIframe.src = 'wvjbscheme://__BRIDGE_LOADED__';
        document.documentElement.appendChild(WVJBIframe);
        setTimeout(function () {
            document.documentElement.removeChild(WVJBIframe)
        }, 0)
    }

    //微信分享
    //需要在页面引入http://res.wx.qq.com/open/js/jweixin-1.0.0.js
    /*****************微信分享********************/
    function wxConfig() {
        $.ajax({
            url: config.wxHost + "Weixin/getShareConfig",
            type: "GET",
            async: true,
            dataType: "json",
            data: {
                "url": window.location.href
            },
            success: function (result) {
                var jsCode = "wx.config({" +
                        "appId:'" + result.appId +
                        "',timestamp:'" + result.timestamp +
                        "',nonceStr:'" + result.nonceStr +
                        "',signature:'" + result.signature +
                        "',url:'" + result.url +
                        "',debug: false,jsApiList: ['checkJsApi','onMenuShareTimeline','onMenuShareAppMessage']});";
                eval(jsCode);
            },
            error: function () {
            }
        })
    }
    //设置分享
    function menuShareTimeline(params) {
        //分享微信朋友圈
        wx.onMenuShareTimeline({
            title: params.title,
            link: params.url,
            imgUrl: params.imgurl,
            success: function () {
                sendShareAjax("WechatMoments");
            },
            cancel: function () {},
            error: function () {}
        });
        //发送朋友
        wx.onMenuShareAppMessage({
            title: params.title, // 分享标题
            desc: params.msg, // 分享描述
            link: params.url, // 分享链接
            imgUrl: params.imgurl,
            type: '', // 分享类型,music、video或link，不填默认为link
            dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
            success: function () {
                sendShareAjax("Wechat");
            },
            cancel: function () {
                // 用户取消分享后执行的回调函数
            }
        });
        //分享到微博
        wx.onMenuShareWeibo({
            title: params.title, // 分享标题
            desc: params.msg, // 分享描述
            link: params.url, // 分享链接
            imgUrl: params.imgurl,
            success: function () {
                // 用户确认分享后执行的回调函数
                sendShareAjax("SinaWeibo");
            },
            cancel: function () {
                // 用户取消分享后执行的回调函数
            }
        });
        //分享到QQ空间
        wx.onMenuShareQZone({
            title: params.title, // 分享标题
            desc: params.msg, // 分享描述
            link: params.url, // 分享链接
            imgUrl: params.imgurl,
            success: function () {
                // 用户确认分享后执行的回调函数
                sendShareAjax("QZone");
            },
            cancel: function () {
                // 用户取消分享后执行的回调函数
            }
        });
        //分享到QQ
        wx.onMenuShareQQ({
            title: params.title, // 分享标题
            desc: params.msg, // 分享描述
            link: params.url, // 分享链接
            imgUrl: params.imgurl,
            success: function ()
            {
                // 用户确认分享后执行的回调函数
                 sendShareAjax("QQ");
            },
            cancel: function () {
                // 用户取消分享后执行的回调函数
            }
        });
    }

    //初始化API，将待初始化的apiName推入qhzapp.initApiList
    qhzapp.initApiList = [];
    for (var i = 0; i < qhzapp.api.length; i++) {
        if (qhzapp.api[i] == 'share') {
            qhzapp.initApiList.push('share');
            if (qhzapp.isApp) {
                qhzapp.eventHandler.on('gotBasicInfo', function () {
                    qhzapp.getShareInfo(config.actcode, config.shareurl);
                })
            } else if (qhzapp.isWeixin) {
                qhzapp.eventHandler.on('initWeixin', function () {
                    console.log("initWeixin");
                    wxConfig();
                    wx.ready(function () {
                        console.log('initShare');
                        qhzapp.eventHandler.emit('initShare');
                    })
                })
            }
        }
        if (qhzapp.api[i] == 'apiShare') {
            qhzapp.isApiShare=true;
        }
    }
    qhzapp.initApiList.push('default');
    
    
    //分享成功回调
    function sendShareAjax(shareType){
    var gameID=$("#gameID").val();
    if(qhzapp.sendShareNum!=1 || qhzapp.isApiShare==false){
        return false;
    }
    $.ajax({
            type: "post",
            url: "/Wechat/Activity/actShare",
            data: {shareType: shareType,gameID:gameID},
            dataType: "json",
            beforeSend: function() { 
                qhzapp.sendShareNum+=1;
             },
            success: function (data) {
             $.alert(data.msg,function(){
                 window.location.reload();    
                });
            }
        });
    };

    //注册事件
    if (qhzapp.isApp) {
        qhzapp.eventHandler.on('initDefault', function () {
            qhzapp.apiReady('default');
        })
        qhzapp.eventHandler.on('gotShareInfo', function () {
            qhzapp.apiReady('share');
        })
        qhzapp.eventHandler.on('webviewOnShow', function () {
            console.log("webviewOnShow:" + qhzapp.switchPageId);
            if (qhzapp.switchPageId == "login") {
                window.location.reload();
            }
        })
        qhzapp.eventHandler.on('appShareAct', function () {
               sendShareAjax(qhzapp.parameter);
               qhzapp.parameter="";//清除传递过来的参数
        })
    } else {
        qhzapp.eventHandler.on('initDefault', function () {
            qhzapp.apiReady('default');
        })
        qhzapp.eventHandler.on('initShare', function () {
            qhzapp.getShareInfo(config.actcode, config.shareurl);
            qhzapp.apiReady('share');
            qhzapp.eventHandler.emit('gotShareInfo');
        })
        qhzapp.eventHandler.on('gotShareInfo', function () {
            qhzapp.share(qhzapp.shareInfo.result);
        })
    }
    qhzapp.eventHandler.on('checkready', function () {
        if (qhzapp.initApiList.length == 0) {
            console.log('JSSDK is ready');
            if (!qhzapp.isDefaultApiReady) {
                qhzapp.eventHandler.emit('ready');
            }
            qhzapp.isDefaultApiReady = true;
        }
    })

    window.webviewOnShow = function (arg) {
        qhzapp.eventHandler.emit("webviewOnShow");
//        if (arg == 1) {
//            qhzapp.eventHandler.emit("webviewOnShow");
//            qhzapp.switchPageId = "";
//        }
//        console.log("webviewOnShow:" + arg);
    }
    window.appShareAct = function (arg) {
        qhzapp.parameter=arg; 
        qhzapp.eventHandler.emit("appShareAct");
    }
    if (qhzapp.isApp) {
        initDefault();
    } else if (qhzapp.isWeixin) {
        initDefault();
        if (qhzapp.initApiList.indexOf('share') > -1) {
            qhzapp.eventHandler.emit('initWeixin');
        }
    } else {
        initDefault();
        qhzapp.apiReady('share');
    }
}
//输出
if (typeof module == 'undefined') {
    window.QHZAPP = QHZAPP;
} else {
    module.exports.QHZAPP = QHZAPP;
}

