[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Admin.ftl" as a]
<html>
<head>
<title>论坛管理</title>
<script type="text/javascript" src="${basejs}/ckeditor/ckeditor.js"></script>
<script type="text/javascript">

function initDGData(row) {
	for(var i in row) {
		if (row[i].status == 1) {
			row[i].statusName = "正常";
		} else if(row[i].status == 0) {
			row[i].statusName = "冻结";
		}
		
		if (row[i].isrecommend == 1) {
			row[i].isrecommendName = "是";
		} else {
			row[i].isrecommendName = "否";
		}
		
		if (row[i].istop == 1) {
			row[i].istopName = "是";
		} else {
			row[i].istopName = "否";
		}
		
	}
}

/*重写edit的表单提交方法*/ 
function submitHandlerForAdd(obj, dg) {
	$(obj).parents("form").form('submit', {
	    onSubmit: function(param) {
       if ($("#add_title").val().length < 1) {
        err("您填写标题！");
        $("#add_title").focus();
        return false;
       };       
        },   
		success : function(str) {
			var result = $.parseJSON(str);
			if (result.success || result.addSuccess || result.editSuccess) {
				getDataGrid(dg).datagrid("reload"); // 重新加载数据表格
				clearForm($(obj).parents("form"));
				submitSuccessHandler(result);
			} else {
				submitErrHandler(result);
			}
		}
	});
}

/*重写edit的表单提交方法*/ 
function submitHandlerForEdit(obj, dg) {
	$(obj).parents("form").form('submit', {
	    onSubmit: function(param) {
       if ($("#edit_title").val().length < 1) {
        err("请您填写标题！");
        $("#edit_title").focus();
        return false;
       };       
        },   
		success : function(str) {
			var result = $.parseJSON(str);
			if (result.success || result.addSuccess || result.editSuccess) {
				getDataGrid(dg).datagrid("reload"); // 重新加载数据表格
				clearForm($(obj).parents("form"));
				submitSuccessHandler(result);
			} else {
				submitErrHandler(result);
			}
		}
	});
}

function mytoolbarStateHandler(event, j) {
    var list = getSelections(j.dg); 
    if (list.length > 0) {
        if (list[0].isrecommend == 1){
            $("#stick").linkbutton("disable");
        	$("#unstick").linkbutton("enable");
        } else {
            $("#stick").linkbutton("enable");
        	$("#unstick").linkbutton("disable");
        }
        if (list[0].status == 1){
            $("#enable").linkbutton("disable");
        	$("#disable").linkbutton("enable");
        } else {
            $("#enable").linkbutton("enable");
        	$("#disable").linkbutton("disable");
        }
        if (list[0].istop == 1){
            $("#toptrue").linkbutton("disable");
        	$("#topfalse").linkbutton("enable");
        } else {
            $("#toptrue").linkbutton("enable");
        	$("#topfalse").linkbutton("disable");
        }
    } else {
        $("#stick").linkbutton("disable");
        $("#unstick").linkbutton("disable");
         $("#enable").linkbutton("disable");
        $("#disable").linkbutton("disable");
        $("#btnrecommend").linkbutton("disable");
        $("#toptrue").linkbutton("disable");
        $("#topfalse").linkbutton("disable");
    }
}

function myButtonHandler(obj, url) {
		var objid = $(obj).attr("id");
		var flag = false;
		if (objid == "stick") {
			confirmAjax('推荐该条帖子', '确定要推荐该条帖子到首页么？', '${base}${baseAdminPath}/${site}/ajax/${channel}/enable', "get");
		}
		if (objid == "unstick") {
		    confirmAjax('取消推荐', '确定要取消推荐么？', '${base}${baseAdminPath}/${site}/ajax/${channel}/disable', "get");
		}
		if (objid == "toptrue") {
		var row = getSelected(dg);
			$.get('${base}${baseAdminPath}/${site}/ajax/${channel}/topcount/'+row.cid, function(result) {
				if (result.count >= 3) {
					err("置顶数量超过3个！");
			        return false;
				}
			confirmAjax('置顶该条帖子', '确定要置顶该条帖子么？', '${base}${baseAdminPath}/${site}/ajax/${channel}/toptrue', "get");
			});
		}
		if (objid == "topfalse") {
		    confirmAjax('取消置顶', '确定要取消置顶么？', '${base}${baseAdminPath}/${site}/ajax/${channel}/topfalse', "get");
		}
		if (objid == "disable") {
		    confirmPostAjax('冻结该选项', '确定要冻结该文章吗？', '${base}${baseAdminPath}/${site}/ajax/${channel}/status/0', "post");
		}
		if (objid == "enable") {
		    confirmPostAjax('解冻该选项', '确认要解冻该文章吗？', '${base}${baseAdminPath}/${site}/ajax/${channel}/status/1', "post");
		}
}

function confirmAjax(title, question, url) {
	$.messager.confirm(title, question, function(flag) {
		if (flag) {
			ajaxGetSubmit(url);
		}
	});
}

function ajaxGetSubmit(url) {
	$.get(url, {ids : getSelected(dg).uid}, function(result) {
		if (result.success) {
			getDataGrid(dg).datagrid("reload"); // 重新加载数据表格
			resultHandler(result);
		} else {
			resultHandler(result);
		}
	});
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
</script>
</head>
<body>
    <section>
		<table>
			<tr>
			   <td>帖子总数：</td>
			   <td><span>${count}</span></td>
			<tr>        
		</table>
		[@a.search]
	        <tr>
	        	<td colspan="10">
		        	<label for="name">标题: </label><input id="name" type="text" name="title" class="search"/>&nbsp;&nbsp;&nbsp;
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
                <td>是否推荐：
                	<input type="radio" id="recommendall" name="isrecommend" value="" checked="checked"/><label for="recommendall">全部</label>
                    <input type="radio" id="recommendone" name="isrecommend" value="1" /><label for="recommendone">是</label>
                    <input type="radio" id="recommendtwo" name="isrecommend" value="0" /><label for="recommendtwo">否</label>
                </td>
        	</tr>
	        <tr>
                <td>板块：
                	<input type="radio" id="cidall" name="cid" value="" checked="checked"/><label for="cidone">全部</label>
                	<input type="radio" id="cidthree" name="cid" value="22"/><label for="cidthree">引资购商论坛</label>
                    <input type="radio" id="cidone" name="cid" value="32" /><label for="cidone">中国制造论坛</label>
                    <input type="radio" id="cidtwo" name="cid" value="34" /><label for="cidtwo">招商引资论坛</label>
                </td>
        	</tr>
		[/@a.search]
        [@a.datagrid]
            <th data-options="field:'title',width:62">标题</th>
            <th data-options="field:'statusName',width:10">状态</th>
            <th data-options="field:'author',width:13,align:'center'">板块</th>
            <th data-options="field:'cataloguename',width:13,align:'center'">板块</th>
            <th data-options="field:'isrecommendName',width:13,align:'center'">是否推荐</th>
            <th data-options="field:'istopName',width:13,align:'center'">是否置顶</th>
            <th data-options="field:'updatetimeStr',width:20">操作时间</th>
            <th data-options="field:'adminname',width:18">操作人</th>
        [/@a.datagrid]
        [@a.toolbar canadd=false]
        <a id="stick" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="myButtonHandler(this, null)">推荐</a>
        <a id="unstick" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="myButtonHandler(this, null)">取消推荐</a>
        <a id="toptrue" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="myButtonHandler(this, null)">置顶</a>
        <a id="topfalse" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="myButtonHandler(this, null)">取消置顶</a>
         <a id="disable" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="myButtonHandler(this, null)">冻结</a>
        <a id="enable" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="myButtonHandler(this, null)">解冻</a>
        [/@a.toolbar]  
        [@a.tools /]
    </section>
		
		[@a.viewsection]
        <tr><td>标题</td><td><span set-key="title"></span></td></tr>
        <tr><td>描述</td><td><span set-key="summary"></span></td></tr>
        <tr><td>关键字</td><td><span set-key="keywords"></span></td></tr>
        <tr><td>是否推荐</td><td><span set-key="isrecommendName"></span></td></tr>
        <tr><td>是否置顶</td><td><span set-key="istopName"></span></td></tr>
        <tr><td>正文</td><td><span set-key="content"></span></td></tr>
        [/@a.viewsection]
   	    [@a.editsection canuploadfile=true onclicksubmit="submitHandlerForEdit(this)"]
         <tr>
            <td>标题：</td><input type="hidden" name="uid" />
            <td><input class="easyui-validatebox" name="title" data-options="required:true" id="edit_title"/></td>
        </tr>
         <tr>
            <td>描述：</td>
            <td><input class="easyui-validatebox" name="summary"/></td>
        </tr>
         <tr>
            <td>关键字：</td>
            <td><input class="easyui-validatebox" name="keywords" /></td>
        </tr>
        <tr>
            <td>正文:</td>
            <td>[@a.ckeditor name="content" /]</td>
        </tr>
    	[/@a.editsection]
</body>
</html>