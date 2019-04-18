package com.supergenius.xo.manager.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.genius.xo.base.dao.BaseDao;
import com.supergenius.xo.manager.entity.Comments;

/**
 * 评论Dao
 * @author XieMing
 * @date 2016-7-18 下午2:25:14
 */
@Component("managerCommentsDao")
public interface CommentsDao extends BaseDao<Comments> {

	/**
	 * 根据键值对选择数量
	 * @param map
	 * @return
	 */
	int getSpeakCount(Map<String, Object> map);
	
	/**
	 * 连表查询获得count
	 * @param map
	 * @return
	 * @author chenminchang
	 * @create 2016-11-4下午4:39:27
	 */
	int searchCount(Map<String, Object> map);
	
	/**
	 * 连表查询获得list
	 * @param map
	 * @return
	 * @author chenminchang
	 * @create 2016-11-4下午4:40:15
	 */
	List<Comments> search(Map<String, Object> map);
	
	/**
	 * 更新comments的status
	 * @param map
	 * @return
	 * @author chenminchang
	 * @create 2016-11-8下午6:31:50
	 */
	boolean updateStatus(Map<String, Object> map);
}
