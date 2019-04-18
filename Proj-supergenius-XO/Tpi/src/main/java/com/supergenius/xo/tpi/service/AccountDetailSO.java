package com.supergenius.xo.tpi.service;

import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.tpi.entity.AccountDetail;
import com.supergenius.xo.tpi.entity.TpiUser;
import com.supergenius.xo.tpi.enums.EAccountDetailType;
import com.supergenius.xo.tpi.enums.EAccountState;

/**
 * 
 * @author LiJiacheng
 */
public interface AccountDetailSO extends BaseSO<AccountDetail> {

	/**
	 * 添加一条记录
	 * 
	 * @param project
	 * @param parseDouble
	 * @param uid
	 * @return
	 * @author YuYingJie
	 */
	boolean add(EAccountDetailType detailType, Double money, String useruid, String payuid, String fromuid, EAccountState state);

	/**
	 * 添加一次性付款的记录
	 * 
	 * @param tpiUser
	 * @param money
	 * @return
	 * @author LiJiacheng
	 */
	boolean add(TpiUser tpiUser, String money, String payuid);

	/**
	 * 查询记录
	 * 
	 * @param useruid
	 * @param payuid
	 * @return
	 * @author YuYingJie
	 */
	AccountDetail get(String useruid, String payuid);

	/**
	 * 更新记录
	 * 
	 * @param detail
	 * @param money
	 * @param state
	 * @return
	 * @author YuYingJie
	 */
	boolean update(AccountDetail detail, double money, EAccountState state);

}
