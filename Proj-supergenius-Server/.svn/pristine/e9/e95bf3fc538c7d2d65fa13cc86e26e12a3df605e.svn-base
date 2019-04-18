package com.supergenius.server.manager.third.seegle;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.supergenius.server.manager.third.seegle.entity.UserEntity;
import com.supergenius.server.manager.third.seegle.url.UserUrlBuilder;
import com.supergenius.server.manager.third.seegle.util.HttpClientUtil;

public class SeegleUser {

	/**
	 * 用户
	 * 
	 * @param accesstoken
	 * @param account
	 * @return
	 */
	public static UserEntity getUserProfileByaccount(String accesstoken, String account) {
		String url = UserUrlBuilder.buildEditUserProfileUrl(accesstoken, account);
		String result = HttpClientUtil.get(url);
		String substr = result.substring(5, result.length() - 1);
		JSONArray jsarray = JSONArray.fromObject(substr);
		JSONObject js = jsarray.getJSONObject(0);// get first user
		UserEntity entity = new UserEntity();
		entity.setAlias(js.getString("alias"));
		entity.setEmail(js.getString("email"));
		entity.setPasswordmd5(js.getString("passwordmd5"));
		entity.setPhone(js.getString("phone"));
		entity.setUseraccount(js.getString("useraccount"));
		entity.setUserid(js.getString("userid"));
		entity.setUsername(js.getString("username"));
		entity.setUserType(js.getInt("usertype"));
		return entity;
	}

	public static void main(String[] args) {
		String username = "songduanlei";
		String password = "123456";
		String token = SeegleToken.getUserToken(username, password);
		System.out.println(token);
		UserEntity entity = getUserProfileByaccount(token, username);
		System.out.println(entity.toString());
	}
}
