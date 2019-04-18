package com.supergenius.xo.tpi.service;

import java.util.List;
import java.util.Map;

import com.genius.model.base.entity.Pager;
import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.tpi.entity.Message;

/**
 * 消息so
 * 
 * @author LiuXiaoke
 */
public interface MessageSO extends BaseSO<Message>{
	
	/**
	 * 分页获取发件箱
	 * @param touseruid
	 * @param pager
	 * @return
	 * @author LiuXiaoke
	 */
	List<Message> getOutList(String touseruid, Pager pager);
	
	/**
	 * 分页获取收件箱
	 * @param fromuseruid
	 * @param pager
	 * @return
	 * @author LiuXiaoke
	 */
	List<Message> getInList(String fromuseruid, Pager pager);
	
	/**
	 * 批量更新头像
	 * @param wheremap
	 * @param map1
	 * @author liushaomin
	 */
	boolean update(Map<String, Object> wheremap, Map<String, Object> map1);
	
	/**
	 * 获取发件箱数量
	 */
	int getOutCount(String fromuserid);
	
	/**
	 * 获取收件箱数量
	 */
	int getInCount(String touserid);
	
	/** 获取未读消息的列表
	 * @param touseruid
	 * @author liushaomin
	 */
	List<Message> getInitMsg(String touseruid);
	
	/** 
	 * 更新团队信息状态（已接受、拒绝）
	 * @param msg
	 * @return
	 * @author liushaomin
	 */
	boolean updateState(Message msg);
	
}
