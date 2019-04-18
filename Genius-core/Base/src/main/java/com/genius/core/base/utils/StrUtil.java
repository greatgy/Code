package com.genius.core.base.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.Enumeration;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.genius.core.base.constant.BaseStrDict;
import com.genius.core.base.constant.SysConst;

/**
 * 字符串工具类
 * 
 * @author architect.bian
 * @createtime 2014-8-27 下午1:44:46
 */
public class StrUtil extends BaseUtil {

	private static Logger log = LoggerFactory.getLogger(StrUtil.class);
	
	private static final String JsonFormat = "{\"response\":\"%s\"}";
	private static char[] randomChar = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', 'a', 's', 'd',
			'f', 'g', 'h', 'j', 'k', 'l', 'z', 'x', 'c', 'v', 'b', 'n', 'm' };
	/**
	 * 随机获取字符串
	 * 
	 * @param length
	 *            随机字符串长度
	 * 
	 * @return 随机字符串
	 */
	public static String getRandomString(int length) {
		if (length <= 0) {
			return "";
		}
		Random random = new Random();
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < length; i++) {
			stringBuffer.append(randomChar[Math.abs(random.nextInt()) % randomChar.length]);
		}
		return stringBuffer.toString();
	}
	
	/**
	 * splitToIntArray("123  632") = [123, 632]
	 * @param str
	 * @return 返回分割后的整数数组
	 */
	public static int[] splitToIntArray(String str) {
		String[] arrstr = StringUtils.split(str);
		int[] result = new int[arrstr.length];
		for (int i = 0; i < arrstr.length; i++) {
			result[i] = Integer.parseInt(arrstr[i]); 
		}
		return result;
	}

	/**
	 * 判断str是否匹配regexStrs中任一一个正则
	 * @param str
	 * @param regexStrs
	 * @return
	 */
	public static boolean isMatchAny(String str, String... regexStrs) {
		for (String regex : regexStrs) {
			if (Pattern.matches(regex, str)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 删除开始结束两头的特定字符
	 * @param str 原字符串
	 * @param remover 要删除的字符
	 * @return 删除后的字符串
	 */
	public static String trim(String str, String remover) {
		return StringUtils.removeEnd(StringUtils.removeStart(str, remover), remover);
	}

	/**
	 * 判断是否数字，是否整数
	 * @param s
	 * @return
	 */
	public static boolean isNumeric(CharSequence cs) {
		if (cs == null || cs.length() == 0) {
            return false;
        }
        int sz = cs.length();
        for (int i = 0; i < sz; i++) {
            if (Character.isDigit(cs.charAt(i)) == false) {
            	if (i != 0 || cs.charAt(i) != '-') {
            		return false;
				}
            }
        }
        return true;
	}
	
	/**
	 * 判断是否数字，是否整数
	 * @param s
	 * @return
	 */
	public static boolean isNumeric(Object obj) {
		if (obj == null) {
			return false;
		}
		return isNumeric(obj.toString());
	}

	/**
	 * @param string
	 * @return
	 */
	public static String toJson(String str) {
		return String.format(JsonFormat, str);
	}

	/**
	 * @param address
	 * @return
	 */
	public static String toJson(Object obj) {
		return String.format(JsonFormat, obj.toString());
	}

	/**
	 * @param text
	 * @param contains
	 * @return
	 */
	public static boolean isContainAny(String text, String... contains) {
		for (String str : contains) {
			if (text.contains(str)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断是否不为空白字符串
	 * @param object
	 * @return
	 */
	public static boolean isNotEmpty(Object strobj) {
		if(strobj != null) {
			return StringUtils.isNotEmpty(strobj.toString().trim());
		}
		return false;
	}

	/**
	 * 判断是否为null或空白字符串
	 * @param strobj
	 * @return
	 */
	public static boolean isEmpty(Object strobj) {
		if(strobj != null) {
			return StringUtils.isEmpty(strobj.toString().trim());
		}
		return true;
	}

	/**
	 * @param summary
	 * @return
	 */
	public static String removeHtml(String str) {
		if (StringUtils.isNotEmpty(str)) {
			return str.replaceAll("<[^>]*>", "").replaceAll("\\&[A-Za-z]+;", "");
		} else {
			return "";
		}
	}

	/**
	 * @param name
	 * @return
	 */
	public static String encode(String name) {
		try {
			return URLEncoder.encode(name, SysConst.UTF8);
		} catch (UnsupportedEncodingException e) {
			logException(log, e);
			return null;
		}
	}

	/**
	 * 删除空白
	 * @param removeHtml
	 * @return
	 */
	public static String removeSpace(String str) {
		if (str == null) {
			return null;
		}
		return str.replaceAll("(?i)\\s", "");
	}

	/**
	 * 返回某个编码的字符串
	 * @param str
	 * @param charset
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String get(String str, String charset) throws UnsupportedEncodingException {
		if (str == null) {
			return null;
		}
		return new String(str.getBytes(), charset);
	}
	
	/**
	 * 返回某个编码的字符串
	 * @param str
	 * @param charset
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String get(String str, String charsetfrom, String charsetto) throws UnsupportedEncodingException {
		if (str == null) {
			return null;
		}
		return new String(str.getBytes(charsetfrom), charsetto);
	}

	/**
	 * 格式化整型
	 * @param string
	 * @param id
	 * @return
	 */
	public static String format(String format, long num) {
		return new DecimalFormat(format).format(num);
	}

	/**
	 * 格式化Double类型<br />
	 * 例如：0.00 0.10<br />
	 * #.## 0.1
	 * @param format
	 * @param muti
	 * @return
	 */
	public static String format(String format, double d) {
		return new DecimalFormat(format).format(d);
	}

	/**
	 * 
	 */
	public static String base64Enc(byte[] binaryData) {
		return Base64.encodeBase64String(binaryData);
	}

	/**
	 * 将第一个字母转成大写
	 * @return
	 * @author Architect.bian
	 * @createtime 2014-7-1 下午4:05:11
	 */
	public static String capitalize(String str) {
		return StringUtils.capitalize(str);
	}
	
	/**
	 * 将第一个字母转成小写
	 * @return
	 * @author Architect.bian
	 * @createtime 2014-7-1 下午4:05:11
	 */
	public static String uncapitalize(String str) {
		return StringUtils.uncapitalize(str);
	}

	/**
	 * 将某个enumration对象转为string
	 * @param headers
	 * @return
	 * @author Architect.bian
	 * @createtime 2015-6-3 下午5:14:57
	 */
	public static String get(Enumeration<?> es) {
		StringBuilder sb = new StringBuilder();
		while (es.hasMoreElements()) {
			Object item = es.nextElement();
			if (sb.length() > 0) {
				sb.append(BaseStrDict.comma);
			}
			sb.append(item);
		}
		return sb.toString();
	}

	public static String join(String[] arr) {
		StringBuilder sb = new StringBuilder();
		for (String str : arr) {
			if (sb.length() > 0) {
				sb.append(BaseStrDict.comma);
			}
			sb.append(str);
		}
		return sb.toString();
	}

	/**
	 * str是否以prefix开头
	 * @param str
	 * @param prefix
	 * @return
	 * @author Architect.bian
	 * @createtime 2016-9-5 下午3:49:44
	 */
	public static boolean startsWith(String str, String prefix) {
		return StringUtils.startsWith(str, prefix);
	}


	/**
	 * 截取字符串中匹配正则的部分
	 */
	public static String getSubFirst(String str, String rgex) {
		Pattern pattern = Pattern.compile(rgex);// 匹配的模式
		Matcher m = pattern.matcher(str);
		while (m.find()) {
			return m.group(0);
		}
		return "";
	}
}