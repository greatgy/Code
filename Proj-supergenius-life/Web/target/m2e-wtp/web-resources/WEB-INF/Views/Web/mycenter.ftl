[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Public.ftl" as p]
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>${title!'个人中心'}</title>
	<meta name="Keywords" content="天财评论，个人中心，消息通知" />
	<meta name="Description" content="天财评论，个人中心，消息通知" />
	<link rel="stylesheet" href="${basecss}/css/default/index.css">
	<link href="${basecss}/css/default/detail.css" rel="stylesheet" type="text/css">
	<script src="${basejs}/js/libs3/swiper.jquery.min.js"></script>
	<style type="text/css">
		.descripes {
		    margin: 0 auto 30px;
		    color: #888;
		    font-size: 0.9rem;
		    line-height: 22px;
		    max-height: 68px;
		    overflow: hidden;
		}
	</style>
</head>
<body>
	<div class="userArticleLeft" >
		<ul class="userInfo">
			<li  id="myarticle">
				<a href="${base}/my/center/article">
					<img src="${baseimg}/imgs/default/art.png" alt="我的文章" title="我的文章" />
					我的文章
				</a>
			</li>
			<li id="myquestion">
				<a href="${base}/my/center/question">
					<img src="${baseimg}/imgs/default/ask.png" alt="我的提问" title="我的提问" />
					我的提问
				</a>
			</li>
			<li id="myvideo">
				<a href="${base}/my/center/video">
					<img src="${baseimg}/imgs/default/film.png" alt="我的视频" title="我的视频" />
					我的视频
				</a>
			</li>
			<li id="mytopic">
				<a href="${base}/my/center/topic">
					<img src="${baseimg}/imgs/default/topic.png" alt="我的话题" title="我的话题" />
					我的话题
				</a>
			</li>
			<li id="mymessage">
				<a href="${base}/my/center/msg">
					<img src="${baseimg}/imgs/default/news.png" alt="消息通知" title="消息通知" />
					消息通知
					<span class="arrow usermessage"></span>
				</a>
			</li>
			<li id="mycomment">
			    <a href="${base}/my/center/comment">
                    <img src="${baseimg}/imgs/default/comment.png" alt="我的评论" title="我的评论" />
                    我的评论
                </a>
            </li>
			<li id="mycollect">
				<a href="${base}/my/center/collect">
					<img src="${baseimg}/imgs/default/collect (2).png" alt="我的收藏" title="我的收藏" />
					我的收藏
				</a>
			</li>
			<li id="mydetail">
				<a href="${base}/my/center/info">
					<img src="${baseimg}/imgs/default/infor.png" alt="个人资料" title="个人资料" />
					个人资料
				</a>
			</li>
		</ul>
		<a class="logout">退出登录</a>
	</div>
	<div class="contentLeft userArticleRight">
	[#-- 我的文章 --]
		[#if channel == "article"]
			<div class="myCollect">
				<ul class="artTab">
					<li class="curart" id="published">
						已发布
					</li>
					<li id="draft">草稿箱</li>
				</ul>
				[#if article??]
					[#list article?keys as key]
						[#assign articleList = article[key]]
							<ul class="tabBox userarticle hd" id="article${key}">
								[#if articleList?size>0]
									[#list articleList as item]
									[#if item.imgmedium??]
										<li>
											<a class="tabitemimg" [#if key == 'draft']href="${base}/life/my/article/${item.uid}"[#else]href="${base}/article/${(item.firstCid)?c}/${(item.oid)?c}" [/#if]">
												<img src="${webimg}${item.imgmedium}" alt="${item.title}" title="${item.title}">
											</a>
											<div class="tabitemRt">
												<a [#if key == 'draft']href="${base}/life/my/article/${item.uid}"[#else]href="${base}/article/${(item.firstCid)?c}/${(item.oid)?c}" [/#if] class="articleTitle" href="${base}/${item.cid}/${(item.oid)?c}">
													${item.title}
												</a>
												[#if item.summary??]
													<p class="descripe">[@p.TrimSubstring content="${item.summary}" mylength=80 /]</p>
												[#else]
													<p class="descripe">[@p.TrimSubstring content="${item.content}" mylength=80 /]</p>
												[/#if]
												<div class="timepiece">
													<span class="time">[@p.mytime datetime="${item.createtimeStr}"/]</span>
													<div class="operateBox">
														<a href="${base}/article/${(item.firstCid)?c}/${(item.oid)?c}" class="littleicon">
															<img src="${baseimg}/imgs/default/heart.png" />
															${item.prizecount}
														</a>
														<a href="${base}/article/${(item.firstCid)?c}/${(item.oid)?c}" class="littleicon">
															<img src="${baseimg}/imgs/default/discuss.png" />
															${item.commentscount}
														</a>
														<a href="${base}/article/${(item.firstCid)?c}/${(item.oid)?c}" class="littleicon">
															<img src="${baseimg}/imgs/default/clickcount.png" />
															${item.clickcount}
														</a>
													</div>
												</div>
											</div>
										</li>
										[#else]
											<li>
												[#-- <a class="tabitemimg" [#if key == 'draft']href="${base}/life/my/article/${item.uid}"[#else]href="${base}/article/${item.cid}/${(item.oid)?c}" [/#if]">
													<img src="${webimg}${item.imgmedium}" alt="${item.title}" title="${item.title}">
												</a> --]
												<div class="reciveLi">
													<a [#if key == 'draft']href="${base}/life/my/article/${item.uid}"[#else]href="${base}/article/${(item.firstCid)?c}/${(item.oid)?c}" [/#if] class="articleTitle" href="${base}/${item.cid}/${(item.oid)?c}">
														${item.title}
													</a>
													[#if item.summary??]
														<p class="descripes">[@p.TrimSubstring content="${item.summary}" mylength=80 /]</p>
													[#else]
														<p class="descripes">[@p.TrimSubstring content="${item.content}" mylength=80 /]</p>
													[/#if]
													<div class="timepiece">
														<span class="time">[@p.mytime datetime="${item.createtimeStr}"/]</span>
														<div class="operateBox">
															<a href="${base}/article/${(item.firstCid)?c}/${(item.oid)?c}" class="littleicon">
																<img src="${baseimg}/imgs/default/heart.png" />
																${item.prizecount}
															</a>
															<a href="${base}/article/${(item.firstCid)?c}/${(item.oid)?c}" class="littleicon">
																<img src="${baseimg}/imgs/default/discuss.png" />
																${item.commentscount}
															</a>
															<a href="${base}/article/${(item.firstCid)?c}/${(item.oid)?c}" class="littleicon">
																<img src="${baseimg}/imgs/default/clickcount.png" />
																${item.clickcount}
															</a>
														</div>
													</div>
												</div>
											</li>
										[/#if]
									[/#list]
								[#else]
									<div class='IsEmpty'>空空如也~</div>
								[/#if]
							</ul>
					[/#list]
				[/#if]
			</div>
		[/#if]
		[#-- 我的提问 --]
		[#if channel == "question"]
			<div class="myCollect">
				[#if problem??]
					<ul class="tabBox userquestion">
					[#list problem as item]
						<li>
							<a href="${base}/problem/detail/${(item.cid)?c}/${(item.oid)?c}" class="articleTitle">${item.title}
							<span class="[#if item.ismember == 1 && (item.cid)?c == 16384][#if item.state == 0]comment2[#elseif item.state == 2]comment1[/#if][/#if]">[#if item.ismember == 1 && item.cid == 16384][#if item.state == 0]待点评[#elseif item.state == 2]已点评[/#if][/#if]</span>
							</a>
							<p class="descripe">[@p.TrimSubstring content="${item.content}" mylength=180 /]</p>
							<div class="pubTime">
								[@p.mytime datetime="${item.createtimeStr}"/]
							</div>
						</li>
					[/#list]
					</ul>
				[/#if]
			</div>
		[/#if]
		[#-- 我的视频 --]
		[#if channel == "video"]
			<div class="myCollect">
				<ul class="videoTab">
					<li class="curart" id="testList">
						效果检验
					</li>
					<li id="stageList">我的舞台</li>
				</ul>
				[#if video??]
					[#list video?keys as key]
						[#assign videoList = video[key]]
							<ul class="talentList fruitsList uservideo hd" id="video${key}">
								[#assign i = 1]
								[#if videoList?size>0]
									[#list videoList as item]
										<li [#if i % 2 == 0]class="middl"[/#if]>
											<a href="${base}/videoDetail/${(item.cid)?c}/${item.uid}">
												<img class="talentimg" src="${webimg}${item.imgmedium}" alt="${item.title}" title="${item.title}">
												[#if item.type == 1]<div class="voice">音频</div>[/#if]
											</a>
											<div class="talentDetail">
												<span class="talentTitle"><i class="type">初一</i>语文</span>
												[#if me??]
													[#if me.getIsUser() && (item.cid)?c == 32768]
													 	<span class="comment [#if item.state == 2] commented[/#if]">
															[#if item.state == 2] 专家已点评[#elseif item.state == 0]未点评[/#if]
														</span> 
													[/#if]
												[/#if]
												<p>
													<span class="time">上传于<i>[@p.mytime datetime="${item.createtimeStr}"/]</i></span>
												</p>
											</div>
										</li>
										[#assign i = i + 1]
									[/#list]
								[#else]
									<div class='IsEmpty' style="line-height: 284px;">空空如也~</div>
								[/#if]
							</ul>
					[/#list]
				[/#if]
			</div>
		[/#if]
		[#-- 我的话题 --]
		[#if channel == "topic"]
			<div class="myCollect">
				<ul class="topictab">
					<li class="curart" id="publish" >
						<a href="${base}/my/center/topic">已发布</a>
					</li>
					<li id="join" onclick="jointopic()">已参与</li>
				</ul>
				[#if topic??]
					<ul class="tabBox topicTabbox">
					[#list topic as item]
						<li>
							<a href="${base}/topic/review/${(item.cid)?c}/${item.oid}" class="articleTitle">
								${item.title}
							</a>
							<p class="descripe">[@p.TrimSubstring content="${item.content}" mylength=180 /]</p>
						</li>
					[/#list]
				</ul>
				[/#if]
			</div>
		[/#if]
		
		[#-- 我的收藏 --]
		[#if channel == "collect"]
			<div class="myCollect">
				[#if collect??]
					<ul class="tabBox usercollect">
					[#list collect as item]
					<li>
						<a class="tabitemimg" href="${base}/article/${(item.cid)?c}/${(item.oid)?c}" title="${item.title}">
							<img src="${webimg}${item.imgmedium}" alt="${item.title}" title="${item.title}">
						</a>
						<div class="tabitemRt">
							<a href="${base}/article/${(item.cid)?c}/${(item.oid)?c}" class="articleTitle" href="${base}/${item.cid}/${(item.oid)?c}">
								${item.title}
							</a>
							[#if item.summary??]
								<p class="descripe">[@p.TrimSubstring content="${item.summary}" mylength=80 /]</p>
							[#else]
								<p class="descripe">[@p.TrimSubstring content="${item.content}" mylength=80 /]</p>
							[/#if]
							<div class="timepiece">
								<span class="time">[@p.mytime datetime="${item.createtimeStr}"/]</span>
								<div class="operateBox">
									<a href="${base}/article/${(item.cid)?c}/${(item.oid)?c}" class="littleicon">
										<img src="${baseimg}/imgs/default/heart.png" />
										${item.prizecount}
									</a>
									<a href="${base}/article/${(item.cid)?c}/${(item.oid)?c}" class="littleicon">
										<img src="${baseimg}/imgs/default/discuss.png" />
										${item.commentscount}
									</a>
									<a href="${base}/article/${(item.cid)?c}/${(item.oid)?c}" class="littleicon">
										<img src="${baseimg}/imgs/default/clickcount.png" />
										${item.clickcount}
									</a>
								</div>
							</div>
						</div>
					</li>
					[/#list]
					</ul>
				[/#if]
			</div>
		[/#if]
		
		[#if channel == "info"]
			<form class="editInfo">
				<h4 class="baseinfo">
					基本信息
					<a href="${userbase}/my/user" class="edit" id="user_edit">编辑</a>
				</h4>
				<ul class="baseinfoList">
					<li>
						<span class="labelTips">头像</span>
						<div class="userBaseRt">
							[#if user.useravatar??]
								<img src="${user.useravatar}" />
								[#else]
								<img src="${baseimg}/imgs/default/user.png" />
							[/#if]
						</div>
					</li>
					<li>
						<span class="labelTips">昵称</span>
						<div class="userBaseRt">
							${user.username}
						</div>
					</li>
					<li>
						<span class="labelTips">性别</span>
						<div class="userBaseRt">
							[#if user.gender == 1] 
								男
							[#else] 
								女
							[/#if]
						</div>
					</li>
					<li>
						<span class="labelTips">个人简介</span>
						<div class="userBaseRt">
							${user.summary}
						</div>
					</li>
				</ul>
			</form>
		[/#if]
		[#-- 我的评论 --]
        [#if channel == "comment"]
            [#if commentsList??]
                <!-- 我的提问 -->
                    <div class="myCollect">
                        <h4 class="baseinfo">我的评论</h4>
                        <div class="manycomments">您共有<span>${commentscount!'0'}</span>条评论</div>
                        <ul class="tabBox commentsbox">
                            [#list commentsList as comment]
                                <li>
                                    <a [#if comment.channel == '5'] href="${base}/article/${(comment.firstCid)?c}/${(comment.articleoid)?c}?commentuid=${(comment.uid)}"
                                        [#elseif comment.channel == '6']href="${base}/topic/${(comment.firstCid)?c}/${(comment.topicoid)?c}"
                                        [#elseif comment.channel == '7']href="${base}/videoDetail/${(comment.firstCid)?c}/${(comment.fromuid)}"
                                        [#elseif comment.channel == '8']href="${base}/catalogue/2/${(comment.firstCid)?c}"[/#if]>
                                            <p>[@p.TrimSubstring content="${comment.content}" mylength=80 /]</p>
                                            <div class="sourcefrom">
                                                [@p.TrimSubstring content="${comment.title}" mylength=80 /]
                                            </div>
                                            <div class="pubTime">
                                                [@p.mytime datetime="${comment.createtimeStr}"/]
                                                <div class="zan_play">
                                                    <span>
                                                        <img src="${baseimg}/imgs/default/thumb.png" alt="赞" title="赞" />
                                                        赞${comment.prizecount!'0'}
                                                    </span>
                                                </div>
                                            </div>
                                     </a>
                                </li>
                               [/#list]
                        </ul>
                    </div>
            [/#if]
        [/#if]
		[#-- 消息通知 --]
		[#if channel == "msg"]
			[#if msg??]	
				<div class="userNews">
					[#assign oldcreatetime = 0]
					[#list msg as item]
						[#if oldcreatetime != item.createtime.toString("YYYY年MM月dd日")]
						[#assign oldcreatetime = item.createtime.toString("YYYY年MM月dd日")]
							<div class="date">
								<h5>
									${item.createtime.toString("YYYY年MM月dd日")}
									<a href="" class="clean hd">清除</a>
								</h5>
							</div>
						[/#if]
						<ul class="newslist">
							<li>
								[#if item.isread == false]
								<i>NEW</i>
								[/#if]
								[#if item.type ==1]
									<span class="newstip">${item.fromusername}赞了你的文章</span>
								[#elseif item.type ==2]
									<span class="newstip">${item.fromusername}评论了你的文章</span>
								[#elseif item.type ==3]
									<span class="newstip">${item.fromusername}赞了你的评论</span>
								[#elseif item.type ==4]
									<span class="newstip">${item.fromusername}回复了你的评论</span>
								[#elseif item.type ==5]
									<span class="newstip">${item.fromusername}赞了你的话题</span>
								[#elseif item.type ==6]
									<span class="newstip">${item.fromusername}评论了你的话题</span>
								[#elseif item.type ==7]
									<span class="newstip">专家点评了你的视频</span>
								[#elseif item.type ==8]
									<span class="newstip">专家点评了你的问题</span>
								[#elseif item.type ==9]
									<span class="newstip">您的举报系统已处理</span>
								[/#if]
								<a href="javascript:;" class="form readNews" [#if item.type ==7 || item.type ==8]data-href="${item.href}"[#else]data-href="${item.href}"[/#if] data-uid="${item.uid}">${item.title}</a>
								<a href="javascript:;" class="delete" data-uid="${item.uid}"><img src="${baseimg}/imgs/default/crush.png" alt="删除" title="删除" /></a>
							</li>
						</ul>
					[/#list]
				</div>
				</div>
			[/#if]
		[/#if]
	</div>
	<div class="nomoreContent hd" >我是有底线的~</div>
	<script type="text/javascript" src="${basejs}/js/pages/scroll.js"></script>
	<script type="text/javascript">
		var pagenum=2;
		var prepage=1;
		var id;
		var videoid;
		var topicid;
		$(function(){
			$("#catalogue_0").removeClass("current");
		});
		
		function loadmorecollect() {
			var url = "${base}/ajax/my/collect";
			if(prepage!= pagenum){
				prepage=pagenum;
				$.get(url, {pagenum:pagenum}, function(data){
				    pagenum += 1;
					if(data.trim() == ""){// 没有更多内容
						pagenum -= 1;
						$(window).unbind("scroll", defaultScrollHandler);
					}
					$(".usercollect").append(data);
				});
			}	
		}
		
		function jointopic() {
			$(window).unbind("scroll", defaultScrollHandler);
			$.get("${base}/ajax/my/mycenter/joinTopic", {uid:"${me.uid}"}, function(data){
				$(".topicTabbox").html(data);
			});
			$.fn.scrollHandle({
				obj: window,
				fun: loadmoretopicjoin
			})
		}
		
		
		
		function loadmorequestion() {
			var url = "${base}/ajax/my/mycenter/problem";
			if(prepage!= pagenum){
				prepage=pagenum;
				$.get(url, {pagenum:pagenum}, function(data){
				    pagenum += 1;
					if(data.trim() == ""){// 没有更多内容
						pagenum -= 1;
						$(window).unbind("scroll", defaultScrollHandler);
					}
					$(".usercollect").append(data);
				});
			}	
		}
		
		function loadmoremsg() {
			var url = "${base}/ajax/my/msg";
			if(prepage!= pagenum){
				prepage=pagenum;
				$.get(url, {pagenum:pagenum}, function(data){
			    pagenum += 1;
				if(data.trim() == ""){// 没有更多内容
					pagenum -= 1;
					$(window).unbind("scroll", defaultScrollHandler);
				}
				$(".userNews").append(data);
			});
			}	
		}
		
		function loadmorearticle() {
			var url = "${base}/ajax/my/article/"+id;
			if(prepage!= pagenum){
				prepage=pagenum;
				$.get(url, {pagenum:pagenum}, function(data){
				    pagenum += 1;
					if(data.trim() == ""){// 没有更多内容
						$(window).unbind("scroll", defaultScrollHandler);
					}
					$("#article"+id).append(data);
				});
			}
		}
		function loadmorecomment() {
			var url = "${base}/ajax/my/comments";
			if(prepage!= pagenum){
				prepage=pagenum;
				$.get(url, {pagenum:pagenum}, function(data){
				    pagenum += 1;
					if(data.trim() == ""){// 没有更多内容
						$(window).unbind("scroll", defaultScrollHandler);
					}
					$(".commentsbox").append(data);
				});
			}
		}
		function loadmorevideo() {
			var url = "${base}/ajax/my/mycenter/video/"+videoid;
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
		function loadmoretopic() {
			var url = "/ajax/my/mycenter/topic";
			if(prepage!= pagenum){
				prepage=pagenum;
				$.get(url, {pagenum:pagenum}, function(data){
				    pagenum += 1;
					if(data.trim() == ""){// 没有更多内容
						$(window).unbind("scroll", defaultScrollHandler);
					}
					$(".topicTabbox").append(data);
				});
			}
		}
		function loadmoretopicjoin() {
			var url = "/ajax/my/mycenter/topic/join";
			if(prepage!= pagenum){
				prepage=pagenum;
				$.get(url, {pagenum:pagenum}, function(data){
				    pagenum += 1;
					if(data.trim() == ""){// 没有更多内容
						$(window).unbind("scroll", defaultScrollHandler);
					}
					$(".topicTabbox").append(data);
				});
			}
		}
		
		//订阅
		function subscribeHandler(uid,useruid) {
			$.ajax({
					type:"GET",
					url:"${base}/ajax/subscribe/"+useruid,
					success:function(data){
						if(data.result == true) {
						console.log(this)
							$("#addsub"+uid).addClass("hd");
							$("#cancelsub"+uid).removeClass("hd");
						}
					}
				});
		}
		
		//取消
		function cancelsubscribeHandler(uid,useruid) {
			$.ajax({
					type:"GET",
					url:"${base}/ajax/cancelsubscribe/"+useruid,
					success:function(data){
						if(data.result == true) {
							$("#cancelsub"+uid).addClass("hd");
							$("#addsub"+uid).removeClass("hd");
						}
					}
				});
		}

	$(function($){
		[#if me??]
			$.get("${base}/ajax/my/unreads", function(data){
    			if(data != 0){
                    $(".usermessage").before('<i class="reddot"></i>');
    			}
            });
        [/#if]
        
        $(".contentRight").addClass("userCenterLeft");
		$(".contentLeft").addClass("userCenterRight");
        [#-- 文章 --]
		id = $(".artTab li:first").attr("id");
		$(".artTab li:first").addClass("curart");
		$("#article"+id).removeClass("hd");
		$(".artTab li").click(function(){
			id = $(this).attr("id");
			$(this).addClass("curart").siblings().removeClass("curart");
			$("#article"+id).removeClass("hd").siblings(".userarticle").addClass("hd");
		});	
        [#-- 视频 --]
		videoid = $(".videoTab li:first").attr("id");
		$(".videoTab li:first").addClass("curart");
		$("#video"+videoid).removeClass("hd");
		$(".videoTab li").click(function(){
			videoid = $(this).attr("id");
			$(this).addClass("curart").siblings().removeClass("curart");
			$("#video"+videoid).removeClass("hd").siblings(".uservideo").addClass("hd");
		});	
		
		topicid = $(".topictab li:first").attr("id");
		$(".topictab li:first").addClass("curart");
		$(".topictab li").click(function(){
			topicid = $(this).attr("id");
			$(this).addClass("curart").siblings().removeClass("curart");
		});	
		
	
		
		
		
		[#if channel == 'msg']
			$("#mymessage").addClass("curguide");
			$("#mymessage img").attr('src',"${baseimg}/imgs/default/newsed.png");
			[#if msg?size>0]
				$(".userNews").prepend("<h4 class='baseinfo'>消息通知<a href='javascript:void(0);' class='clean'>清除</a></h4>");
				$.fn.scrollHandle({
					obj: window,
					fun: loadmoremsg
				})
			[#else]
				$(".contentLeft ").html("<div class='IsEmpty'>空空如也~</div>");
			[/#if]
			
		[#elseif channel == 'collect']
			$("#mycollect").addClass("curguide");
			$("#mycollect img").attr('src',"${baseimg}/imgs/default/collected.png");
			[#if collect?size>0]
				$(".usercollect").before("<h4 class='baseinfo'>我的收藏</h4>");
				$.fn.scrollHandle({
					obj: window,
					fun: loadmorecollect
				})
			[#else]
				$(".contentLeft ").html("<div class='IsEmpty'>空空如也~</div>");
			[/#if]
		[#elseif channel == 'question']
			$("#myquestion").addClass("curguide");
			$("#myquestion img").attr('src',"${baseimg}/imgs/default/asked.png");
			[#if problem?size>0]
				$(".userquestion").before("<h4 class='baseinfo'>我的提问</h4>");
				$.fn.scrollHandle({
					obj: window,
					fun: loadmorequestion
				})
			[#else]
				$(".contentLeft ").html("<div class='IsEmpty'>空空如也~</div>");
			[/#if]
		[#elseif channel == 'comment']
			$("#mycomment").addClass("curguide");
			$("#mycomment img").attr('src',"${baseimg}/imgs/default/commented.png");
			[#if commentsList?size>0]
                $.fn.scrollHandle({
                    obj: window,
                    fun: loadmorecomment
                })
            [#else]
                $(".contentLeft ").html("<div class='IsEmpty'>空空如也~</div>");
            [/#if]
		[#elseif channel == 'article']
			$(".artTab").before("<h4 class='baseinfo'>我的文章</h4>");
			$("#myarticle").addClass("curguide");
			$("#myarticle img").attr('src',"${baseimg}/imgs/default/arted.png");
			$.fn.scrollHandle({
				obj: window,
				fun: loadmorearticle
			})
		[#elseif channel == 'topic']
			$("#mytopic").addClass("curguide");
			$("#mytopic img").attr('src',"${baseimg}/imgs/default/topiced.png");
				$(".topictab").before("<h4 class='baseinfo'>我的话题</h4>");
				$.fn.scrollHandle({
					obj: window,
					fun: loadmoretopic
				})
			
		[#elseif channel == 'video']
			$(".videoTab").before("<h4 class='baseinfo'>我的视频</h4>");
			$("#myvideo").addClass("curguide");
			$("#myvideo img").attr('src',"${baseimg}/imgs/default/filmed.png");
				$.fn.scrollHandle({
					obj: window,
					fun: loadmorevideo
				})
		[#elseif channel == 'info']
			$("#mydetail").addClass("curguide");
			$("#mydetail img").attr('src',"${baseimg}/imgs/default/infored.png");
		[/#if]
		
		$(".contentRight").addClass("userCenterLeft");
		$(".contentLeft").addClass("userCenterRight");
		
		/********************初始化数据*****************/
		//退出登录
		$(".logout").on("click", function(){
			var url = "${base}/offline";
			$.post(url, function(data){
				if(data) {
					window.location.href = "${base}";
				} else {
					alert("退出失败，请检查网络连接~");
				}
			})
		});
		
		//清除消息框的内容
		$(".clean").on("click", function() {
			var url = "${base}/ajax/my/msg/deleteAll";
			if(!confirm("真的要清除所有消息吗?")){
				return false;
			}
			$.post(url, function(data){
				if (data) {	
					$(".contentLeft ").html("<div class='IsEmpty'>空空如也~</div>");
				}  else {
					alert("退出失败，请检查网络连接~");
				}
			})
		})
		
		/**************阅读消息 开始*****************/
		$(document).on("click", ".readNews", function(){
			var newsUid = $(this).data("uid");
			var href = $(this).data("href");
			var url = "${base}/ajax/my/msg/read";
			var hrefurl = $(this).data("href");
			//设置成已读
			$.get(url, {uid: newsUid}, function(data){
				if (data = "success") {	
					window.location.href = hrefurl;
				}
			})
		});
		
		/**************消息通知 用户点击垃圾桶图标进行删除  开始*****************/
		$(document).on("click", ".newslist li .delete",function(){
			var uid = $(this).data("uid");
			var that = $(this);
			  $.post("${base}/ajax/my/msg/delete",{uid:uid},function(result){
   				 if (result) {
   				 	that.parent().addClass("hd");
   				 } else {
   				 	alert("删除失败，请稍后再试~");
   				 }
  			  });
		});
		
	});
	</script>