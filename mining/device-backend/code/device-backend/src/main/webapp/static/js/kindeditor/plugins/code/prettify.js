﻿var q=null;window.PR_SHOULD_USE_CONTINUATION=!0,function(){function b(e,t,n,r){t&&(n(e={a:t,d:e}),r.push.apply(r,e.e))}function a(l,m){var y,w={};!function(){for(var e=l.concat(m),t=[],n={},r=0,a=e.length;r<a;++r){var s=e[r],i=s[3];if(i)for(var o=i.length;0<=--o;)w[i.charAt(o)]=s;i=""+(s=s[1]),n.hasOwnProperty(i)||(t.push(s),n[i]=q)}t.push(/[\S\s]/),y=function(e){function l(e){var t=e.charCodeAt(0);if(92!==t)return t;var n=e.charAt(1);return(t=i[n])?t:"0"<=n&&n<="7"?parseInt(e.substring(1),8):"u"===n||"x"===n?parseInt(e.substring(2),16):e.charCodeAt(1)}function c(e){return e<32?(e<16?"\\x0":"\\x")+e.toString(16):("\\"!==(e=String.fromCharCode(e))&&"-"!==e&&"["!==e&&"]"!==e||(e="\\"+e),e)}function o(e){for(var t=e.substring(1,e.length-1).match(/\\u[\dA-Fa-f]{4}|\\x[\dA-Fa-f]{2}|\\[0-3][0-7]{0,2}|\\[0-7]{1,2}|\\[\S\s]|[^\\]/g),n=(e=[],[]),r="^"===t[0],a=r?1:0,s=t.length;a<s;++a){var i,o=t[a];if(/\\[bdsw]/i.test(o))e.push(o);else o=l(o),a+2<s&&"-"===t[a+1]?(i=l(t[a+2]),a+=2):i=o,n.push([o,i]),i<65||122<o||(i<65||90<o||n.push([32|Math.max(65,o),32|Math.min(i,90)]),i<97||122<o||n.push([-33&Math.max(97,o),-33&Math.min(i,122)]))}for(n.sort(function(e,t){return e[0]-t[0]||t[1]-e[1]}),t=[],o=[NaN,NaN],a=0;a<n.length;++a)(s=n[a])[0]<=o[1]+1?o[1]=Math.max(o[1],s[1]):t.push(o=s);for(n=["["],r&&n.push("^"),n.push.apply(n,e),a=0;a<t.length;++a)s=t[a],n.push(c(s[0])),s[1]>s[0]&&(s[1]+1>s[0]&&n.push("-"),n.push(c(s[1])));return n.push("]"),n.join("")}function t(e){for(var t=e.source.match(/\[(?:[^\\\]]|\\[\S\s])*]|\\u[\dA-Fa-f]{4}|\\x[\dA-Fa-f]{2}|\\\d+|\\[^\dux]|\(\?[!:=]|[()^]|[^()[\\^]+/g),n=t.length,r=[],a=0,s=0;a<n;++a){var i=t[a];"("===i?++s:"\\"===i.charAt(0)&&(i=+i.substring(1))&&i<=s&&(r[i]=-1)}for(a=1;a<r.length;++a)-1===r[a]&&(r[a]=++u);for(s=a=0;a<n;++a)"("===(i=t[a])?void 0===r[++s]&&(t[a]="(?:"):"\\"===i.charAt(0)&&(i=+i.substring(1))&&i<=s&&(t[a]="\\"+r[s]);for(s=a=0;a<n;++a)"^"===t[a]&&"^"!==t[a+1]&&(t[a]="");if(e.ignoreCase&&d)for(a=0;a<n;++a)e=(i=t[a]).charAt(0),2<=i.length&&"["===e?t[a]=o(i):"\\"!==e&&(t[a]=i.replace(/[A-Za-z]/g,function(e){return e=e.charCodeAt(0),"["+String.fromCharCode(-33&e,32|e)+"]"}));return t.join("")}for(var u=0,d=!1,n=!1,r=0,a=e.length;r<a;++r){var s=e[r];if(s.ignoreCase)n=!0;else if(/[a-z]/i.test(s.source.replace(/\\u[\da-f]{4}|\\x[\da-f]{2}|\\[^UXux]/gi,""))){n=!(d=!0);break}}var i={b:8,t:9,n:10,v:11,f:12,r:13},p=[];for(r=0,a=e.length;r<a;++r){if((s=e[r]).global||s.multiline)throw Error(""+s);p.push("(?:"+t(s)+")")}return RegExp(p.join("|"),n?"gi":"g")}(t)}();var v=m.length;return function e(t){for(var n=t.d,r=[n,"pln"],a=0,s=t.a.match(y)||[],i={},o=0,l=s.length;o<l;++o){var c,u=s[o],d=i[u],p=void 0;if("string"==typeof d)c=!1;else{var h=w[u.charAt(0)];if(h)p=u.match(h[1]),d=h[0];else{for(c=0;c<v;++c)if(h=m[c],p=u.match(h[1])){d=h[0];break}p||(d="pln")}!(c=5<=d.length&&"lang-"===d.substring(0,5))||p&&"string"==typeof p[1]||(c=!1,d="src"),c||(i[u]=d)}if(h=a,a+=u.length,c){c=p[1];var f=u.indexOf(c),g=f+c.length;p[2]&&(f=(g=u.length-p[2].length)-c.length),d=d.substring(5),b(n+h,u.substring(0,f),e,r),b(n+h+f,c,N(d,c),r),b(n+h+g,u.substring(g),e,r)}else r.push(n+h,d)}t.e=r}}function e(e){var t=[],n=[];e.tripleQuotedStrings?t.push(["str",/^(?:'''(?:[^'\\]|\\[\S\s]|''?(?=[^']))*(?:'''|$)|"""(?:[^"\\]|\\[\S\s]|""?(?=[^"]))*(?:"""|$)|'(?:[^'\\]|\\[\S\s])*(?:'|$)|"(?:[^"\\]|\\[\S\s])*(?:"|$))/,q,"'\""]):e.multiLineStrings?t.push(["str",/^(?:'(?:[^'\\]|\\[\S\s])*(?:'|$)|"(?:[^"\\]|\\[\S\s])*(?:"|$)|`(?:[^\\`]|\\[\S\s])*(?:`|$))/,q,"'\"`"]):t.push(["str",/^(?:'(?:[^\n\r'\\]|\\.)*(?:'|$)|"(?:[^\n\r"\\]|\\.)*(?:"|$))/,q,"\"'"]),e.verbatimStrings&&n.push(["str",/^@"(?:[^"]|"")*(?:"|$)/,q]);var r=e.hashComments;return r&&(e.cStyleComments?(1<r?t.push(["com",/^#(?:##(?:[^#]|#(?!##))*(?:###|$)|.*)/,q,"#"]):t.push(["com",/^#(?:(?:define|elif|else|endif|error|ifdef|include|ifndef|line|pragma|undef|warning)\b|[^\n\r]*)/,q,"#"]),n.push(["str",/^<(?:(?:(?:\.\.\/)*|\/?)(?:[\w-]+(?:\/[\w-]+)+)?[\w-]+\.h|[a-z]\w*)>/,q])):t.push(["com",/^#[^\n\r]*/,q,"#"])),e.cStyleComments&&(n.push(["com",/^\/\/[^\n\r]*/,q]),n.push(["com",/^\/\*[\S\s]*?(?:\*\/|$)/,q])),e.regexLiterals&&n.push(["lang-regex",/^(?:^^\.?|[!+-]|!=|!==|#|%|%=|&|&&|&&=|&=|\(|\*|\*=|\+=|,|-=|->|\/|\/=|:|::|;|<|<<|<<=|<=|=|==|===|>|>=|>>|>>=|>>>|>>>=|[?@[^]|\^=|\^\^|\^\^=|{|\||\|=|\|\||\|\|=|~|break|case|continue|delete|do|else|finally|instanceof|return|throw|try|typeof)\s*(\/(?=[^*/])(?:[^/[\\]|\\[\S\s]|\[(?:[^\\\]]|\\[\S\s])*(?:]|$))+\/)/]),(r=e.types)&&n.push(["typ",r]),(e=(""+e.keywords).replace(/^ | $/g,"")).length&&n.push(["kwd",RegExp("^(?:"+e.replace(/[\s,]+/g,"|")+")\\b"),q]),t.push(["pln",/^\s+/,q," \r\n\t "]),n.push(["lit",/^@[$_a-z][\w$@]*/i,q],["typ",/^(?:[@_]?[A-Z]+[a-z][\w$@]*|\w+_t\b)/,q],["pln",/^[$_a-z][\w$@]*/i,q],["lit",/^(?:0x[\da-f]+|(?:\d(?:_\d+)*\d*(?:\.\d*)?|\.\d\+)(?:e[+-]?\d+)?)[a-z]*/i,q,"0123456789"],["pln",/^\\[\S\s]?/,q],["pun",/^.[^\s\w"-$'./@\\`]*/,q]),a(t,n)}function f(e,t){function a(e){switch(e.nodeType){case 1:if(i.test(e.className))break;if("BR"===e.nodeName)s(e),e.parentNode&&e.parentNode.removeChild(e);else for(e=e.firstChild;e;e=e.nextSibling)a(e);break;case 3:case 4:if(c){var t=e.nodeValue,n=t.match(o);if(n){var r=t.substring(0,n.index);e.nodeValue=r,(t=t.substring(n.index+n[0].length))&&e.parentNode.insertBefore(l.createTextNode(t),e.nextSibling),s(e),r||e.parentNode.removeChild(e)}}}}function s(e){for(;!e.nextSibling;)if(!(e=e.parentNode))return;var t;for(e=function e(t,n){var r=n?t.cloneNode(!1):t;if(a=t.parentNode){var a=e(a,1),s=t.nextSibling;a.appendChild(r);for(var i=s;i;i=s)s=i.nextSibling,a.appendChild(i)}return r}(e.nextSibling,0);(t=e.parentNode)&&1===t.nodeType;)e=t;r.push(e)}var n,i=/(?:^|\s)nocode(?:\s|$)/,o=/\r\n?|\n/,l=e.ownerDocument;e.currentStyle?n=e.currentStyle.whiteSpace:window.getComputedStyle&&(n=l.defaultView.getComputedStyle(e,q).getPropertyValue("white-space"));var c=n&&"pre"===n.substring(0,3);for(n=l.createElement("LI");e.firstChild;)n.appendChild(e.firstChild);for(var r=[n],u=0;u<r.length;++u)a(r[u]);t===(0|t)&&r[0].setAttribute("value",t);var d=l.createElement("OL");d.className="linenums";for(var p=Math.max(0,t-1|0)||0,h=(u=0,r.length);u<h;++u)(n=r[u]).className="L"+(u+p)%10,n.firstChild||n.appendChild(l.createTextNode(" ")),d.appendChild(n);e.appendChild(d)}function t(e,t){for(var n=t.length;0<=--n;){var r=t[n];p.hasOwnProperty(r)?window.console&&console.warn("cannot override language handler %s",r):p[r]=e}}function N(e,t){return e&&p.hasOwnProperty(e)||(e=/^\s*</.test(t)?"default-markup":"default-code"),p[e]}function g(e){var t=e.g;try{var n=(l=function(e){var t,r=/(?:^|\s)nocode(?:\s|$)/,a=[],s=0,i=[],o=0;e.currentStyle?t=e.currentStyle.whiteSpace:window.getComputedStyle&&(t=document.defaultView.getComputedStyle(e,q).getPropertyValue("white-space"));var l=t&&"pre"===t.substring(0,3);return function e(t){switch(t.nodeType){case 1:if(r.test(t.className))break;for(var n=t.firstChild;n;n=n.nextSibling)e(n);"BR"!==(n=t.nodeName)&&"LI"!==n||(a[o]="\n",i[o<<1]=s++,i[o++<<1|1]=t);break;case 3:case 4:(n=t.nodeValue).length&&(n=l?n.replace(/\r\n?/g,"\n"):n.replace(/[\t\n\r ]+/g," "),a[o]=n,i[o<<1]=s,s+=n.length,i[o++<<1|1]=t)}}(e),{a:a.join("").replace(/\n$/,""),c:i}}(e.h)).a;e.a=n,e.c=l.c,e.d=0,N(t,n)(e);var r,a,s=/\bMSIE\b/.test(navigator.userAgent),i=(t=/\n/g,e.a),o=i.length,l=0,c=e.c,u=c.length,d=(n=0,e.e),p=d.length;e=0;for(d[p]=o,a=r=0;a<p;)d[a]!==d[a+2]?(d[r++]=d[a++],d[r++]=d[a++]):a+=2;for(p=r,a=r=0;a<p;){for(var h=d[a],f=d[a+1],g=a+2;g+2<=p&&d[g+1]===f;)g+=2;d[r++]=h,d[r++]=f,a=g}for(d.length=r;n<u;){var m,y=c[n+2]||o,w=d[e+2]||o,v=(g=Math.min(y,w),c[n+1]);if(1!==v.nodeType&&(m=i.substring(l,g))){s&&(m=m.replace(t,"\r")),v.nodeValue=m;var b=v.ownerDocument,S=b.createElement("SPAN");S.className=d[e+1];var x=v.parentNode;x.replaceChild(S,v),S.appendChild(v),l<y&&(c[n+1]=v=b.createTextNode(i.substring(g,y)),x.insertBefore(v,S.nextSibling))}y<=(l=g)&&(n+=2),w<=l&&(e+=2)}}catch(e){"console"in window&&console.log(e&&e.stack?e.stack:e)}}var n,r,s=[n=[[r=["break,continue,do,else,for,if,return,while"],"auto,case,char,const,default,double,enum,extern,float,goto,int,long,register,short,signed,sizeof,static,struct,switch,typedef,union,unsigned,void,volatile"],"catch,class,delete,false,import,new,operator,private,protected,public,this,throw,true,try,typeof"],"alignof,align_union,asm,axiom,bool,concept,concept_map,const_cast,constexpr,decltype,dynamic_cast,explicit,export,friend,inline,late_check,mutable,namespace,nullptr,reinterpret_cast,static_assert,static_cast,template,typeid,typename,using,virtual,where"],i=[n,"abstract,boolean,byte,extends,final,finally,implements,import,instanceof,null,native,package,strictfp,super,synchronized,throws,transient"],o=[i,"as,base,by,checked,decimal,delegate,descending,dynamic,event,fixed,foreach,from,group,implicit,in,interface,internal,into,is,lock,object,out,override,orderby,params,partial,readonly,ref,sbyte,sealed,stackalloc,string,select,uint,ulong,unchecked,unsafe,ushort,var"],l=[r,"and,as,assert,class,def,del,elif,except,exec,finally,from,global,import,in,is,lambda,nonlocal,not,or,pass,print,raise,try,with,yield,False,True,None"],c=[r,"alias,and,begin,case,class,def,defined,elsif,end,ensure,false,in,module,next,nil,not,or,redo,rescue,retry,self,super,then,true,undef,unless,until,when,yield,BEGIN,END"],u=/^(DIR|FILE|vector|(de|priority_)?queue|list|stack|(const_)?iterator|(multi)?(set|map)|bitset|u?(int|float)\d*)/,m=/\S/,d=e({keywords:[s,o,n=[n,"debugger,eval,export,function,get,null,set,undefined,var,with,Infinity,NaN"],"caller,delete,die,do,dump,elsif,eval,exit,foreach,for,goto,if,import,last,local,my,next,no,our,print,package,redo,require,sub,undef,unless,until,use,wantarray,while,BEGIN,END"+l,c,r=[r,"case,done,elif,esac,eval,fi,function,in,local,set,then,until"]],hashComments:!0,cStyleComments:!0,multiLineStrings:!0,regexLiterals:!0}),p={};t(d,["default-code"]),t(a([],[["pln",/^[^<?]+/],["dec",/^<!\w[^>]*(?:>|$)/],["com",/^<\!--[\S\s]*?(?:--\>|$)/],["lang-",/^<\?([\S\s]+?)(?:\?>|$)/],["lang-",/^<%([\S\s]+?)(?:%>|$)/],["pun",/^(?:<[%?]|[%?]>)/],["lang-",/^<xmp\b[^>]*>([\S\s]+?)<\/xmp\b[^>]*>/i],["lang-js",/^<script\b[^>]*>([\S\s]*?)(<\/script\b[^>]*>)/i],["lang-css",/^<style\b[^>]*>([\S\s]*?)(<\/style\b[^>]*>)/i],["lang-in.tag",/^(<\/?[a-z][^<>]*>)/i]]),["default-markup","htm","html","mxml","xhtml","xml","xsl"]),t(a([["pln",/^\s+/,q," \t\r\n"],["atv",/^(?:"[^"]*"?|'[^']*'?)/,q,"\"'"]],[["tag",/^^<\/?[a-z](?:[\w-.:]*\w)?|\/?>$/i],["atn",/^(?!style[\s=]|on)[a-z](?:[\w:-]*\w)?/i],["lang-uq.val",/^=\s*([^\s"'>]*(?:[^\s"'/>]|\/(?=\s)))/],["pun",/^[/<->]+/],["lang-js",/^on\w+\s*=\s*"([^"]+)"/i],["lang-js",/^on\w+\s*=\s*'([^']+)'/i],["lang-js",/^on\w+\s*=\s*([^\s"'>]+)/i],["lang-css",/^style\s*=\s*"([^"]+)"/i],["lang-css",/^style\s*=\s*'([^']+)'/i],["lang-css",/^style\s*=\s*([^\s"'>]+)/i]]),["in.tag"]),t(a([],[["atv",/^[\S\s]+/]]),["uq.val"]),t(e({keywords:s,hashComments:!0,cStyleComments:!0,types:u}),["c","cc","cpp","cxx","cyc","m"]),t(e({keywords:"null,true,false"}),["json"]),t(e({keywords:o,hashComments:!0,cStyleComments:!0,verbatimStrings:!0,types:u}),["cs"]),t(e({keywords:i,cStyleComments:!0}),["java"]),t(e({keywords:r,hashComments:!0,multiLineStrings:!0}),["bsh","csh","sh"]),t(e({keywords:l,hashComments:!0,multiLineStrings:!0,tripleQuotedStrings:!0}),["cv","py"]),t(e({keywords:"caller,delete,die,do,dump,elsif,eval,exit,foreach,for,goto,if,import,last,local,my,next,no,our,print,package,redo,require,sub,undef,unless,until,use,wantarray,while,BEGIN,END",hashComments:!0,multiLineStrings:!0,regexLiterals:!0}),["perl","pl","pm"]),t(e({keywords:c,hashComments:!0,multiLineStrings:!0,regexLiterals:!0}),["rb"]),t(e({keywords:n,cStyleComments:!0,regexLiterals:!0}),["js"]),t(e({keywords:"all,and,by,catch,class,else,extends,false,finally,for,if,in,is,isnt,loop,new,no,not,null,of,off,on,or,return,super,then,true,try,unless,until,when,while,yes",hashComments:3,cStyleComments:!0,multilineStrings:!0,tripleQuotedStrings:!0,regexLiterals:!0}),["coffee"]),t(a([],[["str",/^[\S\s]+/]]),["regex"]),window.prettyPrintOne=function(e,t,n){var r=document.createElement("PRE");return r.innerHTML=e,n&&f(r,n),g({g:t,i:n,h:r}),r.innerHTML},window.prettyPrint=function(c){for(var e=[document.getElementsByTagName("pre"),document.getElementsByTagName("code"),document.getElementsByTagName("xmp")],u=[],t=0;t<e.length;++t)for(var n=0,r=e[t].length;n<r;++n)u.push(e[t][n]);e=q;var d=Date;d.now||(d={now:function(){return+new Date}});var p=0,h=/\blang(?:uage)?-([\w.]+)(?!\S)/;!function e(){for(var t=window.PR_SHOULD_USE_CONTINUATION?d.now()+250:1/0;p<u.length&&d.now()<t;p++){var n=u[p];if(0<=(r=n.className).indexOf("prettyprint")){var r,a,s;if(s=!(r=r.match(h))){for(var i=void 0,o=(s=n).firstChild;o;o=o.nextSibling){var l=o.nodeType;i=1===l?i?s:o:3===l&&m.test(o.nodeValue)?s:i}s=(a=i===s?void 0:i)&&"CODE"===a.tagName}for(s&&(r=a.className.match(h)),r&&(r=r[1]),s=!1,i=n.parentNode;i;i=i.parentNode)if(("pre"===i.tagName||"code"===i.tagName||"xmp"===i.tagName)&&i.className&&0<=i.className.indexOf("prettyprint")){s=!0;break}s||((s=!!(s=n.className.match(/\blinenums\b(?::(\d+))?/))&&(!s[1]||!s[1].length||+s[1]))&&f(n,s),g({g:r,h:n,i:s}))}}p<u.length?setTimeout(e,250):c&&c()}()},window.PR={createSimpleLexer:a,registerLangHandler:t,sourceDecorator:e,PR_ATTRIB_NAME:"atn",PR_ATTRIB_VALUE:"atv",PR_COMMENT:"com",PR_DECLARATION:"dec",PR_KEYWORD:"kwd",PR_LITERAL:"lit",PR_NOCODE:"nocode",PR_PLAIN:"pln",PR_PUNCTUATION:"pun",PR_SOURCE:"src",PR_STRING:"str",PR_TAG:"tag",PR_TYPE:"typ"}}();