[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Admin.ftl" as a]
<html>
<head>
<title>题库管理</title>
<script type="text/javascript" src="${basejs}/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
<!--
var isedit = false;

function initDGData(row) {
	for(var i in row) {
		if(row[i].type == 1){
			if(row[i].options[0] != null){
				row[i].rightoption = row[i].options[0].name;
				if(row[i].options[0].sortorder == -1){
					row[i].editrightradio = "undefined";
				}else{
					row[i].editrightradio = row[i].options[0].sortorder;
				}
				
			}
			if(row[i].options[1] != null){
				row[i].erroption1 = row[i].options[1].name;
				if(row[i].options[1].sortorder == -1){
					row[i].editerrradio1 = "undefined";
				}else{
					row[i].editerrradio1 = row[i].options[1].sortorder;
				}
			}
			if(row[i].options[2] != null){
				row[i].erroption2 = row[i].options[2].name;
				if(row[i].options[2].sortorder == -1){
					row[i].editerrradio2 = "undefined";
				}else{
					row[i].editerrradio2 = row[i].options[2].sortorder;
				}
			}		
			if(row[i].options[3] != null){
				row[i].erroption3 = row[i].options[3].name;
				if(row[i].options[3].sortorder == -1){
					row[i].editerrradio3 = "undefined";
				}else{
					row[i].editerrradio3 = row[i].options[3].sortorder;
				}
			}
		}
	}
}

function getFreshRowUrl(dg) {
    var row = getSelected(dg);
    if(row != null) {
        return "${base}${baseAdminPath}/ajax/role/list/{0}".format(row.uid);
    } else {
        return null;
    }
}

function freshRow(row, data, i) {	
	if(row.type == 1){
		$("#editsingletable").show();
		$("#viewsingletable").show();
		$("#editmaterialtable").hide();
		$("#viewmaterialtable").hide();
	}else{
		$("#editsingletable").hide();
		$("#viewsingletable").hide();
		$("#editmaterialtable").show();
		$("#viewmaterialtable").show();
	}
}

function initFormData(form, data) {
	form.form('load', data);
	form.set(data);
	if(data.type == 2){
		$("#dgmaterialedit").datagrid("loadData", data.questions);
		$("#dgmaterialview").datagrid("loadData", data.questions);
	}
}

 $(function(){
	 $("input[name='type']").bind("click",function(){    
        if($(this).val() == 2){
        	 $("#singletable").hide();
        	 $("#materialtable").show();
        }else{
        	$("#materialtable").hide();
        	$("#singletable").show();
        }    
    });    
 });
 
 //点击添加材料中的问题
function addQuestions(pisedit){
	isedit = pisedit;
	$("#questionswin").window("open");
}

 //提交填写的选项
function qstok(){
	var edit = "";
	if(isedit) {
		edit = "edit";
	}
	var title = $("#qtextarea").val();
	var rightoption = $("input[name=qrightoption]").val();
	var erroption1 = $("input[name=qerroption1]").val();
	var erroption2 = $("input[name=qerroption2]").val();
	var erroption3 = $("input[name=qerroption3]").val();
	var rightradio = $('input[name="rightradio"]').filter(':checked').val();
	var errradio = $('input[name="errradio"]').filter(':checked').val();
	var errradio2 = $('input[name="errradio2"]').filter(':checked').val();
	var errradio3 = $('input[name="errradio3"]').filter(':checked').val();
	var analysis = $("input[name=qanalysis]").val();
	var arr = new Array(title, rightoption, erroption1, erroption2, erroption3, rightradio, errradio, errradio2, errradio3, analysis);
	appendRow("#dgmaterial" + edit, arr);
	if(isedit) {// 添加時不提交，编辑才提交
		submitQuestion(arr);
	}
	cancelqstwin();
}

// 在dg中追加row
function appendRow(dg, arr){
	$(dg).datagrid('appendRow',{
		title: arr[0],
		rightoption:arr[1],
		erroption1:arr[2],
		erroption2:arr[3],
		erroption3:arr[4],
		rightradio:arr[5],
		errradio:arr[6],
		errradio2:arr[7],
		errradio3:arr[8],
		analysis:arr[9],
	});
}

//删除行
function deleterow(dg, isedit) {
	var rows = getSelections(dg);
	if(rows.length == 0) {
		warn("请选择选项");
		return;
	}
	$.messager.confirm('确认','您确认想要删除记录吗？',function(r){
	    if (r){    
	        var title = [];
			for(var i=0;i<rows.length;i++){
				title.push(rows[i].title);		
			}
			if(isedit) {
				var uid = $("input[name=uid]").val();
				$.post("${base}${baseAdminPath}/moral/ajax/materialqst/delete", { 'title': title.toString(),"uid":uid },function(data){
					if(data.success){
						ok("删除成功");
					}else{
						ok("删除失败");
						return;
					}
			   	});		
			}
			var index = -1;
			for(var i=0; i<rows.length; i++){
				index = $(dg).datagrid("getRowIndex", rows[i]);
				$(dg).datagrid("deleteRow", index);
			}  
	    }    
	});  
}


function viewqst(dg){
	var rows = getSelections(dg);
	if(rows.length == 0) {
		warn("请选择选项");
		return;
	}else{
		alert("TODO");
		//$("#questionsview").window("open");
	}
}

// 编辑时将选项保存到后台
function submitQuestion(questions){
    var uid = $("input[name=uid]").val();
    $.post("${base}${baseAdminPath}/moral/ajax/materialqst/add",{ "questions":questions.toString(),"uid":uid },function(data){
		if(data.success){
			ok("添加成功");
	    	$(dg).datagrid("reload");
		}else{
			ok("添加失败");
		}
   	});
}

//点击提交添加表单
function mySubmitHandler(dg){
	var qstrows = getRows("#dgmaterial");
	insertMaterialQst(qstrows, false);
	insertSingleQst();
	submitHandler(dg);
	afterSubmit();
}

//编辑时提交表单
function mySubmitEditHandler(dg){
	EditSingleQst();
	submitHandler(dg);
	afterSubmit();
}

// 添加页面把添加的题目放到input隐藏域中
function insertMaterialQst(rows){
	for(var i=0; i< rows.length; i++){
		var Mvalue = rows[i].title + "liusvo" + rows[i].rightoption + "liusvo" + rows[i].erroption1 + "liusvo" + rows[i].erroption2 + "liusvo" + rows[i].erroption3 + "liusvo" + rows[i].analysis;
		var inputhtml = "<input name='qst' type='hidden' value='" + Mvalue + "'/>";
		var Mrediovalue = rows[i].rightradio + "/" + rows[i].errradio + "/" + rows[i].errradio2 + "/" + rows[i].errradio3;
		var Mredioinputhtml = "<input name='qstredio' type='hidden' value='" + Mrediovalue + "'/>";
		$("#qst").append(Mredioinputhtml);
		$("#qst").append(inputhtml);
	}
}
// 单选题 添加页面把添加的题目放到input隐藏域中
function insertSingleQst(){
	var singlevalue = $("#rightoption").val() + "liusvo" + $("#erroption1").val() + "liusvo" + $("#erroption2").val() + "liusvo" + $("#erroption3").val();
	var singleinputhtml = "<input name='singleqst' type='hidden' value='" +singlevalue+ "'/>";
	var optionsradio = $('input[name="rightradio"]').filter(':checked').val() + "," + $('input[name="errradio1"]').filter(':checked').val() + "," + $('input[name="errradio2"]').filter(':checked').val() + "," + $('input[name="errradio3"]').filter(':checked').val();
	var singletypeinputhtml = "<input name='optionsradio' type='hidden' value='" +optionsradio+ "'/>";
	$("#qst").append(singleinputhtml);
	$("#qst").append(singletypeinputhtml);
}

// 编辑单选题 添加页面把添加的题目放到input隐藏域中
function EditSingleQst(){
	var singleavalue = $("#Erightoption").val() + "liusvo" + $("#Eerroption1").val() + "liusvo" + $("#Eerroption2").val() + "liusvo" + $("#Eerroption3").val();
	var singleinputhtml = "<input name='singleqst' type='hidden' value='" +singleavalue+ "'/>";
	var optionsradio = $('input[name="editrightradio"]').filter(':checked').val() + "," + $('input[name="editerrradio1"]').filter(':checked').val() + "," + $('input[name="editerrradio2"]').filter(':checked').val() + "," + $('input[name="editerrradio3"]').filter(':checked').val();
	var singletypeinputhtml = "<input name='optionsradio' type='hidden' value='" +optionsradio+ "'/>";
	$("#Eqst").append(singleinputhtml);
	$("#Eqst").append(singletypeinputhtml);
}
	
// 提交表单后清空添加页面的列表等
function afterSubmit() {
	$("#qst").html("");// 清空暂存域
	$("#Eqst").html("");// 清空暂存域
}

// 取消添加选型标题
function cancelqstwin(){
	$("#qtextarea").val("");
	$("#qrightoption").attr("value","");
	$("#qanalysis").attr("value","");
	$("#qerroption1").attr("value","");
	$("#qerroption2").attr("value","");
	$("#qerroption3").attr("value","");
	$("#questionswin").window("close");
}

//-->
</script> 	
</head>
<body>
    <section>
        [@a.search]
        	<tr>
        		 <td><label for="sname">题目：</label></td>
                 <td><input id="sname" type="text" name="title" class="" style="width:500px;"/></td>
        	</tr>
            <tr>
                <td>
	        		<label for="schapter">章节: </label>
	        		<select id="schapter" name="chapter">
					    <option value="">全部</option>
					    [#assign chapter = map?keys]
					    	[#list chapter as item]
						    	<option value="${item}">${map[item]}</option>
						    [/#list]
		            </select>
	        	</td>
	        	<td>
                    <label for="stype">题型: </label>
	        	</td>
	        	<td>
                    <select class="easyui-combobox" data-options="panelHeight:100" style="width:100px;" name="type">
                        <option value="" name="type">全部</option>
                        <option id="rdchannel_1" value="1" name="type">单选题</option>
                        <option id="rdchannel_2" value="2" name="type">材料题</option>
                    </select>
	        	</td>
            </tr>
            <tr>
	        	<td><label for="sstatus">状态：</label></td>
                <td>
                    <input type="radio" name="status" value=""/>全部
                    <input type="radio" name="status" value="1"/>正常
                    <input type="radio" name="status" value="0"/>冻结
                </td>
        	</tr>
            <tr>
                <td>起始发布时间：</td>
                <td><input id="timestart" name="createtimestart" type="text" data-options="editable:false" class="easyui-datebox" /></td>
                <td>结束发布时间：</td>
                <td><input id="timeend" name="createtimeend" type="text" data-options="editable:false" class="easyui-datebox" /></td>
            </tr>
        [/@a.search]
        
        [@a.datagrid]
            <th data-options="field:'title',width:600">标题</th>
            <th data-options="field:'chapterName',width:75">章节</th>
			<th data-options="field:'typeName',width:75">题型</th>
            <th data-options="field:'statusName',width:75">状态</th>
            <th data-options="field:'createtimeStr',width:175">添加日期</th>
        [/@a.datagrid]
        [@a.toolbar]
        [/@a.toolbar]
        [@a.tools /]
	</section>
    
    [@a.addsection onclicksubmit="mySubmitHandler(this)"]
    	<tr>
            <td>章节：</td>
            <td>
	            [#list map?keys as mkey]
                    <input type="radio" id="rdchannel_${mkey_index}" [#if mkey_index == 0]checked="checked"[/#if]name="chapter" value="${mkey}"/> <label for="rdchannel_${mkey_index}">${map[mkey]}</label>&nbsp;
                [/#list]
            </td>
        </tr>
    	<tr>
            <td>题型：</td>
            <td>
                <input id="rdtype" type="radio" name="type" value="1" checked="checked" /> <label for="rdtype">单选</label> &nbsp;
                <input id="rdtype1" type="radio" name="type" value="2"/> <label for="rdtype1">材料</label> &nbsp;
            </td>
        </tr>
        <tr>
        	<td id="qst">
        	</td>
        </tr>
		<table id="singletable" class="singletable">
			<tr>
	        	<td>题目：</td>
	            <td><textarea class="easyui-validatebox" style="width:300px;height:100px" type="text" name="title"></textarea></td>
	        </tr>
	        <tr>
	            <td>正确答案：</td>
		        <td><input id="rightoption" class="easyui-validatebox long" type="text" name="rightoption" /></td>
                <td>
                	<input type="radio" id= "radior_-1" name="rightradio" value="undefined" checked="checked" /><label for="radior_-1">随机</label> &nbsp;
	                [#list map1?keys as mkey]
			          	<input type="radio" id= "radior_${mkey_index}" name="rightradio" value="${mkey}"/><label for="radior_${mkey_index}">${map1[mkey]}</label> &nbsp;
		            [/#list]
                </td>
	        </tr>
	        <tr>
	            <td>错误答案1：</td>
		        <td><input id="erroption1" class="easyui-validatebox long" type="text" name="erroption1" /></td>
		        <td>
	        		<input type="radio" id= "radiorerr1_-1" name="errradio1" value="undefined" checked="checked" /><label for="radiorerr1_-1">随机</label> &nbsp;
		        	[#list map1?keys as mkey]
			          	<input type="radio" id= "radiorerr1_${mkey_index}" name="errradio1" value="${mkey}"/><label for="radiorerr1_${mkey_index}">${map1[mkey]}</label> &nbsp;
		            [/#list]
                </td>
	        </tr>
	        <tr>
	            <td>错误答案2：</td>
		        <td><input id="erroption2" class="easyui-validatebox long" type="text" name="erroption2" /></td>
		        <td>
	        		<input type="radio" id= "radiorerr2_-1" name="errradio2" value="undefined" checked="checked" /><label for="radiorerr2_-1">随机</label> &nbsp;
		        	[#list map1?keys as mkey]
			          	<input type="radio" id= "radiorerr2_${mkey_index}" name="errradio2" value="${mkey}"/><label for="radiorerr2_${mkey_index}">${map1[mkey]}</label> &nbsp;
		            [/#list]
                </td>
	        </tr>
	        <tr>
	            <td>错误答案3：</td>
		        <td><input id="erroption3" class="easyui-validatebox long" type="text" name="erroption3" /></td>
		        <td>
	        		<input type="radio" id= "radiorerr3_-1" name="errradio3" value="undefined" checked="checked" /><label for="radiorerr3_-1">随机</label> &nbsp;
		        	[#list map1?keys as mkey]
			          	<input type="radio" id= "radiorerr3_${mkey_index}" name="errradio3" value="${mkey}"/><label for="radiorerr3_${mkey_index}">${map1[mkey]}</label> &nbsp;
		            [/#list]
                </td>
	        </tr>
	        <tr>
	            <td>答案解析：</td>
		        <td><input class="easyui-validatebox long" type="text" name="analysis" /></td>
	        </tr>
	    </table>
	    
	    <table id="materialtable" class="materialtable" >
			<tr>
	        	<td>材料：</td>
	            <td><td>[@a.ckeditor name="desc"/]</td></td>
	        </tr>
	        <tr>
	        	<td>选项： </td>
	        	<td colspan="2" style="padding-top:0%;">
	                [@a.datagrid id="dgmaterial" title="选项" toolbar="#materialtoolbar" options="width:620,fitColumns:true,resizeHandle:'right',singleSelect:true,method:'get'"]
						<th data-options="field:'title',width:20">标题</th>
					[/@a.datagrid]
			        [@a.toolbar id="materialtoolbar" canadd=false candel=false canedit=false canview=false]
			        	<a id="materialaddbtn" href="" onclick="addQuestions(false)" class="easyui-linkbutton" data-options="iconCls:'icon-add'">添加</a>
			        	<a id="materialaddbtn" href="" onclick="deleterow('#dgmaterial', false)" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">删除</a>
					[/@a.toolbar]
	            </td>
	        </tr>
	    </table>
    [/@a.addsection]
    
    <div id="questionswin" class="easyui-window" title="添加材料题选项" style="width:850px;height:400px" data-options="iconCls:'icon-add',modal:true,closed:true">
    	<tr>
        	<td>题&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;目：</td>
            <td><textarea id="qtextarea" class="easyui-validatebox" style="width:300px;height:100px" type="text" name="qtitle"></textarea></td>
        </tr>
    	</br>
        <tr>
            <td>正确答案：&nbsp;&nbsp;</td>
	        <td><input id="qrightoption" class="easyui-validatebox long" type="text" name="qrightoption" /></td>
	        <td>
            	<input type="radio" id= "radior_-1" name="rightradio" value="undefined" checked="checked" /><label for="radior_-1">随机</label> &nbsp;
                [#list map1?keys as mkey]
		          	<input type="radio" id= "radior_${mkey_index}" name="rightradio" value="${mkey}"/><label for="radior_${mkey_index}">${map1[mkey]}</label> &nbsp;
	            [/#list]
            </td>
        </tr>
        </br>
        <tr>
            <td>错误答案1：</td>
	        <td><input id="qerroption1" class="easyui-validatebox long" type="text" name="qerroption1" /></td>
	        <td>
            	<input type="radio" id= "radiorerr_-1" name="errradio" value="undefined" checked="checked" /><label for="radiorerr_-1">随机</label> &nbsp;
                [#list map1?keys as mkey]
		          	<input type="radio" id= "radiorerr_${mkey_index}" name="errradio" value="${mkey}"/><label for="radiorerr_${mkey_index}">${map1[mkey]}</label> &nbsp;
	            [/#list]
            </td>
        </tr>
        </br>
        <tr>
            <td>错误答案2：</td>
	        <td><input id="qerroption2" class="easyui-validatebox long" type="text" name="qerroption2" /></td>
	        <td>
            	<input type="radio" id= "radiorerr2_-1" name="errradio2" value="undefined" checked="checked" /><label for="radiorerr2_-1">随机</label> &nbsp;
                [#list map1?keys as mkey]
		          	<input type="radio" id= "radiorerr2_${mkey_index}" name="errradio2" value="${mkey}"/><label for="radiorerr2_${mkey_index}">${map1[mkey]}</label> &nbsp;
	            [/#list]
            </td>
        </tr>
        </br>
        <tr>
            <td>错误答案3：</td>
	        <td><input id="qerroption3" class="easyui-validatebox long" type="text" name="qerroption3" /></td>
	        <td>
            	<input type="radio" id= "radiorerr3_-1" name="errradio3" value="undefined" checked="checked" /><label for="radiorerr3_-1">随机</label> &nbsp;
                [#list map1?keys as mkey]
		          	<input type="radio" id= "radiorerr3_${mkey_index}" name="errradio3" value="${mkey}"/><label for="radiorerr3_${mkey_index}">${map1[mkey]}</label> &nbsp;
	            [/#list]
            </td>
        </tr>   
        </br>
        <tr>
            <td>答案解析：&nbsp;&nbsp;</td>
	        <td><input id="qanalysis" class="easyui-validatebox long" type="text" name="qanalysis" /></td>
        </tr>
        </br>
        <tr>
        	<a id="qstaddbtn" href="#" onclick="qstok()" class="easyui-linkbutton" data-options="iconCls:'icon-add'">添加</a>
        	<a id="cancelqst" href="" onclick="cancelqstwin()" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取消</a>
    	</tr> 
	</div>  
    
    
    [@a.editsection onclicksubmit="mySubmitEditHandler(this)"]
    	<tr>
            <td><input type="hidden" name="uid" /></td>
        </tr>
        <tr>
            <td>章节：</td>
            <td>
      		 <div class="chapter">
      		    [#list map?keys as mkey]
                    <input type="radio" id="rdchannel_${mkey_index}" name="chapter" value="${mkey}"/> <label for="rdchannel_${mkey_index}">${map[mkey]}</label>&nbsp;
                [/#list]
             </div>
            </td>
        </tr>
        <table id="editsingletable">
        	<tr>
	        	<td id="Eqst">
	        	</td>
	        </tr>
	        <tr>
	            <td>题目：</td>
	            <td><textarea class="easyui-validatebox" style="width:300px;height:100px" type="text" name="title"></textarea></td>
	        </tr>
        
	        <tr>
	            <td>正确答案：
	            <td><input class="easyui-validatebox long" type="text" id="Erightoption" name="rightoption"/></td>
	            <td>
	             <div class="channel">
	            	<input type="radio" id= "radior_-1" name="editrightradio" value="undefined"/><label for="radior_-1">随机</label> &nbsp;
	                [#list map1?keys as mkey]
			          	<input type="radio" id="radior_${mkey_index}" name="editrightradio" value="${mkey}"/><label for="radior_${mkey_index}">${map1[mkey]}</label> &nbsp;
		            [/#list]
	            </div>
	            </td>
	        </tr>
	        <tr>
	            <td>错误答案1：</td>
	            <td><input class="easyui-validatebox long" type="text" id="Eerroption1" name="erroption1"/></td>
	            <td>
	        		<input type="radio" id= "radiorerr1_-1" name="editerrradio1" value="undefined" /><label for="radiorerr1_-1">随机</label> &nbsp;
		        	[#list map1?keys as mkey]
			          	<input type="radio" id= "radiorerr1_${mkey_index}" name="editerrradio1" value="${mkey}"/><label for="radiorerr1_${mkey_index}">${map1[mkey]}</label> &nbsp;
		            [/#list]
	            </td>
	        </tr>
	        <tr>
	            <td>错误答案2：</td>
	            <td><input class="easyui-validatebox long" type="text" id="Eerroption2" name="erroption2"/></td>
	            <td>
	        		<input type="radio" id= "radiorerr2_-1" name="editerrradio2" value="undefined" /><label for="radiorerr2_-1">随机</label> &nbsp;
		        	[#list map1?keys as mkey]
			          	<input type="radio" id= "radiorerr2_${mkey_index}" name="editerrradio2" value="${mkey}"/><label for="radiorerr2_${mkey_index}">${map1[mkey]}</label> &nbsp;
		            [/#list]
	            </td>
	        </tr>
	        <tr>
	            <td>错误答案3：</td>
	            <td><input class="easyui-validatebox long" type="text" id="Eerroption3" name="erroption3"/></td>
	            <td>
	        		<input type="radio" id= "radiorerr3_-1" name="editerrradio3" value="undefined" /><label for="radiorerr3_-1">随机</label> &nbsp;
		        	[#list map1?keys as mkey]
			          	<input type="radio" id= "radiorerr3_${mkey_index}" name="editerrradio3" value="${mkey}"/><label for="radiorerr3_${mkey_index}">${map1[mkey]}</label> &nbsp;
		            [/#list]
	            </td>
	        </tr>
	         <tr>
	            <td>答案解析：</td>
	            <td><input class="easyui-validatebox long" type="text" name="analysis"/></td>
	        </tr>
        </table>
        
         <table id="editmaterialtable">	
			<tr>
	        	<td>材料：</td>
	            <td><td>[@a.ckeditor name="desc"/]</td></td>
	        </tr>         	
         	<tr>
         		<td>选项：</td>
	            <td colspan="2" style="padding-top:0;">
	            	[@a.datagrid id="dgmaterialedit" title="材料题中的选择题" toolbar="#toolbaredit" options="width:420,fitColumns:true,resizeHandle:'right',singleSelect:true,method:'get'"]
						<th data-options="field:'uid',hidden:true"></th>
						<th data-options="field:'title',width:20">标题</th>
					[/@a.datagrid]
			        [@a.toolbar id="redtoolbaredit" canadd=false candel=false canedit=false canview=false]
			        	<a id="addbtn" href="" onclick="addQuestions(true)" class="easyui-linkbutton" data-options="iconCls:'icon-add'">添加</a>
			        	<a id="addbtn" href="" onclick="deleterow('#dgmaterialedit', true)" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">删除</a>
					[/@a.toolbar]
				</td>
	        </tr>
         </table>
        
    [/@a.editsection]
        
    [@a.viewsection]
    	<tr colspan="2">
            <td>章节：</td>
            <td><span set-key="chapterName"></span></td>
        </tr>
        <tr colspan="2">
            <td>题型：</td>
            <td><span set-key="typeName"></span></td>
        </tr>
        <tr colspan="2">
            <td>题目：</td>
            <td><span set-key="title"></span></td>
        </tr>
         <table id="viewsingletable">	
         	<tr colspan="2">
	            <td>正确答案：</td>
	            <td><span set-key="rightoption"></span></td>
	        </tr>
	        <tr colspan="2">
	            <td>错误答案1：</td>
	            <td><span set-key="erroption1"></span></td>
	        </tr>
	        <tr colspan="2">
	            <td>错误答案2：</td>
	            <td><span set-key="erroption2"></span></td>
	        </tr>
	        <tr colspan="2">
	            <td>错误答案3：</td>
	            <td><span set-key="erroption3"></span></td>
	        </tr>
	         <tr colspan="2">
	            <td>答案解析：</td>
	            <td><span set-key="analysis"></span></td>
	        </tr>
         </table>	
         <table id="viewmaterialtable">	
         	 <tr colspan="2">
	            <td>材料：</td>
	            <td><span set-key="desc"></span></td>
	        </tr>
	        <tr>
         		<td>选项：</td>
	            <td colspan="2" style="padding-top:0;">
	            	[@a.datagrid id="dgmaterialview" title="材料题中的选择题" toolbar="#toolbaredit" options="width:420,fitColumns:true,resizeHandle:'right',singleSelect:true,method:'get'"]
						<th data-options="field:'uid',hidden:true"></th>
						<th data-options="field:'title',width:20">标题</th>
					[/@a.datagrid]
					[@a.toolbar id="tooldgmaterialview" canadd=false candel=false canedit=false canview=false]
			        	<a id="addbtn" href="" onclick="viewqst('#dgmaterialview')" class="easyui-linkbutton" data-options="iconCls:'icon-add'">查看详情</a>
					[/@a.toolbar]
				</td>
	        </tr>
         </table>
        
    [/@a.viewsection]
    
    
    
    <div id="questionsview" class="easyui-window" title="材料题选项" style="width:850px;height:400px" data-options="iconCls:'icon-add',modal:true,closed:true">
     	<table id="">	
	        <tr colspan="2">
	            <td>题&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;目：</td>
	            <td><span set-key="title"></span></td>
	        </tr>
	    	</br>
	        <tr colspan="2">
		            <td>正确答案：</td>
		            <td><span set-key="rightoption"></span></td>
		        </tr>
	        </br>
	        <tr colspan="2">
		            <td>错误答案1：</td>
		            <td><span set-key="erroption1"></span></td>
		        </tr>
	        </br>
	        <tr colspan="2">
		            <td>错误答案2：</td>
		            <td><span set-key="erroption2"></span></td>
		        </tr>
	        </br>
	        <tr colspan="2">
		            <td>错误答案3：</td>
		            <td><span set-key="erroption3"></span></td>
		        </tr>
	        </br>
	        <tr colspan="2">
	            <td>答案解析：</td>
	            <td><span set-key="analysis"></span></td>
	        </tr>
        </table>
	</div>  
	
</body>
</html>
