package com.supergenius.xo.managernews.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.managernews.dao.CollectDao;
import com.supergenius.xo.managernews.entity.Collect;
import com.supergenius.xo.managernews.service.CollectSO;

/**
 * 收藏so实现
 * 
 * @author 雍雪振
 * @date 2018年1月2日09:58:13
 */
@Service("managernewsCollectSOImpl")
public class CollectSOImpl extends  BaseSOImpl<Collect> implements CollectSO {

	@Autowired
	private CollectDao dao;
	
	@Override
	protected BaseDao<Collect> getDao() {
		// TODO Auto-generated method stub
		return dao;
	}

}
