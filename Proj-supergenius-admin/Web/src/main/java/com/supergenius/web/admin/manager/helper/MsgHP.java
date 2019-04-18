package com.supergenius.web.admin.manager.helper;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.genius.server.base.helper.BaseHP;
import com.supergenius.global.conf.SysConf;
import com.supergenius.global.conf.WebConf;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.manager.enums.EExpertLevel;
import com.supergenius.xo.manager.enums.EMajor;
import com.supergenius.xo.manager.enums.EMsg;
import com.supergenius.xo.manager.enums.EMsgGroup;

/** 
 * manager后台消息HP,消息都在这里发送
 * @author chenminchang
 * @date 2016-7-29 下午3:12:15 
 */
public class MsgHP extends BaseHP {
	
	private static Logger log = LoggerFactory.getLogger(MsgHP.class);
	/**
	 * 文章被审核后发表获得积分发送消息
	 * @param title
	 * @param href
	 * @param score
	 * @param adminuid
	 * @param touseruid
	 * @param typegroup
	 * @param type
	 * @return
	 * @author chenminchang
	 */
	public static boolean sendFinanceMsg(String title, String href, int score, String adminuid, String touseruid, EMsgGroup typegroup, EMsg type) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.adminuid, adminuid);
		map.put(MapperDict.touseruid, touseruid);
		map.put(MapperDict.typegroup, typegroup);
		map.put(MapperDict.type, type);
		map.put(MapperDict.title, title);
		map.put(MapperDict.href, href);
		map.put(MapperDict.score, score);
		return InboxHP.sendInbox(map);
	}
	
	/**
	 * 申请学员成功或失败发送消息
	 * @param typegroup
	 * @param type
	 * @param title
	 * @param touseruid
	 * @param adminuid
	 * @param major
	 * @return
	 * @author chenminchang
	 * @create 2016-10-24下午8:17:30
	 */
	public static boolean sendAppStudentMsg(EMsgGroup typegroup, EMsg type, String title, String href, String touseruid, String adminuid, EMajor major) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.adminuid, adminuid);
		map.put(MapperDict.touseruid, touseruid);
		map.put(MapperDict.typegroup, typegroup);
		map.put(MapperDict.type, type);
		map.put(MapperDict.title, title);
		map.put(MapperDict.href, href);
		map.put(MapperDict.content, major.getName());
		return InboxHP.sendInbox(map);
	}

	/**
	 * 裁判申请没有通过
	 * @param major
	 * @param adminid
	 * @param uid
	 * @param reason
	 * @author XieMing
	 * 2016-11-2 上午11:29:38
	 * @return 
	 */
	public static boolean sendAppJudgeNoPass(EMajor major, String adminuid, String touseruid, String username, String reason) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.adminuid, adminuid);
		map.put(MapperDict.touseruid, touseruid);
		map.put(MapperDict.typegroup, EMsgGroup.notify);
		map.put(MapperDict.type, EMsg.judgeAppNotPass);
		map.put(MapperDict.title, username + SysConf.JudgeAppNotPassTitle);
		map.put(MapperDict.href, WebConf.baseManagerPath + SysConf.MyJudgePath);
		map.put(MapperDict.content, major.getName());
		map.put(MapperDict.cause, reason);
		return InboxHP.sendInbox(map);
	}
	
	/**
	 * 指定主裁判发送信息
	 * @param typegroup
	 * @param type
	 * @param title
	 * @param href
	 * @param touseruid
	 * @param adminuid
	 * @param major
	 * @return
	 * @author liubin
	 * @createtime 2016-11-3上午10:33:08
	 * @return boolean
	 */
	public static boolean sendPKSpecifyChairJudgementMsg(EMsgGroup typegroup, EMsg type, String title, String href, String touseruid, String adminuid, String content) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.adminuid, adminuid);
		map.put(MapperDict.touseruid, touseruid);
		map.put(MapperDict.typegroup, typegroup);
		map.put(MapperDict.type, type);
		map.put(MapperDict.title, title);
		map.put(MapperDict.href, href);
		map.put(MapperDict.content, content);
		return InboxHP.sendInbox(map);
	}
	
	/**
	 * 裁判申请通过
	 * @param major
	 * @param adminid
	 * @param uid
	 * @param reason
	 * @author XieMing
	 * 2016-11-2 上午11:29:38
	 * @return 
	 */
	public static boolean sendAppJudgePass(EMajor major, String adminuid, String touseruid, String username) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.adminuid, adminuid);
		map.put(MapperDict.touseruid, touseruid);
		map.put(MapperDict.typegroup, EMsgGroup.notify);
		map.put(MapperDict.type, EMsg.judgeAppPass);
		map.put(MapperDict.title, username + SysConf.JudgeAppPassTitle);
		map.put(MapperDict.href, WebConf.baseManagerPath + SysConf.MyJudgePath);
		map.put(MapperDict.content, major.getName());
		return InboxHP.sendInbox(map);
	}
	
	/**
	 * 裁判面试没有通过
	 * @param major
	 * @param adminid
	 * @param uid
	 * @param reason
	 * @author XieMing
	 * 2016-11-2 上午11:29:38
	 * @return 
	 */
	public static boolean sendJudgeInterviewNoPass(EMajor major, String adminuid, String touseruid, String username, String reason) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.adminuid, adminuid);
		map.put(MapperDict.touseruid, touseruid);
		map.put(MapperDict.typegroup, EMsgGroup.notify);
		map.put(MapperDict.type, EMsg.judgeInterviewNotPass);
		map.put(MapperDict.title, username + SysConf.JudgeInterviewNotPassTitle);
		map.put(MapperDict.href, WebConf.baseManagerPath + SysConf.MyJudgePath);
		map.put(MapperDict.content, major.getName());
		map.put(MapperDict.cause, reason);
		return InboxHP.sendInbox(map);
	}
	
	/**
	 * 裁判面试通过
	 * @param major
	 * @param adminid
	 * @param uid
	 * @param reason
	 * @author XieMing
	 * 2016-11-2 上午11:29:38
	 * @return 
	 */
	public static boolean sendJudgeInterviewPass(EMajor major, String adminuid, String touseruid, String username) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.adminuid, adminuid);
		map.put(MapperDict.touseruid, touseruid);
		map.put(MapperDict.typegroup, EMsgGroup.notify);
		map.put(MapperDict.type, EMsg.judgeInterviewPass);
		map.put(MapperDict.title, username + SysConf.JudgeInterviewPassTitle);
		map.put(MapperDict.href, WebConf.baseManagerPath + SysConf.MyJudgePath);
		map.put(MapperDict.content, major.getName());
		return InboxHP.sendInbox(map);
	}
	
	/**
	 * 裁判题目审核不通过消息
	 * @param major
	 * @param adminuid
	 * @param touseruid
	 * @param username
	 * @param reason
	 * @param count
	 * @return
	 * @author XieMing
	 * 2016-11-2 下午5:13:50
	 */
	public static boolean sendCheckNoPass(EMajor major, String adminuid, String touseruid, String username, String reason, int count) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.adminuid, adminuid);
		map.put(MapperDict.touseruid, touseruid);
		map.put(MapperDict.typegroup, EMsgGroup.notify);
		map.put(MapperDict.type, EMsg.fileNotPass);
		map.put(MapperDict.title, username + SysConf.JudgeCheckNotPassTitle);
		map.put(MapperDict.href, WebConf.baseManagerPath + SysConf.MyJudgePath);
		map.put(MapperDict.content, major.getName());
		map.put(MapperDict.count, count);
		map.put(MapperDict.cause, reason);
		return InboxHP.sendInbox(map);
	}
	
	/**
	 * 裁判题目审核通过消息
	 * @param major
	 * @param adminuid
	 * @param touseruid
	 * @param username
	 * @param time
	 * @return
	 * @author XieMing
	 * 2016-11-2 下午5:14:04
	 */
	public static boolean sendCheckPass(EMajor major, String adminuid, String touseruid, String username, String time) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.adminuid, adminuid);
		map.put(MapperDict.touseruid, touseruid);
		map.put(MapperDict.typegroup, EMsgGroup.notify);
		map.put(MapperDict.type, EMsg.filePass);
		map.put(MapperDict.title, username + SysConf.JudgeCheckPassTitle);
		map.put(MapperDict.href, WebConf.baseManagerPath + SysConf.MyJudgePath);
		map.put(MapperDict.content, major.getName());
		map.put(MapperDict.time, time.substring(0, 10));
		return InboxHP.sendInbox(map);
	}
	
	/**
	 * 更改专家级别
	 * @param major
	 * @param adminuid
	 * @param touseruid
	 * @param level
	 * @return
	 * @author XieMing
	 * 2016-11-3 下午8:25:23
	 */
	public static boolean sendExpertLevel(EMajor major, String adminuid, String touseruid, EExpertLevel level) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.adminuid, adminuid);
		map.put(MapperDict.touseruid, touseruid);
		map.put(MapperDict.typegroup, EMsgGroup.notify);
		map.put(MapperDict.type, EMsg.specialExp);
		map.put(MapperDict.title, SysConf.ChangeExpertLevelTitle);
		map.put(MapperDict.href, WebConf.baseManagerPath + SysConf.MyExpertPath);
		map.put(MapperDict.content, major.getName());
		map.put(MapperDict.level, level.getName());
		return InboxHP.sendInbox(map);
	}

	/**
	 * 专家申请通过
	 * @param major
	 * @param adminUid
	 * @param uid
	 * @param showname
	 * @author XieMing
	 * 2016-11-4 下午1:34:28
	 */
	public static boolean sendAppExpertPass(EMajor major, String adminUid, String touseruid, String username, EExpertLevel level) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.adminuid, adminUid);
		map.put(MapperDict.touseruid, touseruid);
		map.put(MapperDict.typegroup, EMsgGroup.notify);
		map.put(MapperDict.type, EMsg.expAppPass);
		map.put(MapperDict.title, username + SysConf.ExpertAppPassTitle);
		map.put(MapperDict.href, WebConf.baseManagerPath + SysConf.MyExpertPath);
		map.put(MapperDict.content, major.getName());
		map.put(MapperDict.level, level.getName());
		return InboxHP.sendInbox(map);
		
	}

	/**
	 * 专家申请没有通过
	 * @param major
	 * @param adminUid
	 * @param uid
	 * @param showname
	 * @param reason
	 * @author XieMing
	 * 2016-11-4 下午1:34:33
	 */
	public static boolean sendAppExpertNoPass(EMajor major, String adminUid, String touseruid, String username, String reason, EExpertLevel level) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.adminuid, adminUid);
		map.put(MapperDict.touseruid, touseruid);
		map.put(MapperDict.typegroup, EMsgGroup.notify);
		map.put(MapperDict.type, EMsg.expAppNotPass);
		map.put(MapperDict.title, username + SysConf.ExpertAppNotPassTitle);
		map.put(MapperDict.href, WebConf.baseManagerPath + SysConf.MyExpertPath);
		map.put(MapperDict.content, major.getName());
		map.put(MapperDict.cause, reason);
		map.put(MapperDict.level, level.getName());
		return InboxHP.sendInbox(map);
		
	}

	/**
	 * 专家研究报告审核通过
	 * @param major
	 * @param adminUid
	 * @param uid
	 * @param showname
	 * @param time
	 * @author XieMing
	 * 2016-11-4 下午4:38:54
	 */
	public static boolean sendExpertCheckPass(EMajor major, String adminUid, String touseruid, String username, String time, EExpertLevel level) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.adminuid, adminUid);
		map.put(MapperDict.touseruid, touseruid);
		map.put(MapperDict.typegroup, EMsgGroup.notify);
		map.put(MapperDict.type, EMsg.expReportPass);
		map.put(MapperDict.title, username + SysConf.ExpertCheckPassTitle);
		map.put(MapperDict.href, WebConf.baseManagerPath + SysConf.MyExpertPath);
		map.put(MapperDict.content, major.getName());
		map.put(MapperDict.time, time.substring(0, 10));
		map.put(MapperDict.level, level.getName());
		log.debug("inbox content ----------------------------------------------------" + major + "---" + adminUid + "---" + touseruid + "---" + username + "---" + time + "---" + level);
		return InboxHP.sendInbox(map);
	}
	
	/**
	 * 专家研究报告审核不通过
	 * @param major
	 * @param adminUid
	 * @param uid
	 * @param showname
	 * @param reason
	 * @author XieMing
	 * 2016-11-4 下午4:38:57
	 */
	public static boolean sendExpertCheckNoPass(EMajor major, String adminUid, String touseruid, String username, String reason, EExpertLevel level) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.adminuid, adminUid);
		map.put(MapperDict.touseruid, touseruid);
		map.put(MapperDict.typegroup, EMsgGroup.notify);
		map.put(MapperDict.type, EMsg.expReportNotPass);
		map.put(MapperDict.title, username + SysConf.ExpertCheckNotPassTitle);
		map.put(MapperDict.href, WebConf.baseManagerPath + SysConf.MyExpertPath);
		map.put(MapperDict.content, major.getName());
		map.put(MapperDict.cause, reason);
		map.put(MapperDict.level, level.getName());
		return InboxHP.sendInbox(map);
	}

	/**
	 * 专家面试通过
	 * @param major
	 * @param adminUid
	 * @param uid
	 * @param showname
	 * @param levelto
	 * @author XieMing
	 * 2016-11-4 下午5:36:53
	 */
	public static boolean sendExpertInterviewPass(EMajor major, String adminUid, String touseruid, String username, EExpertLevel levelto) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.adminuid, adminUid);
		map.put(MapperDict.touseruid, touseruid);
		map.put(MapperDict.typegroup, EMsgGroup.notify);
		map.put(MapperDict.type, EMsg.becomeExp);
		map.put(MapperDict.title, username + SysConf.ExpertInterviewPassTitle);
		map.put(MapperDict.href, WebConf.baseManagerPath + SysConf.MyJudgePath);
		map.put(MapperDict.content, major.getName());
		return InboxHP.sendInbox(map);
	}

	/**
	 * 专家面试不通过
	 * @param major
	 * @param adminUid
	 * @param uid
	 * @param showname
	 * @param reason
	 * @param levelto
	 * @author XieMing
	 * 2016-11-4 下午5:37:01
	 */
	public static boolean sendExpertInterviewNoPass(EMajor major, String adminUid, String touseruid, String username, String reason, EExpertLevel levelto) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.adminuid, adminUid);
		map.put(MapperDict.touseruid, touseruid);
		map.put(MapperDict.typegroup, EMsgGroup.notify);
		map.put(MapperDict.type, EMsg.expInterviewNotPass);
		map.put(MapperDict.title, username + SysConf.ExpertInterviewNotPassTitle);
		map.put(MapperDict.href, WebConf.baseManagerPath + SysConf.MyExpertPath);
		map.put(MapperDict.content, major.getName());
		map.put(MapperDict.cause, reason);
		map.put(MapperDict.level, levelto.getName());
		return InboxHP.sendInbox(map);
	}
	
	/**
	 * 举报失败，给举报者发送信息
	 * @param adminUid
	 * @param touseruid
	 * @param content
	 * @return
	 * @author liubin
	 * @createtime 2016-11-8下午1:56:46
	 * @return boolean
	 */
	public static boolean sendCompliantNoPass(String adminUid, String touseruid, String content) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.adminuid, adminUid);
		map.put(MapperDict.touseruid, touseruid);
		map.put(MapperDict.typegroup, EMsgGroup.notify);
		map.put(MapperDict.type, EMsg.compFailed);
		map.put(MapperDict.title, SysConf.ComplaintFailedTitle);
		map.put(MapperDict.content, content);
		return InboxHP.sendInbox(map);
	}
	
	/**
	 * 裁判连续10场被举报成功三次，取消裁判资格，给裁判发送信息
	 * @param adminUid
	 * @param touseruid
	 * @return
	 * @author liubin
	 * @createtime 2016-11-8下午2:07:04
	 * @return boolean
	 */
	public static boolean sendCancelJudgementQualification(String adminUid, String touseruid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.adminuid, adminUid);
		map.put(MapperDict.touseruid, touseruid);
		map.put(MapperDict.typegroup, EMsgGroup.notify);
		map.put(MapperDict.href, WebConf.baseManagerPath + SysConf.ComplaintJudgeUrl);
		map.put(MapperDict.type, EMsg.compThreeCount);
		map.put(MapperDict.title, SysConf.CancelJudgementQualificationTitle);
		return InboxHP.sendInbox(map);
	}
	
	/**
	 * 举报裁判成功，给裁判累加一次举报，发送给裁判
	 * @param adminUid
	 * @param touseruid
	 * @param content
	 * @return
	 * @author liubin
	 * @createtime 2016-11-8下午2:16:24
	 * @return boolean
	 */
	public static boolean sendCompJudgeSuccessToJudge(String adminUid, String touseruid, String content) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.adminuid, adminUid);
		map.put(MapperDict.touseruid, touseruid);
		map.put(MapperDict.typegroup, EMsgGroup.notify);
		map.put(MapperDict.href, WebConf.baseManagerPath + SysConf.ComplaintJudgeUrl);
		map.put(MapperDict.type, EMsg.compJudgeSuccessToJudge);
		map.put(MapperDict.title, SysConf.AddOneComplaintToJudgeTitle);
		map.put(MapperDict.content, content);
		return InboxHP.sendInbox(map);
	}
	
	/**
	 * 举报裁判成功，给举报人发送消息
	 * @param adminUid
	 * @param touseruid
	 * @param content
	 * @return
	 * @author liubin
	 * @createtime 2016-11-8下午2:22:22
	 * @return boolean
	 */
	public static boolean sendCompJudgeSuccess(String adminUid, String touseruid, String content) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.adminuid, adminUid);
		map.put(MapperDict.touseruid, touseruid);
		map.put(MapperDict.typegroup, EMsgGroup.notify);
		map.put(MapperDict.href, WebConf.baseManagerPath + SysConf.ComplaintJudgeUrl);
		map.put(MapperDict.type, EMsg.compJudgeSuccess);
		map.put(MapperDict.title, SysConf.AddOneComplaintToJudgeTitle);
		map.put(MapperDict.content, content);
		return InboxHP.sendInbox(map);
	}
	
	/**
	 * 举报专家成功发送消息，给举报人
	 * @param adminUid
	 * @param touseruid
	 * @param content
	 * @param result
	 * @return
	 * @author liubin
	 * @createtime 2016-11-8下午2:41:05
	 * @return boolean
	 */
	public static boolean sendComExpertSuccess(String adminUid, String touseruid, String content, String result) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.adminuid, adminUid);
		map.put(MapperDict.touseruid, touseruid);
		map.put(MapperDict.typegroup, EMsgGroup.notify);
		map.put(MapperDict.href, WebConf.baseManagerPath + SysConf.ComplaintExpertUrl);
		map.put(MapperDict.type, EMsg.compExpertSuccess);
		map.put(MapperDict.result, result);
		map.put(MapperDict.title, SysConf.ComplaintExpertSuccess);
		map.put(MapperDict.content, content);
		return InboxHP.sendInbox(map);
	}
	
	/**
	 * 举报专家成功，给专家发消息
	 * @param adminUid
	 * @param touseruid
	 * @param content
	 * @param result
	 * @return
	 * @author liubin
	 * @createtime 2016-11-8下午2:48:53
	 * @return boolean
	 */
	public static boolean sendComExpertSuccessToExpert(String adminUid, String touseruid, String content, String result) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.adminuid, adminUid);
		map.put(MapperDict.touseruid, touseruid);
		map.put(MapperDict.typegroup, EMsgGroup.notify);
		map.put(MapperDict.href, WebConf.baseManagerPath + SysConf.ComplaintExpertUrl);
		map.put(MapperDict.type, EMsg.compExpertSuccessToExpert);
		map.put(MapperDict.result, result);
		map.put(MapperDict.title, SysConf.ComplaintExpertSuccess);
		map.put(MapperDict.content, content);
		return InboxHP.sendInbox(map);
	}
	
	/**
	 * 证书颁发成功
	 * @param adminUid
	 * @param touseruid
	 * @param content
	 * @param level
	 * @return
	 * @author liubin
	 * @createtime 2016-11-9下午1:42:07
	 * @return boolean
	 */
	public static boolean sendAwardCertificateMsg(String adminUid, String touseruid, String content, String level) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.adminuid, adminUid);
		map.put(MapperDict.touseruid, touseruid);
		map.put(MapperDict.typegroup, EMsgGroup.notify);
		map.put(MapperDict.type, EMsg.awardCertificate);
		map.put(MapperDict.level, level);
		map.put(MapperDict.title, SysConf.AwardCertificateTitle);
		map.put(MapperDict.href, WebConf.baseManagerPath + SysConf.MyCenterExpertUrl);
		map.put(MapperDict.content, content);
		return InboxHP.sendInbox(map);
	}
	
	/**
	 * 开题申审核结果没通过，给申请者发消息
	 * @param adminUid
	 * @param touseruid
	 * @param content
	 * @return
	 * @author liubin
	 * @createtime 2016-11-10下午7:02:13
	 * @return boolean
	 */
	public static boolean sendCheckreplyNoPassMsg(String adminUid, String touseruid, String major, String degree) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.adminuid, adminUid);
		map.put(MapperDict.touseruid, touseruid);
		map.put(MapperDict.typegroup, EMsgGroup.notify);
		map.put(MapperDict.type, EMsg.openAppNotPass);
		map.put(MapperDict.title, major + degree + SysConf.CheckAppOpenreplyNoPassTitle);
		map.put(MapperDict.href, WebConf.baseManagerPath + SysConf.MyReplyUrl);
		return InboxHP.sendInbox(map);
	}
	
	/**
	 * 开题申审核结果通过，给申请者发消息
	 * @param adminUid
	 * @param touseruid
	 * @param content
	 * @return
	 * @author liubin
	 * @createtime 2016-11-10下午7:02:53
	 * @return boolean
	 */
	public static boolean sendCheckreplyPassMsg(String adminUid, String touseruid, String major, String degree) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.adminuid, adminUid);
		map.put(MapperDict.touseruid, touseruid);
		map.put(MapperDict.typegroup, EMsgGroup.notify);
		map.put(MapperDict.type, EMsg.openAppPass);
		map.put(MapperDict.title, major + degree + SysConf.CheckAppOpenreplyPassTitle);
		map.put(MapperDict.href, WebConf.baseManagerPath + SysConf.MyReplyUrl);
		return InboxHP.sendInbox(map);
	}
	
	/**
	 * 开题材料审核通过，给申请人发信息
	 * @param adminUid
	 * @param touseruid
	 * @return
	 * @author liubin
	 * @createtime 2016-11-10下午8:33:44
	 * @return boolean
	 */
	public static boolean sendCheckreplyFilePassMsg(String adminUid, String touseruid, String major, String time, String degree) {
		Map<String, Object> map = new HashMap<String, Object>();
		String timeStr = time.substring(0, time.indexOf(" "));
		map.put(MapperDict.adminuid, adminUid);
		map.put(MapperDict.touseruid, touseruid);
		map.put(MapperDict.typegroup, EMsgGroup.notify);
		map.put(MapperDict.type, EMsg.openFilePass);
		map.put(MapperDict.time, timeStr);
		map.put(MapperDict.title, major + degree + SysConf.CheckAppOpenreplyFilePassTitle);
		map.put(MapperDict.href, WebConf.baseManagerPath + SysConf.MyReplyUrl);
		return InboxHP.sendInbox(map);
	}
	
	/**
	 * 开题材料审核未通过，给申请人发信息
	 * @param adminUid
	 * @param touseruid
	 * @return
	 * @author liubin
	 * @createtime 2016-11-10下午8:34:16
	 * @return boolean
	 */
	public static boolean sendCheckreplyFileNoPassMsg(String adminUid, String touseruid, String major, String degree) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.adminuid, adminUid);
		map.put(MapperDict.touseruid, touseruid);
		map.put(MapperDict.typegroup, EMsgGroup.notify);
		map.put(MapperDict.type, EMsg.openFileNotPass);
		map.put(MapperDict.title, major + degree + SysConf.CheckAppOpenreplyFileNoPassTitle);
		map.put(MapperDict.href, WebConf.baseManagerPath + SysConf.MyReplyUrl);
		return InboxHP.sendInbox(map);
	}
	
	/**
	 * 开题论证会未通过，给参与者发送信息
	 * @param adminUid
	 * @param touseruid
	 * @param major
	 * @param degree
	 * @return
	 * @author liubin
	 * @createtime 2016-11-12下午4:01:30
	 * @return boolean
	 */
	public static boolean sendOpenReplyNoPassMsg(String adminUid, String touseruid, String major, String degree) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.adminuid, adminUid);
		map.put(MapperDict.touseruid, touseruid);
		map.put(MapperDict.typegroup, EMsgGroup.notify);
		map.put(MapperDict.type, EMsg.openNotPass);
		map.put(MapperDict.title, major + degree + SysConf.OpenReplyNoPasstTitle);
		map.put(MapperDict.href, WebConf.baseManagerPath + SysConf.MyReplyUrl);
		return InboxHP.sendInbox(map);
	}
	
	/**
	 * 开题论证会通过，给参与者发送信息
	 * @param adminUid
	 * @param touseruid
	 * @param major
	 * @param degree
	 * @return
	 * @author liubin
	 * @createtime 2016-11-12下午4:02:09
	 * @return boolean
	 */
	public static boolean sendOpenReplyPassMsg(String adminUid, String touseruid, String major, String degree) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.adminuid, adminUid);
		map.put(MapperDict.touseruid, touseruid);
		map.put(MapperDict.typegroup, EMsgGroup.notify);
		map.put(MapperDict.type, EMsg.openPass);
		map.put(MapperDict.title, major + degree + SysConf.OpenReplyPasstTitle);
		map.put(MapperDict.href, WebConf.baseManagerPath + SysConf.MyReplyUrl);
		return InboxHP.sendInbox(map);
	}
	
	/**
	 * 答辩材料申请审核通过，给申请人发消息
	 * @param adminUid
	 * @param touseruid
	 * @param major
	 * @param degree
	 * @return
	 * @author liubin
	 * @createtime 2016-11-11下午3:10:17
	 * @return boolean
	 */
	public static boolean sendCheckAppreplyPassMsg(String adminUid, String touseruid, String major, String time, String degree) {
		Map<String, Object> map = new HashMap<String, Object>();
		String timeStr = time.substring(0, time.indexOf(" "));
		map.put(MapperDict.adminuid, adminUid);
		map.put(MapperDict.touseruid, touseruid);
		map.put(MapperDict.typegroup, EMsgGroup.notify);
		map.put(MapperDict.type, EMsg.repFilePass);
		map.put(MapperDict.time, timeStr);
		map.put(MapperDict.title, major + degree + SysConf.CheckAppreplyFilePassTitle);
		map.put(MapperDict.href, WebConf.baseManagerPath + SysConf.MyReplyUrl);
		return InboxHP.sendInbox(map);
	}
	
	/**
	 * 答辩材料申请审核未通过，给申请人发消息
	 * @param adminUid
	 * @param touseruid
	 * @param major
	 * @param degree
	 * @return
	 * @author liubin
	 * @createtime 2016-11-11下午3:09:59
	 * @return boolean
	 */
	public static boolean sendCheckAppreplyNoPassMsg(String adminUid, String touseruid, String major, String degree) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.adminuid, adminUid);
		map.put(MapperDict.touseruid, touseruid);
		map.put(MapperDict.typegroup, EMsgGroup.notify);
		map.put(MapperDict.type, EMsg.repFileNotPass);
		map.put(MapperDict.title, major + degree + SysConf.CheckAppreplyFileNoPassTitle);
		map.put(MapperDict.href, WebConf.baseManagerPath + SysConf.MyReplyUrl);
		return InboxHP.sendInbox(map);
	}

	
	/**
	 * 预答辩材料审核未通过，给申请人发消息
	 * @param adminUid
	 * @param touseruid
	 * @param major
	 * @param degree
	 * @return
	 * @author liubin
	 * @createtime 2016-11-12下午2:05:44
	 * @return boolean
	 */
	public static boolean sendCheckAppPrereplyFileNoPassMsg(String adminUid, String touseruid, String major, String degree) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.adminuid, adminUid);
		map.put(MapperDict.touseruid, touseruid);
		map.put(MapperDict.typegroup, EMsgGroup.notify);
		map.put(MapperDict.type, EMsg.prepfileNotPass);
		map.put(MapperDict.title, major + degree + SysConf.CheckAppPrereplyFilePassTitle);
		map.put(MapperDict.href, WebConf.baseManagerPath + SysConf.MyReplyUrl);
		return InboxHP.sendInbox(map);
	}
	
	/**
	 * 预答辩材料审核通过，给申请人发消息
	 * @param adminUid
	 * @param touseruid
	 * @param major
	 * @param degree
	 * @return
	 * @author liubin
	 * @createtime 2016-11-12下午2:06:24
	 * @return boolean
	 */
	public static boolean sendCheckAppPrereplyFilePassMsg(String adminUid, String touseruid, String major, String degree) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.adminuid, adminUid);
		map.put(MapperDict.touseruid, touseruid);
		map.put(MapperDict.typegroup, EMsgGroup.notify);
		map.put(MapperDict.type, EMsg.prepfilePass);
		map.put(MapperDict.title, major + degree + SysConf.CheckAppreplyPassTitle);
		map.put(MapperDict.href, WebConf.baseManagerPath + SysConf.MyReplyUrl);
		return InboxHP.sendInbox(map);
	}
	
	/**
	 * 答辩未通过，给答辩人发消息
	 * @param adminUid
	 * @param touseruid
	 * @param major
	 * @param degree
	 * @return
	 * @author liubin
	 * @createtime 2016-11-12下午5:41:15
	 * @return boolean
	 */
	public static boolean sendReplyNoPassMsg(String adminUid, String touseruid, String major, String degree) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.adminuid, adminUid);
		map.put(MapperDict.touseruid, touseruid);
		map.put(MapperDict.typegroup, EMsgGroup.notify);
		map.put(MapperDict.type, EMsg.repNotPass);
		map.put(MapperDict.content, major);
		map.put(MapperDict.level, degree);
		map.put(MapperDict.title, major + degree + SysConf.ReplyNoPassTitle);
		map.put(MapperDict.href, WebConf.baseManagerPath + SysConf.MyReplyUrl);
		return InboxHP.sendInbox(map);
	}
	
	/**
	 * 答辩通过，给答辩人发消息
	 * @param adminUid
	 * @param touseruid
	 * @param major
	 * @param degree
	 * @return
	 * @author liubin
	 * @createtime 2016-11-12下午5:41:37
	 * @return boolean
	 */
	public static boolean sendReplyPassMsg(String adminUid, String touseruid, String major, String degree) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.adminuid, adminUid);
		map.put(MapperDict.touseruid, touseruid);
		map.put(MapperDict.typegroup, EMsgGroup.notify);
		map.put(MapperDict.type, EMsg.repPass);
		map.put(MapperDict.content, major);
		map.put(MapperDict.level, degree);
		map.put(MapperDict.title, major + degree + SysConf.ReplyPassTitle);
		map.put(MapperDict.href, WebConf.baseManagerPath + SysConf.MyReplyUrl);
		return InboxHP.sendInbox(map);
	}
	
	/**
	 * 二次答辩未通过，给答辩人发送消息
	 * @param adminUid
	 * @param touseruid
	 * @param major
	 * @param degree
	 * @return
	 * @author liubin
	 * @createtime 2016-11-12下午6:07:35
	 * @return boolean
	 */
	public static boolean sendSecondReplyNoPassMsg(String adminUid, String touseruid, String major, String degree) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.adminuid, adminUid);
		map.put(MapperDict.touseruid, touseruid);
		map.put(MapperDict.typegroup, EMsgGroup.notify);
		map.put(MapperDict.type, EMsg.secondNotPass);
		map.put(MapperDict.content, major);
		map.put(MapperDict.level, degree);
		map.put(MapperDict.title, major + degree + SysConf.SecondReplyNoPassTitle);
		map.put(MapperDict.href, WebConf.baseManagerPath + SysConf.MyReplyUrl);
		return InboxHP.sendInbox(map);
	}
	
	/**
	 * 二次答辩通过，给答辩人发送消息
	 * @param adminUid
	 * @param touseruid
	 * @param major
	 * @param degree
	 * @return
	 * @author liubin
	 * @createtime 2016-11-12下午6:07:52
	 * @return boolean
	 */
	public static boolean sendSecondReplyPassMsg(String adminUid, String touseruid, String major, String degree) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.adminuid, adminUid);
		map.put(MapperDict.touseruid, touseruid);
		map.put(MapperDict.typegroup, EMsgGroup.notify);
		map.put(MapperDict.type, EMsg.secondPass);
		map.put(MapperDict.content, major);
		map.put(MapperDict.level, degree);
		map.put(MapperDict.title, major + degree + SysConf.SecondReplyPassTitle);
		map.put(MapperDict.href, WebConf.baseManagerPath + SysConf.MyReplyUrl);
		return InboxHP.sendInbox(map);
	}
	
	/**
	 * 研究报告未通过，给申请人发送消息
	 * @param adminUid
	 * @param touseruid
	 * @param major
	 * @param degree
	 * @return
	 * @author liubin
	 * @createtime 2016-11-12下午6:13:15
	 * @return boolean
	 */
	public static boolean sendReportNoPassMsg(String adminUid, String touseruid, String major, String degree) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.adminuid, adminUid);
		map.put(MapperDict.touseruid, touseruid);
		map.put(MapperDict.typegroup, EMsgGroup.notify);
		map.put(MapperDict.type, EMsg.repReportNotPass);
		map.put(MapperDict.content, major);
		map.put(MapperDict.level, degree);
		map.put(MapperDict.title, major + degree + SysConf.ReplyNoPassTitle);
		map.put(MapperDict.href, WebConf.baseManagerPath + SysConf.MyReplyUrl);
		return InboxHP.sendInbox(map);
	}
	
	/**
	 * 研究报告通过，给申请人发送消息
	 * @param adminUid
	 * @param touseruid
	 * @param major
	 * @param degree
	 * @return
	 * @author liubin
	 * @createtime 2016-11-12下午6:13:43
	 * @return boolean
	 */
	public static boolean sendReportPassMsg(String adminUid, String touseruid, String major, String degree) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.adminuid, adminUid);
		map.put(MapperDict.touseruid, touseruid);
		map.put(MapperDict.typegroup, EMsgGroup.notify);
		map.put(MapperDict.type, EMsg.getCertificate);
		map.put(MapperDict.content, major);
		map.put(MapperDict.level, degree);
		map.put(MapperDict.title, major + degree + SysConf.ReplyPassTitle);
		map.put(MapperDict.href, WebConf.baseManagerPath + SysConf.MyReplyUrl);
		return InboxHP.sendInbox(map);
	}
	
	/**
	 * 创建答辩会议室成功，给答辩这发送消息
	 * @param adminUid
	 * @param touseruid
	 * @param major
	 * @param degree
	 * @return
	 * @author liubin
	 * @createtime 2016-11-16下午7:29:28
	 * @return boolean
	 */
	public static boolean sendCreatQQGroupmettingMsg(String adminUid, String touseruid, String major, String qqgroup, String degree) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.adminuid, adminUid);
		map.put(MapperDict.touseruid, touseruid);
		map.put(MapperDict.typegroup, EMsgGroup.notify);
		map.put(MapperDict.type, EMsg.creatReplyQQMetting);
		map.put(MapperDict.content, major);
		map.put(MapperDict.level, degree);
		map.put(MapperDict.qqgroup, qqgroup);
		map.put(MapperDict.title, major + degree + SysConf.CreatReplyQQGroupMetting);
		map.put(MapperDict.href, WebConf.baseManagerPath + SysConf.MyReplyUrl);
		return InboxHP.sendInbox(map);
	}
	
	/**
	 * 创建挑战会议室成功，给裁判和挑战者发送消息
	 * @param adminUid
	 * @param touseruid
	 * @param name
	 * @param qqgroup
	 * @param degree
	 * @return
	 * @author liubin
	 * @createtime 2016年11月28日下午3:30:33
	 * @return boolean
	 */
	public static boolean sendCreatQQGroupmettingForPKMsg(String adminUid, String touseruid, String name, String qqgroup, String uid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.adminuid, adminUid);
		map.put(MapperDict.touseruid, touseruid);
		map.put(MapperDict.typegroup, EMsgGroup.notify);
		map.put(MapperDict.type, EMsg.qqGroupOkToabAndJudge);
		map.put(MapperDict.content, name);
		map.put(MapperDict.qqgroup, qqgroup);
		map.put(MapperDict.title, name);
		map.put(MapperDict.href, WebConf.baseManagerPath + String.format(SysConf.PKDetailUrl, uid));
		return InboxHP.sendInbox(map);
	}
	
	/**
	 * 创建挑战会议室成功，给观众发送消息
	 * @param adminUid
	 * @param touseruid
	 * @param name
	 * @param qqgroup
	 * @return
	 * @author liubin
	 * @createtime 2016年11月28日下午3:37:12
	 * @return boolean
	 */
	public static boolean sendCreatQQGroupmettingForPKToViewMsg(String adminUid, String touseruid, String name, String qqgroup, String uid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.adminuid, adminUid);
		map.put(MapperDict.touseruid, touseruid);
		map.put(MapperDict.typegroup, EMsgGroup.notify);
		map.put(MapperDict.type, EMsg.qqGroupOkToWatcher);
		map.put(MapperDict.content, name);
		map.put(MapperDict.qqgroup, qqgroup);
		map.put(MapperDict.title, name);
		map.put(MapperDict.href, WebConf.baseManagerPath + String.format(SysConf.PKDetailUrl, uid));
		return InboxHP.sendInbox(map);
	}
	
	/**
	 * 积分满足晋级发消息
	 * @param adminUid
	 * @param touseruid
	 * @return
	 * @author chenminchang
	 * @create 2016年11月20日下午5:13:30
	 */
	public static boolean sendCanUpWithScoreMsg(String adminUid, String touseruid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.adminuid, adminUid);
		map.put(MapperDict.touseruid, touseruid);
		map.put(MapperDict.typegroup, EMsgGroup.notify);
		map.put(MapperDict.type, EMsg.scoreCanPK);
		map.put(MapperDict.title, EMsg.scoreCanPK.getName());
		map.put(MapperDict.href, WebConf.baseManagerPath + SysConf.MyHome);
		return InboxHP.sendInbox(map);
	}
	
	/**
	 * 发送裁判费用信息
	 * @param adminUid
	 * @param touseruid
	 * @param name
	 * @return
	 * @author liubin
	 * @createtime 2016年11月29日下午12:24:21
	 * @return boolean
	 */
	public static boolean sendPKJudgementGetRewardMsg(String adminUid, String touseruid, String name, String uid) {
		Map<String, Object> map = new HashMap<String, Object>();
	    map.put(MapperDict.adminuid, adminUid);
	    map.put(MapperDict.touseruid, touseruid);
	    map.put(MapperDict.typegroup, EMsgGroup.notify);
	    map.put(MapperDict.type, EMsg.judgeFee);
	    map.put(MapperDict.content, name);
	    map.put(MapperDict.title, name);
	    map.put(MapperDict.href, WebConf.baseUserPath + SysConf.MyTradeDetailUrl);
		return InboxHP.sendInbox(map);
	}
	
	/**
	 * 挑战和或者被挑战着在同一级别失败五次，进行降级处理
	 * @param adminUid
	 * @param touseruid
	 * @param name
	 * @param level
	 * @return
	 * @author liubin
	 * @createtime 2016年11月29日下午1:55:55
	 * @return boolean
	 */
	public static boolean sendPKUserGetPunishMsg(String adminUid, String touseruid, String name, String level, String uid) {
		Map<String, Object> map = new HashMap<String, Object>();
	    map.put(MapperDict.adminuid, adminUid);
	    map.put(MapperDict.touseruid, touseruid);
	    map.put(MapperDict.typegroup, EMsgGroup.notify);
	    map.put(MapperDict.type, EMsg.demotion);
	    map.put(MapperDict.title, name);
	    map.put(MapperDict.content, name);
	    map.put(MapperDict.level, level);
	    map.put(MapperDict.href, WebConf.baseManagerPath + String.format(SysConf.PKDetailUrl, uid));
		return InboxHP.sendInbox(map);
	}
	
	/**
	 * 挑战双方获胜，获得奖励发送消息
	 * @param adminUid
	 * @param touseruid
	 * @param name
	 * @return
	 * @author liubin
	 * @createtime 2016年11月29日下午2:16:05
	 * @return boolean
	 */
	public static boolean sendPKUserWinGetRewardMsg(String adminUid, String touseruid, String name, String uid) {
		Map<String, Object> map = new HashMap<String, Object>();
	    map.put(MapperDict.adminuid, adminUid);
	    map.put(MapperDict.touseruid, touseruid);
	    map.put(MapperDict.typegroup, EMsgGroup.notify);
	    map.put(MapperDict.type, EMsg.pkWinFee);
	    map.put(MapperDict.content, name);
	    map.put(MapperDict.title, name);
	    map.put(MapperDict.href, WebConf.baseUserPath + SysConf.MyTradeDetailUrl);
		return InboxHP.sendInbox(map);
	}
	
	/**
	 * 挑战者缺席
	 * @param adminUid
	 * @param touseruid
	 * @param name
	 * @return
	 * @author liubin
	 * @createtime 2016年11月29日下午3:47:24
	 * @return boolean
	 */
	public static boolean sendPKUseraAbsentMsg(String adminUid, String touseruid, String name, String uid) {
		Map<String, Object> map = new HashMap<String, Object>();
	    map.put(MapperDict.adminuid, adminUid);
	    map.put(MapperDict.touseruid, touseruid);
	    map.put(MapperDict.typegroup, EMsgGroup.notify);
	    map.put(MapperDict.type, EMsg.aAbsent);
	    map.put(MapperDict.content, name);
	    map.put(MapperDict.title, name);
	    map.put(MapperDict.href, WebConf.baseManagerPath + String.format(SysConf.PKDetailUrl, uid));
		return InboxHP.sendInbox(map);
	}
	
	/**
	 * 被挑战者缺席
	 * @param adminUid
	 * @param touseruid
	 * @param name
	 * @return
	 * @author liubin
	 * @createtime 2016年11月29日下午3:50:33
	 * @return boolean
	 */
	public static boolean sendPKUserbAbsentMsg(String adminUid, String touseruid, String name, String uid) {
		Map<String, Object> map = new HashMap<String, Object>();
	    map.put(MapperDict.adminuid, adminUid);
	    map.put(MapperDict.touseruid, touseruid);
	    map.put(MapperDict.typegroup, EMsgGroup.notify);
	    map.put(MapperDict.type, EMsg.bAbsent);
	    map.put(MapperDict.content, name);
	    map.put(MapperDict.title, name);
	    map.put(MapperDict.href, WebConf.baseManagerPath + String.format(SysConf.PKDetailUrl, uid));
		return InboxHP.sendInbox(map);
	}
	
	/**
	 * 挑战结果已经产生，给所有人发消息
	 * @param adminUid
	 * @param touseruid
	 * @param name
	 * @return
	 * @author liubin
	 * @createtime 2016年11月29日下午5:03:19
	 * @return boolean
	 */
	public static boolean sendPKAllMemberMsg(String adminUid, String touseruid, String name, String uid) {
		Map<String, Object> map = new HashMap<String, Object>();
	    map.put(MapperDict.adminuid, adminUid);
	    map.put(MapperDict.touseruid, touseruid);
	    map.put(MapperDict.typegroup, EMsgGroup.notify);
	    map.put(MapperDict.type, EMsg.pkResult); 
	    map.put(MapperDict.content, name);
	    map.put(MapperDict.title, name);
	    map.put(MapperDict.href, WebConf.baseManagerPath + String.format(SysConf.PKDetailUrl, uid));
		return InboxHP.sendInbox(map);
	}
}
