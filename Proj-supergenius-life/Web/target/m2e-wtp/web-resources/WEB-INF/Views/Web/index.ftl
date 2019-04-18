[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Public.ftl" as p]
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>${title!'天才人生——首页'}</title>
	<link rel="stylesheet" href="${basecss}/css/default/index.css">
	<script type="text/javascript" src="${basejs}/js/pages/scroll.js"></script>
	<script type="text/javascript" src="${basejs}/js/pages/history.js"></script>
	<style>
		.talentDetail p .princeimg{
			position:relative;
			top: -10px;
    		left: -21px;
		}
	</style>
</head>
<body>
	<div id="lunbo">
		<div class="banner">
			<ul id="banner_list">
				[#if bannerList??]
					[#assign bannercount = 0]
					[#list bannerList as item]	
							<li data-index="${bannercount}">
								<a href="${item.url}" class="banneritem" style="background: #000 url(${webimg}${item.content}) no-repeat center center;background-size: 100% 100%;"  title="${item.title}"></a>
							</li>
						[#assign bannercount = bannercount + 1]
					[/#list]
				[/#if]
			</ul>
		</div>
		<ul id="controller">
			[#if bannerList??]
				[#assign bannercount = 0]
				[#list bannerList as item]
					<li [#if bannercount == 0]class="active"[/#if]></li>
					[#assign bannercount = bannercount + 1]
				[/#list]
			[/#if]
		</ul>
	</div>
	<div class="mainBox">
		<div class="contentLeft">
			<ul class="judgement" style="margin-top:0;padding-top:6px;">
				<li class="curjudge" id="new" onclick="lastArticle(this)">最新</li>
				<li id="hot" class="" onclick="originalArticle(this)">原创</li>
			</ul>
			<ul class="tabBox">
				[#if list??]
					[#list list as article]
						[#if article.imgmedium??]
							<li>
								<a class="tabitemimg" href="${base}/article/${(article.firstCid)?c}/${(article.oid)?c}" title="${article.title}">
									<img src="${webimg}${article.imgmedium}" alt="${article.title}" title="${article.title}"/>
								</a>
								<div class="tabitemRt">
									<a href="${base}/article/${(article.firstCid)?c}/${(article.oid)?c}" class="articleTitle">
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
											<a href="${base}/article/${(article.firstCid)?c}/${(article.oid)?c}" class="littleicon">
												<img src="${baseimg}/imgs/default/clickcount.png" />
												${article.clickcount}
											</a>
											<a href="${base}/article/${(article.firstCid)?c}/${(article.oid)?c}" class="littleicon">
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
									<a href="${base}/article/${(article.firstCid)?c}/${(article.oid)?c}" class="articleTitle">
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
											<a href="${base}/article/${(article.firstCid)?c}/${(article.oid)?c}" class="littleicon">
												<img src="${baseimg}/imgs/default/clickcount.png" />
												${article.clickcount}
											</a>
											<a href="${base}/article/${(article.firstCid)?c}/${(article.oid)?c}" class="littleicon">
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
		<div class="contentRight">
			<div class="workplaceitem">
				<h3>
					话题区
					[#if topicList?size>1]<a class="change" href="javascript:;" onclick="loadtopic();" id="changetopic">换一换<img src="${baseimg}/imgs/default/refresh.png" alt="换一批" title="换一批"></a>[/#if]
				</h3>
				<ul class="hotArticle hotTopic">
					[#if topicList??]
						[#list topicList as topic]
							<li>
								<a href="${base}/topic/${(topic.cid)?c}/${topic.oid}" title="${topic.title}">
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
								<a href="${base}/problem/detail/${(item.cid)?c}/${(item.oid)?c}">
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
								<a href="${base}/videoDetail/${(item.cid)?c}/${item.uid}">
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
		</div>
	</div>
	<script type="text/javascript">
		var prePage = 1;
		var pagenum = 2;
		var originalprePage=1;
		var originalpagenum=2;
		var topicnum = 2;
		var problemnum = 2;
		var videonum = 2;
		
		// 加载更多文章
		function loadmore() {
			var url = "${base}/ajax/first/article";
			if(prePage != pagenum) {
			    prePage = pagenum;
				$.get(url, {pagenum:pagenum}, function(data){
					if(data.trim() == ""){// 没有更多内容
						$(window).unbind("scroll", defaultScrollHandler);
						return false;
					}
					$(".tabBox").append(data);
					pagenum+=1;
				});
			}
		}
		//加载原创文章
		function loadmoreoriginal() {
			var url="${base}/ajax/article/originalarticle";
			if(originalprePage != originalpagenum) {
			    originalprePage = originalpagenum;
				$.get(url, {pagenum:originalpagenum}, function(data){
					if(data.trim() == ""){// 没有更多内容
						$(window).unbind("scroll", defaultScrollHandler);
						return false;
					}
					$(".tabBox").append(data);
					originalpagenum+=1;
				});
			}
		}
		// 话题换一换
		function loadtopic() {
			var	url = "${base}/ajax/first/topic";
			$.get(url, {pagenum:topicnum}, function(data){
				if(data.trim() == ""){// 没有更多内容
					topicnum=1;
					$("#changetopic").click();
					return false;
				}
				$(".hotTopic").html(data);
				topicnum+=1;
			});
		}
		
		// 问答换一换
		function loadproblem() {
			var	url = "${base}/ajax/first/problems";
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
			var	url = "${base}/ajax/first/video";
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
		ajax_history('${base}/ajax/index/article', '${pagesize}', '.tabBox', 1, '${FirstLoadSize}');// 加载文章的url, 下拉加载时的加载size, 文章列表父级, pagenum, 首次加载size（去除轮播后的）
			
		$.fn.scrollHandle({
			obj: window,
			fun: loadmore
		})
		
		/*轮播*/
		var a = 1;
		/*var lunboimgclass = ".banneritem1";
		var img = "url(" + $(lunboimgclass).attr("name") + ") no-repeat center center";*/
		$("#controller li").eq(0).addClass("active").siblings().removeClass("active");
		$("#banner_list li").eq(0).css("display","block").siblings().css("display","none");
		/*$(lunboimgclass).css("background", img).css("background-size", "100% 100%");*/
		$("#controller li").click(function(){
			var i = $(this).index();
			a = i;
			/*lunboimgclass = ".banneritem" + (a + 1);
			img = "url(" + $(lunboimgclass).attr("name") + ") no-repeat center center";
			$(lunboimgclass).css("background", img).css("background-size", "100% 100%");*/
			$(this).addClass("active").siblings().removeClass("active");
			$("#banner_list li").eq(i).css("display","block").siblings().css("display","none");
		});
		setInterval(function(){
			if(a == $("#controller li").length){
				a = 0 ;
			}
			/*lunboimgclass = ".banneritem" + (a + 1);
			img = "url(" + $(lunboimgclass).attr("name") + ") no-repeat center center";
			$(lunboimgclass).css("background", img).css("background-size", "100% 100%");*/
			$("#controller li").eq(a).addClass("active").siblings().removeClass("active");
			$("#banner_list li").eq(a).css("display","block").siblings().css("display","none");
			a++;
		},2500);
		$("#index").addClass("current").siblings().removeClass("current");
			
		});
		//获得最新文章
		function lastArticle(obj){
			$(window).unbind("scroll", defaultScrollHandler);
			$.fn.scrollHandle({
				obj: window,
				fun: loadmore
			})
			prePage=0;
			pagenum = 1;
			$(obj).addClass("curjudge").siblings().removeClass("curjudge");
			var url = "${base}/ajax/first/article";
			var json = {};
			json.pagenum=pagenum;
			$.get(url, json,function(data){
				if(data != ''){
					pagenum +=1;
					$(".tabBox").html(data);
				}
			});
		}
		//获得原创文章
		function originalArticle(obj){
			$(window).unbind("scroll", defaultScrollHandler);
			$.fn.scrollHandle({
				obj: window,
				fun: loadmoreoriginal
			})
			originalprePage = 0;
			originalpagenum = 1;
			$(obj).addClass("curjudge").siblings().removeClass("curjudge");
			var url = "${base}/ajax/article/originalarticle";
			var json = {};
			json.pagenum=originalpagenum;
			$.get(url, json,function(data){
				if(data != ''){
					originalpagenum +=1;
					$(".tabBox").html(data);
				}
			});
		}
	</script>
</body>
</html>