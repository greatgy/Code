[#ftl]
<html>
<head>
	<title>会员注册--支付会员费</title>
	<meta name="Keywords" content="会员中心，支付会员费" />
	<meta name="Description" content="会员中心，支付会员费" />
	<link rel="stylesheet" type="text/css" href="${basecss}/css/default/register.css">
	<script type="text/javascript" src="${basejs}/js/libs/mask.js"></script>
	<script type="text/javascript" src="${basejs}/js/libs/validator-1.0.0.js"></script>
	<script type="text/javascript">
<!--		
	$(function(){
		$(".pay").click(function(){
			var flag = true;
			if ($(".payways input[name='banktype']:checked").val() == undefined) {
				$("#selectbanktype").removeClass("hd");
				flag = false;
			}
			if(flag) {
				$("#rechargeform").submit();
				$("#selectbanktype").addClass("hd");
				$(".maskcontent").show();
				$(".mask").show();
			}
		});
		
		$(".bankimg").click(function() {
		    $(this).siblings("input[name='banktype']").attr("checked", "checked");
		});
	});
	
			// 手动查询
		function bankQueryResult() {
			var url = "${accountbase}/api/${apiuid}/recharge/query/${payuid}"; 
			$.get(getNoCachePath(url), {site:${site}}, function(data){
				var jsondata = $.parseJSON(data);
				if (jsondata.errs != undefined) {
					$("#result").removeClass("hd");
					$(".maskcontent").hide();
					$(".mask").hide();
					clearInterval(interval);
					return;
				}
				var result = jsondata.result;
				if (result == 1) {
					gourl("${base}/payregistermoney/recharge/result" + result + "?uid=${uid}");
				} else {
					$("#result").removeClass("hd");
					$(".maskcontent").hide();
					$(".mask").hide();
					clearInterval(interval);
				}
			});
		}
		
		var interval;
		function handler(){
			//$('#alertrechargebox').css("display",'block');
			interval = setInterval("ApiHandler()",5000);
		}
		
		// 自动查询
		function ApiHandler(){
			var url = "${accountbase}/api/${apiuid}/recharge/query/${payuid}"; 
			$.get(getNoCachePath(url), {site:${site}}, function(data){
				var jsondata = $.parseJSON(data);
				if (jsondata.errs != undefined) {
					$("#result").removeClass("hd");
					$(".maskcontent").hide();
					$(".mask").hide();
					clearInterval(interval);
					return;
				}
				var result = jsondata.result;
				if (result == 1) {
					gourl("${base}/payregistermoney/recharge/result" + result + "?uid=${uid}");
				} else if (result == 0 || result == 2) {
				    //不做处理,继续查询
				} else {
					clearInterval(interval);
					$("#result").removeClass("hd");
					$(".maskcontent").hide();
					$(".mask").hide();
				}
			});
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
							<div class="payways">
								<h2>支付超天才会员费</h2>
								<ul class="wayslist">
									<li>支付金额：<b>${registerPayMonney}</b>&nbsp;元</li>
							<form id="rechargeform" action="${accountbase}/api/${apiuid}/recharge/${payuid}" method="post" data-beforesubmithandler="handler();" target="_blank" data-canresubmitrepeat="true">
				                <!--隐藏属性-->
								<input name="notifyurl" type="hidden" value="${base}/api/payregistermoney/recharge/notify"/>
								<input name="resulturl" type="hidden" value="${base}/payregistermoney/recharge/result%%s"/>
								<input name="userip" type="hidden" value="${userip}"/>
								<input name="site" type="hidden" value="${site}"/>
								<input name="type" type="hidden" value="${type}"/>
								<input name="useruid" type="hidden" value="${uid}"/>
								<input name="money" type="hidden" value="${registerPayMonney}"/>
								<input name="username" type="hidden" value="${user.name}" />
								<input name="useremail" type="hidden" value="${user.email}"/>
								<!--隐藏属性-->
									<li style="display:inline;">选择支付方式：</li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<span id="selectbanktype" class="hd" style="color: red; font-size: 15px;">请选择支付方式</span>
									<br/>
									<br/>
									<br/>
									<li style="margin-left: 40px;">
										<div class="paywaysright">
											<div class="bank">
												<input type="radio" name="banktype" value="11" />
												<img style="width: 108px;height: 28px;padding: 4px 8px;border: 1px solid #aeaeae;" class="bankimg" src="${baseimg}/imgs/default/alipay.png" alt=""/>
											</div>
											<div class="bank" style="margin-left: 20px;">
												<input type="radio" name="banktype" value="13" />
												<img style="width: 108px;height: 28px;padding: 4px 8px;border: 1px solid #aeaeae;" class="bankimg" src="${baseimg}/imgs/default/wxpay.png" alt=""/>
											</div>
											<div class="bank" style="margin-left: 20px;">
												<input type="radio" name="banktype" value="6"/>
												<img style="width: 108px;height: 28px;padding: 4px 8px;border: 1px solid #aeaeae;" class="bankimg" src="${baseimg}/imgs/default/online.png" alt="" />
											</div>
											<div class="bank">
												<input type="radio" name="banktype" value="0" />
												<img style="width: 108px;height: 28px;padding: 4px 8px;border: 1px solid #aeaeae;" class="bankimg" src="${baseimg}/imgs/default/gongshang.png" alt=""/>
											</div>
											[#-- <div class="bank">
												<input type="radio" name="banktype" value="3"/>
												<img class="bankimg" src="${baseimg}/imgs/default/nongye.png" alt=""/>
											</div>
											<div class="bank">
												<input type="radio" name="banktype" value="4"/>
												<img class="bankimg" src="${baseimg}/imgs/default/jiaotong.png" alt=""/>
											</div> --]
											<div class="bank" style="margin-left: 20px;">
												<input type="radio" name="banktype" value="1"/>
												<img style="width: 108px;height: 28px;padding: 4px 8px;border: 1px solid #aeaeae;" class="bankimg" src="${baseimg}/imgs/default/zhaoshang.png" alt=""/>
											</div>
										</div>
									</li>
									<li id="result" class="hd" style="font-size: 15px; color:#f00;display:inline-block;text-align:center; width:inherit;margin-top:0px;width: 700px;">
										<b>支付失败，请重新支付！</b><br/>
									</li>
								</ul>
								<a href="javascript:;" class="pay">去付款</a>
								</form>
							</div>
						</div>
						<div class="registercontbottom">
							<h2>网银支付常见问题</h2>
							<ul class="rightslist">
								<li>1、问：网银支付支持哪些银行？</li>
								<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;答：目前暂时只支持工商银行、农业银行、招商银行、交通银行4家银行进行支付。</li>
								<br/>
								<li>2、问：网银支付还需要将银行卡开通网上银行功能么？</li>
								<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;答：您如果通过选定网上银行则需要开通网银。</li>
								<br/>
								<li>3、问：我银行选择错了，支付的时候还能改吗？</li>
								<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;答：您选择网银支付，如您选择了中国银行进行支付，提交订单后想换成其他银行，可以在支付时候修改支付银行。</li>
								<br/>
								<li>4、问：如果多次出现支付失败怎么办？</li>
								<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;答：<a href="${officialbase}/contact" target="_blank">请联系客服处理</a>。</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
			<div class="mask"></div>
			<div class="maskcontent rechargecover">
				<div class="rechargecoverment">
					<h1>
						<img src="${baseimg}/imgs/default/recharsuccess.png" alt="" />
						请在新开网银页面完成付款
					</h1>
					<div class="tipreturn">支付成功后订单状态可能会延迟更新，可稍后查看</div>
					<div class="recharbtns">
						<a href="javascript:;" onclick="bankQueryResult()" class="finish">已完成付款</a>
						<a href="javascript:;" onclick="bankQueryResult()" class="default">付款遇到问题</a>
					</div>
				</div>
			</div>
		</div>
</body>
</html>