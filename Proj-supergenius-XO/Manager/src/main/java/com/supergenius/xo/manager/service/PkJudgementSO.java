package com.supergenius.xo.manager.service;

import java.util.List;

import com.genius.model.base.entity.Pager;
import com.genius.model.base.enums.EStatus;
import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.manager.entity.PKJudgement;
import com.supergenius.xo.manager.enums.EStudentLevel;

/**
 * 挑战的裁判(各选5个)SO
 * @author XieMing
 * @date 2016-4-29 下午3:17:01
 */
public interface PkJudgementSO extends BaseSO<PKJudgement> {

	/**
	 * 挑战者交费后更新PKJudgement的状态为start
	 * @param pkJudgement
	 * @return
	 * @author XieMing
	 * 2016-8-1 下午8:13:10
	 */
	boolean updateStatus(PKJudgement pkJudgement);

	/**
	 * 用户缴费后根据pkschedule的uid获取对应的裁判，并更新他们的状态
	 * @param pkscheduleuid
	 * @param uid
	 * @author XieMing
	 * 2016-8-4 下午8:06:34
	 */
	boolean updateList(String pkscheduleuid, EStatus status);
	
	/**
	 * 用户缴费后根据pkschedule的uid获取对应的裁判
	 * @param pkscheduleuid
	 * @return
	 * @author XieMing
	 * 2016-8-4 下午8:19:04
	 */
	List<PKJudgement> getListByPkuid(String pkscheduleuid);
	
	/**
	 * 根据邀请人uid和pkuid获取裁判
	 * @param useruid
	 * @param pkscheduleuid
	 * @return
	 * @author chenminchang
	 * @create 2016-8-26上午10:16:12
	 */
	List<PKJudgement> getListByPkuid(String useruid, String pkscheduleuid);
	
	/**
	 * 根据邀请人uid和pkuid获取为操作的裁判
	 * @param useruid
	 * @param pkscheduleuid
	 * @return
	 * @author XieMing
	 * 2016年11月24日 下午8:45:49
	 */
	List<PKJudgement> getListByPkuidAndUseruidWait(String useruid, String pkscheduleuid);

	/**
	 * 分页获取用户所有（不分专业）的被邀请做裁判的记录
	 * @param judgeuids
	 * @param pager
	 * @return
	 * @author XieMing
	 * 2016-8-21 下午5:32:26
	 */
	List<PKJudgement> getList(String judgeuid, Pager pager, List<EStatus> statuList);

	/**
	 * 裁判接受邀请后更新对应的pkJudgement pkSchedule pkStateDetail
	 * @param uid
	 * @return
	 * @author XieMing
	 * 2016-8-23 下午6:20:33
	 */
	boolean updateAccept(String uid);
	
	/**
	 * 根据裁判useruid和邀请者useruid和挑战uid获取一条数据
	 * @param useruid
	 * @param judgeuseruid
	 * @param pkscheduleuid
	 * @return
	 * @author chenminchang
	 * @create 2016-10-10上午10:06:41
	 */
	PKJudgement getOne(String useruid, String judgementuid, String pkscheduleuid);
	
	/**
	 * 根据级别得到裁判缺席和没有缺席的次数
	 * @param judgementuid
	 * @param pklevel
	 * @return
	 * @author liubin
	 * @createtime 2016-11-5下午12:42:02
	 * @return int
	 */
	int getCount(String judgementuid, EStudentLevel pklevel, boolean isabsent);
	
	/**
	 * 根据裁判uid和挑战uid得到参加该挑战的PKJudgement对象
	 * @param judgementuid
	 * @param pkscheduleuid
	 * @return
	 * @author liubin
	 * @createtime 2016-11-5下午3:38:13
	 * @return PKJudgement
	 */
	PKJudgement getOne(String judgementuid, String pkscheduleuid);

	/**
	 * 根据挑战的uid获取等待处理的裁判的列表
	 * @param uid
	 * @return
	 * @author XieMing
	 * 2016年11月24日 下午8:58:00
	 */
	List<PKJudgement> getListByPkuidWait(String uid);
}
