package com.supergenius.xo.finance.service;

import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.finance.entity.Label;

/**
 * 标签so
 * 
 * @author LouPengYu
 * @date 2018年1月2日09:55:19
 */
public interface LabelSO extends BaseSO<Label> {

	/**
	 * 添加标签
	 * 
	 * @author 
	 * @date 
	 * @return 
	 */
	boolean add(Label label, AdminLog adminLog);
	
	/**
	 * 更改标签
	 * 
	 * @author 
	 * @date 
	 * @return boolean
	 */
	boolean update(Label label, AdminLog adminLog);



}
