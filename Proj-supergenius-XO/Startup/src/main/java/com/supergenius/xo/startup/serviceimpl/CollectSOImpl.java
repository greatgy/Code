package com.supergenius.xo.startup.serviceimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.model.base.entity.Pager;
import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.startup.dao.CollectDao;
import com.supergenius.xo.startup.entity.Collect;
import com.supergenius.xo.startup.service.CollectSO;

/**
 * CollectSOImpl
 * 
 * @author 杨光
 * @date 2017年8月23日14:26:35
 */
@Service
public class CollectSOImpl extends BaseSOImpl<Collect> implements CollectSO {

	@Autowired
	CollectDao dao;

	@Override
	protected BaseDao<Collect> getDao() {
		return dao;
	}

	@Override
	public List<Collect> getList(String useruid, Pager pager) {
		Map<String, Object> map;
		if (pager != null) {
			map = getParamMap(pager);
		} else {
			map = getParamMap();
		}
		map.put(MapperDict.useruid, useruid);
		return dao.getList(map);
	}

	@Override
	public int getCount() {
		Map<String, Object> map = getParamMap();
		return dao.getCountByuseruid(map);
	}

}
