package com.supergenius.xo.manager.service;

import java.util.List;

import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.manager.entity.AppExpert;
import com.supergenius.xo.manager.entity.AppExpertDetail;
import com.supergenius.xo.manager.enums.EAppExpert;
import com.supergenius.xo.manager.enums.EAppExpertStage;
import com.supergenius.xo.manager.enums.EExpertLevel;
import com.supergenius.xo.manager.enums.EMajor;

/** 
 * 专家申请SO
 * @author guanshiqian
 * @date 2016-4-29 上午10:24:08 
 */
public interface AppExpertSO extends BaseSO<AppExpert> {
	
	/**
	 * 更新AppExpert,AppExpertDetail
	 * @param appExpert
	 * @param appExpertDetail
	 * @author liubin
	 * @createtime 2016-8-23下午3:02:48
	 * @return boolean
	 */
	boolean update(AppExpert appExpert, EAppExpertStage stagefrom, EAppExpertStage stageto);
	
	/**
	 * 同时添加两条记录
	 * @param appExpert
	 * @param appExpertDetail
	 * @author liubin
	 * @createtime 2016-8-26下午3:09:06
	 * @return boolean
	 */
	boolean add(AppExpert appExpert, EAppExpertStage stagefrom, EAppExpertStage stageto);
	
	/**
	 * 根据useruid,major和levelto得到一个AppExpert
	 * @param useruid
	 * @param major
	 * @param type
	 * @author liubin
	 * @createtime 2016-8-29下午2:01:49
	 * @return AppExpert
	 */
	AppExpert getOne(String userUid, EMajor major, EExpertLevel levelTo);
	
	/**
	 * 根据useruid,major和levelfrom得到一个AppExpert
	 * @param useruid
	 * @param major
	 * @param levelFrom
	 * @author liubin
	 * @createtime 2016-8-29下午4:32:39
	 * @return AppExpert
	 */
	AppExpert getOneByLevelFrom(String userUid, EMajor major, EExpertLevel levelFrom);
	
	/**
	 * 根据useruid,major和type得到一个AppExpert
	 * @param userUid
	 * @param major
	 * @param type
	 * @author liubin
	 * @createtime 2016-8-29下午5:50:50
	 * @return AppExpert
	 */
	AppExpert getOne(String userUid, EMajor major, EAppExpert type);

	/**
	 * 获取申请某个等级的专家的个数
	 * @param expert
	 * @return
	 * @author XieMing
	 * 2016-10-23 下午2:41:42
	 */
	int getCount(EExpertLevel levelto);

	/**
	 * 后台更新专家申请状态
	 * @param appExpert
	 * @param appExpertDetail
	 * @param adminLog
	 * @author XieMing
	 * 2016-11-4 下午4:16:51
	 */
	boolean updateStage(AppExpert appExpert, AppExpertDetail appExpertDetail, AdminLog adminLog);

	/**
	 * 根据申请状态获取列表
	 * @param interviewing
	 * @return
	 * @author XieMing
	 * 2016-11-14 下午4:27:55
	 */
	List<AppExpert> getList(EAppExpertStage interviewing);
}
