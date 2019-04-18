package com.supergenius.xo.official.serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.core.base.constant.BaseMapperDict;
import com.genius.model.base.entity.Pager;
import com.genius.model.base.enums.EStatus;
import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.official.constants.MapperDict;
import com.supergenius.xo.official.dao.ArticleDao;
import com.supergenius.xo.official.entity.Article;
import com.supergenius.xo.official.enums.EArticleType;
import com.supergenius.xo.official.service.ArticleSO;

/**
 * 文章so的实现
 * @author liushaomin
 * @modifier YuYingJie
 */
@Service("officialArticleSOImpl")
public class ArticleSOImpl extends BaseSOImpl<Article> implements ArticleSO{
	
	@Autowired
	ArticleDao dao;

	@Override
	protected BaseDao<Article> getDao() {
		return dao;
	}

	/* (non-Javadoc)
	 * @see com.supergenius.xo.official.service.ArticleSO#setTop(java.lang.String[], boolean)
	 */
	@Override
	public boolean setTop(String[] ids, boolean istop) {
		Map<String, Object> map = new HashMap<String, Object>();
		for (String id : ids) {
			map.put(MapperDict.uid, id);
			map.put(MapperDict.istop, istop);
			dao.updateFields(map);
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see com.supergenius.xo.tpi.service.TeamSO#update(com.genius.model.base.enums.EStatus, java.lang.String[])
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

	/* (non-Javadoc)
	 * @see com.supergenius.xo.official.service.ArticleSO#deleteByUids(java.lang.String[])
	 */
	@Override
	public boolean deleteByUids(String[] ids) {
		for (String id : ids) {
			dao.delete(id);
		}
		return true;
	}
	
	/* (non-Javadoc)
	 * @see com.supergenius.xo.official.service.ArticleSO#getTop()
	 */
	@Override
	public Article getTop(boolean bool){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.istop, bool);	
		return dao.getOne(map);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.supergenius.xo.official.service.ArticleSO#getThree(java.lang.String)
	 */
	@Override
	public List<Article> getLastNext(String uid) {
		
		Article article = get(uid);
		if(article == null){
			return null;
		}
		Map<String, Object> map = new HashMap<>();
		map.put(BaseMapperDict.ascDesc, BaseMapperDict.desc);
		map.put(MapperDict.type, EArticleType.news);
		//获取该类型下创建时间早于当前文章的创建时间的文章列表，按时间倒序排列取第一个，即是当前文章的上一片文章
		map.put(MapperDict.createtime + MapperDict.suffix_less_key, article.getCreatetime());
		map.put(MapperDict.orderBy, MapperDict.createtime);
		List<Article> list = dao.getList(map);
		Article last = null;
		if(!list.isEmpty()){
			last = list.get(0);
		}
		Map<String, Object> map2 = new HashMap<>();
		//获取一个该类型下创建时间晚于当前文章的创建时间的文章，即是当前文章的下一篇文章
		map2.put(BaseMapperDict.ascDesc, BaseMapperDict.asc);
		map2.put(BaseMapperDict.startIndex, 0);
		map2.put(MapperDict.type, EArticleType.news);
		map2.put(MapperDict.createtime + MapperDict.suffix_greater_key, article.getCreatetime());
		List<Article> articless = dao.getList(map2);
		Article next = null;
		if(!articless.isEmpty()){
			next = articless.get(0);
		}
		
		List<Article> articles = new ArrayList<>();
		articles.add(0, next);
		articles.add(1, dao.get(uid));
		articles.add(2, last);
		return articles;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.supergenius.xo.official.service.ArticleSO#getList(com.supergenius.xo.official.enums.EArticleType, com.genius.model.base.entity.Pager)
	 */
	@Override
	public List<Article> getList(EArticleType e, Pager pager) {
		Map<String, Object> map = getParamMap(pager);
		map.put(MapperDict.type, e);
		return dao.getList(map);
	}
}
