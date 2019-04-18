package com.supergenius.web.admin.manager.helper;

import java.util.Map;

import com.supergenius.global.conf.SysConf;
import com.supergenius.server.manager.helper.BaseInboxHP;

/** 
 * 后台使用的InboxHP
 * @author chenminchang
 * @date 2016-8-17 下午5:47:07 
 */
public class InboxHP extends BaseInboxHP{

	/**
	 * 后台发送消息的入口,发送消息同时也会将消息注入inbox中，并在序列化文件中增加一条新消息数量
	 * @param map
	 * @return
	 * @author chenminchang
	 */
	public static boolean sendInbox(Map<String, Object> map) {
		String userIgnoreFilePath = SysConf.getUserIgnoreFilePath();
		return sendInbox(map, userIgnoreFilePath);
	}
}
