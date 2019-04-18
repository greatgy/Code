package com.supergenius.xo.managernews.serviceimpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.model.base.enums.EStatus;
import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.managernews.dao.CatalogueDao;
import com.supergenius.xo.managernews.entity.Catalogue;
import com.supergenius.xo.managernews.service.CatalogueSO;

/**
 * 目录模块SO实现
 * 
 * @author JiaShitao
 * @date 2018年7月3日10:05:23
 */
@Service("managernewsCatalogueSOImpl")
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