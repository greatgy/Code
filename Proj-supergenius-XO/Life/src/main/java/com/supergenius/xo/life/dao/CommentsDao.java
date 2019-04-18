package com.supergenius.xo.life.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.genius.xo.base.dao.BaseDao;
import com.supergenius.xo.life.entity.Comments;

/**
 * 评论Dao
 * 
 * @author ChenQi
 * @date 2018年5月9日16:35:20
 */
@Component("lifeCommentsDao")
public interface CommentsDao extends BaseDao<Comments>{
	
	/**
	 * 根据搜索条件得到count
	 * 
	 * @param map
	 * @return
	 * @author ChenQi
	 * @create 2018年5月9日16:35:25
	 */
	int searchCount(Map<String, Object> map);

	/**
	 * 根据搜索条件得到List
	 * 
	 * @param map
	 * @return
	 * @author ChenQi
	 * @create 2018年5月9日16:35:29
	 */
	List<Comments> search(Map<String, Object> map);

	/**
	 * 得到fromuid的list
	 * 
	 * @param map
	 * @return
	 */
	List<String> getFromuidList(Map<String, Object> map);

	/**
	 * 获取收藏的人数
	 * 
	 * @param map
	 * @return
	 * @author ChenQi
	 * @create 2018年5月9日16:35:32
	 */
	int getCountByfromuseruid(Map<String, Object> map);
}
