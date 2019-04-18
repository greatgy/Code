package com.supergenius.xo.career.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.model.base.entity.Pager;
import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.career.dao.ProblemDao;
import com.supergenius.xo.career.entity.Problem;
import com.supergenius.xo.career.service.ProblemSO;
import com.supergenius.xo.common.constants.MapperDict;

/**
 * 问题Impl
 * 
 * @author ChenQi
 * @date 2017年11月13日17:06:28
 */
@Service("CareerProblemSOImpl")
public class ProblemSOImpl extends BaseSOImpl<Problem> implements ProblemSO {

	
	@Autowired
	ProblemDao dao;
	
	@Override
	protected BaseDao<Problem> getDao() {
		return dao;
	}

	@Override
	public boolean update(String[] uids, boolean istop) {
		Map<String, Object> map = new HashMap<String, Object>();
		for (String uid : uids) {
			map.put(MapperDict.uid, uid);
			map.put(MapperDict.istop, istop);
			dao.updateFields(map);
		}
		return true;
	}
	
	@Override
	public List<Problem> getRecommendList(Pager pager, boolean istop) {
		Map<String, Object> map = getParamMap(pager);
		map.put(MapperDict.istop, istop);
		return dao.getList(map);
	}
}
