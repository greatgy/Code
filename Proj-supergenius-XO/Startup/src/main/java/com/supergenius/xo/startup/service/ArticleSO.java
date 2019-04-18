package com.supergenius.xo.startup.service;

import java.util.List;

import org.joda.time.DateTime;

import com.genius.model.base.entity.Pager;
import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.startup.entity.Article;

/**
 * 文章so
 * 
 * @author 许志翔
 * @date 2017年8月23日14:40:58
 */
public interface ArticleSO extends BaseSO<Article> {

	/**
	 * 设置是否置顶
	 * 
	 * @param oid
	 * @param istop
	 * @return
	 * @author 许志翔
	 */
	boolean update(String[] ids, boolean istop);

	/**
	 * 得到指定数量的推荐文章
	 * 
	 * @param isrecommend
	 * @author ChenQi
	 * @createtime 2017年8月25日18:49:09
	 * @return List<Article>
	 */
	List<Article> getRecommendList(Pager pager, boolean isrecommend);

	/**
	 * 根据cids获取文章
	 * 
	 * @param cids
	 * @author ChenQi
	 * @createtime 2017年8月28日12:18:39
	 * @return List<Article>
	 */
	List<Article> getListByCids(Pager pager, List<Integer> cids);
	
	/**
	 * 根据cid获取文章
	 * 
	 * @param cids
	 * @author ChenQi
	 * @createtime 2017年8月31日19:22:35
	 * @return List<Article>
	 */
	List<Article> getListByCid(Pager pager, Integer cid);
	
	/**
	 * 获取我的收藏的文章列表
	 * @param useruid
	 * @return
	 * @author 许志翔
	 * @date 2017年9月8日11:00:08
	 */
	List<Article> getListByUseruid(String useruid, Pager pager);
	
	/**
	 * 获取文章创建时间大于当前时间的最小创建时间
	 * @param useruid
	 * @return
	 * @author YangGuang
	 * @date 2017年9月8日11:00:08
	 */
	DateTime getCacheTime();
	
}