package com.supergenius.xo.manager.serviceimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.model.base.entity.Pager;
import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.manager.dao.CountDetailDao;
import com.supergenius.xo.manager.entity.CountDetail;
import com.supergenius.xo.manager.enums.ECount;
import com.supergenius.xo.manager.service.CountDetailSO;

/** 
 * 计数明细SOImpl
 * @author guanshiqian
 * @date 2016-4-27 下午5:24:35 
 */
@Service("managerCountDetailSOImpl")
public class CountDetailSOImpl extends BaseSOImpl<CountDetail> implements CountDetailSO {

	@Autowired
	CountDetailDao dao;
	
	@Override
	protected BaseDao<CountDetail> getDao() {
		return dao;
	}

	@Override
	public List<CountDetail> getListByRefuid(String uid) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.refuid, uid);
		return dao.getList(map);
	}
	
	@Override
	public List<CountDetail> getListByRefuid(String uid, Pager pager) {
		Map<String, Object> map = getParamMap(pager);
		map.put(MapperDict.refuid, uid);
		return dao.getList(map);
	}

	@Override
	public List<CountDetail> getList(String userUid, String refUid, ECount type) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.refuid, refUid);
		map.put(MapperDict.useruid, userUid);
		map.put(MapperDict.type, type);
		return dao.getList(map);
	}

	@Override
	public List<CountDetail> getList(String refuid, ECount type) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.refuid, refuid);
		map.put(MapperDict.type, type);
		return dao.getList(map);
	}

	@Override
	public int getCount(String refuid, ECount type) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.refuid, refuid);
		map.put(MapperDict.type, type);
		return dao.getCount(map);
	}

	@Override
	public List<CountDetail> getListBySize(String refuid, ECount type, int pageSize) {
		Map<String, Object> map = getParamMap(pageSize);
		map.put(MapperDict.refuid, refuid);
		map.put(MapperDict.type, type);
		return dao.getList(map);
	}

	@Override
	public CountDetail getOne(String userUid, String refUid, ECount type) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.useruid, userUid);
		map.put(MapperDict.refuid, refUid);
		map.put(MapperDict.type, type);
		return dao.getOne(map);
	}

}
