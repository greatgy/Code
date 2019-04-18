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
							<form action="${base}/pay_caution02/${user.uid}" id="ajaxform">
								<div class="paystepTip">缴费确认提示&nbsp;<span>3/3</span></div>
								<div class="tipnumbers">
									<div id="tip2" class="tip1 tip3">
										<div class="blueblock">3</div>
										<div class="tip1container">
											<div class="tip1content">
												<h3>提示</h3>
												<p>本质上我们是一个提升有志者学习、工作、创业、经营和管理能力的小众培训网站，我们不能保证每一位会员都能逆袭成功。您仍然愿意成为超天才会员吗？</p>
											</div>
										</div>
									</div>
								</div>
							</form>
							<div class="buttons">
								<a class="prevpage" >否</a>
								<a class="submit" href="${base}/pay_caution02/${user.uid}">是</a>
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