package com.supergenius.xo.tpi.dao;

import java.util.List;

import com.genius.model.base.entity.Pager;
import com.genius.xo.mongodb.dao.BaseMongoDao;
import com.supergenius.xo.tpi.entity.Article;
import com.supergenius.xo.tpi.enums.EArticleChannel;

/**
 * 文章Dao
 *  
 * @author ShangJianguo
 */
public interface ArticleDao extends BaseMongoDao<Article>{

	/**
	 * 进行模糊查询，根据文章标题和内容进行搜索
	 * @param 搜索的关键字 
	 * @param pager
	 * @param channel
	 * @return
	 * @author ShangJianguo
	 */
	List<Article> search(String keyword, Pager pager, EArticleChannel channel);
	
	/**
	 * 根据文章类别(String)进行查询
	 * @param typename
	 * @param pager
	 * @return
	 * @author LiuXiaoke
	 */
	List<Article> getList(String typename, Pager pager);
	
	/**
	 * 根据文章类别(String)获取数量
	 * @param typename
	 * @return
	 * @author LiuXiaoke
	 */
	int getCount(String typename);
	
}
