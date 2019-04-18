[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Admin.ftl" as a]
<html>
<head>
<title>[#if "${type}" == 1]并购方案[#else]投资计划[/#if]管理</title>
<script type="text/javascript" src="${basejs}/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
<!--
//-->
</script>
</head>
<body>
	<section>
        [@a.search]
        	<tr>
                <td><label for="stitle">目标项目：</label></td>
                <td><input id="stitle" type="text" name="title" /></td>
            </tr>
            <tr>
                <td><label for="sauthor">提交者：</label></td>
                <td><input id="sauthor" type="text" name="fromname" /></td>
            </tr>
            <tr>
                <td>起始发布时间：</td>
                <td><input id="timestart" name="createtimestart" type="text" data-options="editable:false" class="easyui-datebox" /></td>
                <td>结束发布时间：</td>
                <td><input id="timeend" name="createtimeend" type="text" data-options="editable:false" class="easyui-datebox" /></td>
            </tr>
        [/@a.search]
        
        [#assign title = "并购方案"/]
    	[#if "${type}" == "2"]
    		[#assign title = "投资计划"/]
    	[/#if]
        
        [@a.datagrid title="${title}列表" url="${base}${baseAdminPath}/ajax/${channel}/list/${type}"]
            <th data-options="field:'title',width:500">目标项目</th>
            <th data-options="field:'fromname',width:100">提交者</th>
            <th data-options="field:'createtimeStr',width:150">提交时间</th>
        [/@a.datagrid]
        
        [@a.toolbar canadd=false canedit=false candel=false]
        [/@a.toolbar]
        
        [@a.tools /]
	</section>
	
    [@a.viewsection]
        <tr>
            <td>目标项目：</td>
            <td><span set-key="title"></span></td>
        </tr>
        <tr>
            <td>提交者：</td>
            <td><span set-key="fromname"></span></td>
        </tr>
        <tr>
            <td>方案内容：</td>
            <td><span set-key="content"></span></td>
        </tr>
        <tr>
            <td>提交时间：</td>
            <td><span set-key="createtime"></span></td>
        </tr>
        
    [/@a.viewsection]
    
</body>
</html>
