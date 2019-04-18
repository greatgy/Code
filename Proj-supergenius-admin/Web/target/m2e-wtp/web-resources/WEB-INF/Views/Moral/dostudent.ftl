[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Admin.ftl" as a]
<html>
<head>
<title>学员管理</title>
<script type="text/javascript" src="${basejs}/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
	<!--
	function getFreshRowUrls(dg) {
		var row = getSelected(dg);
		var urls = new Array();
		urls.push("${base}${baseAdminPath}/moral/ajax/scoredetail/list?id={0}".format(row.useruid));
		return urls;
	}
	
	function freshRow(row, data, i) {
		row.detail = data.rows;
	}

	function initFormData(form, data) {
		form.form('load', data);
		form.set(data);
		if (data.detail) {
			$("#dgdetail").datagrid("loadData", data.detail);
		}
	}
	
	 function audit(dg){
        var rows = getSelections(dg)
        if(rows.length == 0) {
           alert("请选择要审核的数据");
           return;
        }
        if(rows[0].status != 1) {
            alert("此状态不可审核");
            return;
        }
        if(rows[0].score < 90) {
			alert("分数不合格，不予颁发证书");
			return;
		}
		if(rows[0].certificate != null) {
			alert("已颁发证书");
			return;
		}
        initFormData($("#auditexam"), rows[0]);
        $('#auditexam').window('open');
    }
    
    function subauditform(){
         var rows = getSelections("#dg")
         $('#auditform').form({    
            onSubmit: function(){
                var result = $("input[name='result']:checked").val();
                if(typeof(result) == "undefined"){
                    alert("请选择审核结果");
                    return false;
                }
            },    
            success:function(data){
                data = JSON.parse(data);
                if(data.success == true) {
                	closeaudit();
                    $("#dg").datagrid("reload");
                    alert("颁发成功");
                }else {
                    alert("颁发失败");
                }
            },
         });
        $('#auditform').submit();
    }
    
    /*关闭颁发证书弹出框*/
    function closeaudit(){
        $("#auditexam").window('close');
        $("#auditform").form("clear");
    }
    
	-->
</script>
</head>
<body>
    <section>       
        [@a.search]
            <tr>
                <td><label for="sname">名称：</label></td>
                <td><input id="sname" type="text" name="name" /></td>
                <td><label for="ssn">学号：</label></td>
                <td><input id="ssn" type="text" name="sn" /></td>
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
            <th data-options="field:'name',width:500">名称</th>
            <th data-options="field:'sn',width:200">学号</th>
            <th data-options="field:'score',width:200">成绩</th>
            <th data-options="field:'createtimeStr',width:200">成为学员时间</th>
            <th data-options="field:'statusName',width:200">状态</th>
        [/@a.datagrid]
        
        [@a.toolbar canadd=false nameedit="修改积分"]
            [@a.status /]
        	 <a id="btn" href="" onclick="audit('#dg')" class="easyui-linkbutton" data-options="">颁发证书</a>
        [/@a.toolbar]
        [@a.tools/]
    </section>
    
    <div id="auditexam" class="easyui-window" title="审核对话框" 
        data-options="iconCls:'icon-save',modal:true,minimizable:false,maximizable:false,closed:true,collapsible:false" style="width:800px;height:510px;padding:10px;">
        <form id="auditform" method="post" action="${base}${baseAdminPath}/moral/ajax/student/certificate">
            <table>
                <tr>
                    <td><input type="hidden" name="uid" value=""></td>
                </tr>
                <tr>
		            <td>姓名：</td>
		            <td><span set-key="name"></span></td>
		        </tr>
		        <tr>
		            <td>学号：</td>
		            <td><span set-key="sn"></span></td>
		        </tr>
		        <tr>
		            <td>成绩：</td>
		            <td><span set-key="score"></span></td>
		        </tr>
		        <tr>
		            <td>联系电话：</td>
		            <td><span set-key="mobile"></span></td>
		        </tr>
		        <tr>
		            <td>联系邮箱：</td>
		            <td><span set-key="email"></span></td>
		        </tr>
                <tr>
		            <td>上传证书：</td>
		            <td>[@a.imgupload multiple=false imgpath="/imgs/webdata/moral/student" /]</td>
		        </tr>
		        
		        <tr>
                    <td><input type="radio" name="result" value="1" checked="checked" id="radioc1"/><label for="radioc1"> 颁发证书</lable></td>
                    <td><input type="radio" name="result" value="0" id="radioc2"/> <label for="radioc2">不予颁发证书</lable></td>
                </tr>
                
                <tr>
                    <td>
                        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="subauditform()">确定</a>
                    </td>
                    <td>
                        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="closeaudit()">取消</a>                    
                    </td>
                </tr>
            </table>
        </form>    
    </div>
	    
	    [@a.editsection]
	    	<tr>
	    		<td>当前积分：</td>
	    		<td><span set-key="userStatistics.score"></span></td>
	    		<td><input class="easyui-validatebox" type="hidden" name="useruid" /></td>
	    	</tr>
	    	<tr>
	    		<td>修改后积分：</td>
	    		<td><input class="easyui-validatebox" type="text" name="finishscore" data-options="required:true" /></td>
	    	<tr>
	    	<tr>
	    		<td>修改理由：
	    		<td>
	    			<textarea class="easyui-validatebox" style="width:350px; height:60px;" set-value="" name="desc" data-options="required:true" title=""></textarea>
	    		</td>
	    	</tr>
	    	<tr>
	    		<td colspan="2" style="padding-top:0;">
					[@a.datagrid id="dgdetail" title="修改历史" options="width:800,fitColumns:true,resizeHandle:'right',singleSelect:true,method:'get'"]
						<th data-options="field:'useruid'">管理员</th>
						<th data-options="field:'beginscore'">变更前</th>
						<th data-options="field:'finishscore'">变更后</th>
						<th data-options="field:'desc'">理由</th>
						<th data-options="field:'createtimeStr'">时间</th>
					[/@a.datagrid]
	    		</td>
	    	</tr>
	    [/@a.editsection]
	    [@a.viewsection]
	        <tr>
	            <td>姓名：<input type="hidden" name="uid" /></td>
	            <td><span set-key="name"></span></td>
	        </tr>
	        <tr>
	            <td>学号：</td>
	            <td><span set-key="sn"></span></td>
	        </tr>
	        <tr>
	           <td>头衔：</td>
	           <td><span set-key="level"></span></td>
	        </tr>
	        <tr>
	           <td>成绩：</td>
	           <td><span set-key="score"></span></td>
	        </tr>
	        <tr>
	           <td>证书：</td>
	           <td><img style="width:200px; height:200px" src="" set-format="${webimg}{0}" set-key="certificate.img"/></td>
	        </tr>
	        <tr>
	           <td>证书编号：</td>
	           <td><span set-key="certificate.sn"></span></td>
	        </tr>
	        <tr>
	           <td>获得证书时间：</td>
	           <td><span set-key="certificate.createtime"></span></td>
	        </tr>
	    [/@a.viewsection]
    </div>
</body>
