package com.supergenius.xo.enterpriser.service;

import java.util.List;

import org.joda.time.DateTime;

import com.genius.model.base.entity.Pager;
import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.enterpriser.entity.Article;

/**
 * 文章SO
 * @author ChenQi
 * @date 2018年1月29日18:05:14
 */
public interface ArticleSO extends BaseSO<Article>{

	/**
	 * 得到指定数量的置顶文章
	 * 
	 * @param isrecommend
	 * @author YangGuang
	 * @createtime 2018年1月2日17:05:05
	 * @return List<Article>
	 */
	List<Article> getBannerList(Pager pager, boolean isrecommend, int cid);
	
	/**
	 * 得到指定数量的推荐文章
	 * 
	 * @param isrecommend
	 * @author YangGuang
	 * @createtime 2018年1月2日17:05:05
	 * @return List<Article>
	 */
	List<Article> getRecommendList(Pager pager, boolean isrecommend, int cid);

	/**
	 * 根据cid获取文章，当cid为0时，获取所有的
	 * 
	 * @param cid
	 * @author YangGuang
	 * @createtime 2018年1月2日17:35:38
	 * @return List<Article>
	 */
	List<Article> getListByCid(Integer cid);
	
	/**
	 * 根据作者，获取相关文章
	 * 
	 * @param useruid
	 * @author ChenQi
	 * @createtime 2017年12月4日10:46:29
	 * @return List<Article>
	 */
	List<Article> getRelatecarticleList(Pager pager, String useruid);

	/**
	 * 得到第一页指定数量的文章
	 * @author XueZhenYong
	 * @Datetime 2018年1月2日下午6:50:09
	 * @return List<Article>
	 */
	List<Article> getFirstArticle(Pager pager, int cid);
	
	/**
	 * 获取我的收藏的文章列表
	 * @param useruid
	 * @return
	 * @author ChenQi
	 * @date 2018年1月4日15:23:35
	 */
	List<Article> getCollectList(String useruid, Pager pager);
	
	/**
	 * 获取文章创建时间大于当前时间的最小创建时间
	 * @param useruid
	 * @return
	 * @author YangGuang
	 * @date 2017年9月8日11:00:08
	 */
	DateTime getCacheTime();
}