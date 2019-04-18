[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Admin.ftl" as a]
<html>
<head>
<title>统计信息</title>
<script type="text/javascript" src="${basejs}/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="${basejs}/js/echart/echarts.js"></script>
<script type="text/javascript">
var questionList;
var viewOptionsHtml="";
$(function() {
	$.getJSON('${base}${baseAdminPath}/startup/ajax/statistic/question/list', function(data) {
		questionList=data.rows;
	});
	$(".l-btn-left").click(function() {
		$("#echart").addClass("hd");
		$("#first").removeClass("hd");
	});
	$("#btnview").click(function() {
    	//隐藏没有内容的tr
    	var row = getSelected(dg);
	    if (row) {
	    	if (row.useruid == null) {
	    		$(".phone").addClass("hd");
	   		}
	    } else {
	        err("请选择一项！");
	    }
    });
})

function initDGData(row) {
	for (var i in row) {
		if (row[i].ruleruid == '10000000000000000000000000000000') {
	        row[i].ruleruid = "不能确定";
	    }
	    if (row[i].ruleruid == '10000000000000000000000000000002') {
	        row[i].ruleruid = "适合创业";
	    }
	    if (row[i].ruleruid == '10000000000000000000000000000001'|| row[i].ruleruid == '10000000000000000000000000000003') {
	        row[i].ruleruid = "不适合创业";
	    }
		if (row[i].selectedMap != null && row[i].selectedMap.length != 0) {
			var counts = new Array();
			var index = 0;
			for (var key in row[i].selectedMap) {
				var count = row[i].selectedMap[key];
				counts[index] = count;
				index++;
			}
            row[i].counts = counts;
		}
		
		if (row[i].questionList != null && row[i].questionList.length != 0) {
			var html = "";
			var index = 0;
			<!-- 遍历问题列表 -->
			for (var k in row[i].questionList) {
				index++;
				html = html + "<span style='font-weight:bold;'>" + index + "&nbsp;&nbsp;&nbsp;" + row[i].questionList[k].name + "</span>";
				<!-- 遍历有选项类别的问题选项 -->
				for (var key in row[i].questionList[k].optionsMap) {
					if (key == row[i].gender){
						html = html + "<ul>";
						var count = 0;
						for (var o in row[i].questionList[k].optionsMap[key]) {
							count++;
							html = html + "<li>";
							if (row[i].selectedMap[index] == count) {
								html = html + "&nbsp;&nbsp;<span style='color:red'>" + row[i].questionList[k].optionsMap[key][o].option + "&nbsp;&nbsp;&nbsp;" + row[i].questionList[k].optionsMap[key][o].data + "</span>";
							} else {
								html = html + "&nbsp;&nbsp;<span style='color:gray'>" + row[i].questionList[k].optionsMap[key][o].option + "&nbsp;&nbsp;&nbsp;" + row[i].questionList[k].optionsMap[key][o].data + "</span>";
							}
							html = html + "</li>";
						}
						html = html + "</ul>";
					}
				}
				<!-- 遍历无选项类别的问题选项 -->
				var count = 0;
				html = html + "<ul>";
				for (var o in row[i].questionList[k].optionList) {
					count++;
					html = html + "<li>";
					if (row[i].selectedMap[index] == count) {
						html = html + "&nbsp;&nbsp;<span style='color:red'>" + row[i].questionList[k].optionList[o].option + "&nbsp;&nbsp;&nbsp;" + row[i].questionList[k].optionList[o].data + "</span>";
					} else {
						html = html + "&nbsp;&nbsp;<span style='color:gray'>" + row[i].questionList[k].optionList[o].option + "&nbsp;&nbsp;&nbsp;" + row[i].questionList[k].optionList[o].data + "</span>";
					}
					html = html + "</li>";	
				}
				html = html + "</ul>";
			}
			if (html == "") {
                row[i].html = "暂无";
            } else {
                row[i].html = html;
            }
		}
	}
	for (var i in questionList) {
	    if (questionList[i].options != "" && questionList[i].options != undefined) {
	        var viewOptionsJson = JSON.parse(questionList[i].options);
	        if (viewOptionsJson[''] != undefined && viewOptionsJson[''] != '') {
	        	organized(questionList[i].optionList);
	             viewOptionsHtml +='<br/><br/><br/><br/><span style="display: inline-block;width: 500px;height:400px;vertical-align: bottom;">'+questionList[i].order+'、'+questionList[i].name+'<br/><span id='+ questionList[i].uid +' style="width:500px;height:380px;"></span></span>';
	             viewOptionsHtml += '<span style="display: inline-block;width: 450px;padding-left: 25px;height: 400px;"><table style="width:100%;height:100%;border-collapse:collapse;><tr><td style="border-bottom:1px solid #dedede;"><td>选项</td><td style="border-bottom:1px solid #dedede;">内容</td><td style="border-bottom:1px solid #dedede;">数量</td><td style="border-bottom:1px solid #dedede;">占比</td></tr>';
	             					for(var j in questionList[i].optionList){
		             					var option=questionList[i].optionList;
		             					viewOptionsHtml += '<tr><td style="border-bottom:1px solid #dedede;">'+option[j].option+'</td><td style="border-bottom:1px solid #dedede;">'+option[j].data+'</td><td style="border-bottom:1px solid #dedede;">'+option[j].count+'</td><td style="border-bottom:1px solid #dedede;">'+option[j].percentage+'</td></tr>';
	             					}
	             					viewOptionsHtml += '</table></span>';
	        } else {
	           if (viewOptionsJson['0'] != undefined && viewOptionsJson['0'] != '') {
	           	   organized(questionList[i].optionsMap['0']);
	               viewOptionsHtml += '<br/><br/><br/><br/><span style="display: inline-block;width: 500px;height:400px;vertical-align: bottom;">'+questionList[i].order+'、'+questionList[i].name+'<br/><span id="'+questionList[i].uid+'_0" style="width: 500px;height:380px;"></span></span>';
	               viewOptionsHtml += '<span style="display: inline-block;width: 450px;padding-left: 25px;height: 400px;"><table style="width:100%;height:100%;border-collapse:collapse;><tr><td style="border-bottom:1px solid #dedede;"><td>选项</td><td style="border-bottom:1px solid #dedede;">内容</td><td style="border-bottom:1px solid #dedede;">数量</td><td style="border-bottom:1px solid #dedede;">占比</td></tr>';
	             					for(var j in questionList[i].optionsMap['0']){
		             					var option=questionList[i].optionsMap['0'];
		             					viewOptionsHtml += '<tr><td style="border-bottom:1px solid #dedede;">'+option[j].option+'</td><td style="border-bottom:1px solid #dedede;">'+option[j].data+'</td><td style="border-bottom:1px solid #dedede;">'+option[j].count+'</td><td style="border-bottom:1px solid #dedede;">'+option[j].percentage+'</td></tr>';
	             					}
	             					viewOptionsHtml += '</table></span>';
	           }
	           if (viewOptionsJson['1'] != undefined && viewOptionsJson['1'] != '') {
	           organized(questionList[i].optionsMap['1']);
	               viewOptionsHtml += '<br/><br/><span style="display: inline-block;width: 500px;height:400px;vertical-align: bottom;"><span id="'+questionList[i].uid+'_1" style="width: 500px;height:380px;"></span></span>';
	               viewOptionsHtml += '<span style="display: inline-block;width: 450px;padding-left: 25px;height: 400px;"><table style="width:100%;height:100%;border-collapse:collapse;><tr><td style="border-bottom:1px solid #dedede;"><td>选项</td><td style="border-bottom:1px solid #dedede;">内容</td><td style="border-bottom:1px solid #dedede;">数量</td><td style="border-bottom:1px solid #dedede;">占比</td></tr>';
	             					for(var j in questionList[i].optionsMap['1']){
		             					var option=questionList[i].optionsMap['1'];
		             					viewOptionsHtml += '<tr><td style="border-bottom:1px solid #dedede;">'+option[j].option+'</td><td style="border-bottom:1px solid #dedede;">'+option[j].data+'</td><td style="border-bottom:1px solid #dedede;">'+option[j].count+'</td><td style="border-bottom:1px solid #dedede;">'+option[j].percentage+'</td></tr>';
	             					}
	             					viewOptionsHtml += '</table></span>';
	           }
	        }
	    }
	}
}

function organized(optionList){
	var num=0;
	for(var i =0;i<optionList.length;i++){
		num=num+optionList[i].count;
	}
	for(var i =0;i<optionList.length;i++){
		var percent=Math.round(((optionList[i].count/num).toFixed(2))*100);
		if(optionList[i].count ==0){
			percent=0;
		}
		optionList[i].percentage=percent+"%";
	} 
}

function chart(id, optionList,yname){
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
	                text: ''
	            },
	            legend: {},
	            color: ['#3398DB'],
	            tooltip: {
	            	trigger: 'item',  
	        		formatter: '{b}:\n{c}%'  
	            },
	            grid: {
			        left: '3%',
			        right: '10%',
			        bottom: '3%',
			        containLabel: true
	    		},
	    		calculable: true,
	            yAxis: [
		            {
			            type : 'category',
			            data : xarr,
			            name: yname,
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

function myButtonHandler(obj, url) {
	$("#viewOption").html(viewOptionsHtml);
	var objid = $(obj).attr("id");
		if (objid == "btnanalysis") {
			$("#first").addClass("hd");
			$("#echart").removeClass("hd");
		}
	for (var i in questionList) {
		if (questionList[i].options != "" && questionList[i].options != undefined) {
			var viewOptionsJson = JSON.parse(questionList[i].options);
	        if (viewOptionsJson[''] != undefined && viewOptionsJson[''] != '') {
	            chart(questionList[i].uid, questionList[i].optionList,"");
	        } else {
	           if (viewOptionsJson['0'] != undefined && viewOptionsJson['0'] != '') {
		           chart(questionList[i].uid+"_0", questionList[i].optionsMap['0'],"男生选项");
	           }
	           if (viewOptionsJson['1'] != undefined && viewOptionsJson['1'] != '') {
		           chart(questionList[i].uid+"_1", questionList[i].optionsMap['1'],"女生选项");
	           }
			}
			
		}
	}
}

</script>
</head>
<body>
	<section id="first">
		<table>
			<tr>
                <td>测试总数:</td>
                <td><span>${total}</span>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                [#if countMap??]
					[#list countMap?keys as key]
	                <td>${key}:</td>
	                <td><span>${countMap[key]}</span>&nbsp;&nbsp;&nbsp;&nbsp;</td>
	                [/#list]
	            [/#if]
			<tr>
		</table>
		[@a.search]
	        <tr>
	        	<td colspan="10">
		        	<label for="name">姓名: </label><input id="name" type="text" name="name" class="search"/>&nbsp;&nbsp;&nbsp;
	        	</td>
	        </tr>
	        <tr>
	            <td>起始操作时间: <input name="createtimestart" type="text" editable="false" class="easyui-datebox" /></td>
                <td>结束操作时间: <input name="createtimeend" type="text" editable="false" class="easyui-datebox" /></td>
	        </tr>
	        <script type="text/javascript">
				<!--
					$("#slttype2").val("${channelfrom}");
				//-->
			</script>
		[/@a.search]
		
		[@a.datagrid]
            <th data-options="field:'name',width:100">昵称</th>
            <th data-options="field:'phone',width:100">手机号</th>
            <th data-options="field:'email',width:100">邮箱</th>
            <th data-options="field:'ruleruid',width:100">结果</th>
            <th data-options="field:'createtimeStr',width:200">操作时间</th>
            <th data-options="field:'loginip',width:200">登陆IP</th>
	    [/@a.datagrid]
	    
	    [@a.toolbar canadd=false canedit=false ]
	    <a id="btnanalysis" href="" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="myButtonHandler(this, '')">统计详情</a>
	    [/@a.toolbar]
	    [@a.tools /]
	    
	</section>
 
 	[@a.viewsection]
		<tr>
            <td>姓名:</td>
            <td><span set-key="name"></span></td>
        </tr>
		<tr>
            <td>手机:</td>
            <td><span set-key="phone"></span></td>
        </tr>      
        <tr>
            <td>邮箱:</td>
            <td><span set-key="email"></span></td>
        </tr>
        <tr>
            <td>意见反馈:</td>
            <td><span set-key="advice"></span></td>
        </tr>
        <tr>
            <td>操作时间:</td>
            <td><span set-key="createtimeStr"></span></td>
        </tr>
        <tr>
            <td>登陆IP:</td>
            <td><span set-key="loginip"></span></td>
        </tr>
        <tr>
            <td>测试结果:</td>
            <td><span set-key="score"></span>&nbsp;&nbsp;&nbsp;&nbsp;<span set-key="rejectcount"></span>&nbsp;&nbsp;&nbsp;&nbsp;<span set-key="contont"></span></td>
        </tr>
        <tr>
            <td>用户选择结果:</td>
            <td><span set-key="html"></span></td>
        </tr>
	[/@a.viewsection]
	
	[@a.viewsection  id="echart"]
            	<span>测试总数:${total}</span>&nbsp;&nbsp;&nbsp;&nbsp;
            [#if countMap??]
				[#list countMap?keys as key]
                <span>${key}:${countMap[key]}</span>&nbsp;&nbsp;&nbsp;&nbsp;
                [/#list]
            [/#if]
        <tr><td style="color:black"><span id="viewOption"></span></td></tr>
    [/@a.viewsection]
	
</body>
</html>
