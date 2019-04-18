package com.supergenius.xo.managernews.service;

import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;

import com.genius.model.base.entity.Pager;
import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.managernews.entity.Article;

/**
 * 文章so
 * 
 * @author JiaShitao
 * @date 2018年7月3日09:54:02
 */
public interface ArticleSO extends BaseSO<Article> {

	/**
	 * @author 雍雪振
	 * @time 2018年7月9日下午4:28:13
	 * @description:得到指定数量的置顶文章
	 */
	List<Article> getBannerList(Pager pager, boolean b, int cid);

	/**
	 * 获取文章创建时间大于当前时间的最小创建时间
	 * @param useruid
	 * @return
	 * @author YangGuang
	 * @date 2017年9月8日11:00:08
	 */
	DateTime getCacheTime();
	
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
	 * @param useruid
	 * @return
	 * @author 杨光
	 * @date 2017年9月19日09:56:44
	 */
	List<Article> getCollectList(String useruid, Pager pager);
	
	/**
	 * 批量更新到删除状态
	 * 
	 * @author JiaShitao
	 * @data 2018年7月3日18:21:08
	 */
	boolean updateToDeleted(Map<String, Object> map);
}
