[#ftl]
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>找回密码</title>
	<meta name="Keywords" content="会员中心找回密码" />
	<meta name="Description" content="会员中心找回密码" />
	<script type="text/javascript" src="${basejs}/js/libs/validator-1.0.0.js"></script>
	<link rel="stylesheet" type="text/css" href="${basecss}/css/default/findpw.css">
<script type="text/javascript">
<!--
    $(function() {
        $(".submit").click(function() {
            $("#reset_form").submit();
        } );
        
        $('input[name="f_name"]').keydown(function(){  
            if(event.keyCode==13){
                $(".submit").click();
                return false;
            }  
        });
        $('input[name="m_name"]').keydown(function(){  
            if(event.keyCode==13){
                $(".submit").click();
                return false;
            }  
        });
    });
-->
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
					<h3>
						<img src="${baseimg}/imgs/default/login_logo.png" alt="" /> 
						找回密码
					</h3>
					<div class="mainmid">
						<form id="reset_form" action="${base}/resetpwd/ste3?method=${origin}" method="post">
							<p class="notifyCode">验证码已发送至${token}</span></p>
							<ul class="mailist">
								<li>
									<label for="txtecode"><i>*</i>验证码</label>
									<input type="text" class="hd" id="txtemail" name="token" value="${token}"/>
									<input type="text" class="hd" name="useruid" value="${user.uid}"/>
									<input type="text" placeholder="输入验证码" isnotempty="" customreg="^\d{6}$|请填写6位验证码" ajaxvalid="${base}/ajax/user/code|验证码输入正确|验证码输入错误，请重新输入" name="code" class="mail" id="txtcode" />
									<div id="code" class="msg-box">
										<div id="focustxtcode" class="msg-prompt">
											<i></i>
											<span>请输入您收到的验证码</span>
										</div>
										<div id="errtxtcode" class="msg-error hd">
											<i></i>
											<span id="errmsgtxtcode">不能为空</span>
										</div>
										<div id="oktxtcode" class="msg-correct hd">
											<i></i>
											<span></span>
										</div>
									</div>
								</li>
							</ul>
							<a id="submit" class="submit">下一步</a>
						</form>
					</div>
				</div>
			</div>
		</div>
</body>
</html>