package com.supergenius.xo.moral.serviceimpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.core.base.utils.DateUtil;
import com.genius.model.base.entity.Pager;
import com.genius.model.base.enums.EStatus;
import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.genius.xo.mongodb.MongoDict;
import com.supergenius.moral.moral.dao.ArticleDao;
import com.supergenius.moral.moral.dao.CommentDao;
import com.supergenius.moral.moral.dao.VideoDao;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.moral.entity.Article;
import com.supergenius.xo.moral.entity.Comment;
import com.supergenius.xo.moral.entity.Video;
import com.supergenius.xo.moral.enums.EComment;
import com.supergenius.xo.moral.service.CommentSO;
import com.supergenius.xo.user.entity.User;

/**
 * 评论so实现
 * 
 * @author liushaomin
 */
@Service("moralCommentSOImpl")
public class CommentSOImpl extends BaseSOImpl<Comment> implements CommentSO {

	@Autowired
	CommentDao dao;

	@Autowired
	ArticleDao articleDao;
	
	@Autowired
	VideoDao videoDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.genius.xo.base.serviceimpl.BaseSOImpl#getDao()
	 */
	@Override
	protected BaseDao<Comment> getDao() {
		return dao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.genius.xo.base.serviceimpl.BaseSOImpl#getList(java.util.Map)
	 */
	@Override
	public List<Comment> getList(Map<String, Object> map) {
		List<Comment> comments = dao.getList(map);
		for (Comment item : comments) {
			if (item.getChannel().equals(EChannel.moralvideo)) {
				Video video = videoDao.get(item.getFromuid());
				if (video != null) {
					item.setFromtitle(video.getName());
				}
			} else {
				Article article = articleDao.get(item.getFromuid());
				if (article != null) {
					item.setFromtitle(article.getTitle());
				}
			}
		}
		return comments;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.moral.service.CommentSO#setStatus(java.lang.String[], com.genius.model.base.enums.EStatus)
	 */
	@Override
	public boolean setStatus(String[] ids, EStatus eStatus) {
		Map<String, Object> map = new HashMap<String, Object>();
		for (String id : ids) {
			map.put(MapperDict.status, eStatus);
			map.put(MapperDict.uid, id);
			dao.updateFields(map);
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.moral.service.CommentSO#getComments(java.lang.String)
	 * 
	 * @author: LiJiacheng 2015-8-3 下午2:33:28
	 */
	@Override
	public List<Comment> getComments(String uid, Pager pager) {
		Map<String, Object> map = getParamMap(pager);
		map.put(MapperDict.fromuid, uid);
		map.put(MapperDict.type, EComment.comment);
		map.put(MapperDict.channel, EChannel.moralarticle);
		map.put(MapperDict.touid, null);
		List<Comment> list = dao.getList(map);
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.moral.service.CommentSO#getCommentsCount(java.lang.String)
	 * 
	 * @author: LiJiacheng 2015-8-3 下午2:36:06
	 */
	@Override
	public int getCommentsCount(String uid, EComment comment, EChannel channel) {
		return dao.getCommentDistinct(uid, comment, channel).size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.moral.service.CommentSO#newComment(com.supergenius.xo.moral.entity.Comment)
	 * 
	 * @author: LiJiacheng 2015-8-4 上午10:22:21
	 */
	@Override
	public boolean newComment(Comment comment, User fromUser, User toUser) {
		if (fromUser != null) {
			comment.setFromuseruid(fromUser.getUid());
			comment.setFromuseroid(fromUser.getOid());
			//comment.setFromusername(fromUser.getShowname());
		} else {
			comment.setFromuseruid(MapperDict.DefaultAnonymousUid);
			comment.setFromuseroid(MapperDict.DefaultAnonymousOid);
		}
		if (toUser != null) {
			comment.setTousername(toUser.getShowname());
			comment.setTouseroid(toUser.getOid());
		} else {
			comment.setTouseruid(MapperDict.DefaultAnonymousUid);
			comment.setTouseroid(MapperDict.DefaultAnonymousOid);
		}
		comment.setChannel(EChannel.moralarticle);
		comment.setType(EComment.comment);
		comment.setPrizecount(0);
		comment.setCreatetime(DateTime.now());
		comment.setStatus(EStatus.enable);
		if (dao.insert(comment)) {
			Map<String, Object> map = new HashMap<>();
			map.put(MapperDict.uid, comment.getFromuid());
			map.put(MapperDict.countcomment, 1);
			return articleDao.increase(map);
		} else {
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.moral.service.CommentSO#CommentPrize(com.supergenius.xo.moral.entity.Comment)
	 * 
	 * @author: LiJiacheng 2015-8-4 上午10:22:21
	 */
	@Override
	public boolean commentPrize(Comment comment, User fromUser, User toUser, int count) {
		Map<String, Object> map = null;
		if (-1 == count) {
			comment.setFromuseruid(fromUser.getUid());
			comment.setFromuseroid(fromUser.getOid());
			comment.setFromusername(fromUser.getName());
			if (toUser != null) {
				comment.setTouseroid(toUser.getOid());
				comment.setTousername(toUser.getName());
			}
			comment.setChannel(EChannel.moralarticle);
			comment.setType(EComment.prize);
			comment.setContent("1");
			comment.setCreatetime(DateTime.now());
			comment.setUpdatetime(DateTime.now());
			comment.setStatus(EStatus.enable);
			map = new HashMap<>();
			map.put(MongoDict._id, comment.getTouid());
			map.put(MapperDict.prizecount, 1);
			return dao.insert(comment) && dao.increase(map);
		} else if (0 == count) {
			Map<String, Object> updateMap = new HashMap<>();
			updateMap.put(MongoDict._id, comment.getTouid());
			updateMap.put(MapperDict.prizecount, 1);
			return updateCommentPrize(fromUser.getUid(), comment.getFromuid(), comment.getTouid(), "1") && dao.increase(updateMap);
		} else if (1 == count) {
			Map<String, Object> updateMap = new HashMap<>();
			updateMap.put(MongoDict._id, comment.getTouid());
			updateMap.put(MapperDict.prizecount, -1);
			return updateCommentPrize(fromUser.getUid(), comment.getFromuid(), comment.getTouid(), "0") && dao.increase(updateMap);
		} else {
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.moral.service.CommentSO#updateCommentPrize(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 * 
	 * @author: LiJiacheng 2015-8-11 下午2:00:16
	 */
	@Override
	public boolean updateCommentPrize(String userUid, String articleUid, String commentUid, String content) {
		Map<String, Object> where = new HashMap<>();
		Map<String, Object> fields = new HashMap<>();
		where.put(MapperDict.fromuid, articleUid);
		where.put(MapperDict.touid, commentUid);
		where.put(MapperDict.fromuseruid, userUid);
		where.put(MapperDict.type, EComment.prize);
		fields.put(MapperDict.content, content);
		return dao.update(where, fields);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.moral.service.CommentSO#deleteArticleComment(java.lang.String[])
	 * 
	 * @author: LiJiacheng 2015-8-18 下午3:58:17
	 */
	@Override
	public boolean deleteArticleComment(String id) {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.fromuid, id);
		return dao.deleteByMap(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.moral.service.CommentSO#getComments(java.lang.String, com.supergenius.xo.common.enums.EChannel, com.genius.model.base.entity.Pager)
	 */
	@Override
	public List<Comment> getComments(String fromuid, EChannel channel, Pager pager) {
		Map<String, Object> map;
		if (pager == null) {
			map = getParamMap();
		} else {
			map = getParamMap(pager);
		}
		map.put(MapperDict.fromuid, fromuid);
		map.put(MapperDict.channel, channel);
		List<Comment> list = dao.getList(map);
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.moral.service.CommentSO#getCommentsCount(java.lang.String, com.supergenius.xo.common.enums.EChannel)
	 */
	@Override
	public int getCommentsCount(String fromuid, EChannel channel) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.fromuid, fromuid);
		map.put(MapperDict.channel, channel);
		return dao.getCount(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.moral.service.CommentSO#getMoralTalk(int)
	 * 
	 * @author: LiJiacheng 2015-8-26 上午11:42:50
	 */
	@Override
	public List<Comment> getMoralTalk(int num) {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.channel, EChannel.moraltalk);
		map.put(MapperDict.pageSize, num);
		map.put(MapperDict.status, EStatus.enable);
		return dao.getList(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.moral.service.CommentSO#addMoralTalk(com.supergenius.xo.moral.entity.Comment)
	 * 
	 * @author: LiJiacheng 2015-8-26 下午2:11:06
	 */
	@Override
	public boolean addMoralTalk(Comment comment, User fromUser, User toUser) {
		comment.setFromuseruid(fromUser.getUid());
		comment.setFromuseroid(fromUser.getOid());
		comment.setFromusername(fromUser.getShowname());
		comment.setChannel(EChannel.moraltalk);
		comment.setType(EComment.comment);
		comment.setCreatetime(DateTime.now());
		if (toUser != null) {
			comment.setTouseruid(toUser.getUid());
			comment.setTouseroid(toUser.getOid());
			comment.setTousername(toUser.getShowname());
		}
		return dao.insert(comment);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.moral.service.CommentSO#getLastComment()
	 * 
	 * @author: LiJiacheng 2015-8-26 下午2:32:59
	 */
	@Override
	public Comment getLastComment() {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.status, EStatus.enable);
		map.put(MapperDict.channel, EChannel.moraltalk);
		return dao.getList(map).get(0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.moral.service.CommentSO#getCount(java.lang.String, com.supergenius.xo.common.enums.EChannel)
	 * 
	 * @author: LiJiacheng 2015-8-31 下午4:17:58
	 */
	@Override
	public int getCount(String fromuid, EChannel channel) {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.fromuid, fromuid);
		map.put(MapperDict.channel, channel);
		map.put(MapperDict.touid, null);
		return dao.getCount(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.moral.service.CommentSO#deleteByFields(java.lang.String[], com.supergenius.xo.common.enums.EChannel)
	 */
	@Override
	public boolean deleteByFields(String[] ids, EChannel channel) {
		for (String id : ids) {
			Map<String, Object> map = new HashMap<>();
			map.put(MapperDict.fromuid, id);
			map.put(MapperDict.channel, channel);
			dao.deleteByMap(map);
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.moral.service.CommentSO#add(com.supergenius.xo.moral.entity.Comment, com.supergenius.xo.user.entity.User)
	 */
	@Override
	public boolean add(Comment comment, User u) {
		comment.setFromuseruid(u.getUid());
		comment.setFromuseroid(u.getOid());
		comment.setFromusername(u.getShowname());
		comment.setChannel(EChannel.moralvideo);
		comment.setType(EComment.comment);
		return dao.insert(comment);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.moral.service.CommentSO#getTodayComment(java.lang.String)
	 * 
	 * @author: LiJiacheng 2015-9-6 下午6:17:34
	 */
	@Override
	public int getTodayCommentCount(String userUid) {
		DateTime dateTime = new DateTime(DateUtil.parseDate(new Date(), DateUtil.FORMAT_DATE_CHINA));
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.fromuseruid, userUid);
		map.put(MapperDict.channel, EChannel.moralarticle);
		map.put(MapperDict.type, EComment.comment);
		map.put(MapperDict.createtime + MapperDict.suffix_greaterOrEqual_key, dateTime);
		return dao.getCount(map);
	}

}
