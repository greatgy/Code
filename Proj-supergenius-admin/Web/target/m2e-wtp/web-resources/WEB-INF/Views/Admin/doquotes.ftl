[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Admin.ftl" as a]
<html>
<head>
<title>名人名言管理</title>
<script type="text/javascript">
<!--
    function getSelectedIDs(list, dg) {
        if (list) {
        } else {
            list = getSelections(dg)
        }
        var ids = new Array();
        for ( var i in list) {
            var item = list[i];
            ids.push(item.site);
        }
        return ids;
    }
-->
</script>
</head>
<body>
    <section>
    	[@a.search]
            <tr>
		        <td>类型：</td>
		        <td>
		        	<select name="type" class="easyui-combobox" data-options="required:true" >
		        		<option value="">全部</option>
		                [#assign type = types?keys]
		                    [#list type as item]
		                        <option value="${item}">${types[item]}</option>
		                    [/#list]
		            </select>
		        </td>
       		 </tr>
        [/@a.search]
        [@a.datagrid]
            <th data-options="field:'author',width:80">作者</th>
            <th data-options="field:'typeName',width:80">类型</th>
            <th data-options="field:'content',width:400">内容</th>
            <th data-options="field:'updatetimeStr',width:100">修改时间</th>
        [/@a.datagrid]
        [@a.toolbar/]
        [@a.tools /]
    </section>

    [@a.addsection]
        <tr>
            <td>作者：</td>
            <td><input class="easyui-validatebox" type="text" name="author" data-options="required:true" /></td>
        </tr>
        <tr>
            <td>内容：</td>
            <td><input class="easyui-validatebox" type="text" name="content" data-options="required:true" /></td>
        </tr>
        <tr>
            <td>类型：</td>
            <td>
            	<select name="type" class="easyui-combobox" data-options="required:true" >
                    [#assign type = types?keys]
                        [#list type as item]
                            <option value="${item}">${types[item]}</option>
                        [/#list]
                </select>
            </td>
        </tr>
    [/@a.addsection]

    [@a.editsection]
        <tr>
            <td><input type="hidden" name="site" /></td>
        </tr>
        <tr>
            <td>作者：</td>
            <td><input class="easyui-validatebox" type="text" name="author" data-options="required:true" /></td>
        </tr>
        <tr>
            <td>内容：</td>
            <td><input class="easyui-validatebox" type="text" name="content" data-options="required:true" /></td>
        </tr>
        <tr>
            <td>类型：</td>
            <td>
            	<select name="type">
                    [#assign type = types?keys]
                        [#list type as item]
                            <option value="${item}" >${types[item]}</option>
                        [/#list]
                </select>
            </td>
        </tr>
    [/@a.editsection]

    [@a.viewsection]
        <tr>
            <td>作者：</td>
            <td><span set-key="author"></span></td>
        </tr>
        <tr>
            <td>内容：</td>
            <td><span set-key="content"></span></td>
        </tr>
        <tr>
            <td>类型：</td>
            <td><span set-key="typeName"></span></td>
        </tr>
        <tr>
            <td>添加时间：</td>
            <td><span set-key="createtimeStr"></span></td>
        </tr>
        <tr>
            <td>修改时间：</td>
            <td><span set-key="updatetimeStr"></span></td>
        </tr>
    [/@a.viewsection]

</body>
</html>