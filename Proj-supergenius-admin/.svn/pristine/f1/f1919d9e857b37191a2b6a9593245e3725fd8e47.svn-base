[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Admin.ftl" as a]
<html>
<head>
<title>文章管理</title>
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
	//判断是否原创
	if (row.isoriginal == 1) {
		$("#origin_yes").prop("checked", "checked");
	} else {
		$("#origin_no").prop("checked", "checked");
	}
	//判断类别
	if (row.type == 0) {
		$("#type_article").prop("checked", "checked"); //表示文章
	} else if (row.type == 1) {
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
		if (row[i].type == 0) {
			row[i].typeName = "文章";
		} else if (row[i].type == 1) {
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
        [@a.datagrid url="${base}${baseAdminPath}/${site}/ajax/aicontribute/list"]
            <th data-options="field:'title',width:85">标题</th>
            <th data-options="field:'author',width:15">作者</th>
            <th data-options="field:'contact',width:15">联系方式</th>
            <th data-options="field:'statusName',width:10">状态</th>
            <th data-options="field:'cataloguename',width:30">所属板块</th>
            <th data-options="field:'istopName',width:13, align:'center'">是否置顶</th>
            <th data-options="field:'updatetimeStr',width:20">操作时间</th>
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
	        <tr><td>姓名</td><td><span set-key="username"></span></td></tr>
	        <tr><td>联系方式</td><td><span set-key="contact"></span></td></tr>
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
	            <td><img id="avatarbig" style="width:150px; height:150px" src="" set-format="${webimg}{0}" set-key="imgoriginal"/>
	            	[@a.imgupload multiple=false imgpath="${photopath}" name="imgdata"/]
	            </td>
	        </tr>
	        <tr>
	            <td>作者：</td>
	            <td><input class="easyui-validatebox" name="author"/></td>  
	        </tr>
	        <tr>
	            <td>投稿人：</td>
	            <td><input class="easyui-validatebox" name="username" /></td>
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
	            <td>联系方式：</td>            
	            <td><input class="easyui-validatebox" name="contact"/></td>
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