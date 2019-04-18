package com.supergenius.xo.manager.serviceimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.core.base.constant.BaseMapperDict;
import com.genius.model.base.enums.EStatus;
import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.genius.xo.baseadmin.service.AdminLogSO;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.manager.dao.CertificateDao;
import com.supergenius.xo.manager.entity.Certificate;
import com.supergenius.xo.manager.enums.ECertificate;
import com.supergenius.xo.manager.enums.EMajor;
import com.supergenius.xo.manager.service.CertificateSO;

/**
 * 证书明细Impl
 * @author XieMing
 * @date 2016-7-18 下午2:31:22
 */
@Service
public class CertificateSOImpl extends BaseSOImpl<Certificate> implements CertificateSO {

	@Autowired
	CertificateDao dao;
	
	@Autowired
	AdminLogSO adminLogSO;
	
	@Override
	protected BaseDao<Certificate> getDao() {
		return dao;
	}

	@Override
	public Certificate getOne(EMajor major, String useruid, ECertificate type) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.major, major);
		map.put(MapperDict.useruid, useruid);
		map.put(MapperDict.type, type);
		return dao.getOne(map);
	}

	@Override
	public Certificate getOne(String useruid, EMajor major) {
		Map<String, Object> map = getParamMap();
		map.remove(BaseMapperDict.ascDesc);
		map.put(MapperDict.type + BaseMapperDict.desc, true);
		map.put(MapperDict.major, major);
		map.put(MapperDict.useruid, useruid);
		return dao.getOne(map);
	}

	@Override
	public int getCountByType(String useruid, ECertificate type) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.useruid, useruid);
		map.put(MapperDict.type, type);
		return dao.getCount(map);
	}

	@Override
	public List<Certificate> getList(String useruid, EMajor major, List<ECertificate> list) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.useruid, useruid);
		map.put(MapperDict.major, major);
		map.put(MapperDict.type + MapperDict.suffix_in_key, list);
		return dao.getList(map);
	}

	@Override
	public boolean updateStatus(String uid, EStatus status) {
		Map<String, Object> map = getParamMap(true);
		map.put(MapperDict.uid, uid);
		map.put(MapperDict.status, status);
		return dao.updateFields(map);
	}

	@Override
	public int searchCount(Map<String, Object> map) {
		return dao.searchCount(map);
	}

	@Override
	public List<Certificate> searchList(Map<String, Object> map) {
		return dao.searchList(map);
	}

	@Override
	public boolean update(Certificate certificate, AdminLog adminLog) {
		return dao.update(certificate) && adminLogSO.add(adminLog);
	}

	@Override
	public Certificate getOne(String useruid, ECertificate type) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.useruid, useruid);
		map.put(MapperDict.type, type);
		return dao.getOne(map);
	}
}
