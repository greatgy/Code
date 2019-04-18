[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Admin.ftl" as a]
[#import "/WEB-INF/FtlLib/Macro/Judge.ftl" as j]
<html>
<head>
<title>挑战管理</title>
<script type="text/javascript" src="${basejs}/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
<!--
   function initDGData(row) {
        for(var i in row) {
            if (row[i].pkjudgements != null && row[i].pkjudgements != '') {
                var vicepkjudgement = '';
                var vicepkjudgement2 = '';
                for (var k in row[i].pkjudgements) {
                    if (row[i].pkjudgements[k] != null && row[i].pkjudgements[k] != '') {
                        if (row[i].pkjudgements[k].useruid == row[i].pkuseruid && row[i].pkjudgements[k].username != null) {
                            vicepkjudgement = vicepkjudgement + row[i].pkjudgements[k].username + ',';
                        } else if (row[i].pkjudgements[k].useruid == row[i].pkuseruid2 && row[i].pkjudgements[k].username != null) {
                            vicepkjudgement2 = vicepkjudgement2 + row[i].pkjudgements[k].username + ',';
                        }
                    }
                }
                if (vicepkjudgement != '') {
                    vicepkjudgement = vicepkjudgement.substring(0, vicepkjudgement.length - 1);
                }
                if (vicepkjudgement2 != '') {
                    vicepkjudgement2 = vicepkjudgement2.substring(0, vicepkjudgement2.length - 1);
                }
                row[i].vicepkjudgementname = vicepkjudgement;
                row[i].vicepkjudgement2name = vicepkjudgement2;
            }
        }
    }            

$.extend($.fn.validatebox.defaults.rules, {  
    equalTo: {
        validator:function(value,param){
            return $(param[0]).val() == value;
        },
        message:'字段不匹配'
    }
           
});

	$(function() {
		//点击下载文件
		$("#adownload").click(function() {
			var uid = $("#viewuid").val();
			var url = "${base}${baseAdminPath}/${site}/${channel}/download" + "?uid=" + uid;
			gourl(url);
		});
	 });
	 
    function mytoolbarStateHandler(event, j) {
        var list = getSelections(j.dg); 
        if (list.length > 0) {
            $("#btnpkstageview").linkbutton("enable");
            var row = list[0];
            if (row.stage == "11" || row.stage == "12" || row.stage == "13" || row.stage == "29") {
                $("#btnsetjudge").linkbutton("enable");
                $("input[name='setjudgement1name']").val(row.judgementname);
                $("input[name='setjudgement2name']").val(row.judgementname2);
                $("input[name='setjudgement3name']").val(row.judgementchairname);
            } else {
                $("#btnsetjudge").linkbutton("disable");
            }
            if (row.stage == "24") {
                $("#btnsetpkresult").linkbutton("enable");
            } else {
                $("#btnsetpkresult").linkbutton("disable");
            }
            if (row.stage == "16") {
                $("#btnaddvideo").linkbutton("enable");
            } else {
                $("#btnaddvideo").linkbutton("disable");
            }
            if (row.stage == "23") {
                $("#btnaddqqgroup").linkbutton("enable");
            } else {
                $("#btnaddqqgroup").linkbutton("disable");
            }
            if (row.ticketstatus == "1") {
                $("#btnsetticket").linkbutton("enable");
            } else {
                $("#btnsetticket").linkbutton("disable");
            }
            if (row.ticketstatus == "1" || row.ticketstatus == "2") {
                $("#btnpkview").linkbutton("enable");
            } else {
                $("#btnpkview").linkbutton("disable");
            }
            if (row.stage == "15") {
                $("#btnsetpkover").linkbutton("enable");
            } else {
                $("#btnsetpkover").linkbutton("disable");
            }
            if (row.stage == "14") {
                $("#btnuploadfile").linkbutton("enable");
            } else {
                $("#btnuploadfile").linkbutton("disable");
            }
        } else {
            $("#btnpkstageview").linkbutton("disable");
            $("#btnsetjudge").linkbutton("disable");
            $("#btnsetticket").linkbutton("disable");
            $("#btnsetpkresult").linkbutton("disable");
            $("#btnpkstageview").linkbutton("disable");
            $("#btnpkview").linkbutton("disable");
            $("#btnaddqqgroup").linkbutton("disable");
            $("#btnaddvideo").linkbutton("disable");
            $("#btnsetpkover").linkbutton("disable");
            $("#btnuploadfile").linkbutton("disable");
        }
    }
    
     function myButtonHandler(obj, url) {
        $("#pkdetaillisttable").empty();
        $("#pkviewlisttable").empty();
        var pkdetailhtml = '<table border="1"><tr><td width="150px">用户</td><td width="150px">原状态</td><td width="150px">现状态</td><td width="150px">创建时间</td></tr>';
        var pkviewhtml = '<table border="1"><tr><td width="150px">会员名</td><td width="150px">用户邮件</td><td width="150px">用户类型</td><td width="150px">状态</td></tr>';
        var objid = $(obj).attr("id");
        if (objid == "btnpkstageview") {
            var uid = getSelected(dg).uid;
            var url = '${base}${baseAdminPath}/${site}/ajax/${channel}/getpkdetail';
            $.get(url, {uid : uid}, function (result) {
                var views = JSON.parse(result);
                for (var k in views) {
                    pkdetailhtml = pkdetailhtml + '<tr><td width="50px">' + views[k].username + '</td><td width="50px">' + views[k].fromstageName + '</td><td>' + views[k].tostageName + '</td><td>' + views[k].createtimeStr + '</td></tr>';
                }
                pkdetailhtml = pkdetailhtml + '</table>'
            $("#pkdetaillisttable").append(pkdetailhtml);
            });
			$("#pkdetaildialogfrom").dialog('open');
		}
		if (objid == "btnpkview") {
		    var uid = getSelected(dg).uid;
		    var url = '${base}${baseAdminPath}/${site}/ajax/${channel}/getpkview';
		    $.get(url, {uid : uid}, function (result) {
		        var detail = JSON.parse(result);
                for (var k in detail) {
                    pkviewhtml = pkviewhtml + '<tr><td width="50px">' + detail[k].showname + '</td><td width="50px">' + detail[k].email + '</td><td width="50px">' + detail[k].type + '</td><td width="50px">' + detail[k].statusName + '</td></tr>';
                }
                pkviewhtml = pkviewhtml + '</table>';
            $("#pkviewlisttable").append(pkviewhtml);
            });
			$("#pkviewdialogfrom").dialog('open');
		}
		if (objid == "btnaddqqgroup") {
		    initFormData($("#addqqgroupdialogfrom"), getSelected(dg));
		    $("#addqqgroupdialogfrom").dialog('open');
		}
		if (objid == "btnuploadfile") {
		    initFormData($("#uploadfiledialogfrom"), getSelected(dg));
		    $("#uploadfiledialogfrom").dialog('open');
		}
		if (objid == "btnaddvideo") {
		    var uid = getSelected(dg).uid;
		    window.location.href = "${base}${baseAdminPath}/manager/video/pk/add/" + uid;
		}
		if (objid == "btnsetpkover") {
		    confirmAjax('结束挑战', '您确定要结束本次挑战吗？', '${base}${baseAdminPath}/manager/ajax/challenge/setover');
		}
    }
    
	function confirmAjax(title, question, url) {
		$.messager.confirm(title, question, function(flag) {
			if (flag) {
				ajaxPostSubmit(url, null);
			}
		});
	}
    
   function addQQGroupDialogFromHandler(obj, dlgid)　{
		var qq = $("input[name='qqgroup']").val();
		var url = '${base}${baseAdminPath}/${site}/ajax/${channel}/addqqmetting';
		ajaxPostSubmit(url, qq)
		getStatusDLG(dlgid).dialog('close');
	}
	
   function uploadFileDialogFromHandler(obj, dlgid)　{
		var file = $("input[name='pkfile']").val();
		$(dlgid).form('submit',{
			url	: '${base}${baseAdminPath}/${site}/ajax/${channel}/uploadfile',
			success: function(data){  
				var result = $.parseJSON(data);  
		        if (result.success){    
		        	clearForm(dlgid);
		            getDataGrid(dg).datagrid("reload"); // 重新加载数据表格
		            
		        }
		        resultHandler(result);   
		    }    
		});
		getStatusDLG(dlgid).dialog('close');
	}
	
	function ajaxPostSubmit(url, qq) {
		$.post(url, {uid : getSelected(dg).uid, qq : qq}, function(result) {
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
                <td>挑战申请总数：</td>
                <td><span>${totalpkcount}</span></td>
                <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td>挑战申请成功数：</td>
                <td><span>${successpkcount}</span></td>
                <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td>挑战申请失败数：</td>
                <td><span>${failpkcount}</span></td>
                <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td>门票收入合计：</td>
                <td><span>${totalincome}</span></td>
            <tr>
        </table>
        [@a.search]
            <tr>
                <td><label for="msearch">搜索框:</label></td>
                <td><input id="msearch" type="text" name="search" style="height:18px;"/></td> <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td><label for="mmajor">专业:</label></td>
                <td><select id="mmajor" name="major">
                    <option value="">全部</option>
                    [#list majors?keys as major]
                    <option value="${major}">${majors[major]}</option>
                    [/#list]
                </select>
                </td> <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td><label for="mlevel">级别:</label></td>
                <td><select id="mlevel" name="level">
                    <option value="">全部</option>
                    [#list map?keys as level]
                    <option value="${level}">${map[level]}</option>
                    [/#list]
                   </select>
                </td> <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td><label for="mstage">挑战进度:</label></td>
                <td><select id="mstage" name="stage">
                    <option value="">全部</option>
                    <option value="1">申请中</option>
                    <option value="2">进行中</option>
                    <option value="5">即将开始</option>
                    <option value="3">已结束</option>
                    <option value="4">已取消</option>
                </select></td> <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td><label for="sstatus">状态:</label></td>
                <td>
                    <input type="radio" name="status" value=""/>全部
                    <input type="radio" name="status" value="0"/>冻结
                    <input type="radio" name="status" value="1"/>解冻
                </td>
            </tr>
        [/@a.search]
        
        [@a.datagrid]
        <input type="hidden" name="uid">
        <th data-options="field:'sn'">挑战编号</th>
        <th data-options="field:'pkusername'">挑战者</th>
        <th data-options="field:'pkusername2'">被挑战者</th>
        <th data-options="field:'name', width:15">挑战名称</th>
        <th data-options="field:'pktimeStr'">挑战时间</th>
        <th data-options="field:'pkstagename'">挑战进度</th>
        <th data-options="field:'ticketprice'">门票价格</th>
        <th data-options="field:'ticketsalecount'">已售票</th>
        <th data-options="field:'pkresult'">结果</th>
        <th data-options="field:'createtimeStr'">创建时间</th>
        [/@a.datagrid]
        
        [@a.toolbar canadd=false canedit=false candel=false]
            <a id="btnpkstageview" href="" class="easyui-linkbutton" data-options="iconCls:'icon-tip'" onclick="myButtonHandler(this, '')">查看挑战进度</a>
            <a id="btnpkview" href="" class="easyui-linkbutton" data-options="iconCls:'icon-tip'" onclick="myButtonHandler(this, '')">查看挑战观众</a>
            <a id="btnsetjudge" href="" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" data-crumbshow="{selector:'#setjudgesection', title:'指定裁判'}" onclick="editHandler(this, null, '#setjudgesection')">指定裁判</a>
            <a id="btnaddqqgroup" href="" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="myButtonHandler(this, '')">创建qq群会议室</a>
            <a id="btnsetticket" href="" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" data-crumbshow="{selector:'#setticketsection', title:'编辑门票信息'}" onclick="editHandler(this, null, '#setticketsection')">编辑门票信息</a>
            <a id="btnuploadfile" href="" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="myButtonHandler(this, '')">上传题目</a>
            <a id="btnsetpkover" href="" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="myButtonHandler(this, '')">结束挑战</a>
            <a id="btnsetpkresult" href="" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" data-crumbshow="{selector:'#setpkresultsection', title:'设定挑战结果'}" onclick="editHandler(this, null, '#setpkresultsection')">设定挑战结果</a>
            <a id="btnaddvideo" href="" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="myButtonHandler(this, '')">添加挑战视频</a>
            [@a.status/]
        [/@a.toolbar]
        
        [@a.tools /]
     </section> 
     
     [@a.editsection id="setpkresultsection" namesubmit="设定挑战结果" action="${base}${baseAdminPath}/${site}/ajax/${channel}/setresult"]
          <td><input type="hidden" name="uid"></td>
          <tr>
            <td>挑战名称:</td>
            <td><span set-key="name"></span></td>
          </tr>
          <tr>
            <td>挑战专业:</td>
            <td><span set-key="majorname"></span></td>
          </tr>
          <tr>
            <td>挑战者姓名:</td>
            <td><span set-key="pkusername"></span></td>
          </tr>
          <tr>
            <td>挑战者分数:</td>
            <td><input class="easyui-numberbox" precision="2" data-options="required:true" name="userscore" /></td>
          </tr>
          <tr>
            <td>被挑战者姓名:</td>
            <td><span set-key="pkusername2"></span></td>
          </tr>
          <tr>
            <td>被挑战者分数:</td>
            <td><input class="easyui-numberbox" precision="2" data-options="required:true"  name="userscore2" /></td>
          </tr>
          <tr>
            <td>结果说明:</td>
             <td>
                [@a.ckeditor name="desc"/]
             </td>
          </tr>
     [/@a.editsection]
     
     [@a.editsection id="setticketsection" namesubmit="确认门票信息" action="${base}${baseAdminPath}/${site}/ajax/${channel}/editticket"]
          <td><input type="hidden" name="uid"></td>
          <td><input type="hidden" name="confeuid"></td>
          <tr>
            <td>挑战名称:</td>
            <td><span set-key="name"></span></td>
          </tr>
          <tr>
            <td>挑战专业:</td>
            <td><span set-key="majorname"></span></td>
         </tr>
         <tr>
            <td>会议室名称:</td>
            <td><span set-key="conferencename"></span></td>
         </tr>
         <tr>
            <td>门票价格:</td>
            <td><input class="easyui-validatebox" required="true" name="ticketprice"></td>
         </tr>
         <tr>
            <td>门票数量:</td>
            <td><input class="easyui-validatebox" required="true" name="ticketcount"></td>
         </tr>
[#--         
         <tr>
            <td>会议室密码:</td>
            <td><input id="password" name="password" validType="length[6,32]" class="easyui-validatebox" required="true" type="password" value=""/></td>
         </tr>
         <tr>
            <td>确认会议室密码:</td>
            <td><input type="password" name="repassword" id="repassword" required="true" class="easyui-validatebox"  validType="equalTo['#password']" invalidMessage="两次输入密码不匹配"/></td>
         </tr>
--]
     [/@a.editsection]
     
     [@a.editsection id="setjudgesection" namesubmit="指定裁判" action="${base}${baseAdminPath}/${site}/ajax/${channel}/setjudgement"]
         <td><input type="hidden" name="uid"></td>
         <td><input type="hidden" name="judgementuid"></td>
         <td><input type="hidden" name="judgementuid2"></td>
         <td><input type="hidden" name="judgementchairuid"></td>
         <tr>
            <td>挑战名称:</td>
            <td><span set-key="name"></span></td>
            <td width="10%">
            <td>挑战专业:</td>
            <td><span set-key="majorname"></span></td>
         </tr>
         <tr>
            <td>挑战者裁判编号:</td>
            <td><span set-key="judgementsn"></span></td>
            <td width="10%">
            <td>挑战者裁判姓名:</td>
            <td><span set-key="judgementname"></span></td>
            <td width="10%">
            <td>选择挑战者裁判:</td>  
            <td>[@j.judgesearch name="setjudgement1"/]</td>
         </tr>
         <tr>
            <td>被挑战者裁判编号:</td>
            <td><span set-key="judgementsn2"></span></td>
            <td width="10%">
            <td>被挑战者裁判姓名:</td>
            <td><span set-key="judgementname2"></span></td>
            <td width="10%">
            <td>选择被挑战者裁判:</td>  
            <td>[@j.judgesearch name="setjudgement2"/]</td>
         </tr>
         <tr>
            <td>主裁判编号:</td>
            <td><span set-key="judgementchairsn"></span></td>
            <td width="10%">
            <td>主裁判姓名:</td>
            <td><span set-key="judgementchairname"></span></td>
            <td width="10%">
            <td>选择主裁判:</td>  
            <td>[@j.judgesearch name="setjudgement3"/]</td>
         </tr>
     [/@a.editsection]
     
     [@a.viewsection]
	    <tr>
            <td>挑战编号:</td><input id="viewuid" type="hidden" name="uid"/>
            <td><span set-key="sn"></span></td>
        </tr>
        <tr>
            <td>挑战名称:</td>
            <td><span set-key="name"></span></td>
        </tr>
        <tr>
            <td>挑战专业:</td>
            <td><span set-key="majorname"></span></td>
        </tr>
        <tr>
            <td>挑战级别:</td>
            <td><span set-key="levelname"></span></td>
        </tr>
        <tr>
            <td>挑战者:</td>
            <td><span set-key="pkusername"></span></td>
        </tr>
        <tr>
            <td>被挑战者:</td>
            <td><span set-key="pkusername2"></span></td>
        </tr>
        <tr>
            <td>主裁判:</td>
            <td><span set-key="judgementchairname"></span></td>
        </tr>
        <tr>
            <td>挑战者裁判:</td>
            <td><span set-key="judgementname"></span></td>
        </tr>
        <tr>
            <td>挑战者备选裁判:</td>
            <td><span set-key="vicepkjudgementname"></span></td>
        </tr>
        <tr>
            <td>被挑战者裁判:</td>
            <td><span set-key="judgementname2"></span></td>
        </tr>
        <tr>
            <td>被挑战者备选裁判:</td>
            <td><span set-key="vicepkjudgement2name"></span></td>
        </tr>
        <tr>
            <td>挑战时间:</td>
            <td><span set-key="pktimeStr"></span></td>
        </tr>
        <tr>
            <td>挑战费:</td>
            <td><span set-key="pkprice"></span></td>
        </tr>
        <tr>
            <td>挑战题目:</td>
            <td><a href="" id="adownload"><span set-key="filenameFix"></a></span></td>
        </tr>
         <tr>
            <td>QQ群会议室:</td>
            <td><span set-key="qqgroup"></span></td>
        </tr>
        <tr>
            <td>门票价格:</td>
            <td><span set-key="ticketprice"></span></td>
        </tr>
        <tr>
            <td>已售门票:</td>
            <td><span set-key="ticketsalecount"></span></td>
        </tr>
        <tr>
            <td>挑战进度:</td>
            <td><span set-key="pkstagename"></span></td>
        </tr>
        <tr>
            <td>挑战结果:</td>
            <td><span set-key="pkresult"></span></td>
        </tr>
        <tr>
            <td>挑战者分数:</td>
            <td><span set-key="userscore"></span></td>
        </tr>
        <tr>
            <td>被挑战者分数:</td>
            <td><span set-key="userscore2"></span></td>
        </tr>
    [/@a.viewsection]
    
    <form id="pkdetaildialogfrom" class="easyui-dialog" style="width:600px; height:300px; padding:10px 20px; " title="挑战明细列表" buttons="#dlg-status-btns" data-options="closed:true" >
		<div id="pkdetaillisttable">
		</div>
		<div id="dlg-status-btns">
			<a href="" class="easyui-linkbutton" data-options="iconCls:'icon-back'" onclick="statusdlg_cancelHandler(this, '#pkdetaildialogfrom')">返回</a>
		</div>
	</form>
	
	<form id="pkviewdialogfrom" class="easyui-dialog" style="width:600px; height:300px; padding:10px 20px; " title="挑战明细列表" buttons="#dlg-status-btns" data-options="closed:true" >
		<div id="pkviewlisttable">
		</div>
		<div id="dlg-status-btns">
			<a href="" class="easyui-linkbutton" data-options="iconCls:'icon-back'" onclick="statusdlg_cancelHandler(this, '#pkviewdialogfrom')">返回</a>
		</div>
	</form>
	
	<form id="addqqgroupdialogfrom" class="easyui-dialog" style="width:400px; height:70px; padding:10px 20px; " title="创建QQ会议室" buttons="#dlg-status-btns" data-options="closed:true" >
		<div id="addqqgrouptable">
		     <tr>
	            <td>qq群会议室号:</td>
	            <td><input name="qqgroup" placeholder="请输入已创建的会议室QQ群号码" class="easyui-validatebox" required="true"></td>
	        </tr> 
		</div>
		<div id="dlg-status-btns">
			<a href="" class="easyui-linkbutton" iconCls="icon-ok" onclick="addQQGroupDialogFromHandler(this ,'#addqqgroupdialogfrom')">确定</a>
			<a href="" class="easyui-linkbutton" iconCls="icon-cancel" onclick="statusdlg_cancelHandler(this, '#addqqgroupdialogfrom')">取消</a>
		</div>
	</form>
	
	<form id="uploadfiledialogfrom" class="easyui-dialog" style="width:400px; height:70px; padding:10px 20px; " title="上传挑战题目" buttons="#dlg-status-btns" data-options="closed:true" enctype="multipart/form-data"  method="post">
		<div id="uploadfiletable" style="margin-top:20px;">
		     <tr><input type="hidden" name="uid">
	            <td>挑战题目:</td>
	            <td><input class="easyui-filebox" type="file" required="true" name="filedata"></td>
	        </tr> 
		</div>
		<div id="dlg-status-btns">
			<a href="" class="easyui-linkbutton" iconCls="icon-ok" onclick="uploadFileDialogFromHandler(this ,'#uploadfiledialogfrom')">上传</a>
			<a href="" class="easyui-linkbutton" iconCls="icon-cancel" onclick="statusdlg_cancelHandler(this, '#uploadfiledialogfrom')">取消</a>
		</div>
	</form>

</body>
</html>
