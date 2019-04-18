[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Admin.ftl" as a]
<html>
<head>
<title>图片管理</title>
<script type="text/javascript" src="${basejs}/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
$(function() {
	$("#btnedit").click(function() {
		$(window.frames["frameImg0"].document).find("#formImg0").css("display","block");
	});
});
</script>
</head>
<body>
    <section>
		<table>
			<tr>
			   <td>banner总数：</td>
			   <td><span>4</span></td>
			<tr>        
		</table>
        [@a.datagrid]
            <th data-options="field:'title',width:62">标题</th>
        [/@a.datagrid]
        [@a.toolbar canadd=false candel=false]
        [/@a.toolbar]  
        [@a.tools /]
    </section>
		
		[@a.viewsection]
        <tr><td>标题</td><td><span set-key="title"></span></td></tr>
        <tr><td>地址</td><td><span set-key="url"></span></td></tr>
        <tr>
	        <td>图片:</td>
	        <td><img src="" set-format="${webimg}{0}" set-key="content" style="width: 600px; height: 400px;"/></td>
        </tr>
        [/@a.viewsection]
   	    [@a.editsection canuploadfile=true]
         <tr>
            <td>标题：</td><input type="hidden" name="oid" />
            <td><input class="easyui-validatebox" name="title" data-options="required:true" id="edit_title"/></td>
        </tr>
         <tr>
            <td>地址：</td>
            <td><input class="easyui-validatebox" name="url" data-options="required:true" id="edit_url"/></td>
        </tr>
        <tr>
            <td>图片:</td>
            <td><img id="avatarbig" style="width:150px; height:150px" src="" set-format="${webimg}{0}" set-key="content"/>
            	[@a.imgupload multiple=false imgpath="${photopath}" name="imgdata"/]
            </td>
        </tr>
    	[/@a.editsection]
</body>
</html>