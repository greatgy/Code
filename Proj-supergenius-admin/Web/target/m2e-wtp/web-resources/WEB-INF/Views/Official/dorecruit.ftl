[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Admin.ftl" as a]
<html>
<head>
<title>招聘信息管理</title>
<script type="text/javascript" src="${basejs}/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="${basejs}/js/libs/validator-1.0.0.js"></script>
<script type="text/javascript">
</script> 	
</head>
<body>
    <section>
        [@a.search]
            <tr>
                <td><label for="stitle">标题：</label></td>
                <td><input id="stitle" type="text" name="title" /></td>
                <td><label for="sstatus">状态：</label></td>
                <td>
                    <input type="radio" name="status" value=""/> 全部
                    <input type="radio" name="status" value="1"/>正常
                    <input type="radio" name="status" value="0"/>冻结
                </td>
            </tr>
            <tr>
                <td><label for="sstatus">岗位类别：</label></td>
                <td>
                    <input type="radio" name="type" value=""/> 全部
                    <input type="radio" name="type" value="1"/>开发
                    <input type="radio" name="type" value="0"/>产品
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
            <th data-options="field:'title',width:500">标题</th>
            <th data-options="field:'typeName',width:100">岗位类别</th>
            <th data-options="field:'address',width:100">工作地址</th>
            <th data-options="field:'statusName',width:100">状态</th>
            <th data-options="field:'createtimeStr',width:150">发布日期</th>
        [/@a.datagrid]
        [@a.toolbar]
            [@a.status/]
            [@a.state statefield="istop" actionenable="${base}${baseAdminPath}/official/ajax/recruit/istop/1" actiondisable="${base}${baseAdminPath}/official/ajax/recruit/istop/0" namedisable="取消置顶" nameenable="置顶"/]
        [/@a.toolbar]
        [@a.tools /]
	</section>
	[@a.addsection]
        <tr>
            <td>标题：</td>
            <td><input class="easyui-validatebox" type="text" name="title" validtype="length[1,13]" invalidMessage="有效长度1-13" data-options="required:true" /></td>
        </tr>
        <tr>
            <td>岗位类别：</td>
            <td>
             <div class="channel">
                 <select id="rdchannel_${mkey_index}" class="easyui-combobox" name="type" data-options="panelHeight:100" style="width:100px;">
                    <option value="">全部</option>
                    [#list map?keys as mkey]
                       <option id="rdchannel_${mkey_index}" name="type" value="${mkey}">
                            <label for="rdchannel_${mkey_index}">${map[mkey]}</label>
                        </option>
                    [/#list]
                </select>
             </div>
            </td>
        </tr>
        <tr>
            <td>工作地址：</td>
            <td><input class="easyui-validatebox" type="text" name="address" data-options="required:true" /></td>
        </tr>
        <tr>
            <td>内容：</td>
            <td>
                [@a.ckeditor name="content"/]
            </td>
        </tr>
    [/@a.addsection]
    
    [@a.editsection]
        <tr>
            <td><input type="hidden" name="uid" /></td>
            <td><input type="hidden" name="adminuid" /></td>
        </tr>
        <tr>
            <td>标题：</td>
            <td><input class="easyui-validatebox" type="text" name="title" data-options="required:true" /></td>
        </tr>
        <tr>
            <td>岗位类别：</td>
            <td>
             <div class="channel">
                 <select id="rdchannel_${mkey_index} class="easyui-combobox" name="type" data-options="panelHeight:100" style="width:100px;">
                    <option value="">全部</option>
                    [#list map?keys as mkey]
                       <option id="rdchannel_${mkey_index}" name="type" value="${mkey}">
                            <label for="rdchannel_${mkey_index}">${map[mkey]}</label>
                        </option>
                    [/#list]
                </select>
             </div>
            </td>
        </tr>
        <tr>
            <td>工作地址：</td>
            <td><input class="easyui-validatebox" type="text" name="address" data-options="required:true" /></td>
        </tr>
        <tr>
            <td>内容：</td>
            <td>
                [@a.ckeditor name="content"/]
            </td>
        </tr>
    [/@a.editsection]
    
    [@a.viewsection]
        <tr>
            <td>标题：</td>
            <td><span set-key="title"></span></td>
        </tr>
        <tr>
            <td>岗位类别：</td>
            <td><span set-key="typeName"></span></td>
        </tr>
        <tr>
            <td>工作地址：</td>
            <td><span set-key="address"></span></td>
        </tr>
        <tr>
            <td>内容：</td>
            <td><span set-key="content"></span></td>
        </tr>
    [/@a.viewsection]
</body>
</html>
