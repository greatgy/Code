package com.supergenius.xo.finance.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.finance.dao.SubscribeDao;
import com.supergenius.xo.finance.entity.Subscribe;
import com.supergenius.xo.finance.service.SubscribeSO;

/**
 * 订阅so实现
 * 
 * @author LouPengYu
 * @date 2018年1月2日09:55:19
 */
@Service("financeSubscribeSOImpl")
public class SubscribeSOImpl extends BaseSOImpl<Subscribe> implements SubscribeSO {

	@Autowired
	private SubscribeDao dao;

	@Override
	protected BaseDao<Subscribe> getDao() {
		// TODO Auto-generated method stub
		return dao;
	}

	
}
