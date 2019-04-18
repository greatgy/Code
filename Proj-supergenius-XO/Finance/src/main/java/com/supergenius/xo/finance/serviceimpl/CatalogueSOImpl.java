package com.supergenius.xo.finance.serviceimpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.model.base.enums.EStatus;
import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.finance.dao.CatalogueDao;
import com.supergenius.xo.finance.entity.Catalogue;
import com.supergenius.xo.finance.service.CatalogueSO;

/**
 * 目录SO实现
 * 
 * @author XueZhenYong
 * @date 2017年12月29日下午3:32:22
 */
@Service("financeCatalogueSOImpl")
public class CatalogueSOImpl extends BaseSOImpl<Catalogue> implements CatalogueSO {

	@Autowired
	private CatalogueDao dao;

	@Override
	protected BaseDao<Catalogue> getDao() {
		return dao;
	}

	@Override
	public Catalogue get(int cid) {
		return dao.get(cid);
	}

	@Override
	public boolean update(int cid, EStatus status) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.status, status);
		map.put(MapperDict.cid, cid);
		return dao.updateFields(map);
	}
}
