[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Admin.ftl" as a]
<html>
<head>
<title>内容管理</title>
<script type="text/javascript" src="${basejs}/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
	 function initDGData(row) {
		for (var i in row) {
			if (row[i].type == 0) {
				row[i].typename = "首页";
			} else if (row[i].type == 1) {
			    row[i].typename = "培训细则";
			}
			if (row[i].status == 0) {
				row[i].statusName = "无效";
			} else if (row[i].status == 1) {
			    row[i].statusName = "有效";
			}
		}
	}
</script>
</head>
<body>
	<section>
		[@a.search]
		     <tr>
                <td><label for="semail">类型:</label></td>
                <td><select id="stype" name="type" data-options="panelHeight:200">
                    <option class="easyui-combobox" value="">全部</option>
                        <option value="">全部</option>
                        <option value="0">首页</option>
                        <option value="1">培训细则</option>
                        <option value="2">专业简介</option>
                	</select>
                </td>
			</tr> 
		[/@a.search]
		[@a.datagrid]
            <th data-options="field:'typename'" width="33%">类型</th>
            <th data-options="field:'statusName'" width="33%">状态</th>
            <th data-options="field:'createtimeStr'" width="33%">创建时间</th>
        [/@a.datagrid]
        [@a.toolbar canadd=false candel=false]
        [/@a.toolbar]
        
        [@a.tools /]
	</section>
	[@a.editsection]
        <tr>
            <td>类型:</td><input type="hidden" name="uid"/></td>
            <td><span type="text" set-key="typename"></span></td>
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
            <td>内容类型:</td>
            <td><span set-key="typename"></span></td>
        </tr>
        <tr>
            <td>内容:</td>
            <td><span set-key="content"></span></td>
        </tr>
        <tr>
            <td>创建时间:</td>
            <td><span set-key="createtimeStr"></span></td>
        </tr>
    [/@a.viewsection]
</body>
</html>