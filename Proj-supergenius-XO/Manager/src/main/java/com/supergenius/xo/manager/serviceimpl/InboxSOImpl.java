package com.supergenius.xo.manager.serviceimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.model.base.entity.Pager;
import com.genius.model.base.enums.EStatus;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.manager.dao.InboxDao;
import com.supergenius.xo.manager.dao.MessageDao;
import com.supergenius.xo.manager.entity.Inbox;
import com.supergenius.xo.manager.entity.Message;
import com.supergenius.xo.manager.enums.EMsgGroup;
import com.supergenius.xo.manager.service.InboxSO;
import com.supergenius.xo.user.dao.UserDao;

/**
 * 收件箱Impl
 * @author XieMing
 * @date 2016-7-18 下午2:32:08
 */
@Service("managerInboxSOImpl")
public class InboxSOImpl extends BaseSOImpl<Inbox> implements InboxSO {

	@Autowired
	InboxDao dao;
	
	@Autowired
	MessageDao msgDao;
	
	@Autowired
	UserDao userDao;
	
	@Override
	protected InboxDao getDao() {
		return dao;
	}

	@Override
	public Inbox getMessageAndUserOfInbox(Inbox inbox) {
		Message message = msgDao.get(inbox.getMsguid());
		inbox.setFromuser(userDao.get(message.getFromuid()));
		inbox.setMessage(message);
		return inbox;
	}

	@Override
	public List<Inbox> getUserInbox(String useruid, Pager pager, EMsgGroup typegroup) {
		Map<String, Object> map = getParamMap(pager);
		map.put(MapperDict.useruid, useruid);
		map.put(MapperDict.typegroup, typegroup);
		return dao.getList(map);
	}

	@Override
	public int getCountByUnRead(String useruid, EMsgGroup typegroup) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.useruid, useruid);
		map.put(MapperDict.typegroup, typegroup);
		map.put(MapperDict.isread, false);
		return dao.getCount(map);
	}

	@Override
	public int getCountByUnRead(String useruid) {
		return getCountByUnRead(useruid, null);
	}

	@Override
	public boolean updateRead(List<String> uids) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.uids, uids);
		map.put(MapperDict.isread, true);
		return dao.updateRead(map);
	}

	@Override
	public boolean addInbox(Message message, String touseruid) {
		Inbox inbox = new Inbox();
		inbox.setUseruid(touseruid);
		inbox.setMsguid(message.getUid());
		inbox.setTypegroup(message.getTypegroup());
		inbox.setIsread(false);
		return dao.insert(inbox);
	}

	@Override
	public int getCountByUser(String useruid, EMsgGroup typegroup) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.useruid, useruid);
		map.put(MapperDict.typegroup, typegroup);
		return dao.getCount(map);
	}

	@Override
	public boolean delete(String uid) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.uid, uid);
		map.put(MapperDict.status, EStatus.disable);
		return dao.updateFields(map);
	}

	@Override
	public List<Inbox> getList(String useruid, boolean flag) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.useruid, useruid);
		map.put(MapperDict.isread, flag);
		return dao.getList(map);
	}
	
}
