[#ftl]
[#assign security=JspTaglibs["http://www.springframework.org/security/tags"] /]
[#import "/WEB-INF/FtlLib/Macro/Public.ftl" as p]
<html>
<head>
<title>网站初始化</title>
<style type="text/css" media="screen">
tr {line-height:30px;}
.combo input.txt:focus {outline: 0; border: 0px solid #71a0df; margin: 0px 0px; }
</style>
<script type="text/javascript">
<!--
//-->
</script>
</head>
<body>
<section>
	[#if msg.doSuccess??]
	<h4 class="alert_success">操作成功</h4>
	[/#if]
	<div class="easyui-panel" title="执行操作" style="width:600px;">
		<form action="${base}${baseAdminPath}/command" method="post">
	        <table cellpadding="6">
	            <tr>
	                <td>操作:</td>
	                <td>
	                	<select id="cc" class="easyui-combobox" name="action">
						    <option value="">请选择</option>
						    <option value="searchIndexInitialize_finance">天财评论全站索引初始化</option>
						    <option value="searchIndexInitialize_startup">创业平台全站索引初始化</option>
						    <option value="searchIndexInitialize_ai">天才AI全站索引初始化</option>
						    <option value="searchIndexInitialize_career">天才职场全站索引初始化</option>
						    <option value="searchIndexInitialize_gupage">顾雏军专栏全站索引初始化</option>
						    <option value="searchIndexInitialize_enterpriser">引资购商全站索引初始化</option>
						    <option value="searchIndexInitialize_life">天才人生全站索引初始化</option>
						    <option value="searchIndexInitialize_managernews">职业经理人培训全站索引初始化</option>
						    <option value="searchIndexInitialize_entrepreneur">企业家培训全站索引初始化</option>
						    <option value="searchIndexInitialize_moralnews">职业道德培训文章全站索引初始化</option>
						    <option value="">其他</option>
						</select>
	                </td>
	            </tr>
	            <!--<tr>
	                <td>操作密码:</td>
	                <td><input class="easyui-validatebox" type="password" name="pwd" data-options="required:true" validType="remote['http://localhost/']" /></td>
	            </tr>-->
	        </table>
	        <div style="text-align:center;padding:5px">
	            <a href="" class="easyui-linkbutton" onclick="submit(this)">提交</a>
	        </div>
	    </form>
    </div>
</section>
</body>
</html>