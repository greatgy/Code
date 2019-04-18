[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Admin.ftl" as a]
<html>
<head>
<title>${channelname}</title>
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
		$("#addupload").on("click",function(){
			showXiuXiu("altcontent","#imgbox","#contributeimg","#imgshow","#imgshow_big");
		});
		$("#editupload").on("click",function(){
			showXiuXiu("altcontentedit","#imgboxedit","#contributeimgedit","#imgshowedit","#imgshow_bigedit");
		});
	})
</script>
<script type="text/javascript">
   var commendstate = 0;
$(function() {
	checkRecommend("recommend");
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
		$("#origin_yes").prop("checked", "checked"); //表示图片
	} else {
		$("#origin_no").prop("checked", "checked"); //表示图片
	}
	

	var catalogid = "#catalogue"+row.cid //选中模块的id
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
		if (row[i].isrecommend == 1) {
			row[i].isrecommendName = "是";
		} else {
			row[i].isrecommendName = "否";
		}
		
		if (row[i].kind == 0) {
			row[i].typeName = "文章";
		} else if (row[i].kind == 1) {
			row[i].typeName = "视频";
		} else {
			row[i].typeName = "图片";
		}
		
		if (row[i].ismember == 0) {
			row[i].memberName = "用户";
		} else if(row[i].ismember == 1){
			row[i].memberName = "会员";
		}else{
			row[i].memberName = "管理员";
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
        if (list[0].status == 1){
            $("#enable").linkbutton("disable");
        	$("#disable").linkbutton("enable");
        } else {
            $("#enable").linkbutton("enable");
        	$("#disable").linkbutton("disable");
        }
        if (commendstate == 1) {
        	if (list[0].isrecommend == 1){
	        	$("#unrecommend").linkbutton("enable");
	        } else {
	        	$("#unrecommend").linkbutton("disable");
	        }
        } else {
        	if (list[0].isrecommend == 1){
	            $("#recommend").linkbutton("disable");
	        	$("#unrecommend").linkbutton("enable");
	        } else {
	            $("#recommend").linkbutton("enable");
	        	$("#unrecommend").linkbutton("disable");
	        }
        }
    } else {
        $("#stick").linkbutton("disable");
        $("#unstick").linkbutton("disable");
        $("#enable").linkbutton("disable");
        $("#disable").linkbutton("disable");
        $("#recommend").linkbutton("disable");
        $("#unrecommend").linkbutton("disable");
    }
     if (list.length > 1){
        $("#disable").linkbutton("disable");
        $("#btnedit").linkbutton("disable");
        $("#stick").linkbutton("disable");
        $("#unstick").linkbutton("disable");
        $("#enable").linkbutton("disable");
        $("#disable").linkbutton("disable");
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
			confirmAjax('置顶该文章', '确定要置顶该文章么？', '${base}${baseAdminPath}/${site}/ajax/financearticle/enable', "get");
		});
		}
		if (objid == "unstick") {
		    confirmAjax('取消置顶', '确定要取消置顶么？', '${base}${baseAdminPath}/${site}/ajax/financearticle/disable', "get");
		}
		if (objid == "disable") {
		    confirmPostAjax('冻结该选项', '确定要冻结该文章吗？', '${base}${baseAdminPath}/${site}/ajax/financearticle/status/0', "post");
		}
		if (objid == "enable") {
		    confirmPostAjax('解冻该选项', '确认要解冻该文章吗？', '${base}${baseAdminPath}/${site}/ajax/financearticle/status/1', "post");
		}
		if (objid == "recommend") {
			$.get('${base}${baseAdminPath}/${site}/ajax/${channel}/recommendcount', function(result) {
			if ("${channel}" == "economics") {
				if (result.count >= 4) {
					commendstate = 1;
					$("#recommend").linkbutton("disable");
					return false;
				}
			} else {
				if (result.count >= 1) {
					commendstate = 1;
					$("#recommend").linkbutton("disable");
					return false;
				}
			}
			});
		    confirmAjax('推荐该文章', '推荐后将在首页显示该篇文章，确定要推荐吗？', '${base}${baseAdminPath}/${site}/ajax/${channel}/recommend', "get");
		}
		if (objid == "unrecommend") {
		    confirmAjax('取消推荐', '取消推荐后，该篇文章将不在显示在首页，确定要取消推荐吗？', '${base}${baseAdminPath}/${site}/ajax/${channel}/unrecommend', "get");
		}
}

function checkRecommend(e) {
	if (e == "recommend") {
		 $.get('${base}${baseAdminPath}/${site}/ajax/${channel}/recommendcount', function(result) {
			if ("${channel}" == "economics") {
				if (result.count >= 4) {
					commendstate = 1;
					$("#recommend").linkbutton("disable");
				}
			} else {
				if (result.count >= 1) {
					commendstate = 1;
					$("#recommend").linkbutton("disable");
				}
			}
		});
	} else {
		$.get('${base}${baseAdminPath}/${site}/ajax/${channel}/recommendcount', function(result) {
			if ("${channel}" == "economics") {
				if (result.count < 4) {
					commendstate = 0;
					$("#recommend").linkbutton("enable");
				}
			} else {
				if (result.count <= 1) {
					commendstate = 0;
					$("#recommend").linkbutton("enable");
				}
			}
		});
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
			if (title == '取消推荐') {
				checkRecommend("unrecommend");
			}
			if (title == '推荐该文章') {
				checkRecommend("recommend");
			}
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
	     if (($("#imgshow_big").attr("src") == "")) {
        err("您未上传图片！");
        return false;
       };
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
                    <input type="radio" id="statusthree" name="status" value="10" /><label for="statusthree">未发布</label>
                </td>
        	</tr>
        	[#if channel != 'financearticle']
	        <tr>
                <td>是否置顶：
                	<input type="radio" id="statusall" name="istop" value="" checked="checked"/><label for="statusone">全部</label>
                    <input type="radio" id="statusone" name="istop" value="1" /><label for="statusone">是</label>
                    <input type="radio" id="statustwo" name="istop" value="0" /><label for="statustwo">否</label>
                </td>
        	</tr>
        	<tr>
                <td>是否推荐：
                	<input type="radio" id="recommendall" name="isrecommend" value="" checked="checked"/><label for="recommendone">全部</label>
                    <input type="radio" id="recommendone" name="isrecommend" value="1" /><label for="recommendone">是</label>
                    <input type="radio" id="recommendtwo" name="isrecommend" value="0" /><label for="recommendtwo">否</label>
                </td>
        	</tr>
        	[/#if]
		[/@a.search]
        [@a.datagrid options="fitColumns:true,resizeHandle:'right',pagination:true,singleSelect:false,method:'get'"]
            <th data-options="field:'title',width:80">标题</th>
            [#if channel == 'economics']
            <th data-options="field:'publisher',width:20">发布人</th>
            [#else]
            <th data-options="field:'author',width:20">作者</th>
            [/#if]
            <th data-options="field:'origin',width:15">来源</th>
            <th data-options="field:'cidName',width:30">板块</th>
            <th data-options="field:'memberName',width:10">用户类型</th>
            <th data-options="field:'score',width:10">积分</th>
            [#if channel != 'financearticle']
            <th data-options="field:'istopName',width:10,align:'center'">是否置顶</th>
            <th data-options="field:'isrecommendName',width:10,align:'center'">是否推荐</th>
            [/#if]
            <th data-options="field:'statusName',width:10">状态</th>
            <th data-options="field:'createtimeStr',width:24">发布时间</th>
            <th data-options="field:'adminname',width:20">操作人</th>
        [/@a.datagrid]
        [@a.toolbar]
        	[#if channel != 'financearticle']
	        <a id="stick" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="myButtonHandler(this, null)">置顶</a>
	        <a id="unstick" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="myButtonHandler(this, null)">取消置顶</a>
	        <a id="recommend" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="myButtonHandler(this, null)">推荐</a>
        	<a id="unrecommend" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="myButtonHandler(this, null)">取消推荐</a>
        	[/#if]
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
	        <tr><td>SEO关键字：</td><td><span set-key="keywords"></td></tr>
	        <tr><td>来源</td><td><span set-key="origin"></span></td></tr>
	        <tr><td>是否置顶</td><td><span set-key="istopName"></span></td></tr>
	        <tr><td>类别</td><td><span set-key="typeName"></span></td></tr>
	        <tr><td>所属板块</td><td><span set-key="cidName"></span></td></tr>
	        <tr><td>摘要</td><td><span set-key="summary"></span></td></tr>
	        <tr><td>定时发布</td><td><span set-key="booktimeStr"></span></td></tr>
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
		    		<input name="cids" set-key="isEconomics" type="checkbox" id="catalogue10" value="1"><label for="catalogue10">财经资讯</label>
		    		<input name="cids" set-key="isInvest" type="checkbox" id="catalogue11" value="2"><label for="catalogue11">投资资讯</label>
		    		<input name="cids" set-key="isMerger" type="checkbox" id="catalogue12" value="4"><label for="catalogue12">并购资讯</label>
		    		<input name="cids" set-key="isRisk" type="checkbox" id="catalogue13" value="8"><label for="catalogue13">风险资讯</label>
		    		<input name="cids" set-key="isTechnology" type="checkbox" id="catalogue14" value="16"><label for="catalogue14">科技资讯</label>
		    		<input name="cids" set-key="isGold" type="checkbox" id="catalogue15" value="32"><label for="catalogue15">醒世金语</label>
		    		<input name="cids" set-key="isEntrepreneur" type="checkbox" id="catalogue16" value="64"><label for="catalogue16">企业家</label>
		    		<input name="cids" set-key="isProfession" type="checkbox" id="catalogue17" value="128"><label for="catalogue17">职业经理人</label>
		    		<input name="cids" set-key="isNightwords" type="checkbox" id="catalogue18" value="256"><label for="catalogue18">职场夜话</label>
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
	        <tr>
	            <td>积分：</td>
	            <td><input class="easyui-numberbox" name="score" data-options="required:true"/></td>
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
            <td>标题图:</td>
            [#-- <td class="addimgdata">[@a.imgupload multiple=false imgpath="${photopath}" name="imgdata"/]</td> --]
            <td>
	            <a id="addupload" href="javascript:void(0);" class="title_3_a">点此上传</a><br>
		        <div id="imgbox">
		        	<span id="altcontent"></span>
		        </div>
		        <div class="hd" id="imgshow" style="margin-left: 175px;">
		        	<img id="imgshow_big" src=''/>
		        </div>
		        <input id="contributeimg" name="contributeimg" type="text" value="" class="hd"/>
	        </td>
    	</tr>
    	<tr>
            <td>标签：</td>
            <td><input class="easyui-validatebox" type="text" name="labelcontent" placeholder="添加多个请以空格隔开" /></td>
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
        <tr>
            <td>类别：</td>            
            <td>
            <input name="type_radio" type="radio" id="add_type_article" value="0" checked="checked"><label for="add_type_article">文章</label>
            <input name="type_radio" type="radio" id="add_type_video" value="1"><label for="add_type_video">视频</label>
            <input name="type_radio" type="radio" id="add_type_photo" value="2"><label for="add_type_photo">图片</label>
            </td>
        </tr>
        [#if channel == 'financearticle']
         <tr>
			<td>版块:</td>
			<td>
	    		<input name="cids" set-key="isEconomics" type="checkbox" id="catalogue1" value="1" checked><label for="catalogue1">财经资讯</label>
	    		<input name="cids" set-key="isInvest" type="checkbox" id="catalogue2" value="2"><label for="catalogue2">投资资讯</label>
	    		<input name="cids" set-key="isMerger" type="checkbox" id="catalogue4" value="4"><label for="catalogue4">并购资讯</label>
	    		<input name="cids" set-key="isRisk" type="checkbox" id="catalogue8" value="8"><label for="catalogue8">风险资讯</label>
	    		<input name="cids" set-key="isTechnology" type="checkbox" id="catalogue16" value="16"><label for="catalogue16">科技资讯</label>
	    		<input name="cids" set-key="isGold" type="checkbox" id="catalogue32" value="32"><label for="catalogue32">醒世金语</label>
	    		<input name="cids" set-key="isEntrepreneur" type="checkbox" id="catalogue64" value="64"><label for="catalogue64">企业家</label>
	    		<input name="cids" set-key="isProfession" type="checkbox" id="catalogue128" value="128"><label for="catalogue128">职业经理人</label>
	    		<input name="cids" set-key="isNightwords" type="checkbox" id="catalogue256" value="256"><label for="catalogue256">职场夜话</label>
			</td>
	      </tr>
	    [/#if]
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
        <tr>
            <td>定时发布：</td>
            <td><input name="publishtime" type="text" editable="false" class="easyui-datetimebox" /></td>
        </tr>
    	[/@a.addsection]
</body>
</html>