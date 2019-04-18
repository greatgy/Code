[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Public.ftl" as p]
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>${title!'天才人生'}</title>
	<meta name="Keywords" content="天才人生,效果检验详情页" />
	<meta name="Description" content="天才人生,效果检验详情页" />
	<link rel="stylesheet" href="${basecss}/css/mobile/default/index.css">
	<link rel="stylesheet" href="${basecss}/css/default/ckeditor.css" type="text/css">
	<script type="text/javascript" src="${basejs}/js/mobile/comment-1.0.0.js"></script>
	<script type="text/javascript" src="${basejs}/js/pages/scroll.js"></script>
	<script type="text/javascript" src="${basejs}/js/weixinshare.js"></script>
	<script type="text/javascript" src="${basejs}/js/libs3/jquery.qqface.min.js"></script>
	<script type="text/javascript" src="${basejs}/js/mobile/lifesecondcomment.js"></script>
	<script type="text/javascript" src="${basejs}/js/pages/video.js"></script>
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
            				var title = '${bean.title}——天才人生';
            				var desc = '${(bean.summary)?replace("\\s*|\t|\r|\n", "" , "r")}';
            				if (desc == ""){
            				    desc = "视频";
            				}
            				var link = "${base}/m/videoDetail/${cid?c}/${bean.uid}";
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
		var fromuid = '${bean.uid}';
		var cid = '${(bean.cid)?c}';
		var base = '${base}';
		var baseimg = '${baseimg}';
		var userimg = '${userimg}';
		var channel = 'videocomments';
		var type = 0;
		var href = window.location.href;
		$(function(){
		 //适配视频
			adaptVideo(".playvideo",true);//传入articleContent的class或者id
			var vid = document.getElementById("audioEle");
			if(vid != null){
				vid.onplay = function() {
				    $("#imgAudio").data("type","true");
				};
				vid.onpause = function() {
				   $("#imgAudio").data("type","false");
				};
			}
			 $(document).on('click', "#imgAudio", function(e){
				var audio = document.getElementById("audioEle");
				var imga = $("#imgAudio").data("type");
			 	if( imga == "true"){
					//暂停
					audio.pause();
			 		$("#imgAudio").data("type","false");
			 	}else{
					//播放(继续播放)
					audio.play();
			 		$("#imgAudio").data("type","true");
			 	}
			});
        	$('.face').qqFace({ 
		        assign:'content', //给输入框赋值 
		        path:'${baseimg}/imgs/face/qq/' //表情图片存放的路径 
		    });
		   	$("#hot").click(function(){
		   		$("#displayComment").find("li").remove(); 
				$("#new").removeClass("curjudge");
				$("#hot").addClass("curjudge");
				type=1;
				temp=0;
				num = 1;
				loadmore();
			});
			$("#new").click(function(){
				$("#displayComment").find("li").remove(); 
				$("#hot").removeClass("curjudge");
				$("#new").addClass("curjudge");
				type=0;
				temp=0;
				num = 1;
				loadmore();
			});
			var url = window.location.href;
			$(".report").attr("href","${base}/m/life/my/report?cid=${cid?c}&fromuid=${bean.uid}&title=${bean.title}&url="+url);
		});
	</script>
	<style type="text/css">
		.qqFace{margin-top:4px;background:#fff;padding:2px;border:1px #dfe6f6 solid;}
		.qqFace table td{padding:0px;}
		.qqFace table td img{cursor:pointer;border:1px #fff solid;}
		.qqFace table td img:hover{border:1px #0066cc solid;}
		.uploadUser .prinimg {
	       width: 30px;
		    height: 30px;
		    margin-left: -24px;
		    top: 0;
		    left: -138px;
		    margin-top: -38px;
		    margin-right: inherit;
		    border: 0;
		}
		.uploadUser {
		    display: inline-block;
		    position: absolute;
		    margin-left: 119px;
		    float: right;
		    font-size: 15px;
		    color: #333;
		}
	</style>
</head>
<body>
	<div class="contentLeft" style=" margin-top: -30px;">
		<div class="video">
			<h3>
				${bean.title}
			</h3>
			<div class="playvideo">
				${bean.content}

				<div class="statics">
					<span>
						<img src="${baseimg}/imgs/default/film.png" alt="播放" title="播放" />
						${bean.clickCount}次播放
					</span>
					<span>
						<a href="javascript:;" class="report"><img src="${baseimg}/imgs/default/report.png" alt="举报" title="举报" />举报</a>
					</span>
				</div>
			</div>
		</div>
		<div class="wordbox conWordbox" [#if me == ''] onclick= "isMember('${base}',${cid?c},${pcid});" [/#if] >
			<textarea  id="content" class="comment_textarea"></textarea>
			<div class="wordpiece">
				<a [#if me??] class="face" [#else] onclick= "isMember('${base}',${cid?c},${pcid});" [/#if]><img src="${baseimg}/imgs/default/smile.png" alt="" /></a>
				<button class="submitBtn firstedcomments" [#if me??] onclick="submitCommentHandler(this);" [#else] onclick= "isMember('${base}',${cid?c},${pcid});" [/#if]>发表</button>
				<p class="hidename"><input type="checkbox" id="isnick"><label for="isnick">匿名</label></input></p>
			</div>
		</div>
		<div class="commentBox">
			<ul class="judgement">
				<li class="curjudge" id="new">最新</li>
				<li id="hot">最热</li>
			</ul>
			<!-- 评论部分 -->
			<ul class="commentList conCommentlist" id="displayComment">
				[#if majorreply??]
				<li class="activeComment">
					<a href="#" class="qsUserimg">
						<img src="${baseimg}/imgs/default/experts.png" alt="">
					</a>
					<div class="commentitemRt replay_comment" data-uid="${majorreply.uid}" data-touseruid="${majorreply.fromuseruid}"  data-tousername="${majorreply.fromusername}"  data-useruid="${majorreply.useruid}">
						<a href="#" class="qsUsername">${majorreply.username}</a>
						<span class="expert">专业点评</span>
						<div class="qsword">
							${majorreply.content}
						</div>
						<div class="wordBottom">
							<span class="qstime">${(majorreply.createtime).toString("M月dd日  HH:mm:ss")}</span>
							<div class="qsCommentOperate">
								<a class="zan" id="btnprize_commentspraise_${(me.oid?c)!"0"}_${cid?c}_${majorreply.uid}" data-counter=".prizecounter"><img src="${baseimg}/imgs/default/heart.png" data-isprize="${majorreply.isprize!'false'}"><span class="zancountjs">${majorreply.prizecount!'0'}</span></a>
								[#if me.uid == bean.useruid]
									<a href="javascript:;" data-reply="replyopen" class="reply" onclick="reply(this)" >回复</a>
								[#else]
									<a href="javascript:;" class="reply hd">回复</a>
								[/#if]
								[#if secondmajorreply?size>0]<a class="leftcomments" onclick="showsecondmajor(this);" data-reply="true">展开<img src="${baseimg}/imgs/default/arrawDown.png" alt="展开" /></a>[#else]<a class="leftcomments hd" onclick="showcomment(this);" data-reply="true">展开<img src="${baseimg}/imgs/default/arrawDown.png" alt="展开" /></a>[/#if]
							</div>
						</div>
					</div>
					<ul class="secondComment hd" id="secondmajor">
						[#if secondmajorreply?size>0]
						[#list secondmajorreply?reverse as item2]
							[#if item2.fromUser??]
								[#assign fromMan2=item2.fromUser/]
							[#elseif item2.fromVisitor??]
								[#assign fromMan2=item2.fromVisitor/]
							[/#if]
							<li class="ment">
					            <div class="userimg">
									[#if item2.fromuseroid == 10]
										<img src="${baseimg}/imgs/default/experts.png">
									[#elseif item2.fromUser??]
										<img [#if fromMan2.getIsUser()]class="userborder"[/#if][#if "${fromMan2.useravatar}" == ""]src="${baseimg}/imgs/default/user.png"[#else]src="${fromMan2.useravatar}"[/#if] />
										[#if fromMan2.getIsUser()]<img src="${baseimg}/imgs/default/prince.png" class="princeimg" alt="" />[/#if]
									[#else]
										<img src="${fromMan2.useravatar}" />					
									[/#if]
								</div>
								<div class="commentitemRt" data-topuid="${firstuid}" data-uid="${item2.uid}" data-touseruid="${item2.fromuseruid}"  data-tousername="${item2.fromusername}" data-useruid="${item2.useruid}">
									<div class="qsUsername">
						                [#if item2.fromUser??]
						               		[@p.PrivateString content="${item2.fromusername}" /]
										[#else]
											[@p.PrivateString content="${fromMan2.username}" /]
										[/#if]
										<a id="deletecomment_${item2.uid}_${useruid}" [#if useruid == item2.useruid || useruid == item2.fromuseruid]class="delete"[#else]class="hd"[/#if]><img src="${baseimg}/imgs/default/crush.png"/></a>
									</div>
									<div class="qsword">
										${item2.content!''}
									</div>
									<div class="wordBottom">
										<span class="qstime">${(item2.createtime).toString("M月dd日  HH:mm:ss")}</span>
										<div class="qsCommentOperate">
											<a class="zan" id="btnprize_commentspraise_${(me.oid?c)!"0"}_${cid?c}_${item2.uid}" data-counter=".prizecounter"><img src="${base}/imgs/default/heart.png" data-isprize="${item2.isprize!'false'}"><span class="zancountjs">${item2.prizecount!'0'}</span></a>
										</div>
									</div>
								</div>
							</li>
						[/#list]
					[/#if]
					</ul>
				</li>
				[/#if]
			</ul>
			<div class="page"></div>
		</div>
	</div>
	<div class="contentRight" style=" margin-top: -10px;">
		<div class="workplaceitem">
			<h3>
				相关视频
			</h3>
			<ul class="relateVideoList">
			[#if relateVideoList??]
				[#list relateVideoList as item]	
						<li>
							<a href="${base}/m/videoDetail/${cid?c}/${item.uid}">
								<img class="talentimg" src="${webimg}${item.imgmedium}" alt="${item.title}" title="${item.title}">
								<div class="play">
									<img src="${baseimg}/imgs/default/playbtn.png" alt="播放" title="播放">
								</div>
							</a>
							<div class="talentDetail">
								<div class="videoTitle">${item.title}</div>
								<div class="videoRelates">
									<span class="looktimes">
										<img src="${baseimg}/imgs/default/audience.png" alt="">
										${item.clickCount}人
									</span>
									<span class="relateTime">[@p.mytime datetime="${item.createtimeStr}"/]</span>
								</div>
							</div>
						</li>
				[/#list]
			[/#if]
			</ul>
		</div>
	</div>
	<script>
		
		
	</script>
</body>
</html>