package com.supergenius.xo.sudokuapi.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.sudokuapi.dao.RechargesDao;
import com.supergenius.xo.sudokuapi.entity.Recharges;
import com.supergenius.xo.sudokuapi.service.RechargesSO;

/**
 * 充值记录SO实现
 * 
 * @author YangGuang
 */
@Service
public class RechargesSOImpl extends BaseSOImpl<Recharges> implements RechargesSO {

	@Autowired
	RechargesDao rechargesDao;

	@Override
	protected BaseDao<Recharges> getDao() {
		return rechargesDao;
	}

}
