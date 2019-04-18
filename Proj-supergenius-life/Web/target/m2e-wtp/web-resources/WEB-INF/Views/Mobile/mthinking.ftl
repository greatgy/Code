[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Public.ftl" as p]
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>${title!'天才人生话题详情页'}</title>
	<link href="${basecss}/css/mobile/default/index.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" href="${basecss}/css/default/ckeditor.css" type="text/css">
	<script type="text/javascript" src="${basejs}/js/pages/scroll.js"></script>
	<script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
	<script type="text/javascript" src="${basejs}/js/weixinshare.js"></script>
</head>
<body>
	<ul class="judgement topicTab">
		<li id="reviewlist1" onclick="review(this)" class="curjudge" data-index="0">备选话题</li>
		<li id="oldTopicsList1" onclick="last(this)" data-index="1">往期话题</li>
	</ul>
	<div class="swiper-container">
  		<div class="swiper-wrapper">
			<div class="choseTopic swiper-slide one">
				[#if me??]
					<a href="${base}/m/my/topic/contribute?cid=${cid?c}"><img src="${baseimg}/imgs/mobile/default/topicbg.png"/></a>
				[#else]
					<a href="${base}/m/login?cid=${cid?c}&pcid=${pcid}" ><img src="${baseimg}/imgs/mobile/default/topicbg.png"/></a>
				[/#if]
				<ul class="hotArticleList reviewlist">
					[#if list??]
						[#assign i=1]
						[#list list as item]
							<li>
								<div class="topicRt">
									<a href="${base}/topic/review/${(item.cid)?c}/${item.oid}" title="${item.title}">
										${item.title}
									</a>
									<p>[@p.TrimSubstring content="${item.content}" mylength=60 /]</p>
									<div class="topicbottom">
										<span class="time">[@p.mytime datetime="${item.createtimeStr}"/]</span>
										<span class="topicnews"><img src="${baseimg}/imgs/default/dthumb.png" alt="">${item.prizecount}</span>
									</div>
								</div>
							</li>
							[#assign i=i+1]
						[/#list]
					[/#if]
				</ul>
			</div>
			<div class="oldsTopic swiper-slide two">
				<ul class="hotArticleList oldTopicsList">
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
			
			var mySwiper = new Swiper('.swiper-container', {
				loop: false,
			    observer:true,//修改swiper自己或子元素时，自动初始化swiper  
				observeParents:true,
				autoplayDisableOnInteraction : false,
				onSlideChangeStart: function(swiper){
			      $(".topicTab").children("li").each(function(){ 
			      	if($(this).data("index") == swiper.activeIndex){
			      		$(this).addClass("curjudge").siblings().removeClass("curjudge");
			      	}
			      	scrollForSlide(swiper.activeIndex);
			      });
			    }
			})
				$(".topicTab li").click(function(){
					$(this).addClass("curjudge").siblings().removeClass("curjudge");
				});
			
			 $("#reviewlist1").click(function(){
				mySwiper.slideTo(0, 500, false);
				$(".two").css('height', '430px');
				$(".one").css('height', 'auto');
				scrollToNewArticle();
			});
			$("#oldTopicsList1").click(function(){
				mySwiper.slideTo(1, 500, false);
				$(".one").css('height', '430px');
				$(".two").css('height', 'auto');
				scrollToHotArticle();
			});
			
			function scrollForSlide(index){
				if(index == 0){
				$(".two").css('height', '430px');
				$(".one").css('height', 'auto');
					scrollToNewArticle();
				}
				if(index == 1){
				$(".one").css('height', '430px');
				$(".two").css('height', 'auto');
					scrollToHotArticle();
				}
			}
		var prePage = 1;
		var pagenum = 2;
		var prePage1 = 1;
		var pagenum1 = 2;
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
			last($(".oldTopicsList1"));
			$.fn.scrollHandle({
				obj: window,
				fun: loadhotarticle
			})
		};
		// 加载更多文章
		function loadmore() {
			var	url = "${base}/m/ajax/topic/review/moretopic";
			if(prePage != pagenum) {
			    prePage = pagenum;
			    var json = {};
				json.cid='${cid?c}';
				json.pagenum=pagenum;
				$.get(url, json, function(data){
					if(data.trim() == ""){// 没有更多内容
						$(window).unbind("scroll", defaultScrollHandler);
						return false;
					}
					$(".reviewlist").append(data);
					pagenum+=1;
				});
			}
		}
		
		function loadhotarticle() {
			var	url = "${base}/m/ajax/topic/thinking/relatetopic";
			if(prePage1 != pagenum1) {
			prePage1 = pagenum1;
			    var json = {};
				json.cid='${cid?c}';
				json.pagenum=pagenum1;
				$.get(url, json, function(data){
					if(data.trim() == ""){// 没有更多内容
						$(window).unbind("scroll", defaultScrollHandler);
						return false;
					}
					$(".oldTopicsList").append(data);
					pagenum1 += 1;
				});
			}
		}
			
		})
		
		
		//备选话题
		function review(obj){
			pagenum=1;
			prePage = 0;
			$(obj).addClass("curjudge").siblings().removeClass("curjudge");
			var url = "${base}/m/ajax/topic/review/moretopic";
			var json = {};
			json.cid='${cid?c}';
			json.pagenum=pagenum;
			$.get(url, json,function(data){
				if(data != ''){
					$(".reviewlist").html(data);
					pagenum+=1;
				}
			});
		}
		
		//往期话题
		function last(obj){
			pagenum1 = 1;
			prePage1 = 0;
			$(obj).addClass("curjudge").siblings().removeClass("curjudge");
			var url = "${base}/m/ajax/topic/thinking/relatetopic";
			var json = {};
			json.cid='${cid?c}';
			json.pagenum=pagenum1;
			$.get(url, json,function(data){
				if(data != ''){
					$(".oldTopicsList").html(data);
					pagenum1 +=1;
				}
			});
		}	
	</script>
</body>
</html>
