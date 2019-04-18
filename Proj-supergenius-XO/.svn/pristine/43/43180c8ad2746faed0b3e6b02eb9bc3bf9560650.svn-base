package com.supergenius.xo.finance.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.finance.dao.CollectDao;
import com.supergenius.xo.finance.entity.Collect;
import com.supergenius.xo.finance.service.CollectSO;

/**
 * 收藏so实现
 * 
 * @author LouPengYu
 * @date 2018年1月2日09:58:13
 */
@Service("financeCollectSOImpl")
public class CollectSOImpl extends  BaseSOImpl<Collect> implements CollectSO {

	@Autowired
	private CollectDao dao;
	
	@Override
	protected BaseDao<Collect> getDao() {
		// TODO Auto-generated method stub
		return dao;
	}

}
