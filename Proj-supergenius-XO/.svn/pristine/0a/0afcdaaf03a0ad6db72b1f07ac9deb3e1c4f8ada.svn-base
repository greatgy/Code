package com.supergenius.xo.tpi.serviceimpl;

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
import com.supergenius.xo.tpi.constants.MapperDict;
import com.supergenius.xo.tpi.dao.ArticleDao;
import com.supergenius.xo.tpi.dao.TpiCountDao;
import com.supergenius.xo.tpi.entity.Article;
import com.supergenius.xo.tpi.enums.EArticleChannel;
import com.supergenius.xo.tpi.service.ArticleSO;

/**
 * 文章SO实现
 * @author ShangJianguo
 */
@Service
public class ArticleSOImpl extends BaseSOImpl<Article> implements ArticleSO {

	@Autowired
	ArticleDao dao;
	
	@Autowired
	TpiCountDao tpiCountDAO;
	
	/* (non-Javadoc)
	 * @see com.genius.xo.base.serviceimpl.BaseSOImpl#getDao()
	 * @author: ShangJianguo
	 * 2015-1-6 下午5:38:15
	 */
	@Override
	protected BaseDao<Article> getDao() {
		
		return dao;
	}
	
	/* (non-Javadoc)
	 * @see com.genius.xo.base.serviceimpl.BaseSOImpl#update(java.lang.Object)
	 * @author: ShangJianguo
	 * 2015-1-7 下午12:09:48
	 */
	@Override
	public boolean update(Article t) {
		Article article = dao.get(t.getUid());
		article.set(t);
		return dao.update(article);
	}

	/* (non-Javadoc)
	 * @see com.supergenius.xo.tpi.service.ArticleSO#updateIstop(java.lang.String[], boolean)
	 * @author: ShangJianguo
	 * 2015-1-7 下午4:44:26
	 */
	@Override
	public boolean setTop(String[] uids, boolean istop) {
		if (!checkIsPublish(uids)) {
			return false;
		}
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.istop, istop);
		for (String item : uids) {
			map.put(BaseMapperDict.uid, item);
			dao.updateFields(map);
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see com.supergenius.xo.tpi.service.ArticleSO#setPublic(java.lang.String[], boolean)
	 * @author: ShangJianguo
	 * 2015-1-7 下午6:14:06
	 */
	@Override
	public boolean setPublic(String[] uids, boolean ispublic) {
		if (!checkIsPublish(uids)) {
			return false;
		}
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.ispublic, ispublic);
		for (String item : uids) {
			map.put(BaseMapperDict.uid, item);
			dao.updateFields(map);
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see com.supergenius.xo.tpi.service.ArticleSO#setStatus(java.lang.String[], int)
	 * @author: ShangJianguo
	 * 2015-1-7 下午6:18:45
	 */
	@Override
	public boolean setStatus(String[] uids, EStatus status) {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.status, status);
		for (String item : uids) {
			map.put(BaseMapperDict.uid, item);
			dao.updateFields(map);
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see com.supergenius.xo.tpi.service.ArticleSO#getList(com.supergenius.xo.tpi.enums.EArticleChannel)
	 */
	@Override
	public List<Article> getList(String typename, Pager pager) {
		return dao.getList(typename, pager);
	}
	
	/* (non-Javadoc)
	 * @see 
	 * @author: LiuXiaoke
	 */
	@Override
	public int getCount(String typename) {
		return dao.getCount(typename);
	}
	
	/* (non-Javadoc)
	 * @see com.supergenius.xo.tpi.service.ArticleSO#getListByRecommend(com.genius.model.base.entity.Pager)
	 */
	@Override
	public List<Article> getListByTop(EArticleChannel channel, Pager pager) {
		Map<String, Object> map = getParamMap(pager);
		map.put(MapperDict.channel, channel);
		map.put(MapperDict.istop, true);
		return dao.getList(map);
	}
	/* (non-Javadoc)
	 * @see com.supergenius.xo.tpi.service.TypeSO#getListByType(com.supergenius.xo.tpi.enums.EType)
	 */
	@Override
	public List<Article> getListByType(String typename, Pager pager) {
		return dao.getList(typename, pager);
	}
	
	/* (non-Javadoc)
	 * @see com.supergenius.xo.tpi.service.ArticleSO#getLastNext(java.lang.String)
	 * @author: LiuXiaoke
	 * 2014-11-10 上午10:43:15
	 */
	@Override
	public Map<String, Object> getLastNext(String uid) {
		Article article = get(uid);
		Map<String, Object> map = getParamMap();
		//获取该类型下创建时间早于当前文章的创建时间的文章列表，按时间倒序排列取第一个，即是当前文章的上一片文章
		map.put(MapperDict.createtime + MapperDict.suffix_less_key, article.getCreatetime());
		map.put(MapperDict.orderBy, MapperDict.createtime);
		List<Article> list = dao.getList(dealLastNextMap(map, article));
		for (Article article2 : list) {
			System.out.println(article2.getCreatetimeStr());
		}
		Article last = null;
		if(!list.isEmpty()){
			last = list.get(0);
		}
		map = getParamMap();
		//获取一个该类型下创建时间晚于当前文章的创建时间的文章，即是当前文章的下一篇文章
		map.put(MapperDict.createtime + MapperDict.suffix_greater_key, article.getCreatetime());
		Article next = dao.getOne(dealLastNextMap(map, article));
		Map<String, Object> result = new HashMap<>();
		result.put(MapperDict.last, last);
		result.put(MapperDict.next, next);
		return result;
	}
	
	/*
	 * 在操作之前校验数据是否都是已经发布
	 * @param ids
	 * @return 若都已经发布则返回true，否则返回false
	 * @author ShangJianguo
	 */
	private boolean checkIsPublish(String[] ids) {
		Map<String, Object> querymap = new HashMap<>();
		querymap.put(MapperDict.ids, ids);
		List<Article> list = dao.getList(querymap);
		for (Article item : list) {
			if (item.getStatus() != EStatus.enable) {
				return false;
			}
		}
		return true;
	}
	
	/*
	 * 为查询map添加条件
	 * @param map
	 * @param article
	 * @return
	 * @author LiuXiaoke
	 */
	private Map<String, Object> dealLastNextMap(Map<String, Object> map, Article article) {
		if (article.getChannel() == EArticleChannel.mergecase) {
			map.put(MapperDict.ctype, article.getCtype());
		} else if (article.getChannel() == EArticleChannel.mergenews) {
			map.put(MapperDict.ntype, article.getNtype());
		}
		return map;
	}

	/* (non-Javadoc)
	 * @see com.supergenius.xo.tpi.service.ArticleSO#getList(com.supergenius.xo.tpi.enums.EArticleChannel, com.genius.model.base.entity.Pager)
	 */
	@Override
	public List<Article> getList(EArticleChannel channel, Pager pager) {
		Map<String, Object> map = getParamMap(pager);
		map.put(MapperDict.channel, channel);
		return dao.getList(map);
	}
	
}
