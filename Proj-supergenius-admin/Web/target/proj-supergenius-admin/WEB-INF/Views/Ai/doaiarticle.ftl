[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Admin.ftl" as a]
<html>
<head>
<title>文章管理</title>
<script src="http://open.web.meitu.com/sources/xiuxiu.js" type="text/javascript"></script>
<script type="text/javascript" src="${basejs}/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
	function showXiuXiu(altcontent,imgbox,imgname,imgshow,imgshow_big){
	  /*第1个参数是加载编辑器div容器，第2个参数是编辑器类型，第3个参数是div容器宽，第4个参数是div容器高*/
	  xiuxiu.setLaunchVars("uploadBtnLabel", "上传");
	  xiuxiu.setLaunchVars("titleVisible", "0");
	  xiuxiu.setLaunchVars("cropPresets", "3:2");
	  xiuxiu.setLaunchVars("customMenu", ["edit"]);
	  xiuxiu.embedSWF(altcontent,1,"100%","400px");
	  xiuxiu.setUploadURL("${base}${baseAdminPath}/${site}/ajax/${channel}/uploadimg");//修改为您自己的图片上传接口
	  xiuxiu.setUploadType(2);
	  xiuxiu.setUploadDataFieldName("fileimg");
	  xiuxiu.onInit = function (){
	    xiuxiu.loadPhoto("");//默认待处理图片
	  }
	  xiuxiu.onUploadResponse = function (data){
		$(imgbox).html("<span id ="+ altcontent +"></span>");
		$(imgname).val(data);
		var origin = data.split(",")[3];
		$(imgshow).removeClass("hd");
		$(imgshow_big).attr("src",'${webimg}' + origin);
	  }
	  xiuxiu.onClose = function (){
  			$(imgbox).html("<span id ="+ altcontent +"></span>");
	  }
	}
	$(document).ready(function(){
		$("#editupload").on("click",function(){
			showXiuXiu("altcontentedit","#imgboxedit","#contributeimgedit","#imgshowedit","#imgshow_bigedit");
		});
	})
</script>
<script type="text/javascript">
$(function() {
	//修正点击add，图片上传按钮不回显的BUG
	$("#btnadd").click(function() {
	   var editor = CKEDITOR.instances["ckeid1"];
       editor.setData("");
    });
    
	$("#btnedit").click(function() {
	var row = getSelected(dg);
	//判断是否原创
	if (row.isoriginal == 1) {
		$("#origin_yes").prop("checked", "checked");
	} else {
		$("#origin_no").prop("checked", "checked");
	}
	//判断类别
	if (row.kind == 0) {
		$("#type_article").prop("checked", "checked"); //表示文章
	} else if (row.kind == 1) {
		$("#type_video").prop("checked", "checked"); //表示视频
	} else {
		$("#type_photo").prop("checked", "checked"); //表示图片
	}

	var catalogid = "#catalogue"+row.cid //选中模块的id
	$(catalogid).prop("checked", "checked"); //将单选框标记设置成选中
	});
});

function initDGData(row) {
	for(var i in row) {
		if (row[i].istop == 1) {
			row[i].istopName = "是";
		} else {
			row[i].istopName = "否";
		}
		
		if (row[i].isoriginal == 1) {
			row[i].isoriginalName = "是";
		} else {
			row[i].isoriginalName = "否";
		}
		if (row[i].kind == 0) {
			row[i].typeName = "文章";
		} else if (row[i].kind == 1) {
			row[i].typeName = "视频";
		} else {
			row[i].typeName = "图片";
		}
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
        if (list[0].status == 1){
            $("#enable").linkbutton("disable");
        	$("#disable").linkbutton("enable");
        } else {
            $("#enable").linkbutton("enable");
        	$("#disable").linkbutton("disable");
        }
    } else {
        $("#stick").linkbutton("disable");
        $("#unstick").linkbutton("disable");
        $("#enable").linkbutton("disable");
        $("#disable").linkbutton("disable");
        $("#btnrecommend").linkbutton("disable");
    }
    if(list.length > 1){
	    $("#btnedit").linkbutton("disable");
	    $("#btnview").linkbutton("disable");
	    $("#stick").linkbutton("disable");
	    $("#unstick").linkbutton("disable");
	 	$("#enable").linkbutton("disable");
	    $("#disable").linkbutton("disable");
        $("#btnrecommend").linkbutton("disable");
	}
}

function myButtonHandler(obj, url) {
		var objid = $(obj).attr("id");
		var flag = false;
		if (objid == "stick") {
			$.get('${base}${baseAdminPath}/${site}/ajax/${channel}/topcount', function(result) {
			if (result.count >= 4) {
				err("置顶数量超过4个！");
		        return false;
			}
			confirmAjax('置顶该文章', '确定要置顶该文章么？', '${base}${baseAdminPath}/${site}/ajax/${channel}/enable', "get");
		});
		}
		if (objid == "unstick") {
		    confirmAjax('取消置顶', '确定要取消置顶么？', '${base}${baseAdminPath}/${site}/ajax/${channel}/disable', "get");
		}
		if (objid == "disable") {
		    confirmPostAjax('冻结该选项', '确定要冻结该文章吗？', '${base}${baseAdminPath}/${site}/ajax/${channel}/status/0', "post");
		}
		if (objid == "enable") {
		    confirmPostAjax('解冻该选项', '确认要解冻该文章吗？', '${base}${baseAdminPath}/${site}/ajax/${channel}/status/1', "post");
		}
}

function confirmAjax(title, question, url) {
	$.messager.confirm(title, question, function(flag) {
		if (flag) {
			ajaxGetSubmit(url);
		}
	});
}

function ajaxGetSubmit(url) {
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
		<table>
			<tr>
			   <td>文章总数：</td>
			   <td><span>${count}</span></td>
			<tr>        
		</table>
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
	        <tr>
                <td>是否置顶：
                	<input type="radio" id="statusall" name="istop" value="" checked="checked"/><label for="statusone">全部</label>
                    <input type="radio" id="statusone" name="istop" value="1" /><label for="statusone">是</label>
                    <input type="radio" id="statustwo" name="istop" value="0" /><label for="statustwo">否</label>
                </td>
        	</tr>
		[/@a.search]
        [@a.datagrid options="fitColumns:true,resizeHandle:'right',pagination:true,singleSelect:false,method:'get'"]
            <th data-options="field:'title',width:100">标题</th>
            <th data-options="field:'author',width:15">作者</th>
            <th data-options="field:'statusName',width:10">状态</th>
            <th data-options="field:'cataloguename',width:15">所属板块</th>
            <th data-options="field:'istopName',width:13,align:'center'">是否置顶</th>
            <th data-options="field:'createtimeStr',width:20">发布时间</th>
            <th data-options="field:'adminname',width:20">操作人</th>
        [/@a.datagrid]
        [@a.toolbar canadd=false]
	        <a id="stick" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="myButtonHandler(this, null)">置顶</a>
	        <a id="unstick" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="myButtonHandler(this, null)">取消置顶</a>
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
	        <tr><td>标签</td><td><span set-key="tags"></span></td></tr>
	        <tr><td>来源</td><td><span set-key="origin"></span></td></tr>
	        <tr><td>是否置顶</td><td><span set-key="istopName"></span></td></tr>
	        <tr><td>是否原创</td><td><span set-key="isoriginalName"></span></td></tr>
	        <tr><td>类别</td><td><span set-key="typeName"></span></td></tr>
	        <tr><td>所属板块</td><td><span set-key="cataloguename"></span></td></tr>
	        <tr><td>摘要</td><td><span set-key="summary"></span></td></tr>
	        <tr><td>正文</td><td><span set-key="content"></span></td></tr>
	        <tr><td></td><td><span id="don" style="width: 600px;height:400px;"></span></td></tr>
        [/@a.viewsection]
   	    [@a.editsection canuploadfile=true]
	         <tr>
	            <td>标题：</td><input type="hidden" name="uid" />
	            <td><input class="easyui-validatebox" name="title" data-options="required:true" /></td>
	        </tr>
	        <tr>
	            <td>标题图:</td>
	            <td>
	            	<a id="editupload" href="javascript:void(0);" class="title_3_a">点此上传</a><br>
			        <div id="imgboxedit">
			        	<span id="altcontentedit"></span>
			        </div>
			        <div class="" id="imgshowedit" style="margin-left: 175px;">
			        	<img id="imgshow_bigedit" src='' set-format="${webimg}{0}" set-key="imglittle"/>
			        </div>
			        <input id="contributeimgedit" name="contributeimg" type="text" value="" class="hd"/>
	            </td>
	        </tr>
	        <tr>
	            <td>作者：</td>
	            <td><input class="easyui-validatebox" name="author"/></td>  
	        </tr>
	        <tr>
	            <td>标签：</td>
	            <td><input class="easyui-validatebox" name="tags" disabled="disabled"/></td>
	        </tr>
	        <tr>
	            <td>SEO关键字：</td>
	            <td><input class="easyui-validatebox" name="keywords"/></td>
	        </tr>
	        <tr>
	            <td>来源：</td>            
	            <td><input class="easyui-validatebox" name="origin"/></td>
	        </tr>
	        <tr>
            <td>是否原创：</td>            
            <td>
            <input name="origin_radio" type="radio" id="origin_yes" value="1"><label for="origin_yes">是</label>
            <input name="origin_radio" type="radio" id="origin_no" value="0"><label for="origin_no">否</label>
            </td>
        </tr>
	        <tr>
	            <td>类别：</td>            
	            <td>
	            <input name="type_radio" type="radio" id="type_article" value="0"><label for="type_article">文章</label>
	            <input name="type_radio" type="radio" id="type_video" value="1"><label for="type_video">视频</label>
	            <input name="type_radio" type="radio" id="type_photo" value="2"><label for="type_photo">图片</label>
	            </td>
	        </tr>
	        <tr>
	 			<td>版块:</td>
 				<td>
 				[#if catelogueList??]
				    [#list catelogueList as item]
				    	[#if item.cid>10]
				    		<input name="add_cataloguetype" type="radio" id="catalogue${item.cid}" value="${item.cid}"><label for="catalogue${item.cid}">${item.name}</label>
						[/#if]
					[/#list]
				[/#if]
				</td>
	        </tr>
	        <tr>
	            <td>摘要：</td>            
	            <td>
	            <textarea class="easyui-validatebox" name="summary" rows="5" cols="60"></textarea>
	            </td>
	        </tr>
	        <tr>
	            <td>正文:</td>
	            <td>[@a.ckeditor name="content" /]</td>
	        </tr>
    	[/@a.editsection]
</body>
</html>