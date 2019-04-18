package com.supergenius.xo.admin.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.admin.dao.EmailLogDao;
import com.supergenius.xo.admin.entity.EmailLog;
import com.supergenius.xo.admin.service.EmailLogSO;

/**
 * 群发邮件SOImpl
 * @author XieMing
 * @date 2016-5-23 下午2:43:40
 */
@Service
public class EmailLogSOImpl extends BaseSOImpl<EmailLog> implements EmailLogSO {

	@Autowired
	private EmailLogDao dao;
	
	@Override
	protected BaseDao<EmailLog> getDao() {
		return dao;
	}

}
