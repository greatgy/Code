package com.supergenius.xo.manager.serviceimpl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.model.base.enums.EStatus;
import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.manager.dao.ConfeMemberDao;
import com.supergenius.xo.manager.entity.ConfeMember;
import com.supergenius.xo.manager.service.ConfeMemberSO;

/**
 * 会议室成员Impl
 * @author XieMing
 * @date 2016-7-18 下午2:30:48
 */
@Service
public class ConfeMemberSOImpl extends BaseSOImpl<ConfeMember> implements ConfeMemberSO {

	@Autowired
	ConfeMemberDao dao;
	
	@Override
	protected BaseDao<ConfeMember> getDao() {
		return dao;
	}

	@Override
	public boolean updateStatus(String uid, EStatus status) {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.uid, uid);
		map.put(MapperDict.status, status);
		return dao.updateFields(map);
	}

	@Override
	public ConfeMember getByUseruidPkuid(String useruid, String pkuid) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.useruid, useruid);
		map.put(MapperDict.pkuid, pkuid);
		return dao.getOne(map);
	}

	@Override
	public ConfeMember getByUseruidConfuid(String useruid, String confuid) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.confuid, confuid);
		map.put(MapperDict.useruid, useruid);
		return dao.getOne(map);
	}
}
