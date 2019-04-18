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

	public static String pageSplitter = GlobalUtil.getUUID();// 防止在没配置的情况下将所有的字符分割，uid则不会分割
	public static int BackPageSize;
	public static String RegisterCheckPass;
	public static String RegisterCheckFail;
	public static int OfficialIndexTpiarticleSize;
	public static int OfficialIndexProjectSize;
	public static int OfficialIndexDebateSize;
	public static int OfficialIndexTopicSize;
	public static int OfficialIndexFinanceSize;
	public static int MobileOfficialIndexProjectSize;

	public static String baseFinancePath;
	public static String baseTpiPath;
	public static String baseMoralPath;
	public static String baseManagerPath;
	public static String baseUserPath;
	public static String baseOfficialImg;// 官网静态图片地址
	public static String MoralArticleUrlPath;
	public static String CertificateUrl;
	public static String ClickSeeDetail;
	public static String AppStudentMsgTitle;
	public static String AppStudentFailedMsgTitle;
	
	@Value("#{web}")
	public void setWebConf(Properties prop) {
		WebConf.BackPageSize = Integer.valueOf(prop.getProperty("BackPageSize"));
		WebConf.RegisterCheckPass = prop.getProperty("RegisterCheckPass");
		WebConf.RegisterCheckFail = prop.getProperty("RegisterCheckFail");
		WebConf.OfficialIndexTpiarticleSize = Integer.valueOf(prop.getProperty("OfficialIndexTpiarticleSize"));
		WebConf.OfficialIndexProjectSize = Integer.valueOf(prop.getProperty("OfficialIndexProjectSize"));
		WebConf.MobileOfficialIndexProjectSize = Integer.valueOf(prop.getProperty("MobileOfficialIndexProjectSize"));
		WebConf.OfficialIndexDebateSize = Integer.valueOf(prop.getProperty("OfficialIndexDebateSize"));
		WebConf.OfficialIndexTopicSize = Integer.valueOf(prop.getProperty("OfficialIndexTopicSize"));
		WebConf.OfficialIndexFinanceSize = Integer.valueOf(prop.getProperty("OfficialIndexFinanceSize"));
		WebConf.baseFinancePath = prop.getProperty("baseFinancePath");
		WebConf.baseTpiPath = prop.getProperty("baseTpiPath");
		WebConf.baseMoralPath = prop.getProperty("baseMoralPath");
		WebConf.baseOfficialImg = prop.getProperty("baseOfficialImg");
		WebConf.MoralArticleUrlPath = prop.getProperty("MoralArticleUrlPath");
		WebConf.CertificateUrl = prop.getProperty("CertificateUrl");
		WebConf.ClickSeeDetail = prop.getProperty("ClickSeeDetail");
		WebConf.AppStudentMsgTitle = prop.getProperty("AppStudentMsgTitle");
		WebConf.AppStudentFailedMsgTitle = prop.getProperty("AppStudentFailedMsgTitle");
		WebConf.baseManagerPath = prop.getProperty("baseManagerPath");
		WebConf.baseUserPath = prop.getProperty("baseUserPath");
	}

}
