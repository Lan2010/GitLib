﻿KindEditor.plugin("autoheight",function(i){var o,r=this;r.autoHeightMode&&(r.isCreated?e():r.afterCreate(e));function a(){var e=r.edit,t=e.doc.body;e.iframe.height(o),r.resize(null,Math.max((i.IE?t.scrollHeight:t.offsetHeight)+76,o))}function e(){var e,t;o=i.removeUnit(r.height),r.edit.afterChange(a),e=r.edit,t=e.doc.body,e.iframe[0].scroll="no",t.style.overflowY="hidden",a()}});