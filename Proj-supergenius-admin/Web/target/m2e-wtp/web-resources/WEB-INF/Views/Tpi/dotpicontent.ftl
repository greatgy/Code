[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Admin.ftl" as a]
<html>
<head>
<title>${channelname}管理</title>
<script type="text/javascript" src="${basejs}/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
<!--
//-->
</script>
</head>
<body>
	<section>
		[@a.search]
        	<tr>
	            <td>状态: 
		            <label for="sall">全部</label><input id="sall" name="status" value="" type="radio" class="radio" />
		            <label for="senable">正常</label><input id="senable" name="status" value="1" type="radio" class="radio" />
		            <label for="sdisable">冻结</label><input id="sdisable" name="status" value="0" type="radio" class="radio" />
	            </td>
	        </tr>
	        <tr>
				<td>开始创建时间: <input id="timestart" name="createtimestart" type="text" data-options="editable:false" class="easyui-datebox" /></td>
				<td>结束创建时间: <input id="timeend" name="createtimeend" type="text" editable="false" class="easyui-datebox" /></td>
		 	</tr>
		[/@a.search]
		
		[@a.datagrid]
			<th data-options="field:'typeName',width:100">类型</th>
			<th data-options="field:'title',width:100">标题</th>
			<th data-options="field:'createtimeStr',width:200">创建时间</th>
        [/@a.datagrid]
        
        [@a.toolbar]
	    [/@a.toolbar]
	    [@a.tools /]
	</section>
	
	[@a.addsection]
		<tr>
        	<td>类型</td>
        	<td>
	            <select name="type">
				    [#assign ks = enums?keys]
			    	[#list ks as item]
				    <option value="${item}">${enums[item]}</option>
				    [/#list]
	            </select>
        	</td>
        </tr>
        <tr>
            <td>标题</td>
            <td><input class="easyui-validatebox long" type="text" name="title" data-options="required:true" /></td>
        </tr>
        <tr>
            <td>内容</td>
            <td>[@a.ckeditor name="content"/]</td>
        </tr>
    [/@a.addsection]
	
	
	[@a.editsection canuploadfile=true]
		<tr>
            <td>标题<input name="uid" type="hidden" set-key="uid"/></td>
            <td><input class="easyui-validatebox long" type="text" name="title" data-options="required:true" /></td>
        </tr>
         <tr>
          	<td>内容</td>	
            <td>[@a.ckeditor name="content" /]</td>
		</tr>
	[/@a.editsection]
	
	[@a.viewsection]
        <tr colspan="2">
            <td>类型：</td>
            <td><span set-key="typeName"></span></td>
        </tr>
        <tr colspan="2">
            <td>标题：</td>
            <td><span set-key="title"></span></td>
        </tr>
        <tr colspan="2">
            <td>内容：</td>
            <td>[@a.ckeditor name="content" /]</td>
        </tr>
        <tr colspan="2">
            <td>创建时间：</td>
            <td><span set-key="createtimeStr"></span></td>
        </tr>
	[/@a.viewsection]
	
</body>
</html>