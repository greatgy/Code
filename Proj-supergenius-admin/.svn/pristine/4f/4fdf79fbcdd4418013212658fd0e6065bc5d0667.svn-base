[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Admin.ftl" as a]
<html>
<head>
<title>考试管理</title>
<script type="text/javascript" src="${basejs}/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
</script> 	
</head>
<body>
    <section>
        [@a.search]
            <tr>
                <td><label for="sname">名称：</label></td>
                <td><input id="sname" type="text" name="name" /></td>
                <td><label for="susername">学员姓名：</label></td>
                <td><input id="susername" type="text" name="username" /></td>
            </tr>
            <tr>
                <td>起始发布时间：</td>
                <td><input id="timestart" name="createtimestart" type="text" data-options="editable:false" class="easyui-datebox" /></td>
                <td>结束发布时间：</td>
                <td><input id="timeend" name="createtimeend" type="text" data-options="editable:false" class="easyui-datebox" /></td>
            </tr>
        [/@a.search]
        
        [@a.datagrid]
            <th data-options="field:'username',width:100">姓名</th>
            <th data-options="field:'stateName',width:100">状态</th>
            <th data-options="field:'score',width:100">成绩</th>
            <th data-options="field:'begintimeStr',width:200">开始时间</th>
            <th data-options="field:'finishtimeStr',width:200">结束时间</th>
        [/@a.datagrid]
        [@a.toolbar canadd=false candel=false candel=false canedit=false]
        [/@a.toolbar]
        [@a.tools /]
	</section>
    
    [@a.viewsection]
        <tr>
            <td>学员姓名：</td>
            <td><span set-key="username"></span></td>
        </tr>
        <tr>
            <td>成绩：</td>
            <td><span set-key="score"></span></td>
        </tr>
        <tr>
            <td>开始时间：</td>
            <td><span set-key="begintimeStr"></span></td>
        </tr>
        <tr>
            <td>结束时间：</td>
            <td><span set-key="finishtimeStr"></span></td>
        </tr>
    [/@a.viewsection]
	
</body>
</html>
