﻿define(["jquery","Extend","Global","Validate"],function(i,e,r,t){"use strict";var s,n,a,d=require("module/model/list");return{onLoad:function(e,t){a=e,t,n=i("#formAddModel"),function(){s=n.validate();var o="/api/device/addDeviceType";n.on("submit",function(){if(s.form())return i("body").msg("loading",{text:"提交中",level:"top"}),i.ajax({url:r.path+o,type:"post",data:n.serialize(),success:function(e,t,o){r.checkJson(e,{level:"top"})&&(a.hide(),i("body").msg("success",{text:"添加成功",level:"top",time:2e3}),d.refresh())},error:function(e,t){i("body").msg("error",{text:o+" - "+e.status+" - "+e.statusText+" - "+t,level:"top"})}}),!1})}()}}});