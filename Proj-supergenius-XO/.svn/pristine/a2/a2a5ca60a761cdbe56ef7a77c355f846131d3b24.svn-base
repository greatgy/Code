package com.supergenius.xo.life.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.life.dao.ComplaintDao;
import com.supergenius.xo.life.entity.Complaint;
import com.supergenius.xo.life.service.ComplaintSO;

/**
 * 投诉举报Impl
 * @author YangGuang
 * @date 2018年5月9日16:48:27
 */
@Service("lifeComplaintSOImpl")
public class ComplaintSOImpl extends BaseSOImpl<Complaint> implements ComplaintSO {

	@Autowired
	ComplaintDao dao;
	
	
	@Override
	protected ComplaintDao getDao() {
		return dao;
	}

}
