[#ftl]
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${title!'天才人生'}</title>
<link href="${basecss}/css/default/index.css" type="text/css" rel="stylesheet" />
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
<style type="text/css">
.questionBox .submitBtn {
    margin-right: 25%;
    width: auto;
    padding: 2px 45px;
    height: auto;
}
.questionBox .submitd {
  	margin-left: 18%;
    margin-right: 5%;
    color:#fff;
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
<div class="askQuestionBox questionBox">
    <form class="draft" action="${base}/my/topic/contribute" method="post" enctype="multipart/form-data">
		<label class="mb_30">标题</label>
        <input class="draft_input ml_35" type="text" id="txttitle" name="title" placeholder="一句话说明问题，并以句号结尾"/>
        <br>
        <label class="mb_30">封面图</label>
    	<a id="upload" href="javascript:showXiuXiu()" class="title_3_a">点此上传</a><br>
        <div id="imgbox">
        	<span id="altcontent"></span>
        </div>
        <div class="hd" id="imgshow" style="margin-left: 175px;">
        	<img id="imgshow_big" src=''/>
        </div>
        <input id="contributeimg" name="contributeimg" type="text" value="" class="hd"/>
       <br>
       <label class="mb_30">话题描述</label>
       <div style="width:700px;display: inline-block;vertical-align: top; margin-bottom: 40px">
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