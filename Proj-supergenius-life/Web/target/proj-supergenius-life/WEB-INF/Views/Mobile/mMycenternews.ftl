[#ftl]
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>${title!'天才人生'}</title>
	<link rel="stylesheet" href="${basecss}/css/mobile/default/index.css">
	<script type="text/javascript" src="${basejs}/js/pages/scroll.js"></script>
	<script src="${basejs}/js/libs3/swiper.jquery.min.js"></script>
	<script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
	<script type="text/javascript" src="${basejs}/js/weixinshare.js"></script>
	<style>
		.containerBox{
			margin-top:30px;
		}
	</style>
</head>
<body>
	<!-- 消息通知 -->
	[#if msg??]	
	<div class="userNews">
	[#assign oldcreatetime = 0]
	[#list msg as item]
		[#if oldcreatetime != item.createtime.toString("YYYY年MM月dd日")]
		[#assign oldcreatetime = item.createtime.toString("YYYY年MM月dd日")]
		<div class="date">
			<h5>
				${item.createtime.toString("YYYY年MM月dd日")}
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
		[/#if]
	</div>
	<script>
		var pagenum=2;
		var prepage=1;
		function loadmoremsg() {
			var url = "${base}/m/ajax/my/msg";
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
		$(function(){
			[#if msg?size>0]
				$(".userNews").prepend("<a href='javascript:void(0);' class='clean'>清除</a>");
				$.fn.scrollHandle({
					obj: window,
					fun: loadmoremsg
				})
			[#else]
				$(".newslist ").html("<div class='IsEmpty'>空空如也~</div>");
			[/#if]
			//清除消息框的内容
			$(".clean").on("click", function() {
				var url = "${base}/m/ajax/my/msg/deleteAll";
				if(!confirm("真的要清除所有消息吗?")){
					return false;
				}
				$.post(url, function(data){
					if (data) {	
						$(".userNews ").html("<div class='IsEmpty'>空空如也~</div>");
					}  else {
						alert("退出失败，请检查网络连接~");
					}
				})
			})
			/**************阅读消息 开始*****************/
			$(document).on("click", ".readNews",function(){
				var newsUid = $(this).data("uid");
				var href = $(this).data("href");
				var url = "${base}/m/ajax/my/msg/read";
				var hrefurl = $(this).data("href");
				//设置成已读
				$.get(url, {uid: newsUid}, function(data){
					if (data = "success") {	
						window.location.href = hrefurl;
					}
				})
			});
			/**************消息通知 用户点击垃圾桶图标进行删除  开始*****************/
			$(document).on('click', ".newslist li .delete", function(){
				var uid = $(this).data("uid");
				var that = $(this);
				  $.post("${base}/m/ajax/my/msg/delete",{uid:uid},function(result){
	   				 if (result) {
	   				 	that.parent().addClass("hd");
	   				 } else {
	   				 	alert("删除失败，请稍后再试~");
	   				 }
	  			  });
			});
		});
	</script>
</body>
</html>