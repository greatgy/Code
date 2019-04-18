package com.supergenius.xo.official.serviceimpl;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.official.constants.MapperDict;
import com.supergenius.xo.official.dao.ContentDao;
import com.supergenius.xo.official.entity.Content;
import com.supergenius.xo.official.enums.EType;
import com.supergenius.xo.official.service.ContentSO;

/**
 * 网站内容SO实现
 * @author LiuXiaoke
 */
@Service("officialContentSOImpl")
public class ContentSOImpl extends BaseSOImpl<Content> implements ContentSO {

	@Autowired
	ContentDao dao;
	
	/*
	 * (non-Javadoc)
	 * @see com.genius.xo.base.serviceimpl.BaseSOImpl#getDao()
	 */
	@Override
	protected BaseDao<Content> getDao() {
		return dao;
	}

	/* (non-Javadoc)
	 * @see com.supergenius.xo.official.service.ContentSO#getOneByType(com.supergenius.xo.official.enums.EType)
	 */
	@Override
	public Content getOneByType(EType type) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.type, type);
		return dao.getOne(map);
	}

}
