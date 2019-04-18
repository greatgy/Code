[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Admin.ftl" as a]
<html>
<head>
<title>天才人生-回答</title>
<script type="text/javascript" src="${basejs}/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
function datagrid_loadFilter(data) {
	initDGData(data.rows);
	return data;
}
function initDGData(row) {
	for(var i in row) {
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
    }
}
function myButtonHandler(obj, url) {
	var objid = $(obj).attr("id");
	var flag = false;
	if (objid == "disable") {
	    confirmPostAjax('冻结该选项', '确定要冻结该文章吗？', '${base}${baseAdminPath}/${site}/ajax/${channel}/status/0', "post");
	}
	if (objid == "enable") {
	    confirmPostAjax('解冻该选项', '确认要解冻该文章吗？', '${base}${baseAdminPath}/${site}/ajax/${channel}/status/1', "post");
	}
}
function confirmPostAjax(title, question, url) {
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
function confirmAjax(title, question, url) {
	$.messager.confirm(title, question, function(flag) {
		if (flag) {
			ajaxGetSubmit(url);
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
		        	<label for="name">关键字: </label><input id="name" type="text" name="name" class="search"/>&nbsp;&nbsp;&nbsp;
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
                <td>板块：
                	<input type="radio" id="moduleall" name="cid" value="" checked/><label for="moduleall">全部</label>
                    <input type="radio" id="problem" name="cid" value="7" /><label for="problem">人生问答</label>
                    <input type="radio" id="recommend" name="cid" value="32"/><label for="recommend">课程推荐</label>
                    <input type="radio" id="data" name="cid" value="43" /><label for="data">资料交流</label>
                    <input type="radio" id="major" name="cid" value="44" /><label for="major">专业匹配</label>
                    <input type="radio" id="excellent" name="cid" value="45" /><label for="excellent">大学优选</label>
                    <input type="radio" id="design" name="cid" value="55" /><label for="design">人生设计</label>
                </td>
            </tr>
		[/@a.search]
        [@a.datagrid]
            <th data-options="field:'title',width:100">所属问题</th>
            <th data-options="field:'fromusername',width:15">发布人</th>
            <th data-options="field:'cataloguename',width:30">所属板块</th>
            <th data-options="field:'statusName',width:15">状态</th>
            <th data-options="field:'updatetimeStr',width:40">操作时间</th>
            <th data-options="field:'adminname',width:20">操作人</th>
        [/@a.datagrid]
        [@a.toolbar canadd=false canedit=false]
	        <a id="disable" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="myButtonHandler(this, null)">冻结</a>
        	<a id="enable" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="myButtonHandler(this, null)">解冻</a>
        [/@a.toolbar]
        [@a.tools /]
   </section>
        [@a.viewsection]
	        <tr><td>所属问题</td><td><span set-key="title"></span></td></tr>
	        <tr><td>发表人</td><td><span set-key="fromusername"></span></td></tr>
	        <tr><td>所属板块</td><td><span set-key="cataloguename"></span></td></tr>
	        <tr><td>状态</td><td><span set-key="statusName"></span></td></tr>
	        <tr><td>操作时间</td><td><span set-key="updatetimeStr"></span></td></tr>
	        <tr><td>操作人</td><td><span set-key="adminname"></span></td></tr>
	        <tr><td></td><td><span id="don" style="width: 600px;height:400px;"></span></td></tr>
        [/@a.viewsection]
</body>
</html>