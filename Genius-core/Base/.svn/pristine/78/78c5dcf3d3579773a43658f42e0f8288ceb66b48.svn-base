package com.genius.core.base.conf;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.genius.core.base.constant.BaseStrDict;
import com.genius.core.base.utils.StrUtil;

/**
 * @author Architect.bian
 *
 */
@Component
public final class MappingConf extends BaseStrDict {
	
	public static Properties mappingProps;
	private static String[] needLoginUrls = new String[]{};
	private static String[] noNeedLoginUrls = new String[]{};
	private static String[] noNeedInitPageVarUrls = new String[]{};
	private static String[] jsonUrls = new String[]{};
	private static String[] adminUrls = new String[]{};
	private static String[] adminCommonUrls = new String[]{};
	private static String[] forMemberUrls = new String[]{};

	@Value("#{mapping}")
	public void setMapping(Properties mapping) {
		MappingConf.mappingProps = mapping;
	}

	/**
	 * 获取需要登录拦截的url
	 * @return 字符串数组
	 */
	public static String[] getNeedLoginUrls() {
		if (needLoginUrls.length == 0) {
			needLoginUrls = MappingConf.mappingProps.get("needLoginUrls").toString().split(BaseStrDict.vLineRegSplitter);
		}
		return needLoginUrls;
	}
	
	/**
	 * 获取仅会员可访问的的url
	 * @return 字符串数组
	 */
	public static String[] getForMemberUrls() {
		if (forMemberUrls.length == 0) {
			forMemberUrls = MappingConf.mappingProps.get("forMemberUrls").toString().split(BaseStrDict.vLineRegSplitter);
		}
		return forMemberUrls;
	}

	/**
	 * 仅会员可以访问的uri
	 * @param requestURI
	 * @return
	 */
	public static boolean urlForMember(String requestURI) {
		if (StrUtil.isMatchAny(requestURI, MappingConf.getNeedLoginUrls()) && StrUtil.isMatchAny(requestURI, MappingConf.getForMemberUrls())) {
			return true;
		}
		return false;
	}
	
	/**
	 * 需要登录的uri
	 * @param requestURI
	 * @return
	 */
	public static boolean isLoginNeedUrl(String requestURI) {
		if (StrUtil.isMatchAny(requestURI, MappingConf.getNeedLoginUrls()) && !StrUtil.isMatchAny(requestURI, MappingConf.getNoNeedLoginUrls()) && !StrUtil.isMatchAny(requestURI, MappingConf.getAdminUrls())) {
			return true;
		}
		return false;
	}

	/**
	 * 获取登录不需要拦截的url
	 * @return 字符串数组
	 */
	public static String[] getNoNeedLoginUrls() {
		if (noNeedLoginUrls.length == 0) {
			noNeedLoginUrls = MappingConf.mappingProps.get("noNeedLoginUrls").toString().split(BaseStrDict.vLineRegSplitter);
		}
		return noNeedLoginUrls;
	}

	/**
	 * 获取输出页面时不需要初始化参数的url
	 * @return 字符串数组
	 */
	public static String[] getNoNeedInitPageVarUrls() {
		if (noNeedInitPageVarUrls.length == 0) {
			noNeedInitPageVarUrls = MappingConf.mappingProps.get("noNeedInitPageVarUrls").toString().split(BaseStrDict.vLineRegSplitter);
		}
		return noNeedInitPageVarUrls;
	}

	/**
	 * 需要初始化页面的uri
	 * @param uri
	 * @return 是否
	 * @author: Architect.bian
	 * 2014-6-23 下午5:01:38
	 */
	public static boolean isNeedInitPageVarUrl(String requestURI) {
		return !StrUtil.isMatchAny(requestURI, MappingConf.getNoNeedInitPageVarUrls());
	}
	
	/**
	 * 获取管理后台的url
	 * @return 所有url的数组
	 * @author Architect.bian
	 * @createtime 2014-8-27 下午1:57:26
	 */
	public static String[] getAdminUrls() {
		if (adminUrls.length == 0) {
			adminUrls = MappingConf.mappingProps.get("adminurls").toString().split(BaseStrDict.vLineRegSplitter);
		}
		return adminUrls;
	}

	/**
	 * 是否是管理后台的url
	 * @param request
	 * @return
	 */
	public static boolean isAdminUrl(String requestURI) {
		String[] strs = MappingConf.getAdminUrls();
		if (StrUtil.isMatchAny(requestURI, strs)) {
			return true;
		}
		return false;
	}
	
	/**
	 * 获取结果为json的url
	 * @return url的字符串数组
	 */
	public static String[] getJsonUrls() {
		if (jsonUrls.length == 0) {
			jsonUrls = MappingConf.mappingProps.get("jsonurls").toString().split(BaseStrDict.vLineRegSplitter);
		}
		return jsonUrls;
	}

	/**
	 * 是否是Json的url
	 * @param request
	 * @return 是否
	 */
	public static boolean isJsonUrl(String requestURI) {
		String[] strs = MappingConf.getJsonUrls();
		if (StrUtil.isMatchAny(requestURI, strs)) {
			return true;
		}
		return false;
	}
	
	/**
	 * 获得管理员登录后都可访问的url
	 * @return 返回所有url的字符串数组
	 * @author Architect.bian
	 * @createtime 2014-8-27 下午1:55:23
	 */
	public static String[] getAdminCommonUrls() {
		if (adminCommonUrls.length == 0) {
			adminCommonUrls = MappingConf.mappingProps.get("admincommonurls").toString().split(BaseStrDict.vLineRegSplitter);
		}
		return adminCommonUrls;
	}
}
