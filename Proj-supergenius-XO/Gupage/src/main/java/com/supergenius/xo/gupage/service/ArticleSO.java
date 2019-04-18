package com.supergenius.xo.gupage.service;

import java.util.List;

import org.joda.time.DateTime;

import com.genius.model.base.entity.Pager;
import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.gupage.entity.Article;

/**
 * 文章so
 * 
 * @author 杨光
 * @date 2017年9月19日09:54:02
 */
public interface ArticleSO extends BaseSO<Article> {

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
	 * 获取文章创建时间大于当前时间的最小创建时间
	 * @param useruid
	 * @return
	 * @author YangGuang
	 * @date 2017年9月8日11:00:08
	 */
	DateTime getCacheTime();
}
