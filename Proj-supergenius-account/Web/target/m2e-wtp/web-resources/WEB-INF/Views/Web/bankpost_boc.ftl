[#ftl]
[#compress]
<html>
<head>
<title></title>
<style type="text/css" media="screen">
</style>
<script type="text/javascript">
<!--
	window.onload = function(){
		document.getElementById("form").submit();
	}
//-->
</script>
</head>
<body>

[#if err??]
	${err.result}
[#else]
	<form id="form" method="post" action="${bankpostbocurl}">
		<input type="hidden" name="merchantNo" value="${merchantNo}" />
		<input type="hidden" name="payType" value="${payType}" />
		<input type="hidden" name="orderNo" value="${orderNo}" />
		<input type="hidden" name="curCode" value=${curCode} />
		<input type="hidden" name="orderAmount" value="${orderAmount}" />
		<input type="hidden" name="orderTime" value="${orderTime}" />
		<input type="hidden" name="orderNote" value="${orderNote}" />
		<input type="hidden" name="orderUrl" value="${orderUrl}" />
		<input type="hidden" name="orderTimeoutDate" value="${orderTimeoutDate}" />
		<input type="hidden" name="signData" value="${signData}" />
	</form>
[/#if]
</body>
</html>
[/#compress]