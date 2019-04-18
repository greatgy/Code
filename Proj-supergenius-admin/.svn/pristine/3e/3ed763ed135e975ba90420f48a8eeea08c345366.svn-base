[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Admin.ftl" as a]
<html>
<head>
<title>团队/项目类别管理</title>
<script type="text/javascript">
<!--
//-->
</script>
</head>
<body>
	<section>
		[@a.search]
	        <tr>
	            <td><label for="sname">分类名</label><input id="sname" type="text" name="name" /></td>
	            <td>
	        		<label for="stype">所属类别: </label>
	        		<select id="stype" name="type">
					    <option value="">全部</option>
					    [#assign type = enumstype?keys]
					    	[#list type as item]
						    	<option value="${item}">${enumstype[item]}</option>
						    [/#list]
		            </select>
	        	</td>
	        </tr>
		[/@a.search]
		
		[@a.datagrid title="团队/项目类别列表"]
			<th data-options="field:'name',width:20">分类名</th>
			<th data-options="field:'typeName',width:20">所属分类</th>
			<th data-options="field:'typecode',width:20">分类代码</th>
			<th data-options="field:'createtimeStr',width:20">添加时间</th>
        [/@a.datagrid]
        
        [@a.toolbar candel=false]
	    [/@a.toolbar]
	    
	    [@a.tools /]
	</section>
	
	[@a.addsection canuploadfile=true]
		<tr>
			<td>所属分类: 
	            <label for="stypeteam">团队</label><input id="stypeteam" name="type" value="0" type="radio" class="radio" />
	            <label for="stypeproject">项目</label><input id="stypeproject" name="type" value="1" type="radio" class="radio" />
	    	</td>
		</tr>
    	<tr>
            <td>标题：</td>
            <td colspan="3"><input class="easyui-validatebox" type="text" name="name" data-options="required:true" /></td>
        </tr>
        <tr>
            <td>分类代码：</td>
            <td colspan="3"><input class="easyui-validatebox" type="text" name="typecode" data-options="required:true" /></td>
        </tr>
        <tr>
            <td>分类简介：</td>
            <td colspan="3"><input class="easyui-validatebox" type="text" name="summary" data-options="required:true" /></td>
        </tr>
        <tr>
            <td>（团队）所需人数：</td>
            <td colspan="3"><input class="easyui-validatebox" type="text" name="membernum" replacelholder="团队所需人数" /></td>
        </tr>
        <tr>
            <td>标识图:</td>
            <td>[@a.imgupload multiple=false imgpath="/imgs/webdata/tpi/type" /]</td>
        </tr>
    [/@a.addsection]
    
    [@a.editsection canuploadfile=true]
    	<tr>
			<td>所属分类: 
	            <label for="stypeteam">团队</label><input id="stypeteam" name="type" value="0" type="radio" class="radio" />
	            <label for="stypeproject">项目</label><input id="stypeproject" name="type" value="1" type="radio" class="radio" />
	    	</td>
		</tr>
		<tr>
            <td>标题:<input type="hidden" name="uid"/></td>
            <td><input class="easyui-validatebox long" type="text" name="name" data-options="required:true"></input></td>
        </tr>
        <tr>
            <td>分类代码:</td>
            <td><input class="easyui-validatebox" type="text" name="typecode" data-options="required:true"></input></td>
        </tr>
         <tr>
            <td>分类简介:</td>
            <td><input class="easyui-validatebox" type="text" name="summary" data-options="required:true"></input></td>
        </tr>
         <tr>
            <td>（团队）所需人数:</td>
            <td><input class="easyui-validatebox" type="text" name="membernum"></input></td>
        </tr>
        <tr>
           <td>标识图:</td><td><img style="width:200px; height:200px" src="" set-format="${webimg}{0}" set-key="img"/>
           [@a.imgupload multiple=false imgpath="/imgs/webdata/tpi/type" /]</td>
        </tr>
	[/@a.editsection]
	
	[@a.viewsection]
        <tr colspan="2">
            <td>标题：</td>
            <td><span set-key="name"></span></td>
        </tr>
        <tr colspan="2">
            <td>所属分类：</td>
            <td><span set-key="type"></span></td>
        </tr>
        <tr colspan="2">
            <td>分类代码：</td>
            <td><span set-key="typecode"></span></td>
        </tr>
        <tr colspan="2">
            <td>简介	：</td>
            <td><span set-key="summary"></span></td>
        </tr>
        <tr colspan="2">
            <td>（团队）所需人数	：</td>
            <td><span set-key="membernum"></span></td>
        </tr>
        <tr colspan="2">
            <td>标识图：</td>
            <td><img src="" set-format="${webimg}{0}" set-key="img" ></td>
        </tr>
	[/@a.viewsection]
	
</body>
</html>