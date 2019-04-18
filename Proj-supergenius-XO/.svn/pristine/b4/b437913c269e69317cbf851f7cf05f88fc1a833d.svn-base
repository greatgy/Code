package com.supergenius.xo.gupage.serviceimpl;

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
import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.gupage.dao.ArticleDao;
import com.supergenius.xo.gupage.entity.Article;
import com.supergenius.xo.gupage.service.ArticleSO;

/**
 * 文章SO实现
 * 
 * @author yangguang
 * @date 2017年11月13日16:14:02
 */
@Service("gupageArticleSOImpl")
public class ArticleSOImpl extends BaseSOImpl<Article> implements ArticleSO {

	@Autowired
	private ArticleDao dao;

	@Override
	protected BaseDao<Article> getDao() {
		return dao;
	}
	
	@Override
	public List<Article> getListByCid(Pager pager, int cid) {
		Map<String, Object> map = getParamMap(pager);
		map.put(MapperDict.type, true);
		map.put(MapperDict.cid, cid);
		return dao.getList(map);
	}
	
	@Override
	public List<Article> getListByCid(Integer cid) {
		Map<String, Object> map = getParamMap();
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
