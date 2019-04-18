[#ftl]
[#assign security=JspTaglibs["http://www.springframework.org/security/tags"] /]
[#import "/WEB-INF/FtlLib/Macro/Public.ftl" as p]
<html>
<head>
<title>修改操作密码</title>
<style type="text/css" media="screen">
tr {line-height:30px;}
</style>
<script type="text/javascript">
<!--
//-->
</script>
</head>
<body>
	[#if err.pwd2IsNotMatched??]
	<h4 class="alert_error">两次输入的密码不一致</h4>
	[/#if]
	[#if msg.doSuccess??]
	<h4 class="alert_success">操作成功</h4>
	[/#if]
	<div class="easyui-panel" title="修改操作密码" style="width:600px;">
	<form action="${base}${baseAdminPath}/admindopwd" method="post">
        <table cellpadding="6">
            <tr>
                <td>姓名:</td>
                <td>${(admin.name)!}</td>
            </tr>
            <tr>
                <td>登录ID:</td>
                <td>${(admin.adminid)!}</td>
            </tr>
            <tr>
                <td>新操作密码:</td>
                <td><input class="easyui-validatebox" type="password" name="pwd" data-options="required:true" /></td>
            </tr>
            <tr>
                <td>确认新操作密码:</td>
                <td><input class="easyui-validatebox" type="password" name="pwd2" data-options="required:true" /></td>
            </tr>
        </table>
        <div style="text-align:center;padding:5px">
            <a href="" class="easyui-linkbutton" onclick="submit(this)">提交</a>
        </div>
    </form>
    </div>
</body>
</html>