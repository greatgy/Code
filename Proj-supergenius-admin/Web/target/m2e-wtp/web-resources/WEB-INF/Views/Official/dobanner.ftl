[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Admin.ftl" as a]
<html>
<head>
<title>banner管理</title>
<script type="text/javascript" src="${basejs}/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
$(function() {
	$("#btnedit").click(function() {
	   $(window.frames["frameImg1"].document).find("#formImg1").css("display","block");
    });
	$("#btnadd").click(function() {
		$(window.frames["frameImg0"].document).find("#formImg0").css("display","block");
	});
});
</script>
</head>
<body>
	<section>
        [@a.datagrid]
            <th data-options="field:'title',width:500">标题</th>
            <th data-options="field:'bannertypeName',width:200">类型</th>
            <th data-options="field:'href',width:200">图片链接地址</th>
            <th data-options="field:'statusName',width:200">状态</th>
            <th data-options="field:'createtimeStr',width:200">发布时间</th>
        [/@a.datagrid]
        
        [@a.toolbar]
            [@a.updown /]
		[/@a.toolbar]

		[@a.tools /]
	</section>
	
	[@a.addsection]
        <tr>
            <td>标题：</td>
            <td><input class="easyui-validatebox" type="text" name="title" data-options="required:true" /></td>
        </tr>
        <tr>
            <td>类别：</td>
            <td>
      		 <div class="channel">
      		    [#list map?keys as mkey]
                    <input type="radio" id="rdchannel_${mkey_index}" name="bannertype" value="${mkey}"/> <label for="rdtype__${mkey_index}">${map[mkey]}</label>&nbsp;
                [/#list]
      		 </div>
            </td>
        </tr>
        <tr>
            <td>填充色：</td>
            <td><input class="easyui-validatebox" type="text" name="background" data-options="required:false" /></td>
        </tr>
        <tr>
            <td>图片链接地址：</td>
            <td><input class="easyui-validatebox" type="text" name="href" data-options="required:false" /></td>
        </tr>
        <tr>
            <td>轮播图片：</td>
            <td>[@a.imgupload multiple=false imgpath="/imgs/webdata/official/banner" /]</td>
        </tr>       
	[/@a.addsection]
	
    [@a.editsection]
    	<tr>
            <td><input type="hidden" name="uid" /></td>
            <td><input type="hidden" name="banneruid" /></td>
        </tr>
    	<tr>
            <td>标题：</td>
            <td><input class="easyui-validatebox" type="text" name="title" data-options="required:true" /></td>
        </tr>
        <tr>
            <td>板块：</td>
            <td>
      		 <div class="channel">
      		    [#list map?keys as mkey]
                    <input type="radio" id="rdchannel_${mkey_index}" name="bannertype" value="${mkey}"/> <label for="rdchannel_${mkey_index}">${map[mkey]}</label>&nbsp;
                [/#list]
             </div>
            </td>
        </tr>
        <tr>
            <td>填充色：</td>
            <td><input class="easyui-validatebox" type="text" name="background" data-options="required:false" /></td>
        </tr>
        <tr>
            <td>图片链接地址：</td>
            <td><input class="easyui-validatebox" type="text" name="href" data-options="required:false" /></td>
        </tr>
        <tr>
            <td>轮播图片：</td>
            <td>
            	<img style="width:200px; height:200px" src="" set-format="${webimg}{0}" set-key="imglittle"/>
            	[@a.imgupload multiple=false imgpath="/imgs/webdata/official/banner" /]
            </td>
        </tr>
    [/@a.editsection]
    
    [@a.viewsection]
        <tr>
            <td>标题：</td>
            <td><input class="easyui-validatebox" type="text" name="title" data-options="required:true" /></td>
        </tr>
        <tr>
            <td>图片链接地址：</td>
            <td><input class="easyui-validatebox" type="text" name="href" data-options="required:false" /></td>
        </tr>
        <tr>
            <td>轮播图片：</td>
            <td><img src="" set-format="${webimg}{0}" set-key="imgmedium" style="display:true"/></td>
        </tr>
    [/@a.viewsection]
</body>
</html>
