package com.supergenius.xo.manager.service;

import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;

import com.genius.model.base.entity.Pager;
import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.manager.entity.Conference;
import com.supergenius.xo.manager.entity.Judge;
import com.supergenius.xo.manager.entity.PKJudgement;
import com.supergenius.xo.manager.entity.PKSchedule;
import com.supergenius.xo.manager.entity.PKStateDetail;
import com.supergenius.xo.manager.enums.EMajor;
import com.supergenius.xo.manager.enums.EPKStage;
import com.supergenius.xo.manager.enums.EStudentLevel;
import com.supergenius.xo.user.entity.TradeDetail;
import com.supergenius.xo.user.entity.User;
import com.supergenius.xo.user.enums.EGoods;

/**
 * 挑战日程SO
 * @author XieMing
 * @date 2016-4-29 下午3:17:11
 */
public interface PkScheduleSO extends BaseSO<PKSchedule> {
	
	/**
	 * 分页获取挑战日程
	 */
	List<PKSchedule> getList(Pager pager, List<EPKStage> stages);
	
	/**
	 * 根据专业与挑战状态获取挑战列表
	 * @param pager
	 * @param major
	 * @param stage
	 * @return
	 * @author XieMing
	 * 2016-8-1 上午11:53:42
	 */
	List<PKSchedule> getList(Pager pager, EMajor major, EPKStage stage);
	
	/**
	 * 通过状态分页同时获取作为挑战者和被挑战者的list
	 * @param pager
	 * @param useruid
	 * @param stages
	 * @return
	 * @author chenminchang
	 * @create 2016-8-26下午5:31:46
	 */
	List<PKSchedule> getList(Pager pager, String useruid, List<EPKStage> stages);
	
	/**
	 * 通过状态和挑战角色分页获取list
	 * @param pager
	 * @param useruid
	 * @param stages
	 * @param isPKuser1 当获取挑战者的 true 被挑战者为false
	 * @return
	 * @author chenminchang
	 * @create 2016-8-26下午5:23:00
	 */
	List<PKSchedule> getList(Pager pager, String useruid, List<EPKStage> stages, boolean isPKuser1);
	 
	/**
	 * 支付挑战费后更新挑战的状态，所选裁判的状态，还有记录交易明细,sn为TradeDetail的sn
	 * @param pkSchedule
	 * @author XieMing
	 * 2016-8-4 下午5:55:35
	 */
	boolean updatePayStage(PKSchedule pkSchedule, TradeDetail tradeDetail, PKStateDetail pkStateDetail);
	
	/**
	 * 更新挑战阶段状态
	 * @param pkSchedule
	 * @param pkStateDetail
	 * @return
	 * @author XieMing
	 * 2016-8-5 上午10:27:32
	 */
	boolean updateStage(PKSchedule pkSchedule, PKStateDetail pkStateDetail);
	
	/**
	 * 更新pkSchedule的状态及其他字段,可以在soImpl添加多个字段
	 * @param pkSchedule
	 * @param pkStateDetail
	 * @return
	 * @author chenminchang
	 * @create 2016-9-6下午2:48:17
	 */
	boolean updateFields(PKSchedule pkSchedule, PKStateDetail pkStateDetail);

	/**
	 * 生成一条挑战记录，包括pkjudgement记录所选的judge
	 * @param pkSchedule
	 * @param pkjudges
	 * @return
	 * @author XieMing
	 * 2016-8-6 下午2:23:09
	 */
	boolean add(PKSchedule pkSchedule, String pkjudges);

	/**
	 * 查看用户发起的挑战是否有还未缴费的
	 * @param major
	 * @param unpay
	 * @return
	 * @author XieMing
	 * 2016-8-9 下午4:30:02
	 */
	PKSchedule getOneByMajorAndStage(String uid, EMajor major, EPKStage stage);
	
	/**
	 * 根据orderGoodsSN,major,stage得到PKSchedule对象
	 * @param orderGoodsSN
	 * @param major
	 * @param stage
	 * @return
	 */
	PKSchedule getOne(String orderGoodsSN, EMajor major, EPKStage stage);

	/**
	 * 获取裁判参加过挑战的场数
	 * @param useruid
	 * @param sn
	 * @author XieMing
	 * 2016-8-18 下午4:24:36
	 */
	int getCount(String useruid, String sn);

	/**
	 * 用户买挑战门票更新计数
	 * @param pkSchedule
	 * @param count
	 * @return
	 * @author XieMing
	 * 2016-8-29 下午7:12:51
	 */
	boolean update(PKSchedule pkSchedule, int count);
	
	/**
	 * 更新挑战题目
	 * @param uid
	 * @param filepath
	 * @return
	 * @author chenminchang
	 * @create 2016年11月24日下午12:38:29
	 */
	boolean updateFile(String uid, String file);

	/**
	 * 获取该专业下最新的一条记录
	 * @param uid
	 * @param major
	 * @author XieMing
	 * 2016-9-1 上午11:11:01
	 */
	PKSchedule getOne(String uid, EMajor major);
	
	/**
	 * 通过sn，major和多种状态来得到挑战日程list
	 * @param orderGoodsSN
	 * @param major
	 * @param stages
	 * @author liubin
	 * @createtime 2016-9-2下午4:46:03
	 * @return List<PKSchedule>
	 */
	List<PKSchedule> getList(String orderGoodsSN, EMajor major, List<EPKStage> stages);

	/**
	 * 表的级联查询得到我购买视频相关状态列表的数量
	 * @param userUid
	 * @param type
	 * @param stages
	 * @return
	 * @author liubin
	 * @createtime 2016-9-2下午6:50:52
	 * @return int
	 */
	int getCountByOrderGoods(String userUid, EGoods type, List<EPKStage> stages);
	
	/**
	 * 表的级联查询得到我购买视频相关状态的数量
	 * @param userUid
	 * @param type
	 * @param stage
	 * @return
	 * @author liubin
	 * @createtime 2016-9-2下午6:57:08
	 * @return int
	 */
	int getCountByOrderGoods(String userUid, EGoods type, EPKStage stage);
	
	/**
	 * 表的级联查询得到我购买视频相关状态的list
	 * @param userUid
	 * @param type
	 * @param stage
	 * @return
	 * @author liubin
	 * @createtime 2016-9-2下午7:33:05
	 * @return List<PKSchedule>
	 */
	List<PKSchedule> getListByOrderGoods(Map<String, Object> map);
	
	/**
	 * 返回挑战费用
	 * @param user
	 * @param pkSchedule
	 * @param order
	 * @param tradeDetail
	 * @return
	 * @author chenminchang
	 * @create 2016-9-5下午7:53:51
	 */
	boolean updateRetuenPKFee(User user, PKSchedule pkSchedule, TradeDetail tradeDetail, PKStateDetail pkStateDetail);
	
	/**
	 * 支付挑战费用
	 * @param user
	 * @param pkSchedule
	 * @param order
	 * @param tradeDetail
	 * @return
	 * @author chenminchang
	 * @create 2016-9-5下午7:53:44
	 */
	boolean updatePayPKFee(User user, PKSchedule pkSchedule, TradeDetail tradeDetail, PKStateDetail pkStateDetail);

	/**
	 * 裁判接受后的相关操作
	 * @param pkSchedule
	 * @param pkStageDetail
	 * @param uid
	 * @author XieMing
	 * 2016-9-13 下午6:22:46
	 */
	boolean updateJudgeAccept(PKSchedule pkSchedule, PKStateDetail pkStageDetail, String judgementuid);
	
	/**
	 * 裁判拒绝后的相关操作
	 * @param pkSchedule
	 * @param pkStageDetail
	 * @param uid
	 * @author XieMing
	 * 2016-9-13 下午6:22:46
	 */
	boolean updateJudgeRefuse(PKSchedule pkSchedule, PKStateDetail pkStageDetail, String judgementuid);
	
	/**
	 * 得到指定阶段的挑战总数
	 * @param stages
	 * @return
	 * @author liubin
	 * @createtime 2016-10-20下午2:42:42
	 * @return int
	 */
	int getCount(List<EPKStage> stages);
	
	/**
	 * 根据条件筛选查询count
	 * @return
	 * @author liubin
	 * @createtime 2016-10-25下午12:28:01
	 * @return int
	 */
	int getCountBySearch(Map<String, Object> map);
	
	/**
	 * 根据条件筛选查询list
	 * @param map
	 * @return
	 * @author liubin
	 * @createtime 2016-10-25下午12:29:17
	 * @return List<PKSchedule>
	 */
	List<PKSchedule> getListBySearch(Map<String, Object> map);
	
	/**
	 * 更新裁判和状态信息
	 * @param pkSchedule
	 * @param pkStateDetail
	 * @return
	 * @author liubin
	 * @createtime 2016-11-2下午8:45:34
	 * @return boolean
	 */
	boolean update(PKSchedule pkSchedule, PKStateDetail pkStateDetail);
	
	/**
	 * 更新后台操作挑战相关信息
	 * @param pkSchedule
	 * @param adminLog
	 * @return
	 * @author liubin
	 * @createtime 2016-11-4下午12:31:25
	 * @return boolean
	 */
	boolean update(PKSchedule pkSchedule, AdminLog adminLog);
	
	/**
	 * 得到学员某专业同一级别上的list
	 * @param pkuseruid
	 * @param major
	 * @param level
	 * @return
	 * @author liubin
	 * @createtime 2016-11-4下午8:09:35
	 * @return List<PKSchedule>
	 */
	List<PKSchedule> getList(String pkuseruid, EMajor major, EStudentLevel level, List<EPKStage> stages);
	
	/**
	 * 得到学员某专业同一级别上的list
	 * @param pkuseruid
	 * @param major
	 * @param level
	 * @param stages
	 * @return
	 * @author liubin
	 * @createtime 2016年12月1日下午3:57:38
	 * @return List<PKSchedule>
	 */
	List<PKSchedule> getListByPKUser2(String pkuseruid2, EMajor major, EStudentLevel level, List<EPKStage> stages);
	
	/**
	 * 挑战结束给裁判的奖励更新
	 * @param pkSchedule
	 * @param user
	 * @param judge
	 * @param tradeDetail
	 * @return
	 * @author liubin
	 * @createtime 2016-11-5下午3:15:02
	 * @return boolean
	 */
	boolean update(PKSchedule pkSchedule, User user, Judge judge, TradeDetail tradeDetail, PKJudgement pkJudgement);
	
	/**
	 * 分页得到裁判中含有某个uid的List<PKSchedule>
	 * @param pager
	 * @param judgementuid
	 * @param stages
	 * @return
	 * @author liubin
	 * @createtime 2016-11-8上午10:28:05
	 * @return List<PKSchedule>
	 */
	List<PKSchedule> getListByAllJudgementuid(Pager pager, String judgementuid, EMajor major, List<EPKStage> stages, DateTime createtime);
	
	/**
	 * 创建qq会议室
	 * @param pkSchedule
	 * @param conference
	 * @param confeMember
	 * @return
	 * @author liubin
	 * @createtime 2016-11-16下午3:50:06
	 * @return boolean
	 */
	boolean update(PKSchedule pkSchedule, PKStateDetail pkStateDetail, Conference conference);
	
	/**
	 * 后台更新挑战状态
	 * @param pkSchedule
	 * @param pkStateDetail
	 * @param adminLog
	 * @return
	 * @author liubin
	 * @createtime 2016年11月23日下午3:39:04
	 * @return boolean
	 */
	boolean update(PKSchedule pkSchedule, PKStateDetail pkStateDetail, AdminLog adminLog);
	
	/**
	 * 更新挑战者失败
	 * @param pkSchedule
	 * @param pkStateDetail
	 * @param user
	 * @param judge
	 * @param tradeDetail
	 * @return
	 * @author liubin
	 * @createtime 2016年11月29日下午3:31:27
	 * @return boolean
	 */
	boolean update(PKSchedule pkSchedule, PKStateDetail pkStateDetail, User user, TradeDetail tradeDetail);
	
	/**
	 * 更新挑战者裁判
	 * @param pkSchedule
	 * @param pkStateDetail
	 * @param list
	 * @return
	 * @author liubin
	 * @createtime 2016年12月1日下午5:14:10
	 * @return boolean
	 */
	boolean update(PKSchedule pkSchedule, PKStateDetail pkStateDetail, List<PKJudgement> list);
}

