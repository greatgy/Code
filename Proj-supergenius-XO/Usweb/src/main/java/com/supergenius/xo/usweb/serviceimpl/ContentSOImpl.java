package com.supergenius.xo.usweb.serviceimpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.core.base.utils.StrUtil;
import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.usweb.dao.ContentDao;
import com.supergenius.xo.usweb.entity.Content;
import com.supergenius.xo.usweb.service.ContentSO;

/**
 * 内容SO实现
 * 
 * @author JiaShitao
 * @date 2018年9月19日09:47:55
 */
@Service("uswebContentSOImpl")
public class ContentSOImpl extends BaseSOImpl<Content> implements ContentSO {

	@Autowired
	private ContentDao dao;

	@Override
	protected BaseDao<Content> getDao() {
		return dao;
	}

	@Override
	public boolean add(Content content) {
		return dao.insert(content);
	}

	@Override
	public boolean update(Content content) {
		return dao.update(content);
	}

	@Override
	public Content getContentByName(String name) {
		if (!StrUtil.isEmpty(name)) {
			Map<String, Object> map = getParamMap();
			map.put(MapperDict.name, name);
			return dao.getOne(map);
		}
		return null;
	}
}