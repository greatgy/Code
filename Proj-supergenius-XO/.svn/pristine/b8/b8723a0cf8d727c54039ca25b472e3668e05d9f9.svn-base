package com.supergenius.xo.moral.service;

import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.moral.entity.CountDetail;
import com.supergenius.xo.moral.enums.ECountDetail;
import com.supergenius.xo.user.entity.User;

/**
 * 点赞、收藏、点击SO
 * 
 * @author LiJiacheng
 */
public interface CountDetailSO extends BaseSO<CountDetail> {

	/**
	 * 更新点赞和收藏的状态
	 * 
	 * @param userUid
	 * @param articleUid
	 * @return
	 * @author LiJiacheng
	 */
	boolean updateCount(String userUid, String articleUid, int count, ECountDetail detail);

	/**
	 * 帖子点赞
	 * 
	 * @return
	 * @author LiJiacheng
	 */
	boolean updateArticelPraise(String articleUid, int count, User user);

	/**
	 * 帖子收藏
	 * 
	 * @param articleUid
	 * @param count
	 * @param user
	 * @return
	 * @author LiJiacheng
	 */
	boolean updateArticelCollect(String articleUid, int count, User user);

	/**
	 * 获取我收藏的帖子的总数
	 * 
	 * @param user
	 * @return
	 * @author LiJiacheng
	 */
	int getMyCollectedCount(User user);

	/**
	 * 根据文章的uid，删除点击细节
	 * 
	 * @param ids
	 * @return
	 * @author LiJiacheng
	 */
	boolean deleteArticleCountDetail(String ids);

	/**
	 * 帖子置顶
	 * 
	 * @param articleUid
	 * @return
	 * @author LiJiacheng
	 */
	boolean articleTop(String articleUid);

	/**
	 * 查看帖子是否置顶过
	 * 
	 * @param articleUid
	 * @return
	 * @author LiJiacheng
	 */
	boolean articleIsTop(String articleUid);

}
