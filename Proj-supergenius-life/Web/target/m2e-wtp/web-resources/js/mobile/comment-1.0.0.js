/****************************************************************************************
 *天财评论为例： 评论回复是通过js—comment.js实现，该js会在页面加载完毕后，调用id以btncomment开头的元素，通过传入Echanel(板块finance)_oid(用户oid)_uid(详细财经uid)并通过异步方法，
 *将数据组织为html在指定区域中显示
 *
 *详细：
 *1、这是一个加在jQuery原型上的插件，规定页面上连接标签，如果是以btncomment开头的话，点击后则会触发此插件功能
 *2、触发连接后，将会触发ajax请求，向服务器发送的数据是连接的id值，形式为： <a id="btncommentfinance_${me.oid}_${bean.uid}">
 *3、控制器为 CommentController ，控制器将解析出请求的板块枚举类型，用户的oid，以及评论对象的uid，处理完毕后将会转到apicomment.ftl页面
 *4、apicomment.ftl组织填充数据后显示评论板块
 *评论计数的即时显示功能： 
 *var countervalue = $(this).attr("counterclass");
 *vars.counter = $("."+countervalue);
 *即：当前连接将会有一个属性来标识计数的标签
 */

/****************************************************************************************
 * 赞
 */


;(function ($) {
	$.fn.prize = function(options) {
		$(this).blur();
		var uid = $(this).attr("id");
		var temp = $(this);
		var json = {};
		json.href=href;
		var uri = base + "/m/ajax/prize/" + uid;
		$.get(getNoCachePath(uri), json, function(data){
			if(data){
				dataparams.prize = dataparams.prize + 1;
				prizePlusAjaxHandler(temp);
			}else{
				dataparams.prize = dataparams.prize - 1;
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
		if ($(obj).data("notcomment")){
			$(obj).attr("src", baseimg + "/imgs/default/redheart.png");
		} else {
			$(obj).attr("src", baseimg + "/imgs/default/zaned.png");
		}
	}else{
		 if ($(obj).data("notcomment")){
			$(obj).attr("src", baseimg + "/imgs/default/heart.png");
		} else {
			$(obj).attr("src", baseimg + "/imgs/default/zan.png");
		}
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

/********************************************************************************************
 * 收藏
 */
;(function($){
	$.fn.collect = function(options) {
		var defauts = {
				uid : "",
				collectClickHandler : defaultCollectClickHandler
		}
		var vars = $.extend({}, defauts, options);
		if ($(this).length >0 ) {
			vars.uid = $(this).attr("id").replace("btncollect", "");
			vars.obj = this;
			$(this).bind("click", vars, vars.collectClickHandler);
		}
	}
	
	function defaultCollectClickHandler(e){
		var vars = e.data;
		var json = {};
		var uri = base + "/m/my/ajax/collect/" + vars.uid;
		$.get(getNoCachePath(uri), json, function(data){
			if (data) {
				dataparams.collect = 1;
				collectPlusAjaxHandler(vars);
			} else {
				dataparams.collect = 0;
				collectMinusAjaxHandler(vars);
			}
			updateCollect(data);
		});
	}
	
})(jQuery);

function collectMinusAjaxHandler(vars) {
	$(vars.obj).find(".liwaysimg02").attr("src", baseimg + "/imgs/default/collect.png");
	$(vars.obj).data("iscollect", false);
}

function collectPlusAjaxHandler(vars) {
	$(vars.obj).find(".liwaysimg02").attr("src", baseimg + "/imgs/default/collects.png");
	$(vars.obj).data("iscollect", true);
}

function updateCollect(isadd){
	var collectcount = $("#articlecollectcount").html();
	if(isadd){
		$("#articlecollectcount").html(parseInt(collectcount)+1);
	}else{
		$("#articlecollectcount").html(parseInt(collectcount)-1);
	}
}
$(function($){
	$(".shareBox a[id^='btncollect']").each(function(){$(this).collect();});
});

function registerPraise(){
	$(".reply a[id^='btnprize']").each(function(){$(this).prize()});
	prizeBindEvent();
}

/**
 * 解除点赞事件绑定
 */
function unRegisterPraise(){
	$(".reply a[id^='btnprize']").each(function(){
		$(this).unbind("click");
	});
}

function collectBindEvet(){
	$("a[id^='btncollect'] img").each(function(){
		collectBindEvetEach(this);
	});
}

/**
 * 为某个取消收藏注册事件
 * @param obj
 */
function collectBindEvetEach(obj){
	var iscollect = $(obj).data("iscollect");
	if(iscollect) {
		$(obj).attr("src", baseimg + "/imgs/default/collects.png");
	} else {
		$(obj).attr("src", baseimg + "/imgs/default/collect.png");
	}
}

/**
 * 非会员进行跳转
 */
function isMember(base,cid,pcid){
	window.location.href=base+'/m/login?cid='+cid+'&pcid='+pcid;
}