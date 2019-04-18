package com.supergenius.xo.manager.service;

import java.util.List;

import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.manager.entity.AppExpertDetail;
import com.supergenius.xo.manager.enums.EAppExpertStage;

/** 
 * 专家申请明细SO
 * @author guanshiqian
 * @date 2016-4-29 下午12:04:32 
 */
public interface AppExpertDetailSO extends BaseSO<AppExpertDetail> {
	
	/**
	 * 得到指定阶段的AppExpertDetail对象
	 * @param userUid
	 * @param appExpertUid
	 * @param stageTo
	 * @author liubin
	 * @createtime 2016-8-21下午6:56:53
	 * @return AppExpertDetail
	 */
	AppExpertDetail getOne(String userUid, String appExpertUid, EAppExpertStage stageTo);

	/**
	 * 获取申请期间的材料
	 * @param uid
	 * @param fileList
	 * @return
	 * @author XieMing
	 * 2016-10-24 下午5:36:23
	 */
	List<String> getFileList(String uid, List<EAppExpertStage> fileList);
	
}
