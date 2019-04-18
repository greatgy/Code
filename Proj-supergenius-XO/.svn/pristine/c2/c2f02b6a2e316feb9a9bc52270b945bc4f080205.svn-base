package com.supergenius.xo.career.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.genius.xo.base.dao.BaseDao;
import com.supergenius.xo.career.entity.Article;

/**
 * 文章Dao
 * @author yangguang
 * @date 2017年11月13日16:00:07
 */
@Component("careerArticleDao")
public interface ArticleDao extends BaseDao<Article> {
	
	/**
	 * 获得我的收藏
	 * 
	 * @author yangguang
	 * @data 2017年11月13日16:00:20
	 */
	List<Article> getCollectList(Map<String, Object> map);
	
	/**
	 * 获取文章创建时间大于当前时间的最小创建时间
	 * @param map
	 * @return
	 * @author yangguang
	 * @date 2018年1月29日14:57:30
	 */
	Date getCacheTime(Map<String, Object> map);
}
