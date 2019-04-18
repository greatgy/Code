package com.supergenius.server.manager.third.seegle;

import java.io.Serializable;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.supergenius.server.manager.third.seegle.url.ConfGroupUrlBuilder;
import com.supergenius.server.manager.third.seegle.util.HttpClientUtil;
import com.supergenius.server.manager.third.seegle.util.SeegleConstant;

/**
 * 
 * 视频会议集群ID
 * 
 */
public class SeegleConfGroup implements Serializable {
	
	private static final long serialVersionUID = 7885064635974251516L;

	/**
	 * 获取所有的集群
	 * 
	 * @return
	 */
	public static JSONArray listGrouptype() {
		String url = ConfGroupUrlBuilder.buildConfGroupUrl();
		String result = HttpClientUtil.get(url);
		String substr = result.substring(5, result.length() - 1);
		JSONArray js = JSONArray.fromObject(substr);
		return js;
	}

	/**
	 * 获取会议集群类型
	 * 
	 * @return
	 */
	public static String getGroupType() {
		JSONArray js = listGrouptype();
		int size = js.size();
		for (int i = 0; i < size; i++) {
			JSONObject temp = (JSONObject) js.get(i);
			if (temp.getString("name").equals(SeegleConstant.DEfAULT_GROUP)) {
				return temp.getString("id");
			}
		}
		return ((JSONObject) (js.get(0))).getString("id");
	}

	public static void main(String[] args) {
		System.out.println(getGroupType());
	}

}
