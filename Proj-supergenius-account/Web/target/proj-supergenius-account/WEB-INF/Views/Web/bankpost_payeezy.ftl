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
	<form id="form" method="post" action="${BankPayeezyPostUrl}" >
		<input type="hidden" name="x_login" value="${x_login}" />
		<input type="hidden" name="x_amount" value="${x_amount}" />
		<input type="hidden" name="x_fp_sequence" value="${x_fp_sequence}" />
		<input type="hidden" name="x_fp_timestamp" value="${x_fp_timestamp}" />
		<input type="hidden" name="x_fp_hash" value="${x_fp_hash}" />
		<input type="hidden" name="x_show_form" value="PAYMENT_FORM" />
		<input type="hidden" name="Checkout" value="submit"/>
		<input type="hidden" name="charset" value="utf-8"/>
		<input type="hidden" name="x_relay_url" value="${x_relay_url}"/>
		<input type="hidden" name="x_relay_response" value="TRUE"/>
		<input type="hidden" name="merchant_cookie_1" value="${merchant_cookie_1}"/>
	</form>
</body>
</html>
[/#compress]