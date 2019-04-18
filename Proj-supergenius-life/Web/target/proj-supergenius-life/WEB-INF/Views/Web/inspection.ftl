[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Public.ftl" as p]
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>${title!'天才人生'}</title>
	<meta name="Keywords" content="天才人生,效果检验" />
	<meta name="Description" content="天才人生,效果检验" />
	<link rel="stylesheet" href="${basecss}/css/default/index.css">
	<script type="text/javascript" src="${basejs}/js/pages/scroll.js"></script>
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
				$("#memberList").hide();
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
				$("#memberList").hide();
				$("#hot").removeClass("curjudge");
				$("#new").addClass("curjudge");
				$.ajax({
		            type: "GET",
		            url: "${base}/ajax/video/${cid?c}",
		            data: {pagenum:1},
		            async: true,
		            success: function (data) {
		                console.log(data)
						$("#videoList").html(data);
		            }
		        });
			});
			$(".memberUser").click(function(){
				type = 2;
				prePage = 1;
				pagenum = 2;
				$("#videoList").hide();
				$("#hotList").hide();
				$("#hot").removeClass("curjudge");
				$("#memberList").removeClass("hd");
				$("#new").removeClass("curjudge");
				$("#memberList").show();
				$("#memberList").addClass("curjudge");
				
				$.ajax({
		            type: "GET",
		            url: "${base}/ajax/video/membervideo?cid="+${cid?c},
		            data: {pagenum:1},
		            async: true,
		            success: function (data) {
		                console.log(data)
						$("#memberList").html(data);
		            }
		        });
					
					
			});
		});
	</script>
	<style>
		a:focus,a:hover{
			text-decoration: none;
		}
		.pubQuestion {
		    margin-top: -5px;
		}
	</style>
</head>
<body>
	<div class="effectInspection" style="background: #fed811 url(${webimg}${photo.content}) no-repeat center center">
	[#if me??]
		<a href="${base}/my/addvideo/${cid?c}" class="publishFruit"><i>+</i>发布自学成果</a>
	[#else]
		<button class="publishFruit pop-up"><i>+</i>发布自学成果</button>
	[/#if]
	</div>
	<div class="fruitBox">
		<ul class="judgement">
			<li class="curjudge" id="new">最新</li>
			<li id="hot">最热</li>
			[#if me??]
				<a class="memberPiece [#if me.getIsUser()]memberUser[#else]normalUsertip[/#if]">会员专区</a>
        		<a class="pubQuestion" href="${base}/my/addvideo/${cid?c}">+发布自学成果</a>
			[#else]
				<a class="memberPiece pop-up">会员专区</a>	
        		<a class="pubQuestion pop-up" >+发布自学成果</a>
        	[/#if]
        	
		</ul>
		<ul class="talentList fruitsList" id="videoList">
			[#if videoList??]
				[#list videoList as item]	
						<li>
							<a 
							[#if item.ismember==1]
								[#if me??]
				            		[#if me.getIsUser() || item.user.uid == me.uid]href="${base}/videoDetail/${cid?c}/${item.uid}"[#else]href="javascript:;" class="normalUsertip"[/#if]
				            	[#else]
				            		href="javascript:;" class="pop-up"
				            	[/#if]
							[#else]
								[#if me??]
				            		href="${base}/videoDetail/${cid?c}/${item.uid}"
				            	[#else]
				            		href="javascript:;" class="pop-up"
				            	[/#if]
							[/#if]
			            	 target="_blank">
								<img class="talentimg" src="${webimg}${item.imgmedium}" alt="${item.title}" title="${item.title}" />
							</a>
							<div class="talentDetail">
								<span class="talentTitle">${item.title}</span>
								[#if item.ismember == 1] 
									[#if item.state == 2]
										<span class="comment commented">
											专家已点评
										</span>
									[#elseif item.state == 0]
										<span class="comment">
											未点评
										</span>
									[/#if]
								[/#if]
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
		
		<ul class="talentList fruitsList hd" id="hotList">
			[#if hotVideoList??]
				[#list hotVideoList as item]	
						<li>
							<a 
							[#if item.ismember==1]
								[#if me??]
				            		[#if me.getIsUser() || item.user.uid == me.uid]href="${base}/videoDetail/${cid?c}/${item.uid}"[#else]href="javascript:;" class="normalUsertip"[/#if]
				            	[#else]
				            		href="javascript:;" class="pop-up"
				            	[/#if]
							[#else]
								[#if me??]
				            		href="${base}/videoDetail/${cid?c}/${item.uid}"
				            	[#else]
				            		href="javascript:;" class="pop-up"
				            	[/#if]
							[/#if]
			            	 target="_blank">
								<img class="talentimg" src="${webimg}${item.imgmedium}" alt="${item.title}" title="${item.title}" />
							</a>
							<div class="talentDetail">
								<span class="talentTitle">${item.title}</span>
								[#if item.ismember == 1] 
									[#if item.state == 2]
										<span class="comment commented">
											专家已点评
										</span>
									[#elseif item.state == 0]
										<span class="comment">
											未点评
										</span>
									[/#if]
								[/#if]
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
		<ul class="talentList fruitsList hd" id="memberList">
		</ul>
	</div>
	
	<script type="text/javascript">
		$.fn.scrollHandle({
			obj: window,
			fun: loadmore
		})
		
		// 加载更多文章
		function loadmore() {
			var url = "";
			if (type == 0) {
				url = "${base}/ajax/video/${cid?c}";
			} else if (type == 1) {
				url = "${base}/ajax/hotvideo/${cid?c}";
			} else {
				url = "${base}/ajax/video/membervideo?cid="+${cid?c};
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
					} else {
						$("#memberList").append(data);
					}
					pagenum+=1;
				});
			}
		}
	</script>
</body>
</html>