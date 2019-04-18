package com.supergenius.xo.startup.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.genius.xo.base.dao.BaseDao;
import com.supergenius.xo.startup.entity.Article;

/**
 * 文章Dao
 * @author 许志翔
 * @date 2017年8月23日14:39:00
 */
@Component
public interface ArticleDao extends BaseDao<Article> {
	/**
	 * 获取收藏的文章列表
	 * @param map
	 * @return
	 * @author 许志翔
	 * @date 2017年9月8日10:59:46
	 */
	List<Article> getListByUseruid(Map<String, Object> map);
	
	/**
	 * 获取文章创建时间大于当前时间的最小创建时间
	 * @param map
	 * @return
	 * @author yangguang
	 * @date 2018年1月29日14:57:30
	 */
	Date getCacheTime(Map<String, Object> map);
	
}
