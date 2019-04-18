package com.supergenius.xo.user.serviceimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.model.base.entity.Pager;
import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.user.dao.OrderDao;
import com.supergenius.xo.user.dao.OrderGoodsDao;
import com.supergenius.xo.user.dao.OrderLogDao;
import com.supergenius.xo.user.entity.Order;
import com.supergenius.xo.user.entity.OrderGoods;
import com.supergenius.xo.user.entity.TradeDetail;
import com.supergenius.xo.user.entity.User;
import com.supergenius.xo.user.service.OrderSO;
import com.supergenius.xo.user.service.TradeDetailSO;
import com.supergenius.xo.user.service.UserSO;

/**
 * 订单Service
 * @author diaobisong
 */
@Service
public class OrderSOImpl extends BaseSOImpl<Order> implements OrderSO  {

	@Autowired
	private OrderDao dao;
	@Autowired
	private OrderLogDao orderLogDao;
	@Autowired
	private OrderGoodsDao orderGoodsDao;
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private UserSO userSO;
	@Autowired
	private TradeDetailSO tradeDetailSO;
	
	/*
	 * (non-Javadoc)
	 * @see com.genius.xo.base.serviceimpl.BaseSOImpl#getDao()
	 */
	@Override
	protected BaseDao<Order> getDao() {
		return dao;
	}

	/*
	 * (non-Javadoc)
	 * @see com.genius.xo.base.serviceimpl.BaseSOImpl#getCount()
	 */
	@Override
	public int getCount() {
		return dao.getCount(getParamMap());
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.supergenius.xo.user.service.OrderSO#getList(java.lang.String, com.genius.model.base.entity.Pager)
	 */
	@Override
	public List<Order> getList(String useruid, Pager pager) {
		Map<String, Object> OrderMap = getParamMap(pager);
		OrderMap.put(MapperDict.useruid, useruid);
		List<Order> orders = orderDao.getList(OrderMap);
		for (Order item : orders) {
			Map<String, Object> orderGoodsMap = getParamMap();
			orderGoodsMap.put(MapperDict.orderuid, item.getUid());
			List<OrderGoods> goodsList = orderGoodsDao.getList(orderGoodsMap);
			item.setOrderGoods(goodsList);
		}
		return orders;
	}

	@Override
	public boolean add(Order order, TradeDetail tradeDetail) {
		User user = userSO.get(order.getUseruid());
		user.subtractAccount(order.getMoney());
		user.setTotalpay(user.getTotalpay() + order.getMoney());
		if(order.getOrderLog() != null) {
			orderLogDao.insert(order.getOrderLog());
		}
		return userSO.updateAccountAndTotalpay(user) && dao.insert(order) && orderGoodsDao.insertList(order.getOrderGoods()) && tradeDetailSO.add(tradeDetail);
	}

	@Override
	public int getCount(String useruid) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.useruid, useruid);
		return dao.getCount(map);
	}
}
