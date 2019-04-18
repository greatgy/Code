[#ftl]
<html>
<head>
	<title>会员注册--支付会员费提示</title>
	<meta name="Keywords" content="会员中心，支付会员费提示" />
	<meta name="Description" content="会员中心，支付会员费提示" />
	<link rel="stylesheet" type="text/css" href="${basecss}/css/default/register.css">
   <script type="text/javascript" src="${basejs}/js/libs/validator-1.0.0.js"></script>
 <script>
 <!--
   function chkProtocolValid(){ 
	    return $("#chkProtocolValid:checked").length == 1;
	}
//-->
</script>
</head>
<body>
		<div class="container">
			<div class="main">
				<div class="topcommon">
					<img src="${baseimg}/imgs/default/findpw01.png" alt="" />
					<h2>从这里，找到更好的自己！</h2>
				</div>
				<div class="midcommon">
					<div class="logotop">
						<img src="${baseimg}/imgs/default/login_logo.png" alt="" />
						会员注册
					</div>
					<div class="registercontainer">
						<div class="steps">
							<div class="stepitem curstepitem">
								<span class="rectbg">1</span>
								<span>填写账户信息</span>
							</div>
							<div class="linebg redlinebg"></div>
							<div class="stepitem curstepitem">
								<span class="rectbg">2</span>
								<span>验证注册邮箱</span>
							</div>
							<div class="linebg redlinebg"></div>
							<div class="stepitem curstepitem">
								<span class="rectbg">3</span>
								<span>基本信息</span>
							</div>
							<div class="linebg redlinebg"></div>
							<div class="stepitem curstepitem">
								<span class="rectbg">4</span>
								<span>支付会员费</span>
							</div>
						</div>
						<div class="registercontmiddle">
								<div class="tipnumbers">
									<div id="tip0" class="tip1">
										<div class="blueblock">Tip1</div>
										<div class="tip1container">
											<div class="tip1content">
												<h3>缴费确认提示</h3>
												<p>非常感谢您对本网站的厚爱和信任，但本网站温馨提醒您：请认真阅读<a href="${base}/userrule" target="_blank">《用户协议》</a>及<a href="${base}/legal" target="_blank">《法律声明》</a>，以明确本网站所能提供的有限的服务内容。</p>
												<div class="check">
										        <form id="form" action="${base}/pay_caution02/${(user.uid)!}">
												<input name="box" id="chkProtocolValid" customvalid="chkProtocolValid()|充值前必须同意注册条款"  type="checkbox" checked="checked" >
													我已阅读并同意《用户协议》、《法律声明》等相关规定
												</div>
											</div>
										</div>
									</div>
									<div id="tip1" class="tip1 tip2 hd">
										<div class="blueblock">Tip2</div>
										<div class="tip1container">
											<div class="tip1content">
												<h3>缴费确认提示</h3>
												<p>您所缴纳的会员费，将在任何情况下都不予以退款，请确认是否付款！</p>
											</div>
										</div>
									</div>
									<div id="tip2" class="tip1 tip3 hd">
										<div class="blueblock">Tip3</div>
										<div class="tip1container">
											<div class="tip1content">
												<h3>缴费确认提示</h3>
												<p>本网站不能保证其内容和服务能让您完全满意，也不能保证所有会员能顺利完成 相关培训直至拿到学位证书</p>
											</div>
										</div>
									</div>
								</div>
							</form>
							<div class="buttons">
								<button class="prevpage">上一步</button>
								<button class="submit">确&nbsp;&nbsp;&nbsp;认</button>
							</div>
						</div>
					</div>
				</div>
			</div>
	</div>
	<script>
		$(function(){
			var currIndex = 0;
			var step = 20;
			$(".submit").click(function(){
				currIndex += 1;
				if(currIndex == 3) {
					$("#form").submit();
				}
				var i = currIndex;
				while(i >= 0) {
					i--;
					var last = $("#tip" + i);
					slide(last, step * -1);
				};
				var curr = $("#tip" + currIndex);
				curr.removeClass("hd");
			});

			$(".prevpage").click(function(){
				currIndex -= 1;
				if(currIndex < 0) {
					window.location.href = g.base + "/register_improve/${(user.uid)!}";
				}

				var i = currIndex;
				while(i >= 0){
					var last = $("#tip" + i);
					slide(last, step * 1);
					i--;
				}
				
				var curr = $("#tip" + (currIndex + 1));
				curr.addClass("hd");
			});

			//设置左移或右移
			function slide(obj, left) {
				var currLeft = obj.css("left");
				//alert(currLeft)
				var newLeft = parseInt(currLeft) + left;
				//alert(newLeft)
				obj.animate({left : newLeft}, "fast", "linear");
			}
			
		});
	</script>
</body>
</html>