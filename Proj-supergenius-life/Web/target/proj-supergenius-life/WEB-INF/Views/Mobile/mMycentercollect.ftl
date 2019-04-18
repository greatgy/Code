[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Public.ftl" as p]
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>${title!'天才人生'}</title>
	<link rel="stylesheet" href="${basecss}/css/mobile/default/index.css">
	<script type="text/javascript" src="${basejs}/js/pages/scroll.js"></script>
	<script src="${basejs}/js/libs3/swiper.jquery.min.js"></script>
	<script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
	<script type="text/javascript" src="${basejs}/js/weixinshare.js"></script>
</head>
<body>
	<!-- 我的收藏 -->
	<div class="myCollect">
		[#if collect??]
		<ul class="tabBox myTabbox">
		[#list collect as item]
			<li>
				<a class="tabitemimg" href="${base}/m/article/${(item.cid)?c}/${(item.oid)?c}" title="${item.title}">
					<img src="${webimg}${item.imgmedium}" alt="${item.title}" title="${item.title}"/>
				</a>
				<div class="tabitemRt">
					<a href="${base}/m/article/${(item.cid)?c}/${(item.oid)?c}" class="articleTitle">
						[#--  <span class="videotype">【视频】</span>--]
						${item.title}
					</a>
					[#if item.summary??]
						<p class="descripe">[@p.TrimSubstring content="${item.summary}" mylength=80 /]</p>
					[#else]
						<p class="descripe">[@p.TrimSubstring content="${item.content}" mylength=80 /]</p>
					[/#if]
					<div class="timepiece">
						<span class="time">[@p.mytime datetime="${item.createtimeStr}"/]</span>
						<div class="operateBox">
							<a href="${base}/m/article/${(item.cid)?c}/${(item.oid)?c}" class="littleicon">
								<img src="${baseimg}/imgs/default/heart.png" />
								${item.prizecount}
							</a>
							<a href="${base}/m/article/${(item.cid)?c}/${(item.oid)?c}" class="littleicon">
								<img src="${baseimg}/imgs/default/discuss.png" />
								${item.commentscount}
							</a>
							<a href="${base}/m/article/${(item.cid)?c}/${(item.oid)?c}" class="littleicon">
								<img src="${baseimg}/imgs/default/clickcount.png" />
								${item.clickcount}
							</a>
						</div>
					</div>
				</div>
			</li>
			[/#list]
		</ul>
		[/#if]
	</div>
	<script>
		var pagenum=2;
		var prepage=1;
		function loadmorecollect() {
			var url = "${base}/m/ajax/my/collect";
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
			[#if collect?size>0]
				$.fn.scrollHandle({
					obj: window,
					fun: loadmorecollect
				})
			[#else]
				$(".usercollect ").html("<div class='IsEmpty'>空空如也~</div>");
			[/#if]
		});
	</script>
</body>
</html>