[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Admin.ftl" as a]
<html>
<head>
<title>模块管理</title>
<script type="text/javascript" src="${basejs}/ckeditor/ckeditor.js"></script>
<style>
a.forhead {
    display: inline-block;
    float: right;
    margin-right: 5px;
}
a.nexthead {
    display: inline-block;
    float: right;
    margin-right: 5px;
}
</style>
<script type="text/javascript">

function initDGData(row) {
	for(var i in row) {
		if (row[i].pcid == 0) {
			row[i].gradeName = "一级模块";
		} else {
			row[i].gradeName = "二级模块";
		}

		if (row[i].status == 1) {
			row[i].statusName = "正常";
		} else {
			row[i].statusName = "冻结";
		}
		
	}
}

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
		    confirmAjax('冻结该模块', '确定要冻结该模块吗？', '${base}${baseAdminPath}/${site}/ajax/${channel}/status/0', "post");
		}
		if (objid == "enable") {
		    confirmAjax('解冻该选项', '确认要解冻该模块吗？', '${base}${baseAdminPath}/${site}/ajax/${channel}/status/1', "post");
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
	$.post(url, {cid : getSelected(dg).cid}, function(result) {
		if (result.success) {
			getDataGrid(dg).datagrid("reload"); // 重新加载数据表格
			resultHandler(result);
		} else {
			resultHandler(result);
		}
	});
}


function addSubmitHandler(obj, dg) {
    $(obj).parents("form").form('submit', {
        success : function(str) {
            var result = $.parseJSON(str);
            if (result.success || result.addSuccess || result.editSuccess) {
                window.location.href = "${base}${baseAdminPath}/life/lifecatalogue";
            } else {
                submitErrHandler(result);
            }
        }
    });
}

</script>
</head>
<body>
    <section>
		<table>
			<tr>
			   <td>模块总数：</td>
			   <td><span>${count}</span></td>
			<tr>        
		</table>
		[@a.search]
	        <tr>
	        	<td colspan="10">
		        	<label for="name">模块名: </label><input id="name" type="text" name="name" class="search"/>&nbsp;&nbsp;&nbsp;
	        	</td>
	        </tr>
	        <tr>
        		<td>状态：
                    <input type="radio" id="statusall" name="status" value="" checked/><label for="statusall">全部</label>
                    <input type="radio" id="statusone" name="status" value="1" /><label for="statusone">正常</label>
                    <input type="radio" id="statustwo" name="status" value="0" /><label for="statustwo">冻结</label>
                </td>
        	</tr>
		[/@a.search]
        [@a.datagrid]
            <th data-options="field:'name',width:40">名称</th>
            <th data-options="field:'gradeName',width:40">级别</th>
            <th data-options="field:'statusName',width:40">状态</th>
            <th data-options="field:'updatetimeStr',width:20">操作时间</th>
            <th data-options="field:'adminname',width:20">操作人</th>
        [/@a.datagrid]
        [@a.toolbar canadd=false candel=false]
        <a id="disable" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="myButtonHandler(this, null)">冻结</a>
        <a id="enable" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="myButtonHandler(this, null)">解冻</a>
        [/@a.toolbar]  
        [@a.tools /]
    </section>
		
		[@a.viewsection]
        <tr><td>名称</td><td><span set-key="name"></span></td></tr>
        <tr><td>级别</td><td><span set-key="gradeName"></span></td></tr>
        <tr><td>状态</td><td><span set-key="statusName"></span></td></tr>
        <tr><td>操作时间</td><td><span set-key="updatetimeStr"></span></td></tr>
        <tr><td>操作人</td><td><span set-key="adminname"></span></td></tr>
        <tr><td></td><td><span id="don" style="width: 600px;height:400px;"></span></td></tr>
        [/@a.viewsection]
   	    [@a.editsection canuploadfile=true]
         <tr>
            <td>现名称：</td><input type="hidden" name="cid" />
            <td><input class="easyui-validatebox" name="name" data-options="required:true" disabled = "disabled"/></td>
        </tr>
        <tr id="defaultsection">
            <td>修改为：</td>
            <td><input class="easyui-validatebox" name="newname" data-options="required:true" /></td>
        </tr>
    	[/@a.editsection]
    	
</body>
</html>