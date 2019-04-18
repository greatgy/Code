package com.supergenius.xo.user.service;

import java.util.List;

import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.user.entity.Order;
import com.supergenius.xo.user.entity.OrderLog;

/**
 * 订单日志Service
 * @author diaobisong
 */
public interface OrderLogSO extends BaseSO<OrderLog> {
	
	/**
	 * 根据useruid和order获取OrderLog
	 * @param userUid
	 * @param orderList
	 * @return
	 */
	public List<OrderLog> getByOrderList(String userUid, List<Order> orderList);
}
