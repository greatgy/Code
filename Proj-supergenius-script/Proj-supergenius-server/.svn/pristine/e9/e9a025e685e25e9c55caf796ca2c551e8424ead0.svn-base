package com.supergenius.server.manager.third.seegle;

import java.io.IOException;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.supergenius.server.manager.third.seegle.entity.ConfEntity;
import com.supergenius.server.manager.third.seegle.url.TokenUrlBuilder;
import com.supergenius.server.manager.third.seegle.util.HttpClientUtil;

/**
 * token：2小时时效，未用token操作，2小时后失效。
 * @author thomasong
 * @modify chenminchang
 */
public class SeegleToken {

	private static Logger log = LoggerFactory.getLogger(SeegleToken.class);

	/**
	 * 获取管理员token
	 * 
	 * @return
	 */
	public static String getAdminToken() {
		String result = HttpClientUtil.get(TokenUrlBuilder.getAdminUrl());
		String substr = result.substring(5, result.length() - 1);
		JSONObject js = new JSONObject();
		js = JSONObject.fromObject(substr);
		return String.valueOf(js.get("token"));
	}

	/**
	 * 获取普通用户token
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public static String getUserToken(String username, String password) {
		log.info(String.format("begain to getUserToken：username = %s, password = %s", username, password));
		String url = TokenUrlBuilder.getUrl(username, password);
		String result = HttpClientUtil.get(url);
		String substr = result.substring(5, result.length() - 1);
		JSONObject js = new JSONObject();
		js = JSONObject.fromObject(substr);
		return String.valueOf(js.get("token"));
	}

	// 测试
	public static void main(String[] args) throws IOException {
		// String token="null({\"token\":\"813560@admin@1381679744\"})";
		// String substr=token.substring(5,token.length()-1);
		// JSONObject js=new JSONObject();
		// js=JSONObject.fromObject(substr);
		// System.out.println(js.get("token"));
		// Document doc = saxb.
		// System.out.println(substr);
		// System.out.println(TokenUrlBuilder.getAdminUrl());
		ConfEntity confEntity = SeegleConference.get(getUserToken("2545245D6dfgdfgxdfg", "123456"), "180056");
		System.out.println(confEntity);
		// System.out.println(getAdminToken());
	}

}
