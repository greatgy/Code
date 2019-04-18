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

	public static String defaultMark = "";
	public static String defaultavatar = "";
	public static String defaultavatartiny = "";
	public static String pageSplitter = GlobalUtil.getUUID();// 防止在没配置的情况下将所有的字符分割，uid则不会分割
	public static String DefaultAnonymousName;
	public static String LoginURL;
	public static String SnsRootPath;

	@Value("#{web}")
	public void setWebConf(Properties prop) {
		WebConf.defaultMark = prop.getProperty("defaultMark");
		WebConf.defaultavatar = prop.getProperty("defaultavatar");
		WebConf.defaultavatartiny = prop.getProperty("defaultavatartiny");
		WebConf.DefaultAnonymousName = prop.getProperty("DefaultAnonymousName");
		WebConf.LoginURL = prop.getProperty("LoginURL");
		WebConf.SnsRootPath = prop.getProperty("SnsRootPath");
	}
}
