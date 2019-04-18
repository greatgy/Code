package com.supergenius.xo.life.service;

import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.life.entity.Subject;

/**
 * 科目SO
 *@Author:JiaShitao
 *@Date:2018年5月7日下午6:06:20
 */

public interface SubjectSO extends BaseSO<Subject> {

	/**
	 * 根据sid获取
	 * 
	 * @param sid
	 * @return
	 * @author YangGuang
	 * @date 2018年5月10日15:58:18
	 */
	Subject get(int sid);
	
	/**
	 * 冻结或解冻
	 * 
	 * @author YangGuang
	 * @date 2018年5月10日16:12:03
	 * @return boolean
	 */
	boolean updateStatusBySid(int sid);
}
