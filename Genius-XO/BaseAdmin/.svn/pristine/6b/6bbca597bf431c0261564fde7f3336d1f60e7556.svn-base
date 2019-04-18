package com.genius.xo.baseadmin.service;

import java.util.List;

import com.genius.model.base.enums.EStatus;
import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.model.baseadmin.entity.Role;

/**
 * RoleSO角色业务逻辑
 * 
 * @author architect.bian
 * @createtime 2014-7-28 下午5:37:03
 */
public interface RoleSO extends BaseSO<Role> {

	List<Role> getListByAdminuid(String adminuid, boolean... allstatus);

	boolean deleteByAdminuid(String adminid);

	Role getByRoleid(String roleid);
	
	/**
	 * 更新角色状态,插入操作记录
	 * @param adminLog
	 * @param status
	 * @param ids
	 * @author YuYingJie
	 */
	boolean updateStatus(AdminLog adminLog, EStatus status, String[] ids);

}
