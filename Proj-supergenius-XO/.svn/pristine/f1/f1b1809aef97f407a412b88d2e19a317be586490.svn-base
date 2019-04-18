package com.supergenius.xo.manager.serviceimpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.model.base.enums.EStatus;
import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.genius.xo.baseadmin.service.AdminLogSO;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.manager.dao.ConferenceDao;
import com.supergenius.xo.manager.entity.Conference;
import com.supergenius.xo.manager.enums.EConfer;
import com.supergenius.xo.manager.service.ConferenceSO;

/**
 * 会议室Impl
 * @author XieMing
 * @date 2016-7-18 下午2:30:29
 */
@Service
public class ConferenceSOImpl extends BaseSOImpl<Conference> implements ConferenceSO {
	
	@Autowired
	ConferenceDao dao;
	
	@Autowired
	AdminLogSO adminLogSO;
	
	@Override
	protected BaseDao<Conference> getDao() {
		return dao;
	}

	@Override
	public boolean update(Conference conference, AdminLog adminLog) {
		return dao.update(conference) && adminLogSO.add(adminLog);
	}

	@Override
	public int getCountByType(EConfer type) {
		Map<String, Object> map = getParamMap(true);
		map.put(MapperDict.type, type);
		return dao.getCount(map);
	}

	@Override
	public boolean updateStatus(String uid, EStatus status) {
		Map<String, Object> map = getParamMap(true);
		map.put(MapperDict.uid, uid);
		map.put(MapperDict.status, status);
		return dao.updateFields(map);
	}

	@Override
	public Conference getByTypeuid(String typeuid) {
		Map<String, Object> map = getParamMap(true);
		map.put(MapperDict.typeuid, typeuid);
		return dao.getOne(map);
	}

	@Override
	public boolean updateJoinCount(Conference conference) {
		Map<String, Object> map = getParamMap(true);
		map.put(MapperDict.uid, conference.getUid());
		map.put(MapperDict.expectjoincount, conference.getExpectJoinCount());
		map.put(MapperDict.realjoincount, conference.getRealJoinCount());
		return dao.updateFields(map);
	}

	
}
