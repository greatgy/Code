package com.supergenius.moral.moral.dao;

import java.util.List;

import com.genius.xo.mongodb.dao.BaseMongoDao;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.moral.entity.Comment;
import com.supergenius.xo.moral.enums.EComment;

/**
 * 评论dao
 * 
 * @author liushaomin
 */
public interface CommentDao extends BaseMongoDao<Comment> {

	/**
	 * 去重复获取讨论人数
	 * 
	 * @param uid
	 * @param comment
	 * @return
	 * @author LiJiacheng
	 */
	List<?> getCommentDistinct(String uid, EComment comment, EChannel channel);

}
