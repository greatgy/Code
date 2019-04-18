[#ftl]
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>${title!'天才人生'}</title>
	<link rel="stylesheet" href="${basecss}/css/mobile/default/detail.css">
	<link rel="stylesheet" href="${basecss}/css/default/ckeditor.css" type="text/css">
	<script type="text/javascript" src="${basejs}/js/mobile/comment-1.0.0.js"></script>
	<script type="text/javascript" src="${basejs}/js/pages/scroll.js"></script>
	<script type="text/javascript" src="${basejs}/js/libs3/jquery.qqface.min.js"></script>
	<script type="text/javascript" src="${basejs}/js/mobile/lifecomment.js"></script>
	<script type="text/javascript" src="${basejs}/js/pages/video.js"></script>
	<script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
	<script type="text/javascript" src="${basejs}/js/weixinshare.js"></script>
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
		var phoneurl = '${base}/phonelogin';
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
		    /*margin-bottom: 20px;*/
		}
		.articleContent img {
			max-width: 100%;
		    margin: 15px auto;
		    width: auto !important;
    		height: auto!important;
		    text-indent: inherit;
		    text-align: center;
		}
		.aimItem p {
		    line-height: 2.2rem;
		}
		.aimItem img {
			margin: 5px auto 15px;
		    clear: both;
		    max-height: 350px;
		    overflow: hidden;
		}
		.isnick {
		    display: inline-block;
		    float: right;
		    margin-right: 2%;
		}
		.isnick input[type="checkbox"] {
		    vertical-align: bottom;
		    width: auto;
		    padding: 0;
		    line-height: normal;
		    height: auto;
	    }
	    @media screen and (min-width: 768px) and (max-width: 1024px) {
	    	.aimItem .right{
		    	float: right;
	    		margin-left: 20px;
	    	}
	    	.aimItem .left {
			    float: left;
			    margin-right: 20px;
			}
	    }
	</style>
</head>
<body>
	<div class="articleContent aimContent">
		${bean.content}			
		<div class="shareBox lifeshare">
			<a class="littleicon" href="javascript:;" id="btnprize_contentpraise_${(me.oid?c)!"0"}_${(bean.cid)?c}_${bean.uid}" data-counter=".prizecounter">
				<img src="${baseimg}/imgs/default/heart.png" data-notcomment="true" data-isprize="${bean.isprize!'false'}"/>
				<span class="zancountjs">${bean.prizecount!"0"}</span>
			</a>
			<a class="littleicon" href="javascript:;" onclick="wantComment();">
				<img src="${baseimg}/imgs/default/discuss.png" />
				<span id="articlecommentcount">${bean.commentscount!"0"}</span>
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
	</div>
	<div class="say lifesay" [#if me == ''] onclick= "isMember('${base}',${cid?c},${pcid});" [/#if] >
		<h4>有话要说</h4>
		<textarea placeholder="说点什么吧" id="content" class="comment_textarea"></textarea>
		<div class="wordpiece">
			<a href="javascript:;" [#if me??] class="face" [#else] onclick= "isMember('${base}',${cid?c},${pcid});" [/#if]><img src="${baseimg}/imgs/default/smile.png" alt=""></a>
			<button class="submitBtn firstedcomments" [#if me??] onclick="submitCommentHandler(this);" [#else] onclick= "isMember('${base}',${cid?c},${pcid});" [/#if]>>发表</button>
			<p class="isnick"><input type="checkbox" id="isnick"><label for="isnick">匿名</label></input></p>
		</div>
	</div>
	<ul class="judgement">
		<li class="curjudge border" onclick="loadlast(this);" style="border-right: 3px solid #999;padding-right:8px;">最新</li>
		<li onclick="hotcomment(this);">最热</li>
	</ul>
	<div class="commentList" id="displayComment">
	</div>
	
	<script>
		var hotnum = 1;
		function hotcomment(obj){
			hotnum = 1;
			$(obj).addClass("curjudge").siblings().removeClass("curjudge");
			var url = "${base}/m/ajax/lifecomment/hotcomment";
			var json = {};
			json.cid=${(bean.cid)?c};
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