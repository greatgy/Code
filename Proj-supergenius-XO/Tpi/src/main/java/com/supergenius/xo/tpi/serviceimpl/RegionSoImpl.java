package com.supergenius.xo.tpi.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.tpi.dao.RegionDao;
import com.supergenius.xo.tpi.entity.Region;
import com.supergenius.xo.tpi.service.RegionSO;

/**
 * 区域 SO 实现
 * 
 * @author ShangJianguo
 */
@Service
public class RegionSoImpl extends BaseSOImpl<Region> implements RegionSO {

	@Autowired
	private RegionDao dao;

	@Override
	protected BaseDao<Region> getDao() {
		return dao;
	}

	/* (non-Javadoc)
	 * @see com.supergenius.xo.tpi.service.RegionSO#getList(java.lang.String)
	 * @author: ShangJianguo
	 * 2015-1-25 下午6:52:46
	 */
	@Override
	public List<Region> getList(int parentid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.parentid, parentid);
		return dao.getList(map);
	}

	/* (non-Javadoc)
	 * @see com.supergenius.xo.tpi.service.RegionSO#getProvinceList()
	 * @author: ShangJianguo
	 * 2015-1-25 下午6:52:46
	 */
	@Override
	public List<Region> getProvinceList() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.parentid, 0);
		return dao.getList(map);
	}

	/* (non-Javadoc)
	 * @see com.supergenius.xo.tpi.service.RegionSO#get(int)
	 * @author: ShangJianguo
	 * 2015-1-26 下午5:49:32
	 */
	@Override
	public Region get(int id) {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.uid, id);
		return dao.getOne(map);
	}

}
