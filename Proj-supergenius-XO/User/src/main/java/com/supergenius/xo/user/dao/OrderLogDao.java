package com.supergenius.xo.user.dao;

import com.genius.xo.base.dao.BaseDao;
import com.supergenius.xo.user.entity.OrderLog;

/**
 * 订单日志Dao
 * @author diaobisong
 */
public interface OrderLogDao extends BaseDao<OrderLog> {
	
	public void removeOrder(String orderuid);
}
