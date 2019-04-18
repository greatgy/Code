package com.supergenius.xo.entrepreneur.service;

import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.entrepreneur.entity.Content;

/**
 * 内容SO
 * 
 * @author tf
 * @date 2018年7月5日
 */
public interface ContentSO extends BaseSO<Content>{

	/**
	 * 添加内容
	 * 
	 * @author tf
	 * @date 2018年7月5日
	 * @return boolean
	 */
	boolean add(Content content, AdminLog adminLog);
	
	/**
	 * 更新内容
	 * 
	 * @author tf
	 * @date 2018年7月5日
	 * @return boolean
	 */
	boolean update(Content content, AdminLog adminLog);
	
	/**
	 * 冻结或解冻
	 * 
	 * @author tf
	 * @date 2018年7月5日
	 * @return boolean
	 */
	boolean updateStatusByUid(String uid);

}
