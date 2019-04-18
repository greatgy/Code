package com.supergenius.xo.manager.serviceimpl;

import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.manager.dao.MyVideoDao;
import com.supergenius.xo.manager.entity.MyVideo;
import com.supergenius.xo.manager.service.MyVideoSO;

/**
 * 我的视频SOImpl
 * 
 * @author liubin
 * @date 2016-8-12 上午10:46:40
 */
@Service
public class MyVideoSOImpl extends BaseSOImpl<MyVideo> implements MyVideoSO {

	@Autowired
	MyVideoDao dao;

	@Override
	protected BaseDao<MyVideo> getDao() {
		return dao;
	}

	@Override
	public List<MyVideo> getListBySearch(Map<String, Object> map) {
		Map<String, Object> newMap = getParamMap();
		newMap.putAll(map);
		return dao.getSearchList(newMap);
	}

	@Override
	public int getStatus(String userUid, String videoUid, int days) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.useruid, userUid);
		map.put(MapperDict.videouid, videoUid); 
		if(dao.getOne(map).getExpiretime().isBeforeNow()) {
			return -1;
		}else if(dao.getOne(map).getExpiretime().compareTo(new DateTime().plusDays(days)) > 0) {
			return 1;
		}else {
			return 0;
		}
	}

	@Override
	public MyVideo getOne(String videoUid, String userUid) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.videouid, videoUid);
		map.put(MapperDict.useruid, userUid);
		return dao.getOne(map);
	}

	@Override
	public List<MyVideo> getList(String expiretimeBeforeThreeday) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.expiretime_inthreeday, expiretimeBeforeThreeday);
		return dao.getList(map);
	}

	@Override
	public MyVideo getOne(String userUid) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.useruid, userUid);
		return dao.getOne(map);
	}
}
