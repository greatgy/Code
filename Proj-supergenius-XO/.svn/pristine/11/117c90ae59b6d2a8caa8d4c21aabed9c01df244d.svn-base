package com.supergenius.xo.life.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.life.dao.CollectDao;
import com.supergenius.xo.life.entity.Collect;
import com.supergenius.xo.life.service.CollectSO;

/**
 * 收藏so实现
 * 
 * @author YangGuang
 * @date 2018年5月15日15:00:22
 */
@Service("lifeCollectSOImpl")
public class CollectSOImpl extends  BaseSOImpl<Collect> implements CollectSO {

	@Autowired
	private CollectDao dao;
	
	@Override
	protected BaseDao<Collect> getDao() {
		// TODO Auto-generated method stub
		return dao;
	}

}
