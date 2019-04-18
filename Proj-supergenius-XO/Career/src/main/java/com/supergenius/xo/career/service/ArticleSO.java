package com.supergenius.xo.career.service;

import java.util.List;

import org.joda.time.DateTime;

import com.genius.model.base.entity.Pager;
import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.career.entity.Article;
import com.supergenius.xo.career.enums.ETop;

/**
 * 文章so
 * 
 * @author 杨光
 * @date 2017年9月19日09:54:02
 */
public interface ArticleSO extends BaseSO<Article> {

	/**
	 * 设置是否置顶 ，置底
	 * 
	 * @param oid
	 * @param istop
	 * @return
	 * @author 杨光
	 */
	boolean update(String[] ids, ETop istop);

	/**
	 * 获取我的收藏的文章列表
	 * @param useruid
	 * @return
	 * @author 杨光
	 * @date 2017年9月19日09:56:44
	 */
	List<Article> getCollectList(String useruid, Pager pager);
	
	/**
	 * 得到指定数量的推荐文章
	 * 
	 * @param isrecommend
	 * @author 许志翔
	 * @createtime 2017年9月19日18:00:43
	 * @return List<Article>
	 */
	List<Article> getRecommendList(Pager pager, boolean isrecommend, int cid, boolean isbanner);
	
	/**
	 * 根据cid获取文章
	 * 
	 * @param cids
	 * @author 许志翔
	 * @createtime 2017年9月19日18:00:35
	 * @return List<Article>
	 */
	List<Article> getListByCid(Pager pager, int cid);
	
	/**
	 * 根据cid获取文章，当cid为0时，获取所有的
	 * 
	 * @param cids
	 * @author 许志翔
	 * @createtime 2017年9月19日18:00:35
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
	 * 获取文章创建时间大于当前时间的最小创建时间
	 * @param useruid
	 * @return
	 * @author YangGuang
	 * @date 2017年9月8日11:00:08
	 */
	DateTime getCacheTime();
}
