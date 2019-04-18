package com.supergenius.xo.manager.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.model.base.entity.Pager;
import com.genius.model.base.enums.EStatus;
import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.genius.xo.baseadmin.service.AdminLogSO;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.manager.dao.VideoDao;
import com.supergenius.xo.manager.entity.Video;
import com.supergenius.xo.manager.enums.EMajor;
import com.supergenius.xo.manager.enums.EVideoFrom;
import com.supergenius.xo.manager.service.VideoSO;

/**
 * 视频Impl
 * 
 * @author XieMing
 * @date 2016-7-18 下午2:31:03
 */
@Service
public class VideoSOImpl extends BaseSOImpl<Video> implements VideoSO {

	@Autowired
	VideoDao dao;
	@Autowired
	AdminLogSO adminLogSO;

	@Override
	protected BaseDao<Video> getDao() {
		return dao;
	}

	@Override
	public Video getOneByPkuid(String uid) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.refuid, uid);
		return dao.getOne(map);
	}

	@Override
	public List<Video> getHotVideoList(Map<String, Object> map) {
		Map<String, Object> newMap = getParamMap();
		if (map.get(MapperDict.pager) != null) {
			Pager pager = (Pager) map.get(MapperDict.pager);
			newMap = getParamMap(pager);
		}
		newMap.putAll(map);
		return dao.getList(newMap);
	}

	@Override
	public int getCountByKeyword(Map<String, Object> map) {
		Map<String, Object> newMap = getParamMap();
		newMap.putAll(map);
		return dao.getCount(newMap);
	}

	@Override
	public List<Video> getVideosByKeyword(Map<String, Object> map) {
		Map<String, Object> newMap = getParamMap();
		if (map.get(MapperDict.pager) != null) {
			Pager pager = (Pager) map.get(MapperDict.pager);
			newMap = getParamMap(pager);
		}
		newMap.putAll(map);
		return dao.getList(newMap);
	}

	@Override
	public int getCountBySearch(Map<String, Object> map) {
		Map<String, Object> newMap = getParamMap();
		newMap.putAll(map);
		return dao.getCount(newMap);
	}

	@Override
	public List<Video> getlistBySearch(Map<String, Object> map) {
		Map<String, Object> newMap = getParamMap();
		if (map.get(MapperDict.pager) != null) {
			Pager pager = (Pager) map.get(MapperDict.pager);
			newMap = getParamMap(pager);
		}
		newMap.putAll(map);
		return dao.getList(newMap);
	}

	@Override
	public int getCount (EMajor major) {
		Map<String, Object> newMap = getParamMap();
		newMap.put(MapperDict.major, major);
		return dao.getCount(newMap);
	}

	@Override
	public List<Video> getList(EMajor major) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.major, major);
		return dao.getList(map);
	}

	@Override
	public List<Video> getList(EMajor major, Pager pager) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.major, major);
		if(pager != null) {
			map.put(MapperDict.pager, pager);
		}
		return dao.getList(map);
	}

	@Override
	public int getTotalPlayCount(EMajor major) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.major, major);
		Integer count = dao.getTotalPlayCount(map);
		if (count == null) {
			return 0;
		}
		return count;
	}

	@Override
	public int getCount(EVideoFrom channelfrom) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.channelfrom, channelfrom);
		return dao.getCount(map);
	}
	
	@Override
	public int getCount() {
		Map<String, Object> map = getParamMap();
		return dao.getCount(map);
	}

	@Override
	public List<Video> getList(boolean isrecommend, Pager pager) {
		Map<String, Object> map = getParamMap(pager);
		map.put(MapperDict.isrecommend, isrecommend);
		map.put(MapperDict.orderBy, MapperDict.sql_order_by + MapperDict.updatetime);
		if(pager != null) {
			map.put(MapperDict.pager, pager);
		}
		return dao.getList(map);
	}

	@Override
	public boolean updateStatus(String uid, EStatus status, AdminLog adminLog) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.uid, uid);
		return updateStatus(uid, status) && adminLogSO.add(adminLog);
	}
	
	@Override
	public boolean updateStatus(String uid, EStatus status) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.uid, uid);
		map.put(MapperDict.status, status);
		return dao.updateFields(map);
	}

	@Override
	public boolean updateStatus(String uid, boolean isrecommend, AdminLog adminLog) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.uid, uid);
		return updateIsrecommend(uid, isrecommend) && adminLogSO.add(adminLog);
	}
	
	@Override
	public boolean updateIsrecommend(String uid, boolean isrecommend) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.uid, uid);
		map.put(MapperDict.isrecommend, isrecommend);
		return dao.updateFields(map);
	}

	@Override
	public Video getOne(String refuid, EVideoFrom channelfrom) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.refuid, refuid);
		map.put(MapperDict.channelfrom, channelfrom);
		return dao.getOne(map);
	}
	
}
