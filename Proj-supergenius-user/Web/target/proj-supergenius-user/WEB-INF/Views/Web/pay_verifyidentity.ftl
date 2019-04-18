[#ftl]
<html>
<head>
	<title>会员注册--缴费成功后提交公安系统验证身份</title>
	<meta name="Keywords" content="会员注册，缴费成功后提交公安系统验证身份" />
	<meta name="Description" content="会员注册，缴费成功后提交公安系统验证身份" />
	<script type="text/javascript" src="${basejs}/js/libs/validator-1.0.0.js"></script>
	<link rel="stylesheet" type="text/css" href="${basecss}/css/default/register.css">
<script type="text/javascript">
</script>
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
							<div class="errtip">身份证信息有误，请您输入正确的身份证信息!</div>
							<form action="${base}/pay_verifyidentity/${user.uid}" class="baseinfor" method="post">
								<ul class="labelbase baseinforlist testidenty">
									<li>
										<label for="mail"><i>*</i>&nbsp;真实姓名：</label>
										<input type="text" id="txtname" isnotempty="" ischinese="" placeholder="请填写与身份证一致的姓名" value="${name}" name="name" />
										<div id="focustxtname" class="tipnote tipright hd"><i></i><span id="focusmsgtxtname">请填写与身份证一致的姓名</span></div>
										[#if msg??]
											<div id="errtxtname" class="tipnote tipwrong"><i></i><span id="errmsgtxtname">验证失败！</span></div>
										[#else]
											<div id="errtxtname" class="tipnote tipwrong hd"><i></i><span id="errmsgtxtname">请填写与身份证一致的姓名</span></div>
										[/#if]
									   <div id="oktxtname" class="tipnote hd"><i></i><img src="${baseimg}/imgs/default/yes.png" alt="" /><span id="okmsgtxtname"></span></div>
									</li>
									<li>
										<label for="configpw"><i>*</i>&nbsp;身份证号：</label>
										<input type="text" id="txtidcard" isnotempty="" isidentityid="" placeholder="请输入有效的身份证号码" value="${identityid}" name="identityid" />
										<div id="focustxtidcard" class="tipnote tipright hd"><i></i><span id="focusmsgtxtidcard">请输入有效的身份证号码</span></div>
									    <div id="errtxtidcard" class="tipnote tipwrong hd"><i></i><span id="errmsgtxtidcard">请输入有效的身份证号码</span></div>
									   <div id="oktxtidcard" class="tipnote hd"><i></i><img src="${baseimg}/imgs/default/yes.png" alt="" /><span id="okmsgtxtidcard"></span></div>
									</li>
								</ul>
								<div style="text-align:center; color:red">
									[#if err_captcha_over_three_times?? ]
										<span>您今日已经输入3次，请明天再输入！</span>
									[#else]
										<span>您今日已输入${count!0}次，还有${surpluscount}次机会。</span>
									[/#if]
								</div>
								<br/>
								[#if !err_captcha_over_three_times??]
									<div class="formbottom">
										<button class="submit nextsubmit">确认</button>
									</div>
								[/#if]
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
</body>
</html>