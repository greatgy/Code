[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Admin.ftl" as a]
<html>
<head>
<title>SEO管理</title>
<script type="text/javascript">
$(function() {
	var temp = '注意：对于文章详情页使用正则表达式 例如 http://startup.180800.cn/article/20/107&nbsp&nbsp匹配网址为：/article/.*/.*;&nbsp&nbsp标题为:$' + '{bean.title!""};&nbsp&nbsp关键词为:$' + '{bean.keywords!""};&nbsp&nbsp描述为:$' + '{bean.summary!""}';
	$("#tips").html(temp);
})	
</script>
</head>
<body>
	<section>
		[@a.datagrid options="fitColumns:true,resizeHandle:'right',pagination:false,singleSelect:true,method:'get'" url="${base}${baseAdminPath}/ajax/seo${site}/list"]
			<th data-options="field:'uid',sortable:'true',width:35">UID</th>
            <th data-options="field:'uri',width:20">匹配网址</th>
            <th data-options="field:'name',width:20">名称</th>
            <th data-options="field:'title',width:50">标题</th>
	    [/@a.datagrid]
	    
	    [@a.toolbar urldel="${base}${baseAdminPath}/ajax/seo${site}/delete" ]
	    [/@a.toolbar]
	    
	    
	</section>
	
	[@a.addsection action="${base}${baseAdminPath}/ajax/seo${site}/add"]
    	<tr>
            <td>匹配网址</td>
            <td><input class="easyui-validatebox" type="text" name="uri" />
            [@a.help title="支持正则，如.*代表匹配任意字符，举例：http://startup.180800.cn/index 匹配网址为：/index; http://startup.180800.cn/article/20/107 匹配网址为：/article/.*/.*" /]
            </td>
        </tr>
        <tr>
            <td>名称</td>
            <td><input class="easyui-validatebox" type="text" name="name" data-options="required:true" /></td>
        </tr>
        <tr>
            <td>标题</td>
            <td><input class="easyui-validatebox long" type="text" name="title" data-options="required:true" />
            [@a.help title="内置变量：$" + "{title}" + "&nbsp&nbsp页面标题"/]
            </td>
        </tr>
        <tr>
            <td>关键词</td>
            <td><input class="long" type="text" name="keywords" />
            [@a.help title="内置变量：$" + "{keywords}" + "&nbsp&nbsp页面关键字" /]
            </td>
        </tr>
        <tr>
            <td>描述</td>
            <td><input class="long" class="long" type="text" name="desc" />
            [@a.help title="内置变量：$" + "{description}" + "&nbsp&nbsp页面描述" /]
            </td>
        </tr>
        <tr>
            <td>父级UID</td>
            <td><input class="easyui-validatebox" type="text" name="parentuid" /></td>
        </tr>
        <br/>
        <tr>
        	<td></td>
        	<td>
        		<p id="tips"></p>
    		</td>
    	</tr>
    [/@a.addsection]
	
	[@a.editsection action="${base}${baseAdminPath}/ajax/seo${site}/add"]
		<tr>
            <td>UID<input type="hidden" name="uid" /></td>
            <td><span set-key="uid"></span></td>
        </tr>
    	<tr>
            <td>匹配网址</td>
            <td><input class="easyui-validatebox" type="text" name="uri" />
            [@a.help title="支持正则，如.*代表匹配任意字符，举例：首页/index 产品详情页/product/.*" /]
            </td>
        </tr>
        <tr>
            <td>名称</td>
            <td><input class="easyui-validatebox" type="text" name="name" data-options="required:true" /></td>
        </tr>
        <tr>
            <td>标题</td>
            <td><input class="easyui-validatebox long" type="text" name="title" data-options="required:true" />
            [@a.help title="内置变量：$" + "{title}" /]
            </td>
        </tr>
        <tr>
            <td>关键词</td>
            <td><input class="long" type="text" name="keywords" />
            [@a.help title="内置变量：$" + "{keywords}" /]
            </td>
        </tr>
        <tr>
            <td>描述</td>
            <td><input class="long" class="long" type="text" name="desc" />
            [@a.help title="内置变量：$" + "{description}" /]
            </td>
        </tr>
        <tr>
            <td>父级UID</td>
            <td><input class="easyui-validatebox" type="text" name="parentuid" /></td>
        </tr>
    [/@a.editsection]
	
	[@a.viewsection]
		<tr>
            <td>UID</td>
            <td><span set-key="uid"></span></td>
        </tr>
		<tr>
            <td>匹配网址</td>
            <td><span set-key="uri"></span></td>
        </tr>
		<tr>
            <td>名称</td>
            <td><span set-key="name"></span></td>
        </tr>
        <tr>
            <td>标题</td>
            <td><span set-key="title"></span></td>
        </tr>
        <tr>
            <td>关键词</td>
            <td><span set-key="keywords"></span></td>
        </tr>
        <tr>
            <td>描述</td>
            <td><span set-key="desc"></span></td>
        </tr>
        <tr>
            <td>父级UID</td>
            <td><span set-key="parentuid"></span></td>
        </tr>
	[/@a.viewsection]
	
</body>
</html>