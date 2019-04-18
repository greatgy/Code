package com.supergenius.server.manager.third.seegle.url;

import org.apache.commons.codec.digest.DigestUtils;

import com.supergenius.server.manager.third.seegle.util.SeegleConstant;
import com.supergenius.server.manager.third.seegle.util.SeegleLinkConstant;

public class TokenUrlBuilder {

	/**
	 * 获取管理员Token
	 * 
	 * @return
	 */
	public static String getAdminUrl() {
		StringBuilder sb = new StringBuilder(SeegleLinkConstant.TOKEN);
		sb.append("?orgid=" + SeegleConstant.orgid);
		sb.append("&u=" + SeegleConstant.username);
		sb.append("&p=" + DigestUtils.md5Hex(SeegleConstant.password));
		return sb.toString();
	}

	/**
	 * 获取普通用户Token
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public static String getUrl(String username, String password) {
		StringBuilder sb = new StringBuilder(SeegleLinkConstant.TOKEN);
		sb.append("?orgid=" + SeegleConstant.orgid);
		sb.append("&u=" + username);
		sb.append("&p=" + DigestUtils.md5Hex(password));
		return sb.toString();
	}
}
