package com.supergenius.server.manager.third.seegle.url;

import com.supergenius.server.manager.third.seegle.entity.AuthEntity;
import com.supergenius.server.manager.third.seegle.entity.ConfEntity;
import com.supergenius.server.manager.third.seegle.util.SeegleConstant;
import com.supergenius.server.manager.third.seegle.util.SeegleLinkConstant;

public class ConfUrlBuilder {
	/**
	 * 会议添加
	 * 
	 * @param auth
	 * @param conf
	 * @return
	 */
	public static String buildAddUrl(AuthEntity auth, ConfEntity conf) {
		StringBuilder sb = new StringBuilder(SeegleLinkConstant.CONF_ADD);
		sb.append("?accessKey=" + auth.getAccessKey());
		sb.append("&orgid=" + SeegleConstant.orgid);
		sb.append("&confname=" + conf.getConfname());
		sb.append("&begintime=" + conf.getBegintime());
		sb.append("&endtime=" + conf.getEndtime());
		sb.append("&grouptype=" + SeegleConstant.CONF_GROUP_ID);
		sb.append("&max_conf_user=" + conf.getMax_conf_user());
		sb.append("&max_conf_tourist=" + conf.getMax_conf_tourist());
		sb.append("&max_conf_spokesman=" + conf.getMax_conf_spokesman());
		sb.append("&conf_password_md5=" + conf.getConf_password_md5());
		sb.append("&description=" + conf.getDescription());
		String result = sb.toString();
		result = result.replaceAll(" ", "%20");
		return result;
	}

	/**
	 * 会议列表，cname是模糊受过
	 * 
	 * @param accesstoken
	 * @return
	 */
	public static String buildListUrl(String accesstoken, String... cname) {
		StringBuilder sb = new StringBuilder(SeegleLinkConstant.CONF_LIST);
		sb.append("?accessKey=" + accesstoken);
		sb.append("&orgid=" + SeegleConstant.orgid);
		if (null != cname && cname.length > 0) {
			sb.append("&cname=" + cname[0]);
		}
		String result = sb.toString();
		result = result.replaceAll(" ", "%20");
		return result;
	}

	/**
	 * 会议删除
	 * 
	 * @param token
	 * @param confId
	 * @return
	 */
	public static String buildDeleteUrl(String token, String confId) {
		StringBuilder sb = new StringBuilder(SeegleLinkConstant.CONF_DEL);
		sb.append("?accessKey=" + token);
		sb.append("&orgid=" + SeegleConstant.orgid);
		sb.append("&cid=" + confId);
		String result = sb.toString();
		result = result.replaceAll(" ", "%20");
		return result;

	}

	/**
	 * 获取会议登录信息
	 * 
	 * @param token
	 * @param confId
	 * @return
	 */
	public static String buildLoginUrl(String token, String confId) {
		StringBuilder sb = new StringBuilder(SeegleLinkConstant.CONF_LOGIN);
		sb.append("?accessKey=" + token);
		sb.append("&cid=" + confId);
		String result = sb.toString();
		result = result.replaceAll(" ", "%20");
		return result;

	}

	/**
	 * 获取会议信息
	 * 
	 * @param token
	 * @param confId
	 * @return
	 */
	public static String buildGetUrl(String token, String confId) {
		StringBuilder sb = new StringBuilder(SeegleLinkConstant.CONF_EDIT);
		sb.append("?accessKey=" + token);
		sb.append("&orgid=" + SeegleConstant.orgid);
		sb.append("&cid=" + confId);
		sb.append("&type=get");
		String result = sb.toString();
		result = result.replaceAll(" ", "%20");
		return result;
	}

	/**
	 * 修改会议资料
	 * 
	 * @param auth
	 * @param conf
	 * @return
	 */
	public static String buildUpdateUrl(String auth, ConfEntity conf) {
		StringBuilder sb = new StringBuilder(SeegleLinkConstant.CONF_EDIT);
		sb.append("?accessKey=" + auth);
		sb.append("&type=set");
		sb.append("&orgid=" + SeegleConstant.orgid);
		sb.append("&confname=" + conf.getConfname());
		sb.append("&begintime=" + conf.getBegintime());
		sb.append("&endtime=" + conf.getEndtime());
		sb.append("&grouptype=" + SeegleConstant.CONF_GROUP_ID);
		sb.append("&max_conf_user=" + conf.getMax_conf_user());
		sb.append("&max_conf_tourist=" + conf.getMax_conf_tourist());
		sb.append("&max_conf_spokesman=" + conf.getMax_conf_spokesman());
		sb.append("&conf_password_md5=" + conf.getConf_password_md5());
		sb.append("&description=" + conf.getDescription());
		String result = sb.toString();
		result = result.replaceAll(" ", "%20");
		return result;
	}

	/**
	 * 获取所有会议室
	 * 
	 * @param token
	 * @return
	 * @author chenminchang
	 * @create 2016-9-9上午9:56:59
	 */
	public static String buildAllLogUrl(String token) {
		StringBuilder sb = new StringBuilder(SeegleLinkConstant.LOG_CONF_ALL);
		sb.append("?accessKey=" + token);
		String result = sb.toString();
		result = result.replaceAll(" ", "%20");
		return result;
	}
}
