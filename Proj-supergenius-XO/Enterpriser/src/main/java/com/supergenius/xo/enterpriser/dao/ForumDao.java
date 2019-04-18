package com.supergenius.xo.enterpriser.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.genius.xo.base.dao.BaseDao;
import com.supergenius.xo.enterpriser.entity.Forum;

/**
 * 文章Dao
 * @author XueZhenYong
 * @date 2017年12月29日下午2:56:26
 */
@Component("enterpriserForumDao")
public interface ForumDao extends BaseDao<Forum>{
	
	/**
	 * 获得我的收藏
	 * 
	 * @author ChenQi
	 * @data 2018年1月4日15:24:41
	 */
	List<Forum> getCollectList(Map<String, Object> map);
	
	/**
	 * 获取文章创建时间大于当前时间的最小创建时间
	 * @param map
	 * @return
	 * @author yangguang
	 * @date 2018年1月29日14:57:30
	 */
	Date getCacheTime(Map<String, Object> map);
}
