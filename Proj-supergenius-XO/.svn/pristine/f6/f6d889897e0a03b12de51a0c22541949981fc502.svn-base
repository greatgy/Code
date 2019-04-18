package com.supergenius.xo.user.service;

import java.util.List;

import com.genius.model.base.entity.Pager;
import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.user.entity.Order;
import com.supergenius.xo.user.entity.TradeDetail;

/**
 * 订单Service
 * @author diaobisong
 */
public interface OrderSO extends BaseSO<Order> {
	
	
	/**
	 * 获取所有订单
	 * 
	 * @return
	 * @author guanshiqian
	 */
	List<Order> getList(String useruid, Pager pager);
	
	/**
	 * 用户购买视频或者门票，更新用户账户、增加order、ordergoos、tradedetail记录
	 * @param order
	 * @param tradeDetail
	 * @return
	 * @author XieMing
	 * 2016-8-4 下午6:45:46
	 */
	public boolean add(Order order, TradeDetail tradeDetail);

	/**
	 * @param useruid
	 * @return
	 * @author XieMing
	 * 2016-9-8 下午3:20:27
	 */
	public int getCount(String useruid);
}
