package com.supergenius.xo.life.serviceimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.life.dao.CatalogueDao;
import com.supergenius.xo.life.entity.Catalogue;
import com.supergenius.xo.life.service.CatalogueSO;

/**
 * 目录模块SO实现
 * 
 * @author ChenQi
 * @date 2018年5月9日18:36:11
 */
@Service("lifeCatalogueSOImpl")
public class CatalogueSOImpl extends BaseSOImpl<Catalogue> implements CatalogueSO {

	@Autowired
	private CatalogueDao dao;

	@Override
	protected BaseDao<Catalogue> getDao() {
		return dao;
	}

	@Override
	public Catalogue getOneByCid(long cid) {
		return dao.get(cid);
	}

	@Override
	public List<Catalogue> getListByPcid(long pcid) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.pcid, pcid);
		return dao.getList(map);
	}

	@Override
	public boolean update(String cid, int status) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.status, status);
		map.put(MapperDict.cid, cid);
		return dao.updateFields(map);
	}

	@Override
	public List<Catalogue> getFirstCatalogues() {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.pcid, 0);
		return dao.getList(map);
	}

}
