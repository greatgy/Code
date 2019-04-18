[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Admin.ftl" as a]
<html>
<head>
<title>视频管理</title>
<script type="text/javascript">
<!--
$.extend($.fn.validatebox.defaults.rules, {  
    //数字  
    Number: {  
        validator: function (value) {  
            var reg =/^[0-9]*$/;  
            return reg.test(value);  
        },  
        message: '请输入正确的金额数字.'  
    }   
})
//-->
</script>
</head>
<body>
    <section>
        <table>
            <tr>
                <td>视频总数：</td>
                <td><span>${totalvideo}</span></td>
                <td>挑战视频数：</td>
                <td><span>${pkvideo}</span></td>
                <td>开题视频数：</td>
                <td><span>${openvideo}</span></td>
                <td>答辩视频数：</td>
                <td><span>${replyvideo}</span></td>
                <td>专家专题讲解视频数：</td>
                <td><span>${expertvideo}</span></td>
                <td>范例视频数：</td>
                <td><span>${specialvideo}</span></td>
                <td>其他视频数：</td>
                <td><span>${othervideo}</span></td>
            </tr>
        </table>
       
        [@a.search]
            <tr>
                <td><label>关键字:</label></td>
                <td><input type="text" name="keyword"/></td>
                <td><label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;类型:</label></td>
                <td><select name="channelfrom">
                    <option value="">全部</option>
                    [#assign chapter = channelfrom?keys]
                        [#list chapter as item]
                            <option value="${item}">${channelfrom[item]}</option>
                        [/#list]
                    </select>
                </td>
                <td><label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;专业:</label></td>
                <td>
                    <select name="major">
                        <option value="">全部</option>
                        [#assign major = majors?keys]
                            [#list major as item]
                                <option value="${item}">${majors[item]}</option>
                            [/#list]
                    </select>
                </td>
                <td><label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;是否推荐:</label></td>
                <td>
                    <select name="isrecommend">
                        <option value="">全部</option>
                        <option value="true">推荐</option>
                        <option value="false">不推荐</option>
                    </select>
                </td>
            </tr>
        [/@a.search]
        
        [@a.datagrid]
            <th data-options="field:'title', width:50">视频标题</th>
            <th data-options="field:'sn'">视频编号</th>
            <th data-options="field:'channelfromName'">视频类型</th>
            <th data-options="field:'majorName'">视频专业</th>
            <th data-options="field:'price'">视频价格</th>
            <th data-options="field:'createtimeStr'">上传时间</th>
            <th data-options="field:'buyUserCount'">购买人数</th>
        [/@a.datagrid]
       
        [@a.toolbar canadd=true canedit=true candel=false]
            [@a.status/]
            [@a.state nameenable="设置推荐" namedisable="取消推荐" statefield="isrecommend" actionenable="${base}${baseAdminPath}/${site}/ajax/${channel}/recommend/1" actiondisable="${base}${baseAdminPath}/${site}/ajax/${channel}/recommend/0"/]
        [/@a.toolbar]
        [@a.tools /]
    </section>
    [@a.viewsection]
        <tr><td>视频标题:</td><td><span set-key="title"></span></td></tr>
        <tr><td>视频编号:</td><td><span set-key="sn"></span></td></tr>
        <tr><td>视频类型:</td><td><span set-key="channelfromName"></span></td></tr>
        <tr><td>专业:</td><td><span set-key="majorName"></span></td></tr>
        <tr><td>视频价格:</td><td><span set-key="price"></span></td></tr>
        <tr><td>上传时间:</td><td><span set-key="createtimeStr"></span></td></tr>
        <tr><td>购买人:</td><td><span set-key="userNames"></span></td></tr>
        <tr><td>视频播放次数：</td><td><span set-key="playcount"></span></td></tr>
        <tr><td>视频播放:</td><td><span id="swfDiv" set-key="code"></span></td></tr>
    [/@a.viewsection]
    [@a.addsection canuploadfile=true]
        <tr><td><input type="hidden" id="refuid" name="refuid" /></td></tr>
        <tr><td>标题：</td><td><input class="easyui-validatebox" type="text" name="title" data-options="required:true" value="${videoname!''}" /></td></tr>
        <tr><td>类型：</td>
            <td>
                [#assign index = 0]
                [#list channelfrom?keys as mkey]
                   [#if index == 0 || index == 1 || index ==2]
                    <label><input type="radio" set-key="type" name="channelfrom" value="${mkey}" disabled/>${channelfrom[mkey]}</label>&nbsp;
                   [#elseif index == 3]
                    <label><input type="radio" set-key="type" name="channelfrom" checked="checked" value="${mkey}" />${channelfrom[mkey]}</label>&nbsp;
                   [#else]
                    <label><input type="radio" set-key="type" name="channelfrom" value="${mkey}" />${channelfrom[mkey]}</label>&nbsp;
                   [/#if]
                   [#assign index = index +1]
                [/#list]
            </td>
        </tr>
        <tr><td>专业：</td>
            <td>
                [#list majors?keys as mkey]
                   <label><input type="radio" set-key="type" name="major" value="${mkey}" />${majors[mkey]}</label>&nbsp;
                [/#list]
            </td>
        </tr>
        <tr><td>价格：</td><td><input class="easyui-validatebox" type="text" name="price" data-options="required:true,validType:'Number'" /></td></tr>
        <tr><td>封面图：</td><td>[@a.imgupload multiple=false imgpath="/imgs/webdata/manger/video" name="file" /]</td></tr>
        <tr><td>播放代码：</td><td><textarea rows=20 cols=80 name="code" data-options="required:true" class="textarea easyui-validatebox"></textarea></td></tr>
        <tr><td>视频简介：</td><td><textarea rows=4 cols=80 class="textarea easyui-validatebox" type="textarea" name="summary" data-options="required:true" ></textarea></td></tr>
        <tr><td>结果：</td><td><textarea rows=20 cols=180 class="textarea easyui-validatebox" type="textarea" name="desc" ></textarea></td></tr>
    [/@a.addsection]
    [@a.editsection]
    <tr><td><input type="hidden" name="uid" /></td></tr>
    <tr><td>标题：</td><td><input class="easyui-validatebox" type="text" name="title" data-options="required:true" /></td></tr>
    <tr><td>编号：</td><td><input class="easyui-validatebox" type="text" name="sn" data-options="required:true" /></td></tr>
    <tr><td>类型：</td>
        <td>
            [#list channelfrom?keys as mkey]
               <input type="radio" set-key="channelfrom" name="channelfrom" value="${mkey}" /><label>${channelfrom[mkey]}</label>&nbsp;
            [/#list]
        </td>
    </tr>
    <tr><td>专业：</td>
        <td>
            [#list majors?keys as mkey]
               <input type="radio" set-key="major" name="major" value="${mkey}" /><label>${majors[mkey]}</label>&nbsp;
            [/#list]
        </td>
    </tr>
    <tr><td>价格：</td><td><input class="easyui-validatebox" type="text" name="price" data-options="required:true,validType:'Number'" /></td></tr>
    <tr><td>封面图：</td>
        <td><img id="avatarbig" style="width:150px; height:150px" src="" set-format="${webimg}{0}" set-key="original"/>
             [@a.imgupload multiple=false imgpath="/imgs/webdata/manger/video" name="original"/]
        </td>
    </tr>
    <tr><td>播放代码：</td>
        <td><textarea rows=20 cols=80 name="code" data-options="required:true" class="textarea easyui-validatebox"></textarea></td>
    </tr>
    <tr><td>视频简介：</td>
        <td><textarea rows=3 cols=80 name="summary" data-options="required:true" class="textarea easyui-validatebox" ></textarea></td>
    </tr>
    <tr><td>结果：</td>
        <td><textarea rows=20 cols=180 name="desc" class="textarea easyui-validatebox" ></textarea></td>
    </tr>
    [/@a.editsection]
<script type="text/javascript">
<!--
$(function() {
    if(${reply} == "1") {
        //$(".searchform .formbtns").children("a").eq(0).click();
        $("#btnadd").click();
        $("#refuid").val("${refuid}");
        $("input[name='channelfrom']").eq(0).attr("disabled", true);
        $("input[name='channelfrom']").eq(3).attr("disabled", true);
        $("input[name='channelfrom']").eq(4).attr("disabled", true);
        $("input[name='channelfrom']").eq(5).attr("disabled", true);
        $("input[name='channelfrom']").eq(1).attr("checked", "checked");
        $("input[name='channelfrom']").eq(1).attr("disabled", false);
        $("input[name='channelfrom']").eq(2).attr("disabled", false);
        $("input[name='major']").each(function() {
            if($(this).val() == "${keyword}") {
                $(this).attr("checked", "checked");
            } else {
                $(this).attr("disabled", true);
            }
        });
        $("#addform .formbtns").children("a").eq(2).click(function() {
            $(".searchform .formbtns").children("a").eq(0).click();
        });
    } else if(${pk} == "1") {
        $("#btnadd").click();
        $("#refuid").val("${refuid}");
        $("input[name='channelfrom']").eq(0).attr("checked", "checked");
        $("input[name='channelfrom']").each(function() {
            $(this).attr("disabled", true);
        });
        $("input[name='channelfrom']").eq(0).attr("disabled", false);
        $("input[name='major']").each(function() {
            if($(this).val() == "${keyword}") {
                $(this).attr("checked", "checked");
            } else {
                $(this).attr("disabled", true);
            }
        });
    }
    
    $("#btnadd").click(function() {
         $("input[name='channelfrom']").each(function() {
            $(this).attr("disabled", false);
        });
        $("input[name='channelfrom']").eq(0).attr("disabled", true);
        $("input[name='channelfrom']").eq(1).attr("disabled", true);
        $("input[name='channelfrom']").eq(2).attr("disabled", true);
        $("input[name='channelfrom']").eq(3).attr("checked", true);
        $("input[name='major']").each(function() {
            $(this).attr("disabled", false);
        });
        $("input[name='major']").eq(0).attr("checked", true);
    });
    
})
//-->
</script>
</body>
</html>