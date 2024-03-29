[#ftl]
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>账号管理--账户保护设置密保问题</title>
	<meta name="Keywords" content="会员中心账号管理，账户保护设置密保问题" />
	<meta name="Description" content="会员中心账号管理，账户保护设置密保问题" />
	<link rel="stylesheet" type="text/css" href="${basecss}/css/default/account.css">
</head>
<body>
	<div class="wraper">
		<div class="container">
			<div class="containerleft [#if !member??]containerleftgreen[/#if]">
				<h2>账号管理</h2>
				<ul class="accountlist">
					<li class="accountitem01">
						<a href="${base}/my/user/info"><span>个人信息</span></a>
					</li>
					<li class="accountitem02">
						<a href="${base}/my/user/setpwd"><span>修改登录密码</span></a>
					</li>
					<li class="accountitem03">
						<a href="${base}/my/user/setpaypwd"><span>修改支付密码</span></a>
					</li>
					<li class="accountitem04">
						<a href="${base}/my/user/setemail"><span>修改绑定邮箱</span></a>
					</li>
					<li class="accountitem05 activeli">
						<a href="${base}/my/user/safeqa"><span>账户保护</span></a>
					</li>
					<li class="accountitem06">
                        <a href="${base}/my/user/news"><span>我的消息</span></a>
                    </li>
				</ul>
			</div>
			<div class="containerright  [#if !member??]containerrightgreen[/#if] ">
				<h2>账户保护</h2>
				<div class="setquestioncontent">
					<div class="stepprogress">
					[#if noexist == true]
						[#assign num = 0]
						[#assign again = ""]
						[#assign change ="设置"]
					 [#else]
					 	[#assign num = 1]
					 	[#assign again = "重新"]
					 	[#assign change ="修改"]
						<div class="step checked">
							<span class="circle">1</span>
							<span>验证密保答案</span>
						</div>
						<img src="${baseimg}/imgs/default/redarrow.png" alt="" />
					 [/#if]
						<div class="step  checked">
							<span class="circle">${num + 1}</span>
							<span>${again}选择密保问题</span>
						</div>
						<img src="${basecss}/imgs/default/redarrow.png" alt="" />
						<div class="step checked">
							<span class="circle">${num + 2}</span>
							<span>确认密保答案</span>
						</div>
						<img src="${basecss}/imgs/default/redarrow.png" alt="" />
						<div class="step checked">
							<span class="circle">${num + 3}</span>
							<span>完成</span>
						</div>
					</div>
					<div class="stepinfo">
						<div class="finishstep">
							<div class="finishtop">
							[#if success??]
								<img src="${basecss}/imgs/default/success.png" alt="" />
								<span>您已成功${change}了密保问题。</span>
							</div>
							<p>请您牢记您的密保问题答案，您可以通过密保问题找回登录密码。</p>
							[#else]
							<img src="${basecss}/imgs/default/fail.png" alt="" />
								<span>修改密保失败，请重新修改！</span>
							</div>
							[/#if]	
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>