package com.supergenius.xo.life.serviceimpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.core.base.utils.DateUtil;
import com.genius.core.base.utils.StrUtil;
import com.genius.model.base.entity.Pager;
import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.life.dao.ArticleDao;
import com.supergenius.xo.life.entity.Article;
import com.supergenius.xo.life.enums.ETop;
import com.supergenius.xo.life.service.ArticleSO;

/**
 * 文章SO实现
 * 
 * @author ChenQi
 * @date 2018年5月9日15:46:15
 */
@Service("lifeArticleSOImpl")
public class ArticleSOImpl extends BaseSOImpl<Article> implements ArticleSO {

	@Autowired
	private ArticleDao dao;

	@Override
	protected BaseDao<Article> getDao() {
		return dao;
	}

	@Override
	public boolean update(String[] uids, ETop istop) {
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
		Map<String, Object> map = getParamMap(pager);
		map.put(MapperDict.useruid, useruid);
		map.put(MapperDict.type, true);
		return dao.getCollectList(map);
	}
	
	@Override
	public List<Article> getRecommendList(Pager pager, boolean istop, long cid, boolean isbanner) {
		Map<String, Object> map = getParamMap(pager);
		map.put(MapperDict.istop, istop);
		map.put(MapperDict.type, true);
		map.put(MapperDict.cid, cid);
		if (isbanner == true) {
			map.put(MapperDict.imgoriginal + MapperDict.suffix_notEqual_key, true);
		}
		return dao.getList(map);
	}
	
	@Override
	public List<Article> getListByCid(Pager pager, long cid) {
		Map<String, Object> map = getParamMap(pager);
		map.put(MapperDict.type, true);
		map.put(MapperDict.cid, cid);
		return dao.getList(map);
	}
	
	@Override
	public List<Article> getListByCid(long cid) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.type, true);
		map.put(MapperDict.cid, cid);
		return dao.getList(map);
	}

	@Override
	public List<Article> getRelatecarticleList(Pager pager,long cid, String useruid) {
		Map<String, Object> map = getParamMap(pager);
		map.put(MapperDict.useruid, useruid);
		map.put(MapperDict.type, true);
		map.put(MapperDict.cid, cid);
		return dao.getList(map);
	}
	
	@Override
	public DateTime getCacheTime() {
		Map<String, Object> map = getParamMap();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		Date date = dao.getCacheTime(map);
		if (date != null) {
			String dateTime = df.format(date);
			if (StrUtil.isNotEmpty(dateTime)) {
				return DateUtil.parse(dateTime);
			}
		}
		return null;
	}
}
