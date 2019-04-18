[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Admin.ftl" as a]
[#assign security=JspTaglibs["http://www.springframework.org/security/tags"] /]
<html>
<head>
<title>全站文章管理</title>
<script src="http://open.web.meitu.com/sources/xiuxiu.js" type="text/javascript"></script>
<script type="text/javascript" src="${basejs}/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
	function showXiuXiu(){
	  /*第1个参数是加载编辑器div容器，第2个参数是编辑器类型，第3个参数是div容器宽，第4个参数是div容器高*/
	  xiuxiu.setLaunchVars("uploadBtnLabel", "上传");
	  xiuxiu.setLaunchVars("titleVisible", "0");
	  xiuxiu.setLaunchVars("cropPresets", "3:2");
	  xiuxiu.setLaunchVars("customMenu", ["edit"]);
	  xiuxiu.embedSWF("altcontent",1,"100%","400px");
	  xiuxiu.setUploadURL("${base}${baseAdminPath}/official/allarticle/uploadimg");//修改为您自己的图片上传接口
	  xiuxiu.setUploadType(2);
	  xiuxiu.setUploadDataFieldName("fileimg");
	  xiuxiu.onInit = function (){
	    xiuxiu.loadPhoto("");//默认待处理图片
	  }
	  xiuxiu.onUploadResponse = function (data){
	    $("#imgbox").html("<span id='altcontent'></span>");
	    $("#contributeimg").val(data);
	    var origin = data.split(",")[3];
	    $("#imgshow").removeClass("hd");
	    $("#imgshow_big").attr("src",'${webimg}' + origin);
	  }
	  xiuxiu.onClose = function (){
  		$("#imgbox").html("<span id='altcontent'></span>");
	  }
	}
</script>
<script type="text/javascript">
$(function() {
//修正点击add，图片上传按钮不回显的BUG
	$("#btnadd").click(function() {
       $("#imgshow_big").attr("src",'');
	   $("#imgshow").addClass("hd");
    });
	$(document).on('click', "input[class^='startupcatelogue']", function(){
		if($(this).attr("class").indexOf("29") != -1){
			$(".hdcatatory").addClass("hd");
		}else{
			$(".hdcatatory").removeClass("hd");
		}
		
     });
 });
/*重写add的表单提交方法*/ 
function submitHandler(obj) {

        if ($("#add_title").val().length < 1) {
        err("您未填写标题！");
        $("#add_title").focus();
        return false;
       };         
	   if ($("#imgshow_big").attr("src") == "") {
        err("您未上传图片！");
        return false;
       };
       if (CKEDITOR.instances.content.getData().trim().length < 1) {
        err("正文未填写！");
        return false;
       }; 
      var gupagecid = $("input[name='gupagecid']:checked").val();
      var careercid = $("input[name='careercid']:checked").val();
      var startupcid = $("input[name='startupcid']:checked").val();
      var enterprisercid = $("input[name='enterprisercid']:checked").val();
      var aicid = $("input[name='aicid']:checked").val();
	  var r=document.getElementsByName("cids");
	  var managernewsr=document.getElementsByName("managernewscids");
	  var entrepreneurr=document.getElementsByName("entrepreneurcids");
	  var moralnewscids=document.getElementsByName("moralnewscids");
	  var fin = [] ;
          for(var i=0;i<r.length;i++){
                 if(r[i].checked){
                     fin.push(r[i].value)
                 }
          }
	  var managernews = [] ;
          for(var i=0;i<managernewsr.length;i++){
                 if(managernewsr[i].checked){
                     managernews.push(managernewsr[i].value)
                 }
          }
	  var entrepreneur = [] ;
          for(var i=0;i<entrepreneurr.length;i++){
                 if(entrepreneurr[i].checked){
                     entrepreneur.push(entrepreneurr[i].value)
                 }
          }
	  var moralnews = [] ;
          for(var i=0;i<moralnewscids.length;i++){
                 if(moralnewscids[i].checked){
                     moralnews.push(moralnewscids[i].value)
                 }
          }

        if(gupagecid == undefined && careercid == undefined && startupcid == undefined && enterprisercid == undefined && aicid == undefined && fin.length == 0&& managernews.length == 0 && entrepreneur.length == 0&&moralnews.length==0){
            err("您必须选择一个模块！");
            return false;
            }
        if(fin.length != 0){ 
            $.ajax({
               type:"post",
               traditional : true,
               url:"${base}/supergeniusadmin/finance/ajax/financearticle/add",
               data:{title: $("input[name='title']").val(), labelcontent : $("input[name='labelcontent']").val(), author: $("input[name='author']").val(), publishtime : $("input[name='publishtime']").val(), content: CKEDITOR.instances.content.getData().trim(), summary : $("#summary").val() ,cids : fin, contributeimg : $("input[name='contributeimg']").val(), origin_radio :$("input[name='origin_radio']:checked").val(), origin :$("input[name='origin']").val(), type_radio : $("input[name='type_radio']:checked").val(), SEOkeywords: $("input[name='SEOkeywords']").val()},
               dataType:"json",
               });
        }
        if(managernews.length != 0){
            $.ajax({
               type:"post",
               traditional : true,
               url:"${base}/supergeniusadmin/managernews/ajax/managernewsarticle/add",
               data:{title: $("input[name='title']").val(), labelcontent : $("input[name='labelcontent']").val(), author: $("input[name='author']").val(), publishtime : $("input[name='publishtime']").val(), content: CKEDITOR.instances.content.getData().trim(), summary : $("#summary").val() ,cids : managernews, contributeimg : $("input[name='contributeimg']").val(), origin_radio :$("input[name='origin_radio']:checked").val(), origin :$("input[name='origin']").val(), type_radio : $("input[name='type_radio']:checked").val(), SEOkeywords: $("input[name='SEOkeywords']").val()},
               dataType:"json",
               });
        }
        if(moralnews.length != 0){
            $.ajax({
               type:"post",
               traditional : true,
               url:"${base}/supergeniusadmin/moralnews/ajax/moralnewsarticle/add",
               data:{title: $("input[name='title']").val(), labelcontent : $("input[name='labelcontent']").val(), author: $("input[name='author']").val(), publishtime : $("input[name='publishtime']").val(), content: CKEDITOR.instances.content.getData().trim(), summary : $("#summary").val() ,cids : moralnews, contributeimg : $("input[name='contributeimg']").val(), origin_radio :$("input[name='origin_radio']:checked").val(), origin :$("input[name='origin']").val(), type_radio : $("input[name='type_radio']:checked").val(), SEOkeywords: $("input[name='SEOkeywords']").val()},
               dataType:"json",
               });
        }
        if(entrepreneur.length != 0){
            $.ajax({
               type:"post",
               traditional : true,
               url:"${base}/supergeniusadmin/entrepreneur/ajax/entrepreneurarticle/add",
               data:{title: $("input[name='title']").val(), labelcontent : $("input[name='labelcontent']").val(), author: $("input[name='author']").val(), publishtime : $("input[name='publishtime']").val(), content: CKEDITOR.instances.content.getData().trim(), summary : $("#summary").val() ,cids : entrepreneur, contributeimg : $("input[name='contributeimg']").val(), origin_radio :$("input[name='origin_radio']:checked").val(), origin :$("input[name='origin']").val(), type_radio : $("input[name='type_radio']:checked").val(), SEOkeywords: $("input[name='SEOkeywords']").val()},
               dataType:"json",
               });
        }
        if(gupagecid != undefined){
             var gu = gupagecid;
             if(gu == 10){ gu = "gupagearticle"}
             if(gu == 11){ gu = "gupagenews"}
         $.ajax({
             type:"post",
             url:"${base}/supergeniusadmin/gupagearticle/ajax/"+gu+"/add",     
             data:{title: $("input[name='title']").val(), booktime : $("input[name='publishtime']").val(), author: $("input[name='author']").val(), summary : $("#summary").val(), content: CKEDITOR.instances.content.getData().trim(), contributeimg : $("input[name='contributeimg']").val(), type_radio : $("input[name='type_radio']:checked").val(), keywords: $("input[name='SEOkeywords']").val()},            
             dataType:"json",
             });
        }
         if(enterprisercid != undefined){
              $.ajax({
               type:"post",
               traditional : true,
               url:"${base}/supergeniusadmin/enterpriser/ajax/enterpriserarticle/add",     
               data:{cid : enterprisercid, title : $("input[name='title']").val(), booktime : $("input[name='publishtime']").val(), summary : $("#summary").val(), author: $("input[name='author']").val(), content: CKEDITOR.instances.content.getData().trim(), contributeimg : $("input[name='contributeimg']").val(), origin :$("input[name='origin']").val(), type_radio : $("input[name='type_radio']:checked").val(), keywords: $("input[name='SEOkeywords']").val()},            
               dataType:"json",
               });
         }
          if(careercid != undefined){
               $.ajax({
                 type:"post",
                 traditional : true,
                 url:"${base}/supergeniusadmin/career/ajax/careerarticle/add",     
                 data:{cataloguetype : careercid, labelcontent : $("input[name='labelcontent']").val(), title : $("input[name='title']").val(), booktime : $("input[name='publishtime']").val(), author: $("input[name='author']").val(), summary : $("#summary").val(), content: CKEDITOR.instances.content.getData().trim(), contributeimg : $("input[name='contributeimg']").val(), origin : $("input[name='origin']").val(), origin_radio :$("input[name='origin_radio']:checked").val(), type_radio : $("input[name='type_radio']:checked").val(), keywordscontent: $("input[name='SEOkeywords']").val()},            
                 dataType:"json",
                 });
          }
          if(startupcid != undefined){
                $.ajax({
                   type:"post",
                   traditional : true,
                   url:"${base}/supergeniusadmin/startup/ajax/startuparticle/add",     
                   data:{cataloguetype : startupcid, labelcontent : $("input[name='labelcontent']").val(), title : $("input[name='title']").val(), booktime : $("input[name='publishtime']").val(), author: $("input[name='author']").val(), summary : $("#summary").val(), content: CKEDITOR.instances.content.getData().trim(), contributeimg : $("input[name='contributeimg']").val(), origin : $("input[name='origin']").val(), origin_radio :$("input[name='origin_radio']:checked").val(), type_radio : $("input[name='type_radio']:checked").val(), keywords: $("input[name='SEOkeywords']").val(), isflash : $("input[name='isflash']:checked").val() },            
                   dataType:"json",
                });
           }
           if(aicid != undefined){
                $.ajax({
                   type:"post",
                   traditional : true,
                   url:"${base}/supergeniusadmin/ai/ajax/aiarticle/add",     
                   data:{cid : aicid, title : $("input[name='title']").val(), author: $("input[name='author']").val(), summary : $("#summary").val(), content: CKEDITOR.instances.content.getData().trim(), contributeimg : $("input[name='contributeimg']").val(), origin : $("input[name='origin']").val(), origin_radio :$("input[name='origin_radio']:checked").val(), type_radio : $("input[name='type_radio']:checked").val(), keywords: $("input[name='SEOkeywords']").val()},            
                   dataType:"json",
                });
           }
                $.messager.alert('添加成功 ','添加成功 请到指定页面查看','info',function(){
                });
                setTimeout(function(){
                    $(".messager-body").window('close');
                     window.location.reload();
                },1500);
}
</script> 
<style>
section:first-of-type table tr td:FIRST-CHILD {
    color: #999;
}
</style>	
</head>
<body>
  <section>
    <form id="formt" method= post >
	  <table class="ftable">
        <tr>
            <td>标题：</td>
            <td><input class="easyui-validatebox" type="text" name="title" data-options="required:true" id = "add_title" /></td>
        </tr>
        <br>
        <tr>
            <td>标题图：</td>
            [#-- <td class="addimgdata">[@a.imgupload multiple=false imgpath="${photopath}" name="imgdata"/]</td> --]
             <td>
	            <a id="upload" href="javascript:showXiuXiu()" class="title_3_a">点此上传</a><br>
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
            <td>SEO关键字：</td>
            <td><input class="easyui-validatebox" type="text" name="SEOkeywords" /></td>
        </tr>
        <tr>
            <td>标签：</td>
            <td><input class="easyui-validatebox" type="text" name="labelcontent" placeholder="添加多个请以空格隔开" /></td>
        </tr>
        <tr>
            <td>作者：</td>
            <td><input class="easyui-validatebox" name="author"/></td>
        </tr>
        <tr>
            <td>来源：</td>            
            <td><input class="easyui-validatebox" name="origin"/></td>
        </tr>
	    <tr>
            <td>摘要：</td>            
            <td>
            <textarea id = "summary" class="easyui-validatebox" name="summary" rows="5" cols="60"></textarea>
            </td>
        </tr>
        <tr>
            <td>是否原创：</td>            
            <td>
            <input name="origin_radio" type="radio" id="add_origin_yes" value="1"><label for="add_origin_yes">是</label>
            <input name="origin_radio" type="radio" id="add_origin_no" value="0" checked="checked"><label for="add_origin_no">否</label>
            </td>
        </tr>
        <tr>
            <td>类别：</td>            
            <td>
            <input name="type_radio" type="radio" id="add_type_article" value="0" checked="checked"><label for="add_type_article">文章</label>
            <input name="type_radio" type="radio" id="add_type_video" value="1"><label for="add_type_video">视频</label>
            <input name="type_radio" type="radio" id="add_type_photo" value="2"><label for="add_type_photo">图片</label>
            </td>
        </tr>
         <tr>
          <td>板块：</td>
         </tr> 
        [@security.authorize url="${base}${baseAdminPath}/enterpriser/enterpriserarticle"]
        <tr>
         <td>引资购商:</td>
            <td>
      	    <div class="channel">
      		  [#if enterprisercatalog??]
			    [#list enterprisercatalog as item] 
			    <input name="enterprisercid" type="radio"  id="catalogues_${item.cid}"  value="${item.cid}"><label for="add_catalogue${item.cid}" >${item.name}</label>
				[/#list]
				[/#if]
      	    </div>
            </td>
         </tr>
        [/@security.authorize] 
        [@security.authorize url="${base}${baseAdminPath}/gupagearticle/gupagearticle"]
         <tr>
            <td> 顾雏军专栏 :</td>
            <td>
      		 <div class="channel">
			    <input name="gupagecid" type="radio" class="catelogue add sign" id="add_catalogue" value="10"><label for="add_catalogue10" >个人文章</label>
			     <input name="gupagecid" type="radio" class="catelogue add sign" id="add_catalogue" value="11"><label for="add_catalogue11" >动态</label>
      		 </div>
            </td>
         </tr>
        [/@security.authorize]
         [@security.authorize url="${base}${baseAdminPath}/finance/financearticle"]
         <tr>
            <td>天才评论 :</td>
            <td>
      		 <div class="channel">
      		  [#if financecataloglist??]
			    [#list financecataloglist as item] 
			    [#if item.cid!=0]
			    <input name="cids" type="checkbox" class="catelogue add${item.cid} sign${item.pcid}" id="add_catalogue${item.cid}" value="${item.cid}"><label for="add_catalogue${item.cid}" >${item.name}</label>
				[/#if]
				[/#list]
				[/#if]
      		 </div>
            </td>
         </tr>
         [/@security.authorize]
         [@security.authorize url="${base}${baseAdminPath}/managernews/managernewsarticle"]
         <tr>
            <td>职业经理人培训 :</td>
            <td>
      		 <div class="channel">
      		  [#if managernewscataloglist??]
			    [#list managernewscataloglist as item]
			    [#if item.cid!=0]
			    <input name="managernewscids" type="checkbox" class="catelogue add${item.cid} sign${item.pcid}" id="add_catalogue1${item.cid}" value="${item.cid}"><label for="add_catalogue1${item.cid}" >${item.name}</label>
				[/#if]
				[/#list]
				[/#if]
      		 </div>
            </td>
         </tr>
         [/@security.authorize]
         [@security.authorize url="${base}${baseAdminPath}/entrepreneur/entrepreneurarticle"]
         <tr>
            <td>企业家培训 :</td>
            <td>
      		 <div class="channel">
      		  [#if entrepreneurcataloglist??]
			    [#list entrepreneurcataloglist as item]
			    [#if item.cid!=0]
			    <input name="entrepreneurcids" type="checkbox" class="catelogue add${item.cid} sign${item.pcid}" id="add_catalogue2${item.cid}" value="${item.cid}"><label for="add_catalogue2${item.cid}" >${item.name}</label>
				[/#if]
				[/#list]
				[/#if]
      		 </div>
            </td>
         </tr>
         [/@security.authorize]
         [@security.authorize url="${base}${baseAdminPath}/moralnews/moralnewsarticle"]
         <tr>
            <td>职业道德培训 :</td>
            <td>
      		 <div class="channel">
      		  [#if moralnewscataloglist??]
			    [#list moralnewscataloglist as item]
			    [#if item.cid!=0]
			    <input name="moralnewscids" type="radio" class="catelogue add${item.cid} sign${item.pcid}" id="add_catalogue21${item.cid}" value="${item.cid}"><label for="add_catalogue21${item.cid}" >${item.name}</label>
				[/#if]
				[/#list]
				[/#if]
      		 </div>
            </td>
         </tr>
         [/@security.authorize]
         [@security.authorize url="${base}${baseAdminPath}/career/careerarticle"]
         <tr>
            <td>天才职场:</td>
            <td>
      		 <div class="channel">
      		  [#if careercataloglist??]
			    [#list careercataloglist as item] 
			    <input name="careercid" type="radio" class="catelogue add${item.cid} sign${item.pcid}" id="add_catalogue3${item.cid}" value="${item.cid}"><label for="add_catalogue3${item.cid}" >${item.name}</label>
				[/#list]
				[/#if]
      		 </div>
            </td>
         </tr>
         [/@security.authorize]
         [@security.authorize url="${base}${baseAdminPath}/startup/startuparticle"]
         <tr>
            <td>天才创业 :</td>
            <td>
      		 <div class="channel">
      		  [#if startupcataloglist??]
			    [#list startupcataloglist as item] 
			    <input name="startupcid" type="radio" class="startupcatelogue add${item.cid} sign${item.pcid}" id="add_catalogue4${item.cid}" value="${item.cid}"><label for="add_catalogue4${item.cid}" >${item.name}</label>
				[/#list]
			  [/#if]
      		 </div>
            </td>
         </tr>
         <tr class="hdcatatory">
        	<td>是否快讯</td>
        	<td>
	        	<input class="catatory yeahflash" name="isflash" type="radio" id="type_flash_add" value="1"><label for="type_flash_add">是</label>
	            <input class="catatory notflash" name="isflash" type="radio" id="type_notflash_add" value="0" checked="checked"><label for="type_notflash_add">否</label>
            </td>
        </tr>
         [/@security.authorize]
        <br>
         [@security.authorize url="${base}${baseAdminPath}/ai/aiarticle"]
         <tr>
            <td>天才ai :</td>
            <td>
      		 <div class="channel">
			    <input name="aicid" type="radio" class="catelogue add sign" id="add_catalogue" value="11"><label for="add_catalogue" >AI资讯</label>
      		    <input name="aicid" type="radio" class="catelogue add sign" id="add_catalogue" value="12"><label for="add_catalogue" >AI风向标</label>
      		    <input name="aicid" type="radio" class="catelogue add sign" id="add_catalogue" value="13"><label for="add_catalogue" >AI评论</label>
      		    <input name="aicid" type="radio" class="catelogue add sign" id="add_catalogue" value="14"><label for="add_catalogue" >AI与资本</label>
      		 </div>
            </td>
         </tr>
         [/@security.authorize]
        <tr>
            <td>内容：</td>
            <td>
                [@a.ckeditor name="content" id="content" /]
            </td>
        </tr>
        <tr>
            <td>定时发布：</td>
            <td><input name="publishtime" type="text" editable="false" class="easyui-datetimebox" /></td>
        </tr>
      </table>
        <div class="formbtns">
          <a href="javascript:void(0)" class="easyui-linkbutton l-btn" data-options="iconCls:'icon-ok'" onclick="submitHandler(this)" group="" id="">提交</a>
          <a href="javascript:void(0)" class="easyui-linkbutton l-btn" data-options="iconCls:'icon-undo'" onclick="resetForm(this)" group="" id="">重置</a>
        </div>
   </form>
  </section>
</body>
</html>
