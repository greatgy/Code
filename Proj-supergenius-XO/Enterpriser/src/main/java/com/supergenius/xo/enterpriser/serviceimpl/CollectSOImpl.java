package com.supergenius.xo.enterpriser.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.enterpriser.dao.CollectDao;
import com.supergenius.xo.enterpriser.entity.Collect;
import com.supergenius.xo.enterpriser.service.CollectSO;

/**
 * 收藏so实现
 * 
 * @author YangGuang
 * @date 2018年1月30日11:33:49
 */
@Service("enterpriserCollectSOImpl")
public class CollectSOImpl extends  BaseSOImpl<Collect> implements CollectSO {

	@Autowired
	private CollectDao dao;
	
	@Override
	protected BaseDao<Collect> getDao() {
		// TODO Auto-generated method stub
		return dao;
	}

}
