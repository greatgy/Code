[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Admin.ftl" as a]
<html>
<head>
<title>官网文章管理</title>
<script src="http://open.web.meitu.com/sources/xiuxiu.js" type="text/javascript"></script>
<script type="text/javascript" src="${basejs}/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
	function showXiuXiu(altcontent,imgbox,imgname,imgshow,imgshow_big){
	  /*第1个参数是加载编辑器div容器，第2个参数是编辑器类型，第3个参数是div容器宽，第4个参数是div容器高*/
	  xiuxiu.setLaunchVars("uploadBtnLabel", "上传");
	  xiuxiu.setLaunchVars("titleVisible", "0");
	  xiuxiu.setLaunchVars("cropPresets", "3:2");
	  xiuxiu.setLaunchVars("customMenu", ["edit"]);
	  xiuxiu.embedSWF(altcontent,1,"100%","400px");
	  xiuxiu.setUploadURL("${base}${baseAdminPath}/${site}/ajax/${channel}/uploadimg");//修改为您自己的图片上传接口
	  xiuxiu.setUploadType(2);
	  xiuxiu.setUploadDataFieldName("fileimg");
	  xiuxiu.onInit = function (){
	    xiuxiu.loadPhoto("");//默认待处理图片
	  }
	  xiuxiu.onUploadResponse = function (data){
		$(imgbox).html("<span id ="+ altcontent +"></span>");
		$(imgname).val(data);
		var origin = data.split(",")[3];
		$(imgshow).removeClass("hd");
		$(imgshow_big).attr("src",'${webimg}' + origin);
	  }
	  xiuxiu.onClose = function (){
  			$(imgbox).html("<span id ="+ altcontent +"></span>");
	  }
	}
	$(document).ready(function(){
		$("#addupload").on("click",function(){
			showXiuXiu("altcontent","#imgbox","#contributeimg","#imgshow","#imgshow_big");
		});
		$("#editupload").on("click",function(){
			showXiuXiu("altcontentedit","#imgboxedit","#contributeimgedit","#imgshowedit","#imgshow_bigedit");
		});
	})
	//重写addbtn
	 $("#btnadd").click(function() {
       $("#imgshow_big").attr("src",'');
	   $("#imgshow").addClass("hd");
    });
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
                <td><label for="sstatus">版块：</label></td>
                <td>
                    <input type="radio" name="channel" value=""/> 全部
                    <input type="radio" name="channel" value="0"/>职业经理人培训
                    <input type="radio" name="channel" value="1"/>企业家
                    <input type="radio" name="channel" value="2"/>智慧产业
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
            <th data-options="field:'channelName',width:100">版块</th>
            <th data-options="field:'statusName',width:100">状态</th>
            <th data-options="field:'createtimeStr',width:150">发布日期</th>
        [/@a.datagrid]
        [@a.toolbar]
            [@a.status/]
        [/@a.toolbar]
        [@a.tools /]
	</section>
	[@a.addsection]
        <tr>
            <td>标题：</td>
            <td><input class="easyui-validatebox" type="text" name="title" data-options="required:true" /></td>
        </tr>
        <tr>
            <td>板块：</td>
            <td>
      		 <div class="channel">
                [#list map?keys as mkey]
                    <input type="radio" id="rdchannel_${mkey_index}" name="channel" value="${mkey}"/> <label for="rdchannel_${mkey_index}">${map[mkey]}</label>&nbsp;
                [/#list]
      		 </div>
            </td>
        </tr>
        <tr>
            <td>标题图：</td>
            [#-- <td>[@a.imgupload multiple=false imgpath="/imgs/webdata/official/article" /]</td> --]
            <td>
	            <a id="addupload" href="javascript:void(0);" class="title_3_a">点此上传</a><br>
		        <div id="imgbox">
		        	<span id="altcontent"></span>
		        </div>
		        <div class="hd" id="imgshow" style="margin-left: 175px;">
		        	<img id="imgshow_big" src=''/>
		        </div>
		        <input id="contributeimg" name="contributeimg" type="text" value="" class="hd"/>
	        </td>
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
            <td><input type="hidden" name="imgbig" /></td>
            <td><input type="hidden" name="imglittle" /></td>
            <td><input type="hidden" name="imgmedium" /></td>
            <td><input type="hidden" name="imgoriginal" /></td>
        </tr>
        <tr>
            <td>标题：</td>
            <td><input class="easyui-validatebox" type="text" name="title" data-options="required:true" /></td>
        </tr>
        <tr>
            <td>板块：</td>
            <td>
	      		 <div class="channel">
	                [#list map?keys as mkey]
	                    <input type="radio" id="rdchannel_${mkey_index}" name="channel" value="${mkey}"/> <label for="rdchannel_${mkey_index}">${map[mkey]}</label>&nbsp;
	                [/#list]
	      		 </div>
            </td>
        </tr>
        <tr>
            <td>标题图：</td>
                <td>
	                [#-- [@a.imgupload multiple=false imgpath="/imgs/webdata/official/article" /] --]
	                 <a id="editupload" href="javascript:void(0);" class="title_3_a">点此上传</a><br>
			        <div id="imgboxedit">
			        	<span id="altcontentedit"></span>
			        </div>
			        <div class="" id="imgshowedit" style="margin-left: 175px;">
			        	<img id="imgshow_bigedit" src='' set-format="${webimg}{0}" set-key="imglittle"/>
			        </div>
			        <input id="contributeimgedit" name="contributeimg" type="text" value="" class="hd"/>
            	</td>
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
            <td>标题：<input type="hidden" name="uid" /></td>
            <td><span set-key="title"></span></td>
        </tr>
        <tr>
            <td>版块：</td>
            <td><span set-key="channelName"></span></td>
        </tr>
        <tr>
           <td>标题图：</td>
           <td>
                <img style="width:200px; height:200px" src="" set-format="${webimg}{0}" set-key="imglittle"/>
           </td>
        </tr>
        <tr>
            <td>内容：</td>
            <td><span set-key="content"></span></td>
        </tr>
    [/@a.viewsection]
	
</body>
</html>
