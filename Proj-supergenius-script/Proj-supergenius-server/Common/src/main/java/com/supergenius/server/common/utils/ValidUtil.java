package com.supergenius.server.common.utils;

import java.util.regex.Pattern;

import com.genius.core.base.utils.BaseUtil;

/**
 * 验证类util
 * 
 * @author liubin
 * @date 2016-4-19 上午11:49:09
 */
public class ValidUtil extends BaseUtil {

	// private static Logger log = LoggerFactory.getLogger(ValidUtil.class);

	public static final String regNotEmpty = "\\S+";
	public static final String regNum = "^[-\\+]?\\d+(\\.\\d+)?$";
	public static final String regPassword = "^(?!\\d+$)(?![a-zA-Z]+$)(?![-`=\\\\\\[\\];',./\\~!@#$%^&*()_+|{}:\"<>?]+$)[\\da-zA-Z-`=\\\\\\[\\];',./\\~!@#$%^&*()_+|{}:\"<>?]{6,16}$";
	public static final String regDate = "^\\d{4}-\\d{1,2}-\\d{1,2}$";
	public static final String regDatetime = "^\\d{4}-\\d{1,2}-\\d{1,2}\\s\\d{1,2}:\\d{1,2}:\\d{1,2}$";
	public static final String regQQ = "^[1-9]\\d{4,10}$";
	public static final String regEnglish = "^[A-Za-z ]+$";
	public static final String regChinese = "^[\u0391-\uFFE5]+$";
	public static final String regEmail = "([\\w-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([\\w-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)";
	public static final String regURL = "^http[s]?:\\/\\/[A-Za-z0-9]+\\.[A-Za-z0-9]+[\\/=\\?%\\-&_~`@[\\]\\':+!]*([^<>\"\"])*$";
	public static final String regPhone = "^((\\(\\d{2,3}\\))|(\\d{3}\\-))?(\\(0\\d{2,3}\\)|0\\d{2,3}-)?[1-9]\\d{6,7}(\\-\\d{1,4})?$";
	public static final String regMobile = "^((\\(\\d{2,3}\\))|(\\d{3}\\-))?(13|14|15|18)\\d{9}$";
	public static final String regIdentityid = "^\\d{6}[1|2]\\d{3}[0|1]\\d{1}[0|1|2|3]\\d{1}\\d{3}[0-9a-zA-Z]$";

	/**
	 * 验证不为空
	 * 
	 * @param str
	 * @return 如果不为空返回true,否则返回falses
	 */
	public static boolean isNotEmpty(String str) {
		return Pattern.matches(regNotEmpty, str);
	}

	/**
	 * 验证数字
	 * 
	 * @param str
	 * @return 是数字返回true,否则返回false
	 */
	public static boolean isNumber(String str) {
		return Pattern.matches(regNum, str);
	}

	/**
	 * 验证密码
	 * 
	 * @param str
	 * @return 如果由6～16位数字、字母、特殊字符的组合组成，字母区分大小写则返回true,否则返回false
	 */
	public static boolean isPassword(String str) {
		return Pattern.matches(regPassword, str);
	}

	/**
	 * 验证日期,如2000-01-01
	 * 
	 * @param str
	 * @return 如果符合日期格式则返回true,否则返回false
	 */
	public static boolean isDate(String str) {
		return Pattern.matches(regDate, str);
	}

	/**
	 * 验证日期时间,如2000-01-01 00:00:00
	 * 
	 * @param str
	 * @return 如果符合日期格式则返回true,否则返回false
	 */
	public static boolean isDatetime(String str) {
		return Pattern.matches(regDatetime, str);
	}

	/**
	 * 验证QQ
	 * 
	 * @param str
	 * @return 如果是QQ格式返回true,否则返回false
	 */
	public static boolean isQQ(String str) {
		return Pattern.matches(regQQ, str);
	}

	/**
	 * 验证英文
	 * 
	 * @param str
	 * @return 是英文则返回true,否则返回false
	 */
	public static boolean isEnglish(String str) {
		return Pattern.matches(regEnglish, str);
	}

	/**
	 * 验证中文
	 * 
	 * @param str
	 * @return 是中文则返回true,否则返回false
	 */
	public static boolean isChinese(String str) {
		return Pattern.matches(regChinese, str);
	}

	/**
	 * 验证邮箱
	 * 
	 * @param str
	 * @return 是邮箱返回true,否则返回false
	 */
	public static boolean isEmail(String str) {
		return Pattern.matches(regEmail, str);
	}

	/**
	 * 验证网址
	 * 
	 * @param str
	 * @return 是网址则返回true,否则返回false
	 */
	public static boolean isURL(String str) {
		return Pattern.matches(regURL, str);
	}

	/**
	 * 验证电话
	 * 
	 * @param str
	 * @return 是电话格式则返回true,否则返回false
	 */
	public static boolean isPhone(String str) {
		return Pattern.matches(regPhone, str);
	}

	/**
	 * 验证手机
	 * 
	 * @param str
	 * @return 是手机格式则返回true,否则返回false
	 */
	public static boolean isMobile(String str) {
		return Pattern.matches(regMobile, str);
	}

	/**
	 * 验证身份证号
	 * 
	 * @param str
	 * @return 是身份证号格式则返回true,否则返回false
	 */
	public static boolean isIdentityid(String str) {
		return Pattern.matches(regIdentityid, str);
	}
}
