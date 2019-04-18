package com.supergenius.xo.manager.serviceimpl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.model.base.entity.Pager;
import com.genius.model.base.enums.EStatus;
import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.genius.xo.baseadmin.service.AdminLogSO;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.manager.dao.AppExpertDao;
import com.supergenius.xo.manager.dao.AppExpertDetailDao;
import com.supergenius.xo.manager.dao.ExpertDao;
import com.supergenius.xo.manager.entity.AppExpert;
import com.supergenius.xo.manager.entity.AppExpertDetail;
import com.supergenius.xo.manager.entity.Certificate;
import com.supergenius.xo.manager.entity.Expert;
import com.supergenius.xo.manager.entity.UserLevel;
import com.supergenius.xo.manager.enums.EAppExpertStage;
import com.supergenius.xo.manager.enums.EExpert;
import com.supergenius.xo.manager.enums.EExpertLevel;
import com.supergenius.xo.manager.enums.EMajor;
import com.supergenius.xo.manager.service.CertificateSO;
import com.supergenius.xo.manager.service.ExpertSO;
import com.supergenius.xo.manager.service.UserLevelSO;
import com.supergenius.xo.user.entity.User;
import com.supergenius.xo.user.service.UserSO;

/**
 * 专家SOImpl
 * 
 * @author guanshiqian
 * @date 2016-4-28 下午6:20:27
 */
@Service
public class ExpertSOImpl extends BaseSOImpl<Expert> implements ExpertSO {

	@Autowired
	ExpertDao dao;

	@Autowired
	AppExpertDao appExpertDao;
	
	@Autowired
	AppExpertDetailDao appExpertDetailDao;
	
	@Autowired
	AdminLogSO adminLogSO;
	
	@Autowired
	CertificateSO certificateSO;
	
	@Autowired
	UserLevelSO userLevelSO;
	
	@Autowired
	UserSO userSO;

	@Override
	protected ExpertDao getDao() {
		return dao;
	}

	@Override
	public List<Expert> getListByMajor(EMajor major) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.major, major);
		return dao.getList(map);
	}

	@Override
	public List<Expert> getListByLevel(EExpertLevel level) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.level, level);
		return dao.getList(map);
	}

	@Override
	public List<Expert> getListByType(EExpert type) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.type, type);
		return dao.getList(map);
	}

	@Override
	public List<Expert> getListOrderByCount(Pager pager) {
		Map<String, Object> map = getParamMap(pager);
		map.put(MapperDict.orderBy, MapperDict.sql_order_by + MapperDict.chaircount + MapperDict.desc);
		return dao.getList(map);
	}

	@Override
	public int searchCount(Map<String, Object> map) {
		Map<String, Object> newmap = getParamMap();
		newmap.putAll(map);
		return dao.searchCount(newmap);
	}

	@Override
	public List<Expert> search(Map<String, Object> map) {
		Map<String, Object> newmap = getParamMap();
		if(map.get(MapperDict.pager) != null) {
			Pager pager = (Pager) map.get(MapperDict.pager);
			newmap = getParamMap(pager);
		}
		newmap.putAll(map);
		return dao.search(newmap);
	}

	@Override
	public Expert getOne(String userUid, EMajor major) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.useruid, userUid);
		map.put(MapperDict.major, major);
		return dao.getOne(map);
	}

	@Override
	public boolean update(Expert expert, AppExpert appExpert, String name, EAppExpertStage stagefrom, EAppExpertStage stageto) {
		AppExpertDetail appExpertDetail = new AppExpertDetail(appExpert.getUseruid(), appExpert.getUid(), name, stagefrom, stageto);
		return dao.update(expert) && appExpertDao.update(appExpert) && appExpertDetailDao.insert(appExpertDetail);
	}

	@Override
	public List<Expert> getList(String userUid) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.useruid, userUid);
		return dao.getList(map);
	}

	@Override
	public Expert getOne(String userUid, EMajor major, EStatus status) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.useruid, userUid);
		map.put(MapperDict.major, major);
		map.put(MapperDict.status, status);
		return dao.getOne(map);
	}

	@Override
	public List<Expert> getListByStatus(String userUid, EMajor major, EStatus... status) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.useruid, userUid);
		map.put(MapperDict.major, major);
		if(status.length > 0) {
			map.put(MapperDict.status, Arrays.asList(status));
		}
		return dao.getListByStatus(map);
	}

	@Override
	public Expert getOne(String userUid, EMajor major, EExpertLevel level) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.useruid, userUid);
		map.put(MapperDict.major, major);
		map.put(MapperDict.level, level);
		return dao.getOne(map);
	}

	@Override
	public Expert getOneByStatus(String userUid, EMajor major, EStatus... status) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.useruid, userUid);
		map.put(MapperDict.major, major);
		if(status.length > 0) {
			map.put(MapperDict.status, Arrays.asList(status));
		}
		return dao.getOneByStatus(map);
	}

	@Override
	public List<Expert> getList(String userUid, EExpertLevel level) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.useruid, userUid);
		map.put(MapperDict.level, level);
		return dao.getList(map);
	}

	@Override
	public Expert getOneByStatus(String userUid, EMajor major, EExpert type, EStatus... status) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.useruid, userUid);
		map.put(MapperDict.major, major);
		map.put(MapperDict.type, type);
		if(status.length > 0) {
			map.put(MapperDict.status, Arrays.asList(status));
		}
		return dao.getOneByStatus(map);
	}

	@Override
	public Expert getOne(String expertUid, EStatus status) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.uid, expertUid);
		map.put(MapperDict.status, status);
		return getOne(map);
	}

	@Override
	public int getCount(EExpertLevel level) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.level, level);
		return dao.getCount(map);
	}

	@Override
	public int getCount(EStatus status) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.status, status);
		return dao.getCount(map);
	}

	@Override
	public boolean update(Expert expert, AdminLog adminLog) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.uid, expert.getUid());
		map.put(MapperDict.status, expert.getStatus());
		map.put(MapperDict.chaircount, expert.getChaircount());
		return dao.updateFields(map) && adminLogSO.add(adminLog);
	}

	@Override
	public boolean add(Expert expert, Certificate certificate, AdminLog adminLog, UserLevel userLevel, User user) {
		if(user != null) {
			userSO.updateType(user);
		}
		return dao.insert(expert) && certificateSO.add(certificate) && adminLogSO.add(adminLog) && userLevelSO.add(userLevel);
	}
	
	@Override
	public boolean update(User user, Expert expert, Certificate certificate, AdminLog adminLog) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.uid, user.getUid());
		map.put(MapperDict.type, user.getType());
		return userSO.updateFields(map) && dao.insert(expert) && certificateSO.add(certificate) && adminLogSO.add(adminLog);
	}

	@Override
	public int getFullCount(int count) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.chaircount + MapperDict.suffix_greaterOrEqual_key, count);
		return dao.getCount(map);
	}
}
