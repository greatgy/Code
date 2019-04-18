package com.supergenius.xo.sudokuapi.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.sudokuapi.dao.InboxesDao;
import com.supergenius.xo.sudokuapi.entity.Inboxes;
import com.supergenius.xo.sudokuapi.service.InboxesSO;

/**
 * 站内消息收件箱SO实现
 * @author YuYingJie
 */
@Service
public class InboxesSOImpl extends BaseSOImpl<Inboxes> implements InboxesSO {

	@Autowired
	InboxesDao dao;
	
	@Override
	protected BaseDao<Inboxes> getDao() {
		return dao;
	}

}
