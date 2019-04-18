[#ftl]
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>账号管理--个人信息</title>
	<meta name="Keywords" content="会员中心账号管理，个人信息" />
	<meta name="Description" content="会员中心账号管理，个人信息" />
	<link rel="stylesheet" type="text/css" href="${basecss}/css/default/account.css">
<script type="text/javascript" src="${basejs}/js/libs/validator-1.0.0.js"></script>
</head>
<body>
	<div class="wraper">
		<div class="container">
			<div class="containerleft [#if !member??]containerleftgreen[/#if]">
				<h2>账号管理</h2>
				<ul class="accountlist">
					<li class="accountitem01 activeli">
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
					<li class="accountitem05">
						<a href="${base}/my/user/safeqa"><span>账户保护</span></a>
					</li>
					<li class="accountitem06">
                        <a href="${base}/my/user/news"><span>我的消息</span></a>
                    </li>
				</ul>
			</div>
			<div class="containerright  [#if !member??]containerrightgreen[/#if] ">
				<h2>
					编辑个人资料
				</h2>
				<div class="personfile">
					<div class="personfiletop">
						<div class="filetopleft">
							<img [#if me.avatar??] src="${userimg}${me.avatar}"[#else]src="${baseimg}/imgs/web/noavatar.png"[/#if] />
							<a href="${base}/my/user/setavatar">修改头像</a>
						</div>
						<ul class="filetopright">
							<li>
								<span style="width: 300px;">${me.username}</span>
								[#if me.gender == 0]
								<img src="${baseimg}/imgs/default/lady.png" alt="" />
								[#elseif me.gender == 1]
								<img src="${baseimg}/imgs/default/man.png" alt="" />
								[/#if]
							</li>
							<li>
								<span>身份证号</span>
								<b class="b01">${me.identityid}</b>
							</li>
							<li>
								<span>会员号</span>
								[#if !member??]
								<a href="${base}/register_improve/${me.uid}" class="bemember">成为会员</a>
								[#else]
								<b class="b02">${me.usersn}</b>
								[/#if]
							</li>
							<li>
								<span>账号</span>
								<b class="b03">${me.showname}</b>
							</li>
						</ul>
					</div>
					<form action="${base}/my/user/editinfo" method="post">
						<ul class="personfilebottom editfilebottom">
							<li>
								<span class="filetopleft"><label for="txtnickname">昵称</label></span>
								<input type="text" name="nickname" id="txtnickname" value="${me.nickname}" autocomplete="off" />
							</li>
							<li>
								<span class="filetopleft"><label for="txtdivision">供职单位</label></span>
								<input type="text" name="company" id="txtdivision" value="${me.company}" autocomplete="off" placeholder="您供职的单位(最多20个字)" maxlength="20"/>
							</li>
							<li>
								<span class="filetopleft"><label for="txtsubordinate">所属部门</label></span>
								<input type="text" name="department" id="txtsubordinate" value="${me.department}" autocomplete="off" placeholder="您所属的部门(最多10个字)" maxlength="10" />
							</li>
							<li>
								<span class="filetopleft"><label for="txtjob">担任职务</label></span>
								<input type="text" name="job" id="txtjob" value="${me.job}" autocomplete="off" placeholder="您担任的职务(最多10个字)" maxlength="10" />
							</li>
							<li>
								<span class="filetopleft"><label for="txtsummary">个人简介</label></span>
								<textarea name="summary" id="txtsummary" summary="off" placeholder="个人简介(最多280个字)" maxlength="280" >${me.summary}</textarea>
							</li>
						</ul>
						<div class="editbtns">
							<a href="${base}/my/user/info" class="editcancel">取消</a>
							<button class="submit" >保存</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>