package com.supergenius.xo.sudokuapi.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.sudokuapi.dao.PurchaserecordsDao;
import com.supergenius.xo.sudokuapi.entity.Purchaserecords;
import com.supergenius.xo.sudokuapi.service.PurchaserecordsSO;

/**
 * 购买记录SO实现
 * 
 * @author YangGuang
 */
@Service
public class PurchaserecordsSOImpl extends BaseSOImpl<Purchaserecords> implements PurchaserecordsSO {

	@Autowired
	PurchaserecordsDao purchaserecordsDao;

	@Override
	protected BaseDao<Purchaserecords> getDao() {
		return purchaserecordsDao;
	}

}
