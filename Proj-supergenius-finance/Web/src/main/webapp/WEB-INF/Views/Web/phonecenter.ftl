[#ftl]
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>${title!'个人中心'}</title>
	<meta name="Keywords" content="天财评论，个人中心，消息通知" />
	<meta name="Description" content="天财评论，个人中心，消息通知" />
	<link rel="stylesheet" href="${basecss}/css/default/index.css">
</head>
<body>
	<div class="contentRight userLeft" >
		<ul class="userInfo">
			<li id="myarticle">
				<a href="${base}/my/center/article">
					<img src="${baseimg}/imgs/default/arted.png" alt="我的文章" title="我的文章" />
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
					<img src="${baseimg}/imgs/default/add.png" alt="我的收藏" title="我的收藏" />
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
					<img src="${baseimg}/imgs/default/collect.png" alt="我的订阅" title="我的订阅" />
					我的订阅
					<span class="arrow">>></span>
				</a>
			</li>
		</ul>
		<a class="logout">退出登录</a>
	</div>
<script type="text/javascript">
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
</script>		
