[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Admin.ftl" as a]
<html>
<head>
<title>网站内容管理</title>
<script type="text/javascript" src="${basejs}/ckeditor/ckeditor.js"></script>
</head>
<body>
	<section>
        [@a.datagrid title="${channelname}" url="${base}${baseAdminPath}/ajax/html/content/list"]
            <th data-options="field:'title',width:150">标题</th>
            <th data-options="field:'typeName',width:200">栏目</th>
        [/@a.datagrid]
        [@a.toolbar canadd=true candel=false]
        [/@a.toolbar]
	</section>
	
	[@a.addsection action="${base}${baseAdminPath}/ajax/html/content/add"]
        <tr>
            <td>标题：</td>
            <td><input class="easyui-validatebox" type="text" name="title" data-options="required:true" /></td>
        </tr>
        <tr>
            <td>板块：</td>
            <td>
                <input class="easyui-validatebox" type="radio" name="type" value="9" data-options="required:true" />关于移动端职业经理人培训内容管理
                <input class="easyui-validatebox" type="radio" name="type" value="10" data-options="required:true" />关于移动端企业家培训内容管理
                <input class="easyui-validatebox" type="radio" name="type" value="11" data-options="required:true" />关于移动端超天才智慧产业内容管理
                <input class="easyui-validatebox" type="radio" name="type" value="14" data-options="required:true" />会员通道
            </td>
        </tr>
        <tr>
            <td>内容：</td>
            <td>
                [@a.ckeditor name="content"/]
            </td>
        </tr>
    [/@a.addsection]
	
    [@a.editsection action="${base}${baseAdminPath}/ajax/html/content/edit"]
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
            	<textarea class="easyui-validatebox" name="content" data-options="required:true" cols="110" rows="80"></textarea>
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
