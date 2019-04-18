;(function ($) {

	$.fn.masonry = function(options) {
		var defaults = {
			container : {},
			containerheight : 0,
			tiles : {},
			nextpoints : new Array(),
			maxwidth:0,
			initleft:0,
			minleft: 0,
			maxright: 0,
			maxtop: 0,
			maxtop: 0,
			margin: 6,
			margintop: 6,
			mozillamargintop: 10,
			index: 0
		};
		var vars = $.extend({}, defaults, options);
		vars.container = $(this);
		vars.maxwidth = $(this).outerWidth();
		if($.browser.mozilla) {
			vars.margintop = vars.mozillamargintop;
		}
		vars.tiles.each(function(i) {//初始化最顶层的放置点
		 	var currleft = vars.maxright + vars.margin;
		 	var currtop = 0;
//		 	alert(currleft);
//		 	alert($(this).outerWidth());
//		 	alert(vars.maxwidth);
		 	if((currleft + $(this).outerWidth()) <= vars.maxwidth) {
		 		vars.nextpoints[i] = {"left": currleft, "top": currtop};
		 		vars.maxright = currleft + $(this).outerWidth(); 
		 		return true;
		 	} else {
//		 		alert(Math.floor(vars.maxwidth / $(this).outerWidth()));
		 		if(Math.floor(vars.maxwidth / $(this).outerWidth()) == 1) {
		 			vars.nextpoints[i] = {"left": 0, "top": currtop};
		 		}
		 		return false;
		 	}
		 });
		vars.tiles.each(function() {
			var p = vars.nextpoints[0];
			if(typeof(p) == "undefined") {
				return;
			}
			for (var i = 1; i < vars.nextpoints.length; i++) {
				if(p.top > vars.nextpoints[i].top) {
					p = vars.nextpoints[i];
				}
				if(vars.maxtop < vars.nextpoints[i].top) {
					vars.maxtop = vars.nextpoints[i].top
				}
			}
			$(this).css("left", p.left);
		 	$(this).css("top", p.top);
			p.top += vars.margintop + $(this).outerHeight();
			if(vars.maxtop < p.top) {
				vars.maxtop = p.top;
			}
			vars.container.css("height", vars.maxtop + vars.margintop);
		 });
	 };

})(jQuery);
