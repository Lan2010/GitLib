﻿KindEditor.plugin("anchor",function(t){var l=this,c="anchor",o=l.lang(c+".");l.plugin.anchor={edit:function(){var e=['<div style="padding:20px;">','<div class="ke-dialog-row">','<label for="keName">'+o.name+"</label>",'<input class="ke-input-text" type="text" id="keName" name="name" value="" style="width:100px;" />',"</div>","</div>"].join(""),n=l.createDialog({name:c,width:300,title:l.lang(c),body:e,yesBtn:{name:l.lang("yes"),click:function(e){l.insertHtml('<a name="'+a.val()+'">').hideDialog().focus()}}}).div,a=t('input[name="name"]',n),i=l.plugin.getSelectedAnchor();i&&a.val(unescape(i.attr("data-ke-name"))),a[0].focus(),a[0].select()},delete:function(){l.plugin.getSelectedAnchor().remove()}},l.clickToolbar(c,l.plugin.anchor.edit)});