package com.genius.core.base.conf;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author Architect.bian
 *
 */
public abstract class BaseUriConf {
	
	public static final String api = "/api";
	public static final String ajax = "/ajax";
	public static final String fresh = "/fresh";
	public static final String section = "/section";
	public static final String baseAdminPath = "/admin";
	public static final String baseMobilePath = "/m";
	public static String baseSectionUri = "";
	
//	public static Properties cacheProps;

	@Value("#{url}")
	public void setBaseUriConf(Properties uri) {
//		BaseUriConf.cacheProps = uri;
		BaseUriConf.baseSectionUri = uri.getProperty("baseSectionUri");
	}
}
