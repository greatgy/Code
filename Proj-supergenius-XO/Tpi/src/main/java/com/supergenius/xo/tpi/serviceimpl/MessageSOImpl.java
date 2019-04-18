package com.supergenius.xo.tpi.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.model.base.entity.Pager;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.tpi.constants.MapperDict;
import com.supergenius.xo.tpi.dao.MessageDao;
import com.supergenius.xo.tpi.entity.Message;
import com.supergenius.xo.tpi.enums.EInboxState;
import com.supergenius.xo.tpi.enums.EMsgState;
import com.supergenius.xo.tpi.enums.EOutboxState;
import com.supergenius.xo.tpi.service.MessageSO;

/**
 * 消息SO实现
 * @author LiuXiaoke
 *
 */
@Service
public class MessageSOImpl extends BaseSOImpl<Message> implements MessageSO{

	@Autowired
	private MessageDao dao;
	
	@Override
	protected MessageDao getDao() {
		return dao;
	}
	
	/* (non-Javadoc)
	 * @see com.supergenius.xo.tpi.service.MessageSO.getInList
	 * @author: LiuXiaoke
	 */
	public List<Message> getInList(String touseruid, Pager pager) {
		Map<String, Object> map = getParamMap(pager);
		map.put(MapperDict.touseruid, touseruid);
		map.put(MapperDict.instate, EInboxState.normal);
		return dao.getList(map);
	}
	
	/* (non-Javadoc)
	 * @see com.supergenius.xo.tpi.service.MessageSO.getOutList
	 * @author: LiuXiaoke
	 */
	public List<Message> getOutList(String fromuseruid, Pager pager) {
		Map<String, Object> map = getParamMap(pager);
		map.put(MapperDict.fromuseruid, fromuseruid);
		map.put(MapperDict.outstate, EOutboxState.normal);
		return dao.getList(map);
	}

	/* (non-Javadoc)
	 * @see com.supergenius.xo.tpi.service.MessageSO.update
	 */
	@Override
	public boolean update(Map<String, Object> wheremap, Map<String, Object> map1) {
		return dao.update(wheremap, map1);
	}
	
	/* (non-Javadoc)
	 * @see com.supergenius.xo.tpi.service.MessageSO.getOutCount
	 */
	public int getOutCount(String fromuserid) {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.fromuseruid, fromuserid);
		map.put(MapperDict.instate, EInboxState.normal);
		int count = dao.getCount(map);
		return count;
	}
	
	/* (non-Javadoc)
	 * @see com.supergenius.xo.tpi.service.MessageSO.getInCount
	 */
	public int getInCount(String touserid) {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.touseruid, touserid);
		map.put(MapperDict.instate, EOutboxState.normal);
		int count = dao.getCount(map);
		return count;
	}
	
	/* (non-Javadoc)
	 * @see com.supergenius.xo.tpi.service.MessageSO#getUnreadMsg(java.lang.String)
	 */
	@Override
	public List<Message> getInitMsg(String touseruid) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.touseruid, touseruid);
		map.put(MapperDict.state, EMsgState.init);
		map.put(MapperDict.instate, EInboxState.normal);
		return dao.getList(map);
	}

	/* (non-Javadoc)
	 * @see com.supergenius.xo.tpi.service.MessageSO#updateState(com.supergenius.xo.tpi.entity.Message)
	 */
	@Override
	public boolean updateState(Message msg) {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.uid, msg.getUid());
		map.put(MapperDict.state, msg.getState());
		return dao.updateFields(map);
	}
}
