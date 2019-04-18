[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Public.ftl" as p]
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>${title!'天才人生——资料交流'}</title>
	<link rel="stylesheet" href="${basecss}/css/mobile/default/index.css">
	<script type="text/javascript" src="${basejs}/js/pages/scroll.js"></script>
	<script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
	<script type="text/javascript" src="${basejs}/js/weixinshare.js"></script>
</head>
<body>
	<img src="${webimg}${photo.content}" alt="${photo.title}" title="${photo.title}" class="memberQuestion" />
	<div class="questionBox">
		<h4 class="exclusiveTag">
			<span><i class="specialNmuber">课</i>程推荐</span>
			[#if me??]
        		<a class="pubQuestion" href="${base}/m/addproblem/${cid?c}">+发布提问</a>
        	[#else]
        		<a href="${base}/m/login?cid=${cid?c}&pcid=${pcid}" class="pubQuestion">+发布提问</a>
        	[/#if]
		</h4>
		<ul class="quizList askQuestionList">
			[#if problemList??]
				[#list problemList as item]
					<li>
						<img src="${item.user.useravatar}" class="memberUserimg" alt="${item.user.username}" title="${item.user.username}" />
						<img src="${baseimg}/imgs/default/prince.png" class="princeimg3" alt="" />
						[#if me??]
		            		<a class="quizRt" href="${base}/m/problem/detail/${(item.cid)?c}/${(item.oid)?c}"  target="_blank">
		            	[#else]
		            		<a class="quizRt" href="${base}/m/login?cid=${cid?c}&pcid=${pcid}">
		            	[/#if]
						<div class="col-sm-9">
							<h4>${item.title}[#if item.state==2]<span class="answerStatus">专家已解答</span>[/#if]</h4>
							<p>
							[#if item.content !=""]
								[@p.TrimSubstring content="${item.content}" mylength=50 /]
							[/#if]
							</p>
						</div>
						<div class="col-sm-3">
							<div class="readitem">
								阅读
								<span class="readNum">${item.clickcount}</span>	
							</div>
							<div class="readitem">
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
			var	url = "${base}/m/ajax/first/problem";
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