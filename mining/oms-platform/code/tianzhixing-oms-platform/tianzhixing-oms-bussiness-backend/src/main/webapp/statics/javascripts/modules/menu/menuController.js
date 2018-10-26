$(function(){
	ajaxUtil.ajax('menu/list', null, "GET", function (data) {
		if(data.code === 200){
			createMenus(data.data.list);
		}
	}, null, null, null, null, false);
	/**
	 * 创建节点
	 */
	function createNode(node, target, index){
		$ul = $('<ul class="nav nav-second-level collapse"></ul>');
		
    	var $li = $('<li id="' + node.id + '"></li>'), 
    		$a = $('<a class="J_menuItem data-index="'+ node.id +'" >' + node.name + '</a>');
    	
    	if(node.url){
    		$a = $('<a class="J_menuItem" href="'+ node.url +'" data-index="'+ node.id +'" data-href="'+ node.url +'">' + node.name + '</a>')
    	}
    	
    	$a.click(handleTab);
    	
    	$li.append($a);		    	
    	$ul.append($li);
    	$(target).append($ul);
	    
	}
	

	/**
	 * 创建父菜单
	 */
	function createMenus (menus, nodeLi) {
		if(!!nodeLi){
			$ul = $('<ul class="nav nav-second-level collapse"></ul>');
			
			for (var i = 0; i < menus.length; i++) {
				var $li = $('<li id="' + menus[i].id + '"></li>'), _href = menus[i].url ? '"href="' + menus[i].url + '"' : '';
				
		    	if(menus[i].url && menus[i].node){
		    		$a = $('<a class="J_menuItem" href="'+ menus[i].url +'" data-index="'+ menus[i].id +'" data-href="'+ menus[i].url +'">' + menus[i].name + '<span class="fa arrow"></span> </a>');
		    	}else if(menus[i].node){
		    		$a = $('<a class="J_menuItem" href="'+ menus[i].url +'" data-index="'+ menus[i].id +'" data-href="'+ menus[i].url +'">' + menus[i].name + '</a>');
		    	}else {
		    		$a = $('<a class="J_menuItem '+ _href  +  'data-index="'+ menus[i].id +'" >' + menus[i].name + '</a>');
		    	}
		    	
		    	$a.click(handleTab);
		    	$li.append($a);		    	
		    	$ul.append($li);
		    	$(nodeLi).append($ul);
		    	
		    	//处理下级菜单
		        var nodes = menus[i].node;
		        if (!!nodes) {
		            for (var j = 0; j < nodes.length; j++) {
		            	 createNode(nodes[j], $li, j);
		            }
		        }
		  }
		}else {
			for (var i = 0; i < menus.length; i++) {
		    	var $li = $('<li><a href="javascript:;"><i class="'+menus[i].pic+'"></i> <span class="nav-label">' + menus[i].name + '</span><span class="fa arrow"></span></a>'+
		        '</li>'), $ul = $('<ul class="nav nav-second-level collapse"></ul>');
		    	
		    	$li.append($ul);
		        $('#side-menu').append($li);

		        //处理下级菜单
		        var nodes = menus[i].node;
		        if (!!nodes) {
		            for (var j = 0; j < nodes.length; j++) {
		                createMenuNode(nodes[j], $ul, j);
		            }
		        }
		    }
		}
	}

	/**
	 * 创建子菜单
	 */
	function createMenuNode (node, ul, index) {
		var $li =  $('<li id="' + node.id + '"></li>'),  $a, $span = node.node ? '<span class="fa arrow"></span> ':'';
		if (!node && !ul) {
	        return;
	    }
	    
	    if(node.url && node.node){
	    	$a = $('<a class="J_menuItem" href="'+ node.url +'" data-index="'+ index +'" data-href="'+ node.url +'">' + node.name + '<span class="fa arrow"></span> </a>');
	    }else if(node.url){
	    	$a = $('<a class="J_menuItem" href="'+ node.url +'" data-index="'+ index +'" data-href="'+ node.url +'">' + node.name + '</a>');
	    }else {
	    	$a = $('<a class="J_menuItem data-index="'+ node.id +'" >' + node.name +  $span +'</a>');
	    }
	    $a.on("click", function(){
	    	// 此处暂支持两级，后续需要扩展支持N级 
	    	$("#side-menu > li > ul > li.active").removeClass("active");
	    	$(this).parent().addClass("active");
	    })
	    $a.click(handleTab);
	    $li.append($a);
	    ul.append($li);
	    
	    if(node.node){
	    	var nodeLi = $li;
	    	createMenus(node.node, nodeLi);
	    }
	}
});