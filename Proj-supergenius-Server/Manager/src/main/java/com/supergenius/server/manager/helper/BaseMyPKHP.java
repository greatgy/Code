package com.supergenius.server.manager.helper;

import com.genius.server.base.helper.BaseHP;
import com.supergenius.server.user.helper.AutoIncrHP;
import com.supergenius.server.user.helper.BaseUserHP;
import com.supergenius.xo.common.enums.ESite;
import com.supergenius.xo.manager.entity.PKSchedule;
import com.supergenius.xo.manager.entity.PKStateDetail;
import com.supergenius.xo.manager.service.JudgeSO;
import com.supergenius.xo.manager.service.PkScheduleSO;
import com.supergenius.xo.user.entity.TradeDetail;
import com.supergenius.xo.user.entity.User;
import com.supergenius.xo.user.enums.ETrade;

/**
 * 与pk相关的交易明细及订单变动HP
 * @author chenminchang
 * @date 2016-9-5 下午8:08:53
 */
public class BaseMyPKHP extends BaseHP {

	private static PkScheduleSO so;
	private static JudgeSO judgeSO;

	private static PkScheduleSO getSO() {
		if (so == null) {
			so = spring.getBean(PkScheduleSO.class);
		}
		return so;
	}

	private static JudgeSO getJudgeSO() {
		if (judgeSO == null) {
			judgeSO = spring.getBean(JudgeSO.class);
		}
		return judgeSO;
	}

	/**
	 * 得到挑战者
	 * @param pkSchedule
	 * @return
	 * @author chenminchang
	 * @create 2016-9-7下午4:08:42
	 */
	public static User getPKUser1(PKSchedule pkSchedule) {
		return BaseUserHP.getFromRedis(pkSchedule.getPkuseruid());
	}
	
	/**
	 * 得到被挑战者
	 * @param pkSchedule
	 * @return
	 * @author chenminchang
	 * @create 2016-9-7下午4:08:42
	 */
	public static User getPKUser2(PKSchedule pkSchedule) {
		return BaseUserHP.getFromRedis(pkSchedule.getPkuseruid2());
	}
	
	/**
	 * 得到挑战者裁判
	 * @param pkSchedule
	 * @return
	 * @author chenminchang
	 * @create 2016-9-7下午4:08:42
	 */
	public static User getPKJudge1(PKSchedule pkSchedule) {
		if (pkSchedule.getJudgementuid() != null)
			return BaseUserHP.getFromRedis(pkSchedule.getJudgementuid());
		return null;
	}
	
	/**
	 * 得到被挑战者裁判
	 * @param pkSchedule
	 * @return
	 * @author chenminchang
	 * @create 2016-9-7下午4:08:42
	 */
	public static User getPKJudge2(PKSchedule pkSchedule) {
		if (pkSchedule.getJudgementuid2() != null)
			return BaseUserHP.getFromRedis(pkSchedule.getJudgementuid2());
		return null;
	}
	
	/**
	 * 得到被挑战者裁判
	 * @param pkSchedule
	 * @return
	 * @author chenminchang
	 * @create 2016-9-7下午4:08:42
	 */
	public static User getPKChairJudge(PKSchedule pkSchedule) {
		if (pkSchedule.getJudgementchairsn() != null) {
			String useruid = getJudgeSO().getOneBySn(pkSchedule.getJudgementchairsn()).getUseruid();
			return BaseUserHP.getFromRedis(useruid);
		}
		return null;
	}
	
	/**
	 * 退回挑战费，修改订单和交易明细
	 * 
	 * @param pkSchedule
	 * @return
	 * @author chenminchang
	 * @create 2016-9-5下午8:16:58
	 */
	public static boolean returnPKFee(PKSchedule pkSchedule, String orderLogName, PKStateDetail pkStateDetail) {
		User user = getPKUser1(pkSchedule);
		TradeDetail tradeDetail = new TradeDetail(user.getUid(), pkSchedule.getUid(), pkSchedule.getPkprice(), user.getAccount(), AutoIncrHP.getTradeDetailsn(), null,
				ETrade.unfreezeChallengefee, ETrade.unfreezeChallengefee.getName(), ESite.manager);
		return getSO().updateRetuenPKFee(user, pkSchedule, tradeDetail, pkStateDetail);
	}

	/**
	 * 支付挑战费
	 * 
	 * @param pkSchedule
	 * @param orderLogName
	 * @return
	 * @author chenminchang
	 * @create 2016-9-6上午11:52:45
	 */
	public static boolean payPKFee(PKSchedule pkSchedule, String orderLogName, PKStateDetail pkStateDetail) {
		User user = getPKUser1(pkSchedule);
		TradeDetail tradeDetail = new TradeDetail(user.getUid(), pkSchedule.getUid(), pkSchedule.getPkprice(), user.getAccount(), AutoIncrHP.getTradeDetailsn(), null, ETrade.paypk,
				ETrade.paypk.getName(), ESite.manager);
		return getSO().updatePayPKFee(user, pkSchedule, tradeDetail, pkStateDetail);
	}
	
}
