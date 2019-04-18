[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Admin.ftl" as a]
<html>
<head>
<title>培训讲义管理</title>
<script type="text/javascript" src="${basejs}/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
	<!--
	function initDGData(row) {
		for(var i in row) {
			row[i].download = '<a href=\'${base}${baseAdminPath}/${site}/doc/download/'+row[i].uid+'\'>点击下载</a>';
		}
	}
	-->
</script>
</head>
<body>
    <section>       
        [@a.search]
            <tr>
                <td><label for="stitle">标题：</label></td>
                <td><input id="stitle" type="text" name="name" /></td>
                <td><label for="sstatus">状态：</label></td>
                <td>
                    <div class="channel">
                        <select class="easyui-combobox" name="status" data-options="panelHeight:100" style="width:100px;">
                            <option value="" name="status">全部</option>
                            <option id="rdchannel_0" value="0" name="status">冻结</option>
                            <option id="rdchannel_1" value="1" name="status">正常</option>
                        </select>
                    </div>
                </td>
            </tr>
            <tr>
                <td><label for="sstatus">所属章节：</label></td>
                <td>
                    <div class="channel">
                        <select id="rdchannel_${mkey_index}" class="easyui-combobox" name="chapter" data-options="panelHeight:100" style="width:100px;">
                            [#list map?keys as mkey]
                                <option id="rdchannel_${mkey_index}" name="chapter" value="${mkey}">
                                    <label for="rdchannel_${mkey_index}">${map[mkey]}</label>
                                </option>
                            [/#list]
                        </select>
                    </div>
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
            <th data-options="field:'sn',width:50">编号</th>
            <th data-options="field:'chapterName',width:200">所属章节</th>
            <th data-options="field:'name',width:650">讲义标题</th>
            <th data-options="field:'countdl',width:200">下载量</th>
            <th data-options="field:'statusName',width:75">状态</th>
            <th data-options="field:'createtimeStr',width:300">添加时间</th>
            <th data-options="field:'download',width:200">下载</th>
        [/@a.datagrid]
        
        [@a.toolbar]
            [@a.updown /]
            [@a.status /]
        [/@a.toolbar]
        
        [@a.tools/]
    </section>
    
    [@a.addsection canuploadfile=true]
        <tr>
            <td>讲义名称：</td>
            <td><input class="easyui-validatebox" type="text" name="name" data-options="required:true" /></td>
        </tr>
        <tr>
	       <td>讲义文档:</td>
	       <td><input id="file_doc" type="file" name="docfile"/></td>
        </tr>
        <tr>
            <td>讲义简介：</td>
            <td><input class="easyui-validatebox" type="text" name="docintro" data-options="required:true" /></td>
        </tr>
        <tr>
            <td>所属章节：</td>
            <td>
             <div class="channel">
                <select id="rdchannel_${mkey_index}" class="easyui-combobox" name="chapter" data-options="panelHeight:100" style="width:100px;">
                    [#list map?keys as mkey]
                        <option id="rdchannel_${mkey_index}" name="chapter" value="${mkey}">
                            <label for="rdchannel_${mkey_index}">${map[mkey]}</label>
                        </option>
                    [/#list]
                </select>
            </div>
            </td>
        </tr>
    [/@a.addsection]

    [@a.editsection canuploadfile=true]
        <tr>
            <td><input type="hidden" name="uid" /></td>
        </tr>
        <tr>
            <td>讲义名称：</td>
            <td><input class="easyui-validatebox" type="text" name="name" data-options="required:true" /></td>
        </tr>
        <tr>
        	<td>讲义文档：</td>
        	<td><input id="file_doc" type="file" name="docfile"/></td>
        </tr>
        <tr>
            <td>讲义简介：</td>
            <td><input class="easyui-validatebox" type="text" name="docintro" data-options="required:true" /></td>
        </tr>
        <tr>
            <td>所属章节：</td>
            <td>
                <div class="channel">
                    <select id="rdchannel_${mkey_index}" class="easyui-combobox" name="chapter" data-options="panelHeight:100" style="width:100px;">
                        [#list map?keys as mkey]
                            <option id="rdchannel_${mkey_index}" name="chapter" value="${mkey}">
                                <label for="rdchannel_${mkey_index}">${map[mkey]}</label>
                            </option>
                        [/#list]
                    </select>
                </div>
            </td>
        </tr>
    [/@a.editsection]
    
    [@a.viewsection]
        <tr>
            <td>讲义名称：</td>
            <td><span set-key="name"></span></td>
        </tr>
        <tr>
            <td>状态：</td>
            <td><span set-key="statusName"></span></td>
        </tr>
        <tr>
            <td>所属章节：</td>
            <td><span set-key="chapterName"></span></td>
        </tr>
        <tr>
            <td>编号：</td>
            <td><span set-key="sn"></span></td>
        </tr>
        <tr>
            <td>讲义简介：</td>
            <td><span set-key="docintro"></span></td>
        </tr>
        <tr>
            <td>发布时间：</td>
            <td><span set-key="createtimeStr"></span></td>
        </tr>
        <tr>
            <td>下载次数：</td>
            <td><span set-key="countdl"></span></td>
        </tr>
    [/@a.viewsection]
    
</body>