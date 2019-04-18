[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Admin.ftl" as a]
<html>
<head>
<title>评论管理</title>
<script type="text/javascript">
<!--
//-->
</script>
</head>
<body>
	<section>
        [@a.search]
            <tr>
                <td><label for="sfromusername">评论人姓名：</label></td>
                <td><input id="sfromusername" type="text" name="fromusername" /></td>
                <td><label for="scontent">内容关键字：</label></td>
                <td><input id="scontent" type="text" name="content" /></td>
            </tr>
            <tr>
                <td>起始发布时间：</td>
                <td><input id="timestart" name="createtimestart" type="text" data-options="editable:false" class="easyui-datebox" /></td>
                <td>结束发布时间：</td>
                <td><input id="timeend" name="createtimeend" type="text" data-options="editable:false" class="easyui-datebox" /></td>
            </tr>
            <tr>
                <td><label for="susername">状态：</label></td>
                <td>
                    <input type="radio" name="status" value=""/>全部
                    <input type="radio" name="status" value="1"/>正常
                    <input type="radio" name="status" value="0"/>冻结
                </td>
            </tr>
            <tr>
                <td><label for="sfromtitle">评论的文章：</label></td>
                <td><input id="sfromtitle" type="text" name="fromtitle" /></td>
            </tr>
        [/@a.search]
        
        [@a.datagrid]
            <th data-options="field:'channelName',width:150">类型</th>
            <th data-options="field:'fromtitle',width:300">来源</th>
            <th data-options="field:'fromusername',width:100" >评论人</th>
            <th data-options="field:'content',width:300">评论内容</th>
            <th data-options="field:'statusName',width:50">状态</th>
            <th data-options="field:'createtimeStr',width:175">评论时间</th>
        [/@a.datagrid]
        [@a.toolbar canadd=false canedit=false]
            [@a.status /]
        [/@a.toolbar]
        [@a.tools /]
	</section>
	
    [@a.viewsection]
        <tr>
            <td>评论人姓名：</td>
            <td><span set-key="fromusername"></span></td>
        </tr>
        <tr>
            <td>评论的文章：</td>
            <td><span set-key="fromtitle"></span></td>
        </tr>
        <tr>
            <td>评论内容：</td>
            <td><span set-key="content"></span></td>
        </tr>
       <tr>
            <td>状态 ：</td>
            <td><span set-key="statusName"></span></td>
       </tr> 
       <tr>
            <td>评论时间 ：</td>
            <td><span set-key="createtimeStr"></span></td>
       </tr> 
    [/@a.viewsection]
    
</body>
</html>
