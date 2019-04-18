[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Admin.ftl" as a]
<html>
<head>
<title>证书管理</title>
<script type="text/javascript">
<!--
	$(function() {
		$("#btnsetcertificate").click(function() {
				$("#frameImg0").contents().find("#formImg0").css("display","block");
		});
		getBtnView().click(function() {
			if (getSelected(dg).imglittle != undefined) {
				 $("#viewcertificatea").css("display", "inline");
				 $("#viewcertificatea").attr("href", "${userimg}" + getSelected(dg).imgoriginal);
			} else {
				$("#viewcertificatea").css("display", "none");
			}
		});
	});
    function initDGData(row) {
        for(var i in row) {
            if(row[i].img == null || row[i].img == '') {
                row[i].statusName = "未颁发";
            }
            if(row[i].status == 0) {
                row[i].statusName = "已冻结";
            }
            if(row[i].status == 1 && row[i].img != '' && row[i].img != null) {
                row[i].statusName = "已颁发";
            }
            if(row[i].user != null) {
                row[i].userSn = row[i].user.usersn;
                row[i].showName = row[i].user.name;
            }
        }
    }
    function mytoolbarStateHandler(event, j) {
        var row = getSelected(j.dg); 
        if (row) {
            if (row.img == '' || row.img == null && row.status != '0') {
                $("#btnsetcertificate").linkbutton("enable");
            } else {
                $("#btnsetcertificate").linkbutton("disable");
            }
        } else {
            $("#btnsetcertificate").linkbutton("disable");
        }
    } 
    
    function showImg(imglittle) {
        if( imglittle != null ){ 
  	        return '<img height="20" width="20" src="${userimg}/'+imglittle+'" />';
        } else {
  	        return '';
        }
    }
//-->
</script>
</head>
<body>
    <section>
        [@a.search]
            <tr>
                <td><label>搜索框:</label></td>
                <td><input type="text" name="keyword"/></td>
                <td><label>专业：</label></td>
                <td>
                    <select name="major">
                        <option value="">全部</option>
                            [#list majors?keys as item]
                                <option value="${item}">${majors[item]}</option>
                            [/#list]
                    </select>
                </td>
                <td><label>类型：</label></td>
                <td><select name="type">
                    <option value="">全部</option>
                        [#list types?keys as item]
                            <option value="${item}">${types[item]}</option>
                        [/#list]
                    </select>
                </td>
                <td><label>状态：</label></td>
                <td>
                    <select name="status">
                        <option value="">全部</option>
                        <option value="13">未颁发</option>
                        <option value="1">已颁发</option>
                        <option value="0">已冻结</option>
                    </select>
                </td>
            </tr>
        [/@a.search]
        [@a.datagrid]
            <th data-options="field:'certificatename'">证书名称</th>
            <th data-options="field:'typeName'">类型</th>
            <th data-options="field:'showName'">证书获得人姓名</th>
            <th data-options="field:'userSn'">学员号</th>
            <th data-options="field:'majorName'">专业</th>
            <th data-options="field:'statusName'">状态</th>
            <th data-options="field:'updatetimeStr'">颁发时间</th>
            <th data-options="field:'imglittle', width:160, formatter:showImg">扫描图</th>
        [/@a.datagrid]
        [@a.toolbar canadd=false canedit=false candel=false]
        <a id="btnsetcertificate" href="" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" data-crumbshow="{selector:'#setcertificate', title:'颁发证书'}" onclick="editHandler(this, null, '#setcertificate')">颁发证书</a>
        [@a.status /]
        [/@a.toolbar]
        [@a.tools /]
    </section>
    [@a.viewsection]
        <tr><td>证书名称:</td><td><span set-key="certificatename"></span></td></tr>
        <tr><td>类型:</td><td><span set-key="typeName"></span></td></tr>
        <tr><td>证书获得人姓名:</td><td><span set-key="showName"></span></td></tr>
        <tr><td>会员号:</td><td><span set-key="userSn"></span></td></tr>
        <tr><td>学员号:</td><td><span set-key="studentsn"></span></td></tr>
        <tr><td>专业:</td><td><span set-key="majorName"></span></td></tr>
        <tr><td>状态:</td><td><span set-key="statusName"></span></td></tr>
        <tr><td>获得时间:</td><td><span set-key="createtimeStr"></span></td></tr>
        <tr><td>颁发时间：</td><td><span set-key="updatetimeStr"></span></td></tr>
        <tr><td>扫描图：</td><td><a id="viewcertificatea" href="" target="_blank"><img style="width:150px; height:150px" src="" set-format="${userimg}{0}"  set-key="imglittle" title="点击查看原图"/></a></td></tr>
    [/@a.viewsection]
    [@a.editsection id="setcertificate" namesubmit="颁发证书" action="${base}${baseAdminPath}/${site}/ajax/${channel}/setcertificate"]
    <input type="hidden" name="uid" />
    <tr><td>证书名称:</td><td><span set-key="certificatename"></span></td></tr>
    <tr><td>类型:</td><td><span set-key="typeName"></span></td></tr>
    <tr><td>证书获得人姓名:</td><td><span set-key="showName"></span></td></tr>
    <tr><td>专业:</td><td><span set-key="majorName"></span></td></tr>
    <tr><td>证书扫描件：</td>
        <td><img style="width:150px; height:150px" src="" set-format="${userimg}{1}" set-key="imglittle"/>
             [@a.imgupload multiple=false imgpath="/imgs/webdata/manger/certificate" name="imglittle"/]
        </td>
    </tr>
    [/@a.editsection]
</body>
</html>