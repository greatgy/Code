package com.supergenius.xo.life.service;

import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.life.entity.Content;
/**
 * 内容SO
 *@Author:JiaShitao
 *@Date:2018年5月7日下午5:50:28
 */
public interface ContentSO extends BaseSO<Content> {

	/**
	 * 添加内容
	 * 
	 * @author YangGuang
	 * @date 2018年5月9日18:15:01
	 * @return boolean
	 */
	boolean add(Content content, AdminLog adminLog);

	/**
	 * 更新内容
	 * 
	 * @author YangGuang
	 * @date 2018年5月9日18:15:01
	 * @return boolean
	 */
	boolean update(Content content, AdminLog adminLog);
	
	/**
	 * 冻结或解冻
	 * 
	 * @author YangGuang
	 * @date 2018年5月9日18:19:00
	 * @return boolean
	 */
	boolean updateStatusByUid(String uid);
}
