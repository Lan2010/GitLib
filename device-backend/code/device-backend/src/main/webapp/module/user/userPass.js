﻿define(["jquery","Extend","Global","Validate","Modal"],function(a,e,o,t,n){"use strict";var r,u,d={};return{onLoad:function(e,t){d=t,u=a("#formModPass"),function(){a("#username").text(d.username),r=u.validate();var s="/api/user/changePasswd";u.on("submit",function(){if(r.form())return a("body").msg("loading",{text:"提交中",level:"top"}),a.ajax({url:o.path+s,type:"post",data:{user_name:d.username,password:a("#password").val()},success:function(e,t,s){o.checkJson(e,{level:"top"})&&(n.hide(),a("body").msg("success",{text:"修改成功",level:"top",time:2e3}))},error:function(e,t){a("body").msg("error",{text:s+" - "+e.status+" - "+e.statusText+" - "+t,level:"top"})}}),!1})}()}}});