$(function($){
	$(document).ajaxStart(function(){
		$(".loading").removeClass("hd");
	})
	$(document).ajaxStop(function(){
		$(".loading").addClass("hd");
	})
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
		var isDebate = false;
		if(data.docs[index]._table_ =="finance"){
			if(doc.firstTypeName == "debate") {
				data.docs[index].href = "{0}/debate/article/{1}".format(financebase, data.docs[index].id);
			} else {
				data.docs[index].href ="{0}/{1}/{2}/{3}".format(financebase, "article", data.docs[index].firstCid, data.docs[index].oid);
			}
		}
		if(data.docs[index]._table_ =="startup"){
			data.docs[index].href ="{0}/{1}/{2}/{3}".format(startupbase, "article", data.docs[index].cid, data.docs[index].oid);
		}
		if(data.docs[index]._table_ =="ai"){
			data.docs[index].href ="{0}/{1}/{2}/{3}".format(aibase, "article", data.docs[index].cid, data.docs[index].oid);
		}
		if(data.docs[index]._table_ =="gupage"){
			data.docs[index].href ="{0}/{1}/{2}/{3}".format(gupagebase, "article", data.docs[index].cid, data.docs[index].oid);
		}
		if(data.docs[index]._table_ =="enterpriser"){
			data.docs[index].href ="{0}/{1}/{2}/{3}".format(enterpriserbase, "article", data.docs[index].cid, data.docs[index].oid);
		}
		if(data.docs[index]._table_ =="career"){
			if(data.docs[index].firstTypeName == "problem"){
				data.docs[index].href ="{0}/{1}/{2}/{3}".format(careerbase, "puzzled", "detail", data.docs[index].oid);
			}else{
				data.docs[index].href ="{0}/{1}/{2}/{3}".format(careerbase, "article", data.docs[index].cid, data.docs[index].oid);
			}
		}
		//当摘要为空的时候
		if(data.docs[index].summary == "" || data.docs[index].summary == null || data.docs[index].summary == 'undefined') {
			var content = data.docs[index].content;
			if (content.length <= 100) {
				data.docs[index].summary = content;
			} else {
				data.docs[index].summary = content.substring(0, 100) + "......";
			}
		} 
		var summ= data.docs[index].summary;
		if(summ.length>100) {
			data.docs[index].summary = summ.substring(0, 100) + "......";
		}
	}
	return data;
}

/**
 * 页面的第一次查询
 */
function search() {
	$(".loading").removeClass("hd");
	var fq = '';
	if(PageData.fq != '' && PageData.fq != null && PageData.fq != 'undefined'){
		fq = 'author:' + PageData.fq;
	}
	var args;
		args = {start: 0, table: PageData.table, fq:fq, rows:PageData.pagesize, sort:PageData.sort};
	    client.search(PageData.searchword, args, function(json){
	    	$(".loading").addClass("hd");
			if (json.response.numFound > PageData.pagesize) {
				$(".load").removeClass("hd");
				$.fn.scrollHandle({
					obj: window,
					fun: loadmoreRender
				})
			} else {
				$(window).unbind("scroll", defaultScrollHandler);
				if (json.response.numFound > 0) {
					$(".load").addClass("hd");
					$(".nomore").removeClass("hd");
				} else {
					$(".IsEmpty").removeClass("hd");
					$(".load").addClass("hd");
					return;
				} 
			}
			var data = dataHandling(json.response, json.highlighting);	
			var template = Handlebars.compile($("#tmplArticles").html());
			var html = template(data);
			$(".curResult").append(html);
	});
}

/**
* 加载更多
*/
function loadmoreRender() {
	PageData.start = PageData.pagenum * PageData.pagesize;
	PageData.pagenum = PageData.pagenum + 1;
	var args = {start: PageData.start, table: PageData.table, rows:PageData.pagesize, sort:PageData.sort};
	$(".loading").removeClass("hd");
	if(prepage!=PageData.pagenum){
		prepage=PageData.pagenum
		client.search(PageData.searchword, args, function(json){
			$(".loading").addClass("hd");
			if (json.response.docs.length <= 0) {
				$(window).unbind("scroll", defaultScrollHandler);
				$(".load").addClass("hd");
				$(".nomore").removeClass("hd");
				if ($(".curResult li").length <= 0) {
					return;
				}
				return;
			}
			var data = dataHandling(json.response, json.highlighting);
			var template = Handlebars.compile($("#tmplArticles").html());
			var html = template(data);
			$(".curResult").append(html);
		});
	}	
}