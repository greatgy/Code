package com.supergenius.xo.manager.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 会议室成员用户类型枚举
 * @author XieMing
 * @date 2016-4-29 下午7:35:03
 */
public enum EConfemember {
	
	//挑战过程中
	challenger("0"), //挑战者
	negativeSide("1"), //被挑战者
	challengerJudgment("2"),//挑战者裁判
	negativeSideJudgment("3"),//被挑战者裁判
	chiefUmpire("4"), //主裁判
	visitor("5"),//游客
	//申请答辩和申请裁判过程中    、、、
	student("6"),// 学员
	appReply("8"), //答辩开题专家
	appJudgment("9"),//申请裁判过程中的（裁判或专家）
	admin("7"), //后台添加（申请答辩、申请裁判、申请挑战）管理员
	specialVisitor("10");//后台添加观众（邀请观众）
	
	private final String value;
	
	private EConfemember(String v) {
		this.value = v;
	}
	
	public String toString() {
		return this.value;
	}

	public static EConfemember get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static EConfemember get(String str) {
		for (EConfemember e : values()) {
			if(e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}

	public String getName() {
		return I18N.getEnumName(this, Locale.CHINA);
	}
}
