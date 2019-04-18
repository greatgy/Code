[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Admin.ftl" as a]
<html>
<head>
<title>新闻管理</title>
<script type="text/javascript" src="${basejs}/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
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
        
        [@a.datagrid]
            <th data-options="field:'title',width:500">标题</th>
            <th data-options="field:'author',width:100">作者</th>
            <th data-options="field:'origin',width:200">来源</th>
            <th data-options="field:'statusName',width:100">状态</th>
            <th data-options="field:'createtimeStr',width:150">发布日期</th>
        [/@a.datagrid]
        [@a.toolbar]
            [@a.status/]
            [@a.state /]
        [/@a.toolbar]
        [@a.tools /]
	</section>
	[@a.addsection]
        <tr>
            <td>标题：</td>
            <td><input class="easyui-validatebox" type="text" name="title" data-options="required:true" /></td>
        </tr>
        <tr>
            <td>作者：</td>
            <td><input class="easyui-validatebox" type="text" name="author" data-options="required:true" /></td>
        </tr>
        <tr>
            <td>来源：</td>
            <td><input class="easyui-validatebox" type="text" name="origin" data-options="required:true" /></td>
        </tr>
        <tr>
            <td>关键词：</td>
            <td><input class="easyui-validatebox" type="text" name="keyword" data-options="required:true" /></td>
        </tr>
         <tr>
            <td>标题图：</td>
            <td>[@a.imgupload multiple=false imgpath="/imgs/webdata/official/news" /]</td>
        </tr>
        <tr>
            <td>内容：</td>
            <td>
                [@a.ckeditor name="content"/]
            </td>
        </tr>
    [/@a.addsection]
    
    [@a.editsection]
        <tr>
            <td><input type="hidden" name="uid" /></td>
            <td><input type="hidden" name="adminuid" /></td>
        </tr>
        <tr>
            <td>标题：</td>
            <td><input class="easyui-validatebox" type="text" name="title" data-options="required:true" /></td>
        </tr>
        <tr>
            <td>作者：</td>
            <td><input class="easyui-validatebox" type="text" name="author" data-options="required:true" /></td>
        </tr>
        <tr>
            <td>来源：</td>
            <td><input class="easyui-validatebox" type="text" name="origin" data-options="required:true" /></td>
        </tr>
        <tr>
            <td>关键词：</td>
            <td><input class="easyui-validatebox" type="text" name="keyword" data-options="required:true" /></td>
        </tr>
        <tr>
            <td>标题图：</td>
            <td>
                <img style="width:200px; height:200px" src="" set-format="${webimg}{0}" set-key="imglittle"/>
                [@a.imgupload multiple=false imgpath="/imgs/webdata/official/news" /]
            </td>
        </tr>
        <tr>
            <td>内容：</td>
            <td>
                [@a.ckeditor name="content"/]
            </td>
        </tr>
    [/@a.editsection]
    
    [@a.viewsection]
        <tr>
            <td>标题：</td>
            <td><span set-key="title"></span></td>
        </tr>
        <tr>
            <td>作者：</td>
            <td><span set-key="author"></span></td>
        </tr>
        <tr>
            <td>来源：</td>
            <td><span set-key="origin"></span></td>
        </tr>
        <tr>
            <td>关键词：</td>
            <td><span set-key="keyword"></span></td>
        </tr>
        <tr>
           <td>标题图：</td>
           <td>
                <img style="width:200px; height:200px" src="" set-format="${webimg}{0}" set-key="imglittle"/>
           </td>
        </tr>
        <tr>
            <td>内容：</td>
            <td><span set-key="content"></span></td>
        </tr>
    [/@a.viewsection]
	
</body>
</html>
