/**
 * 事件初始化
 */
function initBindCollect() {
	mouseEventBind();
}

/**
 * 取消收藏鼠标移入移除事件
 */
function mouseEventBind() {
	$(".collected").unbind();
	$(".collected").bind({
		mouseover: function(){$(this).parent().find("span").css("display","block");},
		mouseout: function(){$(this).parent().find("span").css("display","none");}
	});
}

/**
 * 取消收藏ajax请求之后
 * @param e
 */
function collectMinusAjaxHandler(e) {
	$(e.obj).find(".heart").attr("src", g.baseimg +"/imgs/default/notcollect.png");
	$(e.obj).find("span").fadeOut("800","linear",function(){
		$(e.obj).parents("li").remove();
	});
}

/**
 * 取消收藏ajax请求之后
 * @param e
 */
function collectPlusAjaxHandler(e) {
	alert("取消收藏失败");
}

/**
 * 收藏的文章、论战、话题切换
 * @param obj
 * @param showIndex
 */
function ajaxChoose(obj, showIndex) {
	var type = $(obj).data("type");
	var url = g.base + "/my/ajax/mct/collect/" + type;
	$.get(url, function(data){
		var show = $("#collect" + showIndex).find("ul");
		show.html(data);
		bindCollect();
		mouseEventBind();
	})
}
