$(function(){
	//滚动加载更多
	$.fn.scrollHandle({
		obj: window,
		fun: loadmoreRender
	})
	$("#pagesearch").bind("click", pageSearch);
	$("li[id^='tab_']").bind("click", tabSwitch);
	$(".deletelink").click(function(){
		$(this).parents("li").remove();
	});
	$("#searchword").bind("keydown", enterSearch);
	setUserPageStyle();
	//页面内搜索框获得焦点状态
	$(".ritform input").focus(function(){
		$(this).parent().css({'border':'none','outline': '-webkit-focus-ring-color auto 5px'});
	});
	//页面内搜索框失去焦点状态
	$(".ritform input").blur(function(){
		$(this).parent().css({'border':'1px solid #D5D5D5','outline': 'none'});
	});
	//搜索图片样式
	$("#pagesearch").mouseenter(function() {
		$("#pagesearch img").attr("src", g.baseimg + "/imgs/default/bsearch.png");
    });
    $("#pagesearch").mouseleave(function() {
    	$("#pagesearch img").attr("src", g.baseimg + "/imgs/default/search.png");
    });
});

/**
 * 设置搜索框内容为空时抖动提示
 */
function shakeHandlePage() {
	$('#searchword').addClass("txtsearch");
	setTimeout("$('#searchword').removeClass('txtsearch')",400);
}

function enterSearch(event) {
	if (event.keyCode==13) {
		pageSearch();
	}
}

var pagenum = 1;
/**
 * 加载更多
 */
function loadmore() {
	var url = g.base + "/ajax/search/user?keyword=" + PageData.keyword;
	$.ajax({
		type:"GET",
		url:url,
		data:"pagenum=" + (pagenum + 1),
		beforeSend:function(XMLHttpRequest) {
			$("#loading").show();
		},
		success:function(data){
			$("#loading").hide();
			if (data.trim() == "") {
				$(window).unbind("scroll", defaultScrollHandler);
				if ($("#content ul li").length <= PageData.pagesize) {
					return;
				}
				//$(".loadmore").unbind("click", loadmore)
				$(".loadmore a").html("已无更多");
				$(".loadmore").show();
			} else {
				pagenum += 1;
				$("#content ul").append(data);
				if (new RegExp("^user").test(PageData.table)) {
					InitEventLogin();
				}
			}
		}
	});
}

function InitEventLogin(){
	$(".pop-up").each(function(){
		$(this).unbind("click", defaultInitLogin);
		$(this).bind("click", defaultInitLogin);
	});
}

/**
 * 订阅、取消订阅
 * @param obj
 * @param id
 */
function subscribeHandle(obj, id) {
	if (id == "") {
		return;
	}
	var url = g.base + "/my/subscribe/" + id;
	$.get(getNoCachePath(url), function(data){
		if (data) {
			$(obj).parent().find("img").attr("src", g.baseimg + "/imgs/default/cancelsubs.png");
			$(obj).parent().find(".addsub").html("已订阅").css("color","#f00");
		} else {
			$(obj).parent().find("img").attr("src", g.baseimg + "/imgs/default/addsubs.png");
			$(obj).parent().find(".addsub").html("订阅").css("color","#2979ba");
		}
	})
}

/**
* 设置返回的每个doc的值，以方便模板渲染(atricle)
*/
function setDataDoc_article(doc, data) {
	if(doc.firstTypeName == "debate") {
		doc.href = "{0}/debate/article/{1}".format(data.base, doc.id);
	} else {
		doc.href = "{0}/{1}/{2}".format(data.base, doc.firstTypeName, doc.oid);
	}
	doc.clickcount = 0;
	doc.prizecount = 0;
	doc.commentcount = 0;
}

/**
* 设置返回的每个doc的值，以方便模板渲染(topic)
*/
function setDataDoc_topic(doc, data){
	doc.href = "{0}/topic/{1}".format(data.base, doc.id);
	doc.clickcount = 0;
	doc.prizecount = 0;
	doc.commentcount = 0;
}

/**
* 设置返回的每个doc的值，以方便模板渲染(debate)
*/
function setDataDoc_debate(doc, data){
	var countred = data.countred;
	var countblue = data.countblue;
	data.redwidth = countred/(countred + countblue)*100;
	data.bluewidth = countblue/(countred + countblue)*100;
}

/**
* ajax设置相关值(topic)
*/
function myRenderAfter_topic(){
	var ids = {};
	$("div.searchbox").each(function(){
		if ($(this).data("rednerstatus") == 0) {
			var id = $(this).children("input[name='uid']").val();
			var userid = $(this).children("input[name='userid']").val();
			ids[id] = userid;
		}
	})
	if (Object.keys(ids).length > 0) {
		$.post(g.base + "/ajax/search/topic/count", {uids:JSON.stringify(ids)}, function(data){
			if (data == null) {
				return;
			}
			$("div.searchbox").each(function() {
				if ($(this).data("rednerstatus") == 0) {
					var id = $(this).children("input[name='uid']").val();
					$(this).data("rednerstatus", 1)
					$(this).find(".field").each(function(){
						var fd = $(this).data("field");
						if (fd == "hishome") {
							if (data[id].oid == 0) {
								$(this).attr("href", "javascript:void(0)");							
							} else {
								if (me.oid > 0) {
									if (data[id].oid == me.oid) {
										$(this).attr("href", g.base + "/my/topic/" + data[id].oid);
									} else {
										$(this).attr("href", g.base + "/his/topic/" + data[id].oid);
									}
								} else {
									$(this).attr("href", g.base + "/his/topic/" + data[id].oid);
								}
							}
						} else if (fd == "avatar") {
							var avatar;
							if (data[id].avatar == null || data[id].avatar == "") {
								avatar = defaultAvatar;
							} else {
								avatar = g.userimg + "/" + data[id][fd];
							}
							$(this).attr("src", avatar);
						} else {
							$(this).html(data[id][fd]);
						}
					});
				}
			});
		})
	}
}

/**
* ajax设置相关值(debate)
*/
function myRenderAfter_debate() {
	var ids = [];
	$("div.searchbox").each(function(){
		if ($(this).data("rednerstatus") == 0) {
			var id = $(this).children("input[name='uid']").val();
			ids.push(id);
		}
	})
	if (ids.length > 0) {
		$.post(g.base + "/ajax/search/debate/count", {uids:ids.toString()}, function(data) {
			$("div.searchbox").each(function(){
				if ($(this).data("rednerstatus") == 0) {
					$(this).data("rednerstatus", 1);
					var id = $(this).children("input[name='uid']").val();
					var countred = data[id].rednum;
					var countblue = data[id].bluenum;
					var redwidth = countred/(countred + countblue)*100;
					var bluewidth = countblue/(countred + countblue)*100;
					$(".rprogress").css("width", redwidth + "%");
					$(".bprogress").css("width", bluewidth + "%");
					$(".rednum_" + id).html(countred);
					$(".bluenum_" + id).html(countblue);
					$(this).find(".field").each(function(){
						var fd = $(this).data("field");
						if (fd == "red") {
							$(this).data("isvoted", data[id].voted);
							$(this).data("isme", data[id].isred);
						} else if (fd == "blue") {
							$(this).data("isvoted", data[id].voted);
							$(this).data("isme", data[id].isblue);
						}
					});
				}
			});
			//初始化点投票数据
			InitDebateVoteEvent();
		})
	}
}

var SearchParam = {};
setSearchParam(PageData.table);
function setSearchParam(table) {
	if (table == "finance") {
		SearchParam.tmpl = "#tmplArticles";
		SearchParam.box = "#searchBoxArticle";
		SearchParam.renderHandle = setDataDoc_article;
		SearchParam.renderAfterHandle = "";
	} else if(table == "topic") {
		SearchParam.tmpl = "#tmplTopic";
		SearchParam.box = "#searchBoxTopic";
		SearchParam.renderHandle = setDataDoc_topic;
		SearchParam.renderAfterHandle = myRenderAfter_topic;
	} else if (table == "debate") {
		SearchParam.tmpl = "#tmplDebate";
		SearchParam.box = "#searchBoxDebate";
		SearchParam.renderHandle = setDataDoc_debate;
		SearchParam.renderAfterHandle = myRenderAfter_debate;
	}
}

if (PageData.table != "user") {
	search();
}

/**
 * 页面的第一次查询
 */
function search() {
	var args = {start: 0, table: PageData.table, rows:PageData.pagesize, sort:PageData.sort};
	if (PageData.table == "finance" && me.oid == 0) {
		args.fq = "ispublic:true";
	}
	client.search(PageData.searchword, args, function(json){
		$(".keywordcount").html(json.response.numFound);
		$("li[id^='tab_'] a span").html("->");
		$("#tab_" + PageData.table + " a span").html(json.response.numFound);
		$("#emkeyword").html('"' + PageData.keyword + '"');
		$("#searchword").val(PageData.keyword);
		if (json.response.numFound >= PageData.pagesize) {
			$(".noarticle").hide();
			$(window).unbind("scroll", defaultScrollHandler);
			$.fn.scrollHandle({
				obj: window,
				fun: loadmoreRender
			})
		} else {
			if (json.response.numFound > 0) {
				$(".noarticle").hide();
				$(".loadmore").hide();
			} else {
				$(".loadmore").hide();
				$(".noarticle").show();
				return;
			}
		}
		client.render(json, SearchParam.tmpl, SearchParam.box, SearchParam.renderHandle);
		renderAfter(SearchParam.renderAfterHandle);
	});
}

/**
* 加载更多
*/
function loadmoreRender() {
	PageData.start = PageData.pagenum * PageData.pagesize;
	PageData.pagenum = PageData.pagenum + 1;
	var start = PageData.start * PageData.pagesize;
	if (PageData.table != "user") {
		$("#loading").show();
		var args = {start: PageData.start, table: PageData.table, rows:PageData.pagesize, sort:PageData.sort};
		if (PageData.table == "finance" && me.oid == 0) {
			args.fq = "ispublic:true";
		}
		client.search(PageData.searchword, args, function(json){
			$("#loading").hide();
			if (json.response.docs.length <= 0) {
				$(window).unbind("scroll", defaultScrollHandler);
				if ($("#content ul li").length <= PageData.pagesize) {
					return;
				}
				$(".loadmore a").html("已无更多");
				$(".loadmore").show();
				return;
			}
			client.render(json, SearchParam.tmpl, SearchParam.box, SearchParam.renderHandle);
			renderAfter(SearchParam.renderAfterHandle);
		});
	} else {
		loadmore();
	}
}

/**
* 页面搜索
*/
function pageSearch(){
	if ($("#searchword").val().trim() == "") {
		shakeHandlePage();
		return;
	}
	PageData.keyword = $("#searchword").val().trim();
	window.location.href= g.base + "/search/" + PageData.table + "?keyword=" + PageData.keyword;
}

/**
* 导航栏切换
*/
function tabSwitch(){
	var table = table = $(this).data("table").trim();
	if (table == "" || table == "undefined") {
		return;
	}
	window.location.href= g.base + "/search/" + table + "?keyword=" + PageData.keyword;
}

/**
* 设置数据
*/
function initData(data) {
	data.base = g.base;
	data.basejs = g.basejs;
	data.baseimg = g.baseimg;
	data.webimg = g.webimg;
	data.userimg = g.userimg;
	for (var i in data.list) {
		data.list[i].canpopup = false;
		if (me.oid == data.list[i].oid && me.oid != 0) {
			data.list[i].url = g.base + "/my/article";
			data.list[i].url2 = g.base + "/my/topic";
		} else {
			data.list[i].url =  g.base + "/his/article/" + data.list[i].useroid;
			data.list[i].url2 = g.base + "/his/topic/" + data.list[i].useroid;
			if (me.oid != 0) {
				
			} else {
				data.list[i].canpopup = true;
			}
		}
		if (data.list[i].avatar == "unfefined" || data.list[i].avatar == "") {
			data.list[i].avatar = defaultAvatar;
		} else {
			data.list[i].avatar = g.userimg + "/" + data.list[i].avatar;
		}
		
	}
	return data;
}

/**
 * 设置页面样式
 */
function setUserPageStyle(){
	$(".loadmore").hide()
	$(".noarticle").hide();
	if ($("#searchBoxUser").hasClass("searchUserlist")) {
		if ($("#content ul li").length <= 0) {
			$(".noarticle").show();
		}
	}
}

/**
 * solr查询后更新页面数据
 * @param defauleRenderAfter
 */
function renderAfter(defauleRenderAfter) {
	if (typeof defauleRenderAfter == "function") {
		defauleRenderAfter();
	} else {
		var uid = [];
		var fields = [];
		var url = $("div.searchbox:first").children("input[name='url']").val();;
		$("div.searchbox:first").find(".field").each(function(){
			fields.push($(this).data("field"));
		});
		$("div.searchbox").each(function(){
			if ($(this).data("rednerstatus") == 0) {
				uid.push($(this).children("input[name='uid']").val());
			}
		})
		if (uid.length > 0 && fields.length > 0) {
			$.post(url, {uids:uid.toString(), fields: fields}, function(data){
				$("div.searchbox").each(function() {
					if ($(this).data("rednerstatus") == 0) {
						var id = $(this).children("input[name='uid']").val();;
						$(this).data("rednerstatus", 1)
						$(this).find(".field").each(function(){
							var fd = $(this).data("field");
							$(this).html(data[id][fd]);
						});
					}
				});
			});
		}
	}
}
