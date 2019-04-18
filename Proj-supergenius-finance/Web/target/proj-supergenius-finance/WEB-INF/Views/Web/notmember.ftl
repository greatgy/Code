[#ftl]
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>${title!'非会员提醒'}</title>
	<meta name="Keywords" content="非会员提醒" />
	<meta name="Description" content="非会员提醒" />
	<style>
		body{
			background:#fff;
		}
		.containerBox .openmember {
		    display: block;
		    border: 1px solid #1d71b6;
		    margin-top: 9%;
    		border-radius: 8px;
		}
		.containerBox .openmember h2{
		    text-align: center;
		    background-color: #1d71b6;
		    border-bottom: 1px solid #1d71b6;
		    padding: 15px 20px;
		    font-size: 1.2rem;
		    letter-spacing: 1px;
		    color: #333;
		}
	</style>
</head>
<body>
	<div class="openmember">
			<h2>
				温馨提示
			</h2>
			<div class="normalTipbox">
				<p>目前该板块仅对超天才会员开放，成为超天才会员，享更多特权</p>
				<a href="${userbase}/register_improve/${me.uid}" class="beuser">加入超天才会员</a>
			</div>
	</div>
</body>
</html>

