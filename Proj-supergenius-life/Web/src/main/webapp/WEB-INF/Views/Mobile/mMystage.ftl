[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Public.ftl" as p]
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>${title!'天才人生'}</title>
	<meta name="Keywords" content="天才人生,我的舞台" />
	<meta name="Description" content="天才人生,我的舞台" />
	<link rel="stylesheet" href="${basecss}/css/mobile/default/index.css">
	<script type="text/javascript" src="${basejs}/js/pages/scroll.js"></script>
	<script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
	<script type="text/javascript" src="${basejs}/js/weixinshare.js"></script>
	<script type="text/javascript">
		var type = 0;
		var prePage = 1;
		var pagenum = 2;
		$(function(){
			$("#hot").click(function(){
				type = 1;
				prePage = 1;
				pagenum = 2;
				$("#videoList").hide();
				$("#hotList").show();
				$("#new").removeClass("curjudge");
				$("#hot").addClass("curjudge");
				$.ajax({
		            type: "GET",
		            url: "${base}/ajax/hotvideo/${cid?c}",
		            data: {pagenum:1},
		            async: true,
		            success: function (data) {
		                console.log(data)
						$("#hotList").html(data);
		            }
		        });
			});
			$("#new").click(function(){
				type = 0;
				prePage = 1;
				pagenum = 2;
				$("#videoList").show();
				$("#hotList").hide();
				$("#hot").removeClass("curjudge");
				$("#new").addClass("curjudge");
				$.ajax({
		            type: "GET",
		            url: "${base}/ajax/video/${cid?c}",
		            data: {pagenum:1},
		            async: true,
		            success: function (data) {
						$("#videoList").html(data);
		            }
		        });
			});
		});
	</script>
	<style>
		a:focus,a:hover{
			text-decoration: none;
		}
	</style>
</head>
<body>
	<div class="topicBox">
		<ul class="judgement">
			<li class="curjudge" id="new">最新</li>
			<li id="hot">最热</li>
		</ul>
		<ul class="talentList topicList" id="videoList">
			[#if videoList??]
				[#list videoList as item]	
						<li>
							<a href="${base}/videoDetail/${cid?c}/${item.uid}"  target="_blank">
								<img class="talentimg" src="${webimg}${item.imgmedium}" alt="${item.title}" title="${item.title}" />
								<div class="play">
									<img src="${baseimg}/imgs/default/playbtn.png" alt="播放" title="播放">
								</div>
							</a>
							<div class="talentDetail">
								<span class="talentTitle"><i class="type">${item.keywordsname}</i>${item.title}</span>
								<span class="looktimes">
									<img src="${baseimg}/imgs/default/audience.png" alt="">
									${item.clickCount}人观看
								</span>
								<p>
									<span style="display:block"><img src="${item.user.useravatar}" [#if item.user.getIsUser()]class="memberUserimg"[/#if] alt="${item.user.username}" title="${item.user.username}" />
									[#if item.user.getIsUser()]<img src="${baseimg}/imgs/default/prince.png" class="princeimgs" alt="" />[/#if]
									[@p.PrivateString content="${item.user.username}" /]</span>
									<span class="time">上传于<i>${(item.createtime).toString("YYYY.M.dd")}</i></span>
								</p>
							</div>
						</li>
				[/#list]
			[/#if]
		</ul>
		
		<ul class="talentList topicList hd" id="hotList">
			[#if hotVideoList??]
				[#list hotVideoList as item]	
						<li>
							<a href="${base}/videoDetail/${cid?c}/${item.uid}">
								<img class="talentimg" src="${webimg}${item.imgmedium}" alt="${item.title}" title="${item.title}" />
							</a>
							<div class="talentDetail">
								<span class="talentTitle"><i class="type">${item.keywordsname}</i>${item.title}</span>
								<span class="looktimes">
									<img src="${baseimg}/imgs/default/audience.png" alt="">
									${item.clickCount}人观看
								</span>
								<p>
									<span style="display:block"><img src="${item.user.useravatar}" [#if item.user.getIsUser()]class="memberUserimg"[/#if] alt="${item.user.username}" title="${item.user.username}" />
									[#if item.user.getIsUser()]<img src="${baseimg}/imgs/default/prince.png" class="princeimgs" alt="" />[/#if]
									[@p.PrivateString content="${item.user.username}" /]</span>
									<span class="time">上传于<i>${(item.createtime).toString("YYYY.M.dd")}</i></span>
								</p>
							</div>
						</li>
				[/#list]
			[/#if]
		</ul>
	</div>
	<script type="text/javascript">
		$.fn.scrollHandle({
			obj: window,
			fun: loadmore
		})
		
		function loadmore() {
			var url = "";
			if (type == 0) {
				url = "${base}/ajax/video/${cid?c}";
			} else if (type == 1) {
				url = "${base}/ajax/hotvideo/${cid?c}";
			}
			if(prePage != pagenum) {
			    prePage = pagenum;
				$.get(url, {pagenum:pagenum}, function(data){
					if(data.trim() == ""){// 没有更多内容
						return false;
					}
					if (type == 0) {
						$("#videoList").append(data);
					} else if (type == 1) {
						$("#hotList").append(data);
					}
					pagenum+=1;
				});
			}
		}
	</script>
</body>
</html>