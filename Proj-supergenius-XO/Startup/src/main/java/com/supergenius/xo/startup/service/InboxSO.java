package com.supergenius.xo.startup.service;

import java.util.List;

import com.genius.model.base.entity.Pager;
import com.genius.model.base.enums.EStatus;
import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.startup.entity.Inbox;
import com.supergenius.xo.startup.entity.News;

/**
 * 收件箱SO
 * 
 * @author yangguang
 * @date 2017年8月29日18:39:37
 */
public interface InboxSO extends BaseSO<Inbox> {

	
	/**
	 * 添加消息
	 * 
	 * @param news
	 * @param touseruid
	 * @return
	 */
	boolean add(News news, String touseruid);
	
	/**
	 * 通过useruid获取inbox
	 * @param useruid
	 * @return
	 * @author 许志翔
	 * @date 2017年8月30日11:46:50
	 */
	List<Inbox> getList(String useruid, Pager pager);
	
	/**
	 * 通过newsuid获取inbox
	 * @param newsuid
	 * @return
	 * @author 许志翔
	 * @date 2017年8月30日15:21:50
	 */
	Inbox getInboxByNewsuid(String newsuid);
	
	/**
	 * 通过useruid更新字段
	 * @param useruid
	 * @return
	 */
	boolean updateByUseruid(String useruid, EStatus status);

	/**
	 * 获取指定useruid的指定消息组的未读消息数量
	 * @param useruid
	 * @return
	 */
	int getCountByUnRead(String uid);
	
}