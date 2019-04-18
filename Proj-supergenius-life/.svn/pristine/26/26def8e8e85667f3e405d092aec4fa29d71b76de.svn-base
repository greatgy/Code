package com.supergenius.global.conf;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.genius.core.base.conf.BaseWebConf;
import com.genius.core.base.utils.GlobalUtil;

/**
 * @author Architect.bian
 * 
 */
@Component
public class WebConf extends BaseWebConf {

	public static String FinanceBaseRootPath;
	public static String MoralBaseRootPath;
	public static String TpiBaseRootPath;
	public static String ManagerBaseRootPath;
	public static String EnterpriserBaseRootPath;
	public static String AccountBaseRootPath;
	public static String OfficialBaseRootPath;
	public static String StartupBaseRootPath;
	public static String UserBaseRootPath;
	public static String AiBaseRootPath;
	public static String CareerBaseRootPath;
	public static String GupageBaseRootPath;
	public static String LifeBaseRootPath;
	public static String LoginURL;
	public static String DefaultAnonymousName;
	public static String pageSplitter = GlobalUtil.getUUID();// 防止在没配置的情况下将所有的字符分割，uid则不会分割
	public static String SolrSearchUrl;
	public static String ManagernewsBaseRootPath;
	public static String EntrepreneurBaseRootPath;
	@Value("#{web}")
	public void setWebConf(Properties prop) {
		WebConf.FinanceBaseRootPath=prop.getProperty("FinanceBaseRootPath");
		WebConf.MoralBaseRootPath=prop.getProperty("MoralBaseRootPath");
		WebConf.TpiBaseRootPath=prop.getProperty("TpiBaseRootPath");
		WebConf.ManagerBaseRootPath=prop.getProperty("ManagerBaseRootPath");
		WebConf.EnterpriserBaseRootPath=prop.getProperty("EnterpriserBaseRootPath");
		WebConf.AccountBaseRootPath=prop.getProperty("AccountBaseRootPath");
		WebConf.OfficialBaseRootPath=prop.getProperty("OfficialBaseRootPath");
		WebConf.LoginURL = prop.getProperty("LoginURL");
		WebConf.SolrSearchUrl = prop.getProperty("SolrSearchUrl");
		WebConf.DefaultAnonymousName = prop.getProperty("DefaultAnonymousName");
		WebConf.StartupBaseRootPath=prop.getProperty("StartupBaseRootPath");
		WebConf.UserBaseRootPath=prop.getProperty("UserBaseRootPath");
		WebConf.AiBaseRootPath=prop.getProperty("AiBaseRootPath");
		WebConf.CareerBaseRootPath=prop.getProperty("CareerBaseRootPath");
		WebConf.LifeBaseRootPath=prop.getProperty("LifeBaseRootPath");
		WebConf.GupageBaseRootPath=prop.getProperty("GupageBaseRootPath");
		WebConf.ManagernewsBaseRootPath=prop.getProperty("ManagernewsBaseRootPath");
		WebConf.EntrepreneurBaseRootPath=prop.getProperty("EntrepreneurBaseRootPath");
	}

}


