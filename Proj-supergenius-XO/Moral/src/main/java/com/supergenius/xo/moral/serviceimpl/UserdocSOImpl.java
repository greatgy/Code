package com.supergenius.xo.moral.serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.core.base.constant.BaseMapperDict;
import com.genius.core.base.utils.MapBuilder;
import com.genius.model.base.entity.Pager;
import com.genius.model.base.enums.EStatus;
import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.genius.xo.mongodb.MongoDict;
import com.supergenius.moral.moral.dao.UserdocDao;
import com.supergenius.xo.moral.constants.MapperDict;
import com.supergenius.xo.moral.entity.Userdoc;
import com.supergenius.xo.moral.service.UserdocSO;
import com.supergenius.xo.user.dao.UserDao;
import com.supergenius.xo.user.entity.User;

/**
 * 用户上传SO实现
 * 
 * @author LiJiacheng
 */
@Service
public class UserdocSOImpl extends BaseSOImpl<Userdoc> implements UserdocSO {

	@Autowired
	UserdocDao dao;
	
	@Autowired
	UserDao userDao;

	@Override
	protected BaseDao<Userdoc> getDao() {
		return dao;
	}

	/* (non-Javadoc)
	 * @see com.supergenius.xo.moral.service.UserdocSO#update(com.genius.model.base.enums.EStatus, java.lang.String[])
	 */
	@Override
	public boolean update(EStatus eStatus, String[] ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		for (String id : ids) {
			map.put(MapperDict.status, eStatus);
			map.put(MapperDict.uid, id);
			dao.updateFields(map);
		}
		return true;
	}
	
	/* (non-Javadoc)
	 * @see com.genius.xo.base.serviceimpl.BaseSOImpl#getList(java.util.Map)
	 */
	@Override
	public List<Userdoc> getList(Map<String, Object> map) {
		List<Userdoc> userdocs = dao.getList(map);
		for (Userdoc item : userdocs) {
			User user = userDao.get(item.getUseruid());
			if (user != null) {
				item.setUsername(user.getShowname());
			}
		}
		return userdocs;
	}

	/*
	 * (non-Javadoc)
	 * @see com.supergenius.xo.moral.service.UserdocSO#updateCount(com.supergenius.xo.moral.entity.Userdoc)
	 */
	@Override
	public boolean updateCount(Userdoc file) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.uid, file.getUid());
		map.put(MapperDict.countdl, file.getCountdl()+1);
		return dao.updateFields(map);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.genius.xo.base.serviceimpl.BaseSOImpl#getList(com.genius.model.base.entity.Pager)
	 */
	@Override
	public List<Userdoc> getList(Pager pager) {
		Map<String, Object> map = getParamMap(pager);
		List<Userdoc> userdocs = dao.getList(map);
		for (Userdoc item : userdocs) {
			User user = userDao.get(item.getUseruid());
			if (user != null) {
				item.setUsername(user.getShowname());
			}
		}
		return userdocs;
	}

	/*
	 * (non-Javadoc)
	 * @see com.supergenius.xo.moral.service.UserdocSO#group(com.genius.model.base.entity.Pager)
	 */
	@Override
	public List<Map<String, Object>> group(Pager pager) {
		Map<String, Object> where = new HashMap<>();
		where.put(MapperDict.createtime + BaseMapperDict.suffix_less_key, DateTime.now());
		where.put(MapperDict.status, EStatus.enable);
		List<String> fields = new ArrayList<>();
		fields.add(MapperDict._id);
		fields.add(MapperDict.total);
		Map<String, Object> group = new HashMap<>();
		group.put(MapperDict._id, MongoDict.$ + MapperDict.useruid);
		group.put(MapperDict.total, MapBuilder.start(MongoDict.$sum, MongoDict.$ + MapperDict.countdl).get());
		Map<String, Object> sort = new HashMap<>();
		sort.put(MapperDict.total, -1);
		return dao.group(where, fields, group, sort, pager);
	}

	/*
	 * (non-Javadoc)
	 * @see com.supergenius.xo.moral.service.UserdocSO#getList(java.lang.String)
	 */
	@Override
	public List<Userdoc> getList(String useruid) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.useruid, useruid);
		List<Userdoc> userdocs = dao.getList(map);
		for (Userdoc item : userdocs) {
			User user = userDao.get(item.getUseruid());
			if (user != null) {
				item.setUsername(user.getShowname());
			}
		}
		return userdocs;
	}

	/*
	 * (non-Javadoc)
	 * @see com.supergenius.xo.moral.service.UserdocSO#getList(com.genius.model.base.entity.Pager, java.lang.String)
	 */
	@Override
	public List<Userdoc> getList(Pager pager, String orderBy) {
		Map<String, Object> map = getParamMap(pager);
		if (orderBy != null) {
			map.put(MapperDict.orderBy, orderBy);
		}
		return dao.getList(map);
	}

}
