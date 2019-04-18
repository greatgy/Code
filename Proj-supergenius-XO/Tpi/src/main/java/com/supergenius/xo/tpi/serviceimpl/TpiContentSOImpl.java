package com.supergenius.xo.tpi.serviceimpl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.tpi.constants.MapperDict;
import com.supergenius.xo.tpi.dao.TpiContentDao;
import com.supergenius.xo.tpi.entity.TpiContent;
import com.supergenius.xo.tpi.enums.EContent;
import com.supergenius.xo.tpi.service.TpiContentSO;

/**
 *  网站内容
 * @author liushaomin
 */
@Service
public class TpiContentSOImpl extends BaseSOImpl<TpiContent> implements TpiContentSO{
	
	@Autowired
	TpiContentDao dao;

	/* (non-Javadoc)
	 * @see com.genius.xo.base.serviceimpl.BaseSOImpl#getDao()
	 */
	@Override
	protected BaseDao<TpiContent> getDao() {
		return dao;
	}

	/* (non-Javadoc)
	 * @see com.supergenius.xo.tpi.service.TpiContentSO#getType(com.supergenius.xo.tpi.enums.EContent)
	 */
	@Override
	public TpiContent getType(EContent type) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.type, type);
		return dao.getOne(map);
	}

}
