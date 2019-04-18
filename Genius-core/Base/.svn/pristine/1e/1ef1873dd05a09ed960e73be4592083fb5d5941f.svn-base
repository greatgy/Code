package com.genius.core.base.utils;

import java.util.regex.Pattern;

/**
 * @author liushaomin
 *
 */
public class RegexUtil extends BaseUtil {
	
	public static final String regEmail = "([\\w-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([\\w-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)";
	
	public static boolean isEmail(String str) {
		return Pattern.matches(RegexUtil.regEmail, str);
	}
	
	/**
	 * 判定str是否匹配regex
	 * @param regex
	 * @param str
	 * @return
	 * @author: Architect.bian
	 * 2014-6-24 下午1:59:57
	 */
	public static boolean isMatch(String regex, String str) {
		return Pattern.matches(regex, str);
	}
	
	/**
	 * 判断str开头是否是regex
	 * @param regex
	 * @param str
	 * @return
	 * @author Architect.bian
	 * @createtime 2014-12-26 下午7:53:22
	 */
	public static boolean isStartWith(String regex, String str) {
		return Pattern.matches(regex + ".*", str);
	}
}
