[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Admin.ftl" as a]
<html>
<head>
<title>申请学员管理</title>
<script type="text/javascript">
	$(function() {
		$("#btndisable, #btnenable").click(function() {
			if ($(this).attr("id") == "btndisable") {
				$("#setsemester").hide();
			} else {
				$("#setsemester").show();
			}
			return;
		});
	});
	
	function myStatusAfterHandler(event, j){
		setSemester();
	}
	
	//设置学期
	function setSemester() {
		var myDate = new Date();
		var month =  myDate.getMonth() + 1; 
		var year = myDate.getFullYear().toString().substr(2, 2);
		$("#year" + year).attr("selected", "selected");
		$("#month" + month).attr("selected", "selected");
	}
	
	function initDGData(row) {
		for(var i in row) {
			if(row[i].status == 10) {
				row[i].managerStatusName = "待处理";
			} else {
				row[i].managerStatusName = "已拒绝";
			}
		}
	}
	function mytoolbarStateHandler(event, j) {
		var list = getSelections(j.dg);
		if (list.length > 0) {
			var row = list[0];
			if (row.status == 10) {// 待处理
				getBtnDisable().linkbutton("enable");
			} else if (row.status == 0) {//已拒绝
				getBtnEnable().linkbutton("disable");
			}
		}
	}
</script>
</head>
<body>
	<section>
		<table>
			<tr>
				<td>学员申请总数：</td>
                <td><span>${apptotal}</span></td>
                <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td>通过审批数：</td>
                <td><span>${passtotal}</span></td>
                <td>未通过审批数：</td>
                <td><span>${nopasstotal}</span></td>
                <td>通过率：</td>
                <td><span>${((passtotal / apptotal)?default(0.00)) * 100}%</span></td>
			<tr>
		</table>
		[@a.search]
			<tr>
				<td><label for="skeywords">关键字:</label></td>
                <td><input id="skeywords" type="text" name="keywords" style="width:150px;"/>&nbsp;&nbsp;&nbsp;&nbsp;</td> 
                <td><label for="smajor">专业: </label></td>
                <td><select id="smajor" name="major" data-options="panelHeight:200">
                    <option class="easyui-combobox" value="">全部</option>
                        <option value="">全部</option>
                        [#list majormap?keys as mkey]
                            <option value="${mkey}">${majormap[mkey]}</option>
                        [/#list]
                	</select>
                </td>
                <td><label for="smajor">状态: </label></td>
                <td><select id="sstatus" name="status" data-options="panelHeight:200">
                    <option class="easyui-combobox" value="">全部</option>
                        <option value="">全部</option>
                        <option value="10">待处理</option>
                        <option value="0">已拒绝</option>
                	</select>
                </td>
			</tr>
			<tr >
				<td><label for="screatetime">申请时间：</label></td>
                <td colspan="3">
	                <input id="screatetime" type="text" name="createtimestart" editable="false" class="easyui-datebox"/>
	                --
	                <input id="screatetime" type="text" name="createtimeend" editable="false" class="easyui-datebox"/>
                </td>
			</tr>
		[/@a.search]
		
		[@a.datagrid]
            <th data-options="field:'userName',width:100">申请人</th>
            <th data-options="field:'usersn',width:100">会员号</th>
            <th data-options="field:'majorName',width:100">申请专业</th>
            <th data-options="field:'semester',width:100">学期</th>
            <th data-options="field:'genderName'">性别</th>
            <th data-options="field:'email',width:100">邮箱</th>
            <th data-options="field:'managerStatusName'">状态</th>
            <th data-options="field:'createtimeStr',width:100">申请时间</th>
        [/@a.datagrid]   
        [@a.toolbar canadd=false canedit=false candel=false]
        	[@a.status namedisable="拒绝" nameenable="同意"]
        		<tr id="setsemester" style="display:none;">
        			<td><label for="year">设置学期</label></td>
                	<td>20
                		<select id="year" name="year" style="width:50px;">
                			[#list 16..50 as i]
                				<option id="year${i}" value="20${i}">${i}</option>
                			[/#list]
                		</select>
                		年，第
                		<select id="month" name="month" style="width:50px;">
                			[#list 1..12 as i]
                				<option id="month${i}" value="${i}">${i}</option>
                			[/#list]
                		</select>
                		期
                	</td>
        		</tr>
        	[/@a.status]
        [/@a.toolbar]
        [@a.tools /]
	</section>
	    [@a.viewsection]
	    <tr>
            <td>申请专业:</td>
            <td><span set-key="majorName"></span></td>
        </tr>
        <tr>
            <td>姓名:</td>
            <td><span set-key="userName"></span></td>
        </tr>
        <tr>
            <td>英文名:</td>
            <td><span set-key="nickname"></span></td>
        </tr>
        <tr>
            <td>性别:</td>
            <td><span set-key="genderName"></span></td>
        </tr>
        <tr>
            <td>会员号:</td>
            <td><span set-key="usersn"></span></td>
        </tr>
        <tr>
            <td>注册邮箱:</td>
            <td><span set-key="email"></span></td>
        </tr>
        <tr>
            <td>身份证号:</td>
            <td><span set-key="identityid"></span></td>
        </tr>
        <tr>
            <td>供职单位:</td>
            <td><span set-key="company"></span></td>
        </tr>
        <tr>
            <td>所属部门:</td>
            <td><span set-key="department"></span></td>
        </tr>
        <tr>
            <td>担任职务:</td>
            <td><span set-key="job"></span></td>
        </tr>
        <tr>
            <td>个人简介:</td>
            <td><span set-key="summary"></span></td>
        </tr>
        <tr>
            <td>禁止联系时间:</td>
            <td>工作日：<span set-key="workdaytime"></span></td>
            <td>周末：<span set-key="saturdaytime"></span></td>
        </tr>
        <tr>
           <td>状态：</td>
           <td>
                <span set-key="managerStatusName"></span>
           </td>
        </tr>
        <tr>
            <td>申请时间:</td>
            <td><span set-key="createtimeStr"></span></td>
        </tr>
    [/@a.viewsection]
</body>
</html>