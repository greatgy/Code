package com.supergenius.xo.life.service;

import java.util.List;

import org.joda.time.DateTime;

import com.genius.model.base.entity.Pager;
import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.life.entity.Article;
import com.supergenius.xo.life.enums.ETop;

/**
 * 文章so
 * 
 * @author ChenQi
 * @date 2018年5月9日15:45:06
 */
public interface ArticleSO extends BaseSO<Article> {

	/**
	 * 设置是否置顶 ，置底
	 * 
	 * @param oid
	 * @param istop
	 * @return
	 * @author ChenQi
	 */
	boolean update(String[] ids, ETop istop);

	/**
	 * 获取我的收藏的文章列表
	 * @param useruid
	 * @return
	 * @author ChenQi
	 * @date 2018年5月9日15:45:12
	 */
	List<Article> getCollectList(String useruid, Pager pager);
	
	/**
	 * 得到指定数量的推荐文章
	 * 
	 * @param isrecommend
	 * @author ChenQi
	 * @createtime 2018年5月9日15:45:16
	 * @return List<Article>
	 */
	List<Article> getRecommendList(Pager pager, boolean isrecommend, long cid, boolean isbanner);
	
	/**
	 * 根据cid获取文章
	 * 
	 * @param cids
	 * @author 许志翔
	 * @createtime 2018年5月9日15:45:23
	 * @return List<Article>
	 */
	List<Article> getListByCid(Pager pager, long cid);
	
	/**
	 * 根据cid获取文章，当cid为0时，获取所有的
	 * 
	 * @param cids
	 * @author 许志翔
	 * @createtime 2018年5月9日15:45:26
	 * @return List<Article>
	 */
	List<Article> getListByCid(long cid);
	
	/**
	 * 根据作者，获取相关文章
	 * 
	 * @param useruid
	 * @author ChenQi
	 * @createtime 2018年5月9日15:45:29
	 * @return List<Article>
	 */
	List<Article> getRelatecarticleList(Pager pager, long cid, String useruid);

	/**
	 * 获取文章创建时间大于当前时间的最小创建时间
	 * @param useruid
	 * @return
	 * @author ChenQi
	 * @date 2018年5月9日15:45:32
	 */
	DateTime getCacheTime();
}
