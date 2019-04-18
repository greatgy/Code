package com.supergenius.xo.manager.service;

import com.genius.model.base.enums.EStatus;
import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.manager.entity.ConfeMember;

/**
 * 会议室成员SO
 * @author XieMing
 * @date 2016-7-18 下午2:28:55
 */
public interface ConfeMemberSO extends BaseSO<ConfeMember> {
	
	/**
	 * 更新会议室成员状态
	 * @param uid
	 * @param status
	 * @return
	 * @author chenminchang
	 * @create 2016-11-10下午5:08:46
	 */
	boolean updateStatus(String uid, EStatus status);

	/**
	 * 根据挑战uid和useruid获取一条记录
	 * @param useruid
	 * @param pkuid
	 * @return
	 * @author chenminchang
	 * @create 2016-11-10下午5:11:19
	 */
	ConfeMember getByUseruidPkuid(String useruid, String pkuid);
	
	/**
	 * 根据useruid和confuid获取一条数据
	 * @param useruid
	 * @param confuid
	 * @return
	 * @author chenminchang
	 * @create 2016-11-10下午5:15:00
	 */
	ConfeMember getByUseruidConfuid(String useruid, String confuid);
}
