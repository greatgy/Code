[#ftl]
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>${title!'添加足迹'}</title>
	<link rel="stylesheet" href="${basecss}/css/mobile/default/index.css">
	<link href="${basecss}/css/mobile/map/style.css" type="text/css" rel="stylesheet">
	<script type="text/javascript" src="${basejs}/js/mobile/map/jquery.min.js"></script>
	<script type="text/javascript" src="${basejs}/js/mobile/comment-1.0.0.js"></script>
	<script src="https://open.web.meitu.com/sources/xiuxiu.js" type="text/javascript"></script>
	<script type="text/javascript" src="${basejs}/js/mobile/map/City_data.js"></script>
	<script type="text/javascript" src="${basejs}/js/mobile/map/areadata.js"></script>
	<script type="text/javascript" src="${basejs}/js/mobile/map/auto_area.js"></script>
	<script type="text/javascript" src="${basejs}/js/mobile/ckeditor/ckeditor.js"></script>
	<script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
	<script type="text/javascript" src="${basejs}/js/weixinshare.js"></script>
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
	        document.getElementById('city').value =  data.addressComponent.province;
	    }
	    
	    $(function(){
	    	CKEDITOR.replace('content',{
		        toolbar : 'MyToolbarLifeTravel',
		        imageUploadUrl : "${base}/ajax/upload/webdata/Lifecontribute/false/dropimg",
		        filebrowserImageUploadUrl : "${base}/ajax/upload/webdata/Lifecontribute",
		        filebrowserFlashUploadUrl : "${base}/ajax/ckeditor/flash"
    		});
	    })
	    
	    
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
					   
					   $("#contributeimg").html(origin1);
					   [#-- $("#filebox").html(msg); --]
					   }
				  });
		  if (file.files && file.files[0]) {
			   var reader = new FileReader();
			   reader.onload = function (evt) {
				$("#contributeimg").removeClass("hd");
			    prevDiv.innerHTML = '<img class="uploadimg"  src="' + evt.target.result + '" />';
			   }
		   reader.readAsDataURL(file.files[0]);
		  } else {
			$("#contributeimg").addClass("hd");
		   prevDiv.innerHTML = '<div class="uploadimg img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';
		  }
	 }
	</script>
</head>
<body>
	<div class="askQuestionBox questionBox" [#if me == ''] onclick= "isMember('${base}',${cid?c},${pcid});" [/#if]>
		<h3>发表旅行感想</h3>
		<form action="${base}/m/my/contribute" class="questionForm" method="post" enctype="multipart/form-data">
			<input type="hidden" name="cid" value="${cid?c}">
			<input type="hidden" name="type" value="1">
			<div>
				[#-- <label class="mb_30"><span class="state" style="margin-right:16px;">标题：</span></label>  --]
				<input type="text" name="title" id="txttitle" style="width:99%;" placeholder="请输入标题" >
			</div>
			[#-- <label class="state">封面图：</label> --]
			<div class="filebox" id="filebox">
				请选择封面图
		    	<input class="userface" id="userface" type="file" onchange="preview(this)" style="">
			</div>
        	<div id="preview" style="margin-left: 30%;width: 100%;margin-bottom: 15px;"></div>
        	<span id="contributeimg" name="contributeimg" class="hd" style="line-height: 25px;"></span>
			<div>
				[#-- <label class="mb_30"><span class="state">目的地：</span></label>  --]
				<input name="city" id="city" type="text" value="" class="area-danxuan" placeholder="请输入目的地"  data-value="" style="width:99%;"/>
				<input type="hidden" name="code" id="code" type="text" value=""/>
				<input type="hidden" name="province" id="province" type="text" value=""/>
			</div>
			<div>
				<label class="mb_30"><p class="state">旅行感想：</p></label>
				<textarea name="content"></textarea>
			</div>
			<a class="cancelBtn submitBtn" href="/dream/moreQuestion.html">取消</a>
			<a class="submitBtn" onclick="publish()">确认添加</a>
		</form>
	</div>
	<script type="text/javascript">
	    
	   function publish(){
	   		var title = $("#txttitle").val();
			if(title.length > 50 || 1 > title.length){
	   			$("#txttitle").focus();
				alert("请输入标题");
	   		} else{
				$(".questionForm").submit();
			}
	   }
	</script>
</body>
</html>
