package com.supergenius.xo.manager.service;

import java.util.List;

import com.genius.model.base.entity.Pager;
import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.manager.entity.Inbox;
import com.supergenius.xo.manager.entity.Message;
import com.supergenius.xo.manager.enums.EMsgGroup;

/**
 * 收件箱SO
 * @author XieMing
 * @date 2016-7-18 下午2:27:47
 */
public interface InboxSO extends BaseSO<Inbox> {

	/**
	 * 为inbox注入发送者和消息
	 * @param inbox
	 * @return
	 */
	Inbox getMessageAndUserOfInbox(Inbox inbox);
	
	/**
	 * 获取指定useruid的指定消息组的收信箱
	 * @param useruid
	 * @param pager
	 * @param typegroup
	 * @return
	 */
	List<Inbox> getUserInbox(String useruid, Pager pager, EMsgGroup typegroup);
	
	/**
	 * 获取指定useruid的指定消息组的未读消息数量
	 * @param useruid
	 * @param typegroup
	 * @return
	 */
	int getCountByUnRead(String useruid, EMsgGroup typegroup);
	
	/**
	 * 获取指定useruid的所有未读消息数量
	 * @param useruid
	 * @return
	 */
	int getCountByUnRead(String useruid);
	
	/**
	 * 获取指定useruid的指定消息组的所有消息数量
	 * @param useruid
	 * @param typegroup
	 * @return
	 */
	int getCountByUser(String useruid, EMsgGroup typegroup);
	
	/**
	 * 更新指定useruid的指定类型的已读消息状态
	 * @param uids
	 * @return
	 */
	boolean updateRead(List<String> uids);
	
	/**
	 * 添加消息
	 * @param message
	 * @param touseruid
	 * @return
	 */
	boolean addInbox(Message message, String touseruid);
	
	/**
	 * 删除消息，将对应的inbox的status改为disable
	 * @param uid
	 * @return
	 * @author chenminchang
	 */
	boolean delete(String uid);

	/**
	 * 获取所有未读的消息
	 * @param useruid
	 * @param flag
	 * @return
	 * @author XieMing
	 * 2016-9-6 下午12:23:13
	 */
	List<Inbox> getList(String useruid, boolean flag);
}
