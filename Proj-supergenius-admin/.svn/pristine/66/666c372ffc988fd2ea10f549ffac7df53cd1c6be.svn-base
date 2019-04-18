[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Admin.ftl" as a]
<html>
<head>
<title>用户帖子管理</title>
<script src="http://open.web.meitu.com/sources/xiuxiu.js" type="text/javascript"></script>
<script type="text/javascript" src="${basejs}/ckeditor/ckeditor.js"></script>

<script type="text/javascript">
$(function() {
	//修正点击add，图片上传按钮不回显的BUG
	$("#btnadd").click(function() {
	   var editor = CKEDITOR.instances["ckeid1"];
       editor.setData("");
       $("#imgshow_big").attr("src",'');
	   $("#imgshow").addClass("hd");
    });
    
	$("#btnedit").click(function() {
	var row = getSelected(dg);
	//判断类别
	if (row.kind == 0) {
		$("#type_article").prop("checked", "checked"); //表示文章
	} else if (row.kind == 1) {
		$("#type_video").prop("checked", "checked"); //表示视频
	} else {
		$("#type_photo").prop("checked", "checked"); //表示图片
	}
	
	if (row.isoriginal == 1) {
		$("#origin_yes").prop("checked", "checked"); //表示原创
	} else {
		$("#origin_no").prop("checked", "checked"); //表示非原创
	}

	$(".editcatelogue").removeProp("checked");
	[#if catelogueList??]
	    [#list catelogueList as item]
	    	if(and(row.cid, ${(item.cid)?c})){
	    		$("#edit_catalogue"+${(item.cid)?c}).prop("checked", "checked");
	    	}
		[/#list]
	[/#if]
	});
});

function and(a,b){
    a=a.toString(2).split('').reverse();
    b=b.toString(2).split('').reverse();
    var L=Math.max(a.length,b.length),re=[];
  for(var i=0; i<L; i++){
      re.push(a[i] && b[i] ? ((a[i] == 1 && b[i] == 1) ? 1 : 0) : 0)
  }
  return parseInt(re.reverse().join(''),2);
}

function datagrid_loadFilter(data) {
	initDGData(data.rows, data.time);
	return data;
}

function initDGData(row, time) {
	console.log(time);
	for(var i in row) {
		if (row[i].kind == 0) {
			row[i].typeName = "文章";
		} else if (row[i].kind == 1) {
			row[i].typeName = "视频";
		} else if (row[i].kind == 2){
			row[i].typeName = "图片";
		}else if (row[i].kind == 3){
            row[i].typeName = "帖子";
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
    if(list.length > 1){
	    $("#btnadd").linkbutton("disable");
	    $("#btnedit").linkbutton("disable");
	    $("#btnview").linkbutton("disable");
	 	$("#enable").linkbutton("disable");
	    $("#disable").linkbutton("disable");
	    $("#state_enable0").linkbutton("disable");
	    $("#state_disable0").linkbutton("disable");
	}
}

function myButtonHandler(obj, url) {
		var objid = $(obj).attr("id");
		var flag = false;
		if (objid == "disable") {
		    confirmPostAjax('冻结该选项', '确定要冻结该帖子吗？', '${base}${baseAdminPath}/${site}/ajax/moralnewsarticle/status/0', "post");
		}
		if (objid == "enable") {
		    confirmPostAjax('解冻该选项', '确认要解冻该帖子吗？', '${base}${baseAdminPath}/${site}/ajax/moralnewsarticle/status/1', "post");
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
	var content = CKEDITOR.instances["ckeid1"].getData();
	$(obj).parents("form").form('submit', {
	    onSubmit: function(param) {
       if ($("#add_title").val().length < 1) {
        err("您未填写标题！");
        $("#add_title").focus();
        return false;
       };
       if (content == "") {
        	err("您未填写正文！");
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
                    <input type="radio" id="statusone" name="status" value="1" /><label for="statusone">正常</label>
                    <input type="radio" id="statustwo" name="status" value="0" /><label for="statustwo">冻结</label>
                </td>
        	</tr>
		[/@a.search]
        [@a.datagrid options="fitColumns:true,resizeHandle:'right',pagination:true,singleSelect:false,method:'get'"]
            <th data-options="field:'title',width:80">标题</th>
            <th data-options="field:'author',width:15">作者</th>
            <th data-options="field:'origin',width:15">来源</th>
            <th data-options="field:'statusName',width:10">状态</th>
            <th data-options="field:'createtimeStr',width:21">发布时间</th>
            <th data-options="field:'adminname',width:20">操作人</th>
            <th data-options="field:'istopName',width:100">是否置顶</th>
        [/@a.datagrid]
        [@a.toolbar]
	        <a id="disable" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="myButtonHandler(this, null)">冻结</a>
        	<a id="enable" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="myButtonHandler(this, null)">解冻</a>
        	[@a.state nameenable="置顶" namedisable="取消置顶" statefield="istop" actionenable="${base}${baseAdminPath}/moralnews/ajax/${channel}/istop/1" actiondisable="${base}${baseAdminPath}/moralnews/ajax/${channel}/istop/0"/]
        [/@a.toolbar]  
        [@a.tools /]
    </section>
		
		[@a.viewsection]
	        <tr><td>标题</td><td><span set-key="title"></span></td></tr>
	        <tr><td>作者</td><td><span set-key="author"></span></td></tr>
	        <tr><td>SEO关键字：</td><td><span set-key="keywords"></td></tr>
	        <tr><td>来源</td><td><span set-key="origin"></span></td></tr>
	        <tr><td>类别</td><td><span set-key="typeName"></span></td></tr>
	        <tr><td>定时发布</td><td><span set-key="booktimeStr"></span></td></tr>
	        <tr><td>正文</td><td><span set-key="content"></span></td></tr>
	        <tr><td>置顶</td><td><span set-key="istopName"></span></td></tr>
	        <tr><td></td><td><span id="don" style="width: 600px;height:400px;"></span></td></tr>
        [/@a.viewsection]
   	    [@a.editsection canuploadfile=true]
	         <tr>
	            <td>标题：</td><input type="hidden" name="uid" />
	            <td><input class="easyui-validatebox" name="title" data-options="required:true" /></td>
	        </tr>
	        <tr>
	            <td>作者：</td>
	            <td><input class="easyui-validatebox" name="author"/></td>  
	        </tr>
	        <tr>
	            <td>来源：</td>            
	            <td><input class="easyui-validatebox" name="origin"/></td>
	        </tr>
		    <tr>
	            <td>SEO关键字：</td>
	            <td><input class="easyui-validatebox" name="keywords"/></td>
	        </tr>
	        <tr>
            <td>是否原创：</td>            
            <td>
            <input name="origin_radio" type="radio" id="origin_yes" value="1"><label for="origin_yes">是</label>
            <input name="origin_radio" type="radio" id="origin_no" value="0"><label for="origin_no">否</label>
            </td>
        </tr>
	            <input name="type_radio" type="hidden" id="type_photo" value="3"><label for="type_photo"></label>
		        <input name="cids" type="hidden" class="editcatelogue" id="edit_catalogue256" value="256"><label for="edit_catalogue256"></label>
	        <tr>
	            <td>正文:</td>
	            <td>[@a.ckeditor name="content" /]</td>
	        </tr>
	        <tr>
	            <td>定时发布：</td>
	            <td><input name="booktimeStr" type="text" editable="false" class="easyui-datetimebox" /></td>
	        </tr>
    	[/@a.editsection]
    	
    	[@a.addsection canuploadfile=true onclicksubmit="submitHandlerForAdd(this)"]
         <tr>
            <td>标题：</td><input type="hidden" name="uid" />
            <td><input class="easyui-validatebox" name="title" data-options="required:true" id="add_title"/></td>
        </tr>
        <tr>
            <td>SEO关键字：</td>
            <td><input class="easyui-validatebox" type="text" name="SEOkeywords" /></td>
        </tr>
        <tr>
            <td>作者：</td>
            <td><input class="easyui-validatebox" name="author"/></td>
        </tr>
        <tr>
            <td>来源：</td>            
            <td><input class="easyui-validatebox" name="origin"/></td>
        </tr>
        <tr>
            <td>是否原创：</td>            
            <td>
            <input name="origin_radio" type="radio" id="add_origin_yes" value="1"><label for="add_origin_yes">是</label>
            <input name="origin_radio" type="radio" id="add_origin_no" value="0" checked="checked"><label for="add_origin_no">否</label>
            </td>
        </tr>

            <input name="type_radio" type="hidden" id="add_type_photo" value="3"><label for="add_type_photo"></label>
	    	<input name="cids" set-key="isFashionLife" type="hidden" id="catalogue256" value="256"><label for="catalogue256"></label>
        <tr>
            <td>正文:</td>
            <td>[@a.ckeditor name="content" /]</td>
        </tr>
        <tr>
            <td>定时发布：</td>
            <td><input name="publishtime" type="text" editable="false" class="easyui-datetimebox" /></td>
        </tr>
    	[/@a.addsection]
</body>
</html>