/**
 * 会议室HP
 * ============================================================================
 * 声明：© 2013-2014 天才纵横版权所有
 * ----------------------------------------------------------------------------
 * Official Website: http://www.grandgeniusgroup.com
 * ----------------------------------------------------------------------------
 * Copyright: © 2013 wbcom.cn All Rights Reserved.
 * ----------------------------------------------------------------------------
 * @version: 1.0
 * ----------------------------------------------------------------------------
 * @author: 吴红岩
 * ----------------------------------------------------------------------------
 * Create at: 2013-10-27 下午1:10:11
 * ============================================================================
 */
package com.supergenius.server.manager.helper;

import java.util.List;

import com.genius.core.base.utils.DateUtil;
import com.genius.model.base.enums.EStatus;
import com.genius.server.base.helper.BaseHP;
import com.supergenius.xo.manager.entity.Conference;
import com.supergenius.xo.manager.service.ConferenceSO;

/**
 * 因SeegleHP中要用到ConferenceSO，故创建此HP
 * @author wuhongan
 * @modify chenminchang
 */
public class BaseConferenceHP extends BaseHP {

	private static ConferenceSO so;

	protected static ConferenceSO getSO() {
		if (so == null) {
			so = (ConferenceSO) spring.getBean(ConferenceSO.class);
		}
		return so;
	}

	/**
	 * 通过挑战uid获取会议室
	 * @param typeuid
	 * @return
	 * @author chenminchang
	 * @create 2016-11-10下午4:40:44
	 */
	public static Conference getByTypeuid(String typeuid) {
		return getSO().getByTypeuid(typeuid);
	}

	/**
	 * 添加会议室
	 * @param conf
	 * @return
	 * @author chenminchang
	 * @create 2016-11-10下午4:40:25
	 */
	public static boolean add(Conference conf) {
		return getSO().add(conf);
	}
	
	/**
	 * 更新预期与会人数+1
	 * @param conference
	 * @return
	 * @author chenminchang
	 * @create 2016-11-10下午4:41:49
	 */
	public static boolean incrExpectJoinCount(Conference conference) {
		conference.setExpectJoinCount(conference.getExpectJoinCount() + 1);
		return getSO().updateJoinCount(conference);
	}
	
	/**
	 * 更新预期与会人数-1
	 * @param conference
	 * @return
	 * @author chenminchang
	 * @create 2016-11-10下午4:41:49
	 */
	public static boolean decrExpectJoinCount(Conference conference) {
		conference.setExpectJoinCount(conference.getExpectJoinCount() - 1);
		return getSO().updateJoinCount(conference);
	}
	
	/**
	 * 更新实际与会人数+1
	 * @param conference
	 * @return
	 * @author chenminchang
	 * @create 2016-11-10下午4:41:49
	 */
	public static boolean incrRealJoinCount(Conference conference) {
		conference.setRealJoinCount(conference.getRealJoinCount() + 1);
		return getSO().updateJoinCount(conference);
	}
	
	/**
	 * 更新所有conference的状态和与会人数
	 * @return
	 * @author chenminchang
	 * @create 2016-11-10下午12:37:13
	 */
	public static boolean updateComferenceStatus() {
		List<Conference> list = getSO().getList(getParamMap(true));
		for (Conference conference : list) {
			if (!EStatus.disable.equals(conference.getStatus())) {
				boolean change = false;
				if (conference.getBegintime() != null && DateUtil.NowTime().isBefore(conference.getBegintime())) {//未开始
					if (!EStatus.init.equals(conference.getStatus())) {
						conference.setStatus(EStatus.init);
						change = true;
					}
				} else if (conference.getEndtime() != null && DateUtil.NowTime().isBefore(conference.getEndtime())) {//进行中
					if (!EStatus.enable.equals(conference.getStatus())) {
						conference.setStatus(EStatus.enable);
						change = true;
					}
				} else {//已关闭
					if (!EStatus.end.equals(conference.getStatus())) {
						conference.setStatus(EStatus.end);
						change = true;
					}
				}
				if (change)
					getSO().updateStatus(conference.getUid(), conference.getStatus());
			}
		}
		return true;
	} 

}