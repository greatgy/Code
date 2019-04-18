package com.supergenius.xo.life.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.genius.xo.base.dao.BaseDao;
import com.supergenius.xo.life.entity.Article;

/**
 * 文章Dao
 * @author ChenQi
 * @date 2018年5月9日15:44:07
 */
@Component("lifeArticleDao")
public interface ArticleDao extends BaseDao<Article> {
	
	/**
	 * 获得我的收藏
	 * 
	 * @author ChenQi
	 * @data 2018年5月9日15:44:10
	 */
	List<Article> getCollectList(Map<String, Object> map);
	
	/**
	 * 获取文章创建时间大于当前时间的最小创建时间
	 * @param map
	 * @return
	 * @author ChenQi
	 * @date 2018年5月9日15:44:14
	 */
	Date getCacheTime(Map<String, Object> map);
}
