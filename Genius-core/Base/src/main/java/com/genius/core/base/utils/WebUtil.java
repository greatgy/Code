package com.genius.core.base.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.HtmlUtils;

import com.genius.core.base.constant.SysConst;

/**
 * @author Architect.bian
 *
 */
public class WebUtil extends BaseUtil {

	private static Logger log = LoggerFactory.getLogger(WebUtil.class);
	
	/**
	 * @param request
	 * @param sessionId
	 * @return
	 */
	public static String getValueFromAttributeOrCookie(HttpServletRequest request, String key) {
		Object attr = request.getAttribute(key);
		if (attr != null) {
			return request.getAttribute(key).toString();
		} else {
			Cookie cookie = CookieUtil.getCookie(request, key);
			if (cookie != null) {
				return cookie.getValue();
			} else {
				return "";
			}
		}
	}

	/**
	 * @param request
	 * @return
	 */
	public static boolean isAjaxRequest(HttpServletRequest request) {
		if (StringUtils.isEmpty(request.getHeader("X-Requested-With"))) {//HTTP_X_REQUESTED_WITH X-Requested-With
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * @param request
	 * @return
	 */
	public static boolean isNotAjaxRequest(HttpServletRequest request) {
		return !isAjaxRequest(request);
	}

	/**
	 * 判断是否是移动端的请求
	 * @param request
	 * @author: Architect.bian
	 * 2014-6-23 下午12:11:42
	 */
	public static boolean isMobileRequest(HttpServletRequest request) {
		if (request.getHeader("User-Agent") != null) {
			String ua = request.getHeader("User-Agent").toLowerCase();
			if(ua.matches("(?i).*(android|(bb\\d+|meego).+mobile|avantgo|bada\\/|blackberry|blazer|compal|elaine|fennec|hiptop|iemobile|ip(hone|od)|iris|kindle|lge |maemo|midp|mmp|mobile.+firefox|netfront|opera m(ob|in)i|palm( os)?|phone|p(ixi|re)\\/|plucker|pocket|psp|series(4|6)0|symbian|treo|up\\.(browser|link)|vodafone|wap|windows (ce|phone)|xda|xiino).*") || ua.substring(0,4).matches("(?i)1207|6310|6590|3gso|4thp|50[1-6]i|770s|802s|a wa|abac|ac(er|oo|s\\-)|ai(ko|rn)|al(av|ca|co)|amoi|an(ex|ny|yw)|aptu|ar(ch|go)|as(te|us)|attw|au(di|\\-m|r |s )|avan|be(ck|ll|nq)|bi(lb|rd)|bl(ac|az)|br(e|v)w|bumb|bw\\-(n|u)|c55\\/|capi|ccwa|cdm\\-|cell|chtm|cldc|cmd\\-|co(mp|nd)|craw|da(it|ll|ng)|dbte|dc\\-s|devi|dica|dmob|do(c|p)o|ds(12|\\-d)|el(49|ai)|em(l2|ul)|er(ic|k0)|esl8|ez([4-7]0|os|wa|ze)|fetc|fly(\\-|_)|g1 u|g560|gene|gf\\-5|g\\-mo|go(\\.w|od)|gr(ad|un)|haie|hcit|hd\\-(m|p|t)|hei\\-|hi(pt|ta)|hp( i|ip)|hs\\-c|ht(c(\\-| |_|a|g|p|s|t)|tp)|hu(aw|tc)|i\\-(20|go|ma)|i230|iac( |\\-|\\/)|ibro|idea|ig01|ikom|im1k|inno|ipaq|iris|ja(t|v)a|jbro|jemu|jigs|kddi|keji|kgt( |\\/)|klon|kpt |kwc\\-|kyo(c|k)|le(no|xi)|lg( g|\\/(k|l|u)|50|54|\\-[a-w])|libw|lynx|m1\\-w|m3ga|m50\\/|ma(te|ui|xo)|mc(01|21|ca)|m\\-cr|me(rc|ri)|mi(o8|oa|ts)|mmef|mo(01|02|bi|de|do|t(\\-| |o|v)|zz)|mt(50|p1|v )|mwbp|mywa|n10[0-2]|n20[2-3]|n30(0|2)|n50(0|2|5)|n7(0(0|1)|10)|ne((c|m)\\-|on|tf|wf|wg|wt)|nok(6|i)|nzph|o2im|op(ti|wv)|oran|owg1|p800|pan(a|d|t)|pdxg|pg(13|\\-([1-8]|c))|phil|pire|pl(ay|uc)|pn\\-2|po(ck|rt|se)|prox|psio|pt\\-g|qa\\-a|qc(07|12|21|32|60|\\-[2-7]|i\\-)|qtek|r380|r600|raks|rim9|ro(ve|zo)|s55\\/|sa(ge|ma|mm|ms|ny|va)|sc(01|h\\-|oo|p\\-)|sdk\\/|se(c(\\-|0|1)|47|mc|nd|ri)|sgh\\-|shar|sie(\\-|m)|sk\\-0|sl(45|id)|sm(al|ar|b3|it|t5)|so(ft|ny)|sp(01|h\\-|v\\-|v )|sy(01|mb)|t2(18|50)|t6(00|10|18)|ta(gt|lk)|tcl\\-|tdg\\-|tel(i|m)|tim\\-|t\\-mo|to(pl|sh)|ts(70|m\\-|m3|m5)|tx\\-9|up(\\.b|g1|si)|utst|v400|v750|veri|vi(rg|te)|vk(40|5[0-3]|\\-v)|vm40|voda|vulc|vx(52|53|60|61|70|80|81|83|85|98)|w3c(\\-| )|webc|whit|wi(g |nc|nw)|wmlb|wonu|x700|yas\\-|your|zeto|zte\\-")) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 清除XSS隐患
	 * @param js
	 * @return
	 */
	public static String clearXSS(String code) {
		code = code.replaceAll("(?i)<script[^>]*>([\\s\\S]*?)</script>", "");
		code = code.replaceAll("(?i)<script[^>]*(/)?>", "");
		code = code.replaceAll("(?i)<applet[^>]*>([\\s\\S]*?)</applet>", "");
		code = code.replaceAll("(?i)<base[^>]*>([\\s\\S]*?)</base>", "");
		code = code.replaceAll("(?i)<base[^>]*(/)?>", "");
		code = code.replaceAll("(?i)<head[^>]*>([\\s\\S]*?)</head>", "");
		code = code.replaceAll("(?i)<style[^>]*(/)?>", "");
		code = code.replaceAll("(?i)<style[^>]*>([\\s\\S]*?)</style>", "");
		code = code.replaceAll("(?i)<link[^>]*(/)?>", "");
		code = code.replaceAll("(?i)<link[^>]*>([\\s\\S]*?)</link>", "");
		code = code.replaceAll("(?i)<meta[^>]*(/)?>", "");
		code = code.replaceAll("(?i)<meta[^>]*>([\\s\\S]*?)</meta>", "");
		code = code.replaceAll("(?i)<title[^>]*(/)?>", "");
		code = code.replaceAll("(?i)<title[^>]*>([\\s\\S]*?)</title>", "");
		code = code.replaceAll("(?i)<object[^>]*(/)?>", "");
		code = code.replaceAll("(?i)<object[^>]*>([\\s\\S]*?)</object>", "");
		code = code.replaceAll("(?i)<embed[^>]*(/)?>", "");
		code = code.replaceAll("(?i)<embed[^>]*>([\\s\\S]*?)</embed>", "");
		code = code.replaceAll("(?i)<frame[^>]*(/)?>", "");
		code = code.replaceAll("(?i)<frame[^>]*>([\\s\\S]*?)</frame>", "");
		code = code.replaceAll("(?i)<frameset[^>]*(/)?>", "");
		code = code.replaceAll("(?i)<frameset[^>]*>([\\s\\S]*?)</frameset>", "");
		code = code.replaceAll("(?i)<iframe[^>]*(/)?>", "");
		code = code.replaceAll("(?i)<iframe[^>]*>([\\s\\S]*?)</iframe>", "");
		code = code.replaceAll("(?i)<!--([\\s\\S]*?)-->", "");
		code = code.replaceAll("(?i)^!--(.*)--$", "");
		code = code.replaceAll("(?i)javascript:", "");
		code = code.replaceAll("(?i)vbscript:", "");
		code = code.replaceAll("(?i)data:", "");
		code = code.replaceAll("(?i)mhtml:", "");
		code = code.replaceAll("(?i)ms-its:", "");
		code = code.replaceAll("(?i)firefoxurl:", "");
		code = code.replaceAll("(?i)mocha:", "");
		code = code.replaceAll("(?i)livescript:", "");
		code = code.replaceAll("(?i)mocha:", "");
		code = code.replaceAll("(?i)eval\\(([\\s\\S]*?)\\)", "");
		code = code.replaceAll("(?i)expression\\(([\\s\\S]*?)\\)", "");
		code = code.replaceAll("(?i)url\\(([\\s\\S]*?)\\)", "");
		code = code.replaceAll("(?i) on([^>]*?)=", " ");
		code = code.replaceAll("(?i)style([\\s\\S]*?)=([\\s\\S]*?)/\\*([\\s\\S]*?)\\*/[^>]*", "");
		return code;
	}

	/**
	 * 从request提取year month 等字段，获得日期
	 * @param request
	 * @return
	 */
	public static DateTime getDateTime(HttpServletRequest request) {
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String day = request.getParameter("day");
		String hour = request.getParameter("hour");
		String minute = request.getParameter("minute");
		if (!StrUtil.isNumeric(hour)) {
			hour = "0";
		}
		if (!StrUtil.isNumeric(minute)) {
			minute = "0";
		}
		if (StrUtil.isNumeric(year) && StrUtil.isNumeric(month) && StrUtil.isNumeric(day)) {
			return new DateTime(Integer.valueOf(year), Integer.valueOf(month), Integer.valueOf(day), Integer.valueOf(hour), Integer.valueOf(minute), 0, 0, DateTimeZone.forOffsetHours(8));
		}
		return null;
	}

	/**
	 * 是否包含words的列表
	 * @param htmlsource
	 * @param words
	 * @return
	 */
	public static boolean containWords(String htmlsource, List<String> words) {
		if (htmlsource == null) {
			return false;
		}
		String filteredstr = clearHtml(htmlsource.toLowerCase()).toString();
		for (String item : words) {
			if (filteredstr.contains(item)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 返回过滤后的source，如：
	 * <《<h1>你好I love U<h2>
	 * 会返回 《你好IloveU
	 * @param source
	 * @return
	 */
	public static StringBuffer clearHtml(String source) {
		StringBuffer filteredBuffer = new StringBuffer();
		String regex = "[&;0-9a-zA-Z\u2E80-\u9FFF]";
		Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		for(int i = 0, k=0; i < source.length(); i++){
			String c = String.valueOf(source.charAt(i));
			if (source.charAt(i) == '<') {
				for(k = i + 1; k < source.length(); k++) {
					if (source.charAt(k) == '<') {
						k = i;
						break;
					}
					if (source.charAt(k) == '>') {
						break;
					}
				}
				i = k;
			} else {
				if (p.matcher(c).find()) {
					filteredBuffer.append(c);
				}
			}
		}
		return filteredBuffer;
	}

	/**
	 * 去除html标签
	 * @author liubin
	 * @date 2017年9月6日 下午5:11:07
	 * @return StringBuffer
	 */
	public static StringBuffer clearHtmlTag(String source) {
		StringBuffer filteredBuffer = new StringBuffer();
		for (int i = 0, k=0; i < source.length(); i++) {
			String c = String.valueOf(source.charAt(i));
			if (source.charAt(i) == '<') {
				for (k = i + 1; k < source.length(); k++) {
					if (source.charAt(k) == '<') {
						k = i;
						filteredBuffer.append(c);
						break;
					}
					if (source.charAt(k) == '>') {
						if (k == i + 1) {
							filteredBuffer.append(c);
							filteredBuffer.append(source.charAt(k));
						}
						break;
					}
				}
				i = k;
			} else {
				filteredBuffer.append(c);
			}
		}
		return filteredBuffer;
	}

	/**
	 * 下载
	 * @param response 
	 * @param file 要下载的文件
	 * @param fileName 下载时的文件名
	 * @throws Exception
	 * @author ShangJianguo
	 * @createtime 2014-8-13 下午5:36:38
	 */
	public static boolean download(HttpServletResponse response, File file, String fileName) throws Exception{
		if (response == null || file == null) {
			throw new Exception("response and file can't be null.");
		}
		if (StringUtils.isEmpty(fileName)) {
			fileName = file.getName();
		}
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Length", String.valueOf(file.length()));
		try {
			response.setHeader("Content-disposition", String.format("attachment; filename=%s", WebUtil.encodeURI(fileName)));
			bis = new BufferedInputStream(new FileInputStream(file.getPath()));
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
			return true;
		} catch (Exception e) {
			logException(log, e);
			throw e;
		} finally {
			try {
				bis.close();
			} catch (IOException e) {
				logException(log, e);
			}
			try {
				bos.close();
			} catch (IOException e) {
				logException(log, e);
			}
		}
	}

	/**
	 * 进行url编码,默认采用utf8编码方式
	 * @param s
	 * @return
	 * @author Architect.bian
	 * @createtime 2014-8-18 下午3:31:43
	 */
	public static String encodeURI(String s) {
		try {
			return URLEncoder.encode(s, SysConst.UTF8);
		} catch (UnsupportedEncodingException e) {
			logException(log, e);
			return null;
		}
	}
	
	/**
	 * 使用特定enc进行url编码
	 * @param s
	 * @param enc
	 * @return
	 * @throws UnsupportedEncodingException
	 * @author Architect.bian
	 * @createtime 2014-8-18 下午3:32:03
	 */
	public static String encodeURI(String s, String enc) throws UnsupportedEncodingException {
		return URLEncoder.encode(s, enc);
	}
	
	/**
	 * url解码
	 * @param s
	 * @return
	 * @author Architect.bian
	 * @createtime 2014-8-18 下午3:56:49
	 */
	public static String decodeURI(String s) {
		try {
			return decodeURI(s, SysConst.UTF8);
		} catch (UnsupportedEncodingException e) {
			logException(log, e);
		}
		return null;
	}
	
	/**
	 * 使用特定enc进行uri解码
	 * @param s
	 * @param enc
	 * @return
	 * @throws UnsupportedEncodingException
	 * @author Architect.bian
	 * @createtime 2014-8-18 下午3:59:19
	 */
	public static String decodeURI(String s, String enc) throws UnsupportedEncodingException {
		return URLDecoder.decode(s, enc);
	}
	
	/**
	 * html转义
	 * @param s
	 * @return
	 * @author Architect.bian
	 * @createtime 2014-8-18 下午4:01:59
	 */
	public static String escapeHtml(String s) {
		return HtmlUtils.htmlEscape(s);
	}
	
	/**
	 * html反转义
	 * @return
	 * @author Architect.bian
	 * @createtime 2014-8-18 下午4:02:09
	 */
	public static String unescapeHtml(String s) {
		return HtmlUtils.htmlUnescape(s);
	}

	/**
	 * 获取request所有的headers
	 * @param request
	 * @return
	 * @author Architect.bian
	 * @createtime 2015-6-3 下午5:26:09
	 */
	public static Map<String, String> getHeaders(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		Enumeration<?> headers = request.getHeaderNames();
		while (headers.hasMoreElements()) {
			String key = (String) headers.nextElement();
			map.put(key, StrUtil.get(request.getHeaders(key)));
		}
		return map;
	}

	/**
	 * 获取request所有的cookies，可方便输出日志
	 * @param request
	 * @return
	 * @author Architect.bian
	 * @createtime 2015-6-3 下午5:26:25
	 */
	public static String getCookies(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		String[] all = new String[cookies.length];
		for (int i = 0; i < cookies.length; i++) {
			all[i] = JsonUtil.toJson(cookies[i]);
		}
		return StrUtil.join(all);
	}
	
	/**
	 * 获取request所有的params
	 * @param request
	 * @return
	 * @author Architect.bian
	 * @createtime 2015-6-3 下午5:26:41
	 */
	public static Map<?, ?> getParams(HttpServletRequest request) {
		return request.getParameterMap();
	}
	
	/**
	 * 判断是否是ipad的请求
	 * @param request
	 * @author: YangGuang
	 * 2018年6月29日18:49:25
	 */ 
	public static boolean idIpad(HttpServletRequest request) {    
		boolean flag = false;    
	    String agent = request.getHeader("User-Agent");    
	    String[] keywords = { "Android", "iPhone", "iPod", "iPad", "Windows Phone", "MQQBrowser" };    
	       //排除 Windows 桌面系统    
	        if (!agent.contains("Windows NT") || (agent.contains("Windows NT") && agent.contains("compatible; MSIE 9.0;")))    
	        {    
	            //排除 苹果桌面系统    
	            if (!agent.contains("Windows NT") && !agent.contains("Macintosh"))    
	            {    
	            	for (String item : keywords) 
	                {    
	                    if (agent.contains(item))    
	                    {    
	                        flag = true;    
	                        break;    
	                    }    
	                }    
	            }    
	        }    
	    return flag;    
	} 
	
}
