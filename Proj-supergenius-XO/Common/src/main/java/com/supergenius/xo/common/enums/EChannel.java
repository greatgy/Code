package com.supergenius.xo.common.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 频道页或栏目页
 * 
 * @author architect.bian
 * 2014-6-24 上午11:55:24
 */
public enum EChannel {

	index("0"),
	finance("1"),
	debate("2"),
	debatearticle("3"),
	topic("4"),
	contribute("5"),
	comments("6"),
	article("7"),
	team("8"),//团队
	type("9"),//团队类别
	project("10"),//项目
	tpiuser("11"),// 并购平台会员管理
	wishdetail("12"),//并购方案管理
	notice("13"),//招聘信息管理
	comment("14"),
	investment("15"),//投资
	mergers("16"),//并购机构;
	message("17"),//消息中心管理
	emailtemplate("18"),//邮件模板管理
	
	//官网后台
	news("19"),//新闻管理
	official("20"),//官网
	banner("21"),//轮播管理
	officialarticle("22"),//官网文章
	characterscontent("23"),//网站内容管理(文字内容)
	htmlcontent("24"),//网站内容管理(HTML代码)
	recruit("25"),//网站招聘信息
	discuss("26"),//评论互动
	video("27"),//视频管理
	solrcontent("110"),//搜索推荐管理
	
	//职业道德培训
	moral("28"),
	doc("29"),//培训讲义
	cases("30"),//案例
	userdoc("31"),//学员分享
	student("32"),//学员
	exam("33"),//考试
	qst("34"),//题库
	moralarticle("35"),//职业道德发帖
	moralvideo("38"),//职业道德视频
	moralmessage("39"),//职业道德系统消息
	announcement("40"),//社区公告
	moraltalk("41"),//道德杂谈
	
	//支付
	account("36"),
	recharge("37"),//充值
	supergeniusadmin("42"),//超天才管理后台
	
	collect("43"),//收藏
	subscribe("44"),//订阅
	
	tpi("45"),//并购平台
	mergecase("46"),//并购案例
	tpicontent("47"),//网站内容
	mergenews("48"),//并购动态
	recommend("49"),//投资机构
	
	quotes("60"),//名人名言管理
	
	//会员中心
	order("100"),//订单中心
	user("101"),//用户管理
	member("102"),//会员中心
	consumer("106"),//用户中心
	emaillog("103"),//群发邮件
	trade("104"),//交易管理
	visitor("105"),//交易管理
	
	//职业经理人
	manager("140"),//职业经理人
	judge("141"),//裁判管理
	appjudge("142"),//裁判申请管理
	expert("143"),//专家管理
	appexpert("144"),//专家申请管理
	managervideo("145"),//视频管理
	challenge("146"),//挑战管理
	complaint("147"),//举报管理
	certificate("148"),//证书管理
	reply("149"),//答辩管理
	appstudent("150"),//申请学员管理
	conference("151"),//会议室管理
	
	//企业家培训
	enterpriser("170"),//企业家培训
	lecture("171"),//讲座管理
	participate("172"),//报名人管理
	enterpriserhtmlcontent("173"),//网站html页面管理
	appcooperation("174"),//互助合作平台报名管理
	
	//创业平台
	question("190"),//试题管理
	ruler("191"),//规则管理
	statistic("192"),//统计信息
	music("193"),//背景音乐管理
	label("194"),//标签管理
	startupcontent("195"),//广告位管理
	startupcomments("196"),//评论管理
	startuparticle("197"),//文章管理
	startupcatalogue("198"),//模块管理
	info("199"),//个人信息
	msg("200"),//消息通知
	
	//天才AI
	aicontent("220"),//AI内容管理
	aicomments("221"),//AI评论管理
	aiarticle("224"),//AI文章管理
	aicatalogue("225"),//AI模块管理
	ailabel("222"),//AI标签管理
	aimsg("223"),//消息通知
	
	//天才职场
	careerbanner("229"),//banner管理
	careercomplainarticle("230"),//职场吐槽-文章管理
	careercomplainarea("231"),//职场吐槽-专区管理
	careerpuzzledarticle("232"),//职场困惑-文章管理
	careerpuzzledproblem("233"),//职场困惑-问题管理
	careerpuzzledanswer("234"),//职场困惑-回答管理
	careergossip("235"),//职场八卦
	careertease("236"),//职场鬼话
	careerexperience("237"),//职场心得
	careerguide("238"),//应聘指南
	careercomments("239"),//评论管理
	careercatalogue("240"),//模块管理
	careercontent("241"),//广告位管理
	careermsg("242"),//消息通知
	careerquestion("243"),//题目管理
	careerruler("244"),//规则管理
	careerstatistic("245"),//统计信息
	careerarticle("246"),//职场文章管理
	careerpuzzledsecondanswer("247"),//二级回答
	mycenter("248"),//个人中心首页
	careerrulercontent("249"),//规则内容管理
	careermusic("250"),//规则内容管理

	//天财评论
	financecomments("251"),
	financecontent("252"),
	economics("260"), //财经资讯
	invest("261"), // 投资资讯
	merger("262"), // 并购资讯
	risk("263"), // 风险资讯
	technology("264"), // 科技资讯
	gold("265"), // 醒世金语
	entrepreneur("266"), // 企业家
	profession("267"), // 职业经理人
	financelabel("269"),//标签管理
	nightwords("268"), // 职场夜话
	financecatalogue("270"), //模块管理
	financearticle("282"), //管理
	
	//顾雏军专栏	
	gupagephoto("271"),
	gupagevideo("272"), 	
	gupagecatalogue("273"),  //模块管理
	gupagepager("274"),  //论文管理
	gupagepatent("275"),  //专利管理
	gupagecomments("276"),	//评论管理
	gupagecontent("277"),	//广告位管理
	gupagearticle("279"), 
	gupagedebate("280"),
	gupagenews("281"),	
	
	//引资购商
	enterprisercatalogue("175"), //模块管理
	enterpriservideo("285"),
	enterpriserlabel("286"),
	enterpriserforum("287"),
	enterpriserphoto("288"),
	enterprisercontent("289"), //广告位管理
	enterprisercomments("290"),//评论管理
	upmadeinchina("291"),//中国制造提升
	industryfund("292"),//并购产业基金
	crossmerger("293"),//跨境并购
	internationalmerger("294"),//国际并购
	card("295"),//个人中心   我的帖子
	enterpriserarticle("296"),//引资购商文章管理
	
	//天才人生
	lifearticle("301"),//文章管理
	lifeproblem("302"),//问题管理
	lifeanswer("303"),//回答管理
	lifetopic("304"),//话题管理
	lifead("305"),//广告位管理
	lifebanner("306"),//banner管理
	lifeessay("307"),//动态管理
	lifecontent("308"),//内容管理
	lifecomments("309"),//评论管理
	lifecatalogue("310"),//模块管理
	lifesubject("311"),//科目管理
	lifecourse("312"),//课程管理
	lifevideo("313"),//视频管理

	//职业经理人培训文章
	managernewscatalogue("402"),//模块管理
	managernewsad("405"),//广告位管理
	managernewscomments("406"),//评论管理
	managernewsarticle("407"),//文章管理
	managernewscontent("408"),//内容管理
	
	//企业家培训
	entrepreneurarticle("501"),//文章管理
	entrepreneurcatalogue("502"),//模块管理
	entrepreneurad("503"),//广告位管理
	entrepreneurcomments("504"),//评论管理
	//职业道德培训文章
	moralnewscomments("601"),//评论管理
	moralnewscatalogue("602"),//模块管理
	moralnewsad("605"),//广告位管理
	moralnewsannouncement("606"),//公告管理
	moralnewsarticle("607"),//文章管理
	moralnewssimplearticle("608");//帖子管理
	private final String value;
	private EChannel(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}
	
	public int toInt() {
		return Integer.valueOf(this.value);
	}

	public static EChannel get(int v) {
		String str = String.valueOf(v);
		for (EChannel e : values()) {
			if (e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}

	public static EChannel get(String name) {
		for (EChannel e : values()) {
			if (e.name().equals(name)) {
				return e;
			}
		}
		return null;
	}

	public static String getName(EChannel e) {
		return I18N.getEnumName(e, Locale.CHINA);
	}

	public static String getName(EChannel e, Locale locale) {
		return I18N.getEnumName(e, locale);
	}
	
	public String getName() {
		return I18N.getEnumName(this, Locale.CHINA);
	}

}
