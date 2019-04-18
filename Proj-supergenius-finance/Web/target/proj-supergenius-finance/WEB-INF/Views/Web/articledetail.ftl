[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Public.ftl" as p]
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="referrer" content="never" />
	<title>${title!'天财评论详情页'}</title>
	<link href="${basecss}/css/default/detail.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" href="${basecss}/css/default/ckeditor.css" type="text/css">
	<script type="text/javascript" src="${basejs}/js/pages/datacollect.js"></script>
	<script type="text/javascript" src="${basejs}/js/pages/comment-1.0.0.js"></script>
	<script type="text/javascript" src="${basejs}/js/pages/scroll.js"></script>
	<script type="text/javascript" src="${basejs}/js/libs3/jquery.qqface.min.js"></script>
	<script type="text/javascript" src="${basejs}/js/pages/financecomment.js"></script>
	<script type="text/javascript" src="${basejs}/js/pages/video.js"></script>
	<script type="text/javascript" src="${basejs}/js/pages/wxphoto.js"></script>
	[#-- 微信分享 --]
	<script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
	<script type="text/javascript">
		$(function(){
			var url=encodeURIComponent(location.href.split('#')[0]);
			$.ajax({
					type:"GET",
					url:"${base}/ajax/wxshare?url="+url,
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
				var title = '[#if bean.isoriginal == 1][原创][/#if][#if bean.kind == 1][视频][#elseif bean.kind == 2][图片][/#if]${bean.title}——天财评论';		
				var desc = '${(bean.summary)?replace("\\s*|\t|\r|\n", "" , "r")}';
				var link = "${base}/article/${bean.cid}/${(bean.oid)?c}";
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
			imglittle: "${me.avatar}",
			useravatar:"${me.useravatar}",
			oid:"${(me.oid?c)!'0'}",
			uid:"${me.uid}",
			defaultImg: "${baseimg}/imgs/default/user.png",
			isuser: "${me.isUser}"
		}
		var fromuid = '${bean.uid}';
		var cid = '${bean.firstCid}';
		var base = '${base}';
		var baseimg = '${baseimg}';
		var userimg = '${userimg}';
		var phone = '${phone}';
		var phoneurl = '${base}/phonelogin';
		$(function(){
			$("#info"+${catalogue.cid}).addClass("current").siblings().removeClass("current");
		});
		$(function(){
        	$('.face').qqFace({ 
		        assign:'content', //给输入框赋值 
		        path:'${baseimg}/imgs/face/qq/' //表情图片存放的路径 
		    });
		});
		
		function subscribeHandler(useruid) {
			$.ajax({
					type:"GET",
					url:"${base}/ajax/subscribe/"+useruid,
					success:function(data){
						if(data.result == true) {
							$(".addsub").addClass("hd");
							$(".cancelsub").removeClass("hd");
						}
					}
				});
		}
		
		function cancelsubscribeHandler(useruid) {
			$.ajax({
					type:"GET",
					url:"${base}/ajax/cancelsubscribe/"+useruid,
					success:function(data){
						if(data.result == true) {
							$(".cancelsub").addClass("hd");
							$(".addsub").removeClass("hd");
						}
					}
				});
		}
		
		 
		$(function(){
			//去除articleContent中table的内联样式
        	$(".articleContent").find("table").removeAttr("style");
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
		.articleframe {
			border: 1px solid #ddd;
    		padding: 20px 4%;
			text-align: center;
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
		em, strong, big {
			font-weight: bold;
		}
		i {
			font-style: italic;
		}
		u {
		    text-decoration: underline;
		}
		s { 
		    text-decoration: line-through;
		}
		sub { 
		    vertical-align: sub;
		    font-size: smaller;
		}
		sup { 
		    vertical-align: super;
		    font-size: smaller;
		}
		.qqFace{margin-top:4px;background:#fff;padding:2px;border:1px #dfe6f6 solid;}
		.qqFace table td{padding:0px;}
		.qqFace table td img{cursor:pointer;border:1px #fff solid;}
		.qqFace table td img:hover{border:1px #0066cc solid;}
		
		.articleContent ul{
		    padding-left: 40px;
		}
		.articleContent ol li {
		    list-style: decimal;
		}
		.articleContent ul li{
		    list-style: disc;
		}
		.articleContent ol li,.articleContent ul li{
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
				<div class="articleframe">
					<h3>${bean.title}</h3>
					<div class="detailTips">
						[#if "${bean.createtime}" != ""]
							<span class="time">发布时间:${(bean.createtime).toString("yyyy年MM月dd日 ")}</span>
						[/#if]
						[#if "${bean.origin}" != ""]
							<span class="source">来源：${bean.origin}</span>
						[/#if]
						[#if "${bean.author}" != ""]
							<span class="author">作者：${bean.author}</span>
						[/#if]
						<span class="look">浏览量：${bean.clickcount!'0'}</span>
					</div>
					<div class="articleContent">
						${bean.content}
						<span style="font-size:12px;color:#999999;float: left;">声明：该文观表作者点仅代本人，与天财评论无关。</span>
					</div>
				</div>
				<div class="shareBox">
					<a class="littleicon" href="javascript:;" id="btnprize_finance_${(me.oid?c)!"0"}_${bean.cid}_${bean.uid}" data-counter=".prizecounter">
						<img src="${baseimg}/imgs/default/heart.png" data-notcomment="true" data-isprize="${bean.isprize!'false'}"/>
						<span class="zancountjs">${bean.prizecount!"0"}</span>
					</a>
					<a class="littleicon" href="#say" onclick="wantComment();">
						<img src="${baseimg}/imgs/default/discuss.png" />
						<span id="articlecommentcount">${bean.commentscount!"0"}</span>
					</a>
					[#if phone??]
						<a  [#if me??] href="javascript:;" id="btncollect${bean.uid}" data-iscollect="${bean.iscollect!'false'}" class="littleicon"[#else] href="${base}/phonelogin" class="littleicon" [/#if]>
							<img src="${baseimg}/imgs/default/collect.png" data-iscollect="${bean.iscollect!'false'}" class="liwaysimg02"/>
							<span id="articlecollectcount">${bean.collectcount!"0"}</span>
						</a>
					[#else]
						<a  [#if me??] href="javascript:;" id="btncollect${bean.uid}" data-iscollect="${bean.iscollect!'false'}" class="littleicon"[#else] href="javascript:;" class="littleicon pop-up" [/#if]>
							<img src="${baseimg}/imgs/default/collect.png" data-iscollect="${bean.iscollect!'false'}" class="liwaysimg02"/>
							<span id="articlecollectcount">${bean.collectcount!"0"}</span>
						</a>
					[/#if]
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
				<div class="say">
					<div class="discussTitle">
						有话要说
						<span class="readtimes">${bean.commentcount}&nbsp;人讨论&nbsp;&nbsp;&nbsp;&nbsp;${bean.clickcount}&nbsp;人阅读</span>
					</div>
					<textarea id="content" class="comment_textarea"></textarea>
					<div class="saydetail">
						<a class="face"><img src="${baseimg}/imgs/default/smile.png" alt="" /></a>
						[#if me??]
							<a href="javascript:;" onclick="submitCommentHandler(this);" class="publishBtn">发表</a>
							<p class="isnick"><input type="checkbox" id="isnick"><label for="isnick">匿名</label></input></p>
						[#else]
							[#if phone ??]
								<a href="javascript:;" onclick="window.location.href='${base}/phonelogin'" class="publishBtn">发表</a>
								<p class="isnick"><input type="checkbox" id="isnick"><label for="isnick">匿名</label></input></p>
							[#else]
								<a href="javascript:;" class="publishBtn pop-up">发表</a>
								<p class="isnick pop-up"><input type="checkbox" id="isnick"><label for="isnick">匿名</label></input></p>
							[/#if]
						[/#if]
					</div>
				</div>
				<!-- 评论部分 -->
				<div class="commentList" id="displayComment">
					<img class="nothing hd" src="${baseimg}/imgs/default/prompt.png" style="width:481px;heigth:211px">
				</div>
				<div class="page"></div>
			</div>
		</div>
		<div class="contentRight">
			[#if author??]
				<div class="authorbox">
					<div class="author">
						<div class="authorPiece">
							<a href="${base}/finance/uhome/${author.uid}">
								<img src="[#if author.useravatar ??]${author.useravatar}[#else]${baseimg}/imgs/default/author.png[/#if]" alt="" class="authorimg [#if author.getIsUser()] userborder[/#if]">
								[#if author.getIsUser()]<img src="${baseimg}/imgs/default/prince.png" class="princeimg" alt="" />[/#if]
							</a>
							<div class="resAuthor"">						
								<a href="${base}/finance/uhome/${author.uid}" class="authorName">${author.username}</a>
								<span>文章<a href="javascript:;">${count}</a></span>
							</div>
						</div>
						[#if me??]
							[#if me.uid != author.uid]
							<div [#if isSubscribe == true] class="addsub hd" [#else] class="addsub " [/#if]>
								<a href="javascript:;" onclick="subscribeHandler('${author.uid}');">+</a>
								订阅
							</div>
							<div [#if isSubscribe == true] class="cancelsub" [#else] class="cancelsub hd" [/#if]>
								<a href="javascript:;" onclick="cancelsubscribeHandler('${author.uid}');">-</a>
								取消订阅
							</div>
							[/#if]
						[#else]
							[#if me.uid != author.uid]
							<div [#if isSubscribe == true] class="addsub hd" [#else] class="addsub pop-up" [/#if]>
								<a href="javascript:;" onclick="subscribeHandler('${author.uid}');">+</a>
								订阅
							</div>
							[/#if]
						[/#if]
						
						<p>${author.summary}</p>
						<div class="totalArt">共${count}篇文章</div>
					</div>
					<div class="newArticle">
						<h5>最新文章</h5>
						<ul class="newArticles">
							[#list hisArticle as item]
								<li>
									<a href="${base}/article/${item.firstCid}/${(item.oid)?c}">
										[#if item.isoriginal == 1]
											<span class="original">[原创]</span>
										[/#if]
		                                [#if item.kind==1]
		                                	<span class="videotype">【视频】</span>
		                                [#elseif item.kind==2]
		                                	<span class="pictype">【图片】</span>
		                                [/#if]
										${item.title}
									</a>
								</li>
							[/#list]
						</ul>
					</div>
				</div>
			[#else]
				<!-- 游客 -->
				<div class="authorbox">
					<div class="author">
						<a href="javascript:;"><img src="${baseimg}/imgs/default/visitorimg.png" alt="" class="authorimg"></a>
						<a href="javascript:;" class="authorName">游客</a>
						<p>这位投稿者太神秘了，什么都没留下~</p>
					</div>
				</div>
			[/#if]	
			<div class="rightItem">
				<h4><i></i>热门搜索</h4>
				<ul class="tags">
			        [#if HotLabel??]
						[#list HotLabel as label]
				        	<li><a href="${base}/search?keyword=${label.content}&uid=${label.uid}">${label.content}</a></li>
				        [/#list]
			   		[/#if]
			    </ul>
			</div>
			<div class="rightItem">
				[#if RelatecarticleList?? && RelatecarticleList?size>0]
					<h4><i></i>相关文章</h4>
					[#assign bottomlist = RelatecarticleList]
				[#elseif HotArticle?? && HotArticle?size>0]
					<h4><i></i>热门文章</h4>
					[#assign bottomlist = HotArticle]
				[/#if]
				<ul class="hotArticle">
			        [#if bottomlist?? && bottomlist?size>0]
						[#assign i = 0]
						[#list bottomlist as article]
							[#assign i = i+1 ]
							[#if i <= 3]
						        <li>
						        	<a href="${base}/article/${article.cid}/${(article.oid)?c}">
						        		<img src="${webimg}${article.imgmedium}" class="fourimg" alt="${article.title}" title="${article.title}" />
						        		<div class="title">
											${article.title}
											<span class="flashtime">[@p.mytime datetime="${article.createtimeStr}"/]
										</div>
						        	</a>
						        </li>
					        [#else]
								<li>
									<a href="${base}/article/${article.cid}/${(article.oid)?c}">
										[#if article.isoriginal == 1]
											<span class="original">[原创]</span>
										[/#if]
										[#if article.kind == 1]
											<span class="videotype">[视频]</span>
										[#elseif article.kind == 2]
											<span class="pictype">[图片]</span>
										[/#if]
										${article.title}
									</a>
									<span class="flashtime">[@p.mytime datetime="${article.createtimeStr}"/]
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
