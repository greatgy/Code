package com.supergenius.xo.manager.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.model.base.entity.Pager;
import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.genius.xo.baseadmin.service.AdminLogSO;
import com.supergenius.xo.base.enums.EUser;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.manager.dao.ComplaintDao;
import com.supergenius.xo.manager.entity.Certificate;
import com.supergenius.xo.manager.entity.Complaint;
import com.supergenius.xo.manager.entity.Expert;
import com.supergenius.xo.manager.entity.Judge;
import com.supergenius.xo.manager.service.CertificateSO;
import com.supergenius.xo.manager.service.ComplaintSO;
import com.supergenius.xo.manager.service.ExpertSO;
import com.supergenius.xo.manager.service.JudgeSO;

/**
 * 投诉举报Impl
 * @author XieMing
 * @date 2016-7-18 下午2:32:20
 */
@Service
public class ComplaintSOImpl extends BaseSOImpl<Complaint> implements ComplaintSO {

	@Autowired
	ComplaintDao dao;
	
	@Autowired
	AdminLogSO adminLogSO;
	
	@Autowired
	JudgeSO judgeSO;
	
	@Autowired
	CertificateSO certificateSO;
	
	@Autowired
	ExpertSO expertSO;
	
	@Override
	protected ComplaintDao getDao() {
		return dao;
	}

	@Override
	public List<Complaint> getListByToUserType(Pager pager, EUser tousertype) {
		Map<String, Object> map = getParamMap(pager);
		map.put(MapperDict.tousertype, tousertype);
		return dao.getList(map);
	}

	@Override
	public List<Complaint> search(Map<String, Object> map) {
		Map<String, Object> newmap = getParamMap();
		newmap.putAll(map);
		return dao.search(newmap);
	}

	@Override
	public Complaint getOneByRefuid(String uid) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.refuid, uid);
		return dao.getOne(map);
	}

	@Override
	public Complaint getOneByRefuid(String fromuseruid, String touseruid, String refuid) {
		Map<String, Object> map = getParamMap(true);
		map.put(MapperDict.fromuseruid, fromuseruid);
		map.put(MapperDict.touseruid, touseruid);
		map.put(MapperDict.refuid, refuid);
		return dao.getOne(map);
	}

	@Override
	public int getCount(EUser tousertype) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.tousertype, tousertype);
		return dao.getCount(map);
	}

	@Override
	public int getEableCount() {
		Map<String, Object> map = getParamMap();
		return dao.getCount(map);
	}

	@Override
	public int searchCount(Map<String, Object> map) {
		Map<String, Object> newmap = getParamMap(true);
		newmap.putAll(map);
		return dao.searchCount(newmap);
	}

	@Override
	public List<Complaint> getList(String fromuseruid) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.fromuseruid, fromuseruid);
		return dao.getList(map);
	}

	@Override
	public boolean update(Complaint complaint, AdminLog adminLog) {
		return dao.update(complaint) && adminLogSO.add(adminLog);
	}

	@Override
	public Complaint getOne(String touseruid, String refuid, EUser tousertype) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.touseruid, touseruid);
		map.put(MapperDict.refuid, refuid);
		map.put(MapperDict.tousertype, tousertype);
		return dao.getOne(map);
	}

	@Override
	public List<Complaint> searchList(Map<String, Object> map) {
		Map<String, Object> newMap = getParamMap(true);
		newMap.putAll(map);
		return dao.searchList(newMap);
	}

	@Override
	public boolean update(Complaint complaint, Judge judge, Certificate certificate) {
		return dao.update(complaint) && judgeSO.update(judge) && certificateSO.update(certificate);
	}

	@Override
	public boolean update(Complaint complaint, Expert expert, Certificate certificate) {
		return dao.update(complaint) && expertSO.update(expert) && certificateSO.update(certificate);
	}
}
