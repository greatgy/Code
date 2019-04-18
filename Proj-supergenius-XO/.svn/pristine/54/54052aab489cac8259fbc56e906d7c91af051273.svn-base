package com.supergenius.xo.ai.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.model.base.entity.Pager;
import com.genius.model.base.enums.EStatus;
import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.ai.dao.ArticleDao;
import com.supergenius.xo.ai.entity.Article;
import com.supergenius.xo.ai.service.ArticleSO;
import com.supergenius.xo.common.constants.MapperDict;

/**
 * 文章SO实现
 * 
 * @author 杨光
 * @date 2017年9月19日10:00:31
 */
@Service("aiArticleSOImpl")
public class ArticleSOImpl extends BaseSOImpl<Article> implements ArticleSO {

	@Autowired
	private ArticleDao dao;

	@Override
	protected BaseDao<Article> getDao() {
		return dao;
	}

	@Override
	public boolean update(String[] uids, boolean istop) {
		Map<String, Object> map = new HashMap<String, Object>();
		for (String uid : uids) {
			map.put(MapperDict.uid, uid);
			map.put(MapperDict.istop, istop);
			dao.updateFields(map);
		}
		return true;
	}

	@Override
	public List<Article> getCollectList(String useruid, Pager pager) {
		Map<String, Object> map;
		if (pager != null) {
			map = getParamMap(pager);
		} else {
			map = getParamMap();
		}
		map.put(MapperDict.useruid, useruid);
		return dao.getCollectList(map);
	}
	
	@Override
	public List<Article> getRecommendList(Pager pager, boolean istop, int cid) {
		Map<String, Object> map = getParamMap(pager);
		map.put(MapperDict.istop, istop);
		map.put(MapperDict.type, EStatus.enable);
		if (cid == 0) { // 0表示首页
			return dao.getList(map);
		}
		map.put(MapperDict.cid, cid);
		return dao.getList(map);
	}
	
	@Override
	public List<Article> getListByCid(Pager pager, Integer cid) {
		Map<String, Object> map = getParamMap(pager);
		map.put(MapperDict.type, EStatus.enable);
		if (cid == 0) { // 0表示首页
			return dao.getList(map);
		}
		map.put(MapperDict.cid, cid);
		return dao.getList(map);
	}
	
	@Override
	public List<Article> getListByCid(Integer cid) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.type, EStatus.enable);
		if (cid == 0) { // 0表示首页
			return dao.getList(map);
		}
		map.put(MapperDict.cid, cid);
		return dao.getList(map);
	}
	
	@Override
	public boolean updateToDeleted(Map<String, Object> map){
		return dao.updateToDeleted(map);
	}
}
