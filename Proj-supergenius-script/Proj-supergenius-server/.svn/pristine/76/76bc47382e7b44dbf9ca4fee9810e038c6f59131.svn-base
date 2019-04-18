package com.supergenius.server.manager.third.seegle.url;

import com.supergenius.server.manager.third.seegle.util.SeegleConstant;
import com.supergenius.server.manager.third.seegle.util.SeegleLinkConstant;

public class UserUrlBuilder {
	/**
	 * 构建修改用户URL
	 * 
	 * @param accesstoken
	 * @param useraccount
	 * @return
	 */
	public static String buildEditUserProfileUrl(String accesstoken, String useraccount) {
		StringBuilder sb = new StringBuilder(SeegleLinkConstant.USER_EDIT);
		sb.append("?accessKey=" + accesstoken);
		sb.append("&orgid=" + SeegleConstant.orgid);
		sb.append("&useraccount=" + useraccount);
		sb.append("&type=get");
		String result = sb.toString();
		result = result.replaceAll(" ", "%20");
		return result;
	}

}
