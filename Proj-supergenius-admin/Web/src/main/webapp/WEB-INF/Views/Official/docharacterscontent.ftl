[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Admin.ftl" as a]
<html>
<head>
<title>网站内容管理</title>
<script type="text/javascript" src="${basejs}/ckeditor/ckeditor.js"></script>
</head>
<body>
	<section>
        [@a.datagrid title="${channelname}" url="${base}${baseAdminPath}/ajax/characters/content/list"]
            <th data-options="field:'title',width:150">标题</th>
            <th data-options="field:'typeName',width:200">栏目</th>
        [/@a.datagrid]
        [@a.toolbar candel=false]
        [/@a.toolbar]
	</section>
	
	[@a.addsection action="${base}${baseAdminPath}/ajax/characters/content/add"]
        <tr>
            <td>标题：</td>
            <td><input class="easyui-validatebox" type="text" name="title" data-options="required:true" /></td>
        </tr>
        <tr>[#--添加版块 固定--]
            <td>板块：</td>
            <td>
                <input class="easyui-validatebox" type="radio" name="type" value="12" data-options="required:true" />移动端关于我们
                <input class="easyui-validatebox" type="radio" name="type" value="13" data-options="required:true" />移动端联系我们
            </td>
        </tr>
        <tr>
            <td>内容：</td>
            <td>
                [@a.ckeditor name="content"/]
            </td>
        </tr>
    [/@a.addsection]
	
    [@a.editsection action="${base}${baseAdminPath}/ajax/characters/content/edit"]
        <tr>
            <td><input type="hidden" name="uid" /></td>
            <td><input type="hidden" name="adminuid" /></td>
            <td><input type="hidden" name="type" /></td>
        </tr>
        <tr>
            <td>标题：</td>
            <td><input class="easyui-validatebox" type="text" name="title" data-options="required:true" /></td>
        </tr>
        <tr>
            <td>栏目：</td>
            <td><span set-key="typeName"></span></td>
        </tr>
        <tr>
            <td>内容：</td>
            <td>
                [@a.ckeditor name="content" /]
            </td>
        </tr>
    [/@a.editsection]
    
    [@a.viewsection]
    	<tr>
            <td>标题：</td>
            <td><span set-key="title"></span></td>
        </tr>
        <tr>
            <td>栏目：</td>
            <td><span set-key="typeName"></span></td>
        </tr>
        <tr>
            <td>内容：</td>
            <td><span set-key="content"></span></td>
        </tr>
    [/@a.viewsection]
</body>
</html>
