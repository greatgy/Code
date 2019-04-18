package com.supergenius.xo.manager.service;

import java.util.List;
import java.util.Map;

import com.genius.model.base.entity.Pager;
import com.genius.model.base.enums.EStatus;
import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.manager.entity.AppExpert;
import com.supergenius.xo.manager.entity.Certificate;
import com.supergenius.xo.manager.entity.Expert;
import com.supergenius.xo.manager.entity.UserLevel;
import com.supergenius.xo.manager.enums.EAppExpertStage;
import com.supergenius.xo.manager.enums.EExpert;
import com.supergenius.xo.manager.enums.EExpertLevel;
import com.supergenius.xo.manager.enums.EMajor;
import com.supergenius.xo.user.entity.User;

/** 
 * 专家SO
 * @author guanshiqian
 * @date 2016-4-28 下午6:19:42 
 */
public interface ExpertSO extends BaseSO<Expert> {

	/**
	 * 通过专业获取专家
	 * @param major
	 * @return
	 */
	List<Expert> getListByMajor(EMajor major);
	
	/**
	 * 
	 * 通过专家等级获取专家
	 * @param level
	 * @return
	 */
	List<Expert> getListByLevel(EExpertLevel level);
	
	/**
	 * 通过type获取专家
	 * @param type
	 * @return
	 */
	List<Expert> getListByType(EExpert type);
	
	/**
	 * 得到所有专家根据评判场次倒排序
	 * @param page
	 * @return
	 */
	List<Expert> getListOrderByCount(Pager page);
	
	/**
	 * 根据搜索条件得到总数
	 * @param map
	 * @return
	 */
	int searchCount(Map<String, Object> map);
	
	/**
	 * 根据搜索条件得到list
	 * @param map
	 * @return
	 */
	List<Expert> search(Map<String, Object> map);
	
	/**
	 * 通过useruid和major得到一个expert对象
	 * @param userUid
	 * @param major
	 * @author liubin
	 * @createtime 2016-8-17下午3:10:00
	 * @return Expert
	 */
	Expert getOne(String userUid, EMajor major);
	
	/**
	 * 更新Expert，AppExpert的状态
	 * @param expert
	 * @param appExpert
	 * @author liubin
	 * @createtime 2016-8-21下午6:08:08
	 * @return boolean
	 */
	boolean update(Expert expert, AppExpert appExpert, String name, EAppExpertStage stagefrom, EAppExpertStage stageto);
	
	/**
	 * 获得专家list
	 * @param useruid
	 * @author liubin
	 * @createtime 2016-8-22下午7:17:30
	 * @return List<Expert>
	 */
	List<Expert> getList(String userUid);
	
	/**
	 * 获得状态为end的Expert
	 * @param userUid
	 * @param major
	 * @author liubin
	 * @createtime 2016-8-23下午12:28:48
	 * @return Expert
	 */
	Expert getOne(String userUid, EMajor major, EStatus status);
	
	/**
	 * 根据状态得到专家List
	 * @param userUid
	 * @param major
	 * @param status
	 * @author liubin
	 * @createtime 2016-8-26下午5:43:48
	 * @return List<Expert>
	 */
	List<Expert> getListByStatus(String userUid, EMajor major, EStatus... status);
	
	/**
	 * 根据useruid，majoe和level得到专家对象
	 * @param userUid
	 * @param major
	 * @param level
	 * @author liubin
	 * @createtime 2016-8-26下午6:33:43
	 * @return Expert
	 */
	Expert getOne(String userUid, EMajor major, EExpertLevel level);
	
	/**
	 * 根据useruid,majoe和多个status得到专家对象
	 * @param userUid
	 * @param major
	 * @param level
	 * @param status
	 * @author liubin
	 * @createtime 2016-8-28下午6:07:16
	 * @return Expert
	 */
	Expert getOneByStatus(String userUid, EMajor major, EStatus... status);
	
	/**
	 * 根据useruid,majoe,type和多个status得到专家对象
	 * @param userUid
	 * @param major
	 * @param type
	 * @param status
	 * @author liubin
	 * @createtime 2016-8-28下午6:07:16
	 * @return Expert
	 */
	Expert getOneByStatus(String userUid, EMajor major, EExpert type, EStatus... status);
	
	/**
	 * 根据userUid,level得到专家List
	 * @param userUid
	 * @param major
	 * @param level
	 * @author liubin
	 * @createtime 2016-8-29下午2:13:29
	 * @return List<Expert>
	 */
	List<Expert> getList(String userUid, EExpertLevel level);
	
	/**
	 * 根据uid和状态得到专家
	 * @param expertUid
	 * @return
	 * @author liubin
	 * @createtime 2016-9-6下午4:52:19
	 * @return Expert
	 */
	Expert getOne(String expertUid, EStatus status);

	/**
	 * 获取某个级别的裁判个数
	 * @param expert
	 * @return
	 * @author XieMing
	 * 2016-10-22 下午6:27:49
	 */
	int getCount(EExpertLevel level);

	/**
	 * 获取某个状态的裁判的个数
	 * @param end
	 * @return
	 * @author XieMing
	 * 2016-10-22 下午6:27:54
	 */
	int getCount(EStatus status);

	/**
	 * 后台更新专家
	 * @param expert1
	 * @param adminLog
	 * @return
	 * @author XieMing
	 * 2016-11-3 上午11:37:54
	 */
	boolean update(Expert expert, AdminLog adminLog);

	/**
	 * 后台变更专家级别
	 * @param expert
	 * @param certificate
	 * @param adminLog
	 * @param userLevel
	 * @author XieMing
	 * 2016-11-3 下午8:23:00
	 */
	boolean add(Expert expert, Certificate certificate, AdminLog adminLog, UserLevel userLevel, User user);

	/**
	 * 管理后台设置会员为专家
	 * @param user
	 * @param expert
	 * @param certificate
	 * @param adminLog
	 * @return
	 * @author XieMing
	 * 2016-11-7 下午12:04:42
	 */
	boolean update(User user, Expert expert, Certificate certificate, AdminLog adminLog);

	/**
	 * 获得专职专家的个数
	 * @param fullExpertCount
	 * @return
	 * @author XieMing
	 * 2016-11-8 下午3:11:11
	 */
	int getFullCount(int count);

}
