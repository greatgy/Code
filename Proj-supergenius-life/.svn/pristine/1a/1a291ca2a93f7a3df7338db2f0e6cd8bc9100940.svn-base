[#ftl]
<script type="text/javascript">
<!--
	var g = {};
	g.base = "${base}";
	g.baseuri = g.base + "${baseAdminPath}";
	$(function(){
		setBaseCssForInput();
		setAnchorHref();
		initPageVariables();
		new_initPageVariables();
	});

	function new_initPageVariables(){
		initRightPage();
	}
	
	function opensearchbox(){//搜索框切换
		$("#searchbox").toggleClass("hd");
	}

	function initPageVariables() {
		var msg = "${alertmsg!}";
		if(msg != "") {
			alert(msg);
		}
		$("#btnreset").click(function(){window.history.go(-1);});
		$(".goback").click(function(){window.history.go(-1);});
	}
	function submit(obj){
        if($(obj).parents("form").form("validate")) {
        	$(obj).parents("form").submit();
        }
    }
    
    // dlgid是 dialog的id，使用"#dlg"这样的格式
    function opendlg(dlgid){
		$(dlgid).dialog("open");
		setPostion(dlgid);
	}
	
	// 控件的id
	function setPostion(dlgid){
		$(dlgid).dialog('move',{    
		  top:20    
		}); 
	}
	
	//面包屑
	$(function(){
		try {
			$("[data-crumb]").breadcrumb({target:$(".breadcrumbs", window.parent.frames.topbar.document.body),crumbdivider:'<div class="breadcrumb_divider"></div>',crumbhtml:'<a href="javascript:void(0)" data-scrumb="{index:{index}}">{title}</a>'});
		} catch(e){}
	});

	// gridid:数据表格id  dlgid:窗口id
	function openeditdlg(gridid,dlgid,url){
		var rows = $(gridid).datagrid("getSelections");
		if(rows.length == 0){
			$.messager.alert('消息', '请选择一条要编辑的数据。');
			return;
		}
		if(rows.length > 1){
			$.messager.alert('消息', '只能选择一条要编辑的数据。');
			return;
		}
		var id;
		if(rows[0].uid == "" || rows[0].uid == undefined){
			id = rows[0].oid;
		}else{
			id = rows[0].uid;
		}
		$(dlgid).dialog("open");
		setPostion(dlgid);
		$(dlgid).dialog('refresh', url+id);  
	}

	// 提交表单，并且在成功后把窗口关闭，重新加载数据表格
	// formid:表单id  dlgid:窗口id   gridid:数据表格id
	function submitForm(formid, dlgid, gridid){
		$(formid).form('submit', {
			onSubmit: function(){
				return $(this).form('validate');// 返回false终止表单提交
			},
			success: function(data){
				if(data){
					$(dlgid).dialog("close"); // 关闭添加页面
					$(gridid).datagrid("reload"); // 重新加载数据表格
					$(formid).form('clear'); // 清除表格数据
					clearCkeditor();
					$.messager.alert('消息', '操作成功！');
				}else{
					$.messager.alert('消息', '操作失败！');
				}
			}
		});
	}
	
	// 清空所有的ckeditor的富文本编辑器
	function clearCkeditor(){
		var id = "";
		var jsfunc = "";
		$(".cke_skin_v2").each(function(){
			id = $(this).attr("id");
			jsfunc = "CKEDITOR.instances."+id.substring(4)+".setData('')";
			eval(jsfunc);
		});
	}
	
	//删除对话框
	function confirmdelete(datagrid,url){//删除对话框
	  var array = $(datagrid).datagrid('getSelections');
	  if(array.length == 0) {
	  	$.messager.alert('消息', '请先选择要删除的条目！');
	  } else {
	  	$.messager.confirm('确认删除', '你确定要删除选中的条目吗？', function(result){
		if (result){
			var ids = "";
			for(var i = 0; i < array.length; i++){
				 if(i < array.length - 1){
				 	 if(array[i].uid == "" || array[i].uid == undefined){
				 	 	ids = array[i].oid + "," + ids;
				 	 } else {
				 	 	ids = array[i].uid + "," + ids;
				 	}
				 } else {
				  	 if(array[i].uid == "" || array[i].uid == undefined){
				 	 	ids = array[i].oid + "," + ids;
				 	 } else {
				 	 	ids = array[i].uid + "," + ids;
				 	}
				 }
			}
			$.ajax({  
	            type: "GET",  
	            url: url,  
	            data:{id:ids},
	            success: function (result) {  
					$(datagrid).datagrid("reload");// 重新加载数据表格
	            }  
	    	});  
		  }
	   });
	 }
		 
	} 
//-->
</script>
