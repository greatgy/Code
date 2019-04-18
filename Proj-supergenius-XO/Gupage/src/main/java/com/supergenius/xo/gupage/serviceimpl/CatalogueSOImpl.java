package com.supergenius.xo.gupage.serviceimpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.model.base.enums.EStatus;
import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.gupage.dao.CatalogueDao;
import com.supergenius.xo.gupage.entity.Catalogue;
import com.supergenius.xo.gupage.service.CatalogueSO;

/**
 * 目录模块SO实现
 * 
 * @author ChenQi
 * @date 2017年11月13日15:44:16
 */
@Service("gupageCatalogueSOImpl")
public class CatalogueSOImpl extends BaseSOImpl<Catalogue> implements CatalogueSO {

	@Autowired
	private CatalogueDao dao;

	@Override
	protected BaseDao<Catalogue> getDao() {
		return dao;
	}

	@Override
	public boolean update(int cid, EStatus status) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.status, status);
		map.put(MapperDict.cid, cid);
		return dao.updateFields(map);
	}

	@Override
	public Catalogue get(int cid) {
		return dao.get(cid);
	}
	
	@Override
	public Catalogue getOneByCid(int cid) {
		return dao.get(cid);
	}
}
