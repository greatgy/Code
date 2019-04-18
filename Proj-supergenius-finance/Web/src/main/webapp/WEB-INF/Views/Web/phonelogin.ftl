[#ftl]
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>${title!'登录页'}</title>
	<meta name="Keywords" content="登录页" />
	<meta name="Description" content="登录页" />
	<!--# include file="/include/headcommon.html" -->
	<link rel="stylesheet" href="${basecss}/css/default/login.css">
</head>
<body>
		<div class="loginBox">
			<form class="forminfor">
				<ul class="loginList">
					<li>
						<label for="mail"><span class="loginitem usericon"></span></label>
						<input type="mail" id="mail" name="mail" placeholder="请输入登录邮箱" oninput="loginButton();">
					</li>
					<li>
						<label for="password"><span class="loginitem pswicon"></span></label>
						<input type="text" id="phonepassword" name="phonepassword" placeholder="请输入密码" oninput="loginButton();">
					</li>
				</ul>
				<div class="warninginform">
						<img src="${baseimg}/imgs/default/waring.png" alt="" />
						您输入的账号或密码不匹配，请重新输入
				</div>
				<a href="javascript:;" class="loginbtndisable">登录</a>
				<a href="javascript:;" class="loginbtn">登录</a> 
			</form>
			<div class="notAsign">
				<a href="http://${userbase}/resetpwd/usersn" class="forget">忘记密码？</a>
				<a href="http://${userbase}/register" class="userRegister">注册</a>
			</div>
		</div>
	<script type="text/javascript">
		function change(){
			
		}
		$(function () {
			$('#mail').focus(function () {
				$("form [name='phonepassword']").attr("type", "password");
				$(this).parent().find(".usericon").css("background","#f58a08 url(${baseimg}/imgs/default/loginUser.png) no-repeat center center");
			});
			$('#mail').blur(function () {
				$(this).parent().find(".usericon").css("background","#b8b8b8 url(${baseimg}/imgs/default/loginUser.png) no-repeat center center");
			});
			$('#phonepassword').focus(function () {
				$(this).attr("type", "password");
				$(this).parent().find(".pswicon").css("background","#f58a08 url(${baseimg}/imgs/default/psw.png) no-repeat center center");
			});
			$('#phonepassword').blur(function () {
				$(this).parent().find(".pswicon").css("background","#b8b8b8 url(${baseimg}/imgs/default/psw.png) no-repeat center center");
			});
			$(".logindisable").css("display", "block");
			$(".loginlink").css("display", "none");
			if($("form [name='mail']").val() != "" && $.trim($("form [name='phonepassword']").val()).length >= 6) {
				$(".loginbtndisable").css("display", "none");
				$(".loginbtn").css("display", "block");
			}
			/*登录点击事件*/
			$(".loginbtn").click(function(){
				login();
			})
		});
		
		/**
		 * 登录
		 */
		function login(){
			var mail = $("form [name='mail']").val().trim();
			var password = $("form [name='phonepassword']").val();
			if (mail == "" || password.length < 6) {
				return ;
			}
			var url = "${base}" + "/ajax/login";
			var args = {
					'email':mail,
					'password':password
			}
			$.post(getNoCachePath(url), args, function(data){
				if (data.success) {
					if (document.referrer == '') {
					    // 没有来源页面信息的时候，改成首页URL地址
					    window.location.replace("${base}/index");
					} else {
					    window.location.replace(document.referrer);
					}
				} else {
					$(".warninginform").show();
				}
			})
		}
		
		/**
		 * 登录按钮状态切换
		 */
		function loginButton(){
			if ($.trim($("form [name='mail']").val()) != "" && $("form [name='phonepassword']").val().length >= 6) {
				$(".loginbtndisable").css("display", "none");
				$(".loginbtn").css("display", "block");
			} else {
				$(".loginbtndisable").css("display", "block");
				$(".loginbtn").css("display", "none");
			}
		}
	</script>
</body>
</html>