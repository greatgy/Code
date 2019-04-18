$(document).on('click', "a[id^='btnprize']", function(){
	$(this).prize();
 }); 

;(function ($) {
	$.fn.prize = function(options) {
		$(this).blur();
		var uid = $(this).attr("id");
		var temp = $(this);
		var json = {};
		json.href=window.location.href;
		var uri = base + "/ajax/prize/" + uid;
		$.get(getNoCachePath(uri), json, function(data){
			if(data){
				prizePlusAjaxHandler(temp);
			}else{
				prizeMinusAjaxHandler(temp);
			}
		});
		prizeBindEvent();
	};
	
	/**
	 * 赞ajax处理
	 */
	function prizePlusAjaxHandler(obj) {
		updatePrizeCount(obj, true);
		$(obj).children("img").each(function(){
			//$(this).data("isprize", true);
			$(this).attr("data-isprize", true);
			prizeBindEvetEach(this);
		});
	}
	
	/**
	 * 取消赞ajax处理
	 */
	function prizeMinusAjaxHandler(obj) {
		updatePrizeCount(obj, false);
		$(obj).children("img").each(function(){
			//$(this).data("isprize",false);
			$(this).attr("data-isprize",false);
			prizeBindEvetEach(this);
		});
	}
	
})(jQuery);

/**
 * 为赞的图标绑定事件
 */
function prizeBindEvent(){
	$("a[id^='btnprize'] img").each(function(){
		prizeBindEvetEach(this);
	});
}

/**
 * 为某个赞注册事件
 * @param obj
 */
function prizeBindEvetEach(obj){
	var isprize = $(obj).attr("data-isprize");
	if(isprize == "true") {
		$(obj).attr("title", "取消赞");
		$(obj).addClass("specialimg");
	}else{
		$(obj).removeClass("specialimg");
		$(obj).attr("title", "点击赞");
	}
}

/**
 * 更新赞的数量
 * @param obj
 * @param isprize
 */
function updatePrizeCount(obj, isprize){
	var countstr = $(obj).children("span").html();
	if (isprize) {
		$(obj).children("span").html(parseInt(countstr)+1);
	} else {
		$(obj).children("span").html(parseInt(countstr)-1);
	}
}
