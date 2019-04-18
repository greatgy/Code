﻿var g = {};
g.ismozilla = /firefox/.test(navigator.userAgent.toLowerCase());
$(function(){
	//setBaseCssForInput();
	initMenu();
	resetScrollHeight();
	setAnchorHref();
	//initWindowSize();
	//initAnimate();//初始化动画
	initState();//初始化状态，如事件等等
	$(window).bind("load", windowLoadHandler);
	//IEHandler(9);
	//MobileHandler();
});

function windowLoadHandler() {
	if($.browser.msie && $.browser.version >= 10) {
		window.setTimeout(initImageData, 100);
	} else {
		initImageData();
	}
	resizeWindow();
}

function initImageData() {
	$("img").not("img[title]").each(function(){
		$(this).attr("title",$(this).attr("alt"));
	});
	$("img").each(function(){
		if($(this).attr("title") != "") {
			$(this).attr("title",$(this).attr("alt"));
		}
		$(this).data("src", $(this).attr("src"));
	});
	$("img").each(function(){
		if (!isImageOk(this)) {
			var src404 = $(this).data("src404");
			$(this).data("src", $(this).attr("src"));
			if(typeof(src404) == "undefined") {
				$(this).hide();
			} else if(src404 == "") {
				$(this).addClass("hd");
			} else if(src404 == "disable") {
				//src404="disable"，不设置替换图像
			} else {
				$(this).attr("src", src404);
			}
			$(this).bind({load : function(){$(this).show();}});
		} else {
			$(this).show();
		}
	});
}

function IEHandler(v) {
	if($.browser.msie && $.browser.version <= v) {
		try {
			$("[maxlength]").bind("keyup blur", function(){
				if(/^[0-9]+$/.test($(this).attr("maxlength"))) {
					var len = parseInt($(this).attr("maxlength"), 10);
					if($(this).val().length > len) { 
						//alert('Maximum length exceeded: ' + len); 
						$(this).val($(this).val().substr(0, len)); 
						return false; 
					}
				}
			});
		}catch (e){}
	}
}

function MobileHandler() {
	if($.browser.mobile) {
		$(".goto").remove();
	}
}

function initMenu() {
	toggleMenu("#navigation a");
	toggleMenu("#nav a");
	toggleMenu("#aside-menu a");
}

function toggleMenu(id) {
	var path = window.location.href;
	if(path.indexOf("?") > 0) {
		path = path.substring(0, path.indexOf("?"));
	}
//	alert(path);
	var len = 0;
	var curr;
	var thiscurr;
	$(id).each(function(){
//		var charindex = $(this).attr("href").lastIndexOf("/") ;
//		var p = $(this).attr("href").slice(charindex + 1);
		var p = encodeURI($(this).attr("href"));
//		alert(path + "   |   " + p);
		//var regex = new RegExp("^" + p + ".*");
		if(path.toLowerCase() == p.toLowerCase()) {
			curr = this;
			thiscurr = this;
		} else if(path.indexOf(p) > -1) {
			if(p.length > len) {
				len = p.length;
				curr = this;
			}
		}
	})
	if(thiscurr == null) {
		thiscurr = curr;
	}
	$(thiscurr).addClass("here");
}

function initWindowSize() {
	$(window).bind("resize", resizeWindow);
	resizeWindow();
}

function resizeWindow(e){
	//开始设置网页底部的白底，不允许飘在网页中部
//	alert($(window).height());
//	alert($("body").height());
//	alert($(".footer").position().top);
//	alert($(".footer").height());
	$(".footer").attr("style", "");
//	if($("body").height() > $(".footer").position().top + $(".footer").height()) {
	if($(window).height() > $("body").height()) {
		$(".footer").attr("style", "position:absolute;bottom:0;left:0;");
	}
	//-------------结束
    var newwidth = $(window).width();
//    debug(newwidth);
    var flag = $.browser.msie ? ($.browser.version < 9) : false;//是否是IE678
    if(flag) {
//	    if(newwidth < 800){
//	    	
//	    }else if(newwidth < 600){
//
//	    }else if(newwidth < 480){
//	    	
//	    }else if(newwidth < 320){
//	        
//	    }
    } else {
    	//通过根据屏幕大小 用maxwidth控制使用哪个图像
    	$("img[data-imgsrc]").each(function(){
    		var list = $(this).data("imgsrc");
    		var minwidth = Number.MAX_VALUE;
    		var cursrc = "";
    		for (var i = 0; i < list.length; i++) {
    			var obj = list[i];
//    			debug(obj.maxwidth + " ");
    			if(obj.maxwidth >= newwidth && obj.maxwidth <= minwidth) {
    				cursrc = obj.src;
    				minwidth = obj.maxwidth;
    			}
    		}
//    		debug(cursrc);
    		if(cursrc == "") {//大于800px 取最大图 即html代码中原图
    			$(this).attr("src", $(this).data("src"));
    		} else if(cursrc != $(this).attr("src")) {
    			$(this).attr("src", cursrc);
    		}
    	});
    }
}

function initAnimate() {
	
}
function initState() {
	$(window).scroll(function() {
		var top = $(this).scrollTop();
		if (top < 10) {
			$("aside.gotop").hide();
		}
		if (top > 10) {
			$("aside.gotop").show();
		}
	});
	$("aside.gotop").click(function(){goScrollTo("body", "slow"); return false;});
	$(".goback").click(function(){window.history.go(-1);});
	//删除确认
	$(".delconfirm").click(function(e){return deleteConfirmHandler();});
}
