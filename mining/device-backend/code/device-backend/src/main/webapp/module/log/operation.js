﻿define(["jquery","Global","Menu","Route","TSearch","Grid"],function(o,i,u,c,d,a){"use strict";var s,l=!0;return{onSearch:function(a,e){l=!1,d.fillData(e),s.load(e||{})},onLoad:function(a,e){var n,t,r;u.active(a),n=e,t=o("#mainTable"),r="/api/device/deviceLog",s=t.grid({url:i.path+r,data:n,columns:[{data:"orderNo",class:"td-sn",render:function(a,e,n,t){return t.pageSize*(t.page-1)+n}},{data:"operation"},{data:"operTime",render:function(a,e,n){return o.date(a).string}},{data:"user"}],onSuccess:function(a,e){i.checkJson(e)&&(l&&c.updateHash(a),l=!0)},onError:function(a,e){o("body").msg("error",{text:r+" - "+a.status+" - "+a.statusText+" - "+e})}}),d.fillData(e),d.init({onSearch:function(a){s.load(a||{})},onClear:function(a){s.load(a||{})}})}}});