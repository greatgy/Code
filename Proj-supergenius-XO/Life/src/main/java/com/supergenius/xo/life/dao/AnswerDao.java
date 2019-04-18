package com.supergenius.xo.life.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.genius.xo.base.dao.BaseDao;
import com.supergenius.xo.life.entity.Answer;

/**
 * 回答Dao
 * 
 * @author YangGuang
 * @date 2018年5月9日16:32:41
 */
@Component("LifeAnswerDao")
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

}
