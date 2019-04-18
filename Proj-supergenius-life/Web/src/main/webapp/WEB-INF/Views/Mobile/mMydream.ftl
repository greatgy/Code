[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Public.ftl" as p]
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>${title!'交流对话'}</title>
	<link rel="stylesheet" href="${basecss}/css/mobile/default/index.css">
	<link rel="stylesheet" href="${basecss}/css/default/upload.css">
	<script type="text/javascript" src="${basejs}/js/pages/scroll.js"></script>
	<script type="text/javascript" src="${basejs}/js/pages/comment-1.0.0.js"></script>
	<script type="text/javascript" src="${basejs}/js/libs3/jquery.qqface.min.js"></script>
	<script type="text/javascript" src="${basejs}/js/mobile/lifeessay.js"></script>
	<script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
	<script type="text/javascript" src="${basejs}/js/weixinshare.js"></script>
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
				//$("#displayComment").find("li").remove(); 
				$("#new").removeClass("curjudge");
				$("#hot").addClass("curjudge");
				type=1;
				num = 1;
				$.ajax({
					type:"GET",
					url:"${base}/m/ajax/essay/hot/${cid?c}_1",
					success:function(data){
						if(data.trim() == ""){
							if (num == 1) {
								$(".nothing").removeClass("hd");
							} 
							num=0;
						}else{
							$(".nothing").addClass("hd");
							$("#displayComment").html(data);
							num = num + 1;
							initPage();
							$j(".imgb").imgbox();
							prizeBindEvent();
						}
					}
				});
			});
			$("#new").click(function(){
				//$("#displayComment").find("li").remove(); 
				$("#hot").removeClass("curjudge");
				$("#new").addClass("curjudge");
				type=0;
				num = 1;
				$.ajax({
					type:"GET",
					url:"${base}/m/ajax/essay/${cid?c}_1",
					success:function(data){
						if(data.trim() == ""){
							if (num == 1) {
								$(".nothing").removeClass("hd");
							} 
							num=0;
						}else{
							
							$(".nothing").addClass("hd");
							$("#displayComment").html(data);
							num = num + 1;
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
		.conCommentlist .userimg {
		    width: 5%;
		    position: relative;
		}
		.userimg .princeimgeasy {
		    position: absolute;
		    width: 30px;
		    height: 30px;
		    border: none;
		    margin-right: -27px;
		    margin-top: -35px;
		    top: 50%;
		    right: 50%;
		}
		#zwb_upload {
		    padding: 0;
			height: 30px;
		    width: 32px;
		    line-height: 20px;
		    position: relative;
		    cursor: pointer;
		    color: #888;
		    border-radius: 4px;
		    display: inline-block;
		    overflow: hidden;
		}
		#zwb_upload img {
			width: 100%;
		    height: 100%;
		    overflow: hidden;
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
	<div class="treeBox">
		[#if list??]
			[#assign i=1]
			[#list list as item]
				[#if item.fromUser??]
					[#assign fromMan=item.fromUser/]
				[#elseif item.fromVisitor??]
					[#assign fromMan=item.fromVisitor/]
				[/#if]
				[#if item.fromUser??]
					[#assign fromUserName=fromMan.username/]
				[#else]
					[#assign fromUserName=fromMan.nickname/]
				[/#if]
				<div class="dreamitem dreamitem${i}">
					<img src="${baseimg}/imgs/default/tag${i%4+1}.png" class="dreamtagimg" alt="${item.fromusername}的理想" title="${item.fromusername}的理想" />
					<div class="userdream">
						[#if item.fromUser??]
							<img [#if fromMan.getIsUser()]class="userborder"[/#if][#if "${fromMan.useravatar}" == ""]src="${baseimg}/imgs/default/user.png"[#else]src="${fromMan.useravatar}"[/#if] alt="${item.fromusername}" title="${item.fromusername}"/>
							[#if fromMan.getIsUser()]<img src="${baseimg}/imgs/default/prince.png" class="princeimg" alt="" />[/#if]
						[#else]
							<img src="${fromMan.useravatar}" />		
						[/#if]
						
						<span class="dreampiece">[@p.PrivateString content="${fromUserName}" /]的理想</span>
					</div>
				</div>
				[#assign i=i+1]
			[/#list]
		[/#if]
	</div>
	<div class="wordbox conWordbox" [#if me == ''] onclick="isMember('${base}',${cid?c},${pcid});" [/#if] >
		<textarea id="content" class="comment_textarea contro_text"></textarea>
		<div class="wordpiece">
			<a [#if me??] class="face" [#else] onclick="isMember('${base}',${cid?c},${pcid});" [/#if]><img src="${baseimg}/imgs/default/smile.png" alt="" /></a>
			<a id="zwb_upload"><img src="${baseimg}/imgs/default/picture.png" alt="" /><input type="file" class="add hd" multiple></img></a>
			<input type="text" name="filePath" id="callbackPath2" class="hd">
			<button class="submitBtn" [#if me??] onclick="submitCommentHandler(this);" [#else] onclick="isMember('${base}',${cid?c},${pcid});" [/#if]>发表</button>
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
				<img class="nothing hd" src="${baseimg}/imgs/default/prompt.png" style="width:481px;heigth:211px">
			</ul>
		</div>
	</div>
	<script>
		$(function(){
			$(".dreamitem img").click(function () {
				$(this).parent().find(".dreamDetail").show();
				$(this).parent().siblings(".dreamitem").find(".dreamDetail").hide()
			});
			$(".closebutton1").click(function () {
				$(this).parent().hide();
			});
			
		});
	</script>
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