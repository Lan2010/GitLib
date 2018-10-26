﻿define(["jquery","Global","Menu","Validate","DTP"],function(u,n,i,e){"use strict";var a,p,t,s,f,r,o,l,d={},c=function(e){for(var a="",i={},t={},n=0,s=e.length;n<s;n++){var r=e[n],o=r.devParam.replace("@","");if(e[n].name=o,a+=['<div class="cell">','<div class="hd '+(1==r.required?"required":"")+'"><label>'+(r.viewName?r.viewName:r.devParam)+"</label></div>",'<div class="bd">','<div class="control">',""+v(r),"</div>",r.describe?'<span class="prompt">'+r.describe+"</span>":"","</div>","</div>"].join(""),i[o]={},t[o]={},r.regExp&&(i[o].regExp=r.regExp,t[o].regExp=r.regExpTip),r.rule){var d=JSON.parse(r.rule);for(var c in d)i[o][c]=d[c]}}l.html(a),p=f.validate({rules:i,messages:t})},v=function(e){var a="",i=e.name,t=e.devParam,n=e.value||"",s=e.rw,r=e.required,o=e.inputType,d=e.options?e.options:"",c=0;if(d&&u.isJson(d)&&(c=(d=u.parseJSON(d)).length),"r"===s){if(d&&("select"===o||"radio"===o))for(var l=0;l<c;l++)if(d[l].value==n){n=d[l].name;break}return'<span class="input-read">'+u.enCodeText(n)+"</span>"}if("rw"!=s)return"";if("text"===o)a='<input name="'+i+'" data-field="'+t+'" type="text" class="input" value="'+n+'" '+(1==r?"required":"")+">";else if("select"===o&&c){a+='<select name="'+i+'" data-field="'+t+'" class="input" '+(1==r?"required":"")+">",a+='<option value="">请选择</option>';for(l=0;l<c;l++)a+='<option value="'+d[l].value+'" '+(d[l].value==n?"selected":"")+">"+d[l].name+"</option>";a+="</select>"}else if("radio"===o&&c)for(l=0;l<c;l++)a+='<label class="radio-inline"><input name="'+i+'" data-field="'+t+'" type="radio" value="'+d[l].value+'" '+(d[l].value==n?"checked":"")+" "+(1==r?"required":"")+"><i></i><span>"+d[l].name+"</span></label>";else if("checkbox"===o&&c)for(l=0;l<c;l++)a+='<label class="checkbox-inline"><input name="'+i+'" data-field="'+t+'" type="checkbox" value="'+d[l].value+'" '+(d[l].value==n?"checked":"")+" "+(1==r?"required":"")+"><i></i><span>"+d[l].name+"</span></label>";else a='<input name="'+i+'" data-field="'+t+'" type="text" class="input" value="'+n+'">';return a},m=function(){u("#devBtnParam").removeClass("hide");var i="/api/device/setConfig";f.on("submit",function(){if(p.form()){n.$page.msg("loading",{text:"提交中"});var l,e=(l={dev_num:u("#devNo").val(),conf:[]},f.find("input,select,textarea").each(function(e){var a=u(this),i=a.attr("type"),t=a.attr("name"),n=a.data("field"),s=a.val(),r=(a.prop("checked"),!1);if(t)if("radio"===i||"checkbox"===i){var o=f.find('input[name="'+t+'"]');if(0===o.index(a)){var d=o.filter(":checked");if("radio"===i&&(s=d.length?d.val():""),"checkbox"===i){var c=[];d.each(function(){c.push(u(this).val())}),s=c.join(",")}r=!0}}else r=!0;r&&(s=s.replace(/\\/g,"\\\\").replace(/"/g,'\\"'),l.conf.push({o:n,v:s}))}),JSON.stringify(l));return u.ajax({url:n.path+i,type:"post",data:e,contentType:"application/json",success:function(e){n.checkJson(e)&&n.$page.msg("success",{text:"修改成功",time:2e3})},error:function(e,a){n.$page.msg("error",{text:i+" - "+e.status+" - "+e.statusText+" - "+a})}}),!1}})},g=function(e){!function(e){if(!(e.isOnlined||e.bindAccount||e.onlineStatus)){s.find("input").removeClass("input-read"),s.find(".eitable").removeAttr("readonly"),u("#devBtn").removeClass("hide"),u("#productTtime2").datetimepicker({format:"yyyy-mm-dd",endDate:u.date(new Date).ymd,minView:2}),a=s.validate();var i="/api/device/updateDevice";s.on("submit",function(){if(a.form())return n.$page.msg("loading",{text:"修改中"}),u.ajax({url:n.path+i,type:"post",data:s.serialize(),success:function(e){n.checkJson(e)&&n.$page.msg("success",{text:"修改成功",time:2e3})},error:function(e,a){n.$page.msg("error",{text:i+" - "+e.status+" - "+e.statusText+" - "+a})}}),!1})}}(e)};return{onSearch:function(e,a){},onLoad:function(e,a){i.active("device/list"),n.$page.addClass("page-gray"),a&&u.isInit(a.id)?(d=a,s=u("#formMod"),f=u("#formParam"),r=u("#formPass"),o=u("#infoBody"),l=u("#paramsBody"),function(t){if(d.id){o.loading();var i="/api/device/list";u.ajax({url:n.path+i,data:{id:d.id},cache:!1,success:function(e){if(n.checkJson(e)){var a=e.data[0],i=a.onlineStatus;s.fillForm(a,{fields:["id","number","mac","devPasswd",{bindAccount:a.bindAccount?a.bindAccount:"未绑定"},{bindTime:a.bindTime?u.date(a.bindTime).string:""},"model",{onlineStatus:(1==i?'<span class="font-green">在线</span>':0==i&&'<span class="font-gray">离线</span>')||'<span class="font-gray">--</span>'},{millisecond:a.millisecond?u.convertMS(a.millisecond):""},{belongUnitId:1==a.belongUnitId?"天智星":"未知"},{addTime:a.addTime?u.date(a.addTime).string:""},{productTtime:a.productTtime?u.date(a.productTtime).ymd:""},{isOnlined:a.isOnlined?'<span class="font-green">是</span>':'<span class="font-gray">否</span>'}]}),u("#devDurationLabel").text(a.onlineStatus?"在线时长":"离线时长"),t&&t(a),o.loading(!1)}},error:function(e,a){o.loading(!1),n.pageError(i+" - "+e.status+" - "+e.statusText+" - "+a)}})}}(function(e){g(e),function(e){if(e.number)if(e.onlineStatus){l.loading(),u("#devNo").val(e.number);var i="/api/device/getDeviceDetail";u.ajax({url:n.path+i,data:{dev_num:e.number},cache:!1,success:function(e){if(l.loading(!1),n.checkJson(e)){var a=e.conf;c(a),m(a)}},error:function(e,a){l.loading(!1),n.pageError(i+" - "+e.status+" - "+e.statusText+" - "+a)}})}else l.html('<div class="font-gray">只有在线设备才能获取设备参数</div>')}(e),function(e){if(e.id){u("#devNum").val(e.id),t=r.validate();var i="/api/device/setPasswd";r.on("submit",function(){if(t.form())return n.$page.msg("loading",{text:"修改中"}),u.ajax({url:n.path+i,type:"post",data:r.serialize(),success:function(e){n.checkJson(e)&&n.$page.msg("success",{text:"修改成功",time:2e3})},error:function(e,a){n.$page.msg("error",{text:i+" - "+e.status+" - "+e.statusText+" - "+a})}}),!1})}}(e)})):n.pageError("参数错误")}}});