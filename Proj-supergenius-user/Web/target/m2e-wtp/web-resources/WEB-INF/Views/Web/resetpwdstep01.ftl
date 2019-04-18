[#ftl]
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>通过注册邮箱找回密码</title>
	<meta name="Keywords" content="会员中心，通过注册邮箱找回密码" />
	<meta name="Description" content="会员中心，通过注册邮箱找回密码" />
	<script type="text/javascript" src="${basejs}/js/libs/validator-1.0.0.js"></script>
	<link rel="stylesheet" type="text/css" href="${basecss}/css/default/findpw.css">
	<style>
    	.nextsubmit {
            display: inline-block;
            width: 200px;
            height: 40px;
            font-size: 16px;
            border-radius: 6px;
            line-height: 40px;
            color: #fff;
            background: #c61420;
            text-align: center;
            margin-left: 90px;
        }
	</style>
<script type="text/javascript">
<!--
    $(document).ready(function() {
     
        
        //提交表单数据
        $(".submit").click(function() {
            $("#reset_form").submit();
        } );
        if('${not_exist}') {
            $("#txtusersn").val($("#txtusersn").val());
            $("#txtusersn").focus();
        }
        if('${err_user_password_no_match_usersn}') {
            $("#txtemail").val($("#txtemail").val());
            $("#txtemail").focus();
        }

    } );
    
	function myReload() {
        var url = $("#checkCode").attr("src").split('?');
        $("#checkCode").attr("src", getNoCachePath(url[0]));
    }
-->
</script>
</head>
<body>
		<div class="container" id="user_sn">
			<div class="main">
				<div class="topcommon">
					<img src="${baseimg}/imgs/default/findpw01.png" alt="" />
					<h2>从这里，找到更好的自己！${addtime!'ok'}</h2>
				</div>
				<div class="midcommon">
					<h3>
						<img src="${baseimg}/imgs/default/login_logo.png" alt="" /> 
						找回密码
					</h3>
					<div class="mainmid">
						<form id="reset_form" action="${base}/resetpwd/step2?method=${origin}" method="post">
							<ul class="mailist">
								<li>
									<label for="txtusersn"><i>*</i>邮箱/手机号</label>
									<input type="text" placeholder="输入邮箱/手机号" class="mail" isemailormobile isnotempty="不能为空" id="txtusersn" ajaxvalid="${base}/ajax/user/usersnvalid|该用户不存在|请输入邮箱/手机号" name="token" value="${token}" />
									<div id="email" class="msg-box">
										<div id="focustxtusersn" class="msg-weak msg-prompt hd">
											<i></i>
											<span>请输入您的邮箱/手机号</span>
										</div>
										[#if not_exist??]
										<div id="errtxtusersn" class="msg-weak msg-error">
                                            <i></i>
                                            <span id="errmsgtxtusersn"> 该用户不存在</span>
                                        </div>
										[#else]
										<div id="errtxtusersn" class="msg-weak msg-error hd">
                                            <i></i>
                                            <span id="errmsgtxtusersn"></span>
                                        </div>
                                        [/#if]
										<div id="oktxtusersn" class="msg-weak msg-correct hd">
											<i></i>
											<span></span>
										</div>
									</div>
								</li>
								<li>
									<label for="txtcode"><i>*</i>验证码</label>
									<input type="text" placeholder="输入验证码"  isnotempty="不能为空" class="mail certify" id="txtcode" name="checkCode" ajaxvalid="${base}/ajax/user/usercode|验证码输入正确|验证码输入不正确" />
									<img src="${base}/v.jpg" id="checkCode" name="checkCode" onclick="myReload();" title="看不清？点击换一张" style=" width:100px;height:36px; margin-top:2px; margin-bottom:-13px;">
									<a href="javascript:;" onclick="myReload();" class="refresh">刷新</a>
									<div id="code" class="msg-box">
										<div id="focustxtcode" class="msg-prompt hd">
											<i></i>
											<span>请输入验证码</span>
										</div>
										[#if err_user_password_no_match_usersn??]
										<div id="errtxtcode" class="msg-error">
											<i></i>
											<span id="errmsgtxtcode">验证码错误，请重新输入</span>
										</div>
										[#else]
										<div id="errtxtcode" class="msg-error hd">
                                            <i></i>
                                            <span id="errmsgtxtcode"></span>
                                        </div>
										[/#if]
										<div id="oktxtcode" class="msg-correct hd">
											<i></i>
											<span></span>
										</div>
									</div>
								</li>
							</ul>
							<a id="submit" class="submit" href="javascript:;">找回密码</a>
						</form>
					</div>
				</div>
			</div>
		</div>
</body>
</html>