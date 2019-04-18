[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Admin.ftl" as a]
<html>
<head>
<title>规则管理</title>
<script type="text/javascript" src="${basejs}/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
//调整编辑页面显示选项内容
$(function() {
	 $("#btnedit").click(function() {
    	//调整编辑页面显示选项内容
    	var row = getSelected(dg);
	    if (row) {
	    console.log("123");
	    console.log(row);
	    } else {
	        err("请选择一项！");
	    }
    });
});
</script>
</head>
<body>
	<section>
		[@a.search]
	        <tr>
	        	<td colspan="10">
		        	名称: <input id="name" type="text" name="name" />
	        	</td>
	        </tr>
	        <tr>
	            <td>起始操作时间: <input name="createtimestart" type="text" editable="false" class="easyui-datebox" /></td>
                <td>结束操作时间: <input name="createtimeend" type="text" editable="false" class="easyui-datebox" /></td>
	        </tr>
		[/@a.search]
		
		[@a.datagrid]
            <th data-options="field:'name',width:50">类型</th>
            <th data-options="field:'content',width:250,align:'center'">内容</th>
            <th data-options="field:'updatetimeStr',width:50">操作时间</th>
            <th data-options="field:'adminname',width:30">操作人</th>
	    [/@a.datagrid]
	    
	    [@a.toolbar canadd=false candel=false]
	    [/@a.toolbar]
	    [@a.tools /]
		
	</section>
	    [@a.editsection]
	    	<input type="hidden" name="uid" />
		    <tr>
	            <td>类型：</td>
	            <td><input class="easyui-validatebox" name="name" data-options="required:true" /></td>
	        </tr>
	        <tr>
	            <td>内容：</td>            
	            <td>
	            <textarea class="easyui-validatebox" name="content" data-options="required:true"  rows="5" cols="60"></textarea>
	            </td>
	        </tr>
		[/@a.editsection]
</body>
</html>
