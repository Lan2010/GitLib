	/************************************************************************************************************
	(C) www.dhtmlgoodies.com, June 2006
	
	This is a script from www.dhtmlgoodies.com. You will find this and a lot of other scripts at our website.	
	
	Terms of use:
	You are free to use this script as long as the copyright message is kept intact. However, you may not
	redistribute, sell or repost it without our permission.
	
	Thank you!
	
	www.dhtmlgoodies.com
	Alf Magne Kalleland
	
	************************************************************************************************************/	

	
	
	
	var opacitySpeed = 2;	// Speed of opacity - switching between large images - Lower = faster
	var opacitySteps = 10; 	// Also speed of opacity - Higher = faster
	var slideSpeed = 5;	// Speed of thumbnail slide - Lower = faster
	var slideSteps = 8;	// Also speed of thumbnail slide - Higher = faster
	var columnsOfThumbnails = 8;	// Hardcoded number of thumbnail columns, use false if you want the script to figure it out dynamically.
	
	/* Don't change anything below here */
	var DHTMLgoodies_largeImage = false;
	var DHTMLgoodies_imageToShow = false;
	var DHTMLgoodies_currentOpacity = 100;
	var DHTMLgoodies_slideHeight = false;
	var DHTMLgoodies_thumbTotalHeight = false;
	var DHTMLgoodies_viewableHeight = false;
	
	var currentUnqiueOpacityId = false;
	var DHTMLgoodies_currentActiveImage = false;
	var DHTMLgoodies_currentActiveTitle = '';
	var DHTMLgoodies_thumbDiv = false;
	var DHTMLgoodies_thumbSlideInProgress = false;
	
	var browserIsOpera = navigator.userAgent.indexOf('Opera')>=0?true:false;
	var leftArrowObj;
	var rightArrowObj;
	var thumbsColIndex = 1;
	var thumbsTopPos = false;
	
	function initGalleryScript()
	{
		leftArrowObj = document.getElementById('DHTMLgoodies_leftArrow');		
		leftArrowObj.setAttribute('disabled',true);
		rightArrowObj = document.getElementById('DHTMLgoodies_rightArrow');	
		leftArrowObj.style.cursor = 'pointer';	
		rightArrowObj.style.cursor = 'pointer';	
		leftArrowObj.onclick = moveThumbnails;
		rightArrowObj.onclick = moveThumbnails;
		DHTMLgoodies_largeImage = document.getElementById('DHTMLgoodies_largeImage').getElementsByTagName('IMG')[0];
		var innerDiv = document.getElementById('DHTMLgoodies_thumbs_inner');
		DHTMLgoodies_slideHeight = innerDiv.getElementsByTagName('DIV')[0].offsetHeight;
		DHTMLgoodies_thumbDiv = document.getElementById('DHTMLgoodies_thumbs_inner');
		DHTMLgoodies_thumbDiv.style.top = '0px';
		
		var subDivs = DHTMLgoodies_thumbDiv.getElementsByTagName('DIV');
		DHTMLgoodies_thumbTotalHeight = 0;
		var tmpTop = 0;
		for(var no=0;no<subDivs.length;no++){
			if(subDivs[no].className=='strip_of_thumbnails'){
				DHTMLgoodies_thumbTotalHeight = DHTMLgoodies_thumbTotalHeight + DHTMLgoodies_slideHeight;
				subDivs[no].style.top = tmpTop + 'px';
				subDivs[no].style.left = '0px';
				tmpTop = tmpTop + subDivs[no].offsetHeight;
			}
		}

		DHTMLgoodies_viewableHeight = document.getElementById('DHTMLgoodies_thumbs').offsetHeight;
		
		DHTMLgoodies_currentActiveImage = DHTMLgoodies_thumbDiv.getElementsByTagName('A')[0].getElementsByTagName('IMG')[0];
		DHTMLgoodies_currentActiveImage.className='activeImage';
		DHTMLgoodies_currentActiveTitle = document.getElementById('DHTMLgoodies_largeImage').getElementsByTagName('A')[0];
	}
	
	function moveThumbnails()
	{
		if(DHTMLgoodies_thumbSlideInProgress)return;
		DHTMLgoodies_thumbSlideInProgress = true;
		if(this.id=='DHTMLgoodies_leftArrow'){
			thumbsColIndex--;
			rightArrowObj.removeAttribute("disabled");
			if(DHTMLgoodies_thumbDiv.style.top.replace('px','')/1>=0){
				leftArrowObj.setAttribute('disabled',true);
				DHTMLgoodies_thumbSlideInProgress = false;
				return;
			}
			
			slideThumbs(slideSteps,0);
			
		}else{
			thumbsColIndex++;
			leftArrowObj.removeAttribute("disabled");
			var top = DHTMLgoodies_thumbDiv.style.top.replace('px','')/1;	
			var showArrow = true;
			if(DHTMLgoodies_thumbTotalHeight + top - DHTMLgoodies_slideHeight <= DHTMLgoodies_viewableHeight)showArrow = false;
			if(columnsOfThumbnails)showArrow = true;
				
			if(!showArrow)	
			{
				rightArrowObj.setAttribute('disabled',true);
				DHTMLgoodies_thumbSlideInProgress = false;
				return;
			}	
			
			slideThumbs((slideSteps*-1),0);
		}	
		
	}
	
	function slideThumbs(speed,currentPos)
	{
		var topPos;
		if(thumbsTopPos){
			topPos= thumbsTopPos;
		}else{
			var topPos = DHTMLgoodies_thumbDiv.style.top.replace('px','')/1;
			thumbsTopPos = topPos;
		}
		currentPos = currentPos + Math.abs(speed);		
		var tmpTopPos = topPos;
		topPos = topPos + speed;
		thumbsTopPos = topPos;
		DHTMLgoodies_thumbDiv.style.top = topPos + 'px';
		if(currentPos<DHTMLgoodies_slideHeight)setTimeout('slideThumbs(' + speed + ',' + currentPos + ')',slideSpeed);else{
			if(tmpTopPos>=0 || (columnsOfThumbnails && thumbsColIndex==1)){
				document.getElementById('DHTMLgoodies_leftArrow').setAttribute('disabled',true);
			}	
			var top = tmpTopPos;		
			var showArrow = true;
			if(DHTMLgoodies_thumbTotalHeight + top - DHTMLgoodies_slideHeight <= DHTMLgoodies_viewableHeight)showArrow=false;
			if(columnsOfThumbnails){
				if((thumbsColIndex+1)<columnsOfThumbnails)showArrow=true; else showArrow = false;				
			}			
			if(!showArrow){
				document.getElementById('DHTMLgoodies_rightArrow').setAttribute('disabled',true);
			}					
			DHTMLgoodies_thumbSlideInProgress = false;
		}
	
	}
	
	function showPreview(imagePath,title,inputObj)
	{
		if(DHTMLgoodies_currentActiveImage){
			if(DHTMLgoodies_currentActiveImage==inputObj.getElementsByTagName('IMG')[0])return;
			DHTMLgoodies_currentActiveImage.className='';
		}
		DHTMLgoodies_currentActiveImage = inputObj.getElementsByTagName('IMG')[0];
		DHTMLgoodies_currentActiveImage.className='activeImage';
		DHTMLgoodies_currentActiveTitle.innerHTML = title;
		
		DHTMLgoodies_imageToShow = imagePath;
		var tmpImage = new Image();
		tmpImage.src = imagePath;
		currentUnqiueOpacityId = Math.random();
		moveOpacity(opacitySteps*-1,currentUnqiueOpacityId);
	}
	
	function setOpacity()
	{
		if(document.all)
		{
			DHTMLgoodies_largeImage.style.filter = 'alpha(opacity=' + DHTMLgoodies_currentOpacity + ')';
		}else{
			DHTMLgoodies_largeImage.style.opacity = DHTMLgoodies_currentOpacity/100;
		}		
	}
	function moveOpacity(speed,uniqueId)
	{
		
		if(browserIsOpera){
			DHTMLgoodies_largeImage.src = DHTMLgoodies_imageToShow;
			return;
		}
		
		DHTMLgoodies_currentOpacity = DHTMLgoodies_currentOpacity + speed;
		if(DHTMLgoodies_currentOpacity<=5 && speed<0){
		
			var tmpParent = DHTMLgoodies_largeImage.parentNode; 
			DHTMLgoodies_largeImage.parentNode.removeChild(DHTMLgoodies_largeImage);
			DHTMLgoodies_largeImage = document.createElement('IMG');
			tmpParent.appendChild(DHTMLgoodies_largeImage);
			setOpacity();
			DHTMLgoodies_largeImage.src = DHTMLgoodies_imageToShow;
		
			speed=opacitySteps;
		}
		if(DHTMLgoodies_currentOpacity>=99 && speed>0)DHTMLgoodies_currentOpacity=99;		
		setOpacity();	
		if(DHTMLgoodies_currentOpacity>=99 && speed>0)return;		
		if(uniqueId==currentUnqiueOpacityId)setTimeout('moveOpacity(' + speed + ',' + uniqueId + ')',opacitySpeed);		
	}