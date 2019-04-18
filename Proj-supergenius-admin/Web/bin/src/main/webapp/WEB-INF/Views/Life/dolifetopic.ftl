[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Admin.ftl" as a]
<html>
<head>
<title>${channelname}</title>
<script type="text/javascript" src="${basejs}/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
$(function() {
	//修正点击add，图片上传按钮不回显的BUG
	$("#btnadd").click(function() {
	   $(window.frames["frameImg1"].document).find("#formImg1").css("display","block");
	   var editor = CKEDITOR.instances["ckeid1"];
       editor.setData("");
    });
    
	$("#btnedit").click(function() {
	$(window.frames["frameImg0"].document).find("#formImg0").css("display","block");
	var row = getSelected(dg);
	//判断类别
	if (row.kind == 0) {
		$("#type_article").prop("checked", "checked"); //表示文章
	} else if (row.kind == 1) {
		$("#type_video").prop("checked", "checked"); //表示视频
	} else {
		$("#type_photo").prop("checked", "checked"); //表示图片
	}
	
	var data=JSON.parse(row.data);
	var nu="  ";
	for(var a in data.accessory) {
		nu=nu+data.accessory[a].fileName+"</br>";
	}
	$(".accessoryName").html(nu);

	var catalogid = "#edit_catalogue"+row.cid //选中模块的id
	$(catalogid).prop("checked", "checked"); //将单选框标记设置成选中
	});
	
});

function datagrid_loadFilter(data) {
	initDGData(data.rows, data.time);
	return data;
}

function initDGData(row, time) {
	console.log(time);
	for(var i in row) {
		if (row[i].istop == 1) {
			row[i].istopName = "是";
		} else {
			row[i].istopName = "否";
		}
		
		if (row[i].cid == 42) {
			if(row[i].status == 0){
				row[i].statusName = "冻结";
			}else if(row[i].examine == 0 && row[i].status == 1){
				row[i].statusName = "待审核";
			}else{
				row[i].statusName = "正常";
			}
		}else{
			if(row[i].status == 1){
				row[i].statusName = "正常";
			}else{
				row[i].statusName = "冻结";
			}
		}
		
		if(row[i].createtimeStr > time){
			row[i].statusName = "未发布";
		}
		row[i].booktimeStr = row[i].createtimeStr;
	}
}

function mytoolbarStateHandler(event, j) {
    var list = getSelections(j.dg); 
    if (list.length > 0) {
        if (list[0].istop == 1){
            $("#stick").linkbutton("disable");
        	$("#unstick").linkbutton("enable");
        } else {
            $("#stick").linkbutton("enable");
        	$("#unstick").linkbutton("disable");
        }
        
        if (list[0].cid == 42) {
			if(list[0].status == 0){
				$("#enable").linkbutton("disable");
        		$("#disable").linkbutton("enable");
            	$("#checktopic").linkbutton("disable");
			}else if(list[0].examine == 0 && list[0].status == 1){
				$("#enable").linkbutton("disable");
		    	$("#disable").linkbutton("enable");
		        $("#checktopic").linkbutton("enable");
			}else{
				$("#enable").linkbutton("disable");
		    	$("#disable").linkbutton("enable");
		        $("#checktopic").linkbutton("disable");
			}
		}else{
	        $("#checktopic").linkbutton("disable");
			if(list[0].status == 1){
				$("#enable").linkbutton("disable");
	        	$("#disable").linkbutton("enable");
			}else{
				$("#enable").linkbutton("enable");
        		$("#disable").linkbutton("disable");
			}
		}
    } else {
        $("#stick").linkbutton("disable");
        $("#unstick").linkbutton("disable");
        $("#enable").linkbutton("disable");
        $("#disable").linkbutton("disable");
        $("#recommend").linkbutton("disable");
        $("#unrecommend").linkbutton("disable");
        $("#checktopic").linkbutton("disable");
    }
}

function myButtonHandler(obj, url) {
		var cid = getSelected(dg).cid;
		var objid = $(obj).attr("id");
		var flag = false;
		if (objid == "stick") {
			$.get('${base}${baseAdminPath}/${site}/ajax/lifetopic/topcount/'+cid, function(result) {
			if (result.count >= 4) {
				err("该模块置顶数量不能超过4个！");
		        return false;
			}
			confirmAjax('置顶该话题', '确定要置顶该话题么？', '${base}${baseAdminPath}/${site}/ajax/lifetopic/enable', "get");
		});
		}
		if (objid == "unstick") {
		    confirmAjax('取消置顶', '确定要取消置顶么？', '${base}${baseAdminPath}/${site}/ajax/lifetopic/disable', "get");
		}
		if (objid == "disable") {
		    confirmPostAjax('冻结该选项', '确定要冻结该话题吗？', '${base}${baseAdminPath}/${site}/ajax/lifetopic/status/0', "post");
		}
		if (objid == "enable") {
		    confirmPostAjax('解冻该选项', '确认要解冻该话题吗？', '${base}${baseAdminPath}/${site}/ajax/lifetopic/status/1', "post");
		}
		if (objid == "checktopic") {
		    confirmPostAjax('审核通过该选项', '审核通过后将作为正式话题可以进行评论,确认审核通过吗？', '${base}${baseAdminPath}/${site}/ajax/lifetopic/examine/1', "post");
		}
}

function confirmAjax(title, question, url) {
	$.messager.confirm(title, question, function(flag) {
		if (flag) {
			ajaxGetSubmit(title,url);
		}
	});
}

function ajaxGetSubmit(title,url) {
	$.get(url, {ids : getSelected(dg).uid}, function(result) {
		if (result.success) {
			getDataGrid(dg).datagrid("reload"); // 重新加载数据表格
			resultHandler(result);
		} else {
			resultHandler(result);
		}
	});
}

function confirmPostAjax(title, question, url) {
	$.messager.confirm(title, question, function(flag) {
		if (flag) {
			ajaxPostSubmit(url);
		}
	});
}
/*重写add的表单提交方法*/ 
function submitHandlerForAdd(obj, dg) {
	$(obj).parents("form").form('submit', {
	    onSubmit: function(param) {
	     if (($("#imguploadinput1").find("input[name='imgdata']").val() == undefined)) {
		        err("您未上传图片！");
		        return false;
		       };
       if ($("#add_title").val().length < 1) {
	        err("您未填写标题！");
	        $("#add_title").focus();
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

function showimglittle(imglittle) {
  if( imglittle != null ){ 
  	return '<img height="20" width="20" src="${userimg}/'+imglittle+'" />';
  } else {
  	return '<img height="20" width="20" src="${baseimg}/imgs/web/noavatar.png" />';
  }
}

</script>
</head>
<body>
    <section>
		[@a.search]
	        <tr>
	        	<td colspan="10">
		        	<label for="name">关键字: </label><input id="name" type="text" name="name" class="search"/>&nbsp;&nbsp;&nbsp;
	        	</td>
	        </tr>
	        <tr>
        		<td>状态：
                    <input type="radio" id="statusall" name="status" value="" checked/><label for="statusall">全部</label>
                    <input type="radio" id="statusone" name="status" value="12" /><label for="statusone">待审核</label>
                    <input type="radio" id="statustwo" name="status" value="1" /><label for="statustwo">正常</label>
                    <input type="radio" id="statusthree" name="status" value="0" /><label for="statusthree">冻结</label>
                </td>
        	</tr>
		    <tr>
	 			<td>所属版块:
	 				<input name="searchcid" type="radio" id="catalogueall" value="all" checked><label for="catalogueall" >全部</label>
	 				[#if catelogueList??]
				    [#assign i=0]
				    [#list catelogueList as item]
				    [#assign i=i+1]
				    	<input name="searchcid" type="radio" id="catalogue${item.cid}" value="${item.cid}" ><label for="catalogue${item.cid}" >${item.name}</label>
					[/#list]
					[/#if]
				</td>
	        </tr>
        	
		[/@a.search]
        [@a.datagrid]
            <th data-options="field:'title',width:20">标题</th>
            <th data-options="field:'imglittle', width:20, formatter:showimglittle">标题图</th>
            <th data-options="field:'author',width:10">发布人</th>
		    <th data-options="field:'cataloguename',width:15">所属板块</th>
            <th data-options="field:'partycount',width:10">参与人数</th>
            <th data-options="field:'statusName',width:10">状态</th>
            <th data-options="field:'updatetimeStr',width:20">操作时间</th>
            <th data-options="field:'adminname',width:20">操作人</th>
        [/@a.datagrid]
        [@a.toolbar canadd=false]
        	<a id="checktopic" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="myButtonHandler(this, null)">审核</a>
	        <a id="disable" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="myButtonHandler(this, null)">冻结</a>
        	<a id="enable" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="myButtonHandler(this, null)">解冻</a>
        [/@a.toolbar]  
        [@a.tools /]
    </section>
		
		[@a.viewsection]
	        <tr><td>标题</td><td><span set-key="title"></span></td></tr>
	        <tr>
		        <td>题目插图:</td>
		        <td><img src="" set-format="${webimg}{0}" set-key="imgoriginal" style="width: 600px; height: 400px;"/></td>
	        </tr>
	        <tr><td>作者</td><td><span set-key="author"></span></td></tr>
	        <tr><td>问题描述</td><td><span set-key="content"></span></td></tr>
	        <tr><td></td><td><span id="don" style="width: 600px;height:400px;"></span></td></tr>
        [/@a.viewsection]
   	    [@a.editsection canuploadfile=true]
	         <tr>
	            <td>标题：</td><input type="hidden" name="uid" />
	            <td><input class="easyui-validatebox" name="title" data-options="required:true" /></td>
	        </tr>
	        <tr>
	            <td>正文:</td>
	            <td>[@a.ckeditor name="content" /]</td>
	        </tr>
    	[/@a.editsection]
</body>
</html>