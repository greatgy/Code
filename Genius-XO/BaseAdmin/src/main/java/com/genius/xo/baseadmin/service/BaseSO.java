package com.genius.xo.baseadmin.service;

import com.genius.model.base.enums.EStatus;
import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.model.baseadmin.entity.StatusLog;

/**
 * @author Architect.bian
 * 
 */
public interface BaseSO<T> extends com.genius.xo.base.service.BaseSO<T> {

	/**
	 * 更新状态
	 * @param map
	 */
	@Deprecated
	boolean updateStatus(StatusLog statusLog, EStatus status, String... ids);
	
	/**
	 * 更新状态
	 * @param map
	 */
	boolean update(AdminLog adminlog, EStatus status, String... ids);
	
	/**
	 * 针对性的更新某几个字段
	 * @param map
	 */
	//boolean updateField(String fieldname, T t) throws Exception;
}
