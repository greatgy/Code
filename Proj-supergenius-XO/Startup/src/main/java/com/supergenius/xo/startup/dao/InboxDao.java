package com.supergenius.xo.startup.dao;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.genius.xo.base.dao.BaseDao;
import com.supergenius.xo.startup.entity.Inbox;

/**
 * 收件箱Dao
 * @author yangguang
 * @date 2017年8月29日10:02:09
 */
@Component("startupInboxDao")
public interface InboxDao extends BaseDao<Inbox> {

/*	*//**
	 * 更新收件箱的状态
	 * @param map
	 * @return
	 *//*
	boolean updateRead(Map<String, Object> map);*/
	
	/**
	 * 通过useruid更新字段
	 * @param map
	 * @return
	 */
	boolean updateByUseruid(Map<String, Object> map);
}
