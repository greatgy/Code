package com.genius.xo.baseadmin.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.core.base.constant.BaseMapperDict;
import com.genius.core.base.constant.SysConst;
import com.genius.model.base.entity.Pager;
import com.genius.model.base.enums.EStatus;
import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.model.baseadmin.entity.Role;
import com.genius.xo.baseadmin.dao.RoleDao;
import com.genius.xo.baseadmin.service.AdminLogSO;
import com.genius.xo.baseadmin.service.AuthoritySO;
import com.genius.xo.baseadmin.service.RoleSO;

/**
 * 角色业务逻辑实现
 * 
 * @author architect.bian
 * @createtime 2014-7-28 下午7:09:20
 */
@Service
public class RoleSOImpl extends BaseSOImpl<Role> implements RoleSO {

	@Autowired
	private RoleDao dao;
	
	@Autowired
	private AuthoritySO authoritySO;

	@Autowired
	private AdminLogSO adminLogSO;
	
	@Override
	protected RoleDao getDao() {
		return dao;
	}
	
	/**
	 * 获取所有的adminuid为null的所有角色，即可供选择的角色
	 */
	@Override
	public List<Role> getList() {
		Map<String, Object> map = getParamMap(true);
		map.put(BaseMapperDict.adminuid + BaseMapperDict.suffix_isnull_key, true);
		return dao.getList(map);
	}
	
	/**
	 * 分页获取adminuid为null的角色，排除roleid为ROLE_ADMIN的项
	 */
	@Override
	public List<Role> getList(Pager pager) {
		Map<String, Object> map = getParamMap(pager);
		map.put(BaseMapperDict.adminuid + BaseMapperDict.suffix_isnull_key, true);
		map.put(BaseMapperDict.prefix_no_key + BaseMapperDict.roleid, SysConst.ROLE_ADMIN);
		return dao.getList(map);
	}
	
	/**
	 * 通过map获取adminuid为null的角色，排除roleid为ROLE_ADMIN的项
	 */
	@Override
	public List<Role> getList(Map<String, Object> map) {
		map.put(BaseMapperDict.adminuid + BaseMapperDict.suffix_isnull_key, true);
		map.put(BaseMapperDict.prefix_no_key + BaseMapperDict.roleid, SysConst.ROLE_ADMIN);
		return dao.getList(map);
	}

	@Override
	public List<Role> getListByAdminuid(String adminuid, boolean... allstatus) {
		Map<String, Object> map = getParamMap(allstatus);
		map.put(BaseMapperDict.adminuid, adminuid);
		return dao.getList(map);
	}

	@Override
	public boolean deleteByAdminuid(String adminuid) {
		Map<String, Object> map = getParamMap();
		map.put(BaseMapperDict.adminuid, adminuid);
		dao.deleteByMap(map);
		return true;
	}

	@Override
	public Role getByRoleid(String roleid) {
		Map<String,Object> map = getParamMap();
		map.put(BaseMapperDict.roleid, roleid);
		return dao.getOne(map);
	}

	@Override
	public boolean add(Role t) {
		dao.insert(t);
		authoritySO.add(t.getAuthorityList());
		return true;
	}
	
	@Override
	public boolean update(Role t) {
		Role r = dao.get(t.getUid());
		r.set(t);
		dao.update(t);
		authoritySO.deleteByField(BaseMapperDict.roleuid, t.getUid());
		authoritySO.add(t.getAuthorityList());
		return true;
	}

	@Override
	public boolean delete(String... ids) {
		for (String id : ids) {
			dao.delete(id);
			authoritySO.deleteByField(BaseMapperDict.roleuid, id);
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
