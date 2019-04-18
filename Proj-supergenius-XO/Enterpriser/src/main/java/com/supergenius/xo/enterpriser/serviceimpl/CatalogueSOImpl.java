package com.supergenius.xo.enterpriser.serviceimpl;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.model.base.enums.EStatus;
import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.enterpriser.dao.CatalogueDao;
import com.supergenius.xo.enterpriser.entity.Catalogue;
import com.supergenius.xo.enterpriser.service.CatalogueSO;

/**
 * 模块SOImpl
 * @author loupengyu
 * @date 2018年1月29日11:35:00
 */
@Service("enterpriserCatalogueSOImpl")
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
	public List<Catalogue> getListByPcid(int pcid) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.pcid, pcid);
		return dao.getList(map);
	}

	@Override
	public List<Integer> getCidsByPcid(int pcid) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.pcid, pcid);
		return dao.getCidsByPcid(map);
	}
}