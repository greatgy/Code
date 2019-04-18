[#ftl]
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>账号管理--修改支付密码</title>
	<meta name="Keywords" content="会员中心账号管理，修改支付密码" />
	<meta name="Description" content="会员中心账号管理，修改支付密码" />
	<link rel="stylesheet" type="text/css" href="${basecss}/css/default/account.css">
<script type="text/javascript" src="${basejs}/js/libs/validator-1.0.0.js"></script>
<script type="text/javascript">
<!--
	
	$(function(){
			var canSubmit = true;
			var regs = {
			isnum : /^.*[0-9]+.*$/,
			islish: /^.*[A-Za-z]+.*$/,
			issign: /^.*[_\!\.@#\$%\^&\*\(\)\[\]\\?\\\/\|\-~`\+\=\,\r\n\:\'\"。，；……！￥\{\}·]+.*$/
			};
			$("#txtnewpaypwd").keyup(function(){
				var count = 0;
				for(var b in regs){
						if(regs[b].test($(this).val())) count++;
					}
					$(".safe span").removeClass("saferange");
					switch(count)
					{
						case 1:
							$(".safe span:eq(0)").addClass("saferange");
							break;
						case 2:
							$(".safe span:eq(1)").addClass("saferange");
							break;
						case 3:
							$(".safe span:eq(2)").addClass("saferange");
							break;
					}
			});
			
			//enter event
			$("#txtnewpaypwd2").keydown(function (event) {
	            if (event.keyCode == 13) {
	                submitform();
	            }
	        })		
							
			
	});

	function registerValidHandler(){
    	var pwd = $("#txtnewpaypwd").val();
    	var pwd2 = $("#txtnewpaypwd2").val();
    	if(pwd != pwd2 || pwd2 == ""){
    		return false;
    	} else {
    		return true;
    	}
	}
	
	function submitform(){
		$("#setpaypwdform").submit();
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
					<li class="accountitem03 activeli">
						<a href="${base}/my/user/setpaypwd"><span>[#if me.paypwd ??]修改支付密码[#else]设置支付密码[/#if]</span></a>
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
			<div id="containerright1" class="containerright  [#if !member??]containerrightgreen[/#if] [#if find??]hd[/#if]">
				<h2>[#if me.paypwd ??]修改支付密码[#else]设置支付密码[/#if]</h2>
				[#if success??]
					<div action="" class="logincode">
						<div class="setmid">
							<img src="${baseimg}/imgs/default/success.png" alt="" />
							<p class="tip">支付密码设定成功，请牢记新密码</p>
							<a class="submit" href="${base}/my/user/info">确定</a>
						</div>
					</div>
				[#elseif failed??]
					<div action="" class="logincode">
						<div class="setmid">
							<img src="${baseimg}/imgs/default/fail.png" alt="" />
							<p class="tip">支付密码设定失败，请重新修改</p>
							<a class="submit" href="${base}/my/user/setpwd">重新修改</a>
						</div>
					</div>
				[#else]
				<form id="setpaypwdform" class="logincode" [#if me.paypwd ??] action="${base}/my/user/setpaypwd" [#else] action="${base}/my/user/resetpaypwd" [/#if] method="post">
					<ul class="changelogincode">
						[#if me.paypwd ??]
							<li>
								<span>当前支付密码</span>
								<input id="txtcurrpaypwd" name="currpaypwd" type="password" customreg="^.{6,16}$|请填写6-16位字符" isnotempty="支付密码不能为空！" ajaxvalid="${base}/ajax/my/user/verifypaypwd|支付密码输入不正确"  />
	                   			<div class="msg-box">
			                    	<div id="focustxtcurrpaypwd" class="checknote hd"><img src="${baseimg}/imgs/default/light.png" alt="" /><span>请填写当前支付密码</span></div>
			                    	<div id="errtxtcurrpaypwd" class="checknote checknoteno hd"><img src="${baseimg}/imgs/default/waring.png" alt="" /><span id="errmsgtxtcurrpaypwd"></span></div>
			                    	<div id="oktxtcurrpaypwd" class="checknote checknoteyes hd"><img src="${baseimg}/imgs/default/yes.png" alt="" /><span>输入正确</span></div>
		                    	</div>
							</li>
						[/#if]
						<li>
							<span>[#if me.paypwd ??] 新密码 [#else] 密码 [/#if]</span>
							<input id="txtnewpaypwd" name="newpaypwd" type="password" customreg="^.{6,16}$|请填写6-16位字符" isnotempty=" [#if me.paypwd ??] 新支付密码不能为空！[#else] 支付密码不能为空！ [/#if]"  />
                   			<div class="msg-box">
	                    	<div id="focustxtnewpaypwd" class="checknote hd"><img src="${baseimg}/imgs/default/light.png" alt="" /><span>[#if me.paypwd ??] 请输入新支付密码！[#else] 请输入支付密码！ [/#if]</span></div>
	                    	<div id="errtxtnewpaypwd" class="checknote checknoteno hd"><img src="${baseimg}/imgs/default/waring.png" alt="" /><span id="errmsgtxtnewpaypwd"></span></div>
	                    	<div id="oktxtnewpaypwd" class="checknote checknoteyes hd"><img src="${baseimg}/imgs/default/yes.png" alt="" /><span>输入正确</span></div>
	                    	</div>
						</li>
						<div class="safe">
							<span>弱</span>
							<span>中</span>
							<span>强</span>
						</div>
						<li>
							<span>[#if me.paypwd ??] 确认新密码 [#else] 确认密码 [/#if]</span>
							<input id="txtnewpaypwd2" name="newpaypwd2" type="password"  isnotempty="[#if me.paypwd ??] 新支付密码不能为空！[#else] 支付密码不能为空！ [/#if]" customvalid="registerValidHandler()|两次输入的密码不一致" />
                   			<div class="msg-box">
	                    	<div id="focustxtnewpaypwd2" class="checknote hd"><img src="${baseimg}/imgs/default/light.png" alt="" /><span>[#if me.paypwd ??] 请再次输入新支付密码！[#else] 请再次输入支付密码！ [/#if]</span></div>
	                    	<div id="errtxtnewpaypwd2" class="checknote checknoteno hd"><img src="${baseimg}/imgs/default/waring.png" alt="" /><span id="errmsgtxtnewpaypwd2"></span></div>
	                    	<div id="oktxtnewpaypwd2" class="checknote checknoteyes hd"><img src="${baseimg}/imgs/default/yes.png" alt="" /><span>输入正确</span></div>
	                    	</div>
						</li>
					</ul>
					<a class="submit" onclick="" title="" href="javascript:submitform();">确认修改</a>
					 [#if me.paypwd ??]<a href="${base}/resetpwd/usersn?method=paywd" style="display:block;margin: -16px 0 0 -18px;[#if member??]color: #565454;[#else]color: #0dc06e;[/#if]]">找回支付密码?</a>[/#if]
				</form>
				[/#if]
			</div>
			<div id="containerright2" class="containerright  [#if !member??]containerrightgreen[/#if] hd" >
			</div>
			<div id="containerright3" class="containerright  [#if !member??]containerrightgreen[/#if] hd" >
				<div  style="margin : 150px 0 0 150px;">
					<font style="font-size:20px;">支付密码重设的链接已发送至</font>
					<font id="findemail" style="font-size:20px;color:#FFCE3F;"></font>
					<br/>
					<br/>
					<p style="font-size:15px;">链接48小时内有效，请及时处理</p>
					<br/>
					<br/>
					<a id="intoemail" class="submit" href="" target="_blank">进入邮箱</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>