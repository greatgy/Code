[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Admin.ftl" as a]
<html>
<head>
<title>消息中心管理</title>
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
                <td><label for="schannel">目标用户：</label></td>
                <td>
                    <select id="schannel" class="easyui-combobox" name="touseruid" data-options="panelHeight:100" style="width:100px;">   
                        <option value="all">全部</option>
                        <option value="genius">个人用户</option>
                        <option value="investment">投资机构</option>
                        <option value="recommend">并购机构</option>
                        <option value="merger">推荐机构</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td><label for="sauthor">管理员：</label></td>
                <td><input id="sauthor" type="text" name="fromusername" /></td>
                <td><label for="sorigin">内容：</label></td>
                <td><input id="sorigin" type="text" name="content" /></td>
            </tr>
        [/@a.search]
        
        [@a.datagrid title="${channelname}"]
            <th data-options="field:'content',width:550">内容</th>
            <th data-options="field:'fromusername',width:150">发送人</th>
            <th data-options="field:'touseruid',width:150">接收人</th>
            <th data-options="field:'state',width:100">状态</th>
            <th data-options="field:'type',width:100">类型</th>
            <th data-options="field:'createtime',width:150">日期</th>
        [/@a.datagrid]
        [@a.toolbar]
        [/@a.toolbar]
        [@a.tools /]
	</section>
	
	[@a.addsection]
		<tr>
            <td>目标用户：</td>
            <td>
                <input type="radio" name="touseruid" value="all" checked="checked" /> 全部 <br/>
                <input type="radio" name="touseruid" value="genius"/> 个人用户 &nbsp;
                <input type="radio" name="touseruid" value="investment"/> 投资机构 &nbsp;
                <input type="radio" name="touseruid" value="recommend"/> 推荐机构 &nbsp;
                <input type="radio" name="touseruid" value="merger"/> 并购用户 &nbsp;
            </td>
        </tr>
        <tr>
            <td>内容：</td>
            <td><input class="easyui-validatebox" type="text" name="content" data-options="required:true" /></td>
        </tr>
        <tr>
            <td>管理员：</td>
            <td><input class="easyui-validatebox" type="text" name="fromusername" data-options="required:true" /></td>
        </tr>
    [/@a.addsection]
    
    [@a.editsection]
        <tr>
            <td><input type="hidden" name="uid" /></td>
            <td><input type="hidden" name="adminuid" /></td>
        </tr>
        <tr>
            <td>标题：</td>
            <td><input class="easyui-validatebox" type="text" name="fromusername" data-options="required:true" /></td>
        </tr>
        <tr>
            <td>内容：</td>
            <td><input class="easyui-validatebox" type="text" name="content" data-options="required:true" /></td>
        </tr>
    [/@a.editsection]
    
    [@a.viewsection]
        <tr>
            <td>内容：</td>
            <td><span set-key="content"></span></td>
        </tr>
        <tr>
            <td>管理员：</td>
            <td><span set-key="fromusername"></span></td>
        </tr>
    [/@a.viewsection]
    
</body>
</html>
