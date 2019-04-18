package com.supergenius.xo.gupage.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.gupage.dao.PatentDao;
import com.supergenius.xo.gupage.entity.Patent;
import com.supergenius.xo.gupage.service.PatentSO;

/**
 * 专利SO实现
 * 
 * @author loupengyu
 * @date 2018年1月10日11:15:22
 */
@Service("gupagePagerSOImpl")
public class PatentSOImpl extends BaseSOImpl<Patent> implements PatentSO {

	@Autowired
	private PatentDao dao;
	
	@Override
	protected BaseDao<Patent> getDao() {
		return dao;
	}

}
