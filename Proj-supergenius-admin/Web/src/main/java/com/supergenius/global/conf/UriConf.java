package com.supergenius.global.conf;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;

import com.genius.core.base.conf.BaseUriConf;

/**
 * @author Architect.bian
 * 
 */
public class UriConf extends BaseUriConf {

	public static final String baseAdminPath = "/supergeniusadmin";
	
	@Value("#{url}")
	public void setUriConf(Properties uri) {
		
	}
}
