[#ftl]
<html>
<head>
<title></title>
<script type="text/javascript">
<!--
//-->
</script>
</head>
<body>
	<div id="wrapper">
		 <div class="container">
			<div class="tipscontent">
				[#if err??]
					<span class="word">
						[#if err.notexist??]
							银行没有返回结果
						[#elseif exception??]
							支付异常请联系管理员
						[/#if]
					</span>
				[#else]
					<span class="word">
						支付成功
					</span>
					<a href="${resulturl}">点击返回</a>
				[/#if]
			</div>
		</div>
	</div>
</body>
</html>
