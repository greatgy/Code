$(function(a){a.fn.scrollHandle({obj:window,fun:loadmoreRender});a("#keywordsearch").val(PageData.keyword);search()});function setDataDocHighlight(a,d){for(var b in d)a[b]=d[b][0]}
function dataHandling(a,d){for(var b in a.docs){var c=a.docs[b];setDataDocHighlight(c,d[c.uid])}for(b=0;b<a.docs.length;b++){var f=c=!1;1==a.docs[b].kind?c=!0:2==a.docs[b].kind&&(f=!0);if(""==a.docs[b].summary||null==a.docs[b].summary||"undefined"==a.docs[b].summary){var e=a.docs[b].content;a.docs[b].summary=75>=e.length?e:e.substring(0,75)+"......"}a.docs[b].isVideo=c;a.docs[b].isPhoto=f}return a}
function search(){client.search(PageData.searchword,{start:0,table:PageData.table,rows:PageData.pagesize,sort:PageData.sort},function(a){if(a.response.numFound>PageData.pagesize)$(".nomore").addClass("hd"),$(".IsEmpty").addClass("hd"),$(".load").removeClass("hd"),$.fn.scrollHandle({obj:window,fun:loadmoreRender});else if(0<a.response.numFound)$(".IsEmpty").addClass("hd"),$(".load").addClass("hd"),$(".nomore").removeClass("hd");else{$(".IsEmpty").removeClass("hd");$(".load").addClass("hd");$(".nomore").addClass("hd");
return}a=dataHandling(a.response,a.highlighting);a=Handlebars.compile($("#tmplArticles").html())(a);$(".tabBox").append(a)})}
function loadmoreRender(){PageData.start=PageData.pagenum*PageData.pagesize;PageData.pagenum+=1;client.search(PageData.searchword,{start:PageData.start,table:PageData.table,rows:PageData.pagesize,sort:PageData.sort},function(a){$(".loading").addClass("hd");0>=a.response.docs.length?($(window).unbind("scroll",defaultScrollHandler),$(".tabBox li").length<=PageData.pagesize||($(".load").addClass("hd"),$(".nomore").removeClass("hd"))):(a=dataHandling(a.response,a.highlighting),a=Handlebars.compile($("#tmplArticles").html())(a),
$(".tabBox").append(a))})};
