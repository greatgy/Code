[#ftl]
<!-- 一级导航开始 -->
<div class="firstNavBox">
	<div class="container-fluid">
		<div class="row first_nav">
			<div class="navbar navbar01" role="navigation">
				<div class="navbar_com">
						<div class="userbox">
							<a class="back" href="">&lt;</a>
						</div>
					<div class="logo_brand">
						<div class="navbar-brand">
							<a href="http://www.supergenius.cn/m/index">
								<img src="${baseimg}/imgs/mobile/default/slogo.png" class="superlogo img-responsive" alt="超天才" title="超天才">
							</a>
							<a href="${base}/m/index">
								<img src="${baseimg}/imgs/default/logo.png" class="img-responsive" alt="" />
							</a>
						</div>
					</div>
					<a class="searchLink" href="${SolrSearchUrl}/index">
						<img src="${baseimg}/imgs/mobile/default/searchimg.png" alt="" />
					</a>
				</div>
			</div>
		</div><!-- 一级导航结束 -->
	</div>
</div>
<script type="text/javascript">
//手机端返回按钮
    	$(".back").click(function () {
    		if (document.referrer == '') {
			    // 没有来源页面信息的时候，改成首页URL地址
			    $('.back').attr('href', '${base}/index');
			} else {
			    $('.back').attr('href', 'javascript:history.go(-1);');
			}
    	});
 </script>
 	<style type="text/css">
 	    .containerBox {
             margin: 70px auto 30px;
         }
         .contentLeft {
             margin-top: 0px;
         }
         .userbox {
             background: none;
         }
         .back {
             display: inline-block;
             font-size: 2.4rem;
             color: #959595;
             padding-left: 5px;
             line-height: 43px;
         }
      </style>