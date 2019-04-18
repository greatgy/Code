[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Admin.ftl" as a]
<html>
<head>
<title>学员管理</title>
<script type="text/javascript">
	function myStatusAfterHandler(event, j){
		var level = getSelected(dg).levelto;
		$("#changelevel option[value='" + level + "']").attr("selected", "selected");
	}
	function initDGData(row) {
		for(var i in row) {
			if(row[i].channelname == "申请学员") {
				row[i].channelname = "正常";
			}
		}
	}
</script>
</head>
<body>
	<section>
		<table>
			<tr>
				<td>学员总数：</td>
                <td><span>${total}</span></td>
                <td>(特批${special}/特邀${invite})</td>
                [#list student?keys as item]
                <td>${item}:</td>
                <td><span>${student[item]}</span></td>
                [/#list]
			<tr>
		</table>
		[@a.search]
			<tr>
				<td colspan="9"><label for="skeywords">关键字:</label>&nbsp;&nbsp;&nbsp;&nbsp;
                <input id="skeywords" type="text" name="keywords" style="width:150px;"/></td> 
            </tr>
            <tr>
                <td><label for="schannel">类型: </label></td>
                <td><select id="schannel" name="channel" data-options="panelHeight:200">
                    <option class="easyui-combobox" value="">全部</option>
                        <option value="">全部</option>
                        <option value="appStudent">正常</option>
                        <option value="specialStudent">特批</option>
                        <option value="inviteStudent">特邀</option>
                	</select>
                </td>
                <td><label for="smajor">专业: </label></td>
                <td><select id="smajor" name="major" data-options="panelHeight:200">
                    <option class="easyui-combobox" value="">全部</option>
                        <option value="">全部</option>
                        [#list majormap?keys as mkey]
                            <option value="${mkey}">${majormap[mkey]}</option>
                        [/#list]
                	</select>
                </td>
                <td><label for="slevel">级别: </label></td>
                <td><select id="slevel" name="level" data-options="panelHeight:200">
                    <option class="easyui-combobox" value="">全部</option>
                        <option value="">全部</option>
                        [#list studentlevelmap?keys as mkey]
                            <option value="${mkey}">${studentlevelmap[mkey]}</option>
                        [/#list]
                	</select>
                </td>
                <td><label for="scertificate">证书: </label></td>
                <td><select id="scertificate" name="certificate" data-options="panelHeight:200">
                    <option class="easyui-combobox" value="">全部</option>
                        <option value="">全部</option>
                        [#list levelmap?keys as mkey]
                        	<option value="${mkey}">${levelmap[mkey]}</option>
                        [/#list]
                	</select>
                </td>
			</tr>
		[/@a.search]
		
		[@a.datagrid]
            <th data-options="field:'studentsn',width:100">学号</th>
            <th data-options="field:'username',width:100">姓名</th>
            <th data-options="field:'channelname',width:80">类型</th>
            <th data-options="field:'majorname',width:100">专业</th>
            <th data-options="field:'level',width:100">级别</th>
            <th data-options="field:'certificatename',width:50">当前证书</th>
        [/@a.datagrid]   
        [@a.toolbar canadd=false canedit=false candel=false]
        	[@a.status namedisable="更改级别" urldisable="${base}${baseAdminPath}/${site}/ajax/${channel}/setlevel" canenable=false style="width:550px; height:300px; padding:10px 20px;"]
        		<tr>
		            <td>学员名</td>
		            <td><span set-key="username" ></span></td>
		        </tr>
        		<tr>
		            <td>专业</td>
		            <td><span set-key="majorname" ></span></td>
		        </tr>
        		<tr>
		            <td>当前级别</td>
		            <td><span set-key="level" ></span></td>
		        </tr>
        		<tr>
		            <td>更改级别</td>
		            <td><select id="changelevel" name="level" data-options="panelHeight:200">
		                    [#list studentlevelmap?keys as mkey]
		                        <option value="${mkey}">${studentlevelmap[mkey]}</option>
		                    [/#list]
		            	</select>
                	</td>
		        </tr>
        	[/@a.status]
        [/@a.toolbar]
        [@a.tools /]
	</section>
	    [@a.viewsection]
	    <tr>
            <td>会员号:</td>
            <td><span set-key="usersn"></span></td>
        </tr>
        <tr>
            <td>学员名:</td>
            <td><span set-key="username"></span></td>
        </tr>
        <tr>
            <td>类型:</td>
            <td><span set-key="channelname"></span></td>
        </tr>
        <tr>
            <td>专业:</td>
            <td><span set-key="majorname"></span></td>
        </tr>
        <tr>
            <td>级别:</td>
            <td><span set-key="level"></span></td>
        </tr>
        <tr>
            <td>已获学位:</td>
            <td><span set-key="certificatename"></span>
            	<img src="" set-format="${webimg}{0}" set-key="certificatesrc"/>
            </td>
        </tr>
        <tr>
            <td>入学时间:</td>
            <td><span set-key="createtimeStr"></span></td>
        </tr>
        <tr>
            <td>最后登录时间:</td>
            <td><span set-key="lastlogintime"></span></td>
        </tr>
        <tr>
            <td>最后登录IP:</td>
            <td><span set-key="lastloginip"></span></td>
        </tr>
    [/@a.viewsection]
</body>
</html>