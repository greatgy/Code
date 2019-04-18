[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Admin.ftl" as a]
<html>
<head>
<title>${projectchannelname}</title>
<script type="text/javascript" src="${basejs}/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
<!--
var isedit = false;
// 点击添加文章	
function addArticle(pisedit){
	isedit = pisedit;
	$("#addarticle").window("open");
}

// 点击添加
function articleok(){
	var edit = "";
	if(isedit) {
		edit = "edit";			
	}
	var row = {};
	row.title = $("input[name=atitle]").val();
	row.origin = $("input[name=aorigin]").val();
	row.href = $("input[name=ahref]").val();
	appendRow("#dgarticle" + edit, row);
	cancelarticlewin();
}

//添加完成后，清空input，关掉窗口
function cancelarticlewin(){
	$("#atitle").attr("value","");
	$("#aorigin").attr("value","");
	$("#ahref").attr("value","");
	$("#addarticle").window("close");
}

// 在dg中追加row
function appendRow(dg, row){
	$(dg).datagrid('appendRow',{
		title:row.title,
		origin:row.origin,
		href:row.href
	});
}

//点击提交添加项目表单
function mySubmitHandler(dg, isedit){
	var articlerows = "";
	if(isedit){
	 	articlerows = getRows("#dgarticleedit");
	}else{
		articlerows =  getRows("#dgarticle")
	}
	if(articlerows.length != 0){
		insertArticles(articlerows, isedit);
	}
	submitHandler(dg);
	if(isedit){
		$("#editarticles").html("");// 清空暂存域
	}else{
		$("#articles").html("");// 清空暂存域
	}
	
}

// 添加页面把添加的文章放到input隐藏域中
function insertArticles(articlerows, isedit){
	var inputhtml;
	for(var i=0; i<articlerows.length; i++){
		var avalue = articlerows[i].title + "TPI#" + articlerows[i].origin + "TPI#" + articlerows[i].href;
		if(isedit){	
			var inputhtml = "<input name='editarticles' type='hidden' value='" +avalue+ "'/>";
			$("#editarticles").append(inputhtml);
		}else{
			var inputhtml = "<input name='articles' type='hidden' value='" +avalue+ "'/>";
			$("#articles").append(inputhtml);
		}
	}
}
	
// 删除行
function deleterow(dg) {
	var rows = getSelections(dg);
	if(rows.length == 0) {
		warn("请选择文章");
		return;
	}
	$.messager.confirm('确认','您确认想要删除记录吗？',function(r){
	    if (r){    
	        var ids = [];
			for(var i=0;i<rows.length;i++){
				ids.push(rows[i].uid);		
			}
			var index = -1;
			for(var i=0; i<rows.length; i++){
				index = $(dg).datagrid("getRowIndex", rows[i]);
				$(dg).datagrid("deleteRow", index);
			}  
	    }    
	});  
}

//编辑时初始化
function initFormData(form, data) {
	form.form('load', data);
	form.set(data);
	if(data.relatedarticles){
		$("#dgarticleedit").datagrid("loadData", data.relatedarticles);
	}
}
	
function initDGData(row) {
	for(var i in row) {
		if(row[i].istop) {
			row[i].istopName = "置顶";
		} else {
			row[i].istopName = "未置顶";
		}
		if(row[i].isrecommend) {
			row[i].isrecommendName = "是";
		} else {
			row[i].isrecommendName = "否";
		}
		if(row[i].ispublic) {
			row[i].ispublicName = "已公开";
		} else {
			row[i].ispublicName = "未公开";
		}
		if(row[i].status == 1) {
			row[i].statusName = "已发布";
		} else {
			row[i].statusName = "未发布";
		}
		if(row[i].state == 1) {
			row[i].stateName = "已付款";
		} else {
			row[i].stateName = "未付款";
		}
	}
}

//特批免费
 function specialAudit(dg){
        $.messager.confirm('警告','您是否将该机构推荐项目特批为免费',function(r){    
            if (r){    
                var rows = getSelections(dg);
                if(rows.length == 0) {
                   alert("请选择要特批的项目");
                   return;
                }
                var idarray = new Array();
                for(var i=0;i<rows.length;i++){
                    idarray.push(rows[i].uid);
                }
                $.post("${base}${baseAdminPath}/ajax/project/specialaudit?ids=" + idarray, function(data){
                    $("#dg").datagrid("reload");
                    alert("特批成功");
                });
            }    
        });  
    }
//-->
</script>
</head>
<body>
	<section>
		[@a.search]
	        <tr>
	            <td><label for="sname">项目名称：</label><input id="sname" type="text" name="name" /></td>
	            <td><label for="snumber">项目编号：</label><input id="snumber" type="text" name="number" /></td>
	        </tr>	
        	<tr>
	            <td>状态: 
		            <label for="sall">全部</label><input id="sall" name="status" value="" type="radio" class="radio" />
		            <label for="senable">发布</label><input id="senable" name="status" value="1" type="radio" class="radio" />
		            <label for="sdisable">未发布</label><input id="sdisable" name="status" value="0" type="radio" class="radio" />
	            </td>
	        </tr>
        	<tr>
	        	<td>置顶: 
	        		<label for="sistopall">全部</label><input id="sistopall" name="istop" value="" type="radio" class="radio" />
		            <label for="sistop">置顶</label><input id="sistop" name="istop" value="true" type="radio" class="radio" />
		            <label for="suntop">未置顶</label><input id="suntop" name="istop" value="false" type="radio" class="radio" />
	        	</td>
            </tr>
            <tr>
	        	<td>推荐到首页: 
	        		<label for="sisrecommendall">全部</label><input id="sisrecommendall" name="isrecommend" value="" type="radio" class="radio" />
		            <label for="sisrecommend">推荐到首页显示</label><input id="sisrecommend" name="isrecommend" value="true" type="radio" class="radio" />
		            <label for="sunrecommend">未推荐到首页显示</label><input id="sunrecommend" name="isrecommend" value="false" type="radio" class="radio" />
	        	</td>
            </tr>
            <tr>
	        	<td>是否公开: 
	        		<label for="sispublicall">全部</label><input id="sispublicall" name="ispublic" value="" type="radio" class="radio" />
		            <label for="sispublic">是</label><input id="sispublic" name="ispublic" value="true" type="radio" class="radio" />
		            <label for="sunpublic">否</label><input id="sunpublic" name="ispublic" value="false" type="radio" class="radio" />
	        	</td>
            </tr>
            <tr>
	        	<td>魂牵梦绕: 
	        		<label for="sischerishedall">全部</label><input id="sischerishedall" name="ischerished" value="" type="radio" class="radio" />
		            <label for="sischerished">是</label><input id="sischerished" name="ischerished" value="true" type="radio" class="radio" />
		            <label for="suncherished">否</label><input id="suncherished" name="ischerished" value="false" type="radio" class="radio" />
	        	</td>
            </tr>
	        <tr>
				<td>开始创建时间: <input id="timestart" name="createtimestart" type="text" data-options="editable:false" class="easyui-datebox" /></td>
				<td>结束创建时间: <input id="timeend" name="createtimeend" type="text" editable="false" class="easyui-datebox" /></td>
		 	</tr>
		 	
		[/@a.search]
		
		[@a.datagrid title="${projectchannelname}列表" url="${base}${baseAdminPath}/ajax/${channel}/list/${projectchannel}"]
			<th data-options="field:'number',width:150">编号</th>
			<th data-options="field:'name',width:100">名称</th>
			<th data-options="field:'typeName',width:100">并购类型</th>
			<th data-options="field:'stateName',width:100">进度</th>
			<th data-options="field:'istopName',width:100">是否置顶</th>
			<th data-options="field:'isrecommendName',width:100">是否在首页显示</th>
			<th data-options="field:'statusName',width:100">状态</th>
			<th data-options="field:'createtimeStr',width:200">创建时间</th>
        [/@a.datagrid]
        
        [#assign isedit = false/]
        [#assign isadd = false/]
        [#assign nameedit = ""/]
    	[#if "${projectchannel}" == "0"]
    		[#assign isadd = true/]
    		[#assign isedit = true/]
    		[#assign nameedit = "编辑"/]
    	[/#if]
    	
        [@a.toolbar canedit="${isedit}" canadd="${isadd}" nameedit="${nameedit}" nameadd="添加本网推荐项目"]
        	[#if "${projectchannel}" == "1"]<a id="btn" href="" onclick="specialAudit('#dg')" class="easyui-linkbutton" data-options="">特批免费</a>[/#if]
        	[@a.status namedisable="取消发布" nameenable="发布"/]
	    	[@a.state /]
	    	[@a.state nameenable="设置公开" namedisable="取消公开" statefield="ispublic" actionenable="${base}${baseAdminPath}/ajax/${channel}/ispublic" actiondisable="${base}${baseAdminPath}/ajax/${channel}/unpublic"/]
	    	[@a.state nameenable="推荐到首页" namedisable="取消推荐" statefield="isrecommend" actionenable="${base}${baseAdminPath}/ajax/${channel}/isrecommend" actiondisable="${base}${baseAdminPath}/ajax/${channel}/unrecommend"/]
	    	[@a.state nameenable="设置魂牵梦绕" namedisable="取消魂牵梦绕" statefield="ischerished" actionenable="${base}${baseAdminPath}/ajax/${channel}/ischerished" actiondisable="${base}${baseAdminPath}/ajax/${channel}/uncherished"/]
	    [/@a.toolbar]
	    
	    [@a.tools /]
	</section>
	
	[@a.addsection canuploadfile=true onclicksubmit="mySubmitHandler(this, false)"]
		<tr>
            <td>项目名称:</td>
            <td><input class="easyui-validatebox long" type="text" name="name" data-options="required:true"></input></td>
        </tr>
        <tr>
            <td>配图:</td>
            <td>[@a.imgupload multiple=false /]</td>
        </tr>
        <tr>
            <td>最小并购资金:</td>
            <td>
                <input class="easyui-validatebox" type="text" name="minmergefund" data-options="required:true"></input>
                <select id="cc" class="easyui-combobox" name="moneyunit" style="width:50px;" data-options="panelHeight:50">   
                    [#list map["emoneyunit"]?keys as item]
                        [#if item_index == 0]
                        <option value="${item}" selected>${map["emoneyunit"][item]}</option>
                        [#else]
                        <option value="${item}">${map["emoneyunit"][item]}</option>
                        [/#if]   
                    [/#list]
                </select>  
                <select id="cc" class="easyui-combobox" name="currency" style="width:80px;" data-options="panelHeight:100">   
                    [#list map["ecurrency"]?keys as item]
                        <option value="${item}" [#if item_index == 0]selected[/#if]>${map["ecurrency"][item]}</option>   
                    [/#list]
                </select>  
            </td>
        </tr>
        <tr>
        	<td>并购类型:</td>
        	<td>
        		<select name="typeuid">
    				<option value="">--请选择--</option>
				    [#list type as item]
				    	<option value="${item.uid}">${item.name}</option>
				    [/#list]
	            </select>
        	</td>
    	</tr>
        <tr>
            <td>推荐级别:</td>
            <td>
            <select name="level">
            	<option value="">--请选择--</option>
		    	<option value="1">1</option>
		    	<option value="2">2</option>
		    	<option value="3">3</option>
		    	<option value="4">4</option>
		    	<option value="5">5</option>
            </select>
            </td>
        </tr>
        <tr>
            <td>管理层（高层）更新建议比例:</td>
            <td><input class="easyui-validatebox" type="text" name="ratiohigh" data-options="required:true"></input>%</td>
        </tr>
        <tr>
            <td>管理层（中层）更新建议比例:</td>
            <td><input class="easyui-validatebox" type="text" name="ratiomedium" data-options="required:true"></input>%</td>
        </tr>
        <tr>
            <td>变更团队最低进入人数:</td>
            <td><input class="easyui-validatebox" type="text" name="minchangenum" data-options="required:true"></input></td>
        </tr>
        <tr>
            <td>适合收购的并购企业国籍:</td>
            <td><input class="easyui-validatebox" type="text" name="suitcountry" data-options="required:true"></input></td>
        </tr>
        <tr>
            <td>并购背景:</td>
            <td>[@a.ckeditor name="mabackground" /]</td>
        </tr>
        <tr>
            <td>推荐理由:</td>
            <td>[@a.ckeditor name="recommondreason" /]</td>
        </tr>
        
        <tr>
            <td>研究报告信息:</td>
        </tr>
        <tr>
            <td>报告标题:</td>
            <td><input class="easyui-validatebox long" type="text" name="title" data-options="required:true"></input></td>
        </tr>
        <tr>
            <td>报告简介:</td>
            <td>[@a.ckeditor name="desc" /]</td>
        </tr>
        <tr>
            <td>上传研究报告:</td>
            <td><input name="reportpath" type="file"/></td>
        </tr>
        
        <tr>
            <td>添加相关文章信息:</td>
        </tr>
        <tr>
            <td colspan="2" style="padding-top:0;">
				<table id="dgarticle" class="easyui-datagrid" title="相关文章" toolbar="#articletoolbar" tools="" data-options="width:420,fitColumns:true,resizeHandle:'right',singleSelect:true">
					<thead>
						<tr>
							<th data-options="field:'title',width:50">标题</th>
							<th data-options="field:'origin',width:50">来源</th>
							<th data-options="field:'href',hidden:true"></th>
						</tr>
					</thead>
				</table>
		        [@a.toolbar id="articletoolbar" canadd=false candel=false canedit=false canview=false]
		        	<a id="articleaddbtn" href="" onclick="addArticle(false)" class="easyui-linkbutton" data-options="iconCls:'icon-add'">添加</a>
		        	<a id="articleaddbtn" href="" onclick="deleterow('#dgarticle')" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">删除</a>
				[/@a.toolbar]
			</td>
	  	</tr>
	  	<tr>
        	<td id="articles">
        	</td>
        </tr>
	  	<tr><td>并购指标</td></tr>
        <tr><td>股权可转让性</td></tr>
        <tr>
            <td>实际控制人控制权出让意愿：</td>
            <td><input class="easyui-validatebox" type="text" name="assignwill" data-options="required:true"></input></td>
        </tr>
        <tr>
            <td>让外部资本参股的意愿：</td>
            <td><input class="easyui-validatebox" type="text" name="othersharewill" data-options="required:true"></input></td>
        </tr>
        <tr>
            <td>股权转让自由度：</td>
            <td><input class="easyui-validatebox" type="text" name="assignfree" data-options="required:true"></input></td>
        </tr>
        
        <tr><td>行业层面</td></tr>
         <tr>
            <td>行业法律政策环境：</td>
            <td><input class="easyui-validatebox" type="text" name="industrylaw" data-options="required:true"></input></td>
         </tr>
         <tr>
            <td>行业成长前景：</td>
            <td><input class="easyui-validatebox" type="text" name="industrygrowing" data-options="required:true"></input></td>
        </tr>
        <tr>
            <td>行业周期性：</td>
            <td><input class="easyui-validatebox" type="text" name="industryperiod" data-options="required:true"></input></td>
        </tr>
         <tr>
            <td>行业进入壁垒：</td>
            <td><input class="easyui-validatebox" type="text" name="industrybarrier" data-options="required:true"></input></td>
        </tr>
        <tr>
            <td>行业集中度：</td>
            <td><input class="easyui-validatebox" type="text" name="industryconcentration" data-options="required:true"></input></td>
        </tr>
        <tr>
            <td>产业链环境：</td>
            <td><input class="easyui-validatebox" type="text" name="industrychain" data-options="required:true"></input></td>
        </tr>
        <tr><td>企业层面</td></tr>
        <tr>
            <td>战略管理水平：</td>
            <td><input class="easyui-validatebox" type="text" name="firmstrategy" data-options="required:true"></input></td>
        </tr>
        <tr>
            <td>运营管理水平：</td>
            <td><input class="easyui-validatebox" type="text" name="firmoperations" data-options="required:true"></input></td>
        </tr>
        <tr>
            <td>产品和服务的竞争力：</td>
            <td><input class="easyui-validatebox" type="text" name="firmservice" data-options="required:true"></input></td>
        </tr>
        <tr>
            <td>核心市场份额排名：</td>
            <td><input class="easyui-validatebox" type="text" name="firmsshare" data-options="required:true"></input></td>
        </tr>
        <tr><td>技术地位</td></tr>
        <tr>
            <td>在全球行业中的技术地位：</td>
            <td><input class="easyui-validatebox" type="text" name="firmsglobalkill" data-options="required:true"></input></td>
        </tr>
        <tr>
            <td>在中国行业中的技术地位：</td>
            <td><input class="easyui-validatebox" type="text" name="firmschinakill" data-options="required:true"></input></td>
        </tr>
        <tr>
            <td>品牌地位：</td>
            <td><input class="easyui-validatebox" type="text" name="firmbrand" data-options="required:true"></input></td>
        </tr>
        <tr>
            <td>国际市场覆盖率：</td>
            <td><input class="easyui-validatebox" type="text" name="firmcoverage" data-options="required:true"></input></td>
        </tr>
        <tr><td>市场成长性</td></tr>
        <tr>
            <td>国际市场成长性：</td>
            <td><input class="easyui-validatebox" type="text" name="firmgrowth" data-options="required:true"></input></td>
        </tr>
        <tr>
            <td>国内市场成长性：</td>
            <td><input class="easyui-validatebox" type="text" name="firmdomestic" data-options="required:true"></input></td>
        </tr>
        <tr>
            <td>财务健康指数：</td>
            <td><input class="easyui-validatebox" type="text" name="firmfinance" data-options="required:true"></input></td>
        </tr>
        <tr>
            <td>可持续发展能力：</td>
            <td><input class="easyui-validatebox" type="text" name="firmsustainable" data-options="required:true"></input></td>
        </tr>
        <tr><td>并购机会与并购风险</td></tr>
        <tr>
            <td>并购风险：</td>
            <td><input class="easyui-validatebox" type="text" name="risk" data-options="required:true"></input></td>
        </tr>
        <tr>
            <td>并购时机：</td>
            <td><input class="easyui-validatebox" type="text" name="opportunity" data-options="required:true"></input></td>
        </tr>
        <tr><td>并购能力和经验方面的要求</td></tr>
        <tr>
            <td>适合同行业并购指数：</td>
            <td><input class="easyui-validatebox" type="text" name="peermaindex" data-options="required:true"></input></td>
        </tr>
        <tr>
            <td>适合投资者并购指数：</td>
            <td><input class="easyui-validatebox" type="text" name="investmaindex" data-options="required:true"></input></td>
        </tr>
        <tr>
            <td>适合野心并购指数：</td>
            <td><input class="easyui-validatebox" type="text" name="ambitionmaindex" data-options="required:true"></input></td>
        </tr>
	[/@a.addsection]
	
	<div id="addarticle" class="easyui-window" title="添加文章" style="width:570px;height:300px" data-options="iconCls:'icon-add',modal:true,closed:true">
	    <tr>
            <td>标题：</td>
            <td><input id="atitle" class="easyui-validatebox long" type="text" name="atitle" data-options="required:true"></input></td>
        </tr>
        <tr>
            <td>来源：</td>
            <td><input id="aorigin" class="easyui-validatebox long" type="text" name="aorigin" data-options="required:true"></input></td>
        </tr>
        <tr>
            <td>链接：</td>
            <td><input id="ahref" class="easyui-validatebox long" type="text" name="ahref" data-options="required:true"></input></td>
        </tr>  
        <a href="" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="articleok()">添加</a>
	</div> 	
	
	[@a.editsection canuploadfile=true onclicksubmit="mySubmitHandler(this,true)"][#--edit--]
			<tr>
	            <td>项目名称:<input type="hidden" name="uid"/></td>
	            <td><input class="easyui-validatebox long" type="text" name="name" data-options="required:true"></input></td>
	        </tr>
	        <tr>
	            <td>配图:</td>
	            <td><img style="width:200px; height:200px" src="" set-format="${webimg}{0}" set-key="img"/>
	           		[@a.imgupload multiple=false /]
	           	</td>
	        </tr>
	        <tr>
	            <td>最小并购资金:</td>
	            <td><input class="easyui-validatebox" type="text" name="minmergefund" data-options="required:true"></input>
    	            <select id="cc" class="easyui-combobox" name="moneyunit" style="width:50px;" data-options="panelHeight:50">   
                        [#list map["emoneyunit"]?keys as item]
                            <option value="${item}">${map["emoneyunit"][item]}</option>   
                        [/#list]
                    </select>  
                    <select id="cc" class="easyui-combobox" name="currency" style="width:80px;" data-options="panelHeight:100">   
                        [#list map["ecurrency"]?keys as item]
                            <option value="${item}">${map["ecurrency"][item]}</option>   
                        [/#list]
                    </select>                         
	            </td>
	        </tr>
        	<tr>
        	<td>并购类型:</td>
        	<td>
        		<select name="typeuid">
				    [#list type as item]
				    	<option value="${item.uid}">${item.name}</option>
				    [/#list]
	            </select>
        	</td>
    	</tr>
        <tr>
            <td>推荐级别:</td>
            <td>
		    	<select name="level">
	            	<option value="">--请选择--</option>
			    	<option value="1">1</option>
			    	<option value="2">2</option>
			    	<option value="3">3</option>
			    	<option value="4">4</option>
			    	<option value="5">5</option>
	            </select>
            </td>
        </tr>
        <tr>
            <td>管理层（高层）更新建议比例:</td>
            <td><input class="easyui-validatebox" type="text" name="ratiohigh" data-options="required:true"></input></td>
        </tr>
        <tr>
            <td>管理层（中层）更新建议比例:</td>
            <td><input class="easyui-validatebox" type="text" name="ratiomedium" data-options="required:true"></input></td>
        </tr>
        <tr>
            <td>变更团队最低进入人数:</td>
            <td><input class="easyui-validatebox" type="text" name="minchangenum" data-options="required:true"></input></td>
        </tr>
        <tr>
            <td>适合收购的并购企业国籍:</td>
            <td><input class="easyui-validatebox" type="text" name="suitcountry" data-options="required:true"></input></td>
        </tr>
        <tr>
            <td>并购背景:</td>
            <td>[@a.ckeditor name="mabackground" /]</td>
        </tr>
        <tr>
            <td>推荐理由:</td>
            <td>[@a.ckeditor name="recommondreason" /]</td>
        </tr>
        
        <tr>
            <td>研究报告信息:</td>
        </tr>
        <tr>
            <td>报告标题:</td>
            <td><input class="easyui-validatebox long" type="text" name="report.title" data-options="required:true"></input></td>
        </tr>
        <tr>
            <td>报告简介:</td>
            <td>[@a.ckeditor name="report.desc" /]</td>
        </tr>
        <tr>
            <td>上传研究报告:</td>
            <td><input name="reportpath" type="file"/> <a href="${basefile}/files/download/">查看当前研究报告</a></td>
            <td></td>
        </tr>
        
        <tr>
            <td>添加相关文章信息:</td>
        </tr>
        <tr>
            <td colspan="2" style="padding-top:0;">
				<table id="dgarticleedit" class="easyui-datagrid" title="相关文章" toolbar="#articletoolbaredit" tools="" data-options="width:420,fitColumns:true,resizeHandle:'right',singleSelect:true">
					<thead>
						<tr>
							<th data-options="field:'title',width:50">标题</th>
							<th data-options="field:'origin',width:50">来源</th>
							<th data-options="field:'href',hidden:true"></th>
						</tr>
					</thead>
				</table>
		        [@a.toolbar id="articletoolbaredit" canadd=false candel=false canedit=false canview=false]
		        	<a id="articleaddbtn" href="" onclick="addArticle(true)" class="easyui-linkbutton" data-options="iconCls:'icon-add'">添加</a>
		        	<a id="articleaddbtn" href="" onclick="deleterow('#dgarticleedit')" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">删除</a>
				[/@a.toolbar]
			</td>
	  	</tr>
	  	<tr>
        	<td id="editarticles">
        	</td>
        </tr>
        <tr><td>并购指标</td></tr>
        <tr><td>股权可转让性</td></tr>
        <tr>
            <td>实际控制人控制权出让意愿：</td>
            <td><input class="easyui-validatebox" type="text" name="mergerindicator.assignwill" data-options="required:true"></input></td>
        </tr>
        <tr>
            <td>让外部资本参股的意愿：</td>
            <td><input class="easyui-validatebox" type="text" name="mergerindicator.othersharewill" data-options="required:true"></input></td>
        </tr>
        <tr>
            <td>股权转让自由度 ：</td>
            <td><input class="easyui-validatebox" type="text" name="mergerindicator.assignfree" data-options="required:true"></input></td>
        </tr>
        
        <tr><td>行业层面</td></tr>
        <tr>
            <td> 行业法律政策环境：</td>
            <td><input class="easyui-validatebox" type="text" name="mergerindicator.industrylaw" data-options="required:true"></input></td>
        </tr>
         <tr>
            <td>行业成长前景：</td>
            <td><input class="easyui-validatebox" type="text" name="mergerindicator.industrygrowing" data-options="required:true"></input></td>
        </tr>
        <tr>
            <td>行业抗周期性：</td>
            <td><input class="easyui-validatebox" type="text" name="mergerindicator.industryperiod" data-options="required:true"></input></td>
        </tr>
         <tr>
            <td>行业进入壁垒：</td>
            <td><input class="easyui-validatebox" type="text" name="mergerindicator.industrybarrier" data-options="required:true"></input></td>
        </tr>
        <tr>
            <td>行业集中度：</td>
            <td><input class="easyui-validatebox" type="text" name="mergerindicator.industryconcentration" data-options="required:true"></input></td>
        </tr>
        <tr>
            <td>产业链环境：</td>
            <td><input class="easyui-validatebox" type="text" name="mergerindicator.industrychain" data-options="required:true"></input></td>
        </tr>
        <tr><td>企业层面</td></tr>
        <tr>
            <td>战略管理水平：</td>
            <td><input class="easyui-validatebox" type="text" name="mergerindicator.firmstrategy" data-options="required:true"></input></td>
        </tr>
         <tr>
            <td> 运营管理水平：</td>
            <td><input class="easyui-validatebox" type="text" name="mergerindicator.firmoperations" data-options="required:true"></input></td>
        </tr>
        <tr>
            <td>产品和服务的竞争力：</td>
            <td><input class="easyui-validatebox" type="text" name="mergerindicator.firmservice" data-options="required:true"></input></td>
        </tr>
        <tr>
            <td>核心市场份额排名：</td>
            <td><input class="easyui-validatebox" type="text" name="mergerindicator.firmsshare" data-options="required:true"></input></td>
        </tr>
         <tr><td>技术地位</td></tr>
         <tr>
            <td>在全球行业中的技术地位：</td>
            <td><input class="easyui-validatebox" type="text" name="mergerindicator.firmsglobalkill" data-options="required:true"></input></td>
        </tr>
        <tr>
            <td>在中国行业中的技术地位：</td>
            <td><input class="easyui-validatebox" type="text" name="mergerindicator.firmschinakill" data-options="required:true"></input></td>
        </tr>
        <tr>
            <td>品牌地位：</td>
            <td><input class="easyui-validatebox" type="text" name="mergerindicator.firmbrand" data-options="required:true"></input></td>
        </tr>
        <tr>
            <td>国际市场覆盖率：</td>
            <td><input class="easyui-validatebox" type="text" name="mergerindicator.firmcoverage" data-options="required:true"></input></td>
        </tr>
         <tr><td>市场成长性</td></tr>
        <tr>
            <td>国际市场成长性：</td>
            <td><input class="easyui-validatebox" type="text" name="mergerindicator.firmgrowth" data-options="required:true"></input></td>
        </tr>
        <tr>
            <td>国内市场成长性：</td>
            <td><input class="easyui-validatebox" type="text" name="mergerindicator.firmdomestic" data-options="required:true"></input></td>
        </tr>
        <tr>
            <td>财务健康指数：</td>
            <td><input class="easyui-validatebox" type="text" name="mergerindicator.firmfinance" data-options="required:true"></input></td>
        </tr>
        <tr>
            <td>可持续发展能力：</td>
            <td><input class="easyui-validatebox" type="text" name="mergerindicator.firmsustainable" data-options="required:true"></input></td>
        </tr>
        <tr><td>并购机会与并购风险</td></tr>
        <tr>
            <td>并购风险：</td>
            <td><input class="easyui-validatebox" type="text" name="mergerindicator.risk" data-options="required:true"></input></td>
        </tr>
        <tr>
            <td>并购时机：</td>
            <td><input class="easyui-validatebox" type="text" name="mergerindicator.opportunity" data-options="required:true"></input></td>
        </tr>
        <tr><td>并购能力和经验方面的要求</td></tr>
        <tr>
            <td>适合同行业并购指数：</td>
            <td><input class="easyui-validatebox" type="text" name="mergerindicator.peermaindex" data-options="required:true"></input></td>
        </tr>
        <tr>
            <td>适合投资者并购指数：</td>
            <td><input class="easyui-validatebox" type="text" name="mergerindicator.investmaindex" data-options="required:true"></input></td>
        </tr>
        <tr>
            <td>适合野心并购指数：</td>
            <td><input class="easyui-validatebox" type="text" name="mergerindicator.ambitionmaindex" data-options="required:true"></input></td>
        </tr>
	[/@a.editsection]
	
	[@a.viewsection]
        <tr colspan="2">
            <td>项目编号：</td>
            <td><span set-key="number"></span></td>
        </tr>
        <tr colspan="2">
            <td>项目名称：</td>
            <td><span set-key="name"></span></td>
        </tr>
        <tr colspan="2">
            <td>配图：</td>
            <td><img src="" set-format="${webimg}{0}" set-key="img" ></td>
        </tr>
        <tr colspan="2">
            <td>最小并购资金：</td>
            <td><span set-key="minmergefund"></span>万</td>
        </tr>
        <tr colspan="2">
            <td>并购类型：</td>
            <td><span set-key="typeName"></span></td>
        </tr>
        <tr colspan="2">
            <td>推荐级别：</td>
            <td><span set-key="level"></span></td>
        </tr>
        <tr colspan="2">
            <td>管理层（高层）更新建议比例：</td>
            <td><span set-key="ratiohigh"></span></td>
        </tr>
        <tr colspan="2">
            <td>管理层（中层）更新建议比例：</td>
            <td><span set-key="ratiomedium"></span></td>
        </tr>
        <tr colspan="2">
            <td>变更团队最低进入人数：</td>
            <td><span set-key="minchangenum"></span></td>
        </tr>
        <tr colspan="2">
            <td>适合收购的并购企业国籍：</td>
            <td><span set-key="suitcountry"></span></td>
        </tr>
        <tr colspan="2">
            <td>并购背景：</td>
            <td><span set-key="mabackground"></span></td>
        </tr>
        <tr colspan="2">
            <td>推荐理由：</td>
            <td><span set-key="recommondreason"></span></td>
        </tr>
        <tr colspan="2">
            <td>创建时间：</td>
            <td><span set-key="createtimeStr"></span></td>
        </tr>
        <tr colspan="2">
            <td>状态：</td>
            <td><span set-key="status"></span></td>
        </tr>
        
        <tr colspan=""><td>股权可转让性</td></tr>
        <tr colspan="2">
            <td>实际控制人控制权出让意愿：</td>
            <td><span set-key="mergerindicator.assignwill"></span></td>
        </tr>
        <tr colspan="2">
            <td>让外部资本参股的意愿：</td>
            <td><span set-key="mergerindicator.othersharewill"></span></td>
        </tr>
        <tr colspan="2">
            <td>股权转让自由度：</td>
            <td><span set-key="mergerindicator.assignfree"></span></td>
        </tr>
        
        <tr colspan=""><td>行业层面</td></tr>
        <tr colspan="2">
            <td>行业法律政策环境：</td>
            <td><span set-key="mergerindicator.industrylaw"></span></td>
        </tr>
         <tr colspan="2">
            <td>行业成长前景：</td>
            <td><span set-key="mergerindicator.industrygrowing"></span></td>
        </tr>
        <tr colspan="2">
            <td>行业抗周期性：</td>
            <td><span set-key="mergerindicator.industryperiod"></span></td>
        </tr>
         <tr colspan="2">
            <td>行业进入壁垒：</td>
            <td><span set-key="mergerindicator.industrybarrier"></span></td>
        </tr>
         <tr colspan="2">
            <td>行业集中度：</td>
            <td><span set-key="mergerindicator.industryconcentration"></span></td>
        </tr>
        <tr colspan="2">
            <td>产业链环境：</td>
            <td><span set-key="mergerindicator.industrychain"></span></td>
        </tr>
        
         <tr colspan=""><td>企业层面</td></tr>
        <tr colspan="2">
            <td>战略管理水平：</td>
            <td><span set-key="mergerindicator.firmstrategy"></span></td>
        </tr>
        <tr colspan="2">
            <td>运营管理水平：</td>
            <td><span set-key="mergerindicator.firmoperations"></span></td>
        </tr>
        <tr colspan="2">
            <td>产品和服务的竞争力：</td>
            <td><span set-key="mergerindicator.firmservice"></span></td>
        </tr>
        <tr colspan="2">
            <td>核心市场份额排名：</td>
            <td><span set-key="mergerindicator.firmsshare"></span></td>
        </tr>
        <tr colspan=""><td>技术地位</td></tr>
        <tr colspan="2">
            <td>在全球行业中的技术地位：</td>
            <td><span set-key="mergerindicator.firmsglobalkill"></span></td>
        </tr>
        <tr colspan="2">
            <td>在中国行业中的技术地位：</td>
            <td><span set-key="mergerindicator.firmschinakill"></span></td>
        </tr>
        <tr colspan="2">
            <td>品牌地位：</td>
            <td><span set-key="mergerindicator.firmbrand"></span></td>
        </tr>
        <tr colspan="2">
            <td>国际市场覆盖率：</td>
            <td><span set-key="mergerindicator.firmcoverage"></span></td>
        </tr>
        <tr colspan=""><td>市场成长性</td></tr>
        <tr colspan="2">
            <td>国际市场成长性：</td>
            <td><span set-key="mergerindicator.firmgrowth"></span></td>
        </tr>
        <tr colspan="2">
            <td>国内市场成长性：</td>
            <td><span set-key="mergerindicator.firmdomestic"></span></td>
        </tr>
        <tr colspan="2">
            <td>财务健康指数：</td>
            <td><span set-key="mergerindicator.firmfinance"></span></td>
        </tr>
        <tr colspan="2">
            <td>可持续发展能力：</td>
            <td><span set-key="mergerindicator.firmsustainable"></span></td>
        </tr>
        
        <tr colspan=""><td>并购机会与并购风险</td></tr>
        <tr colspan="2">
            <td>并购风险：</td>
            <td><span set-key="mergerindicator.risk"></span></td>
        </tr>
        <tr colspan="2">
            <td>并购时机：</td>
            <td><span set-key="mergerindicator.opportunity"></span></td>
        </tr>
        
        <tr colspan=""><td>并购能力和经验方面的要求</td></tr>
        <tr colspan="2">
            <td>适合同行业并购指数：</td>
            <td><span set-key="mergerindicator.peermaindex"></span></td>
        </tr>
        <tr colspan="2">
            <td>适合投资者并购指数：</td>
            <td><span set-key="mergerindicator.investmaindex"></span></td>
        </tr>
        <tr colspan="2">
            <td>适合野心并购指数：</td>
            <td><span set-key="mergerindicator.ambitionmaindex"></span></td>
        </tr>
	[/@a.viewsection]
	
</body>
</html>