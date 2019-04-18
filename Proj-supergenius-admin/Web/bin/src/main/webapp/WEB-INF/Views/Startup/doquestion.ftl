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
	    if (row[i].type == "0") {
	        row[i].typeName = "一票否决";
	    } else if (row[i].type == "1") {
	        row[i].typeName = "创业加分";
	    }
	    if (row[i].options != "" && row[i].options != undefined) {
	        var viewOptionsJson = JSON.parse(row[i].options);
	        var viewOptionScoreJson = JSON.parse(row[i].optionscore);
	        var viewOptionsHtml = '';
	        if (viewOptionsJson[''] != undefined && viewOptionsJson[''] != '') {
	            $.each(viewOptionsJson[''], function(idx, obj) {
	                viewOptionsHtml += '<span style="font-weight:bold;">' + idx + '</span>' + '<span>&nbsp;&nbsp;&nbsp;&nbsp;' + obj + '</span>&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:red;">' + viewOptionScoreJson[''][idx] + '分</span><br>';
	            });
	        } else {
	           if (viewOptionsJson['0'] != undefined && viewOptionsJson['0'] != '') {
	               viewOptionsHtml += '<span style="font-weight:bold; color:red;">男生选项 </span><br>';
	               $.each(viewOptionsJson['0'], function(idx, obj) {
	                   viewOptionsHtml += '<span style="font-weight:bold;">' + idx + '</span>' + '<span>&nbsp;&nbsp;&nbsp;&nbsp;' + obj + '</span>&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:red;">' + viewOptionScoreJson['0'][idx] + '分</span><br>';
	               });
	           }
	           if (viewOptionsJson['1'] != undefined && viewOptionsJson['1'] != '') {
	               viewOptionsHtml += '<span style="font-weight:bold; color:red;">女生选项 </span><br>';
	               $.each(viewOptionsJson['1'], function(idx, obj) {
	                   viewOptionsHtml += '<span style="font-weight:bold;">' + idx + '</span>&nbsp;&nbsp;&nbsp;&nbsp;' + '<span>' + obj + '</span>&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:red;">' + viewOptionScoreJson['1'][idx] + '分</span><br>';
	               });
	           }
	        }
	        row[i].viewOptionsHtml = viewOptionsHtml;
	    }
	}
}

function mydgOnSelectHandler(event, j) {
	$("#don").hide();
	var row = getSelected(j.dg);
	if (row.options != "" && row.options != undefined) {
		var viewOptionsJson = JSON.parse(row.options);
        if (viewOptionsJson[''] != undefined && viewOptionsJson[''] != '') {
            chart("hav", row.optionList,"选项被选次数柱状图");
        } else {
           $("#don").show();
           if (viewOptionsJson['0'] != undefined && viewOptionsJson['0'] != '') {
	           chart("hav", row.optionsMap['0'],"男生选项被选次数柱状图");
           }
           if (viewOptionsJson['1'] != undefined && viewOptionsJson['1'] != '') {
	           chart("don", row.optionsMap['1'],"女生选项被选次数柱状图");
           }
		}
	}
}
var optionArray = new Array("A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T");//最多添加选项到T;

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
   	   	       optionscore = '{',
   	   	       flag = 'true';
   	       if ($("#genderall").is(":checked")) {
   	           $("#optionTable").find("input").each(function() {
   	               if ($(this).val() == "" || $(this).val == 'undefined') {
   	                   flag = 'false';
                       err("您还有未填完的选项，请您完善后再提交！");
                       return false;
                   }
   	           });
   	           options += '"":{';
   	           optionscore += '"":{';
   	           $("#optionTable").find(".options").each(function() {
   	               var selectOption = $(this).find('.select').text();
	   	           var selectValue = $(this).find('input[name^="option_"]').val();
	   	           var selectScore = $(this).find('input[name^="score_"]').val();
	   	           if (selectOption.length == 1) {
	   	               options += '"' + selectOption + '":"' + selectValue + '",';
	   	               optionscore += '"' + selectOption + '":' + selectScore + ',';
   	               }
   	           });
   	           options = options.substring(0, options.length-1) + '}}';
   	           optionscore = optionscore.substring(0, optionscore.length-1) + '}}';
   	       } else {
   	           if ($("#genderone").is(":checked")) { //男生按钮被选中
   	           	   $("#optionTable0").find("input").each(function() {
   	                   if ($(this).val() == "" || $(this).val == 'undefined') {
   	                       flag = 'false';
   	                       err("您还有未填完的选项，请您完善后再提交！");
   	                       return false;
   	                   }
   	               });
   	               if (flag != 'true') {
		   	           return false;
		   	       }
   	               $("#optionTable1").find("input").each(function() {
   	                   if ($(this).val() == "" || $(this).val == 'undefined') {
   	                       flag = 'false';
   	                       err("您男生选项里还有未填完的选项，请您完善后再提交！");
   	                       return false;
   	                   }
   	               });
   	               if (flag != 'true') {
		   	           return false;
		   	       }
   	           } else {
   	           	   $("#optionTable1").find("input").each(function() {
   	                   if ($(this).val() == "" || $(this).val == 'undefined') {
   	                       flag = 'false';
   	                       err("您还有未填完的选项，请您完善后再提交！");
   	                       return false;
   	                   }
   	               });
   	               if (flag != 'true') {
		   	           return false;
		   	       }
   	               $("#optionTable0").find("input").each(function() {
   	                   if ($(this).val() == "" || $(this).val == 'undefined') {
   	                       flag = 'false';
   	                       err("您男生选项里还有未填完的选项，请您完善后再提交！");
   	                       return false;
   	                   }
   	               });
   	               if (flag != 'true') {
		   	           return false;
		   	       }
   	           }
   	           //组织数据，包括男女选项
               options += '"0":{';
               optionscore += '"0":{';
   	           $("#optionTable0").find(".options").each(function() {
   	               var selectOption = $(this).find('.select').text();
	   	           var selectValue = $(this).find('input[name^="option_"]').val();
	   	           var selectScore = $(this).find('input[name^="score_"]').val();
	   	           if (selectOption.length == 1) {
	   	               options += '"' + selectOption + '":"' + selectValue + '",';
	   	               optionscore += '"' + selectOption + '":' + selectScore + ',';
   	               }
   	           });
   	           options = options.substring(0, options.length-1) + '},';
               optionscore = optionscore.substring(0, optionscore.length-1) + '},';
               options += '"1":{';
               optionscore += '"1":{';
                $("#optionTable1").find(".options").each(function() {
   	               var selectOption = $(this).find('.select').text();
	   	           var selectValue = $(this).find('input[name^="option_"]').val();
	   	           var selectScore = $(this).find('input[name^="score_"]').val();
	   	           if (selectOption.length == 1) {
	   	               options += '"' + selectOption + '":"' + selectValue + '",';
	   	               optionscore += '"' + selectOption + '":' + selectScore + ',';
   	               }
   	           });
   	           options = options.substring(0, options.length-1) + '}}';
               optionscore = optionscore.substring(0, optionscore.length-1) + '}}';
   	       }
   	       if (flag != 'true') {
   	           return false;
   	       }
   	   	   param.options = options;
   	   	   param.optionscore = optionscore;
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

//自定义编辑题目的修改方法
function submitHandlerForEdit(obj, dg){
   $(obj).parents("form").form('submit', {
   	   onSubmit: function(param) {
   	       var options = '{',
   	   	       optionscore = '{',
   	   	       flag = 'true';
   	       if ($("#editgenderall").is(":checked")) {
   	           $("#editOptionTable").find("input").each(function() {
   	               if ($(this).val() == "" || $(this).val == 'undefined') {
   	                   flag = 'false';
                       err("您还有未填完的选项，请您完善后再提交！");
                       return false;
                   }
   	           });
   	           options += '"":{';
   	           optionscore += '"":{';
   	           $("#editOptionTable").find(".editOptions").each(function() {
   	               var selectOption = $(this).find('.select').text();
	   	           var selectValue = $(this).find('input[name^="option_"]').val();
	   	           var selectScore = $(this).find('input[name^="score_"]').val();
	   	           if (selectOption.length == 1) {
	   	               options += '"' + selectOption + '":"' + selectValue + '",';
	   	               optionscore += '"' + selectOption + '":' + selectScore + ',';
   	               }
   	           });
   	           options = options.substring(0, options.length-1) + '}}';
   	           optionscore = optionscore.substring(0, optionscore.length-1) + '}}';
   	       } else {
   	           if ($("#editgenderone").is(":checked")) { //男生按钮被选中
   	           	   $("#editOptionTable0").find("input").each(function() {
   	                   if ($(this).val() == "" || $(this).val == undefined) {
   	                       flag = 'false';
   	                       err("您还有未填完的选项，请您完善后再提交！");
   	                       return false;
   	                   }
   	               });
   	               if (flag != 'true') {
		   	           return false;
		   	       }
   	               $("#editOptionTable1").find("input").each(function() {
   	                   if ($(this).val() == "" || $(this).val == undefined) {
   	                       flag = 'false';
   	                       err("您女生选项里还有未填完的选项，请您完善后再提交！");
   	                       return false;
   	                   }
   	               });
   	               if (flag != 'true') {
		   	           return false;
		   	       }
   	           } else {
   	           	   $("#editOptionTable1").find("input").each(function() {
   	                   if ($(this).val() == "" || $(this).val == undefined) {
   	                       flag = 'false';
   	                       err("您还有未填完的选项，请您完善后再提交！");
   	                       return false;
   	                   }
   	               });
   	               if (flag != 'true') {
		   	           return false;
		   	       }
   	               $("#editOptionTable0").find("input").each(function() {
   	                   if ($(this).val() == "" || $(this).val == undefined) {
   	                       flag = 'false';
   	                       err("您男生选项里还有未填完的选项，请您完善后再提交！");
   	                       return false;
   	                   }
   	               });
   	               if (flag != 'true') {
		   	           return false;
		   	       }
   	           }
   	           //组织数据，包括男女选项
               options += '"0":{';
               optionscore += '"0":{';
   	           $("#editOptionTable0").find(".editOptions").each(function() {
   	               var selectOption = $(this).find('.select').text();
	   	           var selectValue = $(this).find('input[name^="option_"]').val();
	   	           var selectScore = $(this).find('input[name^="score_"]').val();
	   	           if (selectOption.length == 1) {
	   	               options += '"' + selectOption + '":"' + selectValue + '",';
	   	               optionscore += '"' + selectOption + '":' + selectScore + ',';
   	               }
   	           });
   	           options = options.substring(0, options.length-1) + '},';
               optionscore = optionscore.substring(0, optionscore.length-1) + '},';
               options += '"1":{';
               optionscore += '"1":{';
                $("#editOptionTable1").find(".editOptions").each(function() {
   	               var selectOption = $(this).find('.select').text();
	   	           var selectValue = $(this).find('input[name^="option_"]').val();
	   	           var selectScore = $(this).find('input[name^="score_"]').val();
	   	           if (selectOption.length == 1) {
	   	               options += '"' + selectOption + '":"' + selectValue + '",';
	   	               optionscore += '"' + selectOption + '":' + selectScore + ',';
   	               }
   	           });
   	           options = options.substring(0, options.length-1) + '}}';
               optionscore = optionscore.substring(0, optionscore.length-1) + '}}';
   	       }
   	       if (flag != 'true') {
   	           return false;
   	       }
   	   	   param.options = options;
   	   	   param.optionscore = optionscore;
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

function generateEditOptionHtml(editIndex, idx, obj, optionscoreJson) {
    var editHtml = '';
    editHtml += '<tr class="editOptions editOption_' + editIndex + '">';
    editHtml += '<td><label for="option_'+ editIndex +'"><font class="select" size="4">' + idx + '</font></label>&nbsp;&nbsp;&nbsp;';
    editHtml += '<input type="text" id="option_' + editIndex + '" style="width:400px;" class="easyui-validatebox txt" value="' + obj + '" name="option_' + editIndex + '" data-options="required:true" />';
    editHtml += '<input type="text" class="easyui-numberbox txt" name="score_' + editIndex + '" style="width:120px;" data-options="min:0, max:10, required:true" value="' + optionscoreJson[idx] + '" placeholder="请输入0-10的得分"/>';
    editHtml += '<label class="addOption addSelect hd"><font color="blue">添加选项</font></label>&nbsp;&nbsp;&nbsp;';
    editHtml += '<label class="delOption delSelect hd"><font color="blue">删除选项</font></label></td></tr>';
    return editHtml;
}

function initManEditOption() {
	var initIndexMan = 3;
    var initManEditHtml = '';
    initManEditHtml += '<table id="editOptionTable0" class="man hd">';
    for (var i = 0; i < initIndexMan; i++) {
        initManEditHtml += generateOptionHtml(i);
    }
    initManEditHtml += '</table>';
    return initManEditHtml;
}

function initWomanEditOption() {
	var initIndexWoman = 3;
    var initWomanEditHtml = '';
    initWomanEditHtml += '<table id="editOptionTable1" class="woman hd">';
    for (var i = 0; i < initIndexWoman; i++) {
        initWomanEditHtml += generateOptionHtml(i);
    }
    initWomanEditHtml += '</table>';
    return initWomanEditHtml;
}

function initAllEditOption() {
	var initIndexAll = 3;
    var initAllEditHtml = '';
    initAllEditHtml += '<table id="editOptionTable" class="all hd">';
    for (var i = 0; i < initIndexAll; i++) {
        initAllEditHtml += generateOptionHtml(i);
    }
    initAllEditHtml += '</table>';
    return initAllEditHtml;
}

//调整编辑页面显示选项内容
function editOptionsShow(row) {
    $("#editOptionsBox").empty();
    if (row.options && row.optionscore) {
        var editIndex = 0;
        var optionsJson = JSON.parse(row.options);
        var optionscoreJson = JSON.parse(row.optionscore);
        var editOptionTableHtml = '';
        if (optionsJson && optionscoreJson) {
            if (optionsJson[''] != undefined && optionsJson[''] != '') {//该选项不区分性别
                $("#editgenderall").attr("checked", "checked");//选中全部
                editOptionTableHtml = '<table id="editOptionTable" class="all">';
                editIndex = 0;
                $.each(optionsJson[''], function(idx, obj) {
                    editOptionTableHtml += generateEditOptionHtml(editIndex, idx, obj, optionscoreJson['']);
                    editIndex ++;
                });
                editOptionTableHtml += '</table>';
                editOptionTableHtml += initManEditOption() + initWomanEditOption();
                $("#editOptionsBox").append(editOptionTableHtml);
                $("#editOptionTable").find(".editOptions").eq(-1).find(".addSelect").removeClass("hd");
                $("#editOptionTable").find(".editOptions").eq(-1).find(".delSelect").removeClass("hd");
                
            } else {
                $("#editgenderone").attr("checked", "checked");//默认选中男
                if (optionsJson['0'] != undefined && optionsJson['0'] != '') {//该选项为男
	                editOptionTableHtml = '<table id="editOptionTable0" class="man">';
	                editIndex = 0;
	                $.each(optionsJson['0'], function(idx, obj) {
	                    editOptionTableHtml += generateEditOptionHtml(editIndex, idx, obj, optionscoreJson['0']);
	                    editIndex ++;
	                });
	                editOptionTableHtml += '</table>';
	                $("#editOptionsBox").append(editOptionTableHtml);
	                $("#editOptionTable0").find(".editOptions").eq(-1).find(".addSelect").removeClass("hd");
	                $("#editOptionTable0").find(".editOptions").eq(-1).find(".delSelect").removeClass("hd");
            	}
            	
            	if (optionsJson['1'] != undefined && optionsJson['1'] != '') {//该选项为女
	                editOptionTableHtml = '<table id="editOptionTable1" class="woman hd">';
	                editIndex = 0;
	                $.each(optionsJson['1'], function(idx, obj) {
	                    editOptionTableHtml += generateEditOptionHtml(editIndex, idx, obj, optionscoreJson['1']);
	                    editIndex ++;
	                });
	                editOptionTableHtml += '</table>';
	                $("#editOptionsBox").append(editOptionTableHtml).append(initAllEditOption());
	                $("#editOptionTable1").find(".editOptions").eq(-1).find(".addSelect").removeClass("hd");
	                $("#editOptionTable1").find(".editOptions").eq(-1).find(".delSelect").removeClass("hd");
            	}
            }
        }
        $.parser.parse($("#editOptionsBox"));
    }
    
    //监听点击增加和删除事件
    var reg = /\d+/g; //取出class属性字段中的数字
	$("#editOptionsBox").unbind('click').on('click', '.addOption', function(event) {
    	$(this).addClass("hd");
	    $(this).siblings(".delSelect").addClass("hd");
	    var option_index_class = $(this).parent().parent().attr("class");
	    var option_index = option_index_class.match(reg);
	    option_index ++;
	    if ($(this).parents("#editOptionTable").attr("id") == 'editOptionTable') {
	        $("#editOptionTable").append(generateOptionHtml(option_index));
	        event.stopPropagation();//阻止事件冒泡
	    } else if ($(this).parents("#editOptionTable0").attr("id") == 'editOptionTable0') {
	        $("#editOptionTable0").append(generateOptionHtml(option_index));
	        event.stopPropagation();
	    } else if ($(this).parents("#editOptionTable1").attr("id") == 'editOptionTable1') {
	        $("#editOptionTable1").append(generateOptionHtml(option_index));
	        event.stopPropagation();
	    }
	    $.parser.parse($(".addOption").parent().parent());//渲染<tr>元素
	    if (option_index < optionArray.length) {
	        $(".addOption").on("click");
	        $(".delOption").on("click");
	    } else {
	        //$("#optionTable").off('click');
	        $(".addOption").addClass("hd");
	    }
	});
	$("#editOptionsBox").on('click', '.delOption', function() { //监听删除事件
	    $(this).parents(".editOptions, .options").prev(".editOptions, .options").find(".addSelect").removeClass("hd");
	    $(this).parents(".editOptions, .options").prev(".editOptions, .options").find(".delSelect").removeClass("hd");
	    $(this).parents(".editOptions, .options").remove();
	});
}

//拼接添加选项html代码
function generateOptionHtml(option_index) {
    var optionHtml = '';
	optionHtml += '<tr class="options editOptions option_' + option_index + '">';
	optionHtml += '<td><label for="option_' + option_index + '"><font class="select" size="4">' + optionArray[option_index] + '</font></label>&nbsp;&nbsp;&nbsp;';
	optionHtml += '<input type="text" id="option_' + option_index + '" style="width:400px;" class="easyui-validatebox txt" name="option_' + option_index + '" data-options="required:true" />';
	optionHtml += '<input type="text" class="easyui-numberbox txt" name="score_' + option_index + '" style="width:120px;" data-options="min:0, max:10, required:true" placeholder="请输入0-10的得分"/>';
	optionHtml += '<label class="addOption addSelect"><font color="blue">添加选项</font></label> &nbsp;&nbsp;&nbsp;';
	optionHtml += '<label class="delOption delSelect"><font color="blue">删除选项</font></label></td></tr>';
	return optionHtml;
}

//重置方法
function resetFormForAdd(obj, dg) { 
	 option_index = 3
     $(obj).parents("form").form('reset');
     $(".option_2").nextAll().remove();
     $(".option_2").find(".addSelect").addClass("addOption").removeClass("hd");
}
var option_index = 3;//添加问题时初始选项个数，若要修改，则需要修改该初始数字并修改addsection中的html代码
$(function() {

	$("#btnadd").click(function() {
	    resetFormForAdd();
    });
    
    $("#btnedit").click(function() {
    	//调整编辑页面显示选项内容
    	var row = getSelected(dg);
	    if (row) {
	        editOptionsShow(row);
	    } else {
	        err("请选择一项！");
	    }
    });
    
	//对动态生成的代码绑定click事件和重新渲染元素
	$("#optionTable").on('click', '.addOption', function() {
	    //$(this).addClass("hd").removeClass("addOption");
	    $(this).addClass("hd");
	    $(this).siblings(".delSelect").addClass("hd");
	    $("#optionTable").append(generateOptionHtml(option_index));
	    $.parser.parse($(".addOption").parent().parent());//渲染<tr>元素
	    option_index ++;
	    if (option_index < optionArray.length) {
	        $(".addOption").on("click");
	    } else {
	        //$("#optionTable").off('click');
	        $(".addOption").addClass("hd");
	    }
	});
	
	$("#optionTable").on('click', '.delOption', function() {
	    $(this).parents(".options").prev(".options").find(".addSelect").addClass("addOption").removeClass("hd");
	    $(this).parents(".options").prev(".options").find(".delSelect").removeClass("hd");
	    $(this).parents(".options").remove();
	    option_index --;
	});
	
	$("#optionTable0").on('click', '.addOption', function() {
	    //$(this).addClass("hd").removeClass("addOption");
	    $(this).addClass("hd");
	    $("#optionTable0").append(generateOptionHtml(option_index));
	    $.parser.parse($(".addOption").parent().parent());//渲染<tr>元素
	    option_index ++;
	    if (option_index < optionArray.length) {
	        $(".addOption").on("click");
	    } else {
	        //$("#optionTable0").off('click');
	        $(".addOption").addClass("hd");
	    }
	});
	
	$("#optionTable0").on('click', '.delOption', function() {
	    $(this).parents(".options").prev(".options").find(".addSelect").addClass("addOption").removeClass("hd");
	    $(this).parents(".options").prev(".options").find(".delSelect").removeClass("hd");
	    $(this).parents(".options").remove();
	    option_index --;
	});
	
	$("#optionTable1").on('click', '.addOption', function() {
	    //$(this).addClass("hd").removeClass("addOption");
	    $(this).addClass("hd");
	    $("#optionTable1").append(generateOptionHtml(option_index));
	    $.parser.parse($(".addOption").parent().parent());//渲染<tr>元素
	    option_index ++;
	    if (option_index < optionArray.length) {
	        $(".addOption").on("click");
	    } else {
	        //$("#optionTable1").off('click');
	        $(".addOption").addClass("hd");
	    }
	});
	
	$("#optionTable1").on('click', '.delOption', function() {
	    $(this).parents(".options").prev(".options").find(".addSelect").addClass("addOption").removeClass("hd");
	    $(this).parents(".options").prev(".options").find(".delSelect").removeClass("hd");
	    $(this).parents(".options").remove();
	    option_index --;
	});
	
	//选择性别时切换不同的选项,当选则男或者女时清除全部的文本框中的内容，反之清除男女table中的内容
	$("#genderone").click(function() {
	    $("#optionTable0").removeClass("hd").siblings().addClass("hd");
	    $("#optionTable").find('input').val("");
	});
	
	$("#gendertwo").click(function() {
	    $("#optionTable1").removeClass("hd").siblings().addClass("hd");
	    $("#optionTable").find('input').val("");
	});
	
	$("#genderall").click(function() {
	    $("#optionTable").removeClass("hd").siblings().addClass("hd");
	    $("#optionTable0").find('input').val("");
	    $("#optionTable1").find('input').val("");
	});
	
	$("#editgenderone").click(function() {
	    $("#editOptionTable0").removeClass("hd").siblings().addClass("hd");
	    //$("#editOptionTable").find(".editOption_2").nextAll().remove();
	    $("#editOptionTable0").find(".editOptions").eq(-1).siblings().find(".addSelect").addClass("hd");
	    $("#editOptionTable0").find(".editOptions").eq(-1).siblings().find(".delSelect").addClass("hd");
	    $("#editOptionTable0").find(".editOptions").eq(-1).find(".addSelect").removeClass("hd");
	    $("#editOptionTable0").find(".editOptions").eq(-1).find(".delSelect").removeClass("hd");
	    $("#editOptionTable").remove();
	    $("#editOptionsBox").append(initAllEditOption());
	    $.parser.parse($("#editOptionTable0"));
	});
	$("#editgendertwo").click(function() {
	    $("#editOptionTable1").removeClass("hd").siblings().addClass("hd");
	    //$("#editOptionTable").find(".editOption_2").nextAll().remove();
	    $("#editOptionTable1").find(".editOptions").eq(-1).siblings().find(".addSelect").addClass("hd");
	    $("#editOptionTable1").find(".editOptions").eq(-1).siblings().find(".delSelect").addClass("hd");
	    $("#editOptionTable1").find(".editOptions").eq(-1).find(".addSelect").removeClass("hd");
	    $("#editOptionTable1").find(".editOptions").eq(-1).find(".delSelect").removeClass("hd");
	    $("#editOptionTable").remove();
	    $("#editOptionsBox").append(initAllEditOption());
	    $.parser.parse($("#editOptionTable1"));
	});
	$("#editgenderall").click(function() {
	    $("#editOptionTable").removeClass("hd").siblings().addClass("hd");
	    //$("#editOptionTable0").find(".editOption_2").nextAll().remove();
	    //$("#editOptionTable1").find(".editOption_2").nextAll().remove();
	    $("#editOptionTable").find(".editOptions").eq(-1).siblings().find(".addSelect").addClass("hd");
	    $("#editOptionTable").find(".editOptions").eq(-1).siblings().find(".delSelect").addClass("hd");
	    $("#editOptionTable").find(".editOptions").eq(-1).find(".addSelect").removeClass("hd");
	    $("#editOptionTable").find(".editOptions").eq(-1).find(".delSelect").removeClass("hd");
	    $("#editOptionTable0").remove();
	    $("#editOptionTable1").remove();
	    $("#editOptionsBox").append(initManEditOption());
	    $("#editOptionsBox").append(initWomanEditOption());
	    $.parser.parse($("#editOptionTable"));
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
                <td>题目类别：
                    <input type="radio" id="typeall" name="type" value="" checked/><label for="typeall">全部</label>
                    <input type="radio" id="typeone" name="type" value="1"/><label for="typeone">创业加分</label>
                    <input type="radio" id="typetwo" name="type" value="0"/><label for="typetwo">一票否决</label>
                </td>
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
            <th data-options="field:'typeName',width:75">题目类型</th>
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
	            <td>题目类型:</td>
	            <td><input type="radio" id="typethree" value="0" name="type" /><label for="typethree">一票否决</label>  <input type="radio" id="typefour" value="1" name="type" checked/><label for="typefour">创业加分</label></td>
	        </tr>
	        <tr>
	            <td>题目名称:</td>
	            <td><input type="text" class="easyui-validatebox" name="name" data-options="required:true" /></td>
	        </tr>
	        <tr>
	            <td>图片:</td>
	            <td class="addimgdata">[@a.imgupload multiple=false imgpath="${photopath}" name="imgdata" size=""/]</td>
        	</tr>
	        <tr>
	            <td>性别:</td>
	            <td><input type="radio" id="genderall" name="gender" value="" checked/><label for="genderall">全部</label>   <input type="radio" id="genderone" name="gender" value="0" /><label for="genderone">男</label>      <input type="radio" id="gendertwo" name="gender" value="1" /><label for="gendertwo">女</label></td>
	        </tr>
	        <tr>
		        <td>添加选项:</td>
		        <td>
			        <table id="optionTable" class="all">
				        <tr class="options option_0">
				            <td><label for="option_0"><font class="select" size="4">A</font></label>&nbsp;&nbsp;&nbsp;<input type="text" id="option_0" style="width:400px;" class="easyui-validatebox" name="option_0" data-options="required:true" /> <input type="text" class="easyui-numberbox" name="score_0" style="width:120px;" data-options="min:0, max:10, required:true" placeholder="请输入0-10的得分"/></td>
				        </tr>
				        <tr class="options option_1">
				        	<td><label for="option_1"><font class="select" size="4">B</font></label>&nbsp;&nbsp;&nbsp;<input type="text" id="option_1" style="width:400px;" class="easyui-validatebox" name="option_1" data-options="required:true" /> <input type="text" class="easyui-numberbox" name="score_1" style="width:120px;" data-options="min:0, max:10, required:true" placeholder="请输入0-10的得分"/></td>
				        </tr>
				        <tr class="options option_2">
				        	<td><label for="option_2"><font class="select" size="4">C</font></label>&nbsp;&nbsp;&nbsp;<input type="text" id="option_2" style="width:400px;" class="easyui-validatebox" name="option_2" data-options="required:true" /> <input type="text" class="easyui-numberbox" name="score_2" style="width:120px;" data-options="min:0, max:10, required:true" placeholder="请输入0-10的得分"/><label class="addOption addSelect"><font color="blue">添加选项</font></label></td>
				        </tr>
			        </table>
			        <table id="optionTable0" class="hd man">
				        <tr class="options option_0">
				            <td><label for="option_0"><font class="select" size="4">A</font></label>&nbsp;&nbsp;&nbsp;<input type="text" id="option_0" style="width:400px;" class="easyui-validatebox" name="option_0" data-options="required:true" /> <input type="text" class="easyui-numberbox" name="score_0" style="width:120px;" data-options="min:0, max:10, required:true" placeholder="请输入0-10的得分"/></td>
				        </tr>
				        <tr class="options option_1">
				        	<td><label for="option_1"><font class="select" size="4">B</font></label>&nbsp;&nbsp;&nbsp;<input type="text" id="option_1" style="width:400px;" class="easyui-validatebox" name="option_1" data-options="required:true" /> <input type="text" class="easyui-numberbox" name="score_1" style="width:120px;" data-options="min:0, max:10, required:true" placeholder="请输入0-10的得分"/></td>
				        </tr>
				        <tr class="options option_2">
				        	<td><label for="option_2"><font class="select" size="4">C</font></label>&nbsp;&nbsp;&nbsp;<input type="text" id="option_2" style="width:400px;" class="easyui-validatebox" name="option_2" data-options="required:true" /> <input type="text" class="easyui-numberbox" name="score_2" style="width:120px;" data-options="min:0, max:10, required:true" placeholder="请输入0-10的得分"/><label class="addOption addSelect"><font color="blue">添加选项</font></label></td>
				        </tr>
			        </table>
			        <table id="optionTable1" class="hd woman">
				        <tr class="options option_0">
				            <td><label for="option_0"><font class="select" size="4">A</font></label>&nbsp;&nbsp;&nbsp;<input type="text" id="option_0" style="width:400px;" class="easyui-validatebox" name="option_0" data-options="required:true" /> <input type="text" class="easyui-numberbox" name="score_0" style="width:120px;" data-options="min:0, max:10, required:true" placeholder="请输入0-10的得分"/></td>
				        </tr>
				        <tr class="options option_1">
				        	<td><label for="option_1"><font class="select" size="4">B</font></label>&nbsp;&nbsp;&nbsp;<input type="text" id="option_1" style="width:400px;" class="easyui-validatebox" name="option_1" data-options="required:true" /> <input type="text" class="easyui-numberbox" name="score_1" style="width:120px;" data-options="min:0, max:10, required:true" placeholder="请输入0-10的得分"/></td>
				        </tr>
				        <tr class="options option_2">
				        	<td><label for="option_2"><font class="select" size="4">C</font></label>&nbsp;&nbsp;&nbsp;<input type="text" id="option_2" style="width:400px;" class="easyui-validatebox" name="option_2" data-options="required:true" /> <input type="text" class="easyui-numberbox" name="score_2" style="width:120px;" data-options="min:0, max:10, required:true" placeholder="请输入0-10的得分"/><label class="addOption addSelect"><font color="blue">添加选项</font></label></td>
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
            <td>题目类型:</td>
            <td><input type="radio" id="typethree" value="0" name="type" /><label for="typethree">一票否决</label>  <input type="radio" id="typefour" value="1" name="type" /><label for="typefour">创业加分</label></td>
        </tr>
        <tr>
            <td>题目名称:</td>
            <td><input type="text" class="easyui-validatebox" style="width:500px" name="name" data-options="required:true" /></td>
        </tr>
        <tr>
            <td>封面:</td>
            <td><img id="avatarbig" style="width:150px; height:150px" src="" set-format="${webimg}{0}" set-key="img"/>
            	[@a.imgupload multiple=false imgpath="${photopath}" name="imgdata" size=""/]
            </td>
        </tr>
        <tr>
            <td>性别:</td>
            <td><input type="radio" id="editgenderall" name="gender" value="" /><label for="editgenderall">全部</label> <input type="radio" id="editgenderone" name="gender" value="0" /><label for="editgenderone">男</label>      <input type="radio" id="editgendertwo" name="gender" value="1" /><label for="editgendertwo">女</label></td>
        </tr>
        <tr>
            <td>编辑选项：</td>
            <td id="editOptionsBox"></td>
        </tr>
        [/@a.editsection]
        [@a.viewsection]
        <tr><td>题目序号:</td><td><span set-key="order"></span></td></tr>
        <tr><td>题目类型:</td><td><span set-key="typeName"></span></td></tr>
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
