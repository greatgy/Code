[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Public.ftl" as p]
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="referrer" content="never" />
	<title>${title!'天才人生话题详情页'}</title>
	<link href="${basecss}/css/default/index.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" href="${basecss}/css/default/ckeditor.css" type="text/css">
	<script type="text/javascript" src="${basejs}/js/pages/comment-1.0.0.js"></script>
	<script type="text/javascript" src="${basejs}/js/pages/scroll.js"></script>
	<script type="text/javascript" src="${basejs}/js/libs3/jquery.qqface.min.js"></script>
	<script type="text/javascript" src="${basejs}/js/pages/lifesecondcomment.js"></script>
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
		var phone = '${phone}';
		var phoneurl = '${base}/phonelogin';
		var channel = 'topiccomments';
		var href = window.location.href;
		$(function(){
        	$('.face').qqFace({ 
		        assign:'content', //给输入框赋值 
		        path:'${baseimg}/imgs/face/qq/' //表情图片存放的路径 
		    });
		});
		
		 
		$(function(){
			//适配视频
			adaptVideo(".articleContent", "${phone}");//传入articleContent的class或者id
			//处理微信图片
			adaptWXphoto(".articleContent");//传入articleContent的class或者id
			
			$(".imglogin").click(function(){
				if($(this).parent().parent().hasClass("otherLink")){
					$("meta[name='referrer']").attr("content", "always");
				}
			});
		});
		
	</script>
	<style type="text/css">
		.bdshare-button-style0-16 a, .bdshare-button-style0-16 .bds_more{
    	        background-image: url(${baseimg}/imgs/icons_0_16.png)!important;
    	    }
		.contentRight {
		    display: inline-block;
		    width: 30%;
		    margin-left: 4%;
		    margin-bottom: 20px;
		    overflow: initial;
		}
		.hotArticle li {
		    margin-bottom: 20px;
		    position: relative;
		    margin-top: -52px;
		    height: 205px;
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
		}
		.articleContent table {
			width: 100%;
		    max-width: 100%;
		    margin-bottom: 15px;
		    border-spacing: 0;
		    empty-cells: show;
		    background-color: #fff;
		    border-collapse: collapse;
		    font-size: 16px;
		}
		.articleContent td {
			padding: 0 8px;
		    vertical-align: top;
		    border: 1px solid #ddd;
		    text-align: center;
		    color: #656b79;
		}
		
		.qqFace{margin-top:4px;background:#fff;padding:2px;border:1px #dfe6f6 solid;}
		.qqFace table td{padding:0px;}
		.qqFace table td img{cursor:pointer;border:1px #fff solid;}
		.qqFace table td img:hover{border:1px #0066cc solid;}
	</style>
	
</head>
<body>
	<div class="contentLeft">
		<ul class="judgement topicTab">
			<li class="curjudge" onclick="loadlast(this)">最新</li>
			<li onclick="loadhot(this)">最热</li>
		</ul>
		<div class="ajaxthink">
			<div class="topicDetailBox">
				<img src="${webimg}/${bean.imgbig}" alt="${bean.title}" title="${bean.title}" />
				<h3>
					<span>${bean.title}</span>
				</h3>
				${bean.content}
				<div class="topictips">
					<span>${bean.author}</span>
					<span>${(bean.createtime).toString("yyyy年MM月dd日 ")}</span>
					<span class="readTimes">阅读&nbsp;&nbsp;${bean.clickcount!'0'}</span>
				</div>
			</div>
			<div class="shareBox">
				<a class="littleicon" href="javascript:;" id="btnprize_topicpraise_${(me.oid?c)!"0"}_${(bean.cid)?c}_${bean.uid}" data-counter=".prizecounter">
					<img src="${baseimg}/imgs/default/heart.png" data-notcomment="true" data-isprize="${bean.isprize!'false'}"/>
					<span class="zancountjs">${bean.prizecount!"0"}</span>
				</a>
				<a class="littleicon" href="javascript:;" onclick="wantComment();">
					<img src="${baseimg}/imgs/default/discuss.png" />
					<span id="articlecommentcount">${bean.commentscount!"0"}</span>
				</a>
				<a href="javascript:;" [#if me??]id="btncollecttopic_${bean.uid}" data-iscollect="${bean.iscollect!'false'}" class="littleicon"[#else]class="littleicon pop-up" [/#if]>
					<img src="${baseimg}/imgs/default/collect.png" data-iscollect="${bean.iscollect!'false'}" class="liwaysimg02"/>
					<span id="articlecollectcount">${bean.collectcount!"0"}</span>
				</a>
				<div class="share">
					分享:
					<div class="bdsharebuttonbox bdshare-button-style0-16" data-bd-bind="1503546374732">
						<a href="#" class="bds_more" data-cmd="more"></a>
						<a href="#" class="bds_weixin" data-cmd="weixin" title="分享到微信"></a>
						<a href="#" class="bds_tsina" data-cmd="tsina" title="分享到新浪微博"></a>
						<a href="#" class="bds_qzone" data-cmd="qzone" title="分享到QQ空间"></a>
					</div>
				</div>
			</div>
			<div class="wordbox topicWordbox [#if me == ''] pop-up [/#if]">
				<textarea id="content" class="comment_textarea"></textarea>
				<div class="wordpiece">
					<a class="[#if me??] face [#else] pop-up [/#if]"><img src="${baseimg}/imgs/default/smile.png" alt="" /></a>
					<button class="submitBtn firstedcomments" [#if me??] onclick="submitCommentHandler(this);" [/#if]>发表</button>
					<p class="hidename"><input type="checkbox" id="isnick" /><label for="isnick">匿名</label></p>
				</div>
			</div>
			<div class="commentBox">
				<h3 class="allComments">
					全部评论
				</h3>
				<ul class="commentList conCommentlist" id="displayComment">
				</ul>
			</div>
		</div>
	</div>
	<div class="contentRight" style="margin-top:72px;">
		<ul class="hotArticle topTopic">
			<li>
				<span class="uptopicon">置顶</span>
				<a href="${base}/topic/${(topic.cid)?c}/${topic.oid}" title="${topic.title}">
					<img src="${webimg}${topic.imgmedium}" alt="${topic.title}" title="${topic.title}">
					<div class="description">
						${topic.title}
					</div>
				</a>
			</li>
		</ul>
		<div class="workplaceitem thinkitem">
			<h3>
				备选话题
				<a class="morelink" href="${base}/topic/review/moretopic?cid=${cid?c}">更多+</a>
			</h3>
			<ul class="hotArticleList">
				[#if reviewList??]
					[#assign i=1]
					[#list reviewList as item]
						<li>
							<span class="[#if i<4]redtopic[/#if] normalNum">${i}</span>
							<a href="${base}/topic/review/${(item.cid)?c}/${item.oid}" title="${item.title}">
								${item.title}
							</a>
							<span class="topicnews"><img src="${baseimg}/imgs/default/dthumb.png" alt="">${item.prizecount}</span>
						</li>
						[#assign i = i+1]
					[/#list]
				[/#if]
			</ul>
			<div class="topicvote">
				[#if me??]
					<a href="${base}/my/topic/contribute?cid=${cid?c}" class="shareLearnExperience">话题投稿</a>
				[#else]
					<a class="shareLearnExperience pop-up">话题投稿</a>
				[/#if]
				<p>获赞越多越可能成为正式话题</p>
			</div>
		</div>
		<div class="workplaceitem">
			<h3>
				往期话题
				[#if relateList?size>9]<a class="change" href="javascript:;" onclick="loadtopic();" id="changetopic">换一换<img src="${baseimg}/imgs/default/refresh.png" alt="换一批" title="换一批"></a>[/#if]
			</h3>
			<ul class="hotArticleList oldTopicsList">
				[#if relateList??]
					[#list relateList as item]
						<li>
							<a href="${base}/topic/${(item.cid)?c}/${item.oid}" title="${item.title}">
								${item.title}
							</a>
							<span class="topicnews"><img src="${baseimg}/imgs/default/dthumb.png" alt="">${item.prizecount}</span>
						</li>
					[/#list]
				[/#if]
			</ul>
		</div>
	</div>
<script>
		$(function(){
			window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"2","bdPic":"","bdStyle":"0","bdSize":"16"},"share":{}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src=${base}/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];
		});
		
		var topicnum = 2;
		// 话题换一换
		function loadtopic() {
			var	url = "${base}/ajax/topic/relatetopic";
			var json ={};
			json.pagenum=topicnum;
			json.cid='${cid?c}';
			$.get(url, json, function(data){
				if(data.trim() == ""){// 没有更多内容
					topicnum=1;
					$("#changetopic").click();
					return false;
				}
				$(".oldTopicsList").html(data);
				topicnum+=1;
			});
		}
		
		//获得最热话题
		function loadhot(obj){
			$(obj).addClass("curjudge").siblings().removeClass("curjudge");
			var url = "${base}/ajax/topic/hotthinking";
			var json = {};
			json.cid='${cid?c}';
			$.get(url, json,function(data){
				if(data != ''){
					$(".ajaxthink").html(data);
					num=1;
					temp=0;
					pagenum=1;
					fromuid = $("input[name='thinkingfromuid']").val();
					loadmore();
				}
			});
		}
		
		//获得最新话题
		function loadlast(obj){
			$(obj).addClass("curjudge").siblings().removeClass("curjudge");
			var url = "${base}/ajax/topic/lastthinking";
			var json = {};
			json.cid='${cid?c}';
			$.get(url, json,function(data){
				if(data != ''){
					$(".ajaxthink").html(data);
					num=1;
					temp=0;
					pagenum=1;
					fromuid = $("input[name='thinkingfromuid']").val();
					loadmore();
				}
			});
		}	
	</script>
</body>
</html>

