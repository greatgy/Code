
/****************************************************************************************
 * 鼠标滚动加载事件
 */

;(function ($) {
	$.fn.scrollHandle = function(options) {
		var defaults = {
				obj: window,  //可滚动窗体对象
				fun: defaultHandle, //自定义处理
				defaultHeight: 0.9, //滚动条距离window底部的比例
				scrollHandler: defaultScrollHandler //绑定事件
		};
		var vars = $.extend({}, defaults, options);
		if(typeof vars.obj != "undefined") {
			$(vars.obj).bind("scroll", vars, vars.scrollHandler);
		}
	};
})(jQuery);

function defaultScrollHandler(e){
	var vars = e.data;
	var windowHeight = $(vars.obj).height();
	var	scrollTop = $(vars.obj).scrollTop();
	var documentHeight = $(document.body).height();
	if ((documentHeight - windowHeight - scrollTop)/windowHeight < vars.defaultHeight) {
		vars.fun();
	}
}

function defaultHandle(){

}