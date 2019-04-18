package com.supergenius.xo.career.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.genius.xo.base.dao.BaseDao;
import com.supergenius.xo.career.entity.Answer;

/**
 * 回答Dao
 * 
 * @author ChenQi
 * @date 2017年11月13日17:23:05
 */
@Component("CareerAnswerDao")
public interface AnswerDao extends BaseDao<Answer> {
	
	/**
	 * 根据搜索条件得到count
	 * 
	 * @param map
	 * @return
	 * @author ChenQi
	 * @create 2017年11月14日18:28:41
	 */
	int searchCount(Map<String, Object> map);

	/**
	 * 根据搜索条件得到List
	 * 
	 * @param map
	 * @return
	 * @author ChenQi
	 * @create 2017年11月14日18:28:45
	 */
	List<Answer> search(Map<String, Object> map);

	/**
	 * 得到fromuid的list
	 * 
	 * @param map
	 * @return
	 */
	List<String> getFromuidList(Map<String, Object> map);

	/**
	 * 获取参与的人数
	 * 
	 * @param map
	 * @return
	 * @author ChenQi
	 * @create 2017年11月14日18:28:48
	 */
	int getCountByfromuseruid(Map<String, Object> map);
}
