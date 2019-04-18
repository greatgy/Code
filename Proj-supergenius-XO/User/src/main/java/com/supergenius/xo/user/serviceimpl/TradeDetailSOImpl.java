package com.supergenius.xo.user.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.model.base.entity.Pager;
import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.common.enums.ESite;
import com.supergenius.xo.user.dao.TradeDetailDao;
import com.supergenius.xo.user.entity.TradeDetail;
import com.supergenius.xo.user.service.TradeDetailSO;

/** 
 * 交易明细SOImpl
 * @author chenminchang
 * @date 2016-3-24 上午10:02:21 
 */
@Service
public class TradeDetailSOImpl extends BaseSOImpl<TradeDetail> implements TradeDetailSO {

	@Autowired
	TradeDetailDao dao;

	@Override
	protected BaseDao<TradeDetail> getDao() {
		return dao;
	}

	@Override
	public List<TradeDetail> getList(String useruid, Pager pager) {
		Map<String, Object> map = getParamMap(pager);
		map.put(MapperDict.useruid, useruid);
		return dao.getList(map);
	}

	@Override
	public int getCountByUseruid(String useruid) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.useruid, useruid);
		return dao.getCount(map);
	}

	@Override
	public double getSumMoney() {
		return dao.getSumMoney(null);
	}
	
	@Override
	public double getSumMoney(ESite site) {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.site, site);
		return dao.getSumMoney(map);
	}
	
}
