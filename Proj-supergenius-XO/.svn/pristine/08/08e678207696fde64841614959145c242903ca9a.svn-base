package com.supergenius.xo.career.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.career.dao.CollectDao;
import com.supergenius.xo.career.entity.Collect;
import com.supergenius.xo.career.service.CollectSO;

/**
 * CollectSOImpl
 * 
 * @author ChenQi
 * @date 2017年11月13日16:40:36
 */
@Service("careerCollectSOImpl")
public class CollectSOImpl extends BaseSOImpl<Collect> implements CollectSO {

	@Autowired
	CollectDao dao;

	@Override
	protected BaseDao<Collect> getDao() {
		return dao;
	}

}
