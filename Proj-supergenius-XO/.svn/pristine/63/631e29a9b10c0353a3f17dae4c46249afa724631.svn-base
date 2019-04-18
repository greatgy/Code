package com.supergenius.xo.startup.service;

import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.startup.entity.Ruler;

/**
 * 规则so
 * 
 * @author ChenQi
 * @date 2017年6月20日11:58:34
 */
public interface RulerSO extends BaseSO<Ruler> {

	/**
	 * 批量删除
	 * 
	 * @param ids
	 * @author ChenQi
	 * @return
	 */
	boolean deleteByUids(String[] ids);

	/**
	 * 添加规则
	 * 
	 * @param ruler
	 * @param adminLog
	 * @author ChenQi
	 * @return
	 */
	boolean add(Ruler ruler, AdminLog adminLog);

	/**
	 * 编辑规则
	 * 
	 * @param ruler
	 * @param adminLog
	 * @author ChenQi
	 * @return
	 */
	boolean update(Ruler ruler, AdminLog adminLog);
	
	/**
	 * 根据分数和一票否决数获得规则
	 * @return
	 * @author ChenQi 2017年6月21日16:32:28
	 */
	Ruler getOne(int sum, int count);
}
