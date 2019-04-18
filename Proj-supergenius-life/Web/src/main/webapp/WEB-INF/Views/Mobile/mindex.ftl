[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Public.ftl" as p]
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>${title!'天才人生'}</title>
	<meta name="Keywords" content="天才人生,首页" />
	<meta name="Description" content="天才人生,首页" />
	<!--# include file="/include/headcommon.html" -->
	<link rel="stylesheet" href="${basecss}/css/swiper.min.css">
	<link rel="stylesheet" href="${basecss}/css/mobile/default/index.css">
	<script type="text/javascript" src="${basejs}/js/pages/scroll.js"></script>
	<script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
	<script type="text/javascript" src="${basejs}/js/weixinshare.js"></script>
	<style type="text/css">
	.operateBox {
		width: 55%;
	}
	.princeimg {
		top: -9px;
		right: 21px;
	}
	.talentDetail p img {
		position: relative;
	}
	</style>
</head>
<body>
	<div id="lunbo" class="lunboindex swiper-container swiper-container-horizontal">
		<ul id="banner_list" class="swiper-wrapper">
			[#if bannerList??]
				[#assign bannercount = 0]
				[#list bannerList as item]
					<li data-index="${bannercount}" class="swiper-slide">
						<a href="${item.url}" class="banneritem banneritem2" target="_blank" style="background: #000 url(${webimg}${item.content}) no-repeat center center;background-size: 100% 100%;"  title="${item.title}">
						</a>
					</li>
					[#assign bannercount = bannercount + 1]
				[/#list]
			[/#if]
		</ul>
		<div class="swiper-pagination swiper-pagination-clickable swiper-pagination-bullets"></div>
	</div>
	
	<div class="mainBox">
		<div class="workplaceitem">
			<h3>
				话题区
				[#if topicList?size>1]<a class="change" href="javascript:;" onclick="loadtopic();" id="changetopic">换一换<img src="${baseimg}/imgs/default/refresh.png" alt="换一批" title="换一批"></a>[/#if]
			</h3>
			<ul class="hotArticle">
				[#if topicList??]
					[#list topicList as topic]
						<li>
							<a href="${base}/m/topic/${(topic.cid)?c}/${topic.oid}" title="${topic.title}">
								<img src="${webimg}/${topic.imgmedium}" alt="${topic.title}" title="${topic.title}">
								<div class="description">
									${topic.title}
								</div>
							</a>
						</li>
					[/#list]
				[/#if]
			</ul>
		</div>
		<div class="workplaceitem">
			<h3>
				问答区
				[#if problemList?size>3]<a class="change" href="javascript:;" onclick="loadproblem();" id="changeproblem">换一换<img src="${baseimg}/imgs/default/refresh.png" alt="换一批" title="换一批"></a>[/#if]
			</h3>
			<ul class="askList">
				[#if problemList??]
					[#list problemList as item]
						<li>
							<a href="${base}/m/problem/detail/${(item.cid)?c}/${(item.oid)?c}">
								<span>${item.title}</span>
								<p>[@p.TrimSubstring content="${item.content}" mylength=200 /]</p>
							</a>
						</li>
					[/#list]
				[/#if]
			</ul>
		</div>
		<div class="workplaceitem">
			<h3>
				才艺展示
				[#if videoList?size>1]<a class="change" href="javascript:;" onclick="loadvideo();" id="changevideo">换一换<img src="${baseimg}/imgs/default/refresh.png" alt="换一批" title="换一批"></a>[/#if]
			</h3>
			<ul class="talentList">
				[#if videoList??]
					[#list videoList as item]
						<li>
							<a href="${base}/m/videoDetail/${(item.cid)?c}/${item.uid}">
								<img class="talentimg" src="${webimg}/${item.imgmedium}" alt="${item.labelName}" title="${item.labelName}" />
								<div class="play">
									<img src="${baseimg}/imgs/default/playbtn.png" alt="播放" title="播放" />
								</div>
							</a>
							<div class="talentDetail">
								<span class="talentTitle"><i class="type">${item.labelName}</i>${item.title}</span>
								<span class="looktimes">
									<img src="${baseimg}/imgs/default/audience.png" alt="" />
									${item.clickCount}人观看
								</span>
								<p>
									<img src="${item.user.useravatar}" [#if item.user.getIsUser()]class="memberUserimg"[/#if] alt="${item.user.username}" title="${item.user.username}" />
									[#if item.user.getIsUser()]<img src="${baseimg}/imgs/default/prince.png" class="princeimg" alt="" />[/#if]
									[@p.PrivateString content="${item.user.username}" /]
									<span class="time">上传于<i>${(item.createtime).toString("yyyy-MM-dd ")}</i></span>
								</p>
							</div>
						</li>
					[/#list]
				[/#if]
			</ul>
		</div>
		<div class="vdbox">
			[#if contentList??]
				[#list contentList as item]
					<a href="${item.originurl}"><img src="${webimg}/${item.content}" alt="${item.name}" /></a>
				[/#list]
			[/#if]
		</div>	
		<ul class="tabBox indexTablist">
			[#if list??]
				[#list list as article]
					[#if article.imgmedium??]
						<li>
							<a class="tabitemimg" href="${base}/m/article/${(article.firstCid)?c}/${(article.oid)?c}" title="${article.title}">
								<img src="${webimg}${article.imgmedium}" alt="${article.title}" title="${article.title}"/>
							</a>
							<div class="tabitemRt">
								<a href="${base}/m/article/${(article.firstCid)?c}/${(article.oid)?c}" class="articleTitle">
									[#if article.isoriginal == 1]
										<span class="original">[原创]</span>
									[/#if]
									[#if article.kind == 1]
										<span class="videotype">[视频]</span>
									[#elseif article.kind == 2]
										<span class="pictype">[图片]</span>
									[/#if]
									${article.title}
								</a>
								<p class="descripe">
									[#if article.summary==""]
										[@p.TrimSubstring content="${article.content}" mylength=80 /]
									[#else]
										[@p.TrimSubstring content="${article.summary}" mylength=80 /]
									[/#if]
								</p>
								<div class="timepiece">
									<span class="time">
										[@p.mytime datetime="${article.createtimeStr}"/]
									</span>
									<div class="operateBox">
										<a href="${base}/m/article/${(article.firstCid)?c}/${(article.oid)?c}" class="littleicon">
											<img src="${baseimg}/imgs/default/clickcount.png" />
											${article.clickcount}
										</a>
										<a href="${base}/m/article/${(article.firstCid)?c}/${(article.oid)?c}" class="littleicon">
											<img src="${baseimg}/imgs/default/heart.png" />
											${article.prizecount}
										</a>
									</div>
								</div>
							</div>
						</li>
					[#else]
						<li>
							<div class="reciveLi">
								<a href="${base}/m/article/${(article.firstCid)?c}/${(article.oid)?c}" class="articleTitle">
									${article.title}
								</a>
								<p class="descripe">
									[#if article.summary==""]
										[@p.TrimSubstring content="${article.content}" mylength=80 /]
									[#else]
										[@p.TrimSubstring content="${article.summary}" mylength=80 /]
									[/#if]
								</p>
								<div class="timepiece">
									<span class="time">
										[@p.mytime datetime="${article.createtimeStr}"/]
									</span>
									<div class="operateBox">
										<a href="${base}/m/article/${(article.firstCid)?c}/${(article.oid)?c}" class="littleicon">
											<img src="${baseimg}/imgs/default/clickcount.png" />
											${article.clickcount}
										</a>
										<a href="${base}/m/article/${(article.firstCid)?c}/${(article.oid)?c}" class="littleicon">
											<img src="${baseimg}/imgs/default/heart.png" />
											${article.prizecount}
										</a>
									</div>
								</div>
							</div>
						</li>
					[/#if]	
				[/#list]
			[/#if]
		</ul>	
	</div>
	
	<script>
	var prePage = 2;
		var pagenum = 3;
		var topicnum = 2;
		var problemnum = 2;
		var videonum = 2;
		
		// 加载更多文章
		function loadmore() {
			var	url = "${base}/m/ajax/first/article";
			if(prePage != pagenum) {
				console.log("pagenum=============="+pagenum);
			    prePage = pagenum;
				$.get(url, {pagenum:pagenum}, function(data){
					if(data.trim() == ""){// 没有更多内容
						$(window).unbind("scroll", defaultScrollHandler);
						return false;
					}
					$(".indexTablist").append(data);
					history_num = pagenum;
					pagenum+=1;
				});
			}
		}
		
		// 话题换一换
		function loadtopic() {
			var	url = "${base}/m/ajax/first/topic";
			$.get(url, {pagenum:topicnum}, function(data){
				if(data.trim() == ""){// 没有更多内容
					topicnum=1;
					$("#changetopic").click();
					return false;
				}
				$(".hotArticle").html(data);
				topicnum+=1;
			});
		}
		
		// 问答换一换
		function loadproblem() {
			var	url = "${base}/m/ajax/first/problems";
			$.get(url, {pagenum:problemnum}, function(data){
				if(data.trim() == ""){// 没有更多内容
					problemnum=1;
					$("#changeproblem").click();
					return false;
				}
				$(".askList").html(data);
				problemnum+=1;
			});
		}
		
		// 视频换一换
		function loadvideo() {
			var	url = "${base}/m/ajax/first/video";
			$.get(url, {pagenum:videonum}, function(data){
				if(data.trim() == ""){// 没有更多内容
					videonum=1;
					$("#changevideo").click();
					return false;
				}
				$(".talentList").html(data);
				videonum+=1;
			});
		}
	
		$(function(){
			$.fn.scrollHandle({
			obj: window,
			fun: loadmore
			})
			/*轮播*/
			var swiper = new Swiper('.swiper-container', {
		        pagination: '.swiper-pagination',
		        paginationClickable: true,
		        spaceBetween: 30,
		        centeredSlides: true,
		        autoplay: 2500,
		        autoplayDisableOnInteraction: false
		    });
		});
		
	</script>
</body>
</html>