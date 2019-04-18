package com.supergenius.global.conf;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.genius.core.base.conf.BaseSysConf;

/**
 * @author Architect.bian
 * 
 */
@Component
public class SysConf extends BaseSysConf {
	public static final int[][] AvatarSizes = new int[][] { { 300, 300 }, { 180, 180 }, { 50, 50 } };
	public static final int[][] ImgShowSizes = new int[][] { { 720, 540 }, { 480, 360 }, { 160, 120 } };
	//solr
	public static int HotLabelSize;
	public static String BaseUrl;
	public static int AliveTime;
	
	@Value("#{sys}")
	public void setSysConf(Properties sys) {
		SysConf.AliveTime = Integer.valueOf(sys.getProperty("AliveTime"));
		SysConf.HotLabelSize = Integer.valueOf(sys.getProperty("HotLabelSize"));
		SysConf.BaseUrl = sys.getProperty("BaseUrl");
	}
	public static int DefaultAnonymousOid = 0;
	
}
