package com.supergenius.xo.account.serviceimpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.model.base.entity.Pager;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.account.dao.AccountDao;
import com.supergenius.xo.account.entity.Account;
import com.supergenius.xo.account.enums.EState;
import com.supergenius.xo.account.service.AccountSO;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.common.enums.ECharge;

/**
 *  充值so的实现
 * @author liushaomin
 */
@Service
public class AccountSOImpl extends BaseSOImpl<Account> implements AccountSO{

	@Autowired
	private AccountDao dao;
	
	@Override
	protected AccountDao getDao() {
		return dao;
	}

	/* (non-Javadoc)
	 * @see com.supergenius.xo.account.service.AccountSO#getOneByPayuid(java.lang.String)
	 */
	@Override
	public Account getOneByPayuid(String payuid) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.payuid, payuid);
		return dao.getOne(map);
	}

	/*
	 * (non-Javadoc)
	 * @see com.supergenius.xo.account.service.AccountSO#getMoneyByType(com.supergenius.xo.account.enums.EType)
	 */
	@Override
	public double getMoneyByType(ECharge type) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.type, type);
		Double money = dao.getSumMoney(map);
		if (money == null) {
			return 0;
		}
		return money;
	}

	/*
	 * (non-Javadoc)
	 * @see com.supergenius.xo.account.service.AccountSO#getList(java.lang.String[])
	 */
	@Override
	public List<Account> getList(String[] accountsn) {
		List<String> list = new ArrayList<>(Arrays.asList(accountsn));
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.sns, list);
		return dao.getList(map);
	}
	
	@Override
	public int getCount(String useruid) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.useruid, useruid);
		map.put(MapperDict.type, ECharge.recharge);
		return dao.getCount(map);
	}

	@Override
	public List<Account> getList(String useruid, Pager pager) {
		Map<String, Object> map = getParamMap(pager);
		map.put(MapperDict.useruid, useruid);
		map.put(MapperDict.type, ECharge.recharge);
		map.put(MapperDict.state, EState.success);
		return dao.getList(map);
	}

}
