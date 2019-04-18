package com.supergenius.xo.ai.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.genius.xo.base.dao.BaseDao;
import com.supergenius.xo.ai.entity.Comments;

/**
 * 评论Dao
 * 
 * @author xuzhixiang
 * @date 2017年9月19日09:48:37
 */
@Component("aiCommentsDao")
public interface CommentsDao extends BaseDao<Comments> {

	/**
	 * 根据搜索条件得到count
	 * 
	 * @param map
	 * @return
	 * @author xuzhixiang
	 * @create 2017年9月19日09:49:48
	 */
	int searchCount(Map<String, Object> map);

	/**
	 * 根据搜索条件得到List
	 * 
	 * @param map
	 * @return
	 * @author xuzhixiang
	 * @create 2017年9月19日09:49:43
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
	 * @author xuzhixiang
	 * @create 2017年9月19日09:49:38
	 */
	int getCountByfromuseruid(Map<String, Object> map);
}
