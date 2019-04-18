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
</head>
<body>
	<div class="contentRight userArticleRight" >
		<ul class="userInfo">
			<li id="myarticle">
				<a href="${base}/my/center/article">
					<img src="${baseimg}/imgs/default/art.png" alt="我的文章" title="我的文章" />
					我的文章
					<span class="arrow">>></span>
				</a>
			</li>
			<li id="mymessage">
				<a href="${base}/my/center/msg">
					<img src="${baseimg}/imgs/default/news.png" alt="消息通知" title="消息通知" />
					消息通知
					<span class="arrow usermessage">>></span>
				</a>
			</li>
			<li id="mycollect">
				<a href="${base}/my/center/collect">
					<img src="${baseimg}/imgs/default/collect.png" alt="我的收藏" title="我的收藏" />
					我的收藏
					<span class="arrow">>></span>
				</a>
			</li>
			<li id="mydetail">
				<a href="${base}/my/center/info">
					<img src="${baseimg}/imgs/default/info.png" alt="个人资料" title="个人资料" />
					个人资料
					<span class="arrow">>></span>
				</a>
			</li>
			<li id="mysubscribe">
				<a href="${base}/my/center/subscribe">
					<img src="${baseimg}/imgs/default/add.png" alt="我的订阅" title="我的订阅" />
					我的订阅
					<span class="arrow">>></span>
				</a>
			</li>
		</ul>
		<a class="logout">退出登录</a>
	</div>
	<div class="contentLeft userArticleLeft">
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
									<li>
										<a class="tabitemimg" [#if key == 'draft']href="${base}/finance/my/article/${item.uid}"[#else]href="${base}/article/${item.firstCid}/${(item.oid)?c}" [/#if]">
											<img src="${webimg}${item.imgmedium}" alt="${item.title}" title="${item.title}">
										</a>
										<div class="tabitemRt">
											<a [#if key == 'draft']href="${base}/finance/my/article/${item.uid}"[#else]href="${base}/article/${item.firstCid}/${(item.oid)?c}" [/#if] class="articleTitle" href="${base}/${item.firstCid}/${(item.oid)?c}">
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
								[#else]
									<div class='IsEmpty'>空空如也~</div>
								[/#if]
							</ul>
					[/#list]
				[/#if]
			</div>
		[/#if]
	
		[#if channel == "collect"]
			<div class="myCollect">
				[#if collect??]
					<ul class="tabBox usercollect">
					[#list collect as item]
					<li>
						<a class="tabitemimg" href="${base}/article/${item.firstCid}/${(item.oid)?c}" title="${item.title}">
							<img src="${webimg}${item.imgmedium}" alt="${item.title}" title="${item.title}">
						</a>
						<div class="tabitemRt">
							<a href="${base}/article/${item.firstCid}/${(item.oid)?c}" class="articleTitle" href="${base}/${item.firstCid}/${(item.oid)?c}">
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
									<span class="newstip">${item.fromusername}收藏了你的文章</span>
									[#elseif item.type ==6]
									<span class="newstip">${item.fromusername}订阅了你</span>
								[/#if]
								<a href="javascript:;" class="form readNews" data-href="${item.href}" data-uid="${item.uid}">${item.title}</a>
								<a href="javascript:;" class="delete" data-uid="${item.uid}"><img src="${baseimg}/imgs/default/crush.png" alt="删除" title="删除" /></a>
							</li>
							
						</ul>
				[/#list]
				</div>
			[/#if]
		[/#if]
		
		[#if channel == "subscribe"]
				<ul class="artTab">
					<li class="curart" id="mySubscribe">
						我订阅的
					</li>
					<li id="subme">被订阅</li>
				</ul>
			[#if subscribe??]
				[#list subscribe?keys as key]
				[#assign subscribeList = subscribe[key]]
			 		<ul class="tabBox userarticle hd" id="article${key}">
					
			 	[#list subscribeList as item]
					<li class="authorbox">
						<div class="author">
							<div class="authorPiece">
								<a href="${base}/finance/uhome/${item.refuser.uid}"><img src="[#if item.refuser.avatar??]${userimg}${item.refuser.avatar}[#else]${basecss}/imgs/mobile/default/normal.png[/#if]" alt="" class="authorimg"></a>
								<div class="resAuthor">						
									<a href="${base}/finance/uhome/${item.refuser.uid}" class="authorName">${item.refuser.showname}</a>
									<span>文章<a href="javascript:;">${item.articlecount}</a></span>
								</div>
							</div>
							[#if key == 'mySubscribe']
							<div class="addsub hd" id="addsub${item.uid}">
								<a href="javascript:;" onclick="subscribeHandler('${item.uid}','${item.refuser.uid}');">+</a>
								订阅
							</div>
							<div class="cancelsub" id="cancelsub${item.uid}">
								<a href="javascript:;" onclick="cancelsubscribeHandler('${item.uid}','${item.refuser.uid}');">-</a>
								取消订阅
							</div>
							[#else]
							<div [#if item.follow == 2] class="addsub hd" [#else] class="addsub" [/#if] id="addsub${item.uid}">
								<a href="javascript:;" onclick="subscribeHandler('${item.uid}','${item.refuser.uid}');">+</a>
								订阅
							</div>
							<div [#if item.follow == 2] class="cancelsub" [#else] class="cancelsub hd" [/#if]  id="cancelsub${item.uid}">
								<a href="javascript:;" onclick="cancelsubscribeHandler('${item.uid}','${item.refuser.uid}');">-</a>
								取消订阅
							</div>
							[/#if]
							<p>${item.refuser.summary}</p>
							<div class="totalArt">共${item.articlecount}篇文章</div>
						</div>
					</li>
				[/#list]
					</ul>
				[/#list]
			[/#if]
		[/#if]
	</div>
	<div class="nomoreContent hd" >我是有底线的~</div>
	<script type="text/javascript" src="${basejs}/js/pages/scroll.js"></script>
	<script type="text/javascript">
		var pagenum=2;
		var prepage=1;
		var id;
		
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
				$(".newslist").append(data);
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
        
		id = $(".artTab li:first").attr("id");
		$(".artTab li:first").addClass("curart");
		$("#article"+id).removeClass("hd");
		$(".artTab li").click(function(){
			id = $(this).attr("id");
			$(this).addClass("curart").siblings().removeClass("curart");
			$("#article"+id).removeClass("hd").siblings(".userarticle").addClass("hd");
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
			$("#mycollect img").attr('src',"${baseimg}/imgs/default/collects.png");
			[#if collect?size>0]
				$(".usercollect").before("<h4 class='baseinfo'>我的收藏</h4>");
				$.fn.scrollHandle({
					obj: window,
					fun: loadmorecollect
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
		[#elseif channel == 'info']
			$("#mydetail").addClass("curguide");
			$("#mydetail img").attr('src',"${baseimg}/imgs/default/infoed.png");
		[#elseif channel == 'subscribe']
			$("#mysubscribe").addClass("curguide");
			$("#mysubscribe img").attr('src',"${baseimg}/imgs/default/added.png");
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
		$(".readNews").on("click", function(){
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
		$(".newslist li .delete").on("click", function(){
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