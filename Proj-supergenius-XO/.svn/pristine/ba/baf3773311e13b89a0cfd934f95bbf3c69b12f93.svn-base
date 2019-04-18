package com.supergenius.xo.user.serviceimpl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.user.dao.OrderLogDao;
import com.supergenius.xo.user.entity.Order;
import com.supergenius.xo.user.entity.OrderLog;
import com.supergenius.xo.user.service.OrderLogSO;

/**
 * 订单日志Service
 * @author diaobisong
 */
@Service
public class OrderLogSOImpl extends BaseSOImpl<OrderLog> implements OrderLogSO {

	@Autowired
	private OrderLogDao dao;
	
	@Override
	protected BaseDao<OrderLog> getDao() {
		return dao;
	}

	@Override
	public List<OrderLog> getByOrderList(String userUid, List<Order> orderList) {
		List<String> orderUidList = new LinkedList<String>();
		for(Order order : orderList){
			orderUidList.add(order.getUid());
		}
		Map<String, Object> condition = new HashMap<String, Object>();
    	condition.put(MapperDict.useruid, userUid); 
    	condition.put(MapperDict.orderuid, orderUidList);
    	return getDao().getList(condition);
	}

}
