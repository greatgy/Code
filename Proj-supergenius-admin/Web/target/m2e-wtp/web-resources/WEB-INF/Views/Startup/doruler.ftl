[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Admin.ftl" as a]
<html>
<head>
<title>统计信息</title>
<script type="text/javascript" src="${basejs}/ckeditor/ckeditor.js"></script>
<script type="text/javascript">

function initDGData(row) {
	for (var i in row) {
	    if (row[i].type == "0") {
	    	row[i].typeValue = row[i].type;
	        row[i].typeName = "创业测试";
	    } else {
	    	row[i].typeValue = row[i].type;
	        row[i].typeName = "其他测试";
	    }
	    
	    if (row[i].rejectmincount != null && row[i].rejectmaxcount != null) {
	    	row[i].rejectcountsection = row[i].rejectmincount + "~" + row[i].rejectmaxcount;
	    }
	    if (row[i].minscore != null && row[i].maxscore != null) {
	    	row[i].scoresection = row[i].minscore + "~" + row[i].maxscore;
	    }
	}
}

$(function() {
	$("#btnedit").click(function() {
    	//修改不能确定规则的显示内容
    	var row = getSelected(dg);
	    if (row) {
	    	if (row.uid == "10000000000000000000000000000000") {
	    		$(".showcontent").siblings().addClass("hd");
	   		} else {
	   			$(".showcontent").siblings().removeClass("hd");
	   		}
	    } else {
	        err("请选择一项！");
	    }
    });
})

function mytoolbarStateHandler(event, j) {
	var list = getSelections(j.dg);
	if (list.length == 0) {
		getBtnDisable().linkbutton("disable");
		getBtnEnable().linkbutton("disable");
		getBtnEdit().linkbutton("disable");
		getBtnDel().linkbutton("disable");
		getBtnView().linkbutton("disable");
	} else {
		var row = list[0];
		if (row.isinit == 0) {// 初始化规则
			getBtnDel().linkbutton("disable");
		}
	}
}
</script>
</head>
<body>
	<section>
		[@a.search]
	        <tr>
	        	<td colspan="10">
		        	名称: <input id="name" type="text" name="name" />
	        	</td>
	        </tr>
	        <tr>
	            <td>类型:
		            <input type="radio" name="type" value="0" data-options="required:true" id="entrepreneurshiptest" checked/><label for="entrepreneurshiptest">创业测试 </label>
		            <input type="radio" name="type" value="10" data-options="required:true" id="other"/><label for="other">其他测试 </label>
		        </td>
	        </tr>
	        <tr>
	            <td>最小一票否决数量: <input id="rejectmincount" type="text" name="rejectmincount" /></td>
                <td>最大一票否决数量: <input id="rejectmaxcount" type="text" name="rejectmaxcount" /></td>
	        </tr>
	        <tr>
	            <td>最小得分: <input id="minscore" type="text" name="minscore" /></td>
                <td>最大得分: <input id="maxscore" type="text" name="maxscore" /></td>
	        </tr>
	        <tr>
	            <td>起始操作时间: <input name="createtimestart" type="text" editable="false" class="easyui-datebox" /></td>
                <td>结束操作时间: <input name="createtimeend" type="text" editable="false" class="easyui-datebox" /></td>
	        </tr>
		[/@a.search]
		
		[@a.datagrid]
            <th data-options="field:'name',width:75">名称</th>
            <th data-options="field:'typeName',width:75">类型</th>
            <th data-options="field:'rejectcountsection',width:50">一票否决数量区间</th>
            <th data-options="field:'scoresection',width:50">得分区间</th>
            <th data-options="field:'content',width:200">反馈内容</th>
            <th data-options="field:'statusName',width:50">状态</th>
            <th data-options="field:'createtimeStr',width:100">创建时间</th>
            <th data-options="field:'adminname',width:75">操作人</th>
	    [/@a.datagrid]
	    
	    [@a.toolbar]
	    [/@a.toolbar]
	    [@a.tools /]
	    
	</section>
 
 	[@a.addsection ]
	    <tr>
            <td>名称:<input type="hidden" name="uid"/></td>
            <td><input class="easyui-validatebox" type="text" name="name" data-options="required:true" ></input></td>
        </tr>
        <tr>
            <td>类型:</td>
            <td>
	            <input type="radio" name="typeValue" value="0" data-options="required:true" id="entrepreneurshiptest" checked/><label for="entrepreneurshiptest">创业测试 </label>
	            <input type="radio" name="typeValue" value="10" data-options="required:true" id="other"/><label for="other">其他测试 </label>
            </td>
        </tr>
        <tr>
            <td>一票否决数量区间:</td>
            <td>
	            <input class="easyui-numberbox" type="text" name="rejectmincount" data-options="min:0, required:true" placeholder="请输入大于0的数字" id="rejectmincount" style="width:140px"></input>
	            ~
	            <input class="easyui-numberbox" type="text" name="rejectmaxcount" data-options="min:0, required:true" placeholder="请输入大于0的数字" id="rejectmaxcount" style="width:140px"></input>
	        </td>
        </tr>
        <tr>
            <td>得分区间:</td>
            <td>
	            <input class="easyui-numberbox" type="text" name="minscore" data-options="min:0, required:true" placeholder="请输入大于0的数字" id="minscore" style="width:140px"></input>
	            ~
	            <input class="easyui-numberbox" type="text" name="maxscore" data-options="min:0, required:true" placeholder="请输入大于0的数字" id="maxscore" style="width:140px"></input>
	        </td>
        </tr>
        <tr>
            <td>反馈内容:</td>
            <td><textarea class="easyui-validatebox long" type="textarea" name="content" data-options="required:true" rows="5" cols="83"></textarea></td>
        </tr>
    [/@a.addsection]
        
 	[@a.editsection]
		<tr>
            <td>名称:<input type="hidden" name="uid"/></td>
            <td><input class="easyui-validatebox" type="text" name="name" data-options="required:true"></input></td>
        </tr>
        <tr>
            <td>类型:</td>
            <td>
	            <input type="radio" name="typeValue" set-key="entrepreneurshiptest" value="0" id="entrepreneurshiptest"/><label for="entrepreneurshiptest">创业测试 </label>
	            <input type="radio" name="typeValue" set-key="entrepreneurshiptest" value="10" id="other"/><label for="other">其他测试 </label>
            </td>
        </tr>
        <tr>
            <td>一票否决数量区间:</td>
            <td>
	            <input class="easyui-numberbox" type="text" name="rejectmincount" data-options="min:0, required:true" placeholder="请输入大于0的数字" style="width:140px"></input>
	            ~
	            <input class="easyui-numberbox" type="text" name="rejectmaxcount" data-options="min:0, required:true" placeholder="请输入大于0的数字" style="width:140px"></input>
	        </td>
        </tr>
        <tr>
            <td>得分区间:</td>
            <td>
	            <input class="easyui-numberbox" type="text" name="minscore" data-options="min:0, required:true" placeholder="请输入大于0的数字" style="width:140px"></input>
	            ~
	            <input class="easyui-numberbox" type="text" name="maxscore" data-options="min:0, required:true" placeholder="请输入大于0的数字" style="width:140px"></input>
            </td>
        </tr>
        <tr class="showcontent">
            <td>反馈内容:</td>
            <td>
	           <textarea type="text" name="content" data-options="required:true" rows="5" cols="83"></textarea>
            </td>
        </tr>
	[/@a.editsection]
	
 	[@a.viewsection]
		<tr>
            <td>名称:</td>
            <td><span set-key="name"></span></td>
        </tr>
		<tr>
            <td>类型:</td>
            <td><span set-key="typeName"></span></td>
        </tr>
		<tr>
            <td>一票否决数量区间:</td>
            <td><span set-key="rejectcountsection"></span></td>
        </tr>
		<tr>
            <td>得分区间:</td>
            <td><span set-key="scoresection"></span></td>
        </tr>
		<tr>
            <td>内容:</td>
            <td><span set-key="content"></span></td>
        </tr>
		<tr>
            <td>状态:</td>
            <td><span set-key="statusName"></span></td>
        </tr>
		<tr>
            <td>创建时间:</td>
            <td><span set-key="createtimeStr"></span></td>
        </tr>
		<tr>
            <td>操作人:</td>
            <td><span set-key="adminName"></span></td>
        </tr>
	[/@a.viewsection]
</body>
</html>
