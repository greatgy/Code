package com.supergenius.xo.common.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.genius.xo.base.dao.BaseDao;
import com.supergenius.xo.common.entity.Message;

/**
 * 消息dao
 * @author YuYingJie
 */
@Component("commonMessageDao")
public interface MessageDao extends BaseDao<Message> {
	
	/**
	 * 通过消息类型获取消息数量
	 * @param map
	 * @return
	 * @author YuYingJie
	 */
	int getCountByMsgType(Map<String, Object> map);
	
	/**
	 * 通过消息类型获取消息
	 * @param map
	 * @return
	 * @author YuYingJie
	 */
	List<Message> getListByMsgType(Map<String, Object> map);
	
	/**
	 * 根据指定条件更新数据
	 * @param map
	 * @return
	 * @author YuYingJie
	 */
	boolean updateWhere(Map<String, Object> map);
}
