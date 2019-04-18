package com.supergenius.xo.manager.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.core.base.utils.StrUtil;
import com.genius.model.base.entity.Pager;
import com.genius.model.base.enums.EStatus;
import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.genius.xo.baseadmin.service.AdminLogSO;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.manager.dao.JudgeDao;
import com.supergenius.xo.manager.entity.Certificate;
import com.supergenius.xo.manager.entity.Judge;
import com.supergenius.xo.manager.entity.UserLevel;
import com.supergenius.xo.manager.enums.EJudge;
import com.supergenius.xo.manager.enums.EMajor;
import com.supergenius.xo.manager.service.CertificateSO;
import com.supergenius.xo.manager.service.JudgeSO;
import com.supergenius.xo.manager.service.UserLevelSO;
import com.supergenius.xo.user.entity.User;
import com.supergenius.xo.user.service.UserSO;

/** 
 * 裁判Impl
 * @author guanshiqian
 * @date 2016-4-28 上午11:45:28 
 */
@Service
public class JudgeSOImpl extends BaseSOImpl<Judge> implements JudgeSO {

	@Autowired
	JudgeDao dao;
	@Autowired
	CertificateSO certificateSO;
	@Autowired
	UserLevelSO userLevelSO;
	@Autowired
	AdminLogSO adminLogSO;
	@Autowired
	UserSO userSO;
	
	@Override
	protected JudgeDao getDao() {
		return dao;
	}

	@Override
	public int searchCount(Map<String, Object> map) {
		Map<String, Object> newmap = getParamMap();
		newmap.putAll(map);
		return dao.searchCount(newmap);
	}

	@Override
	public List<Judge> search(Map<String, Object> map) {
		Map<String, Object> newmap = getParamMap();
		if (map.get(MapperDict.pager) != null) {
			Pager pager = (Pager)map.get(MapperDict.pager);
			newmap = getParamMap(pager);
		}
		newmap.putAll(map);
		return dao.search(newmap);
	}

	@Override
	public Judge getOneBySn(String sn) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.sn, sn);
		return dao.getOne(map);
	}

	@Override
	public Judge getOne(EMajor major, String useruid, EStatus status) {
		Map<String, Object> map = getParamMap();
		if(status != null) {
			map.put(MapperDict.status, status);
		}
		map.put(MapperDict.major, major);
		map.put(MapperDict.useruid, useruid);
		return dao.getOne(map);
	}

	@Override
	public List<Judge> getList(String useruid) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.useruid, useruid);
		return dao.getList(map);
	}

	@Override
	public boolean updateStatus(Judge judge) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.uid, judge.getUid());
		map.put(MapperDict.status, judge.getStatus());
		return dao.updateFields(map);
	}

	@Override
	public List<Judge> getList(String useruid, EMajor major) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.useruid, useruid);
		if(StrUtil.isNotEmpty(major)) {
			map.put(MapperDict.major, major);
		}
		return dao.getList(map);
	}

	@Override
	public int getCount(EJudge type) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.type, type);
		return dao.getCount(map);
	}

	@Override
	public int getCount(EStatus status) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.status, status);
		return dao.getCount(map);
	}

	@Override
	public int getQueryCount(Map<String, Object> map) {
		Map<String, Object> newMap = getParamMap();
		newMap.putAll(map);
		return dao.getQueryCount(newMap);
	}

	@Override
	public List<Judge> getQueryList(Map<String, Object> map) {
		Map<String, Object> newMap = getParamMap();
		newMap.putAll(map);
		return dao.getQueryList(newMap);
	}

	@Override
	public Judge getOne(String userUid) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.useruid, userUid);
		return dao.getOne(map);
	}

	@Override
	public boolean add(Judge judge, Certificate certificate, UserLevel userLevel, User user) {
		return dao.insert(judge) && certificateSO.add(certificate) && userLevelSO.add(userLevel) && userSO.updateType(user);
	}

	@Override
	public Judge getOne(String userUid, EMajor major) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.useruid, userUid);
		map.put(MapperDict.major, major);
		return dao.getOne(map);
	}

	@Override
	public boolean update(Judge judge, AdminLog adminLog) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.uid, judge.getUid());
		map.put(MapperDict.status, judge.getStatus());
		map.put(MapperDict.judgecount, judge.getJudgecount());
		return dao.updateFields(map) && adminLogSO.add(adminLog);
	}

	@Override
	public boolean update(User user, Judge judge, Certificate certificate, AdminLog adminLog) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.uid, user.getUid());
		map.put(MapperDict.type, user.getType());
		return userSO.updateFields(map) && dao.insert(judge) && certificateSO.add(certificate) && adminLogSO.add(adminLog);
	}

	@Override
	public int getFullJudgeCount(int judgecount) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.judgecount + MapperDict.suffix_greaterOrEqual_key, judgecount);
		return dao.getCount(map);
	}
	
}
