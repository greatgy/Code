package com.supergenius.xo.finance.serviceimpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.finance.dao.NewsDao;
import com.supergenius.xo.finance.entity.News;
import com.supergenius.xo.finance.enums.EFinanceMsg;
import com.supergenius.xo.finance.service.NewsSO;

/**
 * 消息so实现
 * 
 * @author LouPengYu
 * @date 2018年1月2日09:55:19
 */
@Service("financeNewsSOImpl")
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
		if (type.equals(EFinanceMsg.commentsarticle) || type.equals(EFinanceMsg.replycomments)) {
			if (!map.containsKey(MapperDict.content)) {
				return null;
			}
		}
		News news = new News();
		news.setTitle(map.get(MapperDict.title).toString());
		news.setTouid(map.get(MapperDict.touid).toString());
		news.setContent(map.get(MapperDict.content).toString());
		news.setType(EFinanceMsg.get(Integer.parseInt(type)));
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
	public int getCountByUnRead(String useruid, EFinanceMsg typegroup) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.touid, useruid);
		if (typegroup !=null && !typegroup.getName().equals("subscribe")) {
			map.put(MapperDict.type, typegroup);
		}
		map.put(MapperDict.isread, false);
		return dao.getCount(map);
	}
}
