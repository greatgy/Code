[#ftl]
<html>
<head>
	<meta charset="UTF-8">
	<title>账号管理--个人信息</title>
	<meta name="Keywords" content="会员中心账号管理，个人信息" />
	<meta name="Description" content="会员中心账号管理，个人信息" />
	<link rel="stylesheet" type="text/css" href="${basecss}/css/default/account.css">
</head>
<body>
		<div class="container">
			<div class="containerleft [#if !member??]containerleftgreen[/#if]">
				<h2>账号管理</h2>
				<ul class="accountlist">
					<li class="accountitem01 activeli">
						<a href="${base}/my/user/info"><span>个人信息</span></a>
					</li>
					<li class="accountitem02">
						<a href="${base}/my/user/setpwd"><span>修改登录密码</span></a>
					</li>
					<li class="accountitem03">
						<a href="${base}/my/user/setpaypwd"><span>修改支付密码</span></a>
					</li>
					[#-- 
					<li class="accountitem04">
						<a href="${base}/my/user/setemail"><span>修改绑定邮箱</span></a>
					</li> --]
					<li class="accountitem05">
						<a href="${base}/my/user/safeqa"><span>账户保护</span></a>
					</li>
					<li class="accountitem06">
						<a href="${base}/my/user/news"><span>我的消息</span></a>
					</li>
				</ul>
			</div>
			<div class="containerright  [#if !member??]containerrightgreen[/#if] ">
				<h2>
					个人资料
					<a href="${base}/my/user/editinfo" class="editinformation">
						<img src="${baseimg}/imgs/default/editimg.png" alt="" />
						编辑个人资料
					</a>
				</h2>
				<div class="personfile">
					<div class="personfiletop">
						<div class="filetopleft">
							<img [#if me.useravatar??] src="${me.useravatar}"[#else]src="${baseimg}/imgs/web/noavatar.png"[/#if] />
							<a href="${base}/my/user/setavatar">修改头像</a>
						</div>
						<ul class="filetopright">
							<li>
								<span style="width: 300px;">[#if !member??]${me.username}[#else]${me.username}[/#if]</span>
								[#if me.gender == 0]
									<img src="${baseimg}/imgs/default/lady.png" alt="" />
								[#elseif me.gender == 1]
									<img src="${baseimg}/imgs/default/man.png" alt="" />
								[/#if]
							</li>
							<li>
								<span>身份证号</span>
								<b class="b01">${me.identityid}</b>
							</li>
							<li>
								<span>会员号</span>
								[#if !member??]
									<a href="${base}/register_improve/${me.uid}" class="bemember">成为会员</a>
								[#else]
									<b class="b02">${me.usersn}</b>
								[/#if]
							</li>
							<li>
								<span>账号</span>
								<b class="b03">${me.showname}</b>
							</li>
						</ul>
					</div>
					<ul class="personfilebottom">
						<li>
							<span class="filetopleft">昵称</span>
							<span>${me.username}</span>
						</li>
						<li>
							<span class="filetopleft">供职单位</span>
							<span>${me.company}</span>
						</li>
						<li>
							<span class="filetopleft">所属部门</span>
							<span>${me.department}</span>
						</li>
						<li>
							<span class="filetopleft">担任职务</span>
							<span>${me.job}</span>
						</li>
						<li>
							<span class="filetopleft">个人简介</span>
							<div class="abstract">
								${me.summary}
							</div>
						</li>
					</ul>
				</div>
			</div>
		</div>
</body>
</html>