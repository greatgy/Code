package com.supergenius.web.finance.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.genius.core.base.utils.JsonUtil;
import com.genius.core.base.utils.StrUtil;
import com.genius.core.cache.rule.Rule;
import com.genius.core.cache.utils.MemcacheUtil;
import com.supergenius.core.rule.WxTockenRule;
import com.supergenius.global.conf.SysConf;
import com.supergenius.server.common.constants.ViewKeyDict;

/**
 * @author 雍雪振
 * @date 2018年4月16日11:48:17
 */
@Controller
public class WXShareController {

	@RequestMapping("/ajax/wxshare")
	@ResponseBody
	public Map<String, String> share(HttpServletRequest request, String url) throws Exception {
		Rule rule = new WxTockenRule(ViewKeyDict.ticket);
		Rule timeRule = new WxTockenRule(ViewKeyDict.time);
		String jsapi_ticket = (String) MemcacheUtil.get(rule);
		DateTime lastTime = (DateTime) MemcacheUtil.get(timeRule);
		if (StrUtil.isEmpty(jsapi_ticket) || (new DateTime().getMillis() - lastTime.getMillis()) >= 7200 * 1000) {
			String string = get(SysConf.getTokenURI);
			JSONObject json = JsonUtil.fromJson(string, JSONObject.class);
			String access_token = json.getString(ViewKeyDict.access_token);
			String token = get(SysConf.getTicketURI + access_token);
			JSONObject json2 = JsonUtil.fromJson(token, JSONObject.class);
			String ticket = json2.getString(ViewKeyDict.ticket);
			MemcacheUtil.set(rule, ticket);
			MemcacheUtil.set(timeRule, new DateTime());
			Map<String, String> ret = sign(ticket, url);
			System.out.println("current is from wx ----------------------------------------");
			return ret;
		} else {
			Map<String, String> ret = sign(jsapi_ticket, url);
			System.out.println("current is from cache ----------------------------------------");
			return ret;
		}
	}

	public static String get(String url) {
		BufferedReader in = null;
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			connection.setConnectTimeout(5000);
			connection.setReadTimeout(5000);
			// 建立实际的连接
			connection.connect();
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuffer sb = new StringBuffer();
			String line;
			while ((line = in.readLine()) != null) {
				sb.append(line);
			}
			return sb.toString();
		} catch (Exception e) {
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return null;
	}

	public static Map<String, String> sign(String jsapi_ticket, String url) {
		Map<String, String> ret = new HashMap<String, String>();
		String nonce_str = create_nonce_str();
		String timestamp = create_timestamp();
		String string1;
		String signature = "";
		// 注意这里参数名必须全部小写，且必须有序
		string1 = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + nonce_str + "&timestamp=" + timestamp + "&url=" + url;
		System.out.println(string1);
		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(string1.getBytes("UTF-8"));
			signature = byteToHex(crypt.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		ret.put("url", url);
		ret.put("jsapi_ticket", jsapi_ticket);
		ret.put("appId", SysConf.wxcorpid);
		ret.put("nonceStr", nonce_str);
		ret.put("timestamp", timestamp);
		ret.put("signature", signature);
		return ret;
	}

	private static String byteToHex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}

	private static String create_nonce_str() {
		return UUID.randomUUID().toString();
	}

	private static String create_timestamp() {
		return Long.toString(System.currentTimeMillis() / 1000);
	}

}
