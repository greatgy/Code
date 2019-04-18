package com.supergenius.xo.manager.service;

import java.util.List;

import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.manager.entity.AppJudgementDetail;
import com.supergenius.xo.manager.enums.EAppJudgementStage;

/**
 * 答辩状态明细SO
 * @author XieMing
 * @date 2016-4-29 下午3:15:24
 */
public interface AppJudgementDetailSO extends BaseSO<AppJudgementDetail> {

	/**
	 * 获取裁判申请到达过某个阶段的申请的个数
	 * @param passinterview
	 * @return
	 * @author XieMing
	 * 2016-10-21 下午4:51:47
	 */
	int getCount(EAppJudgementStage stageto);

	/**
	 * 获取某个申请期间所有的材料
	 * @param uid
	 * @return
	 * @author XieMing
	 * 2016-10-21 下午6:51:31
	 */
	List<String> getFileList(String appjudgementuid, List<EAppJudgementStage> stages);

}
