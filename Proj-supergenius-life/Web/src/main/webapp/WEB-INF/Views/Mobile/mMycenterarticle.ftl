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
	<!-- 我的文章 -->
	<div class="myCollect">
		<ul class="artTab">
			<li class="curart" id="published">
				已发布
			</li>
		</ul>
		[#if article??]
			<ul class="tabBox myTabbox">
				[#list article as item]
					[#if item.imgmedium??]
						<li>
							<a class="tabitemimg" href="${base}/m/article/${(item.firstCid)?c}/${(item.oid)?c}">
								<img src="${webimg}${item.imgmedium}"  alt="${item.title}" title="${item.title}"/>
							</a>
							<div class="tabitemRt">
								<a  href="${base}/m/article/${(item.firstCid)?c}/${(item.oid)?c}" class="articleTitle" >
									[#if item.kind == 1]
										<span class="videotype">【视频】</span>
									[#elseif item.kind == 2]
										<span class="pictype">【图片】</span>
									[/#if]
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
										<a href="${base}/m/article/${(item.firstCid)?c}/${(item.oid)?c}" class="littleicon">
											<img src="${baseimg}/imgs/default/clickcount.png">
											${item.clickcount}
										</a>
										<a href="${base}/m/article/${(item.firstCid)?c}/${(item.oid)?c}" class="littleicon">
											<img src="${baseimg}/imgs/default/heart.png" />
											${item.prizecount}
										</a>
										<a href="${base}/m/article/${(item.firstCid)?c}/${(item.oid)?c}" class="littleicon">
											<img src="${baseimg}/imgs/default/discuss.png" />
											${item.commentscount}
										</a>
									</div>
								</div>
							</div>
						</li>
					[#else]
						<li>
							<div class="tabitemRt">
								<a href="${base}/m/article/${(item.firstCid)?c}/${(item.oid)?c}" class="articleTitle">
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
										<a href="${base}/m/article/${(item.firstCid)?c}/${(item.oid)?c}" class="littleicon">
											<img src="${baseimg}/imgs/default/clickcount.png">
											${item.clickcount}
										</a>
										<a href="${base}/m/article/${(item.firstCid)?c}/${(item.oid)?c}" class="littleicon">
											<img src="${baseimg}/imgs/default/heart.png" />
											${item.prizecount}
										</a>
										<a href="${base}/m/article/${(item.firstCid)?c}/${(item.oid)?c}" class="littleicon">
											<img src="${baseimg}/imgs/default/discuss.png" />
											${item.commentscount}
										</a>
									</div>
								</div>
							</div>
						</li>
					[/#if]
				[/#list]
			</ul>
		[/#if]
	</div>
	
	<script>
	var pagenum=2;
	var prepage=1;
	var id;
	function loadmorearticle() {
		var url = "${base}/m/ajax/my/article/"+id;
		if(prepage!= pagenum){
			prepage=pagenum;
			$.get(url, {pagenum:pagenum}, function(data){
			    pagenum += 1;
				if(data.trim() == ""){// 没有更多内容
					$(window).unbind("scroll", defaultScrollHandler);
				}
				$("#article"+id).append(data);
			});
		}
	}
	$(function(){
		[#-- 文章 --]
		[#if article?size>0]
		$.fn.scrollHandle({
			obj: window,
			fun: loadmorearticle
		});
		[#else]
			$(".userarticle ").html("<div class='IsEmpty'>空空如也~</div>");
		[/#if]
	});
	</script>
</body>
</html>