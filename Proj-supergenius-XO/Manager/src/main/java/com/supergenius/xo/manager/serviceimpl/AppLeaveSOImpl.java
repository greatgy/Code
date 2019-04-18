package com.supergenius.xo.manager.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.manager.dao.AppLeaveDao;
import com.supergenius.xo.manager.entity.AppLeave;
import com.supergenius.xo.manager.service.AppLeaveSO;

/**
 * 请假记录Impl
 * @author XieMing
 * @date 2016-7-19 上午11:58:27
 */
@Service
public class AppLeaveSOImpl extends BaseSOImpl<AppLeave> implements AppLeaveSO {

	@Autowired
	AppLeaveDao dao;
	
	@Override
	protected BaseDao<AppLeave> getDao() {
		return dao;
	}

}
