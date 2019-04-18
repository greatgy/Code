[#ftl]
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>通过注册邮箱找回密码--设定新密码</title>
	<meta name="Keywords" content="会员中心，通过注册邮箱找回密码设定新密码" />
	<meta name="Description" content="会员中心，通过注册邮箱找回密码设定新密码" />
	<script type="text/javascript" src="${basejs}/js/libs/validator-1.0.0.js"></script>
	<link rel="stylesheet" type="text/css" href="${basecss}/css/default/findpw.css">
<script type="text/javascript">
<!--
    //提交表单
    $(function() {
        $(".submit").click(function() {
            $("#reset_form").submit();
        } );
        $('input[name="pwd"]').keydown(function(){  
            if(event.keyCode==13){
                $(".submit").click();
                return false;
            }  
        });
        $('input[name="repwd"]').keydown(function(){  
            if(event.keyCode==13){
                $(".submit").click();
                return false;
            }  
        });
    } );
    
    function registerValidHandler(){
        var pwd = $("#txtuserpwd").val();
        var pwd2 = $("#txtnewpsd").val();
        if(pwd != pwd2 || pwd2 == ""){
            return false;
        } else {
            return true;
        }
    }
    
    //匹配密码强度
    $(function() {
            var regs = {
            isnum : /^.*[0-9]+.*$/,
            islish: /^.*[A-Za-z]+.*$/,
            issign: /^.*[_\!\.@#\$%\^&\*\(\)\[\]\\?\\\/\|\-~`\+\=\,\r\n\:\'\"。，；……！￥\{\}·]+.*$/
            };
            $("#txtuserpwd").keyup(function() {
                var count = 0;
                for(var b in regs) {
                        if(regs[b].test($(this).val())) count++;
                    }
                     $(".strengthli span").removeClass("saferange");
                     $(".strengthli span").addClass("strength");
                    switch(count)
                    {
                        case 1:
                            $(".strengthli span:eq(0)").removeClass("strength");
                            $(".strengthli span:eq(0)").addClass("saferange");
                            break;
                        case 2:
                            $(".strengthli span:eq(1)").removeClass("strength");
                            $(".strengthli span:eq(1)").addClass("saferange");
                            break;
                        case 3:
                            $(".strengthli span:eq(2)").removeClass("strength");
                            $(".strengthli span:eq(2)").addClass("saferange");
                            break;
                    }
            } );
    } );
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
						设定新密码
					</h3>
					<div class="mainmid">
						<form action="${base}/resetpwd/step5?method=${origin}" id="reset_form" method="post">
							<ul class="mailist">
								<li>
									<label for="txtusersn"><i>*</i>新密码</label>
									<input type="password" id="txtuserpwd" ispwd="由6～16位数字、字母、特殊字符的任意组合组成，&lt;br /&gt;字母区分大小写" isnotempty="不能为空" class="mail" name="pwd" onkeyup="$(this).trigger('valid'); $(this).focus()"/>
								    <div id="email" class="msg-box">
                                        <div id="focustxtuserpwd" class="msg-weak msg-prompt hd">
                                            <i></i>
                                            <span>请输入您的新密码</span>
                                        </div>
                                        [#if is_Enemy??]
                                        	<div id="errtxtuserpwd" class="msg-weak msg-error">
	                                            <i></i>
	                                            <span id="errmsgtxtuserpwd">您还未登录</span>
	                                        </div>
                                        [#elseif err_pwd??]
	                                        <div id="errtxtuserpwd" class="msg-weak msg-error">
	                                            <i></i>
	                                            <span id="errmsgtxtuserpwd">您的密码格式错误</span>
	                                        </div>
                                        [#else]
	                                        <div id="errtxtuserpwd" class="msg-weak msg-error hd">
	                                            <i></i>
	                                            <span id="errmsgtxtuserpwd">不能为空</span>
	                                        </div>
                                        [/#if]
                                        <div id="oktxtuserpwd" class="strengthli hd">
                                        <span class="strength">弱</span>
									    <span class="strength">中</span>
									    <span class="strength">强</span>
                                        </div>
                                    </div>
								</li>
								
								<li>
									<label for="txtnewpsw"><i>*</i>确认密码</label>
									<input type="password" id="txtnewpsd" placeholder="确认新密码" customvalid="registerValidHandler()|两次输入的密码不一致" class="mail" name="repwd"/>
								    <div class="msg-box">
                                        [#if err_pwd2_equal??]
                                        <div id="errtxtnewpsd" class="msg-weak msg-error">
                                            <i></i>
                                            <span id="errmsgtxtnewpsd">两次输入密码不一致，请重新设置新密码！</span>
                                        </div>
                                        [#else]
                                        <div id="errtxtnewpsd" class="msg-weak msg-error hd">
                                            <i></i>
                                            <span id="errmsgtxtnewpsd">不能为空</span>
                                        </div>
                                        [/#if]
                                        <div id="oktxtnewpsd" class="msg-weak msg-correct hd">
                                            <i></i>
                                            <span></span>
                                        </div>
                                       </div>
								</li>
							</ul>
							<a class="submit" href="javascript:subform">设定完成</a>
							<input type="hidden" name="token" value="${token}" />
						</form>
					</div>
				</div>
			</div>
		</div>
</body>
</html>