$(document).ready(function(){
	$("#wxAnnouncementList a").each(function() {
		this.href="";
		this.target="";
	});
});
function getAnnouncementList(jsondata,pageIndex){
	if (!jsondata || jsondata==null) {
		$("#wxNo").show();
		$("#wxMore").hide();
		$("#wxLoad").hide();
		return;
	}
	var announcementContent = "";
	if (jsondata.length < 15) {
		$("#wxMore").hide();
		$("#wxLoad").hide();
		$("#wxNo").show();
	}else {
		$("#wxMore").show();
		$("#wxNo").hide();
		$("#wxLoad").hide();
	}
	for ( var i = 0; i < jsondata.length; i++) {
            announcementContent = announcementContent + ' <li class="container pt_10 bord_b" id="'+jsondata[i].articleID+'" >'+
                   ' <div class="title"><span class="color_d green pr_5"><!--color_d ●--></span><a  class="app-pop-page" href="/Mobile/about/article/num/'+jsondata[i].articleID+'" poptitle="详情" >'+jsondata[i].articleTitleFormat+'</a></div><p class="pl_20 color_9">'+jsondata[i].addDatetimeFormat+'</p></li>';
        
	}

        $("#pageIndex").val(pageIndex*1+1);
	$(".white_bg").append(announcementContent);
}
function getMoreAnnouncementList(pageIndex,type){
	var data={'p':pageIndex,'type':type};
	 $.ajax({
		 async:true,
		 url:"/Mobile/About/getMore",
		 type: 'post',
		 dataType:'json',
		 data:data,
		 success:function(json){
		 	if(json.status){
		 		getAnnouncementList(json.data.Rows,pageIndex);
		 	}else{
		 		$("#wxMore").hide();
				$("#wxLoad").hide();
				$("#wxNo").show();
		 	}
		 },
		 error:function(xhr){
              
		 }
	});
}















