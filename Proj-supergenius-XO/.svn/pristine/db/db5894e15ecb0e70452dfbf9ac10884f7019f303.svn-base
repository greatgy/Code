package com.supergenius.xo.gupage.serviceimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.model.base.entity.Pager;
import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.gupage.dao.DebateDao;
import com.supergenius.xo.gupage.entity.Debate;
import com.supergenius.xo.gupage.service.DebateSO;

/**
 * 文章SO实现
 * 
 * @author yangguang
 * @date 2017年11月13日16:14:02
 */
@Service("gupageDebateSOImpl")
public class DebateSOImpl extends BaseSOImpl<Debate> implements DebateSO {

	@Autowired
	private DebateDao dao;

	@Override
	protected BaseDao<Debate> getDao() {
		return dao;
	}
	
	@Override
	public List<Debate> getListByCid(Pager pager, int cid) {
		Map<String, Object> map = getParamMap(pager);
		map.put(MapperDict.type, true);
		map.put(MapperDict.cid, cid);
		return dao.getList(map);
	}
	
	@Override
	public List<Debate> getListByCid(Integer cid) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.type, true);
		map.put(MapperDict.cid, cid);
		return dao.getList(map);
	}
}
