[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Admin.ftl" as a]
<html>
<head>
<title>团队管理</title>
<script type="text/javascript">
<!--
//-->
</script>
</head>
<body>
	<section>
		[@a.search]
	        <tr>
	            <td><label for="sname">名称：</label><input id="sname" type="text" name="name" /></td>
	            <td><label for="susername">创建人：</label><input id="susername" type="text" name="username" /></td>
	        </tr>	
	        <tr>
	        	<td>
	        		<label for="stype">版块: </label>
	        		<select id="stype" name="type">
					    <option value="">全部</option>
					    [#list type as item]
					    	<option value="${item.name}">${item.name}</option>
					    [/#list]
		            </select>
	        	</td>
        	</tr>
        	<tr>
	            <td>状态: 
		            <label for="sall">全部</label><input id="sall" name="status" value="" type="radio" class="radio" />
		            <label for="senable">正常</label><input id="senable" name="status" value="1" type="radio" class="radio" />
		            <label for="sdisable">冻结</label><input id="sdisable" name="status" value="0" type="radio" class="radio" />
	            </td>
	        </tr>
	        <tr>
        		<td>进度: 
	        		<label for="sstage">全部</label><input id="sstage" name="stage" value="" type="radio" class="radio" />
		            <label for="sinit">创建中</label><input id="sinit" name="stage" value="0" type="radio" class="radio" />
		            <label for="ssuccess">创建成功</label><input id="ssuccess" name="stage" value="1" type="radio" class="radio" />
        		</td>
        	</tr>
        	<tr>
	        	<td>置顶: 
	        		<label for="sistopall">全部</label><input id="sistopall" name="istop" value="" type="radio" class="radio" />
		            <label for="sistop">置顶</label><input id="sistop" name="istop" value="true" type="radio" class="radio" />
		            <label for="suntop">未置顶</label><input id="suntop" name="istop" value="false" type="radio" class="radio" />
	        	</td>
            </tr>
	        <tr>
				<td>开始创建时间: <input id="timestart" name="createtimestart" type="text" data-options="editable:false" class="easyui-datebox" /></td>
				<td>结束创建时间: <input id="timeend" name="createtimeend" type="text" editable="false" class="easyui-datebox" /></td>
		 	</tr>
		[/@a.search]
		
		[@a.datagrid]
			<th data-options="field:'sn',width:100">编号</th>
			<th data-options="field:'name',width:100">名称</th>
			<th data-options="field:'typeName',width:100">类型</th>
			<th data-options="field:'num',width:50">成员数</th>
			<th data-options="field:'username',width:100">创建人</th>
			<th data-options="field:'statusName',width:100">状态</th>
			<th data-options="field:'createtimeStr',width:200">创建时间</th>
        [/@a.datagrid]
        
        [@a.toolbar canedit=false canadd=false]
        	[@a.status /]
	    	[@a.state /]
	    	[@a.state statefield='isrecommend' nameenable="推荐到首页" namedisable="取消推荐" actionenable="${base}${baseAdminPath}/ajax/${channel}/isrecommend" actiondisable="${base}${baseAdminPath}/ajax/${channel}/unrecommend"/]
	    	
	    [/@a.toolbar]
	    
	    [@a.tools /]
	</section>
	
	[@a.viewsection]
        <tr colspan="2">
            <td>编号：</td>
            <td><span set-key="sn"></span></td>
        </tr>
        <tr colspan="2">
            <td>名称：</td>
            <td><span set-key="name"></span></td>
        </tr>
        <tr colspan="2">
            <td>类型：</td>
            <td><span set-key="type"></span></td>
        </tr>
        <tr colspan="2">
            <td>成员：</td>
            <td></td>
        </tr>
        <tr colspan="2">
            <td>创建时间：</td>
            <td><span set-key="createtimeStr"></span></td>
        </tr>
        <tr colspan="2">
            <td>创建人：</td>
            <td><span set-key="username"></span></td>
        </tr>
        <tr colspan="2">
            <td>进度：</td>
            <td><span set-key="stage"></span></td>
        </tr>
        <tr colspan="2">
            <td>状态：</td>
            <td><span set-key="status"></span></td>
        </tr>
        <tr colspan="2">
            <td>团队简介：</td>
            <td><span set-key="summary"></span></td>
        </tr>
        <tr colspan="2">
            <td>团队宣言：</td>
            <td><span set-key="declaration"></span></td>
        </tr>
	[/@a.viewsection]
	
</body>
</html>