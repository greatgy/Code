[#ftl]
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>账号管理--修改绑定邮箱</title>
	<meta name="Keywords" content="会员中心账号管理，修改绑定邮箱" />
	<meta name="Description" content="会员中心账号管理，修改绑定邮箱" />
	<link rel="stylesheet" type="text/css" href="${basecss}/css/default/account.css">
</head>
<body>
	<div class="wraper">
		<div class="container">
			<div class="emaillaststep">
			[#if err_email_rebinding_failed??]
				<div class="relogin goemail">
					<img src="${baseimg}/imgs/default/fail.png" alt="" />
					<span>验证已失效</span>
				</div>
			[#else]
				<div class="relogin goemail">
					<img src="${baseimg}/imgs/default/success.png" alt="" />
					<span>绑定邮箱已经成功修改为${me.email}</span>
					<a class="submit" href="${base}/offline">重新登录</a>
				</div>
			[/#if]
			</div>
		</div>
	</div>
</body>
</html>