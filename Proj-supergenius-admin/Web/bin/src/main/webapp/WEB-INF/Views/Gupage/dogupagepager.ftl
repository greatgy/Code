[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Admin.ftl" as a]
<html>
<head>
<title>专利论文管理</title>
<script type="text/javascript" src="${basejs}/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
     var isImg = 0;
$(function() {
	//修正点击add，图片上传按钮不回显的BUG
	$("#btnadd").click(function() {
	   $(window.frames["frameImg0"].document).find("#formImg0").css("display","block");
	   $(window.frames["frameImg1"].document).find("#formImg1").css("display","block");
	   $(".pdf").show();
		$(".img").hide();
		isImg = 0;
    });
    
	$("#btnedit").click(function() {
	$(window.frames["frameImg2"].document).find("#formImg2").css("display","block");
	$(window.frames["frameImg3"].document).find("#formImg3").css("display","block");
		var row = getSelected(dg);
	    if (row.kind == 1) {
	    	$(".editimg").hide();
	    	$(".editpdf").show();
	    } else {
	   		$(".editimg").show();
	    	$(".editpdf").hide();
	        err("请选择一项！");
	    }
	});
	
	$("#btnview").click(function() {
    	var row = getSelected(dg);
	    if (row.kind == 1) {
	    	$(".viewimg").hide();
	    	$(".viewpdf").show();
	    } else {
	   		$(".viewimg").show();
	    	$(".viewpdf").hide();
	        err("请选择一项！");
	    }
    });
    
	$(".kind").each(function(){
		if ($(this).val() == 1) {
			$(".pdf").show();
			$(".img").hide();
			isImg = 0;
		}
	})
	$(".kind").click(function() {
		if ($(this).val() == 0) {
			$(".pdf").hide();
			$(".img").show();
			isImg = 1;
		} else {
			$(".pdf").show();
			$(".img").hide();
			isImg = 0;
		}
	})
});

function initDGData(row) {
	for(var i in row) {
		if (row[i].status == 1) {
			row[i].statusName = "正常";
		} else if(row[i].status == 0) {
			row[i].statusName = "冻结";
		}
		if (row[i].kind == 1) {
			row[i].kindName = "pdf文件";
		} else if(row[i].kind == 0) {
			row[i].kindName = "图片文件";
		}
	}
}

/*重写add的表单提交方法*/ 
function submitHandlerForAdd(obj, dg) {
	$(obj).parents("form").form('submit', {
	    onSubmit: function(param) {
	   if (isImg==0 && $("input[name='file']").val() == "") {
	    err("您未上传文件！");
        return false;
	   };
	   if (($("#imguploadinput0").find("input[name='imgdata']").val() == undefined)) {
        err("您未上传封面图！");
        return false;
       };
	   if (isImg==1 && ($("#imguploadinput1").find("input[name='imgdata1']").val() == undefined)) {
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
		var flag = false;
		if (objid == "disable") {
		    confirmPostAjax('冻结该选项', '确定要冻结该文章吗？', '${base}${baseAdminPath}/${site}/ajax/pager/${channel}/status/0', "post");
		}
		if (objid == "enable") {
		    confirmPostAjax('解冻该选项', '确认要解冻该文章吗？', '${base}${baseAdminPath}/${site}/ajax/pager/${channel}/status/1', "post");
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
		[/@a.search]
        [@a.datagrid]
            <th data-options="field:'title',width:62">标题</th>
            <th data-options="field:'statusName',width:10">状态</th>
            <th data-options="field:'updatetimeStr',width:20">操作时间</th>
            <th data-options="field:'adminname',width:18">操作人</th>
        [/@a.datagrid]
        [@a.toolbar]
         <a id="disable" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="myButtonHandler(this, null)">冻结</a>
        <a id="enable" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="myButtonHandler(this, null)">解冻</a>
        [/@a.toolbar]  
        [@a.tools /]
    </section>
		
		[@a.addsection canuploadfile=true onclicksubmit="submitHandlerForAdd(this)"]
         <tr>
            <td>标题：</td>
            <td><input class="easyui-validatebox" name="title" data-options="required:true" id="add_title"/></td>
        </tr>
            [#if channel == "gupagepatent"]
            	<input type="hidden" name="type" value="0">
            [#else]
            	<input type="hidden" name="type" value="1">
            [/#if]
        <tr>
            <td>封面图:</td>
            <td class="addimgdata">[@a.imgupload multiple=false imgpath="${photopath}" name="imgdata"/]</td>
    	</tr>
        <tr>
            <td>文件类型：</td>
            <td>
            	<input type="radio" name="kind" class="kind" value="0">图片
            	<input type="radio" name="kind" class="kind" value="1" checked>pdf文件
            </td>
        </tr>
        <tr class="pdf">
	       <td>文档:</td>
	       <td><input type="file" name="file" accept="application/pdf"/></td>
        </tr>
        <tr class="img">
            <td>图片:</td>
            <td class="addimgdata">[@a.imgupload multiple=false imgpath="${photopath}" name="imgdata1"/]</td>
    	</tr>
    	[/@a.addsection]
    	
    	[@a.editsection canuploadfile=true onclicksubmit="submitHandlerForEdit(this)"]
         <tr>
            <td>标题：</td><input type="hidden" name="uid" />
            <td><input class="easyui-validatebox" name="title" data-options="required:true" id="edit_title"/></td>
        </tr>
        [#if channel == "gupagepatent"]
        	<input type="hidden" name="type" value="0">
        [#else]
        	<input type="hidden" name="type" value="1">
        [/#if]
    	 <tr>
		      <td>封面图:</td>
		      <td><img src="" set-format="${webimg}{0}" set-key="imgoriginal" style="width: 600px; height: 400px;"/>
		      [@a.imgupload multiple=false imgpath="${photopath}" name="imgdata"/]
		      </td>
	    </tr>
        <tr><td>文件类型：</td><td class="filekind"><span set-key="kindName"></span></td></tr>
        <tr class="editpdf"><td>pdf文件：</td><td><span set-key="filepath"></span><input type="file" name="file" accept="application/pdf"/></td></tr>
        <tr class="editimg"><td>图片：</td><td><img src="" set-format="${webimg}{0}" set-key="filepath" style="width: 600px; height: 400px;"/></span>[@a.imgupload multiple=false imgpath="${photopath}" name="imgdata1"/]</td></tr>
    	[/@a.editsection]
    	
    	[@a.viewsection]
        <tr><td>标题</td><td><span set-key="title"></span></td></tr>
        <tr>
		      <td>封面图:</td>
		      <td><img src="" set-format="${webimg}{0}" set-key="imgoriginal" style="width: 600px; height: 400px;"/></td>
	    </tr>
        <tr><td>文件类型：</td><td class="filekind"><span set-key="kindName"></span></td></tr>
        <tr class="viewpdf"><td>pdf文件：</td><td><span set-key="filepath"></span></td></tr>
        <tr class="viewimg"><td>图片：</td><td><img src="" set-format="${webimg}{0}" set-key="filepath" style="width: 600px; height: 400px;"/></span></td></tr>
        [/@a.viewsection]
</body>
</html>