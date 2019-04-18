package com.supergenius.xo.manager.serviceimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.model.base.entity.Pager;
import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.manager.dao.DynamicDao;
import com.supergenius.xo.manager.entity.Dynamic;
import com.supergenius.xo.manager.service.DynamicSO;

/**
 * 动态Impl
 * @author XieMing
 * @date 2016-7-18 下午2:32:34
 */
@Service("managerDynamicSOImpl")
public class DynamicSOImpl extends BaseSOImpl<Dynamic> implements DynamicSO {

	@Autowired
	DynamicDao dao;
	
	@Override
	protected BaseDao<Dynamic> getDao() {
		return dao;
	}

	@Override
	public List<Dynamic> getList(String userUid, Pager pager) {
		Map<String, Object> map = getParamMap(pager);
		map.put(MapperDict.useruid, userUid);
		return dao.getList(map);
	}
}
