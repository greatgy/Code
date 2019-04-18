[#ftl]
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>账号管理--我的消息</title>
	<meta name="Keywords" content="会员中心账号管理，我的消息" />
	<meta name="Description" content="会员中心账号管理，我的消息" />
	<link rel="stylesheet" type="text/css" href="${basecss}/css/default/account.css">
	<style type="text/css">
            .containerright h2 {
                  height: 50px;
                  line-height: 83px;
                  font-size: 17px;
                  color: #000;
                  padding-left: 0px;
                  border-bottom: none;
                  text-align: center;
                  letter-spacing: 1px;
            }
            .newall{
                      margin-top: 30px;
            }
            .exitnews {
                          background: #0dc06e;
                          position: absolute;
                          margin-top: 5%;
                          margin-left: 24%;
                          width: 100px;
                          height: 39px;
                          color: #fff;
                          font-size: 16px;
                          border-radius: 6px;
            }
            .exitnewsdelete{
                     background: #fff;
                     position: absolute;
                     margin-top: 5%;
                     margin-left: 10%;
                     border: 1px solid #8c8c8c;
                     width: 100px;
                     height: 39px;
                     color: #737171;
                     font-size: 16px;
                     border-radius: 6px;
            }
            .newtitle{
                font-size: 18px;
                margin-left: 7%;
                color: #676767;
                line-height: 83px;
            }
            .newcontent{
                   font-size: 15px;
                   color: #676767;
                   line-height: 30px;
            }
            .newdiv{
                width: 87%;
                margin-top: 3%;
            }
    	</style>
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
					<li class="accountitem05">
						<a href="${base}/my/user/safeqa"><span>账户保护</span></a>
					</li>
					<li class="accountitem06 activeli">
                        <a href="${base}/my/user/news"><span>我的消息</span></a>
                    </li>
				</ul>
			</div>
			<div class="containerright  [#if me.status == '11']containerrightgreen[/#if] ">
                <div class="newall">
                    <h2>${msg.title}</h2>

                       <div class="newdiv"> <span class="newcontent">${msg.content}</span></div>
                        <button class="exitnewsdelete">删除</button>
                        <button class="exitnews">关闭</button>
                </div>
			</div>
		</div>
	</div>
<script type="text/javascript">
        $(".exitnews").click(function(){
            window.location.href="${base}/my/user/news";
        })
        $(".exitnewsdelete").click(function(){
            $.ajax({
                    type: "GET",
                    url: "${base}/my/user/newdelete/${msg.uid}",
                    success: function (data) {
                        window.location.href="${base}/my/user/news";
                    }
                })
        })
	</script>
</body>
</html>
