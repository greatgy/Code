[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Admin.ftl" as a]
<html>
<head>
<title>用户发帖管理</title>
<script type="text/javascript" src="${basejs}/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
<!--
function initDGData(row) {
	for(var i in row) {
		if(row[i].istop) {
			row[i].istopName = "置顶";
		} else {
			row[i].istopName = "未置顶";
		}
	}
}
//-->
</script>
</head>
<body>
    <section>
        [@a.search]
            <tr>
                <td><label for="sname">名称：</label></td>
                <td><input id="sname" type="text" name="name" /></td>
                <td><label for="susername">学员姓名：</label></td>
                <td><input id="susernamee" type="text" name="username" /></td>
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
            <th data-options="field:'title',width:500">名称</th>
            <th data-options="field:'username',width:200">学员姓名</th>
            <th data-options="field:'istopName',width:100">是否置顶</th>
            <th data-options="field:'statusName',width:100">状态</th>
            <th data-options="field:'createtimeStr',width:250">发布日期</th>
        [/@a.datagrid]
        [@a.toolbar canadd=false canedit=false]
            [@a.status/]
            [@a.state nameenable="置顶" namedisable="取消置顶" statefield="istop" actionenable="${base}${baseAdminPath}/moral/ajax/${channel}/istop/1" actiondisable="${base}${baseAdminPath}/moral/ajax/${channel}/istop/0"/]
        [/@a.toolbar]
        [@a.tools /]
	</section>
    [@a.viewsection]
        <tr>
            <td>标题：</td>
            <td><span set-key="title"></span></td>
        </tr>
        <tr>
            <td>关键词：</td>
            <td><span set-key="keywords"></span></td>
        </tr>
         <tr>
           <td>配图：</td>
           <td>
                <img style="width:200px; height:200px" src="" set-format="${webimg}{0}" set-key="imglittle"/>
           </td>
        </tr>
        <tr>
            <td>内容：</td>
            <td><span set-key="content"></span></td>
        </tr>
        <tr>
            <td>点击数：</td>
            <td><span set-key="countclick"></span></td>
        </tr>
        <tr>
            <td>评论数：</td>
            <td><span set-key="countcomment"></span></td>
        </tr>
        <tr>
            <td>赞数：</td>
            <td><span set-key="countpraise"></span></td>
        </tr>
    [/@a.viewsection]
</body>
</html>
