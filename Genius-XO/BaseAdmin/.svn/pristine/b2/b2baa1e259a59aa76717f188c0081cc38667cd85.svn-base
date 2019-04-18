package com.genius.xo.baseadmin.serviceimpl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.core.base.constant.BaseMapperDict;
import com.genius.model.base.enums.EStatus;
import com.genius.model.baseadmin.entity.Admin;
import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.baseadmin.dao.AdminDao;
import com.genius.xo.baseadmin.service.AdminLogSO;
import com.genius.xo.baseadmin.service.AdminSO;
import com.genius.xo.baseadmin.service.RoleSO;

/**
 * @author Architect.bian
 * 
 */
@Service
public class AdminSOImpl extends BaseSOImpl<Admin> implements AdminSO {

	@Autowired
	private AdminDao dao;
	
	@Autowired
	private RoleSO roleSO;
	
	@Autowired
	private AdminLogSO adminLogSO;

	@Override
	protected BaseDao<Admin> getDao() {
		return dao;
	}

	@Override
	public Admin getByAdminid(String adminid) {
		Map<String,Object> map = getParamMap(true);
		map.put(BaseMapperDict.adminid, adminid);
		return dao.getOne(map);
	}

	@Override
	public void updatePwd(Admin admin, String pwd) {
		Map<String, Object> map = new HashMap<>();
		admin.initPwd(pwd);
		map.put(BaseMapperDict.uid, admin.getUid());
		map.put(BaseMapperDict.pwd, admin.getPwd());
		dao.updateFields(map);
	}

	@Override
	public void updateDoPwd(Admin admin, String dopwd) {
		Map<String, Object> map = new HashMap<>();
		admin.initDoPwd(dopwd);
		map.put(BaseMapperDict.uid, admin.getUid());
		map.put(BaseMapperDict.dopwd, admin.getDopwd());
		dao.updateFields(map);
	}
	
	@Override
	public boolean add(Admin t) {
		t.initPwd(t.getPwd());
		t.initDoPwd(t.getDopwd());
		dao.insert(t);
		roleSO.add(t.getRoleList());
		return true;
	}

	@Override
	public boolean update(Admin t) {
		Admin admin = dao.get(t.getUid());
		admin.set(t);
		dao.update(admin);
		roleSO.deleteByAdminuid(admin.getUid());
		roleSO.add(admin.getRoleList());
		return true;
	}

	@Override
	public boolean delete(String... ids) {
		for (String id : ids) {
			dao.delete(id);
			roleSO.deleteByAdminuid(id);
		}
		return true;
	}

	@Override
	public boolean updateStatus(AdminLog adminLog, EStatus status, String[] ids) {
		HashMap<String, Object> map = new HashMap<>();
		for (String id : ids) {
			map.clear();
			map.put(BaseMapperDict.uid, id);
			map.put(BaseMapperDict.status, status);
			dao.updateFields(map);
			adminLog.setDataid(id);
			adminLogSO.add(adminLog);
		}
		return true;
	}

}
