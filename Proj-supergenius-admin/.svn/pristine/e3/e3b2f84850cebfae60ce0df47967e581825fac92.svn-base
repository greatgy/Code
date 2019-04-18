[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Admin.ftl" as a]
<html>
<head>
<title>题库管理</title>
<script type="text/javascript" src="${basejs}/js/echart/echarts.js"></script>

<script type="text/javascript">
<!--
function initDGData(row) {
	for (var i in row) {
		var viewOptionsHtml = '';
	    for (var k in row[i].optionList) {
	 		viewOptionsHtml += '<span style="font-weight:bold;">' + row[i].optionList[k].option + '</span>' + '<span>&nbsp;&nbsp;&nbsp;&nbsp;' + row[i].optionList[k].data + '</span>&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:red;">' + row[i].optionList[k].typename + '</span><br>';
	    } 
	    row[i].viewOptionsHtml = viewOptionsHtml;
	}
}

function mydgOnSelectHandler(event, j) {
	$("#don").hide();
	var row = getSelected(j.dg);
	if (row.options != "" && row.options != undefined) {
        chart("hav", row.optionList,"选项被选次数柱状图");
	}
}
$(function() {
$("#btnadd").click(function() {
   $(window.frames["frameImg0"].document).find("#formImg0").css("display","block");
});
    
$("#btnedit").click(function() {
	$(window.frames["frameImg0"].document).find("#formImg0").css("display","block");
});
})

function chart(id, optionList,label){
	var myChart = echarts.init(document.getElementById(id));
	var xarr = new Array();
	var sarr = new Array();
	var count=0;
	for(var i =0;i<optionList.length;i++){
	 	xarr.push(optionList[i].option);
	 	count=count+optionList[i].count;
	}
	for(var i =0;i<optionList.length;i++){
	 	sarr.push(Math.round((optionList[i].count/count).toFixed(2)*100));
	}
	xarr.reverse();
	sarr.reverse();
	var option = {
            title: {
                text: label
            },
            legend: {},
            color: ['#3398DB'],
            tooltip: {
            	trigger: 'item',  
        		formatter: '{b}:\n{c}%'  
            },
            grid: {
		        left: '3%',
		        right: '9%',
		        bottom: '3%',
		        containLabel: true
    		},
    		calculable: true,
            yAxis: [
	            {
		            type : 'category',
		            data : xarr,
		            name: "选项",
		            axisTick: {
		                alignWithLabel: true
		            }
	            }
            ],
            xAxis: [
	            {
	            	type: 'value',  
            		axisLabel: {  
		                show: true,  
		                interval: 'auto',  
		                formatter: '{value} %'  
	                },  
            		show: true,
	            	name: "百分比"
	       	 	}
       	 	],
            series: [{
                name: '数量',
                type: 'bar',
                barWidth: '60%',
                itemStyle: {  
                normal: {  
                     label: {  
                        show: true,  
                        position: 'right',  
                        formatter: '{c}%'  
                    }  
                }  
            },  
                data: sarr
            }]
	};
	myChart.setOption(option)
}

//自定义提交方法
function submitHandlerForAdd(obj, dg){
   $(obj).parents("form").form('submit', {
   	   onSubmit: function(param) {
   	       var options = '{',
   	   	       optiontype = '{',
   	   	       flag = 'true';
           $("#optionTable").find("input").each(function() {
               if ($(this).val() == "" || $(this).val == 'undefined') {
                   flag = 'false';
                   err("您还有未填完的选项，请您完善后再提交！");
                   return false;
               }
           });
           $("#optionTable").find(".options").each(function() {
               var selectOption = $(this).find('.select').text();
   	           var selectValue = $(this).find('input[name^="option_"]').val();
   	           var selectType = $(this).find('.optiontype').find("option:selected").val();
               options += '"' + selectOption + '":"' + selectValue + '",';
               optiontype += '"' + selectOption + '":' + selectType + ',';
           });
           options = options.substring(0, options.length-1) + '}';
           optiontype = optiontype.substring(0, optiontype.length-1) + '}';
   	   	   param.options = options;
   	   	   param.optiontype = optiontype;
   	   },
       success : function(str) {
       		console.log(str);
			var result = $.parseJSON(str);
			console.log(result);
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

//自定义编辑题目的修改方法
function submitHandlerForEdit(obj, dg){
   $(obj).parents("form").form('submit', {
   	   onSubmit: function(param) {
   	       var options = '{',
   	   	       optiontype = '{';
   	       $("#optionEditTable").find("input").each(function() {
               if ($(this).val() == "" || $(this).val == 'undefined') {
                   flag = 'false';
                   err("您还有未填完的选项，请您完善后再提交！");
                   return false;
               }
   	       });
           $("#optionEditTable").find(".options").each(function() {
               var selectOption = $(this).find('.select').text();
   	           var selectValue = $(this).find('input[name^="option_"]').val();
   	           var selectType = $(this).find('select[id^="optiontype_"]').find("option:selected").val();
               options += '"' + selectOption + '":"' + selectValue + '",';
               optiontype += '"' + selectOption + '":' + selectType + ',';
           });
           options = options.substring(0, options.length-1) + '}';
           optiontype = optiontype.substring(0, optiontype.length-1) + '}';
   	   	   param.options = options;
   	   	   param.optiontype = optiontype;
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

//调整编辑页面显示选项内容
function editOptionsShow(row) {
    $("#editOptionsBox").empty();
    if (row.options && row.optiontype) {
        var editIndex = 0;
        var optionsJson = JSON.parse(row.options);
        var optiontypeJson = JSON.parse(row.optiontype);
        var editOptionTableHtml = '';
        if (optionsJson && optiontypeJson) {
             for (var i in row.optionList) {
                editOptionTableHtml+= '<tr class="options"><td><label for="option_' + row.optionList[i].option + '"><font class="select" size="4">'+row.optionList[i].option+'</font></label>&nbsp;&nbsp;&nbsp;<input type="text" id="editoption_' + row.optionList[i].option + '" style="width:400px;" value="' + row.optionList[i].data + '"class="easyui-validatebox" name="option_' + row.optionList[i].option + '" data-options="required:true" /><select id="optiontype_' + row.optionList[i].option + '"><option value ="1">研究型</option><option value ="2">常规型</option><option value="3">艺术型</option><option value="4">企业型</option><option value="5">现实型</option><option value="6">社会型</option></select></td></tr>';
            }
        }
        $("#optionEditTable").html(editOptionTableHtml);
        for (var k in row.optionList) {
        	$("#optiontype_"+row.optionList[k].option).find("option[value = '"+row.optionList[k].type+"']").attr("selected","selected");
        }
    }
    
}

$(function() {
    $("#btnedit").click(function() {
    	//调整编辑页面显示选项内容
    	var row = getSelected(dg);
	    if (row) {
	        editOptionsShow(row);
	    } else {
	        err("请选择一项！");
	    }
    });
})
//-->
</script> 	
</head>
<body>
    <section>
        <table>
            <tr>
                <td>测试总题数:</td>
                <td><span>${count}</span></td>
            <tr>
        </table>
        [@a.search]
        	<tr>
                 <td><label for="sname">序号：</label><input id="sname" type="text" name="order" class="search" style="width:200px;"/></td>
                 <td><label for="sname">题目名称：</label><input id="sname" type="text" name="name" class="search" style="width:300px;"/></td>
        	</tr>
        	<tr>
                <td>状态：
                    <input type="radio" id="statusall" name="status" value="" /><label for="statusall">全部</label>
                    <input type="radio" id="statusone" name="status" value="1" checked /><label for="statusall">正常</label>
                    <input type="radio" id="statustwo" name="status" value="0" /><label for="statusall">冻结</label>
                </td>
        	</tr>
        [/@a.search]
        
        [@a.datagrid]
            <th data-options="field:'order',width:100">序号</th>
            <th data-options="field:'name',width:300">题目名称</th>
            <th data-options="field:'statusName',width:75">状态</th>
            <th data-options="field:'createtimeStr',width:175">创建时间</th>
            <th data-options="field:'adminName',width:75">操作人</th>
        [/@a.datagrid]
        [@a.toolbar]
        [/@a.toolbar]
        [@a.tools /]
	</section>
        [@a.addsection onclicksubmit="submitHandlerForAdd(this)" onclickreset="resetFormForAdd(this)"]
	        <tr>
	            <td>题目序号:</td>
	            <td><input type="text" class="easyui-numberbox" name="order" data-options="min:0,required:true" /></td>
	        </tr>
	        <tr>
	            <td>题目名称:</td>
	            <td><input type="text" class="easyui-validatebox" name="name" data-options="required:true" /></td>
	        </tr>
	        <tr>
	            <td>图片:</td>
	            <td class="addimgdata">[@a.imgupload multiple=false imgpath="${photopath}" name="imgdata"/]</td>
        	</tr>
	        <tr>
		        <td>添加选项:</td>
		        <td>
			        <table id="optionTable">
				        <tr class="options option_0">
				            <td><label for="option_0"><font class="select" size="4">A</font></label>&nbsp;&nbsp;&nbsp;<input type="text" id="option_0" style="width:400px;" class="easyui-validatebox" name="option_0" data-options="required:true" />
				            <select class="optiontype">
								<option value ="1">研究型</option>
								<option value ="2">常规型</option>
								<option value="3">艺术型</option>
								<option value="4">企业型</option>
								<option value="5">现实型</option>
								<option value="6">社会型</option>
							</select></td>
				 		</tr>
				        <tr class="options option_1">
				        	<td><label for="option_1"><font class="select" size="4">B</font></label>&nbsp;&nbsp;&nbsp;<input type="text" id="option_1" style="width:400px;" class="easyui-validatebox" name="option_1" data-options="required:true" />
				        	<select class="optiontype">
								 <option value ="1">研究型</option>
								 <option value ="2">常规型</option>
								 <option value="3">艺术型</option>
								 <option value="4">企业型</option>
								 <option value="5">现实型</option>
								 <option value="6">社会型</option>
							 </select></td>
				        </tr>
				        <tr class="options option_2">
				        	<td><label for="option_2"><font class="select" size="4">C</font></label>&nbsp;&nbsp;&nbsp;<input type="text" id="option_3" style="width:400px;" class="easyui-validatebox" name="option_3" data-options="required:true" />
				        	<select class="optiontype">
								 <option value ="1">研究型</option>
								 <option value ="2">常规型</option>
								 <option value="3">艺术型</option>
								 <option value="4">企业型</option>
								 <option value="5">现实型</option>
								 <option value="6">社会型</option>
							 </select></td>
				        </tr>
				        <tr class="options option_3">
				        	<td><label for="option_3"><font class="select" size="4">D</font></label>&nbsp;&nbsp;&nbsp;<input type="text" id="option_4" style="width:400px;" class="easyui-validatebox" name="option_4" data-options="required:true" />
				        	<select class="optiontype">
								 <option value ="1">研究型</option>
								 <option value ="2">常规型</option>
								 <option value="3">艺术型</option>
								 <option value="4">企业型</option>
								 <option value="5">现实型</option>
								 <option value="6">社会型</option>
							 </select></td>
				        </tr>
				        <tr class="options option_4">
				        	<td><label for="option_4"><font class="select" size="4">E</font></label>&nbsp;&nbsp;&nbsp;<input type="text" id="option_5" style="width:400px;" class="easyui-validatebox" name="option_5" data-options="required:true" />
				        	<select class="optiontype">
								 <option value ="1">研究型</option>
								 <option value ="2">常规型</option>
								 <option value="3">艺术型</option>
								 <option value="4">企业型</option>
								 <option value="5">现实型</option>
								 <option value="6">社会型</option>
							 </select></td>
				        </tr>
				        <tr class="options option_5">
				        	<td><label for="option_5"><font class="select" size="4">F</font></label>&nbsp;&nbsp;&nbsp;<input type="text" id="option_6" style="width:400px;" class="easyui-validatebox" name="option_6" data-options="required:true" />
				        	<select class="optiontype">
								 <option value ="1">研究型</option>
								 <option value ="2">常规型</option>
								 <option value="3">艺术型</option>
								 <option value="4">企业型</option>
								 <option value="5">现实型</option>
								 <option value="6">社会型</option>
							 </select></td>
				        </tr>
			        </table>
		        </td>
	        </tr>
        [/@a.addsection]
        [@a.editsection onclicksubmit="submitHandlerForEdit(this)"]
        <input type="hidden" name="uid">
        <tr>
            <td>题目序号:</td>
            <td><input type="text" class="easyui-numberbox" name="order" data-options="min:0,required:true" /></td>
        </tr>
        <tr>
            <td>题目名称:</td>
            <td><input type="text" class="easyui-validatebox" style="width:500px" name="name" data-options="required:true" /></td>
        </tr>
        <tr>
            <td>封面:</td>
            <td><img id="avatarbig" style="width:150px; height:150px" src="" set-format="${webimg}{0}" set-key="img"/>
            	[@a.imgupload multiple=false imgpath="${photopath}" name="imgdata"/]
            </td>
        </tr>
        <tr>
            <td>编辑选项：</td>
            <td>
            	<table id="optionEditTable">
            	</table>
            </td>
        </tr>
        [/@a.editsection]
        [@a.viewsection]
        <tr><td>题目序号:</td><td><span set-key="order"></span></td></tr>
        <tr><td>题目名称:</td><td><span set-key="name"></span></td></tr>
        <tr>
	        <td>题目插图:</td>
	        <td><img src="" set-format="${webimg}{0}" set-key="img" style="width: 600px; height: 400px;"/></td>
        </tr>
        <tr><td>题目选项:</td><td><span set-key="viewOptionsHtml"></span></td></tr>
        <tr><td>统计:</td><td><span id="hav" style="width: 600px;height:400px;"></span></td></tr>
        <tr><td></td><td><span id="don" style="width: 600px;height:400px;"></span></td></tr>
        [/@a.viewsection]
</body>
</html>
