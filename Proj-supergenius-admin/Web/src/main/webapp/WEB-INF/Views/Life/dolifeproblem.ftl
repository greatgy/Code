[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Admin.ftl" as a]
<html>
<head>
<title>天才人生-问题</title>
<script type="text/javascript" src="${basejs}/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
$(function() {
	$("#replybtn").click(function() {
		var row = getSelected(dg);
		$.get("${base}${baseAdminPath}/life/ajax/lifeproblem/answers",{uid:row.uid},function(data){
			var list = JSON.parse(data);
			var html = '';
			for(var i= list.length-1;i>=0;i--){
				if(list[i].fromuseruid == 'uid00000000000000000000000000000'){
					html += '<span style="color: #fe1634;display:block;">(专家:)'+list[i].content+'</span>';
				}else{
					html += '<span style="display:block;">'+list[i].content+'</span>';
				}
			}
			$("#lastinfo").html(html);
		});
		$("#replyinfos").focus();
	});
	
	$("#evaluate").click(function(){
		$("#envluates").focus();
	})
});
function datagrid_loadFilter(data) {
	initDGData(data.rows);
	return data;
}
function initDGData(row) {
	for(var i in row) {
	if((row[i].cid == 16384 || row[i].cid == 1073741824) && row[i].ismember == 1){
		if(row[i].state == 0){
			row[i].statusName = '<span style="color: #fe1634;">待点评</span>';
		}else if(row[i].state == 1){
			row[i].statusName = '<span style="color: #fe1634;">待回复</span>';
		}else if (row[i].status == 0) {
			row[i].statusName = "冻结";
		} else {
			row[i].statusName = "正常";
		}
	}else{
		if (row[i].status == 1) {
			row[i].statusName = "正常";
		} else{
			row[i].statusName = "冻结";
		}
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
        /********状态栏 点评/回复 按钮的逻辑判断***********/
        if (list[0].cid == 16384 || list[0].cid == 1073741824){
	        if (list[0].ismember == 1 && list[0].status == 1){
		        if (list[0].state == 0 ){
	            	$("#replybtn").linkbutton("disable");
	            	$("#evaluate").linkbutton("enable");
		        }else if(list[0].state == 1 ){
	            	$("#replybtn").linkbutton("enable");
	            	$("#evaluate").linkbutton("disable");
		        }else{
		            $("#replybtn").linkbutton("disable");
		            $("#evaluate").linkbutton("disable");
		        }
	        } else {
	            $("#replybtn").linkbutton("disable");
	            $("#evaluate").linkbutton("disable");
	        }
        }else {
            $("#replybtn").linkbutton("disable");
		    $("#evaluate").linkbutton("disable");
        }
        /********************************************/
    } else {
        $("#replybtn").linkbutton("disable");
        $("#evaluate").linkbutton("disable");
        $("#enable").linkbutton("disable");
    	$("#disable").linkbutton("disable");
    }
}
function myButtonHandler(obj, url) {
	var cid = getSelected(dg).cid;
	var objid = $(obj).attr("id");
	var flag = false;
	if (objid == "stick") {
		$.get('${base}${baseAdminPath}/${site}/ajax/lifeproblem/topcount/'+cid, function(result) {
		if (result.count >= 4) {
			err("该模块置顶数量不能超过4个！");
	        return false;
		}
		confirmAjax('置顶该问题', '确定要置顶该问题么？', '${base}${baseAdminPath}/${site}/ajax/lifeproblem/enable', "get");
	});
	}
	if (objid == "unstick") {
	    confirmAjax('取消置顶', '确定要取消置顶么？', '${base}${baseAdminPath}/${site}/ajax/lifeproblem/disable', "get");
	}
	if (objid == "disable") {
	    confirmPostAjax('冻结该选项', '确定要冻结该问题吗？', '${base}${baseAdminPath}/${site}/ajax/${channel}/status/0', "post");
	}
	if (objid == "enable") {
	    confirmPostAjax('解冻该选项', '确认要解冻该问题吗？', '${base}${baseAdminPath}/${site}/ajax/${channel}/status/1', "post");
	}
}
/*重写edit的表单提交方法*/ 
function submitHandlerForEdit(obj, dg) {
	$(obj).parents("form").form('submit', {
	    onSubmit: function(param) {
	       if (!$('#checkbox-id').is(':checked') && $("#edit_title").val().length < 1) {
	       	err("您填写评语！");
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
                    <input type="radio" id="problem" name="cid" value="128" /><label for="problem">人生问答</label>
                    <input type="radio" id="recommend" name="cid" value="16384"/><label for="recommend">课程推荐</label>
                    <input type="radio" id="data" name="cid" value="4194304" /><label for="data">资料交流</label>
                    <input type="radio" id="design" name="cid" value="1073741824" /><label for="design">人生设计</label>
                </td>
            </tr>
		[/@a.search]
        [@a.datagrid]
            <th data-options="field:'title',width:100">问题</th>
            <th data-options="field:'username',width:15">发表人</th>
            <th data-options="field:'cataloguename',width:30">所属板块</th>
            <th data-options="field:'statusName',width:15">状态</th>
            <th data-options="field:'updatetimeStr',width:40">操作时间</th>
            <th data-options="field:'adminname',width:20">操作人</th>
        [/@a.datagrid]
        [@a.toolbar canadd=false]
	        <a id="replybtn" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" data-crumbshow="{selector:'#replysection', title:'回复'}" onclick="editHandler(this, null, '#replysection')">回复</a>
	        <a id="evaluate" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" data-crumbshow="{selector:'#evaluatesection', title:'点评'}" onclick="editHandler(this, null, '#evaluatesection')">点评</a>
	        <a id="disable" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="myButtonHandler(this, null)">冻结</a>
        	<a id="enable" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="myButtonHandler(this, null)">解冻</a>
        [/@a.toolbar]
        [@a.tools /]
   </section>
   		[@a.viewsection]
	        <tr><td>问题</td><td><span set-key="title"></span></td></tr>
	        <tr><td>发表人</td><td><span set-key="username"></span></td></tr>
	        <tr><td>所属板块</td><td><span set-key="cataloguename"></span></td></tr>
	        <tr><td>状态</td><td><span set-key="statusName"></span></td></tr>
	        <tr><td>操作时间</td><td><span set-key="updatetimeStr"></span></td></tr>
	        <tr><td>操作人</td><td><span set-key="adminname"></span></td></tr>
	        <tr><td></td><td><span id="don" style="width: 600px;height:400px;"></span></td></tr>
        [/@a.viewsection]
        [@a.editsection canuploadfile=true]
	         <tr>
	            <td>问题：</td><input type="hidden" name="uid" />
	            <td><input class="easyui-validatebox" name="title" data-options="required:true" /></td>
	        </tr>
	         
	        <tr>
	            <td>问题描述:</td>
	            <td>[@a.ckeditor name="content" /]</td>
	        </tr>
    	[/@a.editsection]
        [@a.editsection  id="replysection" action="${base}${baseAdminPath}/${site}/ajax/${channel}/comment_reply" onclicksubmit="submitHandlerForEdit(this)"]
	        <input type="hidden" name="uid">
	        <tr><td>问题:</td><td><span set-key="title"></span></td></tr>
	        <tr><td>问题描述:</td><td><span set-key="content"></span></td></tr>
	        <tr class="reply">
	        	<td>消息：</td>
	        	<td id="lastinfo"></td>
	        </tr>
	        <tr class="reply">
	        	<td></td>
	            <td><input type="checkbox" name="notreply" value="on" id="checkbox-id"/><label for="cidtwo">不予回复</label></td>
	        </tr>
	        <tr>
	        	<td id="goreply">回复：</td>
	            <td><textarea name="infodata" class="replytext" style="width:500px;height:200px;" id="replyinfos"></textarea></td>
	        </tr>
    	[/@a.editsection]
    	[@a.editsection id="evaluatesection" action="${base}${baseAdminPath}/${site}/ajax/${channel}/comment_reply"]
	        <input type="hidden" name="uid">
	        <tr><td>问题:</td><td><span set-key="title"></span></td></tr>
	        <tr><td>问题描述:</td><td><span set-key="content"></span></td></tr>
	        <tr>
	        	<td id="goreply">点评：</td>
	            <td><textarea name="infodata" class="replytext" style="width:500px;height:200px;" id="envluates"></textarea></td>
	        </tr>
    	[/@a.editsection]
</body>
</html>