package com.supergenius.xo.moral.service;

import java.util.List;

import com.genius.model.base.entity.Pager;
import com.genius.model.base.enums.EStatus;
import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.moral.entity.Comment;
import com.supergenius.xo.moral.enums.EComment;
import com.supergenius.xo.user.entity.User;

/**
 * 评论so
 * 
 * @author liushaomin
 * @modifier LiJiacheng
 */
public interface CommentSO extends BaseSO<Comment> {

	/**
	 * 更新状态
	 * 
	 * @param ids
	 * @param eStatus
	 * @return
	 * @author liushaomin
	 */
	boolean setStatus(String[] ids, EStatus eStatus);

	/**
	 * 获取所有评论(不包括回复)
	 * 
	 * @param uid
	 * @return
	 * @author LiJiacheng
	 */
	List<Comment> getComments(String uid, Pager pager);

	/**
	 * 获取评论总数(参与人数)
	 * 
	 * @param uid
	 * @return
	 * @author LiJiacheng
	 */
	int getCommentsCount(String uid, EComment comment, EChannel channel);

	/**
	 * 添加评论
	 * 
	 * @param comment
	 * @return
	 * @author LiJiacheng
	 */
	boolean newComment(Comment comment, User fromUser, User toUser);

	/**
	 * 给评论点赞
	 * 
	 * @param comment
	 * @return
	 * @author LiJiacheng
	 */
	boolean commentPrize(Comment comment, User fromUser, User toUser, int count);

	/**
	 * 更新点赞状态
	 * 
	 * @return
	 * @author LiJiacheng
	 */
	boolean updateCommentPrize(String userUid, String articleUid, String commentUid, String content);

	/**
	 * 根据文章的uid,删除所有文章的评论
	 * 
	 * @param ids
	 * @return
	 * @author LiJiacheng
	 */
	boolean deleteArticleComment(String ids);

	/**
	 * 获取所有评论
	 * 
	 * @param uid
	 * @param moralvideo
	 * @param pager
	 * @return
	 * @author YuYingJie
	 */
	List<Comment> getComments(String fromuid, EChannel channel, Pager pager);

	/**
	 * 获取评论数量
	 * 
	 * @param fromuid
	 * @param moralvideo
	 * @return
	 * @author YuYingJie
	 */
	int getCommentsCount(String fromuid, EChannel channel);

	/**
	 * 从评论表中获取道德杂谈的发言
	 * 
	 * @return
	 * @author LiJiacheng
	 */
	List<Comment> getMoralTalk(int num);

	/**
	 * 道德杂谈发言、回复
	 * 
	 * @param comment
	 * @return
	 * @author LiJiacheng
	 */
	boolean addMoralTalk(Comment comment, User fromUser, User toUser);

	/**
	 * 得到刚刚的发言或回复
	 * 
	 * @return
	 * @author LiJiacheng
	 */
	Comment getLastComment();

	/**
	 * 获取评论(不包含回复)的数量
	 * 
	 * @param fromuid
	 * @param channel
	 * @return
	 * @author LiJiacheng
	 */
	int getCount(String fromuid, EChannel channel);

	/**
	 * 评论删除
	 * 
	 * @param ids
	 * @param channel
	 * @return
	 * @author YuYingJie
	 */
	boolean deleteByFields(String[] ids, EChannel channel);

	/**
	 * 评论添加
	 * 
	 * @param comment
	 * @param u
	 * @author YuYingJie
	 */
	boolean add(Comment comment, User u);

	/**
	 * 获取今天的评论
	 * 
	 * @param userUid
	 * @return
	 * @author LiJiacheng
	 */
	int getTodayCommentCount(String userUid);

}
