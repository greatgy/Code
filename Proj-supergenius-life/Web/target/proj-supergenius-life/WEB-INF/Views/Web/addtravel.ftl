[#ftl]
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>${title!'添加足迹'}</title>
	<link rel="stylesheet" href="${basecss}/css/default/index.css">
	<link href="${basecss}/css/map/style.css" type="text/css" rel="stylesheet">
	<script type="text/javascript" src="${basejs}/js/pages/map/jquery.min.js"></script>
	<script src="https://open.web.meitu.com/sources/xiuxiu.js" type="text/javascript"></script>
	<script type="text/javascript" src="${basejs}/js/pages/map/City_data.js"></script>
	<script type="text/javascript" src="${basejs}/js/pages/map/areadata.js"></script>
	<script type="text/javascript" src="${basejs}/js/pages/map/auto_area.js"></script>
	<script type="text/javascript" src="${basejs}/ckeditor/ckeditor.js"></script>
	<!--此插件为高德地图api,key值为开发者账号的key-->
	<script type="text/javascript" src="https://webapi.amap.com/maps?v=1.4.6&key=9d40cbd45e7f26dc0c0226329e910fd4"></script>
    <script type="text/javascript" src="https://cache.amap.com/lbs/static/addToolbar.js"></script>
	<script type="text/javascript">
	/***************************************
	由于Chrome、IOS10等已不再支持非安全域的浏览器定位请求，为保证定位成功率和精度，请尽快升级您的站点到HTTPS。
	***************************************/
	    var map, geolocation;
	    //加载地图，调用浏览器定位服务
	    map = new AMap.Map('', {
	        resizeEnable: true
	    });
	    map.plugin('AMap.Geolocation', function() {
	        geolocation = new AMap.Geolocation({
	            enableHighAccuracy: true,//是否使用高精度定位，默认:true
	            timeout: 10000,          //超过10秒后停止定位，默认：无穷大
	            buttonOffset: new AMap.Pixel(10, 20),//定位按钮与设置的停靠位置的偏移量，默认：Pixel(10, 20)
	            zoomToAccuracy: true,      //定位成功后调整地图视野范围使定位位置及精度范围视野内可见，默认：false
	            buttonPosition:'RB'
	        });
	        map.addControl(geolocation);
	        geolocation.getCurrentPosition();
	        AMap.event.addListener(geolocation, 'complete', onComplete);//返回定位信息
	    });
	    //解析定位结果
	    function onComplete(data) {
	    	var province = data.addressComponent.province;
	    	if(province.substr(province.length-1,1) == "市") {
	    		province = province.substring(0,province.length - 1);
	    	}
	        document.getElementById('city').value = province;
	    }
	    
	    $(function(){
	    	CKEDITOR.replace('content',{
		        toolbar : 'MyToolbarLifeTravel',
		        imageUploadUrl : "${base}/ajax/upload/webdata/Lifecontribute/false/dropimg",
		        filebrowserImageUploadUrl : "${base}/ajax/upload/webdata/Lifecontribute",
		        filebrowserFlashUploadUrl : "${base}/ajax/ckeditor/flash"
    });
	    })
	    
	    
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
</head>
<body>
	<div class="askQuestionBox questionBox [#if me == ''] pop-up [/#if]">
		<h3>发表旅行感想</h3>
		<form action="${base}/my/contribute" class="questionForm" method="post" enctype="multipart/form-data">
			<input type="hidden" name="cid" value="${cid?c}">
			<input type="hidden" name="type" value="1">
			<div>
				<label class="mb_30"><span class="state" style="margin-right:16px;">标题：</span></label>
				<input type="text" name="title" id="txttitle" style="width:91%;">
			</div>
			<label class="state">封面图：</label>
	    	<a id="upload" [#if me??]href="javascript:showXiuXiu()"[/#if] class="[#if me == ''] pop-up [/#if]title_3_a">点此上传</a><br>
	        <div id="imgbox">
	        	<span id="altcontent"></span>
	        </div>
	        <div class="hd" id="imgshow" style="margin-left: 175px;">
	        	<img id="imgshow_big" src=''/>
	        </div>
	        <input id="contributeimg" name="contributeimg" type="text" value="" class="hd"/>
	        <br/>
			<div>
				<label class="mb_30"><span class="state">目的地：</span></label>
				<input name="city" id="city" type="text" value="" class="area-danxuan"  data-value="" style="width:91%;"/>
				<input type="hidden" name="code" id="code" type="text" value=""/>
				<input type="hidden" name="province" id="province" type="text" value=""/>
			</div>
			<div>
				<label class="mb_30"><p class="state">旅行感想</p></label>
				<textarea name="content"></textarea>
			</div>
			<a class="cancelBtn submitBtn" href="/dream/moreQuestion.html">取消</a>
			<a class="submitBtn [#if me == ''] pop-up [/#if]" [#if me??]onclick="publish()"[/#if]>确认添加</a>
		</form>
	</div>
	<script type="text/javascript">
	    
	   function publish(){
	   		var content = CKEDITOR.instances.content.getData().trim();
	   		var title = $("#txttitle").val();
			if(title.length > 50 || 1 > title.length){
	   			$("#txttitle").focus();
				alert("请输入标题");
	   		} else if($("#imgshow_big").attr("src")==""){
		    	alert("请上传封面图片");
		    }else if(content == ""){
		    	$("#content").focus();
				alert("投稿内容不能为空！");
		    }else{
				$(".questionForm").submit();
			}
	   }
	</script>
</body>
</html>