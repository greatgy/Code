[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Admin.ftl" as a]
<html>
<head>
<title>科目管理</title>
<script type="text/javascript" src="${basejs}/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
	
	function mytoolbarStateHandler(event, j) {
        var list = getSelections(j.dg); 
        if (list.length > 0) {
	        if (list[0].status == 1){
	            $("#enable").linkbutton("disable");
	        	$("#disable").linkbutton("enable");
	        } else {
	            $("#enable").linkbutton("enable");
	        	$("#disable").linkbutton("disable");
	        }
        } else {
	        $("#enable").linkbutton("disable");
	        $("#disable").linkbutton("disable");
        }
    }
    
    function myButtonHandler(obj, url) {
		var objid = $(obj).attr("id");
		if (objid == "disable") {
		    confirmAjax('冻结该选项', '确定要冻结吗？', '${base}${baseAdminPath}/life/ajax/lifesubject/status');
		}
		if (objid == "enable") {
		    confirmAjax('解冻该选项', '确认要解冻吗？', '${base}${baseAdminPath}/life/ajax/lifesubject/status');
		}
	}
	
	function confirmAjax(title, question, url) {
		$.messager.confirm(title, question, function(flag) {
			if (flag) {
				ajaxPostSubmit(url);
			}
		});
	}
	
	function ajaxPostSubmit(url) {
		$.post(url, {sid : getSelected(dg).sid}, function(result) {
			if (result.success) {
				getDataGrid(dg).datagrid("reload"); // 重新加载数据表格
				resultHandler(result);
			} else {
				resultHandler(result);
			}
		});
	}
	
</script>
</head>
<body>
	<section>
		[@a.search]
	        <tr>
	        	<td colspan="10">
		        	<label for="name">名称: </label><input id="name" type="text" name="name" class="search"/>&nbsp;&nbsp;&nbsp;
	        	</td>
	        </tr>
	        <tr>
                <td>状态：
                    <input type="radio" id="statusall" name="status" value="" checked/><label for="statusall">全部</label>
                    <input type="radio" id="statusone" name="status" value="1" /><label for="statusone">正常</label>
                    <input type="radio" id="statustwo" name="status" value="0" /><label for="statustwo">冻结</label>
                </td>
        	</tr>
        	<tr>
                <td>年级：
                	<input type="radio" id="moduleall" name="grade" value="" checked/><label for="moduleall">全部</label>
                    <input type="radio" id="grade_6" name="grade" value="1" /><label for="grade_6">六年级</label>
                    <input type="radio" id="grade_7" name="grade" value="2" /><label for="grade_7">七年级</label>
                    <input type="radio" id="grade_8" name="grade" value="4" /><label for="grade_8">八年级</label>
                    <input type="radio" id="grade_9" name="grade" value="8" /><label for="grade_9">九年级</label>
                </td>
        	</tr>
		[/@a.search]
		
		[@a.datagrid]
            <th data-options="field:'name',width:100">名称</th>
            <th data-options="field:'gradeName',width:100">所属年级</th>
            <th data-options="field:'statusName',width:100">状态</th>
            <th data-options="field:'createtimeStr',width:150">创建时间</th>
            <th data-options="field:'adminname',width:150">操作人</th>
	    [/@a.datagrid]
	    
	    [@a.toolbar candel=false]
		    <a id="disable" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="myButtonHandler(this, null)">冻结</a>
		    <a id="enable" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="myButtonHandler(this, null)">解冻</a>
        [/@a.toolbar]
	    [@a.tools /]
	    
	</section>
   [@a.addsection]
        <tr>
            <td>名称:</td>
            <td><input type="text" class="easyui-validatebox" name="name" data-options="min:0,required:true" /></td>
        </tr>
   		<tr>
            <td>选择年级:</td>
            <td>
            	<input type="checkbox" id="addgrade_6" name="grade" value="1" /><label for="addgrade_6">六年级</label>
                <input type="checkbox" id="addgrade_7" name="grade" value="2" /><label for="addgrade_7">七年级</label>
                <input type="checkbox" id="addgrade_8" name="grade" value="4" /><label for="addgrade_8">八年级</label>
                <input type="checkbox" id="addgrade_9" name="grade" value="8" /><label for="addgrade_9">九年级</label>
            </td>
        </tr>
    [/@a.addsection]
    [@a.editsection ]
        <input type="hidden" name="sid">
        <tr>
            <td>名称:</td>
            <td><input type="text" class="easyui-validatebox" name="name" data-options="min:0,required:true" /></td>
        </tr>
        <tr>
            <td>年级:</td>
            <td>
            	<input type="checkbox" set-key="isSix" id="editgrade_6" name="grade" value="1" /><label for="editgrade_6">六年级</label>
                <input type="checkbox" set-key="isSeven" name="grade" value="2" /><label for="editgrade_7">七年级</label>
                <input type="checkbox" set-key="isEight" name="grade" value="4" /><label for="editgrade_8">八年级</label>
                <input type="checkbox" set-key="isNine" name="grade" value="8" /><label for="editgrade_9">九年级</label>
            </td>
        </tr>
    [/@a.editsection]
 	[@a.viewsection]
		<tr>
            <td>名称:</td>
            <td><span set-key="name"></span></td>
        </tr>
        <tr>
            <td>年级:</td>
            <td><span set-key="gradeName"></span></td>
        </tr>
        <tr>
            <td>操作时间:</td>
            <td><span set-key="updatetimeStr"></span></td>
        </tr>
        <tr>
            <td>操作人:</td>
            <td><span set-key="adminname"></span></td>
        </tr>
	[/@a.viewsection]
	
</body>
</html>
