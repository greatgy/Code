package com.supergenius.global.helper;

import java.util.Map;

import com.supergenius.global.conf.WebConf;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.web.front.life.helper.IndexHP;

/**
 * 系统操作，如为拦截器提供初始化view层参数等
 * 
 * @author architect.bian
 * @createtime 2014-7-24 下午8:30:36
 */
public class SysHP extends com.genius.server.base.helper.SysHP {

	/**
	 * 初始化前台页面的参数
	 * @param model
	 * @author Architect.bian
	 * @createtime 2014-7-24 下午8:29:08
	 */
	public static void initWebPageVars(Map<String, Object> model) {
		model.put(ViewKeyDict.financeBaseRootPath, WebConf.FinanceBaseRootPath);
		model.put(ViewKeyDict.moralBaseRootPath, WebConf.MoralBaseRootPath);
		model.put(ViewKeyDict.tpiBaseRootPath, WebConf.TpiBaseRootPath);
		model.put(ViewKeyDict.managerBaseRootPath, WebConf.ManagerBaseRootPath);
		model.put(ViewKeyDict.enterpriserBaseRootPath, WebConf.EnterpriserBaseRootPath);
		model.put(ViewKeyDict.accountBaseRootPath, WebConf.AccountBaseRootPath);
		model.put(ViewKeyDict.officialBaseRootPath, WebConf.OfficialBaseRootPath);
		model.put(ViewKeyDict.startupBaseRootPath, WebConf.StartupBaseRootPath);
		model.put(ViewKeyDict.userBaseRootPath, WebConf.UserBaseRootPath);
		model.put(ViewKeyDict.aiBaseRootPath, WebConf.AiBaseRootPath);
		model.put(ViewKeyDict.careerBaseRootPath, WebConf.CareerBaseRootPath);
		model.put(ViewKeyDict.gupageBaseRootPath, WebConf.GupageBaseRootPath);
		model.put(ViewKeyDict.lifeBaseRootPath, WebConf.LifeBaseRootPath);
		model.put(ViewKeyDict.managernewsBaseRootPath, WebConf.ManagernewsBaseRootPath);
		model.put(ViewKeyDict.entrepreneurBaseRootPath, WebConf.EntrepreneurBaseRootPath);
		model.put(ViewKeyDict.SolrSearchUrl, WebConf.SolrSearchUrl);
		model.put(ViewKeyDict.cataloguelist, IndexHP.getCatalogueList());// 获得导航栏 
	}
}
