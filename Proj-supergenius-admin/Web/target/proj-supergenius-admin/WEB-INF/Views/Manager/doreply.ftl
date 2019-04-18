[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Admin.ftl" as a]
[#import "/WEB-INF/FtlLib/Macro/Expert.ftl" as e]
[#import "/WEB-INF/FtlLib/Macro/ExpertSingle.ftl" as es]
<html>
<head>
<title>答辩管理</title>
<script type="text/javascript">
<!--
    function initDGData(row) {
        for(var i in row) {
            if (row[i].user != null) {
                row[i].showName = row[i].user.showname;
                row[i].userSn = row[i].user.usersn;
            }
            if(row[i].isvideotopic) {
                row[i].isvideotopicName = "视频会议系统";
            } else {
                row[i].isvideotopicName = "现场";
            }
            if(row[i].file != null) {
                var name = row[i].file.substring(row[i].file.lastIndexOf("/") + 1);
                row[i].File = "<a href='javascript:;' onclick='downloadFile(\"" + row[i].file + "\")'>"+ name +"</a>";
            } else {
                row[i].File = "暂无";
            }
            if (row[i].expert != null) {
                row[i].expertuid = row[i].expert.uid;
                if (row[i].expert.user != null) {
                    row[i].expertname = row[i].expert.user.name;
                }
                row[i].expertsn = row[i].expert.sn;
            }
            if (row[i].expert2 != null) {
                row[i].expert2uid = row[i].expert2.uid;
                if (row[i].expert2.user != null) {
                    row[i].expert2name = row[i].expert2.user.name;
                }
                row[i].expert2sn = row[i].expert2.sn;
            }
            if (row[i].expert3 != null) {
                row[i].expert3uid = row[i].expert3.uid;
                if (row[i].expert3.user != null) {
                    row[i].expert3name = row[i].expert3.user.name;
                }
                row[i].expert3sn = row[i].expert3.sn;
            }
            if (row[i].opentimes != null && row[i].opentimes != '') {
                var opentimeshtml = '';
                var opentimesstr = '';
                var opentimesarr = row[i].opentimes.substring(0, row[i].opentimes.lastIndexOf(',')).split(",");
                for (var k in opentimesarr) {
                    if (k == 0) {
                        opentimeshtml = opentimeshtml + '<tr>';
                        opentimesstr = opentimesstr + '<tr>';
                    } else if (k == 5) {
                        opentimeshtml = opentimeshtml + '</tr><tr>';
                        opentimesstr = opentimesstr + '</tr><tr>';
                    }
                    opentimeshtml = opentimeshtml + '<td><input type="radio" name="opentimeok" value="' + opentimesarr[k] + '"/>' + opentimesarr[k] + '</td>';
                    opentimesstr = opentimesstr + '<td>' + opentimesarr[k] + '</td>';
                }
                row[i].openTimes = opentimeshtml + '</tr>';
                row[i].openTimesView = opentimesstr + '</tr>';
            }
            if (row[i].replytimes != null && row[i].replytimes != '') {
                var replytimeshtml = '';
                var replytimesstr = '';
                var replytimesarr = row[i].replytimes.substring(0, row[i].replytimes.lastIndexOf(',')).split(",");
                for (var k in replytimesarr) {
                    if (k == 0) {
                        replytimeshtml = replytimeshtml + '<tr>';
                        replytimesstr = replytimesstr + '<tr>';
                    } else if (k == 5) {
                        replytimeshtml = replytimeshtml + '</tr><tr>';
                        replytimesstr = replytimesstr + '</tr><tr>';
                    }
                    replytimeshtml = replytimeshtml + '<td><input type="radio" name="replytimeok" value="' + replytimesarr[k] + '"/>' + replytimesarr[k] + '</td>';
                    replytimesstr = replytimesstr + '<td>' + replytimesarr[k] + '</td>';
                }
                row[i].replyTimes = replytimeshtml + '</tr>';
                row[i].replyTimesView = replytimesstr + '</tr>';
            }
            if (row[i].appreplydetaillist != null && row[i].appreplydetaillist.length != 0) {
                var filelist = '';
                var file2list = '';
                var file3list = '';
                var file4list = '';
                var appreplydetail = '<table border="2" cellpadding="15"><tr><td>操作人姓名</td><td>原状态</td><td>现状态</td><td>操作时间</td></tr>';
                
                for(var k in row[i].appreplydetaillist) {
                    if(row[i].appreplydetaillist[k].file != "" && row[i].appreplydetaillist[k].file != null) {
                        if (row[i].appreplydetaillist[k].replystagefrom == "2" || row[i].appreplydetaillist[k].replystagefrom == "4") {
                           var name = row[i].appreplydetaillist[k].file.substring(row[i].appreplydetaillist[k].file.lastIndexOf("/") + 1);
                           filelist = filelist + "<a href='javascript:;' onclick='downloadFile(\"" + row[i].appreplydetaillist[k].file + "\")'>" + name + "</a>&nbsp;&nbsp;<br/>";
                        }
                        if (row[i].appreplydetaillist[k].replystagefrom == "9" || row[i].appreplydetaillist[k].replystagefrom == "11") {
                           var name = row[i].appreplydetaillist[k].file.substring(row[i].appreplydetaillist[k].file.lastIndexOf("/") + 1);
                           file2list = file2list + "<a href='javascript:;' onclick='downloadFile(\"" + row[i].appreplydetaillist[k].file + "\")'>" + name + "</a>&nbsp;&nbsp;<br/>";
                        }
                        if (row[i].appreplydetaillist[k].replystagefrom == "12" || row[i].appreplydetaillist[k].replystagefrom == "14") {
                           var name = row[i].appreplydetaillist[k].file.substring(row[i].appreplydetaillist[k].file.lastIndexOf("/") + 1);
                           file3list = file3list + "<a href='javascript:;' onclick='downloadFile(\"" + row[i].appreplydetaillist[k].file + "\")'>" + name + "</a>&nbsp;&nbsp;<br/>";
                        }
                        if (row[i].appreplydetaillist[k].replystagefrom == "19" || row[i].appreplydetaillist[k].replystagefrom == "21") {
                           var name = row[i].appreplydetaillist[k].file.substring(row[i].appreplydetaillist[k].file.lastIndexOf("/") + 1);
                           file4list = file4list + "<a href='javascript:;' onclick='downloadFile(\"" + row[i].appreplydetaillist[k].file + "\")'>" + name + "</a>&nbsp;&nbsp;<br/>";
                        }
                    }
                    if (row[i].appreplydetaillist[k].adminname == null) {
                        row[i].appreplydetaillist[k].adminname = '';
                    }
                    if (row[i].appreplydetaillist[k].replystagefromName == null || row[i].appreplydetaillist[k].replystagetoName == undefined) {
                        row[i].appreplydetaillist[k].replystagefromName = '';
                    }
                    if (row[i].appreplydetaillist[k].replystagetoName == null || row[i].appreplydetaillist[k].replystagetoName == undefined) {
                        row[i].appreplydetaillist[k].replystagetoName = '';
                    }
                    if (row[i].appreplydetaillist[k].createtimeStr == null || row[i].appreplydetaillist[k].createtimeStr == undefined) {
                        row[i].appreplydetaillist[k].createtimeStr = '';
                    }
                    appreplydetail = appreplydetail + '<tr><td>' + row[i].appreplydetaillist[k].adminname  + '</td><td>' + row[i].appreplydetaillist[k].replystagefromName + '</td><td>' + row[i].appreplydetaillist[k].replystagetoName + '</td><td>' + row[i].appreplydetaillist[k].createtimeStr +'</td></tr>';
                }
                appreplydetail = appreplydetail + '</table>';
                if (filelist == "") {
                    row[i].filelist = "暂无";
                } else {
                    row[i].filelist = filelist;
                }
                if (file2list == "") {
                    row[i].file2list = "暂无";
                } else {
                    row[i].file2list = file2list;
                } if (file3list == "") {
                    row[i].file3list = "暂无";
                } else {
                    row[i].file3list = file3list;
                }
                if (file4list == "") {
                    row[i].file4list = "暂无";
                } else {
                    row[i].file4list = file4list;
                }
                if (appreplydetail == '') {
                    row[i].appreplydetail = "暂无";
                } else {
                    row[i].appreplydetail = appreplydetail;
                }
            } else {
                row[i].filelist = "暂无";
                row[i].file2list = "暂无";
                row[i].file3list = "暂无";
                row[i].file4list = "暂无";
                row[i].appreplydetail = "暂无";
            }
        }
    }
    
    function mytoolbarStateHandler(event, j) {
        var row = getSelected(j.dg); 
        if (row) {
            if (row.replystage == "0") {
                $("#btncheckreply").linkbutton("enable");
            } else {
                $("#btncheckreply").linkbutton("disable");
            }
            if (row.replystage == "3") {
                $("#btncheckopenreplyfile").linkbutton("enable");
            } else {
                $("#btncheckopenreplyfile").linkbutton("disable");
            }
            if (row.replystage == "5") {
                $("#btnsetstartopenreply").linkbutton("enable");
                $("#btnaddqqgroupmetting").linkbutton("enable");
            } else {
                $("#btnsetstartopenreply").linkbutton("disable");
                $("#btnaddqqgroupmetting").linkbutton("disable");
            }
            if (row.replystage == "6") {
                $("#btnsetendopenreply").linkbutton("enable");
            } else {
                $("#btnsetendopenreply").linkbutton("disable");
            }
            if (row.replystage == "5" || row.replystage == "6" || row.replystage == "7") {
                $("#btnsetopenreplyresult").linkbutton("enable");
            } else {
                $("#btnsetopenreplyresult").linkbutton("disable");
            }
            if (row.replystage >= 9 && row.replystage != "23" && row.status == "1") {
                $("#btnaddpkvideo").linkbutton("enable");
            } else {
                $("#btnaddpkvideo").linkbutton("disable");
            }
            if (row.replystage == "10") {
                $("#btncheckprereplyfile").linkbutton("enable");
            } else {
                $("#btncheckprereplyfile").linkbutton("disable");
            }
            if (row.replystage == "13") {
                $("#btnsetreplyfileresult").linkbutton("enable");
            } else {
                $("#btnsetreplyfileresult").linkbutton("disable");
            }
            if (row.replystage != "0" && row.replystage != "1" && row.replystage != "2" && row.replystage != "3" && row.replystage != "4" && row.replystage != "23") {
                $("#btnchangeexpert").linkbutton("enable");
                $("#btnsetreplystart").linkbutton("enable");
                $("input[name='specialexpert1name']").val(row.expert.user.name);
                $("input[name='specialexpert2name']").val(row.expert2.user.name);
                $("input[name='specialexpert3name']").val(row.expert3.user.name);
            } else {
                $("#btnchangeexpert").linkbutton("disable");
                $("#btnsetreplystart").linkbutton("disable");
            }
            if (row.replystage == "16") {
                $("#btnsetreplyend").linkbutton("enable");
            } else {
                $("#btnsetreplyend").linkbutton("disable");
            }
            if (row.replystage == "17") {
                $("#btncheckreplyresult").linkbutton("enable");
            } else {
                $("#btncheckreplyresult").linkbutton("disable");
            }
            if (row.replystage == "20") {
                $("#btncheckreport").linkbutton("enable");
            } else {
                $("#btncheckreport").linkbutton("disable");
            }
        } else {
            $("#btncheckreply").linkbutton("disable");
            $("#btncheckopenreplyfile").linkbutton("disable");
            $("#btnsetstartopenreply").linkbutton("disable");
            $("#btnsetendopenreply").linkbutton("disable");
            $("#btnsetopenreplyresult").linkbutton("disable");
            $("#btncheckprereplyfile").linkbutton("disable");
            $("#btnsetreplyfileresult").linkbutton("disable");
            $("#btnchangeexpert").linkbutton("disable");
            $("#btnsetreplystart").linkbutton("disable");
            $("#btnsetreplyend").linkbutton("disable");
            $("#btncheckreplyresult").linkbutton("disable");
            $("#btncheckreport").linkbutton("disable");
            $("#btnaddqqgroupmetting").linkbutton("disable");
            $("#btnaddpkvideo").linkbutton("disable");
        }
    } 
    
    function downloadFile(name) {
        var path = name.substring(0, name.lastIndexOf("/") + 1);
        var filename = name.substring(name.lastIndexOf("/") + 1, name.lastIndexOf("."));
        var suffix = name.substring(name.lastIndexOf("."));
        var url = "${base}${baseAdminPath}/${site}/${channel}/download" + "?name=" + filename + "&suffix=" + suffix + "&path=" + path;
        gourl(url);
    }
    
   function myButtonHandler(obj, url) {
        var objid = $(obj).attr("id");
        if (objid == "btncheckreply") {
			initFormData($("#checkreplydialogfrom"), getSelected(dg));
			$("#checkreplydialogfrom").dialog('open');
		}
		if (objid == "btnsetopenreplyresult") {
		    $("#setopenreplyresultdialogfrom").dialog('open');
		}
		if (objid == "btnaddqqgroupmetting") {
		    $("#addqqgroupmettingdialogfrom").dialog('open');
		}
		if (objid == "btnaddpkvideo") {
		    var uid = getSelected(dg).uid;
		    window.location.href = "${base}${baseAdminPath}/manager/video/reply/add/" + uid;
		}
    }
    
    function confirmAjax(title, question, url) {
		$.messager.confirm(title, question, function(flag) {
			if (flag) {
				ajaxPostSubmit(url);
			}
		});
	}
	
	function openReplyResultHandler(obj, dlgid)　{
	    var reason = $("#openreplyresultarea").val();
	    var checkresult = $('input[name="openreplyresult"]:checked').val();
		var url = '${base}${baseAdminPath}/${site}/ajax/${channel}/setopenreplyresult';
		ajaxPostSubmit(url, checkresult, reason)
		getStatusDLG(dlgid).dialog('close');
	}
	
	function addqqgroupmettingHandler(obj, dlgid)　{
	    var qq = $("input[name='qqgroup']").val();
		var url = '${base}${baseAdminPath}/${site}/ajax/${channel}/addqqgroupmetting';
		ajaxPostSubmit(url, null, qq)
		getStatusDLG(dlgid).dialog('close');
	}
	
	function checkReplyHandler(obj, dlgid)　{
	    var reason = $("#checkreplyarea").val();
	    var checkresult = $('input[name="checkreply"]:checked').val();
		var url = '${base}${baseAdminPath}/${site}/ajax/${channel}/setcheckreply';
		ajaxPostSubmit(url, checkresult, reason)
		getStatusDLG(dlgid).dialog('close');
	}
	
	function ajaxPostSubmit(url, checkresult, reason) {
		$.post(url, {uid : getSelected(dg).uid, reason : reason, checkresult : checkresult}, function(result) {
			if (result.success) {
				getDataGrid(dg).datagrid("reload"); // 重新加载数据表格
				resultHandler(result);
			} else {
				resultHandler(result);
			}
		});
	}
    
//-->
</script>
</head>
<body>
    <section>
        <table>
            <tr>
                <td>答辩申请总数：</td>
                <td><span>${totalreplycount}</span></td>
                <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td>二次答辩申请总数：</td>
                <td><span>${totalsecondreplycount}</span></td>
                <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td>RMBA答辩成功数：</td>
                <td><span>${rmbareplyasuccesscount}</span></td>
                <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td>SMBA答辩成功数：</td>
                <td><span>${smbareplyasuccesscount}</span></td>
                <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td>TMBA答辩成功数：</td>
                <td><span>${tmbareplyasuccesscount}</span></td>
                <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td>答辩失败数：</td>
                <td><span>${totalreplyfailedcount}</span></td>
                <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td>二次答辩成功数：</td>
                <td><span>${totalreplyfailedcount}</span></td>
            <tr>
        </table>
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
                <td><label>学员级别：</label></td>
                <td>
                    <select name="level">
                        <option value="">全部</option>
                            [#list levels?keys as item]
                                <option value="${item}">${levels[item]}</option>
                            [/#list]
                    </select>
                </td>
                <td><label>答辩类型：</label></td>
                <td><select name="degree">
                    <option value="">全部</option>
                        [#list types?keys as item]
                            <option value="${item}">${types[item]}</option>
                        [/#list]
                    </select>
                </td>
                <td><label>答辩进展：</label></td>
                <td>
                    <select name="stages">
                        <option value="">全部</option>
                        <option value="0">开题申请审核</option>
                        <option value="1">开题论证会</option>
                        <option value="2">预答辩审核</option>
                        <option value="3">答辩申请审核</option>
                        <option value="4">即将开始</option>
                        <option value="5">进行中</option>
                        <option value="6">等待答辩结果</option>
                        <option value="7">研究报告审核中</option>
                        <option value="8">研究报告通过</option>
                    </select>
                </td>
            </tr>
        [/@a.search]
        [@a.datagrid]
            <th data-options="field:'sn'">答辩编号</th>
            <th data-options="field:'showName'">学员姓名</th>
            <th data-options="field:'majorName'">专业</th>
            <th data-options="field:'majorlevelName'">级别</th>
            <th data-options="field:'degree'">申请学位</th>
            <th data-options="field:'isvideotopicName'">面试形式</th>
            <th data-options="field:'opentimeok'">开题时间</th>
            <th data-options="field:'replytimeok'">答辩时间</th>
            <th data-options="field:'replystageName', width:30">状态</th>
            <th data-options="field:'updatetimeStr'">最新操作时间</th>
        [/@a.datagrid]
        [@a.toolbar canadd=false canedit=false candel=false]
        <a id="btncheckreply" href="" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="myButtonHandler(this, '${base}${baseAdminPath}/${site}/ajax/${channel}/setcheckreply')">审核开题申请</a>
        <a id="btncheckopenreplyfile" href="" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" data-crumbshow="{selector:'#setcheckopenreplyfile', title:'审核开题申请材料 '}" onclick="editHandler(this, null, '#setcheckopenreplyfile')">审核开题申请材料 </a>
        <a id="btnaddqqgroupmetting" href="" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="myButtonHandler(this, '${base}${baseAdminPath}/${site}/ajax/${channel}/addqqgroupmetting')">创建qq群会议室</a>
        <a id="btnaddpkvideo" href="" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="myButtonHandler(this, null)">添加视频</a>
        <a id="btnchangeexpert" href="" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" data-crumbshow="{selector:'#setchangeexpert', title:'更改专家'}" onclick="editHandler(this, null, '#setchangeexpert')">更改专家</a>
        <a id="btnsetopenreplyresult" href="" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="myButtonHandler(this, '${base}${baseAdminPath}/${site}/ajax/${channel}/setopenreplyresult')">开题论证会结果</a>
        <a id="btncheckprereplyfile" href="" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" data-crumbshow="{selector:'#setcheckprereply', title:'审核预答辩材料'}" onclick="editHandler(this, null, '#setcheckprereply')">审核预答辩材料</a>
        <a id="btnsetreplyfileresult" href="" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" data-crumbshow="{selector:'#setreplyfileresult', title:'审核答辩材料'}" onclick="editHandler(this, null, '#setreplyfileresult')">审核答辩材料</a>
        <a id="btncheckreplyresult" href="" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" data-crumbshow="{selector:'#setcheckreplyresult', title:'设置答辩结果'}" onclick="editHandler(this, null, '#setcheckreplyresult')">设置答辩结果</a>
        <a id="btncheckreport" href="" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" data-crumbshow="{selector:'#setcheckreport', title:'审核研究报告'}" onclick="editHandler(this, null, '#setcheckreport')">审核研究报告</a>
        [@a.status /]
        [/@a.toolbar]
        [@a.tools /]
    </section>
    [@a.viewsection]
        <tr><td>答辩编号:</td><td><span set-key="sn"></span></td></tr>
        <tr><td>学员姓名:</td><td><span set-key="showName"></span></td></tr>
        <tr><td>专业:</td><td><span set-key="majorName"></span></td></tr>
        <tr><td>级别:</td><td><span set-key="majorlevelName"></span></td></tr>
        <tr><td>申请学位:</td><td><span set-key="degree"></span></td></tr>
        <tr><td>答辩费用:</td><td><span set-key="replyfee"></span></td></tr>
        <tr><td>开题资料:</td><td><span set-key="filelist"></span></td></tr>
        <tr><td>提供的开题时间:</td><td><span set-key="openTimesView"></span></td></tr>
        <tr><td>开题时间：</td><td><span set-key="opentimeok"></span></td></tr>
        <tr><td>QQ群会议室：</td><td><span set-key="qqgroup"></span></td></tr>
        <tr><td>预答辩申请材料：</td><td><span set-key="file2list"></span></td></tr>
        <tr><td>提供的答辩时间：</td><td><span set-key="replyTimesView"></span></td></tr>
        <tr><td>确定的答辩时间：</td><td><span set-key="replytimeok"></span></td></tr>
        <tr><td>答辩申请材料：</td><td><span set-key="file3list"></span></td></tr>
        <tr><td>毕业研究报告</td><td><span set-key="file3list"></span></td></tr>
        <tr><td>当前状态：</td><td><span set-key="replystageName"></span></td></tr>
        <tr><td>答辩状态明细：</td><td><span set-key="appreplydetail"></span></td></tr>
    [/@a.viewsection]
    
    [@a.editsection id="setcheckopenreplyfile" namesubmit="开题材料审核" action="${base}${baseAdminPath}/${site}/ajax/${channel}/setcheckopenreplyfile" canuploadfile=true]
         <td><input type="hidden" name="uid"></td>
         <tr><td>开题材料:</td><td><span set-key="File"></span></td></tr>
         <tr><td>反馈材料:</td><td><input class="easyui-filebox" type="file" name="filedata" /></td></tr>
         <tr><td>选择答辩时间:</td><td><span set-key="openTimes"></span></td></tr>
         <tr><td>开题论证会地点:</td><td><input name="openaddress" /></td></tr>
         <tr><td>指定专家:</td><td>[@e.expertsearch name="specialexpert" /]</td></tr>
         <tr>
            <td>审核结果:</td>
            <td><input type="radio" name="checkopenreplyfile" value="1">通过
                <input type="radio" name="checkopenreplyfile" value="0">不通过
            </td>
         </tr>
         <tr><td class="hd" id="checkopenreplyfileerrmsg" ><span style="color:#f00;">请选择三名专家！<span></td></tr>
    [/@a.editsection]
    
    [@a.editsection id="setcheckprereply" namesubmit="审核预答辩材料" action="${base}${baseAdminPath}/${site}/ajax/${channel}/setcheckprereply" canuploadfile=true]
         <input type="hidden" name="uid">
         <tr><td>预答辩材料:</td> <td><span set-key="File"></span></td></tr>
        <tr>
            <td>审核结果:</td>
            <td><input type="radio" name="checkreplyfile" value="1">通过
                <input type="radio" name="checkreplyfile" value="0">不通过
            </td>
         </tr>
         <tr><td>理由:</td><td><textarea style="height:150px; width:450px;" id="textarea" name="reason" placeholder="请说明理由" class="easyui-validatebox" required="true"></textarea></td></tr>
         <tr><td>反馈材料：</td><td><input class="easyui-filebox" type="file" name="filedata" /></td></tr>
    [/@a.editsection]

    [@a.editsection id="setreplyfileresult" namesubmit="审核答辩材料 " action="${base}${baseAdminPath}/${site}/ajax/${channel}/setreplyfileresult" canuploadfile=true]
         <input type="hidden" name="uid">
         <tr><td>答辩材料:</td><td><span set-key="File"></span></td></tr>
         <tr><td>参会方式:</td><td><span set-key="">现场</span></td></tr>
         <tr><td>选择答辩时间:</td><td><span set-key="replyTimes"></span></td></tr>
         <tr><td>答辩地点:</td><td><input class="easyui-validatebox" required="true" name="replyaddress" /></td></tr>
         <tr><td>反馈材料：</td><td><input class="easyui-filebox" type="file" name="filedata" /></td></tr>
         <tr>
            <td>审核结果:</td>
            <td><input type="radio" name="checkappreply" value="1">通过
                <input type="radio" name="checkappreply" value="0">不通过
            </td>
         </tr>
    [/@a.editsection]

    [@a.editsection id="setchangeexpert" namesubmit="更改专家" action="${base}${baseAdminPath}/${site}/ajax/${channel}/setchangeexpert"]
         <td><input type="hidden" name="uid"></td>
         <td><input type="hidden" name="expertuid"></td>
         <td><input type="hidden" name="expert2uid"></td>
         <td><input type="hidden" name="expert3uid"></td>
         <tr><td>专家1</td><td>姓名:</td><td width="150px"><span set-key="expertname"></span></td><td>编号:</td><td width="200px"><span set-key="expertsn"></span></td><td>指定新专家:</td><td width="400px">[@es.expertsearchsingle name="specialexpert1" /]</td></tr>
         <tr><td>专家2</td><td>姓名:</td><td width="150px"><span set-key="expert2name"></span></td><td>编号:</td><td width="200px"><span set-key="expert2sn"></span></td><td>指定新专家:</td><td width="400px">[@es.expertsearchsingle name="specialexpert2" /]</td></tr>
         <tr><td>专家3</td><td>姓名:</td><td width="150px"><span set-key="expert3name"></span></td><td>编号:</td><td width="200px"><span set-key="expert3sn"></span></td><td>指定新专家:</td><td width="400px">[@es.expertsearchsingle name="specialexpert3" /]</td></tr>
    [/@a.editsection]
    
    [@a.editsection id="setcheckreplyresult" namesubmit="设置答辩结果" action="${base}${baseAdminPath}/${site}/ajax/${channel}/setreplyresult" canuploadfile=true]
         <input type="hidden" name="uid">
         <tr>
            <td>答辩结果:</td>
            <td><input type="radio" name="replyresult" value="1">通过
                <input type="radio" name="replyresult" value="0">不通过
            </td>
         </tr>
         <tr><td>反馈材料：</td><td><input class="easyui-filebox" type="file" name="filedata" /></td></tr>
    [/@a.editsection]
   
    [@a.editsection id="setcheckreport" namesubmit="审核研究报告" action="${base}${baseAdminPath}/${site}/ajax/${channel}/setcheckreport" canuploadfile=true]
         <input type="hidden" name="uid">
         <tr><td>研究报告:</td><td><span set-key="File"></span></td></tr>
         <tr>
            <td>审核结果:</td>
            <td><input type="radio" name="checkreport" value="1">通过
                <input type="radio" name="checkreport" value="0"> 不通过
            </td>
         </tr>
         <tr><td>理由:</td><td><textarea style="height:150px; width:450px;" id="textarea" name="reason" placeholder="请说明理由" class="easyui-validatebox" required="true"></textarea></td></tr>
         <tr><td>反馈材料：</td><td><input class="easyui-filebox" type="file" name="filedata" /></td></tr>
    [/@a.editsection]
   
    <form id="checkreplydialogfrom" class="easyui-dialog" style="width:600px; height:340px; padding:10px 20px; " title="审核开题申请" buttons="#dlg-status-btns" data-options="closed:true" >
		<table>
			<tr>
	            <td>会员号:</td>
	            <td><span set-key="user.usersn"></span></td>
	        </tr>
	        <tr>
	            <td>学员姓名:</td>
	            <td><span set-key="user.showname"></span></td>
	        </tr>
	        <tr>
	            <td>专业:</td>
	            <td><span set-key="majorName"></span></td>
	        </tr>
	        <tr>
	            <td>级别:</td>
	            <td><span set-key="majorlevelName"></span></td>
	        </tr>
	        <tr>
	            <td>申请学位:</td>
	            <td><span set-key="degree"></span></td>
	        </tr>
	        <tr>
	            <td>研究对象:</td>
	            <td><span set-key="">自选</span></td>
	        </tr>
	        <tr>
	            <td>审核结果:</td>
	            <td><input type="radio" class="checkresult" name="checkreply" value="0"/>不通过&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                <input type="radio" class="checkresult" name="checkreply" value="1"/> 通过
	            </td>
	        </tr>
	        <tr>
	            <td>理由:</td>
	            <td><textarea style="height:100px; width:300px;" id="checkreplyarea" placeholder="请说明理由" class="easyui-validatebox" required="true"></textarea></td>
	        </tr>       
		</table>
		<div id="dlg-status-btns">
			<a href="" class="easyui-linkbutton" iconCls="icon-ok" onclick="checkReplyHandler(this ,'#checkreplydialogfrom')">确定</a>
			<a href="" class="easyui-linkbutton" iconCls="icon-cancel" onclick="statusdlg_cancelHandler(this, '#checkreplydialogfrom')">取消</a>
		</div>
	</form>
		
	<form id="addqqgroupmettingdialogfrom" class="easyui-dialog" style="width:500px; height:140px; padding:10px 20px; " title="设置开题论证会结果" buttons="#dlg-status-btns" data-options="closed:true" >
		<div id="addqqgrouptable">
		     <tr>
	            <td>qq群会议室号:</td>
	            <td><input name="qqgroup" placeholder="请输入已创建的会议室QQ群号码" class="easyui-validatebox" required="true"></td>
	        </tr> 
		</div>
		<div id="dlg-status-btns">
			<a href="" class="easyui-linkbutton" iconCls="icon-ok" onclick="addqqgroupmettingHandler(this ,'#addqqgroupmettingdialogfrom')">确定</a>
			<a href="" class="easyui-linkbutton" iconCls="icon-cancel" onclick="statusdlg_cancelHandler(this, '#addqqgroupmettingdialogfrom')">取消</a>
		</div>
	</form>
	
	<form id="setopenreplyresultdialogfrom" class="easyui-dialog" style="width:500px; height:140px; padding:10px 20px; " title="设置开题论证会结果" buttons="#dlg-status-btns" data-options="closed:true" >
		<table>
	        <tr>
	            <td>审核结果:</td>
	            <td><input type="radio" class="checkresult" name="openreplyresult" value="0"/>不通过&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                <input type="radio" class="checkresult" name="openreplyresult" value="1"/>通过
	            </td>
	        </tr>
	        <tr>
	            <td>理由:</td>
	            <td><textarea style="height:100px; width:300px;" id="openreplyresultarea" placeholder="请说明理由" class="easyui-validatebox" required="true"></textarea></td>
	        </tr> 
		</table>
		<div id="dlg-status-btns">
			<a href="" class="easyui-linkbutton" iconCls="icon-ok" onclick="openReplyResultHandler(this ,'#setopenreplyresultdialogfrom')">确定</a>
			<a href="" class="easyui-linkbutton" iconCls="icon-cancel" onclick="statusdlg_cancelHandler(this, '#setopenreplyresultdialogfrom')">取消</a>
		</div>
	</form>
</body>
</html>