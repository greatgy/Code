[#ftl strip_whitespace = true]

[#--
 * expert.ftl
 * 专家查询控件,后台通过你给定的name的值获得选中的用户的uid
 * 如：[@u.expertsearch name="expertuid" /]:则后台通过expertuid获得该值
 * 默认name的值为expertuids1,expertuids2,expertuids3...依次递增
 * @author liubin
 * @since 1.0
--]
[#macro expertsearchsingle name = null]
[#if doexpertsearchindex??]
    [#assign doexpertsearchindex = doexpertsearchindex + 1 ]
[#else]
    [#assign doexpertsearchindex = 0 ]
[/#if]
[#local id = "expertsearchsingle" + doexpertsearchindex]
[#if name == null]
    [#local name = "experts" + doexpertsearchindex]
[/#if]
[#local expertsearchtable = "expertsearchsingletable" + doexpertsearchindex]
[#local expert = "expert" + doexpertsearchindex]
[#local stype = "stype" + doexpertsearchindex]
[#local slevel = "slevel" + doexpertsearchindex]
[#local smajor = "smajor" + doexpertsearchindex]
[#local sstatus = "sstatus" + doexpertsearchindex]
<script type="text/javascript">
<!--
    var initform;
    /*
     *弹出专家库
     */
    function ${id}searchExpert() {
        var level = getSelected(dg).degree;
        var major = getSelected(dg).major;
        $('.${slevel}').attr("value", level);
        $('.${smajor}').attr("value", major);
        $(".${stype}").empty();
       // $(".${smajor}").empty();
        $(".${stype}").append("<option value=''>全部</option>");
       // $(".${smajor}").append("<option value=''>全部</option>");
        $.get("${base}${baseAdminPath}/manager/ajax/expert/search_condition", function(data) {
            for(var item in data.type) {
                $(".${stype}").append("<option value="+item+">"+data.type[item]+"</option>");
            }
        //    for(var item in data.major) {
        //        $(".${smajor}").append("<option value="+item+">"+data.major[item]+"</option>");
        //    }
        });
       $('#${id}').window("open");
     //  $("#${expert}").attr("url", "${base}${baseAdminPath}/manager/ajax/expert/searchlist");
       ${id}mySearchHandler();
    }
    
    /**
    *保存选择的用户的uid到页面上去
    */
    function ${id}mySave() { 
        var uids = '';
        var names = '';
        var expertlist = $('#${expert}').datagrid('getSelections');
        if (expertlist.length > 0) {
            for (var i = 0; i < expertlist.length; i++) {
               uids += expertlist[i].uid + ",";
               names += expertlist[i].user.name + ",";
            }
            uids = uids.substring(0,uids.length-1);
            names = names.substring(0,names.length-1);
        }
        $('input[name="${name}"').val(uids);
        $('input[name="${name}name"').val(names);
        ${id}myClose();
    }
    
    function ${id}myClose() {
        $('#${id}').window("close");
    }
    
    /**
    *查询按钮
    */
    function ${id}mySearchHandler() {
        $('#${expert}').datagrid("load", $('#${expertsearchtable}').serializeObj());
    }
    
    /**
    *重置按钮
    */
    function ${id}myResetForm() {
        initform = $('#${expertsearchtable}').html();
        $('#${expertsearchtable}').html(initform);
        ${id}mySearchHandler();
    }
-->
</script>

<a href="#" class="easyui-linkbutton" onclick="${id}searchExpert()" data-options="iconCls:'icon-search'">选择</a>
<input name="${name}name" style="width:200px" readonly="readonly" >
<input name="${name}" type="hidden" />
<div id="${id}" class="easyui-window" title="选择专家" style="width:800px;height:600px" data-options="iconCls:'icon-save',modal:true,closed:true,closable:true">
    <div style="margin-left:70px">
        <table id='${expertsearchtable}' class="expertsearchtable">
            <tr>
                <td><label>关键字:</label></td>
                <td><input type="text" name="keyword"/></td>
                <td><label>类型：</label></td>
                <td><select class="${stype}" name="type">
                    <option value="">全部</option>
                    </select>
                </td>
               <td><input type="hidden" class="${slevel}" name="level"/></td>
               <td><input type="hidden" class="${smajor}" name="major"/></td>
               [#--   <td><label>专业:</label></td>
                <td>
                    <select class="${smajor}" name="major"> 
                        <option value="">全部</option>
                    </select>
                </td>
                --]
            </tr>
        </table>
    </div>
    <div class="formbtns" style="margin-left:-10px">
            <a href="" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="${id}mySearchHandler()">查询</a> 
            <a href="" class="easyui-linkbutton" data-options="iconCls:'icon-undo'" onclick="${id}myResetForm()">重置</a>
    </div>
    <table id="${expert}" name="${name}" class="easyui-datagrid" style="width:800px;height:380px;border:solid 1px red;" title="裁判选择列表" data-options="url:'${base}${baseAdminPath}/manager/ajax/expert/searchlist',fitColumns:true, method:'get', pagination:true, singleSelect:true">
        <thead>
            <tr>
                <th data-options="field:'usersn'">会员号</th>
                <th data-options="field:'username'">姓名</th>
                <th data-options="field:'sn'">专家号</th>
           	    <th data-options="field:'typeName'">专家类型</th>
            	<th data-options="field:'levelName'">专家级别</th>
            	<th data-options="field:'majorName'">专业</th>
            	<th data-options="field:'chaircount'">评判答辩场数</th>
            	<th data-options="field:'complaintcount'">被举报次数</th>
            	<th data-options="field:'statusName'">状态</th>
            </tr>
        </thead>
    </table>
    <div style="margin-left:320px; margin-top:20px; margin-bottom:30px">
        <a href="#" onclick="${id}mySave()" class="easyui-linkbutton">确定</a>
        <a href="#" onclick="${id}myClose()" class="easyui-linkbutton">取消</a>
    </div>
</div>
[/#macro]