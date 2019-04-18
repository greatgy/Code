package com.supergenius.xo.sudokuapi.serviceimpl;

import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.sudokuapi.dao.MessagesDao;
import com.supergenius.xo.sudokuapi.entity.Messages;
import com.supergenius.xo.sudokuapi.service.MessagesSO;

/**
 * 站内消息SO实现
 * @author YuYingJie
 */
@Service
public class MessagesSOImpl extends BaseSOImpl<Messages> implements MessagesSO {

	final static String TO = "to";
	
	@Autowired
	MessagesDao dao;
	
	@Override
	protected BaseDao<Messages> getDao() {
		return dao;
	}

	@Override
	public List<Messages> getList(String to, String title) {
		Map<String, Object> map = getParamMap();
		map.put(TO, new ObjectId(to));
		map.put(MapperDict.title, title);
		return dao.getList(map);
	}
	
}
