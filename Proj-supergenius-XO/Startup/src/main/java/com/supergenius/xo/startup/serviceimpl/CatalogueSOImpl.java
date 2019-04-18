package com.supergenius.xo.startup.serviceimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.startup.dao.CatalogueDao;
import com.supergenius.xo.startup.entity.Catalogue;
import com.supergenius.xo.startup.service.CatalogueSO;

/**
 * 目录模块SO实现
 * 
 * @author 许志翔
 * @date 2017年8月9日14:56:05
 */
@Service
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
	public List<Catalogue> getListByPcid(int pcid) {
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

}
