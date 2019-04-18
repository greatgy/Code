[#ftl]
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>账号管理--修改登录密码</title>
	<meta name="Keywords" content="会员中心账号管理，修改登录密码" />
	<meta name="Description" content="会员中心账号管理，修改登录密码" />
	<link rel="stylesheet" type="text/css" href="${basecss}/css/default/account.css">
<script type="text/javascript" src="${basejs}/js/libs/validator-1.0.0.js"></script>
<script type="text/javascript">
<!--
    $(function () {
        $("#txtnewpwd2").keydown(function (event) {
            if (event.keyCode == 13) {
                submitform();
            }
        })
    })
    
	function registerValidHandler(){
    	var pwd = $("#txtnewpwd").val();
    	var pwd2 = $("#txtnewpwd2").val();
    	if(pwd != pwd2 || pwd2 == ""){
    		return false;
    	} else {
    		return true;
    	}
	}
	
	function submitform(){
		$("#setpwdform").submit();
	}
//-->
</script>
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
					<li class="accountitem02 activeli">
						<a href="${base}/my/user/setpwd"><span>修改登录密码</span></a>
					</li>
					<li class="accountitem03">
						<a href="${base}/my/user/setpaypwd"><span>修改支付密码</span></a>
					</li>
					[#-- 
					<li class="accountitem04">
						<a href="${base}/my/user/setemail"><span>修改绑定邮箱</span></a>
					</li>
					  --]
					
					<li class="accountitem05">
						<a href="${base}/my/user/safeqa"><span>账户保护</span></a>
					</li>
					<li class="accountitem06">
                        <a href="${base}/my/user/news"><span>我的消息</span></a>
                    </li>
				</ul>
			</div>
			<div class="containerright  [#if !member??]containerrightgreen[/#if] ">
				<h2>修改登录密码</h2>
				[#if success??]
				<div action="" class="logincode">
					<div class="setmid">
						<img src="${baseimg}/imgs/default/success.png" alt="" />
						<p class="tip">登录密码设定成功，请牢记新密码</p>
						<a class="submit" href="${base}/login">立即登录</a>
					</div>
				</div>
				[#elseif failed??]
				<div action="" class="logincode">
					<div class="setmid">
						<img src="${baseimg}/imgs/default/fail.png" alt="" />
						<p class="tip">登录密码设定失败，请重新修改</p>
						<a class="submit" href="${base}/my/user/setpwd">重新修改</a>
					</div>
				</div>
				[#else]
				<form action="${base}/my/user/setpwd" id="setpwdform" class="logincode" method="post">
					<ul class="changelogincode">
						<li>
							<span>当前登录密码</span>
							<input id="txtcurrpwd" name="currpwd" type="password" isnotempty="密码不能为空！" ispwd="您输入的密码格式不正确" ajaxvalid="${base}/ajax/my/user/verifypwd|密码输入不正确"  />
                   			<div class="msg-box">
	                    	<div id="focustxtcurrpwd" class="checknote hd"><img src="${baseimg}/imgs/default/light.png" alt="" /><span>请填写当前用户登录密码</span></div>
	                    	<div id="errtxtcurrpwd" class="checknote checknoteno hd"><img src="${baseimg}/imgs/default/waring.png" alt="" /><span id="errmsgtxtcurrpwd"></span></div>
	                    	<div id="oktxtcurrpwd" class="checknote checknoteyes hd"><img src="${baseimg}/imgs/default/yes.png" alt="" /><span>输入正确</span></div>
	                    	</div>
						</li>
						<li>
							<span>新密码</span>
							<input id="txtnewpwd" name="newpwd" type="password" isnotempty="新密码不能为空！" ispwd="您输入的密码格式不正确" />
							<div class="msg-box">
	                    	<div  id="focustxtnewpwd" class="checknote hd" ><img src="${baseimg}/imgs/default/light.png" alt="" /><span>输入6-16位必须包含数字和字母</span></div>
	                      	<div  id="errtxtnewpwd" class="checknote checknoteno hd"><img src="${baseimg}/imgs/default/waring.png" alt="" /><span id="errmsgtxtnewpwd"></span></div>
	                       	<div  id="oktxtnewpwd" class="checknote checknoteyes hd"><img src="${baseimg}/imgs/default/yes.png" alt="" /><span>输入正确</span></div>
                   			</div>
						</li>
						<li>
							<span>确认新密码</span>
							<input id="txtnewpwd2" name="newpwd2" type="password" isnotempty="新密码不能为空！" ispwd="您输入的密码格式不正确" customvalid="registerValidHandler()|两次输入的密码不一致" />
							<div class="msg-box">
	                    	<div  id="focustxtnewpwd2" class="checknote hd" ><img src="${baseimg}/imgs/default/light.png" alt="" /><span>请再次输入密码</span></div>
	                      	<div  id="errtxtnewpwd2" class="checknote checknoteno hd"><img src="${baseimg}/imgs/default/waring.png" alt="" /><span id="errmsgtxtnewpwd2">两次输入密码不一致</span></div>
	                       	<div  id="oktxtnewpwd2" class="checknote checknoteyes hd"><img src="${baseimg}/imgs/default/yes.png" alt="" /><span>输入正确</span></div>
	                       	</div>
						</li>
					</ul>
					<a class="submit" onclick="" title="" href="javascript:submitform();">确认修改</a>
				</form>
				[/#if]
			</div>
		</div>
	</div>
</body>
</html>