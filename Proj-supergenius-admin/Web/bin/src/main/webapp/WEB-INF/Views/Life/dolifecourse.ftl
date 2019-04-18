[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Admin.ftl" as a]
<html>
<head>
<title>课程管理</title>
<script type="text/javascript" src="${basejs}/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
	var temp = -1;
	$(function() {
		$("#btnedit").click(function() {
	    	//调整编辑页面显示选项内容
	    	var row = getSelected(dg);
		    if (row) {
		    	temp =row.sid;
		    	$("#courseeditgrade_" + row.grade).click();
		    } else {
		        err("请选择一项！");
		    }
		    $(window.frames["frameImg1"].document).find("#formImg1").css("display","block");
	    });
	    
	    //修正点击add，图片上传按钮不回显的BUG
		$("#btnadd").click(function() {
		   $(window.frames["frameImg1"].document).find("#formImg1").css("display","block");
		   $(window.frames["frameImg0"].document).find("#formImg0").css("display","block");
	    });
	
		$(document).on('click', "input[id^='course']", function(){
			$.get('${base}${baseAdminPath}/life/ajax/lifecourse/subjects', {grade:$(this).val()},function(data){
				var list = JSON.parse(data);
				var html = '';
				for(var i= 0;i<list.length;i++){
					if(i == 0){
						html += '<input type="radio" id="addcourse_'+list[i].sid+'" class="addcourse_'+list[i].sid+'" name="subject" value="'+list[i].sid+'" checked/><label for="addcourse_'+list[i].sid+'">'+list[i].name+'</label>';
					}else{
						html += '<input type="radio" id="addcourse_'+list[i].sid+'" class="addcourse_'+list[i].sid+'" name="subject" value="'+list[i].sid+'"/><label for="addcourse_'+list[i].sid+'">'+list[i].name+'</label>';
					}
				}
				$(".subject").html(html);
				if(temp >0){
					$(".addcourse_" + temp).prop("checked","checked");
				}
			});
	    });
	    
	    $("#courseaddgrade_6").click();
	});

	function mytoolbarStateHandler(event, j) {
        var list = getSelections(j.dg); 
        if (list.length > 0) {
	        if (list[0].status == 1){
	            $("#enable").linkbutton("disable");
	        	$("#disable").linkbutton("enable");
	        } else {
	            $("#enable").linkbutton("enable");
	        	$("#disable").linkbutton("disable");
	        }
        } else {
	        $("#enable").linkbutton("disable");
	        $("#disable").linkbutton("disable");
        }
    }
    
    /*重写add的表单提交方法*/ 
function submitHandlerForAdd(obj, dg) {
	$(obj).parents("form").form('submit', {
	    onSubmit: function(param) {
	     if (($("#imguploadinput1").find("input[name='imgdata']").val() == undefined)) {
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
    
    function myButtonHandler(obj, url) {
		var objid = $(obj).attr("id");
		if (objid == "disable") {
		    confirmAjax('冻结该选项', '确定要冻结吗？', '${base}${baseAdminPath}/life/ajax/lifecourse/status');
		}
		if (objid == "enable") {
		    confirmAjax('解冻该选项', '确认要解冻吗？', '${base}${baseAdminPath}/life/ajax/lifecourse/status');
		}
	}
	
	function confirmAjax(title, question, url) {
		$.messager.confirm(title, question, function(flag) {
			if (flag) {
				ajaxPostSubmit(url);
			}
		});
	}
	
	function ajaxPostSubmit(url) {
		$.post(url, {uid : getSelected(dg).uid}, function(result) {
			if (result.success) {
				getDataGrid(dg).datagrid("reload"); // 重新加载数据表格
				resultHandler(result);
			} else {
				resultHandler(result);
			}
		});
	}
	
</script>
</head>
<body>
	<section>
		[@a.search]
	        <tr>
	        	<td colspan="10">
		        	<label for="name">名称: </label><input id="name" type="text" name="name" class="search"/>&nbsp;&nbsp;&nbsp;
	        	</td>
	        </tr>
	        <tr>
                <td>状态：
                    <input type="radio" id="statusall" name="status" value="" checked/><label for="statusall">全部</label>
                    <input type="radio" id="statusone" name="status" value="1" /><label for="statusone">正常</label>
                    <input type="radio" id="statustwo" name="status" value="0" /><label for="statustwo">冻结</label>
                </td>
        	</tr>
        	<tr>
                <td>年级：
                	<input type="radio" id="moduleall" name="grade" value="" checked/><label for="moduleall">全部</label>
                    <input type="radio" id="grade_6" name="grade" value="1" /><label for="grade_6">六年级</label>
                    <input type="radio" id="grade_7" name="grade" value="2" /><label for="grade_7">七年级</label>
                    <input type="radio" id="grade_8" name="grade" value="4" /><label for="grade_8">八年级</label>
                    <input type="radio" id="grade_9" name="grade" value="8" /><label for="grade_9">九年级</label>
                </td>
        	</tr>
		[/@a.search]
		
		[@a.datagrid]
            <th data-options="field:'name',width:100">名称</th>
            <th data-options="field:'gradeName',width:100">所属年级</th>
            <th data-options="field:'subjectname',width:100">所属科目</th>
            <th data-options="field:'statusName',width:100">状态</th>
            <th data-options="field:'createtimeStr',width:150">创建时间</th>
            <th data-options="field:'adminname',width:150">操作人</th>
	    [/@a.datagrid]
	    
	    [@a.toolbar candel=false]
		    <a id="disable" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="myButtonHandler(this, null)">冻结</a>
		    <a id="enable" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="myButtonHandler(this, null)">解冻</a>
        [/@a.toolbar]
	    [@a.tools /]
	    
	</section>
   [@a.addsection]
        <tr>
            <td>名称:</td>
            <td><input type="text" class="easyui-validatebox" name="name" data-options="min:0,required:true" /></td>
        </tr>
   		<tr>
            <td>选择年级:</td>
            <td>
            	<input type="radio" id="courseaddgrade_6" name="grade" value="1" checked/><label for="courseaddgrade_6">六年级</label>
                <input type="radio" id="courseaddgrade_7" name="grade" value="2" /><label for="courseaddgrade_7">七年级</label>
                <input type="radio" id="courseaddgrade_8" name="grade" value="4" /><label for="courseaddgrade_8">八年级</label>
                <input type="radio" id="courseaddgrade_9" name="grade" value="8" /><label for="courseaddgrade_9">九年级</label>
            </td>
        </tr>
   		<tr>
            <td>选择年级:</td>
            <td class="subject">
            </td>
        </tr>
        <tr>
            <td>标题图:</td>
            <td>[@a.imgupload multiple=false imgpath="${photopath}" name="imgdata"/]</td>
    	</tr>
    	<tr>
            <td>出版社:</td>
            <td><input type="text" class="easyui-validatebox" name="press" data-options="min:0" /></td>
        </tr>
    	<tr>
            <td>应用学校:</td>
            <td><input type="text" class="easyui-validatebox" name="useadress" data-options="min:0" /></td>
        </tr>
    	<tr>
            <td>出版时间：</td>
            <td><input name="publishtime" type="text" editable="false" class="easyui-datetimebox" /></td>
        </tr>
        
    [/@a.addsection]
    [@a.editsection ]
        <input type="hidden" name="uid">
        <tr>
            <td>名称:</td>
            <td><input type="text" class="easyui-validatebox" name="name" data-options="min:0,required:true" /></td>
        </tr>
        <tr>
            <td>年级:</td>
            <td>
            	<input type="radio" id="courseeditgrade_1" name="grade" value="1" /><label for="editgrade_1">六年级</label>
                <input type="radio" id="courseeditgrade_2" name="grade" value="2" /><label for="courseeditgrade_2">七年级</label>
                <input type="radio" id="courseeditgrade_4" name="grade" value="4" /><label for="courseeditgrade_4">八年级</label>
                <input type="radio" id="courseeditgrade_8" name="grade" value="8" /><label for="courseeditgrade_8">九年级</label>
            </td>
        </tr>
        <tr>
            <td>选择科目:</td>
            <td class="subject">
            </td>
        </tr>
        <tr>
            <td>图片:</td>
            <td><img id="avatarbig" style="width:150px; height:150px" src="" set-format="${webimg}{0}" set-key="imgoriginal"/>
            	[@a.imgupload multiple=false imgpath="${photopath}" name="imgdata"/]
            </td>
        </tr>
        <tr>
            <td>出版社:</td>
            <td><input type="text" class="easyui-validatebox" name="press" data-options="min:0" /></td>
        </tr>
    	<tr>
            <td>应用学校:</td>
            <td><input type="text" class="easyui-validatebox" name="useadress" data-options="min:0" /></td>
        </tr>
    	<tr>
            <td>出版时间：</td>
            <td><input name="publishtime" type="text" editable="false" class="easyui-datetimebox" /></td>
        </tr>
    [/@a.editsection]
 	[@a.viewsection]
		<tr>
            <td>名称:</td>
            <td><span set-key="name"></span></td>
        </tr>
        <tr>
            <td>年级:</td>
            <td><span set-key="gradeName"></span></td>
        </tr>
        <tr>
            <td>科目:</td>
            <td><span set-key="subjectname"></span></td>
        </tr>
        <tr>
	        <td>课程插图:</td>
	        <td><img src="" set-format="${webimg}{0}" set-key="imgoriginal" style="width: 600px; height: 400px;"/></td>
        </tr>
        <tr>
            <td>操作时间:</td>
            <td><span set-key="updatetimeStr"></span></td>
        </tr>
        <tr>
            <td>操作人:</td>
            <td><span set-key="adminname"></span></td>
        </tr>
	[/@a.viewsection]
	
</body>
</html>