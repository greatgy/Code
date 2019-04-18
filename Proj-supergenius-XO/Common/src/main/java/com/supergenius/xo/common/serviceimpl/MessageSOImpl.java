package com.supergenius.xo.common.serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.model.base.entity.Pager;
import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.baseadmin.serviceimpl.BaseSOImpl;
import com.supergenius.xo.base.enums.EUser;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.common.dao.MessageDao;
import com.supergenius.xo.common.entity.Message;
import com.supergenius.xo.common.enums.EMsg;
import com.supergenius.xo.common.enums.EMsgState;
import com.supergenius.xo.common.service.MessageSO;

@Service("CommonMessageSOImpl")
public class MessageSOImpl extends BaseSOImpl<Message> implements MessageSO {

	@Autowired
	private MessageDao dao;

	@Override
	protected BaseDao<Message> getDao() {
		return dao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.user.service.MessageSO#getList(com.genius.model.base.entity.Pager, java.lang.String, com.supergenius.xo.common.enums.EMsg)
	 */
	@Override
	public List<Message> getList(Pager pager, String touseruid, EMsg type) {
		Map<String, Object> map = getParamMap(pager);
		map.put(MapperDict.touseruid, touseruid);
		map.put(MapperDict.type, type);
		return dao.getList(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.common.service.MessageSO#getCount(java.lang.String, com.supergenius.xo.common.enums.EMsg, com.supergenius.xo.common.enums.EMsgState)
	 */
	@Override
	public int getCount(String touseruid, EMsg type, EMsgState state) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.touseruid, touseruid);
		map.put(MapperDict.type, type);
		map.put(MapperDict.state, state);
		return dao.getCount(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.common.service.MessageSO#getCount(java.lang.String, com.supergenius.xo.common.enums.EMsgState, com.supergenius.xo.common.enums.EMsg[])
	 */
	@Override
	public int getCount(String touseruid, EMsgState state, String[] str) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.touseruid, touseruid);
		map.put(MapperDict.state, state);
		List<String> type = new ArrayList<>();
		if (str != null) {
			for (int i =0; i < str.length ; i++) {
				type.add(str[i]);
			}
			map.put(MapperDict.type, type);
		}
		return dao.getCountByMsgType(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.common.service.MessageSO#getCount(java.lang.String, com.supergenius.xo.common.enums.EMsg)
	 */
	@Override
	public int getCount(String touseruid, EMsg type) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.touseruid, touseruid);
		map.put(MapperDict.type, type);
		return dao.getCount(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.common.service.MessageSO#getList(com.genius.model.base.entity.Pager, java.lang.String, com.supergenius.xo.common.enums.EMsg[])
	 */
	@Override
	public List<Message> getList(Pager pager, String touseruid, String[] str) {
		Map<String, Object> map = getParamMap(pager);
		map.put(MapperDict.touseruid, touseruid);
		List<String> type = new ArrayList<>();
		if (str != null) {
			for (int i = 0; i < str.length; i++) {
				type.add(str[i]);
			}
			map.put(MapperDict.type, type);
		}
		return dao.getListByMsgType(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.common.service.MessageSO#deleteByMap(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean deleteByMap(String uid, String useruid) {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.uid, uid);
		map.put(MapperDict.touseruid, useruid);
		return dao.deleteByMap(map);
	}

	/*
	 * (non-Javadoc)
	 * @see com.supergenius.xo.common.service.MessageSO#updateState(java.lang.String, com.supergenius.xo.common.enums.EMsgState, java.lang.String[])
	 */
	@Override
	public boolean updateState(String touseruid, EMsgState state, String[] type) {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.where + MapperDict.touseruid, touseruid);
		map.put(MapperDict.where + MapperDict.state, state);
		List<String> list = new ArrayList<>();
		if (type != null) {
			for (int i =0; i < type.length ; i++) {
				list.add(type[i]);
			}
			map.put(MapperDict.wheretype + MapperDict._list, type);
		}
		map.put(MapperDict.state, EMsgState.success);
		return dao.updateWhere(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.common.service.MessageSO#getOne(java.lang.String, java.lang.String)
	 */
	@Override
	public Message getOne(String touseruid, String commentuid) {
		Map<String, Object> map = new HashMap<>();
		Map<String, String> map2 = new HashMap<>();
		map2.put(MapperDict.commentuid, commentuid);
		map.put(MapperDict.touseruid, touseruid);
		map.put(MapperDict.data, map2);
		return dao.getOne(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.common.service.MessageSO#getList(com.genius.model.base.entity.Pager, com.supergenius.xo.common.enums.EMsg, org.joda.time.DateTime, com.supergenius.xo.base.enums.EUser)
	 */
	@Override
	public List<Message> getList(Pager pager, EMsg sys, EUser userType, DateTime beUserTime, DateTime beStuTime) {
		Map<String, Object> map;
		if (pager != null) {
			map = getParamMap(pager);
		} else {
			map = getParamMap();
		}
		map.put(MapperDict.type, sys);
		map.put(MapperDict.createtime + MapperDict.suffix_greaterOrEqual_key, beUserTime);
		List<String> list = new ArrayList<>();
		list.add(EUser.all.name());
		if (userType.equals(EUser.user)) {
			list.add(userType.name());
			map.put(MapperDict.data_list, list);
			return dao.getList(map);
		}
		map.put(MapperDict.data_list, list);
		List<Message> usermsgList = dao.getList(map);
		Map<String, Object> map2;
		if (pager != null) {
			map2 = getParamMap(pager);
		} else {
			map2 = getParamMap();
		}
		map2.put(MapperDict.type, sys);
		map2.put(MapperDict.createtime + MapperDict.suffix_greaterOrEqual_key, beStuTime);
		List<String> list2 = new ArrayList<>();
		list2.add(userType.name());
		map2.put(MapperDict.data_list, list2);
		List<Message> stumsgList = dao.getList(map2);
		stumsgList.addAll(usermsgList);
		return stumsgList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.common.service.MessageSO#getCount(java.lang.String, com.supergenius.xo.common.enums.EMsg, com.supergenius.xo.base.enums.EUser, org.joda.time.DateTime)
	 */
	@Override
	public int getCount(EMsg type, EUser userType, DateTime createTime) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.createtime + MapperDict.suffix_greaterOrEqual_key, createTime);
		map.put(MapperDict.type, type);
		List<String> list = new ArrayList<>();
		list.add(userType.name());
		list.add(EUser.all.name());
		map.put(MapperDict.data_list, list);
		return dao.getCount(map);
	}

}
