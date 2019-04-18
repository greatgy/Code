package com.supergenius.xo.manager.service;

import com.genius.model.base.enums.EStatus;
import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.manager.entity.Conference;
import com.supergenius.xo.manager.enums.EConfer;

/**
 * 会议室SO
 * @author XieMing
 * @date 2016-7-18 下午2:29:09
 */
public interface ConferenceSO extends BaseSO<Conference> {

	/**
	 * 更新会议室相关信息
	 * @param conference
	 * @param adminLog
	 * @return
	 * @author liubin
	 * @createtime 2016-11-4下午12:20:24
	 * @return boolean
	 */
	boolean update(Conference conference, AdminLog adminLog);
	
	/**
	 * 根据 类型获取count
	 * @param type
	 * @return
	 * @author chenminchang
	 * @create 2016-11-9下午4:55:58
	 */
	int getCountByType(EConfer type);
	
	/**
	 * 更新状态
	 * @param uid
	 * @param status
	 * @return
	 * @author chenminchang
	 * @create 2016-11-10下午3:24:00
	 */
	boolean updateStatus(String uid, EStatus status);
	
	/**
	 * 通过refuid获取一个会议室
	 * @param typeuid
	 * @return
	 * @author chenminchang
	 * @create 2016-11-10下午3:45:22
	 */
	Conference getByTypeuid(String typeuid); 
	
	/**
	 * 更新预期与会人数和实际与会人数
	 * @param Conference
	 * @return
	 * @author chenminchang
	 * @create 2016-11-10下午3:59:48
	 */
	boolean updateJoinCount(Conference conference);
}
