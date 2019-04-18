package com.genius.xo.baseadmin.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.model.baseadmin.entity.WorkFlow;
import com.genius.xo.baseadmin.dao.WorkFlowDao;
import com.genius.xo.baseadmin.service.WorkFlowSO;

/**
 * @author liushaomin
 * 
 */
@Service
public class WorkFlowSOImpl extends BaseSOImpl<WorkFlow> implements WorkFlowSO {

	@Autowired
	private WorkFlowDao dao;

	@Override
	protected WorkFlowDao getDao() {
		return dao;
	}
}
