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
	<script type="text/javascript" src="${basejs}/js/libs3/jquery.qqface.min.js"></script>
	<script type="text/javascript" src="${basejs}/js/mobile/lifeanswer.js"></script>
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
        				var desc = '我在看[${bean.title}]';
        				var link = "${base}/problem/detail/${(bean.cid)?c}/${(bean.oid)?c}";
        				var imgUrl= "${baseimg}/imgs/default/logo1.png";
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
		var channel = 'problemanswer';
		var type = 0;
		var href = window.location.href;
		var majorreply = '${majorreply??}';
		$(function(){
        	$('.face').qqFace({ 
		        assign:'content', //给输入框赋值 
		        path:'${baseimg}/imgs/face/qq/' //表情图片存放的路径 
		    });
		   	$("#hot").click(function(){
				$("#new").removeClass("curjudge");
				$("#hot").addClass("curjudge");
				num = 1;
				var url = base + "/m/ajax/lifeanswer/hotanswer";
				$.ajax({
					type:"GET",
					url:url,
					data: {fromuid:fromuid, cid:cid, pagenum:num},
					success:function(data){
						if(data.trim() == ""){
							if (num == 1) {
								[#if !majorreply??]$(".nothing").removeClass("hd");[/#if]
							} 
							$(window).unbind("scroll", defaultScrollHandler);
						}else{
							num += 1;
							$(".nothing").addClass("hd");
							$(diplayID).html(data);
							initPage();
							prizeBindEvent();
						}
					}
				});   
			});
			$("#new").click(function(){
				$("#hot").removeClass("curjudge");
				$("#new").addClass("curjudge");
				num = 1;
				var url = base + "/m/ajax/answer/"+cid+"_"+fromuid+"_{0}".format(num);
				$.ajax({
					type:"GET",
					url:url,
					data: {fromuid:fromuid, cid:cid, pagenum:num},
					success:function(data){
						console.log(data)
						if(data.trim() == ""){
							if (num == 1) {
								[#if !majorreply??]$(".nothing").removeClass("hd");[/#if]
							} 
							$(window).unbind("scroll", defaultScrollHandler);
						}else{
							num += 1;
							$(".nothing").addClass("hd");
							$(diplayID).html(data);
							initPage();
							prizeBindEvent();
						}
					}
				});   
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
	</style>
</head>
<body>
	<div class="someQuestion">
		<div class="questionTitle">
			${bean.title}
		</div>
		<br>
			<a href="javascript:;" class="report">举报</a>
				<i><a class="zan" id="btnprizeproblem_problempraise_${(me.oid?c)!"0"}_${(bean.cid)?c}_${bean.uid}" data-counter=".prizecounter">
				<img src="${baseimg}/imgs/default/thumb.png" data-isprize="${bean.isprize!'false'}"><span class="zancountjs">${bean.prizecount!'0'}</span></a></i>
		<div class="relatePiece">
			<span>${bean.username}&nbsp;&nbsp;&nbsp;[@p.mytime datetime="${bean.createtimeStr}"/]</span>
			<span class="replyNum">
					<i><span id="problemcommentcount">${bean.commentscount}</span>个回答</i>
		<div class="qsintroduce">${bean.content}</div>
			</span>
		</div>
	</div>
	<div class="commentBox qsNewquiz">
		<ul class="judgement">
			<li class="curjudge" id="new">最新</li>
			<li id="hot">最热</li>
			[#if me??]
				<a href="javascript:;" class="pubQuestion">我要回答</a>
			[#else]
				<a href="${base}/m/login?cid=${cid?c}&pcid=${pcid}" class="pubQuestion">我要回答</a>
			[/#if]
		</ul>
		<ul class="commentList" id="displayComment">
			[#if majorreply??]
				<li class="activeComment">
					<a href="#" class="qsUserimg">
						<img src="${baseimg}/imgs/default/experts.png" alt="">
					</a>
					<div class="commentitemRt replay_comment" data-uid="${majorreply.uid}" data-touseruid="${majorreply.fromuseruid}"  data-tousername="${majorreply.fromusername}"  data-useruid="${majorreply.useruid}">
						<a href="#" class="qsUsername">[#if majorreply.username??]${majorreply.username}[#else]专家团[/#if]</a>
						<span class="expert">专业点评</span>
						<div class="qsword">
							${majorreply.content}
						</div>
						<div class="wordBottom">
							<span class="qstime">${(majorreply.createtime).toString("M月dd日  HH:mm:ss")}</span>
							<div class="qsCommentOperate">
								<a class="zan" id="btnprizeproblem_answerpraise_${(me.oid?c)!"0"}_${cid?c}_${majorreply.uid}" data-counter=".prizecounter"><img src="${baseimg}/imgs/default/heart.png" data-isprize="${majorreply.isprize!'false'}"><span class="zancountjs">${majorreply.prizecount!'0'}</span></a>
								[#if me.uid == bean.fromuseruid]
									<a href="javascript:;" data-reply="replyopen" class="reply" onclick="reply(this)" >回复</a>
								[#else]
									<a href="javascript:;" class="reply hd">回复</a>
								[/#if]
								<a class="leftcommentss hd" onclick="showsecondmajor(this);" data-reply="true">展开<img src="${baseimg}/imgs/default/arrawDown.png" alt="展开" /></a>
								<a class="leftcomments hd" onclick="showcomment(this);" data-reply="true">展开<img src="${baseimg}/imgs/default/arrawDown.png" alt="展开" /></a>
							</div>
						</div>
					</div>
					<ul class="secondComment hd" id="secondmajor">
						[#if secondmajorreply?? && secondmajorreply?size>0]
						[#list secondmajorreply?reverse as item2]
							[#if item2.fromUser??]
								[#assign fromMan2=item2.fromUser/]
							[#elseif item2.fromVisitor??]
								[#assign fromMan2=item2.fromVisitor/]
							[/#if]
							<li class="ment">
					            <div class="qsUserimg">
									[#if item2.fromuseroid == 10]
										<img src="${baseimg}/imgs/default/experts.png">
									[#elseif item2.fromUser??]
										<img [#if fromMan2.getIsUser()]class="userborder"[/#if][#if "${fromMan2.useravatar}" == ""]src="${baseimg}/imgs/default/user.png"[#else]src="${fromMan2.useravatar}"[/#if] />
										[#if fromMan2.getIsUser()]<img src="${baseimg}/imgs/default/prince.png" class="problemprinceimg" alt="" />[/#if]
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
											<a class="zan" id="btnprizeproblem_answerpraise_${(me.oid?c)!"0"}_${cid?c}_${item2.uid}" data-counter=".prizecounter"><img src="${base}/imgs/default/heart.png" data-isprize="${item2.isprize!'false'}"><span class="zancountjs">${item2.prizecount!'0'}</span></a>
										</div>
									</div>
								</div>
							</li>
						[/#list]
					[/#if]
					</ul>
				</li>
			[/#if]
			<img class="nothing hd" src="${baseimg}/imgs/default/prompt.png" style="width: 370px;height: 211px;">
		</ul>
	</div>
	<!-- 回答问题弹框 -->
	<div class="problemmask"></div>
	<div class="problemmaskContent questionBox answerBox say">
		<a href="javascript:;" class="close">X</a>
		<div class="answer">
			回复@[@p.PrivateString content="${bean.username}" /]
			<i>${bean.title}</i>
		</div>
		<textarea placeholder="请写下你的回答" id="content"></textarea>
		<a class="cancelBtn submitBtn closes" >取消</a>
		<a class="submitBtn" onclick="submitCommentHandler(this);">发布</a>
		<div class="hidename1">
			<input type="checkbox" id="isnick">
		    <label for="hideName">匿名</label>
		</div>
	</div>
	<script type="text/javascript">
		$(function () {
			[#if secondmajorreply?? && secondmajorreply?size>0]
				$(".leftcommentss").removeClass("hd");
			[/#if]
			$(".pubQuestion").click(function () {
				if($(this).hasClass("pop-up")){
					return;
				}
				$(".problemmask").show();
				$(".problemmaskContent").show();
			});
			$(".close").click(function () {
				$(".problemmask").hide();
				$(".problemmaskContent").hide();
			});
			$(".closes").click(function () {
				$(".problemmask").hide();
				$(".problemmaskContent").hide();
			});
		});
	</script>
</body>
</html>