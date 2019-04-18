package com.supergenius.xo.manager.service;

import java.util.List;

import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.manager.entity.AppJudgement;
import com.supergenius.xo.manager.entity.AppJudgementDetail;
import com.supergenius.xo.manager.enums.EAppJudgementStage;
import com.supergenius.xo.manager.enums.EMajor;

/** 
 * 裁判申请SO
 * @author guanshiqian
 * @date 2016-4-28 上午11:54:16 
 */
public interface AppJudgementSO extends BaseSO<AppJudgement> {

	/**
	 * 根据用户的uid获取一条记录
	 * @param useruid
	 * @author XieMing
	 * 2016-8-18 下午3:31:00
	 */
	AppJudgement getOne(EMajor major, String useruid);

	/**
	 * 更新裁判申请状态，增加明细记录
	 * @param appJudgement
	 * @param appJudgementDetail
	 * @author XieMing
	 * 2016-8-19 下午3:32:58
	 */
	boolean update(AppJudgement appJudgement, EAppJudgementStage stageFrom);

	/**
	 * 更新申请状态
	 * @param appJudgement
	 * @param appJudgementDetail
	 * @author XieMing
	 * 2016-11-1 下午5:15:03
	 */
	boolean updateStage(AppJudgement appJudgement, AppJudgementDetail appJudgementDetail, AdminLog adminLog);

	/**
	 * 获取某个阶段的裁判申请的个数
	 * @param passinterview
	 * @return
	 * @author XieMing
	 * 2016-11-7 下午4:20:26
	 */
	int getCount(EAppJudgementStage stage);

	/**
	 * 根据申请状态获取列表
	 * @param interviewing
	 * @return
	 * @author XieMing
	 * 2016-11-14 下午3:27:38
	 */
	List<AppJudgement> getList(EAppJudgementStage stage);

}
