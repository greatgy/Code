[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Public.ftl" as p]
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>${title!'天才人生'}</title>
	<link rel="stylesheet" href="${basecss}/css/mobile/default/index.css">
	<script type="text/javascript" src="${basejs}/js/pages/scroll.js"></script>
	<script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
	<script type="text/javascript" src="${basejs}/js/weixinshare.js"></script>
	<script src="${basejs}/js/libs3/swiper.jquery.min.js"></script>
	<style>
	.talentDetail .time{
		float:right;
	}
	</style>
</head>
<body>

	<div class="myCollect">
	<ul class="artTab">
		<li class="curart" id="testList">
			效果检验
		</li>
		<li id="stageList">我的舞台</li>
	</ul>
	[#list video?keys as key]
		[#assign videoList = video[key]]
		<ul class="talentList fruitsList uservideo hd" id="video${key}">
		[#assign i = 1]
		[#if videoList?size>0]
			[#list videoList as item]
				<li [#if i % 2 == 0]class="middl"[/#if]>
					<a href="${base}/m/videoDetail/${(item.cid)?c}/${item.uid}">
						<img class="talentimg" src="${webimg}${item.imgmedium}" alt="${item.title}" title="${item.title}">
						[#if item.type == 1]<div class="voice">音频</div>[/#if]
					</a>
					<div class="talentDetail">
						<span class="talentTitle"><i class="type">初一</i>语文</span>
						[#if me??]
							[#if me.getIsUser() && (item.cid)?c == 32768]
						<span class="comment [#if item.state == 2]commented[/#if]">
							[#if item.state == 2] 专家已点评[#elseif item.state == 0]未点评[/#if]
						</span>
							[/#if]
						[/#if]
						<span class="time">上传于<i>[@p.mytime datetime="${item.createtimeStr}"/]</i></span>
					</div>
				</li>
				[#assign i = i + 1]
			[/#list]
		[#else]
		<div class='IsEmpty'>空空如也~</div>
		[/#if]
	</ul>
	[/#list]
</div>
<script>
	var pagenum=2;
	var prepage=1;
	var videoid;
	function loadmorevideo() {
			var url = "${base}/m/ajax/my/mycenter/video/"+videoid;
			if(prepage!= pagenum){
				prepage=pagenum;
				$.get(url, {pagenum:pagenum}, function(data){
				    pagenum += 1;
					if(data.trim() == ""){// 没有更多内容
						$(window).unbind("scroll", defaultScrollHandler);
					}
					$("#video"+videoid).append(data);
				});
			}
		}
	$(function(){
		videoid = $(".artTab li:first").attr("id");
		$(".artTab li:first").addClass("curart");
		$("#video"+videoid).removeClass("hd");
		$(".artTab li").click(function(){
			videoid = $(this).attr("id");
			$(this).addClass("curart").siblings().removeClass("curart");
			$("#video"+videoid).removeClass("hd").siblings(".uservideo").addClass("hd");
		});	
		$.fn.scrollHandle({
			obj: window,
			fun: loadmorevideo
		})
	});
</script>
</body>
</html>