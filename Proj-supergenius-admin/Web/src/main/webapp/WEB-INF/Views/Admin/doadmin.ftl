[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Admin.ftl" as a]
[#assign channel = "admin" /]
[#assign channeloid = "admin" /]
<html>
<head>
<title>管理员管理</title>
<script type="text/javascript">
<!--
function getFreshRowUrl(dg) {
	var row = getSelected(dg);
	if(row != null) {
		return "${base}${baseAdminPath}/ajax/role/list/{0}".format(row.uid);
	} else {
		return null;
	}
}
function freshRow(row, data, i) {
	var roles = new Array();
	for(var i = 0; i < data.length; i++) {
		roles.push(data[i].roleid);
	}
	row.roles = roles;
}

//-->
</script>
</head>
<body>
	<section>
		[@a.search]
	        <tr>
	            <td><label for="sadminid">管理员ID</label></td>
	            <td><input id="sadminid" type="text" name="adminid" /></td>
	            <td><label for="sname">管理员姓名</label></td>
	            <td><input id="sname" type="text" name="name" /></td>
	            <td><label for="semail">邮箱</label></td>
	            <td><input id="semail" type="text" name="email" /></td>
	        </tr>
	        <tr>
	            <td><label for="smobile">手机</label></td>
	            <td><input id="smobile" type="text" name="mobile" /></td>
	            <td>状态</td>
	            <td colspan="3">
	            <label for="sall">全部</label><input id="sall" name="status" value="" type="radio" class="radio" />
	            <label for="senable">正常</label><input id="senable" name="status" value="1" type="radio" class="radio" />
	            <label for="sdisable">冻结</label><input id="sdisable" name="status" value="0" type="radio" class="radio" />
	            </td>
	        </tr>
		[/@a.search]
		
		[@a.datagrid]
			<th data-options="field:'adminid',sortable:'true',width:20">管理员ID</th>
            <th data-options="field:'name',width:20">姓名</th>
            <th data-options="field:'email',width:30">邮箱</th>
            <th data-options="field:'mobile',width:20">手机</th>
            <th data-options="field:'statusName',width:20">状态</th>
            <th data-options="field:'lastlogintime',sortable:'true',width:20">上次登录时间</th>
            <th data-options="field:'createtimeStr',sortable:'true',width:20">创建时间</th>
	    [/@a.datagrid]
	    
	    [@a.toolbar]
	    	[@a.status /]
	    [/@a.toolbar]
	    [@a.tools /]
	    
	</section>
	
	[@a.addsection]
    	<tr>
            <td>管理员ID</td>
            <td><input class="easyui-validatebox" type="text" name="adminid" data-options="required:true" /></td>
        </tr>
        <tr>
            <td>管理员姓名</td>
            <td><input class="easyui-validatebox" type="text" name="name" data-options="required:true" /></td>
        </tr>
        <tr>
            <td>登录密码</td>
            <td><input class="easyui-validatebox" type="password" name="pwd" data-options="required:true" /></td>
        </tr>
        <tr>
            <td>操作密码</td>
            <td><input class="easyui-validatebox" type="password" name="dopwd" data-options="required:true" /></td>
        </tr>
        <tr>
            <td>邮箱</td>
            <td><input class="easyui-validatebox" type="text" name="email" data-options="required:true" /></td>
        </tr>
        <tr>
            <td>手机</td>
            <td><input class="easyui-validatebox" type="text" name="mobile" data-options="required:true" /></td>
        </tr>
        <tr>
            <td>分配角色</td>
            <td><input class="easyui-combobox" name="roles" url="${base}${baseAdminPath}/ajax/role/all" valueField="roleid" textField="name" data-options="width:400,height:30,editable:false,method:'get',multiple:true,panelHeight:'auto'" /></td>
        </tr>
    [/@a.addsection]
	
	[@a.editsection]
		<tr>
            <td>管理员ID<input type="hidden" name="uid" /></td>
            <td><span set-key="adminid"></span></td>
        </tr>
        <tr>
            <td>管理员姓名</td>
            <td><input class="easyui-validatebox" type="text" name="name" data-options="required:true" /></td>
        </tr>
        <tr>
            <td>邮箱</td>
            <td><input class="easyui-validatebox" type="text" name="email" data-options="required:true" /></td>
        </tr>
        <tr>
            <td>手机</td>
            <td><input class="easyui-validatebox" type="text" name="mobile" data-options="required:true" /></td>
        </tr>
        <tr>
            <td>分配角色</td>
            <td><input class="easyui-combobox" name="roles" url="${base}${baseAdminPath}/ajax/role/all" valueField="roleid" textField="name" data-options="width:400,height:30,editable:false,method:'get',multiple:true,panelHeight:'auto'" /></td>
        </tr>
	[/@a.editsection]
	
	[@a.viewsection]
		<tr>
            <td>管理员ID</td>
            <td><span set-key="adminid"></span></td>
        </tr>
        <tr>
            <td>管理员姓名</td>
            <td><span set-key="name"></span></td>
        </tr>
        <tr>
            <td>邮箱</td>
            <td><span set-key="email"></span></td>
        </tr>
        <tr>
            <td>手机</td>
            <td><span set-key="mobile"></span></td>
        </tr>
        <tr>
            <td>角色</td>
            <td><input id="combobox" class="easyui-combobox" name="roles" url="${base}${baseAdminPath}/ajax/role/all" valueField="roleid" textField="name" data-options="width:400,height:30,editable:false,method:'get',multiple:true,panelHeight:'auto'" /></td>
        </tr>
	[/@a.viewsection]
	
</body>
</html>