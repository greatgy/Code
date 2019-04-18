package com.supergenius.xo.startup.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.genius.xo.base.dao.BaseDao;
import com.supergenius.xo.startup.entity.Comments;

/**
 * 评论Dao
 * 
 * @author ChenQi
 * @date 2017年8月23日11:16:10
 */
@Component("startupCommentsDao")
public interface CommentsDao extends BaseDao<Comments> {

	/**
	 * 根据搜索条件得到count
	 * 
	 * @param map
	 * @return
	 * @author ChenQi
	 * @create 2017年8月25日16:29:41
	 */
	int searchCount(Map<String, Object> map);

	/**
	 * 根据搜索条件得到List
	 * 
	 * @param map
	 * @return
	 * @author ChenQi
	 * @create 2017年8月25日16:30:03
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
	 * @create 2017年9月5日18:20:16
	 */
	int getCountByfromuseruid(Map<String, Object> map);
}
