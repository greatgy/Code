package com.supergenius.xo.ai.service;

import java.util.List;
import java.util.Map;

import com.genius.model.base.entity.Pager;
import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.ai.entity.Article;

/**
 * 文章so
 * 
 * @author 杨光
 * @date 2017年9月19日09:54:02
 */
public interface ArticleSO extends BaseSO<Article> {

	/**
	 * 设置是否置顶
	 * 
	 * @param oid
	 * @param istop
	 * @return
	 * @author 杨光
	 */
	boolean update(String[] ids, boolean istop);

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
	List<Article> getRecommendList(Pager pager, boolean isrecommend, int cid);
	
	/**
	 * 根据cid获取文章
	 * 
	 * @param cids
	 * @author 许志翔
	 * @createtime 2017年9月19日18:00:35
	 * @return List<Article>
	 */
	List<Article> getListByCid(Pager pager, Integer cid);
	
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
	 * 批量更新到删除状态
	 * 
	 * @author JiaShitao
	 * @data 2018年7月25日18:21:08
	 */
	boolean updateToDeleted(Map<String, Object> map);

}