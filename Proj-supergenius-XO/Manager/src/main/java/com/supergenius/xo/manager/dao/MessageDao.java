package com.supergenius.xo.manager.dao;

import org.springframework.stereotype.Component;

import com.genius.xo.base.dao.BaseDao;
import com.supergenius.xo.manager.entity.Message;

/**
 * 消息Dao
 * @author XieMing
 * @date 2016-7-18 下午2:23:59
 */
@Component("managerMessageDao")
public interface MessageDao extends BaseDao<Message> {

}
