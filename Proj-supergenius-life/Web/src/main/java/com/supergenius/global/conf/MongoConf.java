package com.supergenius.global.conf;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.genius.core.base.conf.BaseSysConf;

/**
 * @author tf
 * @date 2018年8月24日
 */
@Component
public class MongoConf extends BaseSysConf{
	//天才人生
	public static String Host;
	public static int Port;
	public static String DBbase;
	public static String Collection;
	
	@Value("#{mongo}")
	public void setSysConf(Properties sys) {
		MongoConf.Port = Integer.valueOf(sys.getProperty("Port"));
		MongoConf.Host = sys.getProperty("Host");
		MongoConf.DBbase = sys.getProperty("DBbase");
		MongoConf.Collection = sys.getProperty("Collection");
	}
	}
