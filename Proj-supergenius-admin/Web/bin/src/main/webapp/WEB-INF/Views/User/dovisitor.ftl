[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Admin.ftl" as a]
<html>
<head>
<title>用户管理</title>
<script type="text/javascript" src="${basejs}/ckeditor/ckeditor.js"></script>
<script type="text/javascript">

	//调整编辑页面显示选项内容
	$(function() {
		 $("#btnedit").click(function() {
		    $(window.frames["frameImg1"].document).find("#formImg1").css("display","block");
	    });
	    
	    $("#btnadd").click(function() {
		   $(window.frames["frameImg0"].document).find("#formImg0").css("display","block");
	    });
	    
	});
	
	//自定义提交方法
	function submitHandlerForAdd(obj, dg){
	   $(obj).parents("form").form('submit', {
	   	   onSubmit: function(param) {
	   	       if ($("#imguploadinput0").find("input[name='imgdata']").val() == undefined) {
	   	        err("您未上传图片！");
                return false;
	   	       };
	   	   },
	       success : function(str) {
				var result = $.parseJSON(str);
				if (result.success || result.addSuccess || result.editSuccess) {
					getDataGrid(dg).datagrid("reload"); // 重新加载数据表格
					clearForm($(obj).parents("form"));
					submitSuccessHandler(result);
				} else {
					submitErrHandler(result);
				}
			}
	   });
	}
-->
</script> 
</head>
<body>
    <section>
        [@a.search]
        [/@a.search]
        
        [@a.datagrid]
            <th data-options="field:'nickname', width:100">昵称</th>
            <th data-options="field:'avatar'">头像</th>
        [/@a.datagrid]
        [@a.toolbar candel=false canedit=false]
	    [/@a.toolbar]
        [@a.tools /]
    </section>
    
    [@a.addsection onclicksubmit="submitHandlerForAdd(this)"]
    	<tr>
            <td>昵称:</td>
            <td><input type="text" class="easyui-validatebox" name="nickname" data-options="min:0,required:true" /></td>
        </tr>
        <tr>
            <td>图片:</td>
            <td class="addimgdata">[@a.imgupload multiple=false imgpath="${photopath}" name="imgdata"/]</td>
    	</tr>
    [/@a.addsection]
    
    [@a.viewsection]
    	<tr>
            <td>昵称:</td>
            <td><span set-key="nickname"></span></td>
        </tr>
		<tr>
	        <td>原图:</td>
	        <td><img src="" set-format="${webimg}{0}" set-key="avatar" style="width: 600px; height: 400px;"/></td>
        </tr>    
    [/@a.viewsection]
    
</body>
</html>
