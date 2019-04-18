package com.supergenius.xo.moralnews.service;

import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.moralnews.entity.Content;

/**
 * 内容SO
 * 
 * @author JiaShitao
 * @date 2018年9月19日09:47:55
 */
public interface ContentSO extends BaseSO<Content>{
	/**
	 * 添加内容
	 * 
	 * @author tf
	 * @date 2017年9月19日
	 * @return boolean
	 */
	boolean add(Content content, AdminLog adminLog);
	
	/**
	 * 更新内容
	 * 
	 * @author tf
	 * @date 2017年9月19日
	 * @return boolean
	 */
	boolean update(Content content, AdminLog adminLog);
}
