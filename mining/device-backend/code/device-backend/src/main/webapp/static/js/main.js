﻿define("Global",["jquery","Extend"],function(r){"use strict";var c=r("#pageMain"),t=r("#pageSide"),e=r("#pageContent"),s=null!=window.path?window.path:"/stardev",o=null!=window.apiPath?window.apiPath:"/stardev/api",n=!1,a=r.cookie("nickName");return r.ajaxSetup({dataType:"json",error:function(e,t,o,n){console.log("======================================="),console.log("[ Ajax Error ("+t.status+" / "+n+") ]"),console.log('url:         "'+o.url+'"'),console.log('data:        "'+o.data+'"'),console.log('type:        "'+o.type+'"'),console.log('contentType: "'+o.contentType+'"'),console.log('dataType:    "'+o.dataType+'"'),console.log("=======================================")}}),{$side:t,$page:c,$content:e,path:s,api:{path:o},nickName:a,checkBrowser:function(){var e=navigator.appVersion.match(/MSIE 7.0|MSIE 6.0|MSIE 8.0|MSIE 9.0|MSIE 10.0/i);return e&&(r("body").msg("tip",{text:"尊敬的用户，你好！ 本系统不支持IE10及以下的浏览器，请升级你的浏览器。推荐谷歌、火狐浏览器！",mask:!0,close:!1,level:"top"}),t.remove(),c.remove()),!e},checkJson:function(e,t){var o=!0,n=!0,a={},i="";"[object Object]"===Object.prototype.toString.call(t)&&r.extend(a,t),"[object Boolean]"===Object.prototype.toString.call(t)&&(n=t);var l=10101==e.code||10302==e.code;"[object Object]"!=Object.prototype.toString.call(e)&&(o=!(i="数据非对象格式")),0!=e.code&&(i=e.msg?e.msg:e.code,l&&(i="登录超时，2秒后请重新登录"),o=!1),!o&&n&&("top"==a.level?r("body"):c).msg("tip",r.extend({text:i,mask:!!l,time:l?0:3500},a));return l&&setTimeout(function(){window.location.replace(s+"/")},2e3),o},pageReset:function(){return c.removeClass("page-gray"),r("div.datetimepicker,span.select2-hidden-accessible,div.select2-drop").remove(),n&&c.msg("hide"),n=!1,this},pageError:function(e){return n=!0,c.msg("error",{text:e,mask:!0,close:!1}),this}}}),define("Modal",["jquery","Extend"],function(i){"use strict";var l,r=i(window),c=i("body"),s=function(){var e=this,t=r.height()-e.main.outerHeight(),o=r.width(),n=o<=e.options.width?o:e.options.width;t=0<=t?t/2:20,e.main.css({top:t+"px",width:n+"px",opacity:1})},d=function(){l.remove(),c.removeClass("body-modal"),r.off("resize"),l=null};return{show:function(e){var t,o,n,a=i.extend({width:720,minHeight:200,initMove:200,title:"标题",path:"",js:!0,data:{}},e);c.addClass("body-modal"),t=a,o=(r.height()-t.minHeight)/2-t.initMove,n=i(['<div class="modal">','<div class="page-mask-modal"></div>','<div class="modal-cell">','<div class="modal-main transform modal-default" style="width: '+t.width+"px; top: "+(0<o?o:"0")+'px;">','<div class="modal-header">','<div class="title">'+t.title+"</div>",'<div class="close" title="关闭"><i class="fa fa-times"></i></div>',"</div>",'<div class="modal-body" style="min-height: '+(t.minHeight-48)+'px;"></div>',"</div>","</div>","</div>"].join("")),c.append(n),l=n,i.extend(!0,l,{options:a,body:l.find(".modal-body"),main:l.find(".modal-main")}),function(){this.on("click",".close",function(){d()})}.call(l),s.call(l),l.body.loading(),a.path&&require(["text!module/"+a.path+".html"],function(e){l.body.html(e),s.call(l),r.oResize(function(){s.call(l)}),a.js?require(["module/"+a.path],function(e){e.onLoad&&e.onLoad(a.path,a.data),setTimeout(function(){s.call(l)},50)}):setTimeout(function(){s.call(l)},50)},function(e){console.log("[Modal] require html error",e.xhr.status),l.body.loading(!1)})},hide:d}}),define("Header",["jquery","Global","Modal"],function(t,o,e){"use strict";var n=function(){t.ajax({url:o.path+"/api/logout",cache:!1,success:function(e){o.checkJson(e)&&window.location.replace(o.path+"/")},error:function(e){t("body").msg("error",{text:e.status+" - "+e.statusText,level:"top"})}})};return t("#logo").on("click",function(){window.location.href="./"}),t("#logout").on("click",n),t("#btnModPass").on("click",function(){e.show({width:600,title:"你的登录密码",path:"user/pass"})}),t("#headerNickName").text(o.nickName?o.nickName:"欢迎"),{logout:n}}),define("Menu",["jquery","Global"],function(t,e){"use strict";var o=t("#pageMenu");return o.on("click",".node",function(){var e=t(this).parent();o.find(".open"),e.hasClass("open")?e.find("ul").slideUp("fast",function(){e.removeClass("open")}):e.find("ul").slideDown("fast",function(){e.addClass("open")})}),{active:function(e){o.find(".active").removeClass("active"),e&&o.find('a[href^="#'+e.replace(/\//g,"\\/")+'"]').addClass("active").parent().parent().parent().addClass("open active")}}}),define("TSearch",["jquery"],function(i){"use strict";var l=function(e){i("#tFilter").css("display",e?"block":"none"),i("#tFormSearch").find(".adv").toggleClass("open",!!e)};return{init:function(t){var o=i("#tFormSearch"),e=i("#tFilter"),n=i("#tFormFilter"),a=n.length;o.on("submit",function(){a&&n.get(0).reset();var e=o.packJson({nullVal:!1});return t.onSearch&&t.onSearch(e),o.find(".clear").toggleClass("hide",!e),!1}),o.find(".clear").on("click",function(){i(this).addClass("hide"),o.get(0).reset();var e=a?n.packJson({nullVal:!1}):{};t.onClear&&t.onClear(e)}),o.find("input[data-name=keyword]").on("focus",function(){i(this).parent().addClass("keyword-focus")}).on("blur",function(){i(this).parent().removeClass("keyword-focus")}),o.find("span[data-toggle=filter]").on("click",function(){l("none"==e.css("display"))}),a&&(n.on("submit",function(){return t.onFilter&&t.onFilter(i.extend(o.packJson({nullVal:!1}),n.packJson({nullVal:!1}))),!1}),n.find("button[data-btn=clear]").on("click",function(){n.get(0).reset(),t.onFiltClear&&t.onFiltClear(o.packJson({nullVal:!1}))}))},fillData:function(e){var t=i("#tFormSearch"),o=i("#tFormFilter");t.get(0).reset(),t.fillForm(e),o.length&&(o.get(0).reset(),o.fillForm(e)),t.find(".clear").toggleClass("hide",!t.packJson({nullVal:!1})),o.length&&l(!!o.packJson({nullVal:!1}))}}}),require.config({baseUrl:(null!=window.path?window.path:"/auth")+"/static/js",paths:{module:"../../module",async:"lib/require.async",text:"lib/require.text",jquery:"lib/jquery.min",Route:"lib/route",Extend:"jqueryPlugin/jquery.extend",Grid:"jqueryPlugin/jquery.grid",DTP:"jqueryPlugin/jquery.datetimepicker",Validate:"jqueryPlugin/jquery.validate",Select2:"jqueryPlugin/jquery.select2.v3.5.4",BMap:"https://api.map.baidu.com/api?v=3.0&ak=Lelvz6yh1CSHGNNhOmHVPfkp",kindeditor:"kindeditor/kindeditor-all",ParamsConfig:"paramsConfig"},shim:{BMap:{deps:[],exports:"BMap"},kindeditor:{deps:["jquery"]}},urlArgs:"v="+window.version}),require(["Route","jquery","Extend","Global","Header","Menu"],function(e,a,t,i,o,l){"use strict";i.checkBrowser()&&(i.pageReset(),e.extend({routes:{"":"routeHome",":module/:action":"routePage","*":"routeError"},routeHome:function(e){var t=e[0]?a.getAllParam("?"+e[0]):null;i.$page.loading(),l.active(""),require(["text!module/index.html"],function(e){i.pageReset(),i.$content.html(e),require(["module/index"],function(e){e.onLoad&&e.onLoad(null,t),i.$page.loading(!1)})})},routePage:function(e,t,o){var n=e[2]?a.getAllParam("?"+e[2]):null;t!=o?(i.$page.loading(),require(["text!module/"+t+".html"],function(e){i.pageReset(),i.$content.html(e),require(["module/"+t],function(e){e.onLoad&&e.onLoad(t,n),i.$page.loading(!1)})},function(e){console.log("[App routePage] require html error"),console.log(e),l.active(t),require(["text!module/404.html"],function(e){i.pageReset(),i.$content.html(e),i.$page.loading(!1)})})):require(["module/"+t],function(e){e.onSearch&&e.onSearch(t,n)})},routeError:function(){console.log("[App routeError] path rule error"),l.active(""),require(["text!module/403.html"],function(e){i.pageReset(),i.$content.html(e),i.$page.loading(!1)})}}).start())});