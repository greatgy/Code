package com.supergenius.xo.gupage.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.genius.xo.base.dao.BaseDao;
import com.supergenius.xo.gupage.entity.Comments;

/**
 * 评论Dao
 * 
 * @author yangguang
 * @date 2017年11月13日16:00:58
 */
@Component("gupageCommentsDao")
public interface CommentsDao extends BaseDao<Comments> {

	/**
	 * 根据搜索条件得到count
	 * 
	 * @param map
	 * @return
	 * @author yangguang
	 * @create 2017年11月13日16:01:51
	 */
	int searchCount(Map<String, Object> map);

	/**
	 * 根据搜索条件得到List
	 * 
	 * @param map
	 * @return
	 * @author yangguang
	 * @create 2017年11月13日16:01:40
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
	 * @author yangguang
	 * @create 2017年11月13日16:01:29
	 */
	int getCountByfromuseruid(Map<String, Object> map);
}
