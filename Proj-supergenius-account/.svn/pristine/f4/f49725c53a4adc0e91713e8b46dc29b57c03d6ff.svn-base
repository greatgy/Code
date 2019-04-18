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
		var defaults = {
				uid: "",//oid_uid
				counter: {},
				prizeClickHandler: defaultPrizeClickHandler
		};
		var vars = $.extend({}, defaults, options);
		if($(this).length > 0) {
			vars.uid = $(this).attr("id").replace("btnprize", "");
			vars.counter = $($(this).data("counter"));
			vars.obj = this;
			$(this).bind("click", vars, vars.prizeClickHandler);
		}
	};
	
	/**
	 * 赞点击
	 */
	function defaultPrizeClickHandler(e){
		var vars = e.data;
		var json = {};
		var uri = g.baseuri + "/ajax/prize/" + vars.uid;
		$.get(getNoCachePath(uri), json, function(data){
			if(data){
				prizePlusAjaxHandler(vars);
			}else{
				prizeMinusAjaxHandler(vars);
			}
		});
	}
	
	/**
	 * 赞ajax处理
	 */
	function prizePlusAjaxHandler(vars) {
		var countstr = $(".prizecounter", vars.obj).html();
		var count = countstr.substring(1, countstr.length-1);
		$(".prizecounter", vars.obj).html("("+(parseInt(count)+1)+")");
		$(".prizecounter", vars.obj).siblings("img").each(function(){
			$(this).data("isprize", true);
			prizeBindEvetEach(this);
		});
	}
	
	/**
	 * 取消赞ajax处理
	 */
	function prizeMinusAjaxHandler(vars) {
		var countstr = $(".prizecounter", vars.obj).html();
		var count = countstr.substring(1, countstr.length-1);
		$(".prizecounter", vars.obj).html("("+(parseInt(count)-1)+")");
		$(".prizecounter", vars.obj).siblings("img").each(function(){
			$(this).data("isprize",false);
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
	var isprize = $(obj).data("isprize");
	if(isprize) {
		$(obj).attr("src", g.baseimg + "/imgs/default/heart_01.png");
		$(obj).attr("title", "取消赞");
	}else{
		$(obj).attr("src", g.baseimg + "/imgs/default/heart_02.png");
		$(obj).attr("title", "点击赞");
	}
	if(!isprize){
		$(obj).bind("mouseover", function(){
			$(obj).attr("src", g.baseimg + "/imgs/default/heart_01.png");
		});			
		$(obj).bind("mouseleave", function(){
			$(this).attr("src", g.baseimg + "/imgs/default/heart_02.png");
		});
	}else{
		$(obj).unbind("mouseover");
		$(obj).unbind("mouseleave");
	}
}

/****************************************************************************************
 * 转发
 */
;(function ($) {
	
	$.fn.share = function(options) {
		var defaults = {
				uid: "",//oid_uid
				mainlink:null,
				counter: {},
				target: {},
				maxlength:300,
				shareClickHandler: defaultShareClickHandler,
				btnsubmitClickHandler: defaultBtnsubmitClickHandler
		};
		var vars = $.extend({}, defaults, options);
		if($(this).length > 0) {
			vars.uid = $(this).attr("id").replace("btnshare", "");
			vars.mainlink = $(this);
			vars.counter = $(this).children("sup");
			vars.target = $("#box" + vars.uid + " .comments");
			$(this).bind("click", vars, vars.shareClickHandler);
		}
	};
	
	/**
	 * 点击转发
	 */
	function defaultShareClickHandler(e){
		var vars = e.data;
		if(vars.target.data("flag") == 1) {//为1时，是显示转发状态
			 vars.target.addClass("hd");
			 vars.target.data("flag", 0);
			 return;
		 } else {
			 vars.target.data("flag", 1);
			 vars.target.removeClass("hd");
			 $("#box" + vars.uid).removeClass("hd");
			 vars.target.html($("#sharebox").clone(true));
			 $("#sharebox", vars.target).removeClass("hd");
			 $("textarea", vars.target).val($(".content" + vars.uid).text().trim());
			 $("textarea", vars.target).data("uri", $(this).data("uri"));
			 $('textarea', vars.target).autosize();
			 computeTextBoxWordsCount($(".txtbox textarea", vars.target), $("#counter", vars.target));
			 $(".btnsubmit", vars.target).bind("click", vars, vars.btnsubmitClickHandler);
		 }
	}
	 
	/**
	 * 转发ajax处理
	 */
	function shareAjaxHandler(data, vars) {
		if (data) {
			var count = vars.counter.html().length == 0 ? 0 : vars.counter.html();
			vars.counter.html(parseInt(count) + 1);
			vars.mainlink.prepend("已");
			vars.target.addClass("hd");
		}
	}
	
})(jQuery);

$(function(){
	$("a[id^='btnprize']").each( function(){$(this).prize()});
	$("a[id^='btnshare']").each(function(){$(this).share();});
});

function registerPraise(){
	$(".reply a[id^='btnprize']").each(function(){$(this).prize()});
	prizeBindEvent();
}

