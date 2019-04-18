[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Admin.ftl" as a]
<head>
<title>${title}管理</title>
<script type="text/javascript" src="${basejs}/ckeditor/ckeditor.js"></script>
<style type="text/css" media="screen">

</style>
</head>
<body>
	<div class="mybody">
	[#if err.isEmpty??]
		<h4 class="alert_error">更新失败</h4>
	[/#if]
	[#if msg.doSuccess??]
		<h4 class="alert_success">更新成功</h4>
	[/#if]
		<form id="f_form" action="${base}${baseAdminPath}/content/${bean.type}/edit" method="post" enctype="multipart/form-data">
			<input name="oid" value="${(bean.oid)!}" type="hidden" />
			<table cellpadding="5" style="text-align:left;">
				<tr>
	                <td>标题:</td>
	                <td><input class="easyui-validatebox" type="text" name="title" value="${bean.title}" data-options="required:true" /></td>
	            </tr>
	            [#if !bean.isTypeCompanyintroduce]
		            <tr>
		                <td>内容图:</td><td><img style="width:200px; height:200px" src="${webimg}${(bean.imgmedium)!}" /></td>
		                 <td><input id="file" type="file" name="file" /></td>
		            </tr>
	            [/#if]
	            <tr>
					<td>内容:</td>
			    	<td>[@a.ckeditor name="content" content="${(bean.content)!}" channel="content"/]
			    	</td>
			    </tr>
			    [#if bean.isTypeCompanyintroduce]
				    <tr>
						<td>简介（首页简介）:</td>
				    	<td>[@a.ckeditor name="summary" content="${(bean.summary)!}" channel="content"/]</td>
				    </tr>
	            [/#if]
			</table>
		    <div style="padding:5px 0;">
		        <a href="#" class="easyui-linkbutton" onclick="submit(this)">提交</a>
		    </div>
	    </form>
	</div>
</body>
</html>