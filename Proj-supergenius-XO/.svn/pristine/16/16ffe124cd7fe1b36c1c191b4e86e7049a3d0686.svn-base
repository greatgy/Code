package com.supergenius.xo.moral.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.model.base.entity.Pager;
import com.genius.model.base.enums.EStatus;
import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.moral.moral.dao.CaseDao;

import com.supergenius.xo.moral.constants.MapperDict;
import com.supergenius.xo.moral.entity.Case;
import com.supergenius.xo.moral.enums.ECase;
import com.supergenius.xo.moral.service.CaseSO;

/**
 * 案例库SO实现
 * 
 * @author LiJiacheng
 */
@Service
public class CaseSOImpl extends BaseSOImpl<Case> implements CaseSO {

	@Autowired
	CaseDao dao;

	@Override
	protected BaseDao<Case> getDao() {
		return dao;
	}

	/* (non-Javadoc)
	 * @see com.supergenius.xo.moral.service.CaseSO#update(com.genius.model.base.enums.EStatus, java.lang.String[])
	 */
	@Override
	public boolean update(EStatus eStatus, String[] ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		for (String id : ids) {
			map.put(MapperDict.status, eStatus);
			map.put(MapperDict.uid, id);
			dao.updateFields(map);
		}
		return true;
	}

	@Override
	public boolean updateCount(Case file) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.uid, file.getUid());
		map.put(MapperDict.countdl, file.getCountdl()+1);
		return dao.updateFields(map);
	}

	/*
	 * (non-Javadoc)
	 * @see com.supergenius.xo.moral.service.CaseSO#getList(com.supergenius.xo.moral.enums.ECase, com.genius.model.base.entity.Pager)
	 */
	@Override
	public List<Case> getList(ECase doc, Pager pager) {
		Map<String, Object> map = getParamMap(pager);
		map.put(MapperDict.type, doc);
		return dao.getList(map);
	}

	/*
	 * (non-Javadoc)
	 * @see com.supergenius.xo.moral.service.CaseSO#getList(com.supergenius.xo.moral.enums.ECase, com.genius.model.base.entity.Pager, java.lang.String)
	 */
	@Override
	public List<Case> getList(ECase eCase, Pager pager, String orderBy) {
		Map<String, Object> map = getParamMap(pager);
		map.put(MapperDict.type, eCase);
		if (orderBy != null) {
			map.put(MapperDict.orderBy, orderBy);
		}
		return dao.getList(map);
	}

}
