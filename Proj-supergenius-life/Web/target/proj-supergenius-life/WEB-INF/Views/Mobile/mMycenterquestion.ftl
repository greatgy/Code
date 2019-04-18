[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Public.ftl" as p]
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>${title!'天才人生'}</title>
	<link rel="stylesheet" href="${basecss}/css/mobile/default/index.css">
	<script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
	<script type="text/javascript" src="${basejs}/js/weixinshare.js"></script>
</head>
<body>
	<!-- 我的提问 -->
	<div class="myCollect">
		[#if problem??]
			<ul class="tabBox myTabbox">
				[#list problem as item]
					<li>
						<a href="${base}/problem/detail/${(item.cid)?c}/${(item.oid)?c}" class="articleTitle">
							${item.title}
							<span class="[#if item.ismember == 1 && (item.cid)?c == 16384][#if item.state == 0]comment2[#elseif item.state == 2]comment1[/#if][/#if]">[#if item.ismember == 1 && item.cid == 16384][#if item.state == 0]待点评[#elseif item.state == 2]已点评[/#if][/#if]</span>
						</a>
						<p class="descripe">[@p.TrimSubstring content="${item.content}" mylength=180 /]</p>
						<div class="pubTime">
							[@p.mytime datetime="${item.createtimeStr}"/]
						</div>
					</li>
				[/#list]
			</ul>
		[/#if]
	</div>
	<script>
		var pagenum=2;
		var prepage=1;
		function loadmorequestion() {
			var url = "${base}/m/ajax/my/mycenter/problem";
			if(prepage!= pagenum){
				prepage=pagenum;
				$.get(url, {pagenum:pagenum}, function(data){
				    pagenum += 1;
					if(data.trim() == ""){// 没有更多内容
						pagenum -= 1;
						$(window).unbind("scroll", defaultScrollHandler);
					}
					$(".usercollect").append(data);
				});
			}	
		}
		$(function(){
			[#if problem?size>0]
				$.fn.scrollHandle({
					obj: window,
					fun: loadmorequestion
				})
			[#else]
				$(".userquestion ").html("<div class='IsEmpty'>空空如也~</div>");
			[/#if]
		});
	</script>
</body>
</html>