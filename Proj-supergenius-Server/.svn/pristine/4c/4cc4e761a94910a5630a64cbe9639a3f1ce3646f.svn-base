package com.supergenius.server.manager.third.seegle.url;

import com.supergenius.server.manager.third.seegle.util.SeegleConstant;
import com.supergenius.server.manager.third.seegle.util.SeegleLinkConstant;

public class ConfUserUrlBuilder {
	/**
	 * 获取会议成员的URL
	 * 
	 * @param accesstoken
	 * @param cid
	 * @return
	 */
	public static String buildListUserUrl(String accesstoken, String cid) {
		StringBuilder sb = new StringBuilder(SeegleLinkConstant.CONF_USERLIST);
		sb.append("?accessKey=" + accesstoken);
		sb.append("&cid=" + cid);
		String result = sb.toString();
		result = result.replaceAll(" ", "%20");
		return result;
	}

	/**
	 * 获取管理员的URL
	 * 
	 * @param accesstoken
	 * @param cid
	 * @return
	 */
	public static String buildListAdminUserUrl(String accesstoken, String cid) {
		StringBuilder sb = new StringBuilder(SeegleLinkConstant.CONF_ADMINLIST);
		sb.append("?accessKey=" + accesstoken);
		sb.append("&cid=" + cid);
		String result = sb.toString();
		result = result.replaceAll(" ", "%20");
		return result;
	}

	/**
	 * 获取普通用户的URL
	 * 
	 * @param accesstoken
	 * @param cid
	 * @return
	 */
	public static String buildListCommonUserUrl(String accesstoken, String cid) {
		StringBuilder sb = new StringBuilder(SeegleLinkConstant.CONF_COMMONLIST);
		sb.append("?accessKey=" + accesstoken);
		sb.append("&cid=" + cid);
		String result = sb.toString();
		result = result.replaceAll(" ", "%20");
		return result;
	}

	/**
	 * 添加会议成员URL
	 * 
	 * @param accesstoken
	 * @param cid
	 * @param adminList
	 * @param commonList
	 * @return
	 */
	public static String buildAddUserUrl(String accesstoken, String cid, String adminList, String commonList) {
		StringBuilder sb = new StringBuilder(SeegleLinkConstant.CONF_USERADDBYNAME);
		sb.append("?accessKey=" + accesstoken);
		sb.append("&cid=" + cid);
		sb.append("&orgid=" + SeegleConstant.orgid);
		sb.append("&adminList=" + adminList);
		sb.append("&commonList=" + commonList);
		String result = sb.toString();
		result = result.replaceAll(" ", "%20");
		return result;
	}

	/**
	 * 删除会议成员 buildDelUserUrl
	 * 
	 * @param accesstoken
	 * @param cid
	 * @param adminList
	 * @param commonList
	 * @return
	 */
	public static String buildDelUserUrl(String accesstoken, String cid, String adminList, String commonList) {
		StringBuilder sb = new StringBuilder(SeegleLinkConstant.CONF_USERDELBYNAME);
		sb.append("?accessKey=" + accesstoken);
		sb.append("&cid=" + cid);
		sb.append("&orgid=" + SeegleConstant.orgid);
		sb.append("&adminList=" + adminList);
		sb.append("&commonList=" + commonList);
		String result = sb.toString();
		result = result.replaceAll(" ", "%20");
		return result;
	}
}
