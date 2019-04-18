package com.supergenius.xo.common.service;

import java.util.List;

import org.joda.time.DateTime;

import com.genius.model.base.entity.Pager;
import com.genius.xo.baseadmin.service.BaseSO;
import com.supergenius.xo.base.enums.EUser;
import com.supergenius.xo.common.entity.Message;
import com.supergenius.xo.common.enums.EMsg;
import com.supergenius.xo.common.enums.EMsgState;

public interface MessageSO extends BaseSO<Message> {

	/**
	 * 通过登录用户的uid和消息类型获取消息LIST
	 * 
	 * @param pager
	 * @param touseruid
	 * @param type
	 * @return
	 * @author YuYingJie
	 */
	List<Message> getList(Pager pager, String touseruid, EMsg type);

	/**
	 * 通过登录用户的uid和多种消息类型获取消息LIST
	 * 
	 * @param pager
	 * @param touseruid
	 * @param emsg
	 * @return
	 * @author YuYingJie
	 */
	List<Message> getList(Pager pager, String touseruid, String[] type);

	/**
	 * 通过登录用户的uid、消息状态和消息类型获取消息数量
	 * 
	 * @param touseruid
	 * @param emsg
	 * @return
	 * @author YuYingJie
	 */
	int getCount(String touseruid, EMsgState state, String[] type);

	/**
	 * 通过登录用户的uid、消息状态和和消息类型获取消息数量
	 * 
	 * @param touseruid
	 * @param type
	 * @param state
	 * @return
	 * @author YuYingJie
	 */
	int getCount(String touseruid, EMsg type, EMsgState state);

	/**
	 * 通过登录用户的uid和消息类型获取消息数量
	 * 
	 * @param touseruid
	 * @param type
	 * @return
	 * @author YuYingJie
	 */
	int getCount(String touseruid, EMsg type);

	/**
	 * 通过uid和用户useruid删除通知
	 * 
	 * @param uid
	 * @param useruid
	 * @return
	 * @author YuYingJie
	 */
	boolean deleteByMap(String uid, String useruid);

	/**
	 * 更新已读，未读状态
	 * 
	 * @param touseruid
	 * @param state
	 * @return
	 * @author YuYingJie
	 */
	boolean updateState(String touseruid, EMsgState state, String[] type);

	/**
	 * 根据touseruid,commentuid获取一条消息
	 * 
	 * @param touseruid
	 * @param commentuid
	 * @return
	 * @author YuYingJie
	 */
	Message getOne(String touseruid, String commentuid);

	/**
	 * 查询系统消息
	 * 
	 * @param pager
	 * @param sys
	 * @param beStudentTime
	 * @return
	 * @author YuYingJie
	 */
	List<Message> getList(Pager pager, EMsg sys, EUser type, DateTime beUserTime, DateTime beStuTime);

	/**
	 * 查询消息数量
	 * 
	 * @param uid
	 * @param type
	 * @param user
	 * @param beUserTime
	 * @return
	 * @author YuYingJie
	 */
	int getCount(EMsg type, EUser userType, DateTime createTime);

}
