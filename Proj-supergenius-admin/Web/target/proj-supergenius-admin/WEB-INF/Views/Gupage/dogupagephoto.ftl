[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Admin.ftl" as a]
<html>
<head>
<title>图片管理</title>
<script type="text/javascript" src="${basejs}/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
$(function() {
     
	//修正点击add，图片上传按钮不回显的BUG
	$("#btnadd").click(function() {
	   $(window.frames["frameImg1"].document).find("#formImg1").css("display","block");
	   var editor = CKEDITOR.instances["ckeid1"];
       editor.setData("");
    });
    
	$("#btnedit").click(function() {
	$(window.frames["frameImg0"].document).find("#formImg0").css("display","block");
	var row = getSelected(dg);
	
	});
});

function initDGData(row) {
	for(var i in row) {
		if (row[i].status == 1) {
			row[i].statusName = "正常";
		} else if(row[i].status == 0) {
			row[i].statusName = "冻结";
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
	   if (($("#imguploadinput1").find("input[name='imgdata']").val() == undefined)) {
        err("您未上传图片！");
        return false;
       };
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
        if (list[0].istop == 1){
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
    } else {
        $("#stick").linkbutton("disable");
        $("#unstick").linkbutton("disable");
         $("#enable").linkbutton("disable");
        $("#disable").linkbutton("disable");
        $("#btnrecommend").linkbutton("disable");
    }
}

function myButtonHandler(obj, url) {
		var objid = $(obj).attr("id");
		var flag = false;
		if (objid == "stick") {
			$.get('${base}${baseAdminPath}/${site}/ajax/${channel}/topcount', function(result) {
			if (result.count >= 4) {
				err("置顶数量超过4个！");
		        return false;
			}
			confirmAjax('置顶该文章', '确定要置顶该文章么？', '${base}${baseAdminPath}/${site}/ajax/${channel}/enable', "get");
		});
		}
		if (objid == "unstick") {
		    confirmAjax('取消置顶', '确定要取消置顶么？', '${base}${baseAdminPath}/${site}/ajax/${channel}/disable', "get");
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
			   <td>图片总数：</td>
			   <td><span>${count}</span></td>
			<tr>        
		</table>
		[@a.search]
	        <tr>
	        	<td colspan="10">
		        	<label for="name">标题: </label><input id="name" type="text" name="summary" class="search"/>&nbsp;&nbsp;&nbsp;
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
                <td>是否置顶：
                	<input type="radio" id="statusall" name="istop" value="" checked="checked"/><label for="statusone">全部</label>
                    <input type="radio" id="statusone" name="istop" value="1" /><label for="statusone">是</label>
                    <input type="radio" id="statustwo" name="istop" value="0" /><label for="statustwo">否</label>
                </td>
        	</tr>
		[/@a.search]
        [@a.datagrid]
            <th data-options="field:'summary',width:62">标题</th>
            <th data-options="field:'statusName',width:10">状态</th>
            <th data-options="field:'istopName',width:13,align:'center'">是否置顶</th>
            <th data-options="field:'updatetimeStr',width:20">操作时间</th>
            <th data-options="field:'adminname',width:18">操作人</th>
        [/@a.datagrid]
        [@a.toolbar nameadd="添加图片"]
        <a id="stick" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="myButtonHandler(this, null)">置顶</a>
        <a id="unstick" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="myButtonHandler(this, null)">取消置顶</a>
         <a id="disable" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="myButtonHandler(this, null)">冻结</a>
        <a id="enable" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="myButtonHandler(this, null)">解冻</a>
        [/@a.toolbar]  
        [@a.tools /]
    </section>
		
		[@a.viewsection]
        <tr><td>标题</td><td><span set-key="summary"></span></td></tr>
        <tr><td>是否置顶</td><td><span set-key="istopName"></span></td></tr>
        <tr>
	        <td>图片:</td>
	        <td><img src="" set-format="${webimg}{0}" set-key="imgoriginal" style="width: 600px; height: 400px;"/></td>
        </tr>
        [/@a.viewsection]
   	    [@a.editsection canuploadfile=true onclicksubmit="submitHandlerForEdit(this)"]
         <tr>
            <td>标题：</td><input type="hidden" name="uid" />
            <td><input class="easyui-validatebox" name="summary" data-options="required:true" id="edit_title"/></td>
        </tr>
        <tr>
            <td>图片:</td>
            <td><img id="avatarbig" style="width:150px; height:150px" src="" set-format="${webimg}{0}" set-key="imgoriginal"/>
            	[@a.imgupload multiple=false imgpath="${photopath}" name="imgdata"/]
            </td>
        </tr>
    	[/@a.editsection]
    	
    	[@a.addsection canuploadfile=true onclicksubmit="submitHandlerForAdd(this)"]
         <tr>
            <td>标题：</td><input type="hidden" name="uid" />
            <td><input class="easyui-validatebox" name="summary" data-options="required:true" id="add_title"/></td>
        </tr>
        <tr>
            <td>图片:</td>
            <td class="addimgdata">[@a.imgupload multiple=false imgpath="${photopath}" name="imgdata"/]</td>
    	</tr>
    	[/@a.addsection]
</body>
</html>