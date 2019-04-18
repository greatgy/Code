package com.supergenius.xo.manager.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.core.base.utils.StrUtil;
import com.genius.model.base.entity.Pager;
import com.genius.model.base.enums.EStatus;
import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.manager.dao.PkJudgementDao;
import com.supergenius.xo.manager.entity.PKJudgement;
import com.supergenius.xo.manager.enums.EStudentLevel;
import com.supergenius.xo.manager.service.PkJudgementSO;

/**
 * 挑战的裁判(各选5个)SOImpl
 * @author XieMing
 * @date 2016-4-29 下午3:19:54
 */
@Service
public class PkJudgementSOImpl extends BaseSOImpl<PKJudgement> implements PkJudgementSO {

	@Autowired
	PkJudgementDao dao;

	@Override
	protected BaseDao<PKJudgement> getDao() {
		return dao;
	}

	@Override
	public boolean updateStatus(PKJudgement pkJudgement) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.uid, pkJudgement.getUid());
		map.put(MapperDict.status, pkJudgement.getStatus());
		return dao.updateFields(map);
	}

	@Override
	public List<PKJudgement> getListByPkuid(String pkscheduleuid) {
		Map<String, Object> map = getParamMap();
		map.remove(MapperDict.status);
		map.put(MapperDict.pkscheduleuid, pkscheduleuid);
		return dao.getList(map);
	}
	
	@Override
	public List<PKJudgement> getListByPkuid(String useruid, String pkscheduleuid) {
		Map<String, Object> map = getParamMap();
		map.remove(MapperDict.status);
		map.put(MapperDict.useruid, useruid);
		map.put(MapperDict.pkscheduleuid, pkscheduleuid);
		return dao.getList(map);
	}
	
	@Override
	public List<PKJudgement> getListByPkuidAndUseruidWait(String useruid, String pkscheduleuid) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.status, EStatus.wait);
		map.put(MapperDict.useruid, useruid);
		map.put(MapperDict.pkscheduleuid, pkscheduleuid);
		return dao.getList(map);
	}
	
	@Override
	public boolean updateList(String pkscheduleuid, EStatus status) {
		List<PKJudgement> list = getListByPkuid(pkscheduleuid);
		for (PKJudgement pkJudgement : list) {
			pkJudgement.setStatus(status);
			updateStatus(pkJudgement);
		}
		return true;
	}

	@Override
	public List<PKJudgement> getList(String judgeuid, Pager pager, List<EStatus> statuList) {
		Map<String, Object> map = getParamMap(pager, true);
		map.put(MapperDict.judgementuid, judgeuid);
		if(StrUtil.isNotEmpty(statuList)) {
			map.put(MapperDict.status + MapperDict.suffix_in_key, statuList);
		}
		return dao.getList(map);
	}

	@Override
	public boolean updateAccept(String uid) {
		PKJudgement pkJudgement = dao.get(uid);
		String useruid = pkJudgement.getUseruid();
		String pkuid = pkJudgement.getPkscheduleuid();
		Map<String, Object> map = getParamMap();
		map.remove(MapperDict.status);
		map.put(MapperDict.useruid, useruid);
		map.put(MapperDict.pkscheduleuid, pkuid);
		List<PKJudgement> pkJudgeList = dao.getList(map);
		for (PKJudgement pkJudgement2 : pkJudgeList) {
			if(uid.equals(pkJudgement2.getUid())) {
				pkJudgement2.setStatus(EStatus.enable);
			} else {
				pkJudgement2.setStatus(EStatus.deleted);
			}
			updateStatus(pkJudgement2);
		}
		return true;
	}

	@Override
	public PKJudgement getOne(String useruid, String judgementuid, String pkscheduleuid) {
		Map<String, Object> map = getParamMap(true);
		map.put(MapperDict.useruid, useruid);
		map.put(MapperDict.judgementuid, judgementuid);
		map.put(MapperDict.pkscheduleuid, pkscheduleuid);
		return dao.getOne(map);
	}

	@Override
	public int getCount(String judgementuid, EStudentLevel pklevel, boolean isabsent) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.judgementuid, judgementuid);
		map.put(MapperDict.pklevel, pklevel);
		map.put(MapperDict.isabsent, isabsent);
		return dao.getCount(map);
	}

	@Override
	public PKJudgement getOne(String judgementuid, String pkscheduleuid) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.judgementuid, judgementuid);
		map.put(MapperDict.pkscheduleuid, pkscheduleuid);
		return dao.getOne(map);
	}

	@Override
	public List<PKJudgement> getListByPkuidWait(String uid) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.status, EStatus.wait);
		map.put(MapperDict.pkscheduleuid, uid);
		return dao.getList(map);
	}
}
