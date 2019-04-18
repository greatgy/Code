/**
 * ============================================================================
 * 声明：© 2013-2014 天才纵横版权所有
 * ----------------------------------------------------------------------------
 * Official Website: http://www.grandgeniusgroup.com
 * ----------------------------------------------------------------------------
 * Copyright: © 2013 grandgeniusgroup All Rights Reserved.
 * ----------------------------------------------------------------------------
 * @version: 1.0
 * ----------------------------------------------------------------------------
 * @author: Architect.bian
 * ----------------------------------------------------------------------------
 * Create at: 2013-7-14 下午7:37:42
 * ============================================================================
 */
package com.supergenius.xo.user.enums;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 交易类型
 * @author chenminchang
 */
public enum ETrade {

	//如果新增收入类型需要在  getIsIncome()方法中增加这一类型
	recharge("0"), //充值
	sellvideo("1"), //视频收入
	paypk("2"),//扣除挑战费
	judgement("3"), //裁判收入
	payvideo("4"),//购买视频
	ticket("5"),// 购买门票
	cantelticket("6"),// 取消门票
	adminCancelTicket("7"),// 管理员退票
	appreply("8"),//申请答辩
	secAppReply("14"),//二次答辩
	freezeChallengefee("9"),//冻结挑战费
	unfreezeChallengefee("10"),//解冻挑战费
	challengetowin("11"),//挑战获胜
	especiallyadd("12"),//特批增加金额
	especiallycut("13"),//特批减少金额
	levelUpByScore("15"),//积分晋级  
	participate("16");//企业家培训讲座报名

	private final String value;

	private ETrade(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}

	public static ETrade get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static ETrade get(String str) {
		for (ETrade e : values()) {
			if (e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}

	public String getName() {
		return I18N.getEnumName(this, Locale.CHINA);
	}
	
	//获得收入类型list
	public static List<ETrade> getIsIncome() {
		List<ETrade> list = new ArrayList<>();
		list.add(ETrade.sellvideo);
		list.add(ETrade.judgement);
		list.add(ETrade.cantelticket);
		list.add(ETrade.adminCancelTicket);
		list.add(ETrade.unfreezeChallengefee);
		list.add(ETrade.challengetowin);
		list.add(ETrade.especiallyadd);
		return list;
	}
	
	public static boolean isIncome(ETrade type) {
		return getIsIncome().contains(type);
	}
}
