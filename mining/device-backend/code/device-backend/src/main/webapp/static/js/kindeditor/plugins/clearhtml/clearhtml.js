﻿KindEditor.plugin("clearhtml",function(l){var i=this;i.clickToolbar("clearhtml",function(){i.focus();var t=i.html();t=(t=t.replace(/(<script[^>]*>)([\s\S]*?)(<\/script>)/gi,"")).replace(/(<style[^>]*>)([\s\S]*?)(<\/style>)/gi,""),t=l.formatHtml(t,{a:["href","target"],embed:["src","width","height","type","loop","autostart","quality",".width",".height","align","allowscriptaccess"],img:["src","width","height","border","alt","title",".width",".height"],table:["border"],"td,th":["rowspan","colspan"],"div,hr,br,tbody,tr,p,ol,ul,li,blockquote,h1,h2,h3,h4,h5,h6":[]}),i.html(t),i.cmd.selection(!0),i.addBookmark()})});