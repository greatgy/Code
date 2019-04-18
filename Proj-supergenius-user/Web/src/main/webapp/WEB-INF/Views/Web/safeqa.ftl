[#ftl]
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>账号管理--账户保护设置密保问题</title>
	<meta name="Keywords" content="会员中心账号管理，账户保护设置密保问题" />
	<meta name="Description" content="会员中心账号管理，账户保护设置密保问题" />
	<link rel="stylesheet" type="text/css" href="${basecss}/css/default/account.css">
<script type="text/javascript">
	$(function() {
		[#if errmsg??]
			alert("最少绑定一个账号,请确认后解绑");
		[#elseif err_qq_alreadybind??]
			alert("qq号已经被其他账号绑定");	
		[#elseif err_sina_alreadybind??]
			alert("微博号已经被其他账号绑定");	
		[#elseif err_wx_alreadybind??]
			alert("微信号已经被其他账号绑定");	
		[/#if]
		$(".save").click(function() {
			if ( $("#answertxt").val() == "" || $("#answer2txt").val() == "" ) {
				return false;
			}	
			$("#answerform").submit();
		});
		
		$("#answer2txt").keydown(function (event) {
		    if (event.keyCode == 13) {
		        $("#answerform").submit();
		    }
		})
	});

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
					[#-- 
					<li class="accountitem04">
						<a href="${base}/my/user/setemail"><span>修改绑定邮箱</span></a>
					</li> --]
					<li class="accountitem05 activeli">
						<a href="${base}/my/user/safeqa"><span>账户保护</span></a>
					</li>
					<li class="accountitem06">
                        <a href="${base}/my/user/news"><span>我的消息</span></a>
                    </li>
				</ul>
			</div>
			<div class="containerright  [#if me.status == '11']containerrightgreen[/#if] ">
				<h2>账户保护</h2>
				<ul class="protectlist">
					<li>
						<img src="${baseimg}/imgs/default/[#if !member??]gemail.png[#else]email.png[/#if]" alt="" />
						<div class="protectmid">
							<h3>绑定邮箱&nbsp;&nbsp;[#if me.email?? && me.email !=""]${me.email}[/#if]</h3>
							<span>绑定邮箱后可以用来登录天财评论、并购平台、人间仙境、职业道德培训、职业经理人培训等所有超天才产品。</span>
						</div>
						[#if me.email?? && me.email !=""]
							<div class="setresult">
								<a href="${base}/my/user/setemail?change=email" class="setquestions">[更换]</a>
								<a href="${base}/my/unbind?param=1" class="setquestions">[解绑]</a>
								<span>已设置</span>
							</div>
						[#else]
							<div class="setresult">
								<a href="${base}/my/bind?param=1" class="setquestions">[设置]</a>
								<span>未设置</span>
							</div>
						[/#if]
					</li>
					<li>
						<img src="${baseimg}/imgs/default/[#if !member??]giPhone.png[#else]iphone.png[/#if]" alt="" />
						<div class="protectmid">
							<h3>绑定手机&nbsp;&nbsp;[#if me.mobile?? && me.mobile !=""]${me.mobile}[/#if]</h3>
							<span>绑定手机后可以用来登录天财评论、并购平台、人间仙境、职业道德培训、职业经理人培训等所有超天才产品。</span>
						</div>
						[#if me.mobile?? && me.mobile !=""]
							<div class="setresult">
								<a href="${base}/my/user/setemail?change=mobile" class="setquestions">[更换]</a>
								<a href="${base}/my/unbind?param=2" class="setquestions">[解绑]</a>
								<span>已设置</span>
							</div>
						[#else]
							<div class="setresult">
								<a href="${base}/my/bind?param=2" class="setquestions">[设置]</a>
								<span>未设置</span>
							</div>
						[/#if]
					</li>
					<li>
						<img src="${baseimg}/imgs/default/[#if !member??]gwei.png[#else]wei.png[/#if]" alt="" />
						[#if me.wx?? && me.wx != ""]
							<div class="protectmid">
								<h3>[#if wx_nickname??]${wx_nickname}[#else]已绑定微信[/#if]</h3>
								<span>绑定微信后可以用来登录天财评论、并购平台、人间仙境、职业道德培训、职业经理人培训等所有超天才产品。</span>
							</div>
							<div class="setresult">
								<a href="/account/changemail.html" class="setquestions">[更换]</a>
								<a href="${base}/my/unbind?param=3" class="setquestions">[解绑]</a>
								<span>已设置</span>
							</div>
						[#else]
							<div class="protectmid">
								<h3>绑定微信</h3>
								<span>绑定微信后可以用来登录天财评论、并购平台、人间仙境、职业道德培训、职业经理人培训等所有超天才产品。</span>
							</div>
							<div class="setresult">
								<a href="${base}/login/wx" class="setquestions">[设置]</a>
								<span>未设置</span>
							</div>
						[/#if]
					</li>
					<li>
						<img src="${baseimg}/imgs/default/[#if !member??]gsina.png[#else]rsina.png[/#if]" alt="" />
						[#if me.sina?? && me.sina != ""]
							<div class="protectmid">
								<h3>[#if sina_nickname??]${sina_nickname}[#else]已绑定微博[/#if]</h3>
								<span>绑定微博后可以用来登录天财评论、并购平台、人间仙境、职业道德培训、职业经理人培训等所有超天才产品。</span>
							</div>
							<div class="setresult">
								<a href="/account/changemail.html" class="setquestions">[更换]</a>
								<a href="${base}/my/unbind?param=4" class="setquestions">[解绑]</a>
								<span>已设置</span>
							</div>
						[#else]
							<div class="protectmid">
								<h3>绑定微博</h3>
								<span>绑定微博后可以用来登录天财评论、并购平台、人间仙境、职业道德培训、职业经理人培训等所有超天才产品。</span>
							</div>
							<div class="setresult">
								<a href="${base}/login/weibo" class="setquestions">[设置]</a>
								<span>未设置</span>
							</div>
						[/#if]					
					</li>
					<li>
						<img src="${baseimg}/imgs/default/[#if !member??]gqq.png[#else]rqq.png[/#if]" alt="" />
						[#if me.qq?? && me.qq != ""]
						<div class="protectmid">
							<h3>[#if qq_nickname??]${qq_nickname}[#else]已绑定QQ[/#if]</h3>
							<span>绑定QQ后可以用来登录天财评论、并购平台、人间仙境、职业道德培训、职业经理人培训等所有超天才产品。</span>
						</div>
							<div class="setresult">
								<a href="${base}/login/qq" class="setquestions">[更换]</a>
								<a href="${base}/my/unbind?param=5" class="setquestions">[解绑]</a>
								<span>已设置</span>
							</div>
						[#else]
						<div class="protectmid">
							<h3>绑定QQ</h3>
							<span>绑定手机后可以用来登录天财评论、并购平台、人间仙境、职业道德培训、职业经理人培训等所有超天才产品。</span>
						</div>
							<div class="setresult">
								<a href="${base}/login/qq" class="setquestions">[设置]</a>
								<span>未设置</span>
							</div>
						[/#if]
					</li>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>