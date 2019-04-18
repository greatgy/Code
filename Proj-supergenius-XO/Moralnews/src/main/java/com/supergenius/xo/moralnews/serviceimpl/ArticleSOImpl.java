package com.supergenius.xo.moralnews.serviceimpl;

import com.genius.core.base.constant.BaseMapperDict;
import com.genius.model.base.enums.EStatus;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.core.base.utils.DateUtil;
import com.genius.core.base.utils.StrUtil;
import com.genius.model.base.entity.Pager;
import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.moralnews.dao.ArticleDao;
import com.supergenius.xo.moralnews.entity.Article;
import com.supergenius.xo.moralnews.service.ArticleSO;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文章SO实现
 * 
 * @author JiaShitao
 * @date 2018年9月19日10:00:31
 */
@Service("moralnewsArticleSOImpl")
public class ArticleSOImpl extends BaseSOImpl<Article> implements ArticleSO {

	@Autowired
	private ArticleDao dao;

	@Override
	protected BaseDao<Article> getDao() {
		return dao;
	}

	@Override
	public boolean updateToDeleted(Map<String, Object> map) {
		return dao.updateToDeleted(map);
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
	public List<String> getTopTenForm() {
		return dao.getFormList();
	}

	@Override
	public boolean setTop(String id, boolean istop) {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.status, EStatus.enable);
		map.put(MapperDict.istop, istop);
		map.put(MapperDict.kind, 3);
		map.put(BaseMapperDict.uid, id);
		return dao.updateFields(map);
	}

	@Override
	public boolean checkIsTop(String id) {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.istop, true);
		map.put(BaseMapperDict.uid, id);
		map.put(MapperDict.kind, 3);
		if (0 == dao.getList(map).size()) {
			return true;
		} else {
			return false;
		}
	}
}
