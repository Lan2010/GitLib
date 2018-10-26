/*
 * Image preview script 
 * powered by jQuery (http://www.jquery.com)
 * 
 * written by Alen Grakalic (http://cssglobe.com)
 * 
 * for more info visit http://cssglobe.com/post/1695/easiest-tooltip-and-image-preview-using-jquery
 *
 */
 
this.imagePreview = function(){	
	/* CONFIG */
		
		
		
	/* END CONFIG */
	$("a.preview").click(function(e){
		this.t = this.title;
		this.title = "";	
		var c = (this.t != "") ? "<br/>" + this.t : "";
		$("#preview").append("<img src='"+ this.rel +"' alt='Image preview' />"+ c );								 
		$("#preview")
			.fadeIn("fast");						

	});	
	$("#preview").click(function(){
		$(this).fadeOut("fast");
		$(this).find("img").fadeOut("fast");
	});
	
};


// starting the script on page load
$(document).ready(function(){
	imagePreview();
});
