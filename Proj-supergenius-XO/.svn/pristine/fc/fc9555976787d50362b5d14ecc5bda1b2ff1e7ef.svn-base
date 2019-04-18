package com.supergenius.xo.manager.enums;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 晋级枚举类
 * 
 * @author guanshiqian
 * @date 2016-4-29 下午1:26:10
 */
public enum ELevelChannel {

	// *********** 学员 ***********
	challenge("0"), // 挑战晋级
	scoreLevel("1"), // 积分晋级
	replyLevel("2"), // 答辩晋级
	totallFailLevel("3"), // 累计失败5场(被挑战者)
	specialLevel("4"), // 特批级别
	//challengeCancel("5"), // 连续拒绝十场挑战(被挑战者) TODO 和产品确认后是没有这个需求
	appStudent("6"),//申请学员（正常）
	specialStudent("7"),//特批学员
	inviteStudent("8"),//特邀学员

	// *********** 裁判 ***********
	appJudgement("40"), // 申请裁判
	fulltimeJudgement("41"), // 专职裁判
	specialInviteJudgement("42"), // 特聘裁判
	specialJudgement("43"), // 特批裁判
	judgementPunish("44"), // 被举报取消裁判资格
	quitJudgement("45"), // 退出裁判

	// *********** 专家 ***********
	appExpert("80"), // 申请专家
	fulltimeExpert("81"), // 专职专家
	specialInviteExpert("82"), // 特聘专家
	specialExpert("83"), // 特批专家
	expertPunish("84"), // 被举报取消专家资格
	quitExpert("85");// 退出专家

	private final String value;

	private ELevelChannel(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}

	public static ELevelChannel get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static ELevelChannel get(String str) {
		for (ELevelChannel e : values()) {
			if (e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}
	
	public static ELevelChannel getByName(String str) {
		for (ELevelChannel e : values()) {
			if (e.name().equals(str)) {
				return e;
			}
		}
		return null;
	}

	public String getName() {
		return I18N.getEnumName(this, Locale.CHINA);
	}
	
	/**
	 * 得到成为学员的type
	 * @return
	 * @author chenminchang
	 * @create 2016-11-1上午11:01:31
	 */
	public static List<ELevelChannel> getBeComeStudentType() {
		List<ELevelChannel> list = new ArrayList<>();
		list.add(ELevelChannel.appStudent);
		list.add(ELevelChannel.specialStudent);
		list.add(ELevelChannel.inviteStudent);
		return list;
	}
	
	/**
	 * 判断是不是成为学员的类型
	 * @param channel
	 * @return
	 * @author chenminchang
	 * @create 2016-11-1上午11:03:55
	 */
	public static boolean isBeComeStudentType(ELevelChannel channel) {
		for (ELevelChannel e : getBeComeStudentType()) {
			if (e.toString().equals(channel)) {
				return true;
			}
		}
		return false;
	}
}
