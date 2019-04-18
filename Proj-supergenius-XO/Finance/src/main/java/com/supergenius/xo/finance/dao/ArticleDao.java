package com.supergenius.xo.finance.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.genius.xo.base.dao.BaseDao;
import com.supergenius.xo.finance.entity.Article;

/**
 * 文章Dao
 * @author XueZhenYong
 * @date 2017年12月29日下午2:56:26
 */
@Component("financeArticleDao")
public interface ArticleDao extends BaseDao<Article>{
	
	/**
	 * 获得我的收藏
	 * 
	 * @author ChenQi
	 * @data 2018年1月4日15:24:41
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

	/**
	 * 更新字段
	 * @param map
	 * @return
	 * @author JiaShitao
	 * @date 2018年7月20日14:57:30
	 */
	boolean updateFields(Map<String, Object> map);
}
