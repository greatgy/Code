package com.supergenius.xo.gupage.dao;

import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.genius.xo.base.dao.BaseDao;
import com.supergenius.xo.gupage.entity.Article;

/**
 * 文章Dao
 * @author yangguang
 * @date 2017年11月13日16:00:07
 */
@Component("gupageArticleDao")
public interface ArticleDao extends BaseDao<Article> {
	/**
	 * 获取文章创建时间大于当前时间的最小创建时间
	 * @param map
	 * @return
	 * @author yangguang
	 * @date 2018年1月29日14:57:30
	 */
	Date getCacheTime(Map<String, Object> map);
}
