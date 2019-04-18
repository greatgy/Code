package com.supergenius.xo.entrepreneur.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

import com.genius.xo.base.dao.BaseDao;
import com.supergenius.xo.entrepreneur.entity.Article;

/**
 * 文章Dao
 * @author JiaShitao
 * @date 2018年7月3日09:47:55
 */
@Component("entrepreneurArticleDao")
public interface ArticleDao extends BaseDao<Article> {
	
	/**
	 * 获取文章创建时间大于当前时间的最小创建时间
	 * @param map
	 * @return
	 * @author JiaShitao
	 * @date 2018年7月11日15:44:14
	 */
	Date getCacheTime(Map<String, Object> map);
	
	/**
	 * 获得我的收藏
	 * 
	 * @author JiaShitao
	 * @data 2018年7月3日18:21:08
	 */
	List<Article> getCollectList(Map<String, Object> map);
	
	/**
	 * 批量删除
	 * 
	 * @author JiaShitao
	 * @data 2018年7月19日18:21:08
	 */
	boolean updateToDeleted(Map<String, Object> map);
}
