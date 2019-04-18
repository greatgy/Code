[#ftl]
<html>
<head>
	<meta charset="UTF-8">
	<title>会员注册--实名认证</title>
	<meta name="Keywords" content="会员中心，会员注册，实名认证" />
	<meta name="Description" content="会员中心，会员注册，实名认证" />
	<link href="http://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
	<link href="http://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
	<link rel="stylesheet" href="${basejs}/js/dependancies/bootstrap-select-1.12.4/dist/css/bootstrap-select.min.css">
	<script src="http://cdn.bootcss.com/jquery/1.11.0/jquery.min.js" type="text/javascript"></script>
	<script src="http://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="${basejs}/js/dependancies/bootstrap-select-1.12.4/dist/js/bootstrap-select.js"></script>
	<script src="${basejs}/js/countrypicker.js"></script>
	
	<script type="text/javascript">
			var baseimg ='${baseimg}';
<!--
			$(function(){
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
					$(".strength span").removeClass("curstrength");
					switch(count)
					{
						case 1:
							$(".strength span:eq(0)").addClass("curstrength");
							break;
						case 2:
							$(".strength span:eq(1)").addClass("curstrength");
							break;
						case 3:
							$(".strength span:eq(2)").addClass("curstrength");
							break;
					}
			});
			
			$("#txtconfigpwd").keydown(function(event) {
			    if (event.keyCode == 13) {
			        $(".baseinfor").submit();
			    }
			})
	});
	
	    function registerValidHandler(){
		  var pwd = $("#txtpassword").val();
		  var pwd2 = $("#txtconfigpwd").val();
		  if(pwd != pwd2 || pwd2 == ""){
		      return false;
		  } else {
		      return true;
		  }
	   }
	   		
	function myReload() {
        var url = $("#checkCode").attr("src").split('?');
        $("#checkCode").attr("src", getNoCachePath(url[0]));
    }
	   		   
// -->
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
						<div class="steps">
							<span class="firstblock" style=" background: #fff url(${baseimg}/imgs/default/paybgred.png) no-repeat center center;">实名认证</span>
							<span class="firstblock" style="color: #333; background: #fff url(${baseimg}/imgs/default/paybgwhite.png) no-repeat center center;">支付会员费</span>
						</div>
						<div class="registercontmiddle">
							<h2>成为会员需要缴纳会员费：<i class="money">10000，00</i>元</h2>
							<form  action="${base}/register_improve/${(user.uid)!}" class="baseinfor" method="post">
								<ul class="labelbase baseinforlist">
									<li>
										<label for="ways"><i>*</i>&nbsp;实名方式：</label>
										<select  id="ways">
											<option value="0">身份证</option>
											<option value="1">护照</option>
										</select>
									</li>
									<li>
										<label for="mail"><i>*</i>&nbsp;真实姓名：</label>
										<input type="text" id="txtname" isnotempty="" ischinese="" name="name"  placeholder="请填写与身份证/护照上一致的姓名" />
										<div id="focustxtname" class="tipnote tipright hd"><span id="focusmsgtxtname">请填写与身份证一致的姓名</span></div>
						            	<div id="errtxtname" class="tipnote tipwrong hd"><span id="errmsgtxtname">请输入中文</span></div>
						            	<div id="oktxtname" class="tipnote hd"><img src="${baseimg}/imgs/default/yes.png" alt="" /><span id="okmsgtxtname"></span></div>
									</li> 
									<li id="op1" class="hd">
									<label for="selectcountry"><i>*</i>&nbsp;国家：</label>
									<select id="selectcountry" name="selectcountry" class="selectpicker countrypicker" data-live-search="true" data-default="中国" data-flag="true"></select>
									</li>
									<li id="op0">
										<label for="txtidcard"><i>*</i>&nbsp;身份证号：</label>
										<input type="text" id="txtidcard"  class="InputText" isidentityid isnotempty name="identityid" ajaxvalid="${base}/ajax/my/user/verifyidcard|该身份证信息已经存在"/>
										<div id="focustxtidcard" class="tipnote tipright hd"><i></i><span id="focusmsgtxtidcard">身份证号校验，与名字是否一致</span></div>
                                        <div id="errtxtidcard" class="tipnote tipwrong hd"><span id="errmsgtxtidcard">请填写正确的身份证号</span></div>
                                        <div id="oktxtidcard" class="tipnote hd"><img src="${baseimg}/imgs/default/yes.png" alt="" /><span id="okmsgtxtID_number"></span></div>
									</li>
									<li>
										<label for="contactphone"><i>*</i>&nbsp;联系电话：</label>
										<input type="text" id="txtcontactphone" class="InputText" isnotempty="" ismobile="" name="mobile" type="text" placeholder="请输入正确的手机号码"/>
										<div id="focustxtcontactphone" class="tipnote tipright hd"><i></i><span id="focusmsgtxtcontactphone">请输入正确的手机号码</span></div>
                                        <div id="errtxtcontactphone" class="tipnote tipwrong hd"><span id="errmsgtxtcontactphone">手机号码错误</span></div>
                                        [#if (errs.err_mobile)??]
                                         <div id="errtxtcontactphone" class="tipnote tipwrong"><span id="errmsgtxtcontactphone">手机号码错误</span></div>
                                        [/#if]
                                        <div id="oktxtcontactphone" class="tipnote hd"><img src="${baseimg}/imgs/default/yes.png" alt="" /><span id="okmsgtxtcontactphone"></span></div>
									</li>
									<li>
										<label for="certifycode"><i>*</i>&nbsp;验&nbsp;证&nbsp;码：</label>
										<input type="text" id="certifycode" name="checkCode"/>
										<img src="${base}/v.jpg" id="checkCode" name="checkCode" onclick="myReload();" title="看不清？点击换一张" style=" width:100px;height:34px; margin-top:2px;vertical-align: top;">
										<a href="javascript:;" onclick="myReload();" class="refresh">刷新</a>
									    [#if (errs.err_captcha)??]
  										<div id="errtxtcertifyCode" class="tipnote tipwrong"><i></i><span id="errmsgtxtcertifyCode">验证码错误</span></div>
                     	               [/#if]
									</li>
								</ul>
								<div class="baseinforbottom">
									<h2>已为您创建了超天才支付账户，请为您的账户设置支付密码</h2>
									<ul class="labelbase ">
										<li>
											<label for="password"><i>*</i>&nbsp;设置支付密码：</label>
											<input type="password" id="txtpassword" name="password" isnotempty="" customreg="^.{6,16}$|请填写6-16位字符" ispwd="请输入6-16位，包括数字和字母" placeholder="请输入6-16位，包括数字和字母" />
											<div id="focustxtpassword" class="tipnote tipright hd"><span id="focusmsgtxtpassword">请输入6-16位，包括数字和字母</span></div> 
		                                 	<div id="errtxtpassword" class="tipnote tipwrong hd"><span id="errmsgtxtpassword">请输入6-16位，包括数字和字母</span></div>
		                       	            <div id="oktxtpassword" class="tipnote hd"><img src="${baseimg}/imgs/default/yes.png" alt="" /><span id="oktxtpassword"></span></div>
										</li>
										<li>
											<div class="strength">
												<span>弱</span>
												<span>中</span>
												<span>强</span>
											</div>
										</li>    
										<li>
										<label for="txtconfigpwd"><i>*</i>&nbsp;确认支付密码：</label>
										<input type="password" id="txtconfigpwd" name="password2" ispwd="" customvalid="registerValidHandler()|两次输入的密码不一致" placeholder="请再次输入密码" />
										<div id="focustxtconfigpwd" class="tipnote tipright hd"><span id="focusmsgtxtconfigpwd">请确支付认密码</span></div>
	                    	            <div id="errtxtconfigpwd" class="tipnote tipwrong hd"><span id="errmsgtxtconfigpwd" >两次输入的密码不一致</span></div>
	                    	            <div id="oktxtconfigpwd" class="tipnote hd"><img src="${baseimg}/imgs/default/yes.png" alt=""/><span id="okmsgtxtconfigpwd" ></span></div>
									</li>
									</ul>
								</div>
								<div class="formbottom">
									<button class="submit nextsubmit" type="submit">完成，下一步</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	<script src="${basejs}/js/libs3/jquery.lavalamp.min.js"></script>
	<link rel="stylesheet" type="text/css" href="${basecss}/css/default/register.css">
	<script type="text/javascript" src="${basejs}/js/libs/validator-1.0.0.js"></script>
<script type="text/javascript">
	$("#ways").change(function () {  
            var ss = $(this).children('option:selected').val();  
            if (ss == "1") {  
  				var content = '';
  					content+= '<label for="txtpassport"><i>*</i>&nbsp;护照：</label>';
					content+='<input type="text" id="txtpassport"  class="InputText" name="passport"  isnotempty=""  />';
					content+='<div id="focustxtpassport" class="tipnote tipright hd"><i></i><span id="focusmsgtxtpassport">护照号校验，与名字是否一致</span></div>';
					content+='<div id="errtxtpassport" class="tipnote tipwrong hd"><span id="errmsgtxtpassport">请填写正确的护照号</span></div>';
					content+='<div id="oktxtpassport" class="tipnote hd"><img src="${baseimg}/imgs/default/yes.png" alt="" /><span id="okmsgtxtpassport"></span></div>';
					$("#op1").removeClass("hd");
					$("#op0").html(content);
            }  else if (ss == "0") {  
	             var content = ' <label for="txtID_number"><i>*</i>&nbsp;身份证号：</label>';
					content+='<input type="text" id="txtidcard"  class="InputText" isidentityid isnotempty name="identityid" ajaxvalid="${base}/ajax/my/user/verifyidcard|该身份证信息已经存在"/>';
					content+='<div id="focustxtidcard" class="tipnote tipright hd"><i></i><span id="focusmsgtxtidcard">身份证号校验，与名字是否一致</span></div>';
					content+='<div id="errtxtidcard" class="tipnote tipwrong hd"><span id="errmsgtxtidcard">请填写正确的身份证号</span></div>';
					content+='<div id="errtxtidcard" class="tipnote hd"><img src="${baseimg}/imgs/default/yes.png" alt="" /><span id="okmsgtxtID_number"></span></div>';
					$("#op0").html(content);      
					$("#op1").addClass("hd");
					$("#op2").html('');                     
            } 
        });
</script>
</body>
</html>