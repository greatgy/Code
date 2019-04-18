[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Admin.ftl" as a]
<html>
<head>
<title>案例管理</title>
<script type="text/javascript" src="${basejs}/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
	<!--
	$(function(){
		$("#rdtype_0").click(function(){
			$("#link").show();
			$("#doc").hide();
		});
		$("#rdtype_1").click(function(){
			$("#link").hide();
			$("#doc").show();
		});
		
	})
	
	function initDGData(row) {
		for(var i in row) {
			if(row[i].type == 0){
				row[i].download = '<a href=\'${basefile}'+row[i].href+'\'>点击下载</a>';
			}else{
				row[i].download = '<a href=' + row[i].href + '\>打开连接</a>';
				row[i].url = row[i].href;
			}
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
            <th data-options="field:'statusName',width:100">状态</th>
            <th data-options="field:'createtimeStr',width:250">发布日期</th>
            <th data-options="field:'download',width:200">下载</th>
            <th data-options="field:'countdl',width:200">下载量</th>
        [/@a.datagrid]
        [@a.toolbar]
            [@a.status/]
        [/@a.toolbar]
        [@a.tools /]
	</section>
	[@a.addsection canuploadfile=true]
        <tr>
            <td>名称：</td>
            <td><input class="easyui-validatebox" type="text" name="name" data-options="required:true" /></td>
        </tr>
        <tr>
            <td>类型：</td>
            <td>
	            [#list map?keys as mkey]
                    <input type="radio" id="rdtype_${mkey_index}" [#if mkey_index == 0]checked="checked"[/#if]name="type" value="${mkey}"/> <label for="rdtype_${mkey_index}">${map[mkey]}</label>&nbsp;
                [/#list]
            </td>
        </tr>
        <tr id="doc">
	       <td>文档:</td>
	       <td id="file"><input type="file" name="file"/> </td>
        </tr>
        <tr id="link">
	       <td>链接地址:</td>
	       <td><input type="text" name="url" id="urls" /></td>
        </tr>
    [/@a.addsection]
    
    [@a.editsection canuploadfile=true]
        <tr>
            <td><input type="hidden" name="uid" /></td>
        </tr>
         <tr>
            <td>名称：</td>
            <td><input class="easyui-validatebox" type="text" name="name" data-options="required:true" /></td>
        </tr>
        <tr>
            <td>类型：</td>
            <td>
	            [#list map?keys as mkey]
                    <input type="radio" id="rdtype_${mkey_index}" [#if mkey_index == 0]checked="checked"[/#if]name="type" value="${mkey}"/> <label for="rdtype_${mkey_index}">${map[mkey]}</label>&nbsp;
                [/#list]
            </td>
        </tr>
        <tr>
	       <td>文档:</td>
	       <td><input type="file" name="file"/></td>
        </tr>
        <tr>
	       <td>链接地址:</td>
	       <td><input type="text" name="url"/></td>
        </tr>
    [/@a.editsection]
    
    [@a.viewsection]
        <tr>
            <td>名称：</td>
            <td><span set-key="name"></span></td>
        </tr>
        <tr>
            <td>类型：</td>
            <td><span set-key="typeName"></span></td>
        </tr>
        <tr>
            <td>下载地址：</td>
            <td><span set-key="download"></span></td>
        </tr>
        <tr>
            <td>连接地址：</td>
            <td><span set-key="url"></span></td>
        </tr>
    [/@a.viewsection]
	
</body>
</html>
