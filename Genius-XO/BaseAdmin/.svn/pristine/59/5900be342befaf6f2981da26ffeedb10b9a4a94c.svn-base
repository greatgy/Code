package com.genius.xo.baseadmin.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.model.baseadmin.entity.WorkOrder;
import com.genius.xo.baseadmin.dao.WorkOrderDao;
import com.genius.xo.baseadmin.service.WorkOrderSO;

/**
 * @author liushaomin
 * 
 */
@Service
public class WorkOrderSOImpl extends BaseSOImpl<WorkOrder> implements WorkOrderSO {

	@Autowired
	private WorkOrderDao dao;

	@Override
	protected WorkOrderDao getDao() {
		return dao;
	}
}
