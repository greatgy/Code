[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Public.ftl" as p]
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>${title!'天才人生'}</title>
	<link rel="stylesheet" href="${basecss}/css/mobile/default/index.css">
	<link rel="stylesheet" href="${basecss}/css/default/upload.css">
	<script type="text/javascript" src="${basejs}/js/pages/scroll.js"></script>
	<script type="text/javascript" src="${basejs}/js/mobile/comment-1.0.0.js"></script>
	<script type="text/javascript" src="${basejs}/js/libs3/jquery.qqface.min.js"></script>
	<script type="text/javascript" src="${basejs}/js/mobile/lifeessay.js"></script>
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
                            var title = "${title!'天才人生'}";
                            var desc = "${description!'天才人生'}";
                            var link =  window.location.href;
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
			showname: "${me.showname!'游客'}",
			imglittle: "${me.avatar}",
			oid:"${(me.oid?c)!'0'}",
			uid:"${(me.uid)}",
			useravatar: "${me.useravatar}",
			isuser: "${me.isUser}",
			defaultImg: "${baseimg}/imgs/default/user.png"
		}
		var cid = '${cid?c}';
		var channel = 'essay';
		var base = '${base}';
		var baseimg = '${baseimg}';
		var webimg = '${webimg}';
		var userimg = '${userimg}';
		var href = window.location.href;
		var type = 0;
		$(function(){
        	$('.face').qqFace({ 
		        assign:'content', //给输入框赋值 
		        path:'${baseimg}/imgs/face/qq/' //表情图片存放的路径 
		    });
		    $("#hot").click(function(){
				$("#new").removeClass("curjudge");
				$("#hot").addClass("curjudge");
				type=1;
				num=1;
				var url = base + "/ajax/essay/hot/"+cid+"_{0}".format(num);
				$.ajax({
					type:"GET",
					url:url,
					success:function(data){
						if(data.trim() == ""){
							if (num == 1) {
								$(".nothing").removeClass("hd");
							} 
							num=0;
						}else{
							num += 1;
							$(".nothing").addClass("hd");
							$(diplayID).html(data);
							initPage();
							$j(".imgb").imgbox();
							prizeBindEvent();
						}
					}
				});
			});
			$("#new").click(function(){
				$("#hot").removeClass("curjudge");
				$("#new").addClass("curjudge");
				type=0;
				num=1;
				var url = base + "/ajax/essay/"+cid+"_{0}".format(num);
				$.ajax({
					type:"GET",
					url:url,
					success:function(data){
						if(data.trim() == ""){
							if (num == 1) {
								$(".nothing").removeClass("hd");
							} 
							num=0;
						}else{
							num += 1;
							$(".nothing").addClass("hd");
							$(diplayID).html(data);
							initPage();
							$j(".imgb").imgbox();
							prizeBindEvent();
						}
					}
				});
			});
		});
	</script>
	<style type="text/css">
		.qqFace{margin-top:4px;background:#fff;padding:2px;border:1px #dfe6f6 solid;}
		.qqFace table td{padding:0px;}
		.qqFace table td img{cursor:pointer;border:1px #fff solid;}
		.qqFace table td img:hover{border:1px #0066cc solid;}
		.hidename {
		    display: inline-block;
		    float: right;
		    margin-right: 30px;
		    margin-top: 13px;
		    font-size: 14px;
		    color: #777777;
		}
	    .commentOperate a img {
		     width: 20px;
		    height: 20px;
		}
	</style>
	
	<link rel="stylesheet" href="${basecss}/css/default/lrtk.css" />
	<script type="text/javascript" src="${basejs}/js/libs/jquery.min.js"></script>
	<script type="text/javascript" src="${basejs}/js/libs/jquery.imgbox.pack.js"></script>
	
	<script type="text/javascript">
		var $j = jQuery.noConflict();
		$j(function($j) {
			$j(".imgb").imgbox();
		});
		
	</script>
</head>
<body>
	<div class="contentLeft">
		<div class="wordbox conWordbox" [#if me == ''] onclick= "isMember('${base}',${cid?c},${pcid});" [/#if]>
			<textarea id="content" class="comment_textarea contro_text"></textarea>
			<div class="wordpiece">
				<a [#if me??] class="face" [#else] onclick= "isMember(${base},${cid?c},${pcid});" [/#if] style="width: 42px;height: 38px;"><img src="${baseimg}/imgs/default/smile.png" alt="" /></a>
				<a id="zwb_upload" name="123"><img src="${baseimg}/imgs/default/picture.png" alt="" /><input type="file" class="add hd" multiple></img></a>
				<input type="text" name="filePath" id="callbackPath2" class="hd">
				<button class="submitBtn" [#if me??] onclick="submitCommentHandler(this);" [#else] onclick= "isMember(${base},${cid?c},${pcid});" [/#if]>发表</button>
				<form class="hidename"><input type="checkbox" id="isnick"/><label for="isnick">匿名</label></form>
			</div>
			<div class="pictureBox" id="preBox">
			</div>
			<div class="commentBox">
				<ul class="judgement">
					<li id="new" class="curjudge">最新</li>
					<li id="hot">最热</li>
				</ul>
				<ul class="commentList conCommentlist" id="displayComment">
					<img class="nothing hd" src="${baseimg}/imgs/default/prompt.png" style="width: 340px;height: 195px;">
				</ul>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="${basejs}/js/libs/upload.js"></script>
	<script type="text/javascript">
		
		$("#zwb_upload").bindUpload({
	        url:"${base}/life/essay/uploadimg",//上传服务器地址
	        callbackPath:"#callbackPath2",//图片地址的保存容器的id或者class
	        num:9,//上传数量的限制 默认为空 无限制
	        type:"jpg|png|gif|svg",//上传文件类型 默认为空 无限制
	        size:3,//上传文件大小的限制,默认为5单位默认为mb
	    });
	    
	</script>
</body>
</html>