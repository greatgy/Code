package com.supergenius.xo.ai.serviceimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.model.base.enums.EStatus;
import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.ai.dao.CatalogueDao;
import com.supergenius.xo.ai.entity.Catalogue;
import com.supergenius.xo.ai.service.CatalogueSO;
import com.supergenius.xo.common.constants.MapperDict;

/**
 * 目录模块SO实现
 * 
 * @author yangguang
 * @date 2017年9月19日10:05:23
 */
@Service("aiCatalogueSOImpl")
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
	public List<Catalogue> getListByCid(int cid) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.cid, cid);
		return dao.getList(map);
	}

}
