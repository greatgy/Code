package com.supergenius.xo.startup.serviceimpl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.genius.xo.baseadmin.service.AdminLogSO;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.startup.dao.RulerDao;
import com.supergenius.xo.startup.entity.Ruler;
import com.supergenius.xo.startup.service.RulerSO;

/**
 * 
 * @author ChenQi
 * @date 2017年6月20日12:02:08
 */
@Service
public class RulerSOImpl extends BaseSOImpl<Ruler> implements RulerSO {

	@Autowired
	private RulerDao dao;

	@Autowired
	private AdminLogSO adminlogSO;

	@Override
	protected BaseDao<Ruler> getDao() {
		return dao;
	}

	@Override
	public boolean deleteByUids(String[] ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<String> list = Arrays.asList(ids);
		map.put(MapperDict.uids, list);
		dao.deleteByMap(map);
		return true;
	}

	@Override
	public boolean add(Ruler ruler, AdminLog adminLog) {
		return dao.insert(ruler) && adminlogSO.add(adminLog);
	}

	@Override
	public boolean update(Ruler ruler, AdminLog adminLog) {
		return dao.update(ruler) && adminlogSO.add(adminLog);
	}
	
	@Override
	public Ruler getOne(int sum, int count) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.sum, sum);
		map.put(MapperDict.count, count);
		return dao.getOne(map);
	}
}
