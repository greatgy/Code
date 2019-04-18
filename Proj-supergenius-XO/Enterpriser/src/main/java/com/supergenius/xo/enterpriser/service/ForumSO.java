package com.supergenius.xo.enterpriser.service;

import java.util.List;

import com.genius.model.base.entity.Pager;
import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.enterpriser.entity.Forum;

/**
 * 帖子SO
 * @author ChenQi
 * @date 2018年1月29日18:05:14
 */
public interface ForumSO extends BaseSO<Forum>{
	
	/**
	 * 得到指定数量的推荐帖子
	 * 
	 * @param isrecommend
	 * @author YangGuang
	 * @createtime 2018年1月2日17:05:05
	 * @return List<Forum>
	 */
	List<Forum> getRecommendList(Pager pager, boolean isrecommend, int cid);

	/**
	 * 根据cid获取帖子，当cid为0时，获取所有的
	 * 
	 * @param cid
	 * @author YangGuang
	 * @createtime 2018年1月2日17:35:38
	 * @return List<Forum>
	 */
	List<Forum> getListByCid(Integer cid);
	
	/**
	 * 根据作者，获取相关帖子
	 * 
	 * @param useruid
	 * @author ChenQi
	 * @createtime 2017年12月4日10:46:29
	 * @return List<Forum>
	 */
	List<Forum> getRelatecarticleList(Pager pager, String useruid);

	/**
	 * 得到第一页指定数量的帖子
	 * @author XueZhenYong
	 * @Datetime 2018年1月2日下午6:50:09
	 * @return List<Forum>
	 */
	List<Forum> getFirstForum(Pager pager, int cid);
	
	/**
	 * 获取我的收藏的帖子列表
	 * @param useruid
	 * @return
	 * @author ChenQi
	 * @date 2018年1月4日15:23:35
	 */
	List<Forum> getCollectList(String useruid, Pager pager);
	
	/**
	 * 设置是否推荐
	 * @param ids
	 * @param isrecommend
	 * @return
	 */
	boolean setRecommend(String[] ids, boolean isrecommend);
	/**
	 * 设置是否置顶
	 * @param ids
	 * @param istop
	 * @return
	 */
	boolean setTop(String[] ids, boolean istop);

}