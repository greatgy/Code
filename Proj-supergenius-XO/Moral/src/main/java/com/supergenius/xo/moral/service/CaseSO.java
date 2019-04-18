package com.supergenius.xo.moral.service;

import java.util.List;

import com.genius.model.base.entity.Pager;
import com.genius.model.base.enums.EStatus;
import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.moral.entity.Case;
import com.supergenius.xo.moral.enums.ECase;

/**
 * 案例库SO
 * 
 * @author LiJiacheng
 */
public interface CaseSO extends BaseSO<Case> {
	
	/**
	 * 修改状态
	 * @param eStatus
	 * @param ids
	 * @return
	 * @author liushaomin
	 */
	boolean update(EStatus eStatus, String[] ids);

	/**
	 * 案例下载量加一
	 * @param file
	 * @return
	 * @author YuYingJie
	 */
	boolean updateCount(Case file);

	/**
	 * 获取案例
	 * @param doc
	 * @param pager
	 * @return
	 * @author YuYingJie
	 */
	List<Case> getList(ECase doc, Pager pager);
	
	/**
	 * 获取List,自定义排序字段
	 * @param doc
	 * @param pager
	 * @return
	 * @author YuYingJie
	 */
	List<Case> getList(ECase eCase, Pager pager, String orderBy);

}
