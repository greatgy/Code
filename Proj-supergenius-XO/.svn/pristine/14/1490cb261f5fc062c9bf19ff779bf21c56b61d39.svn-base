package com.supergenius.xo.user.serviceimpl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.core.base.constant.BaseMapperDict;
import com.genius.model.base.entity.Pager;
import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.user.dao.OrderGoodsDao;
import com.supergenius.xo.user.entity.Order;
import com.supergenius.xo.user.entity.OrderGoods;
import com.supergenius.xo.user.enums.EGoods;
import com.supergenius.xo.user.service.OrderGoodsSO;

/**
 * 订单商品Service
 * @author diaobisong
 */
@Service
public class OrderGoodsSOImpl extends BaseSOImpl<OrderGoods> implements OrderGoodsSO {

	@Autowired
	private OrderGoodsDao dao;
	
	@Override
	protected BaseDao<OrderGoods> getDao() {
		return dao;
	}

	@Override
	public List<OrderGoods> getByOrderList(String userUid, List<Order> orderList) {
		List<String> orderUidList = new LinkedList<String>();
		for(Order order : orderList){
			orderUidList.add(order.getUid());
		}
		Map<String, Object> condition = new HashMap<String, Object>();
    	condition.put(MapperDict.useruid, userUid); 
    	condition.put(MapperDict.orderuid, orderUidList);
    	return getDao().getList(condition);
	}

	@Override
	public List<OrderGoods> getList(String orderuid) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.orderuid, orderuid);
		return dao.getList(map);
	}

	@Override
	public List<OrderGoods> getListBySn(String sn) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.sn, sn);
		return dao.getList(map);
	}
	

	@Override
	public List<OrderGoods> getListBySn(String sn, Pager pager) {
		Map<String, Object> map = getParamMap(pager);
		map.put(MapperDict.sn, sn);
		return dao.getList(map);
	}

	@Override
	public List<OrderGoods> getListBySnAndUseruid(String sn, String useruid) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.useruid, useruid);
		map.put(MapperDict.sn, sn);
		return dao.getList(map);
	}

	@Override
	public List<OrderGoods> getListBySize(String pkScheduelSN, EGoods type, int pageSize) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.sn, pkScheduelSN);
		map.put(MapperDict.type, EGoods.ticket);
		map.put(BaseMapperDict.pageSize, pageSize);
		map.put(MapperDict.ascDesc, MapperDict.desc);
		return dao.getList(map);
	}

	@Override
	public List<OrderGoods> getList(String userUid, EGoods type) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.useruid, userUid);
		map.put(MapperDict.type, type);
		return dao.getList(map);
	}

	@Override
	public OrderGoods getOne(String useruid, String sn) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.sn, sn);
		map.put(MapperDict.useruid, useruid);
		return dao.getOne(map);
	}

	@Override
	public int getCount(String sn) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.sn, sn);
		return dao.getCount(map);
	}

	@Override
	public int getBuyUserCount(String sn, EGoods type) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.sn, sn);
		map.put(MapperDict.type, type);
		map.put(MapperDict.distinct_user, true);
		return dao.getCount(map);
	}

	@Override
	public List<String> getBuyUserList(String sn, EGoods type) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.sn, sn);
		map.put(MapperDict.type, type);
		map.put(MapperDict.distinct_user, true);
		return dao.getBuyUserList(map);
	}

	@Override
	public List<OrderGoods> getListByRefuid(String refuid, EGoods type) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.refuid, refuid);
		map.put(MapperDict.type, type);
		return dao.getList(map);
	}
}
