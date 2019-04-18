[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Public.ftl" as p]
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>${title!'天才人生'}</title>
	<link rel="stylesheet" href="${basecss}/css/default/index.css">
	<script type="text/javascript" src="${basejs}/js/pages/scroll.js"></script>
	<script type="text/javascript" src="${basejs}/js/pages/history.js"></script>
</head>
<body>
	<div class="contentLeft">
		[#if bean??]${bean.content}[/#if]
		<!-- 轮播 -->
		<div id="lunbo" class="guideBox">
			<div class="banner">
				<ul id="banner_list">
					[#if list??]
						[#assign bannercount = 0]
						[#list list as item]	
							[#if bannercount <4]
								<li data-index="${bannercount}">
									<a href="${base}/article/${cid?c}/${(item.oid)?c}" class="banneritem" style="background: #000 url(${webimg}${item.imgbig}) no-repeat center center;background-size: 100% 100%;"  title="${item.title}">
										<p class="titlebottom">
											${item.title}
										</p>
									</a>
								</li>
							[/#if]
							[#assign bannercount = bannercount + 1]
						[/#list]
					[/#if]
				</ul>
			</div>
			<ul id="controller">
				[#if list??]
					[#assign bannercount = 0]
					[#list list as item]
						[#if bannercount<4]
							<li [#if bannercount == 0]class="active"[/#if]></li>
						[/#if]
						[#assign bannercount = bannercount + 1]
					[/#list]
				[/#if]
			</ul>
		</div>
		<ul class="tabBox">
			[#if list??]
				[#list list as article]
					<li>
						<a class="tabitemimg" href="${base}/article/${cid?c}/${(article.oid)?c}" title="${article.title}">
							<img src="${webimg}${article.imgmedium}" alt="${article.title}" title="${article.title}"/>
						</a>
						<div class="tabitemRt">
							<a href="${base}/article/${cid?c}/${(article.oid)?c}" class="articleTitle">
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
									<a href="${base}/article/${cid?c}/${(article.oid)?c}" class="littleicon">
										<img src="${baseimg}/imgs/default/clickcount.png" />
										${article.clickcount}
									</a>
									<a href="${base}/article/${cid?c}/${(article.oid)?c}" class="littleicon">
										<img src="${baseimg}/imgs/default/heart.png" />
										${article.prizecount}
									</a>
								</div>
							</div>
						</div>
					</li>
				[/#list]
			[/#if]
		</ul>
	</div>
	<div class="contentRight">
			[#if adList?? && adList?size>0]
				<div class="vdbox">
					[#list adList as item]
						<a href="${item.originurl}"><img src="${webimg}/${item.content}" alt="${item.name}" /></a>
					[/#list]
				</div>
			[/#if]
		<div class="workplaceitem">
			<h3>
				热门文章
			</h3>
			<ul class="hotArticles">
		        [#if hotArticleList??]
					[#assign i = 0]
					[#list hotArticleList as article]
						[#assign i = i+1 ]
						[#if i <= 3]
					        <li class="top1">
					        	[#if i == 1]
					        		<span class="hoticon redicon">TOP&nbsp;1</span>
					        	[#elseif i == 2]
					        		<span class="hoticon yellowicon">TOP&nbsp;2</span>
					        	[#elseif i == 3]
					        		<span class="hoticon greenicon">TOP&nbsp;3</span>
					        	[/#if]
					        	<a href="${base}/article/${cid?c}/${(article.oid)?c}" title="${article.title}">
					        		<img src="${webimg}${article.imgmedium}" alt="${article.title}" title="${article.title}" />
					        		<div class="description">
										[#if article.isoriginal == 1]
											<span class="original">[原创]</span>
										[/#if]
										[#if article.kind == 1]
											<span class="videotype">[视频]</span>
										[#elseif article.kind == 2]
											<span class="pictype">[图片]</span>
										[/#if]
										${article.title}
									</div>
					        	</a>
					        </li>
				        [#elseif i==4 || i== 5]
					        <li>
								<a href="${base}/article/${cid?c}/${(article.oid)?c}" title="${article.title}" > 
									<span class="numtag">${i}</span>
									<img class="fourimg" src="${webimg}${article.imgmedium}" alt="${article.title}" title="${article.title}" />
									<div class="title">
										[#if article.isoriginal == 1]
											<span class="original">[原创]</span>
										[/#if]
										[#if article.kind == 1]
											<span class="videotype">[视频]</span>
										[#elseif article.kind == 2]
											<span class="pictype">[图片]</span>
										[/#if]
										${article.title}
										<span class="flashtime">[@p.mytime datetime="${article.createtimeStr}"/]</span>
									</div>
								</a>
							</li>
				        [#else]
							<li>
								<a href="${base}/article/${cid?c}/${(article.oid)?c}">
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
								<span class="flashtime">[@p.mytime datetime="${article.createtimeStr}"/]</span>
							</li>
						[/#if]
					[/#list]
			    [/#if]
		    </ul>
		</div>
	</div>
	<script>
		$(function(){
			/*轮播*/
			var a = 1;
			$("#controller li").eq(0).addClass("active").siblings().removeClass("active");
			$("#banner_list li").eq(0).css("display","block").siblings().css("display","none");
			$("#controller li").click(function(){
				var i = $(this).index();
				a = i;
				$(this).addClass("active").siblings().removeClass("active");
				$("#banner_list li").eq(i).css("display","block").siblings().css("display","none");
			});
			setInterval(function(){
				if(a == $("#controller li").length){
					a = 0 ;
				}
				$("#controller li").eq(a).addClass("active").siblings().removeClass("active");
				$("#banner_list li").eq(a).css("display","block").siblings().css("display","none");
				a++;
			},2500);
			$("#index").addClass("current").siblings().removeClass("current");
			
		});
		
		$(function(){
			ajax_history('${base}/ajax/article/catalogue/${cid?c}', '${pagesize}', '.tabBox', 1, '${FirstLoadSize}');// 加载文章的url, 下拉加载时的加载size, 文章列表父级, pagenum, 首次加载size（去除轮播后的）
			$.fn.scrollHandle({
				obj: window,
				fun: loadmore
			})
		})
		
		var prePage = 2;
		var pagenum = 3;
		// 加载更多文章
		function loadmore() {
			var	url = "${base}/ajax/otherpage/catalogue/${cid?c}";
			if(prePage != pagenum) {
			    prePage = pagenum;
				$.get(url, {pagenum:pagenum}, function(data){
					if(data.trim() == ""){// 没有更多内容
						$(window).unbind("scroll", defaultScrollHandler);
						return false;
					}
					$(".tabBox").append(data);
					history_num = pagenum;
					pagenum+=1;
				});
			}
		}
	</script>
</body>
</html>