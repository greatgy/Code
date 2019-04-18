[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Public.ftl" as p]
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="referrer" content="never" />
	<title>${title!'天才人生详情页'}</title>
	<link href="${basecss}/css/mobile/default/detail.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" href="${basecss}/css/default/ckeditor.css" type="text/css">
	<script type="text/javascript" src="${basejs}/js/pages/datacollect.js"></script>
	<script type="text/javascript" src="${basejs}/js/mobile/comment-1.0.0.js"></script>
	<script type="text/javascript" src="${basejs}/js/pages/scroll.js"></script>
	<script type="text/javascript" src="${basejs}/js/libs3/jquery.qqface.min.js"></script>
	<script type="text/javascript" src="${basejs}/js/mobile/lifecomment.js"></script>
	<script type="text/javascript" src="${basejs}/js/pages/video.js"></script>
	<script type="text/javascript" src="${basejs}/js/pages/wxphoto.js"></script>
	[#-- 微信分享 --]
	<script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
	<script type="text/javascript">
		$(function(){
			var url=encodeURIComponent(location.href.split('#')[0]);
			$.ajax({
					type:"GET",
					url:"${financebase}/ajax/wxshare?url="+url,
					success:function(data){
						wx.config({
						    debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
						    appId: data.appId, // 必填，服务号的唯一标识，此处填写服务号corpid
						    timestamp:data.timestamp , // 必填，生成签名的时间戳
						    nonceStr: data.nonceStr, // 必填，生成签名的随机串
						    signature: data.signature,// 必填，签名，见附录1
						    jsApiList: ['onMenuShareAppMessage','onMenuShareTimeline','onMenuShareQQ','onMenuShareQZone'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录3
						});
					}
				});
		});
		wx.ready(function(){
				var title = '[#if bean.isoriginal == 1][原创][/#if][#if bean.kind == 1][视频][#elseif bean.kind == 2][图片][/#if]${bean.title}——天才人生';
				var desc = '${(bean.summary)?replace("\\s*|\t|\r|\n", "" , "r")}';
				var link = "${base}/article/[#if cid??]${cid?c}[#else]${(bean.firstCid)?c}[/#if]/${(bean.oid)?c}";
				var imgUrl= "${webimg}${bean.imgmedium}";
				//分享给朋友 
				wx.onMenuShareAppMessage({
				    title: title, // 分享标题
				    desc: desc, // 分享描述
				    link: link, // 分享链接，该链接域名必须与当前企业的可信域名一致
				    imgUrl: imgUrl, // 分享图标
				    type: 'link', // 分享类型,music、video或link，不填默认为link
				    dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
				    success: function () {
				        // 用户确认分享后执行的回调函数
				    },
				    cancel: function () {
				        // 用户取消分享后执行的回调函数
				    }
				});
				//分享到朋友圈
				wx.onMenuShareTimeline({
				    title: title, // 分享标题
				    link: link, // 分享链接，该链接域名必须与当前企业的可信域名一致
				    imgUrl: imgUrl, // 分享图标
				    success: function () {
				        // 用户确认分享后执行的回调函数
				    },
				    cancel: function () {
				        // 用户取消分享后执行的回调函数
				    }
				});
				//分享到QQ
				wx.onMenuShareQQ({
					title: title, // 分享标题
					desc: desc, // 分享描述
					link: link, // 分享链接
					imgUrl: imgUrl, // 分享图标
					success: function () {
					// 用户确认分享后执行的回调函数
					},
					cancel: function () {
					// 用户取消分享后执行的回调函数
					}
				});
				//分享到qq空间
				wx.onMenuShareQZone({
					title: title, // 分享标题
					desc: desc, // 分享描述
					link: link, // 分享链接
					imgUrl: imgUrl, // 分享图标
					success: function () {
					// 用户确认分享后执行的回调函数
					},
					cancel: function () {
					// 用户取消分享后执行的回调函数
					}
				});
				wx.error(function(res){
				    console.log(res)
				});
		});
		
	</script>
	
	<script type="text/javascript">
		var me = {
			showname: "${me.username!'游客'}",
			useravatar:"${me.useravatar}",
			oid:"${(me.oid?c)!'0'}",
			uid:"${me.uid}",
			defaultImg: "${baseimg}/imgs/default/user.png",
			isuser: "${me.isUser}"
		}
		var commentuid='${commentuid!"com"}';
		var fromuid = '${bean.uid}';
		var cid = '${(bean.firstCid)?c}';
		var base = '${base}';
		var baseimg = '${baseimg}';
		var userimg = '${userimg}';
		var phone = '${phone}';
		var phoneurl = '${base}/phonelogin';
		var channel = 'articlecomments';
		var href = window.location.href.split("?commentuid")[0];
		$(function(){
		console.log(href);
		console.log(commentuid);
        	$('.face').qqFace({
		        assign:'content', //给输入框赋值 
		        path:'${baseimg}/imgs/face/qq/' //表情图片存放的路径 
		    });
		});
		 
		$(function(){
			//去除articleContent中table的内联样式
        	$(".articleContent").find("table").removeAttr("style");
			//适配视频
			adaptVideo(".articleContent", "${phone}");//传入articleContent的class或者id
			//处理微信图片
			adaptWXphoto(".articleContent");//传入articleContent的class或者id
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
		.isnick {
		    display: inline-block;
		    float: right;
		    margin-right: 2%;
		}
		.articleContent img {
			display:block;
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
		
		.articleContent ul, .articleContent ol{
		    padding-left: 40px;
		}
		.articleContent ol li {
		    list-style: decimal;
		}
		.articleContent ul li{
		    list-style: disc;
		}
		.articleContent ol li, .articleContent ul li{
		    margin-bottom: 15px;
		    font-size: 1rem;
		    line-height: 24px;
		    text-align: left;
		}
	</style>
	
</head>
<body>
	<div class="contentLeft articleComment">
		<div class="articleBox">
			<h3>${bean.title}</h3>
			<div class="detailTips">
			<span class="time">发布时间:${(bean.createtime).toString("yyyy年MM月dd日 ")}</span>
			[#if "${bean.origin}" != "" && cid?c != '274877906944']
				<span class="source">来源：${bean.origin}</span>
			[/#if]
			[#if "${bean.author}" != ""]
				<span class="author">作者：${bean.author}</span>
			[/#if]
			<span class="look">浏览量：${bean.clickcount!'0'}次</span>
		</div>
		<div class="articleContent">
			${bean.content}
			<span style="font-size:12px;color:#999999;float: left;margin-top: 10px;">声明：本文观点仅代表作者本人，与本网站无关。</span>
		</div>
		<div class="shareBox">
			<a class="littleicon" href="javascript:;" id="btnprize_articlepraise_${(me.oid?c)!"0"}_[#if cid??]${cid?c}[#else]${(bean.firstCid)?c}[/#if]_${bean.uid}" data-counter=".prizecounter">
				<img src="${baseimg}/imgs/default/heart.png" data-notcomment="true" data-isprize="${bean.isprize!'false'}"/>
				<span class="zancountjs">${bean.prizecount!"0"}</span>
			</a>
			<a class="littleicon" href="javascript:;" onclick="wantComment();">
				<img src="${baseimg}/imgs/default/discuss.png" />
				<span id="articlecommentcount">${bean.commentscount!"0"}</span>
			</a>
			<a id="btncollectarticle_${bean.uid}" data-iscollect="${bean.iscollect!'false'}" class="littleicon" [#if me??] href="javascript:;"[#else] href="${base}/m/login?cid=${cid?c}&pcid=${pcid}" [/#if]>
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
		<div class="say" [#if me == ''] onclick= "isMember('${base}',${cid?c},${pcid});" [/#if]>
			<h4>有话要说</h4>
			<textarea placeholder="说点什么吧" id="content" class="comment_textarea"></textarea>
			<div class="wordpiece">
				<a [#if me??] class="face" [#else] onclick= "isMember('${base}',${cid?c},${pcid});" [/#if]><img src="${baseimg}/imgs/default/smile.png" alt="" /></a>
				<button class="submitBtn firstedcomments" [#if me??] onclick="submitCommentHandler(this);" [#else] onclick= "isMember('${base}',${cid?c},${pcid});"  [/#if]>发表</button>
				<p class="isnick"><input type="checkbox" id="isnick"><label for="isnick">匿名</label></input></p>
			</div>
		</div>
		<!-- 评论部分 -->
		<div class="commentList"  id="displayComment">
		</div>
	</div>
	<div class="contentRight">
		<div class="rightItem rightItemBox">
			[#if relateArticleList?size>0]
				[#if cid == 274877906944]
					<h3>相关足迹</h3>
				[#else]
					<h3>相关文章</h3>
				[/#if]
				[#assign bottomlist = relateArticleList]
			[#elseif hotArticleList?size>0]
				<h3>热门文章</h3>
				[#assign bottomlist = hotArticleList]
			[/#if]
			<ul class="relateArticles">
				[#if bottomlist?size>0]
					[#assign i = 0]
					[#list bottomlist as article]
						[#assign i = i+1 ]
						[#if i <= 3 && cid != 274877906944]
					        <li>
					        	<a href="${base}/article/[#if cid??]${cid?c}[#else]${(article.firstCid)?c}[/#if]/${(article.oid)?c}">
					        		<img src="${webimg}${article.imgmedium}" class="fourimg" alt="${article.title}" title="${article.title}" />
					        		<div class="title">
					        			[#if article.isoriginal == 1]
											<span class="original">[原创]</span>
										[/#if]
										[#if article.kind == 1]
											<span class="videotype">[视频]</span>
										[#elseif article.kind == 2]
											<span class="pictype">[图片]</span>
										[/#if]
										${article.title}
									</div>
					        	</a>
					        </li>
				        [#else]
							<li>
								<a href="${base}/article/[#if cid??]${cid?c}[#else]${(article.firstCid)?c}[/#if]/${(article.oid)?c}">
					        		<img src="${webimg}${article.imgmedium}" class="fourimg" alt="${article.title}" title="${article.title}" />
					        		<div class="title">
										[#if article.isoriginal == 1]
											<span class="original">[原创]</span>
										[/#if]
										[#if article.kind == 1]
											<span class="videotype">[视频]</span>
										[#elseif article.kind == 2]
											<span class="pictype">[图片]</span>
										[/#if]
										${article.title}
									</div>
								</a>
							</li>
						[/#if]
					[/#list]
			    [/#if]
			</ul>
		</div>
	</div>
<script>
	window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"2","bdPic":"","bdStyle":"0","bdSize":"16"},"share":{}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='${base}/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];
</script>
</body>
</html>

