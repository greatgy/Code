package com.supergenius.xo.entrepreneur.serviceimpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.core.base.utils.DateUtil;
import com.genius.core.base.utils.StrUtil;
import com.genius.model.base.entity.Pager;
import com.genius.model.base.enums.EStatus;
import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.entrepreneur.dao.ArticleDao;
import com.supergenius.xo.entrepreneur.entity.Article;
import com.supergenius.xo.entrepreneur.service.ArticleSO;

/**
 * 文章SO实现
 * 
 * @author JiaShitao
 * @date 2018年7月3日10:00:31
 */
@Service("entrepreneurArticleSOImpl")
public class ArticleSOImpl extends BaseSOImpl<Article> implements ArticleSO {

	@Autowired
	private ArticleDao dao;

	@Override
	protected BaseDao<Article> getDao() {
		return dao;
	}

	@Override
	public List<Article> getRelatecarticleList(Pager pager, int cid, String authoruid) {
		Map<String, Object> map = getParamMap(pager);
		map.put(MapperDict.authoruid, authoruid);
		map.put(MapperDict.cid, cid);
		return dao.getList(map);
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

	/**
	 * 批量删除
	 * 
	 * @author JiaShitao
	 * @data 2018年7月19日18:21:08
	 */
	public boolean updateToDeleted(Map<String, Object> map) {
		return dao.updateToDeleted(map);
	}
}
