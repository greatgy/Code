package com.genius.xo.baseadmin.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.model.baseadmin.entity.StatusLog;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.genius.xo.baseadmin.dao.StatusLogDao;
import com.genius.xo.baseadmin.service.StatusLogSO;

/**
 * @author liushaomin
 * 
 */
@Service
public class StatusLogSOImpl extends BaseSOImpl<StatusLog> implements StatusLogSO {

	@Autowired
	private StatusLogDao dao;

	@Override
	protected StatusLogDao getDao() {
		return dao;
	}

}
