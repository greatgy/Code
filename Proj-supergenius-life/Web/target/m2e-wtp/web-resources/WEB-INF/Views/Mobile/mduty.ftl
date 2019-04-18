[#ftl]
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>${title!'天才人生'}</title>
	<link rel="stylesheet" href="${basecss}/css/mobile/default/index.css">
	<script type="text/javascript" src="${basejs}/js/mobile/comment-1.0.0.js"></script>
	<script type="text/javascript" src="${basejs}/js/pages/scroll.js"></script>
	<script type="text/javascript" src="${basejs}/js/libs3/jquery.qqface.min.js"></script>
	<script type="text/javascript" src="${basejs}/js/mobile/lifesecondcomment.js"></script>
	<script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
	<script type="text/javascript" src="${basejs}/js/weixinshare.js"></script>
	<script type="text/javascript" src="${basejs}/js/pages/video.js"></script>
	<script type="text/javascript" src="${basejs}/js/pages/wxphoto.js"></script>
	<script type="text/javascript">
		var me = {
			showname: "${me.username!'游客'}",
			useravatar:"${me.useravatar}",
			oid:"${(me.oid?c)!'0'}",
			uid:"${me.uid}",
			defaultImg: "${baseimg}/imgs/default/user.png",
			isuser: "${me.isUser}"
		}
		var fromuid = '${bean.uid}';
		var cid = '${(bean.cid)?c}';
		var base = '${base}';
		var baseimg = '${baseimg}';
		var userimg = '${userimg}';
		var channel = 'contentcomments';
		var href = window.location.href;
		$(function(){
        	$('.face').qqFace({ 
		        assign:'content', //给输入框赋值 
		        path:'${baseimg}/imgs/face/qq/' //表情图片存放的路径 
		    });
		});
		
	</script>
	<style type="text/css">
		.bdshare-button-style0-16 a, .bdshare-button-style0-16 .bds_more{
    	        background-image: url(${baseimg}/imgs/icons_0_16.png)!important;
    	    }
		.articleContent p {
			font-size: 1rem;
		    line-height: 1.8rem;
		    color: #333;
		    text-align: left;
		    margin-bottom: 20px;
		}
		.articleContent img {
			max-width: 100%;
		    margin: 5px auto;
		    width: auto !important;
    		height: auto!important;
		    text-indent: inherit;
		    text-align: center;
		}
	</style>
</head>
<body>
	${self_study.content}			
	${bean.content}		
	[#if bannerList??]
		[#list bannerList as item]	
			<img src="${webimg}${item.content}" alt="${item.title}" title="${item.title}" class="noteimg" />
		[/#list]
	[/#if]	
	<div class="wordbox" [#if me == ''] onclick= "isMember('${base}',${cid?c},${pcid});" [/#if] >
		<textarea placeholder="说点什么吧" id="content" class="comment_textarea"></textarea>
		<div class="wordpiece">
			<a [#if me??] class="face" [#else] onclick= "isMember('${base}',${cid?c},${pcid});" [/#if]><img src="${baseimg}/imgs/default/smile.png" alt="" /></a>
			<button class="submitBtn firstedcomments" [#if me??] onclick="submitCommentHandler(this);" [#else] onclick= "isMember('${base}',${cid?c},${pcid});" [/#if] >发表</button>
			<p class="hidename"><input type="checkbox" id="isnick" /><label for="isnick">匿名</label></p>
		</div>
	</div>
	<div class="commentBox">
		<ul class="judgement">
			<li class="curjudge" onclick="loadlast(this);">最新</li>
			<li onclick="hotcomment(this);">最热</li>
		</ul>
		<ul class="commentList conCommentlist"  id="displayComment">
		</ul>
	</div>
	<div class="workplaceitem learnPlaceitem">
		<h3>自学心得体会</h3>
		<ul class="hotArticleList">
			[#if list??]
				[#list list as item]
					<li>
						<span class="normalicon"></span>
						<a href="${base}/m/article/[#if cid??]${cid?c}[#else]${(item.firstCid)?c}[/#if]/${(item.oid)?c}" title="${item.title}">
							${item.title}
						</a>
					</li>
				[/#list]
			[/#if]
		</ul>
		[#if me??]
			<a class="shareLearnExperience" href="${base}/m/my/contribute?cid=8192">分享自学心得</a>
		[#else]
			<a class="shareLearnExperience" href="${base}/m/login?cid=${cid?c}&pcid=${pcid}">分享自学心得</a>
		[/#if]
	</div>
	
	<script>
		var hotnum = 1;
		function hotcomment(obj){
			$(obj).addClass("curjudge").siblings().removeClass("curjudge");
			var url = "${base}/m/ajax/lifecomment/hotcomment";
			var json = {};
			json.cid='${(bean.cid)?c}';
			json.fromuid='${bean.uid}';
			json.pagenum=hotnum;
			$.get(url, json,function(data){
				if(data != ''){
					hotnum +=1;
					$("#displayComment").html(data);
					initPage();// 表情解析
					prizeBindEvent();
				}
			});
		}
		window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"2","bdPic":"","bdStyle":"0","bdSize":"16"},"share":{}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='${base}/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];
	</script>
</body>
</html>