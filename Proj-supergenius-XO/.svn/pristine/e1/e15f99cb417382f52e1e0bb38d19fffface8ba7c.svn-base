package com.supergenius.xo.manager.dao;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.genius.xo.base.dao.BaseDao;
import com.supergenius.xo.manager.entity.Inbox;

/**
 * 收件箱Dao
 * @author XieMing
 * @date 2016-7-18 下午2:23:06
 */
@Component("managerInboxDao")
public interface InboxDao extends BaseDao<Inbox> {

	/**
	 * 更新收件箱的状态
	 * @param map
	 * @return
	 */
	boolean updateRead(Map<String, Object> map);
}
