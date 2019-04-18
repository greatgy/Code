[#ftl]
<html>
<head>
<script src="http://res.wx.qq.com/connect/zh_CN/htmledition/js/wxLogin.js"></script>
<script type="text/javascript">
$(document).ready(function()
{
    var obj = new WxLogin
    ({
        id:"QRcode",
        appid: "wx7ad782443f7fb272",
        scope: "snsapi_login",
        redirect_uri:"http%3a%2f%2f218.247.144.127%3a8097%2fafterlogin%2fwx" ,
        state: "",
        style: "black",        
    });
});
</script>
</head>
<body>
	<div class="container">
		<div id="QRcode"></div>
	</div>
</body>
</html>
