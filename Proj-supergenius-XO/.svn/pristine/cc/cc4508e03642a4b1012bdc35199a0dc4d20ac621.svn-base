package com.supergenius.xo.manager.serviceimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.core.base.utils.StrUtil;
import com.genius.model.base.entity.Pager;
import com.genius.model.base.enums.EStatus;
import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.genius.xo.baseadmin.dao.AdminLogDao;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.manager.dao.CommentsDao;
import com.supergenius.xo.manager.entity.Comments;
import com.supergenius.xo.manager.service.CommentsSO;
import com.supergenius.xo.user.enums.EComment;
import com.supergenius.xo.user.service.UserSO;

/**
 * 评论Impl
 * @author XieMing
 * @date 2016-7-18 下午2:31:40
 */
@Service("managerCommentsSOImpl")
public class CommentsSOImpl extends BaseSOImpl<Comments> implements CommentsSO {

	@Autowired
	CommentsDao dao;
	@Autowired
	UserSO userSO;
	@Autowired
	AdminLogDao adminLogDao;
	
	@Override
	protected BaseDao<Comments> getDao() {
		return dao;
	}
	
	@Override
	public List<Comments> getList(String fromuid, EComment type, Pager pager, String order) {
		Map<String, Object> map = pager == null ? getParamMap() : getParamMap(pager);
		map.put(MapperDict.fromuid, fromuid);
		map.put(MapperDict.type, type);
		if (StrUtil.isNotEmpty(order)) {
			map.put(MapperDict.ascDesc, order);
		}
		List<Comments> list = dao.getList(map);
		for (Comments item : list) {
			if (StrUtil.isNotEmpty(item.getFromuseruid())
					&& !MapperDict.DefaultAnonymousUid.equals(item.getFromuseruid())) {
				item.setFromUser(userSO.get(item.getFromuseruid()));
			}
			if (StrUtil.isNotEmpty(item.getTouseruid())
					&& !MapperDict.DefaultAnonymousUid.equals(item.getTouseruid())) {
				item.setToUser(userSO.get(item.getTouseruid()));
			}
		}
		return list;
	}

	@Override
	public int getCount(String fromuid, EChannel channel, EComment type) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.fromuid, fromuid);
		map.put(MapperDict.channel, channel);
		map.put(MapperDict.type, type);
		return dao.getSpeakCount(map);
	}

	@Override
	public List<Comments> getList(String fromuid, EChannel channel, EComment type) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.fromuid, fromuid);
		map.put(MapperDict.channel, channel);
		map.put(MapperDict.type, type);
		return dao.getList(map);
	}

	@Override
	public List<Comments> getList(String fromuseruid, EComment praise) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.fromuseruid, fromuseruid);
		map.put(MapperDict.type, praise);
		return dao.getList(map);
	}

	@Override
	public Comments getOne(String touid, String fromuseruid, EComment type, EChannel eChannel) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.touid, touid);
		map.put(MapperDict.fromuseruid, fromuseruid);
		map.put(MapperDict.channel, eChannel);
		map.put(MapperDict.type, type);
		return dao.getOne(map);
	}

	@Override
	public int getCount(EComment type) {
		Map<String, Object> map = getParamMap(true);
		map.put(MapperDict.type, type);
		return dao.getCount(map);
	}

	@Override
	public int getSpeakCount(EComment... type) {
		Map<String, Object> map = getParamMap(true);
		if (type.length > 0) {
			map.put(MapperDict.type, type[0]);
		}
		return dao.getSpeakCount(map);
	}

	@Override
	public int searchCount(Map<String, Object> map) {
		return dao.searchCount(map);
	}

	@Override
	public List<Comments> search(Map<String, Object> map) {
		return dao.search(map);
	}

	@Override
	public boolean deleteByTouid(String touid) {
		Map<String, Object> map = getParamMap(true);
		map.put(MapperDict.touid, touid);
		return dao.deleteByMap(map);
	}

	@Override
	public boolean updateStatus(String uid, EStatus status, AdminLog adminLog) {
		Map<String, Object> map = getParamMap(true);
		map.put(MapperDict.uid, uid);
		map.put(MapperDict.status, status);
		return dao.updateFields(map) && adminLogDao.insert(adminLog);
	}

	@Override
	public boolean updateStatusByTouid(String touid, EStatus status) {
		Map<String, Object> map = getParamMap(true);
		map.put(MapperDict.touid, touid);
		map.put(MapperDict.status, status);
		return dao.updateStatus(map);
	}

	@Override
	public List<Comments> getList(String touid) {
		Map<String, Object> map = getParamMap(true);
		map.put(MapperDict.touid, touid);
		return dao.getList(map);
	}

}
