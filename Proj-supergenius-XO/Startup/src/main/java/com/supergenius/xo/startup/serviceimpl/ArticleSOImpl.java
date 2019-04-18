package com.supergenius.xo.startup.serviceimpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.supergenius.xo.startup.dao.ArticleDao;
import com.supergenius.xo.startup.entity.Article;
import com.supergenius.xo.startup.service.ArticleSO;

/**
 * 文章SO实现
 * 
 * @author 许志翔
 * @date 2017年8月23日14:43:173
 */
@Service("startupArticleSOImpl")
public class ArticleSOImpl extends BaseSOImpl<Article> implements ArticleSO {

	@Autowired
	private ArticleDao dao;

	@Override
	protected BaseDao<Article> getDao() {
		return dao;
	}

	@Override
	public boolean update(String[] ids, boolean istop) {
		Map<String, Object> map = new HashMap<String, Object>();
		for (String id : ids) {
			map.put(MapperDict.uid, id);
			map.put(MapperDict.istop, istop);
			dao.updateFields(map);
		}
		return true;
	}

	@Override
	public List<Article> getRecommendList(Pager pager, boolean istop) {
		Map<String, Object> map = getParamMap(pager);
		map.put(MapperDict.istop, istop);
		return dao.getList(map);
	}

	@Override
	public List<Article> getListByCids(Pager pager, List<Integer> cids) {
		if (cids == null || cids.size() == 0) {
			return new ArrayList<>();
		}
		Map<String, Object> map = getParamMap(pager);
		map.put(MapperDict.cids, cids);
		return dao.getList(map);
	}

	@Override
	public List<Article> getListByCid(Pager pager, Integer cid) {
		Map<String, Object> map = getParamMap(pager);
		map.put(MapperDict.cid, cid);
		return dao.getList(map);
	}
	
	@Override
	public List<Article> getListByUseruid(String useruid, Pager pager) {
		Map<String, Object> map;
		if (pager != null) {
			map = getParamMap(pager);
		} else {
			map = getParamMap();
		}
		map.put(MapperDict.useruid, useruid);
		return dao.getListByUseruid(map);
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
	
	@Override
	public boolean updateFields(Map<String, Object> map){
		return dao.updateFields(map);
	}
}
