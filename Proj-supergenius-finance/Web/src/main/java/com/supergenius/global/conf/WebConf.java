package com.supergenius.global.conf;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.genius.core.base.conf.BaseWebConf;

/**
 * @author Architect.bian
 * 
 */
@Component
public class WebConf extends BaseWebConf {

	public static String MoralBaseRootPath;
	public static String TpiBaseRootPath;
	public static String OfficialBaseRootPath;
	public static String ManagerBaseRootPath;
	public static String UserBaseRootPath;
	public static String StartupBaseRootPath;
	public static String EnterpriserBaseRootPath;
	public static String AiBaseRootPath;
	public static String CareerBaseRootPath;
	public static String FinanceBaseRootPath;
	public static String AccountBaseRootPath;
	public static String GupageBaseRootPath;
	public static String LifeBaseRootPath;
	public static String SolrSearchUrl;
	public static String DefaultAnonymousName;
	public static String ManagernewsBaseRootPath;
	public static String EntrepreneurBaseRootPath;

	@Value("#{web}")
	public void setWebConf(Properties prop) {
		WebConf.MoralBaseRootPath = prop.getProperty("MoralBaseRootPath");
		WebConf.TpiBaseRootPath = prop.getProperty("TpiBaseRootPath");
		WebConf.OfficialBaseRootPath = prop.getProperty("OfficialBaseRootPath");
		WebConf.ManagerBaseRootPath = prop.getProperty("ManagerBaseRootPath");
		WebConf.UserBaseRootPath = prop.getProperty("UserBaseRootPath");
		WebConf.StartupBaseRootPath = prop.getProperty("StartupBaseRootPath");
		WebConf.AiBaseRootPath = prop.getProperty("AiBaseRootPath");
		WebConf.CareerBaseRootPath = prop.getProperty("CareerBaseRootPath");
		WebConf.EnterpriserBaseRootPath = prop.getProperty("EnterpriserBaseRootPath");
		WebConf.SolrSearchUrl = prop.getProperty("SolrSearchUrl");
		WebConf.DefaultAnonymousName = prop.getProperty("DefaultAnonymousName");
		WebConf.FinanceBaseRootPath = prop.getProperty("FinanceBaseRootPath");
		WebConf.AccountBaseRootPath = prop.getProperty("AccountBaseRootPath");
		WebConf.LifeBaseRootPath=prop.getProperty("LifeBaseRootPath");
		WebConf.GupageBaseRootPath = prop.getProperty("GupageBaseRootPath");
		WebConf.ManagernewsBaseRootPath = prop.getProperty("ManagernewsBaseRootPath");
		WebConf.EntrepreneurBaseRootPath = prop.getProperty("EntrepreneurBaseRootPath");
	}

}