[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Admin.ftl" as a]
<html>
<head>
<title>学员分享管理</title>
<script type="text/javascript" src="${basejs}/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
	<!--
	function initDGData(row) {
		for(var i in row) {
			row[i].attachment = '<a href=\'${basefile}'+row[i].file+'\'>点击下载</a>';
		}
	}
	-->
</script>
</head>
<body>
    <section>       
        [@a.search]
            <tr>
                <td><label for="sname">名称：</label></td>
                <td><input id="sname" type="text" name="name" /></td>
            </tr>
             <tr>
	        	<td><label for="sstatus">状态：</label></td>
                <td>
                    <input type="radio" name="status" value=""/>全部
                    <input type="radio" name="status" value="1"/>正常
                    <input type="radio" name="status" value="0"/>冻结
                </td>
        	</tr>
            <tr>
                <td>起始发布时间：</td>
                <td><input id="timestart" name="createtimestart" type="text" data-options="editable:false" class="easyui-datebox" /></td>
                <td>结束发布时间：</td>
                <td><input id="timeend" name="createtimeend" type="text" data-options="editable:false" class="easyui-datebox" /></td>
            </tr>
        [/@a.search]
        
        [@a.datagrid]
            <th data-options="field:'name',width:500">名称</th>
            <th data-options="field:'username',width:500">学员姓名</th>
            <th data-options="field:'countdl',width:200">下载量</th>
            <th data-options="field:'createtimeStr',width:300">上传时间</th>
            <th data-options="field:'statusName',width:75">状态</th>
            <th data-options="field:'attachment',width:150">下载分享</th>
        [/@a.datagrid]
        
        [@a.toolbar canadd=false canview=false nameedit="加入到案例库"]
            [@a.status /]
        [/@a.toolbar]
        [@a.tools/]
    </section>
    
    [@a.editsection canuploadfile=true]
		<tr>
            <td>标题:<input type="hidden" name="uid"/></td>
            <td><span set-key="name"></span></td>
        </tr>
        <tr>
            <td>学员姓名:</td>
            <td><span set-key="username"></span></td>
        </tr>
        <tr>
            <td>下载量:</td>
            <td><span set-key="countdl"></span></td>
        </tr>
        <tr>
            <td>上传时间:</td>
            <td><span set-key="createtimeStr"></span></td>
        </tr>
        <tr>
        	<td></td>
            <td>确认加入到案例中，请点击提交。</td>
        </tr>
	[/@a.editsection]
</body>