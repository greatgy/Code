[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Admin.ftl" as a]
<html>
<head>
<title>招聘信息管理</title>
<script type="text/javascript">
<!--
//-->
</script>
</head>
<body>
	<section>
		[@a.search]
        	 <tr>
	        	<td>
	        		<label for="stype">来源类型: </label>
	        		<select id="stype" name="type">
					    <option value="">全部</option>
					    [#assign noticetype = enumstype?keys]
					    	[#list noticetype as item]
						    	<option value="${item}">${enumstype[item]}</option>
						    [/#list]
		            </select>
	        	</td>
        	</tr>
	        <tr>
	            <td><label for="scontent">内容：</label><input id="scontent" type="text" name="content" /></td>
	            <td><label for="sfromname">来源对象：</label><input id="sfromname" type="text" name="fromname" /></td>
	        </tr>	
        	<tr>
	            <td>状态: 
		            <label for="sall">全部</label><input id="sall" name="status" value="" type="radio" class="radio" />
		            <label for="senable">正常</label><input id="senable" name="status" value="1" type="radio" class="radio" />
		            <label for="sdisable">冻结</label><input id="sdisable" name="status" value="0" type="radio" class="radio" />
	            </td>
	        </tr>
	        <tr>
				<td>开始创建时间: <input id="timestart" name="createtimestart" type="text" data-options="editable:false" class="easyui-datebox" /></td>
				<td>结束创建时间: <input id="timeend" name="createtimeend" type="text" editable="false" class="easyui-datebox" /></td>
		 	</tr>
		[/@a.search]
		
		[@a.datagrid]
			<th data-options="field:'typeName',width:100">来源类型</th>
			<th data-options="field:'fromname',width:100">来源对象</th>
			<th data-options="field:'statusName',width:100">状态</th>
			<th data-options="field:'createtimeStr',width:200">创建时间</th>
        [/@a.datagrid]
        
        [@a.toolbar canedit=false canadd=false]
        	[@a.status /]
	    [/@a.toolbar]
	    
	    [@a.tools /]
	</section>
	
	[@a.viewsection]
        <tr colspan="2">
            <td>来源类型：</td>
            <td><span set-key="type"></span></td>
        </tr>
        <tr colspan="2">
            <td>来源对象：</td>
            <td><span set-key="fromname"></span></td>
        </tr>
        <tr colspan="2">
            <td>内容：</td>
            <td><span set-key="content"></span></td>
        </tr>
        <tr colspan="2">
            <td>状态：</td>
            <td><span set-key="status"></span></td>
        </tr>
        <tr colspan="2">
            <td>创建时间：</td>
            <td><span set-key="createtime"></span></td>
        </tr>
	[/@a.viewsection]
	
</body>
</html>