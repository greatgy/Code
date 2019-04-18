[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Admin.ftl" as a]
<html>
<head>
<title>视频管理</title>
<script type="text/javascript" src="${basejs}/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
$(function() {
    
	$("#replybtn").click(function() {
		var row = getSelected(dg);
		$.get("${base}${baseAdminPath}/life/ajax/lifevideo/comments",{uid:row.uid},function(data){
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

function initDGData(row) {
	for(var i in row) {
		if(row[i].cid == 32768 && row[i].state == 0){
			row[i].statusName = '<span style="color:#fe1634">待点评</span>';
		}else if(row[i].state == 1){
			row[i].statusName = '<span style="color: #fe1634;">待回复</span>';
		}else if (row[i].status == 1) {
			row[i].statusName = "正常";
		} else if(row[i].status == 0) {
			row[i].statusName = "冻结";
		}
		if(row[i].cid == 32768){
			row[i].catalogueName = '效果检验';
		} else if(row[i].cid == 2147483648) {
			row[i].catalogueName = "我的舞台";
		}
	}
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
        if(list[0].cid == 32768 && list[0].state == 0){
        	$("#evaluate").linkbutton("enable");
        }else{
        	$("#evaluate").linkbutton("disable");
        }
        if(list[0].cid == 32768 && list[0].state == 1){
        	$("#replybtn").linkbutton("enable");
        }else{
        	$("#replybtn").linkbutton("disable");
        }
    } else {
        $("#stick").linkbutton("disable");
        $("#unstick").linkbutton("disable");
         $("#enable").linkbutton("disable");
        $("#disable").linkbutton("disable");
        $("#btnrecommend").linkbutton("disable");
        $("#replybtn").linkbutton("disable");
        $("#evaluate").linkbutton("disable");
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

 	function myButtonHandler(obj, url) {
		var objid = $(obj).attr("id");
		if (objid == "disable") {
		    confirmAjax('冻结该选项', '确定要冻结吗？', '${base}${baseAdminPath}/life/ajax/lifevideo/status');
		}
		if (objid == "enable") {
		    confirmAjax('解冻该选项', '确认要解冻吗？', '${base}${baseAdminPath}/life/ajax/lifevideo/status');
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
	$.get(url, {uid : getSelected(dg).uid}, function(result) {
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
		        	<label for="name">标题: </label><input id="name" type="text" name="name" class="search"/>&nbsp;&nbsp;&nbsp;
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
                    <input type="radio" id="cidall" name="cid" value="" checked/><label for="cidall">全部</label>
                    <input type="radio" id="cidone" name="cid" value="32768" /><label for="cidone">效果检验</label>
                    <input type="radio" id="cidtwo" name="cid" value="2147483648" /><label for="cidtwo">我的舞台</label>
                </td>
        	</tr>
        	<tr>
                <td>年级：
                	<input type="radio" id="moduleall" name="grade" value="" checked/><label for="moduleall">全部</label>
                    <input type="radio" id="grade_6" name="grade" value="1" /><label for="grade_6">六年级</label>
                    <input type="radio" id="grade_7" name="grade" value="2" /><label for="grade_7">七年级</label>
                    <input type="radio" id="grade_8" name="grade" value="4" /><label for="grade_8">八年级</label>
                    <input type="radio" id="grade_9" name="grade" value="8" /><label for="grade_9">九年级</label>
                </td>
        	</tr>
		[/@a.search]
        [@a.datagrid]
            <th data-options="field:'title',width:62">标题</th>
            <th data-options="field:'catalogueName',width:62">所属板块</th>
            <th data-options="field:'gradeName',width:10">年级</th>
            <th data-options="field:'subjectname',width:10">科目</th>
            <th data-options="field:'statusName',width:10">状态</th>
            <th data-options="field:'updatetimeStr',width:20">操作时间</th>
            <th data-options="field:'adminname',width:18">操作人</th>
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
	        <tr><td>标题</td><td><span set-key="title"></span></td></tr>
	        <tr><td>所属板块</td><td><span set-key="catalogueName"></span></td></tr>
	        <tr><td>年级</td><td><span set-key="gradeName"></span></td></tr>
	        <tr><td>科目</td><td><span set-key="subjectname"></span></td></tr>
	        <tr><td>描述</td><td><span set-key="summary"></span></td></tr>
	        <tr><td>关键字</td><td><span set-key="keywords"></span></td></tr>
	        <tr>
		        <td>图片:</td>
		        <td><img src="" set-format="${webimg}{0}" set-key="imgoriginal" style="width: 600px; height: 400px;"/></td>
	        </tr>
	        <tr><td>正文</td><td style="height:600px;"><span set-key="content"></span></td></tr>
        [/@a.viewsection]
    	
		[@a.editsection  id="replysection" onclicksubmit="submitHandlerForEdit(this)"]
	        <input type="hidden" name="uid">
	        <tr><td>视频标题</td><td><span set-key="title"></span></td></tr>
	        <tr><td>视频</td><td style="height:600px;"><span set-key="content"></span></td></tr>
	        <tr><td>所属年级</td><td><span set-key="gradeName"></span></td></tr>
	        <tr><td>科目</td><td><span set-key="subjectname"></span></td></tr>
	        <tr><td>发起人</td><td><span set-key="username"></span></td></tr>
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
    	[@a.editsection id="evaluatesection"]
	        <input type="hidden" name="uid">
	        <tr><td>视频标题</td><td><span set-key="title"></span></td></tr>
	        <tr><td>视频/音频</td><td style="height:600px;"><span set-key="content"></span></td></tr>
	        <tr><td>所属年级</td><td><span set-key="gradeName"></span></td></tr>
	        <tr><td>科目</td><td><span set-key="subjectname"></span></td></tr>
	        <tr><td>发起人</td><td><span set-key="username"></span></td></tr>
	        <tr>
	        	<td id="goreply">点评：</td>
	            <td><textarea name="infodata" class="replytext" style="width:500px;height:200px;" id="envluates"></textarea></td>
	        </tr>
    	[/@a.editsection]
</body>
</html>