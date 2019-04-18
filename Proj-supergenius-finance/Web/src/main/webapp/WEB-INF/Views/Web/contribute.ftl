[#ftl]
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${title!'上传文章'}</title>
<link href="${basecss}/css/default/contribute11.css" type="text/css" rel="stylesheet" />
<script src="https://open.web.meitu.com/sources/xiuxiu.js" type="text/javascript"></script>
<script type="text/javascript" src="${basejs}/ckeditor/ckeditor.js"></script>
<script src="${basejs}/js/libs3/swiper.jquery.min.js"></script>
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
        imageUploadUrl : "${base}/ajax/upload/webdata/Financecontribute/false/dropimg",
        filebrowserImageUploadUrl : "${base}/ajax/upload/webdata/Financecontribute",
        filebrowserFlashUploadUrl : "${base}/ajax/ckeditor/flash"
    });
    [#if _id??]
    	$("#catalogues_"+${_id}).prop("checked", "checked");
    	$("#catalogue_"+${_id}).addClass("current").siblings().removeClass("current");
    [#else]
    	$("#catalogues_1").prop("checked", "checked");
    	$("#catalogue_1").addClass("current").siblings().removeClass("current");
    [/#if]
});
//-->
</script>
</head>
<body>
<div class="contribute_box" style="width:1000px; margin-bottom:100px;clear:both;">
    <div class="contribute_800" style="padding-bottom:30px">
    <form id="contribute" class="draft" action="${base}/my/member/contribute" method="post" enctype="multipart/form-data">
    	<div id="form">	
    		[#if !me??]
	    		<a class="memberbtn pop-up" >我是会员</a>
    		[/#if]
    		
    		<label class="mb_30"><span class="red">*</span>标题</label>
	        <input class="draft_input ml_35" type="text" id="txttitle" name="title" isnotempty="" placeholder="输入文章标题（1—50字）" maxlength="50" />
	        <img src="${baseimg}/imgs/default/err.png" style="width:32px;height:25px;margin-top:2px; margin-left:10px; margin-bottom:-8px;" class="hd" name="tittleErr">
	        <span style="color:red" class="hd" name="tittleErr">请输入标题</span>
            <br>
	        <label class="mb_30">作者</label>
	        <input class="draft_input ml_35" type="text" id="txtauthor" name="author" placeholder="输入文章作者" maxlength="50" />  
	        <br>
	        <label class="mb_30"><span class="red">*</span>封面图</label>
	    	<a id="upload" href="javascript:showXiuXiu()" class="title_3_a">点此上传</a><br>
	        <div id="imgbox">
	        	<span id="altcontent"></span>
	        </div>
	        <div class="hd" id="imgshow" style="margin-left: 175px;">
	        	<img id="imgshow_big" src=''/>
	        </div>
	        <input id="contributeimg" name="contributeimg" type="text" value="" class="hd"/>
	        
	        <label class="mb_30" >关键字</label>
	        <input class="draft_input ml_20" type="text" id="txtkeywords" name="tags" placeholder="多个标签之间以空格隔开">
            <br>
            <div>
	            <label class="mb_30" ><span class="red">*</span>类别</label>
	       		<input type="radio" name="kind" id="article" value="0" checked/><label for="article">文章</label>
	       		<input type="radio" name="kind" id="video" value="1" /><label for="video">视频</label>
	       		<input type="radio" name="kind" id="photo" value="2"/><label for="photo">图片</label>
	        </div>
	        <div>
	            <label class="mb_30" ><span class="red">*</span>是否原创</label>
	       		<input type="radio" name="isoriginal" id="isoriginal_yes" value="1"/><label for="article">是</label>
	       		<input type="radio" name="isoriginal" id="isoriginal_no" value="0" checked/><label for="video">否</label>
	        </div>
	        <div class="module">
	            <label class="mb_30" ><span class="red">*</span>所属板块</label>
	            [#if LeftCatalogue??]
	            	[#list LeftCatalogue as item]
	            		[#if item.cid>0]
	            			<input type="checkbox" name="cids" id="catalogues_${item.cid}" value="${item.cid}"/><label for="${item.cid}">${item.name}</label>
	            		[/#if]
	            	[/#list]
	            [/#if]
	         </div>
	       
	       <label class="mb_30"><span class="red">*</span>稿件正文</label>
	       <div style="width:700px;display: inline-block;vertical-align: top; margin-bottom: 40px">
		    	<textarea class="draft_input2" id="content" name="content"></textarea>
		   </div>
		   <img src="${baseimg}/imgs/default/err.png" style="width:32px;height:25px;margin-top:2px; margin-left:10px; margin-bottom:-8px;" class="hd" name="textErr">
		   <span style="color:red" class="hd" name="textErr">请输入正文</span>
		   <br>
		   
    	 [#if !me??]
	        <label class="mb_30 w90">验证码</label>
	        <input name="checkCode" class="checkCodeInput" id="checkCodeInput" type="text" style="height:35px;  background-color: rgb(250, 255, 189);"/>
			<img src="${base}/v.jpg" id="checkCode" onclick="myReload();" title="看不清？点击换一张" style="width:100px;height:35px;margin-top:2px; margin-left:5px; margin-bottom:-12px;">
			<img src="${baseimg}/imgs/default/err.png" style="width:32px;height:25px;margin-top:2px; margin-left:10px; margin-bottom:-8px;" class="hd" name="checkCodeErr">
			<span style="color:red" class="hd" name="checkCodeErr">验证码错误</span>
	        <br/>
	       
    	[/#if]
	      <input type="button" class="draft_tg right" onclick="publish(1)" value="确认发布"></input>
	    [#if me??]
	      <input type="button" class="draft_tg right" onclick="publish(0)" value="保存到草稿箱"></input>
	    [/#if]
	      <input type="hidden" name="type" value="0"/>
	    <span class="agree_check">
        <input name="ischeckbox" id="checkbox" type="checkbox" checked="false" value="0">
        <label class="agree_check1">我已阅读该规定并同意所有条款</label>
        <img src="${baseimg}/imgs/default/err.png" style="width:32px;height:25px;margin-top:2px; margin-left:10px; margin-bottom:-8px;" class="hd" name="checkboxErr">
        <span style="color:red" class="hd" name="checkboxErr">请同意条款</span>
    	</span>
      </div>
    </form>
  </div>
</div>
<script type="text/javascript">
	//重新加载验证码
	function myReload() {
        var url = $("#checkCode").attr("src").split('?');
        $("#checkCode").attr("src", getNoCachePath(url[0]));
    }
    
   function publish(type){
   		var code =  $("#checkCodeInput").val();
   		var content = CKEDITOR.instances.content.getData().trim();
   		var title = $("#txttitle").val();
   		$("[name='checkCodeErr']").hide();
   		$("[name='tittleErr']").hide();
   		$("[name='textErr']").hide();
   		$("[name='checkboxErr']").hide();
   		[#if !me??]
   			$.get("${base}/ajax/checkCode", {checkCode:code}, function(data){
    			    if (data.result) {
    			    	console.log("验证码正确");
    			    	if(title.length > 50 || 1 > title.length){
    			    		$("#txttitle").focus();
    			    		$("[name='tittleErr']").show();
				   		}else if($("#imgshow_big").attr("src")==""){
					    	alert("请上传封面图片");
					    }else if(content == ""){
					    	$("#content").focus();
					    	$("[name='textErr']").show();
					    }else if($("[name='ischeckbox']:checked").val() != 0) {
					    	$("#checkbox").focus();
					    	$("[name='checkboxErr']").show();
						}else{
							$("input[name='type']").val(type);
							$("#contribute").submit();
						}
    			    } else {
    			    	$("#checkCodeInput").focus();
    			    	$("[name='checkCodeErr']").show();
    			    }
    		});
    	[#else]
    		if(title.length > 50 || 1 > title.length){
	   			$("#txttitle").focus();
    			$("[name='tittleErr']").show();
	   		}else if($("#imgshow_big").attr("src")==""){
		    	alert("请上传封面图片");
		    }
		    else if(content == ""){
		    	$("#content").focus();
				$("[name='textErr']").show();
		    }else if($("[name='ischeckbox']:checked").val() != 0) {
				$("#checkbox").focus();
				$("[name='checkboxErr']").show();
			}else{
				$("input[name='type']").val(type);
				$("#contribute").submit();
			}
    	[/#if]
   }
</script>
</body>
</html>
