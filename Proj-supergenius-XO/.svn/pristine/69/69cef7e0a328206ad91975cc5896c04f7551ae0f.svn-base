package com.supergenius.xo.user.serviceimpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.user.dao.ContentDao;
import com.supergenius.xo.user.entity.Content;
import com.supergenius.xo.user.enums.EContent;
import com.supergenius.xo.user.service.ContentSO;

/** 
 *内容SOImpl
 * @author guanshiqian
 * @date 2016-4-15 下午6:02:20 
 */
@Service
public class ContentSOImpl extends BaseSOImpl<Content> implements ContentSO {

	@Autowired
	private ContentDao dao;
	
	@Override
	protected BaseDao<Content> getDao() {
		return dao;
	}

	/*
	 * (non-Javadoc)
	 * @see com.supergenius.xo.user.service.ContentSO#getOneByType(com.supergenius.xo.user.enums.EContent)
	 */
	@Override
	public Content getOneByType(EContent type) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.type, type);
		return dao.getOne(map);
	}
	
}
