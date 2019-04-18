package com.genius.xo.baseadmin.serviceimpl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.genius.core.base.constant.BaseMapperDict;
import com.genius.model.base.enums.EStatus;
import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.model.baseadmin.entity.StatusLog;
import com.genius.xo.baseadmin.service.AdminLogSO;
import com.genius.xo.baseadmin.service.StatusLogSO;

/**
 * 所有service层的基类
 *
 * @param <T>
 * @author architect.bian
 * 2014-6-24 上午11:23:06
 */
public abstract class BaseSOImpl<T> extends com.genius.xo.base.serviceimpl.BaseSOImpl<T> {

	@Autowired
	private StatusLogSO statusLogSO;
	
	@Autowired
	private AdminLogSO adminLogSO;
	
	/**
	 * 更新状态
	 * @param statusDetail
	 * @param status
	 * @param ids
	 * @return
	 * @author Architect.bian
	 * @createtime 2014-7-27 下午4:12:07
	 * @modifier architect.bian
	 */
	@Deprecated
	public boolean updateStatus(StatusLog statusLog, EStatus status, String... ids) {
		HashMap<String, Object> map = new HashMap<>();
		for (String id : ids) {
			map.clear();
			map.put(BaseMapperDict.uid, id);
			map.put(BaseMapperDict.oid, id);
			map.put(BaseMapperDict.status, status);
			getDao().updateFields(map);
			statusLog.setFromuid(id);
			statusLogSO.add(statusLog);
		}
		return true;
	}
	
	/**
	 * 更新状态
	 * @param log
	 * @param status
	 * @param ids
	 * @return
	 * @author Architect.bian
	 * @createtime 2014-10-17 上午11:21:52
	 */
	public boolean update(AdminLog log, EStatus status, String... ids) {
		HashMap<String, Object> map = new HashMap<>();
		for (String id : ids) {
			map.clear();
			map.put(BaseMapperDict.uid, id);
			map.put(BaseMapperDict.oid, id);
			map.put(BaseMapperDict.status, status);
			getDao().updateFields(map);
			//操作成功
			log.setDataid(id);
			adminLogSO.add(log);
		}
		return true;
	}
	
}
