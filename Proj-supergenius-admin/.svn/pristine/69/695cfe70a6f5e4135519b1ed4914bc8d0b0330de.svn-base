[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Admin.ftl" as a]
<html>
<head>
<title>${channelname}</title>
<script type="text/javascript" src="${basejs}/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
	$(function(){
		$("#btnedit").click(function() {
			var row = getSelected(dg);
			$.get('${base}${baseAdminPath}/career/ajax/${channel}/teasecount/'+row.uid, function(result) {
				if (result.count > 0 || row.touid != null) {
					$("#notedittease").show();
					$("#edittease").hide();
					if(row.touid == null){
						$("#tip").html("(${channelname}已经产生评论，不可更改时间)");
					} else{
						$("#tip").html("(${channelname}的评论，不可更改时间)");
					}
				}else{
					$("#edittease").show();
					$("#notedittease").hide();
				}
			})
		});
	})
	function initDGData(row) {
			for(var i in row) {
				if (row[i].fromUserType == 1) {
					row[i].fromType = "会员";
				} else {
					row[i].fromType = "游客";
				}
				if('${channel}' == "careertease"){
					if(row[i].touid == null){
						row[i].typesName="鬼话";
					}else{
						row[i].typesName="鬼话评论";
					}
				}else{
					if(row[i].touid == null){
						row[i].typesName="吐槽";
					}else{
						row[i].typesName="吐槽评论";
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
	        } else {
		        $("#enable").linkbutton("disable");
		        $("#disable").linkbutton("disable");
	        }
    }
    
    function myButtonHandler(obj, url) {
		var objid = $(obj).attr("id");
		if (objid == "disable") {
		    confirmAjax('冻结该选项', '冻结后该评论在前台将不可见，确定要冻结吗？', '${base}${baseAdminPath}/career/ajax/${channel}/status');
		}
		if (objid == "enable") {
		    confirmAjax('解冻该选项', '解冻后将在前台正常显示该评论，确认要解冻吗？', '${base}${baseAdminPath}/career/ajax/${channel}/status');
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

</script>
</head>
<body>
	<section>
		<table>
			<tr>
			   <td>评论数：</td>
			   <td><span>${commentsCount}</span></td>
			<tr>
			<tr>
			   <td>点赞数：</td>
			   <td><span>${praiseCount}</span></td>
			<tr>
			<tr>
			   <td>总参与人数：</td>
			   <td><span>${allCount}</span></td>
			<tr>
		</table>
		[@a.search]
	        <tr>
	        	<td colspan="10">
		        	<label for="search">搜索: </label><input id="search" type="text" name="search" />&nbsp;&nbsp;&nbsp;
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
                <td>状态：
                    <input type="radio" id="twiceall" name="touid" value="" checked/><label for="twiceall">全部</label>
                    <input type="radio" id="tease" name="touid" value="1" /><label for="tease">[#if channel=='careertease']鬼话[#else]吐槽[/#if]</label>
                    <input type="radio" id="comment" name="touid" value="0" /><label for="comment">评论</label>
                </td>
        	</tr>
	        <tr>
	            <td>起始操作时间: <input name="createtimestart" type="text" editable="false" class="easyui-datebox" /></td>
                <td>结束操作时间: <input name="createtimeend" type="text" editable="false" class="easyui-datebox" /></td>
	        </tr>
		[/@a.search]
		
		[@a.datagrid rownumbers=true]
            <th data-options="field:'content',width:200">内容</th>
            <th data-options="field:'fromType',width:100">发布人身份</th>
            <th data-options="field:'fromusername',width:100">发布人昵称</th>
            <th data-options="field:'typesName',width:80">类型</th>
            <th data-options="field:'statusName',width:80">状态</th>
            <th data-options="field:'createtimeStr',width:120">创建时间</th>
            <th data-options="field:'adminname',width:100">操作人</th>
	    [/@a.datagrid]
	    
	    [@a.toolbar canadd=false]
		    <a id="disable" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="myButtonHandler(this, null)">冻结</a>
	        <a id="enable" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="myButtonHandler(this, null)">解冻</a>
        [/@a.toolbar]
	    [@a.tools /]
	    
	</section>
 	[@a.viewsection]
		<tr>
            <td>评论人:</td>
            <td><span set-key="fromusername"></span></td>
        </tr>
        <tr>
            <td>评论内容:</td>
            <td><span set-key="content"></span></td>
        </tr>
        <tr>
            <td>状态:</td>
            <td><span set-key="statusName"></span></td>
        </tr>
        <tr>
            <td>创建时间:</td>
            <td><span set-key="createtimeStr"></span></td>
        </tr>
        <tr>
            <td>操作人:</td>
            <td><span set-key="adminname"></span></td>
        </tr>
	[/@a.viewsection]
	[@a.editsection canuploadfile=true]
	        <tr>
	            <td>评论人：</td>
	            <input type="hidden" name="uid" />
	            <td><input class="easyui-validatebox" name="fromusername"/></td>  
	        </tr>
	         <tr>
	            <td>评论内容:</td>
	            <td>[@a.ckeditor name="content" /]</td>
	        </tr>
	        <tr>
	            <td>状态:</td>
	            <td>
		            <input type="radio" id="statusone" name="status" value="1" /><label for="statusone">正常</label>
		            <input type="radio" id="statustwo" name="status" value="0" /><label for="statustwo">冻结</label>
	            </td>
            </tr>
	        <tr>
	            <td>创建时间:</td>
	            <td id="edittease" style="display:none;"><input name="createtimeStr" type="text" editable="false" class="easyui-datetimebox"/></td>
	            <td id="notedittease" style="display:none;"><span set-key="createtimeStr"></span>&nbsp;&nbsp;&nbsp;&nbsp;<span id="tip" style="color:#fe1634;"></td></span>
        	</tr>   
	       <tr>
            <td>操作人:</td>
            <td><span set-key="adminname"></span></td>
        </tr>
    	[/@a.editsection]
	
	
</body>
</html>
