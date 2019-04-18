[#ftl]
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>通过注册邮箱找回密码--通过邮箱重置密码</title>
	<meta name="Keywords" content="会员中心，通过注册邮箱找回密码" />
	<meta name="Description" content="会员中心，通过注册邮箱找回密码" />
	<link rel="stylesheet" type="text/css" href="${basecss}/css/default/findpw.css">
</head>
<body>
		<div class="container">
			<div class="main">
				<div class="topcommon">
					<img src="${baseimg}/imgs/default/findpw01.png" alt="" />
					<h2>从这里，找到更好的自己！</h2>
				</div>
				<div class="midcommon">
					<h3>
						<img src="${baseimg}/imgs/default/login_logo.png" alt="" /> 
						通过注册邮箱找回密码
					</h3>
					<div class="mainmid">
						<div class="goemail">
							<img src="${baseimg}/imgs/default/yes.png" alt="" />
							密码重设的链接已发送至<b>${msg!}</b><br/>
							<font size="2">链接48小时内有效，请及时处理</font>
						</div>
						<div class="bottombtns">
							<a href="${url}" class="submit getin">进入邮箱</a>
						</div>
					</div>
				</div>
			</div>
		</div>
</body>
</html>