package com.supergenius.xo.life.serviceimpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.life.dao.NewsDao;
import com.supergenius.xo.life.entity.News;
import com.supergenius.xo.life.enums.ELifeMsg;
import com.supergenius.xo.life.service.NewsSO;

/**
 * 消息so实现
 * 
 * @author YangGuang
 * @date 2018年5月9日16:00:23
 */
@Service("lifeNewsSOImpl")
public class NewsSOImpl extends BaseSOImpl<News> implements NewsSO{

	@Autowired
	private NewsDao dao;
	
	@Override
	protected BaseDao<News> getDao() {
		// TODO Auto-generated method stub
		return dao;
	}

	@Override
	public News add(Map<String, Object> map) {
		String type = map.get(MapperDict.type).toString();
		News news = new News();
		news.setTitle(map.get(MapperDict.title).toString());
		news.setTouid(map.get(MapperDict.touid).toString());
		news.setContent(map.get(MapperDict.content).toString());
		news.setType(ELifeMsg.get(Integer.parseInt(type)));
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

	@Override
	public int getCountByUnRead(String uid) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.touid, uid);
		map.put(MapperDict.isread, false);
		return dao.getCount(map);
	}
}
