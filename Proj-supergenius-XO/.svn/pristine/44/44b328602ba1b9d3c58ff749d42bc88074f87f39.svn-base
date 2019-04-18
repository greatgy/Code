package com.supergenius.xo.moralnews.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.moralnews.dao.CollectDao;
import com.supergenius.xo.moralnews.entity.Collect;
import com.supergenius.xo.moralnews.service.CollectSO;

/**
 * 收藏so实现
 * 
 * @author JiaShitao
 * @date 2018年9月19日09:47:55
 */
@Service("moralnewsCollectSOImpl")
public class CollectSOImpl extends BaseSOImpl<Collect> implements CollectSO {

	@Autowired
	private CollectDao dao;

	@Override
	protected BaseDao<Collect> getDao() {
		// TODO Auto-generated method stub
		return dao;
	}
}
