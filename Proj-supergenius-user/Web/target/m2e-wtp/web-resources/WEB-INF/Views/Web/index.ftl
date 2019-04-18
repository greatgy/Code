[#ftl]
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>我的超天才</title>
	<meta name="Keywords" content="会员中心首页，我的超天才" />
	<meta name="Description" content="会员中心首页，我的超天才" />
	<link rel="stylesheet" type="text/css" href="${basecss}/css/default/index.css">
	<style type="text/css">
		.blocksmid {
			width: 340px;
		}
		.blocksmid h2{
			margin-top: 8px;
		}
	</style>
</head>
<body>
		<!-- 主体部分开始 -->
		<div class="container">
		    [#if me??]
		    <div class="main">
		    	[#if !member??]
						<div class="maintop beforemember">
							<h2>
								我的超天才
								<a href="javascripst:;" class="modals">模块管理</a>
							</h2>
							<div class="maintopleft">
								<div class="maintoplftop">
									<div class="imgcontainer">
										<a href="${base}/my/user/setavatar" ><img [#if me.useravatar??] src="${me.useravatar}"[#else]src="${baseimg}/imgs/web/noavatar.png"[/#if] /></a>
									</div>
									<div class="persondetail">
										<a href="${base}/register_improve/${me.uid}" class="bemember">升级成为会员，享受更多特权</a>
									</div>
								</div>
								<div class="maintoplfbottom">
									<a href="${base}/my/user">基本信息</a>
									<a href="${base}/my/user/setavatar">修改头像</a>
									<a href="${base}/my/order">我的订单</a>
								</div>
							</div>
							<div class="maintopright">
								<ul class="accountdetail">
									<li>账户余额：<b class="b01">${me.account!?string("0.00")}元</b></li>
									<li>累计支出：<b class="b01">${me.totalpay!?string("0.00")}元</b></li>
									<li>累计收入：<b class="b01">${me.totalincome!?string("0.00")}元</b></li>
									<li>开户时间：<b class="b04">${(me.createtime).toString("YYYY年MM月dd日HH:mm")}</b></li>
								</ul>
								<div class="btns">
									<a href="${base}/my/account/recharge" class="recharge">立即充值</a>
									<a href="${base}/my/user/setpaypwd" class="changepw">修改支付密码</a>
								</div>
							</div>
						</div>
						<ul class="mainbottom beforeColor">
							<li class="blockitem" id="a1" style="display: none;">
								<h2>
									<a href="${managernewsbase}" target="_blank" style="font-weight:bold;">职业经理人培训</a>
									<a href="javascript:;" data-modelid="1" class="deleteModel">隐藏</a>
								</h2>
								<h3 id="msgh1">
									<span>评论<a href="${managerbase}/mycenter/msg" class="number" target="_blank">&nbsp</a></span>
									<span>通知<a href="${managerbase}/mycenter/msg" class="number" target="_blank">&nbsp</a></span>
									<span>系统消息<a href="${managerbase}/mycenter/msg" class="number" target="_blank">&nbsp</a></span>
								</h3>
								<div class="libottom" >
									<div class="judgemid" id="managercontent">
										${managernewsarticle}
									</div>	
									<div class="linkcontaner">
										<a href="${managerbase}/major" class="baselink" target="_blank">申请学员</a>
										<a href="${managerbase}[#if me.isStudent == true]/my/pkschedule/launch[#else]/major[/#if]" class="baselink" target="_blank">发起挑战</a>
										[#-- <a href="${managerbase}/pkschedule" class="baselink" target="_blank">去观战</a> --]
										<a href="${managernewsbase}/my/center/article" class="baselink" target="_blank">我的文章</a>
										<a href="${managernewsbase}/my/member/contribute" class="baselink" target="_blank">投稿</a>
									</div>
								</div>
							</li>
							<li class="blockitem" id="a2" style="display: none;">
								<h2>
									<a href="${moralnewsbase}/index" target="_blank" style="font-weight:bold;">职业道德培训</a>
									<a href="#" data-modelid="2" class="deleteModel">隐藏</a>
								</h2>
								<h3 id="msgh2">
									<span>评论<a href="${moralnewsbase}/my/center/msg" class="number" target="_blank">&nbsp</a></span>
									<span>通知<a href="${moralnewsbase}/my/center/msg" class="number" target="_blank">&nbsp</a></span>
									<span>系统消息<a href="${moralnewsbase}/my/center/msg" class="number" target="_blank">&nbsp</a></span>
								</h3>
								<div class="libottom">
									<div class="judgemid" >
									<a href="${moralnewsbase}/index" id="moralvideoa" target="_blank">
									    <img src="${baseimg}/imgs/default/moral.jpg" alt="" class="leftimg" /></a>
										<ul class="judgelist livelist" id="moralcontent">
										</ul>
									</div>
									<div class="linkcontaner">
										[#if !(is_studentmoral??)]<a href="${moralbase}/mycenter/apply/student" class="baselink" target="_blank">立即报名</a>[/#if]
										<a href="${moralnewsbase}/my/center/topic" class="baselink" target="_blank">我的帖子</a>
										<a href="${moralnewsbase}/my/center/article" class="baselink" target="_blank">我的文章</a>
										<a href="${moralbase}/exercise" class="baselink" target="_blank">模拟练习</a>
										<a href="${moralbase}/mycenter/exam" class="baselink" target="_blank">开始考试</a>
									</div>
								</div>
							</li>
							<li class="blockitem" id="a3" style="display: none;">
								<h2>
									<a href="${financebase}" target="_blank" style="font-weight:bold;">天财评论</a>
									<a href="#" data-modelid="3" class="deleteModel">隐藏</a>
								</h2>
								<h3 id="msgh3">
									<span>评论<a href="${financebase}/my/msg" class="number" target="_blank">&nbsp</a></span>
									<span>通知<a href="${financebase}/my/msg" class="number" target="_blank">&nbsp</a></span>
									<span>系统消息<a href="${financebase}/my/msg" class="number" target="_blank">&nbsp</a></span>
								</h3>
								<div class="libottom">
									<div class="judgemid" id="financecontent">
										${financearticle}
									</div>	
									<div class="linkcontaner">
										<a href="${financebase}" class="baselink" target="_blank">进入天财评论</a>
										<a href="${financebase}/my/center/article" class="baselink" target="_blank">我的文章</a>
									</div>
								</div>
							</li>
							<li class="blockitem" id="a4" style="display: none;">
								<h2>
									<a href="${entrepreneurbase}" target="_blank" style="font-weight:bold;">企业家培训</a>
									<a href="#" data-modelid="4" class="deleteModel">隐藏</a>
								</h2>
								<div class="libottom">
									<div class="judgemid">
										${entrepreneurarticle}
									</div>
									<a href="${entrepreneurbase}/my/center/article" class="baselink">我的文章</a>
									<a href="${enterpriserbase}/index" class="baselink" target="_black">企业家培训</a>
									<a href="${entrepreneurbase}/my/member/contribute" class="baselink">投稿</a>
								</div>
							</li>
							<li class="blockitem" id="a5" style="display:none">
								<h2>
									<a href="${startupbase}" target="_blank" style="font-weight:bold;">天才创业</a>
									<a href="#" data-modelid="5" class="deleteModel">隐藏</a>
								</h2>
								<h3 id="msgh5">
									<span>我的消息<a href="${startupbase}/my/center" class="number" target="_blank">&nbsp</a></span>
								</h3>
								<div class="libottom">
									<div class="judgemid">
										${startuparticle}
									</div>	
									<div class="linkcontaner">
										<a href="${startupbase}/questionnaire" class="baselink">创业测试</a>
										<a href="tencent://message/?uin=2954769862&amp;Menu=yes" class="baselink" target="blank">在线咨询</a>
										<a href="${startupbase}/my/center" class="baselink">我的收藏</a>
										<a href="${startupbase}/contribute" class="baselink">投稿</a>
									</div>
								</div>
							</li>
							<li class="blockitem" id="a6" style="display:none">
								<h2>
									<a href="javascripst:;"  class="normalUsertip" style="font-weight:bold;">人间仙境</a>
									<a href="#" data-modelid="6" class="deleteModel">隐藏</a>
								</h2>
								<h3 class="havenote" id="msgh6">
									<a href="javascripst:;" class="normalUsertip"><span>评论<a href="javascripst:;" class="number normalUsertip">&nbsp</a></span></a>
									<a href="javascripst:;" class="normalUsertip"><span>通知<a href="javascripst:;" class="number normalUsertip" >&nbsp</a></span></a>
									<a href="javascripst:;" class="normalUsertip"><span>系统消息<a href="javascripst:;" class="number normalUsertip">&nbsp</a></span></a>
									<a href="javascripst:;" class="normalUsertip"><span>私信<a href="javascripst:;" class="number normalUsertip">&nbsp</a></span></a>
								</h3>
								<div class="libottom">
									<ul class="mergelist" id="snscontent">
										<li>
											<a href="javascripst:;" class="normalUsertip">
												<img src="${baseimg}/imgs/default/haven01.png" alt="" />
												<a href="javascripst:;" class="mergetitle" target="_blank">微博&nbsp;[#--<b>(12)</b>--]</a>
											</a>
										</li>
										<li>
											<a href="javascripst:;" class="normalUsertip">
												<img src="${baseimg}/imgs/default/haven02.png" alt="" />
												<a href="javascripst:;" class="mergetitle normalUsertip">群文章&nbsp;[#--<b>(12)</b>--]</a>
											</a>
										</li>
										<li>
											<a href="javascripst:;" class="normalUsertip">
												<img src="${baseimg}/imgs/default/haven03.png" alt="" />
												<a href="javascripst:;" class="mergetitle normalUsertip" >荐言堂&nbsp;[#--<b>(12)</b>--]</a>
											</a>
										</li>
										<li>
											<a href="javascripst:;" class="normalUsertip">
												<img src="${baseimg}/imgs/default/haven02.png" alt="" />
												<a href="javascripst:;" class="mergetitle normalUsertip">博客&nbsp;[#--<b>(12)</b>--]</a>
											</a>
										</li>
									</ul>
									<div class="linkcontaner">
										[#--<a href="#" class="baselink" target="_blank">进入并购平台</a>--]
										<a href="javascripst:;" class="baselink normalUsertip">我的微博</a>
										<a href="javascripst:;" class="baselink normalUsertip">我的群组</a>
										<a href="javascripst:;" class="baselink normalUsertip">遐之苑</a>
									</div>	
								</div>
							</li>
							<li class="blockitem" id="a7" style="display:none">
								<h2>
									<a href="${aibase}" target="_blank" style="font-weight:bold;" >天才AI</a>
									<a href="#" data-modelid="7" class="deleteModel">隐藏</a>
								</h2>
								<h3 id="msgh7">
									<span>消息<a href="${aibase}/my/center/msg" class="number" target="_blank">&nbsp;</a></span>
								</h3>
								<div class="libottom">
									<div class="judgemid">
										${aiarticle}
									</div>	
									<div class="linkcontaner">
										<a href="${aibase}/my/center/article" class="baselink">我的文章</a>
										<a href="${aibase}/my/center/article" class="baselink">草稿箱</a>
										<a href="${aibase}/contribute" class="baselink">投稿</a>
									</div>
								</div>
							</li>
							<li class="blockitem" id="a8" style="display:none">
								<h2>
									<a href="${careerbase}" target="_blank" style="font-weight:bold;">天才职场</a>
									<a href="#" data-modelid="8" class="deleteModel">隐藏</a>
								</h2>
								<h3 id="msgh8">
									<span>收件箱<a href="${careerbase}/my/center/msg" class="number" target="_blank">&nbsp;</a></span>
								</h3>
								<div class="libottom">
									<div class="judgemid">
										<a href="${careerbase}" class="workimg">
											<img src="${baseimg}/imgs/default/talentjob_index.png" alt="" class="leftimg">
											<span>首页</span>
										</a>
										<a href="${careerbase}/catalogue/12" class="workimg">
											<img src="${baseimg}/imgs/default/talentjob_career.png" alt="" class="leftimg">
											<span>职场困惑</span>
										</a>
									</div>	
									<div class="linkcontaner">
										<a href="${careerbase}/test?cid=1" class="baselink">职业测试</a>
										<a href="${careerbase}/my/center/article" class="baselink">我的文章</a>
										<a href="${careerbase}/contribute" class="baselink">投稿</a>
										<a href="${careerbase}/catalogue/11" class="baselink">去吐槽</a>
									</div>
								</div>
							</li>
							<li class="blockitem" id="a9" style="display:none">
								<h2>
									<a href="${gupagebase}" target="_blank" style="font-weight:bold;">顾雏军专栏</a>
									<a href="#" data-modelid="9" class="deleteModel">隐藏</a>
								</h2>
							
								<div class="libottom">
									<ul class="mergelist" style=" margin-top: 30px;">
										<li>
											<a href="${gupagebase}/gupage/news/11">
												<img src="${baseimg}/imgs/default/dynamic.png" alt="" />
												<a href="${gupagebase}/gupage/news/11" class="mergetitle">动态</a>
											</a>
										</li>
										<li>
											<a href="${gupagebase}/gupage/fruit">
												<img src="${baseimg}/imgs/default/achievements.png" alt="" />
												<a href="${gupagebase}/gupage/fruit" class="mergetitle">科研成果</a>
											</a>
										</li>
										<li>
											<a href="${gupagebase}/catalogue/photo">
												<img src="${baseimg}/imgs/default/photos.png" alt="" />
												<a href="${gupagebase}/catalogue/photo" class="mergetitle">精彩照片</a>
											</a>
										</li>
										<li>
											<a href="${gupagebase}/gupage/debate">
												<img src="${baseimg}/imgs/default/contend.png" alt="" />
												<a href="${gupagebase}/gupage/debate" class="mergetitle">郎顾之争</a>
											</a>
										</li>
									</ul>	
									<div class="linkcontaner" style=" margin-top: 17px;">
										<a href="${gupagebase}" class="baselink">进入首页</a>
										<a href="${gupagebase}/catalogue/video" class="baselink">查看视频</a>
									</div>
								</div>
							</li>
							<li class="blockitem" id="a10" style="display:none">
								<h2>
									<a href="${enterpriserbase}" target="_blank" style="font-weight:bold;" >引资购商</a>
									<a href="#" data-modelid="10" class="deleteModel">隐藏</a>
								</h2>
								<h3 class="havenote" id="msgh10">
									<span>收件箱<a href="${enterpriserbase}/my/center/msg" class="number" target="_blank">&nbsp;</a></span>
								</h3>
								<div class="libottom">
									<ul class="mergelist">
										<li>
											<a href="${enterpriserbase}/catalogue/0/9">
												<img src="${baseimg}/imgs/default/attractChair.png" alt="" />
												<a href="${enterpriserbase}/catalogue/0/9" class="mergetitle">商城</a>
											</a>
										</li>
										<li>
											<a href="${enterpriserbase}/forums/2/22?cid=22&pcid=2&pagesize=5&FirstLoadSize=15">
												<img src="${baseimg}/imgs/default/attractForum.png" alt="" />
												<a href="${enterpriserbase}/forums/2/22?cid=22&pcid=2&pagesize=5&FirstLoadSize=15" class="mergetitle">引资购商论坛</a>
											</a>
										</li>
										<li>
											<a href="${enterpriserbase}/catalogue/6/61?cid=6&pcid=0&pagesize=5&FirstLoadSize=15">
												<img src="${baseimg}/imgs/default/case.png" alt="" />
												<a href="${enterpriserbase}/catalogue/6/61?cid=6&pcid=0&pagesize=5&FirstLoadSize=15" class="mergetitle">并购产业基金</a>
											</a>
										</li>
										<li>
											<a href="${enterpriserbase}/content/0/7?cid=7&pcid=0">
												<img src="${baseimg}/imgs/default/cooperation.png" alt="" />
												<a href="${enterpriserbase}/content/0/7?cid=7&pcid=0" class="mergetitle">互助合作平台</a>
											</a>
										</li>
									</ul>
									<div class="linkcontaner">
										<a href="${enterpriserbase}" class="baselink">首页</a>
										<a href="${enterpriserbase}/enterpriser/videodetail/9c9e68c8c014494dbd1398d3f15342a1" class="baselink">观看视频</a>
										<a href="${enterpriserbase}/contribute?cid=7" class="baselink">投稿</a>
									</div>	
								</div>
							</li>
							<li class="blockitem" id="a11" style="display:none">
								<h2>
									<a href="${lifebase}" target="_blank" style="font-weight:bold;">天才人生</a>
									<a href="#" data-modelid="11" class="deleteModel">隐藏</a>
								</h2>
								<h3 id="msgh11">
									<span>消息<a href="${lifebase}/my/center/msg" class="number" target="_blank">&nbsp;</a></span>
								</h3>
								<div class="libottom">
									<div class="judgemid">
									${lifearticle}
									</div>	
									<div class="linkcontaner">
										<a href="${lifebase}/my/center/article" class="baselink">我的文章</a>
										<a href="${lifebase}/my/center/collect" class="baselink">我的收藏</a>
										<a href="${lifebase}/my/contribute?cid=1" class="baselink">投稿</a>
										<a href="${lifebase}/my/center/question" class="baselink">我的提问</a>
										<a href="${lifebase}/my/center/topic" class="baselink">我的话题</a>
									</div>
								</div>
							</li>
						</ul>
					[#else]
						<div class="maintop">
							<h2>
								我的超天才
								<a href="javascript:;" class="modals">模块管理</a>
								<a href="http://www.supergenius.cn/huiyuan">会员特权</a>
							</h2>
							<div class="maintopleft">
								<div class="maintoplftop">
									<div class="imgcontainer memberimg">
										<a  href="${base}/my/user/setavatar" >
										<img [#if me.avatar??] src="${userimg}${me.avatar}"[#else]src="${baseimg}/imgs/web/noavatar.png"[/#if] />
										<img src="${baseimg}/imgs/default/prince.png" class="princeimg" alt="" />
										</a>
									</div>
									<div class="persondetail">
										<div class="memberstatus">
										[#if is_student??]<span>职业经理人培训</span>[/#if]
										[#if is_studentmoral??]<span>职业道德培训</span>[/#if]
										[#if is_enterpriser??]<span>企业家培训</span>[/#if]
										</div>
										<ul class="personlist">
											<li>[#if me.company?trim??]${me.company}[/#if]</li>
											<li>会员号：<em class="membernum">${me.usersn}</em></li>
											<li>邮箱：<em class="email">${me.email}</em></li>
										</ul>
									</div>
								</div>
								<div class="maintoplfbottom">
									<a href="${base}/my/user">基本信息</a>
									<a href="${base}/my/user/setavatar">修改头像</a>
									<a href="${base}/my/order">我的订单</a>
								</div>
							</div>
							<div class="maintopright">
								<ul class="accountdetail">
									<li>账户余额：<b class="b01">${me.account!?string("0.00")}元</b></li>
									<li>累计支出：<b class="b01">${me.totalpay!?string("0.00")}元</b></li>
									<li>累计收入：<b class="b01">${me.totalincome!?string("0.00")}元</b></li>
									<li>开户时间：<b class="b04">${(me.createtime).toString("YYYY年MM月dd日HH:mm")}</b></li>
								</ul>
								<div class="btns">
									<a href="${base}/my/account/recharge" class="recharge">立即充值</a>
									<a href="${base}/my/user/setpaypwd" class="changepw">修改支付密码</a>
								</div>
							</div>
						</div>
						<ul class="mainbottom">
							<li class="blockitem" id="a1" style="display: none;">
								<h2>
									<a href="${managernewsbase}" target="_blank" style="font-weight:bold;">职业经理人培训</a>
									<a href="javascript:;" data-modelid="1" class="deleteModel">隐藏</a>
								</h2>
								<h3 id="msgh1">
									<span>评论<a href="${managerbase}/mycenter/msg" class="number" target="_blank">&nbsp</a></span>
									<span>通知<a href="${managerbase}/mycenter/msg" class="number" target="_blank">&nbsp</a></span>
									<span>系统消息<a href="${managerbase}/mycenter/msg" class="number" target="_blank">&nbsp</a></span>
								</h3>
								<div class="libottom" >
									<div class="judgemid" id="managercontent">
										${managernewsarticle}
									</div>	
									<div class="linkcontaner">
										<a href="${managerbase}/major" class="baselink" target="_blank">申请学员</a>
										<a href="${managerbase}[#if me.isStudent == true]/my/pkschedule/launch[#else]/major[/#if]" class="baselink" target="_blank">发起挑战</a>
										[#-- <a href="${managerbase}/pkschedule" class="baselink" target="_blank">去观战</a> --]
										<a href="${managernewsbase}/my/center/article" class="baselink" target="_blank">我的文章</a>
										<a href="${managernewsbase}/my/member/contribute" class="baselink" target="_blank">投稿</a>
									</div>
								</div>
							</li>
							<li class="blockitem" id="a2" style="display: none;">
								<h2>
									<a href="${moralnewsbase}/index" target="_blank" style="font-weight:bold;">职业道德培训</a>
									<a href="#" data-modelid="2" class="deleteModel">隐藏</a>
								</h2>
								<h3 id="msgh2">
									<span>评论<a href="${moralnewsbase}/my/center/msg" class="number" target="_blank">&nbsp</a></span>
									<span>通知<a href="${moralnewsbase}/my/center/msg" class="number" target="_blank">&nbsp</a></span>
									<span>系统消息<a href="${moralnewsbase}/my/center/msg" class="number" target="_blank">&nbsp</a></span>
								</h3>
								<div class="libottom">
									<div class="judgemid" >
									<a href="${moralnewsbase}/index" id="moralvideoa" target="_blank">
									    <img src="${baseimg}/imgs/default/moral.jpg" alt="" class="leftimg" /></a>
										<ul class="judgelist livelist" id="moralcontent">
										</ul>
									</div>
									<div class="linkcontaner">
										[#if !(is_studentmoral??)]<a href="${moralbase}/mycenter/apply/student" class="baselink" target="_blank">立即报名</a>[/#if]
										<a href="${moralnewsbase}/my/center/topic" class="baselink" target="_blank">我的帖子</a>
										<a href="${moralnewsbase}/my/center/article" class="baselink" target="_blank">我的文章</a>
										<a href="${moralbase}/exercise" class="baselink" target="_blank">模拟练习</a>
										<a href="${moralbase}/mycenter/exam" class="baselink" target="_blank">开始考试</a>
									</div>
								</div>
							</li>
							<li class="blockitem" id="a3" style="display: none;">
								<h2>
									<a href="${financebase}" target="_blank" style="font-weight:bold;">天财评论</a>
									<a href="#" data-modelid="3" class="deleteModel">隐藏</a>
								</h2>
								<h3 id="msgh3">
									<span>评论<a href="${financebase}/my/msg" class="number" target="_blank">&nbsp</a></span>
									<span>通知<a href="${financebase}/my/msg" class="number" target="_blank">&nbsp</a></span>
									<span>系统消息<a href="${financebase}/my/msg" class="number" target="_blank">&nbsp</a></span>
								</h3>
								<div class="libottom">
									<div class="judgemid" id="financecontent">
										${financearticle}
									</div>	
									<div class="linkcontaner">
										<a href="${financebase}" class="baselink" target="_blank">进入天财评论</a>
										<a href="${financebase}/my/center/article" class="baselink" target="_blank">我的文章</a>
									</div>
								</div>
							</li>
							<li class="blockitem" id="a4" style="display: none;">
								<h2>
									<a href="${entrepreneurbase}" target="_blank" style="font-weight:bold;">企业家培训</a>
									<a href="#" data-modelid="4" class="deleteModel">隐藏</a>
								</h2>
								<div class="libottom">
									<div class="judgemid">
										${entrepreneurarticle}
									</div>
									<a href="${entrepreneurbase}/my/center/article" class="baselink">我的文章</a>
									<a href="${enterpriserbase}/index" class="baselink" target="_black">企业家培训</a>
									<a href="${entrepreneurbase}/my/member/contribute" class="baselink">投稿</a>
								</div>
							</li>
							<li class="blockitem" id="a5" style="display:none">
								<h2>
									<a href="${startupbase}" target="_blank" style="font-weight:bold;">天才创业</a>
									<a href="#" data-modelid="5" class="deleteModel">隐藏</a>
								</h2>
								<h3 id="msgh5">
									<span>我的消息<a href="${startupbase}/my/center" class="number" target="_blank">&nbsp</a></span>
								</h3>
								<div class="libottom">
									<div class="judgemid">
										${startuparticle}
									</div>	
									<div class="linkcontaner">
										<a href="${startupbase}/questionnaire" class="baselink">创业测试</a>
										<a href="tencent://message/?uin=2954769862&amp;Menu=yes" class="baselink" target="blank">在线咨询</a>
										<a href="${startupbase}/my/center" class="baselink">我的收藏</a>
										<a href="${startupbase}/contribute" class="baselink">投稿</a>
									</div>
								</div>
							</li>
							<li class="blockitem" id="a6" style="display:none">
								<h2>
									<a href="http://sns.supergenius.cn" target="_blank" style="font-weight:bold;">人间仙境</a>
									<a href="#" data-modelid="6" class="deleteModel">隐藏</a>
								</h2>
								<h3 class="havenote" id="msgh6">
									<a href="http://sns.supergenius.cn/letter"><span>评论<a href="http://sns.supergenius.cn/letter/inbox" class="number" target="_blank">&nbsp</a></span></a>
									<a href="http://sns.supergenius.cn/letter"><span>通知<a href="http://sns.supergenius.cn/letter/inbox" class="number" target="_blank">&nbsp</a></span></a>
									<a href="http://sns.supergenius.cn/letter"><span>系统消息<a href="http://sns.supergenius.cn/letter/sys" class="number" target="_blank">&nbsp</a></span></a>
									<a href="http://sns.supergenius.cn/letter"><span>私信<a href="http://sns.supergenius.cn/letter/inbox" class="number" target="_blank">&nbsp</a></span></a>
								</h3>
								<div class="libottom">
									<ul class="mergelist" id="snscontent">
										<li>
											<a href="http://sns.supergenius.cn/weibo" target="_blank">
												<img src="${baseimg}/imgs/default/haven01.png" alt="" />
												<a href="http://sns.supergenius.cn/weibo" class="mergetitle" target="_blank">微博&nbsp;[#--<b>(12)</b>--]</a>
											</a>
										</li>
										<li>
											<a href="http://sns.supergenius.cn/dis/home" target="_blank">
												<img src="${baseimg}/imgs/default/haven02.png" alt="" />
												<a href="http://sns.supergenius.cn/dis/home" class="mergetitle" target="_blank">群文章&nbsp;[#--<b>(12)</b>--]</a>
											</a>
										</li>
										<li>
											<a href="http://sns.supergenius.cn/sug" target="_blank">
												<img src="${baseimg}/imgs/default/haven03.png" alt="" />
												<a href="http://sns.supergenius.cn/sug" class="mergetitle" target="_blank">荐言堂&nbsp;[#--<b>(12)</b>--]</a>
											</a>
										</li>
										<li>
											<a href="http://sns.supergenius.cn/blog" target="_blank">
												<img src="${baseimg}/imgs/default/haven02.png" alt="" />
												<a href="http://sns.supergenius.cn/blog" class="mergetitle" target="_blank">博客&nbsp;[#--<b>(12)</b>--]</a>
											</a>
										</li>
									</ul>
									<div class="linkcontaner">
										[#--<a href="#" class="baselink" target="_blank">进入并购平台</a>--]
										<a href="http://sns.supergenius.cn/weibo" class="baselink" target="_blank">我的微博</a>
										<a href="http://sns.supergenius.cn/dis/home" class="baselink" target="_blank">我的群组</a>
										<a href="http://sns.supergenius.cn/p/${me.oid}/home" class="baselink" target="_blank">遐之苑</a>
									</div>	
								</div>
							</li>
							<li class="blockitem" id="a7" style="display:none">
								<h2>
									<a href="${aibase}" target="_blank" style="font-weight:bold;">天才AI</a>
									<a href="#" data-modelid="7" class="deleteModel">隐藏</a>
								</h2>
								<h3 id="msgh7">
									<span>消息<a href="${aibase}/my/center/msg" class="number" target="_blank">&nbsp;</a></span>
								</h3>
								<div class="libottom">
									<div class="judgemid">
									${aiarticle}
									</div>	
									<div class="linkcontaner">
										<a href="${aibase}/my/center/article" class="baselink">我的文章</a>
										<a href="${aibase}/my/center/article" class="baselink">草稿箱</a>
										<a href="${aibase}/contribute" class="baselink">投稿</a>
									</div>
								</div>
							</li>
							<li class="blockitem" id="a8" style="display:none">
								<h2>
									<a href="${careerbase}" target="_blank" style="font-weight:bold;">天才职场</a>
									<a href="#" data-modelid="8" class="deleteModel">隐藏</a>
								</h2>
								<h3 id="msgh8">
									<span>收件箱<a href="${careerbase}/my/center/msg" class="number" target="_blank">&nbsp;</a></span>
								</h3>
								<div class="libottom">
									<div class="judgemid">
										<a href="${careerbase}" class="workimg">
											<img src="${baseimg}/imgs/default/talentjob_index.png" alt="" class="leftimg">
											<span>首页</span>
										</a>
										<a href="${careerbase}/catalogue/12" class="workimg">
											<img src="${baseimg}/imgs/default/talentjob_career.png" alt="" class="leftimg">
											<span>职场困惑</span>
										</a>
									</div>	
									<div class="linkcontaner">
										<a href="${careerbase}/test?cid=1" class="baselink">职业测试</a>
										<a href="${careerbase}/my/center/article" class="baselink">我的文章</a>
										<a href="${careerbase}/contribute" class="baselink">投稿</a>
										<a href="${careerbase}/catalogue/11" class="baselink">去吐槽</a>
									</div>
								</div>
							</li>
						
							<li class="blockitem" id="a9" style="display:none">
								<h2>
									<a href="${gupagebase}" target="_blank" style="font-weight:bold;">顾雏军专栏</a>
									<a href="#" data-modelid="9" class="deleteModel">隐藏</a>
								</h2>
								<div class="libottom">
									<ul class="mergelist" style=" margin-top: 30px;">
										<li>
											<a href="${gupagebase}/gupage/news/11">
												<img src="${baseimg}/imgs/default/dynamic.png" alt="" />
												<a href="${gupagebase}/gupage/news/11" class="mergetitle">动态</a>
											</a>
										</li>
										<li>
											<a href="${gupagebase}/gupage/fruit">
												<img src="${baseimg}/imgs/default/achievements.png" alt="" />
												<a href="${gupagebase}/gupage/fruit" class="mergetitle">科研成果</a>
											</a>
										</li>
										<li>
											<a href="${gupagebase}/catalogue/photo">
												<img src="${baseimg}/imgs/default/photos.png" alt="" />
												<a href="${gupagebase}/catalogue/photo" class="mergetitle">精彩照片</a>
											</a>
										</li>
										<li>
											<a href="${gupagebase}/gupage/debate">
												<img src="${baseimg}/imgs/default/contend.png" alt="" />
												<a href="${gupagebase}/gupage/debate" class="mergetitle">郎顾之争</a>
											</a>
										</li>
									</ul>	
									<div class="linkcontaner" style=" margin-top: 17px;">
										<a href="${gupagebase}" class="baselink">进入首页</a>
										<a href="${gupagebase}/catalogue/video" class="baselink">查看视频</a>
									</div>
								</div>
							</li>
							<li class="blockitem" id="a10" style="display:none">
								<h2>
									<a href="${enterpriserbase}" target="_blank" style="font-weight:bold;">引资购商</a>
									<a href="#" data-modelid="10" class="deleteModel">隐藏</a>
								</h2>
								<h3 class="havenote" id="msgh10">
									<span>收件箱<a href="${enterpriserbase}/my/center/msg" class="number" target="_blank">&nbsp;</a></span>
								</h3>
								<div class="libottom">
									<ul class="mergelist">
										<li>
											<a href="${enterpriserbase}/catalogue/0/9">
												<img src="${baseimg}/imgs/default/attractChair.png" alt="" />
												<a href="${enterpriserbase}/catalogue/0/9" class="mergetitle">商城</a>
											</a>
										</li>
										<li>
											<a href="${enterpriserbase}/forums/2/22?cid=22&pcid=2&pagesize=5&FirstLoadSize=15">
												<img src="${baseimg}/imgs/default/attractForum.png" alt="" />
												<a href="${enterpriserbase}/forums/2/22?cid=22&pcid=2&pagesize=5&FirstLoadSize=15" class="mergetitle">引资购商论坛</a>
											</a>
										</li>
										<li>
											<a href="${enterpriserbase}/catalogue/6/61?cid=6&pcid=0&pagesize=5&FirstLoadSize=15">
												<img src="${baseimg}/imgs/default/case.png" alt="" />
												<a href="${enterpriserbase}/catalogue/6/61?cid=6&pcid=0&pagesize=5&FirstLoadSize=15" class="mergetitle">并购产业基金</a>
											</a>
										</li>
										<li>
											<a href="${enterpriserbase}/content/0/7?cid=7&pcid=0">
												<img src="${baseimg}/imgs/default/cooperation.png" alt="" />
												<a href="${enterpriserbase}/content/0/7?cid=7&pcid=0" class="mergetitle">互助合作平台</a>
											</a>
										</li>
									</ul>
									<div class="linkcontaner">
										<a href="${enterpriserbase}" class="baselink">首页</a>
										<a href="${enterpriserbase}/enterpriser/videodetail/9c9e68c8c014494dbd1398d3f15342a1" class="baselink">观看视频</a>
										<a href="${enterpriserbase}/contribute?cid=7" class="baselink">投稿</a>
									</div>	
								</div>
							</li>
							<li class="blockitem" id="a11" style="display:none">
								<h2>
									<a href="${lifebase}" target="_blank" style="font-weight:bold;">天才人生</a>
									<a href="#" data-modelid="11" class="deleteModel">隐藏</a>
								</h2>
								<h3 id="msgh11">
									<span>消息<a href="${lifebase}/my/center/msg" class="number" target="_blank">&nbsp;</a></span>
								</h3>
								<div class="libottom">
									<div class="judgemid">
									${lifearticle}
									</div>	
									<div class="linkcontaner">
										<a href="${lifebase}/my/center/article" class="baselink">我的文章</a>
										<a href="${lifebase}/my/center/collect" class="baselink">我的收藏</a>
										<a href="${lifebase}/my/contribute?cid=1" class="baselink">投稿</a>
										<a href="${lifebase}/my/center/question" class="baselink">我的提问</a>
										<a href="${lifebase}/my/center/topic" class="baselink">我的话题</a>
									</div>
								</div>
							</li>
						</ul>
					[/#if]
				<div class="clear" style=" height: 22px;"></div>
			</div>
		</div>
		<!-- 主体部分结束 -->
		<div class="mask"></div>
		<div class="maskcontent modalcontainer" style="height: 100%;overflow-y: auto">
			<div class="modalmain">
				<ul class="modalblocks">
					<li id="1">
						<div class="blockscontent">
							<span class="radiobox checkedspan"></span>
							<div class="blocksmid">
								<h2>职业经理人培训</h2>
							</div>
							<div class="turnbutton">
								<a href="javascript:;" class="forhead"></a>
								<a href="javascript:;" class="nexthead"></a>
							</div>
						</div>
					</li>
					<li id="2">
						<div class="blockscontent">
							<span class="radiobox checkedspan"></span>
							<div class="blocksmid">
								<h2>职业道德培训</h2>
							</div>
							<div class="turnbutton">
								<a href="javascript:;" class="forhead"></a>
								<a href="javascript:;" class="nexthead"></a>
							</div>
						</div>
					</li>
					<li id="3">
						<div class="blockscontent">
							<span class="radiobox checkedspan"></span>
							<div class="blocksmid">
								<h2>天财评论</h2>
							</div>
							<div class="turnbutton">
								<a href="javascript:;" class="forhead"></a>
								<a href="javascript:;" class="nexthead"></a>
							</div>
						</div>
					</li>
					<li id="4">
						<div class="blockscontent">
							<span class="radiobox checkedspan"></span>
							<div class="blocksmid">
								<h2>企业家培训</h2>
							</div>
							<div class="turnbutton">
								<a href="javascript:;" class="forhead"></a>
								<a href="javascript:;" class="nexthead"></a>
							</div>
						</div>
					</li>
					<li id="5">
						<div class="blockscontent">
							<span class="radiobox checkedspan"></span>
							<div class="blocksmid">
								<h2>天才创业</h2>
							</div>
							<div class="turnbutton">
								<a href="javascript:;" class="forhead"></a>
								<a href="javascript:;" class="nexthead"></a>
							</div>
						</div>
					</li>
					<li id="6">
						<div class="blockscontent">
							<span class="radiobox checkedspan"></span>
							<div class="blocksmid">
								<h2>人间仙境</h2>
							</div>
							<div class="turnbutton">
								<a href="javascript:;" class="forhead"></a>
								<a href="javascript:;" class="nexthead"></a>
							</div>
						</div>
					</li>
					<li id="7">
						<div class="blockscontent">
							<span class="radiobox checkedspan"></span>
							<div class="blocksmid">
								<h2>天才AI</h2>
							</div>
							<div class="turnbutton">
								<a href="javascript:;" class="forhead"></a>
								<a href="javascript:;" class="nexthead"></a>
							</div>
						</div>
					</li>
					<li id="8">
						<div class="blockscontent">
							<span class="radiobox checkedspan"></span>
							<div class="blocksmid">
								<h2>天才职场</h2>
							</div>
							<div class="turnbutton">
								<a href="javascript:;" class="forhead"></a>
								<a href="javascript:;" class="nexthead"></a>
							</div>
						</div>
					</li>
					<li id="9">
						<div class="blockscontent">
							<span class="radiobox checkedspan"></span>
							<div class="blocksmid">
								<h2>顾雏军专栏</h2>
							</div>
							<div class="turnbutton">
								<a href="javascript:;" class="forhead"></a>
								<a href="javascript:;" class="nexthead"></a>
							</div>
						</div>
					</li>
					<li id="10">
						<div class="blockscontent">
							<span class="radiobox checkedspan"></span>
							<div class="blocksmid">
								<h2>引资购商</h2>
							</div>
							<div class="turnbutton">
								<a href="javascript:;" class="forhead"></a>
								<a href="javascript:;" class="nexthead"></a>
							</div>
						</div>
					</li>
					<li id="11">
						<div class="blockscontent">
							<span class="radiobox checkedspan"></span>
							<div class="blocksmid">
								<h2>天才人生</h2>
							</div>
							<div class="turnbutton">
								<a href="javascript:;" class="forhead"></a>
								<a href="javascript:;" class="nexthead"></a>
							</div>
						</div>
					</li>
				</ul>
				<div class="helpnote" style="margin-left: -45%;">点击右侧按钮来调节显示顺序</div>
				<div class="checkbtns">
					<button class="cancle" id="cancle">取消</button>
					<button class="save" id="save">保存</button>
				</div>
			</div>
			[/#if]
		</div> 
		<!-- 非会员提醒 -->
		<div class="normalUser">
			<h2>
				温馨提示
				<a href="javascripst:;" class="closebtn">X</a>
			</h2>
			<div class="normalTipbox">
				<p>目前该板块仅对超天才会员开放，成为超天才会员，享更多特权</p>
				<a href="${base}/register_improve/${me.uid}" class="beuser">加入超天才会员</a>
			</div>
		</div>
		<!-- 主体部分结束 -->
<script type="text/javascript">
   var UserMsgUrl = { //防止与全站消息的数组名冲突
      "finance":"${financebase!}/ajax/msg/msg_jsonp?callback=?",
      "startup":"${startupbase!}/ajax/msg/msg_jsonp?callback=?",
      "ai":"${aibase!}/ajax/msg/msg_jsonp?callback=?",
      "life":"${lifebase!}/ajax/msg/msg_jsonp?callback=?",
      "career":"${careerbase!}/ajax/msg/msg_jsonp?callback=?",
      "enterpriser":"${enterpriserbase!}/ajax/msg/msg_jsonp?callback=?",
	  "moral":"${moralbase}/ajax/msg/msg_jsonp?callback=?",
	  "manager":"${managerbase}/ajax/msg/msg_jsonp?callback=?"
	  [#--"tpi":"${tpibase}/ajax/mycenter/msg_jsonp?callback=?"--]
   }
   
   var contentUrl = {
      "finance":"${financebase}/ajax/topArticle_jsonp?callback=?",
      "moral":"${moralbase}/ajax/doc/doc_jsonp?callback=?",
      "manager":"${managerbase}/ajax/dynamic_jsonp?callback=?"
   }
</script>
		<script>
			$(function() {
				//初始化模块
				[#if maskorder??]
					initMask();
				[#else]
					$(".blockitem").css("display","block");
				[/#if]
				$(".blockitem:visible:odd").addClass("liodd");
				/*打开弹框*/
				$(".modals").click(function(){
					$(".maskcontent").show();
					$(".mask").show();
				});
				$(".radiobox").click(function(){
					$(this).toggleClass("checkedspan");
				});
				$(".blocksmid").click(function() {
					$(this).prev().toggleClass("checkedspan");
				});
				$(".checkbtns .cancle").click(function(){
					$(".maskcontent").hide();
					$(".mask").hide();
				});
				$(".checkbtns .save").click(function(){
					$(".maskcontent").hide();
					$(".mask").hide();
				});
				$(".deleteModel").click(function(){
					var modelid = $(this).data("modelid");
					$("#" + modelid).children().find(".radiobox").toggleClass("checkedspan");
					$("#save").click();
				});
				$(".normalUsertip").click(function () {
					$(".normalUser").show();
					$(".mask").show();
				});
				$(".mask").click(function () {
					$(".normalUser").hide();
					$(".mask").hide();
				});
				$(".closebtn").click(function () {
					$(".normalUser").hide();
					$(".mask").hide();
				});
				/*按钮切换顺序*/
				function turns() {

					/*向上切换*/
					$(".forhead").click(function(){
						var curli = $(this).parent().parent().parent();
						var curid = curli.attr("id");
						var lastid = curli.prev().attr("id");
						$("#" + curid).after($("#" + lastid));
					});

					/*向下切换*/
					$(".nexthead").click(function(){
						var curli = $(this).parent().parent().parent();
						var curid = curli.attr("id");
						var nextid = curli.next().attr("id");
						$("#" + nextid).after($("#" + curid));
					});
				}

				turns();
				function checkShow() {
					$(".blockitem").removeClass("liodd");
					/*弹框中未选中项在页面上隐藏*/
					var nochecks = $(".radiobox:not('.checkedspan')").parent().parent();;
					for(var i = 0; i < nochecks.length; i++) {
						var curcheck = parseInt(nochecks.eq(i).attr("id"));
						/*找到对应的a+i，并设置隐藏*/
						$("#a" + curcheck).css("display","none");
					}
					/*获取弹框中选中的*/
					var checklis = $(".radiobox.checkedspan").parent().parent();
					for(var i = 0; i < checklis.length; i++) {
						var curcheck = parseInt(checklis.eq(i).attr("id"));
						/*找到对应的a+i，并设置位置*/
						$("#a" + curcheck).addClass("position" + i);
					}
					var ulheight = Math.ceil((checklis.length)/2) * 272;
					$(".mainbottom").height(ulheight);
					$(".blockitem:visible:odd").addClass("liodd");
				}
				/*点击保存按钮进行定位排序*/
				$("#save").click(function(){
					/*点击之后移除上次点击样式*/
					$(".blockitem").each(function(i){
						$(".blockitem").removeClass("position"+i);
						$(".blockitem").css("display","block");
					});
					getShowOrder();
					checkShow();
				});
			});
				
				//获取新的模块顺序并保存到文件中
				function getShowOrder() {
					var orders = [];
					var url = "${base}/ajax/my/maskorder";
					$(".radiobox.checkedspan").parent().parent().each(function() {
						orders.push($(this).attr("id"));
					});
					$.post(url, {'maskorder' : orders.join(",")}, function(data) {
						if (data.toString() == '{"islogin":false}') {
							gourl("${base}/login");
						} else if (data == 'success') {
							return true;
						} else {
							return false;
						}
					});
				}
				
				//如果用户自定义过模块，就初始化模块
				function initMask() {
					$(".radiobox").removeClass("checkedspan");
					[#assign i = 0]
					[#assign temp = 0]
					[#list maskorder as item]
						$("#a${item}").css("display","block").addClass("position${i}");
						$("#${item} .radiobox").addClass("checkedspan");
						[#if i == 0]
							$(".modalblocks li:first").before($("#${item}"));
						[#else]
							$("#${temp}").after($("#${item}"));
						[/#if]
						[#assign i = i + 1]
						[#assign temp = item]
					[/#list]
					$(".mainbottom").height(Math.ceil((${i})/2) * 272);
				}
				
			  $(function(){
			  		//从各平台获取消息
					var spans1=$("#msgh1 span");//职业经理人
					$.getJSON(UserMsgUrl.manager,function(data){
				       $(spans1[0]).find("a").html(data.countcomment);
					   $(spans1[1]).find("a").html(data.countnotice);
					   $(spans1[2]).find("a").html(data.countsysmsg);
				    });
					var spans2=$("#msgh2 span");//职业道德
				    $.getJSON(UserMsgUrl.moral,function(data){
				       $(spans2[0]).find("a").html(data.countcomment);
					   $(spans2[1]).find("a").html(data.countnotice);
					   $(spans2[2]).find("a").html(data.countsysmsg);
				    });
				    var spans3=$("#msgh3 span");//天财评论
				    $.getJSON(UserMsgUrl.finance,function(data){
				       $(spans3[0]).find("a").html(data.countcomment);
					   $(spans3[1]).find("a").html(data.countnotice);
					   $(spans3[2]).find("a").html(data.countsysmsg);
				    });
				    var spans5=$("#msgh5 span");//天财创业
				    $.getJSON(UserMsgUrl.startup,function(data){
				       $(spans5[0]).find("a").html(data.countnotice);
				    });
				    var spans7=$("#msgh7 span");//天财AI
				    $.getJSON(UserMsgUrl.ai,function(data){
				       $(spans7[0]).find("a").html(data.countcomment);
				    });
				    var spans8=$("#msgh8 span");//天财职场
				    $.getJSON(UserMsgUrl.career,function(data){
				       $(spans8[0]).find("a").html(data.countcomment);
				    });
				    var spans10=$("#msgh10 span");//引资购商
				    $.getJSON(UserMsgUrl.enterpriser,function(data){
				       $(spans10[0]).find("a").html(data.countcomment);
				    });
				    var spans11=$("#msgh11 span");//天才人生
				    $.getJSON(UserMsgUrl.life,function(data){
				       $(spans11[0]).find("a").html(data.countcomment);
				    });
					[#--var spans5=$("#msgh5 span");//并购平台
					$.getJSON(UserMsgUrl.tpi,function(data){
					  $(spans5[0]).find("a").html(data.count);
				    });--]

					//从各平台获取内容
					//moral
					$.getJSON(contentUrl.moral, function(data){
						$("#moralvideoa").attr("href","${moralbase}/mycenter/doc/video/"+data.uid);
						//$("#moralvideoa img").attr("src","${webimg}"+data.img);
						var moralUL = $("#moralcontent");
						for (var i = 0; i < data.list.length; i++) {
					        var doc=data.list[i];
					        moralUL.append("<li><a href='${moralbase}/mycenter/download/doc/"+doc.uid+"'><icon class='dot'></icon>"+doc.name+"</a></li>");
					    }
						
					});
				    //finance
					[#-- var financeUl = $("#financecontent .judgelist");
					$.getJSON(contentUrl.finance, function(data){
					    for(var i = 0; i < 4; i++){//因为finance那边设置置顶为4条数据，页面只能显示4条,因此使用4，而没有使用data.list.length
					        var article=data.list[i];
					        financeUl.append("<li><a href='${financebase}/geniusfocus/"+article.oid+"'><icon class='dot'></icon>"+article.title+"</a></li>");
					    }
					    $("#financeimga").attr("href", "${financebase}/geniusfocus/"+data.list[0].oid);
					    $("#financeimga img").attr("src", "${webimg}"+data.list[0].img);
					}); --]
					//tpi
					[#--var tpiimg = $("#tpicontent li img");
					var tpib = $("#tpicontent li b");
					$.getJSON(contentUrl.tpi, function(data){
						tpiimg.eq(0).attr("src","${webimg}"+data.projImg);
						tpib.eq(0).html(data.projCount);
						tpiimg.eq(1).attr("src","${webimg}"+data.teamImgLittle);
						tpib.eq(1).html(data.teamCount);
						tpiimg.eq(2).attr("src","${webimg}"+data.newsImgLittle);
						tpib.eq(2).html(data.newsCount);
						tpiimg.eq(3).attr("src","${webimg}"+data.caseImgLittle);
						tpib.eq(3).html(data.caseCount);
					});--]
					//manager
					var managerUl = $("#managercontent .judgelist");
					$.getJSON(contentUrl.manager,function(data){
						for(var i in data.dynamic){
							 var dynamic=data.dynamic[i];
							 var html = "";
							 if (dynamic.dataMap_href != null && dynamic.dataMap_href != "")
							 	html = "<li><a href='" + dynamic.dataMap_href + "'" + " title='"+dynamic.title+"'><icon class='dot'></icon>"+dynamic.title+"</a></li>";
							 else
							 	html = "<li><icon class='dot'></icon>"+dynamic.title+"</li>";
							 managerUl.append(html);
						}
					});
				});
		</script>
		<script src="${basejs}/js/libs/mask.js"></script>
</body>
</html>