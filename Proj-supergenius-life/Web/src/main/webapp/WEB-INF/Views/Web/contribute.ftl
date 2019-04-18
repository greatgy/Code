[#ftl]
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${title!'上传文章'}</title>
<link href="${basecss}/css/default/contribute11.css" type="text/css" rel="stylesheet" />
<script src="https://open.web.meitu.com/sources/xiuxiu.js" type="text/javascript"></script>
<script type="text/javascript" src="${basejs}/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="${basejs}/js/libs3/swiper.jquery.min.js"></script>
<script type="text/javascript">
	function showXiuXiu(){
	  /*第1个参数是加载编辑器div容器，第2个参数是编辑器类型，第3个参数是div容器宽，第4个参数是div容器高*/
	  xiuxiu.setLaunchVars("uploadBtnLabel", "上传");
	  xiuxiu.setLaunchVars("titleVisible", "0");
	  xiuxiu.setLaunchVars("cropPresets", "4:3");
	  xiuxiu.setLaunchVars("customMenu", ["edit"]);
	  xiuxiu.embedSWF("altcontent",1,"100%","400px");
	  xiuxiu.setUploadURL("${base}/uploadimg");//修改为您自己的图片上传接口
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
	    $("#imgshow_big").attr("src",'${webimg}' + origin)
	  }
	  xiuxiu.onClose = function (){
  		$("#imgbox").html("<span id='altcontent'></span>");
	  }
	}
</script>

<script type="text/javascript">
<!--
$(function(){
	CKEDITOR.replace('content',{
        toolbar : 'Full',
        imageUploadUrl : "${base}/ajax/upload/webdata/Lifecontribute/false/dropimg",
        filebrowserImageUploadUrl : "${base}/ajax/upload/webdata/Lifecontribute",
        filebrowserFlashUploadUrl : "${base}/ajax/ckeditor/flash"
    });
    [#if cid??]
    	$("#catalogues_"+${(cid)?c}).prop("checked", "checked");
    	if (${(cid)?c} == '8192') {
    		$("#catalogue").hide();
    	}
    [/#if]
    
    if($('input[name="cids"]:checked').length == 0){
    	$('input[name="cids"]:first').prop("checked", "checked");
    }
});
//-->
</script>
</head>
<body>
<div class="contribute_box" style="width:1000px; margin-bottom:100px;clear:both;">
    <div class="contribute_800" style="padding-bottom:30px">
    <form id="contribute" class="draft" action="${base}/my/contribute" method="post" enctype="multipart/form-data">
    	<div id="form">	
    		<label class="mb_30"><span class="red">*</span>标题</label>
	        <input class="draft_input ml_35" type="text" id="txttitle" name="title" placeholder="输入文章标题"/>
            <br>
	        <label class="mb_30">作者</label>
	        <input class="draft_input ml_35" type="text" id="txtauthor" name="author" placeholder="输入文章作者" maxlength="50" />  
	        <br>
	        <label class="mb_30">来源</label>
	        <input class="draft_input ml_35" type="text" id="txorigin" name="origin" placeholder="输入文章来源" maxlength="50" />  
	        <br>
	        <label class="mb_30"><span class="red">*</span>封面图</label>
	    	<a id="upload" href="javascript:showXiuXiu()" class="title_3_a">点此上传</a><br>
	        <div id="imgbox">
	        	<span id="altcontent"></span>
	        </div>
	        <div class="hd" id="imgshow" style="margin-left: 175px;margin-top: -32px;">
	        	<img id="imgshow_big" src=''/>
	        </div>
	        <input id="contributeimg" name="contributeimg" type="text" value="" class="hd"/>
	        <div>
	            <label class="mb_30" >是否原创</label>
	       		<input type="radio" name="isoriginal" id="isoriginal_yes" value="1" /><label for="isoriginal_yes">是</label>
	       		<input type="radio" name="isoriginal" id="isoriginal_no" value="0" checked/><label for="isoriginal_no">否</label>
	        </div>
            <div>
	            <label class="mb_30" >类别</label>
	       		<input type="radio" name="kind" id="article" value="0" checked/><label for="article">文章</label>
	       		<input type="radio" name="kind" id="video" value="1" /><label for="video">视频</label>
	       		<input type="radio" name="kind" id="photo" value="2"/><label for="photo">图片</label>
	        </div>
	        <div id="catalogue">
	            <label class="mb_30" >所属板块</label>
	            <div class="module">
	            	[#if catalogueList??]
	            		[#assign i = 0]
	            		[#list catalogueList as item]
				            <input type="checkbox" name="cids" id="catalogues_${(item.cid)?c}" value="${(item.cid)?c}"/><label for="catalogues_${(item.cid)?c}">${item.name}</label>
				            [#assign i = i +1]
	            		[/#list]
	            	[/#if]
		         </div>
	         </div>
	       <br>
	       <label class="mb_30"><span class="red">*</span>正文</label>
	       <div style="width:700px;display: inline-block;vertical-align: top; margin-bottom: 40px">
		    	<textarea class="draft_input2" id="content" name="content" ></textarea>
		   </div>
		   <br>
	      <input type="button" class="draft_tg right" onclick="publish(1)" value="确认发布"></input>
	      <input type="button" class="draft_tg right" onclick="publish(0)" value="保存到草稿箱"></input>
	      <input type="hidden" name="type" value="0"/>
	      <input type="hidden" name="cid" value="${(cid?c)!1}"/>
	    <span class="agree_check">
	        <input name="ischeckbox" id="checkbox" type="checkbox" checked="false" value="0">
	        <label class="agree_check1">我已阅读该规定并同意所有条款</label>
    	</span>
      </div>
    </form>
  </div>
</div>
<script type="text/javascript">
    
   function publish(type){
   		var content = CKEDITOR.instances.content.getData().trim();
   		var title = $("#txttitle").val();
		if(title.length > 50 || 1 > title.length){
   			$("#txttitle").focus();
			alert("请输入标题");
   		}else if($("#imgshow_big").attr("src")==""){
	    	alert("请上传封面图片");
	    }else if(content == ""){
	    	$("#content").focus();
			alert("投稿内容不能为空！");
	    }else if($("[name='ischeckbox']:checked").val() != 0) {
			$("#checkbox").focus();
			return false;
		}else if($('input[name="cids"]:checked').length == 0){
	    	$('input[name="cids"]:first').prop("checked", "checked");
	    }else{
			$("input[name='type']").val(type);
			$("#contribute").submit();
		}
   }
</script>
</body>
</html>
