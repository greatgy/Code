package com.supergenius.server.manager.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.genius.model.base.entity.Pager;
import com.genius.server.base.helper.BaseHP;
import com.supergenius.server.user.helper.BaseUserConfigHP;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.manager.entity.Inbox;
import com.supergenius.xo.manager.entity.Message;
import com.supergenius.xo.manager.enums.EMsgGroup;
import com.supergenius.xo.manager.service.InboxSO;
import com.supergenius.xo.manager.service.MessageSO;
import com.supergenius.xo.user.entity.User;
import com.supergenius.xo.user.service.UserSO;

/**
 * inboxhp的基类，前后台的inboxhp都继承这个
 * 
 * @author chenminchang
 * @date 2016-7-29 下午3:12:52
 */
public class BaseInboxHP extends BaseHP {

	private static Logger log = LoggerFactory.getLogger(BaseInboxHP.class);
	private static InboxSO so;
	private static MessageSO messageSO;
	private static UserSO userSO;

	private static InboxSO getSO() {
		if (so == null) {
			so = spring.getBean(InboxSO.class);
		}
		return so;
	}

	private static MessageSO getMessgeSO() {
		if (messageSO == null) {
			messageSO = spring.getBean(MessageSO.class);
		}
		return messageSO;
	}

	private static UserSO getUserSO() {
		if (userSO == null) {
			userSO = spring.getBean(UserSO.class);
		}
		return userSO;
	}

	/**
	 * 根据用户和消息组获取收信箱,并将序列化文件中对应类型新消息数量设置为0(基类)
	 * 
	 * @param user
	 * @param typegroup
	 * @return
	 */
	public static List<Inbox> getUserInbox(User user, Pager pager, EMsgGroup typegroup, String userIgnoreFilePath) {
		List<Inbox> list = getSO().getUserInbox(user.getUid(), pager, typegroup);
		List<String> uids = new ArrayList<>();
		if (list != null && list.size() != 0) {
			for (Inbox inbox : list) {
				getSO().getMessageAndUserOfInbox(inbox);
				if (!inbox.isIsread())
					uids.add(inbox.getUid());
			}
		}
		if (uids.size() != 0) {
			getSO().updateRead(uids);
		}
		BaseUserConfigHP.addUserIgnoreMsgCount(user.getOid(), userIgnoreFilePath, typegroup.name() + MapperDict.msgcount, EChannel.manager.name());
		return list;
	}

	/**
	 * 发送消息同时也会将消息注入inbox中，并在序列化文件中增加一条新消息数量(基类)
	 * 
	 * @param map
	 * @return
	 * @author chenminchang
	 */
	public static boolean sendInbox(Map<String, Object> map, String userIgnoreFilePath) {
		String touseruid = (String) map.get(MapperDict.touseruid);
		if (touseruid != null) {
			User user = getUserSO().get(touseruid);
			if (user != null) {
				Message msg = getMessgeSO().addMsg(map);
				if (msg != null) {
					if (getSO().addInbox(msg, touseruid)) {
						BaseUserConfigHP.updateUserIgnoreMsgFile(user.getOid(), userIgnoreFilePath, msg.getTypegroup().name() + MapperDict.msgcount, EChannel.manager.name());
						return true;
					}
					log.info("Message add success, but the inbox send failed. the messageuid is:" + msg.getUid());
				}
			}
		}
		return false;
	}

}
