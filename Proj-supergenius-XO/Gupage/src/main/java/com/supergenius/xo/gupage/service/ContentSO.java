package com.supergenius.xo.gupage.service;

import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.gupage.entity.Content;

/**
 * 内容so
 * 
 * @author loupengyu
 * @date 2018年1月10日11:11:18
 */
public interface ContentSO extends BaseSO<Content> {

	/**
	 * 添加内容
	 * 
	 * @author XueZhenYong
	 * @Datetime 2018年1月11日上午11:46:13
	 */
	boolean add(Content content, AdminLog adminLog);

	/**
	 * 更新内容
	 * 
	 * @author XueZhenYong
	 * @date 2018年1月11日上午11:46:13
	 * @return boolean
	 */
	boolean update(Content content, AdminLog adminLog);

	/**
	 * 冻结或解冻
	 * 
	 * @author XueZhenYong
	 * @Datetime 2018年1月11日上午11:47:09
	 */
	boolean updateStatusByUid(String uid);
}
