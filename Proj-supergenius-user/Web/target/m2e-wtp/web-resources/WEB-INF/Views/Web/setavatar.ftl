[#ftl]
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>账号管理--个人信息</title>
	<script type="text/javascript" src="${basejs}/js/jquery/plugins/jquery.Jcrop.min.js"></script>
	<link rel="stylesheet" type="text/css" href="${basecss}/css/jquery/jquery.Jcrop.css" media="screen" />
	<meta name="Keywords" content="会员中心账号管理，个人信息" />
	<meta name="Description" content="会员中心账号管理，个人信息" />
	<link rel="stylesheet" type="text/css" href="${basecss}/css/default/account.css"/>
<script type="text/javascript">
<!--
$(document).ready(function(){
  $('#imgcrop').Jcrop({setSelect:[0,0,180,180], minSize:[180, 180], onChange:changeCoords, onSelect:changeCoords, aspectRatio:1},function(){});
});

function changeCoords(c) {
	$("#txtx").val(c.x);
	$("#txty").val(c.y);
	$("#txtx2").val(c.x2);
	$("#txty2").val(c.y2);
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
<script type="text/javascript">
<!--
function autosubmit(file) {
	$(file).parents("form").submit();
}
//-->
</script> 
			<div class="containerright  [#if !member??]containerrightgreen[/#if] ">
				<h2>
					修改头像
				</h2>
				<form action="${base}/my/user/setavatar" method="post" enctype="multipart/form-data" style="margin:50px 30px;">
                	[#if avatar??]
                		<input type="hidden" name="avatar" value="${avatar}" />
	                    <img src="${userimg}${avatar}" id="imgcrop" />
	                    <input id="txtx" type="hidden" name="x" value="" />
	                    <input id="txty" type="hidden" name="y" value="" />
	                    <input id="txtx2" type="hidden" name="x2" value="" />
	                    <input id="txty2" type="hidden" name="y2" value="" />
	                    <div class="editbtns">
	                    <label class="sbtn"><input type="submit" value="保存" class="submit"/></label>
	                    <a href="${base}/my/user/setavatar" class="editcancel">取消</a>
	                    </div>
                	[#else]
                    	<label><span>头像：</span><input name="file" type="file" onchange="autosubmit(this)" style="width:160px" />(图片格式为jpg、png或gif)<br /></label>
                	[/#if]
                </form>
			</div>
		</div>
	</div>
</body>
</html>