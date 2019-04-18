[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Public.ftl" as p]
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>${title!'天才人生'}</title>
	<meta name="Keywords" content="天才人生,交流对话" />
	<meta name="Description" content="天才人生,交流对话" />
	<link rel="stylesheet" href="${basecss}/css/default/index.css">
	<link rel="stylesheet" href="${basecss}/css/default/upload.css">
	<script type="text/javascript" src="${basejs}/js/pages/scroll.js"></script>
	<script type="text/javascript" src="${basejs}/js/pages/comment-1.0.0.js"></script>
	<script type="text/javascript" src="${basejs}/js/libs3/jquery.qqface.min.js"></script>
	<script type="text/javascript" src="${basejs}/js/pages/lifeessay.js"></script>
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
		.commentitemRt {
		    display: inline-block;
		    width: 85%;
		    margin-top: 5px;
		   margin-left: -4%;
		}
		.commentList li {
		    padding-top: 20px;
		    padding-bottom: 10px;
		    padding-left: 0px;
		    border-bottom: 1px solid #ddd;
		}
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
	<div class=" wordbox">
			<textarea id="content" class="comment_textarea contro_text [#if me == ''] pop-up [/#if]"></textarea>
			<div class="wordpiece">
				<a class="[#if me??] face [#else] pop-up [/#if]"><img src="${baseimg}/imgs/default/smile.png" alt="" /></a>
				<a id="zwb_upload" name="123"  ><img src="${baseimg}/imgs/default/picture.png" alt="" /><input type="file"  class="[#if me == ''] pop-up [/#if]add hd " multiple></img></a>
				<input type="text" name="filePath" id="callbackPath2" class="hd ">
				<button class="submitBtn [#if me == ''] pop-up [/#if] " [#if me??] onclick="submitCommentHandler(this);" [/#if]>发表</button>
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