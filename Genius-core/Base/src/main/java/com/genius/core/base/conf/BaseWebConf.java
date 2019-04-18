package com.genius.core.base.conf;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;

import com.genius.core.base.constant.SysConst;
import com.genius.core.base.utils.StrUtil;

/**
 * @author Architect.bian
 * @modifier liushaomin
 */
public abstract class BaseWebConf {

	public static Map<String, Object> basePathMap = new HashMap<String, Object>();
	public static String baseRootPath = "";
	public static String baseCssRootPath = "";
	public static String baseImgRootPath = "";
	public static String baseJsRootPath = "";
	public static String baseFileRootPath = "";
	public static String UserImgRootPath = "";
	public static String WebImgRootPath = "";
	
	@Value("#{web}")
	public void setBaseWebConf(Properties prop) {
		String baseRootPath = prop.getProperty("baseRootPath");
		if (StrUtil.isNotEmpty(baseRootPath)) {
			BaseWebConf.baseRootPath = baseRootPath;
		}
		String baseCssRootPath = prop.getProperty("baseCssRootPath");
		if (StrUtil.isNotEmpty(baseCssRootPath)) {
			BaseWebConf.baseCssRootPath = baseCssRootPath;
		}
		String baseImgRootPath = prop.getProperty("baseImgRootPath");
		if (StrUtil.isNotEmpty(baseImgRootPath)) {
			BaseWebConf.baseImgRootPath = baseImgRootPath;
		}
		String baseJsRootPath = prop.getProperty("baseJsRootPath");
		if (StrUtil.isNotEmpty(baseJsRootPath)) {
			BaseWebConf.baseJsRootPath = baseJsRootPath;
		}
		String baseFileRootPath = prop.getProperty("baseFileRootPath");
		if (StrUtil.isNotEmpty(baseFileRootPath)) {
			BaseWebConf.baseFileRootPath = baseFileRootPath;
		}
		String UserImgRootPath = prop.getProperty("UserImgRootPath");
		if (StrUtil.isNotEmpty(UserImgRootPath)) {
			BaseWebConf.UserImgRootPath = UserImgRootPath;
		}
		String WebImgRootPath = prop.getProperty("WebImgRootPath");
		if (StrUtil.isNotEmpty(WebImgRootPath)) {
			BaseWebConf.WebImgRootPath = WebImgRootPath;
		}
	}
	
	public static Map<String, Object> getBasePath(String defaultBasePath) {
		if (basePathMap.size() < 1) {
			basePathMap.put(SysConst.BASE, StringUtils.isEmpty(BaseWebConf.baseRootPath) ? defaultBasePath : BaseWebConf.baseRootPath);
			basePathMap.put(SysConst.BASECSSKEY, StringUtils.isEmpty(BaseWebConf.baseCssRootPath) ? defaultBasePath : BaseWebConf.baseCssRootPath);
			basePathMap.put(SysConst.BASEIMGKEY, StringUtils.isEmpty(BaseWebConf.baseImgRootPath) ? defaultBasePath : BaseWebConf.baseImgRootPath);
			basePathMap.put(SysConst.BASEJSKEY, StringUtils.isEmpty(BaseWebConf.baseJsRootPath) ? defaultBasePath : BaseWebConf.baseJsRootPath);
			basePathMap.put(SysConst.BASEFILEKEY, StringUtils.isEmpty(BaseWebConf.baseFileRootPath) ? defaultBasePath : BaseWebConf.baseFileRootPath);
			basePathMap.put(SysConst.USERIMGKEY, StringUtils.isEmpty(BaseWebConf.UserImgRootPath) ? defaultBasePath : BaseWebConf.UserImgRootPath);
			basePathMap.put(SysConst.WEBIMGKEY, StringUtils.isEmpty(BaseWebConf.WebImgRootPath) ? defaultBasePath : BaseWebConf.WebImgRootPath);
		}
		return basePathMap;
		
	}
}
