package com.supergenius.server.manager.third.seegle;

import java.io.IOException;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.supergenius.server.manager.third.seegle.entity.ConfListEntity;
import com.supergenius.server.manager.third.seegle.url.ConfUserUrlBuilder;
import com.supergenius.server.manager.third.seegle.util.HttpClientUtil;

public class SeegleConfUser {
	/**
	 * 获取用户ID列表 { "confid": 113366, "orgid": 813560, "userid": 1007, "type": 1 }
	 * 
	 * @param accesstoken
	 * @param cid
	 * @return
	 */
	public static JSONArray listConfUser(String accesstoken, String cid) {
		String addUrl = ConfUserUrlBuilder.buildListUserUrl(accesstoken, cid);
		String result = HttpClientUtil.get(addUrl);
		String substr = result.substring(5, result.length() - 1);
		JSONArray js = JSONArray.fromObject(substr);
		return js;
	}

	/**
	 * 添加会议用户 accessKey string 访问令牌 orgid string 企业ID cid string 会议ID adminList
	 * string 新增会议室管理员账号，以逗号分隔(如user1，user2，) commonList string
	 * 新增默认与会者账号，以逗号分隔(如user1，user2，)
	 * 
	 * @param url
	 * @return
	 */
	public static String addConfUser(String accesstoken, String cid, String adminList, String commonList) {
		String addUrl = ConfUserUrlBuilder.buildAddUserUrl(accesstoken, cid, adminList, commonList);
		String result = HttpClientUtil.get(addUrl);
		String substr = result.substring(5, result.length() - 1);
		JSONObject js = JSONObject.fromObject(substr);
		return js.getString("msg");
	}

	/**
	 * 删除会议用户
	 * 
	 * @param accesstoken
	 * @param cid
	 * @param adminList
	 * @param commonList
	 * @return
	 */
	public static String deleteConfUser(String accesstoken, String cid, String adminList, String commonList) {
		String addUrl = ConfUserUrlBuilder.buildDelUserUrl(accesstoken, cid, adminList, commonList);
		String result = HttpClientUtil.get(addUrl);
		String substr = result.substring(5, result.length() - 1);
		JSONObject js = JSONObject.fromObject(substr);
		return js.getString("msg");
	}

	/**
	 * 获取管理员用户 [123,345,duanlei, song]
	 * 
	 * @param accesstoken
	 * @param cid
	 * @return
	 */
	public static String listConfAdminUser(String accesstoken, String cid) {
		String addUrl = ConfUserUrlBuilder.buildListAdminUserUrl(accesstoken, cid);
		String result = HttpClientUtil.get(addUrl);
		String substr = result.substring(5, result.length() - 1);
		return substr;
	}

	/**
	 * 获取普通用户 [123,345,duanlei, song]
	 * 
	 * @param accesstoken
	 * @param cid
	 * @return
	 */
	public static String listConfCommonUser(String accesstoken, String cid) {
		String addUrl = ConfUserUrlBuilder.buildListCommonUserUrl(accesstoken, cid);
		String result = HttpClientUtil.get(addUrl);
		String substr = result.substring(5, result.length() - 1);
		return substr;
	}

	public static void main(String[] args) throws IOException {
		// 获取admin token
		String token = SeegleToken.getAdminToken();
		//获取所有会议室
		List<ConfListEntity> list = SeegleConference.list(token);
		System.out.println(list.size());

		//获取单个会议室的所有成员和管理员
		String adminlist = SeegleConfUser.listConfAdminUser(token, list.get(0).getCid());
		System.out.println("adminlist:" + adminlist);
		String commonlist = SeegleConfUser.listConfCommonUser(token, list.get(0).getCid());
		System.out.println("commonlist:" + commonlist);
	}

}
