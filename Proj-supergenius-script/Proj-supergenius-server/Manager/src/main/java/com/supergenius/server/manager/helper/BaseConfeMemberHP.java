/**
 * 会议室成员HP
 * ============================================================================

\ * 声明：© 2013-2014 天才纵横版权所有
 * ----------------------------------------------------------------------------
 * Official Website: http://www.grandgeniusgroup.com
 * ----------------------------------------------------------------------------
 * Copyright: © 2013 GrandGeniusGroup All Rights Reserved.
 * ----------------------------------------------------------------------------
 * @version: 1.0
 * ----------------------------------------------------------------------------
 * @author: Architect.bian
 * ----------------------------------------------------------------------------
 * Create at: 2013-1-12 上午11:11:17
 * ============================================================================
 */
package com.supergenius.server.manager.helper;

import com.genius.model.base.enums.EStatus;
import com.genius.server.base.helper.BaseHP;
import com.supergenius.xo.manager.entity.ConfeMember;
import com.supergenius.xo.manager.service.ConfeMemberSO;

/**
 * 因SeegleHP中要用到ConfeMemberSO，故创建此HP
 * @author mengxiaobin
 * @modify chenminchang
 */
public class BaseConfeMemberHP extends BaseHP {

	private static ConfeMemberSO so;

	protected static ConfeMemberSO getSO() {
		if (so == null) {
			so = (ConfeMemberSO) spring.getBean(ConfeMemberSO.class);
		}
		return so;
	}


	/**
	 * 添加
	 * @param confemember
	 * @return
	 * @author chenminchang
	 * @create 2016-11-10下午5:07:07
	 */
	public static boolean add(ConfeMember confemember) {
		return getSO().add(confemember);
	}

	/**
	 * 更新会议室成员状态
	 * @param uid
	 * @param status
	 * @return
	 * @author chenminchang
	 * @create 2016-11-10下午5:10:25
	 */
	public static boolean updateStatus(String uid, EStatus status) {
		return getSO().updateStatus(uid, status);
	}
	
	/**
	 * 更新所有字段
	 * @param confemember
	 * @return
	 * @author chenminchang
	 * @create 2016-11-10下午5:35:09
	 */
	public static boolean updateFields(ConfeMember confemember) {
		return getSO().updateFields(confemember);
	}

	/**
	 * 根据挑战uid和useruid获取一条记录
	 * @param useruid
	 * @param pkuid
	 * @return
	 * @author chenminchang
	 * @create 2016-11-10下午5:11:19
	 */
	public static ConfeMember getByUseruidPkuid(String useruid, String pkuid) {
		return getSO().getByUseruidPkuid(useruid, pkuid);
	}

	/**
	 * 根据useruid和confuid获取一条数据
	 * @param useruid
	 * @param confuid
	 * @return
	 * @author chenminchang
	 * @create 2016-11-10下午5:15:00
	 */
	public static ConfeMember getByUseruidConfuid(String useruid, String confuid) {
		return getSO().getByUseruidConfuid(useruid, confuid);
	}

}