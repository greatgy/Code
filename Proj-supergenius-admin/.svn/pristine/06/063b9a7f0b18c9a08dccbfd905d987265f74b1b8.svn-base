[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Admin.ftl" as a]
<html>
<head>
<title>权限管理</title>
<link rel="stylesheet" type="text/css" href="${basecss}/css/jquery/ztree/ztree.css" media="screen" />
<script type="text/javascript" src="${basejs}/js/jquery/plugins/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript">
<!--
[#include "/WEB-INF/Views/Admin/doauthority.ftl" /]
function getFreshRowUrl(dg) {
	var row = getSelected(dg);
	if(row != null) {
		return "${base}${baseAdminPath}/ajax/authority/list/{0}".format(row.uid);
	} else {
		return null;
	}
}
function freshRow(row, data, i) {
	var authorities = new Array();
	for(var i = 0; i < data.length; i++) {
		authorities.push(data[i].url);
	}
	row.authorities = authorities;
}
//-->
</script>
</head>
<body>
	<section>
		[@a.search]
	        <tr>
	            <td><label for="sroleid">角色ID</label></td>
	            <td><input id="sroleid" type="text" name="roleid" /></td>
	            <td><label for="sname">角色名称</label></td>
	            <td><input id="sname" type="text" name="name" /></td>
	        </tr>
	        <tr>
	            <td>状态</td>
	            <td colspan="3">
	            <label for="sall">全部</label><input id="sall" name="status" value="" type="radio" class="radio" />
	            <label for="senable">正常</label><input id="senable" name="status" value="1" type="radio" class="radio" />
	            <label for="sdisable">冻结</label><input id="sdisable" name="status" value="0" type="radio" class="radio" />
	            </td>
	        </tr>
		[/@a.search]
		
		[@a.datagrid]
			<th data-options="field:'roleid',sortable:'true',width:20">角色ID</th>
            <th data-options="field:'name',width:20">角色名称</th>
            <th data-options="field:'statusName',width:20">状态</th>
            <th data-options="field:'updatetimeStr',sortable:'true',width:20">上次修改时间</th>
            <th data-options="field:'createtimeStr',sortable:'true',width:20">创建时间</th>
	    [/@a.datagrid]
	    
	    [@a.toolbar]
	    	[@a.status /]
	    [/@a.toolbar]
	    [@a.tools /]
	    
	</section>
	
	[@a.addsection]
    	<tr>
            <td>角色ID</td>
            <td><input class="easyui-validatebox" type="text" name="roleid" data-options="required:true" /></td>
        </tr>
        <tr>
            <td>角色名称</td>
            <td><input class="easyui-validatebox" type="text" name="rolename" data-options="required:true" /></td>
        </tr>
        <tr>
            <td>描述</td>
            <td><textarea class="easyui-validatebox" style="width:350px; height:60px;" name="desc" data-options="required:true" ></textarea></td>
        </tr>
        <tr>
            <td>分配权限</td>
            <td>[@a.tree name="authorities"/]</td>
        </tr>
    [/@a.addsection]
	
	[@a.editsection]
		<tr>
            <td>角色ID<input type="hidden" name="uid" /></td>
            <td><span set-key="roleid"></span><input type="hidden" name="roleid" /></td>
        </tr>
        <tr>
            <td>角色名称</td>
            <td><input class="easyui-validatebox" type="text" name="name" data-options="required:true" /></td>
        </tr>
        <tr>
            <td>描述</td>
            <td><textarea class="easyui-validatebox" style="width:350px; height:60px;" name="desc" data-options="required:true" ></textarea></td>
        </tr>
        <tr>
            <td>分配权限</td>
            <td>[@a.tree name="authorities"/]</td>
        </tr>
	[/@a.editsection]
	
	[@a.viewsection]
		<tr>
            <td>角色ID</td>
            <td><span set-key="roleid"></span></td>
        </tr>
        <tr>
            <td>角色名称</td>
            <td><span set-key="name"></span></td>
        </tr>
        <tr>
            <td>描述</td>
            <td><span set-key="desc"></span></td>
        </tr>
        <tr>
            <td>分配权限</td>
            <td>[@a.tree name="authorities" /]</td>
        </tr>
	[/@a.viewsection]
	
</body>
</html>