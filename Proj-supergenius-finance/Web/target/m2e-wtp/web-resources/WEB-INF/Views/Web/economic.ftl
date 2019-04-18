[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Public.ftl" as p]
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>${title!'天财评论'}</title>
	<meta name="Keywords" content="${keywords!'天财评论'}" />
	<meta name="Description" content="${description!'天财评论'}" />
	<!--# include file="/include/headcommon.html" -->
	<link rel="stylesheet" href="${basecss}/css/default/index.css">
	<script src="${basejs}/js/libs3/swiper.jquery.min.js"></script>
	<script type="text/javascript" src="${basejs}/js/libs/broadcast.js"></script>
	<script type="text/javascript" src="${basejs}/js/libs/index.js"></script>
	<script type="text/javascript" src="${basejs}/js/pages/scroll.js"></script>
	<script type="text/javascript" src="${basejs}/js/pages/history.js"></script>
</head>
<body>
	<div class="contentLeft">
		<!-- pc轮播 -->
		<div id="lunbo_pc">
			<div class="banner">
				<ul id="banner_list">
				[#if list??]
				[#assign bannercount = 1]
				[#list list as article]
					[#if bannercount <5]
						<li data-index="${bannercount}">
							<a href="${base}/article/${cid}/${(article.oid)?c}" class="banneritem" style="background: #000 url(${webimg}${article.imgbig}) no-repeat center center; background-size: 100% 100%;" title="${article.title}" target="_blank">
								<p class="titlebottom">
									${article.title}
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
				<li class=""></li>
				<li class="active"></li>
				<li class=""></li>
				<li class=""></li>
			</ul>
		</div>
		<!-- 轮播 -->
		<div id="lunbo" class="swiper-container">
			<ul id="banner_list" class="swiper-wrapper">
			[#if list??]
				[#assign bannercount = 0]
				[#list list as article]
					[#if bannercount <4]
						<li data-index="${bannercount}" class="swiper-slide">
							<a href="${base}/article/${cid}/${(article.oid)?c}" class="banneritem" style="background: #000 url(${webimg}${article.imgbig}) no-repeat center center; background-size: 100% 100%;" title="${article.title}" target="_blank">
								<p class="titlebottom">
									${article.title}
								</p>
							</a>
						</li>
					[/#if]
				[#assign bannercount = bannercount + 1]
				[/#list]
			[/#if]
			</ul>
			<div class="swiper-pagination"></div>
		</div>
		<div class="articleList">
			<ul class="tabBox"> 
				[#if list??]
					[#assign i = 1]
					[#list list as article]
						[#if i>4]
							<li>
								<a class="tabitemimg" href="${base}/article/${cid}/${(article.oid)?c}" title="${article.title}">
									<img src="${webimg}${article.imgmedium}" alt="${article.title}" title="${article.title}" />
								</a>
								<div class="tabitemRt">
									<a href="${base}/article/${cid}/${(article.oid)?c}" class="articleTitle">
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
											<a href="${base}/article/${cid}/${(article.oid)?c}" class="littleicon">
												<img src="${baseimg}/imgs/default/clickcount.png" />
												${article.clickcount}
											</a>
											<a href="${base}/article/${cid}/${(article.oid)?c}" class="littleicon">
												<img src="${baseimg}/imgs/default/discuss.png" />
												${article.commentscount}
											</a>
											<a href="${base}/article/${cid}/${(article.oid)?c}" class="littleicon">
												<img src="${baseimg}/imgs/default/heart.png" />
												${article.prizecount}
											</a>
										</div>
									</div>
								</div>
							</li>
						[/#if]
						[#assign i = i + 1]
					[/#list]
				[/#if] 
			</ul>
			<div class="load hd">下拉加载更多~</div>
			<div class="nomore hd">
				<i class="line"></i>
				我是有底线的
				<i class="line"></i>
			</div>
		</div>
	</div>
	<div class="contentRight">
		<div class="rightItem itemBlock">
			<h4><i></i>热门搜索</h4>
			<ul class="tags">
				[#if lablelist??]
					[#list lablelist as label]
		        		<li><a href="${base}/search?keyword=${label.content}&uid=${label.uid}">${label.content}</a></li>
		        	[/#list]
		     	[/#if]
		    </ul>
		</div>
		<div class="rightItem itemBlock">
			<h4><i></i>热门文章</h4>
			<ul class="hotArticle">
				[#if HotArticleList??]
		       [#-- 前三条热门文章大图显示 --]
		       	[#assign hotsize = 1]
		       		[#list HotArticleList as article]
		       			[#if hotsize gte 1  && hotsize lte 3] 
					        <li class="top1">
					        	[#if hotsize == 1]
					        		<span class="hoticon redicon">TOP&nbsp;${hotsize}</span>
					        	[#elseif hotsize == 2]
					        		<span class="hoticon yellowicon">TOP&nbsp;${hotsize}</span>
					        	[#elseif hotsize == 3]
					        		<span class="hoticon greenicon">TOP&nbsp;${hotsize}</span>
					        	[/#if]
					        	<a href="${base}/article/${cid}/${(article.oid)?c}" title="${article.title}">
					        		<img src="${webimg}/${article.imgbig}" alt="${article.title}" title="${article.title}" />
					        		<div class="description">
										${article.title}
									</div>
					        	</a>
					        </li>
		        		[#elseif hotsize lte 5]
			      		  [#-- 第4,5条热门文章 小图显示--]
					        <li>
								<a href="${base}/article/${cid}/${(article.oid)?c}">
									[#if hotsize == 4]
										<span class="numtag four">${hotsize}</span>
									[#elseif hotsize == 5]
										<span class="numtag five">${hotsize}</span>
									[/#if]
									
									<img class="fourimg" src="${webimg}/${article.imglittle}" alt="${article.title}" title="${article.title}" />
									<div class="title">
										${article.title}
										<span class="flashtime">
										[@p.mytime datetime="${article.createtimeStr}"/]
										</span>
									</div>
								</a>
							</li>
						[#elseif hotsize gte 6 && hotsize lte article?size]
						[#-- 后五条文章 --]
							<li>
								<a href="${base}/article/${cid}/${(article.oid)?c}">
								
									[#if article.kind == 2]
										<span class="pictype">[图片]</span>
									[/#if]
									[#if article.isoriginal == 1]
										<span class="original">[原创]</span>
									[/#if]
									${article.title}
								</a>
								<span class="flashtime">
								[@p.mytime datetime="${article.createtimeStr}"/]
								</span>
							</li>
						[/#if]
					[#assign hotsize = hotsize + 1]
					[/#list]
				[/#if]
		    </ul>
		</div>
	</div>
	<script>
		$(function(){
			[#if !phone??]
				ajax_history('${base}/ajax/article/catalogue/${cid}', '${pagesize}', '.tabBox', 5, '${FirstLoadSize}');// 加载文章的url, 下拉加载时的加载size, 文章列表父级, pagenum, 首次加载size（去除轮播后的）
			[/#if]
			
			var swiper = new Swiper('.swiper-container', {
		        pagination: '.swiper-pagination',
		        paginationClickable: true,
		        spaceBetween: 30,
		        centeredSlides: true,
		        autoplay: 2500,
		        autoplayDisableOnInteraction: false
		    });
		    $.fn.scrollHandle({
				obj: window,
				fun: loadmore
			})
			/*pc轮播*/
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
			/*判断是否有广告位内容*/
			[#if ADContent??]
				[#list ADContent as content]
					loadImg("${content.originurl}","${content.content}","${content.uid}",addImg);
				[/#list]
			[/#if]
		});
			var prePage = 1;
			var pagenum = 3;
				
			// 加载更多事件处理
			function loadmore() {
				var	url = "${base}/ajax/otherpage/catalogue/${cid}";
				if(prePage != pagenum) {
				    prePage = pagenum;
					$.get(url, {pagenum:pagenum}, function(data){
						if(data.trim() == ""){// 没有更多内容
							$(this).hide();
							$(".nomore").show();
							$(".load").hide();
							$(window).unbind("scroll", defaultScrollHandler);
							return false;
						}else{
							$(".load").show();
						}
						$(".tabBox").append(data);
						history_num = pagenum;
						pagenum+=1;
					});
				}
			}
			function loadImg(originurl,content,uid,callback){
			    var img = new Image();
			    img.onload = function(){
			      img.onload = null;
			      callback(img,originurl,uid);
			    }
			    img.src="${webimg}"+content;
			    img.width ="326";
			    img.height = "195";
			    if(navigator.userAgent.indexOf("iPad") != -1){  
		            img.width ="193";
			    	img.height = "126";
				}
	 	 }
		 	 
	    function addImg(img, original,uid){
	    	var html='';
		    html += '<div class="rightItem">';
		    [#if cid == 32]
		   		 html += '<span  id="'+uid+'" >';
		   		   html += '</span>';
		    [#else]  
		   		 html += '<a class="huiyuan" id="'+uid+'" href="' + original + '">';
	  		     html += '</a>';
		    [/#if]
			html += '</div>';
		    $(html).prependTo($(".contentRight"));
		    $(img).appendTo($("#"+uid));
	    }
	</script>
</body>
</html>