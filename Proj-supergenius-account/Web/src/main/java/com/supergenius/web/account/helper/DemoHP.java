package com.supergenius.web.account.helper;

import java.util.List;
import java.util.Map;

import com.genius.model.base.entity.Pager;
import com.genius.server.base.helper.BaseHP;
import com.supergenius.xo.account.entity.Account;
import com.supergenius.xo.account.entity.Refund;
import com.supergenius.xo.account.enums.ERefundState;
import com.supergenius.xo.account.enums.EState;
import com.supergenius.xo.account.service.AccountSO;
import com.supergenius.xo.account.service.RefundSO;
import com.supergenius.xo.common.constants.MapperDict;

/**
 * DemoHP
 * @author YangGuang
 */
public class DemoHP extends BaseHP{
	
	private static AccountSO so;
	
	private static RefundSO refundSO;
	
	private static AccountSO getSO() {
		if (so == null) {
			so = (AccountSO) spring.getBean(AccountSO.class);
		}
		return so;
	}
	
	private static RefundSO getRefundSO() {
		if (refundSO == null) {
			refundSO = (RefundSO) spring.getBean(RefundSO.class);
		}
		return refundSO;
	}

	/**
	 * 获得account列表
	 * @author YangGuang
	 */
	public static List<Account> getAccountList() {
		Map<String, Object> map = getParamMap(Pager.getNewInstance(1, 1));
		map.put(MapperDict.state, EState.success);
		return getSO().getList(map);
	}
	
	/**
	 * 获得account列表
	 * @author YangGuang
	 */
	public static List<Refund> getRefundList() {
		Map<String, Object> map = getParamMap(Pager.getNewInstance(1, 1));
		map.put(MapperDict.state, ERefundState.init);
		return getRefundSO().getList(map);
	}

}
