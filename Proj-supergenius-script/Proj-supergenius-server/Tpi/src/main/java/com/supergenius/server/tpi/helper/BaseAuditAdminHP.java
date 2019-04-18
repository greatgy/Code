package com.supergenius.server.tpi.helper;

import java.util.HashMap;
import java.util.Map;

import com.genius.core.serial.utils.SerialUtil;
import com.genius.server.base.helper.BaseHP;

/**
 * 用户审核管理员管理
 * @author ShangJianguo
 */
public class BaseAuditAdminHP extends BaseHP {

	/**
	 * 保存数据
	 * @param data
	 * @param path
	 * @return
	 * @author ShangJianguo
	 */
	public static boolean saveData(Map<String, String> data, String path) {
		return SerialUtil.serializeToJson(data, path);
	}
	
	/**
	 * 读取数据
	 * @param path
	 * @return
	 * @author ShangJianguo
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> getData(String path) {
		Map<String, String> data = (Map<String, String>) SerialUtil.deserializeFromJson(path, Map.class); 
		if (data == null) {
			data = new HashMap<>();
		}
		return data;
	}
}
