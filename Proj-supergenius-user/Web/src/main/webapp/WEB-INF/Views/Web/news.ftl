[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Public.ftl" as p]
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>账号管理--我的消息</title>
	<meta name="Keywords" content="会员中心账号管理，我的消息" />
	<meta name="Description" content="会员中心账号管理，我的消息" />
	<link rel="stylesheet" type="text/css" href="${basecss}/css/default/account.css">
		<script type="text/javascript" src="${basejs}/js/pages/scroll.js"></script>
	<style type="text/css">
        .protectmid {
            display: inline-block;
            width: auto ;
            overflow: hidden;
        }
        .protectlist {
            width: 655px;
            margin: 0 auto;
            margin-top: 20px;
            overflow: hidden;
            margin-left: 40px;
        }
        .protectlist li {
          padding: 8px 10px;
          height: 38px;
          text-align: left;
          border: none;
          margin-bottom: 38px;
          overflow: visible;
        }
        .empet{
                width: 100%;
                height: 100%;
                text-align: center;
                font-size: 18px;
                color: #b3b3b3;
                line-height: 500px;
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
				<h2>我的消息</h2>
				<ul class="protectlist">
				[#if newsList?size > 0]
					[#list newsList as news]
                        <a href="${base}/my/user/newdetail/${news.uid}">
                            <li>
                                <div class="protectmid">
                                    <h3><span style="color: red;">[#if news.isread == false]NEW[/#if]</span>${news.title}</h3>
                                    <span>[@p.TrimSubstring content="${news.content}" mylength=100 /]</span>
                                </div>
                            </li>
						</a>
					[/#list]
				[#else]
				    <p class="empet">空空如也</p>
				[/#if]
				</ul>
			</div>
		</div>
	</div>
	<script type="text/javascript">
        $(function(){
                $.fn.scrollHandle({
                    obj: window,
                    fun: loadmore
                })
            })
            var prePage = 1;
            var pagenum = 2;
            // 加载更多文章
            function loadmore() {
                var	url = "${base}/ajax/my/user/morenews";
                if(prePage != pagenum) {
                    prePage = pagenum;
                    $.get(url, {pagenum:pagenum}, function(data){
                        if(data.trim() == ""){// 没有更多内容
                            $(window).unbind("scroll", defaultScrollHandler);
                            return false;
                        }
                        $(".protectlist").append(data);
                        pagenum+=1;
                    });
                }
            }
	</script>
</body>
</html>
