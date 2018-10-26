﻿!function(x){"use strict";var c={};(x.respond=c).update=function(){};var s=[],n=function(){var t=!1;try{t=new x.XMLHttpRequest}catch(e){t=new x.ActiveXObject("Microsoft.XMLHTTP")}return function(){return t}}(),e=function(e,t){var a=n();a&&(a.open("GET",e,!0),a.onreadystatechange=function(){4!==a.readyState||200!==a.status&&304!==a.status||t(a.responseText)},4!==a.readyState&&a.send(null))},p=function(e){return e.replace(c.regex.minmaxwh,"").match(c.regex.other)};if(c.ajax=e,c.queue=s,c.unsupportedmq=p,c.regex={media:/@media[^\{]+\{([^\{\}]*\{[^\}\{]*\})+/gi,keyframes:/@(?:\-(?:o|moz|webkit)\-)?keyframes[^\{]+\{(?:[^\{\}]*\{[^\}\{]*\})+[^\}]*\}/gi,comments:/\/\*[^*]*\*+([^/][^*]*\*+)*\//gi,urls:/(url\()['"]?([^\/\)'"][^:\)'"]+)['"]?(\))/g,findStyles:/@media *([^\{]+)\{([\S\s]+?)$/,only:/(only\s+)?([a-zA-Z]+)\s?/,minw:/\(\s*min\-width\s*:\s*(\s*[0-9\.]+)(px|em)\s*\)/,maxw:/\(\s*max\-width\s*:\s*(\s*[0-9\.]+)(px|em)\s*\)/,minmaxwh:/\(\s*m(in|ax)\-(height|width)\s*:\s*(\s*[0-9\.]+)(px|em)\s*\)/gi,other:/\([^\)]*\)/g},c.mediaQueriesSupported=x.matchMedia&&null!==x.matchMedia("only all")&&x.matchMedia("only all").matches,!c.mediaQueriesSupported){var y,v,w,E=x.document,S=E.documentElement,T=[],$=[],z=[],i={},b=E.getElementsByTagName("head")[0]||S,o=E.getElementsByTagName("base")[0],C=b.getElementsByTagName("link"),R=function(){var e,t=E.createElement("div"),a=E.body,n=S.style.fontSize,r=a&&a.style.fontSize,s=!1;return t.style.cssText="position:absolute;font-size:1em;width:1em",a||((a=s=E.createElement("body")).style.background="none"),S.style.fontSize="100%",a.style.fontSize="100%",a.appendChild(t),s&&S.insertBefore(a,S.firstChild),e=t.offsetWidth,s?S.removeChild(a):a.removeChild(t),S.style.fontSize=n,r&&(a.style.fontSize=r),e=w=parseFloat(e)},O=function(e){var t="clientWidth",a=S[t],n="CSS1Compat"===E.compatMode&&a||E.body[t]||a,r={},s=C[C.length-1],i=(new Date).getTime();if(e&&y&&i-y<30)return x.clearTimeout(v),void(v=x.setTimeout(O,30));for(var o in y=i,T)if(T.hasOwnProperty(o)){var l=T[o],m=l.minw,h=l.maxw,d=null===m,u=null===h;m&&(m=parseFloat(m)*(-1<m.indexOf("em")?w||R():1)),h&&(h=parseFloat(h)*(-1<h.indexOf("em")?w||R():1)),l.hasquery&&(d&&u||!(d||m<=n)||!(u||n<=h))||(r[l.media]||(r[l.media]=[]),r[l.media].push($[l.rules]))}for(var c in z)z.hasOwnProperty(c)&&z[c]&&z[c].parentNode===b&&b.removeChild(z[c]);for(var p in z.length=0,r)if(r.hasOwnProperty(p)){var f=E.createElement("style"),g=r[p].join("\n");f.type="text/css",f.media=p,b.insertBefore(f,s.nextSibling),f.styleSheet?f.styleSheet.cssText=g:f.appendChild(E.createTextNode(g)),z.push(f)}},l=function(e,t,a){var n=e.replace(c.regex.comments,"").replace(c.regex.keyframes,"").match(c.regex.media),r=n&&n.length||0,s=function(e){return e.replace(c.regex.urls,"$1"+t+"$2$3")},i=!r&&a;(t=t.substring(0,t.lastIndexOf("/"))).length&&(t+="/"),i&&(r=1);for(var o=0;o<r;o++){var l,m,h,d;i?(l=a,$.push(s(e))):(l=n[o].match(c.regex.findStyles)&&RegExp.$1,$.push(RegExp.$2&&s(RegExp.$2))),d=(h=l.split(",")).length;for(var u=0;u<d;u++)m=h[u],p(m)||T.push({media:m.split("(")[0].match(c.regex.only)&&RegExp.$2||"all",rules:$.length-1,hasquery:-1<m.indexOf("("),minw:m.match(c.regex.minw)&&parseFloat(RegExp.$1)+(RegExp.$2||""),maxw:m.match(c.regex.maxw)&&parseFloat(RegExp.$1)+(RegExp.$2||"")})}O()},m=function(){if(s.length){var t=s.shift();e(t.href,function(e){l(e,t.href,t.media),i[t.href]=!0,x.setTimeout(function(){m()},0)})}},t=function(){for(var e=0;e<C.length;e++){var t=C[e],a=t.href,n=t.media,r=t.rel&&"stylesheet"===t.rel.toLowerCase();a&&r&&!i[a]&&(t.styleSheet&&t.styleSheet.rawCssText?(l(t.styleSheet.rawCssText,a,n),i[a]=!0):(/^([a-zA-Z:]*\/\/)/.test(a)||o)&&a.replace(RegExp.$1,"").split("/")[0]!==x.location.host||("//"===a.substring(0,2)&&(a=x.location.protocol+a),s.push({href:a,media:n})))}m()};t(),c.update=t,c.getEmValue=R,x.addEventListener?x.addEventListener("resize",a,!1):x.attachEvent&&x.attachEvent("onresize",a)}function a(){O(!0)}}(this);