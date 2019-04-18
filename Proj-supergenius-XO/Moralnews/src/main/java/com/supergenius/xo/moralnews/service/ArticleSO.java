package com.supergenius.xo.moralnews.service;

import com.genius.model.base.entity.Pager;
import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.moralnews.entity.Article;

import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;

/**
 * 文章so
 * 
 * @author JiaShitao
 * @date 2018年9月19日09:47:55
 */
public interface ArticleSO extends BaseSO<Article> {
	/**
	 * 批量更新到删除状态
	 *
	 * @author JiaShitao
	 * @data 2018年7月3日18:21:08
	 */
	boolean updateToDeleted(Map<String, Object> map);

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
	 * 获取我的收藏的文章列表
	 * 
	 * @param useruid
	 * @return
	 * @author 杨光
	 * @date 2017年9月19日09:56:44
	 */
	List<Article> getCollectList(String useruid, Pager pager);

	/**
	 *	获取发布帖子最多的前十名
	 * @return
	 */
	List<String> getTopTenForm();
	
	/**
	 * 设置贴子置顶
	 * 
	 * @param id
	 * @param istop
	 * @return
	 * @author tf
	 */
	boolean setTop(String id, boolean istop);
	
	/**
	 * 检查是否有置顶的贴子,返回true便可以置顶，返回false不可以置顶
	 * 
	 * @return
	 * @author tf
	 */
	boolean checkIsTop(String id);
}
