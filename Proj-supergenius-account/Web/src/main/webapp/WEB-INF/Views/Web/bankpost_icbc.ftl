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
	<form id="form" method="post" action="https://B2C.icbc.com.cn/servlet/ICBCINBSEBusinessServlet" >
		<input type="hidden" name="interfaceName" value="${interfaceName}" />
		<input type="hidden" name="interfaceVersion" value="${interfaceVersion}" />
		<input type="hidden" name="tranData" value="${tranData}" />
		<input type="hidden" name="merSignMsg" value="${merSignMsg}" />
		<input type="hidden" name="merCert" value="${merCert}" />
	</form>
</body>
</html>
[/#compress]