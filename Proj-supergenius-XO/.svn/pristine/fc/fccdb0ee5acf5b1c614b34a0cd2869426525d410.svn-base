package com.supergenius.xo.manager.service;

import java.util.List;

import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.manager.entity.PKStateDetail;
import com.supergenius.xo.manager.enums.EPKStage;

/**
 * 挑战状态明细SO
 * @author XieMing
 * @date 2016-4-29 下午3:17:20
 */
public interface PkStateDetailSO extends BaseSO<PKStateDetail> {

	/**
	 * 根据tostage获取一条pk明细
	 * @param pkuid
	 * @param tostage
	 * @return
	 * @author chenminchang
	 * @create 2016-9-13下午12:36:15
	 */
	PKStateDetail getOneByToStage(String pkuid, EPKStage tostage);
	
	/**
	 * 根据挑战uid得到所有的挑战详情
	 * @param pkscheduleuid
	 * @return
	 * @author liubin
	 * @createtime 2016-11-1下午3:12:11
	 * @return List<PKStateDetail>
	 */
	List<PKStateDetail> getList(String pkscheduleuid);
	
	/**
	 * 根据挑战uid得到挑战详情的数量
	 * @param pkscheduleuid
	 * @return
	 * @author liubin
	 * @createtime 2016-11-1下午4:27:44
	 * @return int
	 */
	int getCount(String pkscheduleuid);
	
	/**
	 * 根据挑战uid得到挑战详情
	 * @param pkscheduleuid
	 * @return
	 * @author liubin
	 * @createtime 2016-11-15下午7:53:07
	 * @return List<PKStateDetail>
	 */
	List<PKStateDetail> getListByEnable(String pkscheduleuid);
}
