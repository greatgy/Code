package com.supergenius.xo.entrepreneur.serviceimpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.entrepreneur.dao.NewsDao;
import com.supergenius.xo.entrepreneur.entity.News;
import com.supergenius.xo.entrepreneur.enums.EEntrepreneurMsg;
import com.supergenius.xo.entrepreneur.service.NewsSO;

/**
 * 消息SO实现
 * 
 * @author tf
 * @date 2018年7月5日
 */
@Service("entrepreneurNewsSOImpl")
public class NewsSOImpl extends BaseSOImpl<News> implements NewsSO {

	@Autowired
	private NewsDao dao;

	@Override
	protected BaseDao<News> getDao() {
		return dao;
	}

	@Override
	public News add(Map<String, Object> map) {
		String type = map.get(MapperDict.type).toString();
		News news = new News();
		news.setTitle(map.get(MapperDict.title).toString());
		news.setTouid(map.get(MapperDict.touid).toString());
		news.setContent(map.get(MapperDict.content).toString());
		news.setType(EEntrepreneurMsg.get(Integer.parseInt(type)));
		news.setFromuid(map.get(MapperDict.fromuseruid).toString());
		news.setHref(map.get(MapperDict.href).toString());
		if (dao.insert(news)) {
			return news;
		}
		return null;
	}

	@Override
	public Boolean updateByUseruid(Map<String, Object> map) {
		return dao.updateByUseruid(map);
	}
}
