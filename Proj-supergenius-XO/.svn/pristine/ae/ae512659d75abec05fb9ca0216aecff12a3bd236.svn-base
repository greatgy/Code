package com.supergenius.xo.account.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.account.dao.RefundDao;
import com.supergenius.xo.account.entity.Refund;
import com.supergenius.xo.account.service.RefundSO;

/**
 *  退款so的实现
 * @author YangGuang
 */
@Service
public class RefundSOImpl extends BaseSOImpl<Refund> implements RefundSO{

	@Autowired
	private RefundDao dao;
	
	@Override
	protected RefundDao getDao() {
		return dao;
	}

}
