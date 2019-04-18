[#ftl]
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>搜索详情页</title>
	<meta name="Keywords" content="搜索，搜索详情页" />
	<meta name="Description" content="搜索，搜索详情页" />
	<!--# include file="/include/headcommon.html" -->
	<link rel="stylesheet" href="${basecss}/css/default/index.css">
</head>
<body>
	
	<div class="searchTop">
		<a href="#" class="logoimg">
			<img src="${baseimg}/imgs/default/logo.png" alt="" />
		</a>
		<form action="${base}/search" class="searchForm" onsubmit ="return validate()">
			<input type="text" name="content" [#if searchkeyword??] value="${searchkeyword}"[/#if] id="searchcontent"/>
			<button class="submit">搜索</button>
		</form>
	</div>
	<ul class="selectPart">
		<li id="all">
			<a href="javascript:void(0)" class="currentPart">全部</a>
		</li>
		<li id="finance">
			<a href="javascript:void(0)">天财评论</a>
		</li>
		<li id="startup">
			<a href="javascript:void(0)">天才创业</a>
		</li>
		<li id="ai">
			<a href="javascript:void(0)">天才AI</a>
		</li>
		<li id="career">
			<a href="javascript:void(0)">天才职场</a>
		</li>
	</ul>
	<div class="containerBox choseTab">
		<div class="searchDetail">
			<div class="detailLeft">
				<!-- 全部搜索结果部分 -->
				<ul class="curResult">
				</ul>
				<div class="load hd">下拉加载更多~</div>
				<div class="loading hd">正在加载中~</div>
				<div class='IsEmpty hd'>您搜索的内容暂未找到~</div>
				<div class="nomore hd">
					<i class="line"></i>
						我是有底线的
					<i class="line"></i>
				</div>
			</div>
			<div class="detailRight">
				<div class="hotSearch">
					<h3><img src="${baseimg}/imgs/default/hot.png" />热搜词</h3>
					<ul class="searchitem">
						[#if list??]
							[#list list as item]
								<li>
									<a href="${base}/search?content=${item.content}&uid=${item.uid}">${item.content}</a></li>
								</li>
							[/#list]
						[/#if]	
					</ul>
				</div>
				<div class="hotSearch">
					<h3><img src="${baseimg}/imgs/default/hot.png" />热门推荐</h3>
					<div class="recommend">
						[#if ADContent??]
							[#list ADContent as content]
								<a href="${content.originurl}" class="member">
									<img src="${webimg}${content.content}" />
								</a>
							[/#list]
						[/#if]
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="${basejs}/js/libs/scroll.js"></script>
	<script type="text/javascript" src="${basejs}/js/libs/search-1.0.0.js"></script>
	<script type="text/javascript" src="${basejs}/js/libs3/handlebars-4.0.5.min.js"></script>
	<script id="tmplArticles" type="text/x-handlebars-template">
		{{#each docs}}
			<li class="keywordHighlight">
				<a class="resultTitle" href="{{href}}">
					{{{title}}}
				</a>
				<p class="introduce">{{{summary}}}</p>
			</li>
		{{/each}}
	</script>
	<script type="text/javascript">
	<!--
		var baseurl='${baseurl}';
		var financebase = '${financebase}';
		var startupbase = '${startupbase}';
		var aibase = '${aibase}';
		var careerbase = '${careerbase}';
		var searchid;
		var prepage=1;
		var PageData = {
			start : 0,
			pagesize : 20,
			pagenum : 1,
			table : "${channel!' * '}",
			keyword : "${searchkeyword!''}",
			searchword: "${searchkeyword!''}",
		}
		var client = SearchClient("${SearchUrl}");
		$(function($){
			[#if channel??]
			var channel = '${channel}';
			if(channel != '' && channel != null){
				$("#"+channel).children().addClass("currentPart");
				$("#"+channel).siblings().children().removeClass("currentPart");
			}
			[#else]
			$("#all").children().addClass("currentPart");
			$("#all").siblings().children().removeClass("currentPart");
			[/#if]
			$(".selectPart li").click(function(){
				searchid = $(this).attr("id");
				var keyword=encodeURI('${searchkeyword}');
				document.location = "${base}/search?content="+keyword+"&channel="+searchid;
			});	
		})
		function validate(){
			if ($("#searchcontent").val().trim() == "") {
				alert("请输入内容");
				$("#searchcontent").focus();
				return false;
			}
			return true;
		}
	-->
	</script>
	<script type="text/javascript" src="${basejs}/js/pages/search.js"></script>
</body>
</html>