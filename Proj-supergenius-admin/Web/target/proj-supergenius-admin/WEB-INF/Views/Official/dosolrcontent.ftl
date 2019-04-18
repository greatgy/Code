[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Admin.ftl" as a]
<html>
<head>
<title>内容管理</title>
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
	    $(window.frames["frameImg1"].document).find("#formImg1").css("display","block");
    });
    
    $("#btnadd").click(function() {
	   $(window.frames["frameImg0"].document).find("#formImg0").css("display","block");
    });
    
});

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
		    confirmAjax('冻结该选项', '冻结后该评论在前台将不可见，确定要冻结吗？', '${base}${baseAdminPath}/solr/ajax/solrcontent/status');
		}
		if (objid == "enable") {
		    confirmAjax('解冻该选项', '解冻后将在前台正常显示该评论，确认要解冻吗？', '${base}${baseAdminPath}/solr/ajax/solrcontent/status');
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
	
	//自定义提交方法
	function submitHandlerForAdd(obj, dg){
	   $(obj).parents("form").form('submit', {
	   	   onSubmit: function(param) {
	   	       if ($("#imguploadinput0").find("input[name='imgdata']").val() == undefined) {
	   	        err("您未上传图片！");
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
		[/@a.search]
		
		[@a.datagrid]
            <th data-options="field:'name',width:100">名称</th>
            <th data-options="field:'statusName',width:100">状态</th>
            <th data-options="field:'createtimeStr',width:200">创建时间</th>
            <th data-options="field:'adminname',width:200">操作人</th>
	    [/@a.datagrid]
	    
	    [@a.toolbar candel=false]
		    <a id="disable" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="myButtonHandler(this, null)">冻结</a>
		    <a id="enable" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="myButtonHandler(this, null)">解冻</a>
        [/@a.toolbar]
	    [@a.tools /]
	    
	</section>
   [@a.addsection onclicksubmit="submitHandlerForAdd(this)"]
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
            <td>选择类型:</td>
            <td><input type="radio" id="ad" name="type" value="0" checked/><label for="ad">广告位</label>   <input type="radio" id="other" name="type" value="1" /><label for="other">其他</label> </td>
        </tr>
        <tr>
            <td>是否显示:</td>
            <td><input type="radio" id="show" name="isshow" value="1" checked/><label for="show">是</label>   <input type="radio" id="notshow" name="isshow" value="0" /><label for="notshow">否</label> </td>
        </tr>
    [/@a.addsection]
    [@a.editsection]
        <input type="hidden" name="uid">
        <tr>
            <td>名称:</td>
            <td><input type="text" class="easyui-validatebox" name="name" data-options="min:0,required:true" /></td>
        </tr>
        <tr>
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
            <td>选择类型:</td>
            <td><input type="radio" id="editad" name="type" value="0"/><label for="editad">广告位</label>   <input type="radio" id="editother" name="type" value="1" /><label for="editother">其他</label> </td>
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
