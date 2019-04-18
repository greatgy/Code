[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Public.ftl" as p]
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>${title!'天才人生'}</title>
	<link href="${basecss}/css//mobile/default/detail.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" href="${basecss}/css/mobile/default/index.css">
	<script type="text/javascript" src="${basejs}/js/pages/scroll.js"></script>
	<script src="${basejs}/js/libs3/swiper.jquery.min.js"></script>
	<script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
	<script type="text/javascript" src="${basejs}/js/weixinshare.js"></script>
</head>
<body>
	<!-- 我的话题 -->
	<div class="myCollect">
		<ul class="artTab">
			<li class="curart" id="publish">
				<a href="${base}/m/my/center/topic">已发布</a>
			</li>
			<li id="join" onclick="jointopic()">已参与</li>
		</ul>
		[#if topic??]
		<ul class="tabBox topicTabbox">
			[#if topic?size>0]
				[#list topic as item]
					<li>
						<a href="${base}/m/topic/review/${(item.cid)?c}/${item.oid}" class="articleTitle">
							${item.title}
						</a>
						<p class="descripe">[@p.TrimSubstring content="${item.content}" mylength=180 /]</p>
					</li>
				 [/#list]
			[/#if]
		</ul>
		[/#if]
	</div>
	<script>
	var pagenum=2;
	var prepage=1;
	var topicid;
	function loadmoretopic() {
			var url = "${base}/m/ajax/my/mycenter/topic";
			if(prepage!= pagenum){
				prepage=pagenum;
				$.get(url, {pagenum:pagenum}, function(data){
				    pagenum += 1;
					if(data.trim() == ""){// 没有更多内容
						$(window).unbind("scroll", defaultScrollHandler);
					}
					$(".topicTabbox").append(data);
				});
			}
		}
		function loadmoretopicjoin() {
			var url = "${base}/m/ajax/my/mycenter/topic/join";
			if(prepage!= pagenum){
				prepage=pagenum;
				$.get(url, {pagenum:pagenum}, function(data){
				    pagenum += 1;
					if(data.trim() == ""){// 没有更多内容
						$(window).unbind("scroll", defaultScrollHandler);
					}
					$(".topicTabbox").append(data);
				});
			}
		}
		function jointopic() {
			$(window).unbind("scroll", defaultScrollHandler);
			$.get("${base}/m/ajax/my/mycenter/joinTopic", {uid:"${me.uid}"}, function(data){
				$(".topicTabbox").html(data);
			});
			$.fn.scrollHandle({
				obj: window,
				fun: loadmoretopicjoin
			})
		}	
		$(function(){
			topicid = $(".artTab li:first").attr("id");
			$(".artTab li:first").addClass("curart");
			$(".artTab li").click(function(){
				topicid = $(this).attr("id");
				$(this).css("color","#fe6816").parent().siblings().css("color","#333");
				$(this).addClass("curart").siblings().removeClass("curart");
			});	
			$.fn.scrollHandle({
				obj: window,
				fun: loadmoretopic
			})
		});
	</script>
</body>
</html>