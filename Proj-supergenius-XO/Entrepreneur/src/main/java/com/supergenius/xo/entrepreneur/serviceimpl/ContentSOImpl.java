package com.supergenius.xo.entrepreneur.serviceimpl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.model.base.enums.EStatus;
import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.genius.xo.baseadmin.service.AdminLogSO;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.entrepreneur.dao.ContentDao;
import com.supergenius.xo.entrepreneur.entity.Content;
import com.supergenius.xo.entrepreneur.service.ContentSO;

/**
 * 内容SO实现
 * 
 * @author tf
 * @date 2018年7月5日
 */
@Service("entrepreneurContentSOImpl")
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

	@Override
	public boolean updateStatusByUid(String uid) {
		Content content = dao.get(uid);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.uid, uid);
		if (content.getStatus() == EStatus.disable) {
			map.put(MapperDict.status, EStatus.enable);
		} else {
			map.put(MapperDict.status, EStatus.disable);
		}
		return dao.updateFields(map);
	}
}