[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Public.ftl" as p]
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>${title!'天才人生——资料交流'}</title>
	<meta name="Keywords" content="天才人生，资料交流" />
	<meta name="Description" content="天才人生，资料交流" />
	<link rel="stylesheet" href="${basecss}/css/default/index.css">
	<script type="text/javascript" src="${basejs}/js/pages/scroll.js"></script>
</head>
<body>
	<img src="${webimg}${photo.content}" alt="${photo.title}" title="${photo.title}" class="memberQuestion" />
	<div class="questionBox1">
		<h4 class="exclusiveTag">
			<span><i class="specialNmuber">${cataloguename?substring(0,1)}</i>${cataloguename?substring(1)}</span>
			[#if me??]
        		<a class="pubQuestion" href="${base}/addproblem/${cid?c}">+发布提问</a>
        	[#else]
        		<a href="javascript:;" class="pop-up pubQuestion">+发布提问</a>
        	[/#if]
		</h4>
		<ul class="quizList askQuestionList">
			[#if problemList??]
				[#list problemList as item]
					[#if item.user??]
						[#assign fromMan=item.user/]
					[#elseif item.visitor??]
						[#assign fromMan=item.visitor/]
					[/#if]
					<li>
						<img src="${fromMan.useravatar}" class="memberUserimg" alt="${item.user.username}" title="${fromMan.username}" />
						<img src="${baseimg}/imgs/default/prince.png" class="princeimg3" alt="" />
						[#if me??]
		            		<a class=" quizRt" href="${base}/problem/detail/${(item.cid)?c}/${(item.oid)?c}"  target="_blank">
		            	[#else]
		            		<a class=" quizRt" href="javascript:;" class="pop-up">
		            	[/#if]
						<div class="col-xs-8 col-sm-9">
							<h4>${item.title}[#if item.state==2]<span class="answerStatus">专家已解答</span>[/#if]</h4>
							<p>
							[#if item.content==""]
							[#else]
								[@p.TrimSubstring content="${item.content}" mylength=50 /]
							[/#if]
							</p>
						</div>
						<div class="col-xs-4 col-sm-3">
							<div class="readitem col-sm-6 col-xs-6">
								阅读
								<span class="readNum">${item.clickcount}</span>	
							</div>
							<div class="readitem col-sm-6 col-xs-6">
								回答
								<span class="readNum">${item.commentscount}</span>	
							</div>
						</div>
						</a>
					</li>
				[/#list]
			[/#if]
		</ul>
	</div>
	<script>
		$(function(){
			$.fn.scrollHandle({
				obj: window,
				fun: loadmore
			})
		})
		
		var prePage = 2;
		var pagenum = 3;
		// 加载更多文章
		function loadmore() {
			var	url = "${base}/ajax/first/problem";
			if(prePage != pagenum) {
			    prePage = pagenum;
				$.get(url, {pagenum:pagenum, cid:${cid?c}, ismember:1}, function(data){
					if(data.trim() == ""){// 没有更多内容
						$(window).unbind("scroll", defaultScrollHandler);
						return false;
					}
					$(".quizList").append(data);
					pagenum+=1;
				});
			}
		}
	</script>
</body>
</html>