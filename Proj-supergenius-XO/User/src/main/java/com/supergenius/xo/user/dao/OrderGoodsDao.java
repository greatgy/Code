package com.supergenius.xo.user.dao;

import java.util.List;
import java.util.Map;

import com.genius.xo.base.dao.BaseDao;
import com.supergenius.xo.user.entity.OrderGoods;

/**
 * 订单商品Dao
 * @author diaobisong
 */
public interface OrderGoodsDao extends BaseDao<OrderGoods> {
	
	public void removeOrder(String orderuid);

	/**
	 * 获取某商品的购买人（去重）
	 * @param map
	 * @return
	 * @author XieMing
	 * 2016-10-21 下午3:59:04
	 */
	public List<String> getBuyUserList(Map<String, Object> map);
}