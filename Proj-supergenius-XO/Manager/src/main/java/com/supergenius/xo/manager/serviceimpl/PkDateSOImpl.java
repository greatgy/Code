package com.supergenius.xo.manager.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.manager.dao.PkDateDao;
import com.supergenius.xo.manager.entity.PKDate;
import com.supergenius.xo.manager.service.PkDateSO;

/**
 * 挑战时间SOImpl
 * @author XieMing
 * @date 2016-4-29 下午3:19:36
 */
@Service
public class PkDateSOImpl extends BaseSOImpl<PKDate> implements PkDateSO {

	@Autowired
	PkDateDao dao;

	@Override
	protected BaseDao<PKDate> getDao() {
		return dao;
	}
	
}
