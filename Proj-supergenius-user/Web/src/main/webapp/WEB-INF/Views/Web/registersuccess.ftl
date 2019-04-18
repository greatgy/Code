[#ftl]
<html>
<head>
	<meta charset="UTF-8">
	<title>注册成功</title>
	<meta name="Keywords" content="会员中心，用户注册第二步，注册成功" />
	<meta name="Description" content="会员中心，用户注册第二步，注册成功" />
	<link rel="stylesheet" type="text/css" href="${basecss}/css/default/register.css">
</head>
<body>
		<div class="container">
			<div class="main">
				<div class="topcommon">
					<img src="${baseimg}/imgs/default/findpw01.png" alt="" />
					<h2>从这里，找到更好的自己！</h2>
				</div>
				<div class="midcommon">
					<div class="logotop">
						<img src="${baseimg}/imgs/default/login_logo.png" alt="" />
						会员注册
					</div>
					<div class="registercontainer">
						<div class="registercontmiddle">
							<div class="goemail">
								<div class="mailtip">
									<img src="${baseimg}/imgs/default/yes.png" alt="" />
									手机号/邮箱/微信/微博/QQ验证通过
								</div>
								<a href="${base}/my/home" class="stoemail submit finishInfo">完善个人信息</a>
								<a href="${base}/register_improve/${user.uid}" class="toemail submit">更多特权</a>
							</div>
							
						</div>
					</div>
				</div>
			</div>
	</div>
	<script>
		$(function() {
			$(".changemail").click(function(){
				$(".changebox").toggle();
			});
		});
	</script>
</body>
</html>