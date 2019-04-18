package com.supergenius.xo.account.service;

import java.util.List;

import com.genius.model.base.entity.Pager;
import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.account.entity.Account;
import com.supergenius.xo.common.enums.ECharge;

/**
 * 充值so
 * @author liushaomin
 */
public interface AccountSO extends BaseSO<Account>{

	/**
	 * 根据payuid获取数据
	 * @param payuid
	 * @return
	 * @author liushaomin
	 */
	Account getOneByPayuid(String payuid);

	/**
	 * 根据类型获取总金额
	 * @param type
	 * @return
	 * @author YuYingJie
	 */
	double getMoneyByType(ECharge type);

	/**
	 * 通过accountsn查询Account
	 * @param accountsn
	 * @return
	 * @author YuYingJie
	 */
	List<Account> getList(String[] accountsn);
	
	/**
	 * 根据useruid查询总记录数
	 * @param useruid
	 * @return
	 */
	int getCount(String useruid);
	
	/**
	 * 根据useruid和pager查询
	 * @param useruid
	 * @param pager
	 * @return
	 */
	List<Account> getList(String useruid, Pager pager);
	
}
