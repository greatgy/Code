$(function($){
	$.fn.scrollHandle({
		obj: window,
		fun: loadmoreRender
	});
	$("#keywordsearch").val(PageData.keyword);
	search();
});

/**
 * 设置高亮
 */
function setDataDocHighlight(doc, other) {
	for(var k in other) {
		doc[k] = other[k][0];
	}
}

/**
 * 数据加工
 */
function dataHandling(data, other) {
	for(var i in data.docs) {
		var doc = data.docs[i];
		var uid = doc['uid'];
		setDataDocHighlight(doc, other[uid]);
	}
	for(var index = 0; index < data.docs.length; index++) {
		var isVideo = false;
		var isPhoto = false;
		if (data.docs[index].kind == 1) {
			isVideo = true;
		} else if (data.docs[index].kind == 2) {
			isPhoto = true;
		}
		//当摘要为空的时候
		if(data.docs[index].summary == "" || data.docs[index].summary == null || data.docs[index].summary == 'undefined') {
			var content = data.docs[index].content;
			if (content.length <= 75) {
				data.docs[index].summary = content;
			} else {
				data.docs[index].summary = content.substring(0, 75) + "......";	
			}
		} 
		data.docs[index].isVideo = isVideo;
		data.docs[index].isPhoto = isPhoto;
	}
	return data;
}

/**
 * 页面的第一次查询
 */
function search() {
	var args = {start: 0, table: PageData.table, rows:PageData.pagesize, sort:PageData.sort};
	client.search(PageData.searchword, args, function(json){
		if (json.response.numFound > PageData.pagesize) {
			$(".nomore").addClass("hd");
			$(".IsEmpty").addClass("hd");
			$(".load").removeClass("hd");
			$.fn.scrollHandle({
				obj: window,
				fun: loadmoreRender
			})
		} else {
			if (json.response.numFound > 0) {
				$(".IsEmpty").addClass("hd");
				$(".load").addClass("hd");
				$(".nomore").removeClass("hd");
			} else {
				$(".IsEmpty").removeClass("hd");
				$(".load").addClass("hd");
				$(".nomore").addClass("hd");
				return;
			} 
		}
		var data = dataHandling(json.response, json.highlighting);	
		var template = Handlebars.compile($("#tmplArticles").html());
		var html = template(data);
		$(".tabBox").append(html);
	});
}

/**
* 加载更多
*/
function loadmoreRender() {
	PageData.start = PageData.pagenum * PageData.pagesize;
	PageData.pagenum = PageData.pagenum + 1;
	var start = PageData.start * PageData.pagesize;	
		var args = {start: PageData.start, table: PageData.table, rows:PageData.pagesize, sort:PageData.sort};
		client.search(PageData.searchword, args, function(json){
			$(".loading").addClass("hd");
			if (json.response.docs.length <= 0) {
				$(window).unbind("scroll", defaultScrollHandler);
				if ($(".tabBox li").length <= PageData.pagesize) {
					return;
				}
				$(".load").addClass("hd");
				$(".nomore").removeClass("hd");
				return;
			}
			var data = dataHandling(json.response, json.highlighting);
			var template = Handlebars.compile($("#tmplArticles").html());
			var html = template(data);
			$(".tabBox").append(html);
		});
}
