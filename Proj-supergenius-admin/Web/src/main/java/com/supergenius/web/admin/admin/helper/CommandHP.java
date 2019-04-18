package com.supergenius.web.admin.admin.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.genius.server.base.helper.BaseHP;
import com.supergenius.web.admin.ai.helper.AiArticleHP;
import com.supergenius.web.admin.career.helper.CareerArticleHP;
import com.supergenius.web.admin.enterpriser.hellper.EnterpriserArticleHP;
import com.supergenius.web.admin.entrepreneur.hellper.EntrepreneurArticleHP;
import com.supergenius.web.admin.finance.helper.FinanceArticleHP;
import com.supergenius.web.admin.gupage.helper.GupageArticleHP;
import com.supergenius.web.admin.life.helper.LifeArticleHP;
import com.supergenius.web.admin.managernews.helper.ManagernewsArticleHP;
import com.supergenius.web.admin.moralnews.helper.MoralnewsArticleHP;
import com.supergenius.web.admin.startup.helper.StartupArticleHP;

/**
 * 天财评论文章HP（管理后台）
 * @author liushaomin
 */
public class CommandHP extends BaseHP {

	private static Logger log = LoggerFactory.getLogger(CommandHP.class);

	/**
	 * 天财评论全站初始化全站索引
	 * 
	 * @author YangGuang
	 * @createtime 2018年1月13日14:29:14
	 */
	public static void initializeSearchIndex_finance() {
		FinanceArticleHP.initializeSearchIndex();
		log.info("finished StartupArticleHP.initializeSearchIndex()");
	}
	
	/**
	 * 创业平台全站初始化全站索引
	 * 
	 * @author 许志翔
	 * @createtime 2017年9月2日15:06:45
	 */
	public static void initializeSearchIndex_startup() {
		StartupArticleHP.initializeSearchIndex();
		log.info("finished StartupArticleHP.initializeSearchIndex()");
	}
	
	/**
	 * 天才ai全站初始化全站索引
	 * 
	 * @author 杨光
	 * @createtime 2017年9月28日09:37:27
	 */
	public static void initializeSearchIndex_ai() {
		AiArticleHP.initializeSearchIndex();
		log.info("finished AiArticleHP.initializeSearchIndex()");
	}

	/**
	 * 天才职场全站初始化全站索引
	 * 
	 * @author 杨光
	 * @createtime 2017年9月28日09:37:27
	 */
	public static void initializeSearchIndex_career() {
		CareerArticleHP.initializeSearchIndex();
		log.info("finished AiArticleHP.initializeSearchIndex()");
	}
	
	/**
	 * 顾雏军专栏全站初始化全站索引
	 * 
	 * @author 杨光
	 * @createtime 2018年1月13日14:31:35
	 */
	public static void initializeSearchIndex_gupage() {
		GupageArticleHP.initializeSearchIndex();
		log.info("finished AiArticleHP.initializeSearchIndex()");
	}
	
	/**
	 * 引资购商全站初始化全站索引
	 * 
	 * @author YangGuang
	 * @createtime 2018年1月13日14:31:35
	 */
	public static void initializeSearchIndex_enterpriser() {
		EnterpriserArticleHP.initializeSearchIndex();
		log.info("finished AiArticleHP.initializeSearchIndex()");
	}
	
	/**
	 * 天才人生全站初始化全站索引
	 * 
	 * @author YangGuang
	 * @createtime 2018年6月6日15:32:19
	 */
	public static void initializeSearchIndex_life() {
		LifeArticleHP.initializeSearchIndex();
		log.info("finished AiArticleHP.initializeSearchIndex()");
	}
	
	/**
	 * 职业经理人培训文章全站初始化全站索引
	 * 
	 * @author tf
	 * @createtime 2018年7月24日
	 */
	public static void initializeSearchIndex_managernews() {
		ManagernewsArticleHP.initializeSearchIndex();
		log.info("finished AiArticleHP.initializeSearchIndex()");
	}
	
	/**
	 * 企业家培训文章全站初始化全站索引
	 * 
	 * @author tf
	 * @createtime 2018年7月24日
	 */
	public static void initializeSearchIndex_entrepreneur() {
		EntrepreneurArticleHP.initializeSearchIndex();
		log.info("finished AiArticleHP.initializeSearchIndex()");
	}
	
	/**
	 * 职业道德培训文章全站初始化全站索引
	 * 
	 * @author tf
	 * @createtime 2018年7月24日
	 */
	public static void initializeSearchIndex_moralnews() {
		MoralnewsArticleHP.initializeSearchIndex();
		log.info("finished MoralnewsArticleHP.initializeSearchIndex()");
	}
}
