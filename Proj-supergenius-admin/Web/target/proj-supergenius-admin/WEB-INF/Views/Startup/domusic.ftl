[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Admin.ftl" as a]
<html>
<head>
<title>背景音乐管理</title>
<script type="text/javascript" src="${basejs}/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
/*重写add和edit的表单提交方法*/ 
function submitHandlerForAddAndEdit(obj, dg) {
	$(obj).parents("form").form('submit', {
	    onSubmit: function(param) {
		   if (($("input[name='addorder']").val().length < 1) && ($("input[name='addorder']").val().length < 1)) {
		       err("您还未填写序号，请填写序号！");
		       return false;
		   }
		   if (isNaN($("input[name='addorder']").val())) {
		       err("序号必须填写数字！");
		       return false;
		   }	   
		   if (($("input[name='addname']").val().length < 1) && ($("input[name='addname']").val().length < 1)) {
		       err("您还未填写音乐名称，请填写名称！");
		       return false;
		   }

		   if ($("#addlocal").attr("checked")=="checked") {

			if ($("#local_file").val().length<1) {
				err("请您选择需要上传的本地歌曲!");
				return false;
			}
			//判断音乐文件的大小，单位KB	
			var fileSize = document.getElementById("local_file").files[0].size / 1024;  
			if(fileSize > 1024*10){  
			    err("本地音乐大小不能超过10MB");  
			    return false;
			}
			//$("#local_file").attr("disabled", true);//将该控件设置成disable，这样form就不会提交到后台
			$("input[name='temp']").val("0");//设置隐藏input的值，0表示选择的是本地歌曲
			console.log("我改变了值"+$("input[name='temp']").val());
		   } else { 
		   	if ($("#network_file").val().length<1) {
				alert("请您输入歌曲URL!");
				return false;
			}
			/*
			if (!regexUrl($("#network_file").val())) {
				err("请输入正确的URL!");
				return false;
			}*/
			//$("#musicdata_local").attr("disabled", true); //将该控件设置成disable，这样form就不会提交到后台
			$("input[name='temp']").val("1");//设置隐藏input的值，1表示选择的是网络歌曲	
			console.log("network我改变了值"+$("input[name='temp']").val());
		   }
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

/*重写edit的表单提交方法*/ 
function submitHandlerForEdit(obj, dg) {
	$(obj).parents("form").form('submit', {
	    onSubmit: function(param) {
	       
		   if ($("input[name='order']").val().length < 1) {
		       err("您还未填写序号，请填写序号！");
		       return false;
		   }
		   if (isNaN($("input[name='order']").val())) {
		       err("序号必须填写数字！");
		       return false;
		   }
		   if ($("input[name='name']").val().length < 1) {
		       err("您还未填写音乐名称，请填写名称！");
		       return false;
		   }
			
		   if($("#default").attr("checked")=="checked") {
		   	$("input[name='temp']").val("-1");//设置隐藏input的值，-1表示未改变file的值
		   } else if ($("#local").attr("checked")=="checked") {
			if ($("#musicdata_local").val().length<1) {
				err("请您选择需要上传的本地歌曲!");
				return false;
			}
			//判断音乐文件的大小，单位KB	
			var fileSize = document.getElementById("musicdata_local").files[0].size / 1024;  
			if(fileSize > 1024*10){  
			    err("本地音乐大小不能超过10MB");  
			    return false;
			}
			$("input[name='temp']").val("0");//设置隐藏input的值，0表示选择的是本地歌曲
		   } else { 
		   	if ($("#musicdata_network").val().length < 1) {
				err("请您输入歌曲URL!");
				return false;
			}
			/*
			if (!regexUrl($("#musicdata_network").val())) {
				err("请输入正确的URL!");
				return false;
			}*/
			$("input[name='temp']").val("1");//设置隐藏input的值，1表示选择的是网络歌曲	
		   }
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
//定义正则表达式，判断是否为网址
function regexUrl(url) {
	var reg=/^([hH][tT]{2}[pP]:\/\/|[hH][tT]{2}[pP][sS]:\/\/)(([A-Za-z0-9-~]+)\.)+([A-Za-z0-9-~\/])+$/;
	return reg.test(url);
}
</script>
</head>
<body>
    <section>
		<table>
			<tr>
			   <td>背景音乐总数：</td>
			   <td><span>${count}</span></td>
			<tr>        
		</table>
        [@a.datagrid]
            <th data-options="field:'order',width:20">序号</th>
            <th data-options="field:'name',width:40">歌曲名称</th>
            <th data-options="field:'url',width:70">歌曲路径</th>
            <th data-options="field:'createtimeStr',width:30">操作时间</th>
            <th data-options="field:'adminname',width:20">操作人</th>
        [/@a.datagrid]
        [@a.toolbar nameadd="添加音乐" canview=false]
        [/@a.toolbar]
        [@a.tools /]
    </section>
		
   	    [@a.editsection canuploadfile=true onclicksubmit="submitHandlerForEdit(this)"]
         <tr>
            <td>序号：</td><input type="hidden" name="uid" />
            <td><input class="easyui-validatebox" name="order" data-options="required:true" /></td>
        </tr>
        <tr>
            <td>名称：</td>
            <td><input class="easyui-validatebox" name="name"/></td>
        </tr>
        <tr>
            <td>歌曲路径：</td>            
            <td><input type="radio" name="upload" id="default" checked="checked"><input class="easyui-validatebox" name="url" readonly = "readonly"/></td>
        </tr>
        <tr>
        	<td>编辑路径：</td>
            <td>
				<input type="radio" name="upload" id="local" >&nbsp<label for="local">本地音乐</label>&nbsp&nbsp&nbsp
				<input type="file" name="localfile" accept="audio/*" id="musicdata_local"/>
			</td>
        </tr>
        <tr>
        	<td></td>
			<td>
				<input type="radio" name="upload" id="network">&nbsp<label for="network">网络音乐URL</label>&nbsp&nbsp	
				<input type="text" name="networkfile" id="musicdata_network"/>			
			</td>
			<input type="hidden" name="temp" value="0"/>
        </tr>
    	[/@a.editsection]
    	
    	[@a.addsection canuploadfile=true onclicksubmit="submitHandlerForAddAndEdit(this)"]
        <tr>
            <td id="titletest">序号：</td>
            <td><input class="easyui-validatebox" name="addorder" data-options="required:true" /></td>
        </tr>
        <tr>
            <td>歌曲名称：</td>
            <td><input class="easyui-validatebox" name="addname" data-options="required:true"/></td>
        </tr>
        <tr>       	
			<td>歌曲路径：&nbsp&nbsp</td>
			<td>
				<input type="radio" name="upload" id="addlocal" checked="checked">&nbsp<label for="addlocal">本地音乐</label>&nbsp
				<input type="file" name="localfile" accept="audio/*" id="local_file"/>
			</td>
			<input type="hidden" name="temp" value="0"/>
        </tr>
        <tr>
        	<td></td>
        	<td>
				<input type="radio" name="upload" id="addnetwork">&nbsp<label for="addnetwork">网络音乐URL</label>
				<input type="text" name="networkfile" id="network_file"/>				
			</td>
        </tr>
    	[/@a.addsection]
</body>
</html>