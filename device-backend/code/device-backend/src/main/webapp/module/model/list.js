﻿define(["jquery","Global","Menu","Route","TSearch","Grid","DTP","Modal"],function(c,i,r,d,s,t,e,u){"use strict";var l,f=!0,h=function(t){if(confirm("确定删除所选的数据吗？")){i.$page.msg("loading",{text:"删除中"});var n="/api/device/deleteDeviceType";c.ajax({url:i.path+n,data:{code:t},cache:!1,success:function(t){i.checkJson(t)&&(i.$page.msg("success",{text:"删除成功",time:2e3}),l.refresh())},error:function(t,e){i.$page.msg("error",{text:n+" - "+t.status+" - "+t.statusText+" - "+e})}})}};return{refresh:function(){l.refresh()},onSearch:function(t,e){f=!1,s.fillData(e),l.load(e||{})},onLoad:function(t,e){var n,a,o;r.active(t),n=e,a=c("#mainTable"),o="/api/device/getDeviceType",l=a.grid({url:i.path+o,data:n,columns:[{data:"orderNo",class:"txt-c",render:function(t,e,n,a){return a.pageSize*(a.page-1)+n}},{data:"name"},{data:"opt",class:"txt-c",render:function(t,e,n){return['<button type="button" class="btn default btn-del" '+(e.isOnlined||e.onlineStatus||e.bindAccount?"disabled":'data-id="'+e.code+'"')+" >删除</button>"].join("")}}],onSuccess:function(t,e){i.checkJson(e)&&(f&&d.updateHash(t),f=!0)},onError:function(t,e){c("body").msg("error",{text:o+" - "+t.status+" - "+t.statusText+" - "+e})}}),a.on("click",".btn-del",function(){var t=c(this).data("id");t&&h(t)}),c("#btnAdd").on("click",function(){u.show({width:600,title:"添加型号",path:"module/model/add"})}),s.fillData(e),s.init({onSearch:function(t){l.load(t||{})},onClear:function(t){l.load(t||{})}})}}});