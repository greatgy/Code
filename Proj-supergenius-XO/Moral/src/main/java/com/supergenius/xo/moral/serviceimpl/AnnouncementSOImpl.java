package com.supergenius.xo.moral.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.core.base.constant.BaseMapperDict;
import com.genius.model.base.entity.Pager;
import com.genius.model.base.enums.EStatus;
import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.genius.xo.mongodb.Mongo;
import com.genius.xo.mongodb.MongoDict;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.supergenius.moral.moral.dao.AnnouncementDao;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.moral.entity.Announcement;
import com.supergenius.xo.moral.service.AnnouncementSO;

/**
 * 社区公告SO实现
 * 
 * @author LiJiacheng
 */
@Service
public class AnnouncementSOImpl extends BaseSOImpl<Announcement> implements AnnouncementSO {

	@Autowired
	AnnouncementDao dao;

	@Override
	protected BaseDao<Announcement> getDao() {
		return dao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.moral.service.AnnouncementSO#update(com.genius.model.base.enums.EStatus, java.lang.String[])
	 * 
	 * @author: LiJiacheng 2015-8-14 下午2:26:17
	 */
	@Override
	public boolean update(EStatus eStatus, String ids) {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.status, eStatus);
		map.put(MapperDict.uid, ids);
		return getDao().updateFields(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.moral.service.AnnouncementSO#announcement_up(java.lang.String)
	 * 
	 * @author: LiJiacheng 2015-8-14 下午3:54:07
	 */
	@Override
	public boolean announcement_up(String ids) {
		Announcement announcement = dao.get(ids);
		int sn = announcement.getSn();
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.sn + MapperDict.suffix_greater_key, sn);
		map.put(MapperDict.orderBy, MapperDict.sn);
		map.put(MapperDict.pageSize, 1);
		List<Announcement> list = dao.getList(map);
		if (0 == list.size()) {
			return false;
		} else {
			int up = list.get(0).getSn();
			list.get(0).setSn(sn);
			announcement.setSn(up);
			return dao.update(announcement) && dao.update(list.get(0));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.moral.service.AnnouncementSO#announcement_down(java.lang.String)
	 * 
	 * @author: LiJiacheng 2015-8-14 下午3:54:07
	 */
	@Override
	public boolean announcement_down(String ids) {
		Announcement announcement = dao.get(ids);
		int sn = announcement.getSn();
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.sn + MapperDict.suffix_less_key, sn);
		map.put(MapperDict.orderBy, MapperDict.sn);
		map.put(MapperDict.pageSize, 1);
		List<Announcement> list = dao.getList(map);
		if (0 == list.size()) {
			return false;
		} else {
			int up = list.get(0).getSn();
			list.get(0).setSn(sn);
			announcement.setSn(up);
			return dao.update(announcement) && dao.update(list.get(0));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.moral.service.AnnouncementSO#setTop(java.lang.String, boolean)
	 * 
	 * @author: LiJiacheng 2015-8-14 下午4:05:13
	 */
	@Override
	public boolean setTop(String id, boolean istop) {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.status, EStatus.enable);
		map.put(MapperDict.istop, istop);
		map.put(BaseMapperDict.uid, id);
		return dao.updateFields(map);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.moral.service.AnnouncementSO#checkIsTop()
	 * 
	 * @author: LiJiacheng 2015-8-14 下午6:36:01
	 */
	@Override
	public boolean checkIsTop() {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.istop, true);
		if (0 == dao.getList(map).size()) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.moral.service.AnnouncementSO#getAnnouncements()
	 * 
	 * @author: LiJiacheng 2015-8-14 下午7:27:25
	 */
	@Override
	public List<Announcement> getAnnouncements(Pager pager) {
		DBObject dbObject = new BasicDBObject(MapperDict.status, EStatus.enable.toString());
		dbObject.put(MapperDict.endtime, new BasicDBObject(MongoDict.$gte, DateTime.now().getMillis()));
		DBObject orderByDbObject = new BasicDBObject(MapperDict.istop, -1);
		orderByDbObject.put(MapperDict.sn, -1);
		return dao.getList(dbObject, pager, orderByDbObject, Mongo.defaultStrategy);
	}
}
