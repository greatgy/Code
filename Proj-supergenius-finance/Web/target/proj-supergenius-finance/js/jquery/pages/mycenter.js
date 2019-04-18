$(function(){
	//初始化页面模块事件
	initMyCenterEvent();
	//绑定导航栏切换事件
	initNavigationBarEvent();
})

/**
 * 事件初始化
 */
function initMyCenterEvent(){
	initArticleEvent();
	initTopicEvent();
	initCollectEvent();
	initCommentEvent();
	initSubscribeEvent();
}

/**
 * 我的文章全局数据
 */
var FINANCE_PAGE = [
{
	tab:"all",
	pagenum:1,
	url: g.base + "/ajax/my/article",
	isfirst:false
	
},
{
	tab:"publish",
	pagenum:1,
	url: g.base + "/ajax/my/article/publish",
	isfirst:true
},
{
	tab:"refuse",
	pagenum:1,
	url: g.base + "/ajax/my/article/2",
	isfirst:true
},
{
	tab:"init",
	pagenum:1,
	url: g.base + "/ajax/my/article/0",
	isfirst:true
}];

/**
 * 我的文章初始化页面事件
 */
function initArticleEvent(){
	FINANCE_PAGE[0].pagenum = 1;
	FINANCE_PAGE[0].isfirst = false;
	FINANCE_PAGE[1].pagenum = 1;
	FINANCE_PAGE[1].isfirst = true;
	FINANCE_PAGE[2].pagenum = 1;
	FINANCE_PAGE[2].isfirst = true;
	FINANCE_PAGE[3].pagenum = 1;
	FINANCE_PAGE[3].isfirst = true;
	$(".t06_w680_more").bind("click", article_loadmore);
	$(".tab").bind("click",setTab);
}

function initTopicEvent() {
	intiTopicData();
	$(".topicloadmore").bind("click", topic_loadmore)
	$(".tabtopic").click(setStateTab);
}

function initCollectEvent() {
	$(".collectloadmore").bind("click", collect_loadmore);
	initCollectData();
	initCollectPageEvent();
}

function initCollectPageEvent() {
	initBindCollect();
	$(".collectnav li").unbind("click");
	$(".collectnav li").bind("click", function(){
		$(this).siblings().removeClass("curSelect");
		$(this).addClass("curSelect");
		var index = $(this).index();
		var showIndex = index + 1;
		$("#collect" + showIndex).show();
		$("#collect" + showIndex).siblings().hide();
		ajaxChoose(this, showIndex);
	})
}

function initCommentEvent() {
	$("#comment_change").bind("click", comment_showChange);
	commentDeleteEvent();
	initCommentData();
	$(".commentloadmore").bind("click", comment_loadmore);
	initPage();
}

function initSubscribeEvent() {
	$("#addmoreimg").click(function(){
		$("#interestSubs").show();
		$("#mask").show();
	});
	$("a[id^='cancelsub']").click(function(){
		subscribeHandle(this);
	});
	$(".subscribeloadmore").bind("click", subscribe_loadmore);
	subscribe_pagenum = 1;
}

/**
 * 导航栏切换事件
 */
function initNavigationBarEvent(){
	$("#navigationbar li a").click(function(){
		var channel = $(this).data("channel");
		$("#navigationbar li a").removeClass("activeblock");
		$(this).addClass("activeblock");
		var url = g.base + "/ajax/my/center/" + channel;
		$.get(getNoCachePath(url), function(data){
			if(isAjaxNeedLogin(data)){
				defaultInitLogin();
			}else{
				$("#main").empty();
				$("#main").html(data);
				if (channel == "article") {
					initArticleEvent();
				} else if (channel == "topic") {
					initTopicEvent();
				} else if (channel == "collect") {
					initCollectEvent();
				} else if (channel == "comment") {
					initCommentEvent()
				} else {
					initSubscribeEvent();
				}
			}
		})
	});
}

var nothing = '<img class="noarticle" src="' + g.baseimg + '/imgs/default/prompt.png" width="481px" heigth="211px"/>';

/*------------------------------------------我的文章----------------------------------------------------*/

/**
 * 我的文章
 */
function setTab(){
	var tab = $(this).data("tab");
	$(".tablist .curtab").removeClass("curtab");
	$(this).parent().addClass("curtab");
	$(".content").hide();
	$("#article_" + tab).show();
	var url = "";
	var isfirst = false;
	for(var i=0;i<FINANCE_PAGE.length;i++){
		if(FINANCE_PAGE[i].tab == tab){
			url = FINANCE_PAGE[i].url;
			isfirst = FINANCE_PAGE[i].isfirst;
			FINANCE_PAGE[i].isfirst = false;
			break;
		}
	}
	if(isfirst){
		loadData(1, url, tab, isfirst);
	}
}

// 我的文章加载更多事件处理
function article_loadmore() {
	var tab = $(this).data("tab");
	var pagenum = -1;
	var url = "";
	for(var i=0;i<FINANCE_PAGE.length;i++){
		if(FINANCE_PAGE[i].tab == tab){
			FINANCE_PAGE[i].pagenum += 1;
			pagenum = FINANCE_PAGE[i].pagenum;
			url = FINANCE_PAGE[i].url;
			break;
		}
	}
	loadData(pagenum, url, tab, false, this);
}

// 我的文章从后台加载数据
function loadData(pagenum, url, tab, isfirst, obj){
	$.get(url, {pagenum:pagenum}, function(data){
		if(isAjaxNeedLogin(data)){
			defaultInitLogin();
		}else{
			if(data.trim() == ""){// 没有更多内容
				if(isfirst){
					$("#article_"+tab+" .noarticle").show();
					$(".more_"+tab).hide();
				}else{
					$(obj).html("没有更多文章");
					$(obj).unbind("click", article_loadmore);
					
				}
				return;
			}
			if (isfirst && $("#article_"+tab + " .t06_w680_info li").length < 10) {
				$(".more_"+tab).hide();
			}
			$("#article_"+tab + " .t06_w680_info").append(data);
		}
	});
}

/*--------------------------------------我的话题----------------------------------------------*/

/**
 * 我的话题全局数据
 */
var TOPIC_PAGE = [
{
	tab:"all",
	pagenum:1,
	url:g.base + "/ajax/my/topic/all/" + MYCENTER_GLOBLE.meid,
	isfirst:false
},
{
	tab:"publish",
	pagenum:1,
	url:g.base + "/ajax/my/topic/state/1",
	isfirst:true
},
{
	tab:"refuse",
	pagenum:1,
	url:g.base + "/ajax/my/topic/state/2",
	isfirst:true
},
{
	tab:"init",
	pagenum:1,
	url:g.base + "/ajax/my/topic/state/0",
	isfirst:true
},
{
	tab:"join",
	pagenum:1,
	url:g.base + "/ajax/my/topic/join/" + MYCENTER_GLOBLE.meid,
	isfirst:true
}];

/**
 * 初始化全局数据
 * @returns
 */
function intiTopicData(){
	for (var i = 0; i < TOPIC_PAGE.length; i++) {
		TOPIC_PAGE[i].pagenum = 1;
		if (i == 0) {
			TOPIC_PAGE[i].isfirst = false;
		} else {
			TOPIC_PAGE[i].isfirst = true;
		}
	}
}

function setStateTab(){
	var tab = $(this).data("tab");
	$(".tablist li").removeClass("curtab");
	$(this).parent("li").addClass("curtab");
	$(".content").addClass("hd");
	$("#topic_" + tab).removeClass("hd");
	var url = "";
	var isfirst = false;
	for(var i=0;i<TOPIC_PAGE.length;i++){
		if(TOPIC_PAGE[i].tab == tab){
			url = TOPIC_PAGE[i].url;
			isfirst = TOPIC_PAGE[i].isfirst;
			TOPIC_PAGE[i].isfirst = false;
			break;
		}
	}
	if(isfirst){
		topic_loadData(1, url, tab, isfirst);
	}
}
// 加载更多事件处理
function topic_loadmore() {
	var tab = $(this).data("tab");
	var pagenum = -1;
	var url = "";
	for(var i=0;i<TOPIC_PAGE.length;i++){
		if(TOPIC_PAGE[i].tab == tab){
			TOPIC_PAGE[i].pagenum += 1;
			pagenum = TOPIC_PAGE[i].pagenum;
			url = TOPIC_PAGE[i].url;
			break;
		}
	}
	topic_loadData(pagenum, url, tab, false);
}
// 从后台加载数据
function topic_loadData(pagenum, url, tab, isfirst){
		$.get(url, {pagenum:pagenum}, function(data){
			if(isAjaxNeedLogin(data)){
				defaultInitLogin();
			}else{
				if(data.trim() == ""){
					if(isfirst){
						$("#topic_"+tab+" .notopic").removeClass("hd");
						$(".more_"+tab).addClass("hd");
					}else{
						$(".more_"+tab).html("没有更多话题").unbind("click");
					}
					return;
				}
				if (isfirst && $("#topic_"+ tab + " ul li").length < 10) {
					$(".more_"+tab).addClass("hd");
				}
				$("#topic_"+ tab + " ul").append(data);
			}
	});
}

/**
 * 我发布和我参与的话题切换
 * @param name
 * @param cursel
 * @param n
 */
function topicSetTab(name,cursel,n){
	var menu = $("#" + name + cursel);
	menu.siblings().removeClass("curtopic");
	menu.addClass("curtopic");
	for (var i = 1; i <= n; i++) {
		var con = $("#list_" + name + "_" + i);
		if (i == cursel) {
			con.show();
		} else {
			con.hide();
		}
	}
	var topic = $("#" + name + cursel).data("topic");
	if(cursel == 2){
		if(topic == "first"){
			$("#topic_join").removeClass("hd");
			topic_loadData(1, g.base + "/ajax/my/topic/join/" + MYCENTER_GLOBLE.meid, "join", true);
		}
		$("#" + name + cursel).data("topic","");
	}
}

/*--------------------------------------我的收藏----------------------------------------------*/

/**
 * 事件初始化
 */
function initBindCollect() {
	bindCollect();
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
	for (var i = 0; i < collect_data.length; i++) {
		if (collect_data[i].tab == type) {
			if (collect_data[i].isfirst) {
				collect_data[i].isfirst = false;
				var url = g.base + "/ajax/my/collect/change/" + type;
				$.get(url, function(data){
					if(isAjaxNeedLogin(data)){
						defaultInitLogin();
					}else{
						var show = $("#collect" + showIndex).find("ul");
						show.html(data);
						if($("#collect" + showIndex + " ul li").length < 1){
							$("#collect" + showIndex + " .collectloadmore").hide();
							$("#collect" + showIndex + " ul").before(nothing);
						}
						if ($("#collect" + showIndex + " ul li").length < 10) {
							$("#collect" + showIndex + " .collectloadmore").hide();
						}
						if ("debate" == type) {
							InitDebateVoteEvent();
						}
						bindCollect();
						mouseEventBind();
					}
				})
			}
		}
	}
	
}

function initCollectData(){
	for (var i = 0; i < collect_data.length; i++) {
		collect_data[i].pagenum = 1;
		if (i == 0) {
			collect_data[i].isfirst = false; 
		} else {
			collect_data[i].isfirst = true;
		}
	}
}

var collect_data = [{
	pagenum : 1,
	isfirst : false,
	tab : "article"
},{
	pagenum : 1,
	isfirst : true,
	tab : "topic"
},{
	pagenum : 1,
	isfirst : true,
	tab : "debate"
}];

function collect_loadmore(){
	var type = $(this).data("collect");
	var pageunm = type + "_pagenum";
	var obj = this;
	for (var i = 0; i < collect_data.length; i++) {
		if (type == collect_data[i].tab) {
			collect_data[i].pagenum += 1;
			$.get(g.base + "/ajax/my/collect_{0}_{1}".format(collect_data[i].pagenum, type),function(data){
				if(isAjaxNeedLogin(data)){
					defaultInitLogin();
				}else{
					if (data.trim() == "") {
						$(obj).html("无更多收藏").unbind("click");
					} else {
						$(obj).siblings("ul").append(data);
						initCollectPageEvent();
						if ("debate" == collect) {
							InitDebateVoteEvent();
						}
					}
				}
			});
		}
	}
}

/*----------------------------------------------我的评论------------------------------------------------*/

function commentDeleteEvent(){
	$(".delete").unbind("click");
	$(".delete").bind("click", function(){
		comment_deleteMyCommentHandle(this);
	})
}

function comment_deleteMyCommentHandle(obj) {
	var id = $(obj).data("id");
	var url = g.base + "/ajax/my/comment/delete/" + id;
	$.get(getNoCachePath(url), function(data){
		if (!data) {
			alert("操作失败");
		} else {
			$(obj).prev().fadeIn("1000","linear",function(){
				$(obj).fadeOut("1000","linear");
				$(obj).parents("li").remove();
			});
		}
	})
}

function initCommentData(){
	for (var i = 0; i < comment_data.length; i++) {
		comment_data[i].pagenum = 1;
		if (i == 0) {
			comment_data[i].show = true;
			comment_data[i].isfirst = false;
		} else {
			comment_data[i].show = false;
			comment_data[i].isfirst = true;
		}
	}
}

var comment_data = [{
	pagenum : 1,
	show : true,
	isfirst : false,
	tab : "mine",
	name : "我收到的评论"
},{
	pagenum : 1,
	show : false,
	isfirst : true,
	tab : "tome",
	name : "我发表的评论"
}];

/**
 * 加载更多
 */
function comment_loadmore() {
	var tab = $(this).data("tab");
	for(var i = 0; i < comment_data.length; i++) {
		if (comment_data[i].tab == tab) {
			comment_data[i].pagenum +=1;
			comment_loadData(tab, comment_data[i].pagenum, comment_data[i].isfirst);
		}
	}
}

/**
 * 我发布的评论和我收到的评论切换
 */
function　comment_showChange(){
	for (var i = 0; i < comment_data.length; i++) {
		comment_data[i].show = !comment_data[i].show;
	}
	for (var i = 0; i < comment_data.length; i++) {
		if (comment_data[i].show) {
			//显示
			$("#comment_change").html(comment_data[i].name);
			var tab = comment_data[i].tab;
			$("div[id^='comment_']").hide();
			$("#comment_" + tab).show();
			if (comment_data[i].isfirst) {
				comment_loadData(tab, comment_data[i].pagenum, comment_data[i].isfirst);
				comment_data[i].pagenum += 1;
				comment_data[i].isfirst = false;
			}
		}
	}
}

/**
 * 加载数据
 */
function comment_loadData(tab, pagenum, isfirst){
	var url = g.base + "/ajax/my/comment/{0}/{1}".format(tab, pagenum);
	$.get(getNoCachePath(url), function(data){
		if(isAjaxNeedLogin(data)){
			defaultInitLogin();
		}else{
			if (data.trim() == "") {
				if (isfirst) {
					$(".commentloadmore_" + tab).hide();
					$(".mainleft #comment_" + tab + " ul").before(nothing);
				}
				$(".commentloadmore_" + tab).html("无更多评论").unbind("click");
			} else {
				$("#comment_next_" + tab).append(data);
				if(isfirst && $("#comment_next_" + tab + " li").length < 20){
					$(".commentloadmore_" + tab).hide();;
				}
				commentDeleteEvent();
				initPage();
			}
		}
	})
}

/*-----------------------------------------我的订阅------------------------------------------------------*/

function subscribeHandle(obj) {
	var id = $(obj).data("id");
	var url = g.base + "/my/subscribe/" + id;
	$.get(getNoCachePath(url), function(data){
		if (data) {
			alert("操作失败");
		} else {
			$(obj).prev().fadeIn("1000","linear",function(){
				$(obj).fadeOut("1000","linear");
				$(obj).parents("li").remove();
			});
		}
	})
}

var subscribe_pagenum = 1;
var id="#musubs";
function subscribe_loadmore(){
	$.get(g.base + "/ajax/my/substribe/{0}".format(subscribe_pagenum + 1),function(data){
		if(isAjaxNeedLogin(data)){
			defaultInitLogin();
		}else{
			if (data.trim() == "") {
				$(".subscribeloadmore").html("无更多订阅").unbind("click");
			} else {
				subscribe_pagenum += 1;
				$(id).append(data);
				bindSubsEvent();
			}
		}
	});
}

function bindSubsEvent() {
	$("a[id^='cancelsub']").unbind("click");
	$("a[id^='cancelsub']").bind("click", function(){
		subscribeHandle(this);
	});
}

/*------------------------------------------------表情--------------------------------------------------*/
function replaceContent(str) {
	str = str.replace(/\[em_([0-9]*)\]/g,'<img src="' + g.baseimg + '/imgs/face/qq/$1.gif" border="0" />');
	return str;
}

/*生成页面表情*/
function initPage() {
	$(".comments").each(function(){
		var content = replaceContent($(this).html());
		$(this).html(content);
	});
}
