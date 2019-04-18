[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Admin.ftl" as a]
<html>
<head>
<title>讲座管理</title>
<script type="text/javascript" src="${basejs}/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
	$(function() {
		//点击下载文件
		$("#adownload").click(function() {
			var uid = $("#viewuid").val();
			var url = "${base}${baseAdminPath}/${site}/${channel}/download" + "?uid=" + uid;
			gourl(url);
		});
	});
	
	function initDGData(row) {
		for(var i in row) {
			if(row[i].status == 1) {
				row[i].enterpriserStatusName = "报名中";
			} else if(row[i].status == 10) {
				row[i].enterpriserStatusName = "报名未开始";
			} else if(row[i].status == 13) {
				row[i].enterpriserStatusName = "报名结束";
			} else if(row[i].status == 12) {
				row[i].enterpriserStatusName = "时间确定";
			} else {
				row[i].enterpriserStatusName = "其他";
			}
		}
	}
	function mytoolbarStateHandler(event, j) {
		var list = getSelections(j.dg);
		if (list.length ==0 ) {
			$("#btnadd").linkbutton("disable");
			$("#btnenable").linkbutton("disable");
			$("#btndisable").linkbutton("disable");
			$("#btntinfook").linkbutton("disable");
			$("#btninfochange").linkbutton("disable");
		} else{
			var row = list[0];
			$("#btnadd").linkbutton("enable");
			$("#btnenable").linkbutton("enable");
			$("#btndisable").linkbutton("enable");
			$("#btntinfook").linkbutton("enable");
			$("#btninfochange").linkbutton("enable");
			if (row.status == 1) {//已拒绝
				getBtnEnable().linkbutton("disable");
				$("#btndel").linkbutton("disable");
			}
		}
	}
	
	//按钮处理
	function myButtonHandler(obj, url) {
		var objid = $(obj).attr("id");
		if (objid == "btnadd") {
			confirmAjax('增加下一期', '您确定要增加下一期吗？', url);
		} else if (objid == "btnenable") {
			confirmAjax('<font color="red">注意：</font>只能有一期为报名中', '您确定要将 <font color="red">' + getSelected(dg).name + '</font>的第<font color="red">' + getSelected(dg).semester +'</font>期设置为开始报名吗？', url);
		} else if (objid == "btndisable") {
			confirmAjax('<font color="red">注意：</font>只能有一期为报名中', '您确定要将  <font color="red">' + getSelected(dg).name + '</font>的第<font color="red">' + getSelected(dg).semester +'</font>期设置为结束报名吗？', url);
		} else if (objid == "btntinfook") {
			confirmAjax('确定发送讲座信息', '<table><tr><td>讲座名称:</td><td><span>' + getSelected(dg).name + '</span></td></tr><tr><td>演讲人名称:</td><td><span>' + getSelected(dg).username + '</span></td></tr><tr><td>开始时间:</td><td><span>' + getSelected(dg).timeStr + '</span></td></tr><tr><td>讲座地点:</td><td><span>' + getSelected(dg).address + '</span></td></tr></table>', url);
		} else if (objid == "btninfochange") {
			//confirmAjax('<font color="red">发送讲座信息修改邮件</font>', '<table><tr><td>讲座名称:</td><td><span>' + getSelected(dg).name + '</span></td></tr><tr><td>演讲人名称:</td><td><span>' + getSelected(dg).username + '</span></td></tr><tr><td>讲座时间:</td><td><span>' + getSelected(dg).timeStr + '</span></td></tr><tr><td>讲座地点:</td><td><span>' + getSelected(dg).address + '</span></td></tr><tr><td>修改原因:</td><td><input type="' + 'text" id="' + 'confirmreason" /></td></tr></table>', url);
			initFormData($("#dialogfrom"), getSelected(dg));
			$("#dialogfrom").dialog('open');
		} 
	}
	
	//弹框确定，然后去请求
	function confirmAjax(title, question, url) {
		$.messager.confirm(title, question, function(flag) {
			if (flag) {
				ajaxPostSubmit(url);
			}
		});
	}
	//响应修改信息
	function dialogFromHandler(obj, dlgid)　{
		var reason = $("#dialogtextarea").val();
		var url = '${base}${baseAdminPath}/${site}/ajax/${channel}/change/1';
		ajaxPostSubmit(url, reason)
		getStatusDLG(dlgid).dialog('close');
	}
	
	function ajaxPostSubmit(url, reason) {
		$.post(url, {uid : getSelected(dg).uid, reason: reason}, function(result) {
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
				<td>讲座数：</td>
                <td><span>${lecturecount}</span></td>
                <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td>总期数：</td>
                <td><span>${count}</span></td>
			<tr>
		</table>
		[@a.search]
			<tr>
				<td><label for="keywords">关键字:</label></td>
                <td><input id="keywords" type="text" name="keywords" style="width:150px;"/>&nbsp;&nbsp;&nbsp;&nbsp;</td> 
                <td><label for="smajor">状态: </label></td>
                <td><select id="sstatus" name="status" data-options="panelHeight:200">
                    <option class="easyui-combobox" value="">全部</option>
                        <option value="">全部</option>
                        <option value="1">报名中</option>
                        <option value="10">报名未开始</option>
                        <option value="13">报名结束</option>
                        <option value="12">时间确定</option>
                	</select>
                </td>
			</tr>
			<tr >
				<td><label for="stime">开讲时间：</label></td>
                <td colspan="3">
	                <input id="stime" type="text" name="timestart" editable="false" class="easyui-datebox"/>
	                --
	                <input id="stime" type="text" name="timeend" editable="false" class="easyui-datebox"/>
                </td>
			</tr>
		[/@a.search]
		
		[@a.datagrid]
            <th data-options="field:'name'">讲座名称</th>
            <th data-options="field:'username'">演讲人名称</th>
            <th data-options="field:'timeStr'">开讲时间</th>
            <th data-options="field:'address'">讲座地点</th>
            <th data-options="field:'fee'">报名费用</th>
            <th data-options="field:'maxcount'">最大报名人数</th>
            <th data-options="field:'registercount'">已报名人数</th>
            <th data-options="field:'semester'">期数</th>
            <th data-options="field:'enterpriserStatusName'">状态</th>
            <th data-options="field:'createtimeStr'">创建时间</th>
        [/@a.datagrid]   
        [@a.toolbar canadd=false]
        	<a id="btnadd" href="" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="myButtonHandler(this, '${base}${baseAdminPath}/${site}/ajax/${channel}/addsemester')">增加下一期</a>
        	<a id="btnenable" href="" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="myButtonHandler(this, '${base}${baseAdminPath}/${site}/ajax/${channel}/status/1')">开始报名</a>
        	<a id="btndisable" href="" class="easyui-linkbutton" data-options="iconCls:'icon-no'" onclick="myButtonHandler(this, '${base}${baseAdminPath}/${site}/ajax/${channel}/status/13')">结束报名</a>
        	<a id="btntinfook" href="" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="myButtonHandler(this, '${base}${baseAdminPath}/${site}/ajax/${channel}/change/0')">确定信息</a>
        	<a id="btninfochange" href="" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="myButtonHandler(this, '${base}${baseAdminPath}/${site}/ajax/${channel}/change/1')">信息修改</a>
        [/@a.toolbar]
        [@a.tools /]
	</section>
	[@a.addsection canuploadfile=true]
		<tr>
            <td>讲座名称:</td>
            <td><input class="easyui-validatebox long" type="text" name="name" data-options="required:true"></input></td>
        </tr>
        <tr>
            <td>演讲人名称:</td>
            <td><input class="easyui-validatebox long" type="text" name="username" data-options="required:true"></input></td>
        </tr>
        <tr>
            <td>开讲时间:</td>
            <td><input class="easyui-datetimebox long" type="text" name="timeStr" data-options="required:true"></input></td>
        </tr>
        <tr>
            <td>讲座地点:</td>
            <td><input class="easyui-validatebox long" type="text" name="address" ></input></td>
        </tr>
        <tr>
            <td>报名费用:</td>
            <td><input class="easyui-numberbox long" type="text" name="fee" data-options="required:true"></input></td>
        </tr>
        <tr>
            <td>最大报名人数:</td>
            <td><input class="easyui-numberbox long" type="text" name="maxcount" data-options="required:true" value="99"></input></td>
        </tr>
        <tr>
            <td>上传文件:</td>
            <td><input class="easyui-filebox" type="file" name="filedata" ></td>
        </tr>
        <tr>
            <td>报名须知:</td>
            <td>
                [@a.ckeditor id="addckeditor" name="notice"/]
            </td>
        </tr>
	[/@a.addsection]
	
	[@a.editsection canuploadfile=true]
		<tr>
            <td>讲座名称:<input type="hidden" name="uid"/></td>
            <td><input class="easyui-validatebox long" type="text" name="name" data-options="required:true"></input></td>
        </tr>
        <tr>
            <td>演讲人名称:</td>
            <td><input class="easyui-validatebox long" type="text" name="username" ></input></td>
        </tr>
        <tr>
            <td>开讲时间:</td>
            <td><input class="easyui-datetimebox long" type="text" name="timeStr" data-options="required:true"></input></td>
        </tr>
        <tr>
            <td>讲座地点:</td>
            <td><input class="easyui-validatebox long" type="text" name="address" data-options="required:true"></input></td>
        </tr>
        <tr>
            <td>报名费用:</td>
            <td><input class="easyui-numberbox long" type="text" name="fee" data-options="required:true"></input></td>
        </tr>
        <tr>
            <td>最大报名人数:</td>
            <td><input class="easyui-numberbox long" type="text" name="maxcount" data-options="required:true"></input></td>
        </tr>
        <tr>
            <td>已报名人数:</td>
            <td><input class="easyui-validatebox long" type="text" name="registercount" data-options="required:true"></input></td>
        </tr>
        <tr>
            <td>学期:</td>
            <td><span name="semester"></span></td>
        </tr>
        <tr>
            <td>重新上传文件:</td>
            <td><input class="easyui-filebox" type="file" name="filedata" ></td>
        </tr>
        <tr>
            <td>报名须知:</td>
            <td>
                [@a.ckeditor name="notice"/]
            </td>
        </tr>
	[/@a.editsection]
	
	[@a.viewsection]
	    <tr>
            <td>讲座名称:</td><input id="viewuid" type="hidden" name="uid"/>
            <td><span set-key="name"></span></td>
        </tr>
        <tr>
            <td>演讲人名称:</td>
            <td><span set-key="username"></span></td>
        </tr>
        <tr>
            <td>讲座时间:</td>
            <td><span set-key="timeStr"></span></td>
        </tr>
        <tr>
            <td>讲座地点:</td>
            <td><span set-key="address"></span></td>
        </tr>
        <tr>
            <td>报名费用:</td>
            <td><span set-key="fee"></span></td>
        </tr>
        <tr>
            <td>最大报名人数:</td>
            <td><span set-key="maxcount"></span></td>
        </tr>
        <tr>
            <td>已报名人数:</td>
            <td><span set-key="registercount"></span></td>
        </tr>
        <tr>
            <td>学期:</td>
            <td><span set-key="semester"></span></td>
        </tr>
        <tr>
            <td>文件:</td>
            <td><a href="" id="adownload"><span set-key="filenameFix"></a></span></td>
        </tr>
        <tr>
            <td>本期创建时间:</td>
            <td><span set-key="createtimeStr"></span></td>
        </tr>
        <tr>
            <td>报名须知:</td>
            <td><span set-key="notice"></span></td>
        </tr>
    [/@a.viewsection]
	  
	  <form id="dialogfrom" class="easyui-dialog" style="width:500px; height:240px; padding:10px 20px; " title="修改讲座信息" buttons="#dlg-status-btns" data-options="closed:true" >
		<table>
			<tr>
	            <td>讲座名称:</td>
	            <td><span set-key="name"></span></td>
	        </tr>
	        <tr>
	            <td>演讲人名称:</td>
	            <td><span set-key="username"></span></td>
	        </tr>
	        <tr>
	            <td>讲座时间:</td>
	            <td><span set-key="timeStr"></span></td>
	        </tr>
	        <tr>
	            <td>讲座地点:</td>
	            <td><span set-key="address"></span></td>
	        </tr>
	        <tr>
	            <td>修改原因:</td>
	            <td><textarea style="height:100px; width:300px;" id="dialogtextarea" placeholder="修改原因会发送给本期所有报名者，请慎重填写" required></textarea></td>
	        </tr>       
		</table>
		<div id="dlg-status-btns">
			<a href="" class="easyui-linkbutton" iconCls="icon-ok" onclick="dialogFromHandler(this ,'#dialogfrom')">确定</a>
			<a href="" class="easyui-linkbutton" iconCls="icon-cancel" onclick="statusdlg_cancelHandler(this, '#dialogfrom')">取消</a>
		</div>
	</form>
</body>
</html>