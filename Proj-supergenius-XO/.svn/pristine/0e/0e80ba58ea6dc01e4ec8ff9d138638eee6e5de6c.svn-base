package com.supergenius.xo.ai.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.ai.dao.CollectDao;
import com.supergenius.xo.ai.entity.Collect;
import com.supergenius.xo.ai.service.CollectSO;

/**
 * CollectSOImpl
 * 
 * @author ChenQi
 * @date 2017年9月19日10:42:53
 */
@Service("AiCollectSOImpl")
public class CollectSOImpl extends BaseSOImpl<Collect> implements CollectSO {

	@Autowired
	CollectDao dao;

	@Override
	protected BaseDao<Collect> getDao() {
		return dao;
	}

	/*@Override
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
	}*/

}
