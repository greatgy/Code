[#ftl]
[#assign security=JspTaglibs["http://www.springframework.org/security/tags"] /]
[#import "/WEB-INF/FtlLib/Macro/Public.ftl" as p]
<html>
<head>
<title>编辑管理员</title>
<style type="text/css" media="screen">

</style>
<script type="text/javascript">
<!--
$().ready(function(){
	var j = [
			    [#list urls as item]
					[#if item_has_next]
						{value:'${item}'},
					[#else] 
						{value:'${item}'}
					[/#if]
				[/#list]
			];
	$(".ch").each(function(){
		for(var o in j){
			var aname = j[o].value;
			if(aname == $(this).attr("value")){
				$(this).attr("checked","checked");
			}
		}
	});
});

//-->
</script>
</head>
<body>
	<div class="mybody">
		[#if msg??]<p class="red right">----${msg}----</p>[/#if]
		<form id="f_form" action="${base}${baseAdminPath}/admin/${bean.uid}/edit" method="post">
		<h2 class="bg2">编辑：</h2>
			<div class="bg1"><label>登录ID:</label><input value="${(bean.adminid)!}" name="adminid" type="text"/></div>
		 	<div class="bg1"><label>姓名</label><input class="txtbox" name="name" value="${(bean.name)!}" type="text" /></div>
			<div class="bg1"><label>手机号码:</label><input name="mobile" value="${(bean.mobile)!}" type="text" /></div>
			<div class="bg1"><label>email:</label><input name="email" value="${(bean.email)!}" type="text" /></div>
			[#list list as item]
				<label><div class="bg1"><input name="authority" type="checkbox" class="ch" value="${item.authority}"/>${item.authorityname}</div></label>
			[/#list]
			<div class="bg2"><label></label><input name="" value="提交" class="btn2" type="submit" /></div>
		</form>
	</div>
</body>
</html>