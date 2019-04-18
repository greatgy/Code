package com.supergenius.xo.career.serviceimpl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.model.base.enums.EStatus;
import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.career.dao.StatisticsDao;
import com.supergenius.xo.career.entity.Statistics;
import com.supergenius.xo.career.service.StatisticsSO;
import com.supergenius.xo.common.constants.MapperDict;

/**
 * 
 * @author ChenQi
 * @date 2017年6月20日12:02:08
 */
@Service("careerStatisticsSOImpl")
public class StatisticsImpl extends BaseSOImpl<Statistics> implements StatisticsSO {

	@Autowired
	private StatisticsDao dao;

	@Override
	protected BaseDao<Statistics> getDao() {
		return dao;
	}

	@Override
	public boolean update(String[] ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<String> list = Arrays.asList(ids);
		map.put(MapperDict.uids, list);
		map.put(MapperDict.status, EStatus.disable);
		dao.updateByUids(map);
		return true;
	}

	@Override
	public int getCount(EStatus status) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.status, status);
		return dao.getCount(map);
	}

	@Override
	public Map<String, Long> getCountByRuler() {
		Map<String, Long> resultMap = new HashMap<String, Long>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put(MapperDict.status, EStatus.enable);
		HashMap<String, Object> map = new HashMap<String, Object>();
		for (int i = 0; i < 3; i++) {
			paramMap.put(MapperDict.startIndex, i);
			map = dao.getCountByRuler(paramMap);
			if (map == null || map.isEmpty()) {
				break;
			}
			String name = (String) map.get("name");
			Long count = (Long) map.get("count");
			resultMap.put(name, count);
		}
		return resultMap;
	}

}
