package com.supergenius.xo.moralnews.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.genius.xo.baseadmin.service.AdminLogSO;
import com.supergenius.xo.moralnews.dao.ContentDao;
import com.supergenius.xo.moralnews.entity.Content;
import com.supergenius.xo.moralnews.service.ContentSO;

/**
 * 内容SO实现
 * 
 * @author JiaShitao
 * @date 2018年9月19日09:47:55
 */
@Service("moralnewsContentSOImpl")
public class ContentSOImpl extends BaseSOImpl<Content> implements ContentSO {

	@Autowired
	private ContentDao dao;
	
	@Autowired
	private AdminLogSO adminlogSO;

	@Override
	protected BaseDao<Content> getDao() {
		return dao;
	}

	@Override
	public boolean add(Content content, AdminLog adminLog) {
		return dao.insert(content) && adminlogSO.add(adminLog);
	}

	@Override
	public boolean update(Content content, AdminLog adminLog) {
		return dao.update(content) && adminlogSO.add(adminLog);
	}
}
