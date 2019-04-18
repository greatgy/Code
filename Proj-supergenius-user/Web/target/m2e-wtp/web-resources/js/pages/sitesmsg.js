/**
 * 获取 obj = {} 中key的个数
 * 参数 p 是否过滤空
 * 使用: var map = {};
 *      $.Object.length.call(map);
 */
$.extend({
	Object:{
		length:function(p){
			p = p || false;
			return $.map(this, function(o) {
				if (!p) {
					return o;
				} else {
					return true;
				}
			}).length;
		}
	}
})

/*---------------------------------------------------------------------------------------------------*/

/**
 * 若修改该项目中的此文件,则其它项目中的此文件要同时修改
 */
var initPageState = 0;
var siteMsgTotalCount = 0;
var pageState = 0;

$(function(){
	/*$(".newsnotice").click(function() {
		$("#sitemsg").toggleClass("hd");
		return false;
	});
	$("body").click(function() {     
		$("#sitemsg").addClass("hd");
	});*/
	$(".newmessage").click(function() {
		window.open($(this).attr("href"));
	});
	$(".ignore").click(function() {    
		var channel = $(this).parent().data("channel");
        var url = ignoreMsgUrl[channel];
        var clickNode = $(this);
		$.getJSON(url, function(data){   
			if(data.ignoreResult){
				clickNode.parent().remove();        
				var ignoreNum = clickNode.parent().find("a b").text();
				siteMsgTotalCount -= Number(ignoreNum); 
				$(".hasnews .number").html(siteMsgTotalCount);
				if(siteMsgTotalCount == 0){
					pageState = 0;
					$("#sitemsg").remove();
					$(".hasnews").hide();
					$(".nonews").show();
				}
			}
		});
		return false;
	});
	$("#sitemsg div.nomsg").click(function(){
		//阻止事件继续响应
		return false;
	});
	for (var key in msgUrl) { 
		$.getJSON(msgUrl[key], function(data) {  
			siteMsgTotalCount += data.count;
			initChannelMsgCount(data);
			initSiteMsgStyle(data);
		})
	}
	$(".newsnotice").bind("click", getSitesMsgCount);
})

/**
 * 初始化各站点新消息数量(对站点数据、样式、节点设置)
 * @param data
 */
function initChannelMsgCount(data) {
	$("#sitemsg ul li").each(function(){
		if ($(this).data("channel") == data.channel) { 
			$(this).find("a b").html(data.count);
		}
	})
	initPageState++;
}

/**
 * 初始化站点消息样式(判断消息总数是否为0,进行一些页面设置)
 * @param data
 */
function initSiteMsgStyle(data) {
	if (initPageState == $.Object.length.call(msgUrl)) {
		$(".newsection .trangle").css("margin-left", "40%");
		if (siteMsgTotalCount > 0) {
			$(".hasnews .number").html(siteMsgTotalCount);
			$(".hasnews").show();
		} else {
			$("#sitemsg ul").remove();
			getAndSetQuotes();
		}
	}
}

/**
 * 获取并设置名人名言
 */
function getAndSetQuotes() {
	$.getJSON(quoteUrl, function(data) {
		if (data.author != null && data.author != "undefined") {
			$("#sitemsg div.nomsg pre p").html(data.content);
			$("#sitemsg div.nomsg pre span").html("———" + data.author);
		}
		$(".newsection .trangle").css({"margin-left": "40%"});
		$("#sitemsg div.nomsg").show();
		$(".nonews").show();
	})
}

/**
 * 获取各站点新消息数量
 */
function getSitesMsgCount() { 
	var i = 0;  
	var msgCount = 0;
	for (var key in msgUrl) {
		$.getJSON(msgUrl[key], function(data){
			i ++;
			msgCount += data.count;
			setChannelMsgCount(data);
			setSiteMsgStyle();
			if (i == Object.keys(msgUrl).length){
				if (msgCount == 0) {
					if ($("#sitemsg ul").length < 1)
						return;
					getAndSetQuotes();
					$("#sitemsg ul").remove();
					$(".hasnews").hide();
					$(".nonews").show();
				}
			}
		})
	}
}

/**
 * 设置各站点新消息数量
 * @param data
 */
function setChannelMsgCount(data) {
	$("#sitemsg ul li").each(function(){
		if ($(this).data("channel") == data.channel) {
			if (data.count > 0) {
				$(this).find("a b").html(data.count);
			} else {
				$(this).remove();
			}
		}
	})
	pageState++;
}

/**
 * 显现站点新消息数量
 */
function setSiteMsgStyle() {
	if (pageState == $.Object.length.call(msgUrl) && siteMsgTotalCount > 0) {
		$("#sitemsg ul").show();          
	} else if (pageState == $.Object.length.call(msgUrl)) {
		$("#sitemsg div.nomsg").show();
	}
	$("#sitemsg").removeClass("hd");
}
