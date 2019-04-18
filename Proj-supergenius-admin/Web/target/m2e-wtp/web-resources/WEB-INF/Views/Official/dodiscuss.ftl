[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Admin.ftl" as a]
<html>
<head>
<title>评论互动管理</title>
<script type="text/javascript" src="${basejs}/ckeditor/ckeditor.js"></script>
<script type="text/javascript">

function freshRow(row, data, i) {
	if(row.reply != null && row.reply.length == 1) {
		row.reply = row.reply[0];
	}
}

function initDGData(row) {
	for(var i in row) {
		if(row[i].reply != null) {
			row[i].replyName = "已回复";
		} else {
			row[i].replyName = "未回复";
		}
	}
}
</script> 	
</head>
<body>
    <section>
        [@a.search]
            <tr>
                <td><label for="stitle">标题：</label></td>
                <td><input id="stitle" type="text" name="title" /></td>
                <td><label for="sstatus">状态：</label></td>
                <td>
                    <input type="radio" name="status" value=""/> 全部
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
        
        [@a.datagrid url="${base}${baseAdminPath}/${site}/ajax/${channel}/list/${type}"]
        	<th data-options="field:'fromname',width:100">评论者姓名</th>
        	<th data-options="field:'replyName',width:100">回复状态</th>
            <th data-options="field:'content',width:500">内容</th>
            <th data-options="field:'statusName',width:100">显示状态</th>
            <th data-options="field:'createtimeStr',width:150">发布日期</th>
        [/@a.datagrid]
        [@a.toolbar canadd=false nameedit="回复"]
            [@a.status/]
        [/@a.toolbar]
        [@a.tools /]
	</section>
	
	[@a.editsection canuploadfile=true]
		<tr>
            <td>评论者评论<input type="hidden" name="uid"/></td>
        </tr>
        <tr>
            <td>评论者姓名:</td>
            <td><span set-key="fromname"></span></td>
        </tr>
        <tr>
            <td>内容:</td>
            <td><span set-key="content"></span></td>
        </tr>
        <tr>
            <td>网站回复</td>
        <tr>
            <td>回复内容:</td>
            <td>[@a.ckeditor name="replycontent" /]</td>
        </tr>
	[/@a.editsection]
    
    [@a.viewsection]
        <tr>
            <td>评论者评论<input type="hidden" name="uid" /></td>
        </tr>
        <tr>
            <td>评论者姓名：<input type="hidden" name="fromuid" /></td>
            <td><span set-key="fromname"></span></td>
        </tr>
        <tr>
            <td>内容：</td>
            <td><span set-key="content"></span></td>
        </tr>
        <tr>
            <td>网站回复</td>
        </tr>
        <tr>
            <td>管理员姓名：</td>
            <td><span set-key="reply.fromname"></span></td>
        </tr>
        <tr>
            <td>回复内容：</td>
            <td><span set-key="reply.content"></span></td>
        </tr>
    [/@a.viewsection]
	
</body>
</html>
