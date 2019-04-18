package com.supergenius.server.manager.third.seegle.url;

import com.supergenius.server.manager.third.seegle.SeegleToken;
import com.supergenius.server.manager.third.seegle.util.SeegleLinkConstant;

public class ConfGroupUrlBuilder {
	/**
	 * 获取会议集群
	 * 
	 * @return
	 */
	public static String buildConfGroupUrl() {
		String adminToken = SeegleToken.getAdminToken();
		StringBuilder sb = new StringBuilder(SeegleLinkConstant.CONF_GROUP);
		sb.append("?accessKey=" + adminToken);
		return sb.toString();
	}
}
