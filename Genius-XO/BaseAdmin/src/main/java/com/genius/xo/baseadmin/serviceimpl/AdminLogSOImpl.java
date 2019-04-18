package com.genius.xo.baseadmin.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.genius.xo.baseadmin.dao.AdminLogDao;
import com.genius.xo.baseadmin.service.AdminLogSO;

/**
 * @author liushaomin
 * @createtime 2014-10-15 下午5:24:10
 */
@Service
public class AdminLogSOImpl extends BaseSOImpl<AdminLog> implements AdminLogSO {

	@Autowired
	private AdminLogDao dao;

	@Override
	protected AdminLogDao getDao() {
		return dao;
	}

}
