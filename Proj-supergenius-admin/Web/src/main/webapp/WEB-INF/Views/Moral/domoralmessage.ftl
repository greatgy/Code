[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Admin.ftl" as a]
<html>
<head>
<title>系统消息管理</title>
<script type="text/javascript" src="${basejs}/ckeditor/ckeditor.js"></script>
</head>
<body>
    <section>       
        [@a.datagrid]
            <th data-options="field:'sn',width:200">编号</th>
            <th data-options="field:'title',width:200">消息标题</th>
            <th data-options="field:'content',width:400">消息内容</th>
            <th data-options="field:'fromusername',width:200">发送人</th>
            <th data-options="field:'createtimeStr',width:275">发送时间</th>
        [/@a.datagrid]
        
        [@a.toolbar nameadd="发送消息" canedit=false]
        [/@a.toolbar]
        	
        [@a.tools/]
    </section>
    
    [@a.addsection]
        <tr>
            <td>消息标题：</td>
            <td><input class="easyui-validatebox" type="text" name="title" data-options="required:true" /></td>
        </tr>
        <tr>
	       <td>系统消息内容:</td>
	       <td>[@a.ckeditor name="content"/]</td>
        </tr>
        <tr>
            <td>发送给：</td>
            <td>
            	<input type="radio" id="rdwho_0" name="who" value="all" checked="checked"/> <label for="rdwho_0">所有人</label>&nbsp;
            	<input type="radio" id="rdwho_1" name="who" value="user"/> <label for="rdwho_1">会员</label>&nbsp;
            	<input type="radio" id="rdwho_2" name="who" value="studentMoral"/> <label for="rdwho_2">学员</label>&nbsp;
            </td>
        </tr>
    [/@a.addsection]
    
    [@a.viewsection]
       <tr>
       		<td>编号：</td>
       		<td><span set-key="sn"></span></td>
       </tr>
       <tr>
       		<td>消息标题：</td>
       		<td><span set-key="title"></span></td>
       </tr>
       <tr>
       		<td>消息内容：</td>
       		<td><span set-key="content"></span></td>
       </tr>
       <tr>
       		<td>发送人：</td>
       		<td><span set-key="fromusername"></span></td>
       </tr>
       <tr>
       		<td>发送时间：</td>
       		<td><span set-key="createtimeStr"></span></td>
       </tr>
    [/@a.viewsection]
    
</body>