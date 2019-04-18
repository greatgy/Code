package com.supergenius.xo.moral.serviceimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.core.base.constant.BaseMapperDict;
import com.genius.core.base.utils.DateUtil;
import com.genius.core.base.utils.StrUtil;
import com.genius.core.base.utils.WebUtil;
import com.genius.model.base.entity.Pager;
import com.genius.model.base.enums.EStatus;
import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.genius.xo.mongodb.Mongo;
import com.genius.xo.mongodb.MongoDict;
import com.google.common.collect.Lists;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.supergenius.moral.moral.dao.ArticleDao;
import com.supergenius.moral.moral.dao.CountDetailDao;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.moral.entity.Article;
import com.supergenius.xo.moral.entity.CountDetail;
import com.supergenius.xo.moral.enums.ECountDetail;
import com.supergenius.xo.moral.service.ArticleSO;
import com.supergenius.xo.user.dao.UserDao;
import com.supergenius.xo.user.entity.User;

/**
 * 用户发帖so的实现
 * 
 * @author liushaomin
 * @modifier lijiacheng
 */
@Service("moralArticleSOImpl")
public class ArticleSOImpl extends BaseSOImpl<Article> implements ArticleSO {

	@Autowired
	ArticleDao dao;

	@Autowired
	UserDao userDao;

	@Autowired
	CountDetailDao countDetailDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.genius.xo.base.serviceimpl.BaseSOImpl#getDao()
	 */
	@Override
	protected BaseDao<Article> getDao() {
		return dao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.moral.service.ArticleSO#update(com.genius.model.base.enums.EStatus, java.lang.String[])
	 */
	@Override
	public boolean update(EStatus eStatus, String[] ids) {
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
	 * @see com.supergenius.xo.moral.service.ArticleSO#deleteByUids(java.lang.String[])
	 */
	@Override
	public boolean deleteByUids(String id) {
		dao.delete(id);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.genius.xo.base.serviceimpl.BaseSOImpl#getList(java.util.Map)
	 */
	@Override
	public List<Article> getList(Map<String, Object> map) {
		List<Article> articles = dao.getList(map);
		for (Article item : articles) {
			if (StrUtil.isEmpty(item.getUsername())) {
				User user = userDao.get(item.getUseruid());
				if (user != null) {
					item.setUsername(user.getShowname());
				}
			}
		}
		return articles;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.moral.service.ArticleSO#setTop(java.lang.String[])
	 * 
	 * @author: LiJiacheng 2015-7-17 下午5:22:25
	 */
	@Override
	public boolean setTop(String ids, boolean istop) {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.istop, istop);
		map.put(BaseMapperDict.uid, ids);
		return dao.updateFields(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.moral.service.ArticleSO#getMyArticles(java.lang.String, com.genius.model.base.entity.Pager)
	 * 
	 * @author: LiJiacheng 2015-7-20 下午6:23:16
	 */
	@Override
	public List<Article> getMyArticles(String uid, Pager pager) {
		DBObject dbObject = new BasicDBObject(MapperDict.useruid, uid);
		dbObject.put(MapperDict.status, EStatus.enable.toString());
		DBObject orderbyDbObject = new BasicDBObject(MapperDict.istop, -1);
		orderbyDbObject.put(MapperDict.createtime, -1);
		return dao.getList(dbObject, pager, orderbyDbObject, Mongo.defaultStrategy);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.moral.service.ArticleSO#getMyCollectArticle(com.supergenius.xo.user.entity.User)
	 * 
	 * @author: LiJiacheng 2015-8-10 下午12:31:52
	 */
	@Override
	public List<Article> getMyCollectArticle(User user, Pager pager) {
		Map<String, Object> collectArticleMap = getParamMap(pager);
		collectArticleMap.put(MapperDict.fromuseruid, user.getUid());
		collectArticleMap.put(MapperDict.type, ECountDetail.collect);
		collectArticleMap.put(MapperDict.count, 1);
		List<CountDetail> countDetails = countDetailDao.getList(collectArticleMap);
		List<String> articleUids = new ArrayList<>();
		List<Article> articles = new ArrayList<>();
		if (null != countDetails) {
			for (CountDetail countDetail : countDetails) {
				articleUids.add(countDetail.getArticleuid());
			}
		}
		for (String string : articleUids) {
			articles.add(dao.get(string));
		}
		return articles;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.genius.xo.base.serviceimpl.BaseSOImpl#getCount()
	 * 
	 * @author: LiJiacheng 2015-7-23 上午11:39:42
	 */
	@Override
	public int getCount() {
		return dao.getCount(getParamMap());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.moral.service.ArticleSO#getMyArticleCount(com.supergenius.xo.user.entity.User)
	 * 
	 * @author: LiJiacheng 2015-8-10 下午12:13:07
	 */
	@Override
	public int getMyArticleCount(User user) {
		Map<String, Object> articleMap = new HashMap<>();
		articleMap.put(MapperDict.useruid, user.getUid());
		return dao.getCount(articleMap);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.moral.service.ArticleSO#getHotArticles()
	 * 
	 * @author: LiJiacheng 2015-7-27 下午5:34:19
	 */
	@Override
	public List<Map<String, Object>> groupArticles(Pager pager) {
		String[] addStrings = { MongoDict.$ + MapperDict.countclick, MongoDict.$ + MapperDict.countcomment, MongoDict.$ + MapperDict.countcomment, MongoDict.$ + MapperDict.countpraise,
				MongoDict.$ + MapperDict.countpraise, MongoDict.$ + MapperDict.countpraise };
		DBObject addObject = new BasicDBObject(MapperDict.$add, addStrings);
		DBObject hotarticles = new BasicDBObject(MapperDict.hotarticles, addObject);
		hotarticles.put(MapperDict.title, 1);
		hotarticles.put(MapperDict.countcomment, 1);
		DBObject project = new BasicDBObject(MongoDict.$project, hotarticles);
		Map<String, Object> sortMap = new HashMap<>();
		sortMap.put(MapperDict.hotarticles, -1);
		DBObject sort = new BasicDBObject(MongoDict.$sort, sortMap);
		List<DBObject> pipeline = Lists.newArrayList(project, sort);
		return dao.aggregate(pipeline, null, pager);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.moral.service.ArticleSO#updateArticlePraise(int)
	 * 
	 * @author: LiJiacheng 2015-7-31 下午2:57:00
	 */
	@Override
	public boolean updateArticlePraise(String articleUid, int updown) {
		Map<String, Object> map = new HashMap<>();
		map.put(MongoDict._id, articleUid);
		map.put(MapperDict.countpraise, dao.get(articleUid).getCountpraise() + updown);
		map.put(MapperDict.updatetime, DateTime.now());
		return dao.updateFields(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.moral.service.ArticleSO#addArticle(com.supergenius.xo.moral.entity.Article, com.supergenius.xo.user.entity.User)
	 * 
	 * @author: LiJiacheng 2015-8-3 下午3:26:54
	 */
	@Override
	public boolean addArticle(Article article, User user) {
		Article newArticle = new Article(user.getUid(), user.getOid(), user.getName(), WebUtil.clearXSS(article.getTitle()), WebUtil.clearXSS(article.getContent()), null, null, null, null, null,
				false, 0, 0, 0);
		newArticle.setCreatetime(DateTime.now());
		return dao.insert(newArticle);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.moral.service.ArticleSO#getAllArticles(com.genius.model.base.entity.Pager)
	 * 
	 * @author: LiJiacheng 2015-8-21 下午2:30:07
	 */
	@Override
	public List<Article> getAllArticles(Pager pager) {
		DBObject orderbyDbObject = new BasicDBObject(MapperDict.istop, -1);
		orderbyDbObject.put(MapperDict.createtime, -1);
		DBObject dbObject = new BasicDBObject(MapperDict.status, EStatus.enable.toString());
		return dao.getList(dbObject, pager, orderbyDbObject, Mongo.defaultStrategy);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.moral.service.ArticleSO#editArticle(com.supergenius.xo.moral.entity.Article)
	 * 
	 * @author: LiJiacheng 2015-9-2 下午4:45:00
	 */
	@Override
	public boolean editArticle(Article article) {
		Map<String, Object> whereMap = new HashMap<>();
		Map<String, Object> fieldMap = new HashMap<>();
		whereMap.put(MongoDict._id, article.getUid());
		fieldMap.put(MapperDict.content, article.getContent());
		fieldMap.put(MapperDict.title, article.getTitle());
		fieldMap.put(MapperDict.updatetime, DateTime.now().toString());
		return dao.update(whereMap, fieldMap);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.moral.service.ArticleSO#getTodayArticle(java.lang.String)
	 * 
	 * @author: LiJiacheng 2015-9-6 下午4:24:07
	 */
	@Override
	public int getTodayArticle(String userUid) {
		DateTime dateTime = new DateTime(DateUtil.parseDate(new Date(), DateUtil.FORMAT_DATE_CHINA));
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.useruid, userUid);
		map.put(MapperDict.createtime + MapperDict.suffix_greaterOrEqual_key, dateTime);
		return dao.getCount(map);
	}

}
