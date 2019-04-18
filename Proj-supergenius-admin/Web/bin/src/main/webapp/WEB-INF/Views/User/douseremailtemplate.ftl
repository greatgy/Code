[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Admin.ftl" as a]
<html>
<head>
<title>邮件模板管理</title>
<script type="text/javascript" src="${basejs}/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
<!-->
//-->
</script>
</head>
<body>
	<section>
        [@a.datagrid]
            <th data-options="field:'title',width:500">标题</th>
            <th data-options="field:'typeName',width:200">分类</th>
        [/@a.datagrid]   
        [@a.toolbar]
        [/@a.toolbar]
        [@a.tools /]
	</section>
	[@a.addsection]
        <tr>
            <td>标题：</td>
            <td><input class="easyui-validatebox" type="text" name="title" data-options="required:true" /></td>
        </tr>
        <tr>
            <td>类别：</td>
            <td>
                [#list map?keys as mkey]
                    <input type="radio" id="rdtype_${mkey_index}" name="type" value="${mkey}"/> <label for="rdtype_${mkey_index}">${map[mkey]}</label>&nbsp;
                [/#list]
            </td>
        </tr>
        <tr>
            <td>内容：</td>
            <td>
                [@a.ckeditor name="content"/]
            </td>
        </tr>
    [/@a.addsection]

    [@a.editsection]
        <tr>
            <td><input type="hidden" name="uid" /></td>
        </tr>
        <tr>
            <td>标题：</td>
            <td><input class="easyui-validatebox" type="text" name="title" data-options="required:true" /></td>
        </tr>
        <tr>
            <td>类别：</td>
            <td>
                [#list map?keys as mkey]
                   <input type="radio" id="rdtype_${mkey_index}" set-key="type" name="type" value="${mkey}" /> <label for="rdtype_${mkey_index}">${map[mkey]}</label>&nbsp;
                [/#list]
            </td>
        </tr>
        <tr>
            <td>内容：</td>
            <td>
                [@a.ckeditor name="content"/]
            </td>
        </tr>
    [/@a.editsection]
    
    [@a.viewsection]
        <tr>
            <td>标题：</td>
            <td><span set-key="title"></span></td>
        </tr>
        <tr>
            <td>板块：</td>
            <td><span set-key="typeName"></span></td>
        </tr>
        <tr>
            <td>内容：</td>
            <td><span set-key="content"></span></td>
        </tr>
    [/@a.viewsection]
    
</body>
</html>
