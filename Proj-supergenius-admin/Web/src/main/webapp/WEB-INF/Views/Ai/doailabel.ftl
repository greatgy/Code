[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Admin.ftl" as a]
<html>
<head>
<title>标签信息</title>
<script type="text/javascript">
<!--
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
        $("#btnrecommend").linkbutton("disable");
    }
}

function myButtonHandler(obj, url) {
		var objid = $(obj).attr("id");
		if (objid == "disable") {
		    confirmAjax('冻结该选项', '确定要冻结该标签吗？', '${base}${baseAdminPath}/${site}/ajax/${channel}/status/0', "post");
		}
		if (objid == "enable") {
		    confirmAjax('解冻该选项', '确认要解冻该标签吗？', '${base}${baseAdminPath}/${site}/ajax/${channel}/status/1', "post");
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
	$.post(url, {uid : getSelected(dg).uid}, function(result) {
		if (result.success) {
			getDataGrid(dg).datagrid("reload"); // 重新加载数据表格
			resultHandler(result);
		} else {
			resultHandler(result);
		}
	});
}
//-->
</script> 
</head>
<body>
	<section id="first">
		[@a.search]
	        <tr>
                 <td><label for="sname">标签名称：</label><input id="sname" type="text" name="content" class="search" style="width:300px;"/></td>
        	</tr>
        	<tr>
        		<td>状态：
                    <input type="radio" id="statusall" name="status" value="" checked/><label for="statusall">全部</label>
                    <input type="radio" id="statusone" name="status" value="1" /><label for="statusall">正常</label>
                    <input type="radio" id="statustwo" name="status" value="0" /><label for="statusall">冻结</label>
                </td>
        	</tr>
	        <tr>
	            <td>起始操作时间: <input name="createtimestart" type="text" editable="false" class="easyui-datebox" /></td>
                <td>结束操作时间: <input name="createtimeend" type="text" editable="false" class="easyui-datebox" /></td>
	        </tr>
		[/@a.search]
		
		[@a.datagrid]
            <th data-options="field:'content',width:100">名称</th>
            <th data-options="field:'nowclick',width:100">今日点击量</th>
            <th data-options="field:'totalclick',width:100">总点击量</th>
            <th data-options="field:'statusName',width:100">状态</th>
            <th data-options="field:'createtimeStr',width:200">创建时间</th>
            <th data-options="field:'adminname',width:200">操作人</th>
	    [/@a.datagrid]
	    
	    [@a.toolbar candel=false canedit=false]
	    <a id="disable" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="myButtonHandler(this, null)">冻结</a>
        <a id="enable" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="myButtonHandler(this, null)">解冻</a>
	    [/@a.toolbar]
	    [@a.tools /]
	    
	</section>
	
 	[@a.addsection]
        <tr>
            <td>标签：</td>
            <td><input class="easyui-validatebox" type="text" name="content" placeholder="添加多个请以空格隔开" data-options="required:true" /></td>
        </tr>
    [/@a.addsection]
    
 	[@a.viewsection]
		<tr>
            <td>标签:</td>
            <td><span set-key="content"></span></td>
        </tr>
		<tr>
            <td>今日点击量:</td>
            <td><span set-key="nowclick"></span></td>
        </tr>      
        <tr>
            <td>总点击量:</td>
            <td><span set-key="totalclick"></span></td>
        </tr>
        <tr>
            <td>状态:</td>
            <td><span set-key="statusName"></span></td>
        </tr>
        <tr>
            <td>操作时间:</td>
            <td><span set-key="createtimeStr"></span></td>
        </tr>
        <tr>
            <td>操作人:</td>
            <td><span set-key="adminname"></span></td>
        </tr>
	[/@a.viewsection]
</body>
</html>