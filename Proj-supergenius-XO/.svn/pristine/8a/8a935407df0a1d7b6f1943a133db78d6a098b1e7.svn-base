package com.supergenius.xo.life.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.genius.xo.base.dao.BaseDao;
import com.supergenius.xo.life.entity.Essay;

/**
 * 动态Dao
 * 
 * @author ChenQi
 * @date 2018年5月9日17:14:33
 */
@Component("lifeEssayDao")
public interface EssayDao extends BaseDao<Essay> {

	/**
	 * 根据搜索条件得到count
	 * 
	 * @param map
	 * @return
	 * @author ChenQi
	 * @create 2018年5月9日17:14:33
	 */
	int searchCount(Map<String, Object> map);

	/**
	 * 根据搜索条件得到List
	 * 
	 * @param map
	 * @return
	 * @author ChenQi
	 * @create 2018年5月9日17:14:33
	 */
	List<Essay> search(Map<String, Object> map);

	/**
	 * 得到fromuid的list
	 * 
	 * @param map
	 * @return
	 */
	List<String> getFromuidList(Map<String, Object> map);

	/**
	 * 获取参与动态的人数
	 * 
	 * @param map
	 * @return
	 * @author ChenQi
	 * @create 2018年5月9日17:14:33
	 */
	int getCountByfromuseruid(Map<String, Object> map);
}
