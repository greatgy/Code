[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Public.ftl" as p]
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>${title!'天财评论-他的主页'}</title>
	<meta name="Keywords" content="天财评论,他的主页" />
	<meta name="Description" content="天财评论,他的主页" />
	<link rel="stylesheet" href="${basecss}/css/default/index.css">
	<script src="${basejs}/js/libs3/swiper.jquery.min.js"></script>
	<script type="text/javascript" src="${basejs}/js/pages/scroll.js"></script>
	<link rel="stylesheet" href="${basecss}/css/default/detail.css">
	<script type="text/javascript">
	    var me = {
			showname: "${me.showname!'游客'}",
			imglittle: "${me.avatar}",
			oid:"${(me.oid?c)!'0'}",
			uid:"${me.uid}",
			defaultImg: "${baseimg}/imgs/default/user.png"
		}
	</script>
</head>
<body>
		<div class="contentLeft">
			<div class="articleList">
				<ul class="tabBox">
					[#list list as item]
						<li>
							<a class="tabitemimg" href="${base}/article/${item.firstCid}/${(item.oid)?c}" title="${item.title}">
								<img src="${webimg}${item.imgmedium}" alt="${item.title}" title="${item.title}" />
							</a>
							<div class="tabitemRt">
								<a href="${base}/article/${item.firstCid}/${(item.oid)?c}" class="articleTitle">
									[#if item.kind == 1]
										<span class="videotype">[视频]</span>
									[#elseif item.kind == 2]
										<span class="pictype">[图片]</span>
									[/#if]
									${item.title}
								</a>
								<p class="descripe">${item.summary}</p>
								<div class="timepiece">
									<span class="time">[@p.mytime datetime="${item.createtimeStr}"/]</span>
									<div class="operateBox">
										<a href="${base}/article/${item.firstCid}/${(item.oid)?c}" class="littleicon">
											<img src="${baseimg}/imgs/default/heart.png" />
											${item.prizecount}
										</a>
										<a href="${base}/article/${item.firstCid}/${(item.oid)?c}" class="littleicon">
											<img src="${baseimg}/imgs/default/discuss.png" />
											${item.commentscount}
										</a>
										<a href="${base}/article/${item.firstCid}/${(item.oid)?c}" class="littleicon">
											<img src="${baseimg}/imgs/default/clickcount.png" />
											${item.clickcount}
										</a>
									</div>
								</div>
							</div>
						</li>
					[/#list]
				</ul>
				<div class="load hd">下拉加载更多~</div>
				<div class="nomore">
					<i class="line"></i>
					我是有底线的
					<i class="line"></i>
				</div>
			</div>
		</div>
		<div class="contentRight">
			<!-- 登录后 -->
			<div class="authorbox">
				<div class="author">
					<div class="authorPiece">
						<a href="javascript:;">
							<img class="authorimg [#if author.getIsUser()] userborder[/#if]" [#if author.useravatar??]src="${author.useravatar}"[#else]src="${baseimg}/imgs/default/author.png"[/#if]/>
							[#if author.getIsUser()]<img src="${baseimg}/imgs/default/prince.png" class="princeimg" alt="" />[/#if]
						</a>
						<div class="resAuthor">						
							<a href="javascript:;" class="authorName">${author.username}</a>
							<span>文章<a href="javascript:;">${count}</a></span>
						</div>
					</div>
					[#if me??]
							[#if me.uid != author.uid]
							<div [#if isSubscribe == true] class="addsub hd" [#else] class="addsub " [/#if]>
								<a href="javascript:;" onclick="subscribeHandler('${author.uid}');">+</a>
								订阅
							</div>
							<div [#if isSubscribe == true] class="cancelsub" [#else] class="cancelsub hd" [/#if]>
								<a href="javascript:;" onclick="cancelsubscribeHandler('${author.uid}');">-</a>
								取消订阅
							</div>
							[/#if]
						[#else]
							[#if me.uid != author.uid]
							<div [#if isSubscribe == true] class="addsub hd" [#else] class="addsub pop-up" [/#if]>
								<a href="javascript:;" onclick="subscribeHandler('${author.uid}');">+</a>
								订阅
							</div>
							[/#if]
						[/#if]
					<p>${author.summary}</p>
					<div class="totalArt">共${count}篇文章</div>
				</div>
			</div>
			<div class="rightItem">
				<h4><i></i>他的热门</h4>
				<ul class="hotArticle">
			    [#if hotarticles??]
					[#assign i = 0]
					[#list hotarticles as article]
					[#assign i = i+1 ]
					[#if i <= 3]
				        <li class="top1">
				        	[#if i == 1]
				        		<span class="hoticon redicon">TOP1</span>
				        	[#elseif i == 2]
				        		<span class="hoticon yellowicon">TOP2</span>
				        	[#elseif i == 3]
				        		<span class="hoticon greenicon">TOP3</span>
				        	[/#if]
				        	<a href="${base}/article/${article.firstCid}/${(article.oid)?c}" title="${article.title}">
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
							<a href="${base}/article/${article.firstCid}/${(article.oid)?c}">
								[#if i==4]
								<span class="numtag four">${i}</span>
								[#else]
								<span class="numtag five">${i}</span>
								[/#if]
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
							<a href="${base}/article/${article.firstCid}/${(article.oid)?c}">
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
	</div>
	<script>
		$(function(){
			
		    $.fn.scrollHandle({
				obj: window,
				fun: loadmore
			})
			
			
		});
		
		$(function(){
			$("#catalogue_0").removeClass("current");
		});
			var prePage = 1;
			var pagenum = 1;
				
			// 加载更多事件处理
			function loadmore() {
				pagenum = pagenum +　1;
				var	url = "${base}/ajax/his/article/${author.uid}";
				if(prePage != pagenum) {
				    prePage = pagenum;
					$.get(url, {pagenum:pagenum}, function(data){
					    pagenum += 1;
						if(data.trim() == ""){// 没有更多内容
							pagenum -= 1;
							$(this).hide();
							$(".nomore").show();
							$(window).unbind("scroll", defaultScrollHandler);
							return false;
						}
						$(".tabBox").append(data);
					});
				}
			}
			
			function subscribeHandler(useruid) {
			$.ajax({
					type:"GET",
					url:"${base}/ajax/subscribe/"+useruid,
					success:function(data){
						if(data.result == true) {
							$(".addsub").addClass("hd");
							$(".cancelsub").removeClass("hd");
						}
					}
				});
		}
		
		function cancelsubscribeHandler(useruid) {
			$.ajax({
					type:"GET",
					url:"${base}/ajax/cancelsubscribe/"+useruid,
					success:function(data){
						if(data.result == true) {
							$(".cancelsub").addClass("hd");
							$(".addsub").removeClass("hd");
						}
					}
				});
		}
			
	</script>
</body>
</html>