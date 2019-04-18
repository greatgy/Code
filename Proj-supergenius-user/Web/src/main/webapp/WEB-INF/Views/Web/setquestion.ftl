[#ftl]
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>账号管理--账户保护设置密保问题</title>
	<meta name="Keywords" content="会员中心账号管理，账户保护设置密保问题" />
	<meta name="Description" content="会员中心账号管理，账户保护设置密保问题" />
	<link rel="stylesheet" type="text/css" href="${basecss}/css/default/account.css">
<script type="text/javascript">
<!--
$(function() {
	$("#nextstep").click(function() {
		var flag = true;
		var objs = ["sltquestion1", "txtnanswer1", "sltquestion2", "txtnanswer2"];
		for (var k in objs) {
			$("#err" + objs[k]).addClass("hd");
			var elems = $("#" + objs[k]);
			if ( elems.val() == "") {
				$("#err" + objs[k]).removeClass("hd");
				flag = false;
			}
		}
		if (flag) {
			$("#setquestionimg").attr("src","${baseimg}/imgs/default/redarrow.png");
			$("#setquestiondiv").addClass("checked");
			$("#stepinfo1").addClass("hd");
			$("#stepinfo2").removeClass("hd");
			$("#okquestion1").text($("#sltquestion1").find("option:selected").text());
			$("#okanswer1").text($("#txtnanswer1").val());
			$("#okquestion2").text($("#sltquestion2").find("option:selected").text());
			$("#okanswer2").text($("#txtnanswer2").val());
		}
	});
	
	$("#stepinfo2back").click(function() {
		$("#setquestionimg").attr("src","${baseimg}/imgs/default/grayarrow.png");
		$("#setquestiondiv").removeClass("checked");
		$("#stepinfo1").removeClass("hd");
		$("#stepinfo2").addClass("hd");
	});
	
	$("#txtnanswer2").keydown(function(event) {
	    if (event.keyCode == 13) {
	        $("#nextstep").click();
	        return false;
	    }
	});
	
	$("#submit").click(function() {
		$("#setquestionform").submit();
	});
});
//-->
</script>
</head>
<body>
	<div class="wraper">
		<div class="container">
			<div class="containerleft [#if !member??]containerleftgreen[/#if]">
				<h2>账号管理</h2>
				<ul class="accountlist">
					<li class="accountitem01">
						<a href="${base}/my/user/info"><span>个人信息</span></a>
					</li>
					<li class="accountitem02">
						<a href="${base}/my/user/setpwd"><span>修改登录密码</span></a>
					</li>
					<li class="accountitem03">
						<a href="${base}/my/user/setpaypwd"><span>修改支付密码</span></a>
					</li>
					<li class="accountitem04">
						<a href="${base}/my/user/setemail"><span>修改绑定邮箱</span></a>
					</li>
					<li class="accountitem05 activeli">
						<a href="${base}/my/user/safeqa"><span>账户保护</span></a>
					</li>
					<li class="accountitem06">
                        <a href="${base}/my/user/news"><span>我的消息</span></a>
                    </li>
				</ul>
			</div>
			<div class="containerright  [#if !member??]containerrightgreen[/#if] ">
				<h2>账户保护</h2>
				<div class="setquestioncontent">
					<div class="stepprogress">
					 [#if noexist == true]
						[#assign num = 0]
						[#assign again = ""]
						[#assign submit ="提交"]
					 [#else]
					 	[#assign num = 1]
					 	[#assign again = "重新"]
					 	[#assign submit ="确定修改"]
						<div class="step checked">
							<span class="circle">1</span>
							<span>验证密保答案</span>
						</div>
						<img src="${baseimg}/imgs/default/redarrow.png" alt="" />
					 [/#if]
						<div class="step checked">
							<span class="circle">${num + 1}</span>
							<span>${again}选择密保问题</span>
						</div>
						<img id="setquestionimg" src="${baseimg}/imgs/default/grayarrow.png" alt="" />
						<div id="setquestiondiv" class="step ">
							<span class="circle">${num + 2}</span>
							<span>确认密保答案</span>
						</div>
						<img src="${baseimg}/imgs/default/grayarrow.png" alt="" />
						<div class="step">
							<span class="circle">${num + 3}</span>
							<span>完成</span>
						</div>
					</div>
<!--分段显示1-->					
					<div id="stepinfo1" class="stepinfo">
						<form id="setquestionform" action="${base}/my/user/setquestion" method="post">
							<h3>请${again}选择密保问题</h3>
							<ul class="securitylist">
								<li>
									<span>密保问题1</span>
									<select id="sltquestion1" class="keys" name="question1">
										<option value="">---请选择---</option>
										[#assign ks = enums?keys]
									    [#list ks as item]
										<option value="${item}">${enums[item]}</option>
										[/#list]
									</select>
										<span id="errsltquestion1" class="hd" style="color:#f00;"><i></i>&nbsp&nbsp&nbsp&nbsp请选择</span>
								</li>
								<li>
									<span>密保答案1</span>
									<input id="txtnanswer1" class="keys" name="nanswer1" type="text" placeholder="请填写问题答案"  />
									<span id="errtxtnanswer1" class="hd" style="color:#f00;"><i></i>&nbsp&nbsp&nbsp&nbsp不能为空</span>
								</li>
								<li>
									<span>密保问题2</span>
									<select id="sltquestion2" class="keys" name="question2">
										<option value="">---请选择---</option>
										[#assign ks = enums2?keys]
									    [#list ks as item]
										<option value="${item}">${enums2[item]}</option>
										[/#list]
									</select>
										<span id="errsltquestion2" class="hd" style="color:#f00;"><i></i>&nbsp&nbsp&nbsp&nbsp请选择</span>
								</li>
								<li>
								<span>密保答案2</span>
									<input id="txtnanswer2" class="keys" name="nanswer2" type="text" placeholder="请填写问题答案"  />
									<span id="errtxtnanswer2" class="hd" style="color:#f00;"><i></i>&nbsp&nbsp&nbsp&nbsp不能为空</span>
								</li>
							</ul>
							<div class="checkbtns checkposition">
								<a href="javascript:;" class="save" id="nextstep">下一步</a> 
							</div>
						</form>
					</div>
<!--分段显示2-->
					<div id="stepinfo2" class="stepinfo hd">
						<h3>请确认您的密保问题的答案</h3>
						<ul class="securitylist">
							<li>
								<span>密保问题1</span>
								<div id="okquestion1" class="keys safequestion"></div>
							</li>
							<li>
								<span>密保问题答案</span>
								<div id="okanswer1" class="keys safequestion">您父亲的名字？</div>
							</li>
							<li>
								<span>密保问题2</span>
								<div id="okquestion2" class="keys safequestion"></div>
							</li>
							<li>
								<span>密保问题答案</span>
								<div id="okanswer2" class="keys safequestion">您母亲的名字？</div>
							</li>
						</ul>
						<div class="checkbtns">
							<a id="stepinfo2back" href="javascript:;" class="cancle">上一步</a>
							<a id="submit" href="javascript:;" class="save">${submit}</a> 
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>