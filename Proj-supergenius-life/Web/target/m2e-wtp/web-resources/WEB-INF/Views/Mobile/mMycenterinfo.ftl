[#ftl]
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>${title!'天才人生'}</title>
	<link rel="stylesheet" href="${basecss}/css/mobile/default/index.css">
	<script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
	<script type="text/javascript" src="${basejs}/js/weixinshare.js"></script>
	<style>
		.containerBox{
			margin-top:40px;
		}
		.editbtn{
			position:absolute;
			top:13px;
			right:15px;
			font-size: 0.9rem;
	    	color: #fffcfb;
	    	z-index:9999;
		}
	</style>
</head>
<body>
	<form class="editInfo">
			<a href="${userbase}/my/user" class="editbtn" id="user_edit">编辑</a>
		<ul class="baseinfoList">
			<li>
				<span class="labelTips">头像</span>
				<div class="userBaseRt">
					[#if user.useravatar??]
					<img src="${user.useravatar}" />
					[#else]
						<img src="${baseimg}/imgs/default/user.png" />
					[/#if]
				</div>
			</li>
			<li>
				<span class="labelTips">昵称</span>
				<div class="userBaseRt">
					${user.username}
				</div>
			</li>
			<li>
				<span class="labelTips">生日</span>
				<div class="userBaseRt userbaseInfo">
					1885-9-30
				</div>
			</li>
			<li>
				<span class="labelTips">性别</span>
				<div class="userBaseRt">
					[#if user.gender == 1]
						男
					[#else] 
						女
					[/#if]
				</div>
			</li>
			<li>
				<span class="labelTips">个人简介</span>
				<div class="userBaseRt userbaseInfo">
					${user.summary}
				</div>
			</li>
		</ul>
	</form>
	<script>
		
	</script>
</body>
</html>