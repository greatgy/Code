[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Public.ftl" as p]
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>${title!'天才人生'}</title>
	<link rel="stylesheet" href="${basecss}/css/mobile/default/index.css">
		<script type="text/javascript" src="${basejs}/js/pages/scroll.js"></script>
	<link rel="stylesheet" href="${basecss}/css/mobile/pagination.css">
		<script type="text/javascript" src="${basejs}/js/pages/history.js"></script>
	<script type="text/javascript" src="${basejs}/js/mobile/jquery.pagination.js"></script>
	<script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
	<script type="text/javascript" src="${basejs}/js/weixinshare.js"></script>
	<style>
	
	
	</style>
</head>
<body>
	<div class="tabBox">
		<ul class="tabList">
			<li id="newarticle" onclick="newsArticles(this);" class="curTab col-sm-6 col-xs-6 curjudge" data-index="0">最新文章</li>
			<li id="hotarticle" onclick="hotsArticles(this);" class="col-sm-6 col-xs-6" data-index="1">热门文章</li>
		</ul>
		[#if bean??]${bean.content}[/#if]
		<div id="lunzhuan" class="swiper-container  swiper-container-h swiper-container-horizontal swiper-container-autoheight">
			<div class="swiper-wrapper">
				<div class="swiper-slide one">
						<!-- 轮播 -->
						<div id="lunbo" class="lunboindex swiper-container swiper-container-horizontal">
							<ul id="banner_list" class="swiper-wrapper">
								[#if list??]
									[#assign bannercount = 0]
									[#list list as item]	
										[#if bannercount <4]
											<li data-index="${bannercount}" class="swiper-slide">
												<a href="${base}/article/${cid?c}/${(item.oid)?c}" class="banneritem" style="background: #000 url(${webimg}${item.imgbig}) no-repeat center center;background-size: 100% 100%;" title="${item.title}">
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
					<ul class="tabBoxplace" style="margin-top:30px;">
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
				<div class="swiper-slide two">
					<ul class="hotArticles">
						 [#if hotArticleList??]
						[#list hotArticleList as article]
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
			</div>
		</div>
	</div>

	
	
	
	<script>
		$(function(){
			/*轮播*/
            /*轮播*/
            var swiper1 = new Swiper('#lunbo', {
                pagination: '.swiper-pagination',
                paginationClickable: true,
                spaceBetween: 30,
                centeredSlides: true,
                autoplay: 2500,
                autoplayDisableOnInteraction: false
            });
            var puzzSwiper = new Swiper('#lunzhuan', {
			    loop: false,
			    observer:true,//修改swiper自己或子元素时，自动初始化swiper  
				observeParents:true,
				autoplayDisableOnInteraction : false,
				onSlideChangeStart: function(swiper){
			      $(".tabList").children("li").each(function(){ 
			      	if($(this).data("index") == swiper.activeIndex){
			      		$(this).addClass("curTab").siblings().removeClass("curTab");
			      	}
			      	scrollForSlide(swiper.activeIndex);
			      });
			    }
		    });
		    
			$(".tabList li").click(function(){
				$(this).addClass("curTab").siblings().removeClass("curTab");
			});
			
			 $("#newarticle").click(function(){
				puzzSwiper.slideTo(0, 500, false);
				$(".two").css('height', '950px');
				$(".one").css('height', 'auto');
				scrollToNewArticle();
			});
			$("#hotarticle").click(function(){
				puzzSwiper.slideTo(1, 500, false);
				$(".one").css('height', '950px');
				$(".two").css('height', 'auto');
				scrollToHotArticle();
			});

		function scrollForSlide(index){
				if(index == 0){
				$(".two").css('height', '950px');
				$(".one").css('height', 'auto');
					scrollToNewArticle();
				}
				if(index == 1){
				$(".one").css('height', '950px');
				$(".two").css('height', 'auto');
					scrollToHotArticle();
				}
			}
			$.fn.scrollHandle({
				obj: window,
				fun: loadmore
			})
		
		var prePage = 2;
		var pagenum = 3;
		var prePage1 = 2;
		var pagenum1 = 3;
		function scrollToNewArticle(){
				
			window.scrollTo(0,0);
				$(window).unbind("scroll", defaultScrollHandler);
				pagenum = 1;
				prePage = 0;
				$.fn.scrollHandle({
					obj: window,
					fun: loadmore
				})
			};
		function scrollToHotArticle(){
			window.scrollTo(0,0);
			$(window).unbind("scroll", defaultScrollHandler);
			pagenum1 = 1;
			prePage1 = 0;
			$.fn.scrollHandle({
				obj: window,
				fun: loadhotarticle
			})
		};
		
		// 加载更多文章
		function loadmore() {
			var	url = "${base}/m/ajax/otherpage/catalogue/${cid?c}";
			if(prePage != pagenum) {
			    prePage = pagenum;
				$.get(url, {pagenum:pagenum}, function(data){
					if(data.trim() == ""){// 没有更多内容
						$(window).unbind("scroll", defaultScrollHandler);
						return false;
					}
					$(".tabBoxplace").append(data);
					pagenum+=1;
				});
			}
		}
		
		function loadhotarticle() {
			var	url = "${base}/m/ajax/hot/otherpage/catalogue/${cid?c}";
			if(prePage1 != pagenum1) {
			    prePage1 = pagenum1;
				$.get(url, {pagenum:pagenum1}, function(data){
					if(data.trim() == ""){// 没有更多内容
						$(window).unbind("scroll", defaultScrollHandler);
						return false;
					}
					$(".hotArticles").append(data);
					pagenum1 += 1;
				});
			}
		}
	});
		//获得最热话题
		function hotsArticles(obj){
			ishot = true;
			pagenum1 = 1;
			prePage1 = 0;
			$(obj).addClass("curjudge").siblings().removeClass("curjudge");
			var url = "${base}/m/ajax/hot/otherpage/catalogue/${cid?c}";
			var json = {};
			json.pagenum=pagenum1;
			$.get(url, json,function(data){
				if(data != ''){
					pagenum1 +=1;
					$(".hotArticles").html(data);
				}
			});
		}
		
		//获得最新话题
		function newsArticles(obj){
			ishot = false;
			pagenum = 1;
			prePage = 0;
			$(obj).addClass("curjudge").siblings().removeClass("curjudge");
			var url = "${base}/m/ajax/otherpage/catalogue/${cid?c}";
			$.get(url, {pagenum:pagenum},function(data){
				if(data != ''){
					pagenum +=1;
					console.log("newsArticles"+pagenum);
					$(".tabBoxplace").html(data);
				}
			});
		}
		

	</script>
</body>
</html>