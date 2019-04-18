package com.supergenius.xo.startup.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.model.base.entity.Pager;
import com.genius.model.base.enums.EStatus;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.startup.dao.InboxDao;
import com.supergenius.xo.startup.entity.Inbox;
import com.supergenius.xo.startup.entity.News;
import com.supergenius.xo.startup.service.InboxSO;

/**
 * 收件箱Impl
 * 
 * @author yangguang
 * @date 2017年8月29日18:42:07
 */
@Service("startupInboxSOImpl")
public class InboxSOImpl extends BaseSOImpl<Inbox> implements InboxSO {

	@Autowired
	InboxDao dao;
	
	@Override
	protected InboxDao getDao() {
		return dao;
	}

	@Override
	public boolean add(News news, String touseruid) {
		Inbox inbox = new Inbox();
		inbox.setUseruid(touseruid);
		inbox.setNewsuid(news.getUid());
		inbox.setType(news.getType());
		inbox.setIsread(false);
		inbox.setCreatetime(DateTime.now());
		return dao.insert(inbox);
	}

	@Override
	public List<Inbox> getList(String useruid, Pager pager) {
		Map<String, Object> map = getParamMap(pager);
		map.put(MapperDict.useruid, useruid);
		return dao.getList(map);
	}

	@Override
	public Inbox getInboxByNewsuid(String newsuid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.newsuid, newsuid);
		return dao.getOne(map);
	}

	@Override
	public boolean updateByUseruid(String useruid, EStatus status) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.status, status);
		map.put(MapperDict.useruid, useruid);
		return dao.updateByUseruid(map);
	}

	@Override
	public int getCountByUnRead(String uid) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.useruid, uid);
		map.put(MapperDict.isread, false);
		return dao.getCount(map);
	}

}
