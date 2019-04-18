package com.supergenius.xo.moral.daoimpl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.genius.xo.mongodb.daoimpl.BaseMongoDaoImpl;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.supergenius.moral.moral.dao.CommentDao;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.moral.entity.Comment;
import com.supergenius.xo.moral.enums.EComment;

/**
 * 评论dao实现
 * 
 * @author liushaomin
 */
@Component("moralCommentDaoImpl")
public class CommentDaoImpl extends BaseMongoDaoImpl<Comment> implements CommentDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.moral.moral.dao.CommentDao#Distinct(java.lang.String, com.supergenius.xo.moral.enums.EComment)
	 * 
	 * @author: LiJiacheng 2015-8-25 下午2:40:25
	 */
	@Override
	public List<?> getCommentDistinct(String uid, EComment comment, EChannel channel) {
		DBObject dbObject = new BasicDBObject();
		dbObject.put(MapperDict.fromuid, uid);
		if (comment != null) {
			dbObject.put(MapperDict.type, comment.toString());
		}
		if (channel != null) {
			dbObject.put(MapperDict.channel, channel.toString());
		}
		return getColl().distinct(MapperDict.fromuseruid, dbObject);
	}

}
