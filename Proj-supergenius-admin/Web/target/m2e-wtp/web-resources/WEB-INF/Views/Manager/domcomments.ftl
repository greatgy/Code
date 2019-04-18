[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Admin.ftl" as a]
<html>
<head>
<title>评论管理</title>
<script type="text/javascript">
	function initDGData(row) {
		for (var i in row) {
			row[i].content = replaceContent(row[i].content);
			if (row[i].type == 0) {
				row[i].typename = "评论";
			} else if (row[i].type == 1) {
			    row[i].typename = "赞";
			} else if (row[i].type == 2) {
				row[i].typename = "投票";
			}
			if (row[i].status == 0) {
				row[i].statusName = "冻结";
			} else if (row[i].status == 1) {
			    row[i].statusName = "正常";
			}
			if (row[i].channel == 27) {
				row[i].channelName = "视频";
			} else if (row[i].channel == 140) {
			    row[i].channelName = "挑战";
			}
		}
	}
	
	function mytoolbarStateHandler(event, j) {
		var list = getSelections(j.dg);
		if (list.length > 0) {
			var row = list[0];
			if (row.status == 0 && row.touid != undefined) {//二级评论，这里需要去请求一下后台，判断此评论的一级评论是否冻结，如果冻结，则不能解冻
				var url = "${base}${baseAdminPath}/${site}/ajax/${channel}/canenable";
				$.get(url, {uid : row.uid}, function(result) {
					if (!result.success) 
						getBtnEnable().linkbutton("disable");
				});
				
			}
		}
	}
	
	//将[em_*]替换为img标签,显示表情
	function replaceContent(str) {
		str = str.replace(/\[em_([0-9]*)\]/g,'<img src="${baseimg}/imgs/face/qq/$1.gif" border="0" />');
		return str;
	}
</script>
</head>
<body>
	<section>
		<table>
			<tr>
				<td>总评论数：</td>
                <td><span>${commentcount}</span></td>
                <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td>总评论人数：</td>
                <td><span>${count}</span></td>
                <td>总点赞数：</td>
                <td><span>${praisecount}</span></td>
                <td>总点赞人数：</td>
                <td><span>${count2}</span></td>
                <td>总参与人数：</td>
                <td><span>${total}</span></td>
			<tr>
		</table>
		[@a.search]
		     <tr>
		     	<td><label for="skeywords">关键字:</label></td>
                <td><input id="skeywords" type="text" name="keywords" style="width:150px;"/>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td><label for="sstatus">状态:</label></td>
                <td><select id="sstatus" name="status" data-options="panelHeight:200">
                    <option class="easyui-combobox" value="">全部</option>
                        <option value="">全部</option>
                        <option value="0">冻结</option>
                        <option value="1">正常</option>
                	</select>
                </td>
                <td><label for="schannel">评论类型:</label></td>
                <td><select id="schannel" name="channel" data-options="panelHeight:200">
                    <option class="easyui-combobox" value="">全部</option>
                        <option value="">全部</option>
                        <option value="27">视频</option>
                        <option value="140">挑战</option>
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
			<th data-options="field:'fromUserName'">评论人</th>
            <th data-options="field:'channelName'">评论类型</th>
            <th data-options="field:'content',width:100">内容</th>
            <th data-options="field:'fromName'">评论对象</th>
            <th data-options="field:'statusName'">状态</th>
            <th data-options="field:'createtimeStr'">发布时间</th>
		[/@a.datagrid]
		[@a.toolbar canadd=false canedit=false]
			[@a.status /]
        [/@a.toolbar]
        [@a.tools /]
      </section>
       [@a.viewsection]
	    <tr>
            <td>评论人:</td>
            <td><span set-key="fromUserName"></span></td>
        </tr>
        <tr>
            <td>评论类型:</td>
            <td><span set-key="channelName"></span></td>
        </tr>
        <tr>
            <td>评论对象:</td>
            <td><span set-key="fromName"></span></td>
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
            <td>发布时间:</td>
            <td><span set-key="createtimeStr"></span></td>
        </tr>
    [/@a.viewsection]
</body>
<html>
		
