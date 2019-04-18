package com.supergenius.xo.manager.service;

import java.util.List;
import java.util.Map;

import com.genius.model.base.enums.EStatus;
import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.manager.entity.Certificate;
import com.supergenius.xo.manager.entity.Judge;
import com.supergenius.xo.manager.entity.UserLevel;
import com.supergenius.xo.manager.enums.EJudge;
import com.supergenius.xo.manager.enums.EMajor;
import com.supergenius.xo.user.entity.User;

/** 
 * 裁判SO
 * @author guanshiqian
 * @date 2016-4-28 上午11:44:26 
 */
public interface JudgeSO extends BaseSO<Judge> {

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
	List<Judge> search(Map<String, Object> map);
	
	/**
	 * 根据裁判sn得到一个裁判
	 * @param sn
	 * @return
	 * @author XieMing
	 * 2016-8-1 上午11:00:07
	 */
	Judge getOneBySn(String sn);

	/**
	 * 根据用户uid与专业获取对应的裁判记录
	 * @param major
	 * @param useruid
	 * @return
	 * @author XieMing
	 * 2016-8-18 下午3:45:56
	 */
	Judge getOne(EMajor major, String useruid, EStatus status);

	/**
	 * 根据用户的uid获取对应的所有的裁判
	 * @param uid
	 * @author XieMing
	 * 2016-8-19 下午5:32:29
	 */
	List<Judge> getList(String useruid);

	/**
	 * 根据用户uid和专业获取裁判列表
	 * @param useruid
	 * @param major
	 * @return
	 * @author XieMing
	 * 2016-8-21 下午5:23:35
	 */
	List<Judge> getList(String useruid, EMajor major);

	/**
	 * 更新裁判状态
	 * @param judge
	 * @author XieMing
	 * 2016-8-22 下午3:22:21
	 */
	boolean updateStatus(Judge judge);

	/**
	 * 得到某个类型裁判的数量
	 * @param judgment
	 * @return
	 * @author XieMing
	 * 2016-10-21 下午12:33:25
	 */
	int getCount(EJudge judgment);
	
	/**
	 * 得到某个状态的裁判的数量
	 * @param judgment
	 * @return
	 * @author XieMing
	 * 2016-10-21 下午12:41:50
	 */
	int getCount(EStatus status);
	
	/**
	 * 根据查询条件得到状态为enable的数量
	 * @param map
	 * @return
	 * @author liubin
	 * @createtime 2016-11-2下午4:35:19
	 * @return int
	 */
	int getQueryCount(Map<String, Object> map);
	
	/**
	 * 根据查询条件得到状态为enable的List
	 * @param map
	 * @return
	 * @author liubin
	 * @createtime 2016-11-2下午4:35:34
	 * @return List<Judge>
	 */
	List<Judge> getQueryList(Map<String, Object> map);
	
	/**
	 * 根据userUid得到对象
	 * @param uid
	 * @return
	 * @author liubin
	 * @createtime 2016-11-2下午5:01:11
	 * @return Judge
	 */
	Judge getOne(String userUid);
	
	/**
	 * 裁判面试通过添加裁判并创建证书（状态：未颁发）
	 * @param judge
	 * @param certificate
	 * @author XieMing
	 * 2016-11-2 上午11:03:09
	 */
	boolean add(Judge judge, Certificate certificate, UserLevel userLevel, User user);
	
	/**
	 * 根据专业和会员uid得到裁判
	 * @param userUid
	 * @param major
	 * @return
	 * @author liubin
	 * @createtime 2016-11-5下午1:13:19
	 * @return Judge
	 */
	Judge getOne(String userUid, EMajor major);

	/**
	 * 后台更改裁判状态等字段
	 * @param judge1
	 * @param adminLog
	 * @author XieMing
	 * 2016-11-3 上午11:42:12
	 */
	boolean update(Judge judge, AdminLog adminLog);

	/**
	 * 管理后台设置会员为裁判
	 * @param user
	 * @param judge
	 * @param certificate
	 * @param adminLog
	 * @return
	 * @author XieMing
	 * 2016-11-7 下午12:00:46
	 */
	boolean update(User user, Judge judge, Certificate certificate, AdminLog adminLog);

	/**
	 * 获取专职裁判的数量
	 * @return
	 * @author XieMing
	 * 2016-11-7 下午3:17:39
	 */
	int getFullJudgeCount(int judgecount);
}
