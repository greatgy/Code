package com.supergenius.web.admin.tpi.helper;

import java.util.HashMap;
import java.util.Map;

import com.supergenius.global.conf.SysConf;
import com.supergenius.server.tpi.helper.BaseAuditAdminHP;

/**
 * 负责审核的管理员邮箱管理
 * @author ShangJianguo
 */
public class AuditAdminHP extends BaseAuditAdminHP {
	
	/**
	 * 获取序列化的路径
	 * @return
	 * @author ShangJianguo
	 */
	private static String getSavePath() {
		return SysConf.SerialBasePath + SysConf.SerialAuditAdminPath;
	}
	
	/**
	 * 读取数据
	 * @return 
	 * @author ShangJianguo
	 */
	public static Map<String, String> getData() {
		Map<String, String> map = BaseAuditAdminHP.getData(getSavePath());
		if (map == null) {
			map = new HashMap<String, String>();
		}
		return map;
	}
	
	/**
	 * 保存数据
	 * @param type 机构类型的name
	 * @param emails 负责审核的管理员
	 * @return
	 * @author ShangJianguo
	 */
	public static boolean saveData(String type, String emails) {
		Map<String, String> data = getData();
		if (data.get(type) != null && data.get(type).toString().equals(emails)) {
			 return true;
		}
		data.put(type, emails);
		return BaseAuditAdminHP.saveData(data, getSavePath());
	}
}
