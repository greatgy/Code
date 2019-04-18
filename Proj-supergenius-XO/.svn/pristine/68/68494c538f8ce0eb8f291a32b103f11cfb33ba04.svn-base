package com.supergenius.xo.moralnews.serviceimpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.model.base.enums.EStatus;
import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.moralnews.dao.CatalogueDao;
import com.supergenius.xo.moralnews.entity.Catalogue;
import com.supergenius.xo.moralnews.service.CatalogueSO;

/**
 * 目录模块SO实现
 * 
 * @author JiaShitao
 * @date 2018年9月19日10:05:23
 */
@Service("moralnewsCatalogueSOImpl")
public class CatalogueSOImpl extends BaseSOImpl<Catalogue> implements CatalogueSO {

	@Autowired
	private CatalogueDao dao;

	@Override
	protected BaseDao<Catalogue> getDao() {
		return dao;
	}

	@Override
	public Catalogue getOneByCid(int cid) {
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
