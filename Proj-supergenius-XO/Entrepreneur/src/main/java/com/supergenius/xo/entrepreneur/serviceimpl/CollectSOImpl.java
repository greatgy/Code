package com.supergenius.xo.entrepreneur.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.entrepreneur.dao.CollectDao;
import com.supergenius.xo.entrepreneur.entity.Collect;
import com.supergenius.xo.entrepreneur.service.CollectSO;

/**
 * 收藏so实现
 * 
 * @author 雍雪振
 * @date 2018年1月2日09:58:13
 */
@Service("entrepreneurCollectSOImpl")
public class CollectSOImpl extends BaseSOImpl<Collect> implements CollectSO {

	@Autowired
	private CollectDao dao;

	@Override
	protected BaseDao<Collect> getDao() {
		// TODO Auto-generated method stub
		return dao;
	}
}