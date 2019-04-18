package com.supergenius.xo.manager.enums;

import java.util.Arrays;
import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 消息类型 ,每一种消息类型都有不同的提示信息，可在模板中查看
 * 消息模板路径：E:\MyWork2\Proj-supergenius-data\data\manager\tmpl\message
 * @author chenminchang
 * @createtime 2016-7-28下午2:27:49
 */
public enum EMsg {
	
	system(0),//系统消息
	comment(1),//评论
	notify(2),//通知
	
	replyComment(10),//回复评论
	
	appStudent(20),//申请某专业学员
	appStudentFailed(21),//申请申请未通过
	
	//挑战相关 ： a 为挑战者     b 为被挑战者     yes 为接受     no 为拒绝 
	feeSuccess(29),//挑战缴费成功
	getPK(30),//收到挑战
	bNoPK(31),//被挑战者直接拒绝
	bNoPKToSelf(32),//被挑战者直接拒绝(给被挑战者自己发,告诉他多次拒绝可能降级)
	bMonthNoCount(33),//一个自然月内拒绝次数统计
	bYesTime(34),//被挑战者接受挑战时间
	bReselectTime(35),//被    挑战者重选时间
	bNotHandle(36),//被挑战者10日内未处理挑战邀请
	bNotHandleToslfe(37),//被挑战者10日内未处理挑战邀请,给被挑战者发告诉他10日未处理视为拒绝
	aNoAlltime(38),//挑战者拒绝所有时间
	aNoAlltimeToslfe(39),//挑战者拒绝所有时间
	aNotHandle(40),//挑战者5日内未选择新时间
	aNotHandle2(58),//挑战者5日内未选择新时间（发给被挑战者）
	waitabJudge(41),//等待双方裁判处理
	abJudgeOk(42),//双方裁判确定
	createPK(43),//主裁确定，挑战生成
	createPKToJudge(44),//主裁确定，挑战生成,发给三个裁判
	buyTicket(45),//买票成功
	returnTicket(46),//挑战取消退票
	cancelPK(47),//挑战取消
	countDownPKToab(48),//挑战倒计时24小时(挑战者，被挑战者)
	countDownPKToJudge(49),//挑战倒计时24小时，裁判
	countDownPKToWatcher(50),//挑战倒计时24小时，观众
	pkResult(51),//挑战结果
	judgeFee(52),//裁判费
	pkWinFee(53),//挑战获胜方奖励
	demotion(54),//同一级别累计挑战失败5次
	aAbsent(55),//挑战者无故缺席
	bAbsent(56),//被挑战者无故缺席
	judgeAbsent(57),//裁判无故缺席
	qqGroupOkToabAndJudge(59),//挑战QQ群确定 (挑战双方、裁判)
	qqGroupOkToWatcher(60),//挑战QQ群确定(观众)
	
	//答辩相关：reply 简写 rep 
	appRepHint(70),//满足答辩提醒
	openAppNotPass(71),//开题申请未通过
	openAppPass(72),//开题申请通过
	openFileNotPass(73),//开题材料未通过审核
	openFilePass(74),//开题资料通过审核
	openNotPass(75),//开题未通过
	openPass(76),//开题通过
	prepfileNotPass(77),//预答辩材料审核不通过
	prepfilePass(78),//预答辩材料审核通过
	repFileNotPass(79),//答辩材料审核不通过
	repFilePass(80),//答辩材料审核通过
	repCountDown(81),//答辩倒计时24小时
	repPass(82),//答辩通过
	repNotPass(83),//答辩未通过
	secondNotPass(84),//二次答辩未通过
	secondPass(85),//二次答辩通过
	repReportNotPass(86),//研究报告不通过
	getCertificate(87),//研究报告通过获得证书
	expertAbsent(88),//专家无故缺席答辩
	creatReplyQQMetting(89),//创建答辩会议室
	
	//专家申请，晋级 expert 简写 exp
	appExpHint(100),//满足专家申请条件
	expAppNotPass(101),//专家申请表未通过
	expAppPass(102),//专家申请表通过
	expReportNotPass(103),//研究报告审核不通过
	expReportPass(104),//研究报告审核通过
	expInterviewNotPass(105),//专家面试不通过
	becomeExp(106),//专家面试通过,晋级专家
	exitExp(107),//专家退出专家队伍
	recoverExp(108),//专家恢复身份
	hundredCount(109),//100场执教	
	professionalExp(110),//专职专家
	specialExp(111),//特批专家
	
	//裁判申请 judge
	appJudgeHint(120),//满足裁判申请条件
	judgeAppNotPass(121),//裁判申请表未通过
	judgeAppPass(122),//裁判申请表通过
	fileNotPass(123),//题目审核不通过
	filePass(124),//题目审核通过
	judgeInterviewNotPass(125),//裁判面试考核不通过
	judgeInterviewPass(126),//裁判面试考核通过
	exitJudge(127),//裁判退出裁判队伍
	recoverJudge(128),//裁判恢复身份
	fiftyCount(129),//50场执教
	professionalJudge(130),//专职裁判
	specialJudge(131),//特批裁判
	
	//视频购买
	buyVideo(140),//购买视频
	videoExpire(141),//视频快到期通知
	createPKVideo(142),//挑战视频生成
	createReplyVideo(143),//答辩视频生成
	
	//举报 complaint 简写 comp
	compFailed(150),//举报失败
	compJudgeSuccess(151),//举报裁判成功
	compJudgeSuccessToJudge(152),//举报裁判成功（给被举报者发送）
	compThreeCount(153),//任意10场内被成功举报3次
	compExpertSuccess(154),//举报专家成功
	compExpertSuccessToExpert(155),//举报专家成功（给被举报者）
	
	//积分
	financeScore(160),//文章审核通过→发表
	scoreCanPK(161),//积分满足晋级条件
	scoreUpGrade(162),//积分晋级成功
	
	//证书
	awardCertificate(163);//证书已经颁发
	
    private final int value;

 	private EMsg(int v) {
 		this.value = v;
 	}

 	public static EMsg get(int v) {
 		for(EMsg e:values()) {
 			if(e.getValue()==v) {
 				return e;
 			}
 		}
 		return null;
 	}
 	
 	public String toString() {
 		return String.valueOf(value);
 	}
 	
 	public int getValue() {
		return value;
	}
 	
 	public String getName() {
		return I18N.getEnumName(this, Locale.CHINA);
	}
 	
 	/**
 	 * 是否是挑战相关的消息
 	 * @return
 	 * @author XieMing
 	 * 2016-9-6 上午10:06:21
 	 */
 	public static boolean isPkMsg(EMsg type) {
		EMsg[] pkmsg = {EMsg.getPK,EMsg.bNoPK,EMsg.bYesTime,EMsg.bReselectTime,EMsg.bNotHandle,
				EMsg.aNoAlltime,EMsg.aNotHandle,EMsg.aNotHandle2,EMsg.abJudgeOk,
				EMsg.createPK,EMsg.cancelPK,EMsg.countDownPKToab,EMsg.pkResult,EMsg.pkWinFee,EMsg.demotion,EMsg.aAbsent,EMsg.bAbsent,
				};
 		if(Arrays.asList(pkmsg).contains(type)) {
 			return true;
 		} else {
 			return false;
 		}
 	}
 	
 	/**
 	 * 是否是答辩相关的消息
 	 * @return
 	 * @author XieMing
 	 * 2016-9-6 上午10:06:41
 	 */
 	public static boolean isReplyMsg(EMsg type) {
		EMsg[] replymsg = {EMsg.appRepHint,EMsg.openAppNotPass,EMsg.openAppPass,EMsg.openFileNotPass,EMsg.openFilePass,EMsg.openNotPass,EMsg.openPass,
				EMsg.prepfileNotPass,EMsg.prepfilePass,EMsg.repFileNotPass,EMsg.repFilePass,EMsg.repCountDown,EMsg.repPass,EMsg.repNotPass,
				EMsg.secondNotPass,EMsg.secondPass,EMsg.repReportNotPass,EMsg.getCertificate,EMsg.expertAbsent,EMsg.compExpertSuccessToExpert};
 		if(Arrays.asList(replymsg).contains(type)) {
 			return true;
 		} else {
 			return false;
 		}
 	}
 	
 	/**
 	 * 是否是购买挑战的相关消息
 	 * @return
 	 * @author XieMing
 	 * 2016-9-6 上午10:07:05
 	 */
 	public static boolean isBuyPkMsg(EMsg type) {
		EMsg[] buypkmsg = {EMsg.buyTicket,EMsg.returnTicket,EMsg.countDownPKToWatcher,EMsg.pkResult};
 		if(Arrays.asList(buypkmsg).contains(type)) {
 			return true;
 		} else {
 			return false;
 		}
 	}
 	
 	/**
 	 * 是否是购买视频的相关消息
 	 * @return
 	 * @author XieMing
 	 * 2016-9-6 上午10:07:35
 	 */
 	public static boolean isBuyVideoMsg(EMsg type) {
		EMsg[] buyvideomsg = {EMsg.buyVideo,EMsg.videoExpire,EMsg.createPKVideo,EMsg.createReplyVideo};
 		if(Arrays.asList(buyvideomsg).contains(type)) {
 			return true;
 		} else {
 			return false;
 		}
 	}
 	
 	/**
 	 * 是否是裁判管理的相关消息
 	 * @return
 	 * @author XieMing
 	 * 2016-9-6 上午10:07:52
 	 */
 	public static boolean isJudgeMsg(EMsg type) {
		EMsg[] judgemsg = {EMsg.waitabJudge,EMsg.createPKToJudge,EMsg.cancelPK,EMsg.countDownPKToJudge,EMsg.pkResult,EMsg.judgeFee,EMsg.judgeAbsent,EMsg.appJudgeHint,
				EMsg.judgeAppNotPass,EMsg.judgeAppPass,EMsg.fileNotPass,EMsg.filePass,EMsg.judgeInterviewNotPass,EMsg.judgeInterviewPass,
				EMsg.exitJudge,EMsg.recoverJudge,EMsg.fiftyCount,EMsg.professionalJudge,EMsg.specialJudge,EMsg.compJudgeSuccess};
 		if(Arrays.asList(judgemsg).contains(type)) {
 			return true;
 		} else {
 			return false;
 		}
 	}
 	
 	/**
 	 * 是否是专家管理的相关消息
 	 * @return
 	 * @author XieMing
 	 * 2016-9-6 上午10:08:04
 	 */
 	public static boolean isExpertMsg(EMsg type) {
		EMsg[] expertmsg = {EMsg.appExpHint,EMsg.expAppNotPass,EMsg.expAppPass,EMsg.expReportNotPass,EMsg.expReportPass,EMsg.expInterviewNotPass,EMsg.becomeExp,
				EMsg.exitExp,EMsg.recoverExp,EMsg.hundredCount,EMsg.professionalExp,EMsg.specialExp,EMsg.compExpertSuccess};
 		if(Arrays.asList(expertmsg).contains(type)) {
 			return true;
 		} else {
 			return false;
 		}
 	}
 	
}
