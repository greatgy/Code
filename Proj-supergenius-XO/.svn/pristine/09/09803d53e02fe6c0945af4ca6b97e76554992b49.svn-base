package com.supergenius.xo.manager.enums;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.genius.core.base.utils.I18N;

/**
 * 挑战阶段枚举  (这个枚举切勿修改，增加枚举页面上也可能需要增加)
 * @author XieMing
 * @date 2016-5-3 上午11:18:09
 */
public enum EPKStage {
	
	init("0"),//初始
	unpay("1"),//已提交挑战申请，等待支付挑战费
	applyCancel("26"),//挑战者取消，申请失败
	payed("2"),//预支付挑战费
	judgementGroupAuditPass("3"),//裁判委员会审核通过
	applyFailed("7"),//裁判委员会审核未通过，申请失败
	waitingForPKUser2("5"),//等待被挑战者确认
	applyCancel2("25"),//被挑战者拒绝，申请失败
	tenDaysNotHandle("27"),//被挑战者十天未处理，视为拒绝，申请失败
	waitingForPKUser("4"),//被挑战者更改时间，等待挑战者确认
	applyCancel1("6"),//挑战者拒绝被挑战者提出的10个时间，申请失败
	fiveDaysNotHandle("28"),//挑战者五天未处理，视为拒绝，申请失败
	applySuccess("8"),//申请挑战成功，等待裁判确认，为了给裁判自动发邮件applySuccess必须挨着裁判确认不能在applyFailed之前
	confirmByPKJudgement("9"),//挑战者裁判确认
	confirmByPKJudgement2("10"),//被挑战者裁判确认
	setJudgement("11"),//等待指定挑战者裁判
	setJudgement2("12"),//等待指定被挑战者裁判
	setJudgementBoth("29"),//等待指定双方裁判
	setJudgementChair("13"),//等待指定主裁判
	waitingCreateSeegle("23"),//指定完主裁判，等待创建会议室
	waitingForChallenge("14"),//等待挑战(即将开始)
	challenging("15"),//挑战进行中
	
	challengeSuccess("16"),//挑战成功结束（已结束，公布结果）
	pkuserAbsent("18"),//挑战者未参加
	pkuser2Absent("19"),//被挑战者未参加
	bothAbsent("20"),//两方都未参加
	
	challengeCancel("17"),//挑战取消
	
	refuseByPKJudgement("21"),//挑战者裁判拒绝(暂时不用)
	refuseByPKJudgement2("22"),//被挑战者裁判拒绝(暂时不用)
	
	challengSeegleOver("24");//挑战会议结束（等待结果）
	//当前最大值为29

	private final String value;

	private EPKStage(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}

	public static EPKStage get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static EPKStage get(String str) {
		for (EPKStage e : values()) {
			if (e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}

	public static String getName(EPKStage e, Locale locale) {
		return I18N.getEnumName(e, locale);
	}
	
	/**
	 * 判断状态在结束的范围内
	 * 暂时不用，挑战没有在一定的范围内，无法判断大于  小于
	 * @param e
	 * @return
	 */
	public static boolean isOverStage(EPKStage e) {
		if (Integer.parseInt(e.toString()) <= Integer.parseInt(EPKStage.bothAbsent.toString()) && Integer.parseInt(e.toString()) >= Integer.parseInt(EPKStage.challengeSuccess.toString())) {
			return true;
		}
		return false;
	}
	
	public String getName() {
		return I18N.getEnumName(this, Locale.CHINA);
	}
	
	/**
	 * 获取状态为即将开始，进行中，等待结果，挑战取消，挑战结束五个枚举的值与中文名称
	 * @return
	 * @author XieMing
	 * 2016-7-28 下午1:46:27
	 */
	public static Map<String, String> getPkStage() {
		Map<String, String> map = new LinkedHashMap<String, String>(); 
		map.put(EPKStage.waitingForChallenge.toString(), EPKStage.waitingForChallenge.getName());
		map.put(EPKStage.challenging.toString(), EPKStage.challenging.getName());
		map.put(EPKStage.challengSeegleOver.toString(), EPKStage.challengSeegleOver.getName());
		map.put(EPKStage.challengeCancel.toString(), EPKStage.challengeCancel.getName());
		map.put(EPKStage.challengeSuccess.toString(), EPKStage.challengeSuccess.getName());
		return map;
	}

	/**
	 * 获取要展示的挑战的状态的值用于in查询
	 * @return
	 * @author XieMing
	 * 2016-8-2 下午12:06:44
	 */
	public static List<EPKStage> getShowStages() {
		List<EPKStage> list = new ArrayList<EPKStage> ();
		list.add(EPKStage.challenging);
		list.add(EPKStage.waitingForChallenge);
		list.add(EPKStage.challengeSuccess);
		list.add(EPKStage.challengSeegleOver);
		list.add(EPKStage.challengeCancel);
		list.add(EPKStage.pkuserAbsent);
		list.add(EPKStage.pkuser2Absent);
		list.add(EPKStage.bothAbsent);
		return list;
	}
	
	/**
	 * 得到申请中全部类型
	 * @return
	 * @author chenminchang
	 * @create 2016-8-22下午5:42:36
	 */
	public static List<EPKStage> getAppings() {
		List<EPKStage> list = new ArrayList<EPKStage> ();
		list.add(EPKStage.init);
		list.add(EPKStage.unpay);
		list.add(EPKStage.payed);
		list.add(EPKStage.judgementGroupAuditPass);
		list.add(EPKStage.waitingForPKUser);
		list.add(EPKStage.waitingForPKUser2);
		list.add(EPKStage.applySuccess);
		list.add(EPKStage.confirmByPKJudgement);
		list.add(EPKStage.confirmByPKJudgement2);
		list.add(EPKStage.setJudgement);
		list.add(EPKStage.setJudgement2);
		list.add(EPKStage.setJudgementBoth);
		list.add(EPKStage.setJudgementChair);
		list.add(EPKStage.refuseByPKJudgement);
		list.add(EPKStage.refuseByPKJudgement2);
		list.add(EPKStage.waitingCreateSeegle);
		return list;
	}
	
	/**
	 * 得到所有申请结束的状态类型（没有refuseByPKJudgement和refuseByPKJudgement2两个类型，这两个类型暂时不用）
	 * @return
	 * @author liubin
	 * @createtime 2016-10-20下午2:58:55
	 * @return List<EPKStage>
	 */
	public static List<EPKStage> getEndApps() {
		List<EPKStage> list = new ArrayList<EPKStage> ();
		list.add(EPKStage.waitingForChallenge);
		list.add(EPKStage.challenging);
		list.add(EPKStage.challengeSuccess);
		list.add(EPKStage.pkuserAbsent);
		list.add(EPKStage.pkuser2Absent);
		list.add(EPKStage.bothAbsent);
		list.add(EPKStage.challengeCancel);
		list.add(EPKStage.challengSeegleOver);
		return list;
	}
	
	/**
	 * 得到申请失败的全部类型
	 * @return
	 * @author chenminchang
	 * @create 2016-8-22下午5:42:50
	 */
	public static List<EPKStage> getAppFaileds() {
		List<EPKStage> list = new ArrayList<EPKStage> ();
		list.add(EPKStage.applyCancel);
		list.add(EPKStage.applyCancel2);
		list.add(EPKStage.applyCancel1);
		list.add(EPKStage.applyFailed);
		list.add(EPKStage.tenDaysNotHandle);
		list.add(EPKStage.fiveDaysNotHandle);
		return list;
	}
	
	/**
	 * 挑战取消所有枚举全部类型
	 * @return
	 * @author chenminchang
	 * @create 2016-8-22下午5:43:01
	 */
	public static List<EPKStage> getCancels() {
		List<EPKStage> list = new ArrayList<EPKStage> ();
		list.add(EPKStage.challengeCancel);
		return list;
	}
	
	/**
	 * 挑战成功所有枚举全部类型
	 * @return
	 * @author liubin
	 * @createtime 2016-10-20下午2:48:08
	 * @return List<EPKStage>
	 */
	public static List<EPKStage> getSuccess() {
		List<EPKStage> list = new ArrayList<EPKStage> ();
		list.add(EPKStage.challengeSuccess);
		return list;
	}
	
	/**
	 * 得到所有挑战结束类型
	 * @return
	 * @author chenminchang
	 * @create 2016-9-1下午4:32:18
	 */
	public static List<EPKStage> getPKOver() {
		List<EPKStage> list = new ArrayList<EPKStage> ();
		list.add(EPKStage.challengeSuccess);
		list.add(EPKStage.pkuserAbsent);
		list.add(EPKStage.pkuser2Absent);
		list.add(EPKStage.bothAbsent);
		return list;
	}
	
	/**
	 * 得到被挑战者未处理的阶段
	 * @return
	 * @author chenminchang
	 * @create 2016-8-23下午7:10:56
	 */
	public static List<EPKStage> getNotHandleFromPKUser2() {
		List<EPKStage> list = new ArrayList<EPKStage> ();
		list.add(EPKStage.init);
		list.add(EPKStage.unpay);
		list.add(EPKStage.applyCancel);
		list.add(EPKStage.payed);
		list.add(EPKStage.judgementGroupAuditPass);
		list.add(EPKStage.applyFailed);
		list.add(EPKStage.waitingForPKUser2);
		return list;
	}
	
	/**
	 * 得到挑战者申请
	 * @return
	 * @author chenminchang
	 * @create 2016-8-24上午9:25:55
	 */
	public static List<EPKStage> getAppingFromPKUser1() {
		List<EPKStage> list = new ArrayList<EPKStage> ();
		list.add(EPKStage.init);
		list.add(EPKStage.unpay);
		list.add(EPKStage.applyCancel);
		list.add(EPKStage.payed);
		list.add(EPKStage.judgementGroupAuditPass);
		list.add(EPKStage.applyFailed);
		return list;
	}
	
	/**
	 * 挑战双方确认
	 * @return
	 * @author chenminchang
	 * @create 2016-8-24上午9:28:26
	 */
	public static List<EPKStage> getPKUsersAccept() {
		List<EPKStage> list = new ArrayList<EPKStage> ();
		list.add(EPKStage.waitingForPKUser2);
		list.add(EPKStage.applyCancel2);
		list.add(EPKStage.tenDaysNotHandle);
		list.add(EPKStage.fiveDaysNotHandle);
		list.add(EPKStage.waitingForPKUser);
		list.add(EPKStage.applyCancel1);
		return list;
	}
	
	/**
	 * 确认双方裁判
	 * @return
	 * @author chenminchang
	 * @create 2016-8-24上午9:30:20
	 */
	public static List<EPKStage> getPKJudgesAccept() {
		List<EPKStage> list = new ArrayList<EPKStage> ();
		list.add(EPKStage.applySuccess);
		list.add(EPKStage.confirmByPKJudgement);
		list.add(EPKStage.confirmByPKJudgement2);
		list.add(EPKStage.setJudgement);
		list.add(EPKStage.setJudgement2);
		list.add(EPKStage.setJudgementChair);
		list.add(EPKStage.refuseByPKJudgement);
		list.add(EPKStage.refuseByPKJudgement2);
		list.add(EPKStage.waitingCreateSeegle);
		list.add(EPKStage.setJudgementChair);
		list.add(EPKStage.setJudgementBoth);
		return list;
	}
	
	/**
	 * 判断传入的stage是不是已取消
	 * @param stage
	 * @return
	 * @author chenminchang
	 * @create 2016-8-22下午6:03:21
	 */
	public static boolean isCancel(EPKStage stage) {
		return getCancels().contains(stage);
	}

	/**
	 * 判断传入的stage是不是申请中
	 * @param stage
	 * @return
	 * @author chenminchang
	 * @create 2016-8-22下午5:48:08
	 */
	public static boolean isApping(EPKStage stage) {
		return getAppings().contains(stage);
	}
	
	/**
	 * 判断传入的stage是不是申请失败
	 * @param stage
	 * @return
	 * @author chenminchang
	 * @create 2016-8-22下午6:01:59
	 */
	public static boolean isAppFailed(EPKStage stage) {
		return getAppFaileds().contains(stage);
	}
	
	/**
	 * 判断传入的stage是不是被挑战者为未操作的阶段
	 * @param stage
	 * @return
	 * @author chenminchang
	 * @create 2016-8-23下午7:14:11
	 */
	public static boolean isNotHandleFromPKUser2(EPKStage stage) {
		return getNotHandleFromPKUser2().contains(stage);
	}
	
	/**
	 * 判断传入的stage是不是挑战者申请阶段
	 * @param stage
	 * @return
	 * @author chenminchang
	 * @create 2016-8-24上午10:10:16
	 */
	public static boolean isAppingFromPKUser1(EPKStage stage) {
		return getAppingFromPKUser1().contains(stage);
	}
	
	/**
	 * 判断传入的stage是不是  挑战双方确认  阶段
	 * @param stage
	 * @return
	 * @author chenminchang
	 * @create 2016-8-24上午10:12:39
	 */
	public static boolean isPKUsersAccept(EPKStage stage) {
		return getPKUsersAccept().contains(stage);
	}
	
	/**
	 * 判断传入的stage是不是  确认双方裁判   阶段
	 * @param stage
	 * @return
	 * @author chenminchang
	 * @create 2016-8-24上午10:12:44
	 */
	public static boolean isPKJudgesAccept(EPKStage stage) {
		return getPKJudgesAccept().contains(stage);
	}
	
	/**
	 * 判断是否是已结束
	 * @param stage
	 * @return
	 * @author chenminchang
	 * @create 2016-9-1下午4:36:17
	 */
	public static boolean isPKOver(EPKStage stage) {
		return getPKOver().contains(stage);
	}
	
	/**
	 * 挑战状态是否已停止，不会再有后续行为
	 * @param stage
	 * @return
	 * @author XieMing
	 * 2016-9-6 下午3:05:36
	 */
	public static boolean isPkStop(EPKStage stage) {
		if(isPKOver(stage) || getAppFaileds().contains(stage) || stage ==  EPKStage.challengeCancel) {
			return true;
		}
		return false;
	}
}
