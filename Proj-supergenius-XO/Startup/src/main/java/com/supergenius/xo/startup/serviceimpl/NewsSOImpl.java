package com.supergenius.xo.startup.serviceimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.model.base.entity.Pager;
import com.genius.model.base.enums.EStatus;
import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.common.enums.EMsg;
import com.supergenius.xo.startup.dao.NewsDao;
import com.supergenius.xo.startup.entity.News;
import com.supergenius.xo.startup.service.NewsSO;

/**
 * 消息Impl
 * 
 * @author Yangguang
 * @date 2017年8月29日18:53:09
 */
@Service("StartupNewsSOImpl")
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
	public News add(Map<String, Object> map) {
		String type = map.get(MapperDict.type).toString();
		if (type.equals(EMsg.comment) || type.equals(EMsg.replycomment)) {
			if (!map.containsKey(MapperDict.content)) {
				return null;
			}
		}
		News news = new News();
		news.setTitle(map.get(MapperDict.title).toString());
		news.setContent(map.get(MapperDict.content).toString());
		news.setType(EMsg.get(Integer.parseInt(type)));
		news.setFromuid(map.get(MapperDict.fromuseruid).toString());
		news.setHref(map.get(MapperDict.href).toString());
		if (dao.insert(news)) {
			return news;
		}
		return null;
	}

	@Override
	public List<News> getListByUseruid(String useruid, Pager pager) {
		Map<String, Object> map;
		if (pager != null) {
			map = getParamMap(pager);
		} else {
			map = getParamMap();
		}
		map.put(MapperDict.useruid, useruid);
		map.put(MapperDict.status, EStatus.enable);
		return dao.getListByUseruid(map);
	}
}
