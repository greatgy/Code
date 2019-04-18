package com.genius.xo.baseadmin.service;

import com.genius.model.base.enums.EStatus;
import com.genius.model.baseadmin.entity.Admin;
import com.genius.model.baseadmin.entity.AdminLog;

/**
 * @author Architect.bian
 * 
 */
public interface AdminSO extends BaseSO<Admin> {

	Admin getByAdminid(String adminid);

	void updatePwd(Admin admin, String pwd);

	void updateDoPwd(Admin admin, String dopwd);

	/**
	 * 更新管理员状态,插入操作记录
	 * @param adminLog
	 * @param status
	 * @param ids
	 * @author YuYingJie
	 */
	boolean updateStatus(AdminLog adminLog, EStatus status, String[] ids);

}
