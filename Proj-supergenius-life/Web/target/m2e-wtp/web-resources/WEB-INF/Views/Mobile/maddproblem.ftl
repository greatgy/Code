[#ftl]
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>发布问题</title>
	<meta name="Keywords" content="天才人生，发布问题" />
	<meta name="Description" content="天才人生，发布问题" />
	<link rel="stylesheet" href="${basecss}/css/mobile/default/index.css">
	<script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
	<script type="text/javascript" src="${basejs}/js/mobile/comment-1.0.0.js"></script>
    <script type="text/javascript" src="${basejs}/js/weixinshare.js"></script>
</head>
<body>
	<div class="askQuestionBox questionBox" [#if me == ''] onclick= "isMember('${base}',${cid?c},${pcid});" [/#if] >
		<h3>提问</h3>
		<form action="${base}/m/problem/addproblem" class="questionForm" method="post" enctype="multipart/form-data">
			<input type="hidden" name="cid" value="${cid?c}">
			<input type="text" placeholder="一句话说明问题，并以句号结尾" name="title" id="txttitle">
			[#-- <p class="state">问题说明（可选）</p> --]
			<textarea placeholder="问题描述越清楚，越能带来专业的回答" name="content"></textarea>
			[#if cid?c != 1073741824]
				<div class="phidename">
					<input type="checkbox" id="hideName" name="isnick"/>
				    <label for="hideName">匿名</label>
				</div>
			[/#if]
			<a class="cancelBtn submitBtn" href="/dream/moreQuestion.html">取消</a>
			<a class="submitBtn"  onclick="publish()">发布提问</a>
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