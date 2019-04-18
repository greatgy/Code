package com.supergenius.xo.manager.service;

import java.util.List;
import java.util.Map;

import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.manager.entity.AppReply;
import com.supergenius.xo.manager.entity.AppReplyDetail;
import com.supergenius.xo.manager.entity.AppReplyExpert;
import com.supergenius.xo.manager.entity.Certificate;
import com.supergenius.xo.manager.entity.Conference;
import com.supergenius.xo.manager.enums.EMajor;
import com.supergenius.xo.manager.enums.EReplyStage;
import com.supergenius.xo.manager.enums.EStudentLevel;
import com.supergenius.xo.user.entity.TradeDetail;

/**
 * 答辩SO
 * @author XieMing
 * @date 2016-4-29 下午3:15:33
 */
public interface AppReplySO extends BaseSO<AppReply> {

	/**
	 * 根据用户的uid和专业获取用户所有的相关记录
	 * @param majorid
	 * @param useruid
	 * @author XieMing
	 * 2016-8-11 上午10:18:44
	 */
	List<AppReply> getList(EMajor major, String useruid);

	/**
	 * 通过useruid，专业，学员申请时的等级获取一条记录
	 * @param major
	 * @param useruid
	 * @param majordomo
	 * @author XieMing
	 * 2016-8-11 下午3:29:40
	 */
	AppReply getOneByMajorAndLevel(EMajor major, String useruid, EStudentLevel majorlevel);

	/**
	 * 获取用户某个级别的所有记录
	 * @param useruid
	 * @param gaincertificate
	 * @return
	 * @author XieMing
	 * 2016-8-11 下午4:26:03
	 */
	List<AppReply> getList(String useruid, EReplyStage replystage, EStudentLevel majorlevel);
	/**
	 * 更新答辩的状态
	 * @param appReply
	 * @return
	 * @author XieMing
	 * 2016-8-12 下午2:45:00
	 */
	boolean updateReplyStage(AppReply appReply);

	/**
	 * 用户支付答辩费用
	 * @param uid
	 * @return
	 * @author XieMing
	 * 2016-8-12 下午5:34:35
	 */
	boolean updateStagePayed(AppReply appReply, TradeDetail tradeDetail);

	/**
	 * 上传材料后更新appreply的状态与增加appreplydetail记录
	 * @param appReply
	 * @author XieMing
	 * 2016-8-13 下午6:14:04
	 */
	boolean updateUploadData(EReplyStage stageFrom, AppReply appReply);

	/**
	 * 获取用户某专业最新的一条记录
	 * @param useruid
	 * @param major
	 * @return
	 * @author XieMing
	 * 2016-8-15 上午10:47:21
	 */
	AppReply getOneByMajor(String useruid, EMajor major);

	/**
	 * 根据答辩状态与专业获取一条记录
	 * @param useruid
	 * @param major
	 * @param passreport
	 * @return
	 * @author XieMing
	 * 2016-8-15 上午10:54:57
	 */
	AppReply getOneByMajorAndStage(String useruid, EMajor major, EReplyStage replystage);

	/**
	 * 根据答辩状态与专业获取该户的所有记录
	 * @param useruid
	 * @param major
	 * @param passreport
	 * @return
	 * @author XieMing
	 * 2016-8-15 上午11:09:44
	 */
	List<AppReply> getList(String useruid, EMajor major, EReplyStage replystage);

	/**
	 * 获取用户某个专业某个状态的个数
	 * @param uid
	 * @param eMajor
	 * @param passreport
	 * @return
	 * @author XieMing
	 * 2016-8-16 下午5:08:58
	 */
	int getCount(String useruid, EMajor major, EReplyStage replystage);

	/**根据用户uid与专业获取最新的一条完成的答辩
	 * @param uid
	 * @param major
	 * @return
	 * @author XieMing
	 * 2016-8-17 下午6:48:27
	 */
	AppReply getOne(String uid, EMajor major, EReplyStage replystage);
	
	/**
	 * 根据某个阶段得到list
	 * @param replyStage
	 * @return
	 * @author liubin
	 * @createtime 2016-11-9下午4:01:34
	 * @return List<AppReply>
	 */
	List<AppReply> getList(EReplyStage replyStage);

	/**
	 * 根据查询条件查询得到数量
	 * @param map
	 * @return
	 * @author liubin
	 * @createtime 2016-11-9下午7:59:06
	 * @return int
	 */
	int searchCount(Map<String, Object> map);
	
	/**
	 * 根据查询条件查询得到List
	 * @param map
	 * @return
	 * @author liubin
	 * @createtime 2016-11-9下午7:59:52
	 * @return List<AppReply>
	 */
	List<AppReply> searchList(Map<String, Object> map);
	
	/**
	 * 更新申请答辩状态和增加申请详细
	 * @param appReply
	 * @param appReplyDetail
	 * @return
	 * @author liubin
	 * @createtime 2016-11-10下午7:07:01
	 * @return boolean
	 */
	boolean update(AppReply appReply, AppReplyDetail appReplyDetail, AdminLog adminLog);
	
	/**
	 * 通过证书的uid获取一条记录
	 * @param uid
	 * @return
	 * @author XieMing
	 * 2016-11-10 下午12:03:02
	 */
	AppReply getOneByCertificate(String uid);
	
	/**
	 * 答辩审核通过，后台制定三个裁判
	 * @param appReply
	 * @param list
	 * @param appReplyDetail
	 * @param adminLog
	 * @return
	 * @author liubin
	 * @createtime 2016-11-11下午3:22:47
	 * @return boolean
	 */
	boolean update(AppReply appReply, List<AppReplyExpert> list, AppReplyDetail appReplyDetail, AdminLog adminLog);
	
	/**
	 * 研究报告通过，生成证书
	 * @param appReply
	 * @param appReplyDetail
	 * @param adminLog
	 * @param certificate
	 * @return
	 * @author liubin
	 * @createtime 2016-11-12下午6:21:17
	 * @return boolean
	 */
	boolean update(AppReply appReply, AppReplyDetail appReplyDetail, AdminLog adminLog, Certificate certificate);
	
	/**
	 * 改变状态
	 * @param appReply
	 * @param adminLog
	 * @return
	 * @author liubin
	 * @createtime 2016-11-13下午2:30:39
	 * @return boolean
	 */
	boolean update(AppReply appReply, AdminLog adminLog);
	
	/**
	 * 更改答辩状态和添加答辩明细
	 * @param appReply
	 * @param appReplyDetail
	 * @return
	 * @author liubin
	 * @createtime 2016-11-14下午6:26:31
	 * @return boolean
	 */
	boolean update(AppReply appReply, AppReplyDetail appReplyDetail);
	
	/**
	 * 更改答辩字段和添加会议室
	 * @param appReply
	 * @param conference
	 * @return
	 * @author liubin
	 * @createtime 2016-11-16下午6:46:39
	 * @return boolean
	 */
	boolean update(AppReply appReply, Conference conference);
}
