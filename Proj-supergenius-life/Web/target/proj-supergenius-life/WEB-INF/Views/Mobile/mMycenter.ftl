[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Public.ftl" as p]
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>${title!'天才人生'}</title>
	<link href="${basecss}/css/mobile/default/detail.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" href="${basecss}/css/mobile/default/index.css">
	<script type="text/javascript" src="${basejs}/js/mobile/jquery/plugins/jquery.Jcrop.min.js"></script>
	<link rel="stylesheet" type="text/css" href="${basecss}/css/mobile/jquery/jquery.Jcrop.css" media="screen" />
	<script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
	<script type="text/javascript" src="${basejs}/js/weixinshare.js"></script>
	<script src="${basejs}/js/libs3/swiper.jquery.min.js"></script>
	<script type="text/javascript">
			$(function(){ 
				[#if err??]
					alert("请上传jpg\png\gif格式照片");
				[/#if]
			  $('#imgcrop').Jcrop({setSelect:[0,0,180,180], minSize:[180, 180], onChange:changeCoords, onSelect:changeCoords, aspectRatio:1},function(){});
			});
			
			function changeCoords(c) {
				$("#txtx").val(c.x);
				$("#txty").val(c.y);
				$("#txtx2").val(c.x2);
				$("#txty2").val(c.y2);
			}
	</script>
	<style>
		#imgcrop {
			height:83%;
			width:93%;
		}
		.btn {
			display: inline-block;
		    font-size: 1rem;
		    margin-top: 10px;
		    background-color: #827d7d;
		    color: white;
		    padding: 8px 25px;
		    width: auto;
		    height: auto;
		    border-radius: 8px;
		    letter-spacing: 1px;
		}
		.submit {
			background: #ff8800;
		}
		.sbtn {
			margin-left: 65px;
		}
	</style>
</head>
<body>
<script type="text/javascript">
		function autosubmit(file) {
			$(file).parents("form").submit();
			
	}
	function uploadFile(obj) 
       { 
            $("#"+obj).click(); 
       }
</script> 
	<div class="userhome" >
		<div class="userinforbox">
			<div>
				[#if me.useravatar??]
					<img class="changeimgav" src="${me.useravatar}" onclick="uploadFile('headImg')" />
				[#else]
					<img class="changeimgav" src="${baseimg}/imgs/default/user.png" onclick="uploadFile('headImg')" />
				[/#if]
				<form action="${base}/m/my/user/setavatarlife" method="post" enctype="multipart/form-data" >
                	[#if avatar??]
                		<div class="changeHeadImg"> 
	                		<input type="hidden" name="avatar" value="${avatar}" />
		                    <img src="${userimg}${avatar}" id="imgcrop"  />
		                    <input id="txtx" type="hidden" name="x" value="" />
		                    <input id="txty" type="hidden" name="y" value="" />
		                    <input id="txtx2" type="hidden" name="x2" value="" />
		                    <input id="txty2" type="hidden" name="y2" value="" />
		                    <div class="editbtns">
		                    <a href="${base}/m/my/center/mycenter" class="btn editcancel">取消</a>
		                    <label class="sbtn"><input type="submit" value="保存" class="btn submit"/></label>
	                    </div>
                	[#else]
                    	<input name="file" type="file" id="headImg" style="display: none;" onchange="autosubmit(this)" />
                	[/#if]
                </form>
				
			</div>
			<div class="phonenumber">
				${me.username}
			</div>
		</div>
		<ul class="userInfo">
			<li class="curguide">
				<a href="${base}/m/my/center/article">
					<img src="${baseimg}/imgs/default/art.png" alt="我的文章" title="我的文章">
					我的文章
				</a>
			</li>
			<li>
				<a href="${base}/m/my/center/question">
					<img src="${baseimg}/imgs/default/ask.png" alt="我的提问" title="我的提问">
					我的提问
				</a>
			</li>
			<li>
				<a href="${base}/m/my/center/video">
					<img src="${baseimg}/imgs/default/film.png" alt="我的视频" title="我的视频">
					我的视频
				</a>
			</li>
			<li>
				<a href="${base}/m/my/center/topic">
					<img src="${baseimg}/imgs/default/topic.png" alt="我的话题" title="我的话题">
					我的话题
				</a>
			</li>
		</ul>
		<ul class="userInfo homeinfor">
			<li>
				<a href="${base}/m/my/center/msg">
					<img src="${baseimg}/imgs/default/news.png" alt="消息通知" title="消息通知">
					消息通知
					<span class="arrow usermessage"></span>
				</a>
			</li>
			<li>
                <a href="${base}/m/my/center/comment">
                    <img src="${baseimg}/imgs/default/comment.png" alt="我的评论" title="我的评论">
                    我的评论
                </a>
            </li>
			<li>
				<a href="${base}/m/my/center/collect">
					<img src="${baseimg}/imgs/mobile/default/collects.png" alt="我的收藏" title="我的收藏">
					我的收藏
				</a>
			</li>
			<li>
				<a href="${base}/m/my/center/info">
					<img src="${baseimg}/imgs/default/infor.png" alt="个人资料" title="个人资料">
					个人资料
				</a>
			</li>
		</ul>
	</div>
	<script>
		
		$(function () {
			[#if me??]
				$.get("${base}/m/ajax/my/unreads", function(data){
	    			if(data != 0){
	                    $(".usermessage").before('<i class="reddot"></i>');
	    			}
	            });
	        [/#if]
		});
	</script>
</body>
</html>