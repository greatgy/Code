package com.supergenius.xo.ai.serviceimpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.ai.dao.NewsDao;
import com.supergenius.xo.ai.entity.News;
import com.supergenius.xo.ai.enums.EAiMsg;
import com.supergenius.xo.ai.service.NewsSO;
import com.supergenius.xo.common.constants.MapperDict;

/**
 * 消息Impl
 * 
 * @author ChenQi
 * @date 2017-9-19 10:43:44
 */
@Service("AiNewsSOImpl")
public class NewsSOImpl extends BaseSOImpl<News> implements NewsSO {

	
	@Autowired
	NewsDao dao;
	
	/*@Autowired
	UserDao userDao;*/
	
	@Override
	protected BaseDao<News> getDao() {
		return dao;
	}

	@Override
	public Boolean updateByUseruid(Map<String, Object> map) {
		return dao.updateByUseruid(map);
	}

	@Override
	public News add(Map<String, Object> map) {
		String type = map.get(MapperDict.type).toString();
		if (type.equals(EAiMsg.commentsarticle) || type.equals(EAiMsg.replycomments)) {
			if (!map.containsKey(MapperDict.content)) {
				return null;
			}
		}
		News news = new News();
		news.setTitle(map.get(MapperDict.title).toString());
		news.setTouid(map.get(MapperDict.touid).toString());
		news.setContent(map.get(MapperDict.content).toString());
		news.setType(EAiMsg.get(Integer.parseInt(type)));
		news.setFromuid(map.get(MapperDict.fromuseruid).toString());
		news.setHref(map.get(MapperDict.href).toString());
		if (dao.insert(news)) {
			return news;
		}
		return null;
	}

	@Override
	public int getCountByUnRead(String uid) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.touid, uid);
		map.put(MapperDict.isread, false);
		return dao.getCount(map);
	}

}
