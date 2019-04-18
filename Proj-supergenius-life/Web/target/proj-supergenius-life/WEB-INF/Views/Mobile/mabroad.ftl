[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Public.ftl" as p]
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>${title!'天才人生'}</title>
	<link rel="stylesheet" href="${basecss}/css/mobile/default/index.css">
	<link rel="stylesheet" href="${basecss}/css/default/upload.css">
	<link rel="stylesheet" href="${basecss}/css/default/lrtk.css" />
	<script type="text/javascript" src="${basejs}/js/pages/datacollect.js"></script>
	<script type="text/javascript" src="${basejs}/js/pages/scroll.js"></script>
	<script type="text/javascript" src="${basejs}/js/mobile/comment-1.0.0.js"></script>
	
	<script type="text/javascript" src="${basejs}/js/pages/history.js"></script>
	<script type="text/javascript" src="${basejs}/js/libs3/jquery.qqface.min.js"> v
	</script>
	<script type="text/javascript" src="${basejs}/js/libs/upload.js"></script>
	<script type="text/javascript" src="${basejs}/js/libs/jquery.min.js"></script>
	<script type="text/javascript" src="${basejs}/js/libs/jquery.imgbox.pack.js"></script>
	<script type="text/javascript" src="${basejs}/js/libs3/swiper.jquery.min.js"></script>
	<script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
	<script type="text/javascript" src="${basejs}/js/weixinshare.js"></script>

	<script type="text/javascript">
		var me = {
			showname: "${me.showname!'游客'}",
			imglittle: "${me.avatar}",
			oid:"${(me.oid?c)!'0'}",
			uid:"${(me.uid)}",
			useravatar: "${me.useravatar}",
			isuser: "${me.isUser}",
			defaultImg: "${baseimg}/imgs/default/user.png"
		}
		var cid = '${cid?c}';
		var channel = 'essay';
		var base = '${base}';
		var baseimg = '${baseimg}';
		var webimg = '${webimg}';
		var userimg = '${userimg}';
		var href = window.location.href;
		var type = 0;
		$(function(){
        	$('.face').qqFace({ 
		        assign:'content', //给输入框赋值 
		        path:'${baseimg}/imgs/face/qq/' //表情图片存放的路径 
		    });
		});
	</script>

	
	<style type="text/css">
		.qqFace{margin-top:4px;background:#fff;padding:2px;border:1px #dfe6f6 solid;}
		.qqFace table td{padding:0px;}
		.qqFace table td img{cursor:pointer;border:1px #fff solid;}
		.qqFace table td img:hover{border:1px #0066cc solid;}
		.userimg .princeimgeasy {
		    position: absolute;
		    width: 30px;
		    height: 30px;
		    border: 0;
		    margin-left: 21px;
		    margin-top: -61px;
		    margin-right:0;
		    top:43px;
		    right:-25px;
		}
		.commentitemRt {
		    display: inline-block;
		    width: 85%;
		    margin-top: 5px;
		     margin-left: 5%; 
		    /* overflow: hidden; */
		}
	</style>
	<script type="text/javascript">
		var $j = jQuery.noConflict();
		$j(function($j) {
			$j(".imgb").imgbox();
		});
	</script>
</head>
<body>
	<ul class="tabList">
		<li class="curTab col-sm-6 col-xs-6" data-index="0" id="article">最新文章</li>
		<li class="col-sm-6 col-xs-6" data-index="1" id="leaveword">留言区</li>
	</ul>
	<div class="swiper-container swiper-container-h swiper-container-horizontal">
		<div class="swiper-wrapper">
			<div class="swiper-slide one">
				<!-- 轮播 -->
				<div id="lunbo" class="lunboindex swiper-container swiper-container-horizontal" style="margin-bottom:20px;">
					<ul id="banner_list" class="swiper-wrapper">
						[#if list??]
							[#assign bannercount = 0]
							[#list list as item]	
								[#if bannercount <4]
									<li data-index="${bannercount}" class="swiper-slide">
										<a href="${base}/article/${cid?c}/${(item.oid)?c}" class="banneritem banneritem5" style="background: #000 url(${webimg}${item.imgbig}) no-repeat center center;background-size: 100% 100%;"  title="${item.title}">
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
				<ul class="tabBoxplace">
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
					<div class="workplaceitem">
						<div class="wordbox guideWordbox" [#if me == ''] onclick= "isMember('${base}',${cid?c},${pcid});" [/#if]>
							<textarea id="content" class="comment_textarea contro_text"></textarea>
							<div class="wordpiece">
								<a [#if me??] class="face" [#else] onclick= "isMember('${base}',${cid?c},${pcid});" [/#if]><img src="${baseimg}/imgs/default/smile.png" alt="" /></a>
								<a id="zwb_upload" name="123" ><img style= "width: 41px; height:  30px;" src="${baseimg}/imgs/default/picture.png" alt="" /><input type="file" class="add hd" multiple></img></a>
								<input type="text" name="filePath" id="callbackPath2" class="hd">
								<button class="submitBtn" [#if me??] onclick="submitCommentHandler(this);" [#else] onclick= "isMember('${base}',${cid?c},${pcid});" [/#if]>>发表</button>
								<p class="hidename"><input type="checkbox" id="isnick"/><label for="isnick">匿名</label></p>
							</div>
							<div class="pictureBox" id="preBox">
							</div>
						</div>
						<div class="newDiscuss">
							<h4>最新留言</h4>
							<ul class="commentList" id="displayComment">
								<img class="nothing hd" src="${baseimg}/imgs/default/prompt.png" style="width:481px;heigth:211px">
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	<script type="text/javascript" src="${basejs}/js/mobile/lifeessay.js"></script>
	<script>		
		$(function(){
			$(".one").css('height', 'auto');
			$(".two").css('height', '500px');
            /*轮播*/
            var swiper = new Swiper('.lunboindex', {
                spaceBetween: 30,
                centeredSlides: true,
                autoplay: 2500,
                autoplayDisableOnInteraction: false
            });
		
		
		$("#zwb_upload").bindUpload({
	        url:"${base}/life/essay/uploadimg",//上传服务器地址
	        callbackPath:"#callbackPath2",//图片地址的保存容器的id或者class
	        num:9,//上传数量的限制 默认为空 无限制
	        type:"jpg|png|gif|svg",//上传文件类型 默认为空 无限制
	        size:3,//上传文件大小的限制,默认为5单位默认为mb
	    });
	    
		var prePage = 2;
		var pagenum = 3;
		// 加载更多事件处理
		function loadmorearticle() {
			var	url = "${base}/m/ajax/otherpage/catalogue/${cid?c}";
			if(prePage != pagenum) {
			    prePage = pagenum;
				$.get(url, {pagenum:pagenum}, function(data){
					if(data.trim() == ""){// 没有更多内容
						$(window).unbind("scroll", defaultScrollHandler);
						return false;
					}
					$(".tabBoxplace").append(data);
					prePage = pagenum;
					pagenum+=1;
				});
			}
		}
		
		function scrollToArticle(){
			$(window).unbind("scroll", defaultScrollHandler);
			$.fn.scrollHandle({
				obj: window,
				fun: loadmorearticle
			})
		};
		function scrollForSlide(index){
			if(index == 0){
				$(".two").css('height', '500px');
				$(".one").css('height', 'auto');
				scrollToArticle();
			}
			if(index == 1){
				$(".one").css('height', '500px');
				$(".two").css('height', 'auto');
				scrollToLeaveword();
			}
		}
		var aboradSwiper = new Swiper('.swiper-container-h', {
		    	slidesPerView: 1,
			    loop: false,
			    autoplayDisableOnInteraction : false,
			    observer:true,//修改swiper自己或子元素时，自动初始化swiper  
				observeParents:true,
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
		 $("#article").click(function(){
			aboradSwiper.slideTo(0, 500, false);
			$(".two").css('height', '500px');
			$(".one").css('height', 'auto');
			scrollToArticle();
		});
		$("#leaveword").click(function(){
			aboradSwiper.slideTo(1, 500, false);
			$(".one").css('height', '500px');
			$(".two").css('height', 'auto');
			scrollToLeaveword();
		});
	});
	</script>
	
	
	
	
	
	
</body>
</html>