[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Admin.ftl" as a]
<html>
<head>
<title>图片管理</title>
<script type="text/javascript" src="${basejs}/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
$(function() {
	$("#btnedit").click(function() {
		$(window.frames["frameImg0"].document).find("#formImg0").css("display","block");
	});
	});
function datagrid_loadFilter(data) {
	initDGData(data.rows, data.time);
	return data;
}

function initDGData(row, time) {
	for(var i in row) {
		if(row[i].status == 0 ){
			row[i].statusName = "冻结";
		}else if(row[i].status == 1 ){
			row[i].statusName = "正常";
		}
	}
}

function mytoolbarStateHandler(event, j) {
    var list = getSelections(j.dg); 
    if (list.length > 0) {
        if (list[0].status == 0){
            $("#enable").linkbutton("enable");
        	$("#disable").linkbutton("disable");
        } else {
            $("#enable").linkbutton("disable");
        	$("#disable").linkbutton("enable");
        }
    } else {
         $("#enable").linkbutton("disable");
        $("#disable").linkbutton("disable");
    }
}
function myButtonHandler(obj, url) {
		var objid = $(obj).attr("id");
		if (objid == "disable") {
		    confirmPostAjax('冻结该选项', '确定要冻结该banner吗？', '${base}${baseAdminPath}/${site}/ajax/lifebanner/status/0', "post");
		}
		if (objid == "enable") {
		    confirmPostAjax('解冻该选项', '确认要解冻该banner吗？', '${base}${baseAdminPath}/${site}/ajax/lifebanner/status/1', "post");
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
	console.log(getSelected(dg))
	$.post(url, {uid : getSelected(dg).uid}, function(result) {
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
		<table>
			<tr>
			   <td>banner总数：</td>
			   <td><span>4</span></td>
			<tr>        
		</table>
        [@a.datagrid]
            <th data-options="field:'title',width:62">标题</th>
            <th data-options="field:'statusName',width:10">状态</th>
        [/@a.datagrid]
        [@a.toolbar canadd=false candel=false]
	        <a id="disable" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="myButtonHandler(this, null)">冻结</a>
        	<a id="enable" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="myButtonHandler(this, null)">解冻</a>
        [/@a.toolbar]  
        [@a.tools /]
    </section>
		
		[@a.viewsection]
        <tr><td>标题</td><td><span set-key="title"></span></td></tr>
        <tr><td>地址</td><td><span set-key="url"></span></td></tr>
        <tr>
	        <td>图片:</td>
	        <td><img src="" set-format="${webimg}{0}" set-key="content" style="width: 600px; height: 400px;"/></td>
        </tr>
        [/@a.viewsection]
   	    [@a.editsection canuploadfile=true]
         <tr>
            <td>标题：</td><input type="hidden" name="oid" /><input type="hidden" name="cid" />
            <td><input class="easyui-validatebox" name="title" data-options="required:true" id="edit_title"/></td>
        </tr>
         <tr>
            <td>地址：</td>
            <td><input class="easyui-validatebox" name="url" data-options="required:true" id="edit_url"/></td>
        </tr>
        <tr>
            <td>图片:</td>
            <td><img id="avatarbig" style="width:150px; height:150px" src="" set-format="${webimg}{0}" set-key="content"/>
            	[@a.imgupload multiple=false imgpath="${photopath}" name="imgdata"/]
            </td>
        </tr>
    	[/@a.editsection]
</body>
</html>