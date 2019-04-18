package com.supergenius.xo.startup.service;

import com.genius.model.base.enums.EStatus;
import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.startup.entity.Question;

/**
 * 题库so
 * @author ChenQi
 * @date 2017年6月20日11:58:34
 */
public interface QuestionSO extends BaseSO<Question> {
	
	/**
	 * 根据状态返回数量
	 * @author liubin
	 * @date 2017年6月27日 下午6:58:07
	 * @return int
	 */
	int getCount(EStatus status);
	
	/**
	 * 添加问题
	 * @author liubin
	 * @date 2017年6月28日 下午6:40:14
	 * @return boolean
	 */
	boolean add(Question question, AdminLog adminLog);
	
	/**
	 * 根据order更新所有的order字段
	 * @author liubin
	 * @date 2017年6月29日 下午4:22:18
	 * @return boolean
	 */
	boolean updateFieldsByOrderIncr(int order);
	
	/**
	 * 根据order获得question对象
	 * @author liubin
	 * @date 2017年6月29日 下午4:36:01
	 * @return Question
	 */
	Question getOne(int order);
	
	/**
	 * 更新所有的order减1
	 * @author liubin
	 * @date 2017年6月30日 下午12:34:00
	 * @return boolean
	 */
	boolean updateFieldsByOrderDecr(int order);
	
	/**
	 * 更新问题
	 * @author liubin
	 * @date 2017年7月2日 下午4:39:39
	 * @return boolean
	 */
	boolean update(Question question, AdminLog adminLog);
}
