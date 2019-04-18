[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Admin.ftl" as a]
<html>
<head>
<title>视频管理</title>
<script type="text/javascript" src="${basejs}/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
</script> 	
</head>
<body>
    <section>
        [@a.search]
            <tr>
                <td><label for="sname">名称：</label></td>
                <td><input id="sname" type="text" name="name" /></td>
                <td>
	        		<label for="schapter">章节: </label>
	        		<select id="schapter" name="chapter">
					    <option value="">全部</option>
					    [#assign chapter = map?keys]
					    	[#list chapter as item]
						    	<option value="${item}">${map[item]}</option>
						    [/#list]
		            </select>
	        	</td>
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
            <th data-options="field:'chapterName',width:100">章节</th>
            <th data-options="field:'countpl',width:100">播放量</th>
            <th data-options="field:'statusName',width:100">状态</th>
            <th data-options="field:'createtimeStr',width:150">发布日期</th>
        [/@a.datagrid]
        [@a.toolbar]
            [@a.status/]
        [/@a.toolbar]
        [@a.tools /]
	</section>
	[@a.addsection]
        <tr>
            <td>名称：</td>
            <td><input class="easyui-validatebox" type="text" name="name" data-options="required:true" /></td>
        </tr>
        <tr>
            <td>章节：</td>
            <td>
	            [#list map?keys as mkey]
                    <input type="radio" id="rdchannel_${mkey_index}" name="chapter" value="${mkey}"/> <label for="rdchannel_${mkey_index}">${map[mkey]}</label>&nbsp;
                [/#list]
            </td>
        </tr>
        <tr>
            <td>标题图：</td>
            <td>[@a.imgupload multiple=false imgpath="/imgs/webdata/moral/video" /]</td>
        </tr>
        <tr>
            <td>内容：</td>
            <td>
                [@a.ckeditor name="code"/]
            </td>
        </tr>
    [/@a.addsection]
    
    [@a.editsection]
        <tr>
            <td><input type="hidden" name="uid" /></td>
        </tr>
        <tr>
            <td>名称：</td>
            <td><input class="easyui-validatebox" type="text" name="name" data-options="required:true" /></td>
        </tr>
        <tr>
            <td>章节：</td>
            <td>
      		 <div class="chapter">
      		    [#list map?keys as mkey]
                    <input type="radio" id="rdchannel_${mkey_index}" name="chapter" value="${mkey}"/> <label for="rdchannel_${mkey_index}">${map[mkey]}</label>&nbsp;
                [/#list]
             </div>
            </td>
        </tr>
        <tr>
            <td>标题图：</td>
            <td>
                <img style="width:200px; height:200px" src="" set-format="${webimg}{0}" set-key="imglittle"/>
                [@a.imgupload multiple=false imgpath="/imgs/webdata/moral/video" /]
            </td>
        </tr>
        <tr>
            <td>内容：</td>
            <td>
                [@a.ckeditor name="code"/]
            </td>
        </tr>
    [/@a.editsection]
    
    [@a.viewsection]
        <tr>
            <td>名称：<input type="hidden" name="uid" /></td>
            <td><span set-key="name"></span></td>
        </tr>
        <tr>
            <td>章节：</td>
            <td><span set-key="chapterName"></span></td>
        </tr>
        <tr>
           <td>标题图：</td>
           <td>
                <img style="width:200px; height:200px" src="" set-format="${webimg}{0}" set-key="imglittle"/>
           </td>
        </tr>
        <tr>
            <td>内容：</td>
            <td><span set-key="code"></span></td>
        </tr>
    [/@a.viewsection]
	
</body>
</html>
