[#ftl]
<html>
<head>
	<meta charset="UTF-8">
	<title>会员注册--支付会员费提示</title>
	<meta name="Keywords" content="会员中心，支付会员费提示" />
	<meta name="Description" content="会员中心，支付会员费提示" />
	<link rel="stylesheet" type="text/css" href="${basecss}/css/default/register.css">
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
							<span class="firstblock activeblock">实名认证</span>
							<span class="firstblock secondblock">支付会员费</span>
						</div>
						<div class="registercontmiddle">
							<h2>成为会员需要缴纳会员费：<i class="money">10000，00</i>元</h2>
							<form action="${base}/pay02/${user.uid}" id="ajaxform">
								<div class="paystepTip">缴费确认提示&nbsp;<span>1/3</span></div>
								<div class="tipnumbers">
									<div id="tip0" class="tip1">
										<div class="blueblock">1</div>
										<div class="tip1container">
											<div class="tip1content">
												<h3>提示</h3>
												<p>非常感谢您对本网站的厚爱和信任，但本网站温馨提醒您：请认真阅读<a href="${base}/userrule">《用户协议》</a>及<a href="${base}/legal">《法律声明》</a>，以明确本网站所能提供的有限的服务内容。</p>
												
											</div>
										</div>
									</div>
									<div class="check">
										<input type="checkbox" checked />
										我已阅读并同意《用户协议》、《法律声明》等相关规定
									</div>
									<div id="tip1" class="tip1 tip2 hd">
										<div class="blueblock">Tip2</div>
										<div class="tip1container">
											<div class="tip1content">
												<h3>提示</h3>
												<p>您所缴纳的会员费，将在任何情况下都不予以退款，请确认是否付款！</p>
											</div>
										</div>
									</div>
									<div id="tip2" class="tip1 tip3 hd">
										<div class="blueblock">Tip3</div>
										<div class="tip1container">
											<div class="tip1content">
												<h3>提示</h3>
												<p>本网站不能保证其内容和服务能让您完全满意，也不能保证所有会员能顺利完成 相关培训直至拿到学位证书</p>
											</div>
										</div>
									</div>
								</div>
							</form>
							<div class="buttons">
								<a class="prevpage" >上一步</a>
								<a class="submit" href="${base}/pay02/${user.uid}">同意并继续</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	<script>
		$(function(){

			$(".prevpage").click(function(){
				window.history.back();
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