package com.supergenius.xo.entrepreneur.service;

import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;

import com.genius.model.base.entity.Pager;
import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.entrepreneur.entity.Article;

/**
 * 文章so
 * 
 * @author JiaShitao
 * @date 2018年7月3日09:54:02
 */
public interface ArticleSO extends BaseSO<Article> {

	/**
	 * 根据作者，获取相关文章
	 * 
	 * @param authoruid
	 * @author tf
	 * @createtime 2018年7月10日
	 * @return List<Article>
	 */
	List<Article> getRelatecarticleList(Pager pager, int cid, String authoruid);

	/**
	 * 获取我的收藏的文章列表
	 * 
	 * @param useruid
	 * @return
	 * @author 杨光
	 * @date 2017年9月19日09:56:44
	 */
	List<Article> getCollectList(String useruid, Pager pager);

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
	 * 
	 * @param useruid
	 * @return
	 * @author YangGuang
	 * @date 2017年9月8日11:00:08
	 */
	DateTime getCacheTime();

	/**
	 * 批量删除
	 * 
	 * @author JiaShitao
	 * @data 2018年7月19日18:21:08
	 */
	boolean updateToDeleted(Map<String, Object> map);
}
