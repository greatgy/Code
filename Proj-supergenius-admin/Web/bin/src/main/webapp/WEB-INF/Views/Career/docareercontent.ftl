[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Admin.ftl" as a]
<html>
<head>
<title>广告位管理</title>
<script type="text/javascript" src="${basejs}/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
//调整编辑页面显示选项内容
$(function() {
	 $("#btnedit").click(function() {
    	//调整编辑页面显示选项内容
    	var row = getSelected(dg);
	    if (row) {
	        var isshow;
	        row.isshow = row.status ;
	    } else {
	        err("请选择一项！");
	    }
	    if(row.type == 10){
	 		$("#edit_img").siblings("tr").addClass("hd");
	 		$("#teasecontent").prop("checked", "checked");
	    }
	    $(window.frames["frameImg1"].document).find("#formImg1").css("display","block");
    });
    
    $("#btnadd").click(function() {
	   $(window.frames["frameImg0"].document).find("#formImg0").css("display","block");
    });
    
});

function initDGData(row) {
	for(var i in row) {
		if (row[i].type == 0) {
			row[i].module = "职场心得";
		} else if(row[i].type == 1){
			row[i].module = "职场八卦";
		}else if(row[i].type == 2){
			row[i].module = "应聘指南";
		}else if(row[i].type == 3){
			row[i].module = "文章详情页";
		}else if(row[i].type == 10){
			row[i].module = "职场吐槽";
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
		        if (list[0].type == 10){
		        	$("#disable").linkbutton("disable");
		        	$("#enable").linkbutton("disable");
		        }
	        } else {
		        $("#enable").linkbutton("disable");
		        $("#disable").linkbutton("disable");
	        }
    }
    
    function myButtonHandler(obj, url) {
		var objid = $(obj).attr("id");
		if (objid == "disable") {
		    confirmAjax('冻结该选项', '冻结后该评论在前台将不可见，确定要冻结吗？', '${base}${baseAdminPath}/career/ajax/careercontent/status');
		}
		if (objid == "enable") {
		    confirmAjax('解冻该选项', '解冻后将在前台正常显示该评论，确认要解冻吗？', '${base}${baseAdminPath}/career/ajax/careercontent/status');
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
	
/*重写edit的表单提交方法*/ 
function submitHandlerForEdit(obj, dg) {
	var val=$("#editcontent").children('input:radio[name="type"]:checked').val();
	if(val == 2){
		$.get('${base}${baseAdminPath}/career/ajax/careercontent/guidecount', function(result) {
			if (result.count >= 5) {
				err("应聘指南相关推荐超过5个！");
		        return false;
			}else{
				reloadForEdit(obj, dg);
			}
		});
	}else{
		reloadForEdit(obj, dg);
	}
}

function reloadForEdit(obj, dg){
	$(obj).parents("form").form('submit', {
	    onSubmit: function(param) {
       if ($("#edit_title").val().length < 1) {
        err("您填写标题！");
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

function reloadForAdd(obj, dg){
	$(obj).parents("form").form('submit', {
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
	
	//自定义提交方法
	function submitHandlerForAdd(obj, dg){
		var val=$("#addcontent").children('input:radio[name="type"]:checked').val();
		if(val == 2){
			$.get('${base}${baseAdminPath}/career/ajax/careercontent/guidecount', function(result) {
				if (result.count >= 5) {
					err("应聘指南相关推荐超过5个！");
			        return false;
				}else{
					reloadForAdd(obj, dg);
				}
			});
		}else{
			reloadForAdd(obj, dg);
		}
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
                <td>板块：
                	<input type="radio" id="moduleall" name="type" value="" checked/><label for="moduleall">全部</label>
                    <input type="radio" id="experience" name="type" value="0"/><label for="experience">职场心得</label>
                    <input type="radio" id="gossip" name="type" value="1" /><label for="gossip">职场八卦</label>
                    <input type="radio" id="guide" name="type" value="2" /><label for="guide">应聘指南</label>
                    <input type="radio" id="detail" name="type" value="3" /><label for="detail">文章详情页</label>
                </td>
        	</tr>
		[/@a.search]
		
		[@a.datagrid]
            <th data-options="field:'name',width:100">名称</th>
            <th data-options="field:'module',width:100">所属板块</th>
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
   [@a.addsection onclicksubmit="submitHandlerForAdd(this)"]
   		<tr>
            <td>选择类型:</td>
            <td id="addcontent"><input type="radio" id="ad" name="type" value="0" checked/><label for="ad">职场心得</label>
                <input type="radio" id="guide" name="type" value="1"/><label for="guide">职场八卦</label>
                <input type="radio" id="other" name="type" value="2" /><label for="other">招聘指南</label> 
                <input type="radio" id="detail-add" name="type" value="3" /><label for="other">文章详情页</label> 
            </td>
        </tr>
        <tr>
            <td>名称:</td>
            <td><input type="text" class="easyui-validatebox" name="name" data-options="min:0,required:true" /></td>
        </tr>
        <tr>
            <td>图片:</td>
            <td class="addimgdata">[@a.imgupload multiple=false imgpath="${photopath}" name="imgdata"/]</td>
    	</tr>
        <tr>
            <td>链接:</td>
            <td><input type="text" class="easyui-validatebox" name="originurl" data-options="min:0,required:true" /></td>
        </tr>
        <tr>
            <td>是否显示:</td>
            <td><input type="radio" id="show" name="isshow" value="1" checked/><label for="show">是</label>   <input type="radio" id="notshow" name="isshow" value="0" /><label for="notshow">否</label> </td>
        </tr>
    [/@a.addsection]
    [@a.editsection onclicksubmit="submitHandlerForEdit(this)"]
        <input type="hidden" name="uid">
        <tr>
            <td>选择类型:</td>
            <td id="editcontent"><input type="radio" id="ad" name="type" value="0"/><label for="ad">职场心得</label>
                <input type="radio" id="guide" name="type" value="1"/><label for="guide">职场八卦</label>
                <input type="radio" id="other" name="type" value="2" /><label for="other">招聘指南</label> 
                <input type="radio" id="other" name="type" value="3" /><label for="other">文章详情页</label> 
                <input type="radio" id="teasecontent" name="type" value="10" class="hd"/>
            </td>
        </tr>
        <tr>
            <td>名称:</td>
            <td><input type="text" class="easyui-validatebox" name="name" data-options="min:0,required:true" id="edit_title"/></td>
        </tr>
        <tr id="edit_img">
            <td>图片:</td>
            <td><img id="avatarbig" style="width:150px; height:150px" src="" set-format="${webimg}{0}" set-key="content"/>
            	[@a.imgupload multiple=false imgpath="${photopath}" name="imgdata"/]
            </td>
    	</tr>
        <tr>
            <td>链接:</td>
            <td><input type="text" class="easyui-validatebox" name="originurl" data-options="min:0,required:true" /></td>
        </tr>
        <tr>
            <td>是否显示:</td>
            <td><input type="radio" id="editshow" name="isshow" value="1"/><label for="editshow">是</label>   <input type="radio" id="editnotshow" name="isshow" value="0" /><label for="editnotshow">否</label> </td>
        </tr>
    [/@a.editsection]
 	[@a.viewsection]
		<tr>
            <td>名称:</td>
            <td><span set-key="name"></span></td>
        </tr>
        <tr>
            <td>所属板块:</td>
            <td><span set-key="module"></span></td>
        </tr>
		<tr>
	        <td>原图:</td>
	        <td><img src="" set-format="${webimg}{0}" set-key="content" style="width: 600px; height: 400px;"/></td>
        </tr>    
        <tr>
            <td>链接:</td>
            <td><span set-key="originurl"></span></td>
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
