package com.supergenius.xo.user.serviceimpl;

import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.user.dao.NewsDao;
import com.supergenius.xo.user.entity.News;
import com.supergenius.xo.user.enums.EUserMsg;
import com.supergenius.xo.user.service.NewsSO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 消息so实现
 * 
 * @author YangGuang
 * @date 2018年5月9日16:00:23
 */
@Service("userNewsSOImpl")
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
		News news = new News();
		news.setType((EUserMsg) map.get(MapperDict.type));
		news.setTitle(map.get(MapperDict.title).toString());
		news.setTouid(map.get(MapperDict.touid).toString());
		news.setContent(map.get(MapperDict.content).toString());
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
