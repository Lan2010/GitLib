
//动态加载模块
var contentWayPoint = function () {
    var i = 0;
    $('.animate-box').waypoint(function (direction) {
        if (direction === 'down' && !$(this.element).hasClass('animated')) {
            i++;
            $(this.element).addClass('item-animate');
            setTimeout(function () {
                $('body .animate-box.item-animate').each(function (k) {
                    var el = $(this);
                    setTimeout(function () {
                        el.addClass('fadeInUp animated');
                        el.removeClass('item-animate');
                    }, k * 100, 'easeInOutExpo');
                });
            }, 100);
        }
    }, {offset: '85%'});
};
$.fn.countTo = function (a) {
    a = a || {};
    return $(this).each(function () {
        var c = $.extend({},
                $.fn.countTo.defaults, {
                    from: $(this).data("from"),
                    to: $(this).data("to"),
                    speed: $(this).data("speed"),
                    refreshInterval: $(this).data("refresh-interval"),
                },
                a);
        var h = Math.ceil(c.speed / c.refreshInterval),
                i = (c.to - c.from) / h;
        var j = this,
                f = $(this),
                e = 0,
                g = c.from,
                d = f.data("countTo") || {};
        f.data("countTo", d);
        if (d.interval) {
            clearInterval(d.interval)
        }
        d.interval = setInterval(k, c.refreshInterval);
        b(g);
        function k() {
            g += i;
            e++;
            b(g);
            if (typeof (c.onUpdate) == "function") {
                c.onUpdate.call(j, g)
            }
            if (e >= h) {
                f.removeData("countTo");
                clearInterval(d.interval);
                g = c.to;
                if (typeof (c.onComplete) == "function") {
                    c.onComplete.call(j, g)
                }
            }
        }
        function b(m) {
            var l = c.formatter.call(j, m, c);
            f.html(l)
        }
    })
};
$.fn.countTo.defaults = {
    from: 0,
    to: 0,
    speed: 1000,
    refreshInterval: 100,
    formatter: function (b) {
        return b.toFixed(0);
    },
    onUpdate: null,
    onComplete: null
};

$(function () {
    $("#count-number").data("countToOptions", function (b) {
        return b.toFixed(0).replace(/\B(?=(?:\d{3})+(?!\d))/g, ",")
    });
    $(".timer").each(function (a) {
        var b = $(this);
        a = $.extend({},
                a || {},
                b.data("countToOptions") || {});
        b.countTo(a)
    });
    contentWayPoint();
    
});

$(function(){
	var i=0,//大图编号
		len=img.length,//img数组的长度
		cur=0;//当前图片编号
		j=3,//默认显示小图个数
		page=0,//小图的页码
		$s_next=$('#smallImg-next'),//小图下一页
		$s_pre=$('#smallImg-pre'),//小图上一页
		box=$('#smallImg-box').width(),//显示的长度
		$ul=$('#smallImg-ul'),//小图外层
		$imgLi=$ul.find('li'),//小图li
		html=_html='';//存放载入的代码		
	$('#detailImg-box').append('<a href=\"'+img[0].href+'\" class=\"detailImg_1\"><img alt=\"'+img[0].alt+'\" src=\"'+img[i].src+'\"></a>');
	//大图	
	$('#detailImg-next').click(function(){
		++i;
		detailImg_click($s_next,i,len);
	});
	$('#detailImg-pre').click(function(){
		--i;
		detailImg_click($s_pre,i,len);
	});
	//小图
	for(var k=0;k<j;k++){
		var _k=k%len;
		s_html(_k);
		html+=h;
	}
	$ul.append(html);
	$('.smallImg_1').addClass('cur');	
	//小图下一页
	$('#smallImg-next').click(function(){
		if(!$ul.is(':animated')){
			page++;
			var a=page*j,_a,c;
			for(var k=0;k<j;k++,a++){
				smallImg_click(a,_a,len,i);
				_html+=h;
			}
			$ul.append(_html);
			$ul.css({'left':0,'right':'auto'});
			$ul.animate({left:-box},1600,function(){
				$ul.find('li:lt('+j+')').detach();
				$ul.css('left',0);
				_html='';
			});//动画执行后,再删除前9个li,将left设回0
			$('#smallImg-ul li').click(function(){//三处一样，不知道这个要怎么优化？？？
				var _this=$(this);
				i=_this.attr('class').replace(/[^0-9]/ig,'')-1;
				img_info(i);
				s_a_r(_this,'cur');
				cur=i;
			})
		}
	});
	//小图上一页
	$('#smallImg-pre').click(function(){
		if(!$ul.is(':animated')){
			page--;
			var a=(page-1)*j,_a,c;
			for(var k=0;k<j;k++,a--){
				smallImg_click(a,_a,len,i);
				_html=h+_html;
			}
			$ul.prepend(_html).css({'right':box,'left':'auto'});
			$ul.animate({right:0},1600,function(){
				$ul.find('li:gt('+(j-1)+')').detach();//删除后9个li,从8开始
				_html='';
			});
			$('#smallImg-ul li').click(function(){
				var _this=$(this);
				i=_this.attr('class').replace(/[^0-9]/ig,'')-1;
				img_info(i);
				s_a_r(_this,'cur');
				cur=i;
			})
		}
			
	});
	//点击小图
	$('#smallImg-ul li').click(function(){
		var _this=$(this);
		i=_this.attr('class').replace(/[^0-9]/ig,'')-1;
		img_info(i);
		s_a_r(_this,'cur ');
		cur=i;
	});
});

/*----自定义函数-----------*/
var img=[
	{
		'href':'',
		'alt':'图片1',
		'src':'/Public/frontend/images/about/about_13.jpg',
		'smallSrc':'/Public/frontend/images/about/about_13.jpg',
		'title':'标题111'
	},{
		'href':'www.baidu1.com',
		'alt':'图片2',
		'src':'/Public/frontend/images/about/about_12.jpg',
		'smallSrc':'/Public/frontend/images/about/about_12.jpg',
		'title':'标题222'
	},{
		'href':'',
		'alt':'图片3',
		'src':'/Public/frontend/images/about/about_11.jpg',
		'smallSrc':'/Public/frontend/images/about/about_11.jpg',
		'title':'标题333'
	},{
		'href':'',
		'alt':'图片4',
		'src':'/Public/frontend/images/about/about_05.jpg',
		'smallSrc':'/Public/frontend/images/about/about_05.jpg',
		'title':'标题444'
	},{
		'href':'',
		'alt':'图片5',
		'src':'/Public/frontend/images/about/about_08.jpg',
		'smallSrc':'/Public/frontend/images/about/about_08.jpg',
		'title':'标题555'
	},{
		'href':'',
		'alt':'图片6',
		'src':'/Public/frontend/images/about/about_13.jpg',
		'smallSrc':'/Public/frontend/images/about/about_13.jpg',
		'title':'标题666'
	},{
		'href':'',
		'alt':'图片7',
		'src':'/Public/frontend/images/about/about_12.jpg',
		'smallSrc':'/Public/frontend/images/about/about_12.jpg',
		'title':'标题777'
	},{
		'href':'',
		'alt':'图片8',
		'src':'/Public/frontend/images/about/about_11.jpg',
		'smallSrc':'/Public/frontend/images/about/about_11.jpg',
		'title':'标题888'
	},{
		'href':'',
		'alt':'图片9',
		'src':'/Public/frontend/images/about/about_08.jpg',
		'smallSrc':'/Public/frontend/images/about/about_08.jpg',
		'title':'标题999'
	}
]
//大图图片信息
function img_info(i){
	var href=img[i].href,
		alt=img[i].alt,
		src=img[i].src,
		title=img[i].title,
		$main=$('#detailImg-box');
	$main.find('a').attr({'class':'detailImg_'+(i+1)});
	$main.find('img').attr({'alt':alt,'src':src});
	$main.find('p').text(title);
}
function s_a_r(o,c){
	o.addClass(c).siblings().removeClass(c);	
}
//大图左右点击
function i_cur(i,len){
	i=i%len;
	if(i<0){
		i=len+i;
	}
	return i;	
}
function detailImg_click($pn,i,len){
	i_cur(i,len);
	img_info(i);
	var imgCur=$('.smallImg_'+(i+1));
	if(!imgCur.html()){
		$pn.click();
	} 
	s_a_r($('.smallImg_'+(i+1)),'cur');//小图选中
}
//小图左右点击
function smallImg_click(a,_a,len,i){
	_a=a;
	_a=a%len;
	if(_a<0){
		_a+=len;
	}
	c=_a==i?'cur':'';
	s_html(_a,c);
}
function s_html(_a,c){
	return h='<li class=\"smallImg_'+(_a+1)+' '+c+'\"><a data-toggle="modal" data-target="#myModal"><img alt=\"'+img[_a].alt+'\" src=\"'+img[_a].smallSrc+'\"></a></li>';
}
