package com.supergenius.xo.user.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.user.dao.VisitorDao;
import com.supergenius.xo.user.entity.Visitor;
import com.supergenius.xo.user.service.VisitorSO;

/**
 * VisitorSOImpl
 * 
 * @author xuzhixiang
 * @date 2017年8月2日15:34:40
 */
@Service
public class VisitorSOImpl extends BaseSOImpl<Visitor> implements VisitorSO {
	
	@Autowired
	VisitorDao dao;
	
	@Override
	protected BaseDao<Visitor> getDao() {
		return dao;
	}

}