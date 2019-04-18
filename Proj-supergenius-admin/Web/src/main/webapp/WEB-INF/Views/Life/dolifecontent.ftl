[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Admin.ftl" as a]
<html>
<head>
<title>内容管理</title>
<script type="text/javascript" src="${basejs}/ckeditor/ckeditor.js"></script>
</head>
<body>
	<section>
		[@a.search]
	        <tr>
	        	<td colspan="10">
		        	<label for="name">名称: </label><input id="name" type="text" name="name" class="search"/>&nbsp;&nbsp;&nbsp;
	        	</td>
	        </tr>
		[/@a.search]
		
		[@a.datagrid]
            <th data-options="field:'name',width:100">名称</th>
            <th data-options="field:'statusName',width:100">状态</th>
            <th data-options="field:'createtimeStr',width:150">创建时间</th>
            <th data-options="field:'adminname',width:150">操作人</th>
	    [/@a.datagrid]
	    
	    [@a.toolbar canadd=false canedit=true candel=false]
        [/@a.toolbar]
	    [@a.tools /]
	    
	</section>
   [@a.editsection]
	    <input type="hidden" name="uid">
	    <input type="hidden" name="cid">
	    <tr>
            <td>名称:</td>
            <td><input type="text" name="name"></input></td>
        </tr>
        <tr>
            <td>简介:</td>
            <td><input type="text" name="summary"></input></td>
        </tr>
        <tr>
            <td>内容:</td>
            <td>
                <textarea class="easyui-validatebox" name="content" data-options="required:true" cols="110" rows="80"></textarea>
            </td>
        </tr>
	[/@a.editsection]
	[@a.viewsection]
	    <tr>
            <td>内容名称:</td></td>
            <td><span set-key="name"></span></td>
        </tr>
        <tr>
            <td>简介:</td>
            <td><span set-key="summary"></span></td>
        </tr>
        <tr>
            <td>内容:</td>
            <td><span set-key="content"></span></td>
        </tr>
        <tr>
            <td>相关记录:</td>
            <td><span set-key="data"></span></td>
        </tr>
        <tr>
            <td>创建时间:</td>
            <td><span set-key="createtimeStr"></span></td>
        </tr>
    [/@a.viewsection]
	
</body>
</html>
