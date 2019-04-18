/**
 * 
 * ============================================================================
 * 声明：© 2013-2014 天才纵横版权所有
 * ----------------------------------------------------------------------------
 * Official Website: http://www.grandgeniusgroup.com
 * ----------------------------------------------------------------------------
 * Copyright: © 2013 GrandGeniusGroup All Rights Reserved.
 * ----------------------------------------------------------------------------
 * @version: 1.0
 * ----------------------------------------------------------------------------
 * @author: Architect.bian
 * ----------------------------------------------------------------------------
 * Create at: 2013-4-15 下午4:38:18
 * ============================================================================
 */
package com.supergenius.xo.manager.enums;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 申请答辩状态表
 * @author chenminchang
 * @createtime 2016-7-17下午4:00:37
 */
public enum EReplyStage {
     
	applying("0"),//开题申请审核中  
	failApply("1"),//开题申请未通过
	passApply("2"),//开题申请通过，上传开题材料 
	waitPay("23"),//等待缴费
	checkData("3"),//开题材料审核中 
	failData("4"),//开题资料未通过，请重新上传
	passData("5"),//开题资料审核通过，等待开题论证会
	opening("6"),//开题论证会进行中
	endOpen("7"),//开题论证会结束，等待结果 
	failOpen("8"),//开题未通过
	
	passOpen("9"),//开题通过，提交预答辩材料
	checkPredata("10"),//预答辩材料审核中
	failPredata("11"),//预答辩材料未通过
	
	passPredata("12"),//预答辩通过 
	checkReplyData("13"),//答辩材料审核
	failReplyData("14"),//答辩材料未通过
	
	passReplyData("15"),//答辩材料通过
	replying("16"),//答辩进行中
	endReply("17"),//答辩结束，等待结果  
	failReply("18"),//答辩未通过
	waitRepay("24"),//等待支付二次答辩费用
	passReply("19"),//答辩通过，上传研究报告
	checkReport("20"),//毕业研究报告审核中 
	failReport("21"),//研究报告未通过 
	passReport("22");//研究报告通过，获得证书 
	 
	private final String value;

	private EReplyStage(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}

	public static EReplyStage get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static EReplyStage get(String str) {
		for (EReplyStage e : values()) {
			if (e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}
	
	public static String getValues(EReplyStage stage) {
		for (EReplyStage e : values()) {
			if (e.equals(stage)) {
				return e.value;
			}
		}
		return null;
	}

	public String getName() {
		return I18N.getEnumName(this, Locale.CHINA);
	}
	
	/**
	 * 开题申请审核阶段
	 * @return
	 * @author liubin
	 * @createtime 2016-11-9下午4:40:23
	 * @return List<EReplyStage>
	 */
	public static List<EReplyStage> getReportApplicationReviewStages() {
		List<EReplyStage> list = new ArrayList<EReplyStage>();
		list.add(EReplyStage.applying);
		list.add(EReplyStage.failApply);
		list.add(EReplyStage.passApply);
		list.add(EReplyStage.waitPay);
		list.add(EReplyStage.checkData);
		list.add(EReplyStage.failData);
		return list;
	}
	
	/**
	 * 开题论证会阶段
	 * @return
	 * @author liubin
	 * @createtime 2016-11-9下午4:49:01
	 * @return List<EReplyStage>
	 */
	public static List<EReplyStage> getDemonstrationMeetingStages() {
		List<EReplyStage> list = new ArrayList<EReplyStage>();
		list.add(EReplyStage.passData);
		list.add(EReplyStage.opening);
		list.add(EReplyStage.endOpen);
		list.add(EReplyStage.failOpen);
		return list;
	}
	
	/**
	 * 预答辩审核阶段
	 * @return
	 * @author liubin
	 * @createtime 2016-11-9下午4:52:32
	 * @return List<EReplyStage>
	 */
	public static List<EReplyStage> getPreReplyStages() {
		List<EReplyStage> list = new ArrayList<EReplyStage>();
		list.add(EReplyStage.passOpen);
		list.add(EReplyStage.checkPredata);
		list.add(EReplyStage.failPredata);
		return list;
	}
	
	/**
	 * 答辩申请审核阶段
	 * @return
	 * @author liubin
	 * @createtime 2016-11-9下午4:56:50
	 * @return List<EReplyStage>
	 */
	public static List<EReplyStage> getReplyApplicationReviewStages() {
		List<EReplyStage> list = new ArrayList<EReplyStage>();
		list.add(EReplyStage.passPredata);
		list.add(EReplyStage.checkReplyData);
		list.add(EReplyStage.failReplyData);
		return list;
	}
	
	/**
	 * 等待答辩阶段
	 * @return
	 * @author liubin
	 * @createtime 2016-11-9下午5:14:13
	 * @return List<EReplyStage>
	 */
	public static List<EReplyStage> getWaitingReplyingStages() {
		List<EReplyStage> list = new ArrayList<EReplyStage>();
		list.add(EReplyStage.failReply);
		list.add(EReplyStage.waitRepay);
		list.add(EReplyStage.passPredata);
		return list;
	}
	
	/**
	 * 答辩进行中阶段
	 * @return
	 * @author liubin
	 * @createtime 2016-11-9下午4:57:37
	 * @return List<EReplyStage>
	 */
	public static List<EReplyStage> getReplyingStages() {
		List<EReplyStage> list = new ArrayList<EReplyStage>();
		list.add(EReplyStage.replying);
		return list;
	}
	
	/**
	 * 等待答辩结果中
	 * @return
	 * @author liubin
	 * @createtime 2016-11-9下午5:04:58
	 * @return List<EReplyStage>
	 */
	public static List<EReplyStage> getWaitingReplyResultStages() {
		List<EReplyStage> list = new ArrayList<EReplyStage>();
		list.add(EReplyStage.endReply);
		return list;
	}
	
	/**
	 * 研究报告审核阶段
	 * @return
	 * @author liubin
	 * @createtime 2016-11-9下午5:05:32
	 * @return List<EReplyStage>
	 */
	public static List<EReplyStage> getEndReplyStages() {
		List<EReplyStage> list = new ArrayList<EReplyStage>();
		list.add(EReplyStage.passReply);
		list.add(EReplyStage.checkReport);
		list.add(EReplyStage.failReport);
		return list;
	}
	
	/**
	 * 研究报告通过
	 * @return
	 * @author liubin
	 * @createtime 2016-11-9下午6:37:46
	 * @return List<EReplyStage>
	 */
	public static List<EReplyStage> getReportReviewStages() {
		List<EReplyStage> list = new ArrayList<EReplyStage>();
		list.add(EReplyStage.passReport);
		return list;
	}
	
	/**
	 * 和支付相关的状态
	 * @return
	 * @author liubin
	 * @createtime 2016-11-10下午4:01:29
	 * @return List<EReplyStage>
	 */
	public static List<EReplyStage> getAboutPay() {
		List<EReplyStage> list = new ArrayList<EReplyStage>();
		list.add(EReplyStage.waitPay);
		list.add(EReplyStage.waitRepay);
		return list;
	}
}
