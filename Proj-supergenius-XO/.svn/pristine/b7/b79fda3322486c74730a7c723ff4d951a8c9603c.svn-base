package com.supergenius.xo.solr.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.solr.dao.TermsDao;
import com.supergenius.xo.solr.entity.Terms;
import com.supergenius.xo.solr.service.TermsSO;

/**
 * TermsSOImpl
 * 
 * @author yangguang
 * @date 2017年11月2日18:09:03
 */
@Service
public class TermsSOImpl extends BaseSOImpl<Terms> implements TermsSO {

	@Autowired
	TermsDao dao;

	@Override
	protected BaseDao<Terms> getDao() {
		return dao;
	}

}
