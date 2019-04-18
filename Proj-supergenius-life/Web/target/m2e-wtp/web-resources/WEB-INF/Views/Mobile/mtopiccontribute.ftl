[#ftl]
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${title!'天才人生'}</title>
<link href="${basecss}/css/default/mobile/index.css" type="text/css" rel="stylesheet" />
<link href="${basecss}/css/mobile/default/contribute11.css" type="text/css" rel="stylesheet" />
<script src="https://open.web.meitu.com/sources/xiuxiu.js" type="text/javascript"></script>
<script type="text/javascript" src="${basejs}/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="${basejs}/js/libs3/swiper.jquery.min.js"></script>
<script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
<script type="text/javascript" src="${basejs}/js/weixinshare.js"></script>
<script type="text/javascript">
	var origin1 = '';
	 function preview(file) {
		  var prevDiv = document.getElementById('preview');
		 	var formData = new FormData();
			  formData.append("fileimg", $("#userface")[0].files[0]);
				  $.ajax({
					   url: '${base}/m/uploadimg',
					   type: 'post',
					   data: formData,
					   processData: false,
					   contentType: false,
					   success: function (msg) {
						origin1 = msg.split(",")[0];
					   $("#contributeimg").val(msg);
					   }
				  });
		  if (file.files && file.files[0]) {
			   var reader = new FileReader();
			   reader.onload = function (evt) {
			    prevDiv.innerHTML = '<img class="uploadimg"  src="' + evt.target.result + '" />';
			   }
		   reader.readAsDataURL(file.files[0]);
		  } else {
		   prevDiv.innerHTML = '<div class="uploadimg img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';
		  }
	 }
	    
</script>
<style type="text/css">
.questionBox .submitBtn {
  	margin-left: 6%;
    width: auto;
    padding: 2px 45px;
    height: auto;
    color:#fff;
}
.red{
	color:#f60325;
}
</style>
<script type="text/javascript">
<!--
$(function(){
	CKEDITOR.replace('content',{
        toolbar : 'MyToolbar1',
        imageUploadUrl : "${base}/ajax/upload/webdata/Lifecontribute/false/dropimg",
        filebrowserImageUploadUrl : "${base}/ajax/upload/webdata/Lifecontribute",
        filebrowserFlashUploadUrl : "${base}/ajax/ckeditor/flash"
    });
});
//-->
</script>
</head>
<body>
<div class="askQuestionBox questionBox" id="form">
    <form class="draft" action="${base}/m/my/topic/contribute" method="post" enctype="multipart/form-data">
		<label class="mb_30"><span class="red">*</span>标题</label>
        <input class="draft_input ml_35" type="text" id="txttitle" name="title" placeholder="一句话说明问题，并以句号结尾"/>
        <br>
        <label class="mb_30">封面图</label>
        <input id="userface" type="file" onchange="preview(this)" style="width: 62%;display:  inline-block;"><br>
        <input id="contributeimg" name="contributeimg" type="text" value="" class="hd"/>
        <div id="preview" style="margin-left: 30%;width: 100%;margin-bottom: 15px;"></div>
       <br>
       <label class="mb_30"><span class="red">*</span>话题描述</label>
       <div style="width:100%;display: inline-block;vertical-align: top; margin-bottom: 40px">
	    	<textarea class="draft_input2" id="content" name="content" ></textarea>
	   </div>
      <input type="button" class="draft_tg right cancelBtn submitBtn" value="取消"></input>
      <input type="button" class="draft_tg right submitBtn submitd" onclick="publish()" value="发布"></input>
      <input type="hidden" name="cid" value="${cid?c}"/>
    </form>
</div>
<script type="text/javascript">
    
   function publish(){
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
	    }else{
			$(".draft").submit();
		}
   }
</script>
</body>
</html>
