package com.supergenius.xo.account.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.account.dao.AccountlogDao;
import com.supergenius.xo.account.entity.AccountLog;
import com.supergenius.xo.account.service.AccountLogSO;

/**
 * 充值日志so的实现
 * @author liushaomin
 */
@Service
public class AccountLogSOImpl extends BaseSOImpl<AccountLog> implements AccountLogSO{
	
	@Autowired
	private AccountlogDao dao;
	
	@Override
	protected AccountlogDao getDao() {
		return dao;
	}
}
