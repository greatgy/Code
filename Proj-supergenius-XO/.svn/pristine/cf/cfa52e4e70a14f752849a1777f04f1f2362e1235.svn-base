package com.supergenius.xo.manager.service;

import java.util.List;

import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.manager.entity.AppReplyExpert;

/** 
 * 答辩专家So
 * @author chenminchang
 * @date 2016-7-17 下午12:13:13 
 */
public interface AppReplyExpertSO extends BaseSO<AppReplyExpert> {
	
	/**
	 * 根据Appreply的uid获得三个没有缺席的专家专家申请对象
	 * @param appreplyuid
	 * @return
	 */
	List<AppReplyExpert> getAppReplyExpertList(String appreplyuid);
	
	/**
	 * 根据appreplyuid,expertuid得到对象
	 * @param appreplyuid
	 * @param expertuid
	 * @return
	 * @author liubin
	 * @createtime 2016-11-11下午5:54:56
	 * @return AppReplyExpert
	 */
	AppReplyExpert getOne(String appreplyuid, String expertuid);
	
	/**
	 * 更换专家,更新两个专家申请表
	 * @param appReplyExpert
	 * @param appReplyExpert2
	 * @return
	 * @author liubin
	 * @createtime 2016-11-11下午6:01:44
	 * @return boolean
	 */
	boolean update(AppReplyExpert oldAppReplyExpert, AppReplyExpert appReplyExpert);
	
	/**
	 * 得到参加指定答辩的专家
	 * @param appreplyuid
	 * @param isabsent
	 * @return
	 * @author liubin
	 * @createtime 2016-11-14下午12:08:04
	 * @return List<AppReplyExpert>
	 */
	List<AppReplyExpert> getList(String appreplyuid, boolean isabsent);
}
