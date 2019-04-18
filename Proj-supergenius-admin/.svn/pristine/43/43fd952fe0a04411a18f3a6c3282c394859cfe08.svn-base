[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Admin.ftl" as a]
<html>
<head>
<title>用户发帖管理</title>
<script type="text/javascript" src="${basejs}/ckeditor/ckeditor.js"></script>
</head>
<body>
    <section>
        [@a.search]
            <tr>
                <td><label for="stitle">标题：</label></td>
                <td><input id="stitle" type="text" name="title" /></td>
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
                <td>添加公告的起始时间：</td>
                <td><input id="timestart" name="addtimestart" type="text" data-options="editable:false" class="easyui-datebox" /></td>
                <td>添加公告的结束时间：</td>
                <td><input id="timeend" name="addtimeend" type="text" data-options="editable:false" class="easyui-datebox" /></td>
            </tr>
        [/@a.search]
        
        [@a.datagrid]
            <th data-options="field:'sn',width:50">编号</th>
            <th data-options="field:'title',width:550">公告标题</th>
            <th data-options="field:'addtimeStr',width:200">添加时间</th>
            <th data-options="field:'endtimeStr',width:200">到期时间</th>
            <th data-options="field:'statusName',width:100">状态</th>
            <th data-options="field:'istopName',width:100">是否置顶</th>
        [/@a.datagrid]
        
        [@a.toolbar]
            [@a.state nameenable="置顶" namedisable="取消置顶" statefield="istop" actionenable="${base}${baseAdminPath}/moral/ajax/${channel}/istop/1" actiondisable="${base}${baseAdminPath}/moral/ajax/${channel}/istop/0"/]
        [/@a.toolbar]
        
        [@a.tools /]
    </section>
    
    [@a.addsection]
        <tr>
            <td>公告标题：</td>
            <td><input class="easyui-validatebox" type="text" name="title" data-options="required:true" /></td>
        </tr>
        <tr>
           <td>公告内容:</td>
           <td><textarea class="easyui-validatebox" style="width:300px;height:100px" type="text" name="content" data-options="required:true"></textarea></td>
        </tr>
        <tr>
            <td>到期时间：</td>
            <td><input class="easyui-datebox" type="text" name="expiretime" data-options="required:true" /></td>
        </tr>
    [/@a.addsection]
    
    [@a.editsection]
        <tr>
            <td><input type="hidden" name="uid" /></td>
        </tr>
        <tr>
            <td>公告标题：</td>
            <td><input class="easyui-validatebox" type="text" name="title" data-options="required:true" /></td>
        </tr>
        <tr>
           <td>公告内容:</td>
           <td><textarea class="easyui-validatebox" style="width:300px;height:100px" type="text" name="content" data-options="required:true"></textarea></td>
        </tr>
        <tr>
            <td>到期时间：</td>
            <td><input class="easyui-datebox" type="text" name="endtimeStr" data-options="required:true" /></td>
        </tr>
    [/@a.editsection]
    
    [@a.viewsection]
        <tr>
            <td>公告标题：</td>
            <td><span set-key="title"></span></td>
        </tr>
        <tr>
            <td>公告内容:</td>
            <td><span set-key="content"></span></td>
        </tr>
        <tr>
            <td>发布时间：</td>
            <td><span set-key="addtimeStr"></span></td>
        </tr>
        <tr>
            <td>到期时间：</td>
            <td><span set-key="endtimeStr"></span></td>
        </tr>
        <tr>
            <td>编号：</td>
            <td><span set-key="sn"></span></td>
        </tr>
        <tr>
            <td>状态：</td>
            <td><span set-key="statusName"></span></td>
        </tr>
        <tr>
            <td>置顶：</td>
            <td><span set-key="istopName"></span></td>
        </tr>
    [/@a.viewsection]
    
</body>
</html>