[#ftl]
<html>
<head>
	<meta charset="UTF-8">
	<title>用户注册</title>
	<meta name="Keywords" content="会员中心，用户注册" />
	<meta name="Description" content="会员中心，用户注册" />
	<script type="text/javascript" src="${basejs}/js/libs/validator-1.0.0.js"></script>
	<link rel="stylesheet" type="text/css" href="${basecss}/css/default/register.css">
	<script type="text/javascript">
<!--
    function registerValidHandler(){
		var pwd = $("#txtpassword").val();
		var pwd2 = $("#txtconfigpwd").val();
		if(pwd != pwd2 ){
		    return false;
		} else {
		    return true;
		}
	}
	

    function chkProtocolValid(){ 
	    return $(".chkProtocolValid:checked").length == 1;
	}

	$(function(){
		var canSubmit = true;
			var regs = {
			isnum : /^.*[0-9]+.*$/,
			islish: /^.*[A-Za-z]+.*$/,
			issign: /^.*[_\!\.@#\$%\^&\*\(\)\[\]\\?\\\/\|\-~`\+\=\,\r\n\:\'\"。，；……！￥\{\}·]+.*$/
			};
			$("#txtpassword").keyup(function(){
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
            $("#submitform").click(function(){
	            var pwd = $("#txtpassword").val();
				var pwd2 = $("#txtconfigpwd").val();
	            var email=$("#txtemail").val();
	            var code=$("#txtcode").val();
	            if(pwd== "" | pwd2=="" | email == ""| code == ""){
	            	return false;
	            }
                var checked = $("input[name='agreement']:checked");
                if (checked.length == 0) {
                    $("#aggrement").removeClass("hd");
                    return false;
                }
                 if($("#checkbox").is(":checked")){
    	             $("button").attr("disable","true");
    	        }
    	        
                $("#regform").submit();
            });
            $("input").blur(function(){
			  var pwd = $("#txtpassword").val();
				var pwd2 = $("#txtconfigpwd").val();
	            var email=$("#txtemail").val();
	            var code=$("#txtcode").val();
	            if(pwd== "" | pwd2=="" | email == ""| code == ""){
	            	$("#submitform").removeClass("submit");
    	       		$("#submitform").addClass("submit1");
    	       		$("button").attr("disable","true");
	            	return false;
	            }else{
	            	$("#submitform").removeClass("submit1");
    	       		$("#submitform").addClass("submit");
    	       		$("button").attr("disable","false");
	            }
			});
			            
         $("#certifycode").keydown(function(event) {
                if (event.keyCode == 13) {
                    $("#submitform").click();
                    return false;
                }
            })
        });	
//-->
</script>
<script type="text/javascript">
	var countdown=60;   
	function settime(obj) {
		if(countdown == 60){
		    	var email=$("#txtemail").val();
		    	var reg=/([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)|^((\(\d{2,3}\))|(\d{3}\-))?(13|14|15|18)\d{9}$/;
		    	if(!reg.test(email)){
					$("#errtxtemail").removeClass("hd");
					 return;
				}
		    	$.ajax({
					url:"${base}/sendcode?email="+email
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
						用户注册
					</div>
					<div class="registercontainer">
						<div class="registercontmiddle">
							<form name="form" id="regform" action="${base}/register" method="post">
								<ul class="labelbase">
									<li>
										 <label for="mail">[#-- <i>*</i>手机号/邮箱：--]</label>
										<input type="text" id="txtemail" name="email"   placeholder="手机号/邮箱" ajaxvalid="${base}/ajax/user/emailvalid|该邮箱已被注册，请直接登录|该手机号已被注册，请直接登录|请输入163/qq/sina等常用邮箱或手机号" maxlength="100" value="${email}"/>
										
                    	                 <div id="errtxtemail" class="tipnote tipwrong hd"><i></i><span id="errmsgtxtemail">请输入正确的邮箱或手机号</span></div>
									</li>
									<li>
										<label for="txtcode">[#-- <i>*</i>验证码： --]</label>
										<input type="text" id="txtcode" name="code" style="width:212px;"  ajaxvalid="${base}/ajax/user/code|验证码输入正确|验证码输入错误，请重新输入" placeholder="验证码" />
										<button type="button" class="sendcode" onclick="settime(this)" style=" background-color: #faf8f5;">获取验证码</button>
	                                 	<div id="errtxtcode" class="tipnote tipwrong hd"><i></i><span id="errmsgtxtcode">请输入验证码</spa></div>
	                                 	<div id="oktxtcode" class="tipnote hd"><i></i><img src="${baseimg}/imgs/default/yes.png" alt="" /><span id="oktxtpassword"></span></div>
									</li>
									<li>
										<label for="configpw">[#-- <i>*</i>设置密码： --]</label>
										<input type="password" id="txtpassword" name="password" placeholder="密码" />
	                       	            <div id="oktxtpassword" class="tipnote hd"><i></i><img src="${baseimg}/imgs/default/yes.png" alt="" /><span id="oktxtpassword"></span></div>
									</li>
									<div class="safe">
										<span>弱</span>
										<span>中</span>
										<span>强</span>
									</div>
									<li>
										<label for="configpw">[#-- <i>*</i>确认密码： --]</label>
										<input type="password" id="txtconfigpwd" name="password2" customvalid="registerValidHandler()|两次输入的密码不一致" placeholder="确认密码" />
	                    	            <div id="errtxtconfigpwd" class="tipnote tipwrong hd"><i></i><span id="errmsgtxtconfigpwd" >两次输入的密码不一致</span></div>
									</li>
									<li style="  margin-left: 110px;  ">
										<input type="checkbox" id="checkbox" checked="checked" value="1" class="chkProtocolValid" name="agreement"  isfocus="false">
										<label for="checkbox" class="agreens">
											我已阅读并接受
											<a href="${base}/userrule" class="agreement" target="_blank">《用户协议》</a>
											<a href="${base}/legal" class="agreement" target="_blank">《法律声明》</a>
											<a href="${base}/managerule" class="agreement" target="_blank">《社交网络平台管理规定》</a>
										</label>
										<li id="aggrement" class="hd">&nbsp;&nbsp;&nbsp;&nbsp;<font color="red">请阅读并接受注册条款</font></li>
									</li>
								</ul>
								<div class="formbottom">
									<button id="submitform" class="submit1" >立即注册</button>
									<a href="${base}/login" class="loginto">已有帐号，立即登录&nbsp;&gt;&gt;</a>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
</body>
</html>