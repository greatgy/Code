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
	<form id="form" method="post" action="${BankPaypalPostUrl}" >
		<input type="hidden" name="business" value="${business}" />
		<input type="hidden" name="cmd" value="${cmd}" />
		<input type="hidden" name="item_name" value="${item_name}" />
		<input type="hidden" name="item_number" value="${item_number}" />
		<input type="hidden" name="amount" value="${money}" />
		<input type="hidden" name="currency_code" value="${currency_code}" />
		<input type="hidden" name="bn" value="${bn}" />
		<input type="hidden" name="charset" value="utf-8"/>
		<input type="hidden" name="notify_url" value="${notify_url}" />
		<input type="hidden" name="return" value="${resulturl}" />
	</form>
</body>
</html>
[/#compress]