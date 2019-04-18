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
				row[i].typeName = "专题讲座";
			} else if (row[i].type == 1) {
			    row[i].typeName = "企业家培训";
			} else if (row[i].type == 2) {
			    row[i].typeName = "互助合作平台";
			} else if (row[i].type == 5) {
			    row[i].typeName = "引资购商概念";
			} else if (row[i].type == 6) {
			    row[i].typeName = "会员通道";
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
		<table>
			<tr>
                <td>讲座内容数：</td>
                <td><span>${count}</span></td>
                <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td>企业家培训内容数：</td>
                <td><span>${count1}</span></td>
                <td>互助合作平台内容数：</td>
                <td><span>${count2}</span></td>
			<tr>
		</table>
		[@a.search]
		     <tr>
                <td><label for="semail">类型:</label></td>
                <td><select id="stype" name="type" data-options="panelHeight:200">
                    <option class="easyui-combobox" value="">全部</option>
                        <option value="">全部</option>
                        <option value="0">专题讲座</option>
                        <option value="1">企业家培训</option>
                	</select>
                </td>
			</tr> 
		[/@a.search]
		[@a.datagrid]
            <th data-options="field:'name'" width="25%">名称</th>
            <th data-options="field:'typeName'" width="25%">类型</th>
            <th data-options="field:'statusName'" width="25%">状态</th>
            <th data-options="field:'createtimeStr'" width="25%">创建时间</th>
        [/@a.datagrid]
        [@a.toolbar canadd=false canedit=true candel=false]
        [/@a.toolbar]
        
        [@a.tools /]
	</section>
	[@a.addsection]
        <tr>
            <td>名称:</td>
            <td><input type="text" name="name"></input></td>
        </tr>
        <tr>
            <td>内容类型:</td>
             <td><select id="stype" name="type" data-options="panelHeight:200">
                    <option class="easyui-combobox" value="0">专题讲座</option>
                        <option value="0">专题讲座</option>
                        <option value="1">企业家培训</option>
                	</select>
             </td>
        </tr>
        <tr>
            <td>简介:</td>
            <td><input type="text" name="summary"></input></td>
        </tr>
        <tr>
            <td>内容:</td>
            <td>
                [@a.ckeditor name="content"/]
            </td>
        </tr>
	[/@a.addsection]
	[@a.editsection]
	    <input type="hidden" name="uid">
	    <tr>
            <td>名称:</td>
            <td><input type="text" name="name"></input></td>
        </tr>
        <tr>
            <td>内容类型:</td>
            <td><select id="stype" name="type" data-options="panelHeight:200">
                    <option class="easyui-combobox" value="0">专题讲座</option>
                        <option value="0">专题讲座</option>
                        <option value="1">企业家培训</option>
                        <option value="2">互助合作平台</option>
                        <option value="5">引资购商概念</option>
                        <option value="6">会员通道</option>
                	</select>
             </td>        
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
            <td>内容类型:</td>
            <td><span set-key="typeName"></span></td>
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