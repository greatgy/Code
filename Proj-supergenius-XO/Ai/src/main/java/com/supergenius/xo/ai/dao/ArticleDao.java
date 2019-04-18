package com.supergenius.xo.ai.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.genius.xo.base.dao.BaseDao;
import com.supergenius.xo.ai.entity.Article;

/**
 * 文章Dao
 * @author 杨光
 * @date 2017年9月19日09:47:55
 */
@Component("aiArticleDao")
public interface ArticleDao extends BaseDao<Article> {
	
	/**
	 * 获得我的收藏
	 * 
	 * @author 杨光
	 * @data 2017年9月19日18:21:08
	 */
	List<Article> getCollectList(Map<String, Object> map);
	
	/**
	 * 批量更新到删除状态
	 * 
	 * @author JiaShitao
	 * @data 2018年7月25日18:21:08
	 */
	boolean updateToDeleted(Map<String, Object> map);
}
