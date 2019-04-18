package com.supergenius.xo.manager.serviceimpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.manager.dao.ContentDao;
import com.supergenius.xo.manager.entity.Content;
import com.supergenius.xo.manager.enums.EContent;
import com.supergenius.xo.manager.service.ContentSO;

/**
 * 内容SOImpl
 *@author chenzhixing
 */
@Service("managerContentSOImpl")
public class ContentSOImpl extends BaseSOImpl<Content> implements ContentSO {

	@Autowired
	ContentDao dao;

	@Override
	protected ContentDao getDao() {
		return dao;
	}

	@Override
	public Content getOneByType(EContent type) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.type, type);
		return dao.getOne(map);
	}

	@Override
	public boolean updateContent(String uid, String content) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.uid, uid);
		map.put(MapperDict.content, content);
		return dao.updateFields(map);
	}

}
