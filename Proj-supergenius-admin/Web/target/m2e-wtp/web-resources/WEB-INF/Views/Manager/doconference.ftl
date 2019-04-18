[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Admin.ftl" as a]
[#import "/WEB-INF/FtlLib/Macro/User.ftl" as u]
<html>
<head>
<title>会议室管理</title>
<script type="text/javascript" src="${basejs}/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
	function initDGData(row) {
		for (var i in row) {
			if (row[i].status == 0) {
				row[i].statusName = "已删除";
			} else if (row[i].status == 1) {
			    row[i].statusName = "进行中";
			} else if (row[i].status == 10) {
			    row[i].statusName = "未开始";
			} else if (row[i].status == 13) {
			    row[i].statusName = "已关闭";
			}
		}
	}
	
	//按钮显示
	function mytoolbarStateHandler(event, j) {
		var list = getSelections(j.dg);
		if (list.length ==0 ) {
			$("#btnend").linkbutton("disable");
			$("#btnbegin").linkbutton("disable");
			$("#btnadduser").linkbutton("disable");
		} else{
			var row = list[0];
			if (row.status == 1) {//已进行中
				$("#btnend").linkbutton("enable");
				$("#btnadduser").linkbutton("enable");
			} else if (row.status == 13 || row.status == 10) {//已结束 和 未开始
				$("#btnbegin").linkbutton("enable");
				$("#btnadduser").linkbutton("enable");
			} else if (row.status == 0) {//已删除
				getBtnEdit().linkbutton("disable");
				getBtnDel().linkbutton("disable");
				$("#btnend").linkbutton("disable");
				$("#btnbegin").linkbutton("disable");
				$("#btnadduser").linkbutton("disable");
			}
		}
	}
	
	//按钮处理
	function myButtonHandler(obj, url) {
		var objid = $(obj).attr("id");
		if (objid == "btnend") {
			confirmAjax('会议室操作', '您确定要关闭当前会议室吗？', url);
		} else if (objid == "btnbegin") {
			confirmAjax('会议室操作', '您确定要开启当前会议室吗？', url);
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
				<td>会议室总数：</td>
                <td><span>${total}</span></td>
                <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td>挑战：</td>
                <td><span>${pk}</span></td>
                <td>答辩：</td>
                <td><span>${reply}</span></td>
                <td>裁判：</td>
                <td><span>${judge}</span></td>
                <td>专家：</td>
                <td><span>${expert}</span></td>
                <td>其他：</td>
                <td><span>${other}</span></td>
			<tr>
		</table>
		[@a.search]
		     <tr>
		     	<td><label for="skeywords">关键字:</label></td>
                <td><input id="skeywords" type="text" name="keywords" style="width:150px;"/></td>
                <td><label for="sstatus">状态:</label></td>
                <td><select id="sstatus" name="status" data-options="panelHeight:200">
                    <option class="easyui-combobox" value="">全部</option>
                        <option value="">全部</option>
                        <option value="10">未开始</option>
                        <option value="1">进行中</option>
                        <option value="13">已关闭</option>
                        <option value="0">已删除</option>
                	</select>
                </td>
                <td><label for="stype">会议室类型:</label></td>
                <td><select id="stype" name="type" data-options="panelHeight:200">
                    <option class="easyui-combobox" value="">全部</option>
                        [#list conferences?keys as item]
                            <option value="${item}">${conferences[item]}</option>
                        [/#list]
                	</select>
                </td>
			</tr> 
			<tr >
				<td><label for="screatetime">创建时间：</label></td>
                <td colspan="3">
	                <input id="screatetime" type="text" name="createtimestart" editable="false" class="easyui-datebox"/>
	                --
	                <input id="screatetime" type="text" name="createtimeend" editable="false" class="easyui-datebox"/>
                </td>
			</tr>
		[/@a.search]
		[@a.datagrid]
			<th data-options="field:'sn'">编号</th>
            <th data-options="field:'name',width:100">名称</th>
            <th data-options="field:'typeName'">类型</th>
            <th data-options="field:'statusName'">状态</th>
            <th data-options="field:'begintimeStr'">开始时间</th>
            <th data-options="field:'endtimeStr'">关闭时间</th>
            <th data-options="field:'createtimeStr'">创建时间</th>
		[/@a.datagrid]
		[@a.toolbar canadd=false ]
			<a id="btnend" href="" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="myButtonHandler(this, '${base}${baseAdminPath}/${site}/ajax/${channel}/time/0')">关闭会议室</a>
			<a id="btnbegin" href="" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="myButtonHandler(this, '${base}${baseAdminPath}/${site}/ajax/${channel}/time/1')">开启会议室</a>
			<a id="btnadduser" href="" class="easyui-linkbutton" data-options="iconCls:'icon-add'"  data-crumbshow="{selector:'#adduser', title:'邀请会议成员'}" onclick="editHandler(this, null, '#adduser')">邀请</a>
        [/@a.toolbar]
        [@a.tools /]
      </section>
      
      [@a.editsection]
          <input type="hidden" name="uid">
          <tr>
            <td>会议室名称:</td>
            <td><input class="easyui-validatebox" data-options="required:true" name="name" /></td>
          </tr>
          <tr>
            <td>开始时间:</td>
            <td><input class="easyui-datetimebox" data-options="required:true" name="begintimeStr" /></td>
          </tr>
          <tr>
            <td>结束时间:</td>
            <td><input class="easyui-datetimebox" data-options="required:true" name="endtimeStr" /></td>
          </tr>
          <tr>
            <td>最大与会人数:</td>
            <td><input class="easyui-numberbox" data-options="required:true" name="maxcountuser" /></td>
          </tr>
          <tr>
            <td>最大游客人数:</td>
            <td><input class="easyui-numberbox" data-options="required:true" name="maxcounttourist" /></td>
          </tr>
          <tr>
            <td>最大主席人数:</td>
            <td><input class="easyui-numberbox" data-options="required:true"  name="maxcountspokesman" /></td>
          </tr>
          <tr>
             <td>会议室密码:</td>
            <td><input class="easyui-validatebox" data-options="required:true"  name="password" /></td>
          </tr>
          <tr>
             <td>会议室管理密码:</td>
            <td><input class="easyui-validatebox" data-options="required:true"  name="passwordadmin" /></td>
          </tr>
          <tr>
             <td>会议描述:</td>
            <td>
            	<textarea class="easyui-validatebox" name="description" cols="50" rows="10"></textarea>
            </td>
          </tr>
       [/@a.editsection]
      
      [@a.editsection id="adduser" namesubmit="邀请" action="${base}${baseAdminPath}/${site}/ajax/${channel}/adduser"]
      	  <input type="hidden" name="uid">
      	  <tr>
            <td>选择对象类型:</td>
            <td><input type="radio" name="type" checked="true" value="0" />成员</td>
            <td><input type="radio" name="type" value="1" />管理员</td>
          </tr>
      	  <tr>
            <td>选择邀请对象：</td>
            <td colspan="3">[@u.usersearch name="useruids"/]</td>
           </tr>
      [/@a.editsection]
      
       [@a.viewsection]
	    <tr>
            <td>编号:</td>
            <td><span set-key="sn"></span></td>
        </tr>
        <tr>
            <td>名称:</td>
            <td><span set-key="name"></span></td>
        </tr>
        <tr>
            <td>相关事件:</td>
            <td><span set-key="typename"></span></td>
        </tr>
        <tr>
            <td>密码:</td>
            <td><span set-key="password"></span></td>
        </tr>
        <tr>
            <td>管理密码:</td>
            <td><span set-key="passwordadmin"></span></td>
        </tr>
        <tr>
            <td>开始时间:</td>
            <td><span set-key="begintimeStr"></span></td>
        </tr>
        <tr>
            <td>关闭时间:</td>
            <td><span set-key="endtimeStr"></span></td>
        </tr>
        <tr>
            <td>最大与会人数:</td>
            <td><span set-key="maxcountuser"></span></td>
        </tr>
        <tr>
            <td>最大观众人数:</td>
            <td><span set-key="maxcounttourist"></span></td>
        </tr>
        <tr>
            <td>最大主席人数:</td>
            <td><span set-key="maxcountspokesman"></span></td>
        </tr>
        <tr>
            <td>预期与会人数:</td>
            <td><span set-key="expectjoincount"></span></td>
        </tr>
        <tr>
            <td>实际与会人数:</td>
            <td><span set-key="realjoincount"></span></td>
        </tr>
        <tr>
            <td>会议描述:</td>
            <td><span set-key="description"></span></td>
        </tr>
        <tr>
            <td>会议室类型:</td>
            <td><span set-key="typeName"></span></td>
        </tr>
        <tr>
            <td>会议室状态:</td>
            <td><span set-key="statusName"></span></td>
        </tr>
        <tr>
            <td>发布时间:</td>
            <td><span set-key="createtimeStr"></span></td>
        </tr>
    [/@a.viewsection]
</body>
<html>
		
