[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Public.ftl" as p]
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>${title!'天才人生'}</title>
	<link rel="stylesheet" href="${basecss}/css/default/index.css">
	<link rel="stylesheet" href="${basecss}/css/default/upload.css">
	<script type="text/javascript" src="${basejs}/js/pages/datacollect.js"></script>
	<script type="text/javascript" src="${basejs}/js/pages/scroll.js"></script>
	<script type="text/javascript" src="${basejs}/js/pages/history.js"></script>
	<script type="text/javascript" src="${basejs}/js/pages/comment-1.0.0.js"></script>
	<script type="text/javascript" src="${basejs}/js/libs3/jquery.qqface.min.js"></script>
	<script type="text/javascript" src="${basejs}/js/pages/lifeessay.js"></script>
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
		.commentOperate a {
			margin-left: 0;
		}
	</style>
	
	<link rel="stylesheet" href="${basecss}/css/default/lrtk.css" />
	<script type="text/javascript" src="${basejs}/js/libs/jquery.min.js"></script>
	<script type="text/javascript" src="${basejs}/js/libs/jquery.imgbox.pack.js"></script>
	
	<script type="text/javascript">
		var $j = jQuery.noConflict();
		$j(function($j) {
			$j(".imgb").imgbox();
		});
		
	</script>
</head>
<body>
	<div class="contentLeft">
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
				留言区
			</h3>
			<div class="wordbox guideWordbox [#if me == ''] pop-up [/#if]" >
				<textarea id="content" class="comment_textarea contro_text"></textarea>
				<div class="wordpiece">
					<a class="[#if me??] face [#else] pop-up [/#if]"><img src="${baseimg}/imgs/default/smile.png" alt="" /></a>
					<a id="zwb_upload" name="123"><img src="${baseimg}/imgs/default/picture.png" alt="" /><input type="file" class="add hd" multiple></img></a>
					<input type="text" name="filePath" id="callbackPath2" class="hd">
					<button class="submitBtn" [#if me??] onclick="submitCommentHandler(this);" [/#if]>发表</button>
					<form class="hidename"><input type="checkbox" id="isnick"/><label for="isnick">匿名</label></form>
				</div>
				<div class="pictureBox" id="preBox">
				</div>
			</div>
			<div class="newDiscuss">
				<h4>最新留言</h4>
				<ul class="commentList conCommentlist" id="displayComment">
					<img class="nothing hd" src="${baseimg}/imgs/default/prompt.png" style="width:100%;heigth:211px">
				</ul>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="${basejs}/js/libs/upload.js"></script>
	<script type="text/javascript">
		
		$("#zwb_upload").bindUpload({
	        url:"${base}/life/essay/uploadimg",//上传服务器地址
	        callbackPath:"#callbackPath2",//图片地址的保存容器的id或者class
	        num:9,//上传数量的限制 默认为空 无限制
	        type:"jpg|png|gif|svg",//上传文件类型 默认为空 无限制
	        size:3,//上传文件大小的限制,默认为5单位默认为mb
	    });
	    
		$(function(){
			ajax_history('${base}/ajax/article/catalogue/${cid?c}', '${pagesize}', '.tabBox', 1, '${FirstLoadSize}');// 加载文章的url, 下拉加载时的加载size, 文章列表父级, pagenum, 首次加载size（去除轮播后的）
			
			$.fn.scrollHandle({
				obj: window,
				fun: loadmorearticle
			})
		})
		
		var prePage = 2;
		var pagenum = 3;
		// 加载更多文章
		function loadmorearticle() {
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
	</script>
</body>
</html>