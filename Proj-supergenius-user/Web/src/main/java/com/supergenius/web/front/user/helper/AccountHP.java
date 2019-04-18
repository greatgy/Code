package com.supergenius.web.front.user.helper;

import com.genius.server.base.helper.BaseHP;

/** 
 * account相关HP
 * @author chenminchang
 * @date 2016-5-3 下午3:35:31 
 */
public class AccountHP extends BaseHP {
	
	/**
	 * 合并一个url，formayurl 时主要的url，parame是连在后面的参数(没有可传入null)，args是需要插入到url中的变量
	 * @param formaturl
	 * @param parame
	 * @param args
	 * @return
	 */
	public static String urlFormat(String formaturl, String parame, Object... args) {
		String url = String.format(formaturl, args);
		if (parame != null){
			url += "?" + parame;
		}
		return url;
	}
}
