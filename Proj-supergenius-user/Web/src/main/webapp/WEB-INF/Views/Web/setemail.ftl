[#ftl]
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>账号管理--修改绑定邮箱</title>
	<meta name="Keywords" content="会员中心账号管理，修改绑定邮箱" />
	<meta name="Description" content="会员中心账号管理，修改绑定邮箱" />
	<link rel="stylesheet" type="text/css" href="${basecss}/css/default/account.css">
<script type="text/javascript" src="${basejs}/js/libs/validator-1.0.0.js"></script>
<script>
<!--
	$(function () {
	    $("#txtnewemail").keydown(function (event) {
	        if (event.keyCode == 13) {
	            submitform();
	        }
	    });
	})
	function submitform(){
		if($("[name][vflag='false']", $("#setneweamilform")).length == 0) {
			$("#setneweamilform").submit();
		}
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
					<li class="accountitem02">
						<a href="${base}/my/user/setpwd"><span>修改登录密码</span></a>
					</li>
					<li class="accountitem03">
						<a href="${base}/my/user/setpaypwd"><span>修改支付密码</span></a>
					</li>
					[#-- <li class="accountitem04 activeli">
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
				[#if abc == 'email']
				<h2>修改绑定邮箱</h2>
				<form action="${base}/my/changeemail" class="logincode changemail">
						<input type="text" name="change" class="hd" value="${abc}" />
					<ul class="changelogincode">
						<li>
							<label for="txtcode"><span style="width: 105px;">当前绑定邮箱：</span></label>
							<input type="text" id="txtemail" name="email" readonly  value="${me.email}" style=" color: #ff8252;font-size: 14px;font-weight: bold;margin-left: 5px;"/>
							<button type="button" class="sendcode" onclick="settime(this)" style=" background-color: #faf8f5;">获取验证码</button>
						</li>
						<li>
							<label for="txtcode"><span style="width: 105px;"><i style="color:red;">*</i>验证码：</span></label>
							<input type="text" id="txtcode" name="code" isnotempty="" customreg="^\d{6}$|请填写6位验证码" ajaxvalid="${base}/ajax/user/code|验证码输入正确|验证码输入错误，请重新输入" placeholder="输入正确的验证码" />
							<div class="msg-box">
							<div id="focustxtcode" class="tipnote tipright hd" style="margin-left: 15px;"><img src="${baseimg}/imgs/default/light.png" alt="" /><span id="focunsmsgtxtcode">请输入验证码</span></div> 
	                     	<div id="errtxtcode" class="tipnote tipwrong hd" style="margin-left: 15px;"><img src="${baseimg}/imgs/default/waring.png" alt="" /><span id="errmsgtxtcode">请输入验证码</spa></div>
	                     	<div id="oktxtcode" class="tipnote hd"><img src="${baseimg}/imgs/default/yes.png" alt="" /><span id="oktxtpassword"></span></div>
	                     	</div>
						</li>
					</ul>
					<button class="submit" style="margin-left: 111px;">下一步</button>
				</form>
				[#elseif abc == 'mobile']
					<h2>修改绑定手机号</h2>
					<form action="${base}/my/changeemail" class="logincode changemail">
						<input type="text" name="change" class="hd" value="${abc}" />
					<ul class="changelogincode">
						<li>
							<label for="txtcode"><span style="width: 120px;">当前绑定手机号：</span></label>
							<input type="text" id="txtemail" name="email" readonly  value="${me.mobile}" style=" color: #ff8252;font-size: 14px;font-weight: bold;margin-left: 5px;"/>
							<button type="button" class="sendcode" onclick="settime(this)" style=" background-color: #faf8f5;">获取验证码</button>
						</li>
						<li>
							<label for="txtcode"><i style="color:red;">*</i><span style="width: 112px;">验证码：</span></label>
							<input type="text" id="txtcode" name="code" isnotempty="" customreg="^\d{6}$|请填写6位验证码" ajaxvalid="${base}/ajax/user/code|验证码输入正确|验证码输入错误，请重新输入" placeholder="输入正确的验证码" />
							<div class="msg-box" style="width: 180px;">
							<div id="focustxtcode" class="tipnote tipright hd"><img src="${baseimg}/imgs/default/light.png" alt="" /><span id="focunsmsgtxtcode">请输入验证码</span></div> 
	                     	<div id="errtxtcode" class="tipnote tipwrong hd" style="margin-left: 15px;"><img src="${baseimg}/imgs/default/waring.png" alt="" /><span id="errmsgtxtcode">请输入验证码</spa></div>
	                     	<div id="oktxtcode" class="tipnote hd"><i></i><img src="${baseimg}/imgs/default/yes.png" alt="" /><span id="oktxtpassword"></span></div>
	                     	</div>
						</li>
					</ul>
					<button class="submit"  style="margin-left: 111px;"">下一步</button>
				</form>
				[/#if]
				[#if addemail == "email"]
				<h2>绑定邮箱</h2>
				<form action="${base}/my/changeemail/stp2" class="logincode changemail">
					<ul class="changelogincode">
					<li>
						<input type="text" class="hd" value="${me.uid}" name="uid">
						<input type="text" class="hd" value="${addemail}" name="status">
						<label for="mail"><i style="color:red;">*</i><span style="width: 64px;">邮箱：</span></label>
						<input type="text" id="txtemail" name="email" isnotempty="" isemail  placeholder="请输入要绑定的邮箱" ajaxvalid="${base}/ajax/user/emailvalid|该邮箱已被注册，请直接登录|请输入163/qq/sina等常用邮箱或手机号" maxlength="100" style="width: 190px;"/>
						<button type="button" class="sendcode" onclick="settime(this)" style=" background-color: #faf8f5; margin-left: 0px;">获取验证码</button>
						<div class="msg-box">
							 <div id="focustxtemail" class="tipnote tipright hd"><img src="${baseimg}/imgs/default/light.png" alt="" /><span id="focusmsgtxtemail">请输入邮箱</span></div> 
			                 <div id="errtxtemail" class="tipnote tipwrong hd"><img src="${baseimg}/imgs/default/waring.png" alt="" /><span id="errmsgtxtemail">请输入正确的邮箱格式</span></div>
			                 <div id="oktxtemail" class="tipnote hd"><img src="${baseimg}/imgs/default/yes.png" alt="" /><span id="okmsgtxtemail"></span></div>
		                </div> 
					</li>
						<li>
							<label for="txtcode"><i style="color:red;">*</i><span style="width: 64px;">验证码：</span></label>
							<input type="text" id="txtcode" name="code" isnotempty="" customreg="^\d{6}$|请填写6位验证码" ajaxvalid="${base}/ajax/user/code|验证码输入正确|验证码输入错误，请重新输入" placeholder="输入正确的验证码" style="width: 263px;"/>
							<div class="msg-box">
								<div id="focustxtcode" class="tipnote tipright hd"><img src="${baseimg}/imgs/default/light.png" alt="" /><span id="focunsmsgtxtcode">请输入验证码</span></div> 
		                     	<div id="errtxtcode" class="tipnote tipwrong hd"><img src="${baseimg}/imgs/default/waring.png" alt="" /><span id="errmsgtxtcode">请输入验证码</spa></div>
		                     	<div id="oktxtcode" class="tipnote hd"><img src="${baseimg}/imgs/default/yes.png" alt="" /><span id="oktxtpassword"></span></div>
	                     	</div>
						</li>
					</ul>
					<button class="submit" style="margin-left: 63px;">下一步</button>
				</form>
				[#elseif addemail == 'mobile']
					<h2>绑定手机号</h2>
					<form action="${base}/my/changeemail/stp2" class="logincode changemail">
					<ul class="changelogincode">
						<li>
							<input type="text" class="hd" value="${me.uid}" name="uid">
							<input type="text" class="hd" value="${addemail}" name="status">
							<label for="mail"><i style="color:red;">*</i>手机号：</label>
							<input type="text" id="txtemail" name="email" isnotempty="" ismobile  placeholder="请填写可用手机号" ajaxvalid="${base}/ajax/user/mobilevalid|该手机已被注册，请直接登录|请输入163/qq/sina等常用邮箱或手机号" maxlength="100" style="width: 190px;"/>
							<button type="button" class="sendcode" onclick="settime(this)" style=" background-color: #faf8f5;margin-left: 0px;">获取验证码</button>
							<div class="msg-box">
								 <div id="focustxtemail" class="tipnote tipright hd"><img src="${baseimg}/imgs/default/light.png" alt="" /><span id="focusmsgtxtemail">请输入手机号</span></div> 
				                 <div id="errtxtemail" class="tipnote tipwrong hd"><img src="${baseimg}/imgs/default/waring.png" alt="" /><span id="errmsgtxtemail">请输入正确的手机号格式</span></div>
				                 <div id="oktxtemail" class="tipnote hd"><img src="${baseimg}/imgs/default/yes.png" alt="" /><span id="okmsgtxtemail"></span></div>
				            </div>
						</li>
						<li>
							<label for="txtcode"><i style="color:red;">*</i>验证码：</label>
							<input type="text" id="txtcode" name="code" isnotempty="" customreg="^\d{6}$|请填写6位验证码" ajaxvalid="${base}/ajax/user/code|验证码输入正确|验证码输入错误，请重新输入" placeholder="输入正确的验证码" style="width: 263px;" />
							<div class="msg-box">
								<div id="focustxtcode" class="tipnote tipright hd"><img src="${baseimg}/imgs/default/light.png" alt="" /><span id="focunsmsgtxtcode">请输入验证码</span></div> 
		                     	<div id="errtxtcode" class="tipnote tipwrong hd"><img src="${baseimg}/imgs/default/waring.png" alt="" /><span id="errmsgtxtcode">请输入验证码</spa></div>
		                     	<div id="oktxtcode" class="tipnote hd"><img src="${baseimg}/imgs/default/yes.png" alt="" /><span id="oktxtpassword"></span></div>
	                     	</div>
						</li>
					</ul>
					<button class="submit"  style="margin-left: 63px;">下一步</button>
				</form>
				[/#if]
				[#if status == 'email']
				<h2>修改绑定邮箱</h2>
				<div class="containerright  [#if !member??]containerrightgreen[/#if] ">
					<form action="${base}/my/changeemail/stp2" class="logincode changemail">
						<ul class="changelogincode">
							<li>
								<input type="text" class="hd" value="${me.uid}" name="uid">
								<input type="text" class="hd" value="${status}" name="status">
								<label for="mail"><i style="color:red;">*</i><span style="width: 64px;">邮箱：</span></label>
								<input type="text" id="txtemail" name="email" isnotempty="" isemail  placeholder="请输入新的邮箱" ajaxvalid="${base}/ajax/user/emailvalid|该邮箱已被注册，请直接登录|请输入163/qq/sina等常用邮箱或手机号" maxlength="100" style="width: 190px;"/>
								<button type="button" class="sendcode" onclick="settime(this)" style=" background-color: #faf8f5; margin-left: 0px;"">获取验证码</button>
								<div class="msg-box">
									 <div id="focustxtemail" class="tipnote tipright hd"><img src="${baseimg}/imgs/default/light.png" alt="" /><span id="focusmsgtxtemail">请输入邮箱</span></div> 
					                 <div id="errtxtemail" class="tipnote tipwrong hd"><img src="${baseimg}/imgs/default/waring.png" alt="" /><span id="errmsgtxtemail">请输入正确的邮箱格式</span></div>
					                 <div id="oktxtemail" class="tipnote hd"><img src="${baseimg}/imgs/default/yes.png" alt="" /><span id="okmsgtxtemail"></span></div>
				                </div> 
							</li>
							<li>
								<label for="txtcode"><i style="color:red;">*</i><span style="width: 64px;">验证码：</span></label>
								<input type="text" id="txtcode" name="code" isnotempty="" customreg="^\d{6}$|请填写6位验证码" ajaxvalid="${base}/ajax/user/code|验证码输入正确|验证码输入错误，请重新输入" placeholder="输入正确的验证码" style="width: 263px;" />
								<div class="msg-box">
								<div id="focustxtcode" class="tipnote tipright hd"><img src="${baseimg}/imgs/default/light.png" alt="" /><span id="focunsmsgtxtcode">请输入验证码</span></div> 
		                     	<div id="errtxtcode" class="tipnote tipwrong hd"><img src="${baseimg}/imgs/default/waring.png" alt="" /><span id="errmsgtxtcode">请输入验证码</spa></div>
		                     	<div id="oktxtcode" class="tipnote hd"><img src="${baseimg}/imgs/default/yes.png" alt="" /><span id="oktxtpassword"></span></div>
		                     	</div>
							</li>
						</ul>
						<button class="submit"  style="margin-left: 63px;">下一步</button>
					</form>
				</div>
				[#elseif status == 'mobile']
					<h2>修改绑定手机号</h2>
					<div class="containerright  [#if !member??]containerrightgreen[/#if] ">
					<form action="${base}/my/changeemail/stp2" class="logincode changemail">
						<ul class="changelogincode">
							<li>
								<input type="text" class="hd" value="${me.uid}" name="uid">
								<input type="text" class="hd" value="${status}" name="status">
								<label for="mail"><i style="color:red;">*</i>手机号：</label>
								<input type="text" id="txtemail" name="email" isnotempty="" ismobile  placeholder="请填写可用手机号" ajaxvalid="${base}/ajax/user/mobilevalid|该手机已被注册，请直接登录|请输入163/qq/sina等常用邮箱或手机号" maxlength="100" style="width: 190px;"/>
								<button type="button" class="sendcode" onclick="settime(this)" style=" background-color: #faf8f5; margin-left: 0px;">获取验证码</button>
								<div class="msg-box">
								 	<div id="focustxtemail" class="tipnote tipright hd"><img src="${baseimg}/imgs/default/light.png" alt="" /><span id="focusmsgtxtemail">请输入手机号</span></div> 
				                 	<div id="errtxtemail" class="tipnote tipwrong hd"><img src="${baseimg}/imgs/default/waring.png" alt="" /><span id="errmsgtxtemail">请输入正确的手机号格式</span></div>
				                 	<div id="oktxtemail" class="tipnote hd"><img src="${baseimg}/imgs/default/yes.png" alt="" /><span id="okmsgtxtemail"></span></div>
				                 </div>
							</li>
							<li>
								<label for="txtcode"><i style="color:red;">*</i>验证码：</label>
								<input type="text" id="txtcode" name="code" isnotempty="" customreg="^\d{6}$|请填写6位验证码" ajaxvalid="${base}/ajax/user/code|验证码输入正确|验证码输入错误，请重新输入" placeholder="输入正确的验证码" style="width: 263px;"/>
								<div class="msg-box">
									<div id="focustxtcode" class="tipnote tipright hd"><img src="${baseimg}/imgs/default/light.png" alt="" /><span id="focunsmsgtxtcode">请输入验证码</span></div> 
			                     	<div id="errtxtcode" class="tipnote tipwrong hd"><img src="${baseimg}/imgs/default/waring.png" alt="" /><span id="errmsgtxtcode">请输入验证码</spa></div>
			                     	<div id="oktxtcode" class="tipnote hd"><img src="${baseimg}/imgs/default/yes.png" alt="" /><span id="oktxtpassword"></span></div>
		                     	</div>
							</li>
						</ul>
						<button class="submit" style="margin-left: 63px;">下一步</button>
					</ul>
				</form>
				</div>
				[/#if]
				[#if success == 'email']
					<h2>修改绑定邮箱</h2>
					<div class="logincode">
						<div class="goemail">
							<img src="/imgs/default/success.png" alt="" />
							<span>
							修改成功，可以用新邮箱进行登录了,绑定邮箱已经成功修改为${me.email}</span>
							<a class="submit" href="${base}/login">立即登录</a>
						</div>
					</div>
				[#elseif success == 'mobile']
					<h2>修改绑定手机号</h2>
					<div class="logincode">
						<div class="goemail">
							<img src="/imgs/default/success.png" alt="" />
							<span>
							修改成功，可以用新手机号进行登录了,绑定手机号已经成功修改为${me.mobile}</span>
							<a class="submit" href="${base}/login">立即登录</a>
						</div>
					</div>
				[/#if]
			</div>
		</div>
	</div>
<script type="text/javascript">
	var countdown=60;   
	
	function settime(obj) {
		if(countdown == 60){
			[#if status ??|| addemail ??]
				var email = $("#txtemail").val();
				if(email.length == 0){
				$("#focustxtemail").removeClass("hd");
					return;
				}
			[/#if]
		    	$.ajax({
		    		[#if abc == 'email']
						url:"${base}/sendcode?email=${me.email}"
					[#elseif abc == 'mobile']
						url:"${base}/sendcode?email=${me.mobile}"
					[#elseif status ??|| addemail ??]
						url:"${base}/sendcode?email=" + email
					[/#if]
				});
				countdown = 59;
	    } else if (countdown == 0) {   
		        obj.removeAttribute("disabled");      
		        $(".sendcode").html("获取验证码");
		        countdown = 60;   
		        return;  
	    } else {   
		        obj.setAttribute("disabled", true);   
		       $(".sendcode").html("重新发送(" + countdown + ")");
		        countdown--;   
	    }   
	setTimeout(function() {   
	    settime(obj) },1000)   
	}  
	
</script>
</body>
</html>